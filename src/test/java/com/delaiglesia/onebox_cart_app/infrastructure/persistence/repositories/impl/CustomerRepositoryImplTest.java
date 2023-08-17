package com.delaiglesia.onebox_cart_app.infrastructure.persistence.repositories.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.delaiglesia.onebox_cart_app.domain.entity.Customer;
import com.delaiglesia.onebox_cart_app.infrastructure.persistence.converters.CustomerRepositoryConverter;
import com.delaiglesia.onebox_cart_app.infrastructure.persistence.entities.CustomerEntity;
import com.delaiglesia.onebox_cart_app.infrastructure.persistence.repositories.MySqlCustomerRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CustomerRepositoryImplTest {

  private MySqlCustomerRepository mySqlCustomerRepository;

  private CustomerRepositoryImpl customerRepository;

  private CustomerRepositoryConverter customerRepositoryConverter;

  @BeforeEach
  void setUp() {
    mySqlCustomerRepository = mock(MySqlCustomerRepository.class);
    customerRepositoryConverter = mock(CustomerRepositoryConverter.class);
    customerRepository =
        new CustomerRepositoryImpl(mySqlCustomerRepository, customerRepositoryConverter);
  }

  @Test
  void saveCustomer() {
    Customer customerInput = new Customer();
    Customer customerOutput = new Customer();

    CustomerEntity customerEntityInput = new CustomerEntity();
    CustomerEntity customerEntityOutput = new CustomerEntity();

    when(customerRepositoryConverter.mapToTable(customerInput)).thenReturn(customerEntityInput);
    when(mySqlCustomerRepository.save(customerEntityInput)).thenReturn(customerEntityOutput);
    when(customerRepositoryConverter.mapToEntity(customerEntityOutput)).thenReturn(customerOutput);

    Customer result = customerRepository.saveCustomer(customerInput);

    assertEquals(customerOutput, result);

    verify(customerRepositoryConverter).mapToTable(customerInput);
    verify(mySqlCustomerRepository).save(customerEntityInput);
    verify(customerRepositoryConverter).mapToEntity(customerEntityOutput);
  }

  @Test
  void getCustomer() {
    Long id = 1L;
    Customer customerInput = new Customer();
    CustomerEntity customerEntityOutput = new CustomerEntity();

    when(mySqlCustomerRepository.findById(id))
        .thenReturn(java.util.Optional.of(customerEntityOutput));
    when(customerRepositoryConverter.mapToEntity(customerEntityOutput)).thenReturn(customerInput);

    Customer result = customerRepository.getCustomer(id);

    assertEquals(customerInput, result);

    verify(mySqlCustomerRepository).findById(id);
    verify(customerRepositoryConverter).mapToEntity(customerEntityOutput);
  }

  @Test
  void shouldThrowExceptionWhenCustomerNotFound() {

    Long id = 1L;

    when(mySqlCustomerRepository.findById(id)).thenReturn(Optional.empty());

    EntityNotFoundException exception =
        assertThrows(EntityNotFoundException.class, () -> customerRepository.getCustomer(id));

    assertEquals("Customer with id " + id + " not found", exception.getMessage());

    verify(mySqlCustomerRepository).findById(id);
  }
}
