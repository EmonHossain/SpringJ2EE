package emon.spring.project.web.controller;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import emon.spring.project.web.helper.DeleteImageFile;
import emon.spring.project.web.services.PicService;
import emon.spring.project.web.services.ProfileService;
import emon.spring.project.web.validator.CustomImageValidator;

@Controller
public class AvatarController {

	@Autowired
	ServletContext context;

	private PicService picService;
	private ProfileService profileService;
	private DeleteImageFile deleteImageFile;
	
	
	
	/*private ImageIdGenerator idGenerator;
	
	
	@Autowired
	public void setIdGenerator(ImageIdGenerator idGenerator) {
		this.idGenerator = idGenerator;
	}*/

	@Autowired
	public void setDeleteImageFile(DeleteImageFile deleteImageFile) {
		this.deleteImageFile = deleteImageFile;
	}

	@Autowired
	public void setProfileService(ProfileService profileService) {
		this.profileService = profileService;
	}

	@Autowired
	public void setPicService(PicService picService) {
		this.picService = picService;
	}
	
	@RequestMapping("/avatar")
	public String showProfilePic(Model model,@RequestParam(value="change",required=false)String sign,HttpSession session){
		if(sign!=null && sign.equals("true")){
			
			model.addAttribute("proPic",session.getAttribute("proPic").toString());
			model.addAttribute("noskip","true");
			System.out.println(session.getAttribute("proPic").toString());
			
		}
		return "proPicUpload";
	}

	@RequestMapping(value = "/avatar", method = RequestMethod.POST)
	public String uploadAvatar(Model model, @RequestParam("file")@NotEmpty @NotNull MultipartFile file,HttpSession session,Principal principal) throws IOException {
		
		
		
		String userId=null;
		String path =null;
		System.out.println("Hello");
		//System.out.println("Principal name : "+principal.getName());
		System.out.println("Session name : "+session.getAttribute("email"));
		
		if((session.getAttribute("email")!=null && principal==null) || (session.getAttribute("email")==null && principal!=null) || (session.getAttribute("email")!=null && principal!=null)){
			CustomImageValidator validator = new CustomImageValidator();
			if(session.getAttribute("email")!=null){
				userId = session.getAttribute("email").toString();
				session.removeAttribute("email");
			}
			else {
				userId = principal.getName();
				path = picService.getImageName(userId);
			}
			
			System.out.println(file.getContentType());
			System.out.println(file.getOriginalFilename());
			
			if (!validator.validating(file.getContentType())) {
				model.addAttribute("error", "Please select a valid Image file");
				System.out.println("Change propic"+session.getAttribute("proPic"));
				if(session.getAttribute("proPic")!=null)
					model.addAttribute("proPic",session.getAttribute("proPic").toString());
				return "proPicUpload";
			}

			try {
				// Temporary Store
				
				//These six line written bellow can be removed, no impact on site
				/*idGenerator.genereteImageId();
				String imageId = idGenerator.genereteImageId();
				String imageName = idGenerator.formatImageName(file.getOriginalFilename(), imageId);
				File destination = new File(context.getRealPath("/") +
				"resources" + File.separator + "images"+File.separator+"proPic"+File.separator+imageName);
				file.transferTo(destination);*/
				// File destination = new File(context.getRealPath("/") +
				// "resources" + File.separator + file.getOriginalFilename());
				// file.transferTo(destination);
				picService.saveProfileImage(file,userId);
				//picService.saveProfileImage(file, userId);

			} catch (Exception e) {
				// return "exception";
				e.printStackTrace();
			}
			model.addAttribute("success","ok");
			//session=request.getSession(true);
			session.setAttribute("success", "true");
			if(session.getAttribute("proPic")!=null){
				session.removeAttribute("proPic");
				String address = profileService.getUserAddress(userId);
				if(path!=null){
					if(deleteImageFile.removeImageFile(path))
						System.out.println("Image deleted");
				}
				return "redirect:"+address;
			}
			return "redirect:home";
		}
		else {
			model.addAttribute("login_first","You are not logged in");
			return "login";
			
		}
		
	}

}
