package com.delaiglesia.onebox_cart_app.domain.entity;

public record CustomerAddress(Long id, String street, String city, String zipCode, String country) {

  public CustomerAddress() {
    this(null, null, null, null, null);
  }
}
