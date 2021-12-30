package br.com.foxi.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.foxi.domain.Endereco;
import br.com.foxi.repositories.EnderecoRepository;
import br.com.foxi.services.exceptions.ObjectNotFoundException;
@Service
public class EnderecoService {
	@Autowired
	private EnderecoRepository repo;
	
	public Endereco find(Integer id) {
		Optional<Endereco> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				 "Objeto não encontrado! Id: " + id + ", Tipo: " + Endereco.class.getName())); 
	}
	
}
