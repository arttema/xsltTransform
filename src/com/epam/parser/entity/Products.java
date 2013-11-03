package com.epam.parser.entity;

import java.util.ArrayList;

public class Products {

	private ArrayList<Category> categories=new ArrayList<Category>();

	public ArrayList<Category> getCategories() {
		return categories;
	}

	public void setCategories(ArrayList<Category> categories) {
		this.categories = categories;
	}

	public void add(Category category){
		categories.add(category);
	}
	
	@Override
	public String toString() {
		return "\n" + categories;
	}
	

}
