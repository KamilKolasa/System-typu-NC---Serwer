package com.app.repository;

import com.app.model.BuyPack;
import com.app.model.Pack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BuyPackRepository extends JpaRepository<BuyPack, Long> {
}
