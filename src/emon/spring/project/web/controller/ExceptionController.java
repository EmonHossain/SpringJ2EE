package emon.spring.project.web.controller;

import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {
	
	@ExceptionHandler(DataAccessException.class)
	public String dataAccessExcphandler(DataAccessException ex) {
		return "exception";
	}
}
