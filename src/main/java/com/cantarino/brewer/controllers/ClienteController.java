package com.cantarino.brewer.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cantarino.brewer.controllers.page.PageWrapper;
import com.cantarino.brewer.model.Cliente;
import com.cantarino.brewer.model.TipoPessoa;
import com.cantarino.brewer.repository.EstadoRepository;
import com.cantarino.brewer.repository.filter.ClienteFilter;
import com.cantarino.brewer.services.ClienteService;

@Controller
@RequestMapping("/Clientes")
public class ClienteController {

	@Autowired
	private EstadoRepository estadoRepository;

	@Autowired
	private ClienteService clienteService;

	@GetMapping("/Novo")
	public ModelAndView Novo(Cliente cliente) {

		ModelAndView mv = new ModelAndView("Cliente/Cadastro");
		mv.addObject("tiposPessoa", TipoPessoa.values());
		mv.addObject("estados", estadoRepository.findAll());

		return mv;
	}

	@PostMapping("/Novo")
	public ModelAndView Salvar(@Valid Cliente cliente, BindingResult result, RedirectAttributes attributes) {

		if (result.hasErrors())
			return Novo(cliente);

		try {
			clienteService.Salvar(cliente);
		} catch (Exception e) {
			result.rejectValue("cpfOuCnpj", e.getMessage(), e.getMessage());
			return Novo(cliente);

		}
		attributes.addFlashAttribute("mensagem", "Cliente salvo com sucesso");
		return new ModelAndView("redirect:/Clientes/Novo");
	}

	@GetMapping
	public ModelAndView pesquisar(ClienteFilter clienteFilter, BindingResult result,
			@PageableDefault(size = 3) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("Cliente/Pesquisa");
		mv.addObject("tiposPessoa", TipoPessoa.values());

		PageWrapper<Cliente> paginaWrapper = new PageWrapper<>(clienteService.filtrar(clienteFilter, pageable),
				httpServletRequest);
		mv.addObject("pagina", paginaWrapper);
		return mv;
	}
	
	@RequestMapping(consumes = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody List<Cliente> pesquisar(String nome)
	{				
		return clienteService.pesquisarPor(nome);		
	}	

}
