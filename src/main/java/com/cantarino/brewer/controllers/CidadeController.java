package com.cantarino.brewer.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CidadeController {

	@RequestMapping("/Cidade/Novo")
	public String Novo() {
		return "Cidade/Cidade-Cadastro";
	}
}
