package com.xanadutec.coreflex.Religion;

import java.util.List;

import com.xanadutec.coreflex.model.Caste;
import com.xanadutec.coreflex.model.Religion;

public interface ReligionDao {
	
	public List<Religion> getListOfReligion();
	public List<Caste> getListOfCasteAccordingToReligionId(String id);
	
	
}
