package emon.spring.project.web.controller;

import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
	
	@RequestMapping("/sign_in")
	public String showLogin(Model model,Principal principal,HttpSession session){
		System.out.println("Login pageeeeee");
		try{
			if(principal!=null)
				return "redirect:home";
			if(session.getAttribute("success")!=null ){
				if(session.getAttribute("success").toString().equals("true")){
					model.addAttribute("success","ok");
					session.removeAttribute("success");
				}
			}
			if(session.getAttribute("succs_no_photo")!=null){
				if(session.getAttribute("succs_no_photo").toString().equals("noPhoto")){
					model.addAttribute("success","ok");
					session.removeAttribute("succs_no_photo");
				}				
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return "login";
	}
}
