package com.accenture.assessment.cafeordermanager.service;

import com.accenture.assessment.cafeordermanager.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/** This service class contains the business logic to calculate the amount paid/owed by a user */
@Service
@RequiredArgsConstructor
public class PaymentService {
  private final PaymentRepository paymentRepository;

  /**
   * Fetches the total amount paid by a user
   *
   * @param username - name of the user for which the amount needs to be calculated
   * @return {@code String} returns the total amount paid by a user
   */
  public String getAmountPaidByUsername(final String username) {
    // TODO: add logging and handle error scenarios
    // TODO: add unit tests
    double sum =
        paymentRepository.findAllByUser(username).stream()
            .mapToDouble(payment -> Double.parseDouble(payment.getAmount().toString()))
            .sum();
    return String.valueOf(sum);
  }
}
