package com.cantarino.brewer.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ClienteController {

	@RequestMapping("/Cliente/Novo")
	public String novo() {
		return "Cliente/Cliente-Cadastro";
	}

}
