package com.accenture.assessment.cafeordermanager.controller;

import com.accenture.assessment.cafeordermanager.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** Controller for managing order details like amount user paid and owes. */
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/amount")
public class OrderController {
  private final PaymentService paymentService;

  /**
   * Gets the total amount paid by a user
   *
   * @param username - name of the user for which the amount needs to be calculated
   * @return {@code String} returns the total amount paid by a user
   */
  @GetMapping("/paid/{username}")
  public String getAmountPaid(@PathVariable final String username) {
    return paymentService.getAmountPaidByUsername(username);
  }
}
