package emon.spring.project.web.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.TinyBitSet;
import org.springframework.jdbc.core.RowCountCallbackHandler;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component("likeandComment")
public class LikeAndCommentDao {
	private NamedParameterJdbcTemplate jdbc;

	@Autowired
	public void setDataSource(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}
	
	//@Transactional
	public boolean Liked(String picId,String userId){
		
		MapSqlParameterSource param = new MapSqlParameterSource();
		
		param.addValue("picId", picId);
		param.addValue("userId", userId);
		param.addValue("like", 1);
		
		System.out.println("Row counted : ");
		
		
		/*jdbc.query("select count(*) from likecounter",countCallback);
		int count = countCallback.getRowCount();*/
		//int count=1;
		return jdbc.update("Insert into likecounter(picId,userId,count) values (:picId,:userId,:like)", param)==1;
	}
	
	public int counter(){
		RowCountCallbackHandler countCallback = new RowCountCallbackHandler();
		jdbc.query("select count(*) from likecounter",countCallback);
		int count = countCallback.getRowCount();
		return count+1;
	}
	public int countLikeById(String imageId){
		RowCountCallbackHandler countCallback = new RowCountCallbackHandler();
		MapSqlParameterSource parameterSource = new MapSqlParameterSource();
		parameterSource.addValue("imageId", imageId);
		jdbc.query("select count(*) from likecounter where picId=:imageId",parameterSource,countCallback );
		return countCallback.getRowCount();
	}
	
	/*public boolean LikedAlready(String picId,String userId){
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("picId", picId);
		param.addValue("userId", userId);
		
		return jdbc.queryForObject("select count(*) from likecounter where userId=:userId and picId=:picId",param,Integer.class) > 0;
	}*/
	
	public boolean unliked(String picId,String userId){
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("picId", picId);
		param.addValue("userId", userId);
		param.addValue("unliked", 0);
		
		return jdbc.update("update likecounter set count=:unliked where picId=:picId and userId = :userId", param)==1;
	}
	
	/*public boolean likedAgain(String picId,String userId){
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("picId", picId);
		param.addValue("userId", userId);
		param.addValue("liked", 1);
		
		return jdbc.update("update likecounter set count=:liked where picId=:picId and userId = :userId", param)==1;
	}*/

}
