package com.arllain.backend.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arllain.backend.domain.Pedido;
import com.arllain.backend.repositories.PedidoRepository;
import com.arllain.backend.services.exception.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository repo;
	
	public Pedido buscar(Integer id) {
		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " +
		id + ", Pedido: " + Pedido.class.getName()));
	}

}
