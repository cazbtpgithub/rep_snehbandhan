package com.xanadutec.coreflex.notification;


import java.io.BufferedReader;
import org.springframework.transaction.annotation.Transactional;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.util.Date;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xanadutec.coreflex.model.FcmNotification;
import com.xanadutec.coreflex.model.UserModel;
import com.xanadutec.coreflex.model.UserModelDetails;


@Repository("NotificationDao")
@Transactional
public class NotificationDaoImpl implements NotificationDao {

	@Autowired
	private SessionFactory sessionFactory;

	public FcmNotification getFcmNotification() {
		try{
			Session session = sessionFactory.getCurrentSession();
			Criteria criteria = session.createCriteria(FcmNotification.class);
			List<FcmNotification> fcmNotifications = criteria.list();
			return fcmNotifications.get(0);	
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void sendChatFcmNotification(String msg, UserModel userFrom, UserModel userTo, FcmNotification fcmNotification,
		 HttpServletRequest request) {
		try {
			JSONObject body = new JSONObject();
			String CLIENT_TOKEN = userTo.getDeviceToken();
			body.put("to", CLIENT_TOKEN);
			body.put("priority", "high");
			JSONObject bodyText = new JSONObject();
			bodyText.put("Message",msg);
			bodyText.put("UserFrom",userFrom.getUserId());
			bodyText.put("UserTo",userTo.getUserId());
			bodyText.put("UserFromName",userFrom.getFirstName());
			bodyText.put("date",new Date().getTime());
			bodyText.put("type","chat");
			JSONObject notification = new JSONObject();
			notification.put("body", bodyText.toString());
			notification.put("title", "Snehbandhan.org");
			notification.put("sound", "default");
			notification.put("content_available", "true");
			notification.put("priority", "high");
			notification.put("click_action", "OPEN_ACTIVITY_1");

			JSONObject data = new JSONObject();
			data.put("id", "1");
			data.put("notification", bodyText.toString());
			body.put("notification", notification);
			body.put("data", data);
			String url = fcmNotification.getUrl();
			URL obj = new URL(url);
			HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

			// add reuqest header
			con.setRequestMethod("POST");
			con.setRequestProperty("Authorization", fcmNotification.getAuthorization());
			con.setRequestProperty("content-type", "application/json");

			// Send post request
			con.setDoOutput(true);
			OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
			wr.write(body.toString());
			wr.flush();
			int responseCode = con.getResponseCode();
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void sendRegistationNotification(UserModel userFrom, List<UserModelDetails> userModelDetailsList,
			FcmNotification fcmNotification, HttpServletRequest reques) {
		for (UserModelDetails userModelDetails : userModelDetailsList) {
			try {
				JSONObject body = new JSONObject();
				String CLIENT_TOKEN = userModelDetails.getUserModel().getDeviceToken();
				body.put("to", CLIENT_TOKEN);
				body.put("priority", "high");
				JSONObject bodyText = new JSONObject();
				bodyText.put("Message","New Registraion");
				bodyText.put("UserFrom",userFrom.getUserId());
				bodyText.put("UserTo",userModelDetails.getUserModel().getUserId());
				bodyText.put("UserFromName",userFrom.getFirstName());
				bodyText.put("date",new Date().getTime());
				bodyText.put("type","registration");
				
				JSONObject notification = new JSONObject();
				notification.put("body", bodyText.toString());
				notification.put("title", "Snehbandhan.org");
				notification.put("sound", "default");
				notification.put("content_available", "true");
				notification.put("priority", "high");
				notification.put("click_action", "OPEN_ACTIVITY_1");

				JSONObject data = new JSONObject();
				data.put("id", "1");
				data.put("notification", bodyText.toString());
				body.put("notification", notification);
				body.put("data", data);
				String url = fcmNotification.getUrl();
				URL obj = new URL(url);
				HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
				// add reuqest header
				con.setRequestMethod("POST");
				con.setRequestProperty("Authorization", fcmNotification.getAuthorization());
				con.setRequestProperty("content-type", "application/json");
				// Send post request
				con.setDoOutput(true);
				OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
				wr.write(body.toString());
				wr.flush();
				int responseCode = con.getResponseCode();
				BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
				String inputLine;
				StringBuffer response = new StringBuffer();
				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();
			} catch (Exception e) 
			{
				//e.printStackTrace();
			}
		}
		
	}

	@Override
	public void sendSortListedFcmNotification(UserModel userFrom, UserModel userTo, FcmNotification fcmNotification,
			HttpServletRequest request) {
		try {
			JSONObject body = new JSONObject();
			String CLIENT_TOKEN = userTo.getDeviceToken();
			body.put("to", CLIENT_TOKEN);
			body.put("priority", "high");
			JSONObject bodyText = new JSONObject();
			bodyText.put("Message","Your Profile is sortlisted By  "+userFrom.getFirstName());
			bodyText.put("UserFrom",userFrom.getUserId());
			bodyText.put("UserTo",userTo.getUserId());
			bodyText.put("UserFromName",userFrom.getFirstName());
			bodyText.put("date",new Date().getTime());
			bodyText.put("type","shortlist");

			JSONObject notification = new JSONObject();
			notification.put("body", bodyText.toString());
			notification.put("title", "Snehbandhan.org");
			notification.put("sound", "default");
			notification.put("content_available", "true");
			notification.put("priority", "high");
			notification.put("click_action", "OPEN_ACTIVITY_1");

			JSONObject data = new JSONObject();
			data.put("id", "1");
			data.put("notification", bodyText.toString());
			body.put("notification", notification);
			body.put("data", data);
			String url = fcmNotification.getUrl();
			URL obj = new URL(url);
			HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
			// add reuqest header
			con.setRequestMethod("POST");
			con.setRequestProperty("Authorization", fcmNotification.getAuthorization());
			con.setRequestProperty("content-type", "application/json");
			// Send post request
			con.setDoOutput(true);
			OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
			wr.write(body.toString());
			wr.flush();
			int responseCode = con.getResponseCode();
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
