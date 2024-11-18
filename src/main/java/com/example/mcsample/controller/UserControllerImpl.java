package com.example.mcsample.controller;

import com.example.mcsample.controller.api.UserApi;
import com.example.mcsample.dto.UserDTO;
import com.example.mcsample.dto.response.ResponseHandler;
import com.example.mcsample.dto.response.ResponseObj;
import com.example.mcsample.service.interfaces.UserService;
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
    public ResponseEntity<ResponseObj<UserDTO>> getAllUsers(UserDTO userDTO) {
        return null;
    }

    @Override
    public ResponseEntity<ResponseObj<UserDTO>> getUserById(UUID id) {
        return  ResponseHandler.generateResponse(HttpStatus.OK, userService.getById(id));
    }

    @Override
    public ResponseEntity<ResponseObj<UserDTO>> createUser(UserDTO userDTO) {
        return ResponseHandler.generateResponse(HttpStatus.OK, userService.saveUser(userDTO));
    }
}
