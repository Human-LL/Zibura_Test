package com.example.mcsample.controller.api;

import com.example.mcsample.dto.UserDTO;
import com.example.mcsample.dto.response.ResponseObj;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Validated
@Tag(name = "users", description = "crud операции")
@RequestMapping("/api/user")
public interface UserApi {

    @GetMapping("/read/{uid}")
    ResponseEntity<ResponseObj<UserDTO>> readUser(@PathVariable("uid") UUID uuid);

    @GetMapping("/read/all")
    ResponseEntity<ResponseObj<List<UserDTO>>> readAllUser();

    @PostMapping("/create")
    ResponseEntity<ResponseObj<List<UserDTO>>> createUser(@RequestBody UserDTO dto);

    @PutMapping("/update/{uid}")
    ResponseEntity<ResponseObj<UserDTO>> updateUser(@PathVariable("uid") UUID id, @RequestBody UserDTO dto);

    @DeleteMapping("/delete/{uid}")
    ResponseEntity<ResponseObj<String>> deleteUser(@PathVariable("uid") UUID id);

    @GetMapping("/read/allTag")
    ResponseEntity<ResponseObj<List<UserDTO>>> readAllByTag(@RequestParam String tag);
}
