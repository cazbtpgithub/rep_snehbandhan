package com.xanadutec.coreflex.ProfileView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.xanadutec.coreflex.Email.EmailService;
import com.xanadutec.coreflex.model.CompareUserProfileView;
import com.xanadutec.coreflex.model.UserModel;
import com.xanadutec.coreflex.model.UserModelDetails;
import com.xanadutec.coreflex.model.UserProfileView;
import com.xanadutec.coreflex.userTask.UserService;

@Controller
public class ProfileViewController {
	@Autowired 
	UserService userService;
	@Autowired
	EmailService emailService;
	@Autowired
	ProfileViewService profileViewService;
	
	@RequestMapping(value = "/ViewProfileWithPartner", method = RequestMethod.GET)
	public ModelAndView saveCountry(@RequestParam("userId") String userId, ModelMap model, final HttpServletRequest request) {
		
		final String pathact =  request.getServletContext().getRealPath("/resources/UserImages");
		HttpSession httpSession = request.getSession();
		UserModel userModel1=  (UserModel) httpSession.getAttribute("ActUserId");
		UserModelDetails modelDetailsTemp = null;
		
		List<UserProfileView> profileViews;
		List<UserProfileView> onlineProfiles;
		if (userModel1 != null) {
			modelDetailsTemp = userService.getUserdetails(userModel1);
			if (modelDetailsTemp != null && modelDetailsTemp.getGender() != null && modelDetailsTemp.getGender().equalsIgnoreCase("male")) {
				profileViews = userService.getListOFProfileForHomeAccordingToGender("Female", request);	
				onlineProfiles = userService.getListOFProfileForChattingAccordingToGender("Female", request);
			} else {
				profileViews = userService.getListOFProfileForHomeAccordingToGender("Male", request);
				onlineProfiles = userService.getListOFProfileForChattingAccordingToGender("Male", request);
			}
		} else {
			profileViews = userService.getListOFProfileForHomeAccordingToGender("Female", request);
			onlineProfiles = new ArrayList<>();
		}
		final UserModelDetails modelDetails = modelDetailsTemp;
		
		try {
			model.addAttribute("LatestMatch", profileViews);
			if (userModel1 != null) {
				model.addAttribute("loguser", userModel1.getUserId());
				model.addAttribute("Email", userModel1.getUserName());
				model.addAttribute("firstName", userModel1.getFirstName());
			}
			Date expDate = (Date) httpSession.getAttribute("expiryDate");
			model.addAttribute("expDate", expDate);
			model.addAttribute("onlinecount", onlineProfiles.size());
			model.addAttribute("onlineProfiles", onlineProfiles);
			model.addAttribute("pxval", 15);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		UserModel userModel = new UserModel();
		userModel.setUserId(Long.parseLong(userId));
		CompareUserProfileView compareUserProfileView = userService.getCompareUserProfileViewAccordingToUserModelId(userModel, request);
		if (profileViewService.getImagePath() != null) {
			model.addAttribute("imgPath", profileViewService.getImagePath().getPath_name());
		}
		model.addAttribute("compareUserProfileView", compareUserProfileView);
		
		try {
			if (userModel1 != null && modelDetails != null) {
				userService.saveVisitedProfileUserWithDate(userModel1, userModel);
				final UserModelDetails modelDetailsTo = userService.getUserdetails(userModel);
				Thread tt = new Thread(new Runnable() {
					public void run() {
						emailService.viewProfileSendEmail(modelDetails, modelDetailsTo, request, pathact); 
					}
				});
				tt.start();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("ProfileView");
	}  
}
