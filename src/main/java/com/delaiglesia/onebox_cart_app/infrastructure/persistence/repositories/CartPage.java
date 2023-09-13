package com.delaiglesia.onebox_cart_app.infrastructure.persistence.repositories;

import com.delaiglesia.onebox_cart_app.domain.entity.Cart;
import com.delaiglesia.onebox_cart_app.domain.repository.DomainPage;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;

@AllArgsConstructor
public class CartPage implements DomainPage<Cart> {

  private final Page<Cart> page;

  @Override
  public List<Cart> getContent() {
    return page.getContent();
  }

  @Override
  public long getTotalElements() {
    return page.getTotalElements();
  }
}
