package com.xanadutec.coreflex.chat;

import java.util.Date;
import java.util.List;

import com.xanadutec.coreflex.model.Chat;
import com.xanadutec.coreflex.model.UserModel;

public interface ChatDao {

	public void saveChatting(String message,Date date,UserModel userfrom,UserModel userTo);
	public List<Chat> getListOfChatAccordingToUser(UserModel userFrom,UserModel userTo);

}
