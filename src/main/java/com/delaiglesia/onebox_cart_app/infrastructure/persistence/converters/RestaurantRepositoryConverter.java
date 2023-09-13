package com.delaiglesia.onebox_cart_app.infrastructure.persistence.converters;

import com.delaiglesia.onebox_cart_app.domain.entity.Restaurant;
import com.delaiglesia.onebox_cart_app.infrastructure.persistence.entities.RestaurantEntity;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RestaurantRepositoryConverter
    extends RepositoryConverter<RestaurantEntity, Restaurant> {

  @Override
  RestaurantEntity mapToTable(Restaurant restaurant);

  @Override
  Restaurant mapToEntity(RestaurantEntity restaurantEntity);

  @Override
  List<Restaurant> mapToEntity(List<RestaurantEntity> restaurantEntityList);
}
