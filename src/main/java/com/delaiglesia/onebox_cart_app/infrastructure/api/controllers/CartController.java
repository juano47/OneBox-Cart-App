package com.delaiglesia.onebox_cart_app.infrastructure.api.controllers;

import com.delaiglesia.onebox_cart_app.domain.entity.Cart;
import com.delaiglesia.onebox_cart_app.domain.repository.DomainPage;
import com.delaiglesia.onebox_cart_app.domain.usecases.cart.CreateCartUseCase;
import com.delaiglesia.onebox_cart_app.domain.usecases.cart.DeleteCartUseCase;
import com.delaiglesia.onebox_cart_app.domain.usecases.cart.GetCartUseCase;
import com.delaiglesia.onebox_cart_app.domain.usecases.cart.GetCartsUseCase;
import com.delaiglesia.onebox_cart_app.domain.usecases.cart.UpdateCartUseCase;
import com.delaiglesia.onebox_cart_app.infrastructure.api.converters.CartRestConverter;
import com.delaiglesia.onebox_cart_app.infrastructure.api.dto.CartDto;
import com.delaiglesia.onebox_cart_app.infrastructure.persistence.repositories.CartPageable;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
      DeleteCartUseCase deleteCartUseCase,
      final CartRestConverter cartRestConverter) {
    this.createCartUseCase = createCartUseCase;
    this.getCartUseCase = getCartUseCase;
    this.updateCartUseCase = updateCartUseCase;
    this.getCartsUseCase = getCartsUseCase;
    this.deleteCartUseCase = deleteCartUseCase;
    this.cartRestConverter = cartRestConverter;
  }

  @GetMapping
  public Page<CartDto> getCarts(
      @PageableDefault(size = 20) Pageable pageable, @RequestParam final String status) {
    CartPageable cartPageable = new CartPageable(pageable);

    DomainPage<Cart> result = getCartsUseCase.execute(cartPageable, status);

    List<CartDto> cartDtos = result.getContent().stream().map(cartRestConverter::mapToDto).toList();

    return new PageImpl<>(cartDtos, pageable, result.getTotalElements());
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

  // physical delete
  @DeleteMapping("/{id}")
  public void deleteCart(@PathVariable final Long id) {
    deleteCartUseCase.execute(id);
  }
}
