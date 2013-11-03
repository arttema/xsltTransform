package com.epam.parser.dto;

import java.util.ArrayList;

public class Subcategory {
	private ArrayList<Good> goods=new ArrayList<Good>();
	private String name;
	
	public ArrayList<Good> getGoods() {
		return goods;
	}

	public void setGoods(ArrayList<Good> goods) {
		this.goods = goods;
	}

	public void add(Good good){
		goods.add(good);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "\n          "+name+ "\n              " + goods;
	}


}
