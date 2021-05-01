package com.accenture.assessment.cafeordermanager.configuration;

import com.accenture.assessment.cafeordermanager.model.dto.OrderDto;
import com.accenture.assessment.cafeordermanager.model.dto.ProductDto;
import com.accenture.assessment.cafeordermanager.model.entity.Order;
import com.accenture.assessment.cafeordermanager.model.entity.Payment;
import com.accenture.assessment.cafeordermanager.model.entity.Price;
import com.accenture.assessment.cafeordermanager.model.entity.Product;
import com.accenture.assessment.cafeordermanager.repository.OrderRepository;
import com.accenture.assessment.cafeordermanager.repository.PaymentRepository;
import com.accenture.assessment.cafeordermanager.repository.ProductRepository;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class OrderManagerConfiguration implements CommandLineRunner {
  private final PaymentRepository paymentRepository;
  private final ProductRepository productRepository;
  private final OrderRepository orderRepository;

  @Override
  public void run(String... args) throws Exception {

    // load initial payment data
    loadPaymentData();
    loadProductDetails();
    loadOrderDetails();

    log.info("########### INITIAL DATA LOADED ########");
  }

  private void loadPaymentData() throws Exception {
    String paymentFilePath = "src/main/resources/data/payments.json";
    File paymentsFile = ResourceUtils.getFile(paymentFilePath);
    String paymentJson = Files.readString(Paths.get(paymentsFile.getPath()));
    Gson gson = new Gson();
    Payment[] payments = gson.fromJson(paymentJson, Payment[].class);
    paymentRepository.saveAll(Arrays.asList(payments));
  }

  private void loadProductDetails() throws Exception {
    String productFilePath = "src/main/resources/data/products.json";
    File productFile = ResourceUtils.getFile(productFilePath);
    String productJson = Files.readString(Paths.get(productFile.getPath()));
    Gson gsonProduct = new Gson();
    ProductDto[] productDtos = gsonProduct.fromJson(productJson, ProductDto[].class);
    List<Product> products = new ArrayList<>();
    Product product;
    Price price;
    for (ProductDto productDto : productDtos) {
      price = productDto.getPrices();
      product = Product.builder().drinkName(productDto.getDrink_name()).price(price).build();
      price.setProduct(product);
      products.add(product);
    }
    productRepository.saveAll(products);
  }

  private void loadOrderDetails() throws Exception {
    String orderFilePath = "src/main/resources/data/orders.json";
    File orderFile = ResourceUtils.getFile(orderFilePath);
    String orderJson = Files.readString(Paths.get(orderFile.getPath()));
    Gson gsonOrder = new Gson();
    OrderDto[] orderDtos = gsonOrder.fromJson(orderJson, OrderDto[].class);
    List<Order> orders = new ArrayList<>();
    Order order;
    for (OrderDto orderDto : orderDtos) {
      String drink = orderDto.getDrink();
      order =
          Order.builder()
              .user(orderDto.getUser())
              .drink(Product.builder().drinkName(drink).build())
              .size(orderDto.getSize())
              .build();
      orders.add(order);
    }

    orderRepository.saveAll(orders);
  }
}
