package br.com.foxi.domain;

import javax.persistence.Entity;

import br.com.foxi.domain.enums.EstadoPagamento;

@Entity
public class PagamentoComCartao extends Pagamento {

	private static final long serialVersionUID = 1L;
	
	private Integer numeroPercela;
	
	public PagamentoComCartao() {
		
	}

	public PagamentoComCartao(Integer id, EstadoPagamento estado, Pedido pedido, Integer numeroPercela) {
		super(id, estado, pedido);
		this.setNumeroPercela(numeroPercela);
	}

	public Integer getNumeroPercela() {
		return numeroPercela;
	}

	public void setNumeroPercela(Integer numeroPercela) {
		this.numeroPercela = numeroPercela;
	}
	
}
