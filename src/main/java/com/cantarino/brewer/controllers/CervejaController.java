package com.cantarino.brewer.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cantarino.brewer.controllers.page.PageWrapper;
import com.cantarino.brewer.model.Cerveja;
import com.cantarino.brewer.model.Origem;
import com.cantarino.brewer.model.Sabor;
import com.cantarino.brewer.repository.filter.CervejaFilter;
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

		buildModelPage(modelView);

		logger.info("nivel info");
		return modelView;
		// Optional<Cerveja> opt = cervejaRepository.findBySkuIgnoreCase("AAAA1");
		// System.out.println(opt.isPresent());

	}

	@PostMapping(value = "/Novo")
	public ModelAndView Cadastrar(@Valid Cerveja cerveja, BindingResult result, Model model,
			RedirectAttributes redirect) {

		if (result.hasErrors())
			return Novo(cerveja);

		cervejaService.Salvar(cerveja);
		redirect.addFlashAttribute("mensagem", "Salvo com sucesso");

		// nome da url
		return new ModelAndView("redirect:/Cerveja/Novo");
	}

	@GetMapping(value = "/Pesquisar")
	public ModelAndView Pesquisar(CervejaFilter cervejaFilter, BindingResult result,
			@PageableDefault(size = 2) Pageable pageable, HttpServletRequest request) {

		ModelAndView modelView = new ModelAndView("cerveja/Pesquisa");
		buildModelPage(modelView);

		PageWrapper<Cerveja> page = new PageWrapper<Cerveja>(cervejaService.filtrar(cervejaFilter, pageable), request);
		modelView.addObject("pagina", page);

		return modelView;

	}

	private void buildModelPage(ModelAndView modelView) {
		modelView.addObject("sabores", Sabor.values());
		modelView.addObject("estilos", estiloService.getAll());
		modelView.addObject("origens", Origem.values());
	}

}
