package com.autozone.principal;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import com.autozone.database.dao.ProductDAO;
import com.autozone.models.Producto;

public class Principal {

	public static void main(String[] args) {
		ProductDAO productDAO = new ProductDAO();
		
		List<Producto> products = Arrays.asList(
				new Producto("Teclado", new BigDecimal("49.99"), 100),
				new Producto("Mouse", new BigDecimal("19.99"), 150),
				new Producto(null, new BigDecimal("199.99"), 50));
		
		productDAO.insertProductosEnLote(products);
		
		productDAO.actualizarStock(1, 120);
		
	}
	
}
