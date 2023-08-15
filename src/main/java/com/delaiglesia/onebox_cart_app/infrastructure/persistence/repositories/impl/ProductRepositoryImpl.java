package com.delaiglesia.onebox_cart_app.infrastructure.persistence.repositories.impl;

import com.delaiglesia.onebox_cart_app.domain.entity.Product;
import com.delaiglesia.onebox_cart_app.domain.repository.ProductRepository;
import com.delaiglesia.onebox_cart_app.infrastructure.persistence.converters.ProductRepositoryConverter;
import com.delaiglesia.onebox_cart_app.infrastructure.persistence.repositories.MySqlProductRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {

  private final MySqlProductRepository mySqlProductRepository;
  private final ProductRepositoryConverter productRepositoryConverter;

  @Override
  public Product saveProduct(Product product) {
    return null;
  }

  @Override
  public Product getProduct(Long id) {
    return productRepositoryConverter.mapToEntity(
        mySqlProductRepository
            .findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Product not found")));
  }

  @Override
  public void removeProduct(Long id) {}

  @Override
  public List<Product> getAllProducts() {
    return null;
  }
}
