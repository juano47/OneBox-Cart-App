package com.delaiglesia.onebox_cart_app.infrastructure.persistence.repositories.impl;

import com.delaiglesia.onebox_cart_app.domain.entity.Customer;
import com.delaiglesia.onebox_cart_app.domain.repository.CustomerRepository;
import com.delaiglesia.onebox_cart_app.infrastructure.persistence.converters.CustomerRepositoryConverter;
import com.delaiglesia.onebox_cart_app.infrastructure.persistence.repositories.MySqlCustomerRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CustomerRepositoryImpl implements CustomerRepository {

  private final MySqlCustomerRepository mySqlCustomerRepository;
  private final CustomerRepositoryConverter customerRepositoryConverter;

  @Override
  public Customer saveCustomer(Customer customer) {
    return customerRepositoryConverter.mapToEntity(
        mySqlCustomerRepository.save(customerRepositoryConverter.mapToTable(customer)));
  }

  @Override
  public Customer getCustomer(Long id) {
    return customerRepositoryConverter.mapToEntity(
        mySqlCustomerRepository
            .findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Customer not found")));
  }
}
