package com.delaiglesia.onebox_cart_app.domain.usecases;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.delaiglesia.onebox_cart_app.domain.entity.Cart;
import com.delaiglesia.onebox_cart_app.domain.repository.CartRepository;
import com.delaiglesia.onebox_cart_app.domain.usecases.cart.GetCartUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GetCartUseCaseTest {

  private GetCartUseCase getCartUseCase;

  private CartRepository cartRepository;

  @BeforeEach
  void setUp() {
    cartRepository = mock(CartRepository.class);
    getCartUseCase = new GetCartUseCase(cartRepository);
  }

  @Test
  void execute() {
    Long id = 1L;
    Cart expectedCart = new Cart();

    when(cartRepository.getCart(id)).thenReturn(expectedCart);

    Cart result = getCartUseCase.execute(id);

    assertEquals(expectedCart, result);

    verify(cartRepository).getCart(id);
  }
}
