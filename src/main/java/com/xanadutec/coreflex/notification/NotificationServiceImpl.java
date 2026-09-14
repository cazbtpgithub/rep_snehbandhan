package com.xanadutec.coreflex.notification;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xanadutec.coreflex.model.FcmNotification;
import com.xanadutec.coreflex.model.UserModel;
import com.xanadutec.coreflex.model.UserModelDetails;

@Service("NotificationService")
public class NotificationServiceImpl implements NotificationService {

	@Autowired
	private NotificationDao notificationDao;

	@Override
	public FcmNotification getFcmNotification() {
		return notificationDao.getFcmNotification();

	}

	@Override
	public void sendChatFcmNotification(String msg, UserModel userFrom, UserModel userTo, FcmNotification fcmNotification,
			 HttpServletRequest request) {
		
		notificationDao.sendChatFcmNotification(msg, userFrom, userTo, fcmNotification, request);
		
	}

	@Override
	public void sendRegistationNotification(UserModel userFrom, List<UserModelDetails> userModelDetailsList,
			FcmNotification fcmNotification, HttpServletRequest reques) {
		notificationDao.sendRegistationNotification(userFrom, userModelDetailsList, fcmNotification, reques);
	}

	@Override
	public void sendSortListedFcmNotification(UserModel userFrom, UserModel userTo, FcmNotification fcmNotification,
			HttpServletRequest request) {
		
		notificationDao.sendSortListedFcmNotification(userFrom, userTo, fcmNotification, request);
		
	}

	

}
