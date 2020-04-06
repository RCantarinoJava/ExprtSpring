package com.cantarino.brewer.services.events;

import org.springframework.util.StringUtils;

import com.cantarino.brewer.model.Cerveja;

public class CervejaEvent {

	private Cerveja _cerveja;

	public CervejaEvent(Cerveja cerveja) {
		this._cerveja = cerveja;
	}

	public Cerveja getCerveja() {
		return _cerveja;
	}

	public boolean temFoto() {

		return !StringUtils.isEmpty(_cerveja.getFoto());

	}

}
