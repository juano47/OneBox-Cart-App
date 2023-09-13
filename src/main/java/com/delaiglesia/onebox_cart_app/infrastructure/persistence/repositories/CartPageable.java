package com.delaiglesia.onebox_cart_app.infrastructure.persistence.repositories;

import com.delaiglesia.onebox_cart_app.domain.repository.DomainPageable;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@AllArgsConstructor
public class CartPageable implements DomainPageable {

  private final Pageable pageable;

  @Override
  public int getSize() {
    return pageable.getPageSize();
  }

  @Override
  public int getPage() {
    return pageable.getPageNumber();
  }

  @Override
  public Sort getSort() {
    return pageable.getSort();
  }
}
