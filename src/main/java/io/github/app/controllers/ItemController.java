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

import io.github.app.dtos.ItemDto;
import io.github.app.services.ItemService;
import jakarta.validation.Valid;

@Controller
@RequestMapping(value = "/items")
public class ItemController {

	private final ItemService itemService;

	public ItemController(ItemService itemService) {
		this.itemService = itemService;
	}

	@GetMapping
	public String itemHome(Model model) {
		var items = itemService.findAllActive();
		model.addAttribute("items", items);
		return "pages/items-page";
	}

	@GetMapping(value = "/new")
	public String itemNew(Model model) {
		model.addAttribute("item", new ItemDto(null, null, null, false, null));
		model.addAttribute("isUpdate", false);
		return "pages/items-form-page";
	}

	@GetMapping(value = "/{id}")
	public String itemShow(@PathVariable Long id, Model model) {
		var item = itemService.findById(id);
		model.addAttribute("item", item);
		return "pages/items-show";
	}
	
	@GetMapping(value = "/{id}/edit")
	public String itemEdit(@PathVariable Long id, Model model) {
		var item = itemService.findById(id);
		model.addAttribute("item", item);
		model.addAttribute("isUpdate", true);
		return "pages/items-form-page";
	}

	@PostMapping(value = "/create")
	public String itemCreate(
			@Valid @ModelAttribute ItemDto item,
			BindingResult result,
			RedirectAttributes ra,
			Model model) {
		if (result.hasErrors()) {
			model.addAttribute("item", item);
			model.addAttribute("result", result);
			model.addAttribute("isUpdate", false);
			model.addAttribute("formFeedback", Map.of("alert", "danger", "msg",
					"há erros no formulário de cadastro"));
			return "pages/items-form-page";
		}
		itemService.createItem(item);
		ra.addFlashAttribute("formFeedback", Map.of("alert", "success", "msg",
				item.name() + " foi cadastrado com sucesso"));
		return "redirect:/items/new";
	}
	
	@PutMapping(value = "/{id}")
	public String itemUpdate(@PathVariable Long id, @Valid @ModelAttribute ItemDto item, BindingResult result, RedirectAttributes ra, Model model) {
		if(result.hasErrors()) {
			model.addAttribute("item", item);
			model.addAttribute("result", result);
			model.addAttribute("isUpdate", true);
			model.addAttribute("formFeedback", Map.of("alert", "danger", "msg",
					"há erros no formulário de atualização"));
			return "pages/items-form-page";
		}
		itemService.itemUpdate(id,item);
		return "redirect:/items/"+id;
	}
	
	@DeleteMapping(value = "/{id}")
	public String clienteDestroy(@PathVariable("id") Long id) {
		itemService.itemDisable(id);
		return "redirect:/items";
	}
}
