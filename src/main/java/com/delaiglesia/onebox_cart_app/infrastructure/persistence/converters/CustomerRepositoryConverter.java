package com.delaiglesia.onebox_cart_app.infrastructure.persistence.converters;

import com.delaiglesia.onebox_cart_app.domain.entity.Customer;
import com.delaiglesia.onebox_cart_app.infrastructure.persistence.entities.CustomerEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerRepositoryConverter extends RepositoryConverter<CustomerEntity, Customer> {

  @Override
  CustomerEntity mapToTable(Customer customer);

  @Override
  Customer mapToEntity(CustomerEntity customerEntity);
}
