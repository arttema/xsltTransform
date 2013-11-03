package com.epam.parser.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.epam.parser.entity.Good;
import com.epam.parser.util.ResourceManager;

public class GoodsValidator {
	private Good good;
	private Pattern pattern;
	private Matcher matcher;
	private final String dateRegexp = "((0[1-9])|([1-2][0-9])|(3[0-1]))-((0[1-9])|(1[0-2]))-([1-2][0-9]{3})";
	private final String modelRegexp = "[a-zA-Z]{2}[0-9]{3}";
	private final String priceRegexp = "[0-9]+";
	private String producerErrorMessage = null;
	private String modelErrorMessage = null;
	private String priceErrorMessage = null;
	private String colorErrorMessage = null;
	private String issueDateErrorMessage = null;

	private boolean isProductValid = false;

	public Good getGood() {
		return good;
	}

	public void setGood(Good good) {
		this.good = good;
	}

	public boolean validateGood(Good good) {
		producerErrorMessage = null;
		modelErrorMessage = null;
		priceErrorMessage = null;
		colorErrorMessage = null;
		issueDateErrorMessage = null;

		boolean isColorValid = checkColor(good.getColor());
		boolean isPriceValid = checkPrice(good.getPrice());
		boolean isModelValid = checkModel(good.getModel());
		boolean isProducerValid = checkProducer(good.getProducer());
		boolean isIssueDateValid = checkIssueDate(good.getIssueDate());
		
		if (isColorValid && (isPriceValid | good.isNotInStock()) && isModelValid && isProducerValid
				&& isIssueDateValid) {
			isProductValid = true;
			return true;
		} else {
			isProductValid = false;
			return false;
		}
	}

	public boolean validatePriceString(String price) {
		if (price.matches(priceRegexp)) {
			return true;
		} else {
			priceErrorMessage = ResourceManager.INSTANCE
					.getProperty("validator.error.price");
			return false;
		}

	}

	public boolean checkColor(String color) {
		if (color == null) {
			return false;
		} else {
			if (color.length() < 20 && color.length() > 1) {
				return true;
			} else {
				colorErrorMessage = ResourceManager.INSTANCE
						.getProperty("validator.error.color");
				return false;
			}
		}
	}

	public boolean checkModel(String model) {
		if (model == null) {
			return false;
		} else {
			pattern = Pattern.compile(modelRegexp);
			matcher = pattern.matcher(model);
			if (matcher.matches()) {
				return true;
			} else {
				modelErrorMessage = ResourceManager.INSTANCE
						.getProperty("validator.error.model");
				return false;
			}
		}
	}

	public boolean checkPrice(int price) {
		if (price > 0) {
			return true;
		} else {
			priceErrorMessage = ResourceManager.INSTANCE
					.getProperty("validator.error.price");
			return false;
		}
	}

	public boolean checkProducer(String producer) {
		if (producer == null) {
			return false;
		} else {
			if (producer.length() < 20 && producer.length() > 3) {
				return true;
			} else {
				producerErrorMessage = ResourceManager.INSTANCE
						.getProperty("validator.error.producer");
				return false;
			}
		}
	}

	public boolean checkIssueDate(String issueDate) {
		if (issueDate == null) {
			return false;
		} else {
			pattern = Pattern.compile(dateRegexp);
			matcher = pattern.matcher(issueDate);
			if (matcher.matches()) {
				return true;
			} else {
				setIssueDateErrorMessage(ResourceManager.INSTANCE
						.getProperty("validator.error.issueDate"));
				return false;
			}
		}

	}

	public String getProducerErrorMessage() {
		return producerErrorMessage;
	}

	public void setProducerErrorMessage(String producerErrorMessage) {
		this.producerErrorMessage = producerErrorMessage;
	}

	public String getModelErrorMessage() {
		return modelErrorMessage;
	}

	public void setModelErrorMessage(String modelErrorMessage) {
		this.modelErrorMessage = modelErrorMessage;
	}

	public String getPriceErrorMessage() {
		return priceErrorMessage;
	}

	public void setPriceErrorMessage(String priceErrorMessage) {
		this.priceErrorMessage = priceErrorMessage;
	}

	public String getColorErrorMessage() {
		return colorErrorMessage;
	}

	public void setColorErrorMessage(String colorErrorMessage) {
		this.colorErrorMessage = colorErrorMessage;
	}

	public boolean isProductValid() {
		return isProductValid;
	}

	public String getIssueDateErrorMessage() {
		return issueDateErrorMessage;
	}

	public void setIssueDateErrorMessage(String issueDateErrorMessage) {
		this.issueDateErrorMessage = issueDateErrorMessage;
	}

}