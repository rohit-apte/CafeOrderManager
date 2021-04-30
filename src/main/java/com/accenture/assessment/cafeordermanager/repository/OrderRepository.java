package com.accenture.assessment.cafeordermanager.repository;

import com.accenture.assessment.cafeordermanager.model.entity.Order;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Long> {


    List<Order> findAllByUser(final String username);
}
