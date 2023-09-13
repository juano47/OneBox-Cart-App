package com.delaiglesia.onebox_cart_app.infrastructure.persistence.repositories;

import com.delaiglesia.onebox_cart_app.infrastructure.persistence.entities.RestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MySqlRestaurantRepository extends JpaRepository<RestaurantEntity, Long> {}
