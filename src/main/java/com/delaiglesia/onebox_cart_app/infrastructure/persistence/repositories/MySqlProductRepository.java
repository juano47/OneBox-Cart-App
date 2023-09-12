package com.delaiglesia.onebox_cart_app.infrastructure.persistence.repositories;

import com.delaiglesia.onebox_cart_app.infrastructure.persistence.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MySqlProductRepository extends JpaRepository<ProductEntity, Long> {}
