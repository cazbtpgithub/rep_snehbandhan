package com.xanadutec.coreflex.MsgEmailSender;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Properties;
import javax.mail.BodyPart;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import com.xanadutec.coreflex.model.ServerInfo;
import com.xanadutec.coreflex.model.SmsInfo;
import com.xanadutec.coreflex.sms.smsService;

public class MsgEmailSender {

	public static String sendSms(String mob, String smsText, String type, smsService smsservice) {

		SmsInfo smsDetail = smsservice.getSmsDetails();

		String userName = smsDetail.getUserName();
		String password = smsDetail.getPassword();
		String senderId = smsDetail.getSenderid();
		String channel = smsDetail.getChannel();
		String dcs = smsDetail.getDCS();
		String flashsms = smsDetail.getFlashsms();
		String route = smsDetail.getRoute();

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

		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return "success  ";
	}

	public static String sendEmail(String email_Ids, String subject, String body, HttpServletRequest request,
			final ServerInfo serverInfo) {
		
		
		String 
			text ="<table border='0' cellpadding='0' cellspacing='0'>"
				+ "		<tbody><tr><td valign='top'>"
				+ "<table style='max-width: 380px' border='0' cellpadding='0' cellspacing='0'>"
				+ "		<tbody><tr><td style='padding-left: 4px; padding-top: 3px; padding-bottom: 5px'>"
				+ "				<font style='font-family: Arial, MS Sans serif, Arial, Verdana, Helvetica; font-size: 9px; font-style: normal; text-align: justify; "
				+ "				text-transform: none; color: rgb(166, 166, 166)'> You are receiving this mail as a registered member of snehbanbhan.org. Please add "
				+ "				<a href='mailto:noreply@snehbandhan.org' target='_blank'>noreply@snehbandhan.org</a>to your address book to ensure delivery to your inbox."
			    + "				</font></td></tr>"
			    + "		</tbody>"
			    + "</table>"
			    + "			</td></tr><tr><td>"
				+ "<table style='border: solid 1px #dddddd; max-width: 580px' bgcolor='ffffff' border='0' cellpadding='0' cellspacing='0'>"
				+ "		<tbody><tr><td valign='top'>"
				+ "	<table align='right' border='0' cellpadding='0' cellspacing='0'>"
				+ "		<tbody> <tr><td style='font: normal 1.1em/28px arial; color: #4f4f4f; padding: 25px 15px 0px 10px' valign='top'>"
				+ new Date()
				+ "				</td></tr>"
				+ "		</tbody>"
				+ "</table>	"
				+ "				</td></tr><tr style='display: table-row' bgcolor='f6f6f6'> <td valign='top'>"
				+ "<table border='0' cellpadding='0' cellspacing='0'>"
				+ "	 	<tbody>"
				+ "					<tr><td height='15'></td></tr><tr><td width='20'></td><td	style='font-size: 16px; color: #656565; font-family: arial; line-height: 17px'"
				+ "					valign='top'>To ensure you receive all match related<br>communication,move this mail to Social folder.</td>	"
				+ "					<td width='20'></td><td valign='top'><a href='http://www.snehbandhan.org/' target='_blank' data-saferedirecturl=''>"
				+ "					<img class='' src='' style=''></a></td><td width='15'></td></tr><tr><td height='15'></td></tr>"
				+ "		</tbody>"
				+ "</table>"
				+ "			</td></tr><tr><td bgcolor='ff6c0d' valign='top'>"
				+ "<table border='0' cellpadding='0' cellspacing='0' width='100%'>"
				+ "			<tbody>"
				+ "					<tr><td height='5'></td></tr><tr><td style='width: 3%; max-width: 20px'></td>"
				+ "					<td style='font: bold 1.8em/34px arial; color: #ffffff'	align='left' valign='top'>"
				+ "	"+subject
				+ "					</td></tr><tr><td height='5'></td></tr>"
				+ "			</tbody>"
				+ "	</table>	"
				+ "					</td></tr><tr><td valign='top'>"
				+ "<table border='0' cellpadding='0' cellspacing='0' width='100%'>	"
				+ "		<tbody><tr><td valign='top'>"
				+ "<table border='0' cellpadding='0' cellspacing='0' width='100%'>"
				+ "			<tbody>"
				+ "				<tr><td>"
				+ ""	+body
				+ "				</td></tr>"
				+ "			</tbody>"
				+ "</table>"
				+ "			</td> </tr> </tbody> </table> </td> </tr></tbody></table></td></tr><tr><td>		"
				+ "<table style='max-width: 380px' border='0' cellpadding='0' cellspacing='0'> "
				+ "		<tbody> "
				+ "			<tr> <td style='padding-left: 4px; padding-top: 3px'><font style='font-family: Arial, MS Sans serif, Arial, Verdana, Helvetica; font-size: 9px; font-style: normal; "
				+ "			text-align: justify; text-transform: none; color: rgb(166, 166, 166)'>You	 are a <a href='http://www.snehbandhan.org/' target='_blank' data-saferedirecturl='"
				+ "			https://www.google.com/url?hl=en&amp;q=http://www.snehbandhan.org&amp;source=gmail&amp;ust=1490347266420000&amp;usg=AFQjCNFfUlhjn3zBLP9Z7qMm2XntCQwkEg'>snehbandhan.org</a>		"
				+ "			member. This e-mail comes to you in accordance with "
				+ "			<a href='http://www.snehbandhan.org/' style='text-decoration: none; color: #206cd4' target='_blank'"
				+ "			data-saferedirecturl='https://www.google.com/url?hl=en&amp;q=http://www.snehbandhan.org'> snehbandhan.org</a>'s Privacy Policy. <a rel='nofollow'	"
				+ "			href='http://www.snehbandhan.org/' target='_blank' data-saferedirecturl='https://www.google.com/url?hl=en&amp;q=http://www.snehbandhan.org/'>snehbandhan.org</a>"
				+ "			is not responsible for content other than its own and makes no	warranties or guarantees about the products or services that			are advertised.	"
				+ "			</font></td> </tr>					"
				+ "		</tbody>"
				+ "</table>"
				+ "			</td></tr>"
				+ "		</tbody>"
				+ "</table>";
		Folder folder = null;
		String reply = "";
		Properties properties = new Properties();
		properties.setProperty("mail.host", serverInfo.getMail_host());
		properties.setProperty("mail.port", serverInfo.getMail_port());
		properties.setProperty("mail.transport.protocol", "imaps");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.host", serverInfo.getMail_host());
		properties.put("mail.smtp.port", serverInfo.getMail_port());
		Store store;
		Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(serverInfo.getMail_serverName(), serverInfo.getMail_password());
			}
		});
		try {
			store = session.getStore("imaps");

			store.connect(serverInfo.getMail_serverName(), serverInfo.getMail_password());
			folder = store.getFolder("inbox");
			if (!folder.exists()) {
				System.exit(0);
			}

		} catch (Exception e1) {
			e1.printStackTrace();
		}
		try {
			Message replyMessage = new MimeMessage(session);
			replyMessage.setContent(text, "text/html; charset=UTF-8");
			replyMessage.setFrom(new InternetAddress(serverInfo.getMail_serverName(), serverInfo.getMsgName()));
			replyMessage.setContent(text, "text/html");
			replyMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email_Ids, false));
			replyMessage.setSubject(subject);
			store = session.getStore("imaps");
			store.connect();
			folder.open(Folder.READ_WRITE);
			try {
				BodyPart messageBodyPart = new MimeBodyPart();
				messageBodyPart.setText(body);
			} catch (Exception e) {
				reply += e.getMessage() + "   2";
				e.printStackTrace();
			}

		
			Transport t = session.getTransport("smtp");
			try {
			
				t.connect(serverInfo.getMail_serverName(), serverInfo.getMail_password());
				if (!email_Ids.trim().isEmpty()) {
					try {
						t.sendMessage(replyMessage, replyMessage.getAllRecipients());
					} catch (Exception e) {
					}
				}
				// store message in inbox
				replyMessage.setFlag(Flags.Flag.SEEN, true);
				Message[] messages = new Message[1];
				messages[0] = replyMessage;
				folder.appendMessages(messages);
				folder.close(false);
				store.close();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				t.close();
			}
		} catch (Exception e) {
			reply += e.getMessage() + "   3";
			e.printStackTrace();
		}
		return "EMail Sent Successfully!! " + reply;
	}

}
