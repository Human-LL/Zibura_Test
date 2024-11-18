package com.example.mcsample.service;

import static com.example.mcsample.utill.MessageTexts.NOT_FOUND;

import com.example.mcsample.dto.UserDTO;
import com.example.mcsample.exception.NotFoundException;
import com.example.mcsample.mapper.UserMapper;
import com.example.mcsample.repository.UserRepository;
import com.example.mcsample.service.interfaces.UserService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    @Transactional
    public UserDTO getById(UUID id) {
        var user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(NOT_FOUND));
        return userMapper.toDTO(user);
    }

    @Override
    @Transactional
    public UserDTO saveUser(UserDTO userDTO) {
        var user = userMapper.toEntity(userDTO);
        user.setId(UUID.randomUUID());
        userRepository.save(user);
        return userMapper.toDTO(user);
    }
}
