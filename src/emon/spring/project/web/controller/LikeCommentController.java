package emon.spring.project.web.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import emon.spring.project.web.services.LikeCommentService;

@Controller
public class LikeCommentController {
	
	private LikeCommentService likeComment;
	
	@Autowired
	public void setLikeComment(LikeCommentService likeComment) {
		this.likeComment = likeComment;
	}

	@RequestMapping("/like")
	public @ResponseBody String likeable(Model model,@RequestParam("name")String imageName,Principal principal){
		String response = "Helllloo there  "+principal.getName()+"your Image name : "+imageName;
		System.out.println("hello...............fgfhdf........");
		System.out.println(response);
		int counter = likeComment.countForLike(imageName, principal.getName());
		
		return String.valueOf(counter);
	}
}
