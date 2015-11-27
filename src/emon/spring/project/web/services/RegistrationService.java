package emon.spring.project.web.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import emon.spring.project.web.DTO.Registration;
import emon.spring.project.web.dao.registrationDao;
import emon.spring.project.web.helper.PixIdGenerator;

@Service("registrationService")
public class RegistrationService {

	private PixIdGenerator pixIdGenerator;
	@Autowired
	private PasswordEncoder passwordEncoder;
	// generator autowired
	@Autowired
	public void setPixIdGenerator(PixIdGenerator pixIdGenerator) {
		this.pixIdGenerator = pixIdGenerator;
	}

	private registrationDao regDao;

	@Autowired
	public void setRegDao(registrationDao regDao) {
		this.regDao = regDao;
	}

	public boolean createUser(Registration reg) {
		//System.out.println("Pix ID : "+pixIdGenerator.generatePixId(reg.getLastName(), reg.getFirstName()));
		reg.setPixid(pixIdGenerator.generatePixId(reg.getLastName(), reg.getFirstName()));
		reg.setPassword(passwordEncoder.encode(reg.getPassword()));
		return regDao.executeregistrationQuery(reg);
	}
	
	public boolean isUserExist(String id){
		return regDao.userExist(id);
	}

}
