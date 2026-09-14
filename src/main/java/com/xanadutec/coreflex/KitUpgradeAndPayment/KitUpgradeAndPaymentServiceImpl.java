package com.xanadutec.coreflex.KitUpgradeAndPayment;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xanadutec.coreflex.model.Kit_Upgrade_Or_Reg_Details;
import com.xanadutec.coreflex.model.SentMessagesEmail;
import com.xanadutec.coreflex.model.SortlistedProfileUserWithDate;
import com.xanadutec.coreflex.model.UserModel;



@Service("KitUpgradeAndPaymentService")
public class KitUpgradeAndPaymentServiceImpl implements KitUpgradeAndPaymentService  {

	@Autowired
	KitUpgradeAndPaymentDao upgradeAndPaymentDao;

	@Override
	public Kit_Upgrade_Or_Reg_Details saveKit_upgrade_or_reg_details(Kit_Upgrade_Or_Reg_Details kit_Upgrade_Or_Reg_Details) {
		return upgradeAndPaymentDao.saveKit_upgrade_or_reg_details(kit_Upgrade_Or_Reg_Details);
	}

	@Override
	public List<Kit_Upgrade_Or_Reg_Details> getKit_upgrade_or_reg_detailsByUserModel(UserModel model) {
		return upgradeAndPaymentDao.getKit_upgrade_or_reg_detailsByUserModel(model);
	}

	@Override
	public Kit_Upgrade_Or_Reg_Details updateKit_upgrade_or_reg_details(
			Kit_Upgrade_Or_Reg_Details kit_Upgrade_Or_Reg_Details) {
		return upgradeAndPaymentDao.updateKit_upgrade_or_reg_details(kit_Upgrade_Or_Reg_Details);
	}

	@Override
	public SortlistedProfileUserWithDate saveSortlistedProfileUserWithDate(
			SortlistedProfileUserWithDate sortlistedProfileUserWithDate) {
		return upgradeAndPaymentDao.saveSortlistedProfileUserWithDate(sortlistedProfileUserWithDate);
	}

	@Override
	public SortlistedProfileUserWithDate getSortlistedProfileUser(UserModel userModel,
			UserModel sortlistedProfileUser) {
		return upgradeAndPaymentDao.getSortlistedProfileUser(userModel, sortlistedProfileUser);
	}

	@Override
	public SentMessagesEmail saveSentMessagesEmail(SentMessagesEmail messagesEmail) {
		return upgradeAndPaymentDao.saveSentMessagesEmail(messagesEmail);
	}

	@Override
	public SortlistedProfileUserWithDate getSortlistedProfileUserWithDateById(
			SortlistedProfileUserWithDate profileUserWithDate) {
		return upgradeAndPaymentDao.getSortlistedProfileUserWithDateById(profileUserWithDate);
	}

	@Override
	public SortlistedProfileUserWithDate updateSortlistedProfileUserWithDate(
			SortlistedProfileUserWithDate profileUserWithDate) {
		return upgradeAndPaymentDao.updateSortlistedProfileUserWithDate(profileUserWithDate);
	}


	
}
