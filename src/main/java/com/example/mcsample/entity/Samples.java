package com.example.mcsample.entity;

import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Samples {

    @Id
    private UUID uid;

    private String text;
}
