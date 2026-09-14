package com.xanadutec.coreflex.location;

import java.util.Date;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

import javax.transaction.Transaction;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xanadutec.coreflex.model.AboutUs;
import com.xanadutec.coreflex.model.BackgroundImages;
import com.xanadutec.coreflex.model.Country;
import com.xanadutec.coreflex.model.District_SubDistricts;
import com.xanadutec.coreflex.model.FAQS;
import com.xanadutec.coreflex.model.Kit;
import com.xanadutec.coreflex.model.LogoImages;
import com.xanadutec.coreflex.model.MagazinePdf;
import com.xanadutec.coreflex.model.OfferImages;
import com.xanadutec.coreflex.model.PrivacyPolicy;
import com.xanadutec.coreflex.model.State_Country;
import com.xanadutec.coreflex.model.State_Districts;
import com.xanadutec.coreflex.model.SuccessImages;
import com.xanadutec.coreflex.model.SuccessStory;
import com.xanadutec.coreflex.model.TermAndCondition;

@Repository("LocationDao")
@Transactional
public class LocationDaoImpl implements LocationDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void saveCountryByName(String name) {
		Session session = sessionFactory.getCurrentSession();
		Country country = new Country();
		country.setCountry_Name(name);
		session.saveOrUpdate(country);
		}

	@Override
	public List<Country> getListOfcountry() {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Country.class);
		List<Country> list = criteria.list();
		return list;
	}

	@Override
	public void saveStateByCountry(String countryName, String stateName) {
		Country country = new Country();
		country.setCountry_id(Integer.parseInt(countryName));
		State_Country stcountry = new State_Country();
		stcountry.setState_Name(stateName);
		stcountry.setCountry(country);
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(stcountry);
		}

	@Override
	public void saveDistrict(String statId, String distName) {
		Session session = sessionFactory.getCurrentSession();
		State_Country stateCountry = new State_Country();
		stateCountry.setState_Country_id(Integer.parseInt(statId));
		State_Districts stateDistrict = new State_Districts();
		stateDistrict.setDistrict_Name(distName);
		stateDistrict.setState_Country(stateCountry);
		session.saveOrUpdate(stateDistrict);
		}

	@Override
	public List<State_Country> getListOfStateByCountryId(String id) {
		Country country = new Country();
		country.setCountry_id(Integer.parseInt(id));
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(State_Country.class);
		criteria.add(Restrictions.eq("country", country));
		List<State_Country> list = criteria.list();
		return list;
	}

	@Override
	public List<State_Districts> getListOfDistrictByStateId(String stateId) {
		State_Country state = new State_Country();
		state.setState_Country_id(Integer.parseInt(stateId));
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(State_Districts.class);
		criteria.add(Restrictions.eq("state_Country", state));
		List<State_Districts> list = criteria.list();
		return list;
	}

	@Override
	public List<District_SubDistricts> getListOfSubDistrictByDistrictId(String distid) {
		State_Districts district = new State_Districts();
		district.setState_district_id(Integer.parseInt(distid));
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(District_SubDistricts.class);
		criteria.add(Restrictions.eq("state_Districts", district));
		List<District_SubDistricts> list = criteria.list();
		return list;
	}

	@Override
	public void saveSubdistric(String distId, String subdistName) {
		Session session = sessionFactory.getCurrentSession();
		State_Districts dist = new State_Districts();
		dist.setState_district_id(Integer.parseInt(distId));
		District_SubDistricts subDist = new District_SubDistricts();
		subDist.setSubdistricts_Name(subdistName);
		subDist.setState_Districts(dist);
		session.saveOrUpdate(subDist);
		}
	@Override
	public void saveLogoFile(String fileName) {
		try {
			Session session = sessionFactory.getCurrentSession();
			LogoImages logimag = new LogoImages();
			logimag.setId(new Integer(1));
			logimag.setImagename(fileName);
			session.saveOrUpdate(logimag);
			} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void saveSuccessFile(String fileName) {
		try {			
			Session successStorySession = sessionFactory.getCurrentSession();
			successStorySession.createSQLQuery("truncate table SuccessStory").executeUpdate();
			successStorySession.close();
			SuccessImages succimg = new SuccessImages();
			succimg.setId(1);
			succimg.setImagename(fileName);
			Session session = sessionFactory.getCurrentSession();
			session.saveOrUpdate(succimg);
			} catch (Exception e) {
		}
	}

	@Override
	public void saveBackgroundFile(String fileName) {
		try {
			BackgroundImages backImag = new BackgroundImages();
			backImag.setId(1);
			backImag.setImagename(fileName);
			Session session = sessionFactory.getCurrentSession();
			session.saveOrUpdate(backImag);
			} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getLogoImages() {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteri = session.createCriteria(LogoImages.class);
		criteri.add(Restrictions.eq("id", 1));
		LogoImages logoObj = (LogoImages) criteri.uniqueResult();
		return logoObj.getImagename();
	}

	@Override
	public String getSuccessImages() {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteri = session.createCriteria(SuccessImages.class);
		criteri.add(Restrictions.eq("id", 1));
		SuccessImages logoObj = (SuccessImages) criteri.uniqueResult();
		return logoObj.getImagename();
	}

	@Override
	public String getBackgroundmages() {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteri = session.createCriteria(BackgroundImages.class);
		criteri.add(Restrictions.eq("id", 1));
		LogoImages logoObj = (LogoImages) criteri.uniqueResult();
		return logoObj.getImagename();
	}

	@Override
	public void saveMagazinePdfFileName(MagazinePdf magazinePdfObject) {
		try {
			Session session = sessionFactory.getCurrentSession();
			session.saveOrUpdate(magazinePdfObject);
			} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void saveOfferImage(String fileName) {
		try {
			OfferImages offerImg = new OfferImages();
			offerImg.setId(1);
			offerImg.setImagename(fileName);
			Session session = sessionFactory.getCurrentSession();
			session.saveOrUpdate(offerImg);
			} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public void saveSuccessStoryText(String text) {
		SuccessStory story = new SuccessStory();
		story.setStoryText(text);
		Session session = sessionFactory.getCurrentSession();
		session.save(story);
		}

	@Override
	public List<SuccessStory> getListOfSuccessStory() {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(SuccessStory.class);
		List<SuccessStory> list = criteria.list();
		criteria.addOrder(Order.asc("id"));
		/*if (list.size() > 4) {
			list = list.subList(list.size() - 4, list.size());
		}*/
		return list;
	}

	@Override
	public void saveFAQs(FAQS faqs) {
		Session session = sessionFactory.getCurrentSession();
		session.save(faqs);
		}

	@Override
	public List<FAQS> getListOfFAQs() {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(FAQS.class);
		List<FAQS> list = criteria.list();
		return list;
	}

	@Override
	public void savePrivacyPolicy(String text) {
		PrivacyPolicy offerImg = new PrivacyPolicy();
		offerImg.setId(1);
		offerImg.setPrivacyText(text);
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(offerImg);
		}

	@Override
	public String getPrivacyPolicy() {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(PrivacyPolicy.class);
		List<PrivacyPolicy> list = criteria.list();
		return list.get(0).getPrivacyText();
	}

	@Override
	public void saveAboutUs(String firstportion, String secondportion) {
		try {
			AboutUs abtus = new AboutUs();
			abtus.setId(1);
			abtus.setFirstPortion(firstportion);
			abtus.setSecondPortion(secondportion);
			Session session = sessionFactory.getCurrentSession();
			session.saveOrUpdate(abtus);
			} catch (Exception e) {
		}
	}

	@Override
	public AboutUs getAboutUsDetails() {
		try {
			Session session = sessionFactory.getCurrentSession();
			Criteria criteria = session.createCriteria(AboutUs.class);
			List<AboutUs> list = criteria.list();
			AboutUs obj = list.get(0);
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void saveTermAndCondition(String data) {
		try {
			TermAndCondition condition = new TermAndCondition();
			condition.setId(1);
			condition.setText(data);
			Session session = sessionFactory.getCurrentSession();
			session.saveOrUpdate(condition);
			} catch (Exception e) {
		}
	}

	@Override
	public TermAndCondition getTermAndCondition() {
		try {
				Session session = sessionFactory.getCurrentSession();
				Criteria criteria = session.createCriteria(TermAndCondition.class);
				List<TermAndCondition> list = criteria.list();
				TermAndCondition obj = list.get(0);
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
