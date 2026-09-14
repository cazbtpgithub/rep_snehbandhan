package com.xanadutec.coreflex.sms;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xanadutec.coreflex.model.Country;
import com.xanadutec.coreflex.model.District_SubDistricts;
import com.xanadutec.coreflex.model.Kit;
import com.xanadutec.coreflex.model.SmsInfo;
import com.xanadutec.coreflex.model.State_Country;
import com.xanadutec.coreflex.model.State_Districts;

@Service("smsService")
public class smsServiceImpl implements smsService{

	@Autowired
	smsDao smsdao;
	
	@Override
	public SmsInfo getSmsDetails() 
	{
		return smsdao.getSmsDetails();
	}
	
	
}
