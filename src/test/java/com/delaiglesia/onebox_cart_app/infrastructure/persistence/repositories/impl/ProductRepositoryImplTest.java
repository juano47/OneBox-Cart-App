package com.delaiglesia.onebox_cart_app.infrastructure.persistence.repositories.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.delaiglesia.onebox_cart_app.domain.entity.Product;
import com.delaiglesia.onebox_cart_app.infrastructure.persistence.converters.ProductRepositoryConverter;
import com.delaiglesia.onebox_cart_app.infrastructure.persistence.entities.ProductEntity;
import com.delaiglesia.onebox_cart_app.infrastructure.persistence.repositories.MySqlProductRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ProductRepositoryImplTest {

  private ProductRepositoryImpl productRepository;

  private ProductRepositoryConverter productRepositoryConverter;

  private MySqlProductRepository mySqlProductRepository;

  @BeforeEach
  void setUp() {
    mySqlProductRepository = mock(MySqlProductRepository.class);
    productRepositoryConverter = mock(ProductRepositoryConverter.class);
    productRepository =
        new ProductRepositoryImpl(mySqlProductRepository, productRepositoryConverter);
  }

  @Test
  void getProduct() {
    Long id = 1L;
    Product productOutput = new Product();
    ProductEntity productEntityOutput = new ProductEntity();

    when(mySqlProductRepository.findById(id)).thenReturn(Optional.of(productEntityOutput));
    when(productRepositoryConverter.mapToEntity(productEntityOutput)).thenReturn(productOutput);

    Product result = productRepository.getProduct(id);

    assertEquals(productOutput, result);

    verify(mySqlProductRepository).findById(id);
    verify(productRepositoryConverter).mapToEntity(productEntityOutput);
  }

  @Test
  void shouldThrowEntityNotFoundExceptionWhenProductNotFound() {
    Long id = 1L;

    when(mySqlProductRepository.findById(id)).thenReturn(Optional.empty());

    EntityNotFoundException exception =
        assertThrows(EntityNotFoundException.class, () -> productRepository.getProduct(id));

    assertEquals("Product with id 1 not found", exception.getMessage());

    verify(mySqlProductRepository).findById(id);
  }
}
