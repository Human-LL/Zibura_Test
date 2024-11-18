package com.example.mcsample.controller.api;

import com.example.mcsample.dto.SampleDTO;
import com.example.mcsample.dto.UserDTO;
import com.example.mcsample.dto.response.ResponseObj;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Validated
@Tag(name = "user", description = "crud операции")
@RequestMapping
public interface UserApi {

    @GetMapping("/users")
    ResponseEntity<ResponseObj<UserDTO>> getAllUsers(@RequestBody UserDTO userDTO);

    @GetMapping("/user/{id}")
    ResponseEntity<ResponseObj<UserDTO>> getUserById(@PathVariable UUID id);

    @PostMapping("/users")
    ResponseEntity<ResponseObj<UserDTO>> createUser(@Validated @RequestBody UserDTO userDTO);

}
