package com.xanadutec.coreflex.dealer;

import java.util.ArrayList;
import org.springframework.transaction.annotation.Transactional;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xanadutec.coreflex.model.Dealer;
import com.xanadutec.coreflex.model.DealerSellKitDetails;
import com.xanadutec.coreflex.model.Discount;
import com.xanadutec.coreflex.model.KitDealerAssociation;
import com.xanadutec.coreflex.model.Status;
import com.xanadutec.coreflex.model.UserModel;

@Repository("DealerDao")
@Transactional
public class DealerDaoImpl implements DealerDao {

	@Autowired
	SessionFactory sessionFactory;
	@Override
	public void saveDealer(Dealer dealer) {
		try {
			org.hibernate.Session session = sessionFactory.getCurrentSession();
			session.saveOrUpdate(dealer);
			} catch (Exception e) {
		}
	}

	@Override
	public List<Dealer> getDealerList() {
		Status status = new Status();
		status.setStatus_id(1);
		Status status1 = new Status();
		status1.setStatus_id(3);
		org.hibernate.Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Dealer.class);
		criteria.addOrder(Order.desc("regDate"));
		Disjunction or = Restrictions.disjunction();
		or.add(Restrictions.isNull("status"));
		or.add(Restrictions.eq("status", status));
		or.add(Restrictions.eq("status", status1));
		criteria.add(or);
		List<Dealer> list = criteria.list();
		return list;
	}

	@Override
	public Dealer getDealerByEmail(String email) {
		Dealer userModel = null;
		try {
			org.hibernate.Session session = sessionFactory.getCurrentSession();
			Criteria criteria = session.createCriteria(Dealer.class);
			criteria.add(Restrictions.eq("email", email));
			userModel = (Dealer) criteria.uniqueResult();
			} catch (Exception e) {
		}
		return userModel;
	}

	@Override
	public Dealer getDealerByMobile(String Mobile) {
		Dealer userModel = null;
		try {
			org.hibernate.Session session = sessionFactory.getCurrentSession();
			Criteria criteria = session.createCriteria(Dealer.class);
			criteria.add(Restrictions.eq("mobileNum", Mobile));
			userModel = (Dealer) criteria.uniqueResult();
			} catch (Exception e) {
		}
		return userModel;
	}

	@Override
	public Dealer getDealerById(String id) {
		Dealer userModel = null;
		try {
			org.hibernate.Session session = sessionFactory.getCurrentSession();
			Criteria criteria = session.createCriteria(Dealer.class);
			criteria.add(Restrictions.eq("id", Integer.parseInt(id)));
			userModel = (Dealer) criteria.uniqueResult();
			} catch (Exception e) {
		}
		return userModel;
	}

	@Override
	public Dealer getDealerUserModel(UserModel userModel) {
		Dealer userModel1 = null;
		try {
			org.hibernate.Session session = sessionFactory.getCurrentSession();
			Criteria criteria = session.createCriteria(Dealer.class);
			criteria.add(Restrictions.eq("userModel", userModel));
			userModel1 = (Dealer) criteria.uniqueResult();
			} catch (Exception e) {
		}
		return userModel1;
	}

	@Override
	public void deleteDealerByDealerId(String dealerId) {
		try {
			Status status = new Status();
			status.setStatus_id(2);
			org.hibernate.Session session = sessionFactory.getCurrentSession();
			org.hibernate.Query query = session.createQuery("UPDATE Dealer SET status=:status WHERE id=:id");
			query.setParameter("status", status);
			query.setParameter("id", Integer.parseInt(dealerId));
			query.executeUpdate();
			} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Dealer> getSubDealerByMainDealer(UserModel userModel) {
		Status status = new Status();
		status.setStatus_id(1);
		Status status1 = new Status();
		status1.setStatus_id(3);
		org.hibernate.Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Dealer.class);
		Disjunction or = Restrictions.disjunction();
		or.add(Restrictions.isNull("status"));
		or.add(Restrictions.eq("status", status));
		or.add(Restrictions.eq("status", status1));
		criteria.add(Restrictions.eq("mainDealer", userModel));
		criteria.add(or);
		List<Dealer> list = criteria.list();
		return list;
	}

	@Override
	public int getMaxIdOfDealer() {
		try {
			org.hibernate.Session session = sessionFactory.getCurrentSession();
			Criteria criteria = session.createCriteria(Dealer.class);
			criteria.setProjection(Projections.max("id"));
			Integer i = (Integer) criteria.uniqueResult();
			return i;
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public void disableDealerById(String dealerId) {
		try {
			Status status = new Status();
			status.setStatus_id(3);
			org.hibernate.Session session = sessionFactory.getCurrentSession();
			org.hibernate.Query query = session.createQuery("UPDATE Dealer SET status=:status WHERE id=:id");
			query.setParameter("status", status);
			query.setParameter("id", Integer.parseInt(dealerId));
			query.executeUpdate();
			} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void unableDealerById(String dealerId) {
		try {
			Status status = new Status();
			status.setStatus_id(1);
			org.hibernate.Session session = sessionFactory.getCurrentSession();
			org.hibernate.Query query = session.createQuery("UPDATE Dealer SET status=:status WHERE id=:id");
			query.setParameter("status", status);
			query.setParameter("id", Integer.parseInt(dealerId));
			query.executeUpdate();
			} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public int getdealerDiscountbyDealer(Dealer dealer) {
		try {
			org.hibernate.Session session = sessionFactory.getCurrentSession();
			Criteria criteria = session.createCriteria(Dealer.class);
			criteria.add(Restrictions.eq("id", dealer.getId()));
			Dealer dealer1 = (Dealer) criteria.uniqueResult();
			return dealer1.getDiscount();
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public void saveDealerSellKitDetails(DealerSellKitDetails dealerSellKitDetails) {
		org.hibernate.Session session = sessionFactory.getCurrentSession();
		session.save(dealerSellKitDetails);
		}

	@Override
	public List<Discount> getDealerWiseDiscount(List<com.xanadutec.coreflex.model.Package> packageList, Dealer dealer) {
		List<Discount> discountDetailsList = new ArrayList<>();
		try {
			org.hibernate.Session session = sessionFactory.getCurrentSession();
			for (com.xanadutec.coreflex.model.Package package1 : packageList) {
				Criteria criteria = session.createCriteria(DealerSellKitDetails.class);
				ProjectionList p1 = Projections.projectionList();
				p1.add(Projections.property("priceAmount"));
				p1.add(Projections.property("discountAmount"));
				criteria.add(Restrictions.eq("packageId", package1));
				criteria.add(Restrictions.eq("dealer", dealer));
				criteria.setProjection(p1);
				Discount discount = new Discount();
				List l = criteria.list();
				Iterator it = l.iterator();
				long totAmount = 0;
				Float disTot = 0.0f;
				while (it.hasNext()) {
					Object ob[] = (Object[]) it.next();
					totAmount = totAmount + (long) ob[0];
					disTot = disTot + (float) ob[1];
				}
				discount.setPackageName(package1.getPackage_Name());
				discount.setDiscountAmount(disTot);
				discount.setTotalAmount(totAmount);
				discountDetailsList.add(discount);
			}
			return discountDetailsList;
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<KitDealerAssociation> getSubDealerSaleKitDetailsBySubDealer(List<Dealer> list)
	{
		try {
			Session session =sessionFactory.getCurrentSession();
			Criteria criteria =session.createCriteria(KitDealerAssociation.class);
			criteria.add(Restrictions.in("dealer", list));
			List<KitDealerAssociation> kitDealerAssociationsList = criteria.list();
			return kitDealerAssociationsList;
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public long getTotalDealerCount() 
	{
		try {
			Session session =sessionFactory.getCurrentSession();
			String sql = "SELECT count(*) FROM Dealer";
			Query query = session.createQuery(sql);
			List<Object> list=query.list();
			Long  val=(Long) list.get(0);
			return val;
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public UserModel getUserModelByDealerId(String dealerId) {
		Dealer selectedDealer =  null;
		try {
			org.hibernate.Session session = sessionFactory.getCurrentSession();
			Criteria criteria = session.createCriteria(Dealer.class);
			criteria.add(Restrictions.eq("id", Integer.parseInt(dealerId)));
			selectedDealer = (Dealer) criteria.uniqueResult();
			return selectedDealer.getUserModel();
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		return selectedDealer.getUserModel();
	}
	
	@Override
	public List<Dealer> getAllSubDealerByMainDealer(UserModel userModel) {
		Status status = new Status();
		status.setStatus_id(1);
		Status status1 = new Status();
		status1.setStatus_id(3);
		org.hibernate.Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Dealer.class);
		Disjunction or = Restrictions.disjunction();
		criteria.add(Restrictions.eq("mainDealer", userModel));
		criteria.add(or);
		List<Dealer> list = criteria.list();
		return list;
	}
}
