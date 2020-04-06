package com.cantarino.brewer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.multipart.MultipartFile;

import com.cantarino.brewer.model.dto.FotoDTO;
import com.cantarino.brewer.storage.FotoStorageRunnable;
import com.cantarino.brewer.storage.local.IFotoStorage;

@RestController
@RequestMapping("/fotos")
public class FotosController {

	@Autowired
	private IFotoStorage fotoStorage;

	@PostMapping
	public DeferredResult<FotoDTO> Upload(@RequestParam("files[]") MultipartFile[] files) {
		
		DeferredResult<FotoDTO> _result = new DeferredResult<FotoDTO>();
		Thread _thread = new Thread(new FotoStorageRunnable(files, _result, fotoStorage));

		_thread.start();

		return _result;

		// thread presa
		// disponibilidade do sistema
		// operações demoradas retorno assincrono
	}

	@GetMapping("/temp/{nome:.*}")
	public byte[] recuperarFoto(@PathVariable("nome") String nome) {

		return fotoStorage.recuperarFotoTemporaria(nome);
	}
	
	@GetMapping("/{nome:.*}")
	public byte[] recuperar(@PathVariable("nome") String nome) {

		return fotoStorage.recuperarFoto(nome);
	}

}
