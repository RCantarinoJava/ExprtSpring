package com.cantarino.brewer.services.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.cantarino.brewer.storage.local.IFotoStorage;

@Component
public class CervejaListener {

	@Autowired
	private IFotoStorage fotoStorage;

	@EventListener(condition = "#cervejaEvent.temFoto()")
	public void CervejaSalva(CervejaEvent cervejaEvent) {

		fotoStorage.Salvar(cervejaEvent.getCerveja().getFoto());

	}

}
