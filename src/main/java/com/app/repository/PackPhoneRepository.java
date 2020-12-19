package com.app.repository;

import com.app.model.PackPhone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PackPhoneRepository extends JpaRepository<PackPhone, Long> {
    Optional<PackPhone> findByName(String name);
}
