package com.xanadutec.coreflex.Registration;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.xanadutec.coreflex.EducationOccupation.EducationOccupationService;
import com.xanadutec.coreflex.Email.EmailService;
import com.xanadutec.coreflex.FileUploadDownload.FileUploadService;
import com.xanadutec.coreflex.Kit.KitService;
import com.xanadutec.coreflex.KitUpgradeAndPayment.KitUpgradeAndPaymentService;
import com.xanadutec.coreflex.ProfileView.ProfileViewService;
import com.xanadutec.coreflex.Religion.ReligionService;
import com.xanadutec.coreflex.location.LocationService;
import com.xanadutec.coreflex.login.LoginService;
import com.xanadutec.coreflex.model.Caste;
import com.xanadutec.coreflex.model.Country;
import com.xanadutec.coreflex.model.District_SubDistricts;
import com.xanadutec.coreflex.model.Education;
import com.xanadutec.coreflex.model.EducationCategory;
import com.xanadutec.coreflex.model.FcmNotification;
import com.xanadutec.coreflex.model.Kit;
import com.xanadutec.coreflex.model.Kit_Upgrade_Or_Reg_Details;
import com.xanadutec.coreflex.model.Kit_Upgrade_Or_Reg_History_Details;
import com.xanadutec.coreflex.model.Occupation;
import com.xanadutec.coreflex.model.OccupationCategory;
import com.xanadutec.coreflex.model.OnlineUser;
import com.xanadutec.coreflex.model.PartnerEducationAssociation;
import com.xanadutec.coreflex.model.PartnerOccupationAssociation;
import com.xanadutec.coreflex.model.Religion;
import com.xanadutec.coreflex.model.RoleModel;
import com.xanadutec.coreflex.model.SmsInfo;
import com.xanadutec.coreflex.model.State_Country;
import com.xanadutec.coreflex.model.State_Districts;
import com.xanadutec.coreflex.model.Status;
import com.xanadutec.coreflex.model.UserModel;
import com.xanadutec.coreflex.model.UserModelDetails;
import com.xanadutec.coreflex.model.UserModelImagesName;
import com.xanadutec.coreflex.model.UserModelPartnerPreferances;
import com.xanadutec.coreflex.notification.NotificationService;
import com.xanadutec.coreflex.sms.smsService;
import com.xanadutec.coreflex.userTask.UserService;

@Controller
public class RegistrationController {
	@Autowired
	UserService userService;
//	@Autowired
//	private LocationService locationService;
	@Autowired
	ReligionService religionService;
	@Autowired
	EducationOccupationService educationOccupationService;
	@Autowired
	KitService kitService;
	@Autowired
	EmailService emailService;
	@Autowired
	RegistrationService registrationServicec;
	@Autowired
	ProfileViewService profileViewService;
	@Autowired
	FileUploadService fileUploadService;
	@Autowired
	KitUpgradeAndPaymentService kitUpgradeAndPaymentService;
	@Autowired
	NotificationService notificationService;
	@Autowired
	private smsService smsService;
	@Autowired
	private LoginService loginService;

	@RequestMapping(value = "/validateUser", method = RequestMethod.POST)
	@ResponseBody
	public String validateUser(HttpServletRequest request) {
		JSONObject obj;
		try {
			obj = new JSONObject(request.getParameter("jsonObjectData"));
			final JSONObject jsonObj = obj;
			String userName = jsonObj.getString("userName");
			UserModel userModel = userService.getUserByUname(userName);
			if (userModel == null) {
				return "Success";
			} else {
				return "AlreadyAvailable";
			}

		} catch (Exception e) {

		}
		return "";
	}

	@RequestMapping(value = "/validateEmail", method = RequestMethod.POST)
	@ResponseBody
	public String validateEmail(HttpServletRequest request) {

		JSONObject obj;
		try {
			obj = new JSONObject(request.getParameter("jsonObjectData1"));
			final JSONObject jsonObj = obj;
			String email = jsonObj.getString("email");
			if (email == null || email.trim().isEmpty() || email.startsWith("abc")) {
				return "Success";
			}

			UserModelDetails modelDetails = registrationServicec.getUserModelDetailsByEmail(email);
			if (modelDetails == null) {

				return "Success";
			} else {
				return "AlreadyAvailable";
			}

		} catch (Exception e) {

		}
		return "";
	}
	@RequestMapping(value = "/registrationLogin", method = RequestMethod.POST)
	@ResponseBody
	public String registrationLoginNew(final HttpServletRequest request,
			@RequestParam(value = "materialStatusPartner", required = false, defaultValue = "") String materialStatusPartner,
			@RequestParam(value = "physicalStatusPartner", required = false, defaultValue = "") String physicalStatusPartner,
			@RequestParam(value = "bodyTypePartner", required = false, defaultValue = "") String bodyTypePartner,
			@RequestParam(value = "complexionPartner", required = false, defaultValue = "") String complexionPartner,
			@RequestParam(value = "heightSelectPartnerFirst", required = false, defaultValue = "") String heightSelectPartnerFirst,
			@RequestParam(value = "heightSelectPartnerSecond", required = false, defaultValue = "") String heightSelectPartnerSecond,
			@RequestParam(value = "countrySelectPartner", required = false, defaultValue = "") String countrySelectPartner,
			@RequestParam(value = "stateSelectPartner", required = false, defaultValue = "") String stateSelectPartner,
			@RequestParam(value = "cityStateSelectPartnerPreferance", required = false, defaultValue = "") String cityStateSelectPartnerPreferance,
			@RequestParam(value = "religionSelectPartner", required = false, defaultValue = "") String religionSelectPartner,
			@RequestParam(value = "casteSelectPartner", required = false, defaultValue = "") String casteSelectPartner,
			@RequestParam(value = "checkBoxEducationPartner", required = false, defaultValue = "") String checkBoxEducationPartner,
			@RequestParam(value = "checkBoxOccupationPartner", required = false, defaultValue = "") String checkBoxOccupationPartner,
			@RequestParam(value = "incomePartner", required = false, defaultValue = "") String incomePartner,
			@RequestParam(value = "incomeAmountPartner", required = false, defaultValue = "") String incomeAmountPartner,
			@RequestParam(value = "familyClassInfoPartner", required = false, defaultValue = "") String familyClassInfoPartner,
			@RequestParam(value = "familyTypeInfoPartner", required = false, defaultValue = "") String familyTypeInfoPartner,
			@RequestParam(value = "familyValueInfoPartner", required = false, defaultValue = "") String familyValueInfoPartner,
			@RequestParam(value = "eatingPartner", required = false, defaultValue = "") String eatingPartner,
			@RequestParam(value = "drinkPartner", required = false, defaultValue = "") String drinkPartner,
			@RequestParam(value = "smokePartner", required = false, defaultValue = "") String smokePartner,
			@RequestParam(value = "aboutMe", required = false, defaultValue = "") String aboutMe,
			@RequestParam(value = "materialStatus", required = false, defaultValue = "") String materialStatus,
			@RequestParam(value = "physicalStatus", required = false, defaultValue = "") String physicalStatus,
			@RequestParam(value = "heightSelect", required = false, defaultValue = "") String heightSelect,
			@RequestParam(value = "weightSelect", required = false, defaultValue = "") String weightSelect,
			@RequestParam(value = "countrySelect", required = false, defaultValue = "") String countrySelect,
			@RequestParam(value = "stateSelect", required = false, defaultValue = "") String stateSelect,
			@RequestParam(value = "cityStateSelect", required = false, defaultValue = "") String cityStateSelect,
			@RequestParam(value = "subDistSelect", required = false, defaultValue = "") String subDistSelect,
			@RequestParam(value = "religionSelect", required = false, defaultValue = "") String religionSelect,
			@RequestParam(value = "casteSelect", required = false, defaultValue = "") String casteSelect,
			@RequestParam(value = "educationalCategorySelect", required = false, defaultValue = "") String educationalCategorySelect,
			@RequestParam(value = "bodyType", required = false, defaultValue = "") String bodyType,
			@RequestParam(value = "complexion", required = false, defaultValue = "") String complexion,
			@RequestParam(value = "educationalSelect", required = false, defaultValue = "") String educationalSelect,
			@RequestParam(value = "occupationCategorySelect", required = false, defaultValue = "") String occupationCategorySelect,
			@RequestParam(value = "occupationSelect", required = false, defaultValue = "") String occupationSelect,
			@RequestParam(value = "income", required = false, defaultValue = "") String income,
			@RequestParam(value = "incomeAmount", required = false, defaultValue = "") String incomeAmount,
			@RequestParam(value = "familyClassInfo", required = false, defaultValue = "") String familyClassInfo,
			@RequestParam(value = "familyTypeInfo", required = false, defaultValue = "") String familyTypeInfo,
			@RequestParam(value = "familyValueInfo", required = false, defaultValue = "") String familyValueInfo,
			@RequestParam(value = "eating", required = false, defaultValue = "") String eating,
			@RequestParam(value = "drink", required = false, defaultValue = "") String drink,
			@RequestParam(value = "smoke", required = false, defaultValue = "") String smoke,
			@RequestParam(value = "firstName", required = false, defaultValue = "") String firstName,
			@RequestParam(value = "gender", required = false, defaultValue = "") String gender,
			@RequestParam(value = "mobile", required = false, defaultValue = "") String mobile,
			@RequestParam(value = "dob", required = false, defaultValue = "") String dob,
			@RequestParam(value = "userName", required = false, defaultValue = "") String userName,
			@RequestParam(value = "password", required = false, defaultValue = "") String password,
			@RequestParam(value = "email", required = false, defaultValue = "") String email,
			@RequestParam(value = "employedIn", required = false, defaultValue = "") String employedIn,
			@RequestParam(value = "employedInPartner", required = false, defaultValue = "") String employedInPartner,
			@RequestParam(value = "ProfileCreatedFor", required = false, defaultValue = "") String ProfileCreatedFor,
			@RequestParam(value = "kitUser", required = false, defaultValue = "") String kitUser,
			@RequestParam(value = "kitId", required = false, defaultValue = "") String kitId,
			@RequestParam(value = "kitPassword", required = false, defaultValue = "") String kitPassword,
			@RequestParam(value = "ladmark", required = false, defaultValue = "") String landmark,
			@RequestParam(value = "kundaliImg", required = false, defaultValue = "") String kundaliImg) {

//	@RequestMapping(value="registrationLoginNew",method=RequestMethod.POST)
//	public @ResponseBody ResponseEntity<String> registrationLoginNew( 
//			final HttpServletRequest request){
		
		System.out.println("In new Congroller registration==================");
		String ActualFileName = "";
		String KundaliName="";
		Long regId = null;

		try {


			RoleModel roleModel = new RoleModel();
			try {
				roleModel.setId(2);

			} catch (Exception e) {
				e.printStackTrace();
			}
			UserModel userModel = new UserModel();



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

			// update kit
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
							history_Details.setKit_id(kit_Upgrade_Or_Reg_Details.getKit_id());
							history_Details.setUserModel(kit_Upgrade_Or_Reg_Details.getUserModel());
							kitService.saveKit_Upgrade_Or_Reg_History_Details(history_Details);
						} catch (Exception e) {

						}


					}
				}
			} catch (Exception e) {

			}
			String aboutMeMassage = null;
			aboutMeMassage = aboutMe.replace("<br>", " ");

			try {
				userModel.setKit(kit);;

			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				UserModel existingUser = userService.getUserByUname(userName);
				if (existingUser != null) {
					return "Duplicate";
				}
				userModel = userService.addUser(userModel);

			} catch (Exception e) {
				e.printStackTrace();
			}
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
				userModelDetails.setAboutMe(aboutMeMassage);
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

				if(countrySelect=="0" ||countrySelect.equalsIgnoreCase("0"))
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
				state_Country = new State_Country();
				if(stateSelect.equalsIgnoreCase("0")||stateSelect=="0")
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

				if(cityStateSelect=="0" ||cityStateSelect.equalsIgnoreCase("0"))
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

				if(subDistSelect=="0" ||subDistSelect.equalsIgnoreCase("0"))
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
				religion.setReligion_id(Long.parseLong(religionSelect));
			} catch (Exception e) {
				e.printStackTrace();
			}
			Caste caste = null;

			try {
				if(casteSelect=="0" || casteSelect.equalsIgnoreCase("0"))
				{
					casteSelect="1";
				}

				caste = new Caste();
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
				if(occupationSelect.equalsIgnoreCase("0" )|| occupationSelect=="0")
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


			try {
				if (email == null || email.trim().isEmpty() || email.equalsIgnoreCase("abc@gmail.com") || email.startsWith("abc"))
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
			
			StringBuffer builder = new StringBuffer(checkBoxEducationPartner);
			try {
				builder.deleteCharAt(0);
				builder.deleteCharAt(builder.toString().length() - 1);
			} catch (Exception e) {
				e.printStackTrace();
			}

			

			try {
				for (String string : builder.toString().replaceAll("\"", "").split(",")) {
					try {
						if(string != ""){
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
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
            try {
            	builder = new StringBuffer(checkBoxOccupationPartner);
    			builder.deleteCharAt(0);
    			builder.deleteCharAt(builder.toString().length() - 1);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			try {
				for (String string : builder.toString().replaceAll("\"", "").split(",")) {
					try {
						if(string != ""){
	                    	long l=Long.parseLong(string);
	    					if(l==0)
	    					{
	    						l=1;
	    					}
	    					OccupationCategory OccupationCategory = new OccupationCategory();
	    					OccupationCategory.setOccu_id(l);
	    					PartnerOccupationAssociation occupationAssociation = new PartnerOccupationAssociation();
	    					occupationAssociation.setUserModel(userModel);
	    					occupationAssociation.setOccupationCategory(OccupationCategory);
	    					userService.savePartnerOccupationAssociation(occupationAssociation);
	                    }	
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

			// save file code start

//			String fileName = null;
//			final String pathact = request.getServletContext().getRealPath("/resources/UserImages");
//			File dir1 = new File(pathact);
//
//			if (!dir1.exists()){
//				dir1.mkdirs();
//			}
//
//			Iterator<String> itrator = request1.getFileNames();
//			String str = "";
//			int i = 0;
//
//
//
//			while (itrator.hasNext()) {
//				MultipartFile multiFile = request1.getFile(itrator.next());
//				try {
//					i++;
//
//					fileName =multiFile.getOriginalFilename();
//
//
//					// making directories for our required path.
//
//					if(kundaliImg.equalsIgnoreCase("Yes") && !itrator.hasNext())
//					{
//
//						byte[] bytes = multiFile.getBytes();
//						File directory = new File(dir1.getAbsolutePath());
//						directory.mkdirs();
//						String extension = FilenameUtils.getExtension(fileName);
//						String name = "@K" + userModel.getUserId() + "." + extension;
//						KundaliName = KundaliName + name + "#";
//
//						File file = new File(dir1.getAbsolutePath() + File.separator + name);
//						BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(file));
//						stream.write(bytes);
//						stream.close();
//
//					}
//					else
//					{
//
//						byte[] bytes = multiFile.getBytes();
//						File directory = new File(dir1.getAbsolutePath());
//						directory.mkdirs();
//						String extension = FilenameUtils.getExtension(fileName);
//						String name = "@" + i + "_" + userModel.getUserId() + "." + extension;
//						ActualFileName = ActualFileName + name + "#";
//
//						File file = new File(dir1.getAbsolutePath() + File.separator + name);
//						BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(file));
//						stream.write(bytes);
//						stream.close();
//
//
//						//Add water mark code here
//
//						String waterMarkImagepath = request.getServletContext().getRealPath("/resources/images/waterMark.png");
//						String paidLogoImagepath = request.getServletContext().getRealPath("/resources/images/paidLogo.png");
//
//
//						BufferedImage profileImage = ImageIO.read(file);
//						BufferedImage watermarkImage = ImageIO.read(new File(waterMarkImagepath));
//						BufferedImage paidProfileLogo = ImageIO.read(new File(paidLogoImagepath));
//
//						int outWidth = profileImage.getWidth();
//						int outHeight = profileImage.getHeight();
//
//						BufferedImage outputImage = new BufferedImage(outWidth, outHeight, profileImage.getType());
//
//
//						// calculates the coordinate where the image is painted
//						int centerxCoOrdinate = (profileImage.getWidth() - watermarkImage.getWidth()) / 2;
//						int centeryCoOrdinate = (profileImage.getHeight() - watermarkImage.getHeight()) / 2;
//						int bottomRightxCoOrdinate = profileImage.getWidth()-paidProfileLogo.getWidth();
//						int bottomRightyCoOrdinate = profileImage.getHeight()-paidProfileLogo.getHeight();
//
//						// initializes necessary graphic properties
//						Graphics g = profileImage.getGraphics() ;
//						Graphics2D g2d = (Graphics2D) profileImage.getGraphics();
//						AlphaComposite alphaChannel = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f);
//						g2d.setComposite(alphaChannel);
//
//						g2d.drawImage(outputImage,  outWidth, outHeight, null);
//						g2d.drawImage(watermarkImage, centerxCoOrdinate, centeryCoOrdinate, null);
//
//						//add condition here for paid user  
//
//						if(kitUser =="FreeKit" || kitUser =="")
//						{
//						}
//						if(kitUser.equalsIgnoreCase("RegisteredKit"))
//						{
//							g.drawImage(paidProfileLogo,bottomRightxCoOrdinate, bottomRightyCoOrdinate, null);
//						}
//
//
//						g2d.dispose();
//
//						File newFile = new File(dir1.getAbsolutePath() + File.separator + name);
//						try {
//							ImageIO.write(profileImage,extension, newFile);
//						} catch (Exception e) {
//							e.printStackTrace();
//						}
//
//					}
//
//
//				} catch (Exception e) {
//
//					e.printStackTrace();
//					throw new Exception("Error while loading the file"+e.getMessage());
//				}
//			}
//
//			if (ActualFileName != "") {
//				UserModelImagesName imagesName = new UserModelImagesName();
//				imagesName.setUserModel(userModel);
//				imagesName.setImageNames(ActualFileName);
//				imagesName.setKundaliImage(KundaliName);
//				userService.saveUserImageNames(imagesName);
//			}

			
			
			regId=userModel.getUserId();
			final UserModel model2 = userModel;
			final UserModelDetails userModelDetails2 = userModelDetails;
			
			try {

				Thread tt=new Thread(new Runnable() {

					@Override
					public void run() {

						try {
							final String pathact1 = request.getServletContext().getRealPath("/resources/UserImages");
							emailService.sendRegistrationEmail(model2.getFirstName(), model2.getUserName(), model2.getPassword(),
									userModelDetails2.getEmail(), request, pathact1);
						} catch (Exception ex) {
							ex.printStackTrace();
						}
						
					}
				});

				tt.start();

			} catch (Exception e) {

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

			final SmsInfo smsDetail = smsService.getSmsDetails();
			try {

				Thread t1 = new Thread(new Runnable() {

					@Override
					public void run() {

						String userName = smsDetail.getUserName();
						String password = smsDetail.getPassword();
						String senderId = smsDetail.getSenderid();
						String channel = smsDetail.getChannel();
						String dcs = smsDetail.getDCS();
						String flashsms = smsDetail.getFlashsms();
						String route = smsDetail.getRoute();

						String smsText = "Snehbandhan:";
						smsText += "UserName :  " + model2.getUserName() + "     And   ";
						smsText += "Password :  " + model2.getPassword() + "";
						try {
							String url = "http://sms.twitsms.in/api/mt/SendSMS?user=" + userName + "&password="
									+ password + "&senderid=" + senderId + "&channel=" + channel + "&DCS=" + dcs
									+ "&flashsms=" + flashsms + "&number=" + userModelDetails2.getMobileNum() + "&text="
									+ URLEncoder.encode(smsText, "UTF8") + "&route=" + route;
							URL obj = new URL(url);
							HttpURLConnection con = (HttpURLConnection) obj.openConnection();
							con.setRequestMethod("GET");
							int responseCode = con.getResponseCode();

							BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
							String inputLine;
							StringBuffer response = new StringBuffer();

							while ((inputLine = in.readLine()) != null) {
								response.append(inputLine);
							}
							in.close();

						} catch (Exception e) 
						{
							e.printStackTrace();
						}

					}
				});

				t1.start();

			} catch (Exception e) {

			}

			// save file code End

		} catch (

				Exception e)
		{
			e.printStackTrace();
		}

		String registrationId = ""+regId;
		return registrationId;
	}
	
	@RequestMapping(value = "/regImgUpload", method = RequestMethod.POST)
	@ResponseBody
	public String regImgUpload(MultipartHttpServletRequest request1, ModelMap model, final HttpServletRequest request) throws JSONException {
		String ActualFileName = "";
		String KundaliName="";
		
		    JSONObject obj = new JSONObject(request.getParameter("jsonObjectData"));
			final JSONObject jsonObj = obj;

			// step 3 data
			String kundaliImg = jsonObj.getString("kundaliImg");
			String userId = jsonObj.getString("userId");
			String kitUser = jsonObj.getString("kitUser");
			
			
			
		
            UserModel userModel = loginService.getUserDetailsBy(Long.parseLong(userId));

			String fileName = null;
			final String pathact = request.getServletContext().getRealPath("/resources/UserImages");
			File dir1 = new File(pathact);

			if (!dir1.exists()){
				dir1.mkdirs();
			}

			Iterator<String> itrator = request1.getFileNames();
			String str = "";
			int i = 0;



			while (itrator.hasNext()) {
				MultipartFile multiFile = request1.getFile(itrator.next());
				try {
					i++;
				
					fileName =multiFile.getOriginalFilename();


					// making directories for our required path.
					
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
						
						if(kitUser =="FreeKit" || kitUser =="")
						{
						}
						if(kitUser.equalsIgnoreCase("RegisteredKit"))
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
						
					}
					

				} catch (Exception e) {

					e.printStackTrace();
				}
			}

			if (ActualFileName != "") {
				UserModelImagesName imagesName = new UserModelImagesName();
				imagesName.setUserModel(userModel);
				imagesName.setImageNames(ActualFileName);
				imagesName.setKundaliImage(KundaliName);
				userService.saveUserImageNames(imagesName);
			}

			
		return userId;

	}


	@RequestMapping(value = "/registrationLogin1", method = RequestMethod.POST)
	@ResponseBody
	public String login(MultipartHttpServletRequest request1, ModelMap model, final HttpServletRequest request) {
		String ActualFileName = "";
		String KundaliName="";
		JSONObject obj;
		Long regId = null;
		try {
			obj = new JSONObject(request.getParameter("jsonObjectData"));
			final JSONObject jsonObj = obj;

			// step 3 data
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
			String aboutMe = jsonObj.getString("aboutMe");
			String aboutMeMassage = null;
			aboutMeMassage = aboutMe.replace("<br>", " ");
			String materialStatus = jsonObj.getString("materialStatus");
			String physicalStatus = jsonObj.getString("physicalStatus");
			// step 2 data
			String heightSelect = jsonObj.getString("heightSelect");
			String weightSelect = jsonObj.getString("weightSelect");
			String countrySelect = jsonObj.getString("countrySelect");
			String stateSelect = jsonObj.getString("stateSelect");
			String cityStateSelect = jsonObj.getString("cityStateSelect");
			String subDistSelect = jsonObj.getString("subDistSelect");
			String religionSelect = jsonObj.getString("religionSelect");
			String casteSelect = jsonObj.getString("casteSelect");
			String educationalCategorySelect = jsonObj.getString("educationalCategorySelect");
			String bodyType = jsonObj.getString("bodyType");
			String complexion = jsonObj.getString("complexion");
			String educationalSelect = jsonObj.getString("educationalSelect");
			String occupationCategorySelect = jsonObj.getString("occupationCategorySelect");
			String occupationSelect = jsonObj.getString("occupationSelect");
			String income = jsonObj.getString("income");
			String incomeAmount = jsonObj.getString("incomeAmount");
			String familyClassInfo = jsonObj.getString("familyClassInfo");
			String familyTypeInfo = jsonObj.getString("familyTypeInfo");
			String familyValueInfo = jsonObj.getString("familyValueInfo");
			String eating = jsonObj.getString("eating");
			String drink = jsonObj.getString("drink");
			String smoke = jsonObj.getString("smoke");

			String firstName = jsonObj.getString("firstName");
			String gender = jsonObj.getString("gender");
			String mobile = jsonObj.getString("mobile");
			String dob = jsonObj.getString("dob");
			String userName = jsonObj.getString("userName");
			String password = jsonObj.getString("password");
			String email = jsonObj.getString("email");
			String employedIn = jsonObj.getString("employedIn");
			String employedInPartner = jsonObj.getString("employedInPartner");
			String ProfileCreatedFor = jsonObj.getString("ProfileCreatedFor");

			String kitUser = jsonObj.getString("kitUser");
			String kitId = jsonObj.getString("kitId");
			String kitPassword = jsonObj.getString("kitPassword");
			String landmark = jsonObj.getString("ladmark"); 
			String kundaliImg = jsonObj.getString("kundaliImg");
			
			RoleModel roleModel = new RoleModel();
			try {
				roleModel.setId(2);

			} catch (Exception e) {
				e.printStackTrace();
			}
			UserModel userModel = new UserModel();



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
			
			// update kit
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
							history_Details.setKit_id(kit_Upgrade_Or_Reg_Details.getKit_id());
							history_Details.setUserModel(kit_Upgrade_Or_Reg_Details.getUserModel());
							kitService.saveKit_Upgrade_Or_Reg_History_Details(history_Details);
						} catch (Exception e) {

						}


					}
				}
			} catch (Exception e) {

			}
			
			try {
				userModel.setKit(kit);;

			} catch (Exception e) {
				e.printStackTrace();
			}
			
			try {
				userModel = userService.addUser(userModel);

			} catch (Exception e) {
				e.printStackTrace();
			}
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
				userModelDetails.setAboutMe(aboutMeMassage);
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

				if(countrySelect=="0" ||countrySelect.equalsIgnoreCase("0"))
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
				state_Country = new State_Country();
				if(stateSelect.equalsIgnoreCase("0")||stateSelect=="0")
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

				if(cityStateSelect=="0" ||cityStateSelect.equalsIgnoreCase("0"))
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

				if(subDistSelect=="0" ||subDistSelect.equalsIgnoreCase("0"))
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
				religion.setReligion_id(Long.parseLong(religionSelect));
			} catch (Exception e) {
				e.printStackTrace();
			}
			Caste caste = null;

			try {
				if(casteSelect=="0" || casteSelect.equalsIgnoreCase("0"))
				{
					casteSelect="1";
				}

				caste = new Caste();
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
				if(occupationSelect.equalsIgnoreCase("0" )|| occupationSelect=="0")
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

		
			try {
				if (email == null || email.trim().isEmpty() || email.equalsIgnoreCase("abc@gmail.com") || email.startsWith("abc"))
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

			StringBuffer builder = new StringBuffer(checkBoxEducationPartner);
			builder.deleteCharAt(0);
			builder.deleteCharAt(builder.toString().length() - 1);

			try {
				for (String string : builder.toString().replaceAll("\"", "").split(",")) {
					
					
					
					
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
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			builder = new StringBuffer(checkBoxOccupationPartner);
			builder.deleteCharAt(0);
			builder.deleteCharAt(builder.toString().length() - 1);
			try {
				for (String string : builder.toString().replaceAll("\"", "").split(",")) {
					
					long l=Long.parseLong(string);
					if(l==0)
					{
						l=1;
					}
					OccupationCategory OccupationCategory = new OccupationCategory();
					OccupationCategory.setOccu_id(l);
					PartnerOccupationAssociation occupationAssociation = new PartnerOccupationAssociation();
					occupationAssociation.setUserModel(userModel);
					occupationAssociation.setOccupationCategory(OccupationCategory);
					userService.savePartnerOccupationAssociation(occupationAssociation);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

            // save file code start

			String fileName = null;
			final String pathact = request.getServletContext().getRealPath("/resources/UserImages");
			File dir1 = new File(pathact);

			if (!dir1.exists()){
				dir1.mkdirs();
			}

			Iterator<String> itrator = request1.getFileNames();
			String str = "";
			int i = 0;



			while (itrator.hasNext()) {
				MultipartFile multiFile = request1.getFile(itrator.next());
				try {
					i++;
				
					fileName =multiFile.getOriginalFilename();


					// making directories for our required path.
					
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
						
						if(kitUser =="FreeKit" || kitUser =="")
						{
						}
						if(kitUser.equalsIgnoreCase("RegisteredKit"))
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
						
					}
					

				} catch (Exception e) {

					e.printStackTrace();
					throw new Exception("Error while loading the file"+e.getMessage());
				}
			}

			if (ActualFileName != "") {
				UserModelImagesName imagesName = new UserModelImagesName();
				imagesName.setUserModel(userModel);
				imagesName.setImageNames(ActualFileName);
				imagesName.setKundaliImage(KundaliName);
				userService.saveUserImageNames(imagesName);
			}

			regId=userModel.getUserId();
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

			final SmsInfo smsDetail = smsService.getSmsDetails();
			try {

				Thread t1 = new Thread(new Runnable() {

					@Override
					public void run() {

						String userName = smsDetail.getUserName();
						String password = smsDetail.getPassword();
						String senderId = smsDetail.getSenderid();
						String channel = smsDetail.getChannel();
						String dcs = smsDetail.getDCS();
						String flashsms = smsDetail.getFlashsms();
						String route = smsDetail.getRoute();

						String smsText = "Snehbandhan:";
						smsText += "UserName :  " + model2.getUserName() + "     And   ";
						smsText += "Password :  " + model2.getPassword() + "";
						try {
							String url = "http://sms.twitsms.in/api/mt/SendSMS?user=" + userName + "&password="
									+ password + "&senderid=" + senderId + "&channel=" + channel + "&DCS=" + dcs
									+ "&flashsms=" + flashsms + "&number=" + userModelDetails2.getMobileNum() + "&text="
									+ URLEncoder.encode(smsText, "UTF8") + "&route=" + route;
							URL obj = new URL(url);
							HttpURLConnection con = (HttpURLConnection) obj.openConnection();
							con.setRequestMethod("GET");
							int responseCode = con.getResponseCode();

							BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
							String inputLine;
							StringBuffer response = new StringBuffer();

							while ((inputLine = in.readLine()) != null) {
								response.append(inputLine);
							}
							in.close();

						} catch (Exception e) 
						{
							e.printStackTrace();
						}

					}
				});

				t1.start();

			} catch (Exception e) {

			}

			// save file code End

		} catch (

				Exception e)
		{
			e.printStackTrace();
		}

		String registrationId = ""+regId;
		return registrationId;

	}


	@RequestMapping(value = "/saveOnlineuserInfo", method = RequestMethod.POST)
	@ResponseBody
	public String saveOnlineuserInfo(HttpServletRequest request) {

		JSONObject obj;
		try {
			obj = new JSONObject(request.getParameter("jsonObjectData1"));
			final JSONObject jsonObj = obj;
			String email = jsonObj.getString("email");

			UserModelDetails modelDetails = registrationServicec.getUserModelDetailsByEmail(email.trim());

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
		return "success";
	}

	@RequestMapping(value = "/deleteOnlineuserInfo", method = RequestMethod.POST)
	@ResponseBody
	public String deleteOnlineuserInfo(HttpServletRequest request) {

		JSONObject obj;
		try {
			obj = new JSONObject(request.getParameter("jsonObjectData1"));
			final JSONObject jsonObj = obj;
			String email = jsonObj.getString("email");

			UserModelDetails modelDetails = registrationServicec.getUserModelDetailsByEmail(email.trim());

			if (modelDetails != null) {
				userService.deleteOnlineUser(modelDetails.getUserModel());
			}

		} catch (Exception e) {

		}
		return "success";
	}

	@RequestMapping(value = "/validateMobile", method = RequestMethod.POST)
	@ResponseBody
	public String validateMobile(HttpServletRequest request) {

		JSONObject obj;
		try {
			obj = new JSONObject(request.getParameter("jsonObjectData3"));
			final JSONObject jsonObj = obj;
			String mobile = jsonObj.getString("mob");

			UserModelDetails modelDetails = registrationServicec.getUserModelDetailsByMobile(mobile);

			if (modelDetails == null) {

				return "Success";
			} else {
				return "AlreadyAvailable";
			}

		} catch (Exception e) {

		}
		return "";
	}



}
