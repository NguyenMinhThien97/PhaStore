package com.store.pharmacy.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.store.pharmacy.model.CategoryDTO;
import com.store.pharmacy.service.CategoryService;
import com.store.pharmacy.validator.CategoryValidator;

@RestController
@RequestMapping("/categories")
public class CategoryController {

	@Autowired
	private CategoryValidator categoryValidator;

	@Autowired
	private CategoryService categoryService;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(categoryValidator);
	}

	@PostMapping
	public HttpEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDTO) {
		categoryService.checkIfDuplicatedCategory(categoryDTO.getCategoryId());
		String categoryId = categoryService.save(categoryDTO);
		categoryDTO
				.add(linkTo(methodOn(CategoryController.class).findCategory(categoryId)).withRel("category").expand());
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().path("/{categoryId}")
				.buildAndExpand(categoryId).toUri());
		return new ResponseEntity<CategoryDTO>(categoryDTO, httpHeaders, HttpStatus.CREATED);
	}

	@GetMapping("/{categoryId}")
	public HttpEntity<CategoryDTO> findCategory(@PathVariable("categoryId") String categoryId) {
		CategoryDTO categoryDTO = categoryService.findCategory(categoryId);
		categoryDTO.add(linkTo(methodOn(CategoryController.class).findCategory(categoryId)).withSelfRel().expand());
		return new ResponseEntity<CategoryDTO>(categoryDTO, HttpStatus.OK);
	}

	@PatchMapping("/{categoryId}")
	public HttpEntity<CategoryDTO> updateCategory(@PathVariable("categoryId") String categoryId,
			@Valid @RequestBody CategoryDTO categoryDTO) {
		categoryService.checkIfCategoryExits(categoryId);
		categoryService.update(categoryId, categoryDTO);
		categoryDTO.add(linkTo(methodOn(CategoryController.class).findCategory(categoryId)).withSelfRel().expand());
		return new ResponseEntity<CategoryDTO>(categoryDTO, HttpStatus.OK);
	}
}
