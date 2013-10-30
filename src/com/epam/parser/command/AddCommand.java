package com.epam.parser.command;

import java.io.IOException;
import java.io.PrintWriter;
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

public final class AddCommand implements ICommand {
	private static final Logger logger = Logger.getLogger(AddCommand.class);
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String xmlPath = ResourceManager.INSTANCE
				.getPropertyRealPath("xml_path");
		String addPageXsltPath = ResourceManager.INSTANCE
				.getPropertyRealPath("addpage_xslt");

		try (PrintWriter out = response.getWriter();){
			// Generate the transformer.
			Transformer transformer = StylesheetCache
					.newTransformer(addPageXsltPath);

			String parametrizedName = request
					.getParameter(AttributeHandler.ITEM);
			String category = request
					.getParameter(AttributeHandler.PRODUCT_CATEGORY);
			String subcategory = request
					.getParameter(AttributeHandler.PRODUCT_SUBCATEGORY);
			if (parametrizedName != null) {
				transformer.setParameter(AttributeHandler.PARAMETRIZED_NAME,
						parametrizedName);
			}
			if (category != null) {
				transformer.setParameter(AttributeHandler.PRODUCT_CATEGORY,
						category);
			}
			if (subcategory != null) {
				transformer.setParameter(AttributeHandler.PRODUCT_SUBCATEGORY,
						subcategory);
			}
			transformer.setParameter("validator", new GoodsValidator());
			transformer.setParameter("good", new Good());
			// Perform the transformation, sending the output to the response.
			lock.readLock().lock();
			try {
				Source xmlSource = new StreamSource(
						new URL("file", "", xmlPath).openStream());
				transformer.transform(xmlSource, new StreamResult(out));
			} finally {
				lock.readLock().unlock();
			}
		} catch(TransformerException e) {
			logger.error("Error in executing add page command during transformation", e);
		}catch (IOException e) {
			logger.error("Error in executing add page command during stream creation", e);
		}
	}

}
