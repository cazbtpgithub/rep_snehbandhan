package com.xanadutec.coreflex.Registration;

import org.hibernate.Criteria;
import org.springframework.transaction.annotation.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xanadutec.coreflex.model.UserModelDetails;

@Repository("RegistrationDao")
@Transactional
public class RegistrationDaoImpl implements RegistrationDao {

	@Autowired
	private SessionFactory sessionFactory;
	@Override
	public UserModelDetails getUserModelDetailsByEmail(String Email) {
		UserModelDetails userModel=null;
		try {
			Session session = sessionFactory.getCurrentSession();
			Criteria criteria = session.createCriteria(UserModelDetails.class);
			criteria.add(Restrictions.eq("email", Email));
			userModel = (UserModelDetails) criteria.uniqueResult();
			} catch (Exception e) {
		}
		return userModel;
	}

	@Override
	public UserModelDetails getUserModelDetailsByMobile(String Mobile) {
		UserModelDetails userModel=null;
		try {
			Session session = sessionFactory.getCurrentSession();
			Criteria criteria = session.createCriteria(UserModelDetails.class);
			criteria.add(Restrictions.eq("mobileNum", Mobile));
			userModel = (UserModelDetails) criteria.uniqueResult();
			} catch (Exception e) {
		}
		return userModel;
	}
}
