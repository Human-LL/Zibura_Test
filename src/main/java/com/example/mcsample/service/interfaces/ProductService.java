package com.example.mcsample.service.interfaces;


import com.example.mcsample.dto.ProductDTO;
import com.example.mcsample.entity.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface ProductService {
    ProductDTO createProduct(ProductDTO productDTO);

    ProductDTO updatePrice(UUID productId, BigDecimal newPrice);

    ProductDTO applyDiscount(UUID productId, BigDecimal discount);

    String delete(UUID id);

    ProductDTO findById(UUID id);

    List<ProductDTO> findAll();
}