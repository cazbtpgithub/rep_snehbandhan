package com.xanadutec.coreflex.Report;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.GroupLayout.Alignment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.color.WebColors;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.Style;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.border.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.BackgroundImage;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;
import com.xanadutec.coreflex.ReportModel.PartnerPreferancesReportModel;
import com.xanadutec.coreflex.ReportModel.RegistrationFormReportModel;

@Controller
public class RegistrationFormController {

	@Autowired
	RegistrationFormService registrationFormService;

	//Method to generate Registration Form report

	@RequestMapping(value = "/printUserProfile", method = RequestMethod.GET)
	@ResponseBody
	public String getMemberDetails(ModelMap  model,HttpServletRequest request, HttpServletResponse response, @RequestParam("userDetailsID") String userDetails_id)
	{
        String fileName = null;
		try {
		    RegistrationFormReportModel registrationFormModel = registrationFormService.getUserDetailsByUserId(Integer.parseInt(userDetails_id));
			fileName =	generateRegistrationForm(request,response,registrationFormModel);
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		return fileName;
	}
	//Method to generate personal details report
	@RequestMapping(value = "/printPersonalDetails", method = RequestMethod.POST)
	@ResponseBody
	public String printPersonalDetails(ModelMap  model,HttpServletRequest request, HttpServletResponse response, 
			@RequestParam("userDetailsID") String userDetails_id)
	{
		String fileName =null;
		try 
		{
		    List<RegistrationFormReportModel> personalDetailsList =registrationFormService.getUserPersonalDetailsByUserId(Integer.parseInt(userDetails_id));
			fileName =	generatePersonalInformationReport(request, response, personalDetailsList);
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		return fileName;

	}
	//Method to generate partner details report
	@RequestMapping(value = "/printPartnerPreferances", method = RequestMethod.POST)
	@ResponseBody
	public String printPartnerPreferances(ModelMap  model,HttpServletRequest request, HttpServletResponse response, 
			@RequestParam("userDetailsID") String userDetails_id)
	{
		List<PartnerPreferancesReportModel> partnerPreferancesList =  registrationFormService.getPartnerPrferancesByUserId(Integer.parseInt(userDetails_id));
		String fileName =null;
		for (PartnerPreferancesReportModel partnerPreferancesReportModel : partnerPreferancesList)
		{
			try{		
				fileName =	generatePartnerDetailsReport(request, response, partnerPreferancesReportModel,partnerPreferancesList);
			}catch(Exception e){
				e.getMessage();
			}
		}
		return fileName;
	}

	public String generatePartnerDetailsReport(HttpServletRequest request, HttpServletResponse response,PartnerPreferancesReportModel partnerDetailsModel , List<PartnerPreferancesReportModel> partnerDetailsList){
		final String FILE_NAME = "PartnerPrefrences.pdf";
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
			if (!dir1.exists()) {
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
			document.setFontSize(10);

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
			Image image =null;
			try {
				
				image = new Image(data);                        
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
			
			Table partnerPrefrencesTable = new Table(3);
			
			
			 PdfFont font = PdfFontFactory.createFont(FontConstants.HELVETICA_BOLD);
			 PdfFont fontHeader = PdfFontFactory.createFont(FontConstants.TIMES_ROMAN);
			
			Cell headerFirst = null;
			try {
				headerFirst= new Cell().add(new Paragraph("Sr No").setFont(font).setFontColor(Color.WHITE));
				headerFirst.setBackgroundColor(Color.BLACK);
				headerFirst.setBorder(new SolidBorder(Color.WHITE,1f));
				headerFirst.setWidthPercent(20);
				
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
			Cell headerSecond = null;
			try {
				headerSecond= new Cell().add(new Paragraph("Prefernces Type").setFont(font).setFontColor(Color.WHITE));
				headerSecond.setWidthPercent(40);
				headerSecond.setBackgroundColor(Color.BLACK);
				headerSecond.setBorder(new SolidBorder(Color.WHITE,1f));
				
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
			
			
			Cell headerThird = null;
			try {
				headerThird= new Cell().add(new Paragraph("Prefernces Details").setFont(font).setFontColor(Color.WHITE));
				headerThird.setWidthPercent(40);
				headerThird.setBackgroundColor(Color.BLACK);
				headerThird.setBorder(new SolidBorder(Color.WHITE,1f));
				
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
			
			partnerPrefrencesTable.addCell(headerFirst);
			partnerPrefrencesTable.addCell(headerSecond);
			partnerPrefrencesTable.addCell(headerThird);
			
			
			int serialNo = 1;
		
			List<List<PartnerPreferancesReportModel>> paidMemberData = new ArrayList<>();
			paidMemberData.add(partnerDetailsList);
			List<List<PartnerPreferancesReportModel>> paidMemberDataSet = paidMemberData;
			for (List<PartnerPreferancesReportModel> record : paidMemberDataSet) {
				for (PartnerPreferancesReportModel currentData : record) {


					
					try {
						
						partnerPrefrencesTable.addCell(new Cell().add(new Paragraph(Integer.toString(serialNo))));
						partnerPrefrencesTable.addCell(new Cell().add(new Paragraph("Country")));
						partnerPrefrencesTable.addCell(new Cell().add(new Paragraph(currentData.getCountry())));
					} catch (Exception e) {
						// TODO: handle exception
					}
					
					try {
						
						partnerPrefrencesTable.addCell(new Cell().add(new Paragraph(Integer.toString(serialNo+1))));
						partnerPrefrencesTable.addCell(new Cell().add(new Paragraph("State Country")));
						partnerPrefrencesTable.addCell(new Cell().add(new Paragraph(currentData.getState_Country())));
					} catch (Exception e) {
						// TODO: handle exception
					}
					
					try {
						partnerPrefrencesTable.addCell(new Cell().add(new Paragraph(Integer.toString(serialNo+2))));
						partnerPrefrencesTable.addCell(new Cell().add(new Paragraph("District")));
						partnerPrefrencesTable.addCell(new Cell().add(new Paragraph(currentData.getDistricts())));
						
					} catch (Exception e) {
						// TODO: handle exception
					}
					
					try {
						
						partnerPrefrencesTable.addCell(new Cell().add(new Paragraph(Integer.toString(serialNo+3))));
						partnerPrefrencesTable.addCell(new Cell().add(new Paragraph("Marital Status")));
						partnerPrefrencesTable.addCell(new Cell().add(new Paragraph(currentData.getMaterialStatus())));
					} catch (Exception e) {
						// TODO: handle exception
					}
					
					try {
						partnerPrefrencesTable.addCell(new Cell().add(new Paragraph(Integer.toString(serialNo+4))));
						partnerPrefrencesTable.addCell(new Cell().add(new Paragraph("Physical Status")));
						partnerPrefrencesTable.addCell(new Cell().add(new Paragraph(currentData.getPhysicalStatus())));
						
					} catch (Exception e) {
						// TODO: handle exception
					}

					try {
						
						partnerPrefrencesTable.addCell(new Cell().add(new Paragraph(Integer.toString(serialNo+5))));
						partnerPrefrencesTable.addCell(new Cell().add(new Paragraph("Height")));
						partnerPrefrencesTable.addCell(new Cell().add(new Paragraph(currentData.getHeightFrom().toString()+" to "+currentData.getHeightTo())));
					} catch (Exception e) {
						// TODO: handle exception
					}
					
					try {
						partnerPrefrencesTable.addCell(new Cell().add(new Paragraph(Integer.toString(serialNo+6))));
						partnerPrefrencesTable.addCell(new Cell().add(new Paragraph("Body Type")));
						partnerPrefrencesTable.addCell(new Cell().add(new Paragraph(currentData.getBodyType())));
						
					} catch (Exception e) {
						// TODO: handle exception
					}
					
					try {
						partnerPrefrencesTable.addCell(new Cell().add(new Paragraph(Integer.toString(serialNo+7))));
						partnerPrefrencesTable.addCell(new Cell().add(new Paragraph("Complexion")));
						partnerPrefrencesTable.addCell(new Cell().add(new Paragraph(currentData.getComplexion())));
						
					} catch (Exception e) {
						// TODO: handle exception
					}
					
					try {
						partnerPrefrencesTable.addCell(new Cell().add(new Paragraph(Integer.toString(serialNo+8))));
						partnerPrefrencesTable.addCell(new Cell().add(new Paragraph("Employee In")));
						partnerPrefrencesTable.addCell(new Cell().add(new Paragraph(currentData.getEmployeedIn())));
						
					} catch (Exception e) {
						// TODO: handle exception
					}

					
					try {
						partnerPrefrencesTable.addCell(new Cell().add(new Paragraph(Integer.toString(serialNo+9))));
						partnerPrefrencesTable.addCell(new Cell().add(new Paragraph("Annual Income")));
						partnerPrefrencesTable.addCell(new Cell().add(new Paragraph(currentData.getIncomeAmount()+" / "+currentData.getIncomeType())));
						
					} catch (Exception e) {
						// TODO: handle exception
					}

					try {
						partnerPrefrencesTable.addCell(new Cell().add(new Paragraph(Integer.toString(serialNo+10))));
						partnerPrefrencesTable.addCell(new Cell().add(new Paragraph("Religion")));
						partnerPrefrencesTable.addCell(new Cell().add(new Paragraph(currentData.getReligion())));
						
					} catch (Exception e) {
						// TODO: handle exception
					}

					try {
						
						partnerPrefrencesTable.addCell(new Cell().add(new Paragraph(Integer.toString(serialNo+11))));
						partnerPrefrencesTable.addCell(new Cell().add(new Paragraph("caste")));
						partnerPrefrencesTable.addCell(new Cell().add(new Paragraph(currentData.getCaste())));
					} catch (Exception e) {
						// TODO: handle exception
					}
					

					try {
						partnerPrefrencesTable.addCell(new Cell().add(new Paragraph(Integer.toString(serialNo+12))));
						partnerPrefrencesTable.addCell(new Cell().add(new Paragraph("Family Class")));
						partnerPrefrencesTable.addCell(new Cell().add(new Paragraph(currentData.getFamilyClass())));
						
					} catch (Exception e) {
						// TODO: handle exception
					}
					
					try {
						
						partnerPrefrencesTable.addCell(new Cell().add(new Paragraph(Integer.toString(serialNo+13))));
						partnerPrefrencesTable.addCell(new Cell().add(new Paragraph("Family Type")));
						partnerPrefrencesTable.addCell(new Cell().add(new Paragraph(currentData.getFamilyType())));
					} catch (Exception e) {
						// TODO: handle exception
					}
					

				
					try {
						partnerPrefrencesTable.addCell(new Cell().add(new Paragraph(Integer.toString(serialNo+14))));
						partnerPrefrencesTable.addCell(new Cell().add(new Paragraph("Family Values")));
						partnerPrefrencesTable.addCell(new Cell().add(new Paragraph(currentData.getFamilyValues())));
						
					} catch (Exception e) {
						// TODO: handle exception
					}

					
					try {
						partnerPrefrencesTable.addCell(new Cell().add(new Paragraph(Integer.toString(serialNo+15))));
						partnerPrefrencesTable.addCell(new Cell().add(new Paragraph("Drinking Habits")));
						partnerPrefrencesTable.addCell(new Cell().add(new Paragraph(currentData.getDrink())));
					} catch (Exception e) {
						// TODO: handle exception
					}
					

					try {
						partnerPrefrencesTable.addCell(new Cell().add(new Paragraph(Integer.toString(serialNo+16))));
						partnerPrefrencesTable.addCell(new Cell().add(new Paragraph("Eating Habits")));
						partnerPrefrencesTable.addCell(new Cell().add(new Paragraph(currentData.getEating())));
					} catch (Exception e) {
						// TODO: handle exception
					}
					
					try {
						partnerPrefrencesTable.addCell(new Cell().add(new Paragraph(Integer.toString(serialNo+17))));
						partnerPrefrencesTable.addCell(new Cell().add(new Paragraph("Smoking Habits")));
						partnerPrefrencesTable.addCell(new Cell().add(new Paragraph(currentData.getSmoke())));
					} catch (Exception e) {
						// TODO: handle exception
					}


					serialNo++;

				}
			}

		
			
			try {
				
				Paragraph header = new Paragraph("PARTNER PREFERENCES DETAILS");
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
				document.add(partnerPrefrencesTable);
                
				try {
					pdfDoc.close();
					
				} catch (Exception e)
				{
					e.printStackTrace();
				}
				document.close();
				
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
			
			
			
          return FILE_NAME;
		}catch(Exception e){

		}
     return FILE_NAME;
	}


	public String generatePersonalInformationReport(HttpServletRequest request, HttpServletResponse response,
			List<RegistrationFormReportModel> personalDetailsList)
	{
		
		final String FILE_NAME = "PersonalInformation.pdf";
		try{	


			String pathact = request.getServletContext().getRealPath("/resources/snehReport");
			File dir1 = new File(pathact);
			if (!dir1.exists()) {
				dir1.mkdirs();
			}

			
			String filePath = request.getServletContext().getRealPath("/resources/snehReport/" + FILE_NAME);
			PdfDocument pdfDoc = new PdfDocument(new PdfWriter(filePath));
			Document document = new Document(pdfDoc);
			document.setFontSize(9);
			ImageData logoImageData=null;
			String imFile = request.getServletContext().getRealPath("/resources/images/reportHeader.png");
			try {
				 logoImageData = ImageDataFactory.create(imFile); 
			} catch (Exception e) {
				// TODO: handle exception
			}
			    

			Image image=null;
			try {
				 image = new Image(logoImageData);
			} catch (Exception e) {
			}
			                        
		
			 PdfFont font = PdfFontFactory.createFont(FontConstants.HELVETICA_BOLD);
			 PdfFont fontHeader = PdfFontFactory.createFont(FontConstants.TIMES_ROMAN);
			 
			
			Table personalInformationTable = new Table(3);
			
			personalInformationTable.setVerticalAlignment(VerticalAlignment.MIDDLE);
			Cell headerFirst ;
			headerFirst= new Cell().add(new Paragraph("Sr No").setFont(font).setFontColor(Color.WHITE));
			//headerFirst.setWidthPercent(10);
			headerFirst.setBackgroundColor(Color.BLACK);
			headerFirst.setBorder(new SolidBorder(Color.WHITE,1f));
			headerFirst.setWidthPercent(15);
			
			
			
			try {
				
				Cell headerSecond ;
				headerSecond= new Cell().add(new Paragraph("Information Type").setFont(font).setFontColor(Color.WHITE));
				headerSecond.setWidthPercent(35);
				headerSecond.setBackgroundColor(Color.BLACK);
				headerSecond.setBorder(new SolidBorder(Color.WHITE,1f));
				Cell headerThird ;
				headerThird= new Cell().add(new Paragraph("Information Details").setFont(font).setFontColor(Color.WHITE));
				headerThird.setWidthPercent(35);
				headerThird.setBackgroundColor(Color.BLACK);
				headerThird.setBorder(new SolidBorder(Color.WHITE,1f));
				

			personalInformationTable.addCell(headerFirst);
			personalInformationTable.addCell(headerSecond);
			personalInformationTable.addCell(headerThird);
			
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
			
			int serialNo = 1;
			
		
			List<List<RegistrationFormReportModel>> personalDetailsData = new ArrayList<>();
			personalDetailsData.add(personalDetailsList);
			List<List<RegistrationFormReportModel>> personalDetailsDataSet = personalDetailsData;
			for (List<RegistrationFormReportModel> record : personalDetailsDataSet) 
			{
				for (RegistrationFormReportModel currentData : record)
				{
                   try {
					String dateofbirth = ""+currentData.getDateOfBirth();			
					String[] dateofBirtSplit = dateofbirth.split("-");
					String day = dateofBirtSplit[2];
					String [] sliteDay = day.split(" ");				
					String todayDate = sliteDay[0];				
					String dateOf_Birth =   todayDate + "-" + dateofBirtSplit[1] + "-" + dateofBirtSplit[0];
					String userEmailId = "";
					try {	
						String emailId = currentData.getEmail();
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
								userEmailId = currentData.getEmail();

							}

						}
						
					} catch (Exception e)
					{
						e.printStackTrace();
					}
					
				
					
					try {
						personalInformationTable.addCell(new Cell().add(new Paragraph(Integer.toString(serialNo))));
					} catch (Exception e) {
					
					}
					
					try {
						personalInformationTable.addCell(new Cell().add(new Paragraph("Full Name")));
						personalInformationTable.addCell(new Cell().add(new Paragraph(currentData.getFullName())));
						
					} catch (Exception e) {
					}
					
					try {
						personalInformationTable.addCell(new Cell().add(new Paragraph(Integer.toString(serialNo+1))));
						personalInformationTable.addCell(new Cell().add(new Paragraph("Profile Created For")));
						personalInformationTable.addCell(new Cell().add(new Paragraph(currentData.getProfileCreatedFor())));
						
					} catch (Exception e) {
					}
					

					
					try {
						personalInformationTable.addCell(new Cell().add(new Paragraph(Integer.toString(serialNo+2))));
						personalInformationTable.addCell(new Cell().add(new Paragraph("Contact No")));
						personalInformationTable.addCell(new Cell().add(new Paragraph(currentData.getMobileNo())));
						
					} catch (Exception e) {
					}
					
					try {
						personalInformationTable.addCell(new Cell().add(new Paragraph(Integer.toString(serialNo+3))));
						personalInformationTable.addCell(new Cell().add(new Paragraph("Email Id")));
						personalInformationTable.addCell(new Cell().add(new Paragraph(userEmailId)));
						
					} catch (Exception e) {
					}

					

					try {
						
						personalInformationTable.addCell(new Cell().add(new Paragraph(Integer.toString(serialNo+4))));
						personalInformationTable.addCell(new Cell().add(new Paragraph("Date of Birth")));
						personalInformationTable.addCell(new Cell().add(new Paragraph(dateOf_Birth)));
					} catch (Exception e) {
					}

					
					try {
						personalInformationTable.addCell(new Cell().add(new Paragraph(Integer.toString(serialNo+5))));
						personalInformationTable.addCell(new Cell().add(new Paragraph("Gender")));
						personalInformationTable.addCell(new Cell().add(new Paragraph(currentData.getGender())));
						
					} catch (Exception e) {
					}

					
					try {
						personalInformationTable.addCell(new Cell().add(new Paragraph(Integer.toString(serialNo+6))));
						personalInformationTable.addCell(new Cell().add(new Paragraph("Country")));
						personalInformationTable.addCell(new Cell().add(new Paragraph(currentData.getCountry())));
						
					} catch (Exception e) {
						// TODO: handle exception
					}

					try {
						personalInformationTable.addCell(new Cell().add(new Paragraph(Integer.toString(serialNo+7))));
						personalInformationTable.addCell(new Cell().add(new Paragraph("State")));
						personalInformationTable.addCell(new Cell().add(new Paragraph(currentData.getState())));
					} catch (Exception e) {
					}
					
					try {
						
						personalInformationTable.addCell(new Cell().add(new Paragraph(Integer.toString(serialNo+8))));
						personalInformationTable.addCell(new Cell().add(new Paragraph("District")));
						personalInformationTable.addCell(new Cell().add(new Paragraph(currentData.getDistrict())));
					} catch (Exception e) {
					}
					
                 try {
                	 if(currentData.getLandmark() != null)
                	 {
                		 personalInformationTable.addCell(new Cell().add(new Paragraph(Integer.toString(serialNo+9))));
                		 personalInformationTable.addCell(new Cell().add(new Paragraph("Landmark")));
                		 personalInformationTable.addCell(new Cell().add(new Paragraph(currentData.getLandmark())));
                		 
                	 }
                	 else
                	 {
                		 personalInformationTable.addCell(new Cell().add(new Paragraph(Integer.toString(serialNo+9))));
                		 personalInformationTable.addCell(new Cell().add(new Paragraph("Landmark")));
                		 personalInformationTable.addCell(new Cell().add(new Paragraph("-")));
                	 }
						
					} catch (Exception e) {
					}

					try {
						personalInformationTable.addCell(new Cell().add(new Paragraph(Integer.toString(serialNo+10))));
						personalInformationTable.addCell(new Cell().add(new Paragraph("About Me")));
						personalInformationTable.addCell(new Cell().add(new Paragraph(currentData.getAboutMe())));
					} catch (Exception e) {
						// TODO: handle exception
					}

					
					try {
						personalInformationTable.addCell(new Cell().add(new Paragraph(Integer.toString(serialNo+11))));
						personalInformationTable.addCell(new Cell().add(new Paragraph("Marital Status")));
						personalInformationTable.addCell(new Cell().add(new Paragraph(currentData.getMaterialStatus())));
					} catch (Exception e) {
						// TODO: handle exception
					}
						
					try {
						personalInformationTable.addCell(new Cell().add(new Paragraph(Integer.toString(serialNo+12))));
						personalInformationTable.addCell(new Cell().add(new Paragraph("Physical Status")));
						personalInformationTable.addCell(new Cell().add(new Paragraph(currentData.getPhysicalStatus())));
					} catch (Exception e) {
						// TODO: handle exception
					}
					

					try {
						personalInformationTable.addCell(new Cell().add(new Paragraph(Integer.toString(serialNo+13))));
						personalInformationTable.addCell(new Cell().add(new Paragraph("Height")));
						personalInformationTable.addCell(new Cell().add(new Paragraph(currentData.getHeightFrom().toString())));

						
					} catch (Exception e) {
						// TODO: handle exception
					}
					

					try {
						personalInformationTable.addCell(new Cell().add(new Paragraph(Integer.toString(serialNo+14))));
						personalInformationTable.addCell(new Cell().add(new Paragraph("Weight")));
						personalInformationTable.addCell(new Cell().add(new Paragraph(currentData.getWeight().toString())));
					} catch (Exception e) {
						// TODO: handle exception
					}
					
					try {
						
						personalInformationTable.addCell(new Cell().add(new Paragraph(Integer.toString(serialNo+15))));
						personalInformationTable.addCell(new Cell().add(new Paragraph("Body Type")));
						personalInformationTable.addCell(new Cell().add(new Paragraph(currentData.getBodyType())));
						
					} catch (Exception e) {
						// TODO: handle exception
					}
					

					try {

						personalInformationTable.addCell(new Cell().add(new Paragraph(Integer.toString(serialNo+16))));
						personalInformationTable.addCell(new Cell().add(new Paragraph("Complexion")));
						personalInformationTable.addCell(new Cell().add(new Paragraph(currentData.getComplexion())));
					} catch (Exception e) {
						// TODO: handle exception
					}
					try {
						personalInformationTable.addCell(new Cell().add(new Paragraph(Integer.toString(serialNo+17))));
						personalInformationTable.addCell(new Cell().add(new Paragraph("Religion")));
						personalInformationTable.addCell(new Cell().add(new Paragraph(currentData.getReligion())));
					} catch (Exception e) {
						// TODO: handle exception
					}
					
					try {
						personalInformationTable.addCell(new Cell().add(new Paragraph(Integer.toString(serialNo+18))));
						personalInformationTable.addCell(new Cell().add(new Paragraph("Caste")));
						personalInformationTable.addCell(new Cell().add(new Paragraph(currentData.getCaste())));
					} catch (Exception e) {
						// TODO: handle exception
					}


					try {
						personalInformationTable.addCell(new Cell().add(new Paragraph(Integer.toString(serialNo+19))));
						personalInformationTable.addCell(new Cell().add(new Paragraph("Employee In")));
						personalInformationTable.addCell(new Cell().add(new Paragraph(currentData.getEmployeeIn())));
					} catch (Exception e) {
						// TODO: handle exception
					}
					
					try {
						personalInformationTable.addCell(new Cell().add(new Paragraph(Integer.toString(serialNo+20))));
					personalInformationTable.addCell(new Cell().add(new Paragraph("Education Category")));
					personalInformationTable.addCell(new Cell().add(new Paragraph(currentData.getEducationCategory())));
						
					} catch (Exception e) {
						// TODO: handle exception
					}
					try {
						personalInformationTable.addCell(new Cell().add(new Paragraph(Integer.toString(serialNo+21))));
						personalInformationTable.addCell(new Cell().add(new Paragraph("Education")));
						personalInformationTable.addCell(new Cell().add(new Paragraph(currentData.getEductaion())));
						
					} catch (Exception e) {
						// TODO: handle exception
					}
					
					try {
						personalInformationTable.addCell(new Cell().add(new Paragraph(Integer.toString(serialNo+22))));
						personalInformationTable.addCell(new Cell().add(new Paragraph("Annual Income")));
						personalInformationTable.addCell(new Cell().add(new Paragraph(currentData.getIncomeAmount()+ "/" +currentData.getIncomeAmount())));
						
					} catch (Exception e) {
						// TODO: handle exception
					}

					try {
						
						personalInformationTable.addCell(new Cell().add(new Paragraph(Integer.toString(serialNo+23))));
						personalInformationTable.addCell(new Cell().add(new Paragraph("Occupation Category")));
						personalInformationTable.addCell(new Cell().add(new Paragraph(currentData.getOccupationCategory())));
					} catch (Exception e) {
						// TODO: handle exception
					}
					

					try {
						personalInformationTable.addCell(new Cell().add(new Paragraph(Integer.toString(serialNo+24))));
						personalInformationTable.addCell(new Cell().add(new Paragraph("Occupation")));
						personalInformationTable.addCell(new Cell().add(new Paragraph(currentData.getOccupation())));
					} catch (Exception e) {
						// TODO: handle exception
					}
					
					try {
						personalInformationTable.addCell(new Cell().add(new Paragraph(Integer.toString(serialNo+25))));
						personalInformationTable.addCell(new Cell().add(new Paragraph("caste")));
						personalInformationTable.addCell(new Cell().add(new Paragraph(currentData.getCaste())));
						
					} catch (Exception e) {
						// TODO: handle exception
					}

					try {
						personalInformationTable.addCell(new Cell().add(new Paragraph(Integer.toString(serialNo+26))));
						personalInformationTable.addCell(new Cell().add(new Paragraph("Family Class")));
						personalInformationTable.addCell(new Cell().add(new Paragraph(currentData.getFamilyPreference())));
						
					} catch (Exception e) {
						// TODO: handle exception
					}
					
					try {
						personalInformationTable.addCell(new Cell().add(new Paragraph(Integer.toString(serialNo+27))));
						personalInformationTable.addCell(new Cell().add(new Paragraph("Family Type")));
						personalInformationTable.addCell(new Cell().add(new Paragraph(currentData.getFamilyType())));
						
					} catch (Exception e) {
						// TODO: handle exception
					}
					
					try {
						personalInformationTable.addCell(new Cell().add(new Paragraph(Integer.toString(serialNo+28))));
						personalInformationTable.addCell(new Cell().add(new Paragraph("Family Values")));
						personalInformationTable.addCell(new Cell().add(new Paragraph(currentData.getFamilyValues())));
						
					} catch (Exception e) {
						// TODO: handle exception
					}
					try {
						
						personalInformationTable.addCell(new Cell().add(new Paragraph(Integer.toString(serialNo+29))));
						personalInformationTable.addCell(new Cell().add(new Paragraph("Drinking Habits")));
						personalInformationTable.addCell(new Cell().add(new Paragraph(currentData.getDrinkinghabits())));
						
					} catch (Exception e) {
						// TODO: handle exception
					}
					
					try {
						personalInformationTable.addCell(new Cell().add(new Paragraph(Integer.toString(serialNo+30))));
						personalInformationTable.addCell(new Cell().add(new Paragraph("Eating Habits")));
						personalInformationTable.addCell(new Cell().add(new Paragraph(currentData.getEatingHabits())));
					} catch (Exception e) {
						// TODO: handle exception
					}

					try {
						personalInformationTable.addCell(new Cell().add(new Paragraph(Integer.toString(serialNo+31))));
						personalInformationTable.addCell(new Cell().add(new Paragraph("Smoking Habits")));
						personalInformationTable.addCell(new Cell().add(new Paragraph(currentData.getSmokingHabits())));
					} catch (Exception e) {
						// TODO: handle exception
					}

					serialNo++;
					
                   } catch (Exception e) 
                   {
					e.printStackTrace();
				   }

				}
			}
			
			
			try {
				
				Paragraph header = new Paragraph("PERSONAL INFORMATION DETAILS");
				header.setFontSize(18f);
				header.setTextAlignment(TextAlignment.CENTER);
				
				try {
					header.setFont(fontHeader);
					
				} catch (Exception e) {
				}
				
				try {
					document.add(image);
				} catch (Exception e) {
				}
				try {
					document.add(header);
				} catch (Exception e) {
				}
				
				try {
					document.add(personalInformationTable);
				} catch (Exception e) {
				}
				
				pdfDoc.close();
				document.close();
				
			} catch (Exception e) {
				e.printStackTrace();
			}

	    	
			
			return FILE_NAME;

		}catch(Exception e) {
			e.printStackTrace();
		}
		return FILE_NAME;

	}


	public String generateRegistrationForm(HttpServletRequest request, HttpServletResponse response,
			RegistrationFormReportModel personalDetailsModel){

		try{	
			
			String filename = personalDetailsModel.getUserProfileImageName();
			String spliteFileName[]= filename.split("#");	
			String file_Name = spliteFileName[0];
			final String FILE_NAME = "RegistrationForm.pdf";

			String pathact = request.getServletContext().getRealPath("/resources/snehReport");
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
			
			String bgImFile = null;
			try {
			 bgImFile = request.getServletContext().getRealPath("/resources/images/formReportBg.png");
				
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
			 PdfCanvas canvas = new PdfCanvas(pdfDoc.addNewPage());
		        canvas.addImage(ImageDataFactory.create(bgImFile), PageSize.A4, false);
			Document document = new Document(pdfDoc);
			document.setFontSize(10);
		
			Style headerStyle = new Style()
			.setFontSize(10)
			.setFontColor(Color.WHITE)
			.setBackgroundColor(WebColors.getRGBColor("#c82868"))
			.setTextAlignment(TextAlignment.CENTER)
			.setBold();

			Style typeText = new Style()
			.setFontSize(8)
			.setBold();

			String imFile = null;
			try {
				
		     imFile = request.getServletContext().getRealPath("/resources/images/registrationFormHeader.png");
			} catch (Exception e) 
			{
			e.printStackTrace();
			}
  
			ImageData logoImageData = null;
			try {
				
		    logoImageData = ImageDataFactory.create(imFile);              
			} catch (Exception e) 
			{
				e.printStackTrace();
			}

 
			Image image = null;
			try {
			 image = new Image(logoImageData);                        
				
			} catch (Exception e)
			{
				e.printStackTrace();
			}
			
			String imFile1 = null;
			try {			
			imFile1 = request.getServletContext().getRealPath("/resources/UserImages/"+ file_Name);
			} catch (Exception e)
			{
				e.printStackTrace();
			}
			ImageData profileImageData = null;
			try {
				
		     profileImageData = ImageDataFactory.create(imFile1);
			} catch (Exception e) 
			{
				e.printStackTrace();
			}

			Image imagePerson = null;
			try {
				
			imagePerson = new Image(profileImageData);                        
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
			imagePerson.setWidth(120f);
			imagePerson.setHeight(120f);
			

			Date currentDate = new Date();
			String todaysDate = new SimpleDateFormat("dd-MM-yyyy").format(currentDate);

			Paragraph headerBasicInfo = new Paragraph("Basic Information");
			headerBasicInfo.addStyle(headerStyle);
			Paragraph headerLocation = new Paragraph("Location");
			headerLocation.addStyle(headerStyle);
			Paragraph headerReligion = new Paragraph("Religion Information");
			headerReligion.addStyle(headerStyle);
			Paragraph headerProfessional = new Paragraph("Professional Information");
			headerProfessional.addStyle(headerStyle);
			Paragraph headerFamily = new Paragraph("Family Information");
			headerFamily.addStyle(headerStyle);
			Paragraph headerLifeStyle = new Paragraph("Life Style");
			headerLifeStyle.addStyle(headerStyle);
			
			Paragraph headerPartnerPref = new Paragraph("Partner Preferences");
			headerPartnerPref.addStyle(headerStyle);


		
			Text firstTitle = new Text("Sneh Bandhan Marriage");
			Text secondTitle = new Text("Bureau Bio-data Form");
			firstTitle.addStyle(typeText);
			secondTitle.addStyle(typeText);

			Paragraph titleFirstLine = new Paragraph(firstTitle);
			titleFirstLine.setTextAlignment(TextAlignment.RIGHT);

			Paragraph titleSecondLine = new Paragraph(secondTitle);
			titleSecondLine.setTextAlignment(TextAlignment.RIGHT);

			Text salesTitle = new Text("Sales Ex : ________");
			salesTitle.addStyle(typeText);

			Paragraph sales = new Paragraph(salesTitle);
			sales.setTextAlignment(TextAlignment.RIGHT);

			Text dateTitle = new Text("Date : ");
			dateTitle.addStyle(typeText);

			Paragraph date = new Paragraph(dateTitle.getText()+todaysDate);
			date.setTextAlignment(TextAlignment.RIGHT);

			Text name = new Text("Name : ");
			name.addStyle(typeText);


			Text userId = new Text("User ID (Card No) :  ");
			Text dob = new Text(" Date of Birth :  ");
			Text password = new Text(" Password :  ");
			Text expectation = new Text("Expectation").setFontColor(Color.RED);
			Text maritalStatus = new Text("Marital Status :  ");
			Text physicalStatus = new Text("  Physical Status :  ");
			Text bodyType = new Text("Body Type :  ");
			Text gender = new Text("Athletic  Gender :  ");
			Text height = new Text("Height :  ");
			Text weight = new Text("  Weight :  ");
			Text streetAdd = new Text("Street Address");
		
			Paragraph titleDetails = new Paragraph(firstTitle.getText()+"\n"+secondTitle.getText()+"\n"+salesTitle.getText()+"\n"+
					dateTitle.getText()+" : "+todaysDate);
			titleDetails.setTextAlignment(TextAlignment.RIGHT);
			titleDetails.addStyle(typeText);


			Table tableHeaderBasicInfo = new Table(1);
			tableHeaderBasicInfo.setWidthPercent(100);
			tableHeaderBasicInfo.addCell(headerBasicInfo);
			tableHeaderBasicInfo.setMarginTop(50);

			Paragraph basicInfo = new Paragraph();
			String dateOf_Birth =null;
			
			try {
				String dateofbirth = ""+personalDetailsModel.getDateOfBirth();			
				String[] dateofBirtSplit = dateofbirth.split("-");
				String day = dateofBirtSplit[2];
				String [] sliteDay = day.split(" ");				
				String todayDate = sliteDay[0];				
			    dateOf_Birth =   todayDate + "-" + dateofBirtSplit[1] + "-" + dateofBirtSplit[0];
				
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
				try {
					
					basicInfo.add(name.getText()+personalDetailsModel.getFullName()+"\n"+
							userId.getText()+personalDetailsModel.getUsername()+
							"    "+password.getText()+personalDetailsModel.getPassword()+dob.getText()+dateOf_Birth+
							"\n"+expectation.getText()+":  "+personalDetailsModel.getAboutMe()+
							"\n"+maritalStatus.getText()+personalDetailsModel.getMaritalStatus()+
							"        "+bodyType.getText()+personalDetailsModel.getBodyType()+"\n"+physicalStatus.getText()+personalDetailsModel.getPhysicalStatus()+
							"        "+gender.getText()+personalDetailsModel.getGender()+"\n"+height.getText()+personalDetailsModel.getHeightFrom().toString()+
							"        "+weight.getText()+personalDetailsModel.getWeight().toString());
				} catch (Exception e) 
				{
					e.printStackTrace();
				}
				Paragraph signature = new Paragraph();
				signature.add(imagePerson);
				signature.add("________________\n"+"Signature");
			Table tablebasicinfo = new Table(new float[]{1,5});
			tablebasicinfo.setWidthPercent(100);
			tablebasicinfo.addCell(basicInfo);
			tablebasicinfo.addCell(signature);
			tablebasicinfo.setBorder(Border.NO_BORDER);

	
			Table tableLocationtitle = new Table(1);
			tableLocationtitle.setWidthPercent(100);
			tableLocationtitle.addCell(headerLocation);

			String userEmailId = null;
			
			try {
				
				String emailId = personalDetailsModel.getEmail();
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
						userEmailId = personalDetailsModel.getEmail();
						
					}
					
				}
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
			
			
			
			Paragraph personalDetails= new Paragraph();
			try {
				personalDetails.add(streetAdd.getText()+":   "+personalDetailsModel.getLandmark()+
						"\nState : "+personalDetailsModel.getState()+"     District : "+personalDetailsModel.getDistrict()+"          "+"   Taluka : "+personalDetailsModel.getSubDistrict()+
						"\nCity:    "+personalDetailsModel.getDistrict()+"      Pin NO.    _______________________      Phone No:__________________"+
						"\n"+"Contact No : "+personalDetailsModel.getMobileNo()+"     Contact No :  ____________________"+"     Email Id : "+userEmailId);
				
			} catch (Exception e) 
			{
				e.printStackTrace();
			}

			Table tablePersonalDetails = new Table(1);
			tablePersonalDetails.setWidthPercent(100);
			tablePersonalDetails.addCell(personalDetails);

			Table tableReligonTitle = new Table(1);
			tableReligonTitle.setWidthPercent(100);
			tableReligonTitle.addCell(headerReligion);


			Paragraph religionDetails= new Paragraph();
			religionDetails.add("Religion : "+personalDetailsModel.getReligion()+"      Caste : "+personalDetailsModel.getCaste()+"      Mother Tongue:__________________________");

			Table tablereligionDetails = new Table(1);
			tablereligionDetails.setWidthPercent(100);
			tablereligionDetails.addCell(religionDetails);

			Paragraph professionalDetails= new Paragraph();
			try {
				
				professionalDetails.add("Education Category : "+personalDetailsModel.getEducationCategory()+"              Education : "+personalDetailsModel.getEductaion()+"\n"+
						"Occupation Category : "+personalDetailsModel.getOccupationCategory()+"     Occupation : "+personalDetailsModel.getOccupation()+"\n"+
						"Employee In Type  : "+personalDetailsModel.getEmployeeIn()+"     Income : "+personalDetailsModel.getIncomeAmount()+" /"+personalDetailsModel.getIncomeType());
			} catch (Exception e) 
			{
				e.printStackTrace();
			}

			Table tableProfessionalHeader = new Table(1);
			tableProfessionalHeader.setWidthPercent(100);
			tableProfessionalHeader.addCell(headerProfessional);

			Table tableProfessionalDetails = new Table(1);
			tableProfessionalDetails.setWidthPercent(100);
			tableProfessionalDetails.addCell(professionalDetails);



			Paragraph familyDetails= new Paragraph();
			try {
				familyDetails.add("Family Prefrences : "+personalDetailsModel.getFamilyPreference()+"    Family Type : "+personalDetailsModel.getFamilyType()+
						"     Family Values : "+personalDetailsModel.getFamilyValues());
				
			} catch (Exception e)
			{
				e.printStackTrace();
			}

			Table tableFamilyHeader = new Table(1);
			tableFamilyHeader.setWidthPercent(100);
			tableFamilyHeader.addCell(headerFamily);

			Table tableFamilyDetails = new Table(1);
			tableFamilyDetails.setWidthPercent(100);
			tableFamilyDetails.addCell(familyDetails);


			Paragraph lifeStyleDetails= new Paragraph();
			try {
				
				lifeStyleDetails.add("Eating Habits : "+personalDetailsModel.getEatingHabits()+"    Drinking Habits : "+personalDetailsModel.getDrinkinghabits()+
						"    Smoking Habits : "+personalDetailsModel.getSmokingHabits());
			} catch (Exception e) 
			{
				e.printStackTrace();
			}

			Table tableLifeStyleHeaderTable = new Table(1);
			tableLifeStyleHeaderTable.setWidthPercent(100);
			tableLifeStyleHeaderTable.addCell(headerLifeStyle);

			Table tableLifeStyleDetails = new Table(1);
			tableLifeStyleDetails.setWidthPercent(100);
			tableLifeStyleDetails.addCell(lifeStyleDetails);
			
			
			Paragraph PartnerDetails = new Paragraph();
			try {
				PartnerDetails.add("Contry :  "+personalDetailsModel.getPartnerCountry()+"      State :  "+personalDetailsModel.getPartnerState()
				+"     City: "+personalDetailsModel.getDistrict()+"      City/District : "+personalDetailsModel.getPartnerDistrict()+
				"\n"+"Marital Status : "+personalDetailsModel.getPartnerMaritalStatus()+"     Physical Status : "+personalDetailsModel.getPartnerPhysicalStatus()+
				"     Complexion : "+personalDetailsModel.getPartnerComplexion()+"    Body Type : "+personalDetailsModel.getPartnerBodyType()+"\n"+
				" Religion : "+personalDetailsModel.getPartnerReligion()+"     Caste : "+personalDetailsModel.getPartnerCast()+"   Employee In : "+personalDetailsModel.getPartnerEmployeeIn()
				+"    Annual Income : "+personalDetailsModel.getPartnerAnualIncome()+"/"+personalDetailsModel.getPartnerIncomeType()+"\n"+"    family Class : "+personalDetailsModel.getPartnerFamilyClass()+"    Family Type : "+personalDetailsModel.getPartnerfamilyType()
				+"    Family values : "+personalDetailsModel.getPartnerFamilyValues()+"    Eating Habits : "+personalDetailsModel.getPartnerEatingHabit());
				
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
			Table tablePartnerPrefHeaderTable = new Table(1);
			tablePartnerPrefHeaderTable.setWidthPercent(100);
			tablePartnerPrefHeaderTable.addCell(headerPartnerPref);


			Table tablePartnerDetails = new Table(1);
			tablePartnerDetails.setWidthPercent(100);
			tablePartnerDetails.addCell(PartnerDetails);
			


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

			} catch (Exception e)
			{
				e.printStackTrace();
			}
			try {

				document.add(tableHeaderBasicInfo);
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
			
			try {
				
				document.add(tablebasicinfo);
			} catch (Exception e)
			{
				e.printStackTrace();
			}
			try {
				
				document.add(tableLocationtitle);
			} catch (Exception e)
			{
				e.printStackTrace();
			}
			try {
				
				document.add(tablePersonalDetails);
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
			try {
				
				document.add(tableReligonTitle);
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
			try {
				
				document.add(tablereligionDetails);
			} catch (Exception e)
			{
				e.printStackTrace();
			}
			try {
				
				document.add(tableProfessionalHeader);
			} catch (Exception e)
			{
				e.printStackTrace();
			}
			try {
				
				document.add(tableProfessionalDetails);
			} catch (Exception e)
			{
				e.printStackTrace();
			}
			try {
				
				document.add(tableFamilyHeader);
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
			try {
				
				document.add(tableFamilyDetails);
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
			try {
				
				document.add(tableLifeStyleHeaderTable);
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
			try {
				
				document.add(tableLifeStyleDetails);
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
			try {
				
				document.add(tablePartnerPrefHeaderTable);
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
			try {
				
				document.add(tablePartnerDetails);
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
             
			try {
				
				pdfDoc.close();
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

		}catch(Exception e) {
			e.printStackTrace();
		}

		return "success";
	}


}
