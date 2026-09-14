package com.xanadutec.coreflex.KitUpgradeAndPayment;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xanadutec.coreflex.model.Kit_Upgrade_Or_Reg_Details;
import com.xanadutec.coreflex.model.SentMessagesEmail;
import com.xanadutec.coreflex.model.SortlistedProfileUserWithDate;
import com.xanadutec.coreflex.model.UserModel;

@Repository("KitUpgradeAndPaymentDao")
@Transactional
public class KitUpgradeAndPaymentDaoImpl implements KitUpgradeAndPaymentDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Kit_Upgrade_Or_Reg_Details saveKit_upgrade_or_reg_details(
			Kit_Upgrade_Or_Reg_Details kit_Upgrade_Or_Reg_Details) {
		Session session = sessionFactory.getCurrentSession();
		session.save(kit_Upgrade_Or_Reg_Details);
		return kit_Upgrade_Or_Reg_Details;
	}
	
	
	@Override
	public SentMessagesEmail saveSentMessagesEmail(
			SentMessagesEmail messagesEmail) {
		Session session = sessionFactory.getCurrentSession();
		session.save(messagesEmail);
		return messagesEmail;
	}
	

	@Override
	public Kit_Upgrade_Or_Reg_Details updateKit_upgrade_or_reg_details(
			Kit_Upgrade_Or_Reg_Details kit_Upgrade_Or_Reg_Details) {
		Session session = sessionFactory.getCurrentSession();
		session.update(kit_Upgrade_Or_Reg_Details);
		return kit_Upgrade_Or_Reg_Details;
	}

	@Override
	public List<Kit_Upgrade_Or_Reg_Details> getKit_upgrade_or_reg_detailsByUserModel(UserModel model) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Kit_Upgrade_Or_Reg_Details.class);
		criteria.add(Restrictions.eq("userModel", model));
		criteria.addOrder(Order.desc("registrationDate"));
		List<Kit_Upgrade_Or_Reg_Details> lst = criteria.list();
		return lst;
	}

	@Override
	public SortlistedProfileUserWithDate saveSortlistedProfileUserWithDate(
			SortlistedProfileUserWithDate sortlistedProfileUserWithDate) {
		Session session = sessionFactory.getCurrentSession();
		session.save(sortlistedProfileUserWithDate);
		return sortlistedProfileUserWithDate;
	}

	@Override
	public SortlistedProfileUserWithDate getSortlistedProfileUser(UserModel userModel,
			UserModel sortlistedProfileUser) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(SortlistedProfileUserWithDate.class);
		criteria.add(Restrictions.eq("userModel", userModel));
		criteria.add(Restrictions.eq("sortlistedUserModel", sortlistedProfileUser));
		List<SortlistedProfileUserWithDate> lst = criteria.list();
		if (lst.size() > 0) {
			return lst.get(0);
		}
		return null;
	}


	@Override
	public SortlistedProfileUserWithDate getSortlistedProfileUserWithDateById(
			SortlistedProfileUserWithDate profileUserWithDate) {
		Session session = sessionFactory.getCurrentSession();
		 profileUserWithDate = (SortlistedProfileUserWithDate) session.get(SortlistedProfileUserWithDate.class, profileUserWithDate.getSortlistId());
		return 	 profileUserWithDate;
	}


	@Override
	public SortlistedProfileUserWithDate updateSortlistedProfileUserWithDate(
			SortlistedProfileUserWithDate profileUserWithDate) {
		Session session = sessionFactory.getCurrentSession();
		session.update(profileUserWithDate);
		return profileUserWithDate;
	}

}
