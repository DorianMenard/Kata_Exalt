package com.example.exalt.repositories;

import com.example.exalt.entities.Pass;
import jakarta.persistence.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface PassRepository extends JpaRepository<Pass, Long> {
    List<Pass> findByLastNameAndFirstNameAndDateOfBirth(String lastName, String firstName, LocalDate dateOfBirth);
}
