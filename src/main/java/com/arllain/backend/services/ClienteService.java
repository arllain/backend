package com.arllain.backend.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arllain.backend.domain.Cidade;
import com.arllain.backend.domain.Cliente;
import com.arllain.backend.domain.Endereco;
import com.arllain.backend.domain.enums.TipoCliente;
import com.arllain.backend.dto.ClienteDTO;
import com.arllain.backend.dto.ClienteNewDTO;
import com.arllain.backend.repositories.CidadeRepository;
import com.arllain.backend.repositories.ClienteRepository;
import com.arllain.backend.repositories.EnderecoRepository;
import com.arllain.backend.services.exception.DataIntegrityException;
import com.arllain.backend.services.exception.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repo;
	
	@Autowired
	private CidadeRepository cidadeRepo;
	
	@Autowired
	private EnderecoRepository enderoRepo;
	
	@Autowired
	BCryptPasswordEncoder be;
	
	
	public Cliente find(Integer id) {
		Optional<Cliente> cli = repo.findById(id);
		return cli.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " +
		id + ", Tipo: " + Cliente.class.getName()));
	}
	
	@Transactional
	public Cliente insert(Cliente cliente) {
		cliente.setId(null);
		cliente = repo.save(cliente);
		enderoRepo.saveAll(cliente.getEnderecos());
		return cliente;
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
		Cliente cliente = new Cliente(clienteDTO.getId(), clienteDTO.getNome(), clienteDTO.getEmail(), null, null, null);
		return cliente;
	}

	public Cliente fromDTO(@Valid ClienteNewDTO clienteNewDTO) {
		Cliente cliente = new Cliente(null, clienteNewDTO.getNome(), clienteNewDTO.getEmail(), 
				clienteNewDTO.getCpfOuCnpj(), TipoCliente.toEnum(clienteNewDTO.getTipo()), be.encode(clienteNewDTO.getSenha()) );
		Optional<Cidade> cidade = cidadeRepo.findById(clienteNewDTO.getCidadeId());
		Endereco endereco = new Endereco(null, clienteNewDTO.getLogradouro(), clienteNewDTO.getNumero(), clienteNewDTO.getComplemento(), clienteNewDTO.getBairro(), clienteNewDTO.getCep(), cliente, cidade.get());
		cliente.getEnderecos().add(endereco);
		cliente.getTelefones().add(clienteNewDTO.getTelefone1());
		if(clienteNewDTO.getTelefone2() != null) {
			cliente.getTelefones().add(clienteNewDTO.getTelefone2());
		}
		
		if(clienteNewDTO.getTelefone3() != null) {
			cliente.getTelefones().add(clienteNewDTO.getTelefone3());
		}
		return cliente;
	}	
}
