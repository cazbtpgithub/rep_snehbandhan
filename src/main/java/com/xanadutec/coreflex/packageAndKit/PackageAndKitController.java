package com.xanadutec.coreflex.packageAndKit;

import java.awt.Dimension;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.xanadutec.coreflex.Kit.KitService;
import com.xanadutec.coreflex.dealer.DealerService;
import com.xanadutec.coreflex.model.Dealer;
import com.xanadutec.coreflex.model.Kit;
import com.xanadutec.coreflex.model.KitDealerAssociation;
import com.xanadutec.coreflex.model.Package;
import com.xanadutec.coreflex.model.State_Country;
import com.xanadutec.coreflex.model.Status;
import com.xanadutec.coreflex.model.UserModel;

@Controller
public class PackageAndKitController {
	@Autowired
	PackageAndKitService packageandkitService;
	@Autowired
	SessionFactory sessionfactory;
	
	@Autowired
	DealerService dealerService;
	@Autowired
	KitService kitservice;
	@RequestMapping(value = "/package", method = RequestMethod.GET)
	public ModelAndView getpakageHome(ModelMap model, HttpServletRequest request) {

			List<Package> packageList=packageandkitService.getListOfPackage();
			
			
			
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
			

			model.addAttribute("packageList", packageList);
			return new ModelAndView("PackageHome");

	}
	
	
	@RequestMapping(value = "/kit", method = RequestMethod.GET)
	public ModelAndView getKitHome(ModelMap model, HttpServletRequest request) {

		
		List<Package> packageList=packageandkitService.getListOfPackage();
		model.addAttribute("packageList", packageList);
		List<Dealer> list=dealerService.getDealerList();
		model.addAttribute("DealerList", list);
		
		try {
				
				List<KitDealerAssociation> list1=kitservice.getListOfKitdealerAssociation();
				ObjectMapper mapper = new ObjectMapper();
				model.addAttribute("userModelDetailList", mapper.writeValueAsString(list1).replaceAll("'", " ").replaceAll(" ," , "")); 
				
			} catch (Exception e) {
			}
			
		
		
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
		
		
			
		return new ModelAndView("KitHome");

	}
	
	
	@RequestMapping(value = "/kitByType", method = RequestMethod.GET)
	public ModelAndView getKitHome(@RequestParam("packageId") String packageId,ModelMap model, HttpServletRequest request) {

		try {
			List<Kit> list=packageandkitService.getListOfKitByPackage(packageId);
				
				ObjectMapper mapper = new ObjectMapper();
		        model.addAttribute("employeeList", mapper.writeValueAsString(list));
		        
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
	
		return new ModelAndView("KitHomeByType");

	}
	
	@RequestMapping(value = "/savePackage", method = RequestMethod.POST)
	@ResponseBody
	public String saveCountry(@RequestParam("packageName") String packageName,@RequestParam("amount") String amount,@RequestParam("no_of_msg") String no_of_msg,
			@RequestParam("no_of_email") String no_of_email,@RequestParam("no_of_profiles") String no_of_profiles,@RequestParam("no_of_profiles") String no_of_days,
			ModelMap model, HttpServletRequest request) {
		
		packageandkitService.savepackage(packageName, amount,no_of_msg ,no_of_email,no_of_profiles,no_of_days) ;
		  return "Saved Successfully";

	}  
	//      
	
	@RequestMapping(value = "/updatePackageDetails", method = RequestMethod.POST)
	@ResponseBody
	public String updatePackageDetails(@RequestParam("packageId") String packageId,@RequestParam("packName") String packName,
			@RequestParam("price") String price,@RequestParam("messages") String messages,@RequestParam("emails") String emails,
			@RequestParam("profiles") String profiles,@RequestParam("days") String days,
			ModelMap model, HttpServletRequest request) {
			
	
			Status status = new Status();
			status.setStatus_id(1);
			Package pack = new Package();
			pack.setPackage_id(Integer.parseInt(packageId));
			pack.setAmount(price);
			pack.setStatus_id(status);
			pack.setPackage_Name(packName);
			pack.setNo_of_email(emails);
			pack.setNo_of_msg(messages);
			pack.setNo_of_profiles(profiles);
			pack.setNo_of_days(days);
			packageandkitService.updatePackage(pack);
			return "Saved Successfully";

	}  
	
	
	
	@RequestMapping(value = "/saveKit", method = RequestMethod.POST)
	@ResponseBody
	public String saveKit(@RequestParam("userName") String userName,@RequestParam("Password") String Password,
			@RequestParam("packageName") String packageName,
			ModelMap model, HttpServletRequest request) {
			
		packageandkitService.saveKit(userName, Password, packageName);
			return "Saved Successfully";

	}  
	
	
	@RequestMapping(value = "/getKitDataByPackage", method = RequestMethod.GET)
	@ResponseBody
	public String getKitData(@RequestParam("packageId") String packageId,
			ModelMap model, HttpServletRequest request) {
		
		
		List<Kit> list=packageandkitService.getListOfKitByPackage(packageId);
		try {

		      
		        ObjectMapper mapper = new ObjectMapper();
		        model.addAttribute("employeeList", mapper.writeValueAsString(list));
		        
		        
			JSONObject js = new JSONObject();
			
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
          
            js.put("kit", ow.writeValueAsString(list));
            
            
			return js.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	
		
		
	}    
	
	
	
	@RequestMapping(value = "/uploadMyFile", method = RequestMethod.POST)
	@ResponseBody
	public String handleFileUpload(MultipartHttpServletRequest request,HttpServletResponse response,HttpServletRequest request2) throws IOException,Exception {
		

		JSONObject obj;
		JSONObject js = new JSONObject();
		
		String json = "success";
		String feedbackTextJson= null;
		try {
			obj = new JSONObject(request2.getParameter("jsonObjectData"));

			final JSONObject feedcomObj = obj;
			 feedbackTextJson = feedcomObj.getString("Name");
			
		
	} catch (Exception e) 
		{
		e.printStackTrace();
	}
		
		
		final Package pack=new Package();
		pack.setPackage_id(Integer.parseInt(feedbackTextJson));
		final Status status=new Status();
		status.setStatus_id(2);
		
		 Vector headers = new Vector();
		 Vector data = new  Vector();
		 final JTable table = new JTable();
		 DefaultTableModel model = null;
		String pathact = request.getServletContext().getRealPath("/resources/CfsFiles");
		try {
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		File dir1 = new File(pathact);
		String fileName=null;
		if (!dir1.exists())
			dir1.mkdirs();
		
		 

		Iterator<String> itrator = request.getFileNames();
		String str = "";
		while (itrator.hasNext()) {

			MultipartFile multiFile = request.getFile(itrator.next());
			try {
				 fileName =multiFile.getOriginalFilename();
				

				byte[] bytes = multiFile.getBytes();
				File directory = new File(dir1.getAbsolutePath());
				directory.mkdirs();
			
				str = str + fileName;

				File file = new File(dir1.getAbsolutePath() + File.separator + fileName);
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(file));
				stream.write(bytes);
				stream.close();
				
			} catch (Exception e)
			{
				e.printStackTrace();
				throw new Exception("Error while loading the file"+e.getMessage());
			}
			
			
			
			
			
		}
		
		
		
		//Setting of table anad model it require while storing data to database
		int tableWidth = 0; // set the tableWidth
		 int tableHeight = 0; // set the tableHeight
		
		model = new DefaultTableModel(data,headers);
		model.setColumnCount(2);
	    table.setModel(model);
	    table.setAutoCreateRowSorter(true);
	    tableWidth = model.getColumnCount() * 150;
	    tableHeight = model.getRowCount() * 25;
	    table.setPreferredSize(new Dimension(
	      tableWidth, tableHeight));
	    table.setBounds(30,40,200,300);
		
			
	XSSFWorkbook workbook = new XSSFWorkbook(dir1.getAbsolutePath() + File.separator + fileName);
	XSSFSheet sheet = workbook.getSheetAt(0);
	
	XSSFRow row = sheet.getRow(1);
	
	
	int Last=sheet.getRow(1).getLastCellNum();
	
	for(int iii1 =0 ;iii1<=Last;iii1++){
		//insertion of headers
		XSSFCell celll = row.getCell((short) iii1 );
		String cellValue = "";
	
		if (celll == null
				|| celll.getCellType() == HSSFCell.CELL_TYPE_BLANK) {
			       
		} else if (celll.getCellType() == HSSFCell.CELL_TYPE_STRING) {
			cellValue = celll.getStringCellValue();
			
			headers.add(cellValue);
		} else if (celll.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
			double value = celll.getNumericCellValue();
			
			
			headers.add(value);
		}
		
	}
	
	
	data.clear();

	
	for (int iii = 1; iii <= sheet.getLastRowNum(); iii++) {
		
		            Vector d = new Vector();
					XSSFRow row1 = sheet.getRow(iii);
					int Last1=sheet.getRow(iii).getLastCellNum();
					
					
					ArrayList<String> s=new ArrayList<>();
					
					for(int iii1 =0 ;iii1<Last1;iii1++){
						
									XSSFCell celll = row1.getCell((short) iii1 );
									String cellValue = "";
					
									if (celll == null
											|| celll.getCellType() == HSSFCell.CELL_TYPE_BLANK) {
										cellValue="0";
										d.add(cellValue);
									}
									else if (celll.getCellType() == HSSFCell.CELL_TYPE_STRING)
									{
										cellValue = celll.getStringCellValue();
									s.add(cellValue);
										d.add(cellValue);
									}
									else if (celll.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) 
									{
										
										Date dateValue = null;
										if(DateUtil.isCellDateFormatted(celll))
										{
											dateValue = celll.getDateCellValue();
											d.add(dateValue);
										}
										else
										{
												double value = celll.getNumericCellValue();
												
												int valu3= new BigDecimal(value).setScale(0, RoundingMode.HALF_UP).intValue();
												int value1=(int) value;
												//s.add(value+"");
												
												d.add(valu3);
										}
									}
						
				}
					d.add("\n");
					data.add(d);
					
					
					
	}

	
	Thread tt = new Thread(new Runnable() {
	
		public void run() {
			
			
			int  rowIndex=table.getRowCount();
			 int columnIndex=table.getColumnCount();
			 List<Kit> kitList=new ArrayList<Kit>();
				for(int i=0;i<rowIndex;i++)
				{
					Kit kit=new Kit();
					
						
			
				try {
							
						
						
						kit.setKit_username((String)table.getValueAt(i, 0));
						kit.setKit_password((String)table.getValueAt(i, 1));
						kit.setStatus_id(status);
						kit.setPackage_id(pack);
		             
		             
		         	
		         } catch (Exception e2) {
							
						}
				kitList.add(kit);
		     
					
					
				}
				
				
				for(int i=0;i<kitList.size();i++)
				{
					
					Session session=sessionfactory.openSession();
					Transaction t=session.beginTransaction();
					session.saveOrUpdate(kitList.get(i));
					t.commit();
					session.close();
					
				
				}
			
			
			
	}});
	tt.start();
	
	
		
	
		
		return json;

		
		
	
		
	}

	public String toJson(Object data) {
		ObjectMapper mapper = new ObjectMapper();
		StringBuilder builder = new StringBuilder();
		try {
			builder.append(mapper.writeValueAsString(data));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return builder.toString();
	}





	
	
}
