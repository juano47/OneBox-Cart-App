package com.delaiglesia.onebox_cart_app.domain.repository;

import com.delaiglesia.onebox_cart_app.domain.entity.Product;

import java.util.List;

public interface ProductRepository {
	Product saveProduct(Product product);

	Product getProduct(Long id);

	void removeProduct(Long id);

	List<Product> getAllProducts();
}
