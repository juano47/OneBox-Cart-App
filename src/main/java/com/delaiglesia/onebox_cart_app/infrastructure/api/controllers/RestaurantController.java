package com.delaiglesia.onebox_cart_app.infrastructure.api.controllers;

import com.delaiglesia.onebox_cart_app.domain.usecases.restaurant.CreateRestaurantUseCase;
import com.delaiglesia.onebox_cart_app.domain.usecases.restaurant.GetRestaurantUseCase;
import com.delaiglesia.onebox_cart_app.infrastructure.api.converters.RestaurantRestConverter;
import com.delaiglesia.onebox_cart_app.infrastructure.api.dto.RestaurantDto;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

  private final CreateRestaurantUseCase createRestaurantUseCase;

  private final GetRestaurantUseCase getRestaurantUseCase;

  private final RestaurantRestConverter restaurantRestConverter;

  public RestaurantController(
      final CreateRestaurantUseCase createRestaurantUseCase,
      final GetRestaurantUseCase getRestaurantUseCase,
      final RestaurantRestConverter restaurantRestConverter) {
    this.createRestaurantUseCase = createRestaurantUseCase;
    this.getRestaurantUseCase = getRestaurantUseCase;
    this.restaurantRestConverter = restaurantRestConverter;
  }

  @GetMapping
  public RestaurantDto getRestaurant(@RequestParam final Long rid) {
    return restaurantRestConverter.mapToDto(getRestaurantUseCase.execute(rid));
  }
}
