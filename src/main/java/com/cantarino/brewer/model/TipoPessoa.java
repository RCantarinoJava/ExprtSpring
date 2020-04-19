package com.cantarino.brewer.model;

import com.cantarino.brewer.model.validation.group.CnpjCheck;
import com.cantarino.brewer.model.validation.group.CpfCheck;

public enum TipoPessoa {

	FISICA("Fisica", "CPF", "000.000.000-00", CpfCheck.class) {
		@Override
		public String formatar(String input) {

			return input.replaceAll("(\\d{3})(\\d{3})(\\d{3})", "$1.$2.$3-");
			
		}
	},
	JURIDICA("Juridica", "CNPJ", "00.000.000/0000-00", CnpjCheck.class) {
		@Override
		public String formatar(String input) {

			return input.replaceAll("(\\d{2})(\\d{3})(\\d{3})(\\d{4})", "$1.$2.$3/$4-");
		}
	};

	private String descricao;
	private String documento;
	private String mascara;
	private Class<?> grupo;

	TipoPessoa(String descricao, String documento, String mascara, Class<?> grupo) {
		this.descricao = descricao;
		this.documento = documento;
		this.mascara = mascara;
		this.grupo = grupo;
	}

	public abstract String formatar(String input);

	public String getDescricao() {
		return descricao;
	}

	public String getDocumento() {
		return documento;
	}

	public String getMascara() {
		return mascara;
	}

	public Class<?> getGrupo() {
		return grupo;
	}

	public static String removeMascara(String input) {
		return input.replaceAll("\\.|-|/", "");
	}

}
