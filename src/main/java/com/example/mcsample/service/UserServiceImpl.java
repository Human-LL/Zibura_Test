package com.example.mcsample.service;


import com.example.mcsample.dto.UserDTO;
import com.example.mcsample.entity.User;
import com.example.mcsample.mapper.UserMapper;
import com.example.mcsample.repository.UserRepository;
import com.example.mcsample.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import java.util.UUID;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final UserMapper mapper;

    private static final String DELETE_INFO_COMPLETED = "Employee успешно удалён по id: ";

    @Override
    public UserDTO findById(UUID id) {
        User user = repository.getUserByUid(id);
        UserDTO userDto = mapper.toDTO(user);
        userDto.setId(user.getUid());
        return userDto;
    }

    @Override
    public List<UserDTO> findAll() {
        List<User> userList = repository.findAll();
        List<UserDTO> userDtoList = new ArrayList<>();
        for (User user : userList) {
            UserDTO userDto = mapper.toDTO(user);
            userDto.setId(user.getUid());
            userDtoList.add(userDto);
        }
        return userDtoList;
    }

    @Override
    public List<UserDTO> save(UserDTO userDTO) {
        User user = mapper.toEntity(userDTO);
        user.setUid(UUID.randomUUID());
        User savedUser = repository.save(user);
        UserDTO usDto = mapper.toDTO(savedUser);
        usDto.setId(savedUser.getUid());
        return Collections.singletonList(usDto);
    }

    @Override
    public UserDTO update(UUID uuid, UserDTO userDto) {
        User user = repository.findById(uuid).orElse(null);
        if (user != null) {
            mapper.updateEntity(userDto, user);
            User updatedUser = repository.save(user);
            UserDTO usDto = mapper.toDTO(updatedUser);
            usDto.setId(updatedUser.getUid());
            return usDto;
        }
        return null;
    }

    @Override
    public String delete(UUID id) {
        repository.deleteById(id);
        return DELETE_INFO_COMPLETED + id;
    }

    @Override
    public List<UserDTO> findAllByTag(String tag) {
        List<User> employee = repository.getUserByNameLike(tag.toLowerCase());
        List<UserDTO> employeeDtoList = new ArrayList<>();
        for (User user : employee) {
            UserDTO userDto = mapper.toDTO(user);
            userDto.setId(user.getUid());
            employeeDtoList.add(userDto);
        }
        return employeeDtoList;
    }
}