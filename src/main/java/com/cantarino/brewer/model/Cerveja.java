package com.cantarino.brewer.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import com.cantarino.brewer.validations.SKU;


@Entity
@Table(name = "cerveja")
public class Cerveja {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	@SKU
	@NotBlank(message = "SKU é obrigatorio")
	private String sku;

	@NotBlank(message = "Nome é obrigatorio")
	private String nome;

	@NotBlank
	@Size(min = 1, max = 50)
	private String descricao;

	@NotBlank(message = "O valor é obrigatorio")
	@DecimalMin("0.00")
	@DecimalMax(value ="9999999.99" , message ="O valor da cerveja deve ser menos que o R$9.999.999,99 ")
	private BigDecimal valor;
	
	
	@NotBlank(message = "O teor Alcoolico é obrigatorio")	
	@DecimalMax(value ="100.00" , message ="O teor Alcoolico deve ser igual ou menor que 100 ")
	@Column(name = "teor_alcoolico")
	private BigDecimal teorAlcoolico;

	
	@NotBlank(message = "A comissão é obrigatoria")	
	@DecimalMax(value ="100.00" , message ="A comissao deve ser igual ou menor que 100 ")	
	private BigDecimal comissao;

	@NotBlank(message = "A comissão é obrigatoria")	
	@Column(name = "quantidade_estoque")	
	private Integer quantidadeEstoque;

	@NotNull(message = "O sabor é obrigatorio" )
	@Enumerated(EnumType.STRING)
	private Origem origem;

	@NotNull(message = "O sabor é obrigatorio" )
	@Enumerated(EnumType.STRING)
	private Sabor sabor;

	@ManyToOne
	@JoinColumn(name = "codigo_estilo")
	@NotNull(message = "O sabor é obrigatorio" )
	private Estilo estilo;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public BigDecimal getTeorAlcoolico() {
		return teorAlcoolico;
	}

	public void setTeorAlcoolico(BigDecimal teorAlcoolico) {
		this.teorAlcoolico = teorAlcoolico;
	}

	public BigDecimal getComissao() {
		return comissao;
	}

	public void setComissao(BigDecimal comissao) {
		this.comissao = comissao;
	}

	public Integer getQuantidadeEstoque() {
		return quantidadeEstoque;
	}

	public void setQuantidadeEstoque(Integer quantidadeEstoque) {
		this.quantidadeEstoque = quantidadeEstoque;
	}

	public Origem getOrigem() {
		return origem;
	}

	public void setOrigem(Origem origem) {
		this.origem = origem;
	}

	public Sabor getSabor() {
		return sabor;
	}

	public void setSabor(Sabor sabor) {
		this.sabor = sabor;
	}

	public Estilo getEstilo() {
		return estilo;
	}

	public void setEstilo(Estilo estilo) {
		this.estilo = estilo;
	}

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cerveja other = (Cerveja) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
