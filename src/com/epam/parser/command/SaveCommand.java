package com.epam.parser.command;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.log4j.Logger;

import com.epam.parser.command.util.AttributeHandler;
import com.epam.parser.entity.Good;
import com.epam.parser.util.ResourceManager;
import com.epam.parser.util.StylesheetCache;
import com.epam.parser.validation.GoodsValidator;

public final class SaveCommand implements ICommand {
	private static final Logger logger = Logger.getLogger(SaveCommand.class);
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String xmlPath = ResourceManager.INSTANCE
				.getPropertyRealPath("xml_path");
		String addXsltPath = ResourceManager.INSTANCE
				.getPropertyRealPath("add_xslt");

		try (PrintWriter out = response.getWriter();) {
			String category = request.getParameter(AttributeHandler.CATEGORY);
			String subcategory = request
					.getParameter(AttributeHandler.SUBCATEGORY);
			String producer = request.getParameter(AttributeHandler.PRODUCER);
			String issueDate = request
					.getParameter(AttributeHandler.ISSUE_DATE);
			String model = request.getParameter(AttributeHandler.MODEL);
			String color = request.getParameter(AttributeHandler.COLOR);
			String price = request.getParameter(AttributeHandler.PRICE);
			String notInStockString = request
					.getParameter(AttributeHandler.NOT_IN_STOCK);
			boolean notInStock = false;

			if ("on".equals(notInStockString)) {
				notInStock = true;
			}
			GoodsValidator validator = new GoodsValidator();
			if (!validator.validatePriceString(price)) {
				price = "0";
			}
			Good good = new Good(producer, model, issueDate, color, notInStock,
					Integer.parseInt(price));

			Transformer transformer = StylesheetCache
					.newTransformer(addXsltPath);
			transformer.setParameter(AttributeHandler.PRODUCT_CATEGORY,
					category);
			transformer.setParameter(AttributeHandler.PRODUCT_SUBCATEGORY,
					subcategory);
			transformer.setParameter(AttributeHandler.VALIDATOR, validator);
			transformer.setParameter(AttributeHandler.GOOD, good);
			
			Source xmlSource;
			StreamResult streamResult = new StreamResult(new StringWriter());
			File outputFile = new File(xmlPath);
			long lastModified;
			
			lock.readLock().lock();
			try {
				xmlSource = new StreamSource(
						new URL("file", "", xmlPath).openStream());
				transformer.transform(xmlSource, streamResult);
				lastModified = outputFile.lastModified();
			} finally {
				lock.readLock().unlock();
			}
			String xmlString = streamResult.getWriter().toString();

			if (validator.isProductValid()) {
				lock.writeLock().lock();
				try (Writer bw = new BufferedWriter(new OutputStreamWriter(
						new FileOutputStream(outputFile)));) {
					// check if file was modified between transform to result
					// and writing result
					if (lastModified < outputFile.lastModified()) {
						outputFile = new File(xmlPath);
						xmlSource = new StreamSource(new URL("file", "",
								xmlPath).openStream());
						//Product was validated before so no need to call validator again
						transformer.setParameter("isAlreadyValidated", true);
						transformer.transform(xmlSource, streamResult);
						xmlString = streamResult.getWriter().toString();
					}
					bw.write(xmlString);
				} finally {
					lock.writeLock().unlock();
				}
				response.sendRedirect(ResourceManager.INSTANCE.getProperty("categories_page_pt1")+ subcategory+ ResourceManager.INSTANCE.getProperty("categories_page_pt2") + category);
			} else {
				out.print(xmlString);
			}

		} catch (IOException e) {
			logger.error("IOException error in save command");
		} catch (TransformerException e) {
			logger.error("Transformation error in save command");
		}
	}

}
