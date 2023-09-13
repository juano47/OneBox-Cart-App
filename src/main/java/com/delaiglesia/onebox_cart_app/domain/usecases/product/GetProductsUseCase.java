package com.delaiglesia.onebox_cart_app.domain.usecases.product;

import com.delaiglesia.onebox_cart_app.domain.entity.Product;
import com.delaiglesia.onebox_cart_app.domain.repository.ProductRepository;
import com.delaiglesia.onebox_cart_app.domain.usecases.UseCase;
import java.util.List;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@UseCase
public class GetProductsUseCase {
  private final ProductRepository productRepository;

  public List<Product> execute() {
    return productRepository.getAllProducts();
  }
}
