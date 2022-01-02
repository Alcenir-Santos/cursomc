package br.com.foxi.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.foxi.domain.Cliente;
import br.com.foxi.domain.enums.TipoCliente;
import br.com.foxi.dto.ClienteNewDTO;
import br.com.foxi.repositories.ClienteRepository;
import br.com.foxi.resources.exception.FieldMessage;
import br.com.foxi.services.validation.utils.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {
	
	@Autowired
	private ClienteRepository clienteRepository;
	@Override
	public void initialize(ClienteInsert ann) {
	}

	@Override
	public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		
		if(objDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) && !BR.isValidCPF(objDto.getDocumento())) {
			list.add(new FieldMessage("documento", "CPF Inválido"));
		}
		if(objDto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !BR.isValidCNPJ(objDto.getDocumento())) {
			list.add(new FieldMessage("documento", "CNPJ Inválido"));
		}
		Cliente aux = clienteRepository.findByEmail(objDto.getEmail());
		if(aux != null){
			list.add(new FieldMessage("email", "Email ja existente"));
		}
		
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}