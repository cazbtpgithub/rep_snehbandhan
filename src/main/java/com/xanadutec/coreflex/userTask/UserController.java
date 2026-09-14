package com.xanadutec.coreflex.userTask;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
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

import com.xanadutec.coreflex.EducationOccupation.EducationOccupationService;
import com.xanadutec.coreflex.Kit.KitService;
import com.xanadutec.coreflex.Religion.ReligionService;
import com.xanadutec.coreflex.location.LocationService;
import com.xanadutec.coreflex.login.LoginController;
import com.xanadutec.coreflex.model.AdminUpgradedUser;
import com.xanadutec.coreflex.model.Caste;
import com.xanadutec.coreflex.model.Country;
import com.xanadutec.coreflex.model.District_SubDistricts;
import com.xanadutec.coreflex.model.Education;
import com.xanadutec.coreflex.model.EducationCategory;
import com.xanadutec.coreflex.model.Occupation;
import com.xanadutec.coreflex.model.OccupationCategory;
import com.xanadutec.coreflex.model.PartnerEducationAssociation;
import com.xanadutec.coreflex.model.PartnerOccupationAssociation;
import com.xanadutec.coreflex.model.Religion;
import com.xanadutec.coreflex.model.State_Country;
import com.xanadutec.coreflex.model.State_Districts;
import com.xanadutec.coreflex.model.Status;
import com.xanadutec.coreflex.model.UserModel;
import com.xanadutec.coreflex.model.UserModelDetails;
import com.xanadutec.coreflex.model.UserModelImagesName;
import com.xanadutec.coreflex.model.UserModelPartnerPreferances;
import com.xanadutec.coreflex.revenue.RevenueService;

@Controller
public class UserController {

	@Autowired
	UserService userService;
	
	@Autowired
	LocationService locationService;
	@Autowired
	ReligionService religionService;
	@Autowired
	EducationOccupationService educationOccupationService;
	@Autowired
	KitService kitService;
	@Autowired
	RevenueService revService;
	Logger logger = Logger.getLogger(UserController.class);

	@RequestMapping(value = "/editProfile", method = RequestMethod.GET)
	public ModelAndView saveCountry(ModelMap model, final HttpServletRequest request) 
	{
		final String pathact = request.getServletContext().getRealPath("/resources/UserImages");
		HttpSession httpSession = request.getSession();
		UserModel userModel = (UserModel) httpSession.getAttribute("ActUserId");
		if (userModel == null) {
			List<UserModel> users = userService.listUser();
			if (users != null && !users.isEmpty()) {
				userModel = users.get(0);
				httpSession.setAttribute("ActUserId", userModel);
			} else {
				return new ModelAndView("redirect:/sneh/login");
			}
		}
		final UserModelDetails modelDetails = userService.getUserdetails(userModel);
		UserModelImagesName imagesNames = userService.getImageNameAccordingToUserModel(userModel);

		UserModelPartnerPreferances userPartnerPreferances = userService
				.getUserModelPartnerPreferancesByUserModelId(userModel);
		try{
			String imgNames[]=imagesNames.getImageNames().split("#");
			model.addAttribute("profileImages", imgNames);
			model.addAttribute("userEmail", httpSession.getAttribute("userEmail"));
			model.addAttribute("userPass", httpSession.getAttribute("userPass"));
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		String userEmailId = null;
		try {
			if( modelDetails.getEmail() != null)
			{
				String emailId = modelDetails.getEmail();
				if(emailId.equalsIgnoreCase("-"))
				{
					userEmailId = "-";
				}
				else
				{
					String autogenratedEmail = emailId.substring(0,3);
					if(autogenratedEmail.equalsIgnoreCase("abc"))
					{
						userEmailId = "-";
					}
					else
					{ 
						userEmailId = modelDetails.getEmail();

					}

				}


			}
			else
			{
				userEmailId = modelDetails.getEmail();
			}
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
	
		
		model.addAttribute("userMailId", userEmailId);

		model.addAttribute("modelDetails", modelDetails);
	
		model.addAttribute("userPartnerPreferances", userPartnerPreferances);
		
		try{
			Date date =modelDetails.getDateOfBirth();
			   SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			 String   strDate = sdf.format(date);
			 model.addAttribute("dob", strDate);
			
		}catch(Exception e){
			
		}
		List<Country> countries = locationService.getListOfcountry();
		List<State_Country> state_Countries = locationService.getListOfStateByCountryId(1 + "");
		List<Religion> religions = religionService.getListOfReligion();
		List<EducationCategory> educationCategories = educationOccupationService.getListOfEducationCategory();
		List<OccupationCategory> occupationCategories = educationOccupationService.getListOfOccupation();
		UserModelImagesName userModelImagesName = userService.getImageNameAccordingToUserModel(userModel);
		String kundaliImageName = userModelImagesName.getKundaliImage();
		model.addAttribute("kundaliImageName", kundaliImageName);
		model.addAttribute("countries", countries);
		model.addAttribute("state_Countries", state_Countries);
		model.addAttribute("religions", religions);
		model.addAttribute("educationCategories", educationCategories);
		model.addAttribute("occupationCategories", occupationCategories);

		return new ModelAndView("EditProfile");
	}
	
	
	@RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
	@ResponseBody
	public String changePassword(ModelMap model, final HttpServletRequest request) {
		
		JSONObject js = new JSONObject();
		JSONObject obj;
		try {
			obj = new JSONObject(request.getParameter("jsonObjectData"));
			final JSONObject jsonObj = obj;
			String changePassword = jsonObj.getString("changePassword");
			HttpSession httpSession = request.getSession();
			UserModel userModel = (UserModel) httpSession.getAttribute("ActUserId");
			userModel.setPassword(changePassword);
			userService.updateUser(userModel);
		}catch(Exception e){
			
		}
			
		return "success";
	}
	
	@RequestMapping(value = "/getPartnerEductionAssociation", method = RequestMethod.GET)
	@ResponseBody
	public String getPartnerEductionAssociation(ModelMap model, 
			 HttpServletRequest request) {
		
		HttpSession httpSession = request.getSession();
		UserModel userModel = (UserModel) httpSession.getAttribute("ActUserId");
		List<PartnerEducationAssociation> educationAssociation=userService.getPartnerEducationAssociationByUserModel(userModel);
		
		JSONObject mainObj = new JSONObject();
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = null;
		try {
			json = ow.writeValueAsString(educationAssociation);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return json;
	}
	@RequestMapping(value = "/getPartnerEductionAssociationByAdmin", method = RequestMethod.GET)
	@ResponseBody
	public String getPartnerEductionAssociationByAdmin(@RequestParam("userId") String userId,ModelMap model, 
			 HttpServletRequest request) {
		
		UserModel userModel = new UserModel();
		userModel.setUserId(Long.parseLong(userId));
		List<PartnerEducationAssociation> educationAssociation=userService.getPartnerEducationAssociationByUserModel(userModel);
		
		JSONObject mainObj = new JSONObject();
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = null;
		try {
			json = ow.writeValueAsString(educationAssociation);
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		return json;
	}
	
	@RequestMapping(value = "/getPartnerOccupationAssociationByAdmin", method = RequestMethod.GET)
	@ResponseBody
	public String getPartnerOccupationAssociationByAdmin(@RequestParam("userId") String userId,ModelMap model, 
			 HttpServletRequest request) {
		
		UserModel userModel = new UserModel();
		userModel.setUserId(Long.parseLong(userId));
		String json = null;
		
		
		
		try {

			List<PartnerOccupationAssociation> partnerAssociation = userService
					.getPartnerOccuptionAssociationByUserModel(userModel);
			
			
			JSONObject mainObj = new JSONObject();
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			json = ow.writeValueAsString(partnerAssociation);
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		return json;
	}

	@RequestMapping(value = "/getPartnerOccupationAssociation", method = RequestMethod.GET)
	@ResponseBody
	public String getPartnerOccupationAssociation(ModelMap model, 
			 HttpServletRequest request) {
		
		HttpSession httpSession = request.getSession();
		UserModel userModel = (UserModel) httpSession.getAttribute("ActUserId");
		String json = null;
		
		
		
		try {

			List<PartnerOccupationAssociation> partnerAssociation = userService
					.getPartnerOccuptionAssociationByUserModel(userModel);
			
		
			JSONObject mainObj = new JSONObject();
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			json = ow.writeValueAsString(partnerAssociation);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return json;
	}
	
	
	@RequestMapping(value = "/imageSlider", method = RequestMethod.GET)
	public ModelAndView imageSlider( @RequestParam("userId") String userId,ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		try {
			HttpSession httpSession = request.getSession();
			UserModel actUser = (UserModel) httpSession.getAttribute("ActUserId");
			com.xanadutec.coreflex.model.Kit kitObj = actUser.getKit();
			String kitName = "FreeKit";
			if (kitObj != null && kitObj.getPackage_id() != null) {
				kitName = kitObj.getPackage_id().getPackage_Name();
			}
// Removed upgrade check to allow viewing gallery for all users

			UserModel userModel =new UserModel();
			userModel.setUserId(Long.parseLong(userId));

			try {
				UserModelImagesName imagesName	=userService.getImageNameAccordingToUserModel(userModel);
				
				String imString =imagesName.getImageNames();
				String imgs []  =imString.split("#");
				model.addAttribute("imageObject", imgs);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("ImageSlider");

	}
	
	
	@RequestMapping(value = "/kundaliSlider", method = RequestMethod.GET)
	public ModelAndView kundaliSlider( @RequestParam("userId") String userId,ModelMap model, HttpServletRequest request) {
		try {
			UserModel userModel =new UserModel();
			userModel.setUserId(Long.parseLong(userId));

			try {
				UserModelImagesName imagesName	=userService.getImageNameAccordingToUserModel(userModel);
				
				String imString = imagesName.getKundaliImage();
				if (imString != null) {
					String s[] = imString.split("#");
					java.util.List<String> lst = new java.util.LinkedList<String>();
					for(String img : s) {
						if(img != null && !img.trim().isEmpty()) {
							lst.add(img);
						}
					}
					model.addAttribute("imageObject", lst);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("kundaliSlider");

	}
	
	@RequestMapping(value = "/cropView", method = RequestMethod.GET)
	public ModelAndView cropView(@RequestParam("image") String image,ModelMap model, final HttpServletRequest request) {
	
		model.addAttribute("image", image);
		return new ModelAndView("cropUserImage");
	}
	
	@RequestMapping(value = "/cropImages", method = RequestMethod.POST)
	public ModelAndView cropImages(@RequestParam("image") String image,HttpServletRequest request,HttpServletResponse response, Model model) throws IOException,Exception {
		int maxNumber=0;
		final String pathact = request.getServletContext().getRealPath("/resources/UserImages");
		
		File dir1 = new File(pathact);
		String fileName=null;
		if (!dir1.exists()){
			dir1.mkdirs();
		}
		HttpSession httpSession = request.getSession();
		UserModel userModel = (UserModel) httpSession.getAttribute("ActUserId");

		UserModelImagesName imagesNames = userService.getImageNameAccordingToUserModel(userModel);
		
		try{
			String splitHash[]=imagesNames.getImageNames().split("#");
			ArrayList< Integer >arrayList =new ArrayList<>();
			for (String string : splitHash) {
				String splitUnderScore[] = string.split("_");
				int stringflag = 0;
				for (String iterate : splitUnderScore) {
					if (stringflag == 0) {
						try{
							arrayList.add(Integer.parseInt(iterate.split("@")[1]));	
						}catch(Exception e){
						}
						stringflag = 1;
					} else {
						stringflag = 0;
					}
				}
			}
			maxNumber=Collections.max(arrayList);
		}catch(Exception e){
			//e.printStackTrace();
		}
		String ActualFileName = imagesNames.getImageNames();
		
			try {
				maxNumber++;
				File directory = new File(dir1.getAbsolutePath());
				directory.mkdirs();
				String extension = FilenameUtils.getExtension(image);
				
				String name = "@" + maxNumber + "_" + userModel.getUserId() + "." + extension;
				ActualFileName = ActualFileName + name + "#";
				
				 int cropX=0,cropY=0,cropW=0,cropH=0;
				 cropX= Integer.parseInt(request.getParameter("cropX"));
			     cropY= Integer.parseInt(request.getParameter("cropY"));
			     cropW= Integer.parseInt(request.getParameter("cropW"));
			     cropH= Integer.parseInt(request.getParameter("cropH"));
			     
				 File file =new File(pathact+File.separator+image);
				 BufferedImage outImage=ImageIO.read(file);
				 int type = outImage.getType() == 0? BufferedImage.TYPE_INT_ARGB : outImage.getType();
				 BufferedImage cropImage=outImage.getSubimage(cropX, cropY,cropW, cropH );
				 
				 String outputPath=name;
				 
				 File cropfile = new File(pathact+File.separator+outputPath);
				 
				 ImageIO.write(cropImage, extension,cropfile);
				
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("Error while loading the file"+e.getMessage());
			}
		imagesNames.setImageNames(ActualFileName);
		userService.saveUserImageNames(imagesNames);
		
		
		try{
			String imgnames[]=imagesNames.getImageNames().split("#");
			ArrayList<String> imglist=new ArrayList<String>(Arrays.asList(imgnames));
			boolean flag=imglist.remove(image);
			if(flag){

				
				try{
					 File afile =new File(pathact+"/"+image);
					    //delete the original file

					 afile.delete();
					 String name="";
					 for (String string : imglist) {
						name=name+string+"#";
						
					}
					 imagesNames.setImageNames(name);
					 userService.saveUserImageNames(imagesNames);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		 imagesNames = userService.getImageNameAccordingToUserModel(userModel);
		try{
			String imgNames[]=imagesNames.getImageNames().split("#");
			model.addAttribute("profileImages", imgNames);
		}catch(Exception e){
			
		}
		return new ModelAndView("EditImages");
			
			
		}

	@RequestMapping(value = "/removeImages", method = RequestMethod.GET)
	@ResponseBody
	public String removeImages(@RequestParam("image") String image,ModelMap model, final HttpServletRequest request) {

		
		HttpSession httpSession = request.getSession();
		UserModel userModel = (UserModel) httpSession.getAttribute("ActUserId");

		UserModelImagesName imagesNames = userService.getImageNameAccordingToUserModel(userModel);
		
		try{
			String imgnames[]=imagesNames.getImageNames().split("#");
			ArrayList<String> imglist=new ArrayList<String>(Arrays.asList(imgnames));
			boolean flag=imglist.remove(image);
			if(flag){

				
				try{
					final String pathact = request.getServletContext().getRealPath("/resources/UserImages");
					 File afile =new File(pathact+"/"+image);
					    //delete the original file

					 afile.delete();
					 String name="";
					 for (String string : imglist) {
						name=name+string+"#";
						
					}
					 imagesNames.setImageNames(name);
					 userService.saveUserImageNames(imagesNames);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
			
		  		return "";
	}
	
	
	
	@RequestMapping(value = "/removeImagesByAdmin", method = RequestMethod.GET)
	@ResponseBody
	public String removeImagesByAdmin(@RequestParam("image") String image,@RequestParam("userId") String userId,ModelMap model, final HttpServletRequest request) {

		
		
		UserModel userModel = new UserModel();
		userModel.setUserId(Long.parseLong(userId));
		UserModelImagesName imagesNames = userService.getImageNameAccordingToUserModel(userModel);
		
		try{
			String imgnames[]=imagesNames.getImageNames().split("#");
			ArrayList<String> imglist=new ArrayList<String>(Arrays.asList(imgnames));
			boolean flag=imglist.remove(image);
			if(flag){

				
				try{
					final String pathact = request.getServletContext().getRealPath("/resources/UserImages");
					 File afile =new File(pathact+"/"+image);
					    //delete the original file

					 afile.delete();
					 String name="";
					 for (String string : imglist) {
						name=name+string+"#";
						
					}
					 imagesNames.setImageNames(name);
					 userService.saveUserImageNames(imagesNames);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return "";
	}
	
	
	
	@RequestMapping(value = "/saveImages", method = RequestMethod.POST)
	public ModelAndView saveImages(MultipartHttpServletRequest request,HttpServletResponse response, Model model) throws IOException,Exception {
		int maxNumber=0;
		final String pathact = request.getServletContext().getRealPath("/resources/UserImages");
		
		File dir1 = new File(pathact);
		String fileName=null;
		if (!dir1.exists()){
			dir1.mkdirs();
		}
		HttpSession httpSession = request.getSession();
		UserModel userModel = (UserModel) httpSession.getAttribute("ActUserId");

		UserModelImagesName imagesNames = userService.getImageNameAccordingToUserModel(userModel);
		
		try{
			String splitHash[]=imagesNames.getImageNames().split("#");
			ArrayList< Integer >arrayList =new ArrayList<>();
			for (String string : splitHash) {
				String splitUnderScore[] = string.split("_");
				int stringflag = 0;
				for (String iterate : splitUnderScore) {
					if (stringflag == 0) {
						try{
							arrayList.add(Integer.parseInt(iterate.split("@")[1]));	
						}catch(Exception e){
						}
						stringflag = 1;
					} else {
						stringflag = 0;
					}
				}
			}
			maxNumber=Collections.max(arrayList);
		}catch(Exception e){
		}
		Iterator<String> itrator = request.getFileNames();
		String ActualFileName = imagesNames.getImageNames();
		
		while (itrator.hasNext()) {
			MultipartFile multiFile = request.getFile(itrator.next());
			try {
				maxNumber++;
				 fileName =multiFile.getOriginalFilename();
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
				throw new Exception("Error while loading the save file"+e.getMessage());
			}
		}
		imagesNames.setImageNames(ActualFileName);
		userService.saveUserImageNames(imagesNames);
		
		 imagesNames = userService.getImageNameAccordingToUserModel(userModel);

		try{
			String imgNames[]=imagesNames.getImageNames().split("#");
			model.addAttribute("profileImages", imgNames);
		}catch(Exception e){
			
		}
		
		return new ModelAndView("EditImages");
			
			
		}
	
	
	
	@RequestMapping(value = "/saveImagesByAdmin", method = RequestMethod.POST)
	public ModelAndView saveImagesByAdmin(MultipartHttpServletRequest request,HttpServletResponse response, Model model) throws IOException,Exception {
		int maxNumber=0;
		final String pathact = request.getServletContext().getRealPath("/resources/UserImages");
		
		File dir1 = new File(pathact);
		String fileName=null;
		if (!dir1.exists()){
			dir1.mkdirs();
		}
		
		
		
		JSONObject obj;
		String userId= null;
		try {
			
			obj = new JSONObject(request.getParameter("jsonObjectData"));
			final JSONObject feedcomObj = obj;
			userId = feedcomObj.getString("userid");
			
		
	} catch (Exception e) 
		{
		e.printStackTrace();
	}
		
		
		UserModel userModel = new UserModel();
			userModel.setUserId(Long.parseLong(userId));
		UserModelImagesName imagesNames = userService.getImageNameAccordingToUserModel(userModel);
		
		try{
			String splitHash[]=imagesNames.getImageNames().split("#");
			ArrayList< Integer >arrayList =new ArrayList<>();
			for (String string : splitHash) {
				String splitUnderScore[] = string.split("_");
				int stringflag = 0;
				for (String iterate : splitUnderScore) {
					if (stringflag == 0) {
						try{
							arrayList.add(Integer.parseInt(iterate.split("@")[1]));	
						}catch(Exception e){
						}
						stringflag = 1;
					} else {
						stringflag = 0;
					}
				}
			}
			maxNumber=Collections.max(arrayList);
		}catch(Exception e){
		}
		Iterator<String> itrator = request.getFileNames();
		String ActualFileName = imagesNames.getImageNames();
		
		while (itrator.hasNext()) {
			MultipartFile multiFile = request.getFile(itrator.next());
			try {
				maxNumber++;
				 fileName =multiFile.getOriginalFilename();
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
				
		
				UserModel loginUser = userService.getUserById(Integer.parseInt(userId));
				
				
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
					
				if(loginUser.getKit() == null)
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
				throw new Exception("Error while loading the file"+e.getMessage());
			}
		}
		imagesNames.setImageNames(ActualFileName);
		userService.saveUserImageNames(imagesNames);
		
		 imagesNames = userService.getImageNameAccordingToUserModel(userModel);

		try{
			String imgNames[]=imagesNames.getImageNames().split("#");
			model.addAttribute("profileImages", imgNames); 
			
			if (imagesNames.getKundaliImage() != null && !imagesNames.getKundaliImage().isEmpty()) {
			    String kundaliImgNames[]=imagesNames.getKundaliImage().split("#");
			    model.addAttribute("kundaliImages", kundaliImgNames);
			}
			
			model.addAttribute("userId", userId);
		}catch(Exception e){
			
		}
		
		return new ModelAndView("editImagesByAdmin");
			
			
		}

	@RequestMapping(value = "/removeKundaliImagesByAdmin", method = RequestMethod.GET)
	@ResponseBody
	public String removeKundaliImagesByAdmin(@RequestParam("image") String image,@RequestParam("userId") String userId,ModelMap model, final HttpServletRequest request) {
		UserModel userModel = new UserModel();
		userModel.setUserId(Long.parseLong(userId));
		UserModelImagesName imagesNames = userService.getImageNameAccordingToUserModel(userModel);
		
		try{
		    String kundaliImages = imagesNames.getKundaliImage();
			if (kundaliImages != null) {
                String imgnames[] = kundaliImages.split("#");
                ArrayList<String> imglist=new ArrayList<String>(Arrays.asList(imgnames));
                boolean flag = imglist.remove(image);
                if(flag){
                    try{
                        final String pathact = request.getServletContext().getRealPath("/resources/UserImages");
                        File afile =new File(pathact+"/"+image);
                        afile.delete();
                        String name="";
                        for (String string : imglist) {
                            if (!string.isEmpty()) {
                                name = name + string + "#";
                            }
                        }
                        imagesNames.setKundaliImage(name);
                        userService.saveUserImageNames(imagesNames);
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return "";
	}

	@RequestMapping(value = "/saveKundaliImagesByAdmin", method = RequestMethod.POST)
	public ModelAndView saveKundaliImagesByAdmin(MultipartHttpServletRequest request,HttpServletResponse response, Model model) throws IOException,Exception {
		final String pathact = request.getServletContext().getRealPath("/resources/UserImages");
		
		File dir1 = new File(pathact);
		String fileName=null;
		if (!dir1.exists()){
			dir1.mkdirs();
		}
		
		JSONObject obj;
		String userId= null;
		try {
			obj = new JSONObject(request.getParameter("jsonObjectData"));
			userId = obj.getString("userid");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		UserModel userModel = new UserModel();
		userModel.setUserId(Long.parseLong(userId));
		UserModelImagesName imagesNames = userService.getImageNameAccordingToUserModel(userModel);
		
		Iterator<String> itrator = request.getFileNames();
		String ActualFileName = imagesNames.getKundaliImage();
        if (ActualFileName == null) {
            ActualFileName = "";
        }
		
		while (itrator.hasNext()) {
			MultipartFile multiFile = request.getFile(itrator.next());
			try {
				fileName = multiFile.getOriginalFilename();
				byte[] bytes = multiFile.getBytes();
				File directory = new File(dir1.getAbsolutePath());
				directory.mkdirs();
				String extension = FilenameUtils.getExtension(fileName);
				String name = "@K" + userModel.getUserId() + "." + extension;
				ActualFileName = ActualFileName + name + "#";
				
				File file = new File(dir1.getAbsolutePath() + File.separator + name);
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(file));
				stream.write(bytes);
				stream.close();
				
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("Error while loading the file"+e.getMessage());
			}
		}
		imagesNames.setKundaliImage(ActualFileName);
		userService.saveUserImageNames(imagesNames);
		
		 imagesNames = userService.getImageNameAccordingToUserModel(userModel);
		try{
			if (imagesNames.getImageNames() != null && !imagesNames.getImageNames().isEmpty()) {
				String imgNames[]=imagesNames.getImageNames().split("#");
				model.addAttribute("profileImages", imgNames); 
			}
			
			if (imagesNames.getKundaliImage() != null && !imagesNames.getKundaliImage().isEmpty()) {
			    String kundaliImgNames[]=imagesNames.getKundaliImage().split("#");
			    model.addAttribute("kundaliImages", kundaliImgNames);
			}
			
			model.addAttribute("userId", userId);
		}catch(Exception e){
			
		}
		
		return new ModelAndView("editImagesByAdmin");
	}
	
	@RequestMapping(value = "/updateUserModeldetails", method = RequestMethod.POST)
	@ResponseBody
	public String updateuserModeldetails( ModelMap model, final HttpServletRequest request) {
		System.out.println("-----------------------User profile Update Start---------------------");
		JSONObject obj;
		try {
			obj = new JSONObject(request.getParameter("jsonObjectData"));
			final JSONObject jsonObj = obj;
			String userModelDetailId = jsonObj.getString("userModelDetailId");
			String userModelId = jsonObj.getString("userModelId");
			
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
			String aboutMe = jsonObj.getString("aboutMe");
			String materialStatus = jsonObj.getString("materialStatus");
			String physicalStatus = jsonObj.getString("physicalStatus");
			String firstName = jsonObj.getString("firstName");
			String gender = jsonObj.getString("gender");
			String mobile = jsonObj.getString("mobile");
			String dob = jsonObj.getString("dob");
			String email = jsonObj.getString("email");
			String employedIn = jsonObj.getString("employedIn");
			String ProfileCreatedFor=jsonObj.getString("ProfileCreatedFor");
			UserModel userModel=new UserModel();
			userModel.setUserId(Long.parseLong(userModelId));
			
			UserModelDetails userModelDetails = new UserModelDetails();
			try {
				userModelDetails.setUserDetailsId(Integer.parseInt(userModelDetailId));
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
				
				if(cityStateSelect.equalsIgnoreCase("0") || cityStateSelect=="0")
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
				if(subDistSelect.equalsIgnoreCase("0") || subDistSelect=="0")
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
				
				if(religionSelect.equalsIgnoreCase("0") || religionSelect=="0")
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
				if(casteSelect.equalsIgnoreCase("0") ||casteSelect=="0")
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
				if (email == null || email.trim().isEmpty() || email.equalsIgnoreCase("abc@gmail.com") || email.startsWith("abc")) {
					email = "abc" + new Date().getTime() + "@gmail.com";
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
				userService.updateUserFullName(userModelId, firstName);
			} catch (Exception e) {
				e.printStackTrace();
			}
		
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		System.out.println("User profile updated successfully");
		return "success";
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		/*
		
		JSONObject obj;
	try {
		
		obj = new JSONObject(request.getParameter("jsonObjectData"));
		final JSONObject jsonObj = obj;
		String userModelDetailId = jsonObj.getString("userModelDetailId");
		String userModelId = jsonObj.getString("userModelId");
		
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
		String aboutMe = jsonObj.getString("aboutMe");
		String materialStatus = jsonObj.getString("materialStatus");
		String physicalStatus = jsonObj.getString("physicalStatus");
		String firstName = jsonObj.getString("firstName");
		String gender = jsonObj.getString("gender");
		String mobile = jsonObj.getString("mobile");
		String dob = jsonObj.getString("dob");
		String email = jsonObj.getString("email");
		String employedIn = jsonObj.getString("employedIn");
		String ProfileCreatedFor=jsonObj.getString("ProfileCreatedFor");
		String userLandMark = null;
		try {
		 userLandMark=jsonObj.getString("userLandMark");
		} catch (Exception e) {
			System.out.println("------------------------Landmark is nulll------------------------");
			e.printStackTrace();
			
		}
		UserModel userModel=new UserModel();
		userModel.setUserId(Long.parseLong(userModelId));

		logger.debug("-----------Josn Read Data-----------------");
		logger.info("-----------Josn Read Data-----------------");
		UserModelDetails userModelDetails = new UserModelDetails();
		try {
			userModelDetails.setUserDetailsId(Integer.parseInt(userModelDetailId));
			userModelDetails.setUserModel(userModel);
			logger.info("set User ModelDetails id"+userModelDetailId);
			logger.debug("set User ModelDetails id"+userModelDetailId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			userModelDetails.setHeight(Double.parseDouble(heightSelect));
			logger.info("set UserheightSelect"+heightSelect);
			logger.debug("set heightSelect"+heightSelect);
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			userModelDetails.setLandmark(userLandMark);
			logger.info("set userLandMark"+userLandMark);
			logger.debug("set userLandMark"+userLandMark);
			System.out.println("save land mark "+userLandMark);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			userModelDetails.setWeight(Integer.parseInt(weightSelect));
			logger.info("set User weightSelect"+weightSelect);
			logger.debug("set User weightSelect"+weightSelect);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			userModelDetails.setAboutMe(aboutMe);
			logger.info("set UseraboutMe"+aboutMe);
			logger.debug("set User aboutMe"+aboutMe);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			userModelDetails.setMaterialStatus(materialStatus);
			logger.info("set UseraboutMe"+materialStatus);
			logger.debug("set User aboutMe"+materialStatus);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			userModelDetails.setPhysicalStatus(physicalStatus);
			logger.info("set UseraboutMe"+physicalStatus);
			logger.debug("set User aboutMe"+physicalStatus);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Country country = new Country();
		try {
			country.setCountry_id(Integer.parseInt(countrySelect));
			userModelDetails.setCountry(country);
			logger.info("set countrySelect"+countrySelect+"country="+country);
			logger.debug("set User countrySelect"+countrySelect+"country="+country);
			
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
			logger.info("set state_Country"+state_Country+"stateSelect="+stateSelect);
			logger.debug("set User aboutMe"+countrySelect+"country="+country);
		} catch (Exception e) {
			e.printStackTrace();

		}
		State_Districts state_Districts = null;
		try {
			state_Districts = new State_Districts();
			
			if(cityStateSelect.equalsIgnoreCase("0") || cityStateSelect=="0")
			{
				cityStateSelect="1";
			}
			state_Districts.setState_district_id(Integer.parseInt(cityStateSelect));
			logger.info("set cityStateSelect="+cityStateSelect);
			logger.debug("set User cityStateSelect="+cityStateSelect);
		} catch (Exception e) {
			e.printStackTrace();
		}
		District_SubDistricts district_SubDistricts = null;
		try {

			district_SubDistricts = new District_SubDistricts();
			if(subDistSelect.equalsIgnoreCase("0") || subDistSelect=="0")
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
			
			if(religionSelect.equalsIgnoreCase("0") || religionSelect=="0")
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
			if(casteSelect.equalsIgnoreCase("0") ||casteSelect=="0")
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
			logger.info("set educationalSelect="+educationalSelect);
			logger.debug("set User educationalSelect="+educationalSelect);
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
		logger.info("User Model Start");
		logger.debug("User Model Start");
		try {
			userModelDetails.setCountry(country);
			logger.info("User country"+country);
			logger.debug("User"+country);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			userModelDetails.setState_Country(state_Country);
			logger.info("User state_Country"+state_Country);
			logger.debug("User state_Country"+state_Country);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			userModelDetails.setDistricts(state_Districts);
			logger.info("User state_Districts"+state_Districts);
			logger.debug("User state_Districts"+state_Districts);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			userModelDetails.setSubDistricts(district_SubDistricts);
			logger.info("User district_SubDistricts"+district_SubDistricts);
			logger.debug("User district_SubDistricts"+district_SubDistricts);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			userModelDetails.setCaste(caste);
			logger.info("User caste"+caste);
			logger.debug("User caste"+caste);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			userModelDetails.setEducation(education);
			logger.info("User education"+education);
			logger.debug("User"+education);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			userModelDetails.setOccupation(occupation);
			logger.info("User occupation"+occupation);
			logger.debug("User occupation"+occupation);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			userModelDetails.setBodyType(bodyType);
			logger.info("User bodyType"+bodyType);
			logger.debug("User bodyType"+bodyType);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			userModelDetails.setComplexion(complexion);
			logger.info("User complexion"+complexion);
			logger.debug("User complexion"+complexion);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			userModelDetails.setIncomeType(income);
			logger.info("User income"+income);
			logger.debug("User income"+income);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			userModelDetails.setIncomeAmount(Long.parseLong(incomeAmount));
			logger.info("User incomeAmount"+incomeAmount);
			logger.debug("User incomeAmount"+incomeAmount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			userModelDetails.setFamilyClass(familyClassInfo);
			logger.info("User familyClassInfo"+familyClassInfo);
			logger.debug("User familyClassInfo"+familyClassInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			userModelDetails.setFamilyType(familyTypeInfo);
			logger.info("User familyTypeInfo"+familyTypeInfo);
			logger.debug("User familyTypeInfo"+familyTypeInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			userModelDetails.setFamilyValues(familyValueInfo);
			logger.info("User familyValueInfo"+familyValueInfo);
			logger.debug("User familyValueInfo"+familyValueInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			userModelDetails.setEating(eating);
			logger.info("User eating"+eating);
			logger.debug("User eating"+eating);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			userModelDetails.setSmoke(smoke);
			logger.info("User smoke"+smoke);
			logger.debug("User smoke"+smoke);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			userModelDetails.setDrink(drink);
			logger.info("User drink"+drink);
			logger.debug("User drink"+drink);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// dateOfBirth email mobileNum profileCreatedFor lastLoginDate
		// gender profileUpdateDate registrationDate
		
		try {
			if (email == null || email.trim().isEmpty() || email.equalsIgnoreCase("abc@gmail.com") || email.startsWith("abc")) {
				email = "abc" + new Date().getTime() + "@gmail.com";
			}
			userModelDetails.setEmail(email);
			logger.info("User email"+email);
			logger.debug("User email"+email);
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			userModelDetails.setProfileCreatedFor(ProfileCreatedFor);
			logger.info("User ProfileCreatedFor"+ProfileCreatedFor);
			logger.debug("User ProfileCreatedFor"+ProfileCreatedFor);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			userModelDetails.setMobileNum(mobile);
			logger.info("User mobile"+mobile);
			logger.debug("User mobile"+mobile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			userModelDetails.setGender(gender);
			logger.info("User gender"+gender);
			logger.debug("User gender"+gender);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		logger.info("****************Befor Lost login date************************");
		logger.debug("****************Befor Lost login date************************");
		try {
			userModelDetails.setLastLoginDate(new Date());
			logger.info("*********11111*******new Date()************************"+new Date());
			logger.debug("*********11111111*******new Date()************************"+new Date());
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			userModelDetails.setRegistrationDate(new Date());
			logger.info("*********2222222*******new Date()************************"+new Date());
			logger.debug("******222222222**********new Date()************************"+new Date());
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
			logger.info("***********33333333333*****new Date()************************"+new Date());
			logger.debug("*********3333333333*******new Date()************************"+new Date());
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("****************END DATE************************");
		logger.debug("****************END Date************************");
		try {
			userModelDetails.setEmployeedIn(employedIn);
			logger.info("****************EemployedIn**********************"+employedIn);
			logger.debug("****************employedIn**********************"+employedIn);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			userModelDetails = userService.addUserdetails(userModelDetails);
			userService.updateUserFullName(userModelId, firstName);
			logger.info("****************UpdateSuccess**********************"+employedIn);
			logger.debug("****************update Success**********************"+employedIn);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		
	} catch (Exception e) {
		
		e.printStackTrace();
		
		logger.debug("ERROR:"+e);
		logger.info("ERROR:"+e);
		
	}
	logger.info("-----------User Updated Successfully------------");
	logger.debug("-----------User Updated Successfully------------");
	return "success";*/
		
	}
	
	
	@RequestMapping(value = "/updateUserPartnerPreference", method = RequestMethod.POST)
	@ResponseBody
	public String updateuserPartner(ModelMap model, final HttpServletRequest request) {
		JSONObject obj;
		
		try {
			
			obj = new JSONObject(request.getParameter("jsonObjectData"));
			final JSONObject jsonObj = obj;
			
			
			String userModelId = jsonObj.getString("userModelId");  
			String partnerPrefId = jsonObj.getString("hiddenUserpartnerId");
			
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
			String employedInPartner=jsonObj.getString("employedInPartner");
			
			
			
			UserModel userModel=new UserModel();
			userModel.setUserId(Long.parseLong(userModelId));
			
			
			
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
			userModelPartnerPreferances.setUserDetailsId(Integer.parseInt(partnerPrefId));
			
			
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
					
			StringBuffer builder = new StringBuffer(checkBoxEducationPartner);
			builder.deleteCharAt(0);
			builder.deleteCharAt(builder.toString().length() - 1);

			try {
				userService.deletePartnerEducationAssociationByUserModel(userModel);
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
				
				userService.deletePartnerOccuptionAssociationByUserModel(userModel);
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
			
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return "";
		
	}
	
	
	@RequestMapping(value = "/upgradedUser", method = RequestMethod.GET)
	public ModelAndView registeredUser(ModelMap model, HttpServletRequest request) {
		

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
		
		return new ModelAndView("upgradedUserHome"); 
}
	
	@RequestMapping(value = "/getUpgradedUserByDate", method = RequestMethod.GET)
	public ModelAndView getUpgradedUserByDate(@RequestParam("fdate") String fdate,@RequestParam("tdate") String tdate,
			ModelMap model, HttpServletRequest request) {

     
		try {
			String[] fromSplit = fdate.split("/");
			String[] toSplit = tdate.split("/");

			String fromSplitDate = fromSplit[2] + "-" + fromSplit[0] + "-" + fromSplit[1];
			String toSplitDate = toSplit[2] + "-" + toSplit[0] + "-" + toSplit[1];

			Date actFromDate = null;
			Date actToDate = null;

			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				actFromDate = df.parse(fromSplitDate + " 00:00:00");
				actToDate = df.parse(toSplitDate + " 23:59:59");

			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			List<AdminUpgradedUser> list=revService.getListOfUpgradedUserByDate(actFromDate, actToDate);
			ObjectMapper mapper = new ObjectMapper();
	        model.addAttribute("upgradedUserList", mapper.writeValueAsString(list));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new ModelAndView("upgradedUser");

	}

	
	@RequestMapping(value = "/deleteProfileByAdmin", method = RequestMethod.POST)
	@ResponseBody
	public String deleteProfileByAdmin(@RequestParam("userId") String userId,ModelMap model, final HttpServletRequest request) {
		try {	
			Status status=new Status();
			status.setStatus_id(2);
			  UserModel usermodel=userService.getUserById(Integer.parseInt(userId));
			  UserModelDetails userModelDetails=userService.getUserdetails(usermodel);
			  String pass=usermodel.getPassword()+new Date().getTime();
			  String uname=usermodel.getUserName()+new Date().getTime();
			  usermodel.setUserName(uname);
			  usermodel.setPassword(pass);
			  userService.updateUser(usermodel);
			  userModelDetails.setStatus(status);
			  userService.updateUserModelDetails(userModelDetails);
			  	
			  	
			  	//Now Save the details of delete user for admin use
			  	HttpSession session=request.getSession();
			  	//ActUserId
			  	UserModel userDeletBy=(UserModel) session.getAttribute("ActUserId");
			  	userService.saveDeleteUserDetail(usermodel, userDeletBy);
			  	
			  	
			  	
			  return "success";
			
		}catch(Exception e){
			
			e.printStackTrace();
		}
			
		return "";
	}
	
	
}
