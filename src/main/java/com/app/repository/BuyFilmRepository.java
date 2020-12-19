package com.app.repository;

import com.app.model.BuyFilm;
import com.app.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BuyFilmRepository extends JpaRepository<BuyFilm, Long> {
}
