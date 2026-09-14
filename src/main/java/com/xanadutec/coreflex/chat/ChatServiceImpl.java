package com.xanadutec.coreflex.chat;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xanadutec.coreflex.model.Chat;
import com.xanadutec.coreflex.model.UserModel;

@Service("ChatService")
public class ChatServiceImpl  implements ChatService{

	@Autowired
	ChatDao chatdao;
	@Override
	public void saveChatting(String message, Date date, UserModel userfrom, UserModel userTo) {
		
		chatdao.saveChatting(message, date, userfrom, userTo);
		
	}
	@Override
	public List<Chat> getListOfChatAccordingToUser(UserModel userFrom, UserModel userTo) {
		return chatdao.getListOfChatAccordingToUser(userFrom, userTo);
	}

}
