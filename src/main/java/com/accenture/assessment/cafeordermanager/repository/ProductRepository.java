package com.accenture.assessment.cafeordermanager.repository;

import com.accenture.assessment.cafeordermanager.model.entity.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, String> {

    Product findByDrinkName(final String drinkName);
}
