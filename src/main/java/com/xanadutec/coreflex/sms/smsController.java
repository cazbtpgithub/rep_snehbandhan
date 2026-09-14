package com.xanadutec.coreflex.sms;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xanadutec.coreflex.model.SmsInfo;
import com.xanadutec.coreflex.packageAndKit.PackageAndKitService;
import com.xanadutec.coreflex.userTask.UserService;

@Controller
public class smsController {

	@Autowired
	smsService locationservice;
	@Autowired
	PackageAndKitService packageandkitService;
	@Autowired
	UserService userService;
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private smsService smsservice;

	@RequestMapping(value = "/sendSms", method = RequestMethod.POST)
	@ResponseBody
	public String sendEmailOrSms(@RequestParam("mob") final String mob, @RequestParam("smsText") final String smsText,
			@RequestParam("type") String type, org.springframework.ui.Model model) {

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

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "success";
	}

}
