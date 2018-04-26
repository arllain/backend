package com.arllain.backend.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.arllain.backend.domain.Categoria;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {
	
	@RequestMapping(method=RequestMethod.GET)
	public List<Categoria> listar() {
		Categoria categoria1  = new Categoria(1, "Informática");
		Categoria categoria2 = new Categoria(1, "Escritório");
		Categoria categoria3 = new Categoria(1, "Papelaria");
		
		List<Categoria> lista = new ArrayList<>();
		lista.add(categoria1);
		lista.add(categoria2);
		lista.add(categoria3);
		
		return lista;
	}

}
