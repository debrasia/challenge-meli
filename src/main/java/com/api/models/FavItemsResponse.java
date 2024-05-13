package com.api.models;

import java.util.ArrayList;
import java.util.List;

public class FavItemsResponse {

	private List<FavItem> favItems;

	public FavItemsResponse () {
		favItems = new ArrayList<FavItem>();
	}
	
	public List<FavItem> getFavItems() {
		return favItems;
	}

	public void setFavItems(List<FavItem> favItems) {
		this.favItems = favItems;
	}

	@Override
	public String toString() {
		return "FavItemsResponse [favItems=" + favItems + "]";
	}
	
}
