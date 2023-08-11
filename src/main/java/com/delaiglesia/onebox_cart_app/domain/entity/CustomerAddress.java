package com.delaiglesia.onebox_cart_app.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomerAddress {
  private Long id;
  private String street;
  private String city;
  private String zipCode;
  private String country;
}
