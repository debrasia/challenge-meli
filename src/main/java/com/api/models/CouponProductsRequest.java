package com.api.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CouponProductsRequest {

	@JsonProperty("item_ids")
	private List<String> products;
	@JsonProperty("amount")
	private Double amount = 0.00;

	public CouponProductsRequest() {

	}

	public CouponProductsRequest(List<String> products, Double amount) {
		this.products = products;
		this.amount = amount;
	}

	public List<String> getProducts() {
		return products;
	}

	public void setProducts(List<String> products) {
		this.products = products;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "CouponProductsRequest [products=" + products + ", amount=" + amount + "]";
	}

}
