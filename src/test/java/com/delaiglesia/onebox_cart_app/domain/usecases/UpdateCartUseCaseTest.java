package com.delaiglesia.onebox_cart_app.domain.usecases;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import com.delaiglesia.onebox_cart_app.domain.entity.Cart;
import com.delaiglesia.onebox_cart_app.domain.entity.CartItem;
import com.delaiglesia.onebox_cart_app.domain.entity.CartStatus;
import com.delaiglesia.onebox_cart_app.domain.entity.CustomerAddress;
import com.delaiglesia.onebox_cart_app.domain.repository.CartRepository;
import com.delaiglesia.onebox_cart_app.domain.services.CartItemService;
import com.delaiglesia.onebox_cart_app.domain.services.CartService;
import com.delaiglesia.onebox_cart_app.domain.usecases.cart.UpdateCartUseCase;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UpdateCartUseCaseTest {

  private UpdateCartUseCase updateCartUseCase;

  private CartRepository cartRepository;

  private CartItemService cartItemService;

  private CartService cartService;

  @BeforeEach
  void setUp() {
    cartRepository = mock(CartRepository.class);
    cartItemService = mock(CartItemService.class);
    cartService = mock(CartService.class);
    updateCartUseCase = new UpdateCartUseCase(cartRepository, cartItemService, cartService);
  }

  @Test
  void executeAddNewItemsCase() {
    Long cartId = 1L;
    List<CartItem> itemsToAdd = List.of(new CartItem(), new CartItem());
    CustomerAddress shippingAddress = new CustomerAddress();
    Cart cart = new Cart();
    cart.setItems(itemsToAdd);
    cart.setStatus(CartStatus.EMPTY);
    cart.setShippingAddress(shippingAddress);

    when(cartRepository.getCart(cartId)).thenReturn(cart);
    when(cartItemService.setCartItems(itemsToAdd, cart)).thenReturn(itemsToAdd);
    when(cartService.calculateTotalPrice(itemsToAdd)).thenReturn(10.0);
    when(cartRepository.saveCart(cart)).thenReturn(cart);

    Cart result = updateCartUseCase.execute(cartId, cart);

    assertEquals(cart, result);
    assertEquals(CartStatus.ACTIVE, result.getStatus());
    assertEquals(shippingAddress, result.getShippingAddress());
    assertEquals(10.0, result.getTotal());
    assertEquals(itemsToAdd, result.getItems());

    verify(cartRepository).getCart(cartId);
    verify(cartItemService).setCartItems(itemsToAdd, cart);
    verify(cartService).calculateTotalPrice(itemsToAdd);
    verify(cartRepository).saveCart(cart);
  }

  @Test
  void executeUpdateToEmptyItemsCase() {
    Long cartId = 1L;
    CustomerAddress shippingAddress = new CustomerAddress();
    Cart cart = new Cart();
    cart.setStatus(CartStatus.ACTIVE);
    cart.setShippingAddress(shippingAddress);

    when(cartRepository.getCart(cartId)).thenReturn(cart);
    when(cartItemService.setCartItems(null, cart)).thenReturn(List.of());
    when(cartService.calculateTotalPrice(List.of())).thenReturn(0.0);
    when(cartRepository.saveCart(cart)).thenReturn(cart);
    when(cartRepository.saveCart(cart)).thenReturn(cart);

    Cart result = updateCartUseCase.execute(cartId, cart);

    assertEquals(cart, result);
    assertEquals(CartStatus.EMPTY, result.getStatus());
    assertEquals(shippingAddress, result.getShippingAddress());
    assertEquals(0.0, result.getTotal());
    assertEquals(List.of(), result.getItems());

    verify(cartRepository).getCart(cartId);
    verify(cartItemService).setCartItems(null, cart);
    verify(cartService).calculateTotalPrice(List.of());
    verify(cartRepository).saveCart(cart);
  }

  @Test
  void shouldThrowExceptionWhenUpdateCartWithFinalStatus() {
    Long cartId = 1L;
    Cart cart = new Cart();
    cart.setStatus(CartStatus.EXPIRED);

    when(cartRepository.getCart(cartId)).thenReturn(cart);

    IllegalArgumentException exception =
        assertThrows(IllegalArgumentException.class, () -> updateCartUseCase.execute(cartId, cart));

    assertEquals("The cart is in a final status and cannot be updated", exception.getMessage());
    verify(cartRepository).getCart(cartId);
    verifyNoInteractions(cartItemService);
    verifyNoInteractions(cartService);
  }
}
