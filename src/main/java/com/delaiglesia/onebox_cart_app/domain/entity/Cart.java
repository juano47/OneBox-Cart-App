package com.delaiglesia.onebox_cart_app.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Cart implements Serializable {
  private Long id;
  private List<CartItem> items;
  private double total;
  private Customer customer;
  private CartStatus status;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
  private LocalDateTime deletedAt;
  private CustomerAddress shippingAddress;

  // TODO: check if the shipping address is one of the customer addresses
  /*public void setShippingAddress(CustomerAddress shippingAddress) {
  	if (customer == null) {
  		throw new IllegalArgumentException("Customer must be set before setting the shipping address");
  	}

  	if (customer.getAddresses().contains(shippingAddress)) {
  		this.shippingAddress = shippingAddress;
  	} else {
  		throw new IllegalArgumentException("Shipping address must be one of the customer addresses");
  	}
  }*/
}
