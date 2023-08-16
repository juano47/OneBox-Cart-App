package com.delaiglesia.onebox_cart_app.domain.repository;

import com.delaiglesia.onebox_cart_app.domain.entity.Product;

import java.util.List;

public interface ProductRepository {

	Product getProduct(Long id);
}
