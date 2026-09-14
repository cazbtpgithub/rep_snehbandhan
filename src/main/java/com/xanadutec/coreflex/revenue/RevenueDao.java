package com.xanadutec.coreflex.revenue;

import java.util.Date;
import java.util.List;

import com.xanadutec.coreflex.model.AdminUpgradedUser;
import com.xanadutec.coreflex.model.Chat;
import com.xanadutec.coreflex.model.Dealer;
import com.xanadutec.coreflex.model.Kit_Upgrade_Or_Reg_Details;
import com.xanadutec.coreflex.model.Kit_Upgrade_Or_Reg_History_Details;
import com.xanadutec.coreflex.model.Package;
import com.xanadutec.coreflex.model.UserModel;

public interface RevenueDao {
	
	
	public List<Date> getUniqueDateList();
	public List<Kit_Upgrade_Or_Reg_History_Details> getListOfRegHistoryByDate(Date date);
	public List<Kit_Upgrade_Or_Reg_History_Details> getListOfRegHistoryByDateAndPackage(Date date,Package pack);
	public List<AdminUpgradedUser> getListOfUpgradedUserByDate(Date fdate,Date tdate);
	public List<AdminUpgradedUser> getListOnlineUpgradedUser();
	
	
	public List<Kit_Upgrade_Or_Reg_History_Details> getListOfRegHistoryByDateAndDealer(Date date,Dealer dealer);
	public List<Kit_Upgrade_Or_Reg_History_Details> getListOfRegHistoryByDateAndPackageAndDealer(Date date,Package pack,Dealer dealer);
	public List<Kit_Upgrade_Or_Reg_History_Details> getListOfRegHistoryByDateRange(Date fdate, Date tdate);
	
	
}
