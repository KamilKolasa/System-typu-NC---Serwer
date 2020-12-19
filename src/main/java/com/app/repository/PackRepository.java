package com.app.repository;

import com.app.model.Pack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PackRepository extends JpaRepository<Pack, Long> {
    Optional<Pack> findByName(String name);

    @Query("select distinct p.discount from Pack p")
    List<Integer> findAllDiscount();

    @Query("select p from Pack p where p.discount >= :discount")
    List<Pack> findAllPacksWithLargerDiscount(@Param("discount") Integer discount);
}
