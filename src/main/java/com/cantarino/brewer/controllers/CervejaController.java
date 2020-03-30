package com.cantarino.brewer.controllers;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cantarino.brewer.model.Cerveja;
import com.cantarino.brewer.model.Origem;
import com.cantarino.brewer.model.Sabor;
import com.cantarino.brewer.services.CervejaService;
import com.cantarino.brewer.services.EstiloService;



@Controller
@RequestMapping("/Cerveja")
public class CervejaController {

	private static final Logger logger = LoggerFactory.getLogger(CervejaController.class);

	@Autowired
	private CervejaService cervejaService;
	
	@Autowired
	private EstiloService estiloService;

	@RequestMapping("/Novo")
	public ModelAndView Novo(Cerveja cerveja) {

		ModelAndView modelView = new ModelAndView("cerveja/Cadastro");

		modelView.addObject("sabores", Sabor.values());
		modelView.addObject("estilos", estiloService.getAll());
		modelView.addObject("origens", Origem.values());

		logger.info("nivel info");
		return modelView;
		// Optional<Cerveja> opt = cervejaRepository.findBySkuIgnoreCase("AAAA1");
		// System.out.println(opt.isPresent());

	}

	@RequestMapping(value = "/Novo", method = RequestMethod.POST)
	public ModelAndView Cadastrar(@Valid Cerveja cerveja, BindingResult result, Model model,
			RedirectAttributes redirect) {

		if (result.hasErrors())
			return Novo(cerveja);

		cervejaService.Salvar(cerveja);
		redirect.addFlashAttribute("mensagem", "Salvo com sucesso");

		// nome da url
		return new ModelAndView("redirect:/cerveja/Novo");
	}

}
