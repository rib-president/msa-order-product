package com.msa.order.repository;

import com.msa.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface OrderRepository extends JpaRepository<Order, BigInteger>, OrderCustomRepository {
}
