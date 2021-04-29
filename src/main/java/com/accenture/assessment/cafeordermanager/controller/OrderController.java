package com.accenture.assessment.cafeordermanager.controller;

import com.accenture.assessment.cafeordermanager.exception.UserNotFoundException;
import com.accenture.assessment.cafeordermanager.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** Controller for managing order details like amount user paid and owes. */
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/amount")
@Slf4j
public class OrderController {
  private final PaymentService paymentService;

  /**
   * Gets the total amount paid by a user
   *
   * @param username - name of the user for which the amount needs to be calculated
   * @return {@code ResponseEntity} returns the total amount paid by a user if found else returns
   *     not found error
   */
  @GetMapping("/paid/{username}")
  public ResponseEntity<String> getAmountPaid(@PathVariable final String username) {
    String response;
    try {
      response = paymentService.getAmountPaidByUsername(username);
      return new ResponseEntity<>(response, HttpStatus.OK);
    } catch (UserNotFoundException e) {
      log.error(e.getMessage());
      return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
  }
}
