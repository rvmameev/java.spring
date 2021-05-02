package jb.spring.homework.controller;

import jb.spring.homework.dto.ProductDto;
import jb.spring.homework.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
	private final ProductService productService;

	@GetMapping
	public String getAll(Model model) {
		model.addAttribute("products", productService.getAll());

		return "products";
	}

	@GetMapping({"/edit/{id}", "/edit/"})
	public String getProduct(Model model, @PathVariable(required = false) Long id) {
		ProductDto productDto = productService.getById(id);

		model.addAttribute("product", productDto);

		return "product";
	}

	@PostMapping({"/edit/{id}", "/edit/"})
	public String saveProduct(@PathVariable(required = false) Long id, @ModelAttribute ProductDto productDto) {
		productService.save(productDto);

		return "redirect:/product";
	}

	@GetMapping("/delete/{id}")
	public String deleteProduct(@PathVariable Long id) {
		productService.deleteById(id);

		return "redirect:/product";
	}
}
