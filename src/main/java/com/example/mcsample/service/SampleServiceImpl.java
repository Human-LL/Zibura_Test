package com.example.mcsample.service;

import static com.example.mcsample.utill.MessageTexts.MESSAGE_GENERATE_ECHO;
import static com.example.mcsample.utill.MessageTexts.MESSAGE_GENERATE_NOT_FOUND_EXCEPTION;
import static com.example.mcsample.utill.MessageTexts.NOT_FOUND;

import com.example.mcsample.dto.SampleDTO;
import com.example.mcsample.exception.NotFoundException;
import com.example.mcsample.service.interfaces.SampleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class SampleServiceImpl implements SampleService {


    @Override
    public SampleDTO generateEcho(String text) {
        log.info(MESSAGE_GENERATE_ECHO);
        SampleDTO dto = new SampleDTO(text);
        return dto;
    }

    @Override
    public String generateNotFoundException() {
        log.info(MESSAGE_GENERATE_NOT_FOUND_EXCEPTION);
        throw new NotFoundException(NOT_FOUND);
    }

}
