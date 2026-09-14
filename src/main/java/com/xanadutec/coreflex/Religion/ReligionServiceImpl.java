package com.xanadutec.coreflex.Religion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xanadutec.coreflex.model.Caste;
import com.xanadutec.coreflex.model.Religion;

@Service("ReligionService")
public class ReligionServiceImpl implements ReligionService {
	@Autowired
	ReligionDao religionDao;

	@Override
	public List<Religion> getListOfReligion() {
		return religionDao.getListOfReligion();
	}

	@Override
	public List<Caste> getListOfCasteAccordingToReligionId(String id) {
		return religionDao.getListOfCasteAccordingToReligionId(id);
	}

}
