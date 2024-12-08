package com.example.mcsample.service.interfaces;


import com.example.mcsample.dto.UserDTO;

import java.util.List;
import java.util.UUID;

public interface UserService {

    UserDTO findById(UUID uuid);

    List<UserDTO> findAll();

    List<UserDTO> save(UserDTO userDto);

    UserDTO update(UUID uuid, UserDTO userDto);

    String delete(UUID id);

    List<UserDTO> findAllByTag(String tag);
}
