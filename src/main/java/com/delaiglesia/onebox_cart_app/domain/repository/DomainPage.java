package com.delaiglesia.onebox_cart_app.domain.repository;

import java.util.List;

public interface DomainPage<C> {
  List<C> getContent();

  long getTotalElements();
}
