package com.xanadutec.coreflex.location;

import com.xanadutec.coreflex.model.AboutUs;
import com.xanadutec.coreflex.model.Country;
import com.xanadutec.coreflex.model.District_SubDistricts;
import com.xanadutec.coreflex.model.FAQS;
import com.xanadutec.coreflex.model.Kit;
import com.xanadutec.coreflex.model.MagazinePdf;
import com.xanadutec.coreflex.model.State_Country;
import com.xanadutec.coreflex.model.State_Districts;
import com.xanadutec.coreflex.model.SuccessStory;
import com.xanadutec.coreflex.model.TermAndCondition;

import antlr.collections.List;



public interface LocationDao {
	
	
	public void saveCountryByName(String name);
	public java.util.List<Country> getListOfcountry();
	public java.util.List<State_Country> getListOfStateByCountryId(String id);
	public  java.util.List<State_Districts> getListOfDistrictByStateId(String statId);
	public  java.util.List<District_SubDistricts> getListOfSubDistrictByDistrictId(String distid);
	
	
	public void saveStateByCountry(String country, String state);
	public void saveDistrict(String statId,String distName);
	public void saveSubdistric(String distId,String subdistName);
	public void saveLogoFile(String fileName);
	public void saveSuccessFile(String fileName);
	public void saveBackgroundFile(String fileName);
	public void saveOfferImage(String fileName);
	
	public void saveSuccessStoryText(String text);
	public java.util.List<SuccessStory> getListOfSuccessStory();
	public void saveFAQs(FAQS faqs);
	public java.util.List<FAQS> getListOfFAQs();
	
	public void savePrivacyPolicy(String text);
	public String getPrivacyPolicy();
	
	public void saveAboutUs(String firstportion,String secondportion);
	public AboutUs getAboutUsDetails();
	
	public void saveTermAndCondition(String data);
	public TermAndCondition getTermAndCondition();
	
	
	
	public String getLogoImages();
	public String getSuccessImages();
	public String getBackgroundmages();
	public void saveMagazinePdfFileName(MagazinePdf magazinePdfObject);
	
	
	
	
}
