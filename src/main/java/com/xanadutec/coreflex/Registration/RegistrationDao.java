package com.xanadutec.coreflex.Registration;

import com.xanadutec.coreflex.model.UserModelDetails;

public interface RegistrationDao {
	
	
	public UserModelDetails getUserModelDetailsByEmail(String Email);
	public UserModelDetails getUserModelDetailsByMobile(String Mobile);

	
	
}
