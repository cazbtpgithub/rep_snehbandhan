package com.xanadutec.coreflex.sms;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;

import javax.transaction.Transaction;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xanadutec.coreflex.model.Country;
import com.xanadutec.coreflex.model.District_SubDistricts;
import com.xanadutec.coreflex.model.Kit;
import com.xanadutec.coreflex.model.SmsInfo;
import com.xanadutec.coreflex.model.State_Country;
import com.xanadutec.coreflex.model.State_Districts;

@Repository("smsDao")
@Transactional
public class smsDaoImpl implements smsDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public SmsInfo getSmsDetails() {
		
		Session session = sessionFactory.getCurrentSession();
		try {
			SmsInfo smsDetail=(SmsInfo) session.get(SmsInfo.class ,1);
			return smsDetail;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			}
		return null;
	}
	
	
	
}
