package com.example.mcsample.entity;

import java.util.List;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Category {

    @Id
    private UUID id;

    private String name;

    @OneToMany(mappedBy = "category")
    private List<Product> products;
}
