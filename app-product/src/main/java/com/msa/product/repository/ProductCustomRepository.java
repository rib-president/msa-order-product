package com.msa.product.repository;

import com.msa.product.dto.GetProductsByPaginationRequestDto;
import com.msa.product.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductCustomRepository {
  Page<Product> findAllByCriteria(GetProductsByPaginationRequestDto criteria, Pageable pageable);
}
