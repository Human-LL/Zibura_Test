package com.example.mcsample.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class PromoCode {

    @Id
    private UUID id;

    private String code;

    private Double discount;

    private LocalDate startDate;

    private LocalDate endDate;
}
