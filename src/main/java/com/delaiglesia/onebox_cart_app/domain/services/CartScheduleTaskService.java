package com.delaiglesia.onebox_cart_app.domain.services;

import com.delaiglesia.onebox_cart_app.domain.entity.Cart;
import com.delaiglesia.onebox_cart_app.domain.entity.CartStatus;
import com.delaiglesia.onebox_cart_app.domain.repository.CartRepository;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.logging.Logger;

@AllArgsConstructor
public class CartScheduleTaskService {

  private static final Logger logger = Logger.getLogger(CartScheduleTaskService.class.getName());

  private final CartRepository cartRepository;

  // execute every 60 seconds
  @Scheduled(fixedRate = 60000)
  public void updateCartToExpiredStatus() {
    logger.info("Executing updateCartToExpiredStatus scheduled task...");
    LocalDateTime cutoffTime = LocalDateTime.now().minus(10, ChronoUnit.MINUTES);
    List<Cart> expiredCarts = cartRepository.findCartsNotUpdatedInLastTenMinutes(cutoffTime);
    if (expiredCarts.isEmpty()) {
      logger.info("No expired carts found");
      return;
    }else {
      logger.info("Found " + expiredCarts.size() + " expired carts");
      expiredCarts.forEach(cart -> cart.setStatus(CartStatus.EXPIRED));
      cartRepository.saveAllCarts(expiredCarts);
      logger.info("Finished updateCartToExpiredStatus scheduled task");
    }

  }
}
