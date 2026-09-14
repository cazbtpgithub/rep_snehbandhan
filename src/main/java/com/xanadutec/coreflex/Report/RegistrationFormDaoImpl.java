package com.xanadutec.coreflex.Report;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.param.DynamicFilterParameterSpecification;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xanadutec.coreflex.ReportModel.PartnerPreferancesReportModel;
import com.xanadutec.coreflex.ReportModel.RegistrationFormReportModel;
import com.xanadutec.coreflex.model.UserModel;
import com.xanadutec.coreflex.model.UserModelDetails;
import com.xanadutec.coreflex.model.UserModelImagesName;
import com.xanadutec.coreflex.model.UserModelPartnerPreferances;
import com.xanadutec.coreflex.userTask.UserService;



@Repository("RegistrationFormDao")
@Transactional
public class RegistrationFormDaoImpl implements RegistrationFormDao {

	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private UserService userService;
	
	@Override
	public List<RegistrationFormReportModel> getAll_RegistrationForm_Details() {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(RegistrationFormReportModel.class);
		criteria.createCriteria("RegistrationForm", "regDetails");
		return null;
	}

	@Override
	public RegistrationFormReportModel getUserDetailsByUserId(long userDetails_id) 
	{
		try {
			UserModel userModel=new UserModel();
			userModel.setUserId(userDetails_id);
			 Session session = sessionFactory.getCurrentSession();
			  Criteria criteria = session.createCriteria(UserModelDetails.class);
			  criteria.createCriteria("userModel", "u");
			  criteria.createCriteria("country", "c");
			  criteria.createCriteria("state_Country", "s");
			  criteria.createCriteria("districts", "d");
			  criteria.createCriteria("subDistricts", "subdis");
			  criteria.createCriteria("caste", "cast");
			  criteria.createCriteria("cast.religion_id", "castReligion");
			  criteria.createCriteria("education", "edu");
			  criteria.createCriteria("edu.edu_cat_id", "eduCat");
			  criteria.createCriteria("occupation", "occu");
			  criteria.createCriteria("occu.occ_cat_id", "occuCat");
			  criteria.setProjection(Projections.projectionList().add(Projections.property("u.firstName"), "fullName")
					  .add(Projections.property("u.userName"), "username")
					  .add(Projections.property("u.password"), "password")
					  .add(Projections.property("c.country_Name"), "country")
					  .add(Projections.property("s.state_Name"), "state")
					  .add(Projections.property("d.district_Name"), "district")
					  .add(Projections.property("subdis.subdistricts_Name"), "subDistrict")
					  .add(Projections.property("cast.caste_name"), "caste")
					  .add(Projections.property("castReligion.religion_name"), "religion")
					  .add(Projections.property("edu.education_name"), "eductaion")
					  .add(Projections.property("eduCat.edu_cat_name"), "educationCategory")
					  .add(Projections.property("occu.occupation_name"), "occupation")
					  .add(Projections.property("occuCat.occu_name"), "occupationCategory")
					  .add(Projections.property("profileCreatedFor"), "profileCreatedFor")
					  .add(Projections.property("aboutMe"), "aboutMe")
					  .add(Projections.property("email"), "email")
					  .add(Projections.property("gender"), "gender")
					  .add(Projections.property("mobileNum"), "mobileNo")
					  .add(Projections.property("dateOfBirth"), "dateOfBirth")
					  .add(Projections.property("materialStatus"), "maritalStatus")
					  .add(Projections.property("physicalStatus"), "physicalStatus")
					  .add(Projections.property("bodyType"), "bodyType")
					  .add(Projections.property("complexion"), "complexion")
					  .add(Projections.property("height"), "heightFrom")
					  .add(Projections.property("employeedIn"), "employeeIn") 
					  .add(Projections.property("weight"), "weight")
					  .add(Projections.property("materialStatus"), "materialStatus")
					  .add(Projections.property("employeedIn"), "employeeIn")
					  .add(Projections.property("familyClass"), "familyPreference")
					  .add(Projections.property("familyType"), "familyType")
					  .add(Projections.property("familyValues"), "familyValues")
					  .add(Projections.property("eating"), "eatingHabits")
					  .add(Projections.property("drink"), "drinkinghabits")
					  .add(Projections.property("smoke"), "smokingHabits")  
					  .add(Projections.property("incomeType"), "incomeType")  
					  .add(Projections.property("incomeAmount"), "incomeAmount")
					  .add(Projections.property("landmark"), "landmark") 
					  )
			    .setResultTransformer(Transformers.aliasToBean(RegistrationFormReportModel.class));
			  criteria.add(Restrictions.eq("userModel", userModel));
			  
			  List<RegistrationFormReportModel> list = criteria.list();
			  RegistrationFormReportModel registrationFormobject=list.get(0);
			  UserModelPartnerPreferances partnerPreferancesModelObject= userService.getUserModelPartnerPreferancesByUserModelId(userModel);
			  UserModelImagesName  userModelImagesNameObject = userService.getImageNameAccordingToUserModel(userModel);
			  
			  registrationFormobject.setPartnerAnualIncome(partnerPreferancesModelObject.getIncomeAmount().toString());
			  registrationFormobject.setPartnerCountry(partnerPreferancesModelObject.getCountry().getCountry_Name());
			  registrationFormobject.setPartnerState(partnerPreferancesModelObject.getState_Country().getState_Name());
			  registrationFormobject.setPartnerDistrict(partnerPreferancesModelObject.getDistricts().getDistrict_Name());
			  registrationFormobject.setPartnerMaritalStatus(partnerPreferancesModelObject.getMaterialStatus());
			  registrationFormobject.setPartnerPhysicalStatus(partnerPreferancesModelObject.getPhysicalStatus());
			  registrationFormobject.setPartnerComplexion(partnerPreferancesModelObject.getComplexion());
			  registrationFormobject.setPartnerBodyType(partnerPreferancesModelObject.getBodyType());
			  registrationFormobject.setPartnerReligion(partnerPreferancesModelObject.getReligion().getReligion_name());
			  registrationFormobject.setPartnerCast(partnerPreferancesModelObject.getCaste().getCaste_name());
              registrationFormobject.setPartnerEmployeeIn(partnerPreferancesModelObject.getEmployeedIn());
              registrationFormobject.setPartnerAnualIncome(partnerPreferancesModelObject.getIncomeAmount().toString());
              registrationFormobject.setPartnerFamilyClass(partnerPreferancesModelObject.getFamilyClass());
              registrationFormobject.setPartnerfamilyType(partnerPreferancesModelObject.getFamilyType());
              registrationFormobject.setPartnerFamilyValues(partnerPreferancesModelObject.getFamilyValues());
              registrationFormobject.setPartnerEatingHabit(partnerPreferancesModelObject.getEating());
              registrationFormobject.setPartnerIncomeType(partnerPreferancesModelObject.getIncomeType());
              registrationFormobject.setUserProfileImageName(userModelImagesNameObject.getImageNames());
              return registrationFormobject;
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<PartnerPreferancesReportModel> getPartnerPrferancesByUserId(long userDetails_id) 
	{
		try {
			UserModel userModel=new UserModel();
			userModel.setUserId(userDetails_id);
			  Session session = sessionFactory.getCurrentSession();
			  Criteria criteria = session.createCriteria(UserModelPartnerPreferances.class);
			  criteria.createCriteria("userModel", "u");
			  criteria.createCriteria("country", "c");
			  criteria.createCriteria("state_Country", "s");
			  criteria.createCriteria("districts", "d");
			  criteria.createCriteria("caste", "cast");
			  criteria.createCriteria("religion", "r");
			  criteria.setProjection(Projections.projectionList().add(Projections.property("u.firstName"), "name")
					  .add(Projections.property("c.country_Name"), "country")
					  .add(Projections.property("cast.caste_name"), "caste")
					  .add(Projections.property("r.religion_name"), "religion")
					  .add(Projections.property("s.state_Name"), "state_Country")
					  .add(Projections.property("d.district_Name"), "districts")
					  .add(Projections.property("materialStatus"), "materialStatus")
					  .add(Projections.property("physicalStatus"), "physicalStatus")
					  .add(Projections.property("heightFrom"), "heightFrom")
					  .add(Projections.property("heightTo"), "heightTo")
					  .add(Projections.property("employeedIn"), "employeedIn")
					  .add(Projections.property("incomeType"), "incomeType")
					  .add(Projections.property("familyClass"), "familyClass")
					  .add(Projections.property("incomeAmount"), "incomeAmount")
					  .add(Projections.property("familyClass"), "familyClass")
					  .add(Projections.property("familyType"), "familyType")
					  .add(Projections.property("familyValues"), "familyValues")
					  .add(Projections.property("eating"), "eating") 
					  .add(Projections.property("drink"), "drink")
					  .add(Projections.property("smoke"), "smoke")
					  .add(Projections.property("bodyType"), "bodyType")
					  .add(Projections.property("complexion"), "complexion")
					  )
			    .setResultTransformer(Transformers.aliasToBean(PartnerPreferancesReportModel.class));
			 // criteria.add(Restrictions.eq("userDetailsId", userDetails_id));
			  criteria.add(Restrictions.eq("userModel", userModel));
			  List<PartnerPreferancesReportModel> partnerPrferancesList = criteria.list();
			  
			  return partnerPrferancesList;
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return null;
	
	}


	@Override
	public List<RegistrationFormReportModel> getUserPersonalDetailsByUserId(long userDetails_id)
	{
		try {
			UserModel userModel=new UserModel();
			userModel.setUserId(userDetails_id);
			
			 Session session = sessionFactory.getCurrentSession();
			  Criteria criteria = session.createCriteria(UserModelDetails.class);
			  criteria.createCriteria("userModel", "u");
			  criteria.createCriteria("country", "c");
			  criteria.createCriteria("state_Country", "s");
			  criteria.createCriteria("districts", "d");
			  criteria.createCriteria("subDistricts", "subdis");
			  criteria.createCriteria("caste", "cast");
			  criteria.createCriteria("cast.religion_id", "castReligion");
			  criteria.createCriteria("education", "edu");
			  criteria.createCriteria("edu.edu_cat_id", "eduCat");
			  criteria.createCriteria("occupation", "occu");
			  criteria.createCriteria("occu.occ_cat_id", "occuCat");
			  
			  criteria.setProjection(Projections.projectionList().add(Projections.property("u.firstName"), "fullName")
					  .add(Projections.property("u.userName"), "username")
					  .add(Projections.property("u.password"), "password")
					  .add(Projections.property("c.country_Name"), "country")
					  .add(Projections.property("s.state_Name"), "state")
					  .add(Projections.property("d.district_Name"), "district")
					  .add(Projections.property("subdis.subdistricts_Name"), "subDistrict")
					  .add(Projections.property("cast.caste_name"), "caste")
					  .add(Projections.property("castReligion.religion_name"), "religion")
					  .add(Projections.property("edu.education_name"), "eductaion")
					  .add(Projections.property("eduCat.edu_cat_name"), "educationCategory")
					  .add(Projections.property("occu.occupation_name"), "occupation")
					  .add(Projections.property("occuCat.occu_name"), "occupationCategory")
					  .add(Projections.property("profileCreatedFor"), "profileCreatedFor")
					  .add(Projections.property("aboutMe"), "aboutMe")
					  .add(Projections.property("email"), "email")
					  .add(Projections.property("gender"), "gender")
					  .add(Projections.property("mobileNum"), "mobileNo")
					  .add(Projections.property("dateOfBirth"), "dateOfBirth")
					  .add(Projections.property("materialStatus"), "maritalStatus")
					  .add(Projections.property("physicalStatus"), "physicalStatus")
					  .add(Projections.property("bodyType"), "bodyType")
					  .add(Projections.property("complexion"), "complexion")
					  .add(Projections.property("height"), "heightFrom")
					  .add(Projections.property("employeedIn"), "employeeIn") 
					  .add(Projections.property("weight"), "weight")
					  .add(Projections.property("materialStatus"), "materialStatus")
					  .add(Projections.property("employeedIn"), "employeeIn")
					  .add(Projections.property("familyClass"), "familyPreference")
					  .add(Projections.property("familyType"), "familyType")
					  .add(Projections.property("familyValues"), "familyValues")
					  .add(Projections.property("eating"), "eatingHabits")
					  .add(Projections.property("drink"), "drinkinghabits")
					  .add(Projections.property("smoke"), "smokingHabits")  
					  .add(Projections.property("incomeType"), "incomeType")  
					  .add(Projections.property("incomeAmount"), "incomeAmount") 
					  .add(Projections.property("landmark"), "landmark") 
					  )
			    .setResultTransformer(Transformers.aliasToBean(RegistrationFormReportModel.class));
			  criteria.add(Restrictions.eq("userModel", userModel));
			  
			  List<RegistrationFormReportModel> list = criteria.list();
			
			  return list;
			
		} catch (Exception e) 
		{
		
			e.printStackTrace();
		}
		 
		return null;
	}

}
