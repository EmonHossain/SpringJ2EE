package emon.spring.project.web.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import emon.spring.project.web.DTO.RetriveUserInfoDto;
import emon.spring.project.web.dao.RetriveUserInfoDao;

@Service("profileService")
public class ProfileService {

	private RetriveUserInfoDao userInfoDao;

	@Autowired
	public void setUserInfo(RetriveUserInfoDao userInfoDao) {
		this.userInfoDao = userInfoDao;
	}

	public RetriveUserInfoDto getUserInformation(String id) {

		System.out.println("This is Get user info");

		return userInfoDao.showUserInfo(id);
	}

	public boolean exist(String address) {
		// TODO Auto-generated method stub
		return userInfoDao.hasProfile(address);
	}

	public String getUserId(String profile) {
		// TODO Auto-generated method stub
		return userInfoDao.getUserId(profile);
	}
	
	public String getUserAddress(String id) {
		// TODO Auto-generated method stub
		return userInfoDao.getUserAddress(id);
	}

}
