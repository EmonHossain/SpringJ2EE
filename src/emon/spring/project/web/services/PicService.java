package emon.spring.project.web.services;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import emon.spring.project.web.DTO.ImageSaver;
import emon.spring.project.web.dao.PicDao;
import emon.spring.project.web.dao.RetriveUserInfoDao;
import emon.spring.project.web.helper.ImageIdGenerator;

@Service("pictureService")
public class PicService {

	private PicDao picdao;
	private ImageIdGenerator idGenerator;
	private RetriveUserInfoDao userInfoDao;

	@Autowired
	public void setUserInfoDao(RetriveUserInfoDao userInfoDao) {
		this.userInfoDao = userInfoDao;
	}

	@Autowired
	public void setIdGenerator(ImageIdGenerator idGenerator) {
		this.idGenerator = idGenerator;
	}

	@Autowired
	public void setPicdao(PicDao picdao) {
		this.picdao = picdao;
	}

	public String getValue() {
		String va = picdao.getName();
		return va;
	}

	@Transactional
	public void saveProfileImage(MultipartFile file, String userId) {
		// Permanent Store
		String destination = "C:\\Users\\Emon_hossain\\Documents\\workspace-sts-3.7.0.RELEASE\\pix\\WebContent\\resources\\images\\proPic\\";
		// idGenerator = new ImageIdGenerator();
		String imageId = idGenerator.genereteImageId();
		String imageName = idGenerator.formatImageName(file.getOriginalFilename(), imageId);
		System.out.println(imageName);

		File fl1 = new File(file.getOriginalFilename());
		File fl2 = new File(imageName);
		fl1.renameTo(fl2);

		String databaseName = "images/proPic/" + imageName;

		try {
			File f2 = new File(destination + imageName);
			picdao.saveProImgToDatabase(databaseName, userId);
			// picdao.saveProImgToDatabase(databaseName, userId);
			file.transferTo(f2);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public boolean saveImages(MultipartFile[] files, String userId, int size) {
		String destination = "C:\\Users\\Emon_hossain\\Documents\\workspace-sts-3.7.0.RELEASE\\pix\\WebContent\\resources\\images\\image\\";
		for (int i = 0; i < size; i++) {
			String imageId = idGenerator.genereteImageId();
			String originalName = files[i].getOriginalFilename();
			String imageName = idGenerator.formatImageName(files[i].getOriginalFilename(), imageId);
			File fl1 = new File(files[i].getOriginalFilename());
			File fl2 = new File(imageName);
			fl1.renameTo(fl2);

			String databaseName = "images/image/" + imageName;

			try {
				File f2 = new File(destination + imageName);
				picdao.saveImageToDatabase(databaseName, userId, imageId, originalName);
				// picdao.saveProImgToDatabase(databaseName, userId);
				files[i].transferTo(f2);
			} catch (Exception e) {
				e.printStackTrace();
				e.getClass();
				return false;
			}
		}
		return true;
	}

	public boolean saveImages(List<MultipartFile> files, String userId, String albumName, String caption) {

		String destination = "C:\\Users\\Emon_hossain\\Documents\\workspace-sts-3.7.0.RELEASE\\pix\\WebContent\\resources\\images\\image\\";

		List<ImageSaver> imageInfo = new ArrayList<ImageSaver>();
		
		String userAddres = userInfoDao.getUserAddress(userId);
		
		for (MultipartFile multipartFile : files) {

			// creating and Formating Name
			String imageId = idGenerator.genereteImageId();
			String origName = multipartFile.getOriginalFilename();
			String imageName = idGenerator.formatImageName(origName, imageId);

			// putting all image info into imageSaver object one by one
			ImageSaver imgSaver = new ImageSaver();
			imgSaver.setImageId(imageId);
			imgSaver.setRealName(origName);
			imgSaver.setCaption(caption);
			imgSaver.setAlbumName(albumName);
			imgSaver.setUserId(userAddres);
			imgSaver.setDir("images/image/" + imageName);

			// add the object into list
			imageInfo.add(imgSaver);

			// File rename and transfer to permanent address
			try {
				File fl1 = new File(origName);
				File fl2 = new File(imageName);
				fl1.renameTo(fl2);

				File f2 = new File(destination + imageName);
				multipartFile.transferTo(f2);

			} catch (Exception e) {
				e.printStackTrace();
				e.getClass();
			}

		}
		picdao.saveAllImageToDatabase(imageInfo);

		return true;
	}

	public String getImageName(String id) {
		return picdao.getImageDirectoryAndName(id);
	}
	
	public List<ImageSaver> getImagesforProfile(String userId){
		//System.out.println(userId+" first");
		return picdao.getAllImagesforUser(userInfoDao.getUserAddress(userId));
	}
	public List<ImageSaver> getImagesforOther(String userId){
		//System.out.println(userId);
		return picdao.getAllImagesforOther(userId);
	}

	public List<ImageSaver> getImagesofAlbum(String albumName,String userId) {
		//System.out.println("Called : "+albumName+"  "+userId);
		return picdao.getAllImagesOfAlbum(albumName,userId);
	}

}
