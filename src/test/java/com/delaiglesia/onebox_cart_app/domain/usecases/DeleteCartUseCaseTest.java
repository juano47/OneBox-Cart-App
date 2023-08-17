package com.delaiglesia.onebox_cart_app.domain.usecases;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.delaiglesia.onebox_cart_app.domain.entity.Cart;
import com.delaiglesia.onebox_cart_app.domain.repository.CartRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DeleteCartUseCaseTest {

  private DeleteCartUseCase deleteCartUseCase;

  private CartRepository cartRepository;

  @BeforeEach
  void setUp() {
    cartRepository = mock(CartRepository.class);
    deleteCartUseCase = new DeleteCartUseCase(cartRepository);
  }

  @Test
  void execute() {
    Long id = 1L;
    Cart cart = new Cart();
    cart.setId(id);

    when(cartRepository.getCart(id)).thenReturn(cart);

    deleteCartUseCase.execute(id);

    verify(cartRepository).getCart(id);
    verify(cartRepository).removeCart(id);
  }
}
