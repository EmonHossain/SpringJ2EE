package emon.spring.project.web.controller;

import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import emon.spring.project.web.DTO.RetriveUserInfoDto;
import emon.spring.project.web.services.PicService;
import emon.spring.project.web.services.ProfileService;

@Controller
public class HomeController {

	private PicService picService;
	private ProfileService profileService;

	@Autowired
	public void setProfileService(ProfileService profileService) {
		this.profileService = profileService;
	}

	@Autowired
	public void setPicService(PicService picService) {
		this.picService = picService;
	}

	@RequestMapping("/")
	public String showLanding(Model model, Principal principal) {

		if (principal != null)
			return "redirect:/home";

		System.out.println("Hello kitty");
		String value = picService.getValue();
		model.addAttribute("value", value);
		return "landing";
	}

	@RequestMapping("/profile_pic")
	public String showProfileSetterPage() {
		return "proPicUpload";
	}

	@RequestMapping("/home")
	public String showHome(Model model, Principal principal, HttpSession session) {

		String id = principal.getName();
		RetriveUserInfoDto add = profileService.getUserInformation(id);
		model.addAttribute("name", add);

		return "home";
	}
}
