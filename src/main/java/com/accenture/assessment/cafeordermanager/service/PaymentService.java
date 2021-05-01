package com.accenture.assessment.cafeordermanager.service;

import com.accenture.assessment.cafeordermanager.exception.UserNotFoundException;
import com.accenture.assessment.cafeordermanager.model.entity.Order;
import com.accenture.assessment.cafeordermanager.model.entity.Payment;
import com.accenture.assessment.cafeordermanager.model.entity.Product;
import com.accenture.assessment.cafeordermanager.repository.OrderRepository;
import com.accenture.assessment.cafeordermanager.repository.PaymentRepository;
import com.accenture.assessment.cafeordermanager.repository.ProductRepository;
import com.accenture.assessment.cafeordermanager.util.CommonUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/** This service class contains the business logic to calculate the amount paid/owed by a user */
@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentService {
  private final PaymentRepository paymentRepository;
  private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

  /**
   * Fetches the total amount paid by a user
   *
   * @param username - name of the user for which the amount needs to be calculated
   * @return {@code String} returns the total amount paid by a user
   * @throws UserNotFoundException - if no order details found for given user
   */
  public String getAmountPaidByUsername(final String username) throws UserNotFoundException {
    log.info("Getting amount paid for username: {}", username);
    final List<Payment> payments = paymentRepository.findAllByUser(username);
    if (CollectionUtils.isEmpty(payments)) {
      log.error("No payment details found for user: {}", username);
      throw new UserNotFoundException(username);
    }

    double sum =
        payments.stream()
            .mapToDouble(payment -> Double.parseDouble(payment.getAmount().toString()))
            .sum();
    log.info("Exiting amount paid for username: {}", username);
    return String.valueOf(sum);
  }

  /**
   * Fetches the total amount owed by a user
   *
   * @param username - name of the user for which the amount needs to be calculated
   * @return {@code String} returns the total amount owed by a user
   * @throws UserNotFoundException - if no order details found for given user
   */
  public String getAmountOwedByUsername(String username) throws UserNotFoundException {
    log.info("Getting amount owed for username: {}", username);
    List<Order> orders = orderRepository.findAllByUser(username);
    if (CollectionUtils.isEmpty(orders)) {
      log.error("No order details found for user: {}", username);
      throw new UserNotFoundException(username);
    }
    double amountDue = calculateAmountByOrders(orders);
    double totalAmountPaidByUser = getTotalAmountPaidByUser(username);
    double amountOwed = amountDue - totalAmountPaidByUser;

    return String.valueOf(amountOwed);
  }

  private double calculateAmountByOrders(final List<Order> orders) {
    return orders.stream()
        .mapToDouble(
            order -> {
              String drinkName = order.getDrink().getDrinkName();
              String drinkSize = order.getSize();
              String amount = getPriceByProduct(drinkName, drinkSize);
              return Double.parseDouble(amount);
            })
        .sum();
  }

  private String getPriceByProduct(final String drinkName, final String drinkSize) {
    Product product = productRepository.findByDrinkName(drinkName);
    return CommonUtil.getAmountBySize(product.getPrice(), drinkSize);
  }

  private double getTotalAmountPaidByUser(final String username) {
    List<Payment> allPayments = paymentRepository.findAllByUser(username);
    return allPayments.stream()
        .mapToDouble(payment -> Double.parseDouble(payment.getAmount().toString()))
        .sum();
  }
}
