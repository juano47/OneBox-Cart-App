package com.delaiglesia.onebox_cart_app.infrastructure.persistence.repositories.impl;

import com.delaiglesia.onebox_cart_app.domain.entity.Restaurant;
import com.delaiglesia.onebox_cart_app.domain.repository.RestaurantRepository;
import com.delaiglesia.onebox_cart_app.infrastructure.persistence.converters.RestaurantRepositoryConverter;
import com.delaiglesia.onebox_cart_app.infrastructure.persistence.repositories.MySqlRestaurantRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class RestaurantRepositoryImpl implements RestaurantRepository {

  private final MySqlRestaurantRepository mySqlRestaurantRepository;
  private final RestaurantRepositoryConverter restaurantRepositoryConverter;

  @Override
  public Restaurant getRestaurant(final Long id) {
    return restaurantRepositoryConverter.mapToEntity(
        mySqlRestaurantRepository
            .findById(id)
            .orElseThrow(
                () -> new EntityNotFoundException("Restaurant with id " + id + " not found")));
  }

  @Override
  public List<Restaurant> getAllRestaurants() {
    return restaurantRepositoryConverter.mapToEntity(mySqlRestaurantRepository.findAll());
  }

  @Override
  public Restaurant saveRestaurant(Restaurant restaurant) {
    return restaurantRepositoryConverter.mapToEntity(
        mySqlRestaurantRepository.save(restaurantRepositoryConverter.mapToTable(restaurant)));
  }

  @Override
  public void removeRestaurant(Long id) {
    throw new UnsupportedOperationException("Not implemented yet");
  }

  @Override
  public Restaurant updateRestaurant(Restaurant restaurant) {
    throw new UnsupportedOperationException("Not implemented yet");
  }
}
