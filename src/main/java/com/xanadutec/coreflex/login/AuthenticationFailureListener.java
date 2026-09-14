package com.xanadutec.coreflex.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

public class AuthenticationFailureListener {
	public class AuthentificationListener implements AuthenticationFailureHandler {

		@Override
		public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
				org.springframework.security.core.AuthenticationException exception)
				throws IOException, ServletException {

			System.out.println("failure ");

			response.sendRedirect("/accessdenied");
		}
	}
}
