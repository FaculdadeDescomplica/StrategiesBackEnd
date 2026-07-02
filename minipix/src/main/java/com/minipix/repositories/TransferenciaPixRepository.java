package com.minipix.repositories;

import com.minipix.models.TransferenciaPix;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransferenciaPixRepository extends JpaRepository<TransferenciaPix, Long> {

}