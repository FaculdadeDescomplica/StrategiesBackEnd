package com.minipix.repositories;

import com.minipix.models.ChavePix;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChavePixRepository extends JpaRepository<ChavePix, Long> {

}