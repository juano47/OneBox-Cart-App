package com.delaiglesia.onebox_cart_app.infrastructure.config;

import com.delaiglesia.onebox_cart_app.infrastructure.persistence.converters.ProductRepositoryConverter;
import com.delaiglesia.onebox_cart_app.infrastructure.persistence.repositories.MySqlProductRepository;
import com.delaiglesia.onebox_cart_app.infrastructure.persistence.repositories.impl.ProductRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductConfig {

  @Bean
  public ProductRepositoryImpl createProductRepository(
      MySqlProductRepository mySqlProductRepository,
      ProductRepositoryConverter productRepositoryConverter) {
    return new ProductRepositoryImpl(mySqlProductRepository, productRepositoryConverter);
  }
}
