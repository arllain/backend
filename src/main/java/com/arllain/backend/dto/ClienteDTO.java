package com.arllain.backend.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.arllain.backend.domain.Cliente;

public class ClienteDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;

	@NotEmpty(message="Preenchimento obrigatório")
	@Length(min=5, max=120, message="O tamanho deve ser entre 5 e 120 caracteres")
	private String nome;

	@NotEmpty(message="Preenchimento obrigatório")
	@Email(message="Email inválido")
	private String email;
	
	public ClienteDTO() {
		
	}

	public ClienteDTO(Cliente cliente) {
		this.id = cliente.getId();
		this.nome = cliente.getNome();
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	
	
	
}
