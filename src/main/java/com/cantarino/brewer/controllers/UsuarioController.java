package com.cantarino.brewer.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cantarino.brewer.model.Usuario;
import com.cantarino.brewer.services.GrupoService;
import com.cantarino.brewer.services.UsuarioService;

@Controller
@RequestMapping("/Usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	
	@Autowired
	private GrupoService grupoService;
	
	

	@GetMapping("/Novo")
	public ModelAndView Novo(Usuario usuario) {
		
		ModelAndView mv = new ModelAndView("Usuario/Cadastro");
		mv.addObject("grupos", grupoService.getAll());
		
		
		return mv;
	}

	@PostMapping("/Novo")
	public ModelAndView Salvar(@Valid Usuario usuario, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors())
			return Novo(usuario);

		try {
			usuarioService.Salvar(usuario);
		} catch (Exception e) {
			return Novo(usuario);
		}

		attributes.addFlashAttribute("mensagem", "Usuario salvo com sucesso");
		return new ModelAndView("redirect:/Usuario/Novo");
	}
}
