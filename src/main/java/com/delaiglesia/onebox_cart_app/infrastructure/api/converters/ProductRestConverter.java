package com.delaiglesia.onebox_cart_app.infrastructure.api.converters;

import com.delaiglesia.onebox_cart_app.domain.entity.Product;
import com.delaiglesia.onebox_cart_app.infrastructure.api.dto.ProductDto;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductRestConverter extends RestConverter<ProductDto, Product> {

  @Override
  ProductDto mapToDto(Product product);

  @Override
  Product mapToEntity(ProductDto productDto);

  @Override
  default List<ProductDto> mapToDto(List<Product> productList) {
    if (productList == null) {
      return Collections.emptyList();
    }

    List<ProductDto> list = new ArrayList<>(productList.size());
    for (Product product : productList) {
      list.add(mapToDto(product));
    }
    return list;
  }

  @Override
  Product mapToEntityCreate(ProductDto productDto);

  @Override
  ProductDto mapToDtoCreate(Product product);
}
