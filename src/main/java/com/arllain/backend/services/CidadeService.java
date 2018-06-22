package com.arllain.backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arllain.backend.domain.Cidade;
import com.arllain.backend.repositories.CidadeRepository;

@Service
public class CidadeService {

	@Autowired
	private CidadeRepository repo;


	public List<Cidade> findByCidades (Integer estadoId) {
		return repo.findCidades(estadoId);
	}
}
