package com.delaiglesia.onebox_cart_app.domain.entity;

public enum CartStatus {
  // the cart is empty
  EMPTY,
  // the user is adding items to the cart
  ACTIVE,
  // the user is updating the cart
  UPDATING,
  // ready to start payment process
  CHECKOUT,
  PAYMENT_PROCESSING,
  PAYMENT_ERROR,
  // the user didn't update the cart in the last 10 minutes
  EXPIRED,
  SAVED_FOR_LATER,
  // the user has removed the cart
  USER_REMOVED,
  // the cart is being delivered
  DELIVERING,
  // the cart has been marked as delivered by the company
  DELIVERED,
  // the cart has been mark as received by the user
  COMPLETED,
  // the cart has been cancelled by the company
  CANCELLED,
  // the cart has been cancelled by the user
  USER_CANCELLED,
}
