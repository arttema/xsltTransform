package com.epam.parser.entity;

import java.util.ArrayList;

public class Category {

	private ArrayList<Subcategory> subcategories=new ArrayList<Subcategory>();
	private String name;
	
	public ArrayList<Subcategory> getSubcategories() {
		return subcategories;
	}

	public void setSubcategories(ArrayList<Subcategory> subcategories) {
		this.subcategories = subcategories;
	}
	
	public void add(Subcategory subcategory){
		subcategories.add(subcategory);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "\n"+name+ "\n     " + subcategories;
	}
	

}
