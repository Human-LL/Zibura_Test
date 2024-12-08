package com.example.mcsample.controller;


import com.example.mcsample.controller.api.ProductApi;
import com.example.mcsample.dto.ProductDTO;
import com.example.mcsample.dto.UserDTO;
import com.example.mcsample.dto.response.ResponseHandler;
import com.example.mcsample.dto.response.ResponseObj;
import com.example.mcsample.service.interfaces.ProductService;
import com.example.mcsample.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Log4j2
@RestController
public class ProductControllerImpl implements ProductApi {

    private final ProductService productService;

    @Override
    public ResponseEntity<ResponseObj<ProductDTO>> readProduct(UUID uuid) {
        return ResponseHandler.generateResponse(HttpStatus.OK, productService.findById(uuid));
    }

    @Override
    public ResponseEntity<ResponseObj<List<ProductDTO>>> readAllProduct() {
        return ResponseHandler.generateResponse(HttpStatus.OK, productService.findAll());
    }

    @Override
    public ResponseEntity<ResponseObj<ProductDTO>> createProduct(@RequestBody ProductDTO dto) {
        return ResponseHandler.generateResponse(HttpStatus.CREATED, productService.createProduct(dto));
    }

    @Override
    public ResponseEntity<ResponseObj<ProductDTO>> updatePrice(@PathVariable UUID id, @RequestBody BigDecimal newPrice) {
        return ResponseHandler.generateResponse(HttpStatus.OK, productService.updatePrice(id, newPrice));
    }

    @Override
    public ResponseEntity<ResponseObj<ProductDTO>> applyDiscount(@PathVariable UUID id, @RequestBody BigDecimal discount) {
        return ResponseHandler.generateResponse(HttpStatus.OK, productService.applyDiscount(id,discount));
    }


    @Override
    public ResponseEntity<ResponseObj<String>> deleteProduct(UUID id) {
        return ResponseHandler.generateResponse(HttpStatus.OK, productService.delete(id));
    }

}