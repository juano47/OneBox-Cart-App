package com.delaiglesia.onebox_cart_app.domain.repository;

import com.delaiglesia.onebox_cart_app.domain.entity.Customer;

public interface CustomerRepository {
  Customer saveCustomer(Customer customer);

  Customer getCustomer(Long id);
}
