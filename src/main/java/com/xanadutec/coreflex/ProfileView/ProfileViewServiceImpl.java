package com.xanadutec.coreflex.ProfileView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xanadutec.coreflex.model.CompareUserProfileView;
import com.xanadutec.coreflex.model.ImagePath;
import com.xanadutec.coreflex.model.Kit;
import com.xanadutec.coreflex.model.UserModel;

@Service("ProfileViewService")
public class ProfileViewServiceImpl implements ProfileViewService{

	@Autowired
	ProfileViewDao profileViewDao;
	
	@Override
	public CompareUserProfileView loadUserProfileView(UserModel userModel) {
		return null;
	}

	@Override
	public ImagePath getImagePath()
	{
		return profileViewDao.getImagePath();
	}
	
	
}
