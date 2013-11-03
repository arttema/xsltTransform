package com.epam.parser.dto;

public class Good {

	private String producer;
	private String model;
	private String issueDate;
	private String color;
	private boolean notInStock;
	private int price;
	
	public Good(String producer, String model, String issuedateString,
			String color, boolean notInStock, int price) {
		super();
		this.producer = producer;
		this.model = model;
		this.issueDate = issuedateString;
		this.color = color;
		this.notInStock = notInStock;
		this.price = price;
	}
	public String getProducer() {
		return producer;
	}
	public void setProducer(String producer) {
		this.producer = producer;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(String issuedateString) {
		this.issueDate = issuedateString;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public boolean isNotInStock() {
		return notInStock;
	}
	public void setNotInStock(boolean notInStock) {
		this.notInStock = notInStock;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("\n              ");
		stringBuilder.append(producer);
		stringBuilder.append("    ");
		stringBuilder.append(model);
		stringBuilder.append("    ");
		stringBuilder.append(issueDate);
		stringBuilder.append("    ");
		stringBuilder.append(color);
		stringBuilder.append("    ");
		if(notInStock){
		stringBuilder.append("not in stock");
		}else {
			stringBuilder.append(price);
		}
	
		return stringBuilder.toString();
	}
	

}
