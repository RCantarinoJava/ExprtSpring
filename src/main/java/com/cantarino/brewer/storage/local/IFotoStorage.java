package com.cantarino.brewer.storage.local;

import org.springframework.web.multipart.MultipartFile;

public interface IFotoStorage {

	public String SalvarTemporario(MultipartFile[] files);

	public byte[] recuperarFotoTemporaria(String nome);

	public void Salvar(String foto);

	public byte[] recuperarFoto(String nome);
}
