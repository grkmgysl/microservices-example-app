package com.grkmgysl.productservice.repository;

import com.grkmgysl.productservice.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
}
