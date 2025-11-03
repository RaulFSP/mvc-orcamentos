package io.github.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import io.github.app.dtos.OrcamentoDto;

@Controller
@RequestMapping(value = "/orcamentos")
public class OrcamentoController {

	@GetMapping
	public String orcamentoHome(Model model) {
		return "pages/orcamentos-page";
	}
	@GetMapping(value = "/new")
	public String orcamentoNew(Model model) {
		model.addAttribute("isUpdate", false);
		model.addAttribute("orcamento", new OrcamentoDto(null, null, null,null,null,null,null));
		return "pages/orcamentos-form-page";
	}
}
