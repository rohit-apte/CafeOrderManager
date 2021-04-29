package com.accenture.assessment.cafeordermanager.service;

import com.accenture.assessment.cafeordermanager.exception.UserNotFoundException;
import com.accenture.assessment.cafeordermanager.model.entity.Payment;
import com.accenture.assessment.cafeordermanager.repository.PaymentRepository;
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

  /**
   * Fetches the total amount paid by a user
   *
   * @param username - name of the user for which the amount needs to be calculated
   * @return {@code String} returns the total amount paid by a user
   */
  public String getAmountPaidByUsername(final String username) throws UserNotFoundException {
    log.info("Getting amount paid for username: {}", username);
    final List<Payment> payments = paymentRepository.findAllByUser(username);
    if (CollectionUtils.isEmpty(payments)) {
      log.error("No user found for given username: {}", username);
      throw new UserNotFoundException(username);
    }

    double sum =
        payments.stream()
            .mapToDouble(payment -> Double.parseDouble(payment.getAmount().toString()))
            .sum();
    log.info("Exiting amount paid for username: {}", username);
    return String.valueOf(sum);
  }
}
