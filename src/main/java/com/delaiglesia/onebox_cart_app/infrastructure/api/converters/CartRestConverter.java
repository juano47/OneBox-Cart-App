package com.delaiglesia.onebox_cart_app.infrastructure.api.converters;

import com.delaiglesia.onebox_cart_app.domain.entity.Cart;
import com.delaiglesia.onebox_cart_app.domain.entity.Customer;
import com.delaiglesia.onebox_cart_app.infrastructure.api.dto.CartDto;
import com.delaiglesia.onebox_cart_app.infrastructure.api.dto.CustomerDto;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface CartRestConverter extends RestConverter<CartDto, Cart> {

  @Override
  @BeanMapping(ignoreByDefault = true)
  @Mapping(target = "id", source = "id")
  @Mapping(target = "customer", source = "customer", qualifiedByName = "mapCustomer")
  @Mapping(target = "shippingAddress", source = "shippingAddress")
  @Mapping(target = "items", source = "items")
  @Mapping(target = "total", source = "total")
  @Mapping(target = "createdAt", source = "createdAt")
  @Mapping(target = "updatedAt", source = "updatedAt")
  @Mapping(target = "deletedAt", source = "deletedAt")
  @Mapping(target = "status", source = "status")
  CartDto mapToDto(Cart cart);

  @Override
  @BeanMapping(ignoreByDefault = true)
  @Mapping(target = "customer", source = "customer")
  @Mapping(target = "shippingAddress", source = "shippingAddress")
  @Mapping(target = "items", source = "items")
  @Mapping(target = "total", source = "total")
  Cart mapToEntityCreate(CartDto cartDto);

  @Override
  @BeanMapping(ignoreByDefault = true)
  @Mapping(target = "shippingAddress", source = "shippingAddress")
  @Mapping(target = "items", source = "items")
  Cart mapToEntity(CartDto cartDto);

  @Named("mapCustomer")
  default CustomerDto mapCustomer(Customer customer) {
    if (customer == null) {
      return null;
    }

    CustomerDto customerDto = new CustomerDto();

    customerDto.setId(customer.getId());
    customerDto.setFirstname(customer.getFirstname());
    customerDto.setLastname(customer.getLastname());
    customerDto.setEmail(customer.getEmail());
    customerDto.setPhone(customer.getPhone());
    return customerDto;
  }
}
