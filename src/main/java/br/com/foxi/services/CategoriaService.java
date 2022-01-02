package br.com.foxi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.foxi.domain.Categoria;
import br.com.foxi.domain.Cliente;
import br.com.foxi.dto.CategoriaDTO;
import br.com.foxi.repositories.CategoriaRepository;
import br.com.foxi.services.exceptions.DataIntegrityException;
import br.com.foxi.services.exceptions.ObjectNotFoundException;
@Service
public class CategoriaService {
	@Autowired
	private CategoriaRepository repo;
	
	public List<Categoria> findAll() {
		return repo.findAll();
	}
	
	public Categoria find(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				 "Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName())); 
	}
	
	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public Categoria update(Categoria obj) {
		Categoria newObj = find(obj.getId());
		updateData(newObj,obj);
		return repo.save(newObj);
	}
	
	public void delete(Integer id) {
		
		find(id);
		try {
			repo.deleteById(id);			
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir a categoria pois possui produtos vinculados");
 		}
	}
	
	public Page<Categoria> findPage(Integer page, Integer linesPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPage,Direction.valueOf(direction),orderBy);
		
		return repo.findAll(pageRequest);
	}
	
	public Categoria fromDTO(CategoriaDTO objDto) {
		return new Categoria(objDto.getId(),objDto.getName());
	}
	
	private void updateData(Categoria newObj,  Categoria obj) {
		newObj.setNome(obj.getNome());
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
