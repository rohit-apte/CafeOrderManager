package com.accenture.assessment.cafeordermanager.service;

import com.accenture.assessment.cafeordermanager.exception.UserNotFoundException;
import com.accenture.assessment.cafeordermanager.model.entity.Payment;
import com.accenture.assessment.cafeordermanager.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
class PaymentServiceTest {

  private PaymentService paymentService;

  @MockBean private PaymentRepository paymentRepository;

  @BeforeEach
  void setup() {
    paymentService = new PaymentService(paymentRepository);
  }

  @Nested
  class AmountPaid {
    @Test
    void givenNonExistingUsername_whenGetAmountPaidIsCalled_thenReturnNotFound() {
      // given
      Mockito.when(paymentRepository.findAllByUser("nonExistingUser")).thenReturn(null);

      // when and then
      assertThrows(
          UserNotFoundException.class,
          () -> paymentService.getAmountPaidByUsername("nonExistingUser"));
    }

    @Test
    void givenAnExistingUsername_whenGetAmountPaidIsCalled_thenAmountIsReturned()
        throws UserNotFoundException {
      // given and when
      Mockito.when(paymentRepository.findAllByUser("TestUser")).thenReturn(createPaymentList());
      final String amount = paymentService.getAmountPaidByUsername("TestUser");

      // then
      assertEquals("10.0", amount);
    }
  }

  private List<Payment> createPaymentList() {
    Payment payment = Payment.builder().amount(new BigDecimal(10)).build();
    return List.of(payment);
  }
}
