package com.xanadutec.coreflex.Registration;

import com.xanadutec.coreflex.model.UserModelDetails;

public interface RegistrationService {

	public UserModelDetails getUserModelDetailsByEmail(String Email);
	public UserModelDetails getUserModelDetailsByMobile(String Mobile);
	

}
