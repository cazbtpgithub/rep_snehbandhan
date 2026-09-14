package com.xanadutec.coreflex.location;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

@Service("LocationService")
public class LocationServiceImpl implements LocationService{
	@Autowired
	LocationDao locationdao;
	@Override
	public void saveCountryByName(String name) {
		locationdao.saveCountryByName(name);
		
		
	}
	@Override
	public List<Country> getListOfcountry() {
	
		return locationdao.getListOfcountry();
	}
	@Override
	public void saveStateByCountry(String country, String state) {
		
		locationdao.saveStateByCountry(country, state);	
	}
	@Override
	public void saveDistrict(String statId, String distName) {
		locationdao.saveDistrict(statId, distName);
	}
	@Override
	public List<State_Country> getListOfStateByCountryId(String id) {
		return locationdao.getListOfStateByCountryId(id);
	}
	@Override
	public List<State_Districts> getListOfDistrictByStateId(String stateId) {
		return locationdao.getListOfDistrictByStateId(stateId);
	}
	@Override
	public List<District_SubDistricts> getListOfSubDistrictByDistrictId(String distid) {
		return locationdao.getListOfSubDistrictByDistrictId(distid);
	}
	@Override
	public void saveSubdistric(String distId, String subdistName) {
		locationdao.saveSubdistric(distId, subdistName);
		
	}
	@Override
	public void saveLogoFile(String fileName) {
		locationdao.saveLogoFile(fileName);
		
	}
	@Override
	public void saveSuccessFile(String fileName) {
		locationdao.saveSuccessFile(fileName);
	}
	@Override
	public void saveBackgroundFile(String fileName) {
		locationdao.saveBackgroundFile(fileName);
		
	}
	@Override
	public String getLogoImages() {
		return locationdao.getLogoImages();
	}
	@Override
	public String getSuccessImages() {
		return locationdao.getSuccessImages();
	}
	@Override
	public String getBackgroundmages() {
		return locationdao.getBackgroundmages();
	}
	
	@Override
	public void saveMagazinePdfFileName(MagazinePdf magazinePdfObject) 
	{
		locationdao.saveMagazinePdfFileName(magazinePdfObject);
	}
	@Override
	public void saveOfferImage(String fileName) {
		locationdao.saveOfferImage(fileName);
		
	}
	@Override
	public void saveSuccessStoryText(String text) {
		locationdao.saveSuccessStoryText(text);
		
	}
	@Override
	public List<SuccessStory> getListOfSuccessStory() {
		return locationdao.getListOfSuccessStory();
	}
	@Override
	public void saveFAQs(FAQS faqs) {
		locationdao.saveFAQs(faqs);
		
	}
	@Override
	public List<FAQS> getListOfFAQs() {
		return locationdao.getListOfFAQs();
	}
	@Override
	public void savePrivacyPolicy(String text) {
		locationdao.savePrivacyPolicy(text);
		
	}
	@Override
	public String getPrivacyPolicy() {
		return locationdao.getPrivacyPolicy();
	}
	@Override
	public void saveAboutUs(String firstportion, String secondportion) {
		locationdao.saveAboutUs(firstportion, secondportion);
	}
	@Override
	public AboutUs getAboutUsDetails() {
		return locationdao.getAboutUsDetails();
	}
	@Override
	public void saveTermAndCondition(String data) {
		locationdao.saveTermAndCondition(data);
		
	}
	@Override
	public TermAndCondition getTermAndCondition() {
		return locationdao.getTermAndCondition();
	}
	
	
	
	
}
