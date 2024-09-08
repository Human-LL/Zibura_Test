package com.example.mcsample.service.interfaces;

import com.example.mcsample.dto.SampleDTO;

public interface SampleService {

    SampleDTO generateEcho(String text);

    String generateNotFoundException();

    SampleDTO sampleSave(SampleDTO sampleDTO);

}
