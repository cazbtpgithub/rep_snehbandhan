package com.xanadutec.coreflex.chat;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xanadutec.coreflex.model.Chat;
import com.xanadutec.coreflex.model.FcmNotification;
import com.xanadutec.coreflex.model.Kit;
import com.xanadutec.coreflex.model.Status;
import com.xanadutec.coreflex.model.UserModel;
import com.xanadutec.coreflex.notification.NotificationService;
import com.xanadutec.coreflex.userTask.UserService;

@Controller
public class ChatController {
	
	
	@Autowired
	ChatService chatService;
	@Autowired
	UserService userService;
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private NotificationService nitificationService;
	@RequestMapping(value = "/saveChat", method = RequestMethod.POST)
	@ResponseBody
	public String saveChat(ModelMap model, final HttpServletRequest request) {

		JSONObject obj;
		try {
			
			Session session=sessionFactory.openSession();
			obj = new JSONObject(request.getParameter("jsonObjectData"));
			final JSONObject jsonObj = obj;
			final String msg = jsonObj.getString("msg");
			String userFrom = jsonObj.getString("userFrom");
			String userTo = jsonObj.getString("userTo");
		final	UserModel modelFrom=(UserModel) session.get(UserModel.class, new Long(Integer.parseInt(userFrom)));
		final 	UserModel modelTo=(UserModel) session.get(UserModel.class, new Long(Integer.parseInt(userTo)));
			chatService.saveChatting(msg, new Date(), modelFrom, modelTo);
			session.close();
			
			Thread t=new Thread(new Runnable() {
				
				@Override
				public void run() {
					
					FcmNotification fcmNotification = nitificationService.getFcmNotification();
					nitificationService.sendChatFcmNotification(msg, modelFrom, modelTo, fcmNotification, request);
					
					
				}
			});
			t.start();
		
			return "success";
		}catch(Exception e){
			e.printStackTrace();
		}
		return "Error";
	}
	
	
	
	@RequestMapping(value = "/loadChat", method = RequestMethod.POST)
	@ResponseBody
	public String loadChat(ModelMap model, HttpServletRequest request) {
		
		


		JSONObject obj;
		try {
			
			Session session=sessionFactory.openSession();
			obj = new JSONObject(request.getParameter("jsonObjectData"));
			final JSONObject jsonObj = obj;
			
			String userFrom = jsonObj.getString("userFrom");
			String userTo = jsonObj.getString("userTo");
			UserModel modelFrom=(UserModel) session.get(UserModel.class, new Long(Integer.parseInt(userFrom)));
			UserModel modelTo=(UserModel) session.get(UserModel.class, new Long(Integer.parseInt(userTo)));
			List<Chat>chatList=	chatService.getListOfChatAccordingToUser(modelFrom, modelTo);
			
		
			JSONObject mainObj = new JSONObject();
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			
			mainObj.put("chatList", ow.writeValueAsString(chatList));
			String json = mainObj.toString();
			session.close();
		
			return json;
		}catch(Exception e){
			e.printStackTrace();
		}
		return "Error";
	
		
		
	}
	
	
	
}


