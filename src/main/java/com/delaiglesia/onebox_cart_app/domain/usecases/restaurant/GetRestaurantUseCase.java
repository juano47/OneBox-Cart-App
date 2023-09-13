package com.delaiglesia.onebox_cart_app.domain.usecases.restaurant;

import com.delaiglesia.onebox_cart_app.domain.entity.Restaurant;
import com.delaiglesia.onebox_cart_app.domain.repository.RestaurantRepository;
import com.delaiglesia.onebox_cart_app.domain.usecases.UseCase;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@UseCase
public class GetRestaurantUseCase {

  private final RestaurantRepository restaurantRepository;

  public Restaurant execute(Long id) {
    return restaurantRepository.getRestaurant(id);
  }
}
