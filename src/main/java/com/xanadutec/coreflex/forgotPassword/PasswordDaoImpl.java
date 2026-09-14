package com.xanadutec.coreflex.forgotPassword;

import java.io.BufferedReader;
import org.springframework.transaction.annotation.Transactional;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xanadutec.coreflex.model.ServerInfo;
import com.xanadutec.coreflex.model.SmsInfo;
import com.xanadutec.coreflex.model.UserModel;
import com.xanadutec.coreflex.model.UserModelDetails;
import com.xanadutec.coreflex.sms.smsService;

@Repository("PasswordDao")
@Transactional
public class PasswordDaoImpl implements PasswordDao {

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private smsService smsService;
	@Override
	public UserModel getUserByEmailId(String email) {
		email=email.trim();
		Session session=sessionFactory.getCurrentSession();
		Criteria criteria=session.createCriteria(UserModel.class);
		criteria.add(Restrictions.eq("email",email));
		UserModel	 user=(UserModel) criteria.uniqueResult();
		return user;
	}
	@Override
	public HashMap<String, String> getUserNamePasswordAccordingToEmailId(String emailId) {
		Session session = sessionFactory.getCurrentSession();		
		try{
			Criteria criteria = session.createCriteria(UserModelDetails.class);
			criteria.add(Restrictions.eq("email", emailId));
			UserModelDetails userModelDetails =  (UserModelDetails) criteria.uniqueResult();
			UserModel userModel= userModelDetails.getUserModel();
			HashMap<String, String> map=new HashMap<>();
			map.put("userName",userModel.getUserName() );
			map.put("password",userModel.getPassword());
			return map;
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			}
		return null;
	}
	@Override
	public HashMap<String, String> getUserNamePasswordAccordingToMobile(String mob) {
		Session session = sessionFactory.getCurrentSession();
		try{
			Criteria criteria = session.createCriteria(UserModelDetails.class);
			criteria.add(Restrictions.eq("mobileNum", mob));
			UserModelDetails userModelDetails =  (UserModelDetails) criteria.uniqueResult();
			UserModel userModel= userModelDetails.getUserModel();
			HashMap<String, String> map=new HashMap<>();
			map.put("userName",userModel.getUserName() );
			map.put("password",userModel.getPassword());
			return map;
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			}
		return null;
	}
	@Override
	public String sendPasswordEmail(String uname, String pass, String email, HttpServletRequest request,
			final ServerInfo serverInfo) {
		// subject,emailList,attendee, keyPoints,createdDateJson
		String MsgText = "<i>Dear User, Your UserName And Password Is:</i><br>";
		MsgText += "<b>UserName :</b>\t" + uname + "<br>";
		MsgText += "<b>Password :</b>\t" + pass + "<br>";
		try {
			Properties properties = new Properties();
			properties.put("mail.smtp.auth", "true");
			// props.put("mail.smtp.starttls.enable", "true");
			properties.put("mail.smtp.host", serverInfo.getMail_host());
			properties.put("mail.smtp.port", serverInfo.getMail_port());
			javax.mail.Session session = javax.mail.Session.getInstance(properties, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(serverInfo.getMail_serverName(), serverInfo.getMail_password());
				}
			});
			try {
				try {
					Message replyMessage = new MimeMessage(session);
					replyMessage.setSubject("Login Credentials");
					replyMessage.setFrom(new InternetAddress(serverInfo.getMail_serverName(), serverInfo.getMsgName()));
					replyMessage.setContent(MsgText, "text/html");
					replyMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email, false));
					try {

						BodyPart messageBodyPart = new MimeBodyPart();
						messageBodyPart.setText(MsgText);
						// Create a multipar message
						Multipart multipart = new MimeMultipart();
						MimeBodyPart messageBodyP = new MimeBodyPart();
						messageBodyP.setContent(MsgText, "text/html");
						multipart.addBodyPart(messageBodyP);
						replyMessage.setContent(multipart);
						try {
							Transport.send(replyMessage);
						} catch (Exception e) {
							e.printStackTrace();
						}

					} catch (Exception e) {
						e.printStackTrace();
					}

				} catch (Exception e) {
					e.printStackTrace();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Success";

	}
	@Override
	public String sendpasswordMessage(String mob, String uname, String pass) {
		try {
			SmsInfo smsDetail = smsService.getSmsDetails();
			String userName = smsDetail.getUserName();
			String password = smsDetail.getPassword();
			String senderId = smsDetail.getSenderid();
			String channel = smsDetail.getChannel();
			String dcs = smsDetail.getDCS();
			String flashsms = smsDetail.getFlashsms();
			String route = smsDetail.getRoute();
			String smsText = "Snehbandhan:";
			smsText += "UserName :  " + uname +"     And   ";
			smsText += "Password :  " + pass + "";
			try {

				String url = "http://sms.twitsms.in/api/mt/SendSMS?user=" + userName + "&password=" + password
						+ "&senderid=" + senderId + "&channel=" + channel + "&DCS=" + dcs + "&flashsms=" + flashsms
						+ "&number=" + mob + "&text=" + URLEncoder.encode(smsText, "UTF8") + "&route=" + route;
				URL obj = new URL(url);
				HttpURLConnection con = (HttpURLConnection) obj.openConnection();
				con.setRequestMethod("GET");
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
			return "success  ";
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return "Success";
	
	}
}
