package com.cantarino.brewer.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;




@Controller
@RequestMapping("/vendas")
public class VendasController {
	
	
	@GetMapping("/nova")
	public ModelAndView Nova() {

		ModelAndView mv = new ModelAndView("venda/CadastroVenda");
		return mv;
	}

}
