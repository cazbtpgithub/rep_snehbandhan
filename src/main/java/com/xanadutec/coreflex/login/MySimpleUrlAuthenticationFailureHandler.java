package com.xanadutec.coreflex.login;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import com.xanadutec.coreflex.Kit.KitService;
import com.xanadutec.coreflex.model.Kit;
import com.xanadutec.coreflex.model.UserModel;

public class MySimpleUrlAuthenticationFailureHandler implements AuthenticationFailureHandler {

	@Autowired
	private LoginService loginService;
	@Autowired
	private KitService kitService;

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, javax.servlet.http.HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {

		String j_username = request.getParameter("j_username");
		String j_password = request.getParameter("j_password");

		UserModel model = loginService.getUserByUname(j_username);

		//System.out.println("nikhil............");
		if (model == null) {
			Kit kit = kitService.findKitByKitUserNamePassword(j_username, j_password);
			if (kit == null) {
				//System.out.println(1);
				handle(request, response, "/accessdenied");
				clearAuthenticationAttributes(request);
			} else if (kit.getStatus_id().getStatus_name().equalsIgnoreCase("Active")) {
				//System.out.println(2);
				handle(request, response, "/accessdenied");
				clearAuthenticationAttributes(request);
			} else if (kit.getStatus_id().getStatus_name().equalsIgnoreCase("Disactive")) {
				//System.out.println(3);      
				handle(request, response, "/registration");
				clearAuthenticationAttributes(request);
			}
		}else{
			//System.out.println(4);
			handle(request, response, "/accessdenied");
			clearAuthenticationAttributes(request);
		}

	}

	protected void handle(HttpServletRequest request, HttpServletResponse response, String url) throws IOException {
		String targetUrl = url;

		if (response.isCommitted()) {
			System.out.println("Response has already been committed. Unable to redirect to " + targetUrl);
			return;
		}
		redirectStrategy.sendRedirect(request, response, targetUrl);
	}

	protected void clearAuthenticationAttributes(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session == null) {
			return;
		}
		session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
	}

	public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
		this.redirectStrategy = redirectStrategy;
	}

	protected RedirectStrategy getRedirectStrategy() {
		return redirectStrategy;
	}

}
