package com.delaiglesia.onebox_cart_app.infrastructure.config;

import com.delaiglesia.onebox_cart_app.domain.repository.CartRepository;
import com.delaiglesia.onebox_cart_app.domain.repository.CustomerRepository;
import com.delaiglesia.onebox_cart_app.domain.repository.ProductRepository;
import com.delaiglesia.onebox_cart_app.domain.usecases.CreateCartUseCase;
import com.delaiglesia.onebox_cart_app.domain.usecases.GetCartUseCase;
import com.delaiglesia.onebox_cart_app.domain.usecases.GetCartsUseCase;
import com.delaiglesia.onebox_cart_app.domain.usecases.UpdateCartUseCase;
import com.delaiglesia.onebox_cart_app.infrastructure.persistence.converters.CartRepositoryConverter;
import com.delaiglesia.onebox_cart_app.infrastructure.persistence.repositories.MySqlCartRepository;
import com.delaiglesia.onebox_cart_app.infrastructure.persistence.repositories.impl.CartRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CartConfig {

  @Bean
  public CartRepositoryImpl createCartRepository(
      MySqlCartRepository mySqlCartRepository, CartRepositoryConverter cartRepositoryConverter) {
    return new CartRepositoryImpl(mySqlCartRepository, cartRepositoryConverter);
  }

  @Bean
  public CreateCartUseCase createCreateCartUseCase(
      CartRepository cartRepository,
      CustomerRepository customerRepository,
      ProductRepository productRepository) {
    return new CreateCartUseCase(cartRepository, customerRepository, productRepository);
  }

  @Bean
  public GetCartUseCase createGetCartUseCase(CartRepository cartRepository) {
    return new GetCartUseCase(cartRepository);
  }

  @Bean
  public UpdateCartUseCase createUpdateCartUseCase(
      CartRepository cartRepository, ProductRepository productRepository) {
    return new UpdateCartUseCase(cartRepository, productRepository);
  }

  @Bean
  public GetCartsUseCase createGetCartsUseCase(CartRepository cartRepository) {
    return new GetCartsUseCase(cartRepository);
  }
}
