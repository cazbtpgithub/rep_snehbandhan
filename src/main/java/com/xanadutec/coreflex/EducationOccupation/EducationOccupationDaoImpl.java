package com.xanadutec.coreflex.EducationOccupation;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;


import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xanadutec.coreflex.model.Caste;
import com.xanadutec.coreflex.model.District_SubDistricts;
import com.xanadutec.coreflex.model.Education;
import com.xanadutec.coreflex.model.EducationCategory;
import com.xanadutec.coreflex.model.Occupation;
import com.xanadutec.coreflex.model.OccupationCategory;
import com.xanadutec.coreflex.model.Religion;
import com.xanadutec.coreflex.model.State_Country;
import com.xanadutec.coreflex.model.State_Districts;
import com.xanadutec.coreflex.model.UserModel;

@Repository("EducationOccupationDao")
@Transactional
public class EducationOccupationDaoImpl implements EducationOccupationDao {

	@Autowired
	private SessionFactory sessionFactory;
	@Override
	public List<OccupationCategory> getListOfOccupation() {
		Session session=sessionFactory.getCurrentSession();
		Criteria criteria=session.createCriteria(OccupationCategory.class);
		@SuppressWarnings("unchecked")
		List<OccupationCategory>  occupationCategories =criteria.list();
		return occupationCategories;
	}

	@Override
	public List<Occupation> loadListOfOccupation() {
		Session session=sessionFactory.getCurrentSession();
		Criteria criteria=session.createCriteria(Occupation.class);
		criteria.addOrder(Order.asc("occupation_name"));
		@SuppressWarnings("unchecked")
		List<Occupation>  occupations =criteria.list();
		return occupations;
	}
	
	@Override
	public List<EducationCategory> getListOfEducationCategory() {
		Session session=sessionFactory.getCurrentSession();
		Criteria criteria=session.createCriteria(EducationCategory.class);
		@SuppressWarnings("unchecked")
		List<EducationCategory>  educationCategories =criteria.list();
		return educationCategories;
	}



	@Override
	public List<Caste> loadCasteAccordingToReligion(String id) {
		Session session=sessionFactory.getCurrentSession();
		Religion religion =(Religion) session.get(Religion.class, Long.parseLong(id)); 
		Criteria criteria=session.createCriteria(Caste.class);
		criteria.add(Restrictions.eq("religion_id", religion));
		List<Caste>  castes =criteria.list();
		return castes;
	}

	@Override
	public List<Occupation> loadOccupationAccordingToOccuCategory(String id) {
		Session session=sessionFactory.getCurrentSession();
		OccupationCategory category =(OccupationCategory) session.get(OccupationCategory.class, Long.parseLong(id));
		Criteria criteria=session.createCriteria(Occupation.class);
		criteria.add(Restrictions.eq("occ_cat_id", category));
		List<Occupation>  occupations =criteria.list();
		return occupations;
	}

	@Override
	public List<Education> loadEducationAccordingToEduCategory(String id) {
		Session session=sessionFactory.getCurrentSession();
		EducationCategory educationCategory =(EducationCategory) session.get(EducationCategory.class, Long.parseLong(id)) ;
		Criteria criteria=session.createCriteria(Education.class);
		criteria.add(Restrictions.eq("edu_cat_id",educationCategory ));
		List<Education>  educations =criteria.list();
		return educations;
	}
}
