package com.example.mcsample.service.interfaces;

import com.example.mcsample.dto.UserDTO;
import java.util.UUID;

public interface UserService {

    UserDTO getById(UUID id);

    UserDTO saveUser(UserDTO userDTO);
}

