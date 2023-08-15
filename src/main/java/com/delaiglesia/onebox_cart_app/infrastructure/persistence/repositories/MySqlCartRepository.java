package com.delaiglesia.onebox_cart_app.infrastructure.persistence.repositories;

import com.delaiglesia.onebox_cart_app.domain.entity.CartStatus;
import com.delaiglesia.onebox_cart_app.infrastructure.persistence.entities.CartEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MySqlCartRepository extends JpaRepository<CartEntity, Long> {
  List<CartEntity> findAllByStatus(CartStatus status);
}
