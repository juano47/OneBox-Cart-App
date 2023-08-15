package com.delaiglesia.onebox_cart_app.infrastructure.persistence.converters;

import com.delaiglesia.onebox_cart_app.domain.entity.Cart;
import com.delaiglesia.onebox_cart_app.infrastructure.persistence.entities.CartEntity;
import java.util.Collections;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface CartRepositoryConverter extends RepositoryConverter<CartEntity, Cart> {

  @Override
  @Mapping(
      target = "customer.carts",
      source = "customer.carts",
      qualifiedByName = "mapCartsIfNotEmpty")
  CartEntity mapToTable(Cart cart);

  @Override
  @Mapping(
      target = "customer.carts",
      source = "customer.carts",
      qualifiedByName = "mapCartsIfNotEmpty")
  Cart mapToEntity(CartEntity cartEntity);

  @Named("mapCartsIfNotEmpty")
  default List<CartEntity> mapToTableCartsIfNotEmpty(List<Cart> carts) {
    if (carts != null && !carts.isEmpty()) {
      return mapToTable(carts);
    }
    return Collections.emptyList();
  }

  @Named("mapCartsIfNotEmpty")
  default List<Cart> mapToEntityCartsIfNotEmpty(List<CartEntity> carts) {
    if (carts != null && !carts.isEmpty()) {
      return mapToEntity(carts);
    }
    return Collections.emptyList();
  }
}
