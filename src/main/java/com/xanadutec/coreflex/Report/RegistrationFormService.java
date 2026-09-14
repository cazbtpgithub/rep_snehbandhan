package com.xanadutec.coreflex.Report;

import java.util.List;

import com.xanadutec.coreflex.ReportModel.PartnerPreferancesReportModel;
import com.xanadutec.coreflex.ReportModel.RegistrationFormReportModel;

public interface RegistrationFormService {
	
	List<RegistrationFormReportModel> getAll_RegistrationForm_Details();

	RegistrationFormReportModel getUserDetailsByUserId(long userDetails_id);

	List<PartnerPreferancesReportModel> getPartnerPrferancesByUserId(long userDetails_id);

	List<RegistrationFormReportModel> getUserPersonalDetailsByUserId(long userDetails_id);

}
