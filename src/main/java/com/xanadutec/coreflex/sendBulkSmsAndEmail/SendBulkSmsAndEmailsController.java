package com.xanadutec.coreflex.sendBulkSmsAndEmail;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xanadutec.coreflex.location.LocationService;
import com.xanadutec.coreflex.model.Country;
import com.xanadutec.coreflex.model.SmsInfo;
import com.xanadutec.coreflex.model.UserModel;
import com.xanadutec.coreflex.model.UserModelDetails;
import com.xanadutec.coreflex.sms.smsService;
import com.xanadutec.coreflex.userTask.UserService;

@Controller
public class SendBulkSmsAndEmailsController {

	@Autowired
	LocationService locationservice;
	@Autowired
	UserService userService;
	@Autowired
	private smsService smsservice;
	@RequestMapping(value = "/sendBulkSmsAndEmails", method = RequestMethod.GET)
	public ModelAndView sendBulkSmsAndEmails(ModelMap model, HttpServletRequest request) {
		List<Country> countryList = locationservice.getListOfcountry();
		model.addAttribute("countryList", countryList);
		HttpSession httpSession = request.getSession();
		UserModel userModel=(UserModel) httpSession.getAttribute("ActUserId");
		if(userModel.getUserRoll().getId()==4)
		{
			model.addAttribute("loginUserRole", "Employee");
		}
		else
		{
			model.addAttribute("loginUserRole", "Admin");
		}
		return new ModelAndView("BulkSmsAndEmail");
	}
	
	@RequestMapping(value = "/userMobileNumberByGender", method = RequestMethod.GET)
	@ResponseBody
	public String userMobileNumberByGender(ModelMap model, @RequestParam("gender") String gender,@RequestParam("userType") String userType,
			Model uiModel, HttpServletRequest request,HttpServletResponse response) 
	{
		String json = null;
		List<UserModelDetails>userDetailsList = null;
		try {
			HttpSession session = request.getSession();
			if(userType.equalsIgnoreCase("paid"))
			{
				userDetailsList = userService.getUserByGender(gender);
			}
			if(userType.equalsIgnoreCase("unpaid"))
			{
				userDetailsList = userService.getUserByGenderAndUserType(gender);
			}
			System.out.println("List size:"+userDetailsList.size());
			JSONObject mainObj = new JSONObject();
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			JSONObject js = new JSONObject();
			js.put("userModelList", ow.writeValueAsString(userDetailsList));
			json = js.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}
	
	@RequestMapping(value = "/sendBulkSms", method = RequestMethod.GET)
	@ResponseBody
	public String sendMomEmail(final HttpServletRequest request,HttpServletResponse response,ModelMap model) {	
		String bulksmsMessage = request.getParameter("bulksmsMessage");
		try {
			BufferedReader in = null;
			Enumeration<String> s = request.getParameterNames();
			while (s.hasMoreElements()) {
				String mobileNumber = s.nextElement();
				if (mobileNumber.equalsIgnoreCase("companyId")) {
				} else if (request.getParameter(mobileNumber).equalsIgnoreCase("on")) {
				    System.out.println("mobileNumber :"+mobileNumber);
				    System.out.println("Test Message:"+bulksmsMessage);
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
								+ "&number=" + mobileNumber + "&text=" + URLEncoder.encode(bulksmsMessage, "UTF8") + "&route=" + route;
						URL obj = new URL(url);
						HttpURLConnection con = (HttpURLConnection) obj.openConnection();
						con.setRequestMethod("GET");
						int responseCode = con.getResponseCode();
						in = new BufferedReader(new InputStreamReader(con.getInputStream()));
						String inputLine;
						StringBuffer responseBuffer = new StringBuffer();
						while ((inputLine = in.readLine()) != null) {
							responseBuffer.append(inputLine);
						}
						//in.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
					//return "success";
				}
			}
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "sucess";

	}
	
}
