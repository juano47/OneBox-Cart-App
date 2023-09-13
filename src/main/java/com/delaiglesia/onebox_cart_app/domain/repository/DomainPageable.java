package com.delaiglesia.onebox_cart_app.domain.repository;

import org.springframework.data.domain.Sort;

public interface DomainPageable {
  int getSize();

  int getPage();

  Sort getSort();
}
