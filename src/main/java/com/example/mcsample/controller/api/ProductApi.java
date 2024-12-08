package com.example.mcsample.controller.api;

import com.example.mcsample.dto.ProductDTO;
import com.example.mcsample.dto.UserDTO;
import com.example.mcsample.dto.response.ResponseObj;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Validated
@Tag(name = "product", description = "CRUD  операции важные")
@RequestMapping("/api/products")
public interface ProductApi {

    @GetMapping("/read/{uid}")
    ResponseEntity<ResponseObj<ProductDTO>> readProduct(@PathVariable("uid") UUID uuid);

    @GetMapping("/read/all")
    ResponseEntity<ResponseObj<List<ProductDTO>>> readAllProduct();

    @PostMapping("/update/{uid}")
    ResponseEntity<ResponseObj<ProductDTO>> createProduct(@RequestBody ProductDTO dto);

    @PutMapping("/price/{id}")
    ResponseEntity<ResponseObj<ProductDTO>> updatePrice(@PathVariable UUID id, @RequestBody BigDecimal newPrice);

    @PutMapping("/discount/{id}")
    ResponseEntity<ResponseObj<ProductDTO>> applyDiscount(@PathVariable UUID id, @RequestBody BigDecimal discount);

    @DeleteMapping("/delete/{uid}")
    ResponseEntity<ResponseObj<String>> deleteProduct(@PathVariable("uid") UUID id);

}