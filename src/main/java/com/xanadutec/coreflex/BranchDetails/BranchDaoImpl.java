package com.xanadutec.coreflex.BranchDetails;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;

import org.dom4j.Branch;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xanadutec.coreflex.model.BranchModel;

@Repository("BranchDao")
@Transactional
public class BranchDaoImpl implements BranchDao {

	
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public void saveBranch(BranchModel branch) {
		try {

			org.hibernate.Session session = sessionFactory.getCurrentSession();
			session.saveOrUpdate(branch);
			} catch (Exception e) {
		}

	}


	
	
	@Override
	public List<BranchModel> getBranchList() {
		
			try {
			
			Session session =sessionFactory.getCurrentSession();
			Criteria criteria =session.createCriteria(BranchModel.class);
			List<BranchModel> branchList = criteria.list();
			
			return branchList;
			
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}




	@Override
	public List<BranchModel> getBranchDetailsByBranchId(String branchId)
	{
		List<BranchModel> branchModelList = null;
		try {
			Session session = sessionFactory.getCurrentSession();
			Criteria criteria = session.createCriteria(BranchModel.class);
			criteria.add(Restrictions.eq("branchId", Integer.parseInt(branchId)));
			branchModelList = criteria.list();
			return branchModelList;
		} catch (Exception e) 
		{
			e.printStackTrace();
		}

		return branchModelList;

	}




	@Override
	public void deleteBranchDetailsByBranchID(int branchId) 
	{
		BranchModel branchModel = null;
		try {
			Session session = sessionFactory.getCurrentSession();
			branchModel = new BranchModel();
			branchModel.setBranchId(branchId);
			session.delete(branchModel);
			} catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
}
