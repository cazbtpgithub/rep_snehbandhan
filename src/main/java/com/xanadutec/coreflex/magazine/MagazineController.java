package com.xanadutec.coreflex.magazine;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.xanadutec.coreflex.location.LocationService;
import com.xanadutec.coreflex.model.MagazinePdf;
import com.xanadutec.coreflex.model.UserModel;
import com.xanadutec.coreflex.model.UserModelImagesName;

@Controller
public class MagazineController
{
	@Autowired
	LocationService locationservice;
	@Autowired
	MagzineService magazineService;
	@RequestMapping(value = "/magazineSetting", method = RequestMethod.GET)
	public ModelAndView magazineSetting(ModelMap model, HttpServletRequest request)
	{	
		List<MagazinePdf> magazinePdfList = magazineService .getMagazinePdfFilesName();
		model.addAttribute("magazinePdfList", magazinePdfList);
		
		
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
		
		return new ModelAndView("magazine");  
     }

	
	@RequestMapping(value = "/uploadPdfFilefromUploadMagazineAndImages", method = RequestMethod.POST)
	@ResponseBody
	public String uploadPdfFilefromUploadMagazineAndImages(MultipartHttpServletRequest request, HttpServletRequest serveletRequest) throws Exception 
	{
		String magazineFileId =null;

		try {
			
			
			JSONObject obj;
			JSONObject js = new JSONObject();
			String magzinTitle= null;
			try {
				obj = new JSONObject(serveletRequest.getParameter("jsonObjectData"));

				final JSONObject feedcomObj = obj;
				magzinTitle = feedcomObj.getString("Name");


			} catch (Exception e) {
			}


			String fileName = null;
			String ActualPdfFileName = "";
			final String pathact = request.getServletContext().getRealPath("/resources/MagazinePdfFile");
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
					fileName =multiFile.getOriginalFilename();
					byte[] bytes = multiFile.getBytes();
					File directory = new File(dir1.getAbsolutePath());
					directory.mkdirs();
					String extension = FilenameUtils.getExtension(fileName);
					String name = "@" + fileName;
					ActualPdfFileName = ActualPdfFileName + name + "#";

					File file = new File(dir1.getAbsolutePath() + File.separator + name);
					BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(file));
					stream.write(bytes);
					stream.close();

				} catch (Exception e) {
					e.printStackTrace();
					throw new Exception("Error while loading the file"+e.getMessage());
				}
			}

			final MagazinePdf magazinePdfObject=new MagazinePdf();
			Date currentDate = new Date();
			if(fileName !=null)
			{
				magazinePdfObject.setMagazinPdf_fileName(ActualPdfFileName);
			}
			else
			{
				magazinePdfObject.setMagazinPdf_fileName("-");
			}
			magazinePdfObject.setMagazineTitle(magzinTitle);
			magazinePdfObject.setFile_Uploaded_date(currentDate);
			
		
			locationservice.saveMagazinePdfFileName(magazinePdfObject);
			magazineFileId = ""+magazinePdfObject.getMagazinePdfId();
			
			
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		


		return toJson(magazineFileId);
	}
	
	@RequestMapping(value = "/updateUploadPdfFilefromUploadMagazineAndImages", method = RequestMethod.POST)
	@ResponseBody
	public String updateUploadPdfFilefromUploadMagazineAndImages(MultipartHttpServletRequest request, HttpServletRequest serveletRequest) throws Exception 
	{

		JSONObject obj;
		JSONObject js = new JSONObject();
		String magazineFileId= null;
		try {
			obj = new JSONObject(serveletRequest.getParameter("jsonObjectData"));

			final JSONObject feedcomObj = obj;
			magazineFileId = feedcomObj.getString("magazineFileId");


		} catch (Exception e) 
		{
			e.printStackTrace();
		}

		  
		String date =null;
		Date currentDate = new Date();
		date = new SimpleDateFormat("yyyyMMddhhmmss").format(currentDate);
		String fileName = null;
		String ActualPdfFileName = "";
		final String 	zipFilepath = request.getServletContext().getRealPath("/resources/MagazineImages/");
		final String pathact = request.getServletContext().getRealPath("/resources/MagazineImages/"+"Images"+date);
		File dir1 = new File(pathact);

		if (!dir1.exists())
		{
			dir1.mkdirs();
		}

		Iterator<String> itrator = request.getFileNames();
		String str = "";
		int i = 0;



		while (itrator.hasNext()) 
		{
			MultipartFile multiFile = request.getFile(itrator.next());
			try {
				i++;
				fileName =multiFile.getOriginalFilename();
				byte[] bytes = multiFile.getBytes();
				File directory = new File(dir1.getAbsolutePath());
				directory.mkdirs();
				String extension = FilenameUtils.getExtension(fileName);
				String name =fileName;
				str=str+fileName+"#";
				ActualPdfFileName = ActualPdfFileName + name + "#";
				File file = new File(dir1.getAbsolutePath() + File.separator + name);
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(file));


				stream.write(bytes);
				stream.close();


			} catch (Exception e) {

				e.printStackTrace();
				throw new Exception("Error while loading the file"+e.getMessage());
			}



			// ----------------------------------Zip file code-------------	
			try
			{
				File inFolder=new File(pathact);

				File outFolder=new File(zipFilepath+"\\"+"Images"+date+".zip");
				ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(outFolder)));
				BufferedInputStream in = null;
				byte[] data    = new byte[1000];
				String files[] = inFolder.list();

				for (int j=0; j<files.length; j++)
				{
					in = new BufferedInputStream(new FileInputStream(inFolder.getPath() + "/" + files[j]), 1000);        
					out.putNextEntry(new ZipEntry(files[j])); 
					int count;
					while((count = in.read(data,0,1000)) != -1)
					{
						out.write(data, 0, count);
					}
					out.closeEntry();
				}
				out.flush();
				out.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			} 
			//-----------------------------end zip code------------------	


		}
	
		if(fileName != null)
		{
			
			ActualPdfFileName = "Images"+date+".zip";
		}else
		{
			ActualPdfFileName = "-";
		}
		try {
			magazineService.update_MagazinePdfFileName(ActualPdfFileName,magazineFileId,str);
			
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return toJson(magazineFileId);
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

	@RequestMapping(value ="/magazineFileDownload", method = RequestMethod.GET)
	@ResponseBody
	public String magazineFileDownload(@RequestParam("magazinFileName") String filename, HttpServletResponse response,
			HttpServletRequest request) throws IOException
			{
		
		String spliteFileName[]= filename.split("#");
		
		String file_Name = spliteFileName[0];
	
		InputStream inputStream = null;
		
		String pathact = request.getServletContext().getRealPath("/resources/MagazinePdfFile/"+ file_Name);
		inputStream = new FileInputStream(pathact);
		

		response.setContentType("application/octet-stream");
		// Response header
		response.setHeader("Content-Disposition",
				"attachment; filename=\"" + file_Name.substring(1, file_Name.length()) + "\"");
		// Read from the file and write into the response
		OutputStream os = response.getOutputStream();
		byte[] buffer = new byte[1024];
		int len;
		while ((len = inputStream.read(buffer)) != -1) {
			os.write(buffer, 0, len);
		}
		os.flush();
		os.close();

		inputStream.close();
			
		return "";
	}
	
	
	@RequestMapping(value ="/magazineImageZipFileDownload", method = RequestMethod.GET)
	@ResponseBody
	public String magazineImageZipFileDownload(@RequestParam("magazineZipFileName") String filename, HttpServletResponse response,
			HttpServletRequest request) throws IOException
			{
		
		try {
			InputStream inputStream = null;
			
			String pathact = request.getServletContext().getRealPath("/resources/MagazineImages/"+ filename);
			inputStream = new FileInputStream(pathact);
			

			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition","attachment; filename=\"" + filename.substring(0, filename.length()) + "\"");
			OutputStream os = response.getOutputStream();
			byte[] buffer = new byte[1024];
			int len;
			while ((len = inputStream.read(buffer)) != -1) {
				os.write(buffer, 0, len);
			}
			os.flush();
			os.close();

			inputStream.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		
		}
		
	
		return "";
	}
	
	
	@RequestMapping(value = "/magzineSlider", method = RequestMethod.GET)
	public ModelAndView imageSlider( @RequestParam("folderName") String folderName,ModelMap model, HttpServletRequest request)
	{
		try {

			try {

				String folderzip=folderName+".zip";
				String imString =magazineService.getImageNameAccordingToFolderName(folderzip);
				String imgs []  =imString.split("#");
				model.addAttribute("imageObject", imgs);
				model.addAttribute("folder", folderName);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		return new ModelAndView("magazineSlider");

	}
	
	@RequestMapping(value = "/deleteMagazineDetails", method = RequestMethod.POST)
	public String deleteMagazineDetails(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("magazineTableRowID") String magazineTableRowID,@RequestParam("magazinePdfFileName") String magazinePdfFileName,
			@RequestParam("magazineImagesZipFileName") String magazineImagesZipFileName)
	{
		
	    
		String spliteMagazinePdfName[] = magazinePdfFileName.split("#");
		
		String actualMagazinePdfFileName = spliteMagazinePdfName[0];
		
		
		String magazineFolderName[] = magazineImagesZipFileName.split(".zip");
		
		String magazineImagesFolderName = magazineFolderName[0];
		
		
		String magazineImagesZipFile_NameDirectoryPath = request.getServletContext().getRealPath("/resources/MagazineImages/"+ magazineImagesZipFileName);
		
		String magazinePdfFile_NameDirectoryPath = request.getServletContext().getRealPath("/resources/MagazinePdfFile/"+ actualMagazinePdfFileName);
		
		//String magazineImagesFolderDirectoryPath = request.getServletContext().getRealPath("/resources/MagazineImages/"+ magazineImagesFolderName);
		
		/*
		try {
			deleteDirectory(magazineImagesZipFile_NameDirectoryPath);
			deleteDirectory(magazinePdfFile_NameDirectoryPath);
			//deleteDirectory(magazineImagesFolderDirectoryPath);  // used for deleting magazine Images Folders And File  it working but file deleting permission not given server
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		*/
	
		magazineService.deleteMagazineDetailsByMagazineTableId(Integer.parseInt(magazineTableRowID));
		return "success";
		
	}
	
	/*public  void deleteDirectory(String directoryFilePath) throws IOException
	{
	    Path directory = Paths.get(directoryFilePath);

	    if (Files.exists(directory))
	    {
	        Files.walkFileTree(directory, new SimpleFileVisitor<Path>()
	        {
	            @Override
	            public FileVisitResult visitFile(Path path, BasicFileAttributes basicFileAttributes) throws IOException
	            {
	                Files.delete(path);
	                return FileVisitResult.CONTINUE;
	            }

	            @Override
	            public FileVisitResult postVisitDirectory(Path directory, IOException ioException) throws IOException
	            {
	                Files.delete(directory);
	                return FileVisitResult.CONTINUE;
	            }
	        });
	    }
	}*/
	
	
}



