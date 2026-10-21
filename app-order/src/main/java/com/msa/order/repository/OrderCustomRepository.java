package com.msa.order.repository;

import com.msa.order.dto.GetOrdersRequestDto;
import com.msa.order.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderCustomRepository {
  Page<Order> findAllByCriteria(GetOrdersRequestDto criteria, Pageable pageable);
}
