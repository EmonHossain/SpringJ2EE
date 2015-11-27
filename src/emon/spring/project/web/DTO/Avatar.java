package emon.spring.project.web.DTO;

import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

public class Avatar {

	@NotNull(message="Please Select a valid Image")
	private MultipartFile file;

	public Avatar() {

	}

	public Avatar(MultipartFile file) {
		this.file = file;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

}
