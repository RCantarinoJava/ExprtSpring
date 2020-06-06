package com.cantarino.brewer.controllers;



import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cantarino.brewer.controllers.page.PageWrapper;
import com.cantarino.brewer.model.Usuario;
import com.cantarino.brewer.model.dto.StatusUsuario;
import com.cantarino.brewer.repository.filter.UsuarioFilter;
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

	@GetMapping("/Pesquisa")
	public ModelAndView Pesquisa(UsuarioFilter usuarioFilter, @PageableDefault(size = 3) Pageable pageable,
			HttpServletRequest request) {

		ModelAndView mv = new ModelAndView("Usuario/Pesquisa");

		PageWrapper<Usuario> page = new PageWrapper<Usuario>(usuarioService.filtrar(usuarioFilter, pageable), request);

		mv.addObject("pagina", page);
		mv.addObject("grupos", grupoService.getAll());

		return mv;
	}

	@PutMapping("/Status")
	@ResponseStatus(HttpStatus.OK)
	public void Atualizar(@RequestParam("codigos[]") Long[] codigos, @RequestParam("status") StatusUsuario status) {
		usuarioService.alterarStatus(codigos, status);

	}

}
