package com.accenture.assessment.cafeordermanager.controller;

import com.accenture.assessment.cafeordermanager.exception.UserNotFoundException;
import com.accenture.assessment.cafeordermanager.service.PaymentService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class OrderControllerTest {

  @MockBean private PaymentService paymentService;

  @Autowired private MockMvc mockMvc;

  @Test
  void givenNonExistingUsername_whenGetAmountPaidIsCalled_thenReturnNotFound() throws Exception {
    // given
    Mockito.when(paymentService.getAmountPaidByUsername("nonExistingUser"))
        .thenThrow(new UserNotFoundException("nonExistingUser"));

    // when and then
    mockMvc
        .perform(get("/amount/paid/{username}", "nonExistingUser"))
        .andExpect(status().is4xxClientError())
        .andExpect(content().string("No user found for nonExistingUser"));
  }

  @Test
  void givenAnExistingUsername_whenGetAmountPaidIsCalled_thenAmountIsReturned() throws Exception {
    // given
    Mockito.when(paymentService.getAmountPaidByUsername("TestUser")).thenReturn("10.0");

    // when and then
    mockMvc
        .perform(get("/amount/paid/{username}", "TestUser"))
        .andExpect(status().is2xxSuccessful())
        .andExpect(content().string("10.0"));
  }
}
