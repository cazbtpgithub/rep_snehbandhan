package com.xanadutec.coreflex.employee;

import java.util.Date;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xanadutec.coreflex.model.AdminUserModel;
import com.xanadutec.coreflex.model.EmployeeRegistrationDetails;
import com.xanadutec.coreflex.model.Kit_Upgrade_Or_Reg_History_Details;
import com.xanadutec.coreflex.model.Package;
import com.xanadutec.coreflex.model.PermissionType;
import com.xanadutec.coreflex.model.Status;
import com.xanadutec.coreflex.model.UserModel;
import com.xanadutec.coreflex.model.UserModelDetails;
import com.xanadutec.coreflex.model.UserPermissionAssociation;

@Repository("EmployeeDao")
@Transactional
public class EmployeeDaoImpl implements EmployeeDao
{
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void saveEmployeeRegistrationDetails(EmployeeRegistrationDetails employeeRegistrationDetailsObject) 
	{
		try {
			Session session =sessionFactory.getCurrentSession();
			session.saveOrUpdate(employeeRegistrationDetailsObject);
			} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public List<EmployeeRegistrationDetails> getAllEmployeeRegistrationDetails() 
	{
		try {
			Status status = new Status();
			status.setStatus_id(1);
			Status status1 = new Status();
			status1.setStatus_id(3);
			Session session =sessionFactory.getCurrentSession();
			Criteria criteria =session.createCriteria(EmployeeRegistrationDetails.class);
			Disjunction or = Restrictions.disjunction();
			or.add(Restrictions.isNull("status"));
			or.add(Restrictions.eq("status", status));
			or.add(Restrictions.eq("status", status1));
			criteria.add(or);		
			List<EmployeeRegistrationDetails> employeeRegistrationDetailsList = criteria.list();
			return employeeRegistrationDetailsList;
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void savePermissionType(PermissionType permissionTypeObject) 
	{
		try {
			Session session =sessionFactory.getCurrentSession();
			session.saveOrUpdate(permissionTypeObject);
			} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public List<PermissionType> getAllPermissionTypes() 
	{
		try {
			Session session =sessionFactory.getCurrentSession();
			Criteria criteria =session.createCriteria(PermissionType.class);
			List<PermissionType> permissionTypeList = criteria.list();
			return permissionTypeList;
		} catch (Exception e) 
		{
		}
		return null;
	}

	@Override
	public EmployeeRegistrationDetails getEmployeeDetailsById(int emp_id) 
	{
		try {
			Session session =sessionFactory.getCurrentSession();
			Criteria criteria =session.createCriteria(EmployeeRegistrationDetails.class);
			criteria.add(Restrictions.eq("emp_id", emp_id));
			EmployeeRegistrationDetails employeeDetailsObject = (EmployeeRegistrationDetails) criteria.uniqueResult();
			return employeeDetailsObject;
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<UserPermissionAssociation> getUserPermissionAssociationByEmployeeId(Long employee_ID)
	{
		try {
			Session session =sessionFactory.getCurrentSession();
			UserModel userModel=new UserModel();
			userModel.setUserId(employee_ID);
			Criteria criteria = session.createCriteria(UserPermissionAssociation.class);
			criteria.add(Restrictions.eq("userModel", userModel));
			List<UserPermissionAssociation> list = criteria.list();
			return list;		
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void deleteUserPermissionAssociationByEmployeeObject(
			UserModel userModel) 
	{
		try {
			Session session = sessionFactory.getCurrentSession();		
			String hql = "delete from UserPermissionAssociation where userModel= :userModel";
			Query query =session.createQuery(hql);
			query.setParameter("userModel", userModel);
			query.executeUpdate();
			} catch (Exception e) 
		{
			e.printStackTrace();
		}		
	}

	@Override
	public void saveUserPermissionAssociation(UserPermissionAssociation userPermissionAssociation) 
	{ 
		try {
			Session session = sessionFactory.getCurrentSession();
			session.saveOrUpdate(userPermissionAssociation);
			} catch (Exception e) 
		{
			e.printStackTrace();
		}

	}

	@Override
	public List<UserPermissionAssociation> getPerticularUserPermissionAssociationByUserObject(UserModel userModel)
	{
		try {
			Session session =sessionFactory.getCurrentSession();
			Criteria criteria = session.createCriteria(UserPermissionAssociation.class);
			criteria.add(Restrictions.eq("userModel", userModel));
			List<UserPermissionAssociation> userPermissionAsociationlist = criteria.list();
			return userPermissionAsociationlist;
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void updateUserDetails(UserModel userModelObject)
	{
		try {
			Session session =sessionFactory.getCurrentSession();
			session.saveOrUpdate(userModelObject);
			} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public UserModel getUserModel(EmployeeRegistrationDetails employee_RegistrationDetails_Obj) 
	{
		try {
			Session session =sessionFactory.getCurrentSession();
			Criteria criteria =session.createCriteria(UserModel.class);
			criteria.add(Restrictions.eq("userId", employee_RegistrationDetails_Obj.getUserModel().getUserId()));
			UserModel userModel = (UserModel) criteria.uniqueResult();
			return userModel;
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void updateKitByEmployee(String kitPassword, String kitUsername, UserModel user) 
	{
		EmployeeRegistrationDetails employeeDetailsObject = null;
		try {
			Session session = sessionFactory.getCurrentSession();
			Criteria criteria =session.createCriteria(EmployeeRegistrationDetails.class);
			criteria.add(Restrictions.eq("userModel", user));
			employeeDetailsObject = (EmployeeRegistrationDetails) criteria.uniqueResult();
			if(employeeDetailsObject != null)
			{
				Query query = session.createQuery("UPDATE Kit SET empId=:empId WHERE kit_username=:kit_username AND kit_password=:kit_password");
				query.setParameter("kit_username", kitUsername);
				query.setParameter("kit_password", kitPassword);
				query.setParameter("empId", employeeDetailsObject);
				query.executeUpdate();
				}

		} catch (Exception e) 
		{
			e.printStackTrace();
		}

	}

	@Override
	public void updateUserModelDetailsWithEmployee(UserModelDetails userModelDeails, UserModel userModel) 
	{
		EmployeeRegistrationDetails employeeDetailsObject = null;
		try {
			Session session = sessionFactory.getCurrentSession();
			Criteria criteria =session.createCriteria(EmployeeRegistrationDetails.class);
			criteria.add(Restrictions.eq("userModel", userModel));
			employeeDetailsObject = (EmployeeRegistrationDetails) criteria.uniqueResult();
			userModelDeails.setEmpId(employeeDetailsObject);
			if(employeeDetailsObject != null)
			{
				session.update(userModelDeails);
				}else{
				}
		} catch (Exception e) 
		{
			e.printStackTrace();
		}

	}

	@Override
	public List<Kit_Upgrade_Or_Reg_History_Details> getListOfRegHistoryByDateAndEmployee(Date date,
			EmployeeRegistrationDetails emp_Obj) 
			{
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Kit_Upgrade_Or_Reg_History_Details.class);
		criteria.createCriteria("Kit_id", "feed");
		criteria.add(Restrictions.eq("date", date));
		criteria.add(Restrictions.eq("feed.empId", emp_Obj));
		List<Kit_Upgrade_Or_Reg_History_Details> list = criteria.list();   
		return list;
			}

	@Override
	public List<Kit_Upgrade_Or_Reg_History_Details> getListOfRegHistoryByDateAndPackageAndEmployee(Date date,
			EmployeeRegistrationDetails emp_Obj,Package pack) 
			{
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Kit_Upgrade_Or_Reg_History_Details.class);
		criteria.createCriteria("Kit_id", "feed");
		criteria.add(Restrictions.eq("date", date));
		criteria.add(Restrictions.eq("feed.package_id", pack));
		criteria.add(Restrictions.eq("feed.empId", emp_Obj));
		List<Kit_Upgrade_Or_Reg_History_Details> list = criteria.list();
		return list;
			}

	@Override
	public List<AdminUserModel> getListOfUserModelDetailsForEmployeeByEmployee(EmployeeRegistrationDetails emp_Obj)
	{
		Status stat = new Status();
		stat.setStatus_id(1);
		Session session = sessionFactory.getCurrentSession();
		Criteria cr = session.createCriteria(UserModelDetails.class);
		cr.createCriteria("userModel", "u");
		cr.setProjection(Projections.projectionList().add(Projections.property("u.userId"), "userId")
				.add(Projections.property("u.firstName"), "firstName")
				.add(Projections.property("registrationDate"), "registrationDate")
				.add(Projections.property("dateOfBirth"), "dateOfBirth")
				.add(Projections.property("mobileNum"), "mobileNum")
				)
				.setResultTransformer(Transformers.aliasToBean(AdminUserModel.class));
		//deleted user disabled using following lines
		Disjunction or = Restrictions.disjunction();
		or.add(Restrictions.isNull("status"));
		or.add(Restrictions.eq("status", stat));
		cr.add(or);
		cr.add(Restrictions.eq("empId", emp_Obj));
		List<AdminUserModel> list = cr.list();
		return list;
	}

	@Override
	public List<EmployeeRegistrationDetails> getEmployeeDetailsByDate(Date fromDate, Date toDate)
	{
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(EmployeeRegistrationDetails.class);
		criteria.add(Restrictions.between("registrationDate", fromDate, toDate));
		criteria.setProjection(Projections.projectionList()
				.add(Projections.property("fullName"), "fullName")
				.add(Projections.property("userName"), "userName")
				.add(Projections.property("password"), "password")
				.add(Projections.property("mobileNo"), "mobileNo")
				.add(Projections.property("designation"), "designation")
				.add(Projections.property("currentSalary"), "currentSalary")
				.add(Projections.property("registrationDate"), "registrationDate")
				.add(Projections.property("city"),"city"))
				.setResultTransformer(Transformers.aliasToBean(EmployeeRegistrationDetails.class));
		criteria.addOrder(Order.desc("registrationDate"));
		List<EmployeeRegistrationDetails> list = criteria.list();
		return list;
	}

	@Override
	public EmployeeRegistrationDetails getEmployeeDetailsByUserObject(
			UserModel userModel) 
	{
		try {
			Session session =sessionFactory.getCurrentSession();
			Criteria criteria =session.createCriteria(EmployeeRegistrationDetails.class);
			criteria.add(Restrictions.eq("userModel", userModel));
			EmployeeRegistrationDetails empObject = (EmployeeRegistrationDetails) criteria.uniqueResult();
			return empObject;
		} catch (Exception e) 
		{
			e.printStackTrace();
		}	
		return null;
	}

}


