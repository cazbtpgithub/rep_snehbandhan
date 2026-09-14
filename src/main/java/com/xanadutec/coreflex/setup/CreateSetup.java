package com.xanadutec.coreflex.setup;

import java.io.File;
import java.util.List;import javax.persistence.criteria.CriteriaBuilder.In;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xanadutec.coreflex.model.FcmNotification;
import com.xanadutec.coreflex.model.FtpInfo;
import com.xanadutec.coreflex.model.RoleModel;
import com.xanadutec.coreflex.model.ServerInfo;
import com.xanadutec.coreflex.model.UserModel;
import com.xanadutec.coreflex.userTask.UserService;

@Controller
public class CreateSetup {

	@Autowired
	SetupService setupService;
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private UserService userService;

	public CreateSetup() {

	}

	public CreateSetup(HttpServletRequest request) {
		makeSetup(request);
	}

	              
	@RequestMapping(value = "/save_Ftp_Server_Details", method = RequestMethod.POST)
	@ResponseBody
	public String save_Ftp_Server_Details(ModelMap model, HttpServletRequest request,
			@RequestParam("passFtp") String passFtp, @RequestParam("directoryFtp") String directoryFtp,
			@RequestParam("portFtp") String portFtp, @RequestParam("serverNameFtp") String serverNameFtp,
			@RequestParam("userNameFtp") String userNameFtp) {

		FtpInfo ftpInfo =new FtpInfo();
		ftpInfo.setId(1);
		ftpInfo.setFolderName(directoryFtp);
		ftpInfo.setPassword(passFtp);
		ftpInfo.setServerName(serverNameFtp);
		ftpInfo.setUserName(userNameFtp);
		setupService.saveFtpServerInfo(ftpInfo);
		
		return "save_Ftp_Server_Details";
	}

	
	            
	
	
	@RequestMapping(value = "/save_Email_Server_Info", method = RequestMethod.POST)
	@ResponseBody
	public String save_Email_Server_Info(ModelMap model, HttpServletRequest request,
			@RequestParam("hostEmail") String hostEmail, @RequestParam("serverNameEmail") String serverNameEmail,
			@RequestParam("portEmail") String portEmail, @RequestParam("emailName") String emailName,
			@RequestParam("passwordEmail") String passwordEmail) {
		
		ServerInfo info =new ServerInfo();
		info.setMail_host(hostEmail);
		info.setMail_serverName(serverNameEmail);
		info.setMsgName(emailName);
		info.setMail_port(portEmail);
		info.setMail_password(passwordEmail);
		
		setupService.saveServerInfo(info);
		
		return "save_Email_Server_Info";
	}

	           
	@RequestMapping(value = "/save_Fcm_Notification", method = RequestMethod.POST)
	@ResponseBody
	public String save_Fcm_Notification(ModelMap model, HttpServletRequest request,
			@RequestParam("authorization") String authorization, @RequestParam("title") String title,
			@RequestParam("serverUrl") String serverUrl) {
		
		FcmNotification fcmNotification=new  FcmNotification();
		fcmNotification.setAuthorization(authorization);
		fcmNotification.setTitle(title);
		fcmNotification.setUrl(serverUrl);
		
		setupService.saveFcmServerInfo(fcmNotification);

		return "save_Fcm_Notification";
	}

	@RequestMapping(value = "/update_Ftp_Server_Details", method = RequestMethod.POST)
	@ResponseBody
	public String updateFtpServerDetails(ModelMap model, HttpServletRequest request,
			@RequestParam("passFtp") String passFtp, @RequestParam("directoryFtp") String directoryFtp,
			@RequestParam("portFtp") String portFtp, @RequestParam("serverNameFtp") String serverNameFtp,
			@RequestParam("userNameFtp") String userNameFtp) {

		
		FtpInfo ftpInfo =new FtpInfo();
		ftpInfo.setId(1);
		ftpInfo.setFolderName(directoryFtp);
		ftpInfo.setPassword(passFtp);
		ftpInfo.setServerName(serverNameFtp);
		ftpInfo.setPort(Integer.parseInt(portFtp));
		ftpInfo.setUserName(userNameFtp);
		setupService.updateFtpServerInfo(ftpInfo);
		
		
		return "update_Ftp_Server_Details";
	}

	@RequestMapping(value = "/update_Fcm_Notification", method = RequestMethod.POST)
	@ResponseBody
	public String updateFcmNotification(ModelMap model, HttpServletRequest request,
			@RequestParam("authorization") String authorization, @RequestParam("title") String title,
			@RequestParam("serverUrl") String serverUrl) {
		
		FcmNotification fcmNotification=new  FcmNotification();
		fcmNotification.setServerId(1);
		fcmNotification.setAuthorization(authorization);
		fcmNotification.setTitle(title);
		fcmNotification.setUrl(serverUrl);
		
		setupService.updateFcmServerInfo(fcmNotification);

		

		return "update_Fcm_Notification";
	}


	@RequestMapping(value = "/update_Email_Server_Info", method = RequestMethod.POST)
	@ResponseBody
	public String updateEmailServerInfo(ModelMap model, HttpServletRequest request,
			@RequestParam("hostEmail") String hostEmail, @RequestParam("serverNameEmail") String serverNameEmail,
			@RequestParam("portEmail") String portEmail, @RequestParam("emailName") String emailName,
			@RequestParam("passwordEmail") String passwordEmail) {
		ServerInfo info =new ServerInfo();
		
		info.setServerId(1);
		info.setMail_host(hostEmail);
		info.setMail_serverName(serverNameEmail);
		info.setMsgName(emailName);
		info.setMail_port(portEmail);
		info.setMail_password(passwordEmail);
		
		setupService.updateServerInfo(info);
		return "update_Email_Server_Info";
	}
	
	@RequestMapping(value = "/serverSetup", method = RequestMethod.GET)
	public String serverSetup(ModelMap model, HttpServletRequest request) {

		FcmNotification fcmNotification = setupService.getFcmServerInfo();
		ServerInfo serverInfo = setupService.getServerInfo();
		FtpInfo ftpInfo = setupService.getFtpServerInfo();

		model.addAttribute("fcmNotification", fcmNotification);
		model.addAttribute("serverInfo", serverInfo);
		model.addAttribute("ftpInfo", ftpInfo);
		model.addAttribute("hidemenu", "hide");

		return "ServerSetup";
	}

	public void makeSetup(HttpServletRequest request) {
	
		String pathact = request.getServletContext().getRealPath("/resources/CfsFiles");
		Session session = sessionFactory.openSession();
		RoleModel roleModel1 = userService.userRoll(1);
		if (roleModel1 == null) {
			RoleModel roleModel = new RoleModel();
			roleModel.setRole("admin");
			session.save(roleModel);
			session.flush();

			roleModel = new RoleModel();
			roleModel.setRole("moderator");
			session.save(roleModel);

			roleModel = userService.userRoll(1);
			UserModel userModel = new UserModel();
			userModel.setUserName("admin");
			userModel.setPassword("admin");
			userModel.setUserRoll(roleModel);
			session.save(userModel);

			roleModel = userService.userRoll(2);
			userModel = new UserModel();
			userModel.setUserName("coreflex");
			userModel.setPassword("coreflex");
			userModel.setUserRoll(roleModel);
			session.save(userModel);

			roleModel = userService.userRoll(2);
			userModel = new UserModel();
			userModel.setUserName("ssi");
			userModel.setPassword("ssi");
			userModel.setUserRoll(roleModel);
			session.save(userModel);

		}
	}

}
