package com.accenture.assessment.cafeordermanager.repository;

import com.accenture.assessment.cafeordermanager.model.entity.Payment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/** Repository for managing Data Layer */
@Repository
public interface PaymentRepository extends CrudRepository<Payment, Long> {

  List<Payment> findAllByUser(final String username);
}
