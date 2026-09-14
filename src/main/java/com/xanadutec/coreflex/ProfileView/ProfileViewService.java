package com.xanadutec.coreflex.ProfileView;

import com.xanadutec.coreflex.model.CompareUserProfileView;
import com.xanadutec.coreflex.model.ImagePath;
import com.xanadutec.coreflex.model.UserModel;

public interface ProfileViewService {

	public CompareUserProfileView loadUserProfileView(UserModel userModel);
	
	public ImagePath getImagePath();
}
