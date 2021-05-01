package com.accenture.assessment.cafeordermanager.service;

import com.accenture.assessment.cafeordermanager.exception.UserNotFoundException;
import com.accenture.assessment.cafeordermanager.model.entity.Order;
import com.accenture.assessment.cafeordermanager.model.entity.Payment;
import com.accenture.assessment.cafeordermanager.model.entity.Price;
import com.accenture.assessment.cafeordermanager.model.entity.Product;
import com.accenture.assessment.cafeordermanager.repository.OrderRepository;
import com.accenture.assessment.cafeordermanager.repository.PaymentRepository;
import com.accenture.assessment.cafeordermanager.repository.ProductRepository;
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
  @MockBean private ProductRepository productRepository;
  @MockBean private OrderRepository orderRepository;

  @BeforeEach
  void setup() {
    paymentService = new PaymentService(paymentRepository, productRepository, orderRepository);
  }

  @Nested
  class AmountPaid {
    @Test
    void givenNonExistingUsername_whenGetAmountPaidIsCalled_thenThrowUserNotFoundException() {
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
      assertEquals("3.0", amount);
    }
  }

  @Nested
  class AmountOwes {
    @Test
    void givenNonExistingUsername_whenGetAmountOwedIsCalled_thenThrowUserNotFoundException() {
      // given
      Mockito.when(orderRepository.findAllByUser("nonExistingUser")).thenReturn(null);

      // when and then
      assertThrows(
          UserNotFoundException.class,
          () -> paymentService.getAmountOwedByUsername("nonExistingUser"));
    }

    @Test
    void givenAnExistingUsername_whenGetAmountOwedIsCalled_thenAmountIsReturned()
        throws UserNotFoundException {
      // given and when
      Mockito.when(orderRepository.findAllByUser("TestUser")).thenReturn(createOrderList());
      Mockito.when(productRepository.findByDrinkName("latte")).thenReturn(createProduct());
      Mockito.when(paymentRepository.findAllByUser("TestUser")).thenReturn(createPaymentList());
      final String amount = paymentService.getAmountOwedByUsername("TestUser");

      // then
      assertEquals("2.0", amount);
    }
  }

  private List<Payment> createPaymentList() {
    Payment payment = Payment.builder().amount(new BigDecimal(3)).build();
    return List.of(payment);
  }

  private List<Order> createOrderList() {
    Order order = Order.builder().size("huge").user("TestUser").drink(createProduct()).build();
    return List.of(order);
  }

  private Product createProduct() {
    return Product.builder().drinkName("latte").price(Price.builder().huge("5.0").build()).build();
  }
}
