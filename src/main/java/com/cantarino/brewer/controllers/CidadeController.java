package com.cantarino.brewer.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cantarino.brewer.model.Cidade;
import com.cantarino.brewer.repository.CidadeRepository;

@Controller
@RequestMapping("/Cidades")
public class CidadeController {

	@Autowired
	private CidadeRepository cidadeRepository;

	@RequestMapping("/Novo")
	public ModelAndView Novo() {

		ModelAndView mv = new ModelAndView("Cidade/Cidade-Cadastro");

		return mv;
	}

	@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody  List<Cidade> GetbyEstado(@RequestParam(name = "estado" , defaultValue = "-1") Long codigoEstado) {
		List<Cidade> cidades = cidadeRepository.findByEstadoCodigo(codigoEstado);
		
		System.out.println(cidades);

		return cidades;
	}
}
