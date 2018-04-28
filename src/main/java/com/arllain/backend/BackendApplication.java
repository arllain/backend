package com.arllain.backend;

import java.text.SimpleDateFormat;
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
import com.arllain.backend.domain.ItemPedido;
import com.arllain.backend.domain.Pagamento;
import com.arllain.backend.domain.PagamentoComBoleto;
import com.arllain.backend.domain.PagamentoComCartao;
import com.arllain.backend.domain.Pedido;
import com.arllain.backend.domain.Produto;
import com.arllain.backend.domain.enums.EstadoPagamento;
import com.arllain.backend.domain.enums.TipoCliente;
import com.arllain.backend.repositories.CategoriaRepository;
import com.arllain.backend.repositories.CidadeRepository;
import com.arllain.backend.repositories.ClienteRepository;
import com.arllain.backend.repositories.EnderecoRepository;
import com.arllain.backend.repositories.EstadoRepository;
import com.arllain.backend.repositories.ItemPedidoRepository;
import com.arllain.backend.repositories.PagamentoRepository;
import com.arllain.backend.repositories.PedidoRepository;
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
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
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
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("28/04/2018 17:10"), cli1, end1);
		Pedido ped2 = new Pedido(null, sdf.parse("15/04/2018 10:03"), cli1, end2);
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1,ped2));
		
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1,ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		itemPedidoRepository.saveAll(Arrays.asList(ip1,ip2,ip3));
	}
}
