package emon.spring.project.web.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

@Component("imageValidator")
public class CustomImageValidator {
	
	private String reg = "([^\\s]+(/(?i)(jpeg|jpg|png|gif|bmp))$)";
	private Pattern pattern;
	private Matcher matcher;
	
	public CustomImageValidator() {
		pattern = Pattern.compile(reg);
	}
	
	public boolean validating(String im){
		
		matcher = pattern.matcher(im);
		
		return matcher.matches();
	}
}