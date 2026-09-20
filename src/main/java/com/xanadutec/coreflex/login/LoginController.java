package com.xanadutec.coreflex.login;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xanadutec.coreflex.BranchDetails.BranchService;
import com.xanadutec.coreflex.EducationOccupation.EducationOccupationService;
import com.xanadutec.coreflex.Email.EmailService;
import com.xanadutec.coreflex.Kit.KitService;
import com.xanadutec.coreflex.KitUpgradeAndPayment.KitUpgradeAndPaymentService;
import com.xanadutec.coreflex.ProfileView.ProfileViewService;
import com.xanadutec.coreflex.Religion.ReligionService;
import com.xanadutec.coreflex.dealer.DealerService;
import com.xanadutec.coreflex.employee.EmployeeService;
import com.xanadutec.coreflex.location.LocationService;
import com.xanadutec.coreflex.magazine.MagzineService;
import com.xanadutec.coreflex.model.AboutUs;
import com.xanadutec.coreflex.model.BranchModel;
import com.xanadutec.coreflex.model.Country;
import com.xanadutec.coreflex.model.Dealer;
import com.xanadutec.coreflex.model.EducationCategory;
import com.xanadutec.coreflex.model.FAQS;
import com.xanadutec.coreflex.model.Kit;
import com.xanadutec.coreflex.model.Kit_Upgrade_Or_Reg_Details;
import com.xanadutec.coreflex.model.MagazinePdf;
import com.xanadutec.coreflex.model.Occupation;
import com.xanadutec.coreflex.model.OccupationCategory;
import com.xanadutec.coreflex.model.Religion;
import com.xanadutec.coreflex.model.SortlistedProfileUserWithDate;
import com.xanadutec.coreflex.model.State_Country;
import com.xanadutec.coreflex.model.SuccessStory;
import com.xanadutec.coreflex.model.TermAndCondition;
import com.xanadutec.coreflex.model.UserModel;
import com.xanadutec.coreflex.model.UserModelDetails;
import com.xanadutec.coreflex.model.UserModelImagesName;
import com.xanadutec.coreflex.model.UserPermissionAssociation;
import com.xanadutec.coreflex.model.UserProfileView;
import com.xanadutec.coreflex.model.UserProfileViewForMessages;
import com.xanadutec.coreflex.setup.CreateSetup;
import com.xanadutec.coreflex.userTask.UserService;

@Controller
public class LoginController {

	@Autowired
	CreateSetup createSetup;
	@Autowired
	UserService userService;

	@Autowired
	private LocationService locationService;
	@Autowired
	ReligionService religionService;
	@Autowired
	EducationOccupationService educationOccupationService;
	@Autowired
	KitService kitService;
	@Autowired
	ProfileViewService profileViewService;
	@Autowired
	KitUpgradeAndPaymentService kitUpgradeAndPaymentService;
	@Autowired
	DealerService dealerService;
	@Autowired
	LoginService loginService;
	@Autowired
	EmailService emailService;
	@Autowired
	MagzineService magazineService;
	@Autowired
	EmployeeService employeeService;
	@Autowired
	BranchService branchService;

	private Logger logger = Logger.getLogger(LoginController.class);

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView login(HttpServletRequest request,ModelMap model) {
		// createSetup.makeSetup(request);
		
		List<SuccessStory>successList=locationService.getListOfSuccessStory();
		List<FAQS> faqsList=locationService.getListOfFAQs();
		String privacyAndPolicy=locationService.getPrivacyPolicy();
		List<UserProfileView> featuredProfiles = userService.getTopShortlistedProfiles(4, request);
		model.addAttribute("privacyAndPolicy", privacyAndPolicy); 
		model.addAttribute("successList", successList); 
		model.addAttribute("faqsList", faqsList);
		model.addAttribute("featuredProfiles", featuredProfiles);
		
		return new ModelAndView("landing");
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(ModelMap model, HttpServletRequest request) {
		// createSetup.makeSetup(request);
		// HttpSession session = request.getSession(true);
		// session.setAttribute("userId", );

		List<SuccessStory>successList=locationService.getListOfSuccessStory();
		List<FAQS> faqsList=locationService.getListOfFAQs();
		String privacyAndPolicy=locationService.getPrivacyPolicy();
		List<UserProfileView> featuredProfiles = userService.getTopShortlistedProfiles(4, request);
		model.addAttribute("privacyAndPolicy", privacyAndPolicy);
		model.addAttribute("successList", successList);
		model.addAttribute("faqsList", faqsList);
		model.addAttribute("featuredProfiles", featuredProfiles);
		return "landing";
	}

	@RequestMapping(value = "/accessdenied", method = RequestMethod.GET)
	public String loginerror(ModelMap model) {
		model.addAttribute("error", "true");
		model.addAttribute("errorMsg",
				"Unauthorized Access !! Please Enter Correct Username Password");
		return "landing";
	}

	@RequestMapping(value = "/logout", method = {RequestMethod.GET, RequestMethod.POST})
	public String logout(ModelMap model, HttpServletRequest request) {
		try {
			HttpSession session = request.getSession(false);
			if (session != null) {
				session.invalidate();
			}
			SecurityContextHolder.clearContext();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/login";
	}

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView loginSuccess(ModelMap model, HttpServletRequest request) 
	{
		UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String name = user.getUsername(); // get logged in username
		UserModel userModel = userService.getUserByUname(name);
		List<UserPermissionAssociation> userPermissionAssociationsList = employeeService.getPerticularUserPermissionAssociationByUserObject(userModel);
		Date today = null;
		Date yesterday = null;

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		Calendar calender = Calendar.getInstance();
		String today_date = df.format(calender.getTime());
		calender.add(Calendar.DATE, -1);
		String yesterday_date = df.format(calender.getTime());



		String[] todayDate_Split = today_date.split("-");
		String[] yesterdaySplit = yesterday_date.split("-");

		String todays_Date = todayDate_Split[2];
		String todaySplite[] = todays_Date.split(" ");
		String dayofMonth = todaySplite[0];

		String yestar_day = yesterdaySplit[2];
		String yestar_daySplite[] = yestar_day.split(" ");
		String yestarday_dayofMonth = yestar_daySplite[0];
		
		String todaySplitDate = todayDate_Split[0] + "-" + todayDate_Split[1] + "-" +  dayofMonth ;
		String yesterdaySplitDate =  yesterdaySplit[0] + "-" + yesterdaySplit[1] + "-" +yestarday_dayofMonth;

		try 
		{
			today = df.parse(todaySplitDate + " 23:59:59");
			yesterday = df.parse(yesterdaySplitDate + " 00:00:00");

		} catch (ParseException e)
		{
			e.printStackTrace();
		}

		
		long recentlyAddedUser = userService.getRecentlyAddedUserBy_Date(today,yesterday);
		long totalRevenue = userService.getTotalRevenue();
		long  totaldealer = dealerService.getTotalDealerCount();
		long totalsaleKit = userService.getTotalSaleKitCount();


		HttpSession httpSession = request.getSession();
		httpSession.setAttribute("ActUserId", userModel);

		if (userModel.getUserRoll().getId() == 4) 
		{
			model.addAttribute("loginUserRole", "Employee");
		} else 
		{
			model.addAttribute("loginUserRole", "Admin");
		}

		HttpSession session = request.getSession(false);

		session.setAttribute("userPermissionAssociationsList",userPermissionAssociationsList);
		
		session.setAttribute("recentlyAddedUser",recentlyAddedUser);
		session.setAttribute("totaldealer",totaldealer);
		session.setAttribute("totalsaleKit",totalsaleKit);
		session.setAttribute("totalRevenue",totalRevenue);
		

		// String name = user.getUsername(); // get logged in username
		String thisYear = new SimpleDateFormat("yyyy").format(new Date());
		HashMap<String, List<Object[]>> result = kitService
				.getReportByYear(thisYear);
		List<Object[]> open = result.get("open");
		Set<Integer> openMonth = new HashSet<Integer>();
		for (Object[] aRow : open) {

			openMonth.add((Integer) aRow[1]);
		}
		TreeMap<Integer, Long> finalResultOfOpenMonth = new TreeMap<Integer, Long>();

		for (Integer month : openMonth) {
			for (Object[] aRow : open) {
				try {
					Long l = finalResultOfOpenMonth.get(month);
					if ((aRow[1] + "").equalsIgnoreCase(month + "")) {
						if (l == null) {
							finalResultOfOpenMonth.put(month, (Long) aRow[0]);
						} else if (l != null) {
							finalResultOfOpenMonth.put(month, (Long) aRow[0]
									+ l);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		Set<Integer> commonMonthListopen = finalResultOfOpenMonth.keySet();
		LinkedHashMap<String, Long> hashMap = new LinkedHashMap<String, Long>();

		for (Integer integer : commonMonthListopen) {

			String month = "";

			switch (integer) {
			case 1:
				month = "January";
				break;
			case 2:
				month = "February";
				break;
			case 3:
				month = "March";
				break;
			case 4:
				month = "April";
				break;
			case 5:
				month = "May";
				break;
			case 6:
				month = "June";
				break;
			case 7:
				month = "July";
				break;
			case 8:
				month = "August";
				break;
			case 9:
				month = "September";
				break;
			case 10:
				month = "October";
				break;
			case 11:
				month = "November";
				break;
			case 12:
				month = "December";
				break;
			}
			Long count = finalResultOfOpenMonth.get(integer);
			if (count == null) {
				count = (long) 0;
			}
			hashMap.put(month, count);
		}

		/* Sending bulk sms and email */

		Date prevDate = loginService.getPrevEmailTime();

		Date presDate = new Date();

		try {
			if (prevDate != null) {
				long diff = presDate.getTime() - prevDate.getTime();
				long diffDays = diff / (24 * 60 * 60 * 1000);
				long diffHours = diff / (60 * 60 * 1000) % 24;
				
				System.out.println("diffDays  "+diffDays);
				System.out.println("diffHours  "+diffHours);

				if ((diffDays < 0) || (diffHours <= -23)) {

					final String pathact1 = request.getServletContext()
							.getRealPath("/resources/UserImages");

					loginService.updateEmailTime();

					Thread tt = new Thread(new Runnable() {

						@Override
						public void run() {
							emailService.sendKitExpiryMail(pathact1);
						}
					});

					tt.start();

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		
		model.addAttribute("countList", hashMap);
		model.addAttribute("thisYear", thisYear);

		return new ModelAndView("Home");

	}

	@RequestMapping(value = "/getDaywiseCount", method = RequestMethod.POST)
	@ResponseBody
	public String getYearMonthWiseCount(@RequestParam("year") String year,
			@RequestParam("month") String month, HttpServletRequest request) {

		System.out.println(year + "year " + month + "month");
		JSONObject mainObj = new JSONObject();
		ObjectWriter ow = new ObjectMapper().writer()
				.withDefaultPrettyPrinter();
		String json = null;
		try {

			JSONObject js = new JSONObject();
			HashMap<String, List<Object[]>> result = kitService
					.getReportByYearAndMonth(year, month);

			List<Object[]> open = result.get("open");

			Set<Integer> openDay = new HashSet<Integer>();

			for (Object[] aRow : open) {

				openDay.add((Integer) aRow[1]);
			}

			TreeMap<Integer, Long> finalResultOfOpenDay = new TreeMap<Integer, Long>();

			for (Integer day : openDay) {
				for (Object[] aRow : open) {
					try {
						Long l = finalResultOfOpenDay.get(day);
						if ((aRow[1] + "").equalsIgnoreCase(day + "")) {
							if (l == null) {
								finalResultOfOpenDay.put(day, (Long) aRow[0]);
							} else if (l != null) {
								finalResultOfOpenDay.put(day, (Long) aRow[0]
										+ l);
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}

			Set<Integer> commonDayListopen = finalResultOfOpenDay.keySet();
			LinkedHashMap<String, Long> hashMap = new LinkedHashMap<String, Long>();

			for (Integer integer : commonDayListopen) {

				Long count = finalResultOfOpenDay.get(integer);
				if (count == null) {
					count = (long) 0;
				}
				hashMap.put(integer + "", count);

			}

			// System.out.println("hashMap  "+hashMap);

			js.put("hashMap", ow.writeValueAsString(hashMap));

			json = js.toString();
			return json;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";

	}

	//
	@RequestMapping(value = "/homeForDealer", method = RequestMethod.GET)
	public ModelAndView homeForDealer(ModelMap model, HttpServletRequest request) {
		UserDetails user = (UserDetails) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		String name = user.getUsername(); // get logged in username
		UserModel userModel = userService.getUserByUname(name);
		Dealer dealer = dealerService.getDealerUserModel(userModel);
		HttpSession httpSession = request.getSession();
		httpSession.setAttribute("DealeName", userModel.getFirstName());
		httpSession.setAttribute("DealerUserModel", dealer);
		httpSession.setAttribute("ActUserId", userModel);

		// Count calculation start
		String thisYear = new SimpleDateFormat("yyyy").format(new Date());
		HashMap<String, List<Object[]>> result = kitService
				.getReportByYearAndDealer(thisYear, dealer);
		List<Object[]> open = result.get("open");
		Set<Integer> openMonth = new HashSet<Integer>();
		for (Object[] aRow : open) {

			openMonth.add((Integer) aRow[1]);
		}
		TreeMap<Integer, Long> finalResultOfOpenMonth = new TreeMap<Integer, Long>();

		for (Integer month : openMonth) {
			for (Object[] aRow : open) {
				try {
					Long l = finalResultOfOpenMonth.get(month);
					if ((aRow[1] + "").equalsIgnoreCase(month + "")) {
						if (l == null) {
							finalResultOfOpenMonth.put(month, (Long) aRow[0]);
						} else if (l != null) {
							finalResultOfOpenMonth.put(month, (Long) aRow[0]
									+ l);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		Set<Integer> commonMonthListopen = finalResultOfOpenMonth.keySet();
		LinkedHashMap<String, Long> hashMap = new LinkedHashMap<String, Long>();

		for (Integer integer : commonMonthListopen) {

			String month = "";

			switch (integer) {
			case 1:
				month = "January";
				break;
			case 2:
				month = "February";
				break;
			case 3:
				month = "March";
				break;
			case 4:
				month = "April";
				break;
			case 5:
				month = "May";
				break;
			case 6:
				month = "June";
				break;
			case 7:
				month = "July";
				break;
			case 8:
				month = "August";
				break;
			case 9:
				month = "September";
				break;
			case 10:
				month = "October";
				break;
			case 11:
				month = "November";
				break;
			case 12:
				month = "December";
				break;
			}
			Long count = finalResultOfOpenMonth.get(integer);
			if (count == null) {
				count = (long) 0;
			}
			hashMap.put(month, count);
		}

		model.addAttribute("countList", hashMap);
		model.addAttribute("currentYear", thisYear);

		// End count calculation
		return new ModelAndView("HomeForDealer");

	}

	//

	@RequestMapping(value = "/loadLatestMatches", method = RequestMethod.POST)
	public ModelAndView loadLatestMatches(ModelMap model,
			HttpServletRequest request) {
		JSONObject obj;
		try {
			HttpSession httpSession = request.getSession();
			UserModel userModel = (UserModel) httpSession
					.getAttribute("ActUserId");
			if (userModel == null) {
				try {
					Authentication auth = SecurityContextHolder.getContext().getAuthentication();
					if (auth != null && auth.getPrincipal() instanceof UserDetails) {
						UserDetails userDetails = (UserDetails) auth.getPrincipal();
						userModel = userService.getUserByUname(userDetails.getUsername());
						if (userModel != null) {
							httpSession.setAttribute("ActUserId", userModel);
							httpSession.setAttribute("ActUserName", userModel.getFirstName());
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			UserModelDetails modelDetails = userModel != null ? userService
					.getUserdetails(userModel) : null;

			obj = new JSONObject(request.getParameter("jsonObjectData"));
			final JSONObject jsonObj = obj;
			String length = jsonObj.optString("length", "10");

			List<UserModel> models = new ArrayList<>();
			Object vIds = jsonObj.opt("latestMatchVisitorIds");
			if (vIds != null) {
				String rawVisitorIds = vIds.toString().replaceAll("[\\[\\]\"\\s]", "");
				if (!rawVisitorIds.isEmpty()) {
					String[] visitorArray = rawVisitorIds.split(",");
					for (String string : visitorArray) {
						if (!string.equalsIgnoreCase("null") && !string.isEmpty()) {
							try {
								UserModel model2 = new UserModel();
								model2.setUserId(Long.parseLong(string));
								models.add(model2);
							} catch (NumberFormatException nfe) {
							}
						}
					}
				}
			}

			List<UserProfileView> profileViews;

			if (modelDetails != null && "male".equalsIgnoreCase(modelDetails.getGender())) {
				System.out.println("Female view 1");
				profileViews = userService.getListOFProfileForHomeAccordingToGenderExcludeUserIds("Female", models, request);
			} else {
				System.out.println("Male 2");
				profileViews = userService.getListOFProfileForHomeAccordingToGenderExcludeUserIds("Male", models, request);
			}
			model.addAttribute("LatestMatch", profileViews);
			model.addAttribute("length", length);
			model.addAttribute("counterSlider", length);

			return new ModelAndView("loadLatestProfile");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/loadRefineSearchProfile", method = RequestMethod.POST)
	public ModelAndView loadRefineSearchProfile(ModelMap model,
			HttpServletRequest request) {

		JSONObject obj;
		try {

			obj = new JSONObject(request.getParameter("jsonObjectData"));
			final JSONObject jsonObj = obj;
			String refineheightSelectPartnerFirst = jsonObj
					.getString("refineheightSelectPartnerFirst");
			String refineheightSelectPartnerSecond = jsonObj
					.getString("refineheightSelectPartnerSecond");
			String refineageFrom = jsonObj.getString("refineageFrom");
			String refineageTo = jsonObj.getString("refineageTo");
			String refinematerialStatus = jsonObj
					.getString("refinematerialStatus");
			String refinereligionSelectPartner = jsonObj
					.getString("refinereligionSelectPartner");
			String casteSelect = jsonObj.getString("casteSelect");
			String refineoccupationCategorySelect = jsonObj
					.getString("refineoccupationCategorySelect");
			String checkBoxDistrict = jsonObj.getString("checkBoxDistrict");
			String RefineSearchProfile = jsonObj
					.get("RefineSearchProfile").toString();

			StringBuffer builder = new StringBuffer(RefineSearchProfile);
			builder.deleteCharAt(0);
			builder.deleteCharAt(builder.toString().length() - 1);

			String visitorArray[] = builder.toString().split(",");
			List<UserModel> models = new ArrayList<>();

			for (String string : visitorArray) {
				if (string.equalsIgnoreCase("null") || string.equals("")) {

				} else {
					UserModel model2 = new UserModel();
					model2.setUserId(Long.parseLong(string));
					models.add(model2);

				}

			}

			HttpSession httpSession = request.getSession();
			UserModel userModel = (UserModel) httpSession
					.getAttribute("ActUserId");
			UserModelDetails modelDetails = userService
					.getUserdetails(userModel);

			List<UserProfileView> profileViews;
			if (modelDetails != null && "male".equalsIgnoreCase(modelDetails.getGender())) {
				profileViews = userService
						.getlistOfRefineSearchByUserExcludeUserModels(request,
								userModel, "female",
								refineheightSelectPartnerFirst,
								refineheightSelectPartnerSecond, refineageFrom,
								refineageTo, refinematerialStatus,
								refinereligionSelectPartner, casteSelect,
								refineoccupationCategorySelect,
								checkBoxDistrict, models);
			} else {
				profileViews = userService.getlistOfRefineSearchByUserExcludeUserModels(request,
								userModel, "male",
								refineheightSelectPartnerFirst,
								refineheightSelectPartnerSecond, refineageFrom,
								refineageTo, refinematerialStatus,
								refinereligionSelectPartner, casteSelect,
								refineoccupationCategorySelect,
								checkBoxDistrict, models);
			}

			model.addAttribute("LatestMatch", profileViews);

			return new ModelAndView("refine_searchConversationView");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/latest_inbox_conversation", method = RequestMethod.POST)
	public ModelAndView latest_inbox_conversation(ModelMap model,
			HttpServletRequest request) {

		try {

			HttpSession httpSession = request.getSession();
			UserModel userModel = (UserModel) httpSession
					.getAttribute("ActUserId");
			List<UserProfileViewForMessages> profileViews;

			profileViews = userService.getlistOfMessagesAndEmailByUser(request,
					userModel);

			model.addAttribute("LatestMatch", profileViews);

			return new ModelAndView("latest_inbox_conversationView");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/accepted_conversation", method = RequestMethod.POST)
	public ModelAndView accepted_conversation(ModelMap model,
			HttpServletRequest request) {

		JSONObject obj;
		try {

			HttpSession httpSession = request.getSession();
			UserModel userModel = (UserModel) httpSession
					.getAttribute("ActUserId");
			UserModelDetails modelDetails = userService
					.getUserdetails(userModel);

			List<UserProfileViewForMessages> profileViews;
			List<UserProfileViewForMessages> profileViewsOne;

			Map<String, List<UserProfileViewForMessages>> map = userService
					.getlistOfAcceptedInvitationByUser(request, userModel);
			profileViews = map.get("ByYou");
			profileViewsOne = map.get("ByOther");

			model.addAttribute("LatestMatch", profileViews);
			model.addAttribute("LatestMatchOne", profileViewsOne);

			return new ModelAndView("latest_Accepted_conversationView");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/rejected_conversation", method = RequestMethod.POST)
	public ModelAndView rejected_conversation(ModelMap model,
			HttpServletRequest request) {

		JSONObject obj;
		try {

			HttpSession httpSession = request.getSession();
			UserModel userModel = (UserModel) httpSession
					.getAttribute("ActUserId");
			UserModelDetails modelDetails = userService
					.getUserdetails(userModel);

			List<UserProfileViewForMessages> profileViews;
			List<UserProfileViewForMessages> profileViewsOne;

			Map<String, List<UserProfileViewForMessages>> map = userService
					.getlistOfRejectedInvitationByUser(request, userModel);
			profileViews = map.get("ByYou");
			profileViewsOne = map.get("ByOther");

			model.addAttribute("LatestMatch", profileViews);
			model.addAttribute("LatestMatchOne", profileViewsOne);

			return new ModelAndView("latest_Rejected_conversationView");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/refine_search_conversation", method = RequestMethod.GET)
	public ModelAndView refine_search_conversation(
			ModelMap model,
			HttpServletRequest request,
			@RequestParam("refineheightSelectPartnerFirst") final String refineheightSelectPartnerFirst,
			@RequestParam("refineheightSelectPartnerSecond") final String refineheightSelectPartnerSecond,
			@RequestParam("refineageFrom") final String refineageFrom,
			@RequestParam("refineageTo") final String refineageTo,
			@RequestParam("refinematerialStatus") final String refinematerialStatus,
			@RequestParam("refinereligionSelectPartner") final String refinereligionSelectPartner,
			@RequestParam("casteSelect") final String casteSelect,
			@RequestParam("refineoccupationCategorySelect") final String refineoccupationCategorySelect,
			@RequestParam("checkBoxDistrict") String checkBoxDistrict) {

		try {
			HttpSession httpSession = request.getSession();
			UserModel userModel = (UserModel) httpSession
					.getAttribute("ActUserId");
			UserModelDetails modelDetails = userService
					.getUserdetails(userModel);

			List<UserProfileView> profileViews;
			if (modelDetails != null && "male".equalsIgnoreCase(modelDetails.getGender())) {
				profileViews = userService.getlistOfRefineSearchByUser(request,
						userModel, "female", refineheightSelectPartnerFirst,
						refineheightSelectPartnerSecond, refineageFrom,
						refineageTo, refinematerialStatus,
						refinereligionSelectPartner, casteSelect,
						refineoccupationCategorySelect, checkBoxDistrict);
			} else {
				profileViews = userService.getlistOfRefineSearchByUser(request,
						userModel, "male", refineheightSelectPartnerFirst,
						refineheightSelectPartnerSecond, refineageFrom,
						refineageTo, refinematerialStatus,
						refinereligionSelectPartner, casteSelect,
						refineoccupationCategorySelect, checkBoxDistrict);
			}

			model.addAttribute("LatestMatch", profileViews);

			return new ModelAndView("refine_searchConversationView");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/changeSortlistStatus", method = RequestMethod.POST)
	@ResponseBody
	public String changeSortlistStatus(ModelMap model,
			HttpServletRequest request) {
		JSONObject obj;
		JSONObject js = new JSONObject();
		try {
			obj = new JSONObject(request.getParameter("jsonObjectData"));
			final JSONObject jsonObj = obj;
			String sortlistedId = jsonObj.getString("sortlistedId");
			String status = jsonObj.getString("status");
			try {
				SortlistedProfileUserWithDate profileUserWithDate = new SortlistedProfileUserWithDate();
				profileUserWithDate.setSortlistId(Long.parseLong(sortlistedId));

				if (profileUserWithDate != null) {
				}
				profileUserWithDate = kitUpgradeAndPaymentService
						.getSortlistedProfileUserWithDateById(profileUserWithDate);
				profileUserWithDate.setStatusDate(new Date());
				profileUserWithDate.setStatus(status);
				kitUpgradeAndPaymentService
						.updateSortlistedProfileUserWithDate(profileUserWithDate);
			} catch (Exception e) {
				e.printStackTrace();
			}
			js.put("status", "Success");
			String json = js.toString();
			return json;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	@RequestMapping(value = "/invitation_conversation", method = RequestMethod.POST)
	public ModelAndView invitation_conversation(ModelMap model,
			HttpServletRequest request) {

		JSONObject obj;
		try {

			HttpSession httpSession = request.getSession();
			UserModel userModel = (UserModel) httpSession
					.getAttribute("ActUserId");
			UserModelDetails modelDetails = userService
					.getUserdetails(userModel);

			List<UserProfileViewForMessages> profileViews;
			List<UserProfileViewForMessages> profileViewsOne;

			Map<String, List<UserProfileViewForMessages>> map = userService
					.getlistOfInvitationByUser(request, userModel);
			profileViews = map.get("ByYou");
			profileViewsOne = map.get("ByOther");

			model.addAttribute("LatestMatch", profileViews);
			model.addAttribute("LatestMatchOne", profileViewsOne);

			return new ModelAndView("latest_invitation_conversationView");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/latest_sent_conversation", method = RequestMethod.POST)
	public ModelAndView latest_sent_conversation(ModelMap model,
			HttpServletRequest request) {

		JSONObject obj;
		try {

			HttpSession httpSession = request.getSession();
			UserModel userModel = (UserModel) httpSession
					.getAttribute("ActUserId");
			UserModelDetails modelDetails = userService
					.getUserdetails(userModel);

			List<UserProfileViewForMessages> profileViews;

			profileViews = userService.getlistOfSentMessagesAndEmail(request,
					userModel);

			model.addAttribute("LatestMatch", profileViews);

			return new ModelAndView("latest_sent_conversation");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/loadLatestUpgraded", method = RequestMethod.POST)
	public ModelAndView loadLatestUpgraded(ModelMap model,
			HttpServletRequest request) {

		JSONObject obj;
		try {

			HttpSession httpSession = request.getSession();
			UserModel userModel = (UserModel) httpSession
					.getAttribute("ActUserId");
			if (userModel == null) {
				try {
					Authentication auth = SecurityContextHolder.getContext().getAuthentication();
					if (auth != null && auth.getPrincipal() instanceof UserDetails) {
						UserDetails userDetails = (UserDetails) auth.getPrincipal();
						userModel = userService.getUserByUname(userDetails.getUsername());
						if (userModel != null) {
							httpSession.setAttribute("ActUserId", userModel);
							httpSession.setAttribute("ActUserName", userModel.getFirstName());
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			UserModelDetails modelDetails = userModel != null ? userService
					.getUserdetails(userModel) : null;

			obj = new JSONObject(request.getParameter("jsonObjectData"));
			final JSONObject jsonObj = obj;
			String length = jsonObj.optString("length", "10");

			List<UserModel> models = new ArrayList<>();
			Object vIds = jsonObj.opt("latestUpgradedVisitorIds");
			if (vIds != null) {
				String rawVisitorIds = vIds.toString().replaceAll("[\\[\\]\"\\s]", "");
				if (!rawVisitorIds.isEmpty()) {
					String[] visitorArray = rawVisitorIds.split(",");
					for (String string : visitorArray) {
						if (!string.equalsIgnoreCase("null") && !string.isEmpty()) {
							try {
								UserModel model2 = new UserModel();
								model2.setUserId(Long.parseLong(string));
								models.add(model2);
							} catch (NumberFormatException nfe) {
							}
						}
					}
				}
			}

			List<UserProfileView> profileViews;

			if (modelDetails != null && "male".equalsIgnoreCase(modelDetails.getGender())) {
				profileViews = userService
						.getListOFLatestUpgradedUserForHomeAccordingToGenderExcludeUserIds(
								"Female", models, request);
			} else {
				profileViews = userService
						.getListOFLatestUpgradedUserForHomeAccordingToGenderExcludeUserIds(
								"Male", models, request);
			}

			model.addAttribute("LatestMatch", profileViews);
			model.addAttribute("length", length);
			model.addAttribute("counterSlider", length);

			return new ModelAndView("loadLatestUpgraded");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/NotSeenYetProfilesView", method = RequestMethod.POST)
	public ModelAndView NotSeenYetProfilesView(ModelMap model,
			HttpServletRequest request) {

		JSONObject obj;
		try {

			HttpSession httpSession = request.getSession();
			UserModel userModel = (UserModel) httpSession
					.getAttribute("ActUserId");
			if (userModel == null) {
				try {
					Authentication auth = SecurityContextHolder.getContext().getAuthentication();
					if (auth != null && auth.getPrincipal() instanceof UserDetails) {
						UserDetails userDetails = (UserDetails) auth.getPrincipal();
						userModel = userService.getUserByUname(userDetails.getUsername());
						if (userModel != null) {
							httpSession.setAttribute("ActUserId", userModel);
							httpSession.setAttribute("ActUserName", userModel.getFirstName());
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			UserModelDetails modelDetails = userModel != null ? userService
					.getUserdetails(userModel) : null;

			obj = new JSONObject(request.getParameter("jsonObjectData"));
			final JSONObject jsonObj = obj;
			String length = jsonObj.optString("length", "10");

			List<UserModel> models = new ArrayList<>();
			Object vIds = jsonObj.opt("NotSeenYetProfiles");
			if (vIds != null) {
				String rawVisitorIds = vIds.toString().replaceAll("[\\[\\]\"\\s]", "");
				if (!rawVisitorIds.isEmpty()) {
					String[] visitorArray = rawVisitorIds.split(",");
					for (String string : visitorArray) {
						if (!string.equalsIgnoreCase("null") && !string.isEmpty()) {
							try {
								UserModel model2 = new UserModel();
								model2.setUserId(Long.parseLong(string));
								models.add(model2);
							} catch (NumberFormatException nfe) {
							}
						}
					}
				}
			}

			List<UserProfileView> profileViews;

			if (modelDetails != null && "male".equalsIgnoreCase(modelDetails.getGender())) {
				profileViews = userService
						.getListOFNotSeenYetProfilesExcludeUserIds("Female",
								models, request);
			} else {
				profileViews = userService
						.getListOFNotSeenYetProfilesExcludeUserIds("Male",
								models, request);
			}

			model.addAttribute("LatestMatch", profileViews);
			model.addAttribute("length", length);
			model.addAttribute("counterSlider", length);

			return new ModelAndView("NotSeenYetProfilesView");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/ViewedyetProfilesView", method = RequestMethod.POST)
	public ModelAndView ViewedyetProfilesView(ModelMap model,
			HttpServletRequest request) {

		JSONObject obj;
		try {

			HttpSession httpSession = request.getSession();
			UserModel userModel = (UserModel) httpSession
					.getAttribute("ActUserId");
			if (userModel == null) {
				try {
					Authentication auth = SecurityContextHolder.getContext().getAuthentication();
					if (auth != null && auth.getPrincipal() instanceof UserDetails) {
						UserDetails userDetails = (UserDetails) auth.getPrincipal();
						userModel = userService.getUserByUname(userDetails.getUsername());
						if (userModel != null) {
							httpSession.setAttribute("ActUserId", userModel);
							httpSession.setAttribute("ActUserName", userModel.getFirstName());
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			UserModelDetails modelDetails = userModel != null ? userService
					.getUserdetails(userModel) : null;

			obj = new JSONObject(request.getParameter("jsonObjectData"));
			final JSONObject jsonObj = obj;
			String length = jsonObj.optString("length", "10");

			List<UserModel> models = new ArrayList<>();
			Object vIds = jsonObj.opt("ViewedyetProfiles");
			if (vIds != null) {
				String rawVisitorIds = vIds.toString().replaceAll("[\\[\\]\"\\s]", "");
				if (!rawVisitorIds.isEmpty()) {
					String[] visitorArray = rawVisitorIds.split(",");
					for (String string : visitorArray) {
						if (!string.equalsIgnoreCase("null") && !string.isEmpty()) {
							try {
								UserModel model2 = new UserModel();
								model2.setUserId(Long.parseLong(string));
								models.add(model2);
							} catch (NumberFormatException nfe) {
							}
						}
					}
				}
			}

			List<UserProfileView> profileViews;

			if (modelDetails != null && "male".equalsIgnoreCase(modelDetails.getGender())) {
				profileViews = userService
						.getListOFViewedYetProfilesExcludeUserIds("Female",
								models, request);
			} else {
				profileViews = userService
						.getListOFViewedYetProfilesExcludeUserIds("Male",
								models, request);
			}

			model.addAttribute("LatestMatch", profileViews);
			model.addAttribute("length", length);
			model.addAttribute("counterSlider", length);

			return new ModelAndView("ViewedyetProfilesView");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/ShortlistedOrIntrestedView", method = RequestMethod.POST)
	public ModelAndView ShortlistedOrIntrestedView(ModelMap model,
			HttpServletRequest request) {

		JSONObject obj;
		try {

			HttpSession httpSession = request.getSession();
			UserModel userModel = (UserModel) httpSession
					.getAttribute("ActUserId");
			if (userModel == null) {
				try {
					Authentication auth = SecurityContextHolder.getContext().getAuthentication();
					if (auth != null && auth.getPrincipal() instanceof UserDetails) {
						UserDetails userDetails = (UserDetails) auth.getPrincipal();
						userModel = userService.getUserByUname(userDetails.getUsername());
						if (userModel != null) {
							httpSession.setAttribute("ActUserId", userModel);
							httpSession.setAttribute("ActUserName", userModel.getFirstName());
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			UserModelDetails modelDetails = userModel != null ? userService
					.getUserdetails(userModel) : null;

			obj = new JSONObject(request.getParameter("jsonObjectData"));
			final JSONObject jsonObj = obj;
			String length = jsonObj.optString("length", "10");

			List<UserModel> models = new ArrayList<>();
			Object vIds = jsonObj.opt("ShortlistedOrIntrested");
			if (vIds != null) {
				String rawVisitorIds = vIds.toString().replaceAll("[\\[\\]\"\\s]", "");
				if (!rawVisitorIds.isEmpty()) {
					String[] visitorArray = rawVisitorIds.split(",");
					for (String string : visitorArray) {
						if (!string.equalsIgnoreCase("null") && !string.isEmpty()) {
							try {
								UserModel model2 = new UserModel();
								model2.setUserId(Long.parseLong(string));
								models.add(model2);
							} catch (NumberFormatException nfe) {
							}
						}
					}
				}
			}

			List<UserProfileView> profileViews;

			if (modelDetails != null && "male".equalsIgnoreCase(modelDetails.getGender())) {
				profileViews = userService
						.getListOFShortlistedOrIntrestedProfilesExcludeUserIds(
								"Female", models, request);
			} else {
				profileViews = userService
						.getListOFShortlistedOrIntrestedProfilesExcludeUserIds(
								"Male", models, request);
			}

			model.addAttribute("LatestMatch", profileViews);
			model.addAttribute("length", length);
			model.addAttribute("counterSlider", length);

			return new ModelAndView("ShortlistedOrIntrestedView");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/CustomSearch", method = RequestMethod.POST)
	public ModelAndView customSearch(@RequestParam("searchId") String searchId,
									 @RequestParam("searchName") String searchName,
									 @RequestParam("searchRegDate") String searchRegDate,
									 @RequestParam("searchDOB") String searchDOB,
									 ModelMap model, HttpServletRequest request) {
		try {
			HttpSession httpSession = request.getSession();
			UserModel m = (UserModel) httpSession.getAttribute("ActUserId");
			
			List<UserProfileView> profileViews = userService.getListOFCustomSearchProfiles(searchId, searchName, searchRegDate, searchDOB, m);
			model.addAttribute("LatestMatch", profileViews);
			
			return new ModelAndView("CustomSearchView");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/userHome", method = RequestMethod.GET)
	public ModelAndView loginSuccessUserHome(ModelMap model,
			HttpServletRequest request) {

		UserDetails user = (UserDetails) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		String name = user.getUsername(); // get logged in username
		UserModel userModel = userService.getUserByUname(name);
		Kit_Upgrade_Or_Reg_Details kitUpgradeDetails = null;
		List<SortlistedProfileUserWithDate> sortlistedProfileList = null;
		try {

			kitUpgradeDetails = kitService
					.getKitUpgradeAndRegistationDetailByUserModel(userModel);
			HttpSession httpSession1 = request.getSession();
			httpSession1.setAttribute("expiryDate",kitUpgradeDetails.getExpiryDate());
			model.addAttribute("expDate", kitUpgradeDetails.getExpiryDate());
			if (kitUpgradeDetails.getKit_id() != null) {
				model.addAttribute("paidtype", "paid");
			} else {
				model.addAttribute("paidtype", "unpaid");
			}

		} catch (Exception e) {

		}

		UserModelDetails usermodelDetails = userService.getUserdetails(userModel);
		try {
			model.addAttribute("userEmail", usermodelDetails.getEmail());
		} catch (Exception e) {
		}
		model.addAttribute("userPass", userModel.getPassword());
		model.addAttribute("loguser", userModel.getUserId());
		model.addAttribute("firstName", userModel.getFirstName());

		HttpSession httpSession = request.getSession();
		httpSession.setAttribute("ActUserName", userModel.getFirstName());
		httpSession.setAttribute("ActUserId", userModel);
		try {	
			httpSession.setAttribute("userEmail", usermodelDetails.getEmail());
		} catch (Exception e) {
		}
		httpSession.setAttribute("userPass", userModel.getPassword());

		UserModelImagesName imagesObj = userService.getImageNameAccordingToUserModel(userModel);
		String imageName;
		if (imagesObj != null) {
			try {
				String img[] = imagesObj.getImageNames().split("#");
				imageName = img[0];
				httpSession.setAttribute("UserProfile", imageName);
			} catch (Exception e) {

			}
		} else {
			httpSession.setAttribute("UserProfile", "userImage.png");
		}
		List<UserProfileView> profileViews;
		List<UserProfileView> onlineProfiles;

		long latestMatchCount = 0;
		long latestUpgraded = 0;
		long notseenyet = 0;
		long seenyet = 0;
		long sortlisted = 0;

		UserModelDetails modelDetails = userService.getUserdetails(userModel);
		sortlistedProfileList = userService.getListOFShortlistedUserProfileByUser(userModel);
		
		// List<OnlineUser>onlineuserlist=null;
		if (modelDetails != null && "male".equalsIgnoreCase(modelDetails.getGender())) {
			profileViews = userService.getListOFProfileForHomeAccordingToGender("Female", request);
			onlineProfiles = userService.getListOFProfileForChattingAccordingToGender("Female",request);
				long latestMachesTotalCount= userService.getCountOfAllLatestMatchAccordingToGender("Female");
				latestMatchCount = (latestMachesTotalCount + 25000) ;
			latestUpgraded = userService.getCountOfAllLatestUpgradedMatchAccordingToGender("Female");
			seenyet = userService.getCountOfAllSeenYetToGender("Female",userModel);
			sortlisted = userService.getCountOfShortlistedToGender("Female",userModel);
			// onlineuserlist=userService.getListOfOnlineUser("Female");

		} else {
			profileViews = userService.getListOFProfileForHomeAccordingToGender("Male", request);
			onlineProfiles = userService.getListOFProfileForChattingAccordingToGender("Male",request);
		   long latestMachesTotalCount = userService.getCountOfAllLatestMatchAccordingToGender("Male");
		   latestMatchCount = (latestMachesTotalCount + 35000) ;
			latestUpgraded = userService.getCountOfAllLatestUpgradedMatchAccordingToGender("Male");
			seenyet = userService.getCountOfAllSeenYetToGender("Male",userModel);
			sortlisted = userService.getCountOfShortlistedToGender("Male",userModel);
			// onlineuserlist=userService.getListOfOnlineUser("Male");
		}
		try {
			notseenyet = latestMatchCount - seenyet;
		} catch (Exception e) {

		}
		try {

			JSONObject js = new JSONObject();
			ObjectWriter ow = new ObjectMapper().writer()
					.withDefaultPrettyPrinter();
			js.put("LatestMatch", ow.writeValueAsString(profileViews));
			String json = js.toString();
			// System.out.println("onlinecount"+onlineProfiles.size());
			model.addAttribute("onlinecount", onlineProfiles.size());
			model.addAttribute("LatestMatch", profileViews);
			model.addAttribute("onlineProfiles", onlineProfiles);
			model.addAttribute("latestMatchCount", latestMatchCount);
			model.addAttribute("latestUpgraded", latestUpgraded);
			model.addAttribute("seenyet", seenyet);
			model.addAttribute("notseenyet", notseenyet);
			model.addAttribute("sortlisted", sortlisted);
			model.addAttribute("sortlistedProfileList", sortlistedProfileList);
			List<Country> countries = locationService.getListOfcountry();
			List<State_Country> state_Countries = locationService.getListOfStateByCountryId(1 + "");

			List<Religion> religions = religionService.getListOfReligion();
			List<EducationCategory> educationCategories = educationOccupationService.getListOfEducationCategory();
			List<Occupation> occupations = educationOccupationService.loadListOfOccupation();

			model.addAttribute("imgPath", profileViewService.getImagePath().getPath_name());
			model.addAttribute("countries", countries);
			model.addAttribute("state_Countries", state_Countries);
			model.addAttribute("religions", religions);
			model.addAttribute("educationCategories", educationCategories);
			model.addAttribute("occupationCategories", occupations);
			model.addAttribute("delUserId", userModel.getUserId());

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ModelAndView("UserHome");

	}

	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public String registration(ModelMap model, HttpServletRequest request) {

		try {
			String fullname = request.getParameter("fullname");
			String ProfileCreatedFor = request
					.getParameter("ProfileCreatedFor");
			String gender = request.getParameter("gender");
			String dob = request.getParameter("dob");
			String email = request.getParameter("email");
			String mobile = request.getParameter("mobile");
			String userName = request.getParameter("userName");
			String password = request.getParameter("password");
			String kitUser = request.getParameter("kitUser");
			String kitId = request.getParameter("kitId");
			String kitPassword = request.getParameter("kitPassword");
			String hiddenemail= request.getParameter("hiddenemail");
			Kit kit = null;
			if (kitUser != null && kitUser.equalsIgnoreCase("RegisteredKit")) {
				kit = kitService.findKitByKitUserNamePassword(kitId,
						kitPassword);
			}

			if (kit != null && kit.getStatus_id() != null && kit.getStatus_id().getStatus_name() != null) {
				if (kit.getStatus_id().getStatus_name()
						.equalsIgnoreCase("Active")) {

				} else if (kit.getStatus_id().getStatus_name()
						.equalsIgnoreCase("Disactive")) {

				}
			}

			model.addAttribute("fullname", fullname);
			model.addAttribute("ProfileCreatedFor", ProfileCreatedFor);
			model.addAttribute("gender", gender);
			model.addAttribute("dob", dob);
			model.addAttribute("email", email);
			model.addAttribute("mobile", mobile);
			model.addAttribute("userName", userName);
			model.addAttribute("password", password);
			model.addAttribute("kitUser", kitUser);
			model.addAttribute("kitId", kitId);
			model.addAttribute("kitPassword", kitPassword);
			model.addAttribute("hiddenemail", hiddenemail);
			model.addAttribute("DivHide", true);

		} catch (Exception e) {
			e.printStackTrace();
		}

		List<Country> countries = locationService.getListOfcountry();
		List<State_Country> state_Countries = locationService
				.getListOfStateByCountryId(1 + "");

		List<Religion> religions = religionService.getListOfReligion();
		List<EducationCategory> educationCategories = educationOccupationService
				.getListOfEducationCategory();
		List<OccupationCategory> occupationCategories = educationOccupationService
				.getListOfOccupation();

		model.addAttribute("countries", countries);
		model.addAttribute("state_Countries", state_Countries);
		model.addAttribute("religions", religions);
		model.addAttribute("educationCategories", educationCategories);
		model.addAttribute("occupationCategories", occupationCategories);

		return "registration";

	}

	private Serializable Long(int i) {
		// TODO Auto-generated method stub
		return null;
	}

	@RequestMapping(value = "/aboutus", method = RequestMethod.GET)
	public String aboutus(ModelMap model, HttpServletRequest request) {
		// createSetup.makeSetup(request);
		// HttpSession session = request.getSession(true);
		// session.setAttribute("userId", );
		
		AboutUs obj=locationService.getAboutUsDetails();
		model.addAttribute("aboutus", obj);

		return "aboutus";
	}

	@RequestMapping(value = "/contactus", method = RequestMethod.GET)
	public String contactus(ModelMap model, HttpServletRequest request) {

		List<BranchModel> branchList = branchService.getBranchList();

		model.addAttribute("branchList", branchList);
		return "contactus";
	}
	
	@RequestMapping(value = "/FAQs", method = RequestMethod.GET)
	public String FAQs(ModelMap model, HttpServletRequest request) {
        List<FAQS> faqsList=locationService.getListOfFAQs();
		model.addAttribute("faqsList", faqsList);
		return "FAQs";
	}

	@RequestMapping(value = "/magazine", method = RequestMethod.GET)
	public String magazineUrl(ModelMap model, HttpServletRequest request) {
		try {
			List<MagazinePdf> magazinePdfList = magazineService.getMagazinePdfFilesName();
			model.addAttribute("magazinePdfList", magazinePdfList);
			//Map<String, String []> map=new HashMap();	
			LinkedHashMap<String, String[]> map = new LinkedHashMap<>();
			
			for (MagazinePdf imgObj : magazinePdfList) {
				try {
					String keyName=imgObj.getMagazineImageNames();
					keyName=keyName.substring(0,keyName.length()-4);
					String imString = imgObj.getMagzineImgeNamesWithHash();
					String imgs[] = imString.split("#");
					map.put(keyName, imgs);
				} catch (Exception e) 
				{
					e.printStackTrace();
				}	
			}
			model.addAttribute("imgMap", map);
		} catch (Exception e) {
		}
		
		return "magazineDetails";
	}

	@RequestMapping(value = "/TermAndCondition", method = RequestMethod.GET)
	public String termAndCondition(ModelMap model, HttpServletRequest request) {

		TermAndCondition term=locationService.getTermAndCondition();
		model.addAttribute("term", term);
		
		return "TermAndCondition";
	}
	
	@RequestMapping(value = "/privacyPolicy", method = RequestMethod.GET)
	public String privacyPolicy(ModelMap model, HttpServletRequest request) {
		String privacyAndPolicy=locationService.getPrivacyPolicy();
		model.addAttribute("privacyAndPolicy", privacyAndPolicy);
		return "PrivacyPolicy";
	}


	
	@RequestMapping(value = "/changeAdminPass", method = RequestMethod.POST)
	@ResponseBody
	public String changeAdminPass(ModelMap model, HttpServletRequest request) 
	{
		try {
			String currentUserName = request.getParameter("currentUserName");
			String newUserName = request.getParameter("newUserName");
			String currentPass = request.getParameter("currentPass");
			String newPass = request.getParameter("newPass");
			UserModel userModel = userService.getadminDetails();
			if(userModel.getUserName().equals(currentUserName) || userModel.getPassword().equals(currentPass)){	
				userModel.setUserName(newUserName);
				userModel.setPassword(newPass);
				userService.updateUser(userModel);
			}
		
			return "success";

		} catch (Exception e) 
		{
			e.printStackTrace();
		}

		return "success";
	}
	
	
	
}
