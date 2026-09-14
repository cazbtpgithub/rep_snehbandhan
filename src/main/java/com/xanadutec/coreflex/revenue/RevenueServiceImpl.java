

package com.xanadutec.coreflex.revenue;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xanadutec.coreflex.model.AdminUpgradedUser;
import com.xanadutec.coreflex.model.Chat;
import com.xanadutec.coreflex.model.Dealer;
import com.xanadutec.coreflex.model.Kit_Upgrade_Or_Reg_Details;
import com.xanadutec.coreflex.model.Kit_Upgrade_Or_Reg_History_Details;
import com.xanadutec.coreflex.model.Package;
import com.xanadutec.coreflex.model.UserModel;

@Service("RevenueService")
public class RevenueServiceImpl  implements RevenueService{

	@Autowired
	RevenueDao revenuDao;
	@Override
	public List<Date> getUniqueDateList() {
		
		return revenuDao.getUniqueDateList();
	}
	@Override
	public List<Kit_Upgrade_Or_Reg_History_Details> getListOfRegHistoryByDate(Date date) {
		return revenuDao.getListOfRegHistoryByDate(date);
	}
	@Override
	public List<Kit_Upgrade_Or_Reg_History_Details> getListOfRegHistoryByDateAndPackage(Date date, Package pack) {
		return revenuDao.getListOfRegHistoryByDateAndPackage(date, pack);
	}
	@Override
	public List<AdminUpgradedUser> getListOfUpgradedUserByDate(Date fdate, Date tdate) {
		return revenuDao.getListOfUpgradedUserByDate(fdate, tdate);
	}
	@Override
	public List<AdminUpgradedUser> getListOnlineUpgradedUser() {
		return revenuDao.getListOnlineUpgradedUser();
	}
	@Override
	public List<Kit_Upgrade_Or_Reg_History_Details> getListOfRegHistoryByDateAndDealer(Date date, Dealer dealer) {
		return revenuDao.getListOfRegHistoryByDateAndDealer(date, dealer);
	}
	@Override
	public List<Kit_Upgrade_Or_Reg_History_Details> getListOfRegHistoryByDateAndPackageAndDealer(Date date, Package pack,
			Dealer dealer) {
		return revenuDao.getListOfRegHistoryByDateAndPackageAndDealer(date, pack, dealer);
	}
	
	@Override
	public List<Kit_Upgrade_Or_Reg_History_Details> getListOfRegHistoryByDateRange(Date fdate, Date tdate) {
		return revenuDao.getListOfRegHistoryByDateRange(fdate, tdate);
	}
	
}
