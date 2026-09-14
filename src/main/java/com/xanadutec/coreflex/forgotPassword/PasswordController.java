package com.xanadutec.coreflex.forgotPassword;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.xanadutec.coreflex.model.ServerInfo;
import com.xanadutec.coreflex.model.SmsInfo;
import com.xanadutec.coreflex.model.UserModel;
import com.xanadutec.coreflex.sms.smsService;
import com.xanadutec.coreflex.userTask.UserService;

@Controller
public class PasswordController {

	@Autowired
	private PasswordService passwordservice;
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private smsService smsService;

	@RequestMapping(value = "/forgotpassword", method = RequestMethod.GET)
	public ModelAndView getForgotpage(ModelMap model, HttpServletRequest request) {
		
		return new ModelAndView("ForgotPasswordHome");

	}

	@RequestMapping(value = "/sendForgotPassword", method = RequestMethod.GET)
	@ResponseBody
	public String sendPassword(Model uiModel, HttpServletRequest request) {

		String email = request.getParameter("email");
		String type=request.getParameter("byType");
		
		final String email2 = email;
		final String mob=email;

		if(type.equalsIgnoreCase("byEmail"))
		{
			HashMap<String, String> userDetail = passwordservice.getUserNamePasswordAccordingToEmailId(email);

			if (userDetail == null) {
				return "false";
			} else {

				final String userName = userDetail.get("userName");
				final String password = userDetail.get("password");

				final HttpServletRequest req = request;
				org.hibernate.Session session = sessionFactory.openSession();
				Criteria criteria = session.createCriteria(ServerInfo.class);
				List<ServerInfo> list = criteria.list();
				session.close();
				final ServerInfo serverInfo = list.get(0);
				passwordservice.sendPasswordEmail(userName, password, email2, req, serverInfo);
				return "Success";
			} 
			
		}
		
		if(type.equalsIgnoreCase("byText"))
		{
			try {
				

				HashMap<String, String> userDetail =passwordservice.getUserNamePasswordAccordingToMobile(mob);
				if (userDetail == null) {
					return "false";
				} else {
					
					
					final String userName = userDetail.get("userName");
					final String password = userDetail.get("password");
					passwordservice.sendpasswordMessage(mob,userName,password);
					return "Success";
					
				}
				
				
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		return "";
	
	}

	
	
}
