package com.minipix.repositories;

import com.minipix.models.Comprovante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComprovanteRepository extends JpaRepository<Comprovante, Long> {

}