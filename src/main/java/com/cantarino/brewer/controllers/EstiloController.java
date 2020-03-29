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
import com.cantarino.brewer.validations.exceptions.ErroEntidade;

@Controller
public class EstiloController {

	@Autowired
	private EstiloService _estiloService;

	@RequestMapping(value = "/Estilo", method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody ResponseEntity<?> Cadastrar(@RequestBody @Valid Estilo estilo , BindingResult result) {

		if(result.hasErrors())		
			return ResponseEntity.badRequest().body(result.getFieldError("nome").getDefaultMessage());

		try	
		{
			estilo =_estiloService.Salvar(estilo);
		}
		catch (ErroEntidade e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
		
	
		return ResponseEntity.ok(estilo);

	}

}
