package emon.spring.project.web.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import emon.spring.project.web.DTO.ImageSaver;
import emon.spring.project.web.DTO.RetriveUserInfoDto;
import emon.spring.project.web.services.LikeCommentService;
import emon.spring.project.web.services.PicService;
import emon.spring.project.web.services.ProfileService;

@Controller
public class ProfileController {

	private ProfileService profileService;
	private PicService picService;
	private LikeCommentService likenComment;

	@Autowired
	public void setLikenComment(LikeCommentService likenComment) {
		this.likenComment = likenComment;
	}

	@Autowired
	public void setPicService(PicService picService) {
		this.picService = picService;
	}

	@Autowired
	public void setProfileService(ProfileService profileService) {
		this.profileService = profileService;
	}

	@RequestMapping("/{profile}")
	public String showProfilePage(Model model, Principal principal, @PathVariable String profile, HttpSession session) {
		
		String id = null;
		String used=null;
		if(profileService.exist(profile)){
			id = profileService.getUserId(profile);
			System.out.println("id is here "+id+ " ok or not");
			//System.out.println("principal is here "+principal.getName()+ " ok or not");
			
			if(principal!=null){
				used = principal.getName();
				System.out.println("principal name "+used+ " is here");
				if(id.equals(used)){
					
					System.out.println();
					
					RetriveUserInfoDto reg = profileService.getUserInformation(used);
					List<ImageSaver> images = picService.getImagesforProfile(used);
					model.addAttribute("reg",reg);
					model.addAttribute("images", images);
					session.setAttribute("proPic", reg.getPic());
					
					return "showProfile";
				}
				else{
					RetriveUserInfoDto reg = profileService.getUserInformation(id);
					List<ImageSaver> images = picService.getImagesforOther(id);
					model.addAttribute("reg",reg);
					model.addAttribute("images", images);
					return "otherprofile";
				}
					
			}else 
			{
				System.out.println("Id null every time");
				RetriveUserInfoDto reg = profileService.getUserInformation(id);
				List<ImageSaver> images = picService.getImagesforOther(id);
				model.addAttribute("reg",reg);
				model.addAttribute("images", images);
				return "otherprofile";
			}
		}
		else {
			model.addAttribute("pronotexist","Profile not found");
			return "notfound";
		}
		
		
		/*if (principal != null){
			
		}
		if (principal == null || !profile.equals(id)) {
			// session.setAttribute("person", profile);
			System.out.println("Hello");
			model.addAttribute("person", profile);
			return "otherprofile";
		} else if (id.equals(profile)) {
			id = principal.getName();
			model.addAttribute("user", id);
			RetriveUserInfoDto reg = profileService.getUserInformation(id);
			System.out.println(reg);
			model.addAttribute("reg", reg);
			return "showProfile";
		} else*/
	}
}
