package com.xanadutec.coreflex.EducationOccupation;

import java.util.List;

import com.xanadutec.coreflex.model.Caste;
import com.xanadutec.coreflex.model.District_SubDistricts;
import com.xanadutec.coreflex.model.Education;
import com.xanadutec.coreflex.model.EducationCategory;
import com.xanadutec.coreflex.model.Occupation;
import com.xanadutec.coreflex.model.OccupationCategory;
import com.xanadutec.coreflex.model.State_Districts;

public interface EducationOccupationDao {
	
	public List<OccupationCategory> getListOfOccupation();
	public List<EducationCategory> getListOfEducationCategory();	

	public List<Caste> loadCasteAccordingToReligion(String id);
	public List<Occupation> loadOccupationAccordingToOccuCategory(String id);
	public List<Education> loadEducationAccordingToEduCategory(String id);
	List<Occupation> loadListOfOccupation();
	
}
