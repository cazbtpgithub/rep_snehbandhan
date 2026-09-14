package com.xanadutec.coreflex.Registration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xanadutec.coreflex.model.UserModelDetails;

@Service("RegistrationService")
public class RegistrationServiceImpl implements RegistrationService {
	@Autowired
	RegistrationDao religionDao;

	@Override
	public UserModelDetails getUserModelDetailsByEmail(String Email) {
		
		return religionDao.getUserModelDetailsByEmail(Email);
	}

	@Override
	public UserModelDetails getUserModelDetailsByMobile(String Mobile) {
		
		return religionDao.getUserModelDetailsByMobile(Mobile);
	}

	

}
