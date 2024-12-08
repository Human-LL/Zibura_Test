package com.example.mcsample.service;

import com.example.mcsample.dto.ProductDTO;
import com.example.mcsample.entity.Product;
import com.example.mcsample.exception.NotFoundException;
import com.example.mcsample.mapper.ProductMapper;
import com.example.mcsample.repository.ProductRepository;
import com.example.mcsample.service.interfaces.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper mapper;

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        Product product = mapper.toEntity(productDTO);
        Product savedProduct = productRepository.save(product);
        return mapper.toDTO(savedProduct);
    }

    @Override
    public ProductDTO updatePrice(UUID productId, BigDecimal newPrice) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException("Product not found"));
        product.setPrice(newPrice);
        return mapper.toDTO(productRepository.save(product));
    }

    @Override
    public ProductDTO applyDiscount(UUID productId, BigDecimal discount) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException("Product not found"));
        product.setDiscount(discount);
        return mapper.toDTO(productRepository.save(product));
    }

    @Override
    public String delete(UUID id) {
        productRepository.deleteById(id);
        return "Product successfully deleted with id: " + id;
    }

    @Override
    public ProductDTO findById(UUID id) {
        return productRepository.findById(id)
                .map(mapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + id));
    }

    @Override
    public List<ProductDTO> findAll() {
        return productRepository.findAll().stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }
}
