package com.sunpengyun.domain;

import java.io.Serializable;
import java.math.BigDecimal;

public class Goods implements Serializable {
	
	private Integer id;
	private String name;
	private String price;
	private String hunders;
	public Goods(Integer id, String name, String price, String hunders) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.hunders = hunders;
	}
	public Goods() {
		super();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getHunders() {
		return hunders;
	}
	public void setHunders(String hunders) {
		this.hunders = hunders;
	}
	@Override
	public String toString() {
		return "Goods [id=" + id + ", name=" + name + ", price=" + price + ", hunders=" + hunders + "]";
	}
	
	
	
	
	

}
