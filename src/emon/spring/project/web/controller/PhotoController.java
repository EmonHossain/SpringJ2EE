package emon.spring.project.web.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import emon.spring.project.web.DTO.ImageContent;
import emon.spring.project.web.services.PicService;
import emon.spring.project.web.validator.CustomImageValidator;

@Controller
public class PhotoController {

	private CustomImageValidator validator;
	private PicService picService;

	@Autowired
	public void setPicService(PicService picService) {
		this.picService = picService;
	}

	@Autowired
	public void setValidator(CustomImageValidator validator) {
		this.validator = validator;
	}

	@RequestMapping("/photo")
	public String addPhoto(Model model) {
		return "addSinglePhoto";
	}

	@RequestMapping("/album")
	public String addAlbum(Model model) {
		return "addAlbum";
	}

	@RequestMapping(value = "/photo", method = RequestMethod.POST)
	public String addPhotos(Model model, @Valid @ModelAttribute("ImageUpForm") ImageContent imgContent, BindingResult result,Principal principal) {
		String userId = principal.getName();
		if (result.hasErrors()) {
			System.out.println("Errorrrrrr");
		} else {
			List<MultipartFile> files = imgContent.getFiles();
			List<String> fileNames = new ArrayList<String>();
			String albumName=imgContent.getAlbumName();
			String caption=imgContent.getCaption();
			System.out.println("name : "+albumName+ "and caption : "+caption);
			
			//print 
			System.out.println("Voila ,,,,Everything is ok");
			
			if(files !=null && files.size()>0){
				for(MultipartFile multiFile : files){
					
					System.out.println(multiFile.getOriginalFilename());
					if(!validator.validating(multiFile.getContentType())){
						result.rejectValue("files", "", "Select valid image file");
						return "addSinglePhoto";
					}
					fileNames.add(multiFile.getOriginalFilename());
					
				}
				if (picService.saveImages(files, userId, albumName, caption))
					return "redirect:home";
			}
		}

		return "addSinglePhoto";
	}

	@RequestMapping(value = "/album", method = RequestMethod.POST)
	public String addAlbums(Model model, @Valid @ModelAttribute("albumUpForm") ImageContent imgContent, BindingResult result,Principal principal) {
		String userId = principal.getName();
		if (result.hasErrors()) {
			System.out.println("Errorrrrrr");
		} else {
			List<MultipartFile> files = imgContent.getFiles();
			List<String> fileNames = new ArrayList<String>();
			String albumName=imgContent.getAlbumName();
			String caption=imgContent.getCaption();
			System.out.println("name : "+albumName+ "and caption : "+caption);
			
			//print 
			System.out.println("Voila ,,,,Everything is ok");
			
			if(files !=null && files.size()>0){
				for(MultipartFile multiFile : files){
					
					System.out.println(multiFile.getOriginalFilename());
					if(!validator.validating(multiFile.getContentType())){
						result.rejectValue("files", "", "Select valid image file");
						return "addAlbum";
					}
					fileNames.add(multiFile.getOriginalFilename());
					
				}
				if (picService.saveImages(files, userId, albumName, caption))
					return "redirect:home";
			}
		}

		return "addAlbum";
	}
}
