package com.msa.order.repository;

import com.msa.order.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface OrderItemRepository extends JpaRepository<OrderItem, BigInteger> {
}
