package com.store.pharmacy.securities.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.store.pharmacy.securities.model.UserDTO;
import com.store.pharmacy.securities.service.UserService;
import com.store.pharmacy.validator.UserValidator;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping(value = "/users")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private UserValidator userValidator;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(userValidator);
	}

	@PostMapping
	public HttpEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO) {
		userService.checkIfDuplicatedUser(userDTO);
		String userId = userService.save(userDTO);
		userDTO.add(linkTo(methodOn(UserController.class).findUser(userId)).withRel("user").expand());
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(
				ServletUriComponentsBuilder.fromCurrentRequest().path("/{userId}").buildAndExpand(userId).toUri());
		return new ResponseEntity<UserDTO>(userDTO, httpHeaders, HttpStatus.CREATED);
	}

	@GetMapping(value = "/{userId}")
	public HttpEntity<UserDTO> findUser(@PathVariable("userId") String userId) {
		UserDTO userDTO = userService.findUser(userId);
		userDTO.add(linkTo(methodOn(UserController.class).findUser(userId)).withSelfRel().expand());
		return new ResponseEntity<UserDTO>(userDTO, HttpStatus.OK);
	}
}
