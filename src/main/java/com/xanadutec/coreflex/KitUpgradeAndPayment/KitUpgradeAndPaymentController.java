package com.xanadutec.coreflex.KitUpgradeAndPayment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xanadutec.coreflex.Kit.KitService;
import com.xanadutec.coreflex.MsgEmailSender.MsgEmailSender;
import com.xanadutec.coreflex.dealer.DealerService;
import com.xanadutec.coreflex.employee.EmployeeService;
import com.xanadutec.coreflex.model.Dealer;
import com.xanadutec.coreflex.model.DealerSellKitDetails;
import com.xanadutec.coreflex.model.EmployeeRegistrationDetails;
import com.xanadutec.coreflex.model.FcmNotification;
import com.xanadutec.coreflex.model.Kit;
import com.xanadutec.coreflex.model.Kit_Upgrade_Or_Reg_Details;
import com.xanadutec.coreflex.model.Kit_Upgrade_Or_Reg_History_Details;
import com.xanadutec.coreflex.model.Package;
import com.xanadutec.coreflex.model.SentMessagesEmail;
import com.xanadutec.coreflex.model.ServerInfo;
import com.xanadutec.coreflex.model.SortlistedProfileUserWithDate;
import com.xanadutec.coreflex.model.Status;
import com.xanadutec.coreflex.model.UserModel;
import com.xanadutec.coreflex.model.UserModelDetails;
import com.xanadutec.coreflex.notification.NotificationService;
import com.xanadutec.coreflex.packageAndKit.PackageAndKitService;
import com.xanadutec.coreflex.sms.smsService;
import com.xanadutec.coreflex.userTask.UserService;

@Controller
public class KitUpgradeAndPaymentController {

	@Autowired
	PackageAndKitService packageAndKitService;
	@Autowired
	UserService userService;
	@Autowired
	KitService kitService;
	@Autowired
	KitUpgradeAndPaymentService kitUpgradeAndPaymentService;
	@Autowired
	private smsService smsservice;
	@Autowired
	com.xanadutec.coreflex.setup.SetupService setupService;
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private NotificationService notificationService;
	@Autowired
	private DealerService dealerService;
	@Autowired
	private EmployeeService employeeService;

	@RequestMapping(value = "/KitUpgradeAndPayment", method = RequestMethod.GET)
	public ModelAndView KitUpgradeAndPayment(ModelMap model, HttpServletRequest request) {

		List<Package> packages = packageAndKitService.getListOfPackage();
		HttpSession httpSession = request.getSession();
		model.addAttribute("userEmail", httpSession.getAttribute("userEmail"));
		model.addAttribute("userPass", httpSession.getAttribute("userPass"));
		model.addAttribute("packages", packages);
		return new ModelAndView("upgradeAndPayment");

	}

	@RequestMapping(value = "/ShortlistSendEmail", method = RequestMethod.POST)
	@ResponseBody
	public String ShortlistSendEmail(ModelMap model, HttpServletRequest request) {
		JSONObject js = new JSONObject();
		JSONObject obj;
		try {
			obj = new JSONObject(request.getParameter("jsonObjectData"));
			final JSONObject jsonObj = obj;
			String ShortlistEmail = jsonObj.getString("ShortlistEmail");
			String ShortlistVisitedId = jsonObj.getString("ShortlistVisitedId");
			HttpSession httpSession = request.getSession();
			UserModel userModel = (UserModel) httpSession.getAttribute("ActUserId");
			try {
				List<Kit_Upgrade_Or_Reg_Details> kit_Upgrade_Or_Reg_Details = kitUpgradeAndPaymentService
						.getKit_upgrade_or_reg_detailsByUserModel(userModel);

				Kit_Upgrade_Or_Reg_Details details;
				if (kit_Upgrade_Or_Reg_Details.size() > 0) {

					details = kit_Upgrade_Or_Reg_Details.get(0);
					// in milliseconds
					long diff = details.getExpiryDate().getTime() - new Date().getTime();

					// long diffSeconds = diff / 1000 % 60;
					long diffMinutes = diff / (60 * 1000) % 60;
					long diffHours = diff / (60 * 60 * 1000) % 24;
					long diffDays = diff / (24 * 60 * 60 * 1000);
					int flag = 0;
					if (diffDays >= 1) {
						flag = 1;
					} else if (diffHours >= 1) {
						flag = 1;
					} else if (diffMinutes >= 1) {
						flag = 1;
					}

					if (flag == 1) 
					{
						int profileCount = details.getNo_of_profile();
						UserModel visitedModel = new UserModel();
						visitedModel.setUserId(Long.parseLong(ShortlistVisitedId));

						SortlistedProfileUserWithDate sortlistedProfileUserWithDate = kitUpgradeAndPaymentService
								.getSortlistedProfileUser(userModel, visitedModel);

						if (profileCount > 0 || sortlistedProfileUserWithDate != null) {
							js.put("profileCount", true);

							if (details.getNo_of_email() > 0) {

								UserModelDetails modelDetails = userService.getUserdetails(visitedModel);
								
								details.setNo_of_email(details.getNo_of_email() - 1);
								kitUpgradeAndPaymentService.updateKit_upgrade_or_reg_details(details);

								SentMessagesEmail sentMessagesEmail = new SentMessagesEmail();
								sentMessagesEmail.setMsgDate(new Date());
								sentMessagesEmail.setReceived_msg_email_UserModel(visitedModel);
								sentMessagesEmail.setSent_msg_email_userModel(userModel);
								sentMessagesEmail.setMessage(ShortlistEmail);
								sentMessagesEmail.setType("EMAIL");
								kitUpgradeAndPaymentService.saveSentMessagesEmail(sentMessagesEmail);

							} else {
								js.put("profileCount", false);
							}

						} else {
							js.put("profileCount", false);
						}
					}

				} else {
					js.put("profileCount", false);
				}

				ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
				String json = js.toString();
				return json;

			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	@RequestMapping(value = "/ShortlistSendMSG", method = RequestMethod.POST)
	@ResponseBody
	public String ShortlistSendMSG(ModelMap model, HttpServletRequest request) {
		JSONObject js = new JSONObject();
		JSONObject obj;
		try {
			obj = new JSONObject(request.getParameter("jsonObjectData"));
			final JSONObject jsonObj = obj;
			String ShortlistMSG = jsonObj.getString("ShortlistMSG");
			String ShortlistVisitedId = jsonObj.getString("ShortlistVisitedId");

			HttpSession httpSession = request.getSession();
			UserModel userModel = (UserModel) httpSession.getAttribute("ActUserId");
			try {
				List<Kit_Upgrade_Or_Reg_Details> kit_Upgrade_Or_Reg_Details = kitUpgradeAndPaymentService
						.getKit_upgrade_or_reg_detailsByUserModel(userModel);

				Kit_Upgrade_Or_Reg_Details details;
				if (kit_Upgrade_Or_Reg_Details.size() > 0) {

					details = kit_Upgrade_Or_Reg_Details.get(0);
					// in milliseconds
					long diff = details.getExpiryDate().getTime() - new Date().getTime();

					// long diffSeconds = diff / 1000 % 60;
					long diffMinutes = diff / (60 * 1000) % 60;
					long diffHours = diff / (60 * 60 * 1000) % 24;
					long diffDays = diff / (24 * 60 * 60 * 1000);
					int flag = 0;
					if (diffDays >= 1) {
						flag = 1;
					} else if (diffHours >= 1) {
						flag = 1;
					} else if (diffMinutes >= 1) {
						flag = 1;
					}

					if (flag == 1) {

						int profileCount = details.getNo_of_profile();

						UserModel visitedModel = new UserModel();
						visitedModel.setUserId(Long.parseLong(ShortlistVisitedId));

						SortlistedProfileUserWithDate sortlistedProfileUserWithDate = kitUpgradeAndPaymentService
								.getSortlistedProfileUser(userModel, visitedModel);

						if (profileCount > 0 || sortlistedProfileUserWithDate != null) {

							js.put("profileCount", true);

							if (details.getNo_of_email() > 0) {
								UserModelDetails modelDetails = userService.getUserdetails(visitedModel);
								// send email code goes here
								// MsgEmailSender.sendSms(modelDetails.getMobileNum(),
								// ShortlistMSG, "", smsservice);
								details.setNo_of_msg(details.getNo_of_msg() - 1);
								kitUpgradeAndPaymentService.updateKit_upgrade_or_reg_details(details);

								SentMessagesEmail sentMessagesEmail = new SentMessagesEmail();
								sentMessagesEmail.setMsgDate(new Date());
								sentMessagesEmail.setReceived_msg_email_UserModel(visitedModel);
								sentMessagesEmail.setSent_msg_email_userModel(userModel);
								sentMessagesEmail.setMessage(ShortlistMSG);
								sentMessagesEmail.setType("MESSAGE");
								kitUpgradeAndPaymentService.saveSentMessagesEmail(sentMessagesEmail);

							} else {
								js.put("profileCount", false);
							}

						} else {
							js.put("profileCount", false);
						}
					}

				} else {
					js.put("profileCount", false);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			String json = js.toString();
			return json;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	@RequestMapping(value = "/ShortlistUser", method = RequestMethod.POST)
	@ResponseBody
	public String ShortlistUser(ModelMap model, HttpServletRequest request) {
		JSONObject js = new JSONObject();

		JSONObject obj;
		try {

			obj = new JSONObject(request.getParameter("jsonObjectData"));
			final JSONObject jsonObj = obj;
			String VisitorId = jsonObj.getString("VisitorId");

			HttpSession httpSession = request.getSession();
			UserModel userModel = (UserModel) httpSession.getAttribute("ActUserId");

			try {
				List<Kit_Upgrade_Or_Reg_Details> kit_Upgrade_Or_Reg_Details = kitUpgradeAndPaymentService
						.getKit_upgrade_or_reg_detailsByUserModel(userModel);

				Kit_Upgrade_Or_Reg_Details details;
				if (kit_Upgrade_Or_Reg_Details.size() > 0) {

					details = kit_Upgrade_Or_Reg_Details.get(0);

					long diff = details.getExpiryDate().getTime() - new Date().getTime();

					long diffMinutes = diff / (60 * 1000) % 60;
					long diffHours = diff / (60 * 60 * 1000) % 24;
					long diffDays = diff / (24 * 60 * 60 * 1000);

					int flag = 0;
					if (diffDays >= 1) {
						flag = 1;
					} else if (diffHours >= 1) {
						flag = 1;
					} else if (diffMinutes >= 1) {
						flag = 1;
					}

					if (flag == 1) {

						int profileCount = details.getNo_of_profile();
						UserModel visitedModel = new UserModel();
						visitedModel.setUserId(Long.parseLong(VisitorId));

						SortlistedProfileUserWithDate sortlistedProfileUserWithDate = kitUpgradeAndPaymentService
								.getSortlistedProfileUser(userModel, visitedModel);

						if (profileCount > 0 || sortlistedProfileUserWithDate != null) {
							js.put("profileCount", true);

							if (sortlistedProfileUserWithDate == null) {
								sortlistedProfileUserWithDate = new SortlistedProfileUserWithDate();
								sortlistedProfileUserWithDate.setSortlistedDate(new Date());
								sortlistedProfileUserWithDate.setUserModel(userModel);
								sortlistedProfileUserWithDate.setSortlistedUserModel(visitedModel);
								sortlistedProfileUserWithDate.setStatus("INVITATION");
								kitUpgradeAndPaymentService
										.saveSortlistedProfileUserWithDate(sortlistedProfileUserWithDate);

								details.setNo_of_profile(details.getNo_of_profile() - 1);
								kitUpgradeAndPaymentService.updateKit_upgrade_or_reg_details(details);

							}
							UserModelDetails modelDetails = userService.getUserdetails(visitedModel);

							Calendar cal = Calendar.getInstance();
							cal.setTime(modelDetails.getDateOfBirth());

							js.put("dob", cal.get(Calendar.YEAR) + " - "
									+ Integer.parseInt(cal.get(Calendar.MONTH) + "") + " - " + cal.get(Calendar.DATE));
							js.put("mobile", modelDetails.getMobileNum());
							js.put("email", modelDetails.getEmail());
							String landmarkStr = modelDetails.getLandmark();
							js.put("landmark", (landmarkStr == null || landmarkStr.trim().equalsIgnoreCase("undefined")) ? "" : landmarkStr);
							js.put("Address",
									modelDetails.getDistricts().getDistrict_Name() + ",  "
											+ modelDetails.getState_Country().getState_Name() + ",  "
											+ modelDetails.getCountry().getCountry_Name());

						} else {
							js.put("profileCount", false);
						}
					}

				} else {
					js.put("profileCount", false);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

			try {

				Session session = sessionFactory.openSession();
				final UserModel userFrom = userModel;
				final UserModel userTo = (UserModel) session.get(UserModel.class, Long.parseLong(VisitorId));
			
				final HttpServletRequest request2 = request;
				session.close();
				Thread tt = new Thread(new Runnable() {

					@Override
					public void run() {

						try {

							FcmNotification fcmNotification = notificationService.getFcmNotification();
						
							notificationService.sendSortListedFcmNotification(userFrom, userTo, fcmNotification,
									request2);

						} catch (Exception e) {

						}

					}
				});

				tt.start();

			} catch (Exception e) 
			{
               e.printStackTrace();
			}

			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			String json = js.toString();
			return json;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	@RequestMapping(value = "/upgradePackage", method = RequestMethod.POST)
	@ResponseBody
	public String upgradePackage(ModelMap model, HttpServletRequest request) {

		JSONObject obj;
		try {

			HttpSession httpSession = request.getSession();
			UserModel userModel = (UserModel) httpSession.getAttribute("ActUserId");
			UserModelDetails modelDetails = userService.getUserdetails(userModel);
			obj = new JSONObject(request.getParameter("jsonObjectData"));
			final JSONObject jsonObj = obj;
			String KitId = jsonObj.getString("kitId");
			String KitPassword = jsonObj.getString("kitPass");

			Kit kit = kitService.findKitByKitUserNamePassword(KitId, KitPassword);

			if (kit == null) {
				return "InvalidKit";
			} else {
				if (kit.getStatus_id().getStatus_id() == 1) {
					return "AlreadyAvailable";
				} else {

					Status status_id = new Status();
					status_id.setStatus_id(1);
					kit.setStatus_id(status_id);
					kitService.updateKitStatus(kit);
					userModel.setKit(kit);
					userService.addUser(userModel);

					List<Kit_Upgrade_Or_Reg_Details> list_kit_Upgrade_Or_Reg_Details = kitUpgradeAndPaymentService
							.getKit_upgrade_or_reg_detailsByUserModel(userModel);

					if (list_kit_Upgrade_Or_Reg_Details.size() == 0) {
						Kit_Upgrade_Or_Reg_History_Details history_Details = new Kit_Upgrade_Or_Reg_History_Details();
						Kit_Upgrade_Or_Reg_Details kit_Upgrade_Or_Reg_Details = new Kit_Upgrade_Or_Reg_Details();
						kit_Upgrade_Or_Reg_Details.setKit_id(kit);
						kit_Upgrade_Or_Reg_Details
								.setNo_of_email(Integer.parseInt(kit.getPackage_id().getNo_of_email()));
						kit_Upgrade_Or_Reg_Details
								.setNo_of_email(Integer.parseInt(kit.getPackage_id().getNo_of_email()));
						kit_Upgrade_Or_Reg_Details.setNo_of_msg(Integer.parseInt(kit.getPackage_id().getNo_of_msg()));
						kit_Upgrade_Or_Reg_Details
								.setNo_of_profile(Integer.parseInt(kit.getPackage_id().getNo_of_profiles()));
						kit_Upgrade_Or_Reg_Details.setUserModel(userModel);

						kit_Upgrade_Or_Reg_Details.setStatus("New");
						kit_Upgrade_Or_Reg_Details.setRegistrationDate(new Date());
						Calendar c = new GregorianCalendar();
						c.add(Calendar.DATE, Integer.parseInt(kit.getPackage_id().getNo_of_days()));
						Date expiredate = c.getTime();
						kit_Upgrade_Or_Reg_Details.setExpiryDate(expiredate);

						kitUpgradeAndPaymentService.saveKit_upgrade_or_reg_details(kit_Upgrade_Or_Reg_Details);
						try {
							history_Details.setExpiryDate(kit_Upgrade_Or_Reg_Details.getExpiryDate());
							history_Details.setRegistrationDate(kit_Upgrade_Or_Reg_Details.getRegistrationDate());
							history_Details.setActualUsedDate(new Date());
							history_Details.setDate(new Date());

							history_Details.setNo_of_msg(kit_Upgrade_Or_Reg_Details.getNo_of_msg());
							history_Details.setNo_of_email(kit_Upgrade_Or_Reg_Details.getNo_of_email());
							history_Details.setNo_of_profile(kit_Upgrade_Or_Reg_Details.getNo_of_profile());
							history_Details.setStatus(kit_Upgrade_Or_Reg_Details.getStatus());
							history_Details.setKit_id(kit_Upgrade_Or_Reg_Details.getKit_id());
							history_Details.setPayment(Double.parseDouble(kit.getPackage_id().getAmount()));
							history_Details.setUserModel(kit_Upgrade_Or_Reg_Details.getUserModel());
							kitService.saveKit_Upgrade_Or_Reg_History_Details(history_Details);
						} catch (Exception e) {

						}

					} else if (list_kit_Upgrade_Or_Reg_Details.size() > 0) {

						Kit_Upgrade_Or_Reg_History_Details history_Details = new Kit_Upgrade_Or_Reg_History_Details();
						Kit_Upgrade_Or_Reg_Details kit_Upgrade_Or_Reg_Details = list_kit_Upgrade_Or_Reg_Details.get(0);

						int old_no_msg = 0;
						int old_no_email = 0;
						int old_no_profile = 0;

						old_no_msg = kit_Upgrade_Or_Reg_Details.getNo_of_msg();
						old_no_email = kit_Upgrade_Or_Reg_Details.getNo_of_email();
						old_no_profile = kit_Upgrade_Or_Reg_Details.getNo_of_profile();

						kit_Upgrade_Or_Reg_Details.setKit_id(kit);
						kit_Upgrade_Or_Reg_Details
								.setNo_of_email(old_no_email + Integer.parseInt(kit.getPackage_id().getNo_of_email()));

						kit_Upgrade_Or_Reg_Details
								.setNo_of_msg(old_no_msg + Integer.parseInt(kit.getPackage_id().getNo_of_msg()));

						kit_Upgrade_Or_Reg_Details.setNo_of_profile(
								old_no_profile + Integer.parseInt(kit.getPackage_id().getNo_of_profiles()));

						kit_Upgrade_Or_Reg_Details.setUserModel(userModel);

						long diff = kit_Upgrade_Or_Reg_Details.getExpiryDate().getTime() - new Date().getTime();

						long diffMinutes = diff / (60 * 1000) % 60;
						long diffHours = diff / (60 * 60 * 1000) % 24;
						long diffDays = diff / (24 * 60 * 60 * 1000);
						int flag = 0;
						if (diffDays >= 1) {
							flag = 1;
						} else if (diffHours >= 1) {
							flag = 1;
						} else if (diffMinutes >= 1) {
							flag = 1;
						}

						if (flag == 1) {
							kit_Upgrade_Or_Reg_Details.setStatus("ReNew");

							Calendar c = new GregorianCalendar();
							c.setTime(kit_Upgrade_Or_Reg_Details.getExpiryDate());
							kit_Upgrade_Or_Reg_Details.setRegistrationDate(c.getTime());
							c.add(Calendar.DATE, Integer.parseInt(kit.getPackage_id().getNo_of_days()));
							Date expiredate = c.getTime();
							kit_Upgrade_Or_Reg_Details.setExpiryDate(expiredate);
						} else {
							kit_Upgrade_Or_Reg_Details.setStatus("ReNew");
							kit_Upgrade_Or_Reg_Details.setRegistrationDate(new Date());
							Calendar c = new GregorianCalendar();
							c.add(Calendar.DATE, Integer.parseInt(kit.getPackage_id().getNo_of_days()));
							Date expiredate = c.getTime();
							kit_Upgrade_Or_Reg_Details.setExpiryDate(expiredate);
						}

						kitUpgradeAndPaymentService.updateKit_upgrade_or_reg_details(kit_Upgrade_Or_Reg_Details);
						try {
							history_Details.setExpiryDate(kit_Upgrade_Or_Reg_Details.getExpiryDate());
							history_Details.setRegistrationDate(kit_Upgrade_Or_Reg_Details.getRegistrationDate());
							history_Details.setActualUsedDate(new Date());
							history_Details.setDate(new Date());
							history_Details.setNo_of_msg(kit_Upgrade_Or_Reg_Details.getNo_of_msg());
							history_Details.setNo_of_email(kit_Upgrade_Or_Reg_Details.getNo_of_email());
							history_Details.setNo_of_profile(kit_Upgrade_Or_Reg_Details.getNo_of_profile());
							history_Details.setStatus(kit_Upgrade_Or_Reg_Details.getStatus());
							history_Details.setKit_id(kit_Upgrade_Or_Reg_Details.getKit_id());
							history_Details.setPayment(Double.parseDouble(kit.getPackage_id().getAmount()));
							history_Details.setUserModel(kit_Upgrade_Or_Reg_Details.getUserModel());
							kitService.saveKit_Upgrade_Or_Reg_History_Details(history_Details);
						} catch (Exception e) {

						}

					}

					return "Success";
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/upgradePackageByAdmin", method = RequestMethod.POST)
	@ResponseBody
	public String upgradePackageByAdmin(ModelMap model, HttpServletRequest request) {

		JSONObject obj;
		try {

			obj = new JSONObject(request.getParameter("jsonObjectData"));
			final JSONObject jsonObj = obj;
			String KitId = jsonObj.getString("kitId");
			String KitPassword = jsonObj.getString("kitPass");
			String userId = jsonObj.getString("userId");
			Session session = sessionFactory.openSession();
			UserModel userModel = (UserModel) session.get(UserModel.class, Long.parseLong(userId));
			session.close();

			Kit kit = kitService.findKitByKitUserNamePassword(KitId, KitPassword);

			if (kit == null) {
				return "InvalidKit";
			} else {
				if (kit.getStatus_id().getStatus_id() == 1) {
					return "AlreadyAvailable";
				} else {

					Status status_id = new Status();
					status_id.setStatus_id(1);
					kit.setStatus_id(status_id);
					kitService.updateKitStatus(kit);
					userModel.setKit(kit);
					userService.addUser(userModel);

					List<Kit_Upgrade_Or_Reg_Details> list_kit_Upgrade_Or_Reg_Details = kitUpgradeAndPaymentService
							.getKit_upgrade_or_reg_detailsByUserModel(userModel);

					if (list_kit_Upgrade_Or_Reg_Details.size() == 0) {
						Kit_Upgrade_Or_Reg_History_Details history_Details = new Kit_Upgrade_Or_Reg_History_Details();
						Kit_Upgrade_Or_Reg_Details kit_Upgrade_Or_Reg_Details = new Kit_Upgrade_Or_Reg_Details();
						kit_Upgrade_Or_Reg_Details.setKit_id(kit);
						kit_Upgrade_Or_Reg_Details
								.setNo_of_email(Integer.parseInt(kit.getPackage_id().getNo_of_email()));
						kit_Upgrade_Or_Reg_Details
								.setNo_of_email(Integer.parseInt(kit.getPackage_id().getNo_of_email()));
						kit_Upgrade_Or_Reg_Details.setNo_of_msg(Integer.parseInt(kit.getPackage_id().getNo_of_msg()));
						kit_Upgrade_Or_Reg_Details
								.setNo_of_profile(Integer.parseInt(kit.getPackage_id().getNo_of_profiles()));
						kit_Upgrade_Or_Reg_Details.setUserModel(userModel);

						kit_Upgrade_Or_Reg_Details.setStatus("New");
						kit_Upgrade_Or_Reg_Details.setRegistrationDate(new Date());
						Calendar c = new GregorianCalendar();
						c.add(Calendar.DATE, Integer.parseInt(kit.getPackage_id().getNo_of_days()));
						Date expiredate = c.getTime();
						kit_Upgrade_Or_Reg_Details.setExpiryDate(expiredate);

						kitUpgradeAndPaymentService.saveKit_upgrade_or_reg_details(kit_Upgrade_Or_Reg_Details);
						try {
							history_Details.setExpiryDate(kit_Upgrade_Or_Reg_Details.getExpiryDate());
							history_Details.setRegistrationDate(kit_Upgrade_Or_Reg_Details.getRegistrationDate());
							history_Details.setActualUsedDate(new Date());
							history_Details.setDate(new Date());

							history_Details.setNo_of_msg(kit_Upgrade_Or_Reg_Details.getNo_of_msg());
							history_Details.setNo_of_email(kit_Upgrade_Or_Reg_Details.getNo_of_email());
							history_Details.setNo_of_profile(kit_Upgrade_Or_Reg_Details.getNo_of_profile());
							history_Details.setStatus(kit_Upgrade_Or_Reg_Details.getStatus());
							history_Details.setKit_id(kit_Upgrade_Or_Reg_Details.getKit_id());
							history_Details.setPayment(Double.parseDouble(kit.getPackage_id().getAmount()));
							history_Details.setUserModel(kit_Upgrade_Or_Reg_Details.getUserModel());
							kitService.saveKit_Upgrade_Or_Reg_History_Details(history_Details);
						} catch (Exception e) {

						}

					} else if (list_kit_Upgrade_Or_Reg_Details.size() > 0) {

						Kit_Upgrade_Or_Reg_History_Details history_Details = new Kit_Upgrade_Or_Reg_History_Details();
						Kit_Upgrade_Or_Reg_Details kit_Upgrade_Or_Reg_Details = list_kit_Upgrade_Or_Reg_Details.get(0);

						int old_no_msg = 0;
						int old_no_email = 0;
						int old_no_profile = 0;

						old_no_msg = kit_Upgrade_Or_Reg_Details.getNo_of_msg();
						old_no_email = kit_Upgrade_Or_Reg_Details.getNo_of_email();
						old_no_profile = kit_Upgrade_Or_Reg_Details.getNo_of_profile();

						kit_Upgrade_Or_Reg_Details.setKit_id(kit);
						kit_Upgrade_Or_Reg_Details
								.setNo_of_email(old_no_email + Integer.parseInt(kit.getPackage_id().getNo_of_email()));

						kit_Upgrade_Or_Reg_Details
								.setNo_of_msg(old_no_msg + Integer.parseInt(kit.getPackage_id().getNo_of_msg()));

						kit_Upgrade_Or_Reg_Details.setNo_of_profile(
								old_no_profile + Integer.parseInt(kit.getPackage_id().getNo_of_profiles()));

						kit_Upgrade_Or_Reg_Details.setUserModel(userModel);

						long diff = kit_Upgrade_Or_Reg_Details.getExpiryDate().getTime() - new Date().getTime();

						long diffMinutes = diff / (60 * 1000) % 60;
						long diffHours = diff / (60 * 60 * 1000) % 24;
						long diffDays = diff / (24 * 60 * 60 * 1000);
						int flag = 0;
						if (diffDays >= 1) {
							flag = 1;
						} else if (diffHours >= 1) {
							flag = 1;
						} else if (diffMinutes >= 1) {
							flag = 1;
						}

						if (flag == 1) {
							kit_Upgrade_Or_Reg_Details.setStatus("ReNew");

							Calendar c = new GregorianCalendar();
							c.setTime(kit_Upgrade_Or_Reg_Details.getExpiryDate());
							kit_Upgrade_Or_Reg_Details.setRegistrationDate(c.getTime());
							c.add(Calendar.DATE, Integer.parseInt(kit.getPackage_id().getNo_of_days()));
							Date expiredate = c.getTime();
							kit_Upgrade_Or_Reg_Details.setExpiryDate(expiredate);
						} else {
							kit_Upgrade_Or_Reg_Details.setStatus("ReNew");
							kit_Upgrade_Or_Reg_Details.setRegistrationDate(new Date());
							Calendar c = new GregorianCalendar();
							c.add(Calendar.DATE, Integer.parseInt(kit.getPackage_id().getNo_of_days()));
							Date expiredate = c.getTime();
							kit_Upgrade_Or_Reg_Details.setExpiryDate(expiredate);
						}

						kitUpgradeAndPaymentService.updateKit_upgrade_or_reg_details(kit_Upgrade_Or_Reg_Details);
						try {
							history_Details.setExpiryDate(kit_Upgrade_Or_Reg_Details.getExpiryDate());
							history_Details.setRegistrationDate(kit_Upgrade_Or_Reg_Details.getRegistrationDate());
							history_Details.setActualUsedDate(new Date());
							history_Details.setDate(new Date());
							history_Details.setNo_of_msg(kit_Upgrade_Or_Reg_Details.getNo_of_msg());
							history_Details.setNo_of_email(kit_Upgrade_Or_Reg_Details.getNo_of_email());
							history_Details.setNo_of_profile(kit_Upgrade_Or_Reg_Details.getNo_of_profile());
							history_Details.setStatus(kit_Upgrade_Or_Reg_Details.getStatus());
							history_Details.setKit_id(kit_Upgrade_Or_Reg_Details.getKit_id());
							history_Details.setPayment(Double.parseDouble(kit.getPackage_id().getAmount()));
							history_Details.setUserModel(kit_Upgrade_Or_Reg_Details.getUserModel());
							kitService.saveKit_Upgrade_Or_Reg_History_Details(history_Details);
						} catch (Exception e) {

						}

					}

					try { 

						HttpSession httpSession = request.getSession();
						UserModel userModel1 = (UserModel) httpSession.getAttribute("ActUserId");
						if (userModel1.getUserRoll().getId() == 3) {
							
							
							
							Dealer dealer = (Dealer) httpSession.getAttribute("DealerUserModel");
							kitService.updateDealerOfKit(KitId, KitPassword, dealer);
							DealerSellKitDetails dealerSellKitDetails = new DealerSellKitDetails();
							dealerSellKitDetails.setDealer(dealer);
							dealerSellKitDetails.setPackageId(kit.getPackage_id());
							dealerSellKitDetails.setPriceAmount(Integer.parseInt((kit.getPackage_id().getAmount())));
							dealerSellKitDetails.setUpdateDate(new Date());
							double kitPrice = Double.parseDouble(kit.getPackage_id().getAmount());
							float discountAmount = (float) Math.round(kitPrice * dealer.getDiscount()) / 100;
							dealerSellKitDetails.setDiscountAmount(discountAmount);
							dealerService.saveDealerSellKitDetails(dealerSellKitDetails);

						} else if (userModel1.getUserRoll().getId() == 4)
						{
							employeeService.updateKitByEmployee(KitId, KitPassword, userModel1);
							
						}

					} catch (Exception e) 
					{
                      e.printStackTrace();
					}

					return "Success";

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/upgradeKitBonusByAdmin", method = RequestMethod.POST)
	@ResponseBody
	public String upgradeKitBonus(ModelMap model, HttpServletRequest request) {

		JSONObject obj;
		try {

			obj = new JSONObject(request.getParameter("jsonObjectData"));
			final JSONObject jsonObj = obj;
			String KitId = jsonObj.getString("kitId");
			String KitPassword = jsonObj.getString("kitPass");
			String userId = jsonObj.getString("userId");
			String noOfMessage = jsonObj.getString("noOfMessage");
			String noOfDay = jsonObj.getString("noOfDay");
			String noOfProfile = jsonObj.getString("noOfProfile");
			String noOfEmail = jsonObj.getString("noOfEmail");

			UserModel userModel = new UserModel();
			userModel.setUserId(Long.parseLong(userId));

			Kit kit = kitService.findKitByKitUserNamePassword(KitId, KitPassword);

			if (kit == null) {
				return "InvalidKit";
			} else {
				if (kit.getStatus_id().getStatus_id() == 1) {

					List<Kit_Upgrade_Or_Reg_Details> list_kit_Upgrade_Or_Reg_Details = kitUpgradeAndPaymentService
							.getKit_upgrade_or_reg_detailsByUserModel(userModel);

					if (list_kit_Upgrade_Or_Reg_Details.size() > 0) {

						Kit_Upgrade_Or_Reg_History_Details history_Details = new Kit_Upgrade_Or_Reg_History_Details();
						Kit_Upgrade_Or_Reg_Details kit_Upgrade_Or_Reg_Details = list_kit_Upgrade_Or_Reg_Details.get(0);

						int old_no_msg = 0;
						int old_no_email = 0;
						int old_no_profile = 0;

						old_no_msg = kit_Upgrade_Or_Reg_Details.getNo_of_msg();
						old_no_email = kit_Upgrade_Or_Reg_Details.getNo_of_email();
						old_no_profile = kit_Upgrade_Or_Reg_Details.getNo_of_profile();

						kit_Upgrade_Or_Reg_Details.setKit_id(kit);
						kit_Upgrade_Or_Reg_Details.setNo_of_email(old_no_email + Integer.parseInt(noOfEmail));

						kit_Upgrade_Or_Reg_Details.setNo_of_msg(old_no_msg + Integer.parseInt(noOfMessage));

						kit_Upgrade_Or_Reg_Details.setNo_of_profile(old_no_profile + Integer.parseInt(noOfProfile));

						kit_Upgrade_Or_Reg_Details.setUserModel(userModel);

						long diff = kit_Upgrade_Or_Reg_Details.getExpiryDate().getTime() - new Date().getTime();

						long diffMinutes = diff / (60 * 1000) % 60;
						long diffHours = diff / (60 * 60 * 1000) % 24;
						long diffDays = diff / (24 * 60 * 60 * 1000);
						int flag = 0;
						if (diffDays >= 1) {
							flag = 1;
						} else if (diffHours >= 1) {
							flag = 1;
						} else if (diffMinutes >= 1) {
							flag = 1;
						}

						if (flag == 1) {
							kit_Upgrade_Or_Reg_Details.setStatus("Bonus");

							Calendar c = new GregorianCalendar();
							c.setTime(kit_Upgrade_Or_Reg_Details.getExpiryDate());
							//kit_Upgrade_Or_Reg_Details.setRegistrationDate(c.getTime()); old error prone code 
							kit_Upgrade_Or_Reg_Details.setRegistrationDate(new Date());
							c.add(Calendar.DATE, Integer.parseInt(noOfDay));
							Date expiredate = c.getTime();
							kit_Upgrade_Or_Reg_Details.setExpiryDate(expiredate);
						} else {
							kit_Upgrade_Or_Reg_Details.setStatus("Bonus");
							kit_Upgrade_Or_Reg_Details.setRegistrationDate(new Date());
							Calendar c = new GregorianCalendar();
							c.add(Calendar.DATE, Integer.parseInt(kit.getPackage_id().getNo_of_days()));
							Date expiredate = c.getTime();
							kit_Upgrade_Or_Reg_Details.setExpiryDate(expiredate);
						}

						kitUpgradeAndPaymentService.updateKit_upgrade_or_reg_details(kit_Upgrade_Or_Reg_Details);
						try {
							history_Details.setExpiryDate(kit_Upgrade_Or_Reg_Details.getExpiryDate());
							history_Details.setRegistrationDate(kit_Upgrade_Or_Reg_Details.getRegistrationDate());
							history_Details.setActualUsedDate(new Date());
							history_Details.setNo_of_msg(kit_Upgrade_Or_Reg_Details.getNo_of_msg());
							history_Details.setNo_of_email(kit_Upgrade_Or_Reg_Details.getNo_of_email());
							history_Details.setNo_of_profile(kit_Upgrade_Or_Reg_Details.getNo_of_profile());
							history_Details.setStatus(kit_Upgrade_Or_Reg_Details.getStatus());
							history_Details.setKit_id(kit_Upgrade_Or_Reg_Details.getKit_id());
							history_Details.setUserModel(kit_Upgrade_Or_Reg_Details.getUserModel());
							kitService.saveKit_Upgrade_Or_Reg_History_Details(history_Details);
						} catch (Exception e) {

						}

						return "Success";

					}

				} else {

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}

}
