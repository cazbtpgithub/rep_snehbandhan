package com.xanadutec.coreflex.Kit;

import java.util.Date;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xanadutec.coreflex.model.Dealer;
import com.xanadutec.coreflex.model.Kit;
import com.xanadutec.coreflex.model.KitDealerAssociation;
import com.xanadutec.coreflex.model.Kit_Upgrade_Or_Reg_Details;
import com.xanadutec.coreflex.model.Kit_Upgrade_Or_Reg_History_Details;
import com.xanadutec.coreflex.model.Package;
import com.xanadutec.coreflex.model.UserModel;

@Repository("KitDao")
@Transactional
public class KitDaoImpl implements KitDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Kit findKitByKitUserNamePassword(String KitUserName, String KitPassword)
	{
		/*
		Session session=sessionFactory.getCurrentSession();
		String hql = "FROM Kit WHERE kit_username=:kit_username  AND kit_password=:kit_password";
		Query query = session.createQuery(hql);
		query.setParameter("kit_username", KitUserName);
		query.setParameter("kit_password", KitPassword);
		Kit kitObject = (Kit) query.uniqueResult();
	//	query.executeUpdate();
		return kitObject;
		
		*/
		Session session = sessionFactory.getCurrentSession();
		try{
			Criteria criteria = session.createCriteria(Kit.class);
			criteria.add(Restrictions.eq("kit_username", KitUserName));
			criteria.add(Restrictions.eq("kit_password", KitPassword));
			Kit kit = (Kit) criteria.list().get(0);
			return kit;	
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			}
		return null;
	}

	@Override
	public HashMap<String, List<Object[]>> getReportByYear(String year) {
		int yearVal=Integer.parseInt(year);
		Session session = sessionFactory.getCurrentSession();
		String sql = "select count(*),month(registrationDate)from UserModelDetails where year(registrationDate)=:year  group by month(registrationDate)";
		Query query = session.createQuery(sql);
		query.setParameter("year", Integer.parseInt(year));
        List<Object[]> dateList = query.list();
    	HashMap<String, List<Object[]>> result = new HashMap<String, List<Object[]>>();
		result.put("open", dateList);
		return result;
		
	}
	@Override
	public void updateKitStatus(Kit kit) {
		Session session = sessionFactory.getCurrentSession();
		try{
			session.update(kit);	
			}catch(Exception e){
		}finally {
			}
	}

	@Override
	public Kit_Upgrade_Or_Reg_History_Details saveKit_Upgrade_Or_Reg_History_Details(
			Kit_Upgrade_Or_Reg_History_Details history_Details) {
		Session session = sessionFactory.getCurrentSession();
		try{
			session.save(history_Details);	
			}catch(Exception e){
		}finally {
			}
		return history_Details;
	}

	@Override
	public Kit_Upgrade_Or_Reg_Details getKitUpgradeAndRegistationDetailByUserModel(UserModel userModel) {
		try {
			Session session=sessionFactory.getCurrentSession();
			Criteria criteria=session.createCriteria(Kit_Upgrade_Or_Reg_Details.class);
			criteria.add(Restrictions.eq("userModel", userModel));
			Kit_Upgrade_Or_Reg_Details kit_upgrade_Or_reg_details=(Kit_Upgrade_Or_Reg_Details) criteria.uniqueResult();
			return kit_upgrade_Or_reg_details;
		} catch (Exception e) {
		}
		return null;
	}

	@Override
	public void deletKitByUnameAndPassword(String kitusername, String password) {
		try {
			Session session = sessionFactory.getCurrentSession();
			Query query = session.createQuery("DELETE FROM Kit WHERE kit_username=:kit_username  AND kit_password=:kit_password");
			query.setParameter("kit_username", kitusername);
			query.setParameter("kit_password", password);
			query.executeUpdate();
			} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updatekitDetails(String packageId, String kitusername, String password, String kitid) {
try {
			Package pack=new Package();
			pack.setPackage_id(Integer.parseInt(packageId));
			Session session = sessionFactory.getCurrentSession();
			Query query = session.createQuery("UPDATE Kit SET kit_username=:kit_username,kit_password=:kit_password,package_id=:package_id  WHERE kit_id=:kit_id");
			query.setParameter("kit_username", kitusername);
			query.setParameter("kit_password", password);
			query.setParameter("package_id", pack);
			query.setParameter("kit_id", Long.parseLong(kitid));
			query.executeUpdate();
			} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Kit getkitByKitId(Long kit_id) {
		Session session = sessionFactory.getCurrentSession();
		try{
			Criteria criteria = session.createCriteria(Kit.class);
			criteria.add(Restrictions.eq("kit_id", kit_id));
			Kit kit = (Kit) criteria.list().get(0);
			return kit;	
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			}
		return null;
	}

	@Override
	public HashMap<String, List<Object[]>> getReportByYearAndDealer(String year, Dealer dealer) {
		int yearVal=Integer.parseInt(year);
		Session session = sessionFactory.getCurrentSession();
		String sql = "select count(*),month(registrationDate)from UserModelDetails where year(registrationDate)=:year and dealer =:dealer  group by month(registrationDate)";
		Query query = session.createQuery(sql);
		query.setParameter("year", Integer.parseInt(year));
		query.setParameter("dealer", dealer);
        List<Object[]> dateList = query.list();
    	HashMap<String, List<Object[]>> result = new HashMap<String, List<Object[]>>();
		result.put("open", dateList);
		return result;
	}

	@Override
	public String assingeKitTODealer(Package pack, Dealer dealer, Integer kitCount) {
		KitDealerAssociation kitDealer=null;
		String result=null;
		try {
			Session session=sessionFactory.getCurrentSession();
			Criteria criteria=session.createCriteria(KitDealerAssociation.class);
			criteria.add(Restrictions.eq("pack", pack));
			criteria.add(Restrictions.eq("dealer", dealer));
			try {
				kitDealer=(KitDealerAssociation) criteria.uniqueResult();
			} catch (Exception e) {
			}
			if(kitDealer !=null)
			{	
				try {
					int totalCount=kitDealer.getTotalKit()+kitCount;
					int remTot=(totalCount - kitDealer.getReturnKit());	
					Query query = session.createQuery("UPDATE KitDealerAssociation SET totalKit=:totalKit,remKit=:remKit WHERE dealer=:dealer AND pack=:pack ");
					query.setParameter("dealer", dealer);
					query.setParameter("pack", pack);
					query.setParameter("totalKit", totalCount);
					query.setParameter("remKit", remTot);
					query.executeUpdate();
					result="success";
					} catch (Exception e) {
						e.printStackTrace();
				}		
			}
			else
			{	
				KitDealerAssociation association=new KitDealerAssociation();
				association.setDealer(dealer);
				association.setPack(pack);
				association.setTotalKit(kitCount);
				association.setUsedKit(0);
				association.setReturnKit(0);
				association.setRemKit(kitCount);
				session.save(association);
				result="success";
				}
			
		} catch (Exception e) {
		}
		
		return result;
	}

	@Override
	public List<KitDealerAssociation> getListOfKitdealerAssociation()
	{
		Session session=sessionFactory.getCurrentSession();
		Criteria criteria=session.createCriteria(KitDealerAssociation.class);
		List<KitDealerAssociation> list=criteria.list();
		return list;
	}

	@Override
	public String returnKitByDealerAndPackage(Package pack, Dealer dealer, Integer kitCount)
	{
		String result=null;
		try {
			Session session=sessionFactory.getCurrentSession();
			KitDealerAssociation kitDealer=null;
			Criteria criteria=session.createCriteria(KitDealerAssociation.class);
			criteria.add(Restrictions.eq("pack", pack));
			criteria.add(Restrictions.eq("dealer", dealer));
			try 
			{
				kitDealer=(KitDealerAssociation) criteria.uniqueResult();
			} catch (Exception e)
			{
				e.printStackTrace();
			}
			if(kitDealer !=null )
			{
				int totalKit=kitDealer.getTotalKit();
				int finalRetu=kitDealer.getReturnKit()+kitCount;
				if(totalKit >=finalRetu)
				{
					int finalRem=totalKit-finalRetu;
					Query query = session.createQuery("UPDATE KitDealerAssociation SET returnKit=:returnKit,remKit=:remKit WHERE dealer=:dealer AND pack=:pack ");
					query.setParameter("dealer", dealer);
					query.setParameter("pack", pack);
					query.setParameter("returnKit", finalRetu);  
					query.setParameter("remKit", finalRem);
					query.executeUpdate();
					result="success";
				}
				else
				{
					result="fail";
				}
				
			}
			else
			{
				result="fail";
			}
		} catch (Exception e) {
				e.printStackTrace();
		}
		return result;
	}

	@Override
	public HashMap<String, List<Object[]>> getReportByYearAndMonth(String year, String month) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "select count(*),day(registrationDate)from UserModelDetails where year(registrationDate)=:year and month(registrationDate)=:month  group by day(registrationDate) order by day(registrationDate)";
		Query query = session.createQuery(sql);
		query.setParameter("year", Integer.parseInt(year));
		query.setParameter("month", Integer.parseInt(month));
        List<Object[]> dateList = query.list();
    	HashMap<String, List<Object[]>> result = new HashMap<String, List<Object[]>>();
		result.put("open", dateList);
		return result;
	}

	@Override
	public void updateDealerOfKit(String kitusername, String kitPassword, Dealer dealer) {
		try {
				Session session = sessionFactory.getCurrentSession();
				Query query = session.createQuery("UPDATE Kit SET dealer=:dealer WHERE kit_username=:kit_username AND kit_password=:kit_password");
				query.setParameter("kit_username", kitusername);
				query.setParameter("kit_password", kitPassword);
				query.setParameter("dealer", dealer);
				query.executeUpdate();
				} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
