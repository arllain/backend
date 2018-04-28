package com.arllain.backend.domain;

import javax.persistence.Entity;

import com.arllain.backend.domain.enums.EstadoPagamento;

@Entity
public class PagamentoComCartao extends Pagamento {

	private static final long serialVersionUID = 1L;
	private Integer numeroParcelas;
	
	public PagamentoComCartao() {
		
	}

	/**
	 * @param id
	 * @param estado
	 * @param pedido
	 */
	public PagamentoComCartao(Integer id, EstadoPagamento estado, Pedido pedido, Integer numeroParcelas) {
		super(id, estado, pedido);
		this.numeroParcelas = numeroParcelas;
	}

	/**
	 * @return the numeroParcelas
	 */
	public Integer getNumeroParcelas() {
		return numeroParcelas;
	}

	/**
	 * @param numeroParcelas the numeroParcelas to set
	 */
	public void setNumeroParcelas(Integer numeroParcelas) {
		this.numeroParcelas = numeroParcelas;
	}
}

