package com.xanadutec.coreflex.location;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.sun.mail.iap.ProtocolException;
import com.xanadutec.coreflex.BranchDetails.BranchService;
import com.xanadutec.coreflex.EducationOccupation.EducationOccupationService;
import com.xanadutec.coreflex.Religion.ReligionService;
import com.xanadutec.coreflex.model.AdminUpgradedUser;
import com.xanadutec.coreflex.model.AdminUserModel;
import com.xanadutec.coreflex.model.BranchModel;
import com.xanadutec.coreflex.model.Country;
import com.xanadutec.coreflex.model.DeleteUserDetail;
import com.xanadutec.coreflex.model.District_SubDistricts;
import com.xanadutec.coreflex.model.EducationCategory;
import com.xanadutec.coreflex.model.FAQS;
import com.xanadutec.coreflex.model.Kit;
import com.xanadutec.coreflex.model.OccupationCategory;
import com.xanadutec.coreflex.model.PartnerEducationAssociation;
import com.xanadutec.coreflex.model.Religion;
import com.xanadutec.coreflex.model.ServerInfo;
import com.xanadutec.coreflex.model.State_Country;
import com.xanadutec.coreflex.model.State_Districts;
import com.xanadutec.coreflex.model.UserModel;
import com.xanadutec.coreflex.model.UserModelDetails;
import com.xanadutec.coreflex.model.UserModelImagesName;
import com.xanadutec.coreflex.model.UserModelPartnerPreferances;
import com.xanadutec.coreflex.packageAndKit.PackageAndKitService;
import com.xanadutec.coreflex.revenue.RevenueService;
import com.xanadutec.coreflex.userTask.UserService;

@Controller
public class LocationController {

	@Autowired
	LocationService locationservice;
	@Autowired
	PackageAndKitService packageandkitService;
	@Autowired
	UserService userService;
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private ReligionService religionService;
	@Autowired
	EducationOccupationService educationOccupationService;
	@Autowired
	private RevenueService revService;
	@Autowired
	BranchService branchService;

	@RequestMapping(value = "/location", method = RequestMethod.GET)
	public ModelAndView loginSuccess(ModelMap model, HttpServletRequest request) {
		List<Country> countryList = locationservice.getListOfcountry();
		model.addAttribute("countryList", countryList);
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
		return new ModelAndView("LocationHome");
	}

	@RequestMapping(value = "/imagesSetting", method = RequestMethod.GET)
	public ModelAndView imagesSetting(ModelMap model, HttpServletRequest request) {
		List<Country> countryList = locationservice.getListOfcountry();
		model.addAttribute("countryList", countryList);
	    HttpSession httpSession = request.getSession();
		UserModel userModel=(UserModel) httpSession.getAttribute("ActUserId");
		UserModel currentUserModel = userService.getadminDetails();
		List<BranchModel> branchModelList = branchService.getBranchList();
		model.addAttribute("branchModelList", branchModelList);
		if(userModel.getUserRoll().getId()==4){
			model.addAttribute("loginUserRole", "Employee");
		}
		else{
			model.addAttribute("loginUserRole", "Admin");
		}
		model.addAttribute("currentAdminUserName", currentUserModel.getUserName());
		model.addAttribute("currentAdminPassword", currentUserModel.getPassword());
		return new ModelAndView("ImagesSetting");
	}


	@RequestMapping(value = "/uploadLogoFiles", method = RequestMethod.POST)
	@ResponseBody
	public String handleFileUpload(MultipartHttpServletRequest request) throws Exception {
		String fileName = null;
		String ActualFileName = "";
		final String pathact = request.getServletContext().getRealPath("/resources/RameshImages");
		File dir1 = new File(pathact);
		
		if (!dir1.exists()){
			dir1.mkdirs();
		}
		Iterator<String> itrator = request.getFileNames();
		String str = "";
		int i = 0;
      while (itrator.hasNext()) {
			MultipartFile multiFile = request.getFile(itrator.next());
			try {
				i++;
				// just to show that we have actually received the file
				 fileName =multiFile.getOriginalFilename();
				// making directories for our required path.
				byte[] bytes = multiFile.getBytes();
				File directory = new File(dir1.getAbsolutePath());
				directory.mkdirs();
				String extension = FilenameUtils.getExtension(fileName);
				String name = "@" + i +  "." + "png";
				ActualFileName = ActualFileName + name + "#";
				
				File file = new File(dir1.getAbsolutePath() + File.separator + name);
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(file));
				stream.write(bytes);
				stream.close();
				
			} catch (Exception e) 
			{
				e.printStackTrace();
				throw new Exception("Error while loading the file"+e.getMessage());
			}
		}
		locationservice.saveLogoFile(ActualFileName);		
	return "success";
		
	}

	@RequestMapping(value = "/uploadSuccessFiles", method = RequestMethod.POST)
	@ResponseBody
	public String uploadSuccessFiles(MultipartHttpServletRequest request) throws Exception {String fileName = null;
	String ActualFileName = "";
	final String pathact = request.getServletContext().getRealPath("/resources/RameshImages");
	File dir1 = new File(pathact);
	
	if (!dir1.exists()){
		dir1.mkdirs();
	}

	Iterator<String> itrator = request.getFileNames();
	String str = "";
	int i = 0;
   while (itrator.hasNext()) {
		MultipartFile multiFile = request.getFile(itrator.next());
		try {
			i=i+11;
			// just to show that we have actually received the file
			 fileName =multiFile.getOriginalFilename();
			// making directories for our required path.
			byte[] bytes = multiFile.getBytes();
			File directory = new File(dir1.getAbsolutePath());
			directory.mkdirs();
			String extension = FilenameUtils.getExtension(fileName);
			String name = "@" + i +  "." + "png";
			ActualFileName = ActualFileName + name + "#";
			
			File file = new File(dir1.getAbsolutePath() + File.separator + name);
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(file));
			stream.write(bytes);
			stream.close();
			
		} catch (Exception e) 
		{
			e.printStackTrace();
			throw new Exception("Error while loading the file"+e.getMessage());
		}
	}

	locationservice.saveSuccessFile(ActualFileName);
    return "success";
	}

	@RequestMapping(value = "/uploadBackgroundFiles", method = RequestMethod.POST)
	@ResponseBody
	public String uploadBackgroundFiles(MultipartHttpServletRequest request) throws Exception {

		String fileName = null;
		String ActualFileName = "";
		final String pathact = request.getServletContext().getRealPath("/resources/RameshImages");
		File dir1 = new File(pathact);

		if (!dir1.exists()) {
			dir1.mkdirs();
		}

		Iterator<String> itrator = request.getFileNames();
		String str = "";
		int i = 0;

		while (itrator.hasNext()) {
			MultipartFile multiFile = request.getFile(itrator.next());
			try {
				i = i + 100;
				fileName = multiFile.getOriginalFilename();
				byte[] bytes = multiFile.getBytes();
				File directory = new File(dir1.getAbsolutePath());
				directory.mkdirs();
				String extension = FilenameUtils.getExtension(fileName);
				String name = "@" + i + "." + "png";
				ActualFileName = ActualFileName + name + "#";

				File file = new File(dir1.getAbsolutePath() + File.separator + name);
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(file));
				stream.write(bytes);
				stream.close();

			} catch (Exception e) 
			{
				e.printStackTrace();
				throw new Exception("Error while loading the file" + e.getMessage());
			}
		}

		locationservice.saveBackgroundFile(ActualFileName);

		return "success";

	}
	
	
	
	
	
	@RequestMapping(value = "/uploadOfferFiles", method = RequestMethod.POST)
	@ResponseBody
	public String uploadOfferFiles(MultipartHttpServletRequest request) throws Exception {

		String fileName = null;
		String ActualFileName = "";
		final String pathact = request.getServletContext().getRealPath("/resources/RameshImages");
		File dir1 = new File(pathact);

		if (!dir1.exists()) {
			dir1.mkdirs();
		}

		Iterator<String> itrator = request.getFileNames();
		String str = "";
		int i = 0;

		while (itrator.hasNext()) {
			MultipartFile multiFile = request.getFile(itrator.next());
			try {
				i = i + 100;
				fileName = multiFile.getOriginalFilename();
				byte[] bytes = multiFile.getBytes();
				File directory = new File(dir1.getAbsolutePath());
				directory.mkdirs();
				String extension = FilenameUtils.getExtension(fileName);
				String name = "@offer." + "png";
				ActualFileName = ActualFileName + name + "#";

				File file = new File(dir1.getAbsolutePath() + File.separator + name);
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(file));
				stream.write(bytes);
				stream.close();

			} catch (Exception e) 
			{
				e.printStackTrace();
				throw new Exception("Error while loading the file" + e.getMessage());
			}
		}
		locationservice.saveOfferImage(ActualFileName);
		return "success";
	}
	
	@RequestMapping(value = "/saveSuccessStory", method = RequestMethod.POST)
	@ResponseBody
	public String saveSuccessStory(HttpServletRequest request,HttpServletResponse response,
			@RequestParam("chatBoxSuccessStories") String text) throws Exception 
	{
			locationservice.saveSuccessStoryText(text);		
			return "success";
    }
	
	@RequestMapping(value = "/saveFAQs", method = RequestMethod.POST)
	@ResponseBody
	public String saveFAQs(HttpServletRequest request,HttpServletResponse response,
			@RequestParam("question") String question,@RequestParam("answer") String answer
			) throws Exception {
		
		FAQS faqs=new FAQS();
		faqs.setQuestion(question);
		faqs.setAnswer(answer);
		locationservice.saveFAQs(faqs);
	return "success";

	}
	
	
	@RequestMapping(value = "/savePrivacyPolicy", method = RequestMethod.POST)
	@ResponseBody
	public String savePrivacyPolicy(HttpServletRequest request,HttpServletResponse response,
			@RequestParam("chatBox") String text
			) throws Exception 
	{
		try {
			
			locationservice.savePrivacyPolicy(text);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	return "success";
	}
	
	@RequestMapping(value = "/saveAboutUs", method = RequestMethod.POST)
	@ResponseBody
	public String saveAboutUs(HttpServletRequest request,HttpServletResponse response,
			@RequestParam("chatBoxFirstPortion") String firstPortion,
			@RequestParam("chatBoxSecondPortion") String secondPortion
			) throws Exception 
	{
			locationservice.saveAboutUs(firstPortion, secondPortion);
			
	        return "success";

	}
	
	
	@RequestMapping(value = "/saveTermAndCondition", method = RequestMethod.POST)
	@ResponseBody
	public String saveTermAndCondition(HttpServletRequest request,HttpServletResponse response,
			@RequestParam("chatBox") String chatBox
			
			) throws Exception {
		
			locationservice.saveTermAndCondition(chatBox);
			
	return "success";

	}
	
	
	
	
	
	
	//uploadOfferFiles

	@RequestMapping(value = "/getLogoImages", method = RequestMethod.GET)
	@ResponseBody
	public String getLogoImages(MultipartHttpServletRequest request) throws Exception {

		try {
			String logoName = locationservice.getLogoImages();
			return logoName;

		} catch (Exception e)
		{
			e.printStackTrace();
		}

		return "";
	}

	@RequestMapping(value = "/getSuccessImages", method = RequestMethod.POST)
	@ResponseBody
	public String getSuccessImages(MultipartHttpServletRequest request) throws Exception {

		try {
			String logoName = locationservice.getSuccessImages();
			return logoName;
		} catch (Exception e) 
		{
			e.printStackTrace();
		}

		return "";
	}

	@RequestMapping(value = "/getBackgroundImages", method = RequestMethod.POST)
	@ResponseBody
	public String getBackgroundImages(MultipartHttpServletRequest request) throws Exception {

		try {
			String logoName = locationservice.getBackgroundmages();
			return logoName;
		} catch (Exception e) 
		{
			e.printStackTrace();
		}

		return "";
	}

	//

	@RequestMapping(value = "/registeredUser", method = RequestMethod.GET)
	public ModelAndView registeredUser(ModelMap model, HttpServletRequest request) {

		JSONObject js = new JSONObject();
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		try {

			long maxCount = userService.getMaxCountOfUserDetails();
			
			ObjectMapper mapper = new ObjectMapper();
			try {
				List<AdminUserModel> list = userService.getListOfUserModelDetailsForAdminPanel();
				if (list != null && !list.isEmpty()) {
					model.addAttribute("userModelDetailList", mapper.writeValueAsString(list).replaceAll("'", " ").replaceAll(" ,", ""));
				} else {
					model.addAttribute("userModelDetailList", "[]");
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				model.addAttribute("userModelDetailList", "[]");
			}
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
			model.addAttribute("pagination_maxCount", maxCount);
			HttpSession httpSession = request.getSession();
			UserModel userModel=(UserModel) httpSession.getAttribute("ActUserId");
			if(userModel != null && userModel.getUserRoll() != null && userModel.getUserRoll().getId()==4)
			{
				model.addAttribute("loginUserRole", "Employee");
			}
			else
			{
				model.addAttribute("loginUserRole", "Admin");
			}
			

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("registeredUser");
	}
	
	@ResponseBody
	@RequestMapping(value = "/globalSearch", method = RequestMethod.GET)
	public String globalSearch(HttpServletRequest request,@RequestParam("searchText") String searchText) {
		String json = null;
		JSONObject js = new JSONObject();
		try {
			ObjectMapper mapper = new ObjectMapper();
			List<AdminUserModel> list = userService.globalSearch(searchText);
			js.put("userModelDetailList", mapper.writeValueAsString(list).replaceAll("'", " ").replaceAll(" ,", ""));
			json = js.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}
	
	@ResponseBody
	@RequestMapping(value = "/registeredUserServerSideData", method = RequestMethod.POST)
	public String registeredUserServerSideData(HttpServletRequest request) {
		try {
			int draw = Integer.parseInt(request.getParameter("draw") == null ? "1" : request.getParameter("draw"));
			int start = Integer.parseInt(request.getParameter("start") == null ? "0" : request.getParameter("start"));
			int length = Integer.parseInt(request.getParameter("length") == null ? "100" : request.getParameter("length"));
			String search = request.getParameter("search[value]");
			
			String orderColumnStr = request.getParameter("order[0][column]");
			int orderColumn = orderColumnStr != null ? Integer.parseInt(orderColumnStr) : 4;
			String orderDir = request.getParameter("order[0][dir]");
			if (orderDir == null) orderDir = "desc";

			Map<Integer, String> columnFilters = new HashMap<>();
			for (int i = 0; i < 7; i++) {
				String colSearch = request.getParameter("columns[" + i + "][search][value]");
				if (colSearch != null && !colSearch.trim().isEmpty()) {
					columnFilters.put(i, colSearch);
				}
			}

			List<AdminUserModel> data = userService.getRegisteredUsersForDataTable(start, length, search, columnFilters, orderColumn, orderDir);
			long recordsFiltered = userService.getRegisteredUsersForDataTableCount(search, columnFilters);
			long recordsTotal = userService.getMaxCountOfUserDetails();

			Map<String, Object> respMap = new HashMap<>();
			respMap.put("draw", draw);
			respMap.put("recordsTotal", recordsTotal);
			respMap.put("recordsFiltered", recordsFiltered);
			respMap.put("data", data);
			ObjectMapper mapper = new ObjectMapper();
			return mapper.writeValueAsString(respMap);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "{\"draw\":1,\"recordsTotal\":0,\"recordsFiltered\":0,\"data\":[]}";
		}
	}
	
	@RequestMapping(value = "/registeredUserAjax", method = RequestMethod.GET)
	public ModelAndView registeredUserAjax(ModelMap model, HttpServletRequest request,@RequestParam("pageNo") String pageNo) {
		
		
		//JSONObject js = new JSONObject();
		//ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
//		try {
//			 int pageNumber = Integer.parseInt(pageNo);
//			List<AdminUserModel> list = userService.getListOfUserModelDetailsForAdminPanelAjax(pageNumber);
//
//			//List<AdminUserModel> list = userService.getListOfUserModelDetailsForAdminPanel();
//			
//			long maxCount = userService.getMaxCountOfUserDetails();
//			
//			
//			List<UserModelDetails> userModelDetailsList = userService.getListOfUserModelDetails();
//			List<UserModel>userModelsList = userService.listUser();
//			ObjectMapper mapper = new ObjectMapper();
//			model.addAttribute("userModelDetailList",mapper.writeValueAsString(list).replaceAll("'", " ").replaceAll(" ,", ""));
//		    List<Country> countries = locationservice.getListOfcountry();
//			List<State_Country> state_Countries = locationservice.getListOfStateByCountryId(1 + "");
//
//			List<Religion> religions = religionService.getListOfReligion();
//			List<EducationCategory> educationCategories = educationOccupationService.getListOfEducationCategory();
//			List<OccupationCategory> occupationCategories = educationOccupationService.getListOfOccupation();
//			model.addAttribute("countries", countries);
//			model.addAttribute("state_Countries", state_Countries);
//			model.addAttribute("religions", religions);
//			model.addAttribute("educationCategories", educationCategories);
//			model.addAttribute("occupationCategories", occupationCategories);
//			model.addAttribute("userModelDetailsList", userModelDetailsList);
//			model.addAttribute("userModelsList", userModelsList);
//			model.addAttribute("pagination_maxCount", maxCount);
//			HttpSession httpSession = request.getSession();
//			UserModel userModel=(UserModel) httpSession.getAttribute("ActUserId");
//			if(userModel.getUserRoll().getId()==4)
//			{
//				model.addAttribute("loginUserRole", "Employee");
//			}
//			else
//			{
//				model.addAttribute("loginUserRole", "Admin");
//			}
//			
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

		JSONObject js = new JSONObject();
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		try {
            int pageNumber = Integer.parseInt(pageNo);
            System.out.println("pageNo:"+pageNo);
			//List<AdminUserModel> list = userService.getListOfUserModelDetailsForAdminPanel();
			List<AdminUserModel> list = userService.getListOfUserModelDetailsForAdminPanelAjax(pageNumber);
			
			long maxCount = userService.getMaxCountOfUserDetails();
			
			
			//List<UserModelDetails> userModelDetailsList = userService.getListOfUserModelDetails();
			//List<UserModel>userModelsList = userService.listUser();
			ObjectMapper mapper = new ObjectMapper();
			model.addAttribute("userModelDetailList",mapper.writeValueAsString(list).replaceAll("'", " ").replaceAll(" ,", ""));
		    
			model.addAttribute("pagination_maxCount", maxCount);
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
			

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("registeredUserAjax");
	}

	@RequestMapping(value = "/deleteUser", method = RequestMethod.GET)
	public ModelAndView deleteUser(ModelMap model, HttpServletRequest request) {

		try {
						
			List<AdminUserModel> list = userService.getDeletedUserInAdminFormat();		
			ObjectMapper mapper = new ObjectMapper();
			model.addAttribute("deleteUserList", mapper.writeValueAsString(list));		
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

		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		return new ModelAndView("deletedUser");
	}

	@RequestMapping(value = "/onlineupgradedUser", method = RequestMethod.GET)
	public ModelAndView onlineupgradedUser(ModelMap model, HttpServletRequest request) {

		try {

			List<AdminUpgradedUser> list = revService.getListOnlineUpgradedUser();
			ObjectMapper mapper = new ObjectMapper();
			model.addAttribute("upgradedUserList", mapper.writeValueAsString(list));
			
			
			
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

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("onlineupgradedUser");
	}

	@RequestMapping(value = "/getUserModelPartnerPreferancesByUserModelId", method = RequestMethod.POST)
	@ResponseBody
	public String getUserModelPartnerPreferancesByUserModelId(@RequestParam("userId") String userId, ModelMap model,
			HttpServletRequest request) {

//		org.hibernate.Session sess = sessionFactory.openSession();
//		UserModel userModel = (UserModel) sess.get(UserModel.class, Long.parseLong(userId));
//		sess.close();
		
		UserModel userModel = userService.getUserById(Integer.parseInt(userId));
		
		UserModelPartnerPreferances UserPreference = userService.getUserModelPartnerPreferancesByUserModelId(userModel);
		UserModelDetails userModelDetail = userService.getUserdetails(userModel);
		String kundaliImgString = null;
		String kundaliImgs[] = null;
		
		JSONObject mainObj = new JSONObject();
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = null;
		String imgs[] = null;
		try {
			
			UserModelImagesName imagesName = userService.getImageNameAccordingToUserModel(userModel);
			
			
			try {
				 String imString = imagesName.getImageNames();
				 imgs = imString.split("#");
			     kundaliImgString = imagesName.getKundaliImage();
				 kundaliImgs = kundaliImgString.split("#");
				
			} catch (Exception e)
			{
			}
			
			try {

				JSONObject js = new JSONObject();
				js.put("preference", ow.writeValueAsString(UserPreference));
				js.put("userDetail", ow.writeValueAsString(userModelDetail));
				js.put("profileImages", ow.writeValueAsString(imgs));
				js.put("kundaliImage", ow.writeValueAsString(kundaliImgs));
				js.put("userName", userModel.getUserName());
				js.put("password", userModel.getPassword());
				js.put("firstName", userModel.getFirstName() != null ? userModel.getFirstName() : "");

				try {
					js.put("userImage", ow.writeValueAsString(imgs[0]));
				} catch (Exception e) {

				}

				json = js.toString();

			} catch (Exception e) {

				e.printStackTrace();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
		return json;

	}

	@RequestMapping(value = "/sendEmailToRegisteredUser", method = RequestMethod.POST)
	@ResponseBody
	public String sendPassword(@RequestParam("email") String email, @RequestParam("subject") String subject,
			@RequestParam("message") String message, Model uiModel, HttpServletRequest request) {

		final String email2 = email;
		final String subject1 = subject;
		final String message1 = message;
		final HttpServletRequest req = request;

		org.hibernate.Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(ServerInfo.class);
		List<ServerInfo> list = criteria.list();
		session.close();
		final ServerInfo serverInfo = list.get(0);

		Thread tt = new Thread(new Runnable() {

			public void run() {

				sendEmail(subject1, message1, email2, req, serverInfo);
			}
		});
		tt.start();
		return "Success";
	}

	String sendEmail(String subject1, String message1, String email, HttpServletRequest request,
			final ServerInfo serverInfo) {


		try {

			Properties properties = new Properties();
			properties.put("mail.smtp.auth", "true");
			properties.put("mail.smtp.host", serverInfo.getMail_host());
			properties.put("mail.smtp.port", serverInfo.getMail_port());
			Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(serverInfo.getMail_serverName(), serverInfo.getMail_password());
				}
			});


			try {

				try {

					Message replyMessage = new MimeMessage(session);
					replyMessage.setSubject(subject1);
					replyMessage.setFrom(new InternetAddress(serverInfo.getMail_serverName(), serverInfo.getMsgName()));
					replyMessage.setContent(message1, "text/html");
					replyMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email, false));
					try {

						BodyPart messageBodyPart = new MimeBodyPart();

						messageBodyPart.setText(message1);
						Multipart multipart = new MimeMultipart();
						MimeBodyPart messageBodyP = new MimeBodyPart();
						messageBodyP.setContent(message1, "text/html");
						multipart.addBodyPart(messageBodyP);

						replyMessage.setContent(multipart);

						try {
							Transport.send(replyMessage);
						} catch (Exception e) {
							e.printStackTrace();
						}

					} catch (Exception e) {
						e.printStackTrace();
					}

				} catch (Exception e) {
					e.printStackTrace();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Success";

	}

	
	@RequestMapping(value = "/saveCountry", method = RequestMethod.POST)
	@ResponseBody
	public String saveCountry(@RequestParam("cname") String name, ModelMap model, HttpServletRequest request) {
		locationservice.saveCountryByName(name);
		return "Saved Successfully";

	}

	@RequestMapping(value = "/saveState", method = RequestMethod.POST)
	@ResponseBody
	public String saveState(@RequestParam("cname") String name, @RequestParam("stateName") String stateName,
			ModelMap model, HttpServletRequest request) {

		locationservice.saveStateByCountry(name, stateName);
		return "Saved Successfully";

	}

	@RequestMapping(value = "/saveDistrict", method = RequestMethod.POST)
	@ResponseBody
	public String saveDistrict(@RequestParam("cname") String name, @RequestParam("stateName") String stateName,
			@RequestParam("distName") String distName, ModelMap model, HttpServletRequest request) {

		locationservice.saveDistrict(stateName, distName);
		return "Saved Successfully";

	}

	@RequestMapping(value = "/saveSubDistrict", method = RequestMethod.POST)
	@ResponseBody
	public String saveSubDistrict(@RequestParam("cname") String name, @RequestParam("stateName") String stateName,
			@RequestParam("distName") String distName, @RequestParam("subDistName") String subDistName, ModelMap model,
			HttpServletRequest request) 
	{
		locationservice.saveSubdistric(distName, subDistName);
		return "Saved Successfully";

	}

	@RequestMapping(value = "/getstateByCountry", method = RequestMethod.POST)
	@ResponseBody
	public String getstateByCountry(@RequestParam("countryId") String countryId, ModelMap model,
			HttpServletRequest request) {

		List<State_Country> list = locationservice.getListOfStateByCountryId(countryId);

		try {

			JSONObject js = new JSONObject();
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			js.put("stateList", ow.writeValueAsString(list));
			String json = js.toString();
			return json;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";

	}

	@RequestMapping(value = "/getDistrictByState", method = RequestMethod.POST)
	@ResponseBody
	public String getDistrictByState(@RequestParam("statId") String stateId, ModelMap model,
			HttpServletRequest request) {

		List<State_Districts> list = locationservice.getListOfDistrictByStateId(stateId);
		try {

			JSONObject js = new JSONObject();
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			js.put("DistrictList", ow.writeValueAsString(list));
			String json = js.toString();
			return json;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";

	}

	@RequestMapping(value = "/getSubdistrictByDistrict", method = RequestMethod.POST)
	@ResponseBody
	public String getSubdistrictByDistrict(@RequestParam("distId") String distId, ModelMap model,
			HttpServletRequest request) {

		List<District_SubDistricts> list = locationservice.getListOfSubDistrictByDistrictId(distId);
		try {

			JSONObject js = new JSONObject();
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			js.put("subDistrictList", ow.writeValueAsString(list));
			String json = js.toString();
			return json;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";

	}
}
