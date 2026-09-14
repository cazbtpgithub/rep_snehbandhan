package com.xanadutec.coreflex.employee;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xanadutec.coreflex.model.AdminUserModel;
import com.xanadutec.coreflex.model.EmployeeRegistrationDetails;
import com.xanadutec.coreflex.model.Kit_Upgrade_Or_Reg_History_Details;
import com.xanadutec.coreflex.model.Package;
import com.xanadutec.coreflex.model.PermissionType;
import com.xanadutec.coreflex.model.UserModel;
import com.xanadutec.coreflex.model.UserModelDetails;
import com.xanadutec.coreflex.model.UserPermissionAssociation;

@Service("EmployeeService")
public class EmployeeServiceImpl implements EmployeeService
{
   @Autowired
   EmployeeDao employeeDao;
	@Override
	public void saveEmployeeRegistrationDetails(EmployeeRegistrationDetails employeeRegistrationDetailsObject) 
	{
		employeeDao.saveEmployeeRegistrationDetails(employeeRegistrationDetailsObject);		
	}
	@Override
	public List<EmployeeRegistrationDetails> getAllEmployeeRegistrationDetails() 
	{
		return employeeDao.getAllEmployeeRegistrationDetails();
	}
	@Override
	public void savePermissionType(PermissionType permissionTypeObject) 
	{
        employeeDao.savePermissionType(permissionTypeObject);	
	}
	@Override
	public List<PermissionType> getAllPermissionTypes() 
	{
		return employeeDao.getAllPermissionTypes();
	}
	@Override
	public EmployeeRegistrationDetails getEmployeeDetailsById(int emp_id) 
	{
		return employeeDao.getEmployeeDetailsById(emp_id);
	}
	@Override
	public List<UserPermissionAssociation> getUserPermissionAssociationByEmployeeId(Long employee_ID) 
	{
		return employeeDao.getUserPermissionAssociationByEmployeeId(employee_ID);
	}
	@Override
	public void deleteUserPermissionAssociationByEmployeeObject(
			UserModel userModel) 
	{
		 employeeDao.deleteUserPermissionAssociationByEmployeeObject(userModel);
	}
	@Override
	public void saveUserPermissionAssociation(UserPermissionAssociation userPermissionAssociation) 
	{
		employeeDao.saveUserPermissionAssociation(userPermissionAssociation);
	}
	@Override
	public List<UserPermissionAssociation> getPerticularUserPermissionAssociationByUserObject(UserModel userModel)
	{
		return employeeDao.getPerticularUserPermissionAssociationByUserObject(userModel);
	}
	@Override
	public void updateUserDetails(UserModel userModelObject) 
	{
		employeeDao.updateUserDetails(userModelObject);
	}
	@Override
	public UserModel getUserModel(EmployeeRegistrationDetails employee_RegistrationDetails_Obj)
	{
		return employeeDao.getUserModel(employee_RegistrationDetails_Obj);
	}
	@Override
	public void updateKitByEmployee(String kitUsername, String kitPassword, UserModel user)
	{
          employeeDao.updateKitByEmployee(kitPassword,kitUsername,user);		
	}
	@Override
	public void updateUserModelDetailsWithEmployee(UserModelDetails userModelDeails, UserModel userModel)
	{
		employeeDao.updateUserModelDetailsWithEmployee(userModelDeails,userModel);
	}
	@Override
	public List<Kit_Upgrade_Or_Reg_History_Details> getListOfRegHistoryByDateAndEmployee(Date date,
			EmployeeRegistrationDetails emp_Obj)
	{
		return employeeDao.getListOfRegHistoryByDateAndEmployee(date,emp_Obj);
	}
	@Override
	public List<Kit_Upgrade_Or_Reg_History_Details> getListOfRegHistoryByDateAndPackageAndEmployee(Date date,
			Package pack, EmployeeRegistrationDetails emp_Obj) 
	{
		return employeeDao.getListOfRegHistoryByDateAndPackageAndEmployee(date,emp_Obj,pack);
	}
	@Override
	public List<AdminUserModel> getListOfUserModelDetailsForEmployeeByEmployee(EmployeeRegistrationDetails emp_Obj) 
	{
		return employeeDao.getListOfUserModelDetailsForEmployeeByEmployee(emp_Obj);
	}
	@Override
	public List<EmployeeRegistrationDetails> getEmployeeDetailsByDate(
			Date fromDate, Date toDate) {
		return employeeDao.getEmployeeDetailsByDate(fromDate,toDate);
	}
	@Override
	public EmployeeRegistrationDetails getEmployeeDetailsByUserObject(
			UserModel userModel) 
	{
		return employeeDao.getEmployeeDetailsByUserObject(userModel);
	}
	
	
}
