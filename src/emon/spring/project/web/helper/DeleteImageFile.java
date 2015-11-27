package emon.spring.project.web.helper;

import java.io.File;

import org.springframework.stereotype.Component;

@Component("deleteImageFile")
public class DeleteImageFile {
	
	public boolean removeImageFile(String path){
		String halfPath="C:\\Users\\Emon_hossain\\Documents\\workspace-sts-3.7.0.RELEASE\\pix\\WebContent\\resources\\";
		String totalPath = halfPath+path;
		try {
			File file = new File(totalPath);
			if(file.exists()){
				if(!file.delete())
					return false;
			}
			else
				return false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
}
