package com.xanadutec.coreflex.DealerReport;

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

import com.xanadutec.coreflex.model.Dealer;
import com.xanadutec.coreflex.model.KitDealerAssociation;
import com.xanadutec.coreflex.model.Kit_Upgrade_Or_Reg_History_Details;
import com.xanadutec.coreflex.model.Status;
import com.xanadutec.coreflex.model.UserModel;


@Repository("DealerReportDao")

@Transactional
public class DealerReportDaoImpl implements DealerReportDao {
	@Autowired
	private SessionFactory sessionFactory;
	@Override
	public List<DealerReportModel> getDealerDetailsByDate(Date fromDate, Date toDate) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Dealer.class);
		criteria.add(Restrictions.between("regDate", fromDate, toDate));
		criteria.createCriteria("userModel", "u");
		criteria.createCriteria("mainDealer", "mainDealer");
		criteria.setProjection(Projections.projectionList()
				.add(Projections.property("u.userName"), "username")
				.add(Projections.property("id"), "dealerId")
				.add(Projections.property("u.password"), "password")
				.add(Projections.property("mobileNum"), "contactNo")
				.add(Projections.property("fullName"), "fullName")
				.add(Projections.property("email"), "email")
				.add(Projections.property("branchName"),"branch")
				.add(Projections.property("dealecode"),"dealerCode")
				.add(Projections.property("mainDealer.firstName"),"mainDealer")
				.add(Projections.property("discount"),"discount")
				.add(Projections.property("officeNo"),"officeNo")
				.add(Projections.property("address"),"address")
				.add(Projections.property("pincode"),"pincode")
				.add(Projections.property("regDate"),"regDate"))
				.setResultTransformer(Transformers.aliasToBean(DealerReportModel.class));
		criteria.addOrder(Order.desc("regDate"));
		List<DealerReportModel> list = criteria.list();
		return list;
	}
	
	@Override
	public List<DealerReportModel> getDealerRevenueDetails(int dealerId , String packageName) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Kit_Upgrade_Or_Reg_History_Details.class);
		criteria.createCriteria("Kit_id", "kit");
		criteria.createCriteria("kit.dealer", "dealer");
		criteria.add( Restrictions.eq("dealer.id", dealerId));
		criteria.add( Restrictions.eq("kit.package_id", packageName));
		criteria.setProjection(Projections.projectionList()
				.add(Projections.property("dealer.id"), "dealerId")
				.add(Projections.property("dealer.fullName"), "fullName")
				.add(Projections.property("kit.kit_username"),"kituserName")
				.add(Projections.property("date"),"revenueDate")
				.add(Projections.property("kit.package_id"),"packageName"))
				.setResultTransformer(Transformers.aliasToBean(DealerReportModel.class));
		List<DealerReportModel> list = criteria.list();
		return list;
	}
	@Override
	public List<DealerReportModel> getDealerKitDetails(int dealerId) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(KitDealerAssociation.class);
		criteria.createCriteria("dealer", "d");
		criteria.createCriteria("pack", "pkgName");
		criteria.add( Restrictions.eq("d.id", dealerId));
		criteria.setProjection(Projections.projectionList()
				.add(Projections.property("d.id"), "dealerId")
				.add(Projections.property("d.fullName"), "fullName")
				.add(Projections.property("pkgName.package_Name"),"packageName")
				.add(Projections.property("totalKit"),"totalKit")
				.add(Projections.property("usedKit"),"usedKit")
				.add(Projections.property("returnKit"),"returnKit")
				.add(Projections.property("remKit"),"remainingKit"))
				.setResultTransformer(Transformers.aliasToBean(DealerReportModel.class));
		List<DealerReportModel> list = criteria.list();
		return list;
	}

	@Override
	public List<DealerReportModel> getSubDealerDetailsByDate(Date fromDate, Date toDate,UserModel adminUser) 
	{
		Status status = new Status();
		status.setStatus_id(1);

		Status status1 = new Status();
		status1.setStatus_id(3);
		
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Dealer.class);
		//criteria.add(Restrictions.between("regDate", fromDate, toDate));
		Disjunction or = Restrictions.disjunction();
		or.add(Restrictions.isNull("status"));
		or.add(Restrictions.eq("status", status));
		or.add(Restrictions.eq("status", status1));
		criteria.add(Restrictions.eq("mainDealer", adminUser));
		criteria.add(or);
		criteria.createCriteria("userModel", "u");
		criteria.setProjection(Projections.projectionList()
				.add(Projections.property("u.userName"), "username")
				.add(Projections.property("id"), "dealerId")
				.add(Projections.property("u.password"), "password")
				.add(Projections.property("mobileNum"), "contactNo")
				.add(Projections.property("fullName"), "fullName")
				.add(Projections.property("email"), "email")
				.add(Projections.property("branchName"),"branch")
				.add(Projections.property("dealecode"),"dealerCode")
				.add(Projections.property("discount"),"discount")
				.add(Projections.property("officeNo"),"officeNo")
				.add(Projections.property("address"),"address")
				.add(Projections.property("pincode"),"pincode")
				.add(Projections.property("regDate"),"regDate"))
				.setResultTransformer(Transformers.aliasToBean(DealerReportModel.class));
		//criteria.addOrder(Order.desc("regDate"));
		List<DealerReportModel> list = criteria.list();
		return list;
	}
}
