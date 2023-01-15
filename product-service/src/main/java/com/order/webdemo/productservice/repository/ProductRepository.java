package com.order.webdemo.productservice.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import com.order.webdemo.productservice.entity.Product;


public interface ProductRepository extends ReactiveMongoRepository<Product, String>{}
