package com.cantarino.brewer.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cantarino.brewer.model.Estilo;
import com.cantarino.brewer.services.EstiloService;

@Controller
@RequestMapping("/Estilo")
public class EstiloController {

	@Autowired
	private EstiloService _estiloService;

	@RequestMapping(method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody ResponseEntity<?> Cadastrar(@RequestBody @Valid Estilo estilo, BindingResult result) {

		if (result.hasErrors())
			return ResponseEntity.badRequest().body(result.getFieldError("nome").getDefaultMessage());

		estilo = _estiloService.Salvar(estilo);
		return ResponseEntity.ok(estilo);

	}

}

//ResponseEntity ajuda a controlar o HttpRequest
