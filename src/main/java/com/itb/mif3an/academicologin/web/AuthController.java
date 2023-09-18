package com.itb.mif3an.academicologin.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itb.mif3an.academicologin.model.User;
import com.itb.mif3an.academicologin.service.UserService;
import com.itb.mif3an.academicologin.web.dto.UserDto;

//faz a validação esse arquivo 

@Controller
public class AuthController {

	@Autowired
	private UserService userService;

	@ModelAttribute("user")
	public UserDto userDto() {
		return new UserDto();
	}

	@GetMapping("/registration")
	public String showRegistrationForm() {

		return "registration";
	}

	@PostMapping("/registration")
	public String registrerUserAccount(@ModelAttribute("user") UserDto userDto) {
		// SALVAR O BANCO

		userService.save(userDto);

		return "redirect:/registration?success"; // redirecion para uma "rota"
	}

	@ResponseBody
	@RequestMapping(value = "/registration/ajax/getEmail/{campo}/{valor}")
	public String getSearchResultViajaxRegister(@PathVariable("campo") String campo,
			@PathVariable("valor") String valor) {
		
		
		String msg ="";
		UserDto userDto = new UserDto();
		userDto.setEmail(valor);
		User user = userService.findByEmail(userDto.getEmail());
		if (user != null) {
			msg = "E-mail, já existe. Escolha um e-mail válido!! ";
		}
		
		return msg;
		

	}

}
