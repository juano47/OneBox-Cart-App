package com.delaiglesia.onebox_cart_app.infrastructure.api.converters;

import com.delaiglesia.onebox_cart_app.domain.entity.Restaurant;
import com.delaiglesia.onebox_cart_app.infrastructure.api.dto.RestaurantDto;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RestaurantRestConverter extends RestConverter<RestaurantDto, Restaurant> {

  @Override
  RestaurantDto mapToDto(Restaurant restaurant);

  @Override
  Restaurant mapToEntity(RestaurantDto restaurantDto);

  @Override
  default List<RestaurantDto> mapToDto(List<Restaurant> restaurantList) {
    if (restaurantList == null) {
      return Collections.emptyList();
    }

    List<RestaurantDto> list = new ArrayList<>(restaurantList.size());
    for (Restaurant restaurant : restaurantList) {
      list.add(mapToDto(restaurant));
    }
    return list;
  }

  @Override
  Restaurant mapToEntityCreate(RestaurantDto restaurantDto);

  @Override
  RestaurantDto mapToDtoCreate(Restaurant restaurant);
}
