package com.example.mcsample.entity;

import com.example.mcsample.enums.OrderStatus;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Order {

    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    private LocalDate orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status; // Enum: NEW, PROCESSING, COMPLETED, CANCELED

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems;

    private Double totalPrice;

    private String deliveryMethod;
}
