package emon.spring.project.web.helper;

import org.springframework.stereotype.Component;

@Component("pixidGenerator")
public class PixIdGenerator {
	private String a;

	
	public String generatePixId(String lname,String fname){
		 a = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		 
		 String s=lname+fname;
		 for(int i=0;i<3;i++){
			int f = randomWithRange(0, 9);
			s=s+a.charAt(f);
		 }
		 for(int i =0;i<3;i++){
			 int l = randomWithRange(11, 60);
			 s=s+a.charAt(l);
			 
		 }
		
		return s;
	}

	private int randomWithRange(int min, int max) {
		int range = (max - min) + 1;
		return (int) (Math.random() * range) + min;
	}
}
