package com.example.mcsample.service;

import static com.example.mcsample.utill.MessageTexts.MESSAGE_GENERATE_ECHO;
import static com.example.mcsample.utill.MessageTexts.MESSAGE_GENERATE_NOT_FOUND_EXCEPTION;
import static com.example.mcsample.utill.MessageTexts.NOT_FOUND;

import com.example.mcsample.dto.SampleDTO;
import com.example.mcsample.exception.NotFoundException;
import com.example.mcsample.mapper.SampleMapper;
import com.example.mcsample.repository.SamoleRepository;
import com.example.mcsample.service.interfaces.SampleService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class SampleServiceImpl implements SampleService {

    private SamoleRepository samoleRepository;

    private SampleMapper sampleMapper;

    @Autowired
    public SampleServiceImpl(SamoleRepository samoleRepository, SampleMapper sampleMapper) {
        this.samoleRepository = samoleRepository;
        this.sampleMapper = sampleMapper;
    }

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

    @Override
    public SampleDTO sampleSave(SampleDTO sampleDTO) {
        var entity = sampleMapper.toEntity(sampleDTO);
        entity.setUid(UUID.randomUUID());
        samoleRepository.save(entity);
        return sampleMapper.toDTO(entity);
    }
}