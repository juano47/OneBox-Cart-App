package com.delaiglesia.onebox_cart_app.infrastructure.config;

import com.delaiglesia.onebox_cart_app.domain.repository.ProductRepository;
import com.delaiglesia.onebox_cart_app.domain.services.CartItemService;
import com.delaiglesia.onebox_cart_app.domain.services.impl.CartItemServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CartItemConfig {

  @Bean
  public CartItemService createCartItemServiceImpl(final ProductRepository productRepository) {
    return new CartItemServiceImpl(productRepository);
  }
}
