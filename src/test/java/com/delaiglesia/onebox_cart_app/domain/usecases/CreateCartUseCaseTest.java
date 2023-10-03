package com.delaiglesia.onebox_cart_app.domain.usecases;

import com.delaiglesia.onebox_cart_app.domain.entity.Cart;
import com.delaiglesia.onebox_cart_app.domain.entity.CartItem;
import com.delaiglesia.onebox_cart_app.domain.entity.CartStatus;
import com.delaiglesia.onebox_cart_app.domain.entity.Customer;
import com.delaiglesia.onebox_cart_app.domain.entity.CustomerAddress;
import com.delaiglesia.onebox_cart_app.domain.repository.CartRepository;
import com.delaiglesia.onebox_cart_app.domain.repository.CustomerRepository;
import com.delaiglesia.onebox_cart_app.domain.services.CartItemService;
import com.delaiglesia.onebox_cart_app.domain.services.CartService;
import com.delaiglesia.onebox_cart_app.domain.usecases.cart.CreateCartUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

class CreateCartUseCaseTest {

  private CreateCartUseCase createCartUseCase;

  private CartRepository cartRepository;

  private CustomerRepository customerRepository;

  private CartItemService cartItemService;

  private CartService cartService;

  @BeforeEach
  void setUp() {
    cartRepository = mock(CartRepository.class);
    customerRepository = mock(CustomerRepository.class);
    cartItemService = mock(CartItemService.class);
    cartService = mock(CartService.class);
    createCartUseCase =
        new CreateCartUseCase(cartRepository, customerRepository, cartItemService, cartService);
  }

  @Test
  @Disabled
  void execute_emptyCart() {
    Customer customer = new Customer();
    CustomerAddress shippingAddress = new CustomerAddress();
    Cart cart = new Cart();
    cart.setCustomer(customer);
    cart.setShippingAddress(shippingAddress);

    Cart spyCart = spy(cart);

    when(customerRepository.getCustomer(customer.getId())).thenReturn(customer);
    when(cartRepository.saveCart(spyCart)).thenReturn(spyCart);

    Cart result = createCartUseCase.execute(spyCart);

    assertEquals(cart.getCustomer(), result.getCustomer());
    assertEquals(cart.getShippingAddress(), result.getShippingAddress());
    assertNull(result.getItems());
    assertEquals(0.0, result.getTotal());
    assertEquals(CartStatus.EMPTY, result.getStatus());
    assertNotNull(result.getCreatedAt());
    assertNotNull(result.getUpdatedAt());
    assertNull(result.getDeletedAt());

    verify(customerRepository).getCustomer(customer.getId());
    verify(cartRepository).saveCart(spyCart);
    verifyNoInteractions(cartItemService);
    verifyNoInteractions(cartService);
  }

  @Test
  @Disabled
  void execute_NotEmptyCart() {
    Customer customer = new Customer();
    CustomerAddress shippingAddress = new CustomerAddress();
    List<CartItem> items = List.of(new CartItem());
    Cart cart = new Cart();
    cart.setCustomer(customer);
    cart.setShippingAddress(shippingAddress);
    cart.setItems(items);

    Cart spyCart = spy(cart);

    when(customerRepository.getCustomer(customer.getId())).thenReturn(customer);
    when(cartRepository.saveCart(spyCart)).thenReturn(spyCart);
    when(cartItemService.setCartItems(items, null)).thenReturn(items);
    when(cartService.calculateTotalPrice(items)).thenReturn(10.0);

    Cart result = createCartUseCase.execute(spyCart);

    assertEquals(cart.getCustomer(), result.getCustomer());
    assertEquals(cart.getShippingAddress(), result.getShippingAddress());
    assertEquals(items, result.getItems());
    assertEquals(10.0, result.getTotal());
    assertEquals(CartStatus.ACTIVE, result.getStatus());
    assertNotNull(result.getCreatedAt());
    assertNotNull(result.getUpdatedAt());
    assertNull(result.getDeletedAt());

    verify(customerRepository).getCustomer(customer.getId());
    verify(cartRepository).saveCart(spyCart);
    verify(cartItemService).setCartItems(items, null);
    verify(cartService).calculateTotalPrice(items);
  }

  @Test
  void shouldThrowExceptionWhenCustomerIsNull() {
    Cart cart = new Cart();
    IllegalArgumentException exception =
        assertThrows(IllegalArgumentException.class, () -> createCartUseCase.execute(cart));

    assertEquals("Customer must be set when creating a cart", exception.getMessage());

    verifyNoInteractions(customerRepository);
    verifyNoInteractions(cartRepository);
    verifyNoInteractions(cartItemService);
    verifyNoInteractions(cartService);
  }

  @Test
  @Disabled
  void shouldThrowExceptionWhenCustomerIsInvalid() {
    Customer customer = new Customer();
    Cart cart = new Cart();
    cart.setCustomer(customer);

    when(customerRepository.getCustomer(customer.getId())).thenReturn(null);

    IllegalArgumentException exception =
        assertThrows(IllegalArgumentException.class, () -> createCartUseCase.execute(cart));

    assertEquals("Customer does not exist", exception.getMessage());

    verify(customerRepository).getCustomer(customer.getId());
    verifyNoInteractions(cartRepository);
    verifyNoInteractions(cartItemService);
    verifyNoInteractions(cartService);
  }
}
