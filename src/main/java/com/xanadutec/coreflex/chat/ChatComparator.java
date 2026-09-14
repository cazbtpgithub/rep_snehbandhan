package com.xanadutec.coreflex.chat;

import java.util.Comparator;

import com.xanadutec.coreflex.model.Chat;

public class ChatComparator implements Comparator<Chat> {

	@Override
	public int compare(Chat o1, Chat o2) {
		
		return o1.getChatdate().compareTo(o2.getChatdate());
				
}

}
