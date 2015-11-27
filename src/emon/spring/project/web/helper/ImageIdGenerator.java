package emon.spring.project.web.helper;

import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Component;

@Component("imageIdGenerator")
public class ImageIdGenerator {
	public String genereteImageId(){
		String id=UUID.randomUUID().toString();
		String rand = String.valueOf((int )(Math.random() * 9 + 1));
		String uuid =id.replace("-", rand);
		System.out.println("class imageIdGenerator : "+uuid);
		return uuid;
	}
	
	public String formatImageName(String img,String id){
		
		String extension = FilenameUtils.getExtension(img);
		
		return id+"."+extension;
	}
}
