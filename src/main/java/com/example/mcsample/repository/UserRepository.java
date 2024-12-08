package com.example.mcsample.repository;

import com.example.mcsample.entity.User;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    @Query("SELECT u FROM User u WHERE u.uid = :uid")
    User getUserByUid(UUID uid);

    @Query("SELECT u FROM User u WHERE lower(u.name) LIKE %:tag% or lower(u.lastName) LIKE %:tag% or lower(u.email) LIKE %:tag%")
    List<User> getUserByNameLike(@RequestParam("tag") String tag);
}

