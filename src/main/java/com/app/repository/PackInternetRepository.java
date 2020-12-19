package com.app.repository;

import com.app.model.PackInternet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PackInternetRepository extends JpaRepository<PackInternet, Long> {
    Optional<PackInternet> findByName(String name);
}
