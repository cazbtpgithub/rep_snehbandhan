package com.xanadutec.coreflex.Report;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.xanadutec.coreflex.ReportModel.MemberReportModel;
import com.xanadutec.coreflex.model.Kit_Upgrade_Or_Reg_Details;
import com.xanadutec.coreflex.model.UserModel;
import com.xanadutec.coreflex.model.UserModelDetails;
import com.xanadutec.coreflex.userTask.UserService;


@Controller
public class MemberReportController 
{
	@Autowired
	MemberReportService memberReportService;
	@Autowired
	UserService userService;

	@RequestMapping( value ="/memberReport", method = RequestMethod.GET)
	public ModelAndView memberReport(ModelMap model, HttpServletRequest request)
	{
		
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
		
		return new ModelAndView("MemberReport");
	}

	@RequestMapping(value = "/getMemberDetailsByUserAndGender", method = RequestMethod.POST)
	@ResponseBody
	public String getMemberDetails(ModelMap  model,HttpServletRequest request, HttpServletResponse response ,
			@RequestParam("payStatus") String payStatus,@RequestParam("gender") String gender,
			@RequestParam("fromDate") String fromDate,@RequestParam("todate") String todate)
	{

		Date from_Date = null;
		Date toDate = null;
		String userEmailId = null;

		try {
			String[] fromSplit = fromDate.split("/");
			String[] toSplit = todate.split("/");

			String fromSplitDate = fromSplit[2] + "-" + fromSplit[0] + "-" + fromSplit[1];
			String toSplitDate = toSplit[2] + "-" + toSplit[0] + "-" + toSplit[1];

			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try 
			{
				from_Date = df.parse(fromSplitDate + " 00:00:00");
				toDate = df.parse(toSplitDate + " 23:59:59");

			} catch (ParseException e)
			{
				e.printStackTrace();
			}


		} catch (Exception e) 
		{
			e.printStackTrace();
		}

		List<MemberReportModel> complateMemberReportList=new ArrayList<>();

		try {


			if(gender.equalsIgnoreCase("male") && payStatus.equalsIgnoreCase("paidUser"))
			{
				
			
				List<MemberReportModel> memberReportList =memberReportService.getUserDetailsByPaidAndGender(from_Date,toDate);
                 
				int cnt = 1;
				for (MemberReportModel memberReportModel : memberReportList)
				{

					UserModel model2=new UserModel();
					model2.setUserId(memberReportModel.getUserId());
					UserModelDetails userModelDetails=userService.getUserdetails(model2);

					if(userModelDetails.getGender().equalsIgnoreCase("male"))
					{
						try {

							MemberReportModel mModel=new MemberReportModel();
							try {
								mModel.setSrNo(cnt);
							} catch (Exception e) 
							{
								e.printStackTrace();
							}

							try {
								mModel.setUserId(memberReportModel.getUserId());
							} catch (Exception e) 
							{
								e.printStackTrace();
							}

							try {
								mModel.setUserName(memberReportModel.getUserName());
							} catch (Exception e) 
							{
								e.printStackTrace();
							}


							try {
								mModel.setKitId(memberReportModel.getKitId());
							} catch (Exception e) 
							{
								e.printStackTrace();
							}

							try {
								mModel.setActivationDate(memberReportModel.getActivationDate());
							} catch (Exception e) 
							{
								e.printStackTrace();
							}

							try {
								mModel.setExpireDate(memberReportModel.getExpireDate());

							} catch (Exception e) 
							{
								e.printStackTrace();
							}
							try {
								mModel.setMobileNumber(userModelDetails.getMobileNum());
							} catch (Exception e) 
							{
								e.printStackTrace();
							}
							try {
								mModel.setEmailId("-");

							} catch (Exception e) 
							{
								e.printStackTrace();
							}
							try {

								mModel.setGender(userModelDetails.getGender());
							} catch (Exception e) 
							{
								e.printStackTrace();
							}

							try {

								complateMemberReportList.add(mModel);
							} catch (Exception e)
							{
								e.printStackTrace();
							}


							cnt ++;
						} catch (Exception e) 
						{
							e.printStackTrace();
						}

					}

				}	

			}

			if(gender.equalsIgnoreCase("male") && payStatus.equalsIgnoreCase("unpaidUser"))
			{
				String user_Gender ="Male";

				List<UserModelDetails> userModelDetailsList = memberReportService.getAllUnpaidUserDetailsByGender(user_Gender,from_Date,toDate);
                  
				int cnt = 1;
				for (UserModelDetails userModelDetails2 : userModelDetailsList) 
				{

					if(userModelDetails2.getGender().equalsIgnoreCase("Male"))
					{

						MemberReportModel mModel=new MemberReportModel();
						
						try {
							
							mModel.setSrNo(cnt);
						} catch (Exception e)
						{
							e.printStackTrace();
						}
						
						try {
							
							if(userModelDetails2.getUserModel().getKit() == null)
							{	
								mModel.setKitId("  ");
							}
							else
							{
								mModel.setKitId(userModelDetails2.getUserModel().getKit().getKit_username());
							}
							
							String emailId = userModelDetails2.getEmail();
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
									userEmailId = userModelDetails2.getEmail();

								}

							}	
						} catch (Exception e) 
						{
							e.printStackTrace();
						}
						
                         
						try {
							mModel.setUserName(userModelDetails2.getUserModel().getFirstName());
							
						} catch (Exception e) 
						{
							e.printStackTrace();
						}
						
						try {
							
							mModel.setGender(userModelDetails2.getGender());
						} catch (Exception e) 
						{
							e.printStackTrace();
						}
						
						try {
							
							mModel.setEmailId(userEmailId);
						} catch (Exception e)
						{
							e.printStackTrace();
						}
						try {
							mModel.setMobileNumber(userModelDetails2.getMobileNum());
							
						} catch (Exception e)
						{
							e.printStackTrace();
						}
						
						try {
							
							List<Kit_Upgrade_Or_Reg_Details> UpgradeOrRegisterUserlist = memberReportService.getKitActivationAndExpiryDatByObject(userModelDetails2);
							for (Kit_Upgrade_Or_Reg_Details kit_Upgrade_Or_Reg_Details : UpgradeOrRegisterUserlist) 
							{
								
								
								try {
									
									mModel.setActivationDate(kit_Upgrade_Or_Reg_Details.getRegistrationDate());
								} catch (Exception e) 
								{
									e.printStackTrace();
								}
								try {
									
									mModel.setExpireDate(kit_Upgrade_Or_Reg_Details.getExpiryDate());
								} catch (Exception e)
								{
									e.printStackTrace();
								}
								try {
									
									mModel.setKitId(""+kit_Upgrade_Or_Reg_Details.getKit_id().getKit_username());
								} catch (Exception e)
								{
									e.printStackTrace();
								}
								
							}
							try {
								
								complateMemberReportList.add(mModel);
							} catch (Exception e)
							{
							    e.printStackTrace();
							}
						} catch (Exception e)
						{
							e.printStackTrace();
						}

						

						cnt++;
					}


				}



			}
			if(gender.equalsIgnoreCase("female") && payStatus.equalsIgnoreCase("paidUser"))
			{
				try {

					List<MemberReportModel> memberReportList =memberReportService.getUserDetailsByPaidAndGender(from_Date,toDate);  

					int cnt = 1;
					for (MemberReportModel memberReportModel : memberReportList)
					{

						UserModel model2=new UserModel();
						
						try {
							model2.setUserId(memberReportModel.getUserId());
							
						} catch (Exception e)
						{
						e.printStackTrace();
						}
				         
						try {
							UserModelDetails userModelDetails=userService.getUserdetails(model2);

							if(userModelDetails.getGender().equalsIgnoreCase("female"))
							{
								MemberReportModel mModel=new MemberReportModel();
								
								
								try {
									mModel.setSrNo(cnt);
									
								} catch (Exception e)
								{
									e.printStackTrace();
								}
                                
								try {
									
									mModel.setUserId(memberReportModel.getUserId());
								} catch (Exception e)
								{
									e.printStackTrace();
								}
								try {
									
									mModel.setUserName(memberReportModel.getUserName());
								} catch (Exception e)
								{
									e.printStackTrace();
								}
								try {
									
									mModel.setKitId(memberReportModel.getKitId());
								} catch (Exception e)
								{
									e.printStackTrace();
								}
								try {
									mModel.setActivationDate(memberReportModel.getActivationDate());
									
								} catch (Exception e)
								{
									e.printStackTrace();
								}
								try {
									
									mModel.setExpireDate(memberReportModel.getExpireDate());
								} catch (Exception e)
								{
									e.printStackTrace();
								}
								try {
									
									mModel.setMobileNumber(userModelDetails.getMobileNum());
								} catch (Exception e)
								{
									e.printStackTrace();
								}
								try {
									
									mModel.setEmailId("-");
								} catch (Exception e)
								{
									e.printStackTrace();
								}
								try {
									
									mModel.setGender(userModelDetails.getGender());
								} catch (Exception e)
								{
									e.printStackTrace();
								}
								try {
									complateMemberReportList.add(mModel);
									
								} catch (Exception e)
								{
									e.printStackTrace();
								}
								cnt ++;
							}

							
						} catch (Exception e)
						{
						e.printStackTrace();
						}
						
						


					}	


				
				} catch (Exception e) 
				{
				e.printStackTrace();
				}
			}

			if(gender.equalsIgnoreCase("female") && payStatus.equalsIgnoreCase("unpaidUser"))
			{
				
				try {

					String user_Gender ="Female";

					List<UserModelDetails> userModelDetailsList = memberReportService.getAllUnpaidUserDetailsByGender(user_Gender,from_Date,toDate);

					int cnt = 1;
					for (UserModelDetails userModelDetails2 : userModelDetailsList) 
					{
						try {

							if(userModelDetails2.getGender().equalsIgnoreCase("Female"))
							{

								MemberReportModel mModel=new MemberReportModel();

								try {
									mModel.setSrNo(cnt);
									
								} catch (Exception e)
								{
									e.printStackTrace();
								}
								
								try {
									if(userModelDetails2.getUserModel().getKit() == null)
									{	
										mModel.setKitId("  ");
									}
									else
									{
										mModel.setKitId(userModelDetails2.getUserModel().getKit().getKit_username());
									}
									String emailId = userModelDetails2.getEmail();
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
											userEmailId = userModelDetails2.getEmail();

										}

									}	
								} catch (Exception e) 
								{
									e.printStackTrace();
								}
								
								try {
									
									mModel.setUserName(userModelDetails2.getUserModel().getFirstName());
									
								} catch (Exception e) 
								{
									e.printStackTrace();
								}
								
			                     try {
									
			                    	 mModel.setGender(userModelDetails2.getGender());
			                    	 
								} catch (Exception e) 
			                     {
									e.printStackTrace();
								 }
			                     try {
									
			                    	 mModel.setEmailId(userEmailId);
								} catch (Exception e)
			                     {
									e.printStackTrace();
								}
			                     
			                     try {
			                    	 mModel.setMobileNumber(userModelDetails2.getMobileNum());
									
								} catch (Exception e)
			                     {
									e.printStackTrace();
								}
			                     
			                     try {
			                    	 List<Kit_Upgrade_Or_Reg_Details> UpgradeOrRegisterUserlist = memberReportService.getKitActivationAndExpiryDatByObject(userModelDetails2);
										for (Kit_Upgrade_Or_Reg_Details kit_Upgrade_Or_Reg_Details : UpgradeOrRegisterUserlist) 
										{
											try {
												
											} catch (Exception e)
											{
												e.printStackTrace();
											}
											try {
												
												mModel.setActivationDate(kit_Upgrade_Or_Reg_Details.getRegistrationDate());
											} catch (Exception e)
											{
												e.printStackTrace();
											}
											try {
												mModel.setExpireDate(kit_Upgrade_Or_Reg_Details.getExpiryDate());
												
											} catch (Exception e) 
											{
												e.printStackTrace();
											}
											try {
												mModel.setKitId(""+kit_Upgrade_Or_Reg_Details.getKit_id().getKit_username());
												
											} catch (Exception e)
											{
												e.printStackTrace();
											}

										}
								} catch (Exception e)
			                     {
									e.printStackTrace();
								}
 								

			                     try {
									
			                    	 complateMemberReportList.add(mModel);
								} catch (Exception e)
			                     {
									e.printStackTrace();
								}

								cnt++;
							}

						
						} catch (Exception e) 
						{
							e.printStackTrace();
						}
					}

				
				} catch (Exception e) 
				{
					e.printStackTrace();
				}
			}


		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		if(gender.equalsIgnoreCase("allUser") && payStatus.equalsIgnoreCase("paidUser"))
		{
			List<MemberReportModel> memberReportList =memberReportService.getUserDetailsByPaidAndGender(from_Date,toDate);
			int cnt = 1;
			for (MemberReportModel memberReportModel : memberReportList)
			{
				UserModel allUserModel=new UserModel();
				allUserModel.setUserId(memberReportModel.getUserId());
				UserModelDetails userModelDetails=userService.getUserdetails(allUserModel);
					try {
						MemberReportModel memberRemortModel=new MemberReportModel();
						try {
							memberRemortModel.setSrNo(cnt);
						} catch (Exception e) 
						{
							e.printStackTrace();
						}

						try {
							memberRemortModel.setUserId(memberReportModel.getUserId());
						} catch (Exception e) 
						{
							e.printStackTrace();
						}

						try {
							memberRemortModel.setUserName(memberReportModel.getUserName());
						} catch (Exception e) 
						{
							e.printStackTrace();
						}


						try {
							memberRemortModel.setKitId(memberReportModel.getKitId());
						} catch (Exception e) 
						{
							e.printStackTrace();
						}

						try {
							memberRemortModel.setActivationDate(memberReportModel.getActivationDate());
						} catch (Exception e) 
						{
							e.printStackTrace();
						}

						try {
							memberRemortModel.setExpireDate(memberReportModel.getExpireDate());

						} catch (Exception e) 
						{
							e.printStackTrace();
						}
						try {
							memberRemortModel.setMobileNumber(userModelDetails.getMobileNum());
						} catch (Exception e) 
						{
							e.printStackTrace();
						}
						try {
							memberRemortModel.setEmailId("-");

						} catch (Exception e) 
						{
							e.printStackTrace();
						}
						try {

							memberRemortModel.setGender(userModelDetails.getGender());
						} catch (Exception e) 
						{
							e.printStackTrace();
						}

						try {

							complateMemberReportList.add(memberRemortModel);
						} catch (Exception e)
						{
							e.printStackTrace();
						}


						cnt ++;
					} catch (Exception e) 
					{
						e.printStackTrace();
					}

				

			}	

		}
		
	/*	-----------------------------------------------All User Unpaid----------------------------------------------------*/
		
		if(gender.equalsIgnoreCase("allUser") && payStatus.equalsIgnoreCase("unpaidUser"))
		{
		String user_Gender ="Male";
		List<UserModelDetails> userModelDetailsList = memberReportService.getAllUnpaidUserDetailsByGender(user_Gender,from_Date,toDate);
     	int cnt = 1;
		for (UserModelDetails userModelDetails2 : userModelDetailsList) 
		{
			if(userModelDetails2.getGender().equalsIgnoreCase("Male"))
			{
				MemberReportModel remortModel=new MemberReportModel();			
				try {		
					remortModel.setSrNo(cnt);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
				
				try {
					
					if(userModelDetails2.getUserModel().getKit() == null)
					{	
						remortModel.setKitId("  ");
					}
					else
					{
						remortModel.setKitId(userModelDetails2.getUserModel().getKit().getKit_username());
					}
					
					String emailId = userModelDetails2.getEmail();
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
							userEmailId = userModelDetails2.getEmail();

						}

					}	
				} catch (Exception e) 
				{
					e.printStackTrace();
				}
				
                 
				try {
					remortModel.setUserName(userModelDetails2.getUserModel().getFirstName());
					
				} catch (Exception e) 
				{
					e.printStackTrace();
				}
				
				try {
					
					remortModel.setGender(userModelDetails2.getGender());
				} catch (Exception e) 
				{
					e.printStackTrace();
				}
				
				try {
					
					remortModel.setEmailId(userEmailId);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
				try {
					remortModel.setMobileNumber(userModelDetails2.getMobileNum());
					
				} catch (Exception e)
				{
					e.printStackTrace();
				}
				
				try {
					
					List<Kit_Upgrade_Or_Reg_Details> UpgradeOrRegisterUserlist = memberReportService.getKitActivationAndExpiryDatByObject(userModelDetails2);
					for (Kit_Upgrade_Or_Reg_Details kit_Upgrade_Or_Reg_Details : UpgradeOrRegisterUserlist) 
					{
						
						
						try {
							
							remortModel.setActivationDate(kit_Upgrade_Or_Reg_Details.getRegistrationDate());
						} catch (Exception e) 
						{
							e.printStackTrace();
						}
						try {
							
							remortModel.setExpireDate(kit_Upgrade_Or_Reg_Details.getExpiryDate());
						} catch (Exception e)
						{
							e.printStackTrace();
						}
						try {
							
							remortModel.setKitId(""+kit_Upgrade_Or_Reg_Details.getKit_id().getKit_username());
						} catch (Exception e)
						{
							e.printStackTrace();
						}
						
					}
					try {
						
						complateMemberReportList.add(remortModel);
					} catch (Exception e)
					{
					    e.printStackTrace();
					}
				} catch (Exception e)
				{
					e.printStackTrace();
				}

				cnt++;
			}


		}


		
		
		/*------------------FemaleList ---------------------*/

		try {

			String femaleGender ="Female";

			List<UserModelDetails> femaleUserModelDetailsList = memberReportService.getAllUnpaidUserDetailsByGender(femaleGender,from_Date,toDate);

			int femaleCounter = 1;
			for (UserModelDetails femaleUserModelDetails : femaleUserModelDetailsList) 
			{
				try {

					if(femaleUserModelDetails.getGender().equalsIgnoreCase("Female"))
					{

						MemberReportModel mModel=new MemberReportModel();

						try {
							mModel.setSrNo(cnt);
							
						} catch (Exception e)
						{
							e.printStackTrace();
						}
						
						try {
							if(femaleUserModelDetails.getUserModel().getKit() == null)
							{	
								mModel.setKitId("  ");
							}
							else
							{
								mModel.setKitId(femaleUserModelDetails.getUserModel().getKit().getKit_username());
							}
							String emailId = femaleUserModelDetails.getEmail();
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
									userEmailId = femaleUserModelDetails.getEmail();

								}

							}	
						} catch (Exception e) 
						{
							e.printStackTrace();
						}
						
						try {
							
							mModel.setUserName(femaleUserModelDetails.getUserModel().getFirstName());
							
						} catch (Exception e) 
						{
							e.printStackTrace();
						}
						
	                     try {
							
	                    	 mModel.setGender(femaleUserModelDetails.getGender());
	                    	 
						} catch (Exception e) 
	                     {
							e.printStackTrace();
						 }
	                     try {
							
	                    	 mModel.setEmailId(userEmailId);
						} catch (Exception e)
	                     {
							e.printStackTrace();
						}
	                     
	                     try {
	                    	 mModel.setMobileNumber(femaleUserModelDetails.getMobileNum());
							
						} catch (Exception e)
	                     {
							e.printStackTrace();
						}
	                     
	                     try {
	                    	 List<Kit_Upgrade_Or_Reg_Details> UpgradeOrRegisterUserlist = memberReportService.getKitActivationAndExpiryDatByObject(femaleUserModelDetails);
								for (Kit_Upgrade_Or_Reg_Details kit_Upgrade_Or_Reg_Details : UpgradeOrRegisterUserlist) 
								{
									try {
										
									} catch (Exception e)
									{
										e.printStackTrace();
									}
									try {
										
										mModel.setActivationDate(kit_Upgrade_Or_Reg_Details.getRegistrationDate());
									} catch (Exception e)
									{
										e.printStackTrace();
									}
									try {
										mModel.setExpireDate(kit_Upgrade_Or_Reg_Details.getExpiryDate());
										
									} catch (Exception e) 
									{
										e.printStackTrace();
									}
									try {
										mModel.setKitId(""+kit_Upgrade_Or_Reg_Details.getKit_id().getKit_username());
										
									} catch (Exception e)
									{
										e.printStackTrace();
									}

								}
						} catch (Exception e)
	                     {
							e.printStackTrace();
						}
							

	                     try {
							
	                    	 complateMemberReportList.add(mModel);
						} catch (Exception e)
	                     {
							e.printStackTrace();
						}

						femaleCounter++;
					}

				
				} catch (Exception e) 
				{
					e.printStackTrace();
				}
			}

		
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
	
		}
		
		

		String fileName = null;
        
		try 
		{
		fileName = generateMemberReport(request, response,complateMemberReportList);
		} catch (IOException e) 
		{

			e.printStackTrace();
		}


		return fileName;
	}

	public String generateMemberReport(HttpServletRequest request, HttpServletResponse response,List<MemberReportModel> memberList)
			throws IOException{

		final String FILE_NAME = "memberReport.pdf";
		try{
			
			String pathact = null;
	
			try {
				
			pathact = request.getServletContext().getRealPath("/resources/snehReport");
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
			File dir1 = null;
			try {
				
			  dir1 = new File(pathact);
			} catch (Exception e)
			{
				e.printStackTrace();
			}
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
					
			
			
			Document document = null;
			try {
			document = new Document(pdfDoc);
				
			} catch (Exception e) 
			{
				e.printStackTrace();
			}

			document.setFontSize(7);
            
			String imFile = null;
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
		
			Table paidMemberTable = new Table(8);
			paidMemberTable.setWidthPercent(100);

		try {
			
			PdfFont font = PdfFontFactory.createFont(FontConstants.HELVETICA_BOLD);
			PdfFont fontHeader = PdfFontFactory.createFont(FontConstants.TIMES_ROMAN);
			
		
			Cell headerFirst = null;
			try {
				headerFirst= new Cell().add(new Paragraph("Sr No").setFont(font).setFontColor(Color.WHITE));
				headerFirst.setBackgroundColor(Color.BLACK);
				headerFirst.setBorder(new SolidBorder(Color.WHITE,1f));
				headerFirst.setWidthPercent(6);
				
				
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
			Cell headerSecond = null;
			try {
				headerSecond= new Cell().add(new Paragraph("Name").setFont(font).setFontColor(Color.WHITE));
				headerSecond.setBackgroundColor(Color.BLACK);
				headerSecond.setBorder(new SolidBorder(Color.WHITE,1f));
				headerSecond.setWidthPercent(20);
				
			} catch (Exception e)
			{
				e.printStackTrace();
			}
			
			Cell headerThird = null;
			try {
				headerThird= new Cell().add(new Paragraph("Contact No").setFont(font).setFontColor(Color.WHITE));
				headerThird.setBackgroundColor(Color.BLACK);
				headerThird.setBorder(new SolidBorder(Color.WHITE,1f));
				headerThird.setWidthPercent(11);
				
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
			
			Cell headerFourth = null;
			try {
				headerFourth= new Cell().add(new Paragraph("Email Id").setFont(font).setFontColor(Color.WHITE));
				headerFourth.setBackgroundColor(Color.BLACK);
				headerFourth.setBorder(new SolidBorder(Color.WHITE,1f));
				headerFourth.setWidthPercent(20);
				
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
			
			Cell headerFive = null;
			try {
				headerFive= new Cell().add(new Paragraph("Gender").setFont(font).setFontColor(Color.WHITE));
				headerFive.setBackgroundColor(Color.BLACK);
				headerFive.setBorder(new SolidBorder(Color.WHITE,1f));
				headerFive.setWidthPercent(8);
				
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
			
			Cell headerSix = null;
			try {
				headerSix= new Cell().add(new Paragraph("Kit Id").setFont(font).setFontColor(Color.WHITE));
				headerSix.setBackgroundColor(Color.BLACK);
				headerSix.setBorder(new SolidBorder(Color.WHITE,1f));
				headerSix.setWidthPercent(12);
				
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
			
			Cell headerSeven = null;
			try {
				headerSeven= new Cell().add(new Paragraph("Kit Activation Date").setFont(font).setFontColor(Color.WHITE));
				headerSeven.setBackgroundColor(Color.BLACK);
				headerSeven.setBorder(new SolidBorder(Color.WHITE,1f));
				headerSeven.setWidthPercent(10);
				
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
			
			Cell headerEight = null;
			try {
				headerEight= new Cell().add(new Paragraph("Kit Expiry Date").setFont(font).setFontColor(Color.WHITE));
				headerEight.setBackgroundColor(Color.BLACK);
				headerEight.setBorder(new SolidBorder(Color.WHITE,1f));
				headerEight.setWidthPercent(10);
				
				
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
			paidMemberTable.addCell(headerFirst);
			paidMemberTable.addCell(headerSecond);
			paidMemberTable.addCell(headerThird);
			paidMemberTable.addCell(headerFourth);
			paidMemberTable.addCell(headerFive);
			paidMemberTable.addCell(headerSix);
			paidMemberTable.addCell(headerSeven);
			paidMemberTable.addCell(headerEight);
			
			int serialNo = 1;
			
			List<List<MemberReportModel>> paidMemberData = new ArrayList<>();
			paidMemberData.add(memberList);
			List<List<MemberReportModel>> paidMemberDataSet = paidMemberData;
			for (List<MemberReportModel> record : paidMemberDataSet) {
				for (MemberReportModel currentData : record) 
				{
					try {
						
						String kit_Activation_Date =null;
						String expiry_Date = null;
						if(currentData.getActivationDate() != null || ""+currentData.getActivationDate() == "")
						{
							try {
								
								String kitActicationDate = ""+currentData.getActivationDate();			
								String[] kitActivationDateSplit = kitActicationDate.split("-");
								String kitActivationday = kitActivationDateSplit[2];
								String [] spliteKitActuvationDay = kitActivationday.split(" ");				
								String activationDate = spliteKitActuvationDay[0];				
								kit_Activation_Date =   activationDate + "-" + kitActivationDateSplit[1] + "-" + kitActivationDateSplit[0];
								
								} catch (Exception e)
							{
								e.printStackTrace();
							}
						}
						if(currentData.getExpireDate() != null || ""+currentData.getExpireDate() == "")
						{
							
					     try {
					    		String expiryDate = ""+currentData.getExpireDate();			
								String[] expiryDateSplit = expiryDate.split("-");
								String expiryDay = expiryDateSplit[2];
								String [] spliteExpieryDay = expiryDay.split(" ");				
								String todayDate1 = spliteExpieryDay[0];				
							    expiry_Date =   todayDate1 + "-" + expiryDateSplit[1] + "-" + expiryDateSplit[0];
							
						} catch (Exception e) 
					     {
							e.printStackTrace();
						}	
						}	
                        
						try {
							
							paidMemberTable.addCell(new Cell().add(new Paragraph(Integer.toString(serialNo))));
						} catch (Exception e)
						{
							e.printStackTrace();
						}
						try {
							paidMemberTable.addCell(new Cell().add(new Paragraph(currentData.getUserName())));
							
						} catch (Exception e)
						{
							e.printStackTrace();
						}
						try {
							
							paidMemberTable.addCell(new Cell().add(new Paragraph(currentData.getMobileNumber())));
						} catch (Exception e)
						{
							e.printStackTrace();
						}
						
						try {
							paidMemberTable.addCell(new Cell().add(new Paragraph(currentData.getEmailId())));
							
						} catch (Exception e) 
						{
						e.printStackTrace();
						}
						try {
							
							paidMemberTable.addCell(new Cell().add(new Paragraph(currentData.getGender())));
						} catch (Exception e)
						{
							e.printStackTrace();
						}
						try {
							
							paidMemberTable.addCell(new Cell().add(new Paragraph(currentData.getKitId() == null ? "" : ""+ currentData.getKitId())));
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
							
							paidMemberTable.addCell(new Cell().add(new Paragraph(kit_Activation_Date == null ? "" :  kit_Activation_Date)));
							
						} catch (Exception e) 
						{
							e.printStackTrace();
						}
						try {
							
							paidMemberTable.addCell(new Cell().add(new Paragraph(expiry_Date == null ? "" :  expiry_Date)));
						} catch (Exception e) 
						{
							e.printStackTrace();
						}

						serialNo++;
					} catch (Exception e)
					{
						e.printStackTrace();
					}
					

				}
			}
			try {
				Paragraph header = new Paragraph("MEMBER DETAILS");
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
					
					document.add(paidMemberTable);
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

		}catch(Exception e) 
		{
			e.printStackTrace();
		}
		
		return FILE_NAME;

	}
	
	
	

}
