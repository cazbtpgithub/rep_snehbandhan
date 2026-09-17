package com.xanadutec.coreflex.DealerReport;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;
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
import com.itextpdf.kernel.color.WebColors;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.Style;
import com.itextpdf.layout.border.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.xanadutec.coreflex.dealer.DealerService;
import com.xanadutec.coreflex.model.AdminUserModel;
import com.xanadutec.coreflex.model.Dealer;
import com.xanadutec.coreflex.model.Kit_Upgrade_Or_Reg_History_Details;
import com.xanadutec.coreflex.model.Package;
import com.xanadutec.coreflex.model.UserModel;
import com.xanadutec.coreflex.packageAndKit.PackageAndKitService;
import com.xanadutec.coreflex.revenue.RevenueService;
import com.xanadutec.coreflex.userTask.UserService;

@Controller
public class DealerReportController {

	@Autowired
	DealerReportService dealerReportService;
	@Autowired
	DealerService dealerService;
	@Autowired
	PackageAndKitService packageandkitService;
	@Autowired
	RevenueService revService;
	@Autowired
	UserService userService;
	@Autowired
	SessionFactory sessionFactory;

	@RequestMapping(value = "/dealerReport", method = RequestMethod.GET)
	public ModelAndView dealerReportUrl(ModelMap model, HttpServletRequest request, HttpServletResponse response)

	{

		List<Package> packageList = packageandkitService.getListOfPackage();
		model.addAttribute("packageList", packageList);

		List<Dealer> list = dealerService.getDealerList();
		model.addAttribute("DealerList", list);
		
		HttpSession httpSession = request.getSession();
		UserModel userModel = (UserModel) httpSession.getAttribute("ActUserId");

		if (userModel.getUserRoll().getId() == 4) 
		{

			model.addAttribute("loginUserRole", "Employee");
		} else 
		{
			model.addAttribute("loginUserRole", "Admin");
		}
		
		
		
		return new ModelAndView("DealerReport");
	}

	
	@RequestMapping(value = "/subDealerReport", method = RequestMethod.GET)
	public ModelAndView subDealerReport(ModelMap model, HttpServletRequest request, HttpServletResponse response)

	{

		List<Package> packageList = packageandkitService.getListOfPackage();
		model.addAttribute("packageList", packageList);
		HttpSession httpSession = request.getSession();
		UserModel adminUser=(UserModel) httpSession.getAttribute("ActUserId");
		List<Dealer> list=dealerService.getSubDealerByMainDealer(adminUser);
		model.addAttribute("DealerList", list);
		return new ModelAndView("SubDealerReport");
	}
	
	@RequestMapping(value = "/printDealerReport", method = RequestMethod.POST)
	@ResponseBody
	public String dealerReportDetails(HttpServletRequest request, HttpServletResponse response) 
	{
		final String FILE_NAME = "DealerReport.pdf";
		JSONObject jsonObject;
		String stringFromDate = null;
		String stringToDate = null;
		Date fromDate = null;
		Date toDate = null;
		
		//String headerFromDate = null;
		try {
			jsonObject = new JSONObject(request.getParameter("jsonObjectData"));
			final JSONObject jsonObj = jsonObject;
			stringFromDate = jsonObj.getString("fromDate");
			stringToDate = jsonObj.getString("toDate");
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		

		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		try {
			fromDate = format.parse(stringFromDate);
			toDate = format.parse(stringToDate);
		} catch (ParseException e) {

			e.printStackTrace();
		}
		
	
		// headerFromDate = ""+fromDate.getDay()+fromDate.getMonth()+fromDate.getYear();
		
	
		try {

			String pathact = null;
			try {
				 pathact = request.getServletContext().getRealPath("/resources/snehReport");
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			File dir1 = new File(pathact);
			if (!dir1.exists()) {
				dir1.mkdirs();
			}

			String filePath = request.getServletContext().getRealPath("/resources/snehReport/" + FILE_NAME);
			PdfDocument pdfDoc = new PdfDocument(new PdfWriter(filePath));
			pdfDoc.setDefaultPageSize(PageSize.A4.rotate());
			Document document = new Document(pdfDoc);

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

			Table dealerDetailsTable = new Table(13);

			
			 PdfFont font = PdfFontFactory.createFont(FontConstants.HELVETICA_BOLD);
			 PdfFont fontHeader = PdfFontFactory.createFont(FontConstants.TIMES_ROMAN);
	
			Cell headerFirst =null;
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
				
				headerThird= new Cell().add(new Paragraph("Username").setFont(font).setFontColor(Color.WHITE));
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
			
			Cell headerSix  = null;
			try {
				
				headerSix= new Cell().add(new Paragraph("Email Id").setFont(font).setFontColor(Color.WHITE));
				headerSix.setBackgroundColor(Color.BLACK);
				headerSix.setBorder(new SolidBorder(Color.WHITE,1f));
				
			} catch (Exception e)
			{
				e.printStackTrace();
			}
			
			Cell headerSeven = null;
			try {
				headerSeven= new Cell().add(new Paragraph("Branch Name").setFont(font).setFontColor(Color.WHITE));
				headerSeven.setBackgroundColor(Color.BLACK);
				headerSeven.setBorder(new SolidBorder(Color.WHITE,1f));
				
			} catch (Exception e)
			{
				e.printStackTrace();
			}
			
			Cell headerEight = null;
			try {
				
				headerEight= new Cell().add(new Paragraph("Dealer Code").setFont(font).setFontColor(Color.WHITE));
				headerEight.setBackgroundColor(Color.BLACK);
				headerEight.setBorder(new SolidBorder(Color.WHITE,1f));
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
			
			Cell headerNine = null;
			try {
				
				headerNine= new Cell().add(new Paragraph("Main DealerName").setFont(font).setFontColor(Color.WHITE));
				headerNine.setBackgroundColor(Color.BLACK);
				headerNine.setBorder(new SolidBorder(Color.WHITE,1f));
				
			} catch (Exception e)
			{
				e.printStackTrace();
			}
			
			Cell headerTen = null;
			try {
				headerTen= new Cell().add(new Paragraph("Address").setFont(font).setFontColor(Color.WHITE));
				headerTen.setBackgroundColor(Color.BLACK);
				headerTen.setBorder(new SolidBorder(Color.WHITE,1f));
				
				
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
			
			Cell headerEleven = null;
			try {
				headerEleven= new Cell().add(new Paragraph("Pincode").setFont(font).setFontColor(Color.WHITE));
				headerEleven.setBackgroundColor(Color.BLACK);
				headerEleven.setBorder(new SolidBorder(Color.WHITE,1f));
				
				
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
			Cell headerTweve = null;
			try {
				headerTweve= new Cell().add(new Paragraph("Discount(%)").setFont(font).setFontColor(Color.WHITE));
				headerTweve.setBackgroundColor(Color.BLACK);
				headerTweve.setBorder(new SolidBorder(Color.WHITE,1f));
				
				
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
			dealerDetailsTable.addCell(headerFirst);
			dealerDetailsTable.addCell(headerSecond);
			dealerDetailsTable.addCell(headerThird);
			dealerDetailsTable.addCell(headerFourth);
			dealerDetailsTable.addCell(headerRegDate);
			dealerDetailsTable.addCell(headerFive);
			dealerDetailsTable.addCell(headerSix);
			dealerDetailsTable.addCell(headerSeven);
			dealerDetailsTable.addCell(headerEight);
			dealerDetailsTable.addCell(headerNine);
			dealerDetailsTable.addCell(headerTen);
			dealerDetailsTable.addCell(headerEleven);
			dealerDetailsTable.addCell(headerTweve);
			
		
			int serialNo = 1;

			List<List<DealerReportModel>> dealerReportData = new ArrayList<>();
			List<DealerReportModel> dealerList = dealerReportService.getDealerDetailsByDate(fromDate, toDate);
			dealerReportData.add(dealerList);
			List<List<DealerReportModel>> dealerDataSet = dealerReportData;

			for (List<DealerReportModel> record : dealerDataSet) {
				for (DealerReportModel currentData : record) 
				{
                   
					try {
						
						dealerDetailsTable.addCell(new Cell().add(new Paragraph(Integer.toString(serialNo))));
					} catch (Exception e)
					{
						e.printStackTrace();
					}
					try {
						
						dealerDetailsTable.addCell(new Cell().add(new Paragraph(currentData.getFullName())));
					} catch (Exception e) 
					{
						e.printStackTrace();
					}
					try {
						
						dealerDetailsTable.addCell(new Cell().add(new Paragraph(currentData.getUsername())));
					} catch (Exception e) 
					{
						e.printStackTrace();
					}
					try {
						
						dealerDetailsTable.addCell(new Cell().add(new Paragraph(currentData.getPassword())));
					} catch (Exception e)
					{
						e.printStackTrace();
					}
					try {
						String dealerRegistrationDate = null;
						try {
							String registrationDate = ""+currentData.getRegDate();			
							String[] registrationDateSplit = registrationDate.split("-");
							String registrationDay = registrationDateSplit[2];
							String [] spliteRegistrationDay = registrationDay.split(" ");				
							String todayDate1 = spliteRegistrationDay[0];				
							dealerRegistrationDate =   todayDate1 + "-" + registrationDateSplit[1] + "-" + registrationDateSplit[0];
						} catch (Exception e) 
						{
							e.printStackTrace();
						}
						
						dealerDetailsTable.addCell(new Cell().add(new Paragraph(dealerRegistrationDate)));
						
					} catch (Exception e)
					{
						e.printStackTrace();
					}
					try {
						
						dealerDetailsTable.addCell(new Cell().add(new Paragraph(currentData.getContactNo())));
					} catch (Exception e)
					{
						e.printStackTrace();
					}
					try {
						
						dealerDetailsTable.addCell(new Cell().add(new Paragraph(currentData.getEmail() == null ? "" : "" + currentData.getEmail())));
					} catch (Exception e)
					{
						e.printStackTrace();
					}
					try {
						
						dealerDetailsTable.addCell(new Cell().add(new Paragraph(currentData.getBranch() == null ? 
								"" : "" + currentData.getBranch())));
					} catch (Exception e) 
					{
						e.printStackTrace();
					}
					try {
						
						dealerDetailsTable.addCell(new Cell().add(new Paragraph(currentData.getDealerCode())));
					} catch (Exception e)
					{
						e.printStackTrace();
					}
					try {
						
						dealerDetailsTable.addCell(new Cell().add(new Paragraph(currentData.getMainDealer())));
					} catch (Exception e)
					{
						e.printStackTrace();
					}
					try {
						
						dealerDetailsTable.addCell(new Cell().add(new Paragraph(currentData.getAddress() == null ?
								"" : "" + currentData.getAddress())));
					} catch (Exception e) 
					{
						e.printStackTrace();
					}
					try {
						
						dealerDetailsTable.addCell(new Cell()
								.add(new Paragraph(currentData.getPincode() == null ? "" : "" + currentData.getAddress())));
					} catch (Exception e) 
					{
						e.printStackTrace();
					}
					try {
						
						dealerDetailsTable.addCell(new Cell().add(new Paragraph(Double.toString(currentData.getDiscount()) == null ? "" : "" + Double.toString(currentData.getDiscount()))));
					} catch (Exception e)
					{
						e.printStackTrace();
					}

					serialNo++;

				}
			}
			
			Paragraph header = new Paragraph("DEALER DETAILS");
			header.setFontSize(18f);
			header.setTextAlignment(TextAlignment.CENTER);
			header.setFont(fontHeader);
			
			List<String> reportCriteriaDateFormateConvert = reportCriteriaDateFormateConvert(stringFromDate,stringToDate);
		    Paragraph date = new Paragraph("Date : " +reportCriteriaDateFormateConvert.get(0)  + "  To,  " + reportCriteriaDateFormateConvert.get(1));
			date.setTextAlignment(TextAlignment.LEFT);
			try {
			
				document.add(image);
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
			try {
				
				document.add(header);
				document.add(date);
			} catch (Exception e)
			{
				e.printStackTrace();
			}
			try {
				
				document.add(dealerDetailsTable);
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
	
	
	
	private List<String> reportCriteriaDateFormateConvert(String stringFromDate, String stringToDate) 
	{
		List<String> changeFormateDateList = new ArrayList<>();
		String fromDateSplite[] = stringFromDate.split("/");
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


	@RequestMapping(value = "/printSubDealerReport", method = RequestMethod.POST)
	@ResponseBody
	public String printSubDealerReport(HttpServletRequest request, HttpServletResponse response) 
	{

		final String FILE_NAME = "SubDealerReport.pdf";
		JSONObject obj;
		JSONObject js = new JSONObject();
		String fromDt = null;
		String toDt = null;
		Date fromDate = null;
		Date toDate = null;
		try {
			obj = new JSONObject(request.getParameter("jsonObjectData"));
			final JSONObject jsonObj = obj;
			fromDt = jsonObj.getString("fromDate");
			toDt = jsonObj.getString("toDate");
		} catch (Exception e) {
			e.printStackTrace();
		}
		HttpSession httpSession = request.getSession();
		UserModel adminUser=(UserModel) httpSession.getAttribute("ActUserId");

		SimpleDateFormat format = new SimpleDateFormat("dd/mm/yyyy");
		try {
			fromDate = format.parse(fromDt);
			toDate = format.parse(toDt);
		} catch (ParseException e) {

			e.printStackTrace();
		}
		try {

			//final String FILE_NAME = "SubDealerReport.pdf";
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

			Document document = new Document(pdfDoc);

			document.setFontSize(7);

			// Creating an ImageData object

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
		
			Table dealerDetailsTable = new Table(12);

			
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
			Cell headerSecond  = null;
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
				headerThird= new Cell().add(new Paragraph("Username").setFont(font).setFontColor(Color.WHITE));
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
				headerSix= new Cell().add(new Paragraph("Email Id").setFont(font).setFontColor(Color.WHITE));
				headerSix.setBackgroundColor(Color.BLACK);
				headerSix.setBorder(new SolidBorder(Color.WHITE,1f));
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
			Cell headerSeven = null;
			try {
				
				headerSeven= new Cell().add(new Paragraph("Branch Name").setFont(font).setFontColor(Color.WHITE));
				headerSeven.setBackgroundColor(Color.BLACK);
				headerSeven.setBorder(new SolidBorder(Color.WHITE,1f));
				
			} catch (Exception e)
			{
				e.printStackTrace();
			}
			Cell headerEight = null;
			try {
				headerEight= new Cell().add(new Paragraph("Dealer Code").setFont(font).setFontColor(Color.WHITE));
				headerEight.setBackgroundColor(Color.BLACK);
				headerEight.setBorder(new SolidBorder(Color.WHITE,1f));	
			} catch (Exception e)
			{
				e.printStackTrace();
			}
			Cell headerNine = null;
			try {
				
				headerNine= new Cell().add(new Paragraph("Address").setFont(font).setFontColor(Color.WHITE));
				headerNine.setBackgroundColor(Color.BLACK);
				headerNine.setBorder(new SolidBorder(Color.WHITE,1f));
				
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
			Cell headerTen = null;
			try {
				
				headerTen= new Cell().add(new Paragraph("Pincode").setFont(font).setFontColor(Color.WHITE));
				headerTen.setBackgroundColor(Color.BLACK);
				headerTen.setBorder(new SolidBorder(Color.WHITE,1f));
				
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
			Cell headerEleven = null;
			try {
				headerEleven= new Cell().add(new Paragraph("Discount(%)").setFont(font).setFontColor(Color.WHITE));
				headerEleven.setBackgroundColor(Color.BLACK);
				headerEleven.setBorder(new SolidBorder(Color.WHITE,1f));				
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
		
			dealerDetailsTable.addCell(headerFirst);
			dealerDetailsTable.addCell(headerSecond);
			dealerDetailsTable.addCell(headerThird);
			dealerDetailsTable.addCell(headerFourth);
			dealerDetailsTable.addCell(headerRegDate);
			dealerDetailsTable.addCell(headerFive);
			dealerDetailsTable.addCell(headerSix);
			dealerDetailsTable.addCell(headerSeven);
			dealerDetailsTable.addCell(headerEight);
			dealerDetailsTable.addCell(headerNine);
			dealerDetailsTable.addCell(headerTen);
			dealerDetailsTable.addCell(headerEleven);
		
			int serialNo = 1;

			List<List<DealerReportModel>> dealerReportData = new ArrayList<>();
			List<DealerReportModel> dealerList = dealerReportService.getSubDealerDetailsByDate(fromDate, toDate,adminUser);
			dealerReportData.add(dealerList);
			List<List<DealerReportModel>> dealerDataSet = dealerReportData;

			for (List<DealerReportModel> record : dealerDataSet) {
				for (DealerReportModel currentData : record) {
                
					try {
						
						dealerDetailsTable.addCell(new Cell().add(new Paragraph(Integer.toString(serialNo))));
					} catch (Exception e)
					{
						e.printStackTrace();
					}
					try {
						
						dealerDetailsTable.addCell(new Cell().add(new Paragraph(currentData.getFullName())));
					} catch (Exception e)
					{
						e.printStackTrace();
					}
					try {
						
						dealerDetailsTable.addCell(new Cell().add(new Paragraph(currentData.getUsername())));
					} catch (Exception e)
					{
						e.printStackTrace();
					}
					try {
						
						dealerDetailsTable.addCell(new Cell().add(new Paragraph(currentData.getPassword())));
					} catch (Exception e)
					{
						e.printStackTrace();
					}
					try {
						
						dealerDetailsTable.addCell(new Cell().add(new Paragraph(currentData.getRegDate().toString())));
					} catch (Exception e)
					{
						e.printStackTrace();
					}
					try {
						
						dealerDetailsTable.addCell(new Cell().add(new Paragraph(currentData.getContactNo())));
					} catch (Exception e)
					{
						e.printStackTrace();
					}
					try {
						
						dealerDetailsTable.addCell(new Cell()
								.add(new Paragraph(currentData.getEmail() == null ? "" : "" + currentData.getEmail())));
					} catch (Exception e) 
					{
						e.printStackTrace();
					}
					try {
						dealerDetailsTable.addCell(new Cell()
								.add(new Paragraph(currentData.getBranch() == null ? "" : "" + currentData.getBranch())));
						
					} catch (Exception e) 
					{
						e.printStackTrace();
					}
					try {
						dealerDetailsTable.addCell(new Cell().add(new Paragraph(currentData.getDealerCode())));
						
					} catch (Exception e) 
					{
						e.printStackTrace();
					}
					try {
						
						dealerDetailsTable.addCell(new Cell()
								.add(new Paragraph(currentData.getAddress() == null ? "" : "" + currentData.getAddress())));
					} catch (Exception e) 
					{
						e.printStackTrace();
					}
					try {
						
						dealerDetailsTable.addCell(new Cell()
								.add(new Paragraph(currentData.getPincode() == null ? "" : "" + currentData.getAddress())));
					} catch (Exception e)
					{
						e.printStackTrace();
					}
					try {
						
						dealerDetailsTable
						.addCell(new Cell().add(new Paragraph(Double.toString(currentData.getDiscount()) == null
						? "" : "" + Double.toString(currentData.getDiscount()))));
					} catch (Exception e)
					{
						e.printStackTrace();
					}

					serialNo++;

				}
			}
			Paragraph header = new Paragraph("SUB DEALER DETAILS");
			header.setFontSize(18f);
			header.setTextAlignment(TextAlignment.CENTER);
			header.setFont(fontHeader);
			Paragraph date = new Paragraph("Date : " + fromDt + "  To,  " + toDt);
			date.setTextAlignment(TextAlignment.LEFT);
			
			try {
				
				document.add(image);
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
			try {
				document.add(date);
				document.add(header);
			} catch (Exception e)
			{
				e.printStackTrace();
			}
			try {
				
				document.add(dealerDetailsTable);
			} catch (Exception e) 
			{
				e.printStackTrace();
			}

			document.close();
			return FILE_NAME;

		} catch (Exception e) {
			e.printStackTrace();

		}
		return FILE_NAME;
	}
	
	

	@RequestMapping(value = "/printDealerRevenueReport", method = RequestMethod.POST)
	@ResponseBody
	public String dealerRevenueReportDetails(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("packageId") String packageId, @RequestParam("dealerId") String dealerId) 
	{
 
		Dealer dealer =null;
		try {

			dealer = dealerService.getDealerById(dealerId);
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		List<Date> dateList = null;
		try {
			
			 dateList = revService.getUniqueDateList();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
		Package packageObj=null;
		HashMap<Date, List<Kit_Upgrade_Or_Reg_History_Details>> revMap = null;
		
		try {
			
			 revMap = new HashMap<>();
			Session session = sessionFactory.openSession();
			
			if (packageId.equalsIgnoreCase("All")) {
				for (Date date : dateList) {
					revMap.put(date, revService.getListOfRegHistoryByDateAndDealer(date, dealer));
				}
				
			} else {
				
				Package pack = (Package) session.get(Package.class, Integer.parseInt(packageId));
				
				packageObj=pack;
				for (Date date : dateList) {
					
					revMap.put(date, revService.getListOfRegHistoryByDateAndPackageAndDealer(date, pack, dealer));
					
				}
				
			}
		} catch (Exception e) 
		{
			e.printStackTrace();
		}

		List<DealerReportModel> dealerReportData = new ArrayList<>();
		List<List<DealerReportModel>> dealerReportDataSet = new ArrayList<>();

		Double totalAmount = 0.0;

		for (Date key : revMap.keySet()) {
			List<Kit_Upgrade_Or_Reg_History_Details> value = revMap.get(key);

			for (Kit_Upgrade_Or_Reg_History_Details kit_Upgrade_Or_Reg_History_Details : value) {

				try {

					if (kit_Upgrade_Or_Reg_History_Details != null) {
						DealerReportModel model = new DealerReportModel();

						model.setKituserName(kit_Upgrade_Or_Reg_History_Details.getKit_id().getKit_username());
						model.setRevenueDate(key);
						model.setPrice(kit_Upgrade_Or_Reg_History_Details.getPayment());
						dealerReportData.add(model);
						totalAmount += kit_Upgrade_Or_Reg_History_Details.getPayment();

						
					}

				} catch (Exception e) {
				}

			}
			
			
			
		}

		dealerReportDataSet.add(dealerReportData);
		final String FILE_NAME = "DealerRevenueReport.pdf";
	
		try {
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

			Document document = new Document(pdfDoc);
			document.setFontSize(9);

			// Creating an ImageData object
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

			// Creating an Imageobject
			
			Image image = null;
		
			try {
				image = new Image(data);
			} catch (Exception e)
			{
				e.printStackTrace();
			}
			Table dealerDetailsKitTable = new Table(4);

			
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
				headerThird= new Cell().add(new Paragraph("Date").setFont(font).setFontColor(Color.WHITE));
				headerThird.setBackgroundColor(Color.BLACK);
				headerThird.setBorder(new SolidBorder(Color.WHITE,1f));
				
			} catch (Exception e)
			{
				e.printStackTrace();
			}
			
			Cell headerFourth = null;
			try {
				headerFourth= new Cell().add(new Paragraph("Price").setFont(font).setFontColor(Color.WHITE));
				headerFourth.setBackgroundColor(Color.BLACK);
				headerFourth.setBorder(new SolidBorder(Color.WHITE,1f));
				
				
			} catch (Exception e)
			{
				e.printStackTrace();
			}
			dealerDetailsKitTable.addHeaderCell(headerFirst);
			dealerDetailsKitTable.addHeaderCell(headerSecond);
			dealerDetailsKitTable.addHeaderCell(headerThird);
			dealerDetailsKitTable.addHeaderCell(headerFourth);
			
			int serialNo = 1;
			String dealerName = dealer.getFullName();
			
			
			List<List<DealerReportModel>> dealerDataSet = dealerReportDataSet;

			for (List<DealerReportModel> record : dealerDataSet) {
				for (DealerReportModel currentData : record) 
				{
					try {

						dealerDetailsKitTable.addCell(new Cell().add(new Paragraph(Integer.toString(serialNo))));
					} catch (Exception e) 
					{
						e.printStackTrace();
					}
					try {
						dealerDetailsKitTable.addCell(new Cell().add(new Paragraph(currentData.getKituserName())));

					} catch (Exception e) 
					{
						e.printStackTrace();
					}
					try {

						dealerDetailsKitTable
						.addCell(new Cell().add(new Paragraph(currentData.getRevenueDate().toString())));
					} catch (Exception e) 
					{
						e.printStackTrace();
					}
					try {

						dealerDetailsKitTable
						.addCell(new Cell().add(new Paragraph(Double.toString(currentData.getPrice()))));
					} catch (Exception e) 
					{
						e.printStackTrace();
					}

					serialNo++;

				}
			
			}
			
			
			
			
			dealerDetailsKitTable.addCell(new Cell().add(""));
			dealerDetailsKitTable.addCell(new Cell().add(new Paragraph("")));
			Cell totalRevenue = null;
			try {
				
				totalRevenue =new Cell().add(new Paragraph("Total").setFont(font).setFontColor(Color.WHITE));
				totalRevenue.setBorder(new SolidBorder(Color.WHITE,1f));
				totalRevenue.setBackgroundColor(Color.BLACK);
				dealerDetailsKitTable.addCell(totalRevenue);
				dealerDetailsKitTable.addCell(new Cell().add(new Paragraph(Double.toString(totalAmount))));
			} catch (Exception e) 
			{
				e.printStackTrace();
			}

			
			Paragraph dealerNameValue = new Paragraph("Dealer Name :  "+dealerName);
			
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
		
			
			Paragraph header = new Paragraph("DEALER REVENUE DETAILS");
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
				
				document.add(packageNameValue);
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
			try {
				
				document.add(dealerDetailsKitTable);
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
	
	
	
	
	@RequestMapping(value = "/printSubDealerRevenueReport", method = RequestMethod.POST)
	@ResponseBody
	public String printSubDealerRevenueReport(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("packageId") String packageId, @RequestParam("dealerId") String dealerId) {

	 
		Dealer dealer = dealerService.getDealerById(dealerId);

		List<Date> dateList = revService.getUniqueDateList();
		
		Package packageObj=null;
		
		HashMap<Date, List<Kit_Upgrade_Or_Reg_History_Details>> revMap = new HashMap<>();
		Session session = sessionFactory.openSession();

		if (packageId.equalsIgnoreCase("All")) {
			for (Date date : dateList) {
				revMap.put(date, revService.getListOfRegHistoryByDateAndDealer(date, dealer));
			}

		} else {

			Package pack = (Package) session.get(Package.class, Integer.parseInt(packageId));

			packageObj=pack;
			for (Date date : dateList) {

				revMap.put(date, revService.getListOfRegHistoryByDateAndPackageAndDealer(date, pack, dealer));

			}

		}

		List<DealerReportModel> dealerReportData = new ArrayList<>();
		List<List<DealerReportModel>> dealerReportDataSet = new ArrayList<>();

		Double totalAmount = 0.0;

		for (Date key : revMap.keySet()) {
			List<Kit_Upgrade_Or_Reg_History_Details> value = revMap.get(key);

			for (Kit_Upgrade_Or_Reg_History_Details kit_Upgrade_Or_Reg_History_Details : value) {

				try {

					if (kit_Upgrade_Or_Reg_History_Details != null) {
						DealerReportModel model = new DealerReportModel();

						model.setKituserName(kit_Upgrade_Or_Reg_History_Details.getKit_id().getKit_username());
						model.setRevenueDate(key);
						model.setPrice(kit_Upgrade_Or_Reg_History_Details.getPayment());
						dealerReportData.add(model);
						totalAmount += kit_Upgrade_Or_Reg_History_Details.getPayment();

						
					}

				} catch (Exception e) {
				}

			}
			
			
			
		}

		dealerReportDataSet.add(dealerReportData);
		final String FILE_NAME = "SubDealerRevenueReport.pdf";

		try {
			String pathact = null;
			try {
				
			 pathact = request.getServletContext().getRealPath("/resources/snehReport");
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
			File dir1  = null;
			try {
				 dir1 = new File(pathact);
				
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
			if (!dir1.exists()) {
				dir1.mkdirs();
			}
			String filePath  = null;
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

			// Creating an ImageData object
			ImageData data = null;
			String imFile = null;
			try {
				imFile = request.getServletContext().getRealPath("/resources/images/reportHeader.png");
				
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
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
		
			Table dealerDetailsKitTable = new Table(4);

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
				headerThird= new Cell().add(new Paragraph("Date").setFont(font).setFontColor(Color.WHITE));
				headerThird.setBackgroundColor(Color.BLACK);
				headerThird.setBorder(new SolidBorder(Color.WHITE,1f));
				
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
			
			Cell headerFourth = null;
			try {
				headerFourth= new Cell().add(new Paragraph("Price").setFont(font).setFontColor(Color.WHITE));
				headerFourth.setBackgroundColor(Color.BLACK);
				headerFourth.setBorder(new SolidBorder(Color.WHITE,1f));	
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
			dealerDetailsKitTable.addHeaderCell(headerFirst);
			dealerDetailsKitTable.addHeaderCell(headerSecond);
			dealerDetailsKitTable.addHeaderCell(headerThird);
			dealerDetailsKitTable.addHeaderCell(headerFourth);
			int serialNo = 1;
			String dealerName = dealer.getFullName();
			
			
			List<List<DealerReportModel>> dealerDataSet = dealerReportDataSet;

			for (List<DealerReportModel> record : dealerDataSet) {
				for (DealerReportModel currentData : record) 
				{
                   try {
					
                	   dealerDetailsKitTable.addCell(new Cell().add(new Paragraph(Integer.toString(serialNo))));
				} catch (Exception e) 
                   {
					e.printStackTrace();
				}
                   try {
					
                	   dealerDetailsKitTable.addCell(new Cell().add(new Paragraph(currentData.getKituserName())));
				} catch (Exception e)
                   {
					e.printStackTrace();
				}
					try {
						
						dealerDetailsKitTable
						.addCell(new Cell().add(new Paragraph(currentData.getRevenueDate().toString())));
					} catch (Exception e) 
					{
						e.printStackTrace();
					}
					try {
						
						dealerDetailsKitTable
						.addCell(new Cell().add(new Paragraph(Double.toString(currentData.getPrice()))));
					} catch (Exception e)
					{
						e.printStackTrace();
					}

					serialNo++;

				}
			
			}
			
			
			
			
			dealerDetailsKitTable.addCell(new Cell().add(""));
			dealerDetailsKitTable.addCell(new Cell().add(new Paragraph("")));
			
	
			Cell totalRevenue = null;
			try {
				totalRevenue =new Cell().add(new Paragraph("Total").setFont(font).setFontColor(Color.WHITE));
				totalRevenue.setBorder(new SolidBorder(Color.WHITE,1f));
				totalRevenue.setBackgroundColor(Color.BLACK);
				dealerDetailsKitTable.addCell(totalRevenue);
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
			try {
				
				dealerDetailsKitTable.addCell(new Cell().add(new Paragraph(Double.toString(totalAmount))));
			} catch (Exception e) 
			{
				e.printStackTrace();
			}

			Paragraph dealerNameValue = new Paragraph("Dealer Name :  "+dealerName);
			
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
		
			
			Paragraph header = new Paragraph(" SUB DELEAR REVENUE DETAILS");
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

				document.add(packageNameValue);
			} catch (Exception e)
			{
				e.printStackTrace();
			}
			try {

				document.add(dealerDetailsKitTable);
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
	
	
	

	@RequestMapping(value = "/printDealerKitReport", method = RequestMethod.POST)
	@ResponseBody
	public String dealerKitReportDetails(
			@RequestParam ("dealerId") String dealerId,
			HttpServletRequest request, HttpServletResponse response
			) {
	
		try {

			final String FILE_NAME = "DealerKitReport.pdf";
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

			Document document = new Document(pdfDoc);

			document.setMargins(10.0f, 10.0f, 20.0f, 20.0f);
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
			Image image =null;
			try {
				
			image = new Image(data);
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
		
			Table dealerDetailsKitTable = new Table(7);
			
			 PdfFont font = PdfFontFactory.createFont(FontConstants.HELVETICA_BOLD);
			 PdfFont fontHeader = PdfFontFactory.createFont(FontConstants.TIMES_ROMAN);

			Cell headerFirst =null;
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
				headerThird= new Cell().add(new Paragraph("Package Name").setFont(font).setFontColor(Color.WHITE));
				headerThird.setBackgroundColor(Color.BLACK);
				headerThird.setBorder(new SolidBorder(Color.WHITE,1f));
				
			} catch (Exception e)
			{
				e.printStackTrace();
			}
			
			Cell headerFourth = null;
			try {
				headerFourth= new Cell().add(new Paragraph("Total Kit").setFont(font).setFontColor(Color.WHITE));
				headerFourth.setBackgroundColor(Color.BLACK);
				headerFourth.setBorder(new SolidBorder(Color.WHITE,1f));
				
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
			
			Cell headerFive = null;
			try {
				headerFive= new Cell().add(new Paragraph("Used Kit").setFont(font).setFontColor(Color.WHITE));
				headerFive.setBackgroundColor(Color.BLACK);
				headerFive.setBorder(new SolidBorder(Color.WHITE,1f));
				
				
			} catch (Exception e)
			{
				e.printStackTrace();
			}
			Cell headerSix = null;
			try {
				headerSix= new Cell().add(new Paragraph("Return Kit").setFont(font).setFontColor(Color.WHITE));
				headerSix.setBackgroundColor(Color.BLACK);
				headerSix.setBorder(new SolidBorder(Color.WHITE,1f));
				
				
			} catch (Exception e)
			{
				e.printStackTrace();
			}
			Cell headerSeven = null;
			try {
				headerSeven= new Cell().add(new Paragraph("Remaining Kit").setFont(font).setFontColor(Color.WHITE));
				headerSeven.setBackgroundColor(Color.BLACK);
				headerSeven.setBorder(new SolidBorder(Color.WHITE,1f));
				
				
			} catch (Exception e)
			{
				e.printStackTrace();
			}
			dealerDetailsKitTable.addCell(headerFirst);
			dealerDetailsKitTable.addCell(headerSecond);
			dealerDetailsKitTable.addCell(headerThird);
			dealerDetailsKitTable.addCell(headerFourth);
			dealerDetailsKitTable.addCell(headerFive);
			dealerDetailsKitTable.addCell(headerSix);
			dealerDetailsKitTable.addCell(headerSeven);
			
			
			

			int serialNo = 1;
			Dealer dealer = dealerService.getDealerById(dealerId);
			
			List<List<DealerReportModel>> dealerReportData = new ArrayList<>();
			List<DealerReportModel> dealerList = dealerReportService
					.getDealerKitDetails(Integer.parseInt(dealerId));

			dealerReportData.add(dealerList);
			List<List<DealerReportModel>> dealerDataSet = dealerReportData;

			for (List<DealerReportModel> record : dealerDataSet) {
				for (DealerReportModel currentData : record) 
				{
                  
					try {
						dealerDetailsKitTable.addCell(new Cell().add(new Paragraph(Integer.toString(serialNo))));
						
					} catch (Exception e)
					{
						e.printStackTrace();
					}
					try {
						
						dealerDetailsKitTable.addCell(new Cell().add(new Paragraph(currentData.getFullName())));
					} catch (Exception e) 
					{
						e.printStackTrace();
					}
					try {
						
						dealerDetailsKitTable.addCell(new Cell().add(new Paragraph(currentData.getPackageName())));
					} catch (Exception e) 
					{
						e.printStackTrace();
					}
					try {
						
						dealerDetailsKitTable
						.addCell(new Cell().add(new Paragraph(Integer.toString(currentData.getTotalKit()))));
					} catch (Exception e) 
					{
						e.printStackTrace();
					}
					try {
						dealerDetailsKitTable
						.addCell(new Cell().add(new Paragraph(Integer.toString(currentData.getUsedKit()))));
						
					} catch (Exception e)
					{
						e.printStackTrace();
					}
					try {
						
						dealerDetailsKitTable
						.addCell(new Cell().add(new Paragraph(Integer.toString(currentData.getReturnKit()))));
					} catch (Exception e) 
					{
						e.printStackTrace();
					}
					try {
						dealerDetailsKitTable
						.addCell(new Cell().add(new Paragraph(Integer.toString(currentData.getRemainingKit()))));
						
					} catch (Exception e)
					{
						e.printStackTrace();
					}

					serialNo++;

				}
			}
			Paragraph header = new Paragraph("DEALER KIT DETAILS");
			Paragraph dealerName = new Paragraph("Dealer Name : "+dealer.getFullName() + "  ("+dealer.getDealecode()+")");
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
				
				document.add(dealerName);
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
			try {
				
				document.add(dealerDetailsKitTable);
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
		return "Fail";
	}
	
	
	@RequestMapping(value = "/printSubDealerKitReport", method = RequestMethod.POST)
	@ResponseBody
	public String printSubDealerKitReport(
			@RequestParam ("dealerId") String dealerId,
			HttpServletRequest request, HttpServletResponse response
			) {
	
		try {

			final String FILE_NAME = "SubDealerKitReport.pdf";
			String pathact =null;
			try {
				
			pathact = request.getServletContext().getRealPath("/resources/snehReport");
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
			File dir1 = new File(pathact);
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

			Document document = new Document(pdfDoc);

			document.setMargins(10.0f, 10.0f, 20.0f, 20.0f);
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
		
			Table dealerDetailsKitTable = new Table(7);

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
				
				headerSecond= new Cell().add(new Paragraph("Full Name").setFont(font).setFontColor(Color.WHITE));
				headerSecond.setBackgroundColor(Color.BLACK);
				headerSecond.setBorder(new SolidBorder(Color.WHITE,1f));
				
			} catch (Exception e)
			{
				e.printStackTrace();
			}
			Cell headerThird = null;
			try {
				headerThird= new Cell().add(new Paragraph("Package Name").setFont(font).setFontColor(Color.WHITE));
				headerThird.setBackgroundColor(Color.BLACK);
				headerThird.setBorder(new SolidBorder(Color.WHITE,1f));				
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
			Cell headerFourth = null;
			try {
				
				headerFourth= new Cell().add(new Paragraph("Total Kit").setFont(font).setFontColor(Color.WHITE));
				headerFourth.setBackgroundColor(Color.BLACK);
				headerFourth.setBorder(new SolidBorder(Color.WHITE,1f));
				
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
			Cell headerFive = null;
			try {
				
				headerFive= new Cell().add(new Paragraph("Used Kit").setFont(font).setFontColor(Color.WHITE));
				headerFive.setBackgroundColor(Color.BLACK);
				headerFive.setBorder(new SolidBorder(Color.WHITE,1f));
				
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
			Cell headerSix = null;
			try {
				headerSix= new Cell().add(new Paragraph("Return Kit").setFont(font).setFontColor(Color.WHITE));
				headerSix.setBackgroundColor(Color.BLACK);
				headerSix.setBorder(new SolidBorder(Color.WHITE,1f));
				
				
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
			Cell headerSeven = null;
			try {
				headerSeven= new Cell().add(new Paragraph("Remaining Kit").setFont(font).setFontColor(Color.WHITE));
				headerSeven.setBackgroundColor(Color.BLACK);
				headerSeven.setBorder(new SolidBorder(Color.WHITE,1f));				
			} catch (Exception e)
			{
				e.printStackTrace();
			}
			dealerDetailsKitTable.addCell(headerFirst);
			dealerDetailsKitTable.addCell(headerSecond);
			dealerDetailsKitTable.addCell(headerThird);
			dealerDetailsKitTable.addCell(headerFourth);
			dealerDetailsKitTable.addCell(headerFive);
			dealerDetailsKitTable.addCell(headerSix);
			dealerDetailsKitTable.addCell(headerSeven);
			
			
			

			int serialNo = 1;
			Dealer dealer = dealerService.getDealerById(dealerId);
			
			List<List<DealerReportModel>> dealerReportData = new ArrayList<>();
			List<DealerReportModel> dealerList = dealerReportService
					.getDealerKitDetails(Integer.parseInt(dealerId));

			dealerReportData.add(dealerList);
			List<List<DealerReportModel>> dealerDataSet = dealerReportData;

			for (List<DealerReportModel> record : dealerDataSet) {
				for (DealerReportModel currentData : record)
				{
                  
					try {
						
						dealerDetailsKitTable.addCell(new Cell().add(new Paragraph(Integer.toString(serialNo))));
					} catch (Exception e) 
					{
						e.printStackTrace();
					}
					try {
						
						dealerDetailsKitTable.addCell(new Cell().add(new Paragraph(currentData.getFullName())));
					} catch (Exception e) 
					{
						e.printStackTrace();
					}
					try {
						
						dealerDetailsKitTable.addCell(new Cell().add(new Paragraph(currentData.getPackageName())));
					} catch (Exception e)
					{
						e.printStackTrace();
					}
					try {
						
						dealerDetailsKitTable
						.addCell(new Cell().add(new Paragraph(Integer.toString(currentData.getTotalKit()))));
					} catch (Exception e) 
					{
						e.printStackTrace();
					}
					try {
						
						dealerDetailsKitTable
						.addCell(new Cell().add(new Paragraph(Integer.toString(currentData.getUsedKit()))));
					} catch (Exception e) 
					{
						e.printStackTrace();
					}
					try {
						dealerDetailsKitTable
						.addCell(new Cell().add(new Paragraph(Integer.toString(currentData.getReturnKit()))));
						
					} catch (Exception e) 
					{
						e.printStackTrace();
					}
					try {
						dealerDetailsKitTable
						.addCell(new Cell().add(new Paragraph(Integer.toString(currentData.getRemainingKit()))));
						
					} catch (Exception e)
					{
						e.printStackTrace();
					}

					serialNo++;

				}
			}
			Paragraph header = new Paragraph("SUB DEALER KIT DETAILS");
			Paragraph dealerName = new Paragraph("Dealer Name : "+dealer.getFullName() + "  ("+dealer.getDealecode()+")");
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
				
				document.add(dealerName);
			} catch (Exception e)
			{
				e.printStackTrace();
			}
			try {
				
				document.add(dealerDetailsKitTable);
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
		return "Fail";
	}
	
	

	@RequestMapping(value = "/printMemberByDealer", method = RequestMethod.GET)
	@ResponseBody
	public String getMemberByDealer(@RequestParam("dealerId") String dealerId,ModelMap model, HttpServletRequest request) 
	{
	
		final String FILE_NAME = "DealerWiseMemberReport.pdf";
		try {
			
			Dealer dealer=dealerService.getDealerById(dealerId);
			
		
			List<AdminUserModel> list=userService.getListOfUserModelDetailsForDealerByDealer(dealer);
			try {

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

				Document document = new Document(pdfDoc);

				document.setFontSize(9);

				// Creating an ImageData object
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
		    	Table dealerWiseMemberDetailsTable = new Table(5);

			
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
				
				Cell headerFourth = null;
				try {
					
					headerFourth= new Cell().add(new Paragraph("Member Registration Date").setFont(font).setFontColor(Color.WHITE));
					headerFourth.setBackgroundColor(Color.BLACK);
					headerFourth.setBorder(new SolidBorder(Color.WHITE,1f));
					
				} catch (Exception e) 
				{
					e.printStackTrace();
				}
				Cell headerFive = null;
				try {
					headerFive= new Cell().add(new Paragraph("Date of Birth").setFont(font).setFontColor(Color.WHITE));
					headerFive.setBackgroundColor(Color.BLACK);
					headerFive.setBorder(new SolidBorder(Color.WHITE,1f));
				} catch (Exception e) 
				{
					e.printStackTrace();
				}
				dealerWiseMemberDetailsTable.addCell(headerFirst);
				dealerWiseMemberDetailsTable.addCell(headerSecond);
				dealerWiseMemberDetailsTable.addCell(headerThird);
				dealerWiseMemberDetailsTable.addCell(headerFourth);
				dealerWiseMemberDetailsTable.addCell(headerFive);

				int serialNo = 1;
				Dealer dealermodel = dealerService.getDealerById(dealerId);
		
				List<List<AdminUserModel>> dealerMemReportData = new ArrayList<>();
				List<AdminUserModel> memberList = userService.getListOfUserModelDetailsForDealerByDealer(dealer);
				
				dealerMemReportData.add(memberList);
				List<List<AdminUserModel>> memberDataSet = dealerMemReportData;
				
				for (List<AdminUserModel> listMember : memberDataSet) {
					for (AdminUserModel currentData : listMember) 
					{

						try {
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
							String dealerRegistrationDate = null;
							try {

								String registrationDate = ""+currentData.getRegistrationDate();			
								String[] registrationDateSplit = registrationDate.split("-");
								String registrationDay = registrationDateSplit[2];
								String [] spliteRegistrationDay = registrationDay.split(" ");				
								String todayDate1 = spliteRegistrationDay[0];				
								dealerRegistrationDate =   todayDate1 + "-" + registrationDateSplit[1] + "-" + registrationDateSplit[0];


							} catch (Exception e)
							{
								e.printStackTrace();
							}

							try {
								dealerWiseMemberDetailsTable.addCell(new Cell().add(new Paragraph(Integer.toString(serialNo))));

							} catch (Exception e) 
							{
								e.printStackTrace();
							}

							try {

								dealerWiseMemberDetailsTable.addCell(new Cell().add(new Paragraph(currentData.getFirstName())));
							} catch (Exception e)
							{
								e.printStackTrace();
							}
							try {

								dealerWiseMemberDetailsTable.addCell(new Cell().add(new Paragraph(currentData.getMobileNum())));
							} catch (Exception e)
							{
								e.printStackTrace();
							}

							try {

								dealerWiseMemberDetailsTable.addCell(new Cell().add(new Paragraph(dealerRegistrationDate)));
							} catch (Exception e) 
							{
								e.printStackTrace();
							}
							try {
								dealerWiseMemberDetailsTable.addCell(new Cell().add(new Paragraph(dateOf_Birth)));

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
				
				
				
				Paragraph dealerName=null;
				
				Paragraph header = new Paragraph("DEALER WISE MEMBER DETAILS");
				
				try {
			
					dealerName = new Paragraph("Dealer Name : "+	dealer.getUserModel().getFirstName() + "  ("+dealer.getDealecode()+")");
					
				} catch (Exception e) {
				}
				 
				try {
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
						document.add(header);
						
					} catch (Exception e) 
					{
						e.printStackTrace();
					}
					try {
						
						document.add(dealerName);
					} catch (Exception e) 
					{
						e.printStackTrace();
					}
					try {
						document.add(dealerWiseMemberDetailsTable);
						
					} catch (Exception e)
					{
						e.printStackTrace();
					}
				} catch (Exception e) {
				}
				


				document.close();
				return FILE_NAME;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		

	}catch(Exception e){
		
		e.printStackTrace();
	}
		return "fail";
	}


	
	@RequestMapping(value = "/printMemberBySubDealer", method = RequestMethod.GET)
	@ResponseBody
	public String printMemberBySubDealer(@RequestParam("dealerId") String dealerId,ModelMap model, HttpServletRequest request) 
	{
	
		final String FILE_NAME = "SubDealerWiseMemberReport.pdf";
		try {
			
			Dealer dealer=dealerService.getDealerById(dealerId);
			
		
			List<AdminUserModel> list=userService.getListOfUserModelDetailsForDealerByDealer(dealer);
			try {

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
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				PdfDocument pdfDoc =null;
				try {
					
				pdfDoc = new PdfDocument(new PdfWriter(filePath));
				} catch (Exception e) 
				{
					e.printStackTrace();
				}

				Document document = new Document(pdfDoc);
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
				Table dealerWiseMemberDetailsTable = new Table(5);

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
				
				Cell headerFourth = null;
				try {
					headerFourth= new Cell().add(new Paragraph("Member Registration Date").setFont(font).setFontColor(Color.WHITE));
					headerFourth.setBackgroundColor(Color.BLACK);
					headerFourth.setBorder(new SolidBorder(Color.WHITE,1f));
					
				} catch (Exception e) 
				{
					e.printStackTrace();
				}
				
				Cell headerFive = null;
				try {
					headerFive= new Cell().add(new Paragraph("Date of Birth").setFont(font).setFontColor(Color.WHITE));
					headerFive.setBackgroundColor(Color.BLACK);
					headerFive.setBorder(new SolidBorder(Color.WHITE,1f));
					
					
				} catch (Exception e) 
				{
					e.printStackTrace();
				}
				dealerWiseMemberDetailsTable.addCell(headerFirst);
				dealerWiseMemberDetailsTable.addCell(headerSecond);
				dealerWiseMemberDetailsTable.addCell(headerThird);
				dealerWiseMemberDetailsTable.addCell(headerFourth);
				dealerWiseMemberDetailsTable.addCell(headerFive);
				int serialNo = 1;
				Dealer dealermodel = dealerService.getDealerById(dealerId);
		
				List<List<AdminUserModel>> dealerMemReportData = new ArrayList<>();
				List<AdminUserModel> memberList = userService.getListOfUserModelDetailsForDealerByDealer(dealer);
				
				dealerMemReportData.add(memberList);
				List<List<AdminUserModel>> memberDataSet = dealerMemReportData;
				
				for (List<AdminUserModel> listMember : memberDataSet) {
					for (AdminUserModel currentData : listMember) 
					{
						try{
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

							String dealerRegistrationDate = null;
							try {
								String registrationDate = ""+currentData.getRegistrationDate();			
								String[] registrationDateSplit = registrationDate.split("-");
								String registrationDay = registrationDateSplit[2];
								String [] spliteRegistrationDay = registrationDay.split(" ");				
								String todayDate1 = spliteRegistrationDay[0];				
								dealerRegistrationDate =   todayDate1 + "-" + registrationDateSplit[1] + "-" + registrationDateSplit[0];


							} catch (Exception e) 
							{
								e.printStackTrace();
							}
							try {
								dealerWiseMemberDetailsTable.addCell(new Cell().add(new Paragraph(Integer.toString(serialNo))));

							} catch (Exception e) 
							{
								e.printStackTrace();
							}

							try {

								dealerWiseMemberDetailsTable.addCell(new Cell().add(new Paragraph(currentData.getFirstName())));
							} catch (Exception e) 
							{
								e.printStackTrace();
							}
							try {

								dealerWiseMemberDetailsTable.addCell(new Cell().add(new Paragraph(currentData.getMobileNum())));
							} catch (Exception e)
							{
								e.printStackTrace();
							}
							try {

								dealerWiseMemberDetailsTable.addCell(new Cell().add(new Paragraph(dealerRegistrationDate)));
							} catch (Exception e)
							{
								e.printStackTrace();
							}

							try {

								dealerWiseMemberDetailsTable.addCell(new Cell().add(new Paragraph(dateOf_Birth)));
							} catch (Exception e)
							{
								e.printStackTrace();
							}

							serialNo++;	
						}catch(Exception e){
							e.printStackTrace();

						}

					}
				}
				
				
				
				Paragraph dealerName=null;
				
				Paragraph header = new Paragraph("SUB DEALER WISE MEMBER DETAILS");
				
				try {
			
					dealerName = new Paragraph("Dealer Name : "+	dealer.getUserModel().getFirstName() + "  ("+dealer.getDealecode()+")");
					
				} catch (Exception e) {
				}
				 
				try {
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
						
						document.add(dealerName);
					} catch (Exception e) 
					{
						e.printStackTrace();
					}
					try {
						
						document.add(dealerWiseMemberDetailsTable);
					} catch (Exception e) 
					{
						e.printStackTrace();
					}
				} catch (Exception e) {
				}
				


				document.close();
				return FILE_NAME;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		

	}catch(Exception e){
		
		e.printStackTrace();
	}
		return "fail";
	}
	
	
	
	@RequestMapping(value = "/printDealerAllMemberDetails", method = RequestMethod.GET)
	@ResponseBody
	public String printDealerAllMemberDetails(ModelMap model, HttpServletRequest request) 
	{
	
		
		final String FILE_NAME = "DealerAllMemberReport.pdf";
		
		HttpSession session=request.getSession();
		Dealer userModelDealer=(Dealer) session.getAttribute("DealerUserModel");
		
	
		
		
		try {
			
			
		
			try {
				String pathact = null;
				try {
				pathact = request.getServletContext().getRealPath("/resources/snehReport");
					
				} catch (Exception e) 
				{
					e.printStackTrace();
				}
				File dir1  = null;
				try {
				 dir1 = new File(pathact);
					
				} catch (Exception e) 
				{
					e.printStackTrace();
				}
				if (!dir1.exists()) {
					dir1.mkdirs();
				}
				String filePath  = null;
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
			
				Table dealerWiseMemberDetailsTable = new Table(5);

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
				
				Cell headerFourth = null;
				try {
					headerFourth= new Cell().add(new Paragraph("Member Registration Date").setFont(font).setFontColor(Color.WHITE));
					headerFourth.setBackgroundColor(Color.BLACK);
					headerFourth.setBorder(new SolidBorder(Color.WHITE,1f));
					
				} catch (Exception e)
				{
					e.printStackTrace();
				}
				
				Cell headerFive = null;
				try {
					
					headerFive= new Cell().add(new Paragraph("Date of Birth").setFont(font).setFontColor(Color.WHITE));
					headerFive.setBackgroundColor(Color.BLACK);
					headerFive.setBorder(new SolidBorder(Color.WHITE,1f));
				} catch (Exception e) 
				{
					e.printStackTrace();
				}
				
				dealerWiseMemberDetailsTable.addCell(headerFirst);
				dealerWiseMemberDetailsTable.addCell(headerSecond);
				dealerWiseMemberDetailsTable.addCell(headerThird);
				dealerWiseMemberDetailsTable.addCell(headerFourth);
				dealerWiseMemberDetailsTable.addCell(headerFive);

				int serialNo = 1;
				
				
				JSONObject js = new JSONObject();
				ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
				try {
					
					
						List<List<AdminUserModel>> dealerMemReportData = new ArrayList<>();
						List<AdminUserModel> memberList=userService.getListOfUserModelDetailsForDealerByDealer(userModelDealer);
						dealerMemReportData.add(memberList);
						List<List<AdminUserModel>> memberDataSet = dealerMemReportData;
						
						for (List<AdminUserModel> listMember : memberDataSet) {
							for (AdminUserModel currentData : listMember)
							{

								try{
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
									String dealerRegistrationDate = null;
									try {
										String registrationDate = ""+currentData.getRegistrationDate();			
										String[] registrationDateSplit = registrationDate.split("-");
										String registrationDay = registrationDateSplit[2];
										String [] spliteRegistrationDay = registrationDay.split(" ");				
										String todayDate1 = spliteRegistrationDay[0];				
										dealerRegistrationDate =   todayDate1 + "-" + registrationDateSplit[1] + "-" + registrationDateSplit[0];


									} catch (Exception e) 
									{
										e.printStackTrace();
									}



									try {

										dealerWiseMemberDetailsTable.addCell(new Cell().add(new Paragraph(Integer.toString(serialNo))));
									} catch (Exception e) 
									{
										e.printStackTrace();
									}

									try {

										dealerWiseMemberDetailsTable.addCell(new Cell().add(new Paragraph(currentData.getFirstName())));
									} catch (Exception e) 
									{
										e.printStackTrace();
									}

									try {

										dealerWiseMemberDetailsTable.addCell(new Cell().add(new Paragraph(currentData.getMobileNum())));
									} catch (Exception e) 
									{
										e.printStackTrace();
									}
									try {

										dealerWiseMemberDetailsTable.addCell(new Cell().add(new Paragraph(dealerRegistrationDate)));
									} catch (Exception e)
									{
										e.printStackTrace();
									}
									try {
										dealerWiseMemberDetailsTable.addCell(new Cell().add(new Paragraph(dateOf_Birth)));

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
				} catch (Exception e) {
					e.printStackTrace();
				}

				
				
				Paragraph header = new Paragraph("DEALER REGISTRATION MEMBER DETAILS");
				
			
				try {
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
						
						document.add(dealerWiseMemberDetailsTable);
					} catch (Exception e)
					{
						e.printStackTrace();
					}
				} catch (Exception e) {
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
	
		

	}catch(Exception e){
		
		e.printStackTrace();
	}
		return "fail";
	}
	
	@RequestMapping(value = "/Download", method = RequestMethod.GET)
	public void downloadFiles(@RequestParam("path") String filename, HttpServletResponse response,
			HttpServletRequest request) throws IOException {

		if (filename == null || filename.trim().isEmpty() || "success".equalsIgnoreCase(filename) || "fail".equalsIgnoreCase(filename)) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}

		ServletContext context = request.getServletContext();

		String rootPath1 = request.getServletContext().getRealPath("/resources/snehReport");
		File dir1 = new File(rootPath1);

		String AlldestFilePath = dir1.getAbsolutePath() + File.separator + filename;
		String path = AlldestFilePath;
		File file = new File(path);

		if (!file.exists() || !file.isFile()) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}

		InputStream is = null;
		try {
			is = new FileInputStream(file);
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");
			
			OutputStream os = response.getOutputStream();
			byte[] buffer = new byte[15024];
			int len;
			while ((len = is.read(buffer)) != -1) {
				os.write(buffer, 0, len);
			}
			os.flush();
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/login");
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (Exception e) {
					// ignore
				}
			}
		}

	}

}
