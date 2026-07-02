package com.minipix.repositories;

import com.minipix.models.Favorecido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavorecidoRepository extends JpaRepository<Favorecido, Long> {

}