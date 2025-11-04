package io.github.app.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import io.github.app.dtos.ClienteDto;
import io.github.app.dtos.OrcamentoDto;
import io.github.app.repositories.DtoSpecificRepository;
import io.github.app.services.ClienteService;
import io.github.app.services.OrcamentoService;
import jakarta.validation.Valid;

@Controller
@RequestMapping(value = "/orcamentos")
public class OrcamentoController {

	private final OrcamentoService orcamentoService;
	private final ClienteService clienteService;
	private final DtoSpecificRepository dtoSpecificRepository;
	
	

	private OrcamentoController(
			OrcamentoService orcamentoService,
			ClienteService clienteService,
			DtoSpecificRepository dtoSpecificRepository) {
		this.orcamentoService = orcamentoService;
		this.clienteService = clienteService;
		this.dtoSpecificRepository = dtoSpecificRepository;
	}

	
	
	@Bean
	List<ClienteDto> clientes() {
		return clienteService.findAllActive();
	}

	@GetMapping
	public String orcamentoHome(Model model) {
		var dtos = dtoSpecificRepository.orcamentosComCliente();
		model.addAttribute("dtos", dtos);
		return "pages/orcamentos-page";
	}
	@GetMapping(value = "/new")
	public String orcamentoNew(Model model) {

		model.addAttribute("clientes", clientes());
		model.addAttribute("isUpdate", false);
		model.addAttribute("orcamento",
				new OrcamentoDto(null, null, null, null, null));
		return "pages/orcamentos-form-page";
	}
	@PostMapping(value = "/create")
	public String orcamentoCreate(
			@Valid @ModelAttribute("orcamento") OrcamentoDto dto,
			BindingResult result,
			RedirectAttributes ra,
			Model model) {
		
		if (result.hasErrors()) {
			System.out.println("deu ruim");
			model.addAttribute("clientes", clientes());
			model.addAttribute("isUpdate", false);
			model.addAttribute("orcamento", dto);
			model.addAttribute("result", result);
			return "pages/orcamentos-form-page";
		}
		
		orcamentoService.createOrcamento(dto);
		System.out.println("deu bom");
		ra.addFlashAttribute("formFeedback", Map.of("alert", "success", "msg",
				dto.id() + " foi cadastrado com sucesso"));
		return "redirect:/orcamentos/new";
	}
}
