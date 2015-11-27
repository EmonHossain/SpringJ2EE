package emon.spring.project.web.controller;

import java.security.Principal;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import emon.spring.project.web.DTO.Registration;
import emon.spring.project.web.services.RegistrationService;

@Controller
public class RegistrationController {

	private RegistrationService regService;

	@Autowired
	public void setRegService(RegistrationService regService) {
		this.regService = regService;
	}

	@RequestMapping("/sign_up")
	public String registrationPage(Model model, Principal principal) {
		if (principal != null)
			return "redirect:home";
		model.addAttribute(new Registration());
		return "registration";
	}

	@RequestMapping(value = "/sign_up", method = RequestMethod.POST)
	public String registrationPage(Model model, @Valid Registration reg, BindingResult result, HttpSession session) {

		if (!result.hasErrors()) {
			if(regService.isUserExist(reg.getEmail())){
				result.rejectValue("email", "user.exist", "This id is allready taken");
				return "registration";
			}
			if (regService.createUser(reg)) {
				session.setAttribute("email", reg.getEmail());
				// printout

				// set for generator

				System.out.println(reg.getEmail());
				session.setAttribute("succs_no_photo", "noPhoto");
				model.addAttribute("reg", reg);
				// return "proPicUpload";
				return "redirect:avatar";
			} else {
				return "exception";
			}
		} else {
			return "registration";
		}
	}
}
