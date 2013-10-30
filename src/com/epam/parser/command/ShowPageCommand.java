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

public final class ShowPageCommand implements ICommand {
	private static final Logger logger = Logger
			.getLogger(ShowPageCommand.class);

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

		// Get the XML input document and the stylesheet.
		String requestedXSLT = request.getParameter(AttributeHandler.ACTION);
		String requestedXSLTFilename = categoriesXsltPath;
		if (requestedXSLT == null) {
			requestedXSLT = AttributeHandler.CATEGORIES;
		} else {
			switch (requestedXSLT) {
			case AttributeHandler.CATEGORIES:
				requestedXSLTFilename = categoriesXsltPath;
				break;
			case AttributeHandler.SUBCATEGORIES:
				requestedXSLTFilename = subcategoriesXsltPath;
				break;
			case AttributeHandler.GOODS:
				requestedXSLTFilename = goodsXsltPath;
				break;
			default:
				requestedXSLTFilename = categoriesXsltPath;
				break;
			}
		}
		try {
			// Generate the transformer.
			Transformer transformer = StylesheetCache
					.newTransformer(requestedXSLTFilename);
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
			// Perform the transformation, sending the output to the response.
			lock.readLock().lock();
			try (PrintWriter out = response.getWriter();) {
				Source xmlSource = new StreamSource(
						new URL("file", "", xmlPath).openStream());
				transformer.transform(xmlSource, new StreamResult(out));
			}  finally {
				lock.readLock().unlock();
			}
		} catch(TransformerException e) {
			logger.error("Error in executing show page command during transformation", e);
		}catch (IOException e) {
			logger.error("Error in executing show page command during stream creation", e);
		}
	}

}
