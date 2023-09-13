package com.delaiglesia.onebox_cart_app.infrastructure.api.controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.delaiglesia.onebox_cart_app.domain.entity.Cart;
import com.delaiglesia.onebox_cart_app.domain.usecases.CreateCartUseCase;
import com.delaiglesia.onebox_cart_app.domain.usecases.DeleteCartUseCase;
import com.delaiglesia.onebox_cart_app.domain.usecases.GetCartUseCase;
import com.delaiglesia.onebox_cart_app.domain.usecases.GetCartsUseCase;
import com.delaiglesia.onebox_cart_app.domain.usecases.UpdateCartUseCase;
import com.delaiglesia.onebox_cart_app.infrastructure.api.converters.CartRestConverter;
import com.delaiglesia.onebox_cart_app.infrastructure.api.dto.CartDto;
import com.delaiglesia.onebox_cart_app.infrastructure.persistence.repositories.CartPage;
import com.delaiglesia.onebox_cart_app.infrastructure.persistence.repositories.CartPageable;
import com.fasterxml.jackson.databind.JsonNode;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MvcResult;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = CartController.class)
class CartControllerTest extends AbstractControllerTest {

  @MockBean private CreateCartUseCase createCartUseCase;

  @MockBean private GetCartUseCase getCartUseCase;

  @MockBean private GetCartsUseCase getCartsUseCase;

  @MockBean private UpdateCartUseCase updateCartUseCase;

  @MockBean private DeleteCartUseCase deleteCartUseCase;

  @MockBean private CartRestConverter cartRestConverter;

  @Test
  void getCarts() throws Exception {
    String status = "RANDOM_STATUS";
    List<Cart> expectedCarts = List.of(new Cart(), new Cart());
    when(getCartsUseCase.execute(any(CartPageable.class), eq(status)))
        .thenReturn(new CartPage(new PageImpl<>(expectedCarts, Pageable.unpaged(), 2)));
    when(cartRestConverter.mapToDto(any(Cart.class))).thenReturn(new CartDto());

    MvcResult result =
        mockMvc
            .perform(
                get(String.format("/cart?status=%s", status))
                    .accept(MediaType.ALL)
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andReturn();

    assertNotNull(result.getResponse().getContentAsString());
    assertFalse(result.getResponse().getContentAsString().isEmpty());

    JsonNode jsonNode = objectMapper.readTree(result.getResponse().getContentAsString());
    List<CartDto> cartDtos = objectMapper.readValue(jsonNode.get("content").toString(), List.class);
    // assert content
    assertEquals(expectedCarts.size(), cartDtos.size());
    // assert pagination
    assertEquals(expectedCarts.size(), jsonNode.get("totalElements").asInt());
    assertEquals(0, jsonNode.get("number").asInt());
    assertEquals(1, jsonNode.get("totalPages").asInt());
    assertEquals(20, jsonNode.get("size").asInt());

    verify(getCartsUseCase).execute(any(CartPageable.class), eq(status));
    verify(cartRestConverter, times(expectedCarts.size())).mapToDto(any(Cart.class));
  }

  @Test
  void getCart() throws Exception {
    Long cartId = 1L;
    CartDto expectedCartDto = new CartDto();
    expectedCartDto.setId(cartId);
    when(getCartUseCase.execute(cartId)).thenReturn(new Cart());
    when(cartRestConverter.mapToDto(any(Cart.class))).thenReturn(expectedCartDto);

    MvcResult result =
        mockMvc
            .perform(
                get(String.format("/cart/%s", cartId))
                    .accept(MediaType.ALL)
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andReturn();

    assertNotNull(result.getResponse().getContentAsString());
    assertFalse(result.getResponse().getContentAsString().isEmpty());

    CartDto responseBody =
        objectMapper.readValue(result.getResponse().getContentAsString(), CartDto.class);
    assertEquals(expectedCartDto.getId(), responseBody.getId());

    verify(getCartUseCase).execute(cartId);
    verify(cartRestConverter).mapToDto(any(Cart.class));
  }

  @Test
  void createCart() throws Exception {

    CartDto inputCartDto = new CartDto();

    Long cartId = 1L;
    Cart cart = new Cart();
    CartDto expectedCartDto = new CartDto();
    expectedCartDto.setId(cartId);

    when(createCartUseCase.execute(any(Cart.class))).thenReturn(cart);
    when(cartRestConverter.mapToDto(cart)).thenReturn(expectedCartDto);
    when(cartRestConverter.mapToEntityCreate(any(CartDto.class))).thenReturn(cart);

    MvcResult result =
        mockMvc
            .perform(
                post("/cart")
                    .content(objectMapper.writeValueAsString(inputCartDto))
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andReturn();

    assertNotNull(result.getResponse().getContentAsString());
    assertFalse(result.getResponse().getContentAsString().isEmpty());

    CartDto responseBody =
        objectMapper.readValue(result.getResponse().getContentAsString(), CartDto.class);

    assertEquals(cartId, responseBody.getId());

    verify(createCartUseCase).execute(any(Cart.class));
    verify(cartRestConverter).mapToDto(cart);
    verify(cartRestConverter).mapToEntityCreate(any(CartDto.class));
  }

  @Test
  void updateCart() throws Exception {
    CartDto inputCartDto = new CartDto();

    Long cartId = 1L;
    Cart cart = new Cart();
    CartDto expectedCartDto = new CartDto();
    expectedCartDto.setId(cartId);

    when(updateCartUseCase.execute(cartId, cart)).thenReturn(cart);
    when(cartRestConverter.mapToDto(cart)).thenReturn(expectedCartDto);
    when(cartRestConverter.mapToEntity(any(CartDto.class))).thenReturn(cart);

    MvcResult result =
        mockMvc
            .perform(
                put(String.format("/cart/%s", cartId))
                    .content(objectMapper.writeValueAsString(inputCartDto))
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andReturn();

    assertNotNull(result.getResponse().getContentAsString());
    assertFalse(result.getResponse().getContentAsString().isEmpty());

    CartDto responseBody =
        objectMapper.readValue(result.getResponse().getContentAsString(), CartDto.class);

    assertEquals(cartId, responseBody.getId());

    verify(updateCartUseCase).execute(cartId, cart);
    verify(cartRestConverter).mapToDto(cart);
    verify(cartRestConverter).mapToEntity(any(CartDto.class));
  }

  @Test
  void deleteCart() throws Exception {
    Long cartId = 1L;

    mockMvc
        .perform(
            delete(String.format("/cart/%s", cartId))
                .accept(MediaType.ALL)
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andReturn();

    verify(deleteCartUseCase).execute(cartId);
  }
}
