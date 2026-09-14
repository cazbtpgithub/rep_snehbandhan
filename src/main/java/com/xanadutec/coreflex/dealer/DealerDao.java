package com.xanadutec.coreflex.dealer;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.xanadutec.coreflex.model.Dealer;
import com.xanadutec.coreflex.model.DealerSellKitDetails;
import com.xanadutec.coreflex.model.Discount;
import com.xanadutec.coreflex.model.KitDealerAssociation;
import com.xanadutec.coreflex.model.Package;
import com.xanadutec.coreflex.model.UserModel;
import com.xanadutec.coreflex.model.UserModelDetails;

public interface DealerDao {
	
	
	public void saveDealer(Dealer dealer);
	public List<Dealer> getDealerList();
	
	public List<Dealer> getSubDealerByMainDealer(UserModel userModel);
	public Dealer getDealerByEmail(String email);
	public Dealer getDealerByMobile(String Mobile);
	public Dealer getDealerById(String id);
	public Dealer getDealerUserModel(UserModel userModel);
	public void deleteDealerByDealerId(String dealerId);
	public int getMaxIdOfDealer();
	public void disableDealerById(String dealerId);
	public void unableDealerById(String dealerId);
	
	public int getdealerDiscountbyDealer(Dealer dealer);
	public void saveDealerSellKitDetails(DealerSellKitDetails dealerSellKitDetails);
	
	public List<Discount> getDealerWiseDiscount(List<Package> list,Dealer dealer);
	public List<KitDealerAssociation> getSubDealerSaleKitDetailsBySubDealer(List<Dealer> list);
	public long getTotalDealerCount();
	public UserModel getUserModelByDealerId(String dealerId);
	public List<Dealer> getAllSubDealerByMainDealer(UserModel userModel);

}
