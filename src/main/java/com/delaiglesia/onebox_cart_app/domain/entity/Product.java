package com.delaiglesia.onebox_cart_app.domain.entity;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Product implements Serializable {
  private Long id;
  private String name;
  private String description;
  private double price;
}
