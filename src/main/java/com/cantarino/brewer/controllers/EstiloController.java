package com.cantarino.brewer.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cantarino.brewer.controllers.page.PageWrapper;
import com.cantarino.brewer.model.Estilo;
import com.cantarino.brewer.repository.filter.EstiloFilter;
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

	@Cacheable("estilos")
	@GetMapping
	public ModelAndView pesquisar(EstiloFilter estiloFilter, BindingResult result,
			@PageableDefault(size = 2) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("estilo/PesquisaEstilos");

		PageWrapper<Estilo> paginaWrapper = new PageWrapper<>(_estiloService.filtrar(estiloFilter, pageable),
				httpServletRequest);
		mv.addObject("pagina", paginaWrapper);
		return mv;
	}

}

//ResponseEntity ajuda a controlar o HttpRequest
