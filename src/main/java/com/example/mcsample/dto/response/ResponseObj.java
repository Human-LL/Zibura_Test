package com.example.mcsample.dto.response;

import com.example.mcsample.dto.messages.AppMessage;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class ResponseObj<T> {
    private List<AppMessage> messages;
    private Integer count;
    private T data;

    public ResponseObj() {
        this.messages = new ArrayList<>();
    }

    public void error(String massage, String description) {
        messages.add(AppMessage.error(massage, description));
    }
    public void warning(String massage, String description) {
        messages.add(AppMessage.warning(massage, description));
    }
    public void status(String massage, String description) {
        messages.add(AppMessage.status(massage, description));
    }
    public void error(String massage) {
        messages.add(AppMessage.error(massage));
    }
    public void status(String massage) {
        messages.add(AppMessage.status(massage));
    }
    public void info(String massage, String description) {
        messages.add(AppMessage.info(massage, description));
    }

}
