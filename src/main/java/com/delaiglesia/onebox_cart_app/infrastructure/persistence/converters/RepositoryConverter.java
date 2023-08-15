package com.delaiglesia.onebox_cart_app.infrastructure.persistence.converters;

import java.io.Serializable;
import java.util.List;

public interface RepositoryConverter<T extends Serializable, P extends Serializable> {

  default T mapToTable(final P persistenceObject) {
    throw new UnsupportedOperationException();
  }

  default P mapToEntity(final T tableObject) {
    throw new UnsupportedOperationException();
  }

  default List<T> mapToTable(List<P> entityList) {
    throw new UnsupportedOperationException();
  }

  default List<P> mapToEntity(List<T> dtoList) {
    throw new UnsupportedOperationException();
  }
}
