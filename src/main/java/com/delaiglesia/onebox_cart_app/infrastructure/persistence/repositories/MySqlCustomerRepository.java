package com.delaiglesia.onebox_cart_app.infrastructure.persistence.repositories;

import com.delaiglesia.onebox_cart_app.infrastructure.persistence.entities.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MySqlCustomerRepository extends JpaRepository<CustomerEntity, Long> {}
