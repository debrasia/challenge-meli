package com.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FavItem {

	@JsonProperty("id")
	private String itemId;
	@JsonProperty("quantity")
	private Integer quantity;
	
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	@Override
	public String toString() {
		return "FavItem [itemId=" + itemId + ", quantity=" + quantity + "]";
	}	
}
