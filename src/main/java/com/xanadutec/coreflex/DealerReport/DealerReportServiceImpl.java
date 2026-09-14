package com.xanadutec.coreflex.DealerReport;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xanadutec.coreflex.model.AdminUserModel;
import com.xanadutec.coreflex.model.UserModel;


@Service("DealerReportService")
public class DealerReportServiceImpl implements DealerReportService {

	@Autowired
	DealerReportDao dealerReportDao;
	@Override
	public List<DealerReportModel> getDealerDetailsByDate(Date fromDate, Date toDate) {
		
		
		return dealerReportDao.getDealerDetailsByDate(fromDate, toDate);
		}
	@Override
	public List<DealerReportModel> getDealerKitDetails(int dealerId) {
		
		return dealerReportDao.getDealerKitDetails(dealerId);
	}
	@Override
	public List<DealerReportModel> getDealerRevenueDetails(int dealerId , String packageName) {
		return dealerReportDao.getDealerRevenueDetails(dealerId,packageName);
	}
	@Override
	public List<DealerReportModel> getSubDealerDetailsByDate(Date fromDate, Date toDate,UserModel adminUser)
	{
		return dealerReportDao.getSubDealerDetailsByDate(fromDate, toDate, adminUser);
	}
	

}
