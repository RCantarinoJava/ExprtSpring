package com.cantarino.brewer.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


public class Cerveja {
	
	@NotBlank(message = "SKU é obrigatorio")
	private String sku;
	
	@NotBlank(message = "nome é obrigatorio")
	private String nome;
	
	@NotBlank
	@Size(min = 1 ,   max = 50)
	private String descricao;
	
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
}