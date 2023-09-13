package com.delaiglesia.onebox_cart_app.infrastructure.api.converters;

import java.io.Serializable;
import java.util.List;

/**
 * Contract for a generic dto to entity mapper.
 *
 * @param <R> - DTO type parameter.
 * @param <E> - Entity type parameter.
 */
public interface RestConverter<R extends Serializable, E extends Serializable> {

  default E mapToEntity(final R rest) {
    throw new UnsupportedOperationException();
  }

  default R mapToDto(final E entity) {
    throw new UnsupportedOperationException();
  }

  default List<R> mapToDto(List<E> entityList) {
    throw new UnsupportedOperationException();
  }

  default E mapToEntityCreate(final R rest) {
    throw new UnsupportedOperationException();
  }

  default R mapToDtoCreate(final E entity) {
    throw new UnsupportedOperationException();
  }
}
