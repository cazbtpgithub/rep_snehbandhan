package com.xanadutec.coreflex.forgotPassword;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xanadutec.coreflex.model.ServerInfo;
import com.xanadutec.coreflex.model.UserModel;

@Service("PasswordService")
public class PasswordServiceImpl implements PasswordService {
	
	@Autowired
	PasswordDao passwordDao;


	@Override
	public com.xanadutec.coreflex.model.UserModel getUserByEmailId(String email) {
		return passwordDao.getUserByEmailId(email);
	}


	@Override
	public HashMap<String, String> getUserNamePasswordAccordingToEmailId(String emailId) {
		return passwordDao.getUserNamePasswordAccordingToEmailId(emailId);
	}


	@Override
	public HashMap<String, String> getUserNamePasswordAccordingToMobile(String mob) {
		return passwordDao.getUserNamePasswordAccordingToMobile(mob);
	}


	@Override
	public String sendPasswordEmail(String uname, String pass, String email, HttpServletRequest request,
			ServerInfo serverInfo) {
		return passwordDao.sendPasswordEmail(uname, pass, email, request, serverInfo);
	}


	@Override
	public String sendpasswordMessage(String mob, String uname, String pass) {
		return passwordDao.sendpasswordMessage(mob, uname, pass);
	}

}
