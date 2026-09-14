package com.xanadutec.coreflex.notification;

import java.util.List;

import javax.servlet.http.HttpServletRequest;


import com.xanadutec.coreflex.model.FcmNotification;

import com.xanadutec.coreflex.model.UserModel;
import com.xanadutec.coreflex.model.UserModelDetails;

public interface NotificationDao {

	public FcmNotification getFcmNotification();

	



	public void sendChatFcmNotification(String msg,UserModel
			userFrom,UserModel userTo,
			FcmNotification fcmNotification,
			HttpServletRequest request);
	
	public void sendRegistationNotification(UserModel
			userFrom,List<UserModelDetails> userModelDetailsList,
			FcmNotification fcmNotification,
			HttpServletRequest reques); 


	public void sendSortListedFcmNotification(UserModel
			userFrom,UserModel userTo,
			FcmNotification fcmNotification,
			HttpServletRequest request);
	
}
