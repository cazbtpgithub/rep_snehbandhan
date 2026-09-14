package com.xanadutec.coreflex.Kit;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xanadutec.coreflex.model.Dealer;
import com.xanadutec.coreflex.model.Kit;
import com.xanadutec.coreflex.model.KitDealerAssociation;
import com.xanadutec.coreflex.model.Kit_Upgrade_Or_Reg_Details;
import com.xanadutec.coreflex.model.Kit_Upgrade_Or_Reg_History_Details;
import com.xanadutec.coreflex.model.Package;
import com.xanadutec.coreflex.model.UserModel;

@Service("KitService")
public class KitServiceImpl implements KitService{
	
	@Autowired
	private KitDao kitDao;
	public Kit findKitByKitUserNamePassword(String KitUserName ,String KitPassword){
		return kitDao.findKitByKitUserNamePassword(KitUserName, KitPassword);
		
	}
	@Override
	public HashMap<String, List<Object[]>> getReportByYear(String year) {
		return kitDao.getReportByYear(year);
	}
	@Override
	public void updateKitStatus(Kit kit) {
		kitDao.updateKitStatus(kit);		
	}
	@Override
	public Kit_Upgrade_Or_Reg_History_Details saveKit_Upgrade_Or_Reg_History_Details(
			Kit_Upgrade_Or_Reg_History_Details history_Details) {
		return kitDao.saveKit_Upgrade_Or_Reg_History_Details(history_Details);
	}
	@Override
	public Kit_Upgrade_Or_Reg_Details getKitUpgradeAndRegistationDetailByUserModel(UserModel userModel) {
		return kitDao.getKitUpgradeAndRegistationDetailByUserModel(userModel);
	}
	@Override
	public void deletKitByUnameAndPassword(String kitusername, String password) {
		kitDao.deletKitByUnameAndPassword(kitusername, password);
		
	}
	@Override
	public void updatekitDetails(String packageId, String kitusername, String password, String kitid) {
		kitDao.updatekitDetails(packageId, kitusername, password, kitid);
	}
	@Override
	public Kit getkitByKitId(Long kit_id) {
		return kitDao.getkitByKitId(kit_id);
	}
	@Override
	public HashMap<String, List<Object[]>> getReportByYearAndDealer(String year, Dealer dealer) {
		return kitDao.getReportByYearAndDealer(year, dealer);
	}
	@Override
	public String assingeKitTODealer(Package pack, Dealer dealer, Integer kitCount) {
		return kitDao.assingeKitTODealer(pack, dealer, kitCount);
	}
	@Override
	public List<KitDealerAssociation> getListOfKitdealerAssociation() {
		return kitDao.getListOfKitdealerAssociation();
	}
	@Override
	public String returnKitByDealerAndPackage(Package pack, Dealer dealer, Integer kitCount) {
		return kitDao.returnKitByDealerAndPackage(pack, dealer, kitCount);
	}
	@Override
	public HashMap<String, List<Object[]>> getReportByYearAndMonth(String year, String month) {
		return kitDao.getReportByYearAndMonth(year, month);
	}
	@Override
	public void updateDealerOfKit(String kitusername, String kitPassword, Dealer dealer) {
		kitDao.updateDealerOfKit(kitusername, kitPassword, dealer);
	}
}
