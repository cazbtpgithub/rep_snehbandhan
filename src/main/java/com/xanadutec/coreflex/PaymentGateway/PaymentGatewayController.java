package com.xanadutec.coreflex.PaymentGateway;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xanadutec.coreflex.Kit.KitService;
import com.xanadutec.coreflex.KitUpgradeAndPayment.KitUpgradeAndPaymentService;
import com.xanadutec.coreflex.model.Kit;
import com.xanadutec.coreflex.model.Kit_Upgrade_Or_Reg_Details;
import com.xanadutec.coreflex.model.Kit_Upgrade_Or_Reg_History_Details;
import com.xanadutec.coreflex.model.Package;
import com.xanadutec.coreflex.model.PaymentGatewayTransactionSummary;
import com.xanadutec.coreflex.model.Status;
import com.xanadutec.coreflex.model.UserModel;
import com.xanadutec.coreflex.model.UserModelDetails;
import com.xanadutec.coreflex.packageAndKit.PackageAndKitService;
import com.xanadutec.coreflex.userTask.UserService;

@Controller
public class PaymentGatewayController {
	@Autowired
	UserService userService;
	@Autowired
	PackageAndKitService packageAndKitService;
	@Autowired
	KitService kitService;
	@Autowired
	KitUpgradeAndPaymentService kitUpgradeAndPaymentService;
	@Autowired
	PaymentGatewayService paymentGatewayService;

	@RequestMapping(value = "/PaymentGatewayController", method = RequestMethod.POST)
	public ModelAndView loadCityAccordingToState(ModelMap model, HttpServletRequest request) {

		return new ModelAndView("payuform");
	}

	@RequestMapping(value = "/paymentGatewaySession", method = RequestMethod.POST)
	@ResponseBody
	public String paymentGatewaySession(ModelMap model, HttpServletRequest request) {
		JSONObject js = new JSONObject();
		JSONObject obj;
		String json = null;
		try {
			obj = new JSONObject(request.getParameter("jsonObjectData"));
			final JSONObject jsonObj = obj;
			String packId = jsonObj.getString("package_id");
			
			HttpSession httpSession = request.getSession();
			UserModel userModel1 = (UserModel) httpSession.getAttribute("ActUserId");
			final UserModelDetails modelDetails = userService.getUserdetails(userModel1);

			com.xanadutec.coreflex.model.Package packageObj = packageAndKitService
					.getPackageByPackageId(Integer.parseInt(packId));

			httpSession.setAttribute("GatewaypackageAmount", packageObj.getAmount());
			httpSession.setAttribute("GatewaypackageName", packageObj.getPackage_Name());
			httpSession.setAttribute("GatewayEmail", modelDetails.getEmail());
			httpSession.setAttribute("GatewayMobile", modelDetails.getMobileNum());
			httpSession.setAttribute("GatewayFirstName", userModel1.getFirstName());
			httpSession.setAttribute("GatewayPackageId", packId);

			int flag=0;
			if (modelDetails.getEmail().length()<3) {
				flag=1;
			}
			if (modelDetails.getMobileNum()==null ||modelDetails.getMobileNum().isEmpty() || modelDetails.getMobileNum().length() < 10) {
				flag=1;
			}
			if (userModel1.getFirstName() == null || userModel1.getFirstName().isEmpty()) {
				flag=1;
			}
			
			if(flag==1){
				return "false";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			return  "true";
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;

	}

	@RequestMapping(value = "/PaymentGatewayResponse", method = RequestMethod.POST)
	public ModelAndView PaymentGatewayResponse(ModelMap model, HttpServletRequest request) {
		HttpSession httpSession = request.getSession();
		UserModel userModel = (UserModel) httpSession.getAttribute("ActUserId");
		final UserModelDetails modelDetails = userService.getUserdetails(userModel);
		String packageId = "";
		
		try {
			packageId = (String) httpSession.getAttribute("GatewayPackageId");
			httpSession.removeAttribute("GatewaypackageAmount");
			httpSession.removeAttribute("GatewaypackageName");
			httpSession.removeAttribute("GatewayEmail");
			httpSession.removeAttribute("GatewayMobile");
			httpSession.removeAttribute("GatewayFirstName");
			httpSession.removeAttribute("GatewayPackageId");
			
			String txn_id = request.getParameter("txnid");

			String amount = request.getParameter("amount");
			String productinfo = request.getParameter("productinfo");
			String txnid = request.getParameter("txnid");
			String phone = request.getParameter("phone");
			String firstname = request.getParameter("firstname");
			String status = request.getParameter("status");
		
		try {
			PaymentGatewayTransactionSummary txtObj=paymentGatewayService.getPaymentGatewayTransactionSummaryByTransactionId(txn_id);
			if(txtObj!=null)
			{
				String additionalCharges = request.getParameter("additionalCharges");
				List<Package> packages = packageAndKitService.getListOfPackage();
				model.addAttribute("packages", packages);
				httpSession.setAttribute("Amount", amount);
				httpSession.setAttribute("productinfo", productinfo);
				httpSession.setAttribute("txnid", txnid);
				httpSession.setAttribute("status", "Failure Transaction Id Is Duplicate");
				httpSession.setAttribute("additionalCharges", additionalCharges);
				return new ModelAndView("redirect:/KitUpgradeAndPayment");
			}
		} catch (Exception e) {

		}


		PaymentGatewayTransactionSummary gatewayTransactionSummary = new PaymentGatewayTransactionSummary();
		gatewayTransactionSummary.setPhone(phone);
		gatewayTransactionSummary.setStatus(status);
		gatewayTransactionSummary.setProductinfo(productinfo);
		gatewayTransactionSummary.setAmount(amount);
		gatewayTransactionSummary.setTxn_Id(txnid);
		gatewayTransactionSummary.setUserModel(userModel);
		gatewayTransactionSummary.setTxn_date(new Date());

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
					packageObj = packageAndKitService.getPackageByPackageName(productinfo);
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
					} catch (Exception e) 
					{
                      e.printStackTrace();
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

		model.addAttribute("packages", packages);

		httpSession.setAttribute("Amount", amount);
		httpSession.setAttribute("productinfo", productinfo);
		httpSession.setAttribute("txnid", txnid);
		httpSession.setAttribute("status", status);
		httpSession.setAttribute("additionalCharges", additionalCharges);


	
		
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		return new ModelAndView("redirect:/KitUpgradeAndPayment");
	}

	@RequestMapping(value = "/removeTransactionSummary", method = RequestMethod.POST)
	@ResponseBody
	public String removeTransactionSummary(ModelMap model, HttpServletRequest request) {
		try {

			HttpSession httpSession = request.getSession();
			httpSession.removeAttribute("Amount");
			httpSession.removeAttribute("productinfo");
			httpSession.removeAttribute("txnid");
			httpSession.removeAttribute("status");
			httpSession.removeAttribute("additionalCharges");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}
}
