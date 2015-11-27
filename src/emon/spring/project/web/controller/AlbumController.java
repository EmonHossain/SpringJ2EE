package emon.spring.project.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import emon.spring.project.web.DTO.ImageSaver;
import emon.spring.project.web.services.PicService;

@Controller
public class AlbumController {

	private PicService picService;

	@Autowired
	public void setPicService(PicService picService) {
		this.picService = picService;
	}

	@RequestMapping(value = "/album/photos", method = RequestMethod.GET)
	public String showAlbum(Model model, @RequestParam("alb") String albumName,@RequestParam("n") String userId) {
		
		List<ImageSaver> images = picService.getImagesofAlbum(albumName,userId);
		//System.out.println(images);
		//System.out.println("Submitted");
		model.addAttribute("images", images);
		return "album";
	}
}
