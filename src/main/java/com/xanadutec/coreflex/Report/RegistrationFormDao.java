package com.xanadutec.coreflex.Report;

import java.util.List;

import com.xanadutec.coreflex.ReportModel.PartnerPreferancesReportModel;
import com.xanadutec.coreflex.ReportModel.RegistrationFormReportModel;
import com.xanadutec.coreflex.model.UserModelPartnerPreferances;

public interface RegistrationFormDao {
	
	public List<RegistrationFormReportModel> getAll_RegistrationForm_Details();

	public RegistrationFormReportModel getUserDetailsByUserId(long userDetails_id);

	public List<PartnerPreferancesReportModel> getPartnerPrferancesByUserId(long userDetails_id);

	public List<RegistrationFormReportModel> getUserPersonalDetailsByUserId(long userDetails_id);

}
