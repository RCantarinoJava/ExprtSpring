package com.cantarino.brewer.storage;

import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.multipart.MultipartFile;

import com.cantarino.brewer.model.dto.FotoDTO;
import com.cantarino.brewer.storage.local.IFotoStorage;

public class FotoStorageRunnable implements Runnable {

	private MultipartFile[] files;
	private DeferredResult<FotoDTO> resultado;
	private IFotoStorage fotoStorage;
	

	public FotoStorageRunnable(MultipartFile[] files, DeferredResult<FotoDTO> resultado ,  IFotoStorage fotostorage) {

		this.files = files;
		this.resultado = resultado;
		this.fotoStorage = fotostorage;
	}

	@Override
	public void run() {
		String novoNome = fotoStorage.SalvarTemporario(files);
		resultado.setResult(new FotoDTO(novoNome, files[0].getContentType()));

	}

}
