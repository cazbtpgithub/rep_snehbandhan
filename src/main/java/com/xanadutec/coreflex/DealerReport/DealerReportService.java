package com.xanadutec.coreflex.DealerReport;

import java.util.Date;
import java.util.List;

import com.xanadutec.coreflex.model.AdminUserModel;
import com.xanadutec.coreflex.model.UserModel;

public interface DealerReportService {

	List<DealerReportModel> getDealerDetailsByDate(Date fromDate, Date toDate);

	List<DealerReportModel> getDealerKitDetails(int dealerId);

	List<DealerReportModel> getDealerRevenueDetails(int dealerId , String packageName);

	List<DealerReportModel> getSubDealerDetailsByDate(Date fromDate, Date toDate, UserModel adminUser);

}
