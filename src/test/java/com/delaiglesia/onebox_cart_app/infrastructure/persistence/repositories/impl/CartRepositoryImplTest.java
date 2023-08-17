package com.delaiglesia.onebox_cart_app.infrastructure.persistence.repositories.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.delaiglesia.onebox_cart_app.domain.entity.Cart;
import com.delaiglesia.onebox_cart_app.domain.entity.CartStatus;
import com.delaiglesia.onebox_cart_app.infrastructure.persistence.converters.CartRepositoryConverter;
import com.delaiglesia.onebox_cart_app.infrastructure.persistence.entities.CartEntity;
import com.delaiglesia.onebox_cart_app.infrastructure.persistence.repositories.MySqlCartRepository;
import jakarta.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CartRepositoryImplTest {

  private CartRepositoryImpl cartRepository;

  private MySqlCartRepository mySqlCartRepository;

  private CartRepositoryConverter cartRepositoryConverter;

  @BeforeEach
  void setUp() {
    mySqlCartRepository = mock(MySqlCartRepository.class);
    cartRepositoryConverter = mock(CartRepositoryConverter.class);
    cartRepository = new CartRepositoryImpl(mySqlCartRepository, cartRepositoryConverter);
  }

  @Test
  void saveCart() {
    Cart cartInput = new Cart();
    Cart cartOutput = new Cart();

    CartEntity cartEntityInput = new CartEntity();
    CartEntity cartEntityOutput = new CartEntity();

    when(cartRepositoryConverter.mapToTable(cartInput)).thenReturn(cartEntityInput);
    when(mySqlCartRepository.save(cartEntityInput)).thenReturn(cartEntityOutput);
    when(cartRepositoryConverter.mapToEntity(cartEntityOutput)).thenReturn(cartOutput);

    Cart result = cartRepository.saveCart(cartInput);

    assertEquals(cartOutput, result);

    verify(cartRepositoryConverter).mapToTable(cartInput);
    verify(mySqlCartRepository).save(cartEntityInput);
    verify(cartRepositoryConverter).mapToEntity(cartEntityOutput);
  }

  @Test
  void saveAllCarts() {
    List<Cart> cartsInput = List.of(new Cart(), new Cart());

    List<CartEntity> cartEntitiesInput = List.of(new CartEntity(), new CartEntity());
    List<CartEntity> cartEntitiesOutput = List.of(new CartEntity(), new CartEntity());

    when(cartRepositoryConverter.mapToTable(any(Cart.class))).thenReturn(new CartEntity());
    when(mySqlCartRepository.saveAll(cartEntitiesInput)).thenReturn(cartEntitiesOutput);

    cartRepository.saveAllCarts(cartsInput);

    verify(mySqlCartRepository).saveAll(anyList());
  }

  @Test
  void getCart() {
    Long cartId = 1L;
    Cart cartOutput = new Cart();
    Optional<CartEntity> cartEntityOutput = Optional.of(new CartEntity());

    when(mySqlCartRepository.findById(cartId)).thenReturn(cartEntityOutput);
    when(cartRepositoryConverter.mapToEntity(any(CartEntity.class))).thenReturn(cartOutput);

    Cart result = cartRepository.getCart(cartId);

    assertEquals(cartOutput, result);

    verify(mySqlCartRepository).findById(cartId);
    verify(cartRepositoryConverter).mapToEntity(cartEntityOutput.get());
  }

  @Test
  void shouldThrowExceptionWhenCartNotFound() {

    Long cartId = 1L;

    when(mySqlCartRepository.findById(cartId)).thenReturn(Optional.empty());

    EntityNotFoundException exception =
        assertThrows(
            EntityNotFoundException.class,
            () -> {
              cartRepository.getCart(cartId);
            });

    assertEquals("Cart with id " + cartId + " not found", exception.getMessage());
  }

  @Test
  void removeCart() {
    Long cartId = 1L;

    cartRepository.removeCart(cartId);

    verify(mySqlCartRepository).deleteById(cartId);
  }

  @Test
  void getAllCartsByStatus() {
    CartStatus status = CartStatus.EMPTY;
    List<Cart> cartsOutput = List.of(new Cart(), new Cart());
    List<CartEntity> cartEntitiesOutput = List.of(new CartEntity(), new CartEntity());

    when(mySqlCartRepository.findAllByStatus(status)).thenReturn(cartEntitiesOutput);
    when(cartRepositoryConverter.mapToEntity(any(CartEntity.class))).thenReturn(new Cart());

    List<Cart> result = cartRepository.getAllCartsByStatus(status);

    assertEquals(2, result.size());

    verify(mySqlCartRepository).findAllByStatus(status);
    verify(cartRepositoryConverter, times(2)).mapToEntity(any(CartEntity.class));
  }

  @Test
  void findCartsNotUpdatedInLastTenMinutes() {
    List<CartEntity> cartEntitiesOutput = List.of(new CartEntity(), new CartEntity());
    LocalDateTime tenMinutesAgo = LocalDateTime.now().minusMinutes(10);
    List<CartStatus> cartStatuses =
        List.of(CartStatus.EMPTY, CartStatus.ACTIVE, CartStatus.CHECKOUT);

    when(mySqlCartRepository.findCartsNotUpdatedInLastTenMinutes(tenMinutesAgo, cartStatuses))
        .thenReturn(cartEntitiesOutput);
    when(cartRepositoryConverter.mapToEntity(any(CartEntity.class))).thenReturn(new Cart());

    List<Cart> result =
        cartRepository.findCartsNotUpdatedInLastTenMinutes(tenMinutesAgo, cartStatuses);

    assertEquals(2, result.size());

    verify(mySqlCartRepository).findCartsNotUpdatedInLastTenMinutes(tenMinutesAgo, cartStatuses);
    verify(cartRepositoryConverter, times(2)).mapToEntity(any(CartEntity.class));
  }
}
