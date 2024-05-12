package com.api.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.api.models.CouponProductsRequest;
import com.api.models.CouponProductsResponse;
import com.api.models.ProductPrice;

public class CouponService {

	private List<String> bestProductsList = new ArrayList<String>();
	private Integer totalAmount = 0;

	Logger logger = LoggerFactory.getLogger(CouponService.class);
	
	/**
	 * Servicio que devuelve la lista de productos cuyo monto total sea el más
	 * próximo a monto solicitado
	 * 
	 * @param request CouponProductsRequest
	 * @return CouponProductsResponse
	 */
	public CouponProductsResponse getCouponProductsService(CouponProductsRequest request) {

		logger.info("CouponProductsRequest: " + request.toString());
		CouponProductsResponse response = new CouponProductsResponse();

		List<ProductPrice> productList = new ArrayList<ProductPrice>();

		request.getProducts().forEach(productId -> {
			try {
				// Se consulta el precio de cada producto recibido
				Integer price = ApiUtils.getPriceForIdProduct(productId);
				ProductPrice product = new ProductPrice(productId, price);
				productList.add(product);
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		});

		getBestProductsList(productList, request.getAmount(), 0, new ArrayList<>(), 0);

		response.setProducts(bestProductsList);
		response.setTotalAmount(totalAmount);

		logger.info("CouponProductsResponse: " + response.toString());
		return response;
	}

	/**
	 * Metodo que obtiene la combinacion de productos que maximiza el total gastado
	 * 
	 * @param products
	 * @param amount
	 * @param index
	 * @param auxProducts
	 * @param sumPrices
	 */
	private void getBestProductsList(List<ProductPrice> products, Integer amount, Integer index,
			List<String> auxProducts, Integer sumPrices) {

		if (amount - sumPrices > 0 && sumPrices > totalAmount) {
			// Se guarda la lista y el monto que sea más próximo al amount recibido 
			bestProductsList = new ArrayList<String>(auxProducts);
			totalAmount = sumPrices;
		}

		for (int i = index; i < products.size(); i++) {
			auxProducts.add(products.get(i).getProductId());
			// Se llama al metodo de forma recursiva para conseguir todas las combinaciones
			// posibles de la lista de productos
			getBestProductsList(products, amount, i + 1, auxProducts, sumPrices + products.get(i).getPrice());
			auxProducts.remove(auxProducts.size() - 1);
		}
	}
}
