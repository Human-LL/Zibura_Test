package com.example.mcsample.controller;

import com.example.mcsample.controller.api.SampleApi;
import com.example.mcsample.dto.SampleDTO;
import com.example.mcsample.dto.response.ResponseHandler;
import com.example.mcsample.dto.response.ResponseObj;
import com.example.mcsample.service.interfaces.SampleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@Log4j2
@RestController
public class SampleControllerImpl implements SampleApi {

    private final SampleService sampleService;

    @Override
    public ResponseEntity<ResponseObj<SampleDTO>> getEcho(String text) {
        return ResponseHandler.generateResponse(HttpStatus.OK, sampleService.generateEcho(text));
    }

    @Override
    public ResponseEntity<ResponseObj<String>> getNotFoundException() {
        return ResponseHandler.generateResponse(HttpStatus.OK, sampleService.generateNotFoundException());
    }

}
