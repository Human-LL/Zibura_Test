package com.example.mcsample.dto.messages;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Data
@NoArgsConstructor
public class AppMessage {
    private String message;
    private String description;
    private AppMessageSemantic semantic;
    private String target;

    public AppMessage(AppMessageSemantic semantic, String message, String description, String target) {
        this.message = message;
        this.description = description;
        this.semantic = semantic;
        this.target = target;
    }

    public static AppMessage error(String massage, String description, String target) {
        return new AppMessage(AppMessageSemantic.E, massage, description, target);
    }

    public static AppMessage error(String massage, String description) {
        return new AppMessage(AppMessageSemantic.E, massage, description, "");
    }

    public static AppMessage info(String massage, String description) {
        return new AppMessage(AppMessageSemantic.I, massage, description, "");
    }

    public static AppMessage warning(String massage, String description) {
        return new AppMessage(AppMessageSemantic.W, massage, description, "");
    }

    public static AppMessage status(String massage, String description) {
        return new AppMessage(AppMessageSemantic.S, massage, description, "");
    }

    public static AppMessage error(String massage) {
        return new AppMessage(AppMessageSemantic.E, massage, massage, "");
    }

    public static AppMessage info(String massage) {
        return new AppMessage(AppMessageSemantic.I, massage, massage, "");
    }

    public static AppMessage status(String massage) {
        return new AppMessage(AppMessageSemantic.S, massage, massage, "");
    }



}
