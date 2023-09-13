package com.delaiglesia.onebox_cart_app.infrastructure.api.controllers;

import com.delaiglesia.onebox_cart_app.domain.usecases.product.GetProductsUseCase;
import com.delaiglesia.onebox_cart_app.infrastructure.api.converters.ProductRestConverter;
import com.delaiglesia.onebox_cart_app.infrastructure.api.dto.ProductDto;
import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping("/product")
public class ProductController {

  private final GetProductsUseCase getProductsUseCase;

  private final ProductRestConverter productRestConverter;

  public ProductController(
      final GetProductsUseCase getProductsUseCase,
      final ProductRestConverter productRestConverter) {
    this.getProductsUseCase = getProductsUseCase;
    this.productRestConverter = productRestConverter;
  }

  @GetMapping
  public List<ProductDto> getProducts() {
    return getProductsUseCase.execute().stream().map(productRestConverter::mapToDto).toList();
  }
}
