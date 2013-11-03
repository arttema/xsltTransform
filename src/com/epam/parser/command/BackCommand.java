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
import com.epam.parser.util.ResourceManager;
import com.epam.parser.util.StylesheetCache;

public final class BackCommand implements ICommand {
	private static final Logger logger = Logger.getLogger(BackCommand.class);
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String xmlPath = ResourceManager.INSTANCE
				.getPropertyRealPath("xml_path");
		String categoriesXsltPath = ResourceManager.INSTANCE
				.getPropertyRealPath("categories_xslt");
		String subcategoriesXsltPath = ResourceManager.INSTANCE
				.getPropertyRealPath("subcategories_xslt");
		String goodsXsltPath = ResourceManager.INSTANCE
				.getPropertyRealPath("goods_xslt");

		String pageName = request.getParameter(AttributeHandler.PAGE);
		String productSubcategory = null;
		String productCategory = null;
		String requestedXSLTFilename = categoriesXsltPath;
		switch (pageName) {
		case AttributeHandler.CATEGORIES:
			requestedXSLTFilename = categoriesXsltPath;
			break;
		case AttributeHandler.SUBCATEGORIES:
			productCategory = request
					.getParameter(AttributeHandler.PRODUCT_CATEGORY);
			productSubcategory = request
					.getParameter(AttributeHandler.PRODUCT_SUBCATEGORY);
			requestedXSLTFilename = subcategoriesXsltPath;
			break;
		case AttributeHandler.GOODS:
			requestedXSLTFilename = goodsXsltPath;
			productCategory = request
					.getParameter(AttributeHandler.PRODUCT_CATEGORY);
			productSubcategory = request
					.getParameter(AttributeHandler.PRODUCT_SUBCATEGORY);
			break;
		}

		try {
			// Generate the transformer.
			Transformer transformer = StylesheetCache
					.newTransformer(requestedXSLTFilename);

			if (productSubcategory != null && productCategory != null) {
				transformer.setParameter(AttributeHandler.PRODUCT_CATEGORY,
						productCategory);
				transformer.setParameter(AttributeHandler.PRODUCT_SUBCATEGORY,
						productSubcategory);
			}
			// Perform the transformation, sending the output to the response.
			lock.readLock().lock();
			try (PrintWriter out = response.getWriter();) {
				Source xmlSource = new StreamSource(
						new URL("file", "", xmlPath).openStream());
				transformer.transform(xmlSource, new StreamResult(out));
			} finally {
				lock.readLock().unlock();
			}
		} catch (TransformerException e) {
			logger.error(
					"Error in executing show back command during transformation",
					e);
		} catch (IOException e) {
			logger.error(
					"Error in executing show back command during stream creation",
					e);
		}
	}

}
