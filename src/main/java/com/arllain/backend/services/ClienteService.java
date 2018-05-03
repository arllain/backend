package com.arllain.backend.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import com.arllain.backend.domain.Cliente;
import com.arllain.backend.dto.ClienteDTO;
import com.arllain.backend.repositories.ClienteRepository;
import com.arllain.backend.services.exception.DataIntegrityException;
import com.arllain.backend.services.exception.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repo;
	
	public Cliente find(Integer id) {
		Optional<Cliente> cli = repo.findById(id);
		return cli.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " +
		id + ", Tipo: " + Cliente.class.getName()));
	}
	
	public Cliente insert(Cliente cliente) {
		cliente.setId(null);
		return repo.save(cliente);
	}

	public Cliente update(Cliente cliente) {
		Cliente cli = find(cliente.getId());
		cli.setNome(cliente.getNome());
		cli.setEmail(cliente.getEmail());
		return repo.save(cli);
	}

	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		}catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir porque há entidades relacionadas.");
		}
	}

	public List<Cliente> findAll() {
		return repo.findAll();
	}
	
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}	

	
	public Cliente fromDTO(ClienteDTO clienteDTO) {
		Cliente cliente = new Cliente();
		cliente.setNome(clienteDTO.getNome());
		cliente.setEmail(clienteDTO.getEmail());
		return cliente;
	}	
}
