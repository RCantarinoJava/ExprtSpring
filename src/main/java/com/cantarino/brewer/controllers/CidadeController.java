package com.cantarino.brewer.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cantarino.brewer.model.Cidade;
import com.cantarino.brewer.repository.CidadeRepository;

@Controller
@RequestMapping("/Cidades")
public class CidadeController {

	@Autowired
	private CidadeRepository cidadeRepository;

	@RequestMapping("/Novo")
	public ModelAndView Novo(Cidade cidade) {

		ModelAndView mv = new ModelAndView("Cidade/Cidade-Cadastro");

		return mv;
	}

	@Cacheable(value = "cidades", key = "#codigoEstado")
	@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Cidade> GetbyEstado(
			@RequestParam(name = "estado", defaultValue = "-1") Long codigoEstado) {
		List<Cidade> cidades = cidadeRepository.findByEstadoCodigo(codigoEstado);

		System.out.println(cidades);

		return cidades;
	}

	// @CacheEvict(value = "cidades", allEntries = true)
	@CacheEvict(value = "cidades", key = "cidade.estado.codigo" , condition = "#cidade.temEstado()")
	public ModelAndView salvar(@Valid Cidade cidade, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return Novo(cidade);
		}

		try {
			cidadeRepository.save(cidade);
		} catch (Exception e) {
			result.rejectValue("nome", e.getMessage(), e.getMessage());
			return Novo(cidade);
		}

		attributes.addFlashAttribute("mensagem", "Cidade salva com sucesso!");
		return new ModelAndView("redirect:/cidades/nova");
	}
}
