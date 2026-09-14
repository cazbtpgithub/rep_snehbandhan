package com.xanadutec.coreflex.DealerReport;

import java.util.Date;
import java.util.List;

import com.xanadutec.coreflex.model.UserModel;

public interface DealerReportDao {
	public List<DealerReportModel> getDealerDetailsByDate(Date fromDate, Date toDate);

	public List<DealerReportModel> getDealerKitDetails(int dealerId);

	public List<DealerReportModel> getDealerRevenueDetails(int dealerId , String packageName);

	public List<DealerReportModel> getSubDealerDetailsByDate(Date fromDate, Date toDate, UserModel adminUser);

	
	
}
