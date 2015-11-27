package emon.spring.project.web.helper;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.SavedRequest;


public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler{

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		
		HttpSession session = request.getSession();
		SavedRequest savedReq = (SavedRequest) session.getAttribute("SPRING_SECURITY_SAVED_REQUEST_KEY");
		if (savedReq == null) {
		    response.sendRedirect(request.getContextPath() + "/landing");
		}
		else {
		    response.sendRedirect(savedReq.getRedirectUrl());
		}
	}

}
