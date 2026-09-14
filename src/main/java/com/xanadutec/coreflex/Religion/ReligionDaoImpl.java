package com.xanadutec.coreflex.Religion;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;

import javax.transaction.Transaction;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xanadutec.coreflex.model.Caste;
import com.xanadutec.coreflex.model.Country;
import com.xanadutec.coreflex.model.District_SubDistricts;
import com.xanadutec.coreflex.model.Kit;
import com.xanadutec.coreflex.model.Religion;
import com.xanadutec.coreflex.model.State_Country;
import com.xanadutec.coreflex.model.State_Districts;

@Repository("ReligionDao")
@Transactional
public class ReligionDaoImpl implements ReligionDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Religion> getListOfReligion() {
		Session session=sessionFactory.getCurrentSession();
		Criteria criteria=session.createCriteria(Religion.class);
		List<Religion>  religions =criteria.list();
		return religions;
	}

	@Override
	public List<Caste> getListOfCasteAccordingToReligionId(String id) {
		Session session=sessionFactory.getCurrentSession();
		Criteria criteria=session.createCriteria(Caste.class);
		criteria.add(Restrictions.eq("religion_id", session.get(Religion.class, Long.parseLong(id))));
		List<Caste>  castes =criteria.list();
		return castes;
	}
}
