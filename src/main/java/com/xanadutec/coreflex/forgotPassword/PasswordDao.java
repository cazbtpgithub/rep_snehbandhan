package com.xanadutec.coreflex.forgotPassword;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import com.xanadutec.coreflex.model.ServerInfo;
import com.xanadutec.coreflex.model.UserModel;

public interface PasswordDao {

	UserModel getUserByEmailId(String id);
	public HashMap<String, String> getUserNamePasswordAccordingToEmailId(String emailId);
	public HashMap<String, String> getUserNamePasswordAccordingToMobile(String mob);
	public String sendPasswordEmail(String uname, String pass, String email, HttpServletRequest request,
			final ServerInfo serverInfo);
	public  String sendpasswordMessage(String mob,String uname,String pass );
}
