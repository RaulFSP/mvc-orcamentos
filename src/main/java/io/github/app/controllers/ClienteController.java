package io.github.app.controllers;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import io.github.app.dtos.ClienteDto;
import io.github.app.services.ClienteService;
import jakarta.validation.Valid;

@Controller
@RequestMapping(value = "/clientes")
public class ClienteController {

	private final ClienteService clienteService;

	public ClienteController(ClienteService clienteService) {
		this.clienteService = clienteService;
	}

	@GetMapping
	public String clienteHome(Model model) {
		var clientes = clienteService.findAll();
		model.addAttribute("clientes", clientes);
		return "pages/clientes-page";
	}

	@GetMapping(value = "/new")
	public String clientesNew(Model model) {
		model.addAttribute("isEdit", false);
		model.addAttribute("cliente", new ClienteDto(null, null, null));
		return "pages/clientes-form-page";
	}

	@GetMapping(value = "/{id}")
	public String clienteShow(@PathVariable("id") Long id, Model model) {
		model.addAttribute("cliente", clienteService.findById(id));
		return "pages/clientes-show";
	}

	@GetMapping(value = "/{id}/edit")
	public String clienteEdit(@PathVariable("id") Long id, Model model) {
		var dto = clienteService.findById(id);
		model.addAttribute("cliente", dto);
		model.addAttribute("isEdit", true);
		return "pages/clientes-form-page";
	}

	@PostMapping(value = "/create")
	public String clientesCreate(@Valid @ModelAttribute(name = "dto") ClienteDto dto, BindingResult result,
			RedirectAttributes ra, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("cliente", dto);
			model.addAttribute("result", result);
			model.addAttribute("isEdit", false);
			model.addAttribute("formFeedback", Map.of("alert", "danger", "msg", "há erros no formulário de cadastro"));
			return "pages/clientes-form-page";
		}
		clienteService.createCliente(dto);
		ra.addFlashAttribute("formFeedback",
				Map.of("alert", "success", "msg", dto.name() + " foi cadastrado com sucesso"));
		return "redirect:/clientes/new";
	}

	@PutMapping(value = "/update/{id}")
	public String clienteUpdate(@PathVariable("id") Long id, @Valid @ModelAttribute("cliente") ClienteDto cliente,
			RedirectAttributes ra, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("cliente", cliente);
			model.addAttribute("result", result);
			model.addAttribute("isEdit", true);
			model.addAttribute("formFeedback", Map.of("alert", "danger", "msg", "há erros no formulário de atualização"));
			return "pages/clientes-form-page";
		}
		clienteService.clienteUpdate(id, cliente);
		ra.addFlashAttribute("formFeedback",
				Map.of("alert", "success", "msg", cliente.name() + " foi atualizado com sucesso"));
		return "redirect:/clientes/"+cliente.id();
	}

	@DeleteMapping(value = "/{id}")
	public String clienteDestroy(@PathVariable("id") Long id) {
		clienteService.clienteDisable(id);
		return "redirect:/clientes";
	}
}
