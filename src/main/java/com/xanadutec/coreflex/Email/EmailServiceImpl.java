package com.xanadutec.coreflex.Email;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xanadutec.coreflex.model.UserModelDetails;


@Service("EmailService")
public class EmailServiceImpl implements EmailService  {

	@Autowired
	EmailDao emailDao;
	
	@Override
	public void sendRegistrationEmail(String fillName ,String userName,String password ,String emailId,HttpServletRequest request,String path)
	{

		emailDao.sendRegistrationEmail(fillName, userName, password, emailId,request,path);
	}

	@Override
	public void viewProfileSendEmail(UserModelDetails modelDetailsFrom, UserModelDetails modelDetailsTo,
			HttpServletRequest request, String path) 
	{
		emailDao.viewProfileSendEmail(modelDetailsFrom, modelDetailsTo, request, path);
	}

	@Override
	public void sendKitExpiryMail(String path)
	{
		emailDao.sendKitExpiryMail(path);
		
	}
	


}
