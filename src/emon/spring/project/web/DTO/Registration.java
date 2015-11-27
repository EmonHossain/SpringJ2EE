package emon.spring.project.web.DTO;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

public class Registration {

	@Size(min = 3, max = 60)
	@NotBlank
	private String firstName;
	@Size(min = 3, max = 60)
	@NotBlank
	private String lastName;
	@Size(min = 5)
	@NotBlank
	@Email(regexp="^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$")
	private String email;

	@NotNull
	@NotEmpty
	@NotBlank
	private String gender;
	@NotNull
	@NotEmpty
	@NotBlank
	private String skill;
	@NotEmpty
	@NotBlank
	private String country;
	@NotNull
	private String password;
	@DateTimeFormat(pattern = "dd-MM-YYYY")
	@NotEmpty(message = "Date must not empty")
	private String date;

	@Null
	private String picDir;
	
	private String pixid;

	public Registration() {

	}

	public Registration(String firstName, String lastName, String email, String gender, String skill, String country,
			String password, String date, String pixid) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.gender = gender;
		this.skill = skill;
		this.country = country;
		this.password = password;
		this.date = date;
		this.pixid = pixid;

	}

	public String getPixid() {
		return pixid;
	}

	public void setPixid(String pixid) {
		this.pixid = pixid;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getSkill() {
		return skill;
	}

	public void setSkill(String skill) {
		this.skill = skill;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getPicDir() {
		return picDir;
	}

	public void setPicDir(String picDir) {
		this.picDir = picDir;
	}

}
