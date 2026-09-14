package com.xanadutec.coreflex.Email;

import javax.servlet.http.HttpServletRequest;

import com.xanadutec.coreflex.model.UserModelDetails;

public interface EmailDao
{
	public void sendRegistrationEmail(String fillName, String userName, String password, String emailId,
			HttpServletRequest request, String path);

	public void viewProfileSendEmail(UserModelDetails modelDetailsFrom, UserModelDetails modelDetailsTo,
			HttpServletRequest request, String path);
	
	public void sendKitExpiryMail(String path);
}
