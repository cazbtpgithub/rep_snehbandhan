package com.xanadutec.coreflex.EducationOccupation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xanadutec.coreflex.model.Caste;
import com.xanadutec.coreflex.model.District_SubDistricts;
import com.xanadutec.coreflex.model.Education;
import com.xanadutec.coreflex.model.EducationCategory;
import com.xanadutec.coreflex.model.Occupation;
import com.xanadutec.coreflex.model.OccupationCategory;
import com.xanadutec.coreflex.model.State_Districts;

@Service("EducationOccupationService")
public class EducationOccupationServiceImpl implements EducationOccupationService {
	@Autowired
	EducationOccupationDao educationOccupationDao;

	@Override
	public List<OccupationCategory> getListOfOccupation() {
		return educationOccupationDao.getListOfOccupation();
	}

	@Override
	public List<EducationCategory> getListOfEducationCategory() {
		return educationOccupationDao.getListOfEducationCategory();
	}

	
	@Override
	public List<Caste> loadCasteAccordingToReligion(String id) {
		return educationOccupationDao.loadCasteAccordingToReligion(id);
	}

	@Override
	public List<Occupation> loadOccupationAccordingToOccuCategory(String id) {
		return educationOccupationDao.loadOccupationAccordingToOccuCategory(id);
	}

	@Override
	public List<Education> loadEducationAccordingToEduCategory(String id) {
		return educationOccupationDao.loadEducationAccordingToEduCategory(id);
	}

	@Override
	public List<Occupation> loadListOfOccupation() {
		return educationOccupationDao.loadListOfOccupation();
	}

	

}
