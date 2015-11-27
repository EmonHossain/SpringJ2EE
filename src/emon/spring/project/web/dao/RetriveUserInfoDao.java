package emon.spring.project.web.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import emon.spring.project.web.DTO.RetriveUserInfoDto;

@Component("UserDAO")
public class RetriveUserInfoDao {
	private NamedParameterJdbcTemplate jdbc;

	@Autowired
	public void setDataSource(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}

	public RetriveUserInfoDto showUserInfo(String userId) {
		System.out.println("Retrive user dao");
		MapSqlParameterSource param = new MapSqlParameterSource();

		param.addValue("id", userId);
		System.out.println("Param  " + param);
		return jdbc.queryForObject(
				"select fname,lname,address,dob,gender,country,skill,proPicDirectory from user where userId=:id", param,
				new RowMapper<RetriveUserInfoDto>() {

					@Override
					public RetriveUserInfoDto mapRow(ResultSet rs, int rowNum) throws SQLException {
						System.out.println("DAO    showUserInfo");
						RetriveUserInfoDto userInfo = new RetriveUserInfoDto();

						userInfo.setF_name(rs.getString("fname"));
						userInfo.setL_name(rs.getString("lname"));
						userInfo.setAddress(rs.getString("address"));
						userInfo.setDob(rs.getString("dob"));
						userInfo.setGender(rs.getString("gender"));
						userInfo.setSkill(rs.getString("skill"));
						userInfo.setCountry(rs.getString("country"));
						userInfo.setPic(rs.getString("proPicDirectory"));

						System.out.println("The End");
						return userInfo;
					}
				});

	}

	public boolean hasProfile(String address) {
		return jdbc.queryForObject("select count(*) from user where binary address=:address",
				new MapSqlParameterSource("address", address), Integer.class) > 0;
	}

	public String getUserId(String profile) {
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("address", profile);

		return jdbc.queryForObject("select userId from user  where binary address=:address", param, new RowMapper<String>() {

			@Override
			public String mapRow(ResultSet rs, int rowNum) throws SQLException {
				String id = rs.getString("userId");
				return id;
			}
		});
	}
	
	public String getUserAddress(String userId) {
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("userId", userId);

		return jdbc.queryForObject("select address from user where userId=:userId", param, new RowMapper<String>() {

			@Override
			public String mapRow(ResultSet rs, int rowNum) throws SQLException {
				String address = rs.getString("address");
				return address;
			}
		});
	}
	
	

}
