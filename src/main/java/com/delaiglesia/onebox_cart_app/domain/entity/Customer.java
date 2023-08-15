package com.delaiglesia.onebox_cart_app.domain.entity;

import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Customer implements Serializable {
  private Long id;
  private String firstname;
  private String lastname;
  private String email;
  private String phone;
  private List<CustomerAddress> addresses;
  private List<Cart> carts;
}
