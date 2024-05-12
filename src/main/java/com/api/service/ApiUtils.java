package com.api.service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApiUtils {
	
	static Logger logger = LoggerFactory.getLogger(ApiUtils.class);
	
	/**
	 * Devuelve un listado de items id para una categoría específica.
	 * <p>Se usó para generar datos de prueba
	 * @param cant
	 * @param category
	 * @return
	 * @throws Exception
	 */
	public static List<String> getIdProductsByCategory(Integer cant, String category) throws Exception {
		List<String> productsId = new ArrayList<String>();

		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request;

		try {
			// Se genera request para buscar los productos por categoría
			request = HttpRequest.newBuilder()
					.uri(new URI("https://api.mercadolibre.com/sites/MLA/search?category=" + category)).build();
		} catch (URISyntaxException e) {
			logger.error(e.getMessage());
			throw e;
		}

		HttpResponse<String> response;

		try {
			response = client.send(request, BodyHandlers.ofString());
		} catch (IOException | InterruptedException e) {
			throw e;
		}
		
		JSONObject json = new JSONObject(response.body().toString());
		
		if(json.getJSONArray("results").length() == 0) {
			throw new Exception("No se encontraron datos para la categoria " + category);
		}
		
		if(cant > json.getJSONArray("results").length())
			cant = json.getJSONArray("results").length();
		
		for (int i = 0; i < cant; i++) {
			String itemId = json.getJSONArray("results").getJSONObject(i).getString("id");
			productsId.add(itemId);
		}

		return productsId;
	}

	/**
	 * Consulta y devuelve el precio de un producto específico
	 * @param idProduct
	 * @return price
	 * @throws Exception
	 */
	public static Integer getPriceForIdProduct(String idProduct) throws Exception {

		HttpClient client = HttpClient.newHttpClient();

		HttpRequest request;
		try {
			// Se genera el request para consultar por el producto recibido
			request = HttpRequest.newBuilder().uri(new URI("https://api.mercadolibre.com/items/" + idProduct))
					.build();
		} catch (URISyntaxException e) {
			throw e;
		}

		HttpResponse<String> response;
		try {
			// Ejecuto llamada http a la api  
			response = client.send(request, BodyHandlers.ofString());
		} catch (IOException | InterruptedException e) {
			throw e;
		}

		if(response.statusCode() == 404) {
			logger.error(response.toString());
			throw new Exception("El producto " + idProduct + " no se encuentra disponible");
		}else if(response.statusCode() != 200) {
			logger.error(response.toString());
			throw new Exception("Ocurrio un error al consultar el producto " + idProduct + "StatusCode " + response.statusCode());
		}
		// convierto la respuesta en un json, para luego conseguir el precio
		JSONObject json = new JSONObject(response.body().toString());
		Integer price = json.getInt("price");
		return price;
	}

}
