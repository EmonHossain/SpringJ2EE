package emon.spring.project.web.DTO;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

public class ImageContent {

	@NotNull
	private List<MultipartFile> files;
	private String caption;

	private String albumName;

	public ImageContent() {

	}

	public ImageContent(List<MultipartFile> files, String caption, String albumName) {
		this.files = files;
		this.caption = caption;
		this.albumName = albumName;
	}

	public List<MultipartFile> getFiles() {
		return files;
	}

	public void setFiles(List<MultipartFile> files) {
		this.files = files;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public String getAlbumName() {
		return albumName;
	}

	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}

}
