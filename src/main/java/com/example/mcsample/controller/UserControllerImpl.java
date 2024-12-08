package com.example.mcsample.controller;

import com.example.mcsample.controller.api.UserApi;
import com.example.mcsample.dto.UserDTO;
import com.example.mcsample.dto.response.ResponseHandler;
import com.example.mcsample.dto.response.ResponseObj;
import com.example.mcsample.service.interfaces.UserService;

import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@Log4j2
@RestController
public class UserControllerImpl implements UserApi {

    private final UserService userService;

    @Override
    public ResponseEntity<ResponseObj<UserDTO>> readUser(UUID uuid) {
        return ResponseHandler.generateResponse(HttpStatus.OK, userService.findById(uuid));
    }

    @Override
    public ResponseEntity<ResponseObj<List<UserDTO>>> readAllUser() {
        return ResponseHandler.generateResponse(HttpStatus.OK, userService.findAll());
    }

    @Override
    public ResponseEntity<ResponseObj<List<UserDTO>>> createUser(UserDTO dto) {
        return ResponseHandler.generateResponse(HttpStatus.OK, userService.save(dto));
    }

    @Override
    public ResponseEntity<ResponseObj<UserDTO>> updateUser(UUID id, UserDTO dto) {
        return ResponseHandler.generateResponse(HttpStatus.OK, userService.update(id, dto));
    }

    @Override
    public ResponseEntity<ResponseObj<String>> deleteUser(UUID id) {
        return ResponseHandler.generateResponse(HttpStatus.OK, userService.delete(id));
    }

    @Override
    public ResponseEntity<ResponseObj<List<UserDTO>>> readAllByTag(String tag) {
        return ResponseHandler.generateResponse(HttpStatus.OK, userService.findAllByTag(tag));
    }

}
