package com.delaiglesia.onebox_cart_app.infrastructure.persistence.converters;

import com.delaiglesia.onebox_cart_app.domain.entity.Product;
import com.delaiglesia.onebox_cart_app.infrastructure.persistence.entities.ProductEntity;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductRepositoryConverter extends RepositoryConverter<ProductEntity, Product> {

  @Override
  ProductEntity mapToTable(Product product);

  @Override
  Product mapToEntity(ProductEntity productEntity);

  @Override
  List<Product> mapToEntity(List<ProductEntity> productEntityList);
}
