package com.xanadutec.coreflex.dealer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xanadutec.coreflex.model.Dealer;
import com.xanadutec.coreflex.model.DealerSellKitDetails;
import com.xanadutec.coreflex.model.Discount;
import com.xanadutec.coreflex.model.KitDealerAssociation;
import com.xanadutec.coreflex.model.Package;
import com.xanadutec.coreflex.model.UserModel;


@Service("DealerService")
public class DealerServiceImpl implements DealerService  {
	
	@Autowired
	DealerDao dealerDao;

	@Override
	public void saveDealer(Dealer dealer) {
		dealerDao.saveDealer(dealer);
	}

	@Override
	public List<Dealer> getDealerList() {
		return dealerDao.getDealerList();
	}

	@Override
	public Dealer getDealerByEmail(String email) {
		return dealerDao.getDealerByEmail(email);
	}

	@Override
	public Dealer getDealerByMobile(String Mobile) {
		return dealerDao.getDealerByMobile(Mobile);
	}

	@Override
	public Dealer getDealerById(String id) {
		return dealerDao.getDealerById(id);
	}

	@Override
	public Dealer getDealerUserModel(UserModel userModel) {
		return dealerDao.getDealerUserModel(userModel);
	}

	@Override
	public void deleteDealerByDealerId(String dealerId) {
		dealerDao.deleteDealerByDealerId(dealerId);
		
	}

	@Override
	public List<Dealer> getSubDealerByMainDealer(UserModel userModel) {
		return dealerDao.getSubDealerByMainDealer(userModel);
	}

	@Override
	public int getMaxIdOfDealer() {
		return dealerDao.getMaxIdOfDealer();
	}

	@Override
	public void disableDealerById(String dealerId) {
		dealerDao.disableDealerById(dealerId);
		
	}

	@Override
	public void unableDealerById(String dealerId) {
		dealerDao.unableDealerById(dealerId);
		
	}

	@Override
	public int getdealerDiscountbyDealer(Dealer dealer) {
		
		return dealerDao.getdealerDiscountbyDealer(dealer);
	}

	@Override
	public void saveDealerSellKitDetails(DealerSellKitDetails dealerSellKitDetails) {
		
		dealerDao.saveDealerSellKitDetails(dealerSellKitDetails);
	}

	@Override
	public List<Discount> getDealerWiseDiscount(List<Package> list,Dealer dealer) {
		return dealerDao.getDealerWiseDiscount(list,dealer);
	}

	@Override
	public List<KitDealerAssociation> getSubDealerSaleKitDetailsBySubDealer(List<Dealer> list) 
	{
		return dealerDao.getSubDealerSaleKitDetailsBySubDealer(list);
	}

	@Override
	public long getTotalDealerCount() 
	{
		return dealerDao.getTotalDealerCount();
	}

	@Override
	public UserModel getUserModelByDealerId(String dealerId) {
		return dealerDao.getUserModelByDealerId(dealerId);
	}

	@Override
	public List<Dealer> getAllSubDealerByMainDealer(UserModel userModel) {
		return dealerDao.getAllSubDealerByMainDealer(userModel);
	}
	
}
