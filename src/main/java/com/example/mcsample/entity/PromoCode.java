package com.example.mcsample.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
public class PromoCode {

    @Id
    private UUID uid;

    @Column(nullable = false, unique = true)
    private String code;

    @Column(nullable = false)
    private BigDecimal discount;

    @Column(nullable = false)
    private LocalDateTime expirationDate;

    private boolean used = false;

    public PromoCode() {}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PromoCode promoCode = (PromoCode) o;
        return used == promoCode.used && Objects.equals(uid, promoCode.uid) && Objects.equals(code, promoCode.code) && Objects.equals(discount, promoCode.discount) && Objects.equals(expirationDate, promoCode.expirationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uid, code, discount, expirationDate, used);
    }
}