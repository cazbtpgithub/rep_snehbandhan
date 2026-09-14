package com.xanadutec.coreflex.revenue;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.PUT;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xanadutec.coreflex.dealer.DealerService;
import com.xanadutec.coreflex.model.Chat;
import com.xanadutec.coreflex.model.Dealer;
import com.xanadutec.coreflex.model.Discount;
import com.xanadutec.coreflex.model.Kit;
import com.xanadutec.coreflex.model.Kit_Upgrade_Or_Reg_History_Details;
import com.xanadutec.coreflex.model.Package;
import com.xanadutec.coreflex.model.Religion;
import com.xanadutec.coreflex.model.Status;
import com.xanadutec.coreflex.model.UserModel;
import com.xanadutec.coreflex.packageAndKit.PackageAndKitController;
import com.xanadutec.coreflex.packageAndKit.PackageAndKitService;
import com.xanadutec.coreflex.userTask.UserService;

@Controller
public class RevenueController {
	@Autowired
	RevenueService revService;
	@Autowired
	PackageAndKitService packageandkitService;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private DealerService dealerService;
	
	@RequestMapping(value = "/revenueHome", method = RequestMethod.GET)
	public ModelAndView getReportByYear(ModelMap model,HttpServletRequest request) {
	
		List<Date>dateList=revService.getUniqueDateList();
		List<Package> packageList=packageandkitService.getListOfPackage();
		
		
		
		HashMap<Date, List<Kit_Upgrade_Or_Reg_History_Details>> revMap=new HashMap<>();
		for (Date date : dateList) {
			
			revMap.put(date, revService.getListOfRegHistoryByDate(date));
			
		}
		
		model.addAttribute("revMap", revMap);	
		model.addAttribute("packageList", packageList);
		
		HttpSession httpSession = request.getSession();
		UserModel userModel=(UserModel) httpSession.getAttribute("ActUserId");
		
		try {
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
		
		
		return new ModelAndView("RevenueHome");
	}     
	
	
	
	@RequestMapping(value = "/DealerRevenueHome", method = RequestMethod.GET)
	public ModelAndView DealerRevenueHome(ModelMap model) {
	
		List<Package> packageList=packageandkitService.getListOfPackage();
		model.addAttribute("packageList", packageList);
		return new ModelAndView("DealerRevenueHome");
	}     
	
	
	
	@RequestMapping(value = "/dealwiseRevenue", method = RequestMethod.GET)
	public ModelAndView dealwiseRevenue(ModelMap model,HttpServletRequest request) {
	
		List<Package> packageList=packageandkitService.getListOfPackage();
		model.addAttribute("packageList", packageList);
		
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
		return new ModelAndView("dealwiseRevenueHome");
	}   
	
	
	
	
	
	
	@RequestMapping(value = "/getRevenueByPackage", method = RequestMethod.GET)
	public ModelAndView getRevenueByPackage(@RequestParam("packageId") String packageId,ModelMap model, HttpServletRequest request) {

		
		List<Date>dateList=revService.getUniqueDateList();
		HashMap<Date, List<Kit_Upgrade_Or_Reg_History_Details>> revMap=new HashMap<>();
		Session session=sessionFactory.openSession();
		
		
		if(packageId.equalsIgnoreCase("All"))
		{
			for (Date date : dateList) {
				revMap.put(date, revService.getListOfRegHistoryByDate(date));
			}
			
		}else
		{
			
			Package pack =(Package) session.get(Package.class,Integer.parseInt(packageId)); 
			
			for (Date date : dateList) {
				
				revMap.put(date, revService.getListOfRegHistoryByDateAndPackage(date, pack));
				
			}
			
		}
		
		
		
		model.addAttribute("revMap", revMap);	
		
		session.close();
		return new ModelAndView("revenueByPackage");

	}
	
	
	
	
	
	
	
	@RequestMapping(value = "/getRevenueByPackageIdAndDealerId", method = RequestMethod.GET)
	public ModelAndView getRevenueByPackageIdAndDealerId(@RequestParam("packageId") String packageId,
			@RequestParam("dealerId") String dealerId,
			ModelMap model, HttpServletRequest request) {

		
		
		// change the criteria according to the dealer
		
		Dealer dealer=dealerService.getDealerById(dealerId);
		List<Date>dateList=revService.getUniqueDateList();
		HashMap<Date, List<Kit_Upgrade_Or_Reg_History_Details>> revMap=new HashMap<>();
		Session session=sessionFactory.openSession();
		
		if(packageId.equalsIgnoreCase("All"))
		{
			for (Date date : dateList) {
				revMap.put(date, revService.getListOfRegHistoryByDateAndDealer(date,dealer));
			}
			
		}else
		{

			Package pack =(Package) session.get(Package.class,Integer.parseInt(packageId)); 
			
			for (Date date : dateList) {
				
				revMap.put(date, revService.getListOfRegHistoryByDateAndPackageAndDealer(date, pack,dealer));
				
			}
			
		}
			
		model.addAttribute("revMap", revMap);	
		
		session.close();
		return new ModelAndView("revenueByPackage");

	}
	
	
	
	
	
	
	@RequestMapping(value = "/getDiscountByPackageIdAndDealerId", method = RequestMethod.GET)
	public ModelAndView getDiscountByPackageIdAndDealerId(@RequestParam("packageId") String packageId,
			@RequestParam("dealerId") String dealerId,
			ModelMap model, HttpServletRequest request) 
	{
		Dealer dealer=dealerService.getDealerById(dealerId);
		
		List<Package> packageList=packageandkitService.getListOfPackage();
		List<Discount> discountList=dealerService.getDealerWiseDiscount(packageList, dealer);
		model.addAttribute("discountList", discountList);
			
		
		return new ModelAndView("discountByPackageAndDealer");

	}
	
	
	
	@RequestMapping(value = "/getRevenueByPackageAndDealer", method = RequestMethod.GET)
	public ModelAndView getRevenueByPackageAndDealer(@RequestParam("packageId") String packageId,ModelMap model, HttpServletRequest request) {

		
		
		// change the criteria according to the dealer
		
		HttpSession httpSession=request.getSession();
	    Dealer dealer=(Dealer) httpSession.getAttribute("DealerUserModel");
		List<Date>dateList=revService.getUniqueDateList();
		HashMap<Date, List<Kit_Upgrade_Or_Reg_History_Details>> revMap=new HashMap<>();
		Session session=sessionFactory.openSession();
		
		
		if(packageId.equalsIgnoreCase("All"))
		{
			for (Date date : dateList) {
				revMap.put(date, revService.getListOfRegHistoryByDateAndDealer(date,dealer));
			}
			
		}else
		{

			Package pack =(Package) session.get(Package.class,Integer.parseInt(packageId)); 
			
			for (Date date : dateList) {
				
				revMap.put(date, revService.getListOfRegHistoryByDateAndPackageAndDealer(date, pack,dealer));
				
			}
			
		}
		
		model.addAttribute("revMap", revMap);	
		
		session.close();
		return new ModelAndView("revenueByPackage");

	}
	
	
	
	
}


