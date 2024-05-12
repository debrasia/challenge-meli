package com.api.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CouponProductsResponse {

	@JsonProperty("item_ids")
	private List<String> products;
	@JsonProperty("total")
	private Integer totalAmount = 0;

	public CouponProductsResponse() {

	}

	public CouponProductsResponse(List<String> products, Integer totalAmount) {
		this.products = products;
		this.totalAmount = totalAmount;
	}

	public List<String> getProducts() {
		return products;
	}

	public void setProducts(List<String> products) {
		this.products = products;
	}

	public Integer getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Integer totalAmount) {
		this.totalAmount = totalAmount;
	}

	@Override
	public String toString() {
		return "CouponProductsResponse [products=" + products + ", totalAmount=" + totalAmount + "]";
	}

	
}
