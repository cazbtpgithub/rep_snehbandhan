package com.xanadutec.coreflex.employee;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.xanadutec.coreflex.DealerReport.DealerReportModel;
import com.xanadutec.coreflex.EducationOccupation.EducationOccupationService;
import com.xanadutec.coreflex.Kit.KitService;
import com.xanadutec.coreflex.Religion.ReligionService;
import com.xanadutec.coreflex.location.LocationService;
import com.xanadutec.coreflex.model.AdminUserModel;
import com.xanadutec.coreflex.model.Country;
import com.xanadutec.coreflex.model.EducationCategory;
import com.xanadutec.coreflex.model.EmployeeRegistrationDetails;
import com.xanadutec.coreflex.model.Kit;
import com.xanadutec.coreflex.model.Kit_Upgrade_Or_Reg_History_Details;
import com.xanadutec.coreflex.model.OccupationCategory;
import com.xanadutec.coreflex.model.Package;
import com.xanadutec.coreflex.model.PermissionType;
import com.xanadutec.coreflex.model.Religion;
import com.xanadutec.coreflex.model.RoleModel;
import com.xanadutec.coreflex.model.State_Country;
import com.xanadutec.coreflex.model.Status;
import com.xanadutec.coreflex.model.UserModel;
import com.xanadutec.coreflex.model.UserModelDetails;
import com.xanadutec.coreflex.model.UserPermissionAssociation;
import com.xanadutec.coreflex.packageAndKit.PackageAndKitService;
import com.xanadutec.coreflex.revenue.RevenueService;
import com.xanadutec.coreflex.userTask.UserService;

@Controller
public class EmployeeController {
	@Autowired
	EmployeeService employeeService;
	@Autowired
	UserService userService;
	@Autowired
	private KitService kitService;
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	RevenueService revService;
	@Autowired
	PackageAndKitService packageandkitService;
	@Autowired
	LocationService locationservice;
	@Autowired
	ReligionService religionService;
	@Autowired
	EducationOccupationService educationOccupationService;

	@RequestMapping(value = "/employeeRegistration", method = RequestMethod.GET)
	public ModelAndView employeeRegistration(ModelMap model,
			HttpServletRequest request, Model ui_model) 
	{
		
		try {

			List<EmployeeRegistrationDetails> employeeRegistrationDetailsList = employeeService.getAllEmployeeRegistrationDetails();

			List<PermissionType> permissionTypesList = employeeService.getAllPermissionTypes();
			model.addAttribute("employeeRegistrationDetailsList",employeeRegistrationDetailsList);
			model.addAttribute("permissionTypesList", permissionTypesList);

			HttpSession httpSession = request.getSession();
			UserModel userModel = (UserModel) httpSession.getAttribute("ActUserId");

			if (userModel.getUserRoll().getId() == 4) 
			{

				model.addAttribute("loginUserRole", "Employee");
			} else 
			{
				model.addAttribute("loginUserRole", "Admin");
			}


		} catch (Exception e)
		{
			e.printStackTrace();
		}


		return new ModelAndView("EmployeeRegistration");
	}

	@RequestMapping(value = "/saveOrUpdateEmployeeUserDetails", method = RequestMethod.POST)
	@ResponseBody
	public String saveEmployeeUserDetails(ModelMap model,
			HttpServletRequest request, HttpServletResponse response) {

		try {
              
			// ---------------Ajax Call From Enable and Disable Function-------------//
			String enableEmp_id = request.getParameter("empid");
			String enableStatusid = request.getParameter("status_id");
			
			// ---------END------Ajax Call From Enable and Disable Function-------------//

			String emp_id = request.getParameter("employeeHiddenId");
			String emp_enableDisableStatus = request.getParameter("employeeEnableDisableStatus");

			String userName = request.getParameter("userName");
			String fullName = request.getParameter("fullName");
			String dateOfBirth = request.getParameter("dateOfBirth");
			String accountNo = request.getParameter("accountNo");
			String branchName = request.getParameter("branchName");
			String ifcCode = request.getParameter("ifcCode");
			String bankName = request.getParameter("bankName");
			String designation = request.getParameter("designation");
			String experienceDetails = request.getParameter("experienceDetails");
			String totalExperience = request.getParameter("totalExperience");
			String currentSalary = request.getParameter("currentSalary");
			String previousSalary = request.getParameter("previousSalary");
			String mobileNo = request.getParameter("mobileNo");
			String alernateMobileNo = request.getParameter("alernateMobileNo");
			String email = request.getParameter("email");
			String address = request.getParameter("address");
			String city = request.getParameter("city");
			String employeePassword = request.getParameter("employeePassword");
			String registrationDate = request.getParameter("registrationDate");


			Date DateOfBirth = null;
			Date registrationDate1 = null;
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				if(dateOfBirth != "" || dateOfBirth == null )
				{
					if(enableEmp_id == null ||enableStatusid == null)
					{
						String[] fromSplit = dateOfBirth.split("/");
						
						String fromSplitDate = fromSplit[2] + "-" + fromSplit[0] + "-" + fromSplit[1];
						DateOfBirth = df.parse(fromSplitDate + " 00:00:00");
						
						String[] fromSplitRegistration = registrationDate.split("/");
						String registrationSplitDate = fromSplitRegistration[2] + "-" + fromSplitRegistration[0] + "-"
						+ fromSplitRegistration[1];		
						registrationDate1 = df.parse(registrationSplitDate + " 00:00:00");
					}
				
			   }
				
				if(registrationDate != null)
				{
				String[] fromSplitRegistration = registrationDate.split("/");
				String registrationSplitDate = fromSplitRegistration[2] + "-" + fromSplitRegistration[0] + "-" + fromSplitRegistration[1];		
				registrationDate1 = df.parse(registrationSplitDate + " 00:00:00");
				}
				

				} catch (ParseException e) 
			    {
					e.printStackTrace();
				}
			
			String userModelId = null;
			UserModel userModel = new UserModel();
			if (emp_id == "") {
				userModel.setFirstName(fullName);
				userModel.setUserName(userName);
				userModel.setPassword(employeePassword);
				RoleModel roleModel = new RoleModel();
				roleModel.setId(4);
				userModel.setUserRoll(roleModel);
				userService.addUser(userModel);
				
				userModelId = ""+userModel.getUserId();

			}

			EmployeeRegistrationDetails employeeRegistrationDetailsObject = new EmployeeRegistrationDetails();

			employeeRegistrationDetailsObject.setAccountNo(accountNo);
			employeeRegistrationDetailsObject.setAddress(address);
			employeeRegistrationDetailsObject.setBankName(bankName);
			employeeRegistrationDetailsObject.setBranchName(branchName);
			employeeRegistrationDetailsObject.setCity(city);
			if (currentSalary == "" || previousSalary == "") 
			{
				employeeRegistrationDetailsObject.setCurrentSalary(Double.parseDouble((currentSalary == "") ? "0": currentSalary));

				employeeRegistrationDetailsObject.setPreviousSalary(Double.parseDouble(previousSalary == "" ? "0": previousSalary));
			}
			else
			{
				employeeRegistrationDetailsObject.setCurrentSalary(Double.parseDouble((currentSalary == null) ? "0": currentSalary));

				employeeRegistrationDetailsObject.setPreviousSalary(Double.parseDouble(previousSalary == null ? "0": previousSalary));
			}
			employeeRegistrationDetailsObject.setDesignation(designation);
			employeeRegistrationDetailsObject.setEmail(email);
			employeeRegistrationDetailsObject.setExperienceDetails(experienceDetails);
			employeeRegistrationDetailsObject.setFullName(fullName);
			employeeRegistrationDetailsObject.setIfcCode(ifcCode);
			employeeRegistrationDetailsObject.setMobileNo(mobileNo);
			employeeRegistrationDetailsObject.setPassword(employeePassword);
			employeeRegistrationDetailsObject.setTotalExperience(totalExperience);
			employeeRegistrationDetailsObject.setUserModel(userModel);
			employeeRegistrationDetailsObject.setUserName(userName);
			employeeRegistrationDetailsObject.setDateOfBirth(DateOfBirth);
			employeeRegistrationDetailsObject.setRegistrationDate(registrationDate1);
			employeeRegistrationDetailsObject.setAlternateMobileNo(alernateMobileNo);		
			// -----Start----------Enable disable Record------------------------------------------------------------------//
			if (enableStatusid != null) {
				if (enableStatusid.equalsIgnoreCase("1")) 
				{
					int Emp_id = Integer.parseInt(enableEmp_id);// Enable Record
					EmployeeRegistrationDetails employeeRegistrationDetailsObj = employeeService.getEmployeeDetailsById(Emp_id);

					Status status = new Status();
					status.setStatus_id(1);
					employeeRegistrationDetailsObj.setStatus(status);

					UserModel userModelObject = employeeService.getUserModel(employeeRegistrationDetailsObj);

					String modelUser_Name = userModelObject.getUserName();
					String modelPassword = userModelObject.getPassword();
					String spliteModelUser_Name[] = modelUser_Name.split("&");
					String spliteModelPassword[] = modelPassword.split("&");
					String user_modelUser_name = spliteModelUser_Name[0];
					String user_modelpass_word = spliteModelPassword[0];

					userModelObject.setUserName(user_modelUser_name);
					userModelObject.setPassword(user_modelpass_word);
					userModelObject.setFirstName(userModelObject.getFirstName());
					employeeRegistrationDetailsObject.setUserModel(userModelObject);

					employeeService.updateUserDetails(userModelObject);

					employeeService.saveEmployeeRegistrationDetails(employeeRegistrationDetailsObj);
					String returnstatus = "enableEmployeeDetails";

					return returnstatus;
				}
				if (enableStatusid.equalsIgnoreCase("3"))// Disable record
				{
					int Emp_id = Integer.parseInt(enableEmp_id);
					EmployeeRegistrationDetails employeeRegistrationDetailsObj = employeeService.getEmployeeDetailsById(Emp_id);
					String UserName = employeeRegistrationDetailsObj.getUserModel().getUserName();
					String password = employeeRegistrationDetailsObject.getUserModel().getPassword();
					Status status = new Status();
					status.setStatus_id(3);
					employeeRegistrationDetailsObj.setStatus(status);
					employeeRegistrationDetailsObject.setUserName(UserName+ "&" + new Date().getTime());
					employeeRegistrationDetailsObject.setPassword(password+ "&" + new Date().getTime());

					UserModel userModelObject = employeeService.getUserModel(employeeRegistrationDetailsObj);
					String modelUser_Name = userModelObject.getUserName();
					String modelPassword = userModelObject.getPassword();
					userModelObject.setUserName(modelUser_Name + "&"+ new Date().getTime());
					userModelObject.setPassword(modelPassword + "&"+ new Date().getTime());
					userModelObject.setFirstName(userModelObject.getFirstName());
					employeeRegistrationDetailsObject.setUserModel(userModelObject);

					employeeService.updateUserDetails(userModelObject);
					employeeService.saveEmployeeRegistrationDetails(employeeRegistrationDetailsObj);
					String returnstatus = "DisableEmployeeDetails";
					return returnstatus;
				}
				if (enableStatusid.equalsIgnoreCase("2"))// Delete record
				{
					int Emp_id = Integer.parseInt(enableEmp_id);
					EmployeeRegistrationDetails employeeRegistrationDetailsObj = employeeService.getEmployeeDetailsById(Emp_id);
					String UserName = employeeRegistrationDetailsObj.getUserModel().getUserName();
					String password = employeeRegistrationDetailsObject.getUserModel().getPassword();
					Status status = new Status();
					status.setStatus_id(2);
					employeeRegistrationDetailsObj.setStatus(status);
					employeeRegistrationDetailsObject.setUserName(UserName+ "&" + new Date().getTime());
					employeeRegistrationDetailsObject.setPassword(password+ "&" + new Date().getTime());

					UserModel userModelObject = employeeService.getUserModel(employeeRegistrationDetailsObj);
					String modelUser_Name = userModelObject.getUserName();
					String modelPassword = userModelObject.getPassword();
					userModelObject.setUserName(modelUser_Name + "&"+ new Date().getTime());
					userModelObject.setPassword(modelPassword + "&"+ new Date().getTime());
					userModelObject.setFirstName(userModelObject.getFirstName());
					employeeRegistrationDetailsObject.setUserModel(userModelObject);

					employeeService.updateUserDetails(userModelObject);

					employeeService.saveEmployeeRegistrationDetails(employeeRegistrationDetailsObj);

					String returnstatus = "DeleteEmployeeDetails";
					return returnstatus;
				}

			}

			// -----End----------Enable Disable Record ------------------------------------------------------------------//

			if (emp_id.isEmpty()) 
			{
				employeeRegistrationDetailsObject.setRegistrationDate(registrationDate1);

				Status status = new Status();
				status.setStatus_id(1);
				employeeRegistrationDetailsObject.setStatus(status);

			}
			// --------------------Start update Details----------------------------------------------------------//
			if (emp_id != "") 
			{
				employeeRegistrationDetailsObject.setEmp_id(Integer.parseInt(emp_id));
				EmployeeRegistrationDetails employee_RegistrationDetails_Obj = employeeService.getEmployeeDetailsById(Integer.parseInt(emp_id));

				if (emp_enableDisableStatus.equalsIgnoreCase("1"))
				{
					Status status = new Status();
					status.setStatus_id(1);
					employeeRegistrationDetailsObject.setStatus(status);
					employeeRegistrationDetailsObject.setRegistrationDate(registrationDate1);

					UserModel userModelObject = employeeService.getUserModel(employee_RegistrationDetails_Obj);
					userModelObject.setUserName(userName);
					userModelObject.setPassword(employeePassword);
					userModelObject.setFirstName(fullName);
					employeeRegistrationDetailsObject.setUserModel(userModelObject);

					employeeService.updateUserDetails(userModelObject);

					employeeService.saveEmployeeRegistrationDetails(employeeRegistrationDetailsObject);
					String statusSuccess = "success";
					return toJson(statusSuccess);
				}
				if (emp_enableDisableStatus.equalsIgnoreCase("2"))
				{
					Status status = new Status();
					status.setStatus_id(2);
					employeeRegistrationDetailsObject.setStatus(status);
					employeeService.saveEmployeeRegistrationDetails(employeeRegistrationDetailsObject);
					String statusSuccess = "success";
					return toJson(statusSuccess);
				}

			}
			// --------------------End ----------- update Details----------------------------------------------------------//

			employeeService.saveEmployeeRegistrationDetails(employeeRegistrationDetailsObject);// Save Details
             
			String regId = "" + employeeRegistrationDetailsObject.getEmp_id();

			String usermodelIDAndEmployeeId = regId +"#"+  userModelId;
			
			return toJson(usermodelIDAndEmployeeId);
		} catch (Exception e) 
		{
			e.printStackTrace();
		}

		return "success";

	}

	@RequestMapping(value = "/saveOrUpdate_PermissionType", method = RequestMethod.POST)
	@ResponseBody
	public String permissionType(ModelMap model, HttpServletRequest request,
			HttpServletResponse response) 
	{
		String str =null;
		try {

			String permission_Type = request.getParameter("permission_Type");

			PermissionType permissionTypeObject = new PermissionType();
			permissionTypeObject.setPermissionType(permission_Type);
			employeeService.savePermissionType(permissionTypeObject);
		
			str = ""+permissionTypeObject.getId();

		} catch (Exception e) 
		{
			e.printStackTrace();
		}

		return toJson(str);
	}

	@RequestMapping(value = "/userPmployeePermissionType", method = RequestMethod.GET)
	@ResponseBody
	public String userPmployeePermissionType(Model model,HttpServletRequest request,
			@RequestParam("employee_id") String employee_id)
	{
		int employee_ID = Integer.parseInt(employee_id);
		List<PermissionType> permissionTypesList = employeeService.getAllPermissionTypes();

		JSONObject mainObject = new JSONObject();
		ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = null;
		try {
			json = objectWriter.writeValueAsString(permissionTypesList);

		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		return json;

	}

	@RequestMapping(value = "/employeeAssignPermissionAssociation", method = RequestMethod.GET)
	@ResponseBody
	public String employeeAssignPermissionAssociation(ModelMap model,HttpServletRequest request,@RequestParam("employee_id") String employee_id) 
	{
		String json = null;
		try {

			Long employee_ID = (long) Integer.parseInt(employee_id);
			List<UserPermissionAssociation> userPermissionAssociationsList = employeeService.getUserPermissionAssociationByEmployeeId(employee_ID);
			List<PermissionType> permissionTypesList = employeeService.getAllPermissionTypes();

			JSONObject mainObj = new JSONObject();
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

			JSONObject js = new JSONObject();
			js.put("userPermission",ow.writeValueAsString(userPermissionAssociationsList));
			js.put("permissionList", ow.writeValueAsString(permissionTypesList));
			json = js.toString();

		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		return json;

	}

	@RequestMapping(value = "/saveEmployeeUserPermissionTypeAssociation", method = RequestMethod.GET)
	@ResponseBody
	public String saveEmployeeUserPermissionTypeAssociation(Model model,HttpServletRequest request) 
	{
		
		try {
			
			String employeeId = request.getParameter("employee_Id");

			UserModel userModel = new UserModel();
			userModel.setUserId(Long.parseLong(employeeId));
			employeeService.deleteUserPermissionAssociationByEmployeeObject(userModel);

			Enumeration<String> s = request.getParameterNames();
			while (s.hasMoreElements()) {
				String name = s.nextElement();
				if (name.equalsIgnoreCase("employee_Id")) 
				{

				} else if (request.getParameter(name).equalsIgnoreCase("on")) 
				{

					UserPermissionAssociation userPermissionAssociation = new UserPermissionAssociation();
					userPermissionAssociation.setUserModel(userModel);

					PermissionType permissionType = new PermissionType();
					permissionType.setId(Integer.parseInt(name));

					userPermissionAssociation.setUserModel(userModel);
					userPermissionAssociation.setPermissionType(permissionType);
					employeeService.saveUserPermissionAssociation(userPermissionAssociation);

				}
			}
			
			
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return "success";
	}

	@RequestMapping(value = "/employeeSaleKit", method = RequestMethod.GET)
	public ModelAndView employeeUpdateSaleKit(ModelMap model,HttpServletRequest request, HttpServletResponse response)
	{
		HttpSession httpSession = request.getSession();
		UserModel userModel = (UserModel) httpSession.getAttribute("ActUserId");

		if (userModel.getUserRoll().getId() == 4) 
		{

			model.addAttribute("loginUserRole", "Employee");
		} else
		{
			model.addAttribute("loginUserRole", "Admin");
		}
		return new ModelAndView("saleKitForEmployee");
	}

	@RequestMapping(value = "/updateEmployeeUser", method = RequestMethod.GET)
	public ModelAndView employeeUpdateUser(ModelMap model,HttpServletRequest request, HttpServletResponse response) 
	{
		HttpSession httpSession = request.getSession();
		UserModel userModel = (UserModel) httpSession.getAttribute("ActUserId");

		if (userModel.getUserRoll().getId() == 4) 
		{
           model.addAttribute("loginUserRole", "Employee");
		} else 
		{
			model.addAttribute("loginUserRole", "Admin");
		}
		return new ModelAndView("updateEmployeeUser");
	}

	@RequestMapping(value = "/updateSaleKitByEmployee", method = RequestMethod.POST)
	@ResponseBody
	public String updateSaleKitByEmployee(@RequestParam("kitUsername") String kitUsername,@RequestParam("kitPassword") String kitPassword,
			HttpServletRequest request) 
	{

		Kit kit = null;
		String result = null;
		EmployeeRegistrationDetails employeeRegistrationDetails = null;
		try {

			kit = kitService.findKitByKitUserNamePassword(kitUsername,
					kitPassword);
		} catch (Exception e) {
		}

		if (kit == null) {
			result = "one";
		} else {
			try {

				employeeRegistrationDetails = kit.getEmpId();
				if (employeeRegistrationDetails != null) 
				{
					result = "two";

				} else 
				{
					HttpSession session = request.getSession();
					UserModel user = (UserModel) session.getAttribute("ActUserId");
					employeeService.updateKitByEmployee(kitUsername,kitPassword, user);
					result = "success";
				}
			} catch (Exception e)
			{
				e.printStackTrace();
			}

		}

		return result;

	}

	@RequestMapping(value = "/updateuserEmployeeAssociation", method = RequestMethod.POST)
	@ResponseBody
	public String updateuserEmployeeAssociation(@RequestParam("regId") String regId, HttpServletRequest request) 
	{

		UserModel usermodel = null;
		String result = null;

		try 
		{
           usermodel = userService.getUserById(Integer.parseInt(regId));
		} catch (Exception e) 
		{
			e.printStackTrace();
		}

		if (usermodel == null) 
		{

			result = "one";
		} else
		{
			try {

				UserModelDetails userModelDeails = userService.getUserdetails(usermodel);
				if (userModelDeails.getDealer() != null) 
				{
                        result = "two";
				} else 
				{
					HttpSession session = request.getSession();
					UserModel userModel = (UserModel) session.getAttribute("ActUserId");
					employeeService.updateUserModelDetailsWithEmployee(userModelDeails, userModel);
					result = "success";
				}

			} catch (Exception e) 
			{
                   e.printStackTrace();
			}

		}

		return result;
	}

	@RequestMapping(value = "/employeeWiseMember", method = RequestMethod.GET)
	public ModelAndView employeeWiseMember(ModelMap model,HttpServletRequest request, HttpServletResponse response) 
	{


		JSONObject js = new JSONObject();
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		try {

			HttpSession httpSession = request.getSession();
			UserModel userModel = (UserModel) httpSession.getAttribute("ActUserId");

			if (userModel.getUserRoll().getId() == 4) 
			{
				model.addAttribute("loginUserRole", "Employee");
			} else 
			{
				model.addAttribute("loginUserRole", "Admin");
			}
			EmployeeRegistrationDetails empDetails = employeeService.getEmployeeDetailsByUserObject(userModel);

			List<AdminUserModel> list=userService.getListOfUserModelDetailsByEmp(empDetails);


			ObjectMapper mapper = new ObjectMapper();
			model.addAttribute("userModelDetailList", mapper.writeValueAsString(list).replaceAll("'", " ").replaceAll(" ," , "")); 
			List<Country> countries = locationservice.getListOfcountry();
			List<State_Country> state_Countries = locationservice.getListOfStateByCountryId(1 + "");
			List<Religion> religions = religionService.getListOfReligion();
			List<EducationCategory> educationCategories = educationOccupationService.getListOfEducationCategory();
			List<OccupationCategory> occupationCategories = educationOccupationService.getListOfOccupation();
			model.addAttribute("countries", countries);
			model.addAttribute("state_Countries", state_Countries);
			model.addAttribute("religions", religions);
			model.addAttribute("educationCategories", educationCategories);
			model.addAttribute("occupationCategories", occupationCategories);

			List<EmployeeRegistrationDetails> employeeRegistrationDetailsList = employeeService.getAllEmployeeRegistrationDetails();
			model.addAttribute("employeeRegistrationDetailsList",employeeRegistrationDetailsList);
			

		} catch (Exception e) 
		{
			e.printStackTrace();
		}

		return new ModelAndView("employeeWiseMember");
	}
	
	@RequestMapping(value = "/employeeWiseMemberForAdmin", method = RequestMethod.GET)
	public ModelAndView employeeWiseMemberForAdmin(ModelMap model,HttpServletRequest request, HttpServletResponse response) 
	{

		JSONObject js = new JSONObject();
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		try {

			HttpSession httpSession = request.getSession();
			UserModel userModel = (UserModel) httpSession.getAttribute("ActUserId");

			if (userModel.getUserRoll().getId() == 4) 
			{
				model.addAttribute("loginUserRole", "Employee");
			} else 
			{
				model.addAttribute("loginUserRole", "Admin");
			}
			List<EmployeeRegistrationDetails> employeeRegistrationDetailsList = employeeService.getAllEmployeeRegistrationDetails();
			model.addAttribute("employeeRegistrationDetailsList",employeeRegistrationDetailsList);
			
		} catch (Exception e) 
		{
			e.printStackTrace();
		}

		return new ModelAndView("EmployeeWiseMemberForAdmin");
	}
	
	@RequestMapping(value = "/employeeWiseMemberOfAdmin", method =RequestMethod.GET)
	public ModelAndView employeeWiseMemberOfAdmin(ModelMap model, HttpServletRequest request)
	{
		
		String employeeId = request.getParameter("employeeId");
		JSONObject js = new JSONObject();
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		
		try {
			
			EmployeeRegistrationDetails empDetails = new EmployeeRegistrationDetails();
			empDetails.setEmp_id(Integer.parseInt(employeeId));
						List<AdminUserModel> list=userService.getListOfUserModelDetailsByEmp(empDetails);

						ObjectMapper mapper = new ObjectMapper();
						
						model.addAttribute("userModelDetailList", mapper.writeValueAsString(list).replaceAll("'", " ").replaceAll(" ," , "")); 
						List<Country> countries = locationservice.getListOfcountry();
						List<State_Country> state_Countries = locationservice.getListOfStateByCountryId(1 + "");
						List<Religion> religions = religionService.getListOfReligion();
						List<EducationCategory> educationCategories = educationOccupationService.getListOfEducationCategory();
						List<OccupationCategory> occupationCategories = educationOccupationService.getListOfOccupation();
						model.addAttribute("countries", countries);
						model.addAttribute("state_Countries", state_Countries);
						model.addAttribute("religions", religions);
						model.addAttribute("educationCategories", educationCategories);
						model.addAttribute("occupationCategories", occupationCategories);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		

		
		return new ModelAndView("userByDealer");
	}

	@RequestMapping(value = "/employeeWiseRevenue", method = RequestMethod.GET)
	public ModelAndView employeeWiseRevenue(ModelMap model,HttpServletRequest request, HttpServletResponse response) 
	{

		try {
			
			List<EmployeeRegistrationDetails> employeeRegistrationDetailsList = employeeService.getAllEmployeeRegistrationDetails();

			List<Date> dateList = revService.getUniqueDateList();
			List<Package> packageList = packageandkitService.getListOfPackage();

			HashMap<Date, List<Kit_Upgrade_Or_Reg_History_Details>> revMap = new HashMap<>();
			for (Date date : dateList) {

				revMap.put(date, revService.getListOfRegHistoryByDate(date));

			}

			model.addAttribute("revMap", revMap);
			model.addAttribute("packageList", packageList);

			model.addAttribute("employeeRegistrationDetailsList",
					employeeRegistrationDetailsList);
			HttpSession httpSession = request.getSession();
			UserModel userModel = (UserModel) httpSession.getAttribute("ActUserId");

			if (userModel.getUserRoll().getId() == 4) 
			{
	           	model.addAttribute("loginUserRole", "Employee");
			} else 
			{
				model.addAttribute("loginUserRole", "Admin");
			}
			
			
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		
		return new ModelAndView("employeeRevenueHome");
	}

	@RequestMapping(value = "/getRevenueByEmployee", method = RequestMethod.GET)
	public ModelAndView getRevenueByPackageIdAndEmpId(@RequestParam("packageId") String packageId,@RequestParam("employeeId") String employeeId, ModelMap model,
			HttpServletRequest request) 
	{

		try {
			
			
			EmployeeRegistrationDetails emp_Obj = employeeService.getEmployeeDetailsById(Integer.parseInt(employeeId));

			List<Date> dateList = revService.getUniqueDateList();
			HashMap<Date, List<Kit_Upgrade_Or_Reg_History_Details>> revMap = new HashMap<>();
			Session session = sessionFactory.openSession();

			if (packageId.equalsIgnoreCase("All")) 
			{
				for (Date date : dateList) 
				{
					revMap.put(date, employeeService.getListOfRegHistoryByDateAndEmployee(date, emp_Obj));
				}

			} else 
			{

				Package pack = (Package) session.get(Package.class,
						Integer.parseInt(packageId));

				for (Date date : dateList) 
				{

					revMap.put(date, employeeService.getListOfRegHistoryByDateAndPackageAndEmployee(date,pack, emp_Obj));

				}

			}

			model.addAttribute("revMap", revMap);

			session.close();
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		return new ModelAndView("revenueByPackage");

	}

	@RequestMapping(value = "/getMemberByEmployee", method = RequestMethod.GET)
	public ModelAndView getMemberByEmployee(@RequestParam("employeeId") String employeeId, ModelMap model,HttpServletRequest request)
	{

		try {
			EmployeeRegistrationDetails emp_Obj = employeeService.getEmployeeDetailsById(Integer.parseInt(employeeId));
			List<AdminUserModel> list = employeeService.getListOfUserModelDetailsForEmployeeByEmployee(emp_Obj);
			ObjectMapper mapper = new ObjectMapper();
			model.addAttribute("userModelDetailList",mapper.writeValueAsString(list).replaceAll("'", " ").replaceAll(" ,", ""));

		} catch (Exception e) 
		{
			e.printStackTrace();
		}

		return new ModelAndView("userByDealer");

	}

	public String toJson(Object data) 
	{
		ObjectMapper mapper = new ObjectMapper();
		StringBuilder builder = new StringBuilder();
		try 
		{
			builder.append(mapper.writeValueAsString(data));
		} catch (Exception e) 
		{
         	e.printStackTrace();
		}
		return builder.toString();
	}

	@RequestMapping(value = "/employeeReport", method = RequestMethod.GET)
	public ModelAndView employeeReport(ModelMap model,HttpServletRequest request) 
	{
		try {
			
			List<EmployeeRegistrationDetails> employeeRegistrationDetailsList = employeeService.getAllEmployeeRegistrationDetails();
			model.addAttribute("employeeRegistrationDetailsList",employeeRegistrationDetailsList);
			List<Package> packageList = packageandkitService.getListOfPackage();
			model.addAttribute("packageList", packageList);
			
			HttpSession httpSession = request.getSession();
			UserModel userModel = (UserModel) httpSession.getAttribute("ActUserId");

			if (userModel.getUserRoll().getId() == 4) 
			{
	           	model.addAttribute("loginUserRole", "Employee");
			} else 
			{
				model.addAttribute("loginUserRole", "Admin");
			}
			
			
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		return new ModelAndView("EmployeeReport");
	}

	@RequestMapping(value = "/printEmployeeReport", method = RequestMethod.POST)
	@ResponseBody
	public String employeeReportDetails(HttpServletRequest request,HttpServletResponse response) 
	{
 
		final String FILE_NAME = "EmployeeReport.pdf";
		JSONObject obj;
	//	JSONObject js = new JSONObject();
		String stringfromDate = null;
		String stringToDate = null;
		Date fromDate = null;
		Date toDate = null;
		try 
		{
			obj = new JSONObject(request.getParameter("jsonObjectData"));
			final JSONObject jsonObj = obj;
			stringfromDate = jsonObj.getString("fromDate");
			stringToDate = jsonObj.getString("toDate");
		} catch (Exception e) 
		{
			e.printStackTrace();
		}

		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		try
		{
			fromDate = format.parse(stringfromDate);
			toDate = format.parse(stringToDate);
		} catch (ParseException e)
		{
			e.printStackTrace();
		}
		try {
             
			String pathact =null;
			try {
				
			 pathact = request.getServletContext().getRealPath("/resources/snehReport");
			} catch (Exception e) 
			{
				e.printStackTrace();
			}

			File dir1 = new File(pathact);
			if (!dir1.exists()) 
			{
				dir1.mkdirs();
			}
			Paragraph horizontalLine = new Paragraph("____________________________________");
			horizontalLine.setFontSize(18f);
			
			String filePath = null;
			try {
				filePath = request.getServletContext().getRealPath("/resources/snehReport/" + FILE_NAME);
				
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
			PdfDocument pdfDoc = new PdfDocument(new PdfWriter(filePath));

			Document document = new Document(pdfDoc);
			document.setWidth(PageSize.A4.getWidth());
			document.setFontSize(9);

			// Creating an ImageData object

			String imFile = null;
		   
			try {
				
		    imFile = request.getServletContext().getRealPath("/resources/images/reportHeader.png");
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
			
			
			ImageData data = null;
			try {
				
				data = ImageDataFactory.create(imFile);
			} catch (Exception e)
			{
				e.printStackTrace();
			}

			// Creating an Image object
			Image image = null;
			try {
				
				image = new Image(data);
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
	
			Table employeeDetailsTable = new Table(9);
		
			 PdfFont font = PdfFontFactory.createFont(FontConstants.HELVETICA_BOLD);
			 PdfFont fontHeader = PdfFontFactory.createFont(FontConstants.TIMES_ROMAN);
			
			Cell headerFirst = null ;
			try {
				headerFirst= new Cell().add(new Paragraph("Sr No").setFont(font).setFontColor(Color.WHITE));
				headerFirst.setBackgroundColor(Color.BLACK);
				headerFirst.setBorder(new SolidBorder(Color.WHITE,1f));
				
			} catch (Exception e)
			{
				e.printStackTrace();
			}
			
			Cell headerSecond = null;
			try {
				
				headerSecond= new Cell().add(new Paragraph("Full Name").setFont(font).setFontColor(Color.WHITE));
				headerSecond.setBackgroundColor(Color.BLACK);
				headerSecond.setBorder(new SolidBorder(Color.WHITE,1f));
				
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
			
			Cell headerThird = null;
			try {
				
				headerThird= new Cell().add(new Paragraph("User Name").setFont(font).setFontColor(Color.WHITE));
				headerThird.setBackgroundColor(Color.BLACK);
				headerThird.setBorder(new SolidBorder(Color.WHITE,1f));
				
			} catch (Exception e)
			{
				e.printStackTrace();
			}
			Cell headerFourth = null;
			try {
				headerFourth= new Cell().add(new Paragraph("Password").setFont(font).setFontColor(Color.WHITE));
				headerFourth.setBackgroundColor(Color.BLACK);
				headerFourth.setBorder(new SolidBorder(Color.WHITE,1f));	
			} catch (Exception e)
			{
				e.printStackTrace();
			}
			Cell headerRegDate = null;
			try {
				headerRegDate= new Cell().add(new Paragraph("Reg Date").setFont(font).setFontColor(Color.WHITE));
				headerRegDate.setBackgroundColor(Color.BLACK);
				headerRegDate.setBorder(new SolidBorder(Color.WHITE,1f));	
			} catch (Exception e)
			{
				e.printStackTrace();
			}
			Cell headerFive = null;
			try {
				headerFive= new Cell().add(new Paragraph("Contact No").setFont(font).setFontColor(Color.WHITE));
				headerFive.setBackgroundColor(Color.BLACK);
				headerFive.setBorder(new SolidBorder(Color.WHITE,1f));
				
				
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
			Cell headerSix = null;
			
			try {
				
				headerSix= new Cell().add(new Paragraph("Designation").setFont(font).setFontColor(Color.WHITE));
				headerSix.setBackgroundColor(Color.BLACK);
				headerSix.setBorder(new SolidBorder(Color.WHITE,1f));
				
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
			Cell headerSeven = null;
			try {
				
				headerSeven= new Cell().add(new Paragraph("Current Salary").setFont(font).setFontColor(Color.WHITE));
				headerSeven.setBackgroundColor(Color.BLACK);
				headerSeven.setBorder(new SolidBorder(Color.WHITE,1f));
				
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
			Cell headerEight = null;
			try {
				
				headerEight= new Cell().add(new Paragraph("City").setFont(font).setFontColor(Color.WHITE));
				headerEight.setBackgroundColor(Color.BLACK);
				headerEight.setBorder(new SolidBorder(Color.WHITE,1f));
			} catch (Exception e) 
			{
				e.printStackTrace();
			}

			employeeDetailsTable.addCell(headerFirst);
			employeeDetailsTable.addCell(headerSecond);
			employeeDetailsTable.addCell(headerThird);
			employeeDetailsTable.addCell(headerFourth);
			employeeDetailsTable.addCell(headerRegDate);
			employeeDetailsTable.addCell(headerFive);
			employeeDetailsTable.addCell(headerSix);
			employeeDetailsTable.addCell(headerSeven);
			employeeDetailsTable.addCell(headerEight);

			int serialNo = 1;

			List<List<EmployeeRegistrationDetails>> employeeReportData = new ArrayList<>();
			List<EmployeeRegistrationDetails> employeeList = employeeService.getEmployeeDetailsByDate(fromDate, toDate);
		
			employeeReportData.add(employeeList);
			List<List<EmployeeRegistrationDetails>> employeeDataSet = employeeReportData;

			for (List<EmployeeRegistrationDetails> record : employeeDataSet) {

				
				for (EmployeeRegistrationDetails currentData : record) 
				{
                    try {
						
                    	employeeDetailsTable.addCell(new Cell().add(new Paragraph(Integer.toString(serialNo))));
					} catch (Exception e) 
                    {
						e.printStackTrace();
					}
                    try {
						
                    	employeeDetailsTable.addCell(new Cell().add(new Paragraph(currentData.getFullName())));
					} catch (Exception e)
                    {
						e.printStackTrace();
					}
                    try {
						
                    	employeeDetailsTable.addCell(new Cell().add(new Paragraph(currentData.getUserName())));
					} catch (Exception e)
                    {
						e.printStackTrace();
					}
                    try {
						
                    	employeeDetailsTable.addCell(new Cell().add(new Paragraph(currentData.getPassword())));
					} catch (Exception e)
                    {
						e.printStackTrace();
					}
                    try {
						
						String employeeRegistrationDate = null;
						try {
							String registrationDate = ""+currentData.getRegistrationDate();			
							String[] registrationDateSplit = registrationDate.split("-");
							String registrationDay = registrationDateSplit[2];
							String [] spliteRegistrationDay = registrationDay.split(" ");				
							String todayDate1 = spliteRegistrationDay[0];				
							employeeRegistrationDate =   todayDate1 + "-" + registrationDateSplit[1] + "-" + registrationDateSplit[0];


						} catch (Exception e) 
						{
							e.printStackTrace();
						}
						
                    	employeeDetailsTable.addCell(new Cell().add(new Paragraph(""+currentData.getRegistrationDate() == null ? " ":employeeRegistrationDate)));
					} catch (Exception e)
                    {
						e.printStackTrace();
					}
                    try {
						
                    	employeeDetailsTable.addCell(new Cell().add(new Paragraph(currentData.getMobileNo())));
					} catch (Exception e) 
                    {
						e.printStackTrace();
					}
                    try {
						
                    	employeeDetailsTable.addCell(new Cell().add(new Paragraph(currentData.getDesignation())));
					} catch (Exception e) 
                    {
						e.printStackTrace();
					}
                    try {
						
                    	employeeDetailsTable.addCell(new Cell().add(new Paragraph(Double.toString(currentData.getCurrentSalary()))));
					} catch (Exception e) 
                    {
						e.printStackTrace();
					}
                    try {
						
                    	employeeDetailsTable.addCell(new Cell().add(new Paragraph(currentData.getCity())));
					} catch (Exception e)
                    {
						e.printStackTrace();
					}
					serialNo++;
				}
			}
			Paragraph header = new Paragraph("EMPLOYEE DETAILS");
			header.setFontSize(18f);
			header.setTextAlignment(TextAlignment.CENTER);
			header.setFont(fontHeader);
			
			
			List<String> reportCriteriaDateFormateConvert = reportCriteriaDateFormateConvert(stringfromDate,stringToDate);
			
			Paragraph date = new Paragraph("Date : " + reportCriteriaDateFormateConvert.get(0) + "  To,  " + reportCriteriaDateFormateConvert.get(1));
			date.setTextAlignment(TextAlignment.LEFT);
			try {
				
				document.add(image);
			} catch (Exception e)
			{
				e.printStackTrace();
			}
			try {
				
				document.add(header);
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
			try {
				
				document.add(date);
			} catch (Exception e)
			{
				e.printStackTrace();
			}
			try {
				
				document.add(employeeDetailsTable);
			} catch (Exception e)
			{
				e.printStackTrace();
			}
            try {
				
            	document.close();
			} catch (Exception e) 
            {
				e.printStackTrace();
			}
			return FILE_NAME;

		} catch (Exception e) 
		{
			e.printStackTrace();

		}
		return FILE_NAME;
	}

	private List<String> reportCriteriaDateFormateConvert(String stringfromDate, String stringToDate) {
		
		List<String> changeFormateDateList = new ArrayList<>();
		String fromDateSplite[] = stringfromDate.split("/");
		String fromTodateMonth = fromDateSplite[0];
		String fromTodateDay = fromDateSplite[1];
		String fromDateYear = fromDateSplite[2];
		String reportCriteriaFromDate = fromTodateDay+"/"+fromTodateMonth+"/"+fromDateYear;
		changeFormateDateList.add(reportCriteriaFromDate);
		
		String toDateSplite[] = stringToDate.split("/");
		String todateMonth = toDateSplite[0];
		String todateDay = toDateSplite[1];
		String todateYear = toDateSplite[2];
		String reportCriteriaTodate = todateDay+"/"+todateMonth+"/"+todateYear;
		
		changeFormateDateList.add(reportCriteriaTodate);
		return changeFormateDateList;
	}

	@RequestMapping(value = "/employeeWiseRevenueDeatils", method = RequestMethod.POST)
	@ResponseBody
	public String employeeRevenueReportDetails(HttpServletRequest request,HttpServletResponse response,@RequestParam("packageId") String packageId,
			@RequestParam("empId") String empId) 
	{

		EmployeeRegistrationDetails employee = employeeService.getEmployeeDetailsById(Integer.parseInt(empId));

		List<Date> dateList = revService.getUniqueDateList();

		Package packageObj = null;

		HashMap<Date, List<Kit_Upgrade_Or_Reg_History_Details>> revMap = new HashMap<>();
		Session session = sessionFactory.openSession();

		if (packageId.equalsIgnoreCase("All")) {
			for (Date date : dateList) 
			{
				revMap.put(date, employeeService.getListOfRegHistoryByDateAndEmployee(date, employee));
			}

		} else 
		{

			Package pack = (Package) session.get(Package.class,Integer.parseInt(packageId));

			packageObj = pack;
			for (Date date : dateList) 
			{

				revMap.put(date, employeeService.getListOfRegHistoryByDateAndPackageAndEmployee(date,pack, employee));

			}

		}

		List<DealerReportModel> dealerReportData = new ArrayList<>();
		List<List<DealerReportModel>> dealerReportDataSet = new ArrayList<>();

		Double totalAmount = 0.0;

		for (Date key : revMap.keySet()) 
		{
			List<Kit_Upgrade_Or_Reg_History_Details> value = revMap.get(key);

			for (Kit_Upgrade_Or_Reg_History_Details kit_Upgrade_Or_Reg_History_Details : value) 
			{

				try 
				{

					if (kit_Upgrade_Or_Reg_History_Details != null) 
					{
						DealerReportModel model = new DealerReportModel();

						model.setKituserName(kit_Upgrade_Or_Reg_History_Details.getKit_id().getKit_username());
						model.setRevenueDate(key);
						model.setPrice(kit_Upgrade_Or_Reg_History_Details.getPayment());
						dealerReportData.add(model);
						totalAmount += kit_Upgrade_Or_Reg_History_Details.getPayment();


					}

				}
				catch (Exception e) 
				{
					e.printStackTrace();
				}

			}



		}

		dealerReportDataSet.add(dealerReportData);
		final String FILE_NAME = "EmployeeRevenueReport.pdf";

		try {
			String pathact = null;
			try {
				
			 pathact = request.getServletContext().getRealPath("/resources/snehReport");
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
			File dir1 = new File(pathact);
			if (!dir1.exists()) 
			{
				dir1.mkdirs();
			}
			String filePath = null;
			try {
			 filePath = request.getServletContext().getRealPath("/resources/snehReport/" + FILE_NAME);
				
			} catch (Exception e)
			{
				e.printStackTrace();
			}
			PdfDocument pdfDoc = null;
			try {
				
			 pdfDoc = new PdfDocument(new PdfWriter(filePath));
			} catch (Exception e)
			{
				e.printStackTrace();
			}
			Document document = new Document(pdfDoc);
			document.setWidth(PageSize.A4.getWidth());
			document.setFontSize(9);

			// Creating an ImageData object
			String imFile = null;
			try {
				
			 imFile = request.getServletContext().getRealPath("/resources/images/reportHeader.png");
			} catch (Exception e)
			{
				e.printStackTrace();
			}
			ImageData data = null;
			try {
				
				data = ImageDataFactory.create(imFile);
			} catch (Exception e) 
			{
				e.printStackTrace();
			}

			// Creating an Imageobject
			Image image = null;
			try {
				
				image = new Image(data);
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		

			Table empWiseDetailsKitTable = new Table(4);

			 PdfFont font = PdfFontFactory.createFont(FontConstants.HELVETICA_BOLD);
			 PdfFont fontHeader = PdfFontFactory.createFont(FontConstants.TIMES_ROMAN);
			
			
			Cell headerFirst = null;
			try {
				headerFirst= new Cell().add(new Paragraph("Sr No").setFont(font).setFontColor(Color.WHITE));
				headerFirst.setBackgroundColor(Color.BLACK);
				headerFirst.setBorder(new SolidBorder(Color.WHITE,1f));				
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
			Cell headerSecond = null;
			try {
				
				headerSecond= new Cell().add(new Paragraph("Kit Name").setFont(font).setFontColor(Color.WHITE));
				headerSecond.setBackgroundColor(Color.BLACK);
				headerSecond.setBorder(new SolidBorder(Color.WHITE,1f));
				
			} catch (Exception e)
			{
				e.printStackTrace();
			}
			Cell headerThird = null;
			try {
				headerThird= new Cell().add(new Paragraph("Date ").setFont(font).setFontColor(Color.WHITE));
				headerThird.setBackgroundColor(Color.BLACK);
				headerThird.setBorder(new SolidBorder(Color.WHITE,1f));
				
			} catch (Exception e)
			{
				e.printStackTrace();
			}
			Cell headerForth = null;
			try {
				headerForth= new Cell().add(new Paragraph("Price").setFont(font).setFontColor(Color.WHITE));
				headerForth.setBackgroundColor(Color.BLACK);
				headerForth.setBorder(new SolidBorder(Color.WHITE,1f));	
			} catch (Exception e)
			{
				e.printStackTrace();
			}
			empWiseDetailsKitTable.addCell(headerFirst);
			empWiseDetailsKitTable.addCell(headerSecond);
			empWiseDetailsKitTable.addCell(headerThird);
			empWiseDetailsKitTable.addCell(headerForth);
			
			
	
			int serialNo = 1;


			List<List<DealerReportModel>> dealerDataSet = dealerReportDataSet;

			for (List<DealerReportModel> record : dealerDataSet) 
			{
				for (DealerReportModel currentData : record) 
				{
                    try {
						
                    	empWiseDetailsKitTable.addCell(new Cell().add(new Paragraph(Integer.toString(serialNo))));
					} catch (Exception e) 
                    {
						e.printStackTrace();
					}
                    try {
						
                    	empWiseDetailsKitTable.addCell(new Cell().add(new Paragraph(currentData.getKituserName())));
					} catch (Exception e) 
                    {
						e.printStackTrace();
					}
                    try {
                    	
                    	empWiseDetailsKitTable.addCell(new Cell().add(new Paragraph(""+currentData.getRevenueDate())));
						
					} catch (Exception e) 
                    {
						e.printStackTrace();
					}
                    try {
                    	empWiseDetailsKitTable.addCell(new Cell().add(new Paragraph(Double.toString(currentData.getPrice()))));
						
					} catch (Exception e)
                    {
						e.printStackTrace();
					}

					serialNo++;

				}

			}

			empWiseDetailsKitTable.addCell(new Cell().add(""));
			empWiseDetailsKitTable.addCell(new Cell().add(new Paragraph("")));
			
			Cell totalRevenue = null;
			try {
				totalRevenue =new Cell().add(new Paragraph("Total").setFont(font).setFontColor(Color.WHITE));
				totalRevenue.setBorder(new SolidBorder(Color.WHITE,1f));
				totalRevenue.setBackgroundColor(Color.BLACK);
				empWiseDetailsKitTable.addCell(totalRevenue);
				
				
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		
			try {
				
				empWiseDetailsKitTable.addCell(new Cell().add(new Paragraph(Double.toString(totalAmount))));
			} catch (Exception e) 
			{
				e.printStackTrace();
			}


			Paragraph dealerNameValue = new Paragraph("Employee Name :  "+employee.getUserModel().getFirstName());

			Paragraph packageNameValue =null;
			try {
				if(packageObj !=null)
				{
					packageNameValue = new Paragraph("Package Name :  "+packageObj.getPackage_Name());
				}
				else
				{
					packageNameValue = new Paragraph("Package Name :  All ");
				}

			} catch (Exception e) 
			{
               e.printStackTrace();
			}


			Paragraph header = new Paragraph("EMPLOYEE WISE REVENUE DETAILS");
			header.setFontSize(18f);
			header.setTextAlignment(TextAlignment.CENTER);
			header.setFont(fontHeader);
			try {
				document.add(image);
				
			} catch (Exception e)
			{
				e.printStackTrace();
			}
			try {
				
				document.add(header);
			} catch (Exception e)
			{
				e.printStackTrace();
			}
			try {
				
				document.add(dealerNameValue);
			} catch (Exception e)
			{
				e.printStackTrace();
			}
			try {
				
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
			try {
				
				document.add(packageNameValue);
			} catch (Exception e)
			{
				e.printStackTrace();
			}
			try {
				
				document.add(empWiseDetailsKitTable);
			} catch (Exception e)
			{
				e.printStackTrace();
			}
             try {
            	 document.close();
				
			} catch (Exception e) 
             {
				e.printStackTrace();
			}
			return FILE_NAME;

		} catch (Exception e) {
			e.printStackTrace();

		}
		return FILE_NAME;
	}


	@RequestMapping(value = "/printEmpWiseMemberReport", method = RequestMethod.POST)
	@ResponseBody
	public String empWiseMemberReportDetails(HttpServletRequest request, HttpServletResponse response,@RequestParam ("empId")String empId) {

		final String FILE_NAME = "EmployeeWiseMemberReport.pdf";
		try {
  
			String pathact = null;
            try {
			pathact = request.getServletContext().getRealPath("/resources/snehReport");
			} catch (Exception e) 
            {
				e.printStackTrace();
			}
			File dir1 = new File(pathact);
			if (!dir1.exists()) 
			{
				dir1.mkdirs();
			}
             
			String filePath = null;
			try {
				
			 filePath = request.getServletContext().getRealPath("/resources/snehReport/" + FILE_NAME);
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
			PdfDocument pdfDoc = null;
			try {
				
			  pdfDoc = new PdfDocument(new PdfWriter(filePath));
			} catch (Exception e)
			{
				e.printStackTrace();
			}

			Document document = new Document(pdfDoc);

			document.setFontSize(9);

			String imFile  = null;
			try {
				
			imFile = request.getServletContext().getRealPath("/resources/images/reportHeader.png");
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
			
			ImageData data =null;
			try {
				data = ImageDataFactory.create(imFile);              
				
			} catch (Exception e)
			{
				e.printStackTrace();
			}
			
			// Creating an Image object
			Image image = null;
			try {
				
			    image = new Image(data);                        
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
		
			Table empWiseMemberDetailsTable = new Table(5);

			 PdfFont font = PdfFontFactory.createFont(FontConstants.HELVETICA_BOLD);
			 PdfFont fontHeader = PdfFontFactory.createFont(FontConstants.TIMES_ROMAN);
			
			
			Cell headerFirst  = null;
			try {
				headerFirst= new Cell().add(new Paragraph("Sr No").setFont(font).setFontColor(Color.WHITE));
				headerFirst.setBackgroundColor(Color.BLACK);
				headerFirst.setBorder(new SolidBorder(Color.WHITE,1f));		
			} catch (Exception e)
			{
				e.printStackTrace();
			}
			Cell headerSecond = null;
			try {
				headerSecond= new Cell().add(new Paragraph("Member Full Name").setFont(font).setFontColor(Color.WHITE));
				headerSecond.setBackgroundColor(Color.BLACK);
				headerSecond.setBorder(new SolidBorder(Color.WHITE,1f));	
			} catch (Exception e)
			{
				e.printStackTrace();
			}
			Cell headerThird = null;
			try {
				headerThird= new Cell().add(new Paragraph("Contact No").setFont(font).setFontColor(Color.WHITE));
				headerThird.setBackgroundColor(Color.BLACK);
				headerThird.setBorder(new SolidBorder(Color.WHITE,1f));		
			} catch (Exception e)
			{
				e.printStackTrace();
			}
			
			
			
			Cell headerFifth = null;
			try {
				headerFifth= new Cell().add(new Paragraph("Date of Birth").setFont(font).setFontColor(Color.WHITE));
				headerFifth.setBackgroundColor(Color.BLACK);
				headerFifth.setBorder(new SolidBorder(Color.WHITE,1f));
				
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
			
			
			Cell headerForth = null;
			try {
				headerForth= new Cell().add(new Paragraph("Member Registration Date").setFont(font).setFontColor(Color.WHITE));
				headerForth.setBackgroundColor(Color.BLACK);
				headerForth.setBorder(new SolidBorder(Color.WHITE,1f));
				
				
			} catch (Exception e) 
			{
				e.printStackTrace();			
				}
			empWiseMemberDetailsTable.addCell(headerFirst);
			empWiseMemberDetailsTable.addCell(headerSecond);
			empWiseMemberDetailsTable.addCell(headerThird);
			empWiseMemberDetailsTable.addCell(headerFifth);
			empWiseMemberDetailsTable.addCell(headerForth);
			
		

			int serialNo = 1;
			EmployeeRegistrationDetails employee = employeeService.getEmployeeDetailsById(Integer.parseInt(empId));

			List<List<AdminUserModel>> empReportData = new ArrayList<>();
			List<AdminUserModel> empList = employeeService.getListOfUserModelDetailsForEmployeeByEmployee(employee);

			empReportData.add(empList);
			List<List<AdminUserModel>> memberDataSet = empReportData;

			for (List<AdminUserModel> listMember : memberDataSet) 
			{
				for (AdminUserModel currentData : listMember)
				{

					try
					{
						try {
							empWiseMemberDetailsTable.addCell(new Cell().add(new Paragraph(Integer.toString(serialNo))));

						} catch (Exception e) 
						{
							e.printStackTrace();
						}
						try {

							empWiseMemberDetailsTable.addCell(new Cell().add(new Paragraph(currentData.getFirstName())));
						} catch (Exception e) 
						{
							e.printStackTrace();
						}

						String dateOf_Birth = null;
						try {
							String dateofbirth = ""+currentData.getDateOfBirth();			
							String[] dateofBirtSplit = dateofbirth.split("-");
							String day = dateofBirtSplit[2];
							String [] sliteDay = day.split(" ");				
							String todayDate = sliteDay[0];				
							dateOf_Birth =   todayDate + "-" + dateofBirtSplit[1] + "-" + dateofBirtSplit[0];


						} catch (Exception e)
						{
							e.printStackTrace();
						}
						String employeeRegistrationDate = null;
						try {
							String registrationDate = ""+currentData.getRegistrationDate();			
							String[] registrationDateSplit = registrationDate.split("-");
							String registrationDay = registrationDateSplit[2];
							String [] spliteRegistrationDay = registrationDay.split(" ");				
							String todayDate1 = spliteRegistrationDay[0];				
							employeeRegistrationDate =   todayDate1 + "-" + registrationDateSplit[1] + "-" + registrationDateSplit[0];


						} catch (Exception e) 
						{
							e.printStackTrace();
						}
						try {

							empWiseMemberDetailsTable.addCell(new Cell().add(new Paragraph(currentData.getMobileNum())));
						} catch (Exception e)
						{
							e.printStackTrace();
						}
						try {

							empWiseMemberDetailsTable.addCell(new Cell().add(new Paragraph(dateOf_Birth)));
						} catch (Exception e)
						{
							e.printStackTrace();
						}
						try {

							empWiseMemberDetailsTable.addCell(new Cell().add(new Paragraph(employeeRegistrationDate)));
						} catch (Exception e)
						{
							e.printStackTrace();
						}

						serialNo++;	
					}catch(Exception e)
					{
						e.printStackTrace();

					}

				}
			}



			Paragraph empName=null;

			Paragraph header = new Paragraph("EMPLOYEE WISE MEMBER DETAILS");

			try {

				String name=employee.getUserModel().getFirstName();
				empName = new Paragraph("Employee Name : "+name);

			} catch (Exception e) 
			{
				e.printStackTrace();
			}
			
			
			header.setFontSize(18f);
			header.setTextAlignment(TextAlignment.CENTER);
			header.setFont(fontHeader);
			

			try {
				document.add(image);
			} catch (Exception e) {
			}

			try {
				document.add(header);
			} catch (Exception e) {
			}

			try {
				document.add(empName);
			} catch (Exception e) {
			}

			try {
				document.add(empWiseMemberDetailsTable);
			} catch (Exception e) {
			}

            try {
            	document.close();
				
			} catch (Exception e) 
            {
				e.printStackTrace();
			}
			return FILE_NAME;

		} catch (Exception e)
		{
			e.printStackTrace();

		}
		return FILE_NAME;
	}

}
