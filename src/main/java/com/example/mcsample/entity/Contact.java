package com.example.mcsample.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Objects;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
public class Contact {

    @Id
    private UUID uid;

    @Column(name = "email", unique = true)
    private String email;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Contact() {}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact email1 = (Contact) o;
        return Objects.equals(uid, email1.uid) && Objects.equals(email, email1.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uid, email);
    }
}
