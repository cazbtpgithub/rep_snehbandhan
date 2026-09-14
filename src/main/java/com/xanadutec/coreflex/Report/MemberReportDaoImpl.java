package com.xanadutec.coreflex.Report;

import java.util.Date;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xanadutec.coreflex.ReportModel.MemberReportModel;

import com.xanadutec.coreflex.model.AdminUpgradedUser;
import com.xanadutec.coreflex.model.AdminUserModel;

import com.xanadutec.coreflex.model.Kit_Upgrade_Or_Reg_Details;
import com.xanadutec.coreflex.model.Status;
import com.xanadutec.coreflex.model.UserModel;
import com.xanadutec.coreflex.model.UserModelDetails;

@Repository("MemberReportDao")
@Transactional
public class MemberReportDaoImpl implements MemberReportDao
{
	@Autowired
	private SessionFactory sessionFactory;


	@Override
	public List<MemberReportModel> getUserDetailsByPaidAndGender(Date from_Date, Date toDate) 
	{
		
		Session session = sessionFactory.getCurrentSession();
		Criteria criteri = session.createCriteria(Kit_Upgrade_Or_Reg_Details.class);
		
		criteri.createCriteria("userModel", "u");
		criteri.createCriteria("Kit_id", "k");
		criteri.createCriteria("k.package_id", "p");
		criteri.setProjection(Projections.projectionList().add(Projections.property("u.userName"), "userName")
				.add(Projections.property("u.userId"), "userId")
				.add(Projections.property("k.kit_username"), "kitId")
				.add(Projections.property("registrationDate"), "activationDate")
				.add(Projections.property("p.package_Name"), "packageName")
				.add(Projections.property("expiryDate"), "expireDate"))
				.setResultTransformer(Transformers.aliasToBean(MemberReportModel.class));
		criteri.addOrder(Order.desc("registrationDate"));		
		criteri.add(Restrictions.between("registrationDate", from_Date , toDate ));
		List<MemberReportModel> list = criteri.list();
		
		return list;
	}


	@Override
	public List<MemberReportModel> getUnpaidUserDetails()
	{
		try {
			Session session = sessionFactory.getCurrentSession();
			Criteria criteria = session.createCriteria(UserModelDetails.class);
			criteria.createCriteria("userModel", "u");
			criteria.setProjection(Projections.projectionList().add(Projections.property("u.userName"), "userName")
					.add(Projections.property("u.userId"), "userId"))
					.setResultTransformer(Transformers.aliasToBean(MemberReportModel.class));
			
			List<MemberReportModel> list = criteria.list();
			
			return list;
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
	
		return null;
	}


	@Override
	public List<UserModelDetails> getAllUnpaidUserDetailsByGender(String user_Gender, Date from_Date, Date toDate) 
	{
		Status status = new Status();
		status.setStatus_id(1);

		Session session =sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(UserModelDetails.class);
		criteria.add(Restrictions.between("registrationDate", from_Date , toDate ));
		Disjunction or = Restrictions.disjunction();
		or.add(Restrictions.isNull("status"));
		or.add(Restrictions.eq("status", status));
		criteria.add(or);		
		criteria.add(Restrictions.eq("gender", user_Gender));
		criteria.createCriteria("userModel", "userModel");
		criteria.add(Restrictions.isNull("userModel.kit"));
		List<UserModelDetails> userDetailsList = criteria.list();
		return userDetailsList;
	}


	@Override
	public List<Kit_Upgrade_Or_Reg_Details> getKitActivationAndExpiryDatByObject(UserModelDetails userModelDetails2)
	{
		try {
			
			Session session =sessionFactory.getCurrentSession();
			Criteria criteria = session.createCriteria(Kit_Upgrade_Or_Reg_Details.class);
			criteria.add(Restrictions.eq("userModel", userModelDetails2.getUserModel()));
			List<Kit_Upgrade_Or_Reg_Details> useUpdagradeAndRegisterDateList = criteria.list();
			return useUpdagradeAndRegisterDateList;
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
}
