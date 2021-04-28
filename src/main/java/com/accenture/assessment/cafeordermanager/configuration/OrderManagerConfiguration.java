package com.accenture.assessment.cafeordermanager.configuration;

import com.accenture.assessment.cafeordermanager.model.entity.Payment;
import com.accenture.assessment.cafeordermanager.repository.PaymentRepository;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class OrderManagerConfiguration implements CommandLineRunner {
    private final PaymentRepository paymentRepository;

    @Override
    public void run(String... args) throws Exception {

        // load initial payment data
        String paymentFilePath = "src/main/resources/data/payments.json";
        File paymentsFile = ResourceUtils.getFile(paymentFilePath);
        String paymentJson = Files.readString(Paths.get(paymentsFile.getPath()));
        Gson gson = new Gson();
        Payment[] payments = gson.fromJson(paymentJson, Payment[].class);
        paymentRepository.saveAll(Arrays.asList(payments));
        log.info("############### INITIAL DATA LOADED ###############");

    }
}
