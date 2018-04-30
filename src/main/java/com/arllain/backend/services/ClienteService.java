package com.arllain.backend.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arllain.backend.domain.Cliente;
import com.arllain.backend.repositories.ClienteRepository;
import com.arllain.backend.services.exception.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repo;
	
	public Cliente buscar(Integer id) {
		Optional<Cliente> cli = repo.findById(id);
		return cli.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " +
		id + ", Tipo: " + Cliente.class.getName()));
	}
}
