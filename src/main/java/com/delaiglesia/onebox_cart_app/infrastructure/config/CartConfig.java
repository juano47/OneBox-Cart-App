package com.delaiglesia.onebox_cart_app.infrastructure.config;

import com.delaiglesia.onebox_cart_app.domain.repository.CartRepository;
import com.delaiglesia.onebox_cart_app.domain.repository.CustomerRepository;
import com.delaiglesia.onebox_cart_app.domain.repository.ProductRepository;
import com.delaiglesia.onebox_cart_app.domain.services.CartItemService;
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
      final MySqlCartRepository mySqlCartRepository,
      final CartRepositoryConverter cartRepositoryConverter) {
    return new CartRepositoryImpl(mySqlCartRepository, cartRepositoryConverter);
  }

  @Bean
  public CreateCartUseCase createCreateCartUseCase(
      final CartRepository cartRepository,
      final CustomerRepository customerRepository,
      final CartItemService cartItemService) {
    return new CreateCartUseCase(cartRepository, customerRepository, cartItemService);
  }

  @Bean
  public GetCartUseCase createGetCartUseCase(final CartRepository cartRepository) {
    return new GetCartUseCase(cartRepository);
  }

  @Bean
  public UpdateCartUseCase createUpdateCartUseCase(
      final CartRepository cartRepository, CartItemService cartItemService) {
    return new UpdateCartUseCase(cartRepository, cartItemService);
  }

  @Bean
  public GetCartsUseCase createGetCartsUseCase(CartRepository cartRepository) {
    return new GetCartsUseCase(cartRepository);
  }
}
