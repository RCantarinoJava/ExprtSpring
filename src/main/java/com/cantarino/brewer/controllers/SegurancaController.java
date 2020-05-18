package com.cantarino.brewer.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SegurancaController {

	@GetMapping("/login")
	public String Login(@AuthenticationPrincipal User user) {

		if (user != null)
			return "redirect:/Cerveja/Pesquisar";

		return "Login";
	}

	@PostMapping("/login")
	public String Login() {

		return "Login";
	}

}
