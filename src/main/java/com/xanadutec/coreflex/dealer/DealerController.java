package com.xanadutec.coreflex.dealer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xanadutec.coreflex.EducationOccupation.EducationOccupationService;
import com.xanadutec.coreflex.Kit.KitService;
import com.xanadutec.coreflex.Religion.ReligionService;
import com.xanadutec.coreflex.location.LocationService;
import com.xanadutec.coreflex.model.AdminUserModel;
import com.xanadutec.coreflex.model.Country;
import com.xanadutec.coreflex.model.Dealer;
import com.xanadutec.coreflex.model.DealerSellKitDetails;
import com.xanadutec.coreflex.model.EducationCategory;
import com.xanadutec.coreflex.model.Kit;
import com.xanadutec.coreflex.model.KitDealerAssociation;
import com.xanadutec.coreflex.model.OccupationCategory;
import com.xanadutec.coreflex.model.Package;
import com.xanadutec.coreflex.model.Religion;
import com.xanadutec.coreflex.model.RoleModel;
import com.xanadutec.coreflex.model.State_Country;
import com.xanadutec.coreflex.model.UserModel;
import com.xanadutec.coreflex.model.UserModelDetails;
import com.xanadutec.coreflex.packageAndKit.PackageAndKitService;
import com.xanadutec.coreflex.userTask.UserService;

@Controller
public class DealerController {
	
	@Autowired
	UserService userService;
	@Autowired
	DealerService dealerService;
	@Autowired
	LocationService locationservice;
	@Autowired
	ReligionService religionService;
	@Autowired
	EducationOccupationService educationOccupationService;
	@Autowired
	private KitService kitService;
	@Autowired
	PackageAndKitService packageandkitService;
	@Autowired
	KitService kitservice;
	@RequestMapping(value = "/dealerRegistration", method = RequestMethod.GET)
	public ModelAndView dealerRegistration(ModelMap model, HttpServletRequest request) {
		List<Dealer> DealerList=dealerService.getDealerList();
		model.addAttribute("DealerList", DealerList);
		
		HttpSession httpSession = request.getSession();
		UserModel userModel=(UserModel) httpSession.getAttribute("ActUserId");
		
		if(userModel.getUserRoll().getId()==4)
		{
			model.addAttribute("loginUserRole", "Employee");
		}
		else
		{
			model.addAttribute("loginUserRole", "Admin");
		}	
		return new ModelAndView("DealerRegistration");
	}
	@RequestMapping(value = "/subdealerRegistration", method = RequestMethod.GET)
	public ModelAndView subdealerRegistration(ModelMap model, HttpServletRequest request) {
		HttpSession httpSession = request.getSession();
		UserModel adminUser=(UserModel) httpSession.getAttribute("ActUserId");
		List<Dealer> list=dealerService.getSubDealerByMainDealer(adminUser);
		model.addAttribute("DealerList", list);
		return new ModelAndView("subDealerRegistration");

	}
	@RequestMapping(value = "/dealerSaleKit", method = RequestMethod.GET)
	public ModelAndView dealerSaleKit(ModelMap model, HttpServletRequest request)
	{

		List<Package> packageList = packageandkitService.getListOfPackage();
		model.addAttribute("packageList", packageList);
		
		HttpSession httpSession = request.getSession();
		UserModel adminUser=(UserModel) httpSession.getAttribute("ActUserId");
		System.out.println("Login Dealer Id ="+adminUser.getUserId());
		
		List<Dealer> list=dealerService.getSubDealerByMainDealer(adminUser);
		model.addAttribute("DealerList", list);
		
		try {	
			List<KitDealerAssociation> kitDealerAssociationList = dealerService.getSubDealerSaleKitDetailsBySubDealer(list);
			
			ObjectMapper mapper = new ObjectMapper();
			model.addAttribute("userModelDetailList", mapper.writeValueAsString(kitDealerAssociationList).replaceAll("'", " ").replaceAll(" ," , "")); 
			
		} catch (Exception e) {
		}
		
		return new ModelAndView("SaleKitHomeForDealer");

	}
	@RequestMapping(value = "/updateDealerUser", method = RequestMethod.GET)
	public ModelAndView updateDealerUser(ModelMap model, HttpServletRequest request) {
		
		return new ModelAndView("updateDealerUser");

	}
	@RequestMapping(value = "/dealerWiseMember", method = RequestMethod.GET)
	public ModelAndView dealerWiseMember(ModelMap model, HttpServletRequest request) {
		
		List<Dealer> list=dealerService.getDealerList();
		model.addAttribute("DealerList", list);
		HttpSession httpSession = request.getSession();
		UserModel userModel=(UserModel) httpSession.getAttribute("ActUserId");
		
		if(userModel.getUserRoll().getId()==4)
		{
			
			model.addAttribute("loginUserRole", "Employee");
		}
		else
		{
			model.addAttribute("loginUserRole", "Admin");
		}
		return new ModelAndView("DealerWiseUser");

	}
	@RequestMapping(value = "/subdealerWiseMember", method = RequestMethod.GET)
	public ModelAndView subdealerWiseMember(ModelMap model, HttpServletRequest request) {
		
		
		HttpSession httpSession = request.getSession();
		UserModel adminUser=(UserModel) httpSession.getAttribute("ActUserId");
		List<Dealer> list=dealerService.getSubDealerByMainDealer(adminUser);
		model.addAttribute("DealerList", list);
		return new ModelAndView("subDealerWiseUser");

	}
	@RequestMapping(value = "/deleteDealerByAdmin", method = RequestMethod.POST)
	@ResponseBody
	public String deleteDealerByAdmin(
			@RequestParam("dealerId") String dealerId,
			ModelMap model, HttpServletRequest request) {
		Dealer deal=dealerService.getDealerById(dealerId);
		UserModel userModel=deal.getUserModel();
		
		userModel.setUserName(userModel.getUserName()+new Date().getTime());
		userModel.setPassword(userModel.getPassword()+new Date().getTime());
		userService.addUser(userModel);
		dealerService.deleteDealerByDealerId(dealerId);
		return "";
	}
	@RequestMapping(value = "/disableDealerByAdmin", method = RequestMethod.POST)
	@ResponseBody
	public String disableDealerByAdmin(
			@RequestParam("dealerId") String dealerId,
			ModelMap model, HttpServletRequest request) {
		
		
		Dealer deal=dealerService.getDealerById(dealerId);
		UserModel userModel=deal.getUserModel();
		userModel.setUserName(userModel.getUserName()+"s123");
		userModel.setPassword(userModel.getPassword()+"s123");
		userService.addUser(userModel);
		dealerService.disableDealerById(dealerId);
		return "";
	}
	@RequestMapping(value = "/unableDealerByAdmin", method = RequestMethod.POST)
	@ResponseBody
	public String unableDealerByAdmin(
			@RequestParam("dealerId") String dealerId,
			ModelMap model, HttpServletRequest request) {
		Dealer deal=dealerService.getDealerById(dealerId);
		UserModel userModel=deal.getUserModel();
		userModel.setUserName(userModel.getUserName().substring(0, userModel.getUserName().length()-4));
		userModel.setPassword(userModel.getPassword().substring(0,userModel.getPassword().length()-4));
		userService.addUser(userModel);
		dealerService.unableDealerById(dealerId);
		return "";
	}
	
	@RequestMapping(value = "/DealerRegistation", method = RequestMethod.POST)
	@ResponseBody
	public String DealerRegistation(
			ModelMap model, HttpServletRequest request) {
		String dname = request.getParameter("dname");
		String fname = request.getParameter("fname");
		String email = request.getParameter("email");
		String mob = request.getParameter("mob");
		String branchName = request.getParameter("branchName");
		String uname = request.getParameter("uname");
		String password = request.getParameter("password");
		String discount = request.getParameter("discount");
		String officeNo = request.getParameter("officeNo");
		String address = request.getParameter("address");
		String pinCode = request.getParameter("pinCode");
		//String registrationDate = request.getParameter("registrationDate");
	//	Date dealerRegistrationDate = null;

		/*try {
			String[] fromSplit = registrationDate.split("/");

			String fromSplitDate = fromSplit[2] + "-" + fromSplit[0] + "-" + fromSplit[1];

			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
				dealerRegistrationDate = df.parse(fromSplitDate + " 00:00:00");

			} catch (ParseException e) {
				e.printStackTrace();
			}*/
	
		try {
			UserModel adminUser=null;
			
			if((dname.equalsIgnoreCase("0")) || (dname=="0"))
			{
				  HttpSession httpSession = request.getSession();
				  adminUser=(UserModel) httpSession.getAttribute("ActUserId");	
			}
			else
			{
				Dealer dealer=dealerService.getDealerById(dname);
				adminUser=dealer.getUserModel();	
			}	
			int MaxId=dealerService.getMaxIdOfDealer()+1;
			RoleModel role=new RoleModel();
			role.setId(3);
			UserModel userModel=new UserModel();
			userModel.setFirstName(fname);
			userModel.setMobileNo(mob);
			userModel.setUserName(uname);
			userModel.setPassword(password);
			userModel.setUserRoll(role);
			UserModel userModel2=userService.addUser(userModel);
			Dealer dealer=new Dealer();
			dealer.setFullName(fname);
			dealer.setBranchName(branchName);
			dealer.setMobileNum(mob);
			dealer.setEmail(email);
			dealer.setRegDate(new Date());
			dealer.setUserModel(userModel2);
			dealer.setMainDealer(adminUser);
			dealer.setDealecode("SNBDL"+MaxId);
			dealer.setDiscount(Integer.parseInt(discount));
			dealer.setAddress(address);
			dealer.setPincode(pinCode);
			dealer.setOfficeNo(officeNo);
			dealerService.saveDealer(dealer);
			return "success";

			
		} catch (Exception e) {
			e.printStackTrace();
		}
			
		return "fails";
	}
	
	@RequestMapping(value = "/SubDealerRegistation", method = RequestMethod.POST)
	@ResponseBody
	public String SubDealerRegistation(
			ModelMap model, HttpServletRequest request) {
		String fname = request.getParameter("fname");
		String email = request.getParameter("email");
		String mob = request.getParameter("mob");
		String branchName = request.getParameter("branchName");
		String uname = request.getParameter("uname");
		String password = request.getParameter("password");
		String discount = request.getParameter("discount");
		String officeNo = request.getParameter("officeNo");
		String address = request.getParameter("address");
		String pinCode = request.getParameter("pinCode");
        String registrationDate = request.getParameter("registrationDate");
    	Date dealerRegistrationDate = null;

		try {
			String[] fromSplit = registrationDate.split("/");

			String fromSplitDate = fromSplit[2] + "-" + fromSplit[0] + "-" + fromSplit[1];

			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
				dealerRegistrationDate = df.parse(fromSplitDate + " 00:00:00");

			} catch (ParseException e) {
				e.printStackTrace();
			}
		try {
			
		HttpSession httpSession = request.getSession();
		UserModel adminUser=(UserModel) httpSession.getAttribute("ActUserId");
		int MaxId=dealerService.getMaxIdOfDealer()+1;
		RoleModel role=new RoleModel();
		role.setId(3);
		UserModel userModel=new UserModel();
		userModel.setFirstName(fname);
		userModel.setMobileNo(mob);
		userModel.setUserName(uname);
		userModel.setPassword(password);
		userModel.setUserRoll(role);
		UserModel userModel2=userService.addUser(userModel);
		Dealer dealer=new Dealer();
		dealer.setFullName(fname);
		dealer.setBranchName(branchName);
		dealer.setMobileNum(mob);
		dealer.setEmail(email);
		dealer.setRegDate(dealerRegistrationDate);
		dealer.setUserModel(userModel2);
		dealer.setMainDealer(adminUser);
		dealer.setDealecode("SNBDL"+MaxId);
		dealer.setDiscount(Integer.parseInt(discount));
		dealer.setAddress(address);
		dealer.setPincode(pinCode);
		dealer.setOfficeNo(officeNo);
		dealerService.saveDealer(dealer);
		return "success";
		} catch (Exception e) {
			e.printStackTrace();
		}
			
		return "fails";
	}
	@RequestMapping(value = "/updateDealer", method = RequestMethod.POST)
	@ResponseBody
	public String updateDealer(@RequestParam("fname") String fname,
			@RequestParam("email") String email,@RequestParam("mob") String mob,
			@RequestParam("branchName") String branchName,
			@RequestParam("uname") String uname,@RequestParam("password") String password,
			@RequestParam("dealerId") String dealerId,
			@RequestParam("discount") String discount,
			@RequestParam("officeNo") String officeNo,
			@RequestParam("address") String address,
			@RequestParam("pinCode") String pinCode,
			@RequestParam("hiddenRegistrationDate") String hiddenRegistrationDate,
			ModelMap model, HttpServletRequest request) throws ParseException {
		try {
			Date dealerRegistrationDate = null;
			try {
				String[] registrationDateSplit = hiddenRegistrationDate.split("-");

				String registrationDateSplitDate = registrationDateSplit[0] + "-" + registrationDateSplit[1] + "-" + registrationDateSplit[2];
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				dealerRegistrationDate = df.parse(registrationDateSplitDate + " 00:00:00");
			} catch (ParseException e) {
				e.printStackTrace();
			}
			RoleModel role=new RoleModel();
			role.setId(3);

			Dealer deal=dealerService.getDealerById(dealerId);
			UserModel usermodel=deal.getUserModel();
			UserModel userModel1 = userService.getUserByUname(uname);
			UserModel userModel2=null;

			if((userModel1==null) ||( usermodel.getUserId().toString().equals(userModel1.getUserId().toString())) )
			{
				Dealer dealeEmail = dealerService.getDealerByEmail(email);

				if((dealeEmail) ==null ||(dealeEmail.getEmail().equalsIgnoreCase(deal.getEmail()) ))
				{
					Dealer dealerMobile = dealerService.getDealerByMobile(mob);
					if((dealerMobile) ==null ||(dealerMobile.getId()== deal.getId()))
					{
						usermodel.setFirstName(fname);
						usermodel.setMobileNo(mob);
						usermodel.setUserName(uname);
						usermodel.setPassword(password);
						usermodel.setUserRoll(role);
						userModel2=userService.addUser(usermodel);



						//Dealer dealer=new Dealer();
						deal.setFullName(fname);
						deal.setBranchName(branchName);
						deal.setMobileNum(mob);
						deal.setEmail(email);
						deal.setDiscount(Integer.parseInt(discount));
						deal.setOfficeNo(officeNo);
						deal.setPincode(pinCode);
						deal.setAddress(address);
						deal.setUserModel(userModel2);
						//deal.setRegDate(dealerRegistrationDate);
						deal.setMainDealer(deal.getMainDealer());

						dealerService.saveDealer(deal);
						return "success";
					}
					else
					{
						return "cant Update User Mobile Already Used";
					}



				}
				else
				{
					return "cant Update User Email Already Used";
				}

			}
			else
			{
				return "cant Update User Name Already Used";
			}


		} catch (Exception e) {
		}
			
		return "fails";
	}
	
	@RequestMapping(value = "/updateSaleKitDealer", method = RequestMethod.POST)
	@ResponseBody
	public String validateUserByDealer(@RequestParam("kitUsername") String kitUsername,
			@RequestParam("kitPassword") String kitPassword,HttpServletRequest request) {
		
		Kit kit= null;
		String result=null;
		Dealer kitDealer=null;
		try {
			
			kit= kitService.findKitByKitUserNamePassword(kitUsername, kitPassword);
		} catch (Exception e) {
		}
			
		if(kit==null)
		{
			result="one";
		}
		else
		{
			try {
				
				kitDealer=kit.getDealer();
				if(kitDealer!=null)
				{
					result="two";
					
				}
				else
				{
					HttpSession session=request.getSession();
					Dealer dealer=(Dealer) session.getAttribute("DealerUserModel");
					kitService.updateDealerOfKit(kitUsername, kitPassword, dealer);
					
					
					DealerSellKitDetails dealerSellKitDetails=new DealerSellKitDetails();
					
					dealerSellKitDetails.setDealer(dealer);
					dealerSellKitDetails.setPackageId(kit.getPackage_id());
					dealerSellKitDetails.setPriceAmount(Integer.parseInt((kit.getPackage_id().getAmount())));
					dealerSellKitDetails.setUpdateDate(new Date());
					double kitPrice = Double.parseDouble(kit.getPackage_id().getAmount());
					float discountAmount=(float) Math.round(kitPrice * dealer.getDiscount()) / 100;
					dealerSellKitDetails.setDiscountAmount(discountAmount);
					dealerService.saveDealerSellKitDetails(dealerSellKitDetails);
					result="success";
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		return result;
	}
	@RequestMapping(value = "/updateuserDealerAssociation", method = RequestMethod.POST)
	@ResponseBody
	public String updateuserDealerAssociation(@RequestParam("regId") String regId,
			HttpServletRequest request) {
		UserModel usermodel=null;
		String result=null;
		
		try {
			
			usermodel=userService.getUserById(Integer.parseInt(regId));
		} catch (Exception e) {
		}
		
		if(usermodel==null)
		{
			
			result="one";
		}else
		{
			try {
				
				UserModelDetails userModelDeails=userService.getUserdetails(usermodel);
				if(userModelDeails.getDealer()!=null)
				{
					
					result="two";
				}
				else
				{
					
					HttpSession session=request.getSession();
					Dealer dealer=(Dealer) session.getAttribute("DealerUserModel");
					userModelDeails.setDealer(dealer);
					userService.updateUserModelDetailsWithDealer(userModelDeails);
					result="success";
					
				}
				
				
			} catch (Exception e) {
				
			}		
		}
		
		return result;
	}
@RequestMapping(value = "/validateUserByDealer", method = RequestMethod.POST)
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

@RequestMapping(value = "/validateEmailByDealer", method = RequestMethod.POST)
@ResponseBody
public String validateEmail(HttpServletRequest request) {
	JSONObject obj;
	try {
		obj = new JSONObject(request.getParameter("jsonObjectData1"));
		final JSONObject jsonObj = obj;
		String email = jsonObj.getString("email");

		Dealer modelDetails = dealerService.getDealerByEmail(email);
		if (modelDetails == null) {

			return "Success";
		} else {
			return "AlreadyAvailable";
		}

	} catch (Exception e) {

	}
	return "";
}
@RequestMapping(value = "/validateMobileByDealer", method = RequestMethod.POST)
@ResponseBody
public String validateMobile(HttpServletRequest request) {

	JSONObject obj;
	try {
		obj = new JSONObject(request.getParameter("jsonObjectData3"));
		final JSONObject jsonObj = obj;
		String mobile = jsonObj.getString("mob");
	
		Dealer modelDetails = dealerService.getDealerByMobile(mobile);
		
		if (modelDetails == null) {

			return "Success";
		} else {
			return "AlreadyAvailable";
		}

	} catch (Exception e) {

	}
	return "";
}
@RequestMapping(value = "/DealerRegisteredUser", method = RequestMethod.GET)
public ModelAndView registeredUser(ModelMap model, HttpServletRequest request) {
	JSONObject js = new JSONObject();
	ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
	try {
			HttpSession session=request.getSession();
			Dealer userModelDealer=(Dealer) session.getAttribute("DealerUserModel");
			
			List<AdminUserModel> list=userService.getListOfUserModelDetailsForDealerByDealer(userModelDealer);
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
	        
	} catch (Exception e) {
		e.printStackTrace();
	}
	return new ModelAndView("DealerRegisteredUser"); 
}
@RequestMapping(value = "/getMemberByDealer", method = RequestMethod.GET)
public ModelAndView getMemberByDealer(@RequestParam("dealerId") String dealerId,ModelMap model, HttpServletRequest request) {
	try {
		
		Dealer dealer=dealerService.getDealerById(dealerId);
		List<AdminUserModel> list=userService.getListOfUserModelDetailsForDealerByDealer(dealer);
		ObjectMapper mapper = new ObjectMapper();
		model.addAttribute("userModelDetailList", mapper.writeValueAsString(list).replaceAll("'", " ").replaceAll(" ," , "")); 
	        
	} catch (Exception e) {
		e.printStackTrace();
	}
	return new ModelAndView("userByDealer");
}

@RequestMapping(value = "/dealerWiseSubDealer", method = RequestMethod.GET)
public ModelAndView dealerWiseSubDealer(ModelMap model, HttpServletRequest request) {
	List<Dealer> list=dealerService.getDealerList();
	model.addAttribute("DealerList", list);
	HttpSession httpSession = request.getSession();
	model.addAttribute("loginUserRole", "Admin");
	return new ModelAndView("dealerWiseSubDealer");
}

@RequestMapping(value = "/getDealerWiseSubDealer", method = RequestMethod.GET)
public ModelAndView getDealerWiseSubDealer(@RequestParam("dealerId") String dealerId,ModelMap model, HttpServletRequest request) {
	try {

		UserModel userModel =  dealerService.getUserModelByDealerId(dealerId);
		List<Dealer> subDealerList = dealerService.getAllSubDealerByMainDealer(userModel);
		ObjectMapper mapper = new ObjectMapper();
		model.addAttribute("dealerWiseSubDealerList", mapper.writeValueAsString(subDealerList).replaceAll("'", " ").replaceAll(" ," , "")); 

	} catch (Exception e) {
		e.printStackTrace();
	}
	return new ModelAndView("subDealerByDealer");
}
}
	
	
	
