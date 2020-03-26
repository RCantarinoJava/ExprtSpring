package com.cantarino.brewer.controllers;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cantarino.brewer.model.Cerveja;

@Controller
public class CervejaController {

	@RequestMapping("/Cerveja/Novo")
	public String novo(Cerveja cerveja) {
		return "cerveja/Cadastro";
	}

	@RequestMapping(value = "/cerveja/Novo", method = RequestMethod.POST)
	public String Cadastrar(@Valid Cerveja cerveja, BindingResult result, Model model, RedirectAttributes redirect) {

		if (result.hasErrors())
			// forward : nome da view
			return novo(cerveja);

		// nome da url
		redirect.addFlashAttribute("mensagem", "Salvo com sucesso");
		return "redirect:/cerveja/Novo";
	}

}
