package com.xanadutec.coreflex.superAdmin;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.xanadutec.coreflex.model.Kit_Upgrade_Or_Reg_History_Details;
import com.xanadutec.coreflex.model.UserModel;
import com.xanadutec.coreflex.revenue.RevenueService;

@Controller
public class SuperAdminController {

	@Autowired
	RevenueService revService;

	@RequestMapping(value = "/superadmin/dashboard", method = RequestMethod.GET)
	public ModelAndView getSuperAdminDashboard(ModelMap model, HttpServletRequest request) {
		
		HttpSession httpSession = request.getSession();
		UserModel userModel = (UserModel) httpSession.getAttribute("ActUserId");
		
		// In a real application, you might want to enforce role checks here
		// e.g., if(userModel == null || !isSuperAdmin(userModel)) return loginPage;

		java.util.Calendar cal = java.util.Calendar.getInstance();
		cal.set(java.util.Calendar.YEAR, 2026);
		cal.set(java.util.Calendar.MONTH, 0); // January
		cal.set(java.util.Calendar.DAY_OF_MONTH, 1);
		cal.set(java.util.Calendar.HOUR_OF_DAY, 0);
		cal.set(java.util.Calendar.MINUTE, 0);
		cal.set(java.util.Calendar.SECOND, 0);
		Date startDate = cal.getTime();
		
		cal.set(java.util.Calendar.YEAR, 2026);
		cal.set(java.util.Calendar.MONTH, 11); // December
		cal.set(java.util.Calendar.DAY_OF_MONTH, 31);
		cal.set(java.util.Calendar.HOUR_OF_DAY, 23);
		cal.set(java.util.Calendar.MINUTE, 59);
		cal.set(java.util.Calendar.SECOND, 59);
		Date endDate = cal.getTime();
		
		List<Kit_Upgrade_Or_Reg_History_Details> historyList = revService.getListOfRegHistoryByDateRange(startDate, endDate);
		
		model.addAttribute("historyList", historyList);
		
		return new ModelAndView("SuperAdminDashboard");
	}
}
