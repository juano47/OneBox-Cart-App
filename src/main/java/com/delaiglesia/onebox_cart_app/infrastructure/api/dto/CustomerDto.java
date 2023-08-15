package com.delaiglesia.onebox_cart_app.infrastructure.api.dto;

import com.delaiglesia.onebox_cart_app.domain.entity.Cart;
import com.delaiglesia.onebox_cart_app.domain.entity.CustomerAddress;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDto {
  private Long id;
  private String firstname;
  private String lastname;
  private String email;
  private String phone;
  private List<CustomerAddress> addresses;
  private List<Cart> carts;
}
