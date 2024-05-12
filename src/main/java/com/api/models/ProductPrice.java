package com.api.models;

public class ProductPrice {
	
	private String productId;
	private Integer price;

	public ProductPrice() {

	}

	public ProductPrice(String productId, Integer price) {
		this.productId = productId;
		this.price = price;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}
}
