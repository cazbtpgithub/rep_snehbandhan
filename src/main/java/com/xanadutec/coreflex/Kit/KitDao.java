package com.xanadutec.coreflex.Kit;

import java.util.HashMap;
import java.util.List;

import com.xanadutec.coreflex.model.Dealer;
import com.xanadutec.coreflex.model.Kit;
import com.xanadutec.coreflex.model.KitDealerAssociation;
import com.xanadutec.coreflex.model.Kit_Upgrade_Or_Reg_Details;
import com.xanadutec.coreflex.model.Kit_Upgrade_Or_Reg_History_Details;
import com.xanadutec.coreflex.model.Package;
import com.xanadutec.coreflex.model.UserModel;



public interface KitDao {
	public Kit findKitByKitUserNamePassword(String KitUserName, String KitPassword);
	public HashMap<String,  List<Object[]>> getReportByYear(String year);
	public void updateKitStatus(Kit kit);
	public Kit_Upgrade_Or_Reg_History_Details saveKit_Upgrade_Or_Reg_History_Details(Kit_Upgrade_Or_Reg_History_Details history_Details);
	public Kit_Upgrade_Or_Reg_Details getKitUpgradeAndRegistationDetailByUserModel(UserModel userModel);
	public void deletKitByUnameAndPassword(String kitusername,String password);
	public void updatekitDetails(String packageId,String kitusername,String password,String kitid);
	public Kit getkitByKitId(Long kit_id);
	public HashMap<String,  List<Object[]>> getReportByYearAndDealer(String year,Dealer dealer);
	public String assingeKitTODealer(Package pack,Dealer dealer,Integer kitCount);
	public List<KitDealerAssociation> getListOfKitdealerAssociation();
	public String returnKitByDealerAndPackage(Package pack,Dealer dealer,Integer kitCount);
    public HashMap<String,  List<Object[]>> getReportByYearAndMonth(String year,String month);
    public void updateDealerOfKit(String kitusername,String kitPassword,Dealer dealer);
    
    
	
}
