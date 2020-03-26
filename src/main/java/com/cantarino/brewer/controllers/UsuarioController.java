package com.cantarino.brewer.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class UsuarioController {

	
	@RequestMapping("/Usuario/Novo")
	public String Novo() {
		return "Usuario/Usuario-Cadastro";
	}
}
