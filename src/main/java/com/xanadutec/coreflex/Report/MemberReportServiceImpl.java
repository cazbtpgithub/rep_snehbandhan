package com.xanadutec.coreflex.Report;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xanadutec.coreflex.ReportModel.MemberReportModel;
import com.xanadutec.coreflex.model.Kit_Upgrade_Or_Reg_Details;
import com.xanadutec.coreflex.model.UserModel;
import com.xanadutec.coreflex.model.UserModelDetails;

@Service("MemberReportService")
public class MemberReportServiceImpl implements MemberReportService
{
	@Autowired
	MemberReportDao memberReportDao;

	@Override
	public List<MemberReportModel> getUserDetailsByPaidAndGender(Date from_Date, Date toDate) {
		return memberReportDao.getUserDetailsByPaidAndGender(from_Date,toDate);
	}

	@Override
	public List<MemberReportModel> getUnpaidUserDetails()
	{
		return memberReportDao.getUnpaidUserDetails();
	}

	@Override
	public List<UserModelDetails> getAllUnpaidUserDetailsByGender(String user_Gender, Date from_Date, Date toDate)
	{
		return memberReportDao.getAllUnpaidUserDetailsByGender(user_Gender,from_Date,toDate);
	}

	@Override
	public List<Kit_Upgrade_Or_Reg_Details> getKitActivationAndExpiryDatByObject(UserModelDetails userModelDetails2)
	{
		return memberReportDao.getKitActivationAndExpiryDatByObject(userModelDetails2);
	}

	

}
