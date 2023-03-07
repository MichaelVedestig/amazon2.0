package com.amazon.api;

import com.amazon.api.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findAllById(Integer id);

    void deleteByIdAndStoreId(Integer productId, Integer storeId);
}
