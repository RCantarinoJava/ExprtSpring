package com.cantarino.brewer.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cantarino.brewer.model.Cerveja;

@Controller
public class CervejaController {
	
	
	@RequestMapping("/cerveja/Novo")
	public String novo()
	{
		return "cerveja/Cadastro";	
    }  
	
	@RequestMapping(value =  "/cerveja/Novo" , method = RequestMethod.POST )
	public String Cadastrar(Cerveja cerveja) {
		
		System.out.println(cerveja.getSku());
		System.out.println(cerveja.getNome());
		return "cerveja/Cadastro";	
	}


}
