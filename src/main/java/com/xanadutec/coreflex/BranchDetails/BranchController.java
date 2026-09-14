package com.xanadutec.coreflex.BranchDetails;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

import com.xanadutec.coreflex.model.BranchModel;


@Controller
public class BranchController {
	
	@Autowired
	BranchService branchService;

	@RequestMapping(value = "/saveBranchDetails", method = RequestMethod.POST)
	@ResponseBody
	public String saveBranchDetails(ModelMap model, HttpServletRequest request) 
	{

		try {

			String branchName = request.getParameter("branch");
			String branchAdd = request.getParameter("branchAdd");
			String lat = request.getParameter("lat");
			String 	longitude = request.getParameter("longitude");
			String contactNo = request.getParameter("contactNo");
			String emailId = request.getParameter("emailId");



			BranchModel branchModel = new BranchModel();
			branchModel.setBranchName(branchName);
			branchModel.setBranchAddress(branchAdd);
			branchModel.setContactNo(contactNo);
			branchModel.setEmailId(emailId);
			try {
				
				branchModel.setLatitude(Double.parseDouble(lat));
			} catch (Exception e)
			{
				e.printStackTrace();
			}
			try {
				
				branchModel.setLongitude(Double.parseDouble(longitude));
			} catch (Exception e) 
			{
				e.printStackTrace();
			}

			branchService.saveBranch(branchModel);
			
			String branchId = ""+branchModel.getBranchId();
			
			return toJson(branchId);

		} catch (Exception e) 
		{
			e.printStackTrace();
		}

		return "success";
	}
	
	@RequestMapping(value = "/loadGoogleMap", method = RequestMethod.POST)
	@ResponseBody
	public String loadGoogleMap(ModelMap model, HttpServletRequest request, @RequestParam("branch_List") String branchId) 
	{

		try {

			JSONObject js = new JSONObject();
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			List<BranchModel>  branchModelList = branchService.getBranchDetailsByBranchId(branchId);
			js.put("branchModelList", ow.writeValueAsString(branchModelList));
			String json = js.toString();
			return json;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
		
	}  
	
	@RequestMapping(value = "/loadAllBranches", method = RequestMethod.POST)
	@ResponseBody
	public String loadAllBranches(ModelMap model, HttpServletRequest request) 
	{

		try {

			JSONObject js = new JSONObject();
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			//List<BranchModel>  branchModelList = branchService.getBranchDetailsByBranchId(branchId);
			
			List<BranchModel> branchModelList = branchService.getBranchList();
			js.put("branchModelList", ow.writeValueAsString(branchModelList));
			String json = js.toString();
			return json;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
		
	}  
	
	@RequestMapping(value = "/loadDefaultGoogleMap", method = RequestMethod.POST)
	@ResponseBody
	public String loadDefaultGoogleMap(ModelMap model, HttpServletRequest request) 
	{

		try {
			List<BranchModel> allLatLongList = branchService.getBranchList();
			
			
		//	model.addAttribute("allLatLongList", allLatLongList);
			JSONObject js = new JSONObject();
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			js.put("allLatLongList", ow.writeValueAsString(allLatLongList));
			String json = js.toString();
			return json;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
		
	}
	
	@RequestMapping(value = "/deleteBranchDetails", method = RequestMethod.POST)
	public String deleteBranchDetailsByBranchId(HttpServletRequest request, HttpServletResponse response)
	{
		String branchId = request.getParameter("branchID");
		branchService.deleteBranchDetailsByBranchID(Integer.parseInt(branchId));
		return "success";
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
	
}
