package com.accenture.assessment.cafeordermanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class CafeOrderManagerApplication {

  public static void main(String[] args) {
    SpringApplication.run(CafeOrderManagerApplication.class, args);
  }

}
