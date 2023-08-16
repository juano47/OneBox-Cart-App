package com.delaiglesia.onebox_cart_app.infrastructure.config;

import com.delaiglesia.onebox_cart_app.domain.repository.CartRepository;
import com.delaiglesia.onebox_cart_app.domain.repository.CustomerRepository;
import com.delaiglesia.onebox_cart_app.domain.services.CartItemService;
import com.delaiglesia.onebox_cart_app.domain.services.CartScheduleTaskService;
import com.delaiglesia.onebox_cart_app.domain.services.CartService;
import com.delaiglesia.onebox_cart_app.domain.services.impl.CartServiceImpl;
import com.delaiglesia.onebox_cart_app.domain.usecases.CreateCartUseCase;
import com.delaiglesia.onebox_cart_app.domain.usecases.DeleteCartUseCase;
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
  public CartRepository createCartRepository(
      final MySqlCartRepository mySqlCartRepository,
      final CartRepositoryConverter cartRepositoryConverter) {
    return new CartRepositoryImpl(mySqlCartRepository, cartRepositoryConverter);
  }

  @Bean
  public CreateCartUseCase createCreateCartUseCase(
      final CartRepository cartRepository,
      final CustomerRepository customerRepository,
      final CartItemService cartItemService,
      final CartService cartService) {
    return new CreateCartUseCase(cartRepository, customerRepository, cartItemService, cartService);
  }

  @Bean
  public GetCartUseCase createGetCartUseCase(final CartRepository cartRepository) {
    return new GetCartUseCase(cartRepository);
  }

  @Bean
  public UpdateCartUseCase createUpdateCartUseCase(
      final CartRepository cartRepository, CartItemService cartItemService, CartService cartService) {
    return new UpdateCartUseCase(cartRepository, cartItemService, cartService);
  }

  @Bean
  public GetCartsUseCase createGetCartsUseCase(CartRepository cartRepository) {
    return new GetCartsUseCase(cartRepository);
  }

  @Bean
  public DeleteCartUseCase createDeleteCartUseCase(CartRepository cartRepository) {
    return new DeleteCartUseCase(cartRepository);
  }

  @Bean
  public CartScheduleTaskService createCartScheduleTaskService(
      final CartRepository cartRepository) {
    return new CartScheduleTaskService(cartRepository);
  }

  @Bean
  public CartService createCartService() {
    return new CartServiceImpl();
  }
}
