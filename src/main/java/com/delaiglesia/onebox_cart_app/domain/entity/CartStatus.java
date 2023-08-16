package com.delaiglesia.onebox_cart_app.domain.entity;

public enum CartStatus {
  EMPTY, // the cart is empty

  ACTIVE, // the user is adding items to the cart

  CHECKOUT, // ready to start payment process

  PAYMENT_PROCESSING,

  PAYMENT_ERROR,

  EXPIRED, // the user didn't update the cart in the last 10 minutes

  SAVED_FOR_LATER,

  USER_REMOVED, // the user has removed the cart

  DELIVERING, // the cart is being delivered

  DELIVERED, // the cart has been marked as delivered by the company

  COMPLETED, // the cart has been mark as received by the user

  CANCELLED, // the cart has been cancelled by the company

  USER_CANCELLED, // the cart has been cancelled by the user
}
