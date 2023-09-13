package com.delaiglesia.onebox_cart_app.domain.usecases;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import com.delaiglesia.onebox_cart_app.domain.entity.Cart;
import com.delaiglesia.onebox_cart_app.domain.entity.CartStatus;
import com.delaiglesia.onebox_cart_app.domain.repository.CartRepository;
import com.delaiglesia.onebox_cart_app.domain.repository.DomainPage;
import com.delaiglesia.onebox_cart_app.infrastructure.persistence.repositories.CartPage;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

class GetCartsUseCaseTest {

  private GetCartsUseCase getCartsUseCase;

  private CartRepository cartRepository;

  @BeforeEach
  void setUp() {
    cartRepository = mock(CartRepository.class);
    getCartsUseCase = new GetCartsUseCase(cartRepository);
  }

  @Test
  void execute() {
    String status = "empty";
    List<Cart> expectedCarts = List.of(new Cart());

    when(cartRepository.getAllCartsByStatus(null, CartStatus.EMPTY))
        .thenReturn(new CartPage(new PageImpl<>(expectedCarts, Pageable.unpaged(), 1)));

    DomainPage<Cart> result = getCartsUseCase.execute(null, status);

    assertEquals(expectedCarts, result.getContent());
    assertEquals(1, result.getTotalElements());

    verify(cartRepository).getAllCartsByStatus(null, CartStatus.EMPTY);
  }

  @Test
  void executeWithInvalidStatus() {
    String status = "invalid";
    IllegalArgumentException exception =
        assertThrows(IllegalArgumentException.class, () -> getCartsUseCase.execute(null, status));
    assertEquals("Status is not valid", exception.getMessage());

    verifyNoInteractions(cartRepository);
  }
}
