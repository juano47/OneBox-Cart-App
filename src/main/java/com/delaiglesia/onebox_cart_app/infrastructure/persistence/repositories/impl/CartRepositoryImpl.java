package com.delaiglesia.onebox_cart_app.infrastructure.persistence.repositories.impl;

import com.delaiglesia.onebox_cart_app.domain.entity.Cart;
import com.delaiglesia.onebox_cart_app.domain.entity.CartStatus;
import com.delaiglesia.onebox_cart_app.domain.repository.CartRepository;
import com.delaiglesia.onebox_cart_app.infrastructure.persistence.converters.CartRepositoryConverter;
import com.delaiglesia.onebox_cart_app.infrastructure.persistence.repositories.MySqlCartRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CartRepositoryImpl implements CartRepository {

  private final MySqlCartRepository mySqlCartRepository;

  private final CartRepositoryConverter cartRepositoryConverter;

  @Override
  public Cart saveCart(final Cart cart) {
    return cartRepositoryConverter.mapToEntity(
        mySqlCartRepository.save(cartRepositoryConverter.mapToTable(cart)));
  }

  @Override
  public void saveAllCarts(List<Cart> expiredCarts) {
    mySqlCartRepository.saveAll(
        expiredCarts.stream().map(cartRepositoryConverter::mapToTable).toList());
  }

  @Override
  public Cart getCart(final Long id) {
    return cartRepositoryConverter.mapToEntity(
        mySqlCartRepository
            .findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Requested cart does not exist")));
  }

  @Override
  public void removeCart(final Long id) {
    mySqlCartRepository.deleteById(id);
  }

  @Override
  public List<Cart> getAllCartsByStatus(final CartStatus status) {
    return mySqlCartRepository.findAllByStatus(status).stream()
        .map(cartRepositoryConverter::mapToEntity)
        .toList();
  }

  @Transactional
  public List<Cart> findCartsNotUpdatedInLastTenMinutes(LocalDateTime cutoffTime, List<CartStatus> statuses) {
    return mySqlCartRepository.findCartsNotUpdatedInLastTenMinutes(cutoffTime, statuses).stream()
        .map(cartRepositoryConverter::mapToEntity)
        .toList();
  }
}
