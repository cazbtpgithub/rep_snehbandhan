package com.xanadutec.coreflex.employee;

import java.util.Date;
import java.util.List;

import com.xanadutec.coreflex.model.AdminUserModel;
import com.xanadutec.coreflex.model.EmployeeRegistrationDetails;
import com.xanadutec.coreflex.model.Kit_Upgrade_Or_Reg_History_Details;
import com.xanadutec.coreflex.model.Package;
import com.xanadutec.coreflex.model.PermissionType;
import com.xanadutec.coreflex.model.UserModel;
import com.xanadutec.coreflex.model.UserModelDetails;
import com.xanadutec.coreflex.model.UserPermissionAssociation;

public interface EmployeeService 
{

	void saveEmployeeRegistrationDetails(EmployeeRegistrationDetails employeeRegistrationDetailsObject);

	List<EmployeeRegistrationDetails> getAllEmployeeRegistrationDetails();

	void savePermissionType(PermissionType permissionTypeObject);

	List<PermissionType> getAllPermissionTypes();

	EmployeeRegistrationDetails getEmployeeDetailsById(int emp_id);


	List<UserPermissionAssociation> getUserPermissionAssociationByEmployeeId(Long employee_ID);

	void deleteUserPermissionAssociationByEmployeeObject(UserModel userModel);

	void saveUserPermissionAssociation(UserPermissionAssociation userPermissionAssociation);

	List<UserPermissionAssociation> getPerticularUserPermissionAssociationByUserObject(UserModel userModel);

	void updateUserDetails(UserModel userModelObject);

	UserModel getUserModel(EmployeeRegistrationDetails employee_RegistrationDetails_Obj);

	void updateKitByEmployee(String kitUsername, String kitPassword, UserModel user);

	void updateUserModelDetailsWithEmployee(UserModelDetails userModelDeails, UserModel userModel);

	List<Kit_Upgrade_Or_Reg_History_Details> getListOfRegHistoryByDateAndEmployee(Date date,
			EmployeeRegistrationDetails emp_Obj);

	List<Kit_Upgrade_Or_Reg_History_Details> getListOfRegHistoryByDateAndPackageAndEmployee(Date date, Package pack,
			EmployeeRegistrationDetails emp_Obj);

	List<AdminUserModel> getListOfUserModelDetailsForEmployeeByEmployee(EmployeeRegistrationDetails emp_Obj);

	List<EmployeeRegistrationDetails> getEmployeeDetailsByDate(Date fromDate,Date toDate);

	EmployeeRegistrationDetails getEmployeeDetailsByUserObject(
			UserModel userModel);

	

	
}
