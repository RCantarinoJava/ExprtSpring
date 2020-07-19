package com.cantarino.brewer.model.dto;

import java.math.BigDecimal;

import org.springframework.util.StringUtils;

import com.cantarino.brewer.model.Origem;

public class CervejaDTO {

	private long codigo;
	private String nome;
	private String sku;
	private String origem;
	private BigDecimal valor;
	private String foto;

	public CervejaDTO(long codigo, String nome, String sku, Origem origem, BigDecimal valor, String foto) {
		this.codigo = codigo;
		this.nome = nome;
		this.sku = sku;
		this.origem = origem.getDescricao();
		this.valor = valor;
		this.foto = StringUtils.isEmpty(foto) ? "cerveja-mock.png" : foto;
	}

	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getOrigem() {
		return origem;
	}

	public void setOrigem(String origem) {
		this.origem = origem;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

}
