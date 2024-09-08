package com.example.mcsample.controller.api;

import com.example.mcsample.dto.SampleDTO;
import com.example.mcsample.dto.response.ResponseObj;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Validated
@Tag(name = "test", description = "Тестовые вызовы")
@RequestMapping("/test")
public interface SampleApi {

    @GetMapping("/echo")
    @Operation(description = "Эхо вызов. Переданный текст транслируется в ответ")
    ResponseEntity<ResponseObj<SampleDTO>> getEcho(@RequestParam String text);

    @GetMapping("/not_found")
    @Operation(description = "Исключение NOT_FOUND")
    ResponseEntity<ResponseObj<String>> getNotFoundException();

    @PostMapping("/samples")
    ResponseEntity<ResponseObj<SampleDTO>> postSample(@Validated @RequestBody SampleDTO sampleDTO);
}
