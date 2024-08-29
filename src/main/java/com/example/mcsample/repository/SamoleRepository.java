package com.example.mcsample.repository;

import com.example.mcsample.entity.Samples;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SamoleRepository extends JpaRepository<Samples, UUID> {
}
