package com.delaiglesia.onebox_cart_app.infrastructure.api.controllers;

import com.delaiglesia.onebox_cart_app.domain.usecases.CreateCartUseCase;
import com.delaiglesia.onebox_cart_app.domain.usecases.DeleteCartUseCase;
import com.delaiglesia.onebox_cart_app.domain.usecases.GetCartUseCase;
import com.delaiglesia.onebox_cart_app.domain.usecases.GetCartsUseCase;
import com.delaiglesia.onebox_cart_app.domain.usecases.UpdateCartUseCase;
import com.delaiglesia.onebox_cart_app.infrastructure.api.converters.CartRestConverter;
import com.delaiglesia.onebox_cart_app.infrastructure.api.dto.CartDto;
import jakarta.validation.Valid;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
public class CartController {

  private final CreateCartUseCase createCartUseCase;

  private final GetCartUseCase getCartUseCase;

  private final UpdateCartUseCase updateCartUseCase;

  private final GetCartsUseCase getCartsUseCase;

  private final DeleteCartUseCase deleteCartUseCase;

  private final CartRestConverter cartRestConverter;

  public CartController(
      final CreateCartUseCase createCartUseCase,
      final GetCartUseCase getCartUseCase,
      final UpdateCartUseCase updateCartUseCase,
      final GetCartsUseCase getCartsUseCase,
      DeleteCartUseCase deleteCartUseCase, final CartRestConverter cartRestConverter) {
    this.createCartUseCase = createCartUseCase;
    this.getCartUseCase = getCartUseCase;
    this.updateCartUseCase = updateCartUseCase;
    this.getCartsUseCase = getCartsUseCase;
    this.deleteCartUseCase = deleteCartUseCase;
    this.cartRestConverter = cartRestConverter;
  }

  @GetMapping
  public List<CartDto> getCarts(@RequestParam final String status) {
    return getCartsUseCase.execute(status).stream().map(cartRestConverter::mapToDto).toList();
  }

  @GetMapping("/{id}")
  public CartDto getCart(@PathVariable final Long id) {
    return cartRestConverter.mapToDto(getCartUseCase.execute(id));
  }

  @PostMapping
  public CartDto createCart(@Valid @RequestBody final CartDto cartDto) {
    return cartRestConverter.mapToDto(
        createCartUseCase.execute(cartRestConverter.mapToEntityCreate(cartDto)));
  }

  @PutMapping("/{id}")
  public CartDto updateCart(@PathVariable final Long id, @RequestBody final CartDto cartDto) {
    return cartRestConverter.mapToDto(
        updateCartUseCase.execute(id, cartRestConverter.mapToEntity(cartDto)));
  }

  //physical delete
  @DeleteMapping("/{id}")
  public void deleteCart(@PathVariable final Long id) {
    deleteCartUseCase.execute(id);
  }
}
