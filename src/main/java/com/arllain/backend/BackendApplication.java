package com.arllain.backend;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.arllain.backend.domain.Categoria;
import com.arllain.backend.domain.Cidade;
import com.arllain.backend.domain.Cliente;
import com.arllain.backend.domain.Endereco;
import com.arllain.backend.domain.Estado;
import com.arllain.backend.domain.Produto;
import com.arllain.backend.domain.enums.TipoCliente;
import com.arllain.backend.repositories.CategoriaRepository;
import com.arllain.backend.repositories.CidadeRepository;
import com.arllain.backend.repositories.ClienteRepository;
import com.arllain.backend.repositories.EnderecoRepository;
import com.arllain.backend.repositories.EstadoRepository;
import com.arllain.backend.repositories.ProdutoRepository;

@SpringBootApplication
public class BackendApplication implements CommandLineRunner{

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
		
		Estado estado1 = new Estado(null, "Pernambuco");
		Estado estado2 = new Estado(null, "Ceará");
		
		Cidade cid1 = new Cidade(null, "Recife", estado1);
		Cidade cid2 = new Cidade(null, "Caruaru", estado1);
		Cidade cid3 = new Cidade(null, "Fortaleza", estado2);
		
		estado1.getCidades().addAll(Arrays.asList(cid1,cid2));
		estado2.getCidades().addAll(Arrays.asList(cid3));
		
		estadoRepository.saveAll(Arrays.asList(estado1,estado2));
		cidadeRepository.saveAll(Arrays.asList(cid1,cid2, cid3));
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "36378912377", TipoCliente.PESSOA_FISICA);
		cli1.getTelefones().addAll(Arrays.asList("27363323","93838396"));
		
		Endereco end1 = new Endereco(null, "Rua da flores", "300", "Apt 303", "Torre","999874558", cli1, cid1);
		Endereco end2 = new Endereco(null, "Avenida Domingos Ferreira", "1005", "sala 1303", "Boa vaigem","38220834", cli1, cid1);
		
		cli1.getEnderecos().addAll(Arrays.asList(end1,end2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(end1, end2));
		
	}
}
