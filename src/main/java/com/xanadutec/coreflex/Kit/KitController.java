package com.xanadutec.coreflex.Kit;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xanadutec.coreflex.model.Dealer;

@Controller
public class KitController {
	
	@Autowired
	KitService kitService;
	
	@RequestMapping(value = "/getReportByYear", method = RequestMethod.POST)
	public ModelAndView getReportByYear(@RequestParam("year") String year,ModelMap model, HttpServletRequest request) {

			
			HashMap<String, List<Object[]>> result=kitService.getReportByYear(year);
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
								finalResultOfOpenMonth.put(month, (Long) aRow[0] + l);
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}

			Set<Integer> commonMonthListopen = finalResultOfOpenMonth.keySet();
			LinkedHashMap<String, Long> hashMap = new LinkedHashMap<String,Long>();
			
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
			
         return new ModelAndView("userGraph");			
	}
	
	
	@RequestMapping(value = "/getReportByYearAndDealer", method = RequestMethod.POST)
	public ModelAndView getReportByYearAndDealer(@RequestParam("year") String year,ModelMap model, HttpServletRequest request) {
		
		HttpSession httpSession = request.getSession();
		
		Dealer dealer=(Dealer) httpSession.getAttribute("DealerUserModel");
		//  Count calculation start
		
			HashMap<String, List<Object[]>> result=kitService.getReportByYearAndDealer(year,dealer);
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
								finalResultOfOpenMonth.put(month, (Long) aRow[0] + l);
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}

			Set<Integer> commonMonthListopen = finalResultOfOpenMonth.keySet();
			LinkedHashMap<String, Long> hashMap = new LinkedHashMap<String,Long>();
			
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
			
            return new ModelAndView("userGraph");		
	}
	
	
	@RequestMapping(value = "/Deletekit", method = RequestMethod.POST)
	@ResponseBody
	public String saveCountry(@RequestParam("kituserName") String kituserName,
			@RequestParam("kitpassword") String kitpassword,
			ModelMap model, HttpServletRequest request) 
	{
		  try
		   {
			  
			  	kitService.deletKitByUnameAndPassword(kituserName, kitpassword);
			  	return "success";
			
		  	} catch (Exception e) 
		  {
			e.printStackTrace();
		  }
		  return "";
	} 
	
	@RequestMapping(value = "/updateKitByAdmin", method = RequestMethod.POST)
	@ResponseBody
	public String updateKit(@RequestParam("kituserName") String kituserName,
			@RequestParam("kitpassword") String kitpassword,@RequestParam("kitid") String kitid,
			@RequestParam("packageName") String packageName,ModelMap model, HttpServletRequest request) {
		
		
		  try {
			  
			  kitService.updatekitDetails(packageName, kituserName, kitpassword, kitid);
			  	return "success";
			
		  	} catch (Exception e)
		  {
            e.printStackTrace();			
		  }
		  return "";
	} 
	
	
	
	@RequestMapping(value = "/assignKitToDealer", method = RequestMethod.POST)
	@ResponseBody
	public String assignKitToDealer(@RequestParam("packageName") String packageName,@RequestParam("dealerName") String dealerName,
			@RequestParam("noOfKIt") String noOfKIt,
			ModelMap model, HttpServletRequest request) {
		com.xanadutec.coreflex.model.Package pack=new com.xanadutec.coreflex.model.Package();
		Dealer dealer=new Dealer();
		pack.setPackage_id(Integer.parseInt(packageName));
		dealer.setId(Integer.parseInt(dealerName));
		String result=kitService.assingeKitTODealer(pack, dealer, Integer.parseInt(noOfKIt));
		return result;

	} 
	
	
	@RequestMapping(value = "/returnKit", method = RequestMethod.POST)
	@ResponseBody
	public String returnKit(@RequestParam("packageName") String packageName,@RequestParam("dealerName") String dealerName,
			@RequestParam("noOfKIt") String noOfKIt,
			ModelMap model, HttpServletRequest request)
	{
		com.xanadutec.coreflex.model.Package pack=new com.xanadutec.coreflex.model.Package();
		Dealer dealer=new Dealer();
		pack.setPackage_id(Integer.parseInt(packageName));
		dealer.setId(Integer.parseInt(dealerName));
		String result=kitService.returnKitByDealerAndPackage(pack, dealer, Integer.parseInt(noOfKIt));
		return result;

	} 
}
