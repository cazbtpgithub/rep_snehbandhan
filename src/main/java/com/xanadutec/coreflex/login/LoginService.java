package com.xanadutec.coreflex.login;

import java.util.Date;
import java.util.List;


import org.springframework.security.core.userdetails.UserDetails;

import com.xanadutec.coreflex.model.Kit;
import com.xanadutec.coreflex.model.UserModel;

public interface LoginService {

	public UserDetails loadUserByUsername(String userName);

	public UserModel getUserByUnamePassword(String username, String password);

	public void addUser(String userName, String password);

	public UserModel getUserByUname(String userName);

	public UserModel getUserById(int id);

	public List<UserModel> getListOfUserIfUnamePasswordIsValid(String userName, String password);
	
	public void updateEmailTime();
	public Date getPrevEmailTime();

	public UserModel getUserDetailsBy(long id);
	
	public boolean checkUsernameExists(String userName);

	public boolean checkMobileExists(String mobile);
}
