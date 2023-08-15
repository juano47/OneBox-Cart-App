package com.delaiglesia.onebox_cart_app.infrastructure.persistence.repositories.impl;

import com.delaiglesia.onebox_cart_app.domain.entity.Cart;
import com.delaiglesia.onebox_cart_app.domain.entity.CartStatus;
import com.delaiglesia.onebox_cart_app.domain.repository.CartRepository;
import com.delaiglesia.onebox_cart_app.infrastructure.persistence.converters.CartRepositoryConverter;
import com.delaiglesia.onebox_cart_app.infrastructure.persistence.repositories.MySqlCartRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CartRepositoryImpl implements CartRepository {

  private final MySqlCartRepository mySqlCartRepository;

  private final CartRepositoryConverter cartRepositoryConverter;

  @Override
  public Cart saveCart(Cart cart) {
    return cartRepositoryConverter.mapToEntity(
        mySqlCartRepository.save(cartRepositoryConverter.mapToTable(cart)));
  }

  @Override
  public Cart getCart(Long id) {
    return cartRepositoryConverter.mapToEntity(
        mySqlCartRepository
            .findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Requested cart does not exist")));
  }

  @Override
  public void removeCart(Long id) {
    mySqlCartRepository.deleteById(id);
  }

  @Override
  public List<Cart> getAllCartsByStatus(CartStatus status) {
    return mySqlCartRepository.findAllByStatus(status).stream()
        .map(cartRepositoryConverter::mapToEntity)
        .toList();
  }
}
