package emon.spring.project.web.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import emon.spring.project.web.DTO.Registration;

@Component("registrationDAO")
public class registrationDao {
	private NamedParameterJdbcTemplate jdbc;

	@Autowired
	public void setDataSource(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}

	@Transactional
	public boolean executeregistrationQuery(Registration reg) {
		BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(reg);
		MapSqlParameterSource mapParam = new MapSqlParameterSource();
		mapParam.addValue("username", reg.getEmail());
		mapParam.addValue("role", "ROLE_USER");
		
		jdbc.update("insert into authorities (username,authority)values(:username,:role)", mapParam);

		return jdbc.update(
				"Insert into user (userId,fname,lname,password,address,gender,dob,country,skill,proPicDirectory)values (:email,:firstName,:lastName,:password,:pixid,:gender,:date,:country,:skill,NULL)",
				param) == 1;
	}

	public boolean userExist(String id) {
		return jdbc.queryForObject("select count(*) from user where userId=:id", new MapSqlParameterSource("id", id),
				Integer.class) > 0;
	}
}
