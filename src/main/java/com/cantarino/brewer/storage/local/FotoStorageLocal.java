package com.cantarino.brewer.storage.local;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.name.Rename;

public class FotoStorageLocal implements IFotoStorage {

	private static final Logger LOG = LoggerFactory.getLogger(FotoStorageLocal.class);

	private Path local;
	private Path localTemporario;

	public FotoStorageLocal() {
		this(FileSystems.getDefault().getPath(System.getenv("HOME"), ".brewerFotos"));
	}

	public FotoStorageLocal(Path path) {
		this.local = path;
		CriarPastas();
	}

	private String renomearArquivo(String nomeOriginal) {
		return UUID.randomUUID().toString() + "__" + nomeOriginal;

	}

	private void CriarPastas() {
		try {
			Files.createDirectories(this.local);
			this.localTemporario = FileSystems.getDefault().getPath(this.local.toString(), "temp");
			System.out.println(this.localTemporario);
			Files.createDirectories(this.localTemporario);

		} catch (IOException e) {
			LOG.error(e.getMessage());
			throw new RuntimeException("Erro ao criar pastas de fotos");
		}
	}

	@Override
	public String SalvarTemporario(MultipartFile[] files) {

		String novoNome = null;
		if (files == null || files.length < 0)
			return "";

		MultipartFile arquivo = files[0];
		novoNome = renomearArquivo(arquivo.getOriginalFilename());
		try {
			arquivo.transferTo(new File(this.localTemporario.toAbsolutePath().toString()
					+ FileSystems.getDefault().getSeparator() + novoNome));
		} catch (IOException e) {
			LOG.error(e.getMessage());
			throw new RuntimeException("Erro ao salvar fotos", e);
		}
		return novoNome;

	}

	@Override
	public byte[] recuperarFotoTemporaria(String nome) {

		try {
			return Files.readAllBytes(this.localTemporario.resolve(nome));
		} catch (IOException e) {
			throw new RuntimeException("Erro ao recuperar foto temporaria", e);
		}
	}

	@Override
	public void Salvar(String foto) {
		try {
			Files.move(this.localTemporario.resolve(foto), this.local.resolve(foto));
			Thumbnails.of(this.local.resolve(foto).toString()).size(40, 68).toFiles(Rename.PREFIX_DOT_THUMBNAIL);
		} catch (IOException e) {

			throw new RuntimeException("Erro ao salvar foto", e);
		}

	}

	@Override
	public byte[] recuperarFoto(String nome) {

		try {
			return Files.readAllBytes(this.local.resolve(nome));
		} catch (IOException e) {
			throw new RuntimeException("Erro ao recuperar foto no local", e);
		}
	}

}
