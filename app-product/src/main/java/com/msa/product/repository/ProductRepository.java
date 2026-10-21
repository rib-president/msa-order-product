package com.msa.product.repository;

import com.msa.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface ProductRepository extends JpaRepository<Product, BigInteger>, ProductCustomRepository {
}
