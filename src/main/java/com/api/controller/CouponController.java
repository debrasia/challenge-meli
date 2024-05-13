package com.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.models.CouponProductsRequest;
import com.api.models.CouponProductsResponse;
import com.api.models.FavItemsResponse;
import com.api.service.CouponService;

@RestController
@RequestMapping("/coupon/")
public class CouponController {
	
	@PostMapping("")
	public CouponProductsResponse getCouponProducts(@RequestBody CouponProductsRequest request) {
		CouponService cs = new CouponService();
		return cs.getCouponProductsService(request);
	}
	
	@GetMapping("/stats")
	public FavItemsResponse getStats() {
		return new FavItemsResponse();
	}
}
