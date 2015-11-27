package emon.spring.project.web.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowCountCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Component;

import emon.spring.project.web.DTO.ImageSaver;

@Component("pictureDAO")
public class PicDao {

	private NamedParameterJdbcTemplate jdbc;

	@Autowired
	public void setDataSource(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}

	public String getName() {
		String name = "Hello";
		return name;
	}

	public boolean saveProImgToDatabase(String databaseName, String id) {
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("databaseName", databaseName);
		param.addValue("id", id);
		return jdbc.update("update user set proPicDirectory=:databaseName where userId=:id", param) == 1;
	}

	public boolean saveImageToDatabase(String databaseName, String userId, String imageId, String originalName) {
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("databaseName", databaseName);
		param.addValue("id", userId);
		param.addValue("imageId", imageId);
		param.addValue("name", originalName);
		return jdbc.update(
				"insert into picture(picId,realName,albumName,uploadDate,caption,directory,userId)values(:imageId,:name,NULL,NULL,:databaseName,:id)",
				param) == 1;
	}


	public List<ImageSaver> getAllImagesforUser(String userId){
		System.out.println("I am from getAllImagesforUser : "+userId);
		MapSqlParameterSource param = new  MapSqlParameterSource();
		param.addValue("id", userId);
		return jdbc.query("select * from picture where binary userId = :id", param,new RowMapper<ImageSaver>() {

			@Override
			public ImageSaver mapRow(ResultSet rs, int rowNum) throws SQLException {
				RowCountCallbackHandler countCallback = new RowCountCallbackHandler();
				MapSqlParameterSource parameterSource = new MapSqlParameterSource();
				ImageSaver images = new ImageSaver();
				System.out.println("I am from getAllImagesforUser : "+userId);
				images.setImageId(rs.getString("picId"));
				
				parameterSource.addValue("imageId", images.getImageId());
				jdbc.query("select count(*)from likecounter where picId=:imageId",parameterSource, countCallback);
				
				images.setRealName(rs.getString("realName"));
				images.setAlbumName(rs.getString("albumName"));
				images.setCaption(rs.getString("caption"));
				images.setDir(rs.getString("directory"));
				images.setUploadDate(rs.getDate("uploadDate").toString());
				images.setUserId(rs.getString("userId"));
				images.setLikeCounter(countCallback.getRowCount());
				
				return images;
			}
		});
	}
	
	public List<ImageSaver> getAllImagesforOther(String userId){
		System.out.println("I am from getAllImagesforUser : "+userId);
		MapSqlParameterSource param = new  MapSqlParameterSource();
		param.addValue("id", userId);
		return jdbc.query("select * from picture where binary userId = :id", param,new RowMapper<ImageSaver>() {
			
			@Override
			public ImageSaver mapRow(ResultSet rs, int rowNum) throws SQLException {
				ImageSaver images = new ImageSaver();
				System.out.println("I am from getAllImagesforOther : "+userId);
				images.setImageId(rs.getString("picId"));
				images.setRealName(rs.getString("realName"));
				images.setAlbumName(rs.getString("albumName"));
				images.setCaption(rs.getString("caption"));
				images.setDir(rs.getString("directory"));
				images.setUploadDate(rs.getDate("uploadDate").toString());
				images.setUserId(rs.getString("userId"));
				
				return images;
			}
		});
	}
	
	public String getImageDirectoryAndName(String userId) {
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("userId", userId);

		return jdbc.queryForObject("select proPicDirectory from user where userId=:userId", param,
				new RowMapper<String>() {

					@Override
					public String mapRow(ResultSet rs, int rowNum) throws SQLException {
						String dir = rs.getString("proPicDirectory");
						return dir;
					}
				});
	}

	public int[] saveAllImageToDatabase(List<ImageSaver> images) {
		
		System.out.println("hello from saveAllImageToDatabase method");
		SqlParameterSource[] params = SqlParameterSourceUtils.createBatch(images.toArray());

		int[] counter = jdbc.batchUpdate(
				"insert into picture (picId,realName,albumName,uploadDate,caption,directory,userId) values(:imageId,:realName,:albumName,NOW(),:caption,:dir,:userId)",
				params);
		return counter;
	}

	public List<ImageSaver> getAllImagesOfAlbum(String albumName,String userId) {
		MapSqlParameterSource param = new  MapSqlParameterSource();
		param.addValue("albumName", albumName);
		param.addValue("userId", userId);
		System.out.println("database called  "+userId);
		return jdbc.query("select * from picture where binary (albumName = :albumName and userId=:userId)", param,new RowMapper<ImageSaver>() {

			@Override
			public ImageSaver mapRow(ResultSet rs, int rowNum) throws SQLException {
				ImageSaver images = new ImageSaver();
				System.out.println("I am from getAllImagesOfAlbum : "+albumName);
				images.setImageId(rs.getString("picId"));
				images.setRealName(rs.getString("realName"));
				images.setAlbumName(rs.getString("albumName"));
				images.setCaption(rs.getString("caption"));
				images.setDir(rs.getString("directory"));
				images.setUserId(rs.getString("userId"));
				images.setUploadDate(rs.getDate("uploadDate").toString());
				
				return images;
			}
		});
	}
	
	public List<ImageSaver> getAllImagesforOthersAlbum(String userId){
		System.out.println("I am from getAllImagesforUser : "+userId);
		MapSqlParameterSource param = new  MapSqlParameterSource();
		param.addValue("id", userId);
		return jdbc.query("select * from picture where binary userId = :id", param,new RowMapper<ImageSaver>() {
			
			@Override
			public ImageSaver mapRow(ResultSet rs, int rowNum) throws SQLException {
				ImageSaver images = new ImageSaver();
				System.out.println("I am from getAllImagesforOthersAlbum : "+userId);
				images.setImageId(rs.getString("picId"));
				images.setRealName(rs.getString("realName"));
				images.setAlbumName(rs.getString("albumName"));
				images.setCaption(rs.getString("caption"));
				images.setDir(rs.getString("directory"));
				images.setUploadDate(rs.getDate("uploadDate").toString());
				images.setUserId(rs.getString("userId"));
				
				return images;
			}
		});
	}
	
}
