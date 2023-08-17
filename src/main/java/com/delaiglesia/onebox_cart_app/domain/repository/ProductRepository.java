package com.delaiglesia.onebox_cart_app.domain.repository;

import com.delaiglesia.onebox_cart_app.domain.entity.Product;

public interface ProductRepository {

	Product getProduct(Long id);
}
