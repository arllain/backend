package com.arllain.backend.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.arllain.backend.domain.Cidade;
import com.arllain.backend.domain.Estado;
import com.arllain.backend.dto.CidadeDTO;
import com.arllain.backend.dto.EstadoDTO;
import com.arllain.backend.services.CidadeService;
import com.arllain.backend.services.EstadoService;

@RestController
@RequestMapping(value="/estados")
public class EstadoController {
	
	@Autowired
	private EstadoService service;
	
	@Autowired
	private CidadeService cidadeService;
	
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<EstadoDTO>> findAll() {
		List<Estado> lista = service.findAll();
		List<EstadoDTO> listaDTO = lista.stream().map(estado -> new EstadoDTO(estado)).collect(Collectors.toList());	
		return ResponseEntity.ok(listaDTO);
	}
	
	
	@RequestMapping(value="/{estadoId}/cidades", method=RequestMethod.GET)
	public ResponseEntity<List<CidadeDTO>> findCidades(@PathVariable Integer estadoId) {
		List<Cidade> lista = cidadeService.findByCidades(estadoId);
		List<CidadeDTO> listaDTO = lista.stream().map(estado -> new CidadeDTO(estado)).collect(Collectors.toList());	
		return ResponseEntity.ok().body(listaDTO);
	}
	
}
