package com.api.models;

public class ProductPrice {
	
	private String productId;
	private Double price;

	public ProductPrice() {

	}

	public ProductPrice(String productId, Double price) {
		this.productId = productId;
		this.price = price;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
}
