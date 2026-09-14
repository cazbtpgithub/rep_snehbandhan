package com.xanadutec.coreflex.revenue;

import java.util.Collections;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xanadutec.coreflex.model.AdminUpgradedUser;
import com.xanadutec.coreflex.model.Chat;
import com.xanadutec.coreflex.model.Dealer;
import com.xanadutec.coreflex.model.Kit_Upgrade_Or_Reg_Details;
import com.xanadutec.coreflex.model.Kit_Upgrade_Or_Reg_History_Details;
import com.xanadutec.coreflex.model.Package;
import com.xanadutec.coreflex.model.UserModel;

@Repository("RevenueDao")
@Transactional
public class RevenueDaoImpl implements RevenueDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Date> getUniqueDateList() {

		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Kit_Upgrade_Or_Reg_History_Details.class);
		criteria.setProjection(Projections.distinct(Projections.property("date")));
		List<Date> lst = criteria.list();
		return lst;
	}

	@Override
	public List<Kit_Upgrade_Or_Reg_History_Details> getListOfRegHistoryByDate(Date date) {

		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Kit_Upgrade_Or_Reg_History_Details.class);
		criteria.add(Restrictions.eq("date", date));
		List<Kit_Upgrade_Or_Reg_History_Details> list = criteria.list();
		return list;
	}

	@Override
	public List<Kit_Upgrade_Or_Reg_History_Details> getListOfRegHistoryByDateAndPackage(Date date, Package pack) {

		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Kit_Upgrade_Or_Reg_History_Details.class);
		criteria.createCriteria("Kit_id", "feed");
		criteria.add(Restrictions.eq("date", date));
		criteria.add(Restrictions.eq("feed.package_id", pack));
		List<Kit_Upgrade_Or_Reg_History_Details> list = criteria.list();
		return list;

	}

	@Override
	public List<AdminUpgradedUser> getListOfUpgradedUserByDate(Date fdate, Date tdate) {

		try {

			Session session = sessionFactory.getCurrentSession();
			Criteria criteri = session.createCriteria(Kit_Upgrade_Or_Reg_Details.class);
			criteri.createCriteria("userModel", "u");
			criteri.createCriteria("Kit_id", "k");
			criteri.createCriteria("k.package_id", "p");
			criteri.setProjection(Projections.projectionList().add(Projections.property("u.userId"), "userId").add(Projections.property("u.firstName"), "name")
					.add(Projections.property("k.kit_username"), "kit")
					.add(Projections.property("registrationDate"), "registrationDate")
					.add(Projections.property("p.package_Name"), "packageName"))
					.setResultTransformer(Transformers.aliasToBean(AdminUpgradedUser.class));
			criteri.add(Restrictions.between("registrationDate", fdate, tdate));
			criteri.addOrder(Order.desc("registrationDate"));
			List<AdminUpgradedUser> list = criteri.list();

			return list;
		} catch (Exception e) {
			//
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public List<AdminUpgradedUser> getListOnlineUpgradedUser() {

		try {

			Session session = sessionFactory.getCurrentSession();

			Criteria criteri = session.createCriteria(Kit_Upgrade_Or_Reg_Details.class);
			criteri.createCriteria("userModel", "u");
			criteri.createCriteria("Kit_id", "k");
			criteri.createCriteria("k.package_id", "p");
			criteri.setProjection(Projections.projectionList().add(Projections.property("u.userId"), "userId").add(Projections.property("u.firstName"), "name")
					.add(Projections.property("k.kit_username"), "kit")
					.add(Projections.property("registrationDate"), "registrationDate")
					.add(Projections.property("p.package_Name"), "packageName"))
					.setResultTransformer(Transformers.aliasToBean(AdminUpgradedUser.class));
			Disjunction or = Restrictions.disjunction();
			or.add(Restrictions.like("k.kit_username", "1%"));
			or.add(Restrictions.like("k.kit_username", "2%"));
			or.add(Restrictions.like("k.kit_username", "3%"));
			or.add(Restrictions.like("k.kit_username", "4%"));
			or.add(Restrictions.like("k.kit_username", "5%"));
			or.add(Restrictions.like("k.kit_username", "6%"));
			or.add(Restrictions.like("k.kit_username", "7%"));
			or.add(Restrictions.like("k.kit_username", "8%"));
			or.add(Restrictions.like("k.kit_username", "9%"));
			criteri.add(or);

			criteri.addOrder(Order.desc("registrationDate"));
			List<AdminUpgradedUser> list = criteri.list();

			return list;
		} catch (Exception e) {
			//
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public List<Kit_Upgrade_Or_Reg_History_Details> getListOfRegHistoryByDateAndDealer(Date date, Dealer dealer) {
	
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Kit_Upgrade_Or_Reg_History_Details.class);
		criteria.createCriteria("Kit_id", "feed");
		criteria.add(Restrictions.eq("date", date));
		criteria.add(Restrictions.eq("feed.dealer", dealer));
		List<Kit_Upgrade_Or_Reg_History_Details> list = criteria.list();   
		return list;
	}

	@Override
	public List<Kit_Upgrade_Or_Reg_History_Details> getListOfRegHistoryByDateAndPackageAndDealer(Date date,
			Package pack, Dealer dealer) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Kit_Upgrade_Or_Reg_History_Details.class);
		criteria.createCriteria("Kit_id", "feed");
		criteria.add(Restrictions.eq("date", date));
		criteria.add(Restrictions.eq("feed.package_id", pack));
		criteria.add(Restrictions.eq("feed.dealer", dealer));
		List<Kit_Upgrade_Or_Reg_History_Details> list = criteria.list();
		return list;
	}

	@Override
	public List<Kit_Upgrade_Or_Reg_History_Details> getListOfRegHistoryByDateRange(Date fdate, Date tdate) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Kit_Upgrade_Or_Reg_History_Details.class);
		criteria.add(Restrictions.between("date", fdate, tdate));
		criteria.addOrder(Order.desc("date"));
		List<Kit_Upgrade_Or_Reg_History_Details> list = criteria.list();
		return list;
	}

}
