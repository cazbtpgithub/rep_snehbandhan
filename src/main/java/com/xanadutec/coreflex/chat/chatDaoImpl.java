package com.xanadutec.coreflex.chat;

import java.util.Collections;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xanadutec.coreflex.model.Chat;
import com.xanadutec.coreflex.model.UserModel;

@Repository("ChatDao")
@Transactional
public class chatDaoImpl implements ChatDao  {

	@Autowired
	private SessionFactory sessionFactory;
	@Override
	public void saveChatting(String message, Date date, UserModel userfrom, UserModel userTo) {
		try {
			Chat chat=new Chat();
			chat.setChatdate(date);
			chat.setChatText(message);
			chat.setUserModelFrom(userfrom);
			chat.setUserModelTo(userTo);
			Session session=sessionFactory.getCurrentSession();
			session.save(chat);
			} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public List<Chat> getListOfChatAccordingToUser(UserModel userFrom, UserModel userTo) {
		Session session=sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Chat.class);
		criteria.add(Restrictions.eq("userModelFrom", userFrom));
		criteria.add(Restrictions.eq("userModelTo", userTo));
		List<Chat> list = criteria.list();
		criteria = session.createCriteria(Chat.class);
		criteria.add(Restrictions.eq("userModelFrom", userTo));
		criteria.add(Restrictions.eq("userModelTo", userFrom));
		List<Chat> list1 = criteria.list();
		try {
			list.addAll(list1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Collections.sort(list, new ChatComparator());
		return list;
	}
}
