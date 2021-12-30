package br.com.foxi;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.foxi.domain.Categoria;
import br.com.foxi.domain.Cidade;
import br.com.foxi.domain.Cliente;
import br.com.foxi.domain.Endereco;
import br.com.foxi.domain.Estado;
import br.com.foxi.domain.Produto;
import br.com.foxi.domain.enums.TipoCliente;
import br.com.foxi.repositories.CategoriaRepository;
import br.com.foxi.repositories.CidadeRepository;
import br.com.foxi.repositories.ClienteRepository;
import br.com.foxi.repositories.EnderecoRepository;
import br.com.foxi.repositories.EstadoRepository;
import br.com.foxi.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Informatica");
		Categoria cat2 = new Categoria(null, "Escritorio");
		
		Produto p1 = new Produto(null, "computador",2000.00);
		Produto p2 = new Produto(null, "impressora",800.00);
		Produto p3 = new Produto(null, "mouse",80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlandia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2,c3));
		
		estadoRepository.saveAll(Arrays.asList(est1,est2));
		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));
		
		Cliente cli1 = new Cliente(null, "Maria Silva","maria@gmail.com","14144435792",TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("27999266867","27999914223"));
		
		Endereco e1 = new Endereco(null, "rua flores", "300", "AP 201", "Jardins", "29780000", cli1, c1);
		Endereco e2 = new Endereco(null, "Avenida matos", "120", "sala 800", "centro", "29745000", cli1, c2);
		
		cli1.getEndereco().addAll(Arrays.asList(e1,e2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1,e2));
		
		
		
	}
	
	
}
