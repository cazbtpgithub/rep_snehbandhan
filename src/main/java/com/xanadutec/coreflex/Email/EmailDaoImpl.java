package com.xanadutec.coreflex.Email;

import java.io.BufferedReader;
import org.springframework.transaction.annotation.Transactional;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import com.xanadutec.coreflex.model.Kit_Upgrade_Or_Reg_Details;
import com.xanadutec.coreflex.model.ServerInfo;
import com.xanadutec.coreflex.model.SmsInfo;
import com.xanadutec.coreflex.model.UserModel;
import com.xanadutec.coreflex.model.UserModelDetails;
import com.xanadutec.coreflex.model.UserModelImagesName;
import com.xanadutec.coreflex.sms.smsService;

@Repository("EmailDao")
@Transactional
public class EmailDaoImpl implements EmailDao {

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	smsService smsservice;

	@Override
	public void sendRegistrationEmail(String fillName, final String userName, final String password,
			final String emailId, final HttpServletRequest request, final String pathact) {
		// sendPasswordEmail(userName,password,email2,req,passwordservice);

		//"<table background=\"cid:image5\" style=\"background-repeat:no-repeat;background-size: 100% 100%; width:500px; height:570px;\">");
		//String mailTo = "YOUR_RECIPIENT";
		//String subject = "Test e-mail with inline images";
		StringBuffer body = new StringBuffer("<html><br>");
		body.append("<table style=\"border: 1px solid pink; width:700px;\" >");
		/*body.append("<tr><td>");
		body.append("<img src=\"cid:image1\" width=\"40%\" height=\"20%\" /><br>");
		body.append("</td></tr>");*/
		body.append("<tr><td align=\"center\">");
		body.append(
				"<table>");
		body.append("<tr><td></td></tr>");
		body.append("<tr><td></td></tr>");
		body.append("<tr><td align=\"center\" style=\"font:bold 15px/18px arial;color:#535353\">");
		body.append("<font color=\"#b3003b\"> Dear \t " + userName + ",</font><br><br>");
		body.append("<font color=\"#b3003b\"> Welcome to Snehbandhan.org!</font><br>");
		body.append("<font color=\"#b3003b\"> We are happy to have as a member  ");
		body.append("<br>at the most successful matrimony site.</font><br>");
		body.append("<font color=\"#b3003b\">Your Login Details Are:<br> User Name :" + userName + "<br>");
		body.append("Password :" + password);
		body.append("<br><br><br><br><br>");
		body.append("</td></tr>");
		body.append("<tr><td>");
		body.append("<img src=\"cid:image6\" width=\"100%\" height=\"100%\" /><br>");
		body.append("</td></tr>");
		body.append("<tr><td></td></tr>");
		body.append("<tr><td></td></tr>");
		body.append("</table>");
		body.append("</td></tr><br><br><br>");
		body.append("<tr><td align=\"center\">");
		body.append("<a href=\"https://www.facebook.com\"><img src=\"cid:image2\"/></a>&nbsp;&nbsp;");
		body.append("<a href=\"https://mail.google.com\"><img src=\"cid:image3\"/></a>&nbsp;&nbsp;");
		body.append("<a href=\"https://twitter.com\"><img src=\"cid:image4\"/></a>");
		body.append("</td></tr><br></br>" + "	<font size='1;'>" + "You are a Snehbandhan.org member. "
				+ "This e-mail comes to you in accordance with Snehbandhan.org's Privacy Policy. "
				+ "Snehbandhan.org is not responsible for content other than its own and makes no warranties or guarantees about the products or services that are advertised."
				+ "</font>	");
		body.append("</table>");
		body.append("</html>");

		// inline images
		Map<String, String> inlineImages = new HashMap<String, String>();

		ServerInfo serverInfo = null;

		try {
			//inlineImages.put("image1", pathact + "/logo.png");
			inlineImages.put("image2", pathact + "/facebook.png");
			inlineImages.put("image3", pathact + "/gmail.png");
			inlineImages.put("image4", pathact + "/twitter.png");
			//inlineImages.put("image5", pathact + "/background.jpg");
			inlineImages.put("image6", pathact + "/background.jpg");

			org.hibernate.Session session = sessionFactory.getCurrentSession();
			Criteria criteria = session.createCriteria(ServerInfo.class);
			List<ServerInfo> ftpInfos = criteria.list();
			serverInfo = ftpInfos.get(0);
		} catch (Exception e) {
		}

		try {
			sendPasswordEmail(userName, password, emailId, serverInfo, body.toString(), inlineImages);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	void sendPasswordEmail(String uname, String pass, String email, final ServerInfo serverInfo, String htmlBody,
			Map<String, String> mapInlineImages) {

		try {

			Properties properties = new Properties();
			properties.put("mail.smtp.auth", "true");
			// props.put("mail.smtp.starttls.enable", "true");
			properties.put("mail.smtp.host", serverInfo.getMail_host());
			properties.put("mail.smtp.port", serverInfo.getMail_port());
			Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(serverInfo.getMail_serverName(), serverInfo.getMail_password());
				}
			});


			try {

				try {

					Message replyMessage = new MimeMessage(session);
					replyMessage.setSubject("Login Credentials");
					replyMessage.setFrom(new InternetAddress(serverInfo.getMail_serverName(), serverInfo.getMsgName()));
					replyMessage.setContent(htmlBody, "text/html");
					replyMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email, false));
					try {

						BodyPart messageBodyPart = new MimeBodyPart();

						messageBodyPart.setText(htmlBody);
						// Create a multipar message
						Multipart multipart = new MimeMultipart();
						MimeBodyPart messageBodyP = new MimeBodyPart();
						messageBodyP.setContent(htmlBody, "text/html");
						multipart.addBodyPart(messageBodyP);

						if (mapInlineImages != null && mapInlineImages.size() > 0) {
							Set<String> setImageID = mapInlineImages.keySet();

							for (String contentId : setImageID) {
								MimeBodyPart imagePart = new MimeBodyPart();
								imagePart.setHeader("Content-ID", "<" + contentId + ">");
								imagePart.setDisposition(MimeBodyPart.INLINE);

								String imageFilePath = mapInlineImages.get(contentId);
								try {
									imagePart.attachFile(imageFilePath);
								} catch (IOException ex) {
									ex.printStackTrace();
								}

								multipart.addBodyPart(imagePart);
							}
						}

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
	}

	@Override
	public void viewProfileSendEmail(final UserModelDetails modelDetailsFrom, final UserModelDetails modelDetailsTo,
			HttpServletRequest request, final String path) {
		Thread tt = new Thread(new Runnable() {
			public void run() {
				Date date = modelDetailsFrom.getDateOfBirth();
				Calendar calendar = new GregorianCalendar();
				calendar.setTime(date);
				Date d1 = new GregorianCalendar(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
						calendar.get(Calendar.DAY_OF_MONTH), 23, 59).getTime();
				Date today = new Date();
				long diff = today.getTime() - d1.getTime();
				long i = diff / (1000 * 60 * 60 * 24);
				int dob = (int) (i / 365);
				// sendPasswordEmail(userName,password,email2,req,passwordservice);
				StringBuffer body = new StringBuffer("<html> <head>");
				body.append(" </head> <body>"
						+ "	<table  style=' width: 600px; border: 3px solid #f2f2f2; padding: 15px; margin: 5px;'>"
						+ "	<tr>" + "	<td style='height:300px; width: 600px;  colspan='2'>");
				body.append("<img src=\"cid:image1\" width=\"100%\" height=\"90%\" />" + "</td> </tr><tr>"
						+ "<td style=' border: 1px solid #364252; ' colspan='2'>"
						+ "<b> <center style='background:#f2f2f2; color:#d23779;'> Viewed Your Profile. May be intrested in you</center></b>"
						+ "</td> </tr>"
						+ "<tr> <td style=' height:150px; width: 300px; border: 1px solid #364252; padding: 25px; margin: 10px; background-color:#f2f2f2;'>");

				body.append("<img src=\"cid:image5\" width=\"300px\" height=\"300px\" />" + "</td>"
						+ "<td style=' height:150px; width: 300px; border: 1px solid #364252; padding: 25px; margin: 10px; background-color:#f2f2f2;'>"
						+ "Name:" + modelDetailsFrom.getUserModel().getFirstName() + "<br>" + "Age :" + dob
						+ "<br>Location:" + modelDetailsFrom.getDistricts().getDistrict_Name() + ","
						+ modelDetailsFrom.getState_Country().getState_Name() + "<br>Education:"
						+ modelDetailsFrom.getEducation().getEducation_name() + "<br>Occupation:"
						+ modelDetailsFrom.getOccupation().getOccupation_name()
						+ "<br><br><a href=\"www.snehbandhan.org\"><input type='button' value='Connect Now' style='background-color:#d23779; border: none;"
						+ "color: white;   padding: 15px 32px; text-align: center; text-decoration: none; display: inline-block;	font-size: 16px;  position: relative; left: 40%; border-radius: 25px;' />"
						+ "</a></td></tr>"
						+ "<tr><td style='height:70px; width: 300px; border: 1px solid #364252;  background-color:#f2f2f2; ' colspan='2'>");
				body.append("<center><a href=\"https://www.facebook.com\"><img src=\"cid:image2\"/></a>&nbsp;&nbsp;");
				body.append("<a href=\"https://mail.google.com\"><img src=\"cid:image3\"/></a>&nbsp;&nbsp;");
				body.append("<a href=\"https://twitter.com\"><img src=\"cid:image4\"/></a>");
				body.append("</center></td>"
						+ "</tr><tr><td style='border: 1px solid #364252;  background-color:#f2f2f2; ' colspan='2'>"
						+ "<font size='1;' color='red';> You are a Snehbandhan.org member.					 This e-mail comes to you in accordance with Snehbandhan.org's Privacy Policy."
						+ "Snehbandhan.org is not responsible for content other than its own and makes no warranties or guarantees about the products or services that are advertised."
						+ "</font>	</td> </tr>		" + "</table>  </body></html> ");

				// inline images
				Map<String, String> inlineImages = new HashMap<String, String>();

				ServerInfo serverInfo = null;

				try {
					inlineImages.put("image1", path + "/logo.png");
					inlineImages.put("image2", path + "/facebook.png");
					inlineImages.put("image3", path + "/gmail.png");
					inlineImages.put("image4", path + "/twitter.png");

					org.hibernate.Session session = sessionFactory.getCurrentSession();
					Criteria criteria = session.createCriteria(UserModelImagesName.class);
					criteria.add(Restrictions.eq("userModel", modelDetailsFrom.getUserModel()));
					List<UserModelImagesName> imagesNames = criteria.list();
					if (imagesNames == null) {
						inlineImages.put("image5", path + "/userImage.png");
					} else {
						String img[] = imagesNames.get(0).getImageNames().split("#");
						String imageName = img[0];
						inlineImages.put("image5", path + "/" + imageName);
					}

					session = sessionFactory.getCurrentSession();
					criteria = session.createCriteria(ServerInfo.class);
					List<ServerInfo> ftpInfos = criteria.list();
					serverInfo = ftpInfos.get(0);
				} catch (Exception e) {
					e.printStackTrace();
				}

				try {
					sendViewProfileEmail(modelDetailsTo.getEmail(), serverInfo, body.toString(), inlineImages);
				} catch (Exception ex) {
					ex.printStackTrace();
				}

			}
		});
		tt.start();
	}

	void sendViewProfileEmail(String email, final ServerInfo serverInfo, String htmlBody,
			Map<String, String> mapInlineImages) {

		try {

			Properties properties = new Properties();
			properties.put("mail.smtp.auth", "true");
			// props.put("mail.smtp.starttls.enable", "true");
			properties.put("mail.smtp.host", serverInfo.getMail_host());
			properties.put("mail.smtp.port", serverInfo.getMail_port());
			Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(serverInfo.getMail_serverName(), serverInfo.getMail_password());
				}
			});


			try {

				try {

					Message replyMessage = new MimeMessage(session);
					replyMessage.setSubject("Profile Intrest");
					replyMessage.setFrom(new InternetAddress(serverInfo.getMail_serverName(), serverInfo.getMsgName()));
					replyMessage.setContent(htmlBody, "text/html");
					replyMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email, false));
					try {

						BodyPart messageBodyPart = new MimeBodyPart();

						messageBodyPart.setText(htmlBody);
						// Create a multipar message
						Multipart multipart = new MimeMultipart();
						MimeBodyPart messageBodyP = new MimeBodyPart();
						messageBodyP.setContent(htmlBody, "text/html");
						multipart.addBodyPart(messageBodyP);

						if (mapInlineImages != null && mapInlineImages.size() > 0) {
							Set<String> setImageID = mapInlineImages.keySet();

							for (String contentId : setImageID) {
								MimeBodyPart imagePart = new MimeBodyPart();
								imagePart.setHeader("Content-ID", "<" + contentId + ">");
								imagePart.setDisposition(MimeBodyPart.INLINE);

								String imageFilePath = mapInlineImages.get(contentId);
								try {
									imagePart.attachFile(imageFilePath);
								} catch (IOException ex) {
									ex.printStackTrace();
								}

								multipart.addBodyPart(imagePart);
							}
						}

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
	}

	@Override
	public void sendKitExpiryMail(String path) {
		final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			java.util.Date currentDate = new java.util.Date();
			// convert date to calendar
			Calendar c = Calendar.getInstance();
			c.setTime(currentDate);
			c.add(Calendar.DATE, 5); // same with c.add(Calendar.DAY_OF_MONTH,
										// 1);
			java.util.Date currentDatePlusOne = c.getTime();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			String strFromDate = null;
			String strTodate = null;
			java.util.Date actFromDate = null;
			java.util.Date actToDate = null;
			try {
				strFromDate = df.format(currentDatePlusOne) + " 00:00:00";
				strTodate = df.format(currentDatePlusOne) + " 23:59:59";
				actFromDate = dateFormat.parse(strFromDate);
				actToDate = dateFormat.parse(strTodate);

			} catch (Exception e) {
				e.printStackTrace();
			}

			org.hibernate.Session session = sessionFactory.getCurrentSession();
			Criteria criteria = session.createCriteria(Kit_Upgrade_Or_Reg_Details.class);
			criteria.add(Restrictions.between("expiryDate", actFromDate, actToDate));
			List<Kit_Upgrade_Or_Reg_Details> list = criteria.list();
			List<UserModel> userModels = new ArrayList<>();
			for (Kit_Upgrade_Or_Reg_Details kit_Upgrade_Or_Reg_Details : list) {

				userModels.add(kit_Upgrade_Or_Reg_Details.getUserModel());
			}
			// second criteria
			criteria = session.createCriteria(UserModelDetails.class);
			criteria.add(Restrictions.in("userModel", userModels));
			List<UserModelDetails> userModelDetails = criteria.list();
			// Third criteria
			ServerInfo serverInfo = null;
			criteria = session.createCriteria(ServerInfo.class);
			List<ServerInfo> ftpInfos = criteria.list();
			serverInfo = ftpInfos.get(0);
			for (UserModelDetails userModelDetails2 : userModelDetails) {
				StringBuffer body = new StringBuffer("<html> <head>");
				body.append(" </head> <body>"
						+ "	<table  style=' width: 600px; border: 3px solid #f2f2f2; padding: 15px; margin: 5px;'>"
						+ "	<tr>" + "	<td style='height:300px; width: 600px;  colspan='2'>");
				body.append("<img src=\"cid:image1\" width=\"100%\" height=\"90%\" />" + "</td> </tr><tr>"
						+ "<td style=' border: 1px solid #364252; ' colspan='2'>"
						+ "<b> <center style='background:#f2f2f2; color:#d23779;'> Please Upgrade Your Package</center></b>"
						+ "</td> </tr>"
						/*
						 * +
						 * "<tr> <td style=' height:150px; width: 300px; border: 1px solid #364252; padding: 25px; margin: 10px; background-color:#f2f2f2;'>"
						 * );
						 * 
						 * body.append(
						 * "<img src=\"cid:image5\" width=\"300px\" height=\"300px\" />"
						 * + "</td>"
						 */
						+ "<tr><td style=' height:150px; width: 600px; border: 1px solid #364252; padding: 25px; margin: 10px; background-color:#f2f2f2;'>"
						+ " Please Upgrade your package it is going to expire in next five day's"
						+ "<br><br><a href=\"www.snehbandhan.org\"><input type='button' value='Upgrade Now' style='background-color:#d23779; border: none;"
						+ "color: white;   padding: 15px 32px; text-align: center; text-decoration: none; display: inline-block;	font-size: 16px;  position: relative; left: 40%; border-radius: 25px;' />"
						+ "</a></td></tr>"
						+ "<tr><td style='height:70px; width: 300px; border: 1px solid #364252;  background-color:#f2f2f2; ' colspan='2'>");
				body.append("<center><a href=\"https://www.facebook.com\"><img src=\"cid:image2\"/></a>&nbsp;&nbsp;");
				body.append("<a href=\"https://mail.google.com\"><img src=\"cid:image3\"/></a>&nbsp;&nbsp;");
				body.append("<a href=\"https://twitter.com\"><img src=\"cid:image4\"/></a>");
				body.append("</center></td>"
						+ "</tr><tr><td style='border: 1px solid #364252;  background-color:#f2f2f2; ' colspan='2'>"
						+ "<font size='1;' color='red';> You are a Snehbandhan.org member.					 This e-mail comes to you in accordance with Snehbandhan.org's Privacy Policy."
						+ "Snehbandhan.org is not responsible for content other than its own and makes no warranties or guarantees about the products or services that are advertised."
						+ "</font>	</td> </tr>		" + "</table>  </body></html> ");

				// inline images
				Map<String, String> inlineImages = new HashMap<String, String>();

				try {
					inlineImages.put("image1", path + "/logo.png");
					inlineImages.put("image2", path + "/facebook.png");
					inlineImages.put("image3", path + "/gmail.png");
					inlineImages.put("image4", path + "/twitter.png");
					criteria = session.createCriteria(ServerInfo.class);

				} catch (Exception e) {
					e.printStackTrace();
				}

				try {
					sendUpgradeProfileEmail(userModelDetails2.getEmail(), serverInfo, body.toString(), inlineImages);
					sendSms(userModelDetails2.getMobileNum());
				} catch (Exception ex) {
					ex.printStackTrace();
				}

			}
			} catch (Exception e) {

			e.printStackTrace();
		}

	}

	void sendUpgradeProfileEmail(String email, final ServerInfo serverInfo, String htmlBody,
			Map<String, String> mapInlineImages) {
		try {
			Properties properties = new Properties();
			properties.put("mail.smtp.auth", "true");
			// props.put("mail.smtp.starttls.enable", "true");
			properties.put("mail.smtp.host", serverInfo.getMail_host());
			properties.put("mail.smtp.port", serverInfo.getMail_port());
			Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(serverInfo.getMail_serverName(), serverInfo.getMail_password());
				}
			});
			try {
				try {
					Message replyMessage = new MimeMessage(session);
					replyMessage.setSubject("Upgrade Package");
					replyMessage.setFrom(new InternetAddress(serverInfo.getMail_serverName(), serverInfo.getMsgName()));
					replyMessage.setContent(htmlBody, "text/html");
					replyMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email, false));
					try {

						BodyPart messageBodyPart = new MimeBodyPart();

						messageBodyPart.setText(htmlBody);
						// Create a multipar message
						Multipart multipart = new MimeMultipart();
						MimeBodyPart messageBodyP = new MimeBodyPart();
						messageBodyP.setContent(htmlBody, "text/html");
						multipart.addBodyPart(messageBodyP);

						if (mapInlineImages != null && mapInlineImages.size() > 0) {
							Set<String> setImageID = mapInlineImages.keySet();

							for (String contentId : setImageID) {
								MimeBodyPart imagePart = new MimeBodyPart();
								imagePart.setHeader("Content-ID", "<" + contentId + ">");
								imagePart.setDisposition(MimeBodyPart.INLINE);

								String imageFilePath = mapInlineImages.get(contentId);
								try {
									imagePart.attachFile(imageFilePath);
								} catch (IOException ex) {
									ex.printStackTrace();
								}

								multipart.addBodyPart(imagePart);
							}
						}

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
	}	
	public String sendSms(@RequestParam("mob") final String mob) {
		SmsInfo smsDetail = smsservice.getSmsDetails();
		String userName = smsDetail.getUserName();
		String password = smsDetail.getPassword();
		String senderId = smsDetail.getSenderid();
		String channel = smsDetail.getChannel();
		String dcs = smsDetail.getDCS();
		String flashsms = smsDetail.getFlashsms();
		String route = smsDetail.getRoute();

		String smsText = "Please Upgrade your package it is going to expire in next five days's";
	
		try {

			String url = "http://sms.twitsms.in/api/mt/SendSMS?user=" + userName + "&password="
					+ password + "&senderid=" + senderId + "&channel=" + channel + "&DCS=" + dcs
					+ "&flashsms=" + flashsms + "&number=" + mob + "&text="
					+ URLEncoder.encode(smsText, "UTF8") + "&route=" + route;
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

		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return "success";

	}
}
