package com.xanadutec.coreflex.Report;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xanadutec.coreflex.ReportModel.PartnerPreferancesReportModel;
import com.xanadutec.coreflex.ReportModel.RegistrationFormReportModel;


@Service("RegistrationFormService")
public class RegistrationFormServiceImpl implements RegistrationFormService {

	
	@Autowired
	RegistrationFormDao registrationFormDao;
	
	@Override
	public List<RegistrationFormReportModel> getAll_RegistrationForm_Details() 
	{
		
		return registrationFormDao.getAll_RegistrationForm_Details();
	}

	@Override
	public RegistrationFormReportModel getUserDetailsByUserId(long userDetails_id)
	{
		return registrationFormDao.getUserDetailsByUserId(userDetails_id);
	}

	@Override
	public List<PartnerPreferancesReportModel> getPartnerPrferancesByUserId(long userDetails_id)
	{
		return registrationFormDao.getPartnerPrferancesByUserId(userDetails_id);
	}

	@Override
	public List<RegistrationFormReportModel> getUserPersonalDetailsByUserId(long userDetails_id)
	{
		return registrationFormDao.getUserPersonalDetailsByUserId(userDetails_id);
	}

}
