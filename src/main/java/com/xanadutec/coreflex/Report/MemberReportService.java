package com.xanadutec.coreflex.Report;

import java.util.Date;
import java.util.List;

import com.xanadutec.coreflex.ReportModel.MemberReportModel;
import com.xanadutec.coreflex.model.Kit_Upgrade_Or_Reg_Details;
import com.xanadutec.coreflex.model.UserModel;
import com.xanadutec.coreflex.model.UserModelDetails;

public interface MemberReportService {


	List<MemberReportModel> getUserDetailsByPaidAndGender(Date from_Date, Date toDate);

	List<MemberReportModel> getUnpaidUserDetails();

	List<UserModelDetails> getAllUnpaidUserDetailsByGender(String user_Gender, Date from_Date, Date toDate);

	List<Kit_Upgrade_Or_Reg_Details> getKitActivationAndExpiryDatByObject(UserModelDetails userModelDetails2);



}
