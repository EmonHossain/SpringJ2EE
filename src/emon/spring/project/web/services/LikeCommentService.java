package emon.spring.project.web.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import emon.spring.project.web.dao.LikeAndCommentDao;

@Service("likeComment")
public class LikeCommentService {
	
	private LikeAndCommentDao likenCommentDao;
	@Autowired
	public void setLikenCommentDao(LikeAndCommentDao likenCommentDao) {
		this.likenCommentDao = likenCommentDao;
	}

	public int countForLike(String imageId,String userId){
		int count = 0;
		if(likenCommentDao.Liked(imageId, userId)){
			System.out.println("i am in counter");
			count=likenCommentDao.counter();
			
		}
		return count;
	}
	
	public int countBeforeLike(){
		return likenCommentDao.counter();
	}
	
}
