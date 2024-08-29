package com.example.mcsample.dto.response;

import com.example.mcsample.dto.messages.AppMessage;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;


public class ResponseHandler {

    private ResponseHandler() {
    }

    public static <T> ResponseEntity<ResponseObj<T>> generateResponse(HttpStatus status, ResponseObj<T> body) {
        int count = 0;
        if (body.getData() instanceof Collection<?>) {
            count = ((Collection<?>) body.getData()).size();
        } else if (!ObjectUtils.isEmpty(body.getData())) {
            count = 1;
        }
        body.setCount(count);
        return new ResponseEntity<>(body, status);
    }


    public static <T> ResponseEntity<ResponseObj<T>> generateResponse(HttpStatus status, T data) {
        Integer count = 0;
        ResponseObj<T> body = new ResponseObj<>();
        if (data instanceof Collection<?>) {
            count = ((Collection<?>) data).size();
        } else if (!ObjectUtils.isEmpty(data)) {
            count = 1;
        }
        body.setCount(count);
        body.setData(data);
        return new ResponseEntity<>(body, status);
    }

    public static ResponseEntity<ResponseObj<Object>> generateResponse(HttpStatus status, List<AppMessage> appMessageList) {
        return new  ResponseEntity<>(createResponseObj(appMessageList), status);
    }

    public static ResponseEntity<Object> generateErrorResponse(HttpStatus status, List<AppMessage> appMessageList) {
        return new  ResponseEntity<>(createResponseObj(appMessageList), status);
    }

    public static ResponseObj<Object> createResponseObj(List<AppMessage> appMessageList) {
        ResponseObj<Object> result = new ResponseObj<>();
        result.setCount(0);
        result.setMessages(appMessageList);
        result.setData("");
        return result;
    }


}
