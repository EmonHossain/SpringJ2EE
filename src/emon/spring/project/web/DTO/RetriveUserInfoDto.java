package emon.spring.project.web.DTO;

public class RetriveUserInfoDto {
	private String f_name;
	private String l_name;
	private String dob;
	private String country;
	private String gender;
	private String skill;
	private String pic;
	private String address;

	public RetriveUserInfoDto() {

	}

	public RetriveUserInfoDto(String f_name, String l_name, String dob, String country, String gender, String skill,
			String pic, String address) {

		this.f_name = f_name;
		this.l_name = l_name;
		this.dob = dob;
		this.country = country;
		this.gender = gender;
		this.skill = skill;
		this.pic = pic;
		this.address = address;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getF_name() {
		return f_name;
	}

	public void setF_name(String f_name) {
		this.f_name = f_name;
	}

	public String getL_name() {
		return l_name;
	}

	public void setL_name(String l_name) {
		this.l_name = l_name;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
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

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

}
