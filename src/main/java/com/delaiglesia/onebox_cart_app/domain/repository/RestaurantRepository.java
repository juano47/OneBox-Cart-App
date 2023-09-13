package com.delaiglesia.onebox_cart_app.domain.repository;

import com.delaiglesia.onebox_cart_app.domain.entity.Restaurant;
import java.util.List;

public interface RestaurantRepository {

  Restaurant getRestaurant(Long id);

  List<Restaurant> getAllRestaurants();

  Restaurant saveRestaurant(Restaurant restaurant);

  void removeRestaurant(Long id);

  Restaurant updateRestaurant(Restaurant restaurant);
}
