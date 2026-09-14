package com.xanadutec.coreflex.KitUpgradeAndPayment;

import java.util.List;
import java.util.Map;

import com.xanadutec.coreflex.model.Kit_Upgrade_Or_Reg_Details;
import com.xanadutec.coreflex.model.SentMessagesEmail;
import com.xanadutec.coreflex.model.SortlistedProfileUserWithDate;
import com.xanadutec.coreflex.model.UserModel;

public interface KitUpgradeAndPaymentDao {
	Kit_Upgrade_Or_Reg_Details saveKit_upgrade_or_reg_details(Kit_Upgrade_Or_Reg_Details kit_Upgrade_Or_Reg_Details);

	List<Kit_Upgrade_Or_Reg_Details> getKit_upgrade_or_reg_detailsByUserModel(UserModel model);

	Kit_Upgrade_Or_Reg_Details updateKit_upgrade_or_reg_details(Kit_Upgrade_Or_Reg_Details kit_Upgrade_Or_Reg_Details);

	SortlistedProfileUserWithDate saveSortlistedProfileUserWithDate(
			SortlistedProfileUserWithDate sortlistedProfileUserWithDate);

	SortlistedProfileUserWithDate getSortlistedProfileUser(UserModel userModel, UserModel sortlistedProfileUser);

	SentMessagesEmail saveSentMessagesEmail(SentMessagesEmail messagesEmail);

	SortlistedProfileUserWithDate getSortlistedProfileUserWithDateById(
			SortlistedProfileUserWithDate profileUserWithDate);

	SortlistedProfileUserWithDate updateSortlistedProfileUserWithDate(
			SortlistedProfileUserWithDate profileUserWithDate);
}
