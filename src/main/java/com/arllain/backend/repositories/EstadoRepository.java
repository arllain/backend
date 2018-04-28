package com.arllain.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arllain.backend.domain.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado,Integer>{
	
}
