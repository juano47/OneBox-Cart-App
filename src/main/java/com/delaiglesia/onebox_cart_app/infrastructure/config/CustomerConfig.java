package com.delaiglesia.onebox_cart_app.infrastructure.config;

import com.delaiglesia.onebox_cart_app.infrastructure.persistence.converters.CustomerRepositoryConverter;
import com.delaiglesia.onebox_cart_app.infrastructure.persistence.repositories.MySqlCustomerRepository;
import com.delaiglesia.onebox_cart_app.infrastructure.persistence.repositories.impl.CustomerRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomerConfig {

  @Bean
  public CustomerRepositoryImpl createCustomerRepository(
      final MySqlCustomerRepository mySqlCustomerRepository,
      final CustomerRepositoryConverter customerRepositoryConverter) {
    return new CustomerRepositoryImpl(mySqlCustomerRepository, customerRepositoryConverter);
  }
}
