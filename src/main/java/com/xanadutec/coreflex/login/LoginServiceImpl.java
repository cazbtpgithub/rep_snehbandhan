package com.xanadutec.coreflex.login;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.xanadutec.coreflex.model.UserModel;

@Service("loginService")
public class LoginServiceImpl implements LoginService{

	@Autowired
	private LoginDao loginDao;
	@Override
	public UserDetails loadUserByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public UserModel getUserByUnamePassword(String userName, String password) {
		return loginDao.getUserByUnamePassword(userName, password);
	}
	@Override
	public void addUser(String userName, String password) {
		 loginDao.addUser(userName, password);
	}
	@Override
	public UserModel getUserByUname(String userName) {
		return loginDao.getUserByUname(userName);
	}
	@Override
	public UserModel getUserById(int id) {
		// TODO Auto-generated method stub
		return loginDao.getUserById(id);
	}
	
	@Override
	public List<UserModel> getListOfUserIfUnamePasswordIsValid(String userName, String password) {
		// TODO Auto-generated method stub
		return loginDao.getListOfUserIfUnamePasswordIsValid(userName, password);
	}
	@Override
	public void updateEmailTime() {
		// TODO Auto-generated method stub
		loginDao.updateEmailTime();
		
	}
	@Override
	public Date getPrevEmailTime() {
		// TODO Auto-generated method stub
		return loginDao.getPrevEmailTime();
	}
	@Override
	public UserModel getUserDetailsBy(long id) {
		return loginDao.getUserDetailsBy(id);
	}

	@Override
	public boolean checkUsernameExists(String userName) {
		return loginDao.checkUsernameExists(userName);
	}

	@Override
	public boolean checkMobileExists(String mobile) {
		return loginDao.checkMobileExists(mobile);
	}
}
