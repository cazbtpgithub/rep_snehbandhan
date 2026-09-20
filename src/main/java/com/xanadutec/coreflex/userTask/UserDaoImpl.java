package com.xanadutec.coreflex.userTask;

import java.text.DecimalFormat;
import org.springframework.transaction.annotation.Transactional;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.xanadutec.coreflex.model.AdminUserModel;
import com.xanadutec.coreflex.model.Caste;
import com.xanadutec.coreflex.model.CompareUserProfileView;
import com.xanadutec.coreflex.model.Dealer;
import com.xanadutec.coreflex.model.DeleteUserDetail;
import com.xanadutec.coreflex.model.EducationCategory;
import com.xanadutec.coreflex.model.EmployeeRegistrationDetails;
import com.xanadutec.coreflex.model.Kit_Upgrade_Or_Reg_Details;
import com.xanadutec.coreflex.model.Kit_Upgrade_Or_Reg_History_Details;
import com.xanadutec.coreflex.model.Occupation;
import com.xanadutec.coreflex.model.OccupationCategory;
import com.xanadutec.coreflex.model.OnlineUser;
import com.xanadutec.coreflex.model.PartnerEducationAssociation;
import com.xanadutec.coreflex.model.PartnerOccupationAssociation;
import com.xanadutec.coreflex.model.Religion;
import com.xanadutec.coreflex.model.RoleModel;
import com.xanadutec.coreflex.model.SentMessagesEmail;
import com.xanadutec.coreflex.model.SortlistedProfileUserWithDate;
import com.xanadutec.coreflex.model.State_Country;
import com.xanadutec.coreflex.model.State_Districts;
import com.xanadutec.coreflex.model.Status;
import com.xanadutec.coreflex.model.UserModel;
import com.xanadutec.coreflex.model.UserModelDetails;
import com.xanadutec.coreflex.model.UserModelImagesName;
import com.xanadutec.coreflex.model.UserModelPartnerPreferances;
import com.xanadutec.coreflex.model.UserProfileView;
import com.xanadutec.coreflex.model.UserProfileViewForMessages;
import com.xanadutec.coreflex.model.VisitedProfileUserWithDate;
import com.xanadutec.coreflex.model.Kit;
import com.xanadutec.coreflex.setup.StateCityController;

@Repository("userDao")
@Transactional
public class UserDaoImpl implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private UserService userService;

	@Override
	public UserModel addUser(UserModel userModel) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(userModel);
		return userModel;
	}
	@Override
	public List<UserModel> listUser() {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(UserModel.class);
		List<UserModel> listUser = criteria.list();
		return listUser;
	}
	@Override
	public void deleteUser(int userId) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("DELETE FROM UserModel WHERE userId=:userId");
		query.setParameter("userId", userId);
		query.executeUpdate();
		}

	@Override
	public UserModel getUserById(int id) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(UserModel.class);
		criteria.add(Restrictions.eq("userId", Long.parseLong(id + "")));
		UserModel user = (UserModel) criteria.uniqueResult();
		return user;
	}

	@Override
	public RoleModel userRoll(int userRoll) {
		return null;
	}
	@Override
	public UserModelDetails addUserdetails(UserModelDetails userModelDetails) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(userModelDetails);
		return userModelDetails;
	}

	@Override
	public UserModelPartnerPreferances addUserPartnerPreferances(
			UserModelPartnerPreferances userModelPartnerPreferances) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(userModelPartnerPreferances);
		return userModelPartnerPreferances;
	}

	@Override
	public void savePartnerEducationAssociation(PartnerEducationAssociation educationAssociation) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(educationAssociation);
		}

	@Override
	public void savePartnerOccupationAssociation(PartnerOccupationAssociation occupationAssociation) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(occupationAssociation);
		}

	@Override
	public void saveUserImageNames(UserModelImagesName imagesName) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(imagesName);
		}

	@Override
	public UserModel getUserByUname(String userName) {
		UserModel userModel = null;
		try {
			Session session = sessionFactory.getCurrentSession();
			Criteria criteria = session.createCriteria(UserModel.class);
			criteria.add(Restrictions.eq("userName", userName));
			List<UserModel> users = criteria.list();
			if (users != null && !users.isEmpty()) {
				userModel = users.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userModel;
	}

	@Override
	public UserModelImagesName getImageNameAccordingToUserModel(UserModel userModel) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(UserModelImagesName.class);
		criteria.add(Restrictions.eq("userModel", userModel));
		UserModelImagesName image = (UserModelImagesName) criteria.uniqueResult();
		return image;
	}

	@Override
	public List<UserProfileView> getListOFProfileForHomeAccordingToGender(String gender, HttpServletRequest request) {
		Status stat = new Status();
		stat.setStatus_id(1);
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(UserModelDetails.class);
		criteria.addOrder(Order.desc("registrationDate"));
		criteria.add(Restrictions.eq("gender", gender));
		Disjunction or = Restrictions.disjunction();
		or.add(Restrictions.isNull("status"));
		or.add(Restrictions.eq("status", stat));
		criteria.add(or);
		criteria.addOrder(Order.desc("registrationDate"));
		criteria.setMaxResults(7);
		List<UserModelDetails> userModelDetails = criteria.list();
		List<UserProfileView> profileViews = new ArrayList<>();
		for (UserModelDetails userModelDetails2 : userModelDetails) {
			UserProfileView profileView = new UserProfileView();
			profileView.setAboutHer(userModelDetails2.getAboutMe());
			profileView.setName(userModelDetails2.getUserModel().getFirstName());
			profileView.setLocation(userModelDetails2.getDistricts().getDistrict_Name() + ","
					+ userModelDetails2.getState_Country().getState_Name());
			try {
				Date date = userModelDetails2.getDateOfBirth();
				Calendar calendar = new GregorianCalendar();
				calendar.setTime(date);
				Date d1 = new GregorianCalendar(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
						calendar.get(Calendar.DAY_OF_MONTH), 23, 59).getTime();
				Date today = new Date();
				long diff = today.getTime() - d1.getTime();
				long i = diff / (1000 * 60 * 60 * 24);
				int dob = (int) (i / 365);
				profileView.setAge(dob);
			} catch (Exception e) {

			}
			profileView.setEducation(userModelDetails2.getEducation().getEducation_name());
			profileView.setOccupation(userModelDetails2.getOccupation().getOccupation_name());
			profileView.setCaste(userModelDetails2.getCaste().getCaste_name());
			profileView.setVisitorUserId(userModelDetails2.getUserModel().getUserId());
			profileView.setUserId(userModelDetails2.getUserModel().getUserId());

			criteria = session.createCriteria(UserModelImagesName.class);
			criteria.add(Restrictions.eq("userModel", userModelDetails2.getUserModel()));

			List<UserModelImagesName> imgList = criteria.list();
			if (imgList != null && !imgList.isEmpty()) {
				UserModelImagesName imagesName = imgList.get(0);
				try {
					if (imagesName != null && imagesName.getImageNames() != null) {
						String s[] = imagesName.getImageNames().split("#");
						List<String> lst = new ArrayList<>();
						for (String imgStr : s) {
							if (imgStr != null && !imgStr.trim().isEmpty()) {
								lst.add(imgStr.trim());
							}
						}
						profileView.setImageObject(lst);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			try {
				HttpSession httpSession = request.getSession();
				UserModel m = (UserModel) httpSession.getAttribute("ActUserId");
				criteria = session.createCriteria(VisitedProfileUserWithDate.class);
				criteria.add(Restrictions.eq("userModel", m));
				criteria.add(Restrictions.eq("visitedUserModel", userModelDetails2.getUserModel()));
				VisitedProfileUserWithDate user = (VisitedProfileUserWithDate) criteria.uniqueResult();
				if (user != null) {
					profileView.setViewedOnDate(user.getProfileViewedDate());
				}
			} catch (Exception e) {

			}

			profileViews.add(profileView);
		}
		return profileViews;
	}

	@Override
	public UserModelDetails getUserdetails(UserModel userModel) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(UserModelDetails.class);
		criteria.add(Restrictions.eq("userModel", userModel));
		UserModelDetails userModelDetails = (UserModelDetails) criteria.uniqueResult();
		return userModelDetails;
	}

	@Override
	public CompareUserProfileView getCompareUserProfileViewAccordingToUserModelId(UserModel userModel,
			HttpServletRequest request) {
		Session session = sessionFactory.getCurrentSession();
		UserModel targetUser = (UserModel) session.get(UserModel.class, userModel.getUserId());
		if (targetUser == null) {
			targetUser = userModel;
		}
		Criteria criteria = session.createCriteria(UserModelDetails.class);
		criteria.add(Restrictions.eq("userModel", targetUser));
		try {
			UserModelDetails userModelDetails = (UserModelDetails) criteria.uniqueResult();
			if (userModelDetails == null) {
				userModelDetails = new UserModelDetails();
				userModelDetails.setUserModel(targetUser);
			}
			CompareUserProfileView compareUserProfileView = new CompareUserProfileView();
			compareUserProfileView.setAboutHer(userModelDetails.getAboutMe());
			try {
				Date date = userModelDetails.getDateOfBirth();
				if (date != null) {
					Calendar calendar = new GregorianCalendar();
					calendar.setTime(date);
					Date d1 = new GregorianCalendar(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
							calendar.get(Calendar.DAY_OF_MONTH), 23, 59).getTime();
					Date today = new Date();
					long diff = today.getTime() - d1.getTime();
					long i = diff / (1000 * 60 * 60 * 24);
					int dob = (int) (i / 365);
					compareUserProfileView.setAge(dob);
				}
			} catch (Exception e) {

			}
			if (userModelDetails.getCaste() != null) {
				compareUserProfileView.setCaste(userModelDetails.getCaste().getCaste_name());
				if (userModelDetails.getCaste().getReligion_id() != null) {
					compareUserProfileView.setRelogion(userModelDetails.getCaste().getReligion_id().getReligion_name());
				}
			}
			if (userModelDetails.getCountry() != null) {
				compareUserProfileView.setCountry(userModelDetails.getCountry().getCountry_Name());
			}
			if (userModelDetails.getDistricts() != null) {
				compareUserProfileView.setDistrict(userModelDetails.getDistricts().getDistrict_Name());
			}
			if (userModelDetails.getEducation() != null) {
				compareUserProfileView.setEducation(userModelDetails.getEducation().getEducation_name());
			}

			NumberFormat numberFormat = new DecimalFormat("###.#");
			try {
				if (userModelDetails.getHeight() != null) {
					double dd = userModelDetails.getHeight() / 30.48;
					compareUserProfileView.setHeight(numberFormat.format(dd) + "");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			if (userModelDetails.getIncomeAmount() != null) {
				compareUserProfileView.setIncome(userModelDetails.getIncomeAmount() + "");
			}
			String loc = "";
			if (userModelDetails.getDistricts() != null) {
				loc += userModelDetails.getDistricts().getDistrict_Name();
			}
			if (userModelDetails.getState_Country() != null) {
				if (!loc.isEmpty()) loc += ", ";
				loc += userModelDetails.getState_Country().getState_Name();
				compareUserProfileView.setState(userModelDetails.getState_Country().getState_Name());
			}
			compareUserProfileView.setLocation(loc);
			compareUserProfileView.setMaterialStatus(userModelDetails.getMaterialStatus());
			if (userModelDetails.getUserModel() != null) {
				compareUserProfileView.setName(userModelDetails.getUserModel().getFirstName());
				compareUserProfileView.setVisitorUserId(userModelDetails.getUserModel().getUserId());
			}
			if (userModelDetails.getOccupation() != null) {
				compareUserProfileView.setOccupation(userModelDetails.getOccupation().getOccupation_name());
			}
			compareUserProfileView.setPhysicalStatus(userModelDetails.getPhysicalStatus());

			try {
				criteria = session.createCriteria(UserModelImagesName.class);
				criteria.add(Restrictions.eq("userModel", targetUser));
				UserModelImagesName imagesName = (UserModelImagesName) criteria.uniqueResult();
				if (imagesName != null && imagesName.getImageNames() != null) {
					String s[] = imagesName.getImageNames().split("#");
					List<String> lst = new LinkedList<>();
					for (String imgStr : s) {
						if (imgStr != null && !imgStr.trim().isEmpty()) {
							lst.add(imgStr.trim());
						}
					}
					compareUserProfileView.setImageObject(lst);
				}
				if (imagesName != null && imagesName.getKundaliImage() != null) {
					String kImg = imagesName.getKundaliImage().replace("#", "");
					compareUserProfileView.setKundaliImage(kImg);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			UserModelPartnerPreferances preferance = null;
			try {
				criteria = session.createCriteria(UserModelPartnerPreferances.class);
				criteria.add(Restrictions.eq("userModel", targetUser));
				preferance = (UserModelPartnerPreferances) criteria.uniqueResult();
			} catch (Exception e) {}

			HttpSession httpSession = request.getSession();
			UserModel sessionUserModel = (UserModel) httpSession.getAttribute("ActUserId");
			UserModelPartnerPreferances preferanceSession = null;
			if (sessionUserModel != null) {
				try {
					criteria = session.createCriteria(UserModelPartnerPreferances.class);
					criteria.add(Restrictions.eq("userModel", sessionUserModel));
					preferanceSession = (UserModelPartnerPreferances) criteria.uniqueResult();
				} catch (Exception e) {}
			}

			Double count = 0.0;
			if (preferance != null) {
				compareUserProfileView.setPreferanceBody(preferance.getBodyType());
				if (preferanceSession != null && preferanceSession.getBodyType() != null && preferanceSession.getBodyType().equalsIgnoreCase(preferance.getBodyType())) {
					compareUserProfileView.setPreferanceBodyFlag(1);
					count++;
				} else {
					compareUserProfileView.setPreferanceBodyFlag(0);
				}

				compareUserProfileView.setPreferanceComplexion(preferance.getComplexion());
				if (preferanceSession != null && preferanceSession.getComplexion() != null && preferanceSession.getComplexion().equalsIgnoreCase(preferance.getComplexion())) {
					compareUserProfileView.setPreferanceComplexionFlag(1);
					count++;
				} else {
					compareUserProfileView.setPreferanceComplexionFlag(0);
				}

				compareUserProfileView.setPreferanceDrinking(preferance.getDrink());
				if (preferanceSession != null && preferanceSession.getDrink() != null && preferanceSession.getDrink().equalsIgnoreCase(preferance.getDrink())) {
					compareUserProfileView.setPreferanceDrinkingFlag(1);
					count++;
				} else {
					compareUserProfileView.setPreferanceDrinkingFlag(0);
				}

				compareUserProfileView.setPreferanceEating(preferance.getEating());
				if (preferanceSession != null && preferanceSession.getEating() != null && preferanceSession.getEating().equalsIgnoreCase(preferance.getEating())) {
					compareUserProfileView.setPreferanceEatingFlag(1);
					count++;
				} else {
					compareUserProfileView.setPreferanceEatingFlag(0);
				}

				compareUserProfileView.setPreferanceSmoking(preferance.getSmoke());
				if (preferanceSession != null && preferanceSession.getSmoke() != null && preferanceSession.getSmoke().equalsIgnoreCase(preferance.getSmoke())) {
					compareUserProfileView.setPreferanceSmokingFlag(1);
					count++;
				} else {
					compareUserProfileView.setPreferanceSmokingFlag(0);
				}

				compareUserProfileView.setPreferancePhysical(preferance.getPhysicalStatus());
				if (preferanceSession != null && preferanceSession.getPhysicalStatus() != null && preferanceSession.getPhysicalStatus().equalsIgnoreCase(preferance.getPhysicalStatus())) {
					compareUserProfileView.setPreferancePhysicalFlag(1);
					count++;
				} else {
					compareUserProfileView.setPreferancePhysicalFlag(0);
				}

				compareUserProfileView.setPreferanceMaterial(preferance.getMaterialStatus());
				if (preferanceSession != null && preferanceSession.getMaterialStatus() != null && preferanceSession.getMaterialStatus().equalsIgnoreCase(preferance.getMaterialStatus())) {
					compareUserProfileView.setPreferanceMaterialFlag(1);
					count++;
				} else {
					compareUserProfileView.setPreferanceMaterialFlag(0);
				}

				if (sessionUserModel != null) {
					try {
						criteria = session.createCriteria(UserModelDetails.class);
						criteria.add(Restrictions.eq("userModel", sessionUserModel));

						UserModelDetails details = (UserModelDetails) criteria.uniqueResult();
						if (details != null && preferance.getHeightFrom() != null && preferance.getHeightTo() != null) {
							double d = preferance.getHeightFrom() / 30.48;
							double d1 = preferance.getHeightTo() / 30.48;

							compareUserProfileView.setPreferanceHeight(numberFormat.format(d) + " - " + numberFormat.format(d1));
							if (details.getHeight() != null && details.getHeight() >= preferance.getHeightFrom()
									&& details.getHeight() <= preferance.getHeightTo()) {
								compareUserProfileView.setPreferanceHeightFlag(1);
								count++;
							} else {
								compareUserProfileView.setPreferanceHeightFlag(0);
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				try {
					compareUserProfileView.setMatchTotal(count.intValue() + "");
					double percentage = 100 * (count / 8);
					compareUserProfileView.setMatchPercentages((int) percentage + "");
				} catch (Exception e) {}
			}

			return compareUserProfileView;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	@Override
	public List<UserModelDetails> getListOfUserModelDetails() {
		Status stat = new Status();
		stat.setStatus_id(1);
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(UserModelDetails.class);
		criteria.setFirstResult(1);
		Disjunction or = Restrictions.disjunction();
		or.add(Restrictions.isNull("status"));
		or.add(Restrictions.eq("status", stat));
		criteria.add(or);
		criteria.setMaxResults(100);
		List<UserModelDetails> userModelDetails = criteria.list();
		return userModelDetails;
	}

	@Override
	public UserModelPartnerPreferances getUserModelPartnerPreferancesByUserModelId(UserModel userModel) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(UserModelPartnerPreferances.class);
		criteria.add(Restrictions.eq("userModel", userModel));
		UserModelPartnerPreferances modelPartnerPreferances = (UserModelPartnerPreferances) criteria.uniqueResult();
		return modelPartnerPreferances;
	}

	@Override
	public HashMap<String, String> getUserNamePasswordAccordingToEmailId(String emailId) {
		Session session = sessionFactory.getCurrentSession();
		try {
			Criteria criteria = session.createCriteria(UserModelDetails.class);
			UserModelDetails userModelDetails = (UserModelDetails) criteria.uniqueResult();
			UserModel userModel = userModelDetails.getUserModel();
			HashMap<String, String> map = new HashMap<>();
			map.put("userName", userModel.getUserName());
			map.put("password", userModel.getPassword());
			return map;
		} catch (Exception e) {

		} finally {
			}
		return null;
	}

	@Override
	public Long getCountOfAllLatestMatchAccordingToGender(String gender) {
		Status status = new Status();
		status.setStatus_id(1);
		try {
			Query query = null;
			Session session = sessionFactory.getCurrentSession();
			String sql = "select count(*) from UserModelDetails where gender =:gender AND (status =:status OR status is null)";
			query = session.createQuery(sql);
			query.setParameter("gender", gender);
			query.setParameter("status",status);
			query.setParameter("status",null);
			Long result = (Long) query.uniqueResult();
			result = result==null ? 0 : result;
			return result;
		}catch(Exception e){
			e.printStackTrace();
		}
	return (long) 0;
	
		/*Session session = sessionFactory.getCurrentSession();
		try {
			Status stat = new Status();
			stat.setStatus_id(1);
			Criteria criteria = session.createCriteria(UserModelDetails.class);
			criteria.add(Restrictions.eq("gender", gender));
			Disjunction or = Restrictions.disjunction();
			or.add(Restrictions.isNull("status"));
			or.add(Restrictions.eq("status", stat));
			criteria.add(or);
			List l = criteria.list();
			return (long) l.size();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			}
		return (long) 0;*/
	}

	@Override
	public Long getCountOfAllLatestUpgradedMatchAccordingToGender(String gender) {
		Status status = new Status();
		status.setStatus_id(1);
		try {
			Query query = null;
			Session session = sessionFactory.getCurrentSession();
			String sql = "select count(*) FROM UserModelDetails WHERE userModel IN (SELECT userModel FROM Kit_Upgrade_Or_Reg_Details) AND gender =:gender AND (status is null OR status=:status)";
			query = session.createQuery(sql);
			query.setParameter("gender", gender);
			query.setParameter("status",status);
			query.setParameter("status",null);
			Long result = (Long) query.uniqueResult();
			result = result==null ? 0 : result;
			return result;
		}catch(Exception e){
			e.printStackTrace();
		}
	return (long) 0;
		
		
		/*Session session = sessionFactory.getCurrentSession();
		try {
			Query query = session.createQuery("select userModel from Kit_Upgrade_Or_Reg_Details");
			List<UserModel> models = query.list();
			Status stat = new Status();
			stat.setStatus_id(1);
			Criteria criteria = session.createCriteria(UserModelDetails.class);
			criteria.add((Restrictions.in("userModel", models)));
			criteria.add(Restrictions.eq("gender", gender));
			Disjunction or = Restrictions.disjunction();
			or.add(Restrictions.isNull("status"));
			or.add(Restrictions.eq("status", stat));
			criteria.add(or);
			List l = criteria.list();
			return (long) l.size();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			}
		return (long) 0;*/
	}

	@Override
	public Long getCountOfAllSeenYetToGender(String gender, UserModel model) {
		try {
			Query query = null;
			Session session = sessionFactory.getCurrentSession();
			String sql = "select count(*) from VisitedProfileUserWithDate where userModel =:userModel";
			query = session.createQuery(sql);
			query.setParameter("userModel", model);
			Long result = (Long) query.uniqueResult();
			result = result==null ? 0 : result;
			return result;
		}catch(Exception e){
			e.printStackTrace();
		}
	return (long) 0;
	
		/*Session session = sessionFactory.getCurrentSession();
		try {
			Criteria criteria = session.createCriteria(VisitedProfileUserWithDate.class);
			criteria.add(Restrictions.eq("userModel", model));
			List l = criteria.list();
			return (long) l.size();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			}
		return (long) 0;*/
	}

	@Override
	public Long getCountOfShortlistedToGender(String gender, UserModel model) {
		try {
			Query query = null;
			Session session = sessionFactory.getCurrentSession();
			String sql = "select count(*) from SortlistedProfileUserWithDate where userModel =:userModel";
			query = session.createQuery(sql);
			query.setParameter("userModel", model);
			Long result = (Long) query.uniqueResult();
			result = result==null ? 0 : result;
			return result;
		}catch(Exception e){
			e.printStackTrace();
		}
	return (long) 0;
		
	/*	Session session = sessionFactory.getCurrentSession();
		try {
			Criteria criteria = session.createCriteria(SortlistedProfileUserWithDate.class);
			criteria.add(Restrictions.eq("userModel", model));
			List l = criteria.list();
			return (long) l.size();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			}
		return (long) 0;*/
	}

	@Override
	public List<UserProfileView> getListOFProfileForHomeAccordingToGenderExcludeUserIds(String gender,
			List<UserModel> models, HttpServletRequest request) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(UserModelDetails.class);
		Status stat = new Status();
		stat.setStatus_id(1);
		if (models.size() > 0) {
			criteria.add(Restrictions.eq("gender", gender));
			criteria.add((Restrictions.not(Restrictions.in("userModel", models))));
			Disjunction or = Restrictions.disjunction();
			or.add(Restrictions.isNull("status"));
			or.add(Restrictions.eq("status", stat));
			criteria.add(or);
			criteria.setMaxResults(10);
		} else {
			criteria.add(Restrictions.eq("gender", gender));
			Disjunction or = Restrictions.disjunction();
			or.add(Restrictions.isNull("status"));
			or.add(Restrictions.eq("status", stat));
			criteria.add(or);
			criteria.setMaxResults(10);
		}
		criteria.addOrder(Order.desc("registrationDate"));
		List<UserModelDetails> userModelDetails = criteria.list();
		List<UserProfileView> profileViews = new ArrayList<>();
		for (UserModelDetails userModelDetails2 : userModelDetails) {
			UserProfileView profileView = new UserProfileView();
			profileView.setAboutHer(userModelDetails2.getAboutMe());
			profileView.setName(userModelDetails2.getUserModel().getFirstName());
			profileView.setLocation(userModelDetails2.getDistricts().getDistrict_Name() + ","
					+ userModelDetails2.getState_Country().getState_Name());
			try {
				Date date = userModelDetails2.getDateOfBirth();

				Calendar calendar = new GregorianCalendar();
				calendar.setTime(date);
				Date d1 = new GregorianCalendar(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
						calendar.get(Calendar.DAY_OF_MONTH), 23, 59).getTime();
				Date today = new Date();
				long diff = today.getTime() - d1.getTime();
				long i = diff / (1000 * 60 * 60 * 24);
				int dob = (int) (i / 365);
				profileView.setAge(dob);
			} catch (Exception e) {
              e.printStackTrace();
			}
			profileView.setEducation(userModelDetails2.getEducation().getEducation_name());
			profileView.setOccupation(userModelDetails2.getOccupation().getOccupation_name());
			profileView.setCaste(userModelDetails2.getCaste().getCaste_name());
			profileView.setVisitorUserId(userModelDetails2.getUserModel().getUserId());
			profileView.setUserId(userModelDetails2.getUserModel().getUserId());
			criteria = session.createCriteria(UserModelImagesName.class);
			criteria.add(Restrictions.eq("userModel", userModelDetails2.getUserModel()));

			List<UserModelImagesName> imgList = criteria.list();
			if (imgList != null && !imgList.isEmpty()) {
				UserModelImagesName imagesName = imgList.get(0);
				try {
					if (imagesName != null && imagesName.getImageNames() != null) {
						String s[] = imagesName.getImageNames().split("#");
						List<String> lst = new ArrayList<>();
						for (String imgStr : s) {
							if (imgStr != null && !imgStr.trim().isEmpty()) {
								lst.add(imgStr.trim());
							}
						}
						profileView.setImageObject(lst);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			try {
				HttpSession httpSession = request.getSession();
				UserModel m = (UserModel) httpSession.getAttribute("ActUserId");
				criteria = session.createCriteria(VisitedProfileUserWithDate.class);
				criteria.add(Restrictions.eq("userModel", m));
				criteria.add(Restrictions.eq("visitedUserModel", userModelDetails2.getUserModel()));
				VisitedProfileUserWithDate user = (VisitedProfileUserWithDate) criteria.uniqueResult();
				if (user != null) {
					profileView.setViewedOnDate(user.getProfileViewedDate());
				}
			} catch (Exception e) {
             e.printStackTrace();
			}
			profileViews.add(profileView);
		}
		return profileViews;
	}

	@Override
	public void saveVisitedProfileUserWithDate(UserModel userModel, UserModel VisitUserModelId) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(VisitedProfileUserWithDate.class);
		criteria.add(Restrictions.eq("userModel", userModel));
		criteria.add(Restrictions.eq("visitedUserModel", VisitUserModelId));
		VisitedProfileUserWithDate user = (VisitedProfileUserWithDate) criteria.uniqueResult();
		try {
			if (user == null) {
				user = new VisitedProfileUserWithDate();
				user.setUserModel(userModel);
				user.setVisitedUserModel(VisitUserModelId);
				user.setProfileViewedDate(new Date());
				session.save(user);
				} else {
				user.setProfileViewedDate(new Date());
				session.saveOrUpdate(user);
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
		}

	@Override
	public List<PartnerEducationAssociation> getPartnerEducationAssociationByUserModel(UserModel userModel) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(PartnerEducationAssociation.class);
		criteria.add(Restrictions.eq("userModel", userModel));
		List<PartnerEducationAssociation> list = criteria.list();
		return list;
	}

	@Override
	public List<PartnerOccupationAssociation> getPartnerOccuptionAssociationByUserModel(UserModel userModel) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(PartnerOccupationAssociation.class);
		criteria.add(Restrictions.eq("userModel", userModel));
		List<PartnerOccupationAssociation> list = criteria.list();
		return list;
	}

	@Override
	public void deletePartnerEducationAssociationByUserModel(UserModel userModel) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("DELETE FROM PartnerEducationAssociation WHERE userModel=:userModel");
		query.setParameter("userModel", userModel);
		query.executeUpdate();
		}

	@Override
	public void deletePartnerOccuptionAssociationByUserModel(UserModel userModel) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("DELETE FROM PartnerOccupationAssociation WHERE userModel=:userModel");
		query.setParameter("userModel", userModel);
		query.executeUpdate();
		}

	@Override
	public List<UserProfileViewForMessages> getlistOfMessagesAndEmailByUser(HttpServletRequest request,
			UserModel model) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(SentMessagesEmail.class);
		criteria.add(Restrictions.eq("received_msg_email_UserModel", model));
		criteria.addOrder(Order.desc("msgDate"));
		List<SentMessagesEmail> details = criteria.list();
		List<UserModelDetails> userModelDetails = new ArrayList<>();
		List<UserProfileViewForMessages> profileViews = new ArrayList<>();
		Criteria criteria1;
		for (SentMessagesEmail messagesEmail : details) {
			try {
				criteria1 = session.createCriteria(UserModelDetails.class);
				criteria1.add(Restrictions.eq("userModel", messagesEmail.getSent_msg_email_userModel()));
				if (criteria1.list().size() > 0) {
					UserModelDetails details2 = (UserModelDetails) criteria1.uniqueResult();
					UserProfileViewForMessages profileView = new UserProfileViewForMessages();
					profileView.setAboutHer(details2.getAboutMe());
					profileView.setName(details2.getUserModel().getFirstName());
					profileView.setLocation(details2.getDistricts().getDistrict_Name() + ","
							+ details2.getState_Country().getState_Name());
					profileView.setMessage(messagesEmail.getMessage());
					profileView.setCommunication_Type(messagesEmail.getType());
					profileView.setCommunication_date(messagesEmail.getMsgDate());
					try {
						Date date = details2.getDateOfBirth();
						Calendar calendar = new GregorianCalendar();
						calendar.setTime(date);
						Date d1 = new GregorianCalendar(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
								calendar.get(Calendar.DAY_OF_MONTH), 23, 59).getTime();
						Date today = new Date();
						long diff = today.getTime() - d1.getTime();
						long i = diff / (1000 * 60 * 60 * 24);
						int dob = (int) (i / 365);
						profileView.setAge(dob);
					} catch (Exception e) {
						e.printStackTrace();
					}
					profileView.setEducation(details2.getEducation().getEducation_name());
					profileView.setOccupation(details2.getOccupation().getOccupation_name());
					profileView.setCaste(details2.getCaste().getCaste_name());
					profileView.setVisitorUserId(details2.getUserModel().getUserId());
					criteria = session.createCriteria(UserModelImagesName.class);
					criteria.add(Restrictions.eq("userModel", details2.getUserModel()));

					UserModelImagesName imagesName = (UserModelImagesName) criteria.uniqueResult();
					try {
						String s[] = imagesName.getImageNames().split("#");
						List<String> lst = new ArrayList<>();
						lst = Arrays.asList(s);
						profileView.setImageObject(lst);
					} catch (Exception e) {
						e.printStackTrace();
					}
					try {
						HttpSession httpSession = request.getSession();
						UserModel m = (UserModel) httpSession.getAttribute("ActUserId");
						criteria = session.createCriteria(VisitedProfileUserWithDate.class);
						criteria.add(Restrictions.eq("userModel", m));
						criteria.add(Restrictions.eq("visitedUserModel", details2.getUserModel()));
						VisitedProfileUserWithDate user = (VisitedProfileUserWithDate) criteria.uniqueResult();
						if (user != null) {
							profileView.setViewedOnDate(user.getProfileViewedDate());
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					profileViews.add(profileView);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return profileViews;
	}

	@Override
	public List<UserProfileView> getListOFLatestUpgradedUserForHomeAccordingToGenderExcludeUserIds(String gender,
			List<UserModel> models, HttpServletRequest request) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Kit_Upgrade_Or_Reg_Details.class);
		if (models.size() > 0) {
			criteria.add((Restrictions.not(Restrictions.in("userModel", models))));
			criteria.addOrder(Order.desc("registrationDate"));
		} else {
			criteria.addOrder(Order.desc("registrationDate"));
		}
		List<Kit_Upgrade_Or_Reg_Details> details = criteria.list();
		List<UserModelDetails> userModelDetails = new ArrayList<>();
		Criteria criteria1;
		Status stat = new Status();
		stat.setStatus_id(1);
		for (Kit_Upgrade_Or_Reg_Details kit_Upgrade_Or_Reg_Details : details) {
			try {
				criteria1 = session.createCriteria(UserModelDetails.class);
				criteria1.add(Restrictions.eq("userModel", kit_Upgrade_Or_Reg_Details.getUserModel()));
				criteria1.add(Restrictions.eq("gender", gender));  
				criteria1.addOrder(Order.desc("registrationDate"));  // newly added line for datewise 
				Disjunction or = Restrictions.disjunction();
				or.add(Restrictions.isNull("status"));
				or.add(Restrictions.eq("status", stat));
				criteria1.add(or);
				List<UserModelDetails> list = criteria1.list();
				if (list.size() > 0) {
					userModelDetails.add(list.get(0));
				}
				if (userModelDetails.size() >= 10) {
					break;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		List<UserModelDetails> userModelDetails1 = new ArrayList<>();
		try {
			if(userModelDetails.size() > 10)
			{
				userModelDetails1=userModelDetails.subList(0, 10);
			}
			else
			{
				userModelDetails1=userModelDetails;
			}
		} catch (Exception e) {
		}
		List<UserProfileView> profileViews = new ArrayList<>();
		for (UserModelDetails userModelDetails2 : userModelDetails1) {
			UserProfileView profileView = new UserProfileView();
			profileView.setAboutHer(userModelDetails2.getAboutMe());
			profileView.setName(userModelDetails2.getUserModel().getFirstName());
			profileView.setLocation(userModelDetails2.getDistricts().getDistrict_Name() + ","
					+ userModelDetails2.getState_Country().getState_Name());
			try {
				Date date = userModelDetails2.getDateOfBirth();
				Calendar calendar = new GregorianCalendar();
				calendar.setTime(date);
				Date d1 = new GregorianCalendar(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
						calendar.get(Calendar.DAY_OF_MONTH), 23, 59).getTime();
				Date today = new Date();
				long diff = today.getTime() - d1.getTime();
				long i = diff / (1000 * 60 * 60 * 24);
				int dob = (int) (i / 365);
				profileView.setAge(dob);
			} catch (Exception e) {
				e.printStackTrace();
			}
			profileView.setEducation(userModelDetails2.getEducation().getEducation_name());
			profileView.setOccupation(userModelDetails2.getOccupation().getOccupation_name());
			profileView.setCaste(userModelDetails2.getCaste().getCaste_name());
			profileView.setVisitorUserId(userModelDetails2.getUserModel().getUserId());
			criteria = session.createCriteria(UserModelImagesName.class);
			criteria.add(Restrictions.eq("userModel", userModelDetails2.getUserModel()));

			UserModelImagesName imagesName = (UserModelImagesName) criteria.uniqueResult();
			try {
				String s[] = imagesName.getImageNames().split("#");
				List<String> lst = new ArrayList<>();
				lst = Arrays.asList(s);
				profileView.setImageObject(lst);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				HttpSession httpSession = request.getSession();
				UserModel m = (UserModel) httpSession.getAttribute("ActUserId");
				criteria = session.createCriteria(VisitedProfileUserWithDate.class);
				criteria.add(Restrictions.eq("userModel", m));
				criteria.add(Restrictions.eq("visitedUserModel", userModelDetails2.getUserModel()));
				VisitedProfileUserWithDate user = (VisitedProfileUserWithDate) criteria.uniqueResult();
				if (user != null) {
					profileView.setViewedOnDate(user.getProfileViewedDate());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			profileViews.add(profileView);
		}
		return profileViews;
	}

	@Override
	public List<UserProfileView> getListOFNotSeenYetProfilesExcludeUserIds(String gender, List<UserModel> models,
			HttpServletRequest request) {
		HttpSession httpSession = request.getSession();
		UserModel m = (UserModel) httpSession.getAttribute("ActUserId");
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(VisitedProfileUserWithDate.class);
		if (models.size() > 0) {
			criteria.add(Restrictions.eq("userModel", m));
			criteria.add((Restrictions.not(Restrictions.in("visitedUserModel", models))));

		} else {
			criteria.add(Restrictions.eq("userModel", m));
		}
		criteria.addOrder(Order.desc("profileViewedDate"));
		List<VisitedProfileUserWithDate> details = criteria.list();
		List<UserModelDetails> userModelDetails = new ArrayList<>();
		List<UserModel> userModels = new ArrayList<>();
		for (VisitedProfileUserWithDate visitedProfileUserWithDate : details) {
			try {
				userModels.add(visitedProfileUserWithDate.getVisitedUserModel());

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		userModels.addAll(models);
		Session session1 = sessionFactory.getCurrentSession();
		if (userModels.size() > 0) {
			Status stat = new Status();
			stat.setStatus_id(1);
			Criteria criteria1 = session1.createCriteria(UserModelDetails.class);
			criteria1.add(Restrictions.eq("gender", gender));
			criteria1.add(Restrictions.not(Restrictions.in("userModel", userModels)));
			Disjunction or = Restrictions.disjunction();
			or.add(Restrictions.isNull("status"));
			or.add(Restrictions.eq("status", stat));
			criteria1.add(or);
			criteria1.addOrder(Order.desc("registrationDate"));
			criteria1.setMaxResults(10);
			userModelDetails = criteria1.list();
		} else {
			Status stat = new Status();
			stat.setStatus_id(1);
			Criteria criteria1 = session1.createCriteria(UserModelDetails.class);
			criteria1.add(Restrictions.eq("gender", gender));
			criteria1.setMaxResults(10);
			Disjunction or = Restrictions.disjunction();
			or.add(Restrictions.isNull("status"));
			or.add(Restrictions.eq("status", stat));
			criteria1.addOrder(Order.desc("registrationDate"));
			criteria1.add(or);
			userModelDetails = criteria1.list();
		}
		List<UserProfileView> profileViews = new ArrayList<>();
		if (userModelDetails.size() > 10) {
			for (int j = 0; j < 10; j++) {
				UserModelDetails userModelDetails2 = userModelDetails.get(j);
				UserProfileView profileView = new UserProfileView();
				profileView.setAboutHer(userModelDetails2.getAboutMe());
				profileView.setName(userModelDetails2.getUserModel().getFirstName());
				profileView.setLocation(userModelDetails2.getDistricts().getDistrict_Name() + ","
						+ userModelDetails2.getState_Country().getState_Name());
				try {
					Date date = userModelDetails2.getDateOfBirth();

					Calendar calendar = new GregorianCalendar();
					calendar.setTime(date);
					Date d1 = new GregorianCalendar(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
							calendar.get(Calendar.DAY_OF_MONTH), 23, 59).getTime();
					Date today = new Date();
					long diff = today.getTime() - d1.getTime();
					long i = diff / (1000 * 60 * 60 * 24);
					int dob = (int) (i / 365);
					profileView.setAge(dob);
				} catch (Exception e) {
					e.printStackTrace();
				}
				profileView.setEducation(userModelDetails2.getEducation().getEducation_name());
				profileView.setOccupation(userModelDetails2.getOccupation().getOccupation_name());
				profileView.setCaste(userModelDetails2.getCaste().getCaste_name());
				profileView.setVisitorUserId(userModelDetails2.getUserModel().getUserId());
				criteria = session.createCriteria(UserModelImagesName.class);
				criteria.add(Restrictions.eq("userModel", userModelDetails2.getUserModel()));
				UserModelImagesName imagesName = (UserModelImagesName) criteria.uniqueResult();
				try {
					String s[] = imagesName.getImageNames().split("#");
					List<String> lst = new ArrayList<>();
					lst = Arrays.asList(s);
					profileView.setImageObject(lst);
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					criteria = session.createCriteria(VisitedProfileUserWithDate.class);
					criteria.add(Restrictions.eq("userModel", m));
					criteria.add(Restrictions.eq("visitedUserModel", userModelDetails2.getUserModel()));
					VisitedProfileUserWithDate user = (VisitedProfileUserWithDate) criteria.uniqueResult();
					if (user != null) {
						profileView.setViewedOnDate(user.getProfileViewedDate());
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				profileViews.add(profileView);
			}
		} else {
			for (UserModelDetails userModelDetails2 : userModelDetails) {
				UserProfileView profileView = new UserProfileView();

				profileView.setAboutHer(userModelDetails2.getAboutMe());
				profileView.setName(userModelDetails2.getUserModel().getFirstName());
				profileView.setLocation(userModelDetails2.getDistricts().getDistrict_Name() + ","
						+ userModelDetails2.getState_Country().getState_Name());
				try {
					Date date = userModelDetails2.getDateOfBirth();
					Calendar calendar = new GregorianCalendar();
					calendar.setTime(date);
					Date d1 = new GregorianCalendar(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
							calendar.get(Calendar.DAY_OF_MONTH), 23, 59).getTime();
					Date today = new Date();
					long diff = today.getTime() - d1.getTime();
					long i = diff / (1000 * 60 * 60 * 24);
					int dob = (int) (i / 365);
					profileView.setAge(dob);
				} catch (Exception e) {
					e.printStackTrace();
				}
				profileView.setEducation(userModelDetails2.getEducation().getEducation_name());
				profileView.setOccupation(userModelDetails2.getOccupation().getOccupation_name());
				profileView.setCaste(userModelDetails2.getCaste().getCaste_name());
				profileView.setVisitorUserId(userModelDetails2.getUserModel().getUserId());
				criteria = session.createCriteria(UserModelImagesName.class);
				criteria.add(Restrictions.eq("userModel", userModelDetails2.getUserModel()));

				UserModelImagesName imagesName = (UserModelImagesName) criteria.uniqueResult();
				try {
					String s[] = imagesName.getImageNames().split("#");
					List<String> lst = new ArrayList<>();
					lst = Arrays.asList(s);
					profileView.setImageObject(lst);
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {

					criteria = session.createCriteria(VisitedProfileUserWithDate.class);
					criteria.add(Restrictions.eq("userModel", m));
					criteria.add(Restrictions.eq("visitedUserModel", userModelDetails2.getUserModel()));
					VisitedProfileUserWithDate user = (VisitedProfileUserWithDate) criteria.uniqueResult();
					if (user != null) {
						profileView.setViewedOnDate(user.getProfileViewedDate());
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
				profileViews.add(profileView);
			}

		}

		return profileViews;
	}

	@Override
	public List<UserProfileView> getListOFViewedYetProfilesExcludeUserIds(String gender, List<UserModel> models,
			HttpServletRequest request) {
		HttpSession httpSession = request.getSession();
		UserModel m = (UserModel) httpSession.getAttribute("ActUserId");
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(VisitedProfileUserWithDate.class);
		criteria.addOrder(Order.desc("profileViewedDate"));
		if (models.size() > 0) {
			criteria.add(Restrictions.eq("userModel", m));
			criteria.add((Restrictions.not(Restrictions.in("visitedUserModel", models))));
			criteria.setMaxResults(10);
		} else {
			criteria.add(Restrictions.eq("userModel", m));
			criteria.setMaxResults(10);
		}
		List<VisitedProfileUserWithDate> details = criteria.list();
		List<UserModelDetails> userModelDetails = new ArrayList<>();
		List<UserModel> userModels = new ArrayList<>();
		for (VisitedProfileUserWithDate visitedProfileUserWithDate : details) {
			try {
				userModels.add(visitedProfileUserWithDate.getVisitedUserModel());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		Session session1 = sessionFactory.getCurrentSession();
		if (userModels.size() > 0) {
			Status stat = new Status();
			stat.setStatus_id(1);
			Criteria criteria1 = session1.createCriteria(UserModelDetails.class);
			criteria1.addOrder(Order.desc("registrationDate"));
			criteria1.add(Restrictions.eq("gender", gender));
			criteria1.add((Restrictions.in("userModel", userModels)));
			Disjunction or = Restrictions.disjunction();
			or.add(Restrictions.isNull("status"));
			or.add(Restrictions.eq("status", stat));
			criteria1.add(or);
			criteria1.setMaxResults(10);
			userModelDetails = criteria1.list();
		}
		List<UserProfileView> profileViews = new ArrayList<>();
		if (userModelDetails.size() > 10) {
			for (int j = 0; j < 10; j++) {
				UserModelDetails userModelDetails2 = userModelDetails.get(j);
				UserProfileView profileView = new UserProfileView();
				profileView.setAboutHer(userModelDetails2.getAboutMe());
				profileView.setName(userModelDetails2.getUserModel().getFirstName());
				profileView.setLocation(userModelDetails2.getDistricts().getDistrict_Name() + ","
						+ userModelDetails2.getState_Country().getState_Name());
				try {
					Date date = userModelDetails2.getDateOfBirth();
					Calendar calendar = new GregorianCalendar();
					calendar.setTime(date);
					Date d1 = new GregorianCalendar(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
							calendar.get(Calendar.DAY_OF_MONTH), 23, 59).getTime();
					Date today = new Date();
					long diff = today.getTime() - d1.getTime();
					long i = diff / (1000 * 60 * 60 * 24);
					int dob = (int) (i / 365);
					profileView.setAge(dob);
				} catch (Exception e) {
					e.printStackTrace();
				}
				profileView.setEducation(userModelDetails2.getEducation().getEducation_name());
				profileView.setOccupation(userModelDetails2.getOccupation().getOccupation_name());
				profileView.setCaste(userModelDetails2.getCaste().getCaste_name());
				profileView.setVisitorUserId(userModelDetails2.getUserModel().getUserId());
				criteria = session.createCriteria(UserModelImagesName.class);
				criteria.add(Restrictions.eq("userModel", userModelDetails2.getUserModel()));

				UserModelImagesName imagesName = (UserModelImagesName) criteria.uniqueResult();
				try {
					String s[] = imagesName.getImageNames().split("#");
					List<String> lst = new ArrayList<>();
					lst = Arrays.asList(s);
					profileView.setImageObject(lst);
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					criteria = session.createCriteria(VisitedProfileUserWithDate.class);
					criteria.add(Restrictions.eq("userModel", m));
					criteria.add(Restrictions.eq("visitedUserModel", userModelDetails2.getUserModel()));
					VisitedProfileUserWithDate user = (VisitedProfileUserWithDate) criteria.uniqueResult();
					if (user != null) {
						profileView.setViewedOnDate(user.getProfileViewedDate());
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
				profileViews.add(profileView);
			}
		} else {
			for (UserModelDetails userModelDetails2 : userModelDetails) {
				UserProfileView profileView = new UserProfileView();
				profileView.setAboutHer(userModelDetails2.getAboutMe());
				profileView.setName(userModelDetails2.getUserModel().getFirstName());
				profileView.setLocation(userModelDetails2.getDistricts().getDistrict_Name() + ","
						+ userModelDetails2.getState_Country().getState_Name());
				try {
					Date date = userModelDetails2.getDateOfBirth();
					Calendar calendar = new GregorianCalendar();
					calendar.setTime(date);
					Date d1 = new GregorianCalendar(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
							calendar.get(Calendar.DAY_OF_MONTH), 23, 59).getTime();
					Date today = new Date();
					long diff = today.getTime() - d1.getTime();
					long i = diff / (1000 * 60 * 60 * 24);
					int dob = (int) (i / 365);
					profileView.setAge(dob);
				} catch (Exception e) {
					e.printStackTrace();
				}
				profileView.setEducation(userModelDetails2.getEducation().getEducation_name());
				profileView.setOccupation(userModelDetails2.getOccupation().getOccupation_name());
				profileView.setCaste(userModelDetails2.getCaste().getCaste_name());
				profileView.setVisitorUserId(userModelDetails2.getUserModel().getUserId());
				criteria = session.createCriteria(UserModelImagesName.class);
				criteria.add(Restrictions.eq("userModel", userModelDetails2.getUserModel()));
				UserModelImagesName imagesName = (UserModelImagesName) criteria.uniqueResult();
				try {
					String s[] = imagesName.getImageNames().split("#");
					List<String> lst = new ArrayList<>();
					lst = Arrays.asList(s);
					profileView.setImageObject(lst);
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					criteria = session.createCriteria(VisitedProfileUserWithDate.class);
					criteria.add(Restrictions.eq("userModel", m));
					criteria.add(Restrictions.eq("visitedUserModel", userModelDetails2.getUserModel()));
					VisitedProfileUserWithDate user = (VisitedProfileUserWithDate) criteria.uniqueResult();
					if (user != null) {
						profileView.setViewedOnDate(user.getProfileViewedDate());
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
				profileViews.add(profileView);
			}

		}

		return profileViews;
	}

	@Override
	public List<UserProfileView> getListOFShortlistedOrIntrestedProfilesExcludeUserIds(String gender,
			List<UserModel> models, HttpServletRequest request) {
		HttpSession httpSession = request.getSession();
		UserModel m = (UserModel) httpSession.getAttribute("ActUserId");
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(SortlistedProfileUserWithDate.class);
		criteria.addOrder(Order.desc("sortlistedDate"));
		if (models.size() > 0) {
			criteria.add(Restrictions.eq("userModel", m));
			criteria.add((Restrictions.not(Restrictions.in("sortlistedUserModel", models))));
			criteria.setMaxResults(10);
		} else {
			criteria.add(Restrictions.eq("userModel", m));
			criteria.setMaxResults(10);
		}
		List<SortlistedProfileUserWithDate> details = criteria.list();
		List<UserModelDetails> userModelDetails = new ArrayList<>();
		List<UserModel> userModels = new ArrayList<>();
		for (SortlistedProfileUserWithDate sortlistedProfileUserWithDate : details) {
			try {
				userModels.add(sortlistedProfileUserWithDate.getSortlistedUserModel());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		Session session1 = sessionFactory.getCurrentSession();
		if (userModels.size() > 0) {
			Status stat = new Status();
			stat.setStatus_id(1);
			Criteria criteria1 = session1.createCriteria(UserModelDetails.class);
			criteria1.addOrder(Order.desc("registrationDate"));
			criteria1.add(Restrictions.eq("gender", gender));
			criteria1.add((Restrictions.in("userModel", userModels)));
			Disjunction or = Restrictions.disjunction();
			or.add(Restrictions.isNull("status"));
			or.add(Restrictions.eq("status", stat));
			criteria1.add(or);
			criteria1.setMaxResults(10);
			userModelDetails = criteria1.list();
		}
		List<UserProfileView> profileViews = new ArrayList<>();
		if (userModelDetails.size() > 10) {
			for (int j = 0; j < 10; j++) {
				UserModelDetails userModelDetails2 = userModelDetails.get(j);
				UserProfileView profileView = new UserProfileView();
				profileView.setAboutHer(userModelDetails2.getAboutMe());
				profileView.setName(userModelDetails2.getUserModel().getFirstName());
				profileView.setLocation(userModelDetails2.getDistricts().getDistrict_Name() + ","
						+ userModelDetails2.getState_Country().getState_Name());
				try {
					Date date = userModelDetails2.getDateOfBirth();

					Calendar calendar = new GregorianCalendar();
					calendar.setTime(date);
					Date d1 = new GregorianCalendar(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
							calendar.get(Calendar.DAY_OF_MONTH), 23, 59).getTime();
					Date today = new Date();
					long diff = today.getTime() - d1.getTime();
					long i = diff / (1000 * 60 * 60 * 24);
					int dob = (int) (i / 365);
					profileView.setAge(dob);
				} catch (Exception e) {
					e.printStackTrace();
				}
				profileView.setEducation(userModelDetails2.getEducation().getEducation_name());
				profileView.setOccupation(userModelDetails2.getOccupation().getOccupation_name());
				profileView.setLandmark(userModelDetails2.getLandmark());
				profileView.setCaste(userModelDetails2.getCaste().getCaste_name());
				profileView.setVisitorUserId(userModelDetails2.getUserModel().getUserId());
				criteria = session.createCriteria(UserModelImagesName.class);
				criteria.add(Restrictions.eq("userModel", userModelDetails2.getUserModel()));
				UserModelImagesName imagesName = (UserModelImagesName) criteria.uniqueResult();
				try {
					String s[] = imagesName.getImageNames().split("#");
					List<String> lst = new ArrayList<>();
					lst = Arrays.asList(s);
					profileView.setImageObject(lst);
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					criteria = session.createCriteria(VisitedProfileUserWithDate.class);
					criteria.add(Restrictions.eq("userModel", m));
					criteria.add(Restrictions.eq("visitedUserModel", userModelDetails2.getUserModel()));
					VisitedProfileUserWithDate user = (VisitedProfileUserWithDate) criteria.uniqueResult();
					if (user != null) {
						profileView.setViewedOnDate(user.getProfileViewedDate());
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
				profileViews.add(profileView);
			}
		} else {
			for (UserModelDetails userModelDetails2 : userModelDetails) {
				UserProfileView profileView = new UserProfileView();
				profileView.setAboutHer(userModelDetails2.getAboutMe());
				profileView.setName(userModelDetails2.getUserModel().getFirstName());
				profileView.setLocation(userModelDetails2.getDistricts().getDistrict_Name() + ","
						+ userModelDetails2.getState_Country().getState_Name());
				try {
					Date date = userModelDetails2.getDateOfBirth();

					Calendar calendar = new GregorianCalendar();
					calendar.setTime(date);
					Date d1 = new GregorianCalendar(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
							calendar.get(Calendar.DAY_OF_MONTH), 23, 59).getTime();
					Date today = new Date();
					long diff = today.getTime() - d1.getTime();
					long i = diff / (1000 * 60 * 60 * 24);
					int dob = (int) (i / 365);
					profileView.setAge(dob);
				} catch (Exception e) {
					e.printStackTrace();
				}
				profileView.setEducation(userModelDetails2.getEducation().getEducation_name());
				profileView.setOccupation(userModelDetails2.getOccupation().getOccupation_name());
				profileView.setLandmark(userModelDetails2.getLandmark());
				profileView.setCaste(userModelDetails2.getCaste().getCaste_name());
				profileView.setVisitorUserId(userModelDetails2.getUserModel().getUserId());
				criteria = session.createCriteria(UserModelImagesName.class);
				criteria.add(Restrictions.eq("userModel", userModelDetails2.getUserModel()));
				UserModelImagesName imagesName = (UserModelImagesName) criteria.uniqueResult();
				try {
					String s[] = imagesName.getImageNames().split("#");
					List<String> lst = new ArrayList<>();
					lst = Arrays.asList(s);
					profileView.setImageObject(lst);
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					criteria = session.createCriteria(VisitedProfileUserWithDate.class);
					criteria.add(Restrictions.eq("userModel", m));
					criteria.add(Restrictions.eq("visitedUserModel", userModelDetails2.getUserModel()));
					VisitedProfileUserWithDate user = (VisitedProfileUserWithDate) criteria.uniqueResult();
					if (user != null) {
						profileView.setViewedOnDate(user.getProfileViewedDate());
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				profileViews.add(profileView);
			}
		}

		return profileViews;
	}

	@Override
	public void updateUser(UserModel user) {
		Session session = sessionFactory.getCurrentSession();
		session.update(user);
		}

	@Override
	public List<UserProfileViewForMessages> getlistOfSentMessagesAndEmail(HttpServletRequest request,
			UserModel userModel) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(SentMessagesEmail.class);
		criteria.add(Restrictions.eq("sent_msg_email_userModel", userModel));
		criteria.addOrder(Order.desc("msgDate"));
		List<SentMessagesEmail> details = criteria.list();
		List<UserModelDetails> userModelDetails = new ArrayList<>();
		List<UserProfileViewForMessages> profileViews = new ArrayList<>();
		Criteria criteria1;
		for (SentMessagesEmail messagesEmail : details) {
			try {
				criteria1 = session.createCriteria(UserModelDetails.class);
				criteria1.add(Restrictions.eq("userModel", messagesEmail.getReceived_msg_email_UserModel()));
				if (criteria1.list().size() > 0) {
					UserModelDetails details2 = (UserModelDetails) criteria1.uniqueResult();
					UserProfileViewForMessages profileView = new UserProfileViewForMessages();
					profileView.setAboutHer(details2.getAboutMe());
					profileView.setName(details2.getUserModel().getFirstName());
					profileView.setLocation(details2.getDistricts().getDistrict_Name() + ","
							+ details2.getState_Country().getState_Name());
					profileView.setMessage(messagesEmail.getMessage());
					profileView.setCommunication_Type(messagesEmail.getType());
					profileView.setCommunication_date(messagesEmail.getMsgDate());
					try {
						Date date = details2.getDateOfBirth();
						Calendar calendar = new GregorianCalendar();
						calendar.setTime(date);
						Date d1 = new GregorianCalendar(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
								calendar.get(Calendar.DAY_OF_MONTH), 23, 59).getTime();
						Date today = new Date();
						long diff = today.getTime() - d1.getTime();
						long i = diff / (1000 * 60 * 60 * 24);
						int dob = (int) (i / 365);
						profileView.setAge(dob);
					} catch (Exception e) {
						e.printStackTrace();
					}
					profileView.setEducation(details2.getEducation().getEducation_name());
					profileView.setOccupation(details2.getOccupation().getOccupation_name());
					profileView.setCaste(details2.getCaste().getCaste_name());
					profileView.setVisitorUserId(details2.getUserModel().getUserId());
					criteria = session.createCriteria(UserModelImagesName.class);
					criteria.add(Restrictions.eq("userModel", details2.getUserModel()));
					UserModelImagesName imagesName = (UserModelImagesName) criteria.uniqueResult();
					try {
						String s[] = imagesName.getImageNames().split("#");
						List<String> lst = new ArrayList<>();
						lst = Arrays.asList(s);
						profileView.setImageObject(lst);
					} catch (Exception e) {
						e.printStackTrace();
					}
					try {
						HttpSession httpSession = request.getSession();
						UserModel m = (UserModel) httpSession.getAttribute("ActUserId");
						criteria = session.createCriteria(VisitedProfileUserWithDate.class);
						criteria.add(Restrictions.eq("userModel", m));
						criteria.add(Restrictions.eq("visitedUserModel", details2.getUserModel()));
						VisitedProfileUserWithDate user = (VisitedProfileUserWithDate) criteria.uniqueResult();
						if (user != null) {
							profileView.setViewedOnDate(user.getProfileViewedDate());
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					profileViews.add(profileView);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return profileViews;
	}

	@Override
	public Map<String, List<UserProfileViewForMessages>> getlistOfInvitationByUser(HttpServletRequest request,
			UserModel userModel) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(SortlistedProfileUserWithDate.class);
		criteria.add(Restrictions.eq("userModel", userModel));
		criteria.addOrder(Order.desc("sortlistedDate"));
		Criteria criteriaOne = session.createCriteria(SortlistedProfileUserWithDate.class);
		criteriaOne.add(Restrictions.eq("sortlistedUserModel", userModel));
		criteriaOne.addOrder(Order.desc("sortlistedDate"));
		List<SortlistedProfileUserWithDate> details = criteria.list();
		List<SortlistedProfileUserWithDate> detailsOne = criteriaOne.list();
		List<UserModelDetails> userModelDetails = new ArrayList<>();
		List<UserProfileViewForMessages> profileViews = new ArrayList<>();
		List<UserProfileViewForMessages> profileViewsOne = new ArrayList<>();
		Criteria criteria1;
		Map<String, List<UserProfileViewForMessages>> map = new HashMap<>();

		for (SortlistedProfileUserWithDate messagesEmail : details) {
			try {
				criteria1 = session.createCriteria(UserModelDetails.class);
				criteria1.add(Restrictions.eq("userModel", messagesEmail.getSortlistedUserModel()));
				if (criteria1.list().size() > 0) {
					UserModelDetails details2 = (UserModelDetails) criteria1.uniqueResult();
					UserProfileViewForMessages profileView = new UserProfileViewForMessages();
					profileView.setAboutHer(details2.getAboutMe());
					profileView.setName(details2.getUserModel().getFirstName());
					profileView.setLocation(details2.getDistricts().getDistrict_Name() + ","
							+ details2.getState_Country().getState_Name());
					profileView.setMessage("you have sent INVITATION on");
					profileView.setCommunication_Type(messagesEmail.getStatus());
					profileView.setCommunication_date(messagesEmail.getSortlistedDate());
					profileView.setStatus_date(messagesEmail.getStatusDate());
					profileView.setSortlistedProfileUserWithDate(messagesEmail.getSortlistId());
					try {
						Date date = details2.getDateOfBirth();
						Calendar calendar = new GregorianCalendar();
						calendar.setTime(date);
						Date d1 = new GregorianCalendar(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
								calendar.get(Calendar.DAY_OF_MONTH), 23, 59).getTime();
						Date today = new Date();
						long diff = today.getTime() - d1.getTime();
						long i = diff / (1000 * 60 * 60 * 24);
						int dob = (int) (i / 365);
						profileView.setAge(dob);
					} catch (Exception e) {
						e.printStackTrace();
					}
					profileView.setEducation(details2.getEducation().getEducation_name());
					profileView.setOccupation(details2.getOccupation().getOccupation_name());
					profileView.setCaste(details2.getCaste().getCaste_name());
					profileView.setVisitorUserId(details2.getUserModel().getUserId());
					criteria = session.createCriteria(UserModelImagesName.class);
					criteria.add(Restrictions.eq("userModel", details2.getUserModel()));
					UserModelImagesName imagesName = (UserModelImagesName) criteria.uniqueResult();
					try {
						String s[] = imagesName.getImageNames().split("#");
						List<String> lst = new ArrayList<>();
						lst = Arrays.asList(s);
						profileView.setImageObject(lst);
					} catch (Exception e) {
						e.printStackTrace();
					}
					try {
						HttpSession httpSession = request.getSession();
						UserModel m = (UserModel) httpSession.getAttribute("ActUserId");
						criteria = session.createCriteria(VisitedProfileUserWithDate.class);
						criteria.add(Restrictions.eq("userModel", m));
						criteria.add(Restrictions.eq("visitedUserModel", details2.getUserModel()));
						VisitedProfileUserWithDate user = (VisitedProfileUserWithDate) criteria.uniqueResult();
						if (user != null) {
							profileView.setViewedOnDate(user.getProfileViewedDate());
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					profileViews.add(profileView);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		map.put("ByYou", profileViews);
		for (SortlistedProfileUserWithDate messagesEmail : detailsOne) {
			try {
				criteria1 = session.createCriteria(UserModelDetails.class);
				criteria1.add(Restrictions.eq("userModel", messagesEmail.getUserModel()));
				if (criteria1.list().size() > 0) {
					UserModelDetails details2 = (UserModelDetails) criteria1.uniqueResult();
					UserProfileViewForMessages profileView = new UserProfileViewForMessages();
					profileView.setAboutHer(details2.getAboutMe());
					profileView.setName(details2.getUserModel().getFirstName());
					profileView.setLocation(details2.getDistricts().getDistrict_Name() + ","
							+ details2.getState_Country().getState_Name());
					profileView.setMessage(details2.getUserModel().getFirstName() + "sent you INVITATION on");
					profileView.setCommunication_Type(messagesEmail.getStatus());
					profileView.setCommunication_date(messagesEmail.getSortlistedDate());
					profileView.setStatus_date(messagesEmail.getStatusDate());
					profileView.setSortlistedProfileUserWithDate(messagesEmail.getSortlistId());
					try {
						Date date = details2.getDateOfBirth();
						Calendar calendar = new GregorianCalendar();
						calendar.setTime(date);
						Date d1 = new GregorianCalendar(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
								calendar.get(Calendar.DAY_OF_MONTH), 23, 59).getTime();
						Date today = new Date();
						long diff = today.getTime() - d1.getTime();
						long i = diff / (1000 * 60 * 60 * 24);
						int dob = (int) (i / 365);
						profileView.setAge(dob);
					} catch (Exception e) {
						e.printStackTrace();
					}
					profileView.setEducation(details2.getEducation().getEducation_name());
					profileView.setOccupation(details2.getOccupation().getOccupation_name());
					profileView.setCaste(details2.getCaste().getCaste_name());
					profileView.setVisitorUserId(details2.getUserModel().getUserId());
					criteria = session.createCriteria(UserModelImagesName.class);
					criteria.add(Restrictions.eq("userModel", details2.getUserModel()));
					UserModelImagesName imagesName = (UserModelImagesName) criteria.uniqueResult();
					try {
						String s[] = imagesName.getImageNames().split("#");
						List<String> lst = new ArrayList<>();
						lst = Arrays.asList(s);
						profileView.setImageObject(lst);
					} catch (Exception e) {
						e.printStackTrace();
					}
					try {
						HttpSession httpSession = request.getSession();
						UserModel m = (UserModel) httpSession.getAttribute("ActUserId");
						criteria = session.createCriteria(VisitedProfileUserWithDate.class);
						criteria.add(Restrictions.eq("userModel", m));
						criteria.add(Restrictions.eq("visitedUserModel", details2.getUserModel()));
						VisitedProfileUserWithDate user = (VisitedProfileUserWithDate) criteria.uniqueResult();
						if (user != null) {
							profileView.setViewedOnDate(user.getProfileViewedDate());
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					profileViewsOne.add(profileView);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		map.put("ByOther", profileViewsOne);
		return map;
	}

	@Override
	public Map<String, List<UserProfileViewForMessages>> getlistOfAcceptedInvitationByUser(HttpServletRequest request,
			UserModel userModel) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(SortlistedProfileUserWithDate.class);
		criteria.add(Restrictions.eq("userModel", userModel));
		criteria.add(Restrictions.eq("status", "ACCEPTED"));
		criteria.addOrder(Order.desc("sortlistedDate"));
		Criteria criteriaOne = session.createCriteria(SortlistedProfileUserWithDate.class);
		criteriaOne.add(Restrictions.eq("sortlistedUserModel", userModel));
		criteriaOne.add(Restrictions.eq("status", "ACCEPTED"));
		criteriaOne.addOrder(Order.desc("sortlistedDate"));
		List<SortlistedProfileUserWithDate> details = criteria.list();
		List<SortlistedProfileUserWithDate> detailsOne = criteriaOne.list();
		List<UserModelDetails> userModelDetails = new ArrayList<>();
		List<UserProfileViewForMessages> profileViews = new ArrayList<>();
		List<UserProfileViewForMessages> profileViewsOne = new ArrayList<>();
		Criteria criteria1;
		Map<String, List<UserProfileViewForMessages>> map = new HashMap<>();
		for (SortlistedProfileUserWithDate messagesEmail : details) {
			try {
				criteria1 = session.createCriteria(UserModelDetails.class);
				criteria1.add(Restrictions.eq("userModel", messagesEmail.getSortlistedUserModel()));
				if (criteria1.list().size() > 0) {
					UserModelDetails details2 = (UserModelDetails) criteria1.uniqueResult();
					UserProfileViewForMessages profileView = new UserProfileViewForMessages();
					profileView.setAboutHer(details2.getAboutMe());
					profileView.setName(details2.getUserModel().getFirstName());
					profileView.setLocation(details2.getDistricts().getDistrict_Name() + ","
							+ details2.getState_Country().getState_Name());
					profileView.setMessage("you have sent INVITATION on");
					profileView.setCommunication_Type(messagesEmail.getStatus());
					profileView.setCommunication_date(messagesEmail.getSortlistedDate());
					profileView.setStatus_date(messagesEmail.getStatusDate());
					try {
						Date date = details2.getDateOfBirth();
						Calendar calendar = new GregorianCalendar();
						calendar.setTime(date);
						Date d1 = new GregorianCalendar(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
								calendar.get(Calendar.DAY_OF_MONTH), 23, 59).getTime();
						Date today = new Date();
						long diff = today.getTime() - d1.getTime();
						long i = diff / (1000 * 60 * 60 * 24);
						int dob = (int) (i / 365);
						profileView.setAge(dob);
					} catch (Exception e) {
						e.printStackTrace();
					}
					profileView.setEducation(details2.getEducation().getEducation_name());
					profileView.setOccupation(details2.getOccupation().getOccupation_name());
					profileView.setCaste(details2.getCaste().getCaste_name());
					profileView.setVisitorUserId(details2.getUserModel().getUserId());
					criteria = session.createCriteria(UserModelImagesName.class);
					criteria.add(Restrictions.eq("userModel", details2.getUserModel()));
					UserModelImagesName imagesName = (UserModelImagesName) criteria.uniqueResult();
					try {
						String s[] = imagesName.getImageNames().split("#");
						List<String> lst = new ArrayList<>();
						lst = Arrays.asList(s);
						profileView.setImageObject(lst);
					} catch (Exception e) {
						e.printStackTrace();
					}
					try {
						HttpSession httpSession = request.getSession();
						UserModel m = (UserModel) httpSession.getAttribute("ActUserId");
						criteria = session.createCriteria(VisitedProfileUserWithDate.class);
						criteria.add(Restrictions.eq("userModel", m));
						criteria.add(Restrictions.eq("visitedUserModel", details2.getUserModel()));
						VisitedProfileUserWithDate user = (VisitedProfileUserWithDate) criteria.uniqueResult();
						if (user != null) {
							profileView.setViewedOnDate(user.getProfileViewedDate());
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					profileViews.add(profileView);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		map.put("ByYou", profileViews);
		for (SortlistedProfileUserWithDate messagesEmail : detailsOne) {
			try {
				criteria1 = session.createCriteria(UserModelDetails.class);
				criteria1.add(Restrictions.eq("userModel", messagesEmail.getUserModel()));
				if (criteria1.list().size() > 0) {
					UserModelDetails details2 = (UserModelDetails) criteria1.uniqueResult();
					UserProfileViewForMessages profileView = new UserProfileViewForMessages();
					profileView.setAboutHer(details2.getAboutMe());
					profileView.setName(details2.getUserModel().getFirstName());
					profileView.setLocation(details2.getDistricts().getDistrict_Name() + ","
							+ details2.getState_Country().getState_Name());
					profileView.setMessage(details2.getUserModel().getFirstName() + "sent you INVITATION on");
					profileView.setCommunication_Type(messagesEmail.getStatus());
					profileView.setCommunication_date(messagesEmail.getSortlistedDate());
					profileView.setStatus_date(messagesEmail.getStatusDate());
					try {
						Date date = details2.getDateOfBirth();

						Calendar calendar = new GregorianCalendar();
						calendar.setTime(date);
						Date d1 = new GregorianCalendar(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
								calendar.get(Calendar.DAY_OF_MONTH), 23, 59).getTime();
						Date today = new Date();
						long diff = today.getTime() - d1.getTime();
						long i = diff / (1000 * 60 * 60 * 24);
						int dob = (int) (i / 365);
						profileView.setAge(dob);
					} catch (Exception e) {
						e.printStackTrace();
					}
					profileView.setEducation(details2.getEducation().getEducation_name());
					profileView.setOccupation(details2.getOccupation().getOccupation_name());
					profileView.setCaste(details2.getCaste().getCaste_name());
					profileView.setVisitorUserId(details2.getUserModel().getUserId());
					criteria = session.createCriteria(UserModelImagesName.class);
					criteria.add(Restrictions.eq("userModel", details2.getUserModel()));
					UserModelImagesName imagesName = (UserModelImagesName) criteria.uniqueResult();
					try {
						String s[] = imagesName.getImageNames().split("#");
						List<String> lst = new ArrayList<>();
						lst = Arrays.asList(s);
						profileView.setImageObject(lst);
					} catch (Exception e) {
						e.printStackTrace();
					}
					try {
						HttpSession httpSession = request.getSession();
						UserModel m = (UserModel) httpSession.getAttribute("ActUserId");
						criteria = session.createCriteria(VisitedProfileUserWithDate.class);
						criteria.add(Restrictions.eq("userModel", m));
						criteria.add(Restrictions.eq("visitedUserModel", details2.getUserModel()));
						VisitedProfileUserWithDate user = (VisitedProfileUserWithDate) criteria.uniqueResult();
						if (user != null) {
							profileView.setViewedOnDate(user.getProfileViewedDate());
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					profileViewsOne.add(profileView);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		map.put("ByOther", profileViewsOne);
		return map;
	}

	@Override
	public Map<String, List<UserProfileViewForMessages>> getlistOfRejectedInvitationByUser(HttpServletRequest request,
			UserModel userModel) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(SortlistedProfileUserWithDate.class);
		criteria.add(Restrictions.eq("userModel", userModel));
		criteria.add(Restrictions.eq("status", "REJECTED"));
		criteria.addOrder(Order.desc("sortlistedDate"));
		Criteria criteriaOne = session.createCriteria(SortlistedProfileUserWithDate.class);
		criteriaOne.add(Restrictions.eq("sortlistedUserModel", userModel));
		criteriaOne.add(Restrictions.eq("status", "REJECTED"));
		criteriaOne.addOrder(Order.desc("sortlistedDate"));
		List<SortlistedProfileUserWithDate> details = criteria.list();
		List<SortlistedProfileUserWithDate> detailsOne = criteriaOne.list();
		List<UserModelDetails> userModelDetails = new ArrayList<>();
		List<UserProfileViewForMessages> profileViews = new ArrayList<>();
		List<UserProfileViewForMessages> profileViewsOne = new ArrayList<>();
		Criteria criteria1;
		Map<String, List<UserProfileViewForMessages>> map = new HashMap<>();
		for (SortlistedProfileUserWithDate messagesEmail : details) {
			try {
				criteria1 = session.createCriteria(UserModelDetails.class);
				criteria1.add(Restrictions.eq("userModel", messagesEmail.getSortlistedUserModel()));
				if (criteria1.list().size() > 0) {
					UserModelDetails details2 = (UserModelDetails) criteria1.uniqueResult();
					UserProfileViewForMessages profileView = new UserProfileViewForMessages();
					profileView.setAboutHer(details2.getAboutMe());
					profileView.setName(details2.getUserModel().getFirstName());
					profileView.setLocation(details2.getDistricts().getDistrict_Name() + ","
							+ details2.getState_Country().getState_Name());
					profileView.setMessage("you have sent INVITATION on");
					profileView.setCommunication_Type(messagesEmail.getStatus());
					profileView.setCommunication_date(messagesEmail.getSortlistedDate());
					profileView.setStatus_date(messagesEmail.getStatusDate());
					try {
						Date date = details2.getDateOfBirth();
						Calendar calendar = new GregorianCalendar();
						calendar.setTime(date);
						Date d1 = new GregorianCalendar(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
								calendar.get(Calendar.DAY_OF_MONTH), 23, 59).getTime();
						Date today = new Date();
						long diff = today.getTime() - d1.getTime();
						long i = diff / (1000 * 60 * 60 * 24);
						int dob = (int) (i / 365);
						profileView.setAge(dob);
					} catch (Exception e) {
						e.printStackTrace();
					}
					profileView.setEducation(details2.getEducation().getEducation_name());
					profileView.setOccupation(details2.getOccupation().getOccupation_name());
					profileView.setCaste(details2.getCaste().getCaste_name());
					profileView.setVisitorUserId(details2.getUserModel().getUserId());
					criteria = session.createCriteria(UserModelImagesName.class);
					criteria.add(Restrictions.eq("userModel", details2.getUserModel()));

					UserModelImagesName imagesName = (UserModelImagesName) criteria.uniqueResult();
					try {
						String s[] = imagesName.getImageNames().split("#");
						List<String> lst = new ArrayList<>();
						lst = Arrays.asList(s);
						profileView.setImageObject(lst);
					} catch (Exception e) {
						e.printStackTrace();
					}
					try {
						HttpSession httpSession = request.getSession();
						UserModel m = (UserModel) httpSession.getAttribute("ActUserId");
						criteria = session.createCriteria(VisitedProfileUserWithDate.class);
						criteria.add(Restrictions.eq("userModel", m));
						criteria.add(Restrictions.eq("visitedUserModel", details2.getUserModel()));
						VisitedProfileUserWithDate user = (VisitedProfileUserWithDate) criteria.uniqueResult();
						if (user != null) {
							profileView.setViewedOnDate(user.getProfileViewedDate());
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					profileViews.add(profileView);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		map.put("ByYou", profileViews);
		for (SortlistedProfileUserWithDate messagesEmail : detailsOne) {
			try {
				criteria1 = session.createCriteria(UserModelDetails.class);
				criteria1.add(Restrictions.eq("userModel", messagesEmail.getUserModel()));
				if (criteria1.list().size() > 0) {
					UserModelDetails details2 = (UserModelDetails) criteria1.uniqueResult();
					UserProfileViewForMessages profileView = new UserProfileViewForMessages();
					profileView.setAboutHer(details2.getAboutMe());
					profileView.setName(details2.getUserModel().getFirstName());
					profileView.setLocation(details2.getDistricts().getDistrict_Name() + ","
							+ details2.getState_Country().getState_Name());
					profileView.setMessage(details2.getUserModel().getFirstName() + "sent you INVITATION on");
					profileView.setCommunication_Type(messagesEmail.getStatus());
					profileView.setCommunication_date(messagesEmail.getSortlistedDate());
					profileView.setStatus_date(messagesEmail.getStatusDate());
					try {
						Date date = details2.getDateOfBirth();
						Calendar calendar = new GregorianCalendar();
						calendar.setTime(date);
						Date d1 = new GregorianCalendar(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
								calendar.get(Calendar.DAY_OF_MONTH), 23, 59).getTime();
						Date today = new Date();
						long diff = today.getTime() - d1.getTime();
						long i = diff / (1000 * 60 * 60 * 24);
						int dob = (int) (i / 365);
						profileView.setAge(dob);
					} catch (Exception e) {
						e.printStackTrace();
					}
					profileView.setEducation(details2.getEducation().getEducation_name());
					profileView.setOccupation(details2.getOccupation().getOccupation_name());
					profileView.setCaste(details2.getCaste().getCaste_name());
					profileView.setVisitorUserId(details2.getUserModel().getUserId());
					criteria = session.createCriteria(UserModelImagesName.class);
					criteria.add(Restrictions.eq("userModel", details2.getUserModel()));
					UserModelImagesName imagesName = (UserModelImagesName) criteria.uniqueResult();
					try {
						String s[] = imagesName.getImageNames().split("#");
						List<String> lst = new ArrayList<>();
						lst = Arrays.asList(s);
						profileView.setImageObject(lst);
					} catch (Exception e) {
						e.printStackTrace();
					}
					try {
						HttpSession httpSession = request.getSession();
						UserModel m = (UserModel) httpSession.getAttribute("ActUserId");
						criteria = session.createCriteria(VisitedProfileUserWithDate.class);
						criteria.add(Restrictions.eq("userModel", m));
						criteria.add(Restrictions.eq("visitedUserModel", details2.getUserModel()));
						VisitedProfileUserWithDate user = (VisitedProfileUserWithDate) criteria.uniqueResult();
						if (user != null) {
							profileView.setViewedOnDate(user.getProfileViewedDate());
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					profileViewsOne.add(profileView);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		map.put("ByOther", profileViewsOne);
		return map;
	}

	@Override
	public List<UserProfileView> getlistOfRefineSearchByUser(HttpServletRequest request, UserModel userModel,
			String gender, String refineheightSelectPartnerFirst, String refineheightSelectPartnerSecond,
			String refineageFrom, String refineageTo, String refinematerialStatus, String refinereligionSelectPartner,
			String casteSelect, String refineoccupationCategorySelect,String refineDistrict) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(UserModelDetails.class);
		criteria.add(Restrictions.eq("gender", gender));
		criteria.addOrder(Order.desc("registrationDate"));
		List<State_Districts>districtList=new ArrayList<>();
		if(refineDistrict.length()!=0 ||! (refineDistrict.equalsIgnoreCase("0")))
		{
			try {
				String arr[]=refineDistrict.split(",");
				for (String string : arr) {
					State_Districts dist=new State_Districts();
					dist.setState_district_id(Integer.parseInt(string));
					districtList.add(dist);
				}
				
			} catch (Exception e) {
			}
		}
		if(districtList.size()!=0)
		{
			criteria.add(Restrictions.in("districts",districtList));
		}
		if (!refineheightSelectPartnerFirst.equalsIgnoreCase("0")
				&& !refineheightSelectPartnerSecond.equalsIgnoreCase("0")) {
			criteria.add(Restrictions.ge("height", Double.parseDouble(refineheightSelectPartnerFirst)));
			criteria.add(Restrictions.le("height", Double.parseDouble(refineheightSelectPartnerSecond)));
		}
		if (!refineageFrom.equalsIgnoreCase("0") && !refineageTo.equalsIgnoreCase("0")) {
			Calendar now = Calendar.getInstance();
			now = Calendar.getInstance();
			Date actFromDate = null;
			Date actToDate = null;
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				now.add(Calendar.YEAR, -Integer.parseInt(refineageFrom));
				actFromDate = df.parse(now.get(Calendar.YEAR) + "-" + now.get(Calendar.MONTH) + "-"
						+ now.get(Calendar.DATE) + " 00:00:00");
				now = Calendar.getInstance();
				now.add(Calendar.YEAR, -Integer.parseInt(refineageTo));
				actToDate = df.parse(now.get(Calendar.YEAR) + "-" + now.get(Calendar.MONTH) + "-"
						+ now.get(Calendar.DATE) + " 23:59:59");
				criteria.add(Restrictions.between("dateOfBirth", actToDate, actFromDate));

			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if (!refinematerialStatus.equalsIgnoreCase("0")) {
			criteria.add(Restrictions.eq("materialStatus", refinematerialStatus));
		}
		if (!refinereligionSelectPartner.equalsIgnoreCase("0")) {
			Religion religion = new Religion();
			religion.setReligion_id(Long.parseLong(refinereligionSelectPartner));
			criteria.createCriteria("caste", "c");
			criteria.add(Restrictions.eq("c.religion_id", religion));
		}
		if (!refinereligionSelectPartner.equalsIgnoreCase("0")) {
			if (!casteSelect.equalsIgnoreCase("0")) {
				Caste caste = new Caste();
				caste.setCaste_id(Long.parseLong(casteSelect));
				criteria.add(Restrictions.eq("caste", caste));
			}
		}
		if (!refineoccupationCategorySelect.equalsIgnoreCase("0")) {
			Occupation category = new Occupation();
			category.setOccupation_id(Long.parseLong(refineoccupationCategorySelect));
			criteria.add(Restrictions.eq("occupation", category));
		}
		
		Status stat = new Status();
		stat.setStatus_id(1);
		Disjunction or = Restrictions.disjunction();
		or.add(Restrictions.isNull("status"));
		or.add(Restrictions.eq("status", stat));
		criteria.add(or);
		criteria.addOrder(Order.desc("registrationDate"));
		criteria.setMaxResults(10);
		List<UserModelDetails> userModelDetails = criteria.list();
		List<UserProfileView> profileViews = new ArrayList<>();
		for (UserModelDetails details : userModelDetails) {
			try {
				UserModelDetails details2 = details;
				UserProfileView profileView = new UserProfileView();
				profileView.setAboutHer(details2.getAboutMe());
				profileView.setName(details2.getUserModel().getFirstName());
				profileView.setLocation(
						details2.getDistricts().getDistrict_Name() + "," + details2.getState_Country().getState_Name());
				try {
					Date date = details2.getDateOfBirth();
					Calendar calendar = new GregorianCalendar();
					calendar.setTime(date);
					Date d1 = new GregorianCalendar(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
							calendar.get(Calendar.DAY_OF_MONTH), 23, 59).getTime();
					Date today = new Date();
					long diff = today.getTime() - d1.getTime();
					long i = diff / (1000 * 60 * 60 * 24);
					int dob = (int) (i / 365);
					profileView.setAge(dob);
				} catch (Exception e) {
					e.printStackTrace();
				}
				profileView.setEducation(details2.getEducation().getEducation_name());
				profileView.setOccupation(details2.getOccupation().getOccupation_name());
				profileView.setCaste(details2.getCaste().getCaste_name());
				profileView.setVisitorUserId(details2.getUserModel().getUserId());
				criteria = session.createCriteria(UserModelImagesName.class);
				criteria.add(Restrictions.eq("userModel", details2.getUserModel()));

				UserModelImagesName imagesName = (UserModelImagesName) criteria.uniqueResult();
				try {
					String s[] = imagesName.getImageNames().split("#");
					List<String> lst = new ArrayList<>();
					lst = Arrays.asList(s);
					profileView.setImageObject(lst);
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					HttpSession httpSession = request.getSession();
					UserModel m = (UserModel) httpSession.getAttribute("ActUserId");
					criteria = session.createCriteria(VisitedProfileUserWithDate.class);
					criteria.add(Restrictions.eq("userModel", m));
					criteria.add(Restrictions.eq("visitedUserModel", details2.getUserModel()));
					VisitedProfileUserWithDate user = (VisitedProfileUserWithDate) criteria.uniqueResult();
					if (user != null) {
						profileView.setViewedOnDate(user.getProfileViewedDate());
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				profileViews.add(profileView);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return profileViews;
	}

	@Override
	public void updateUserFullName(String userId, String name) {
		try {
			Session session = sessionFactory.getCurrentSession();
			String hql = "update user set Full_Name= :Full_Name " + "WHERE ID= :ID";
			Query query = session.createSQLQuery(hql);
			query.setParameter("Full_Name", name);
			query.setParameter("ID", Long.parseLong(userId));
			int result = query.executeUpdate();
			} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<UserModelDetails> getListOfUserModelDetailsByGender(String gender, HttpServletRequest request) {
		Session session = sessionFactory.getCurrentSession();
		try {
			List<UserModelDetails> usermodelDetailsList = null;
			Criteria criteria = session.createCriteria(UserModelDetails.class);
			criteria.add(Restrictions.eq("gender", gender));
			usermodelDetailsList = criteria.list();
			return usermodelDetailsList;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			}
		return null;
	}

	@Override
	public void saveOnlineUserInformation(UserModel model, String sex) {
		try {
			Session session = sessionFactory.getCurrentSession();
			OnlineUser onlineObj = new OnlineUser();
			onlineObj.setLoginDate(new Date());
			onlineObj.setUserModel(model);
			onlineObj.setSex(sex);
			session.saveOrUpdate(onlineObj);
			} catch (Exception e) {
		}
	}

	@Override
	public void deleteOnlineUser(UserModel usermodel) {
		try {
			Session session = sessionFactory.getCurrentSession();
			Query query = session.createQuery("DELETE FROM OnlineUser WHERE userModel=:userModel");
			query.setParameter("userModel", usermodel);
			query.executeUpdate();
			} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public OnlineUser getonlineUser(UserModel usermodel) {
		try {
			Session session = sessionFactory.getCurrentSession();
			Criteria criteria = session.createCriteria(OnlineUser.class);
			criteria.add(Restrictions.eq("userModel", usermodel));
			OnlineUser user = (OnlineUser) criteria.uniqueResult();
			return user;
		} catch (Exception e) {
		}
		return null;
	}

	@Override
	public void updateOnlineUser(UserModel usermodel) {
		try {
			Session session = sessionFactory.getCurrentSession();
			Query query = session.createQuery("update OnlineUser set loginDate =:loginDate where userModel=:userModel");
			query.setParameter("userModel", usermodel);
			query.setParameter("loginDate", new Date());
			query.executeUpdate();
			} catch (Exception e) {

			e.printStackTrace();
		}
	}

	@Override
	public List<OnlineUser> getListOfOnlineUser(String sex) {
		try {
			Session session = sessionFactory.getCurrentSession();
			Criteria criteria = session.createCriteria(OnlineUser.class);
			criteria.add(Restrictions.eq("sex", sex).ignoreCase());
			List<OnlineUser> userLis = criteria.list();
			return userLis;
		} catch (Exception e) {
		}
		return null;
	}

	@Override
	public List<UserProfileView> getListOFProfileForChattingAccordingToGender(String gender,
			HttpServletRequest request) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(UserModelDetails.class);
		criteria.addOrder(Order.desc("registrationDate"));
		criteria.add(Restrictions.eq("gender", gender));
		criteria.setMaxResults(10);
		List<UserModelDetails> userModelDetails = criteria.list();
		List<UserProfileView> profileViews = new ArrayList<>();
		for (UserModelDetails userModelDetails2 : userModelDetails) {
			try {
				OnlineUser onulinuse = userService.getonlineUser(userModelDetails2.getUserModel());
				if (onulinuse != null) {
					UserProfileView profileView = new UserProfileView();
					profileView.setAboutHer(userModelDetails2.getAboutMe());
					profileView.setName(userModelDetails2.getUserModel().getFirstName());
					profileView.setLocation(userModelDetails2.getDistricts().getDistrict_Name() + ","
							+ userModelDetails2.getState_Country().getState_Name());
					try {
						Date date = userModelDetails2.getDateOfBirth();

						Calendar calendar = new GregorianCalendar();
						calendar.setTime(date);
						Date d1 = new GregorianCalendar(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
								calendar.get(Calendar.DAY_OF_MONTH), 23, 59).getTime();
						Date today = new Date();
						long diff = today.getTime() - d1.getTime();
						long i = diff / (1000 * 60 * 60 * 24);
						int dob = (int) (i / 365);
						profileView.setAge(dob);
					} catch (Exception e) {
					}
					profileView.setEducation(userModelDetails2.getEducation().getEducation_name());
					profileView.setOccupation(userModelDetails2.getOccupation().getOccupation_name());
					profileView.setCaste(userModelDetails2.getCaste().getCaste_name());
					profileView.setVisitorUserId(userModelDetails2.getUserModel().getUserId());
					profileView.setUserId(userModelDetails2.getUserModel().getUserId());
					criteria = session.createCriteria(UserModelImagesName.class);
					criteria.add(Restrictions.eq("userModel", userModelDetails2.getUserModel()));
					UserModelImagesName imagesName = (UserModelImagesName) criteria.uniqueResult();
					try {
						String s[] = imagesName.getImageNames().split("#");
						List<String> lst = new ArrayList<>();
						lst = Arrays.asList(s);
						profileView.setImageObject(lst);
					} catch (Exception e) {
					}
					try {
						HttpSession httpSession = request.getSession();
						UserModel m = (UserModel) httpSession.getAttribute("ActUserId");
						criteria = session.createCriteria(VisitedProfileUserWithDate.class);
						criteria.add(Restrictions.eq("userModel", m));
						criteria.add(Restrictions.eq("visitedUserModel", userModelDetails2.getUserModel()));
						VisitedProfileUserWithDate user = (VisitedProfileUserWithDate) criteria.uniqueResult();
						if (user != null) {
							profileView.setViewedOnDate(user.getProfileViewedDate());
						}
					} catch (Exception e) {
					}
					profileViews.add(profileView);
				}
			} catch (Exception e) {

			}
		}
		return profileViews;
	}

	@Override
	public void updateUserModelDetails(UserModelDetails usermodeldetails) {
		try {
			String email = usermodeldetails.getEmail() + new Date().getTime();
			String mob = usermodeldetails.getMobileNum() + new Date().getTime();
			Status stat = new Status();
			stat.setStatus_id(2);
			usermodeldetails.setStatus(stat);
			usermodeldetails.setEmail(email);
			usermodeldetails.setMobileNum(mob);
			Session session = sessionFactory.getCurrentSession();
			session.update(usermodeldetails);
			} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<AdminUserModel> getListOfUserModelDetailsForAdminPanel() {
		Status stat = new Status();
		stat.setStatus_id(1);
		Session session = sessionFactory.getCurrentSession();
		Criteria cr = session.createCriteria(UserModelDetails.class);
		cr.setFirstResult(0);
		//cr.addOrder(Order.desc("userDetailsId"));
		cr.createCriteria("userModel", "u");
		cr.setProjection(Projections.projectionList().add(Projections.property("u.userId"), "userId")
				.add(Projections.property("u.firstName"), "firstName")
				.add(Projections.property("registrationDate"), "registrationDate")
				.add(Projections.property("dateOfBirth"), "dateOfBirth")
				.add(Projections.property("u.kit"), "kit")
				.add(Projections.property("mobileNum"), "mobileNum")    
				.add(Projections.property("gender"), "gender")
				
				)
				.setResultTransformer(Transformers.aliasToBean(AdminUserModel.class));

		//deleted user disabled using following lines
				Disjunction or = Restrictions.disjunction();
				or.add(Restrictions.isNull("status"));
				or.add(Restrictions.eq("status", stat));
				cr.add(or);
				cr.addOrder(Order.desc("registrationDate"));
				cr.setMaxResults(100);
		List<AdminUserModel> list = cr.list();
		return list;
	}

	@Override
	public List<UserProfileView> getlistOfRefineSearchByUserExcludeUserModels(HttpServletRequest request,
			UserModel userModel, String gender, String refineheightSelectPartnerFirst,
			String refineheightSelectPartnerSecond, String refineageFrom, String refineageTo,
			String refinematerialStatus, String refinereligionSelectPartner, String casteSelect,
			String refineoccupationCategorySelect, String refineDistrict,
			List<UserModel> excludeUserModelsRefineSearch) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(UserModelDetails.class);
		criteria.add(Restrictions.eq("gender", gender));
		criteria.addOrder(Order.desc("registrationDate"));
		criteria.add(Restrictions.not(Restrictions.in("userModel", excludeUserModelsRefineSearch)));
		List<State_Districts>districtList=new ArrayList<>();
		if(refineDistrict.length()!=0 ||! (refineDistrict.equalsIgnoreCase("0")))
		{
			try {
				StringBuffer builder = new StringBuffer(refineDistrict);
				builder.deleteCharAt(0);
				builder.deleteCharAt(builder.toString().length() - 1);
				String arr[]=builder.toString().split(",");
				for (String string : arr) {
					String withoutQuotesDistrictId = string.replace("\"", "");
					int districtId = Integer.parseInt(withoutQuotesDistrictId);
					State_Districts dist=new State_Districts();
					dist.setState_district_id(districtId);
					districtList.add(dist);
				}
			} catch (Exception e) {
			}
		}
		if(districtList.size()!=0)
		{
			criteria.add(Restrictions.in("districts",districtList));	
		}
		if (!refineheightSelectPartnerFirst.equalsIgnoreCase("0")
				&& !refineheightSelectPartnerSecond.equalsIgnoreCase("0")) {
			criteria.add(Restrictions.ge("height", Double.parseDouble(refineheightSelectPartnerFirst)));
			criteria.add(Restrictions.le("height", Double.parseDouble(refineheightSelectPartnerSecond)));
		}
		if (!refineageFrom.equalsIgnoreCase("0") && !refineageTo.equalsIgnoreCase("0")) {
			Calendar now = Calendar.getInstance();
			now = Calendar.getInstance();
			Date actFromDate = null;
			Date actToDate = null;
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				now.add(Calendar.YEAR, -Integer.parseInt(refineageFrom));
				actFromDate = df.parse(now.get(Calendar.YEAR) + "-" + now.get(Calendar.MONTH) + "-"
						+ now.get(Calendar.DATE) + " 00:00:00");
				now = Calendar.getInstance();
				now.add(Calendar.YEAR, -Integer.parseInt(refineageTo));
				actToDate = df.parse(now.get(Calendar.YEAR) + "-" + now.get(Calendar.MONTH) + "-"
						+ now.get(Calendar.DATE) + " 23:59:59");
				criteria.add(Restrictions.between("dateOfBirth", actToDate, actFromDate));

			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if (!refinematerialStatus.equalsIgnoreCase("0")) {
			criteria.add(Restrictions.eq("materialStatus", refinematerialStatus));
		}
		if (!refinereligionSelectPartner.equalsIgnoreCase("0")) {
			Religion religion = new Religion();
			religion.setReligion_id(Long.parseLong(refinereligionSelectPartner));
			criteria.createCriteria("caste", "c");
			criteria.add(Restrictions.eq("c.religion_id", religion));
		}
		if (!refinereligionSelectPartner.equalsIgnoreCase("0")) {
			if (!casteSelect.equalsIgnoreCase("0")) {
				Caste caste = new Caste();
				caste.setCaste_id(Long.parseLong(casteSelect));
				criteria.add(Restrictions.eq("caste", caste));
			}
		}
		if (!refineoccupationCategorySelect.equalsIgnoreCase("0")) {
			Occupation category = new Occupation();
			category.setOccupation_id(Long.parseLong(refineoccupationCategorySelect));
			criteria.add(Restrictions.eq("occupation", category));
		}
		
		Status stat = new Status();
		stat.setStatus_id(1);
		Disjunction or = Restrictions.disjunction();
		or.add(Restrictions.isNull("status"));
		or.add(Restrictions.eq("status", stat));
		criteria.add(or);
		criteria.addOrder(Order.desc("registrationDate"));
		criteria.setMaxResults(10);
		List<UserModelDetails> userModelDetails = criteria.list();
		List<UserProfileView> profileViews = new ArrayList<>();
		for (UserModelDetails details : userModelDetails) {
			try {
				UserModelDetails details2 = details;
				UserProfileView profileView = new UserProfileView();
				profileView.setAboutHer(details2.getAboutMe());
				profileView.setName(details2.getUserModel().getFirstName());
				profileView.setLocation(
						details2.getDistricts().getDistrict_Name() + "," + details2.getState_Country().getState_Name());
				try {
					Date date = details2.getDateOfBirth();
					Calendar calendar = new GregorianCalendar();
					calendar.setTime(date);
					Date d1 = new GregorianCalendar(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
							calendar.get(Calendar.DAY_OF_MONTH), 23, 59).getTime();
					Date today = new Date();
					long diff = today.getTime() - d1.getTime();
					long i = diff / (1000 * 60 * 60 * 24);
					int dob = (int) (i / 365);
					profileView.setAge(dob);
				} catch (Exception e) {
					e.printStackTrace();
				}
				profileView.setEducation(details2.getEducation().getEducation_name());
				profileView.setOccupation(details2.getOccupation().getOccupation_name());
				profileView.setCaste(details2.getCaste().getCaste_name());
				profileView.setVisitorUserId(details2.getUserModel().getUserId());
				criteria = session.createCriteria(UserModelImagesName.class);
				criteria.add(Restrictions.eq("userModel", details2.getUserModel()));

				UserModelImagesName imagesName = (UserModelImagesName) criteria.uniqueResult();
				try {
					String s[] = imagesName.getImageNames().split("#");
					List<String> lst = new ArrayList<>();
					lst = Arrays.asList(s);
					profileView.setImageObject(lst);
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					HttpSession httpSession = request.getSession();
					UserModel m = (UserModel) httpSession.getAttribute("ActUserId");
					criteria = session.createCriteria(VisitedProfileUserWithDate.class);
					criteria.add(Restrictions.eq("userModel", m));
					criteria.add(Restrictions.eq("visitedUserModel", details2.getUserModel()));
					VisitedProfileUserWithDate user = (VisitedProfileUserWithDate) criteria.uniqueResult();
					if (user != null) {
						profileView.setViewedOnDate(user.getProfileViewedDate());
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				profileViews.add(profileView);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return profileViews;
	}

	@Override
	public List<AdminUserModel> getListOfUserModelDetailsForDealerByDealer(Dealer dealer) 
	{
		Status stat = new Status();
		stat.setStatus_id(1);
		Session session = sessionFactory.getCurrentSession();
		Criteria cr = session.createCriteria(UserModelDetails.class);
		cr.createCriteria("userModel", "u");
		cr.setProjection(Projections.projectionList().add(Projections.property("u.userId"), "userId")
				.add(Projections.property("u.firstName"), "firstName")
				.add(Projections.property("registrationDate"), "registrationDate")
				.add(Projections.property("dateOfBirth"), "dateOfBirth")
				.add(Projections.property("mobileNum"), "mobileNum")
				)
				.setResultTransformer(Transformers.aliasToBean(AdminUserModel.class));
		//deleted user disabled using following lines
				Disjunction or = Restrictions.disjunction();
				or.add(Restrictions.isNull("status"));
				or.add(Restrictions.eq("status", stat));
				cr.add(or);
				cr.add(Restrictions.eq("dealer", dealer));
		List<AdminUserModel> list = cr.list();
		return list;
	}

	@Override
	public void saveDeleteUserDetail(UserModel usermodel, UserModel deleteBy) {
		try {
			DeleteUserDetail userDeleteDetails=new DeleteUserDetail();
			userDeleteDetails.setUserModel(usermodel);
			userDeleteDetails.setDeletedBy(deleteBy);
			userDeleteDetails.setDeleteDate(new Date());
			Session session=sessionFactory.getCurrentSession();
			session.save(userDeleteDetails);
			} catch (Exception e) {
		}
	}

	@Override
	public List<DeleteUserDetail> getListOfDeletedUserForAdmin() {
		Session session=sessionFactory.getCurrentSession();
		Criteria criteria=session.createCriteria(DeleteUserDetail.class);
		List<DeleteUserDetail> list=criteria.list();
		return list;
		
	}

	@Override
	public void updateUserModelDetailsWithDealer(UserModelDetails usermodeldetails) {
		try {
			Session session = sessionFactory.getCurrentSession();
			session.update(usermodeldetails);
			} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public UserModel getadminDetails() {
		RoleModel role=new RoleModel();
		role.setId(1);
		Session session=sessionFactory.getCurrentSession();
		Criteria criteria =session.createCriteria(UserModel.class);
		criteria.add(Restrictions.eq("userRoll", role));
		UserModel model=(UserModel) criteria.uniqueResult();
		return model;
	}

	@Override
	public List<AdminUserModel> getListOfUserModelDetailsByEmp(EmployeeRegistrationDetails empModel) {
				Status stat = new Status();
				stat.setStatus_id(1);
				Session session = sessionFactory.getCurrentSession();
				Criteria cr = session.createCriteria(UserModelDetails.class);
				cr.createCriteria("userModel", "u");
				cr.setProjection(Projections.projectionList().add(Projections.property("u.userId"), "userId")
						.add(Projections.property("u.firstName"), "firstName")
						.add(Projections.property("registrationDate"), "registrationDate")
						.add(Projections.property("dateOfBirth"), "dateOfBirth")
						.add(Projections.property("mobileNum"), "mobileNum")
						)
						.setResultTransformer(Transformers.aliasToBean(AdminUserModel.class));
				//deleted user disabled using following lines
						Disjunction or = Restrictions.disjunction();
						or.add(Restrictions.isNull("status"));
						or.add(Restrictions.eq("status", stat));
						cr.add(or);
						cr.add(Restrictions.eq("empId", empModel));
				List<AdminUserModel> list = cr.list();
				return list;
	}

	@Override
	public List<AdminUserModel> getDeletedUserInAdminFormat() {
		try {
			Session session = sessionFactory.getCurrentSession();
			Criteria cr = session.createCriteria(DeleteUserDetail.class);
			cr.createCriteria("userModel", "u");
			cr.createCriteria("deletedBy", "d");
			cr.setProjection(Projections.projectionList().add(Projections.property("id"), "deleteId")
					.add(Projections.property("u.firstName"), "firstName")
					.add(Projections.property("deleteDate"), "deleteDate")
					.add(Projections.property("d.firstName"), "deletedBy")
					)
					.setResultTransformer(Transformers.aliasToBean(AdminUserModel.class));	
			List<AdminUserModel> list1 = cr.list();
			return list1;
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public long getTotalSaleKitCount() 
	{
		try {
			Status status = new Status();
			status.setStatus_id(1);	
			Session session =sessionFactory.getCurrentSession();
			Criteria criteria =session.createCriteria(Kit.class);
			criteria.add(Restrictions.eq("status_id", status));
			criteria.setProjection(Projections.rowCount());
			Long count = (Long)criteria.uniqueResult();
			return count;
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public long getTotalRevenue() 
	{
		try {
			Session session =sessionFactory.getCurrentSession();
			Criteria criteria =session.createCriteria(Kit_Upgrade_Or_Reg_History_Details.class);
			criteria.setProjection(Projections.sum("payment"));
			Double totalSum = (Double)criteria.uniqueResult();
			long totalRevenue = (new Double(totalSum)).longValue();
			return totalRevenue;
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public long getRecentlyAddedUserBy_Date(Date today, Date yesterday) 
	{
		try {
			Session session = sessionFactory.getCurrentSession();
			Criteria criteria =session.createCriteria(UserModelDetails.class);
			criteria.add(Restrictions.between("registrationDate",yesterday, today));
			criteria.setProjection(Projections.rowCount());
			Long count = (Long)criteria.uniqueResult();
			return count;
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public void changeUserRole(RoleModel role, int roleId) {
		role.setId(roleId);
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(role);
		//	return role;
	}

	@Override
	public List<SortlistedProfileUserWithDate> getListOFShortlistedUserProfileByUser(UserModel userModel) {
		try {
			Session session = sessionFactory.getCurrentSession();
			Criteria criteria =session.createCriteria(SortlistedProfileUserWithDate.class);
			criteria.add(Restrictions.eq("userModel", userModel));
			List<SortlistedProfileUserWithDate>sortlistedProfileUserWithDatesList = criteria.list();
			return sortlistedProfileUserWithDatesList;
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<OccupationCategory> getAllOccupationCatogory() {try {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria =session.createCriteria(OccupationCategory.class);
		List<OccupationCategory>occupationCategoriesList = criteria.list();
		return occupationCategoriesList;
	} catch (Exception e)
	{
		e.printStackTrace();
	}
	return null;}

	@Override
	public List<Occupation> getAllOccupations_List() {
		try{
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria =session.createCriteria(Occupation.class);
		List<Occupation>occupationsList = criteria.list();
		return occupationsList;
	} catch (Exception e)
	{
		e.printStackTrace();
	}
	return null;
	}

	@Override
	public List<Religion> getAllReligion() {
		try{
			Session session = sessionFactory.getCurrentSession();
			Criteria criteria =session.createCriteria(Religion.class);
			List<Religion>religionList = criteria.list();
			return religionList;
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Caste> getAllCast() {
		try{
			Session session = sessionFactory.getCurrentSession();
			Criteria criteria =session.createCriteria(Caste.class);
			List<Caste>castList = criteria.list();
			return castList;
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<String> getListOfAllRegisteredMobileNumbers() {
		try {
			Session session =  sessionFactory.getCurrentSession();
			String hql = "select mobileNum from UserModelDetails";
			Query query = session.createQuery(hql);
			List<String> allRegisteredMobileNumbersList = query.list();
            return allRegisteredMobileNumbersList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public List<UserProfileView> getListOFProfileForHomeAccordingToGenderAndUserName(
			String gender, HttpServletRequest request, String userName, String castId, String occupationId, String stateId, String districtId) {
		
		Status stat = new Status();
		stat.setStatus_id(1);
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(UserModelDetails.class);
		if(!occupationId.isEmpty() && !occupationId.equalsIgnoreCase("0"))
		{
            Occupation occupation = new Occupation();
            occupation.setOccupation_id(Long.parseLong(occupationId));
			criteria.add(Restrictions.eq("occupation", occupation));
		}
		if(!castId.isEmpty() && !castId.equalsIgnoreCase("0"))
		{
			Caste caste = new Caste();
			caste.setCaste_id(Long.parseLong(castId));
			criteria.add(Restrictions.eq("caste", caste));
		}
		if(!stateId.isEmpty() && !stateId.equalsIgnoreCase("0"))
		{
			State_Country state_Country = new State_Country();
			state_Country.setState_Country_id(Integer.parseInt(stateId));
			criteria.add(Restrictions.eq("state_Country", state_Country));
		}
		if(!districtId.isEmpty() && !districtId.equalsIgnoreCase("0"))
		{
			State_Districts state_Districts = new State_Districts();
			state_Districts.setState_district_id(Integer.parseInt(districtId));
			criteria.add(Restrictions.eq("districts", state_Districts));
		}
		criteria.createAlias("userModel", "user"); 
		criteria.addOrder(Order.desc("registrationDate"));
		//criteria.add(Restrictions.eq("gender", gender));
		criteria.add(Restrictions.like("user.userName", userName+"%"));
		Disjunction or = Restrictions.disjunction();
		or.add(Restrictions.isNull("status"));
		or.add(Restrictions.eq("status", stat));
		criteria.add(or);
		criteria.addOrder(Order.desc("registrationDate"));
		criteria.setMaxResults(10);
		List<UserModelDetails> userModelDetails = criteria.list();
		List<UserProfileView> profileViews = new ArrayList<>();
		for (UserModelDetails userModelDetails2 : userModelDetails) {

			UserProfileView profileView = new UserProfileView();

			profileView.setAboutHer(userModelDetails2.getAboutMe());
			profileView.setName(userModelDetails2.getUserModel().getFirstName());
			profileView.setLocation(userModelDetails2.getDistricts().getDistrict_Name() + ","
					+ userModelDetails2.getState_Country().getState_Name());
			try {
				Date date = userModelDetails2.getDateOfBirth();

				Calendar calendar = new GregorianCalendar();
				calendar.setTime(date);
				Date d1 = new GregorianCalendar(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
						calendar.get(Calendar.DAY_OF_MONTH), 23, 59).getTime();
				Date today = new Date();
				long diff = today.getTime() - d1.getTime();
				long i = diff / (1000 * 60 * 60 * 24);
				int dob = (int) (i / 365);
				profileView.setAge(dob);
			} catch (Exception e) {

			}
			profileView.setEducation(userModelDetails2.getEducation().getEducation_name());
			profileView.setOccupation(userModelDetails2.getOccupation().getOccupation_name());
			profileView.setCaste(userModelDetails2.getCaste().getCaste_name());
			profileView.setVisitorUserId(userModelDetails2.getUserModel().getUserId());
			profileView.setUserId(userModelDetails2.getUserModel().getUserId());

			criteria = session.createCriteria(UserModelImagesName.class);
			criteria.add(Restrictions.eq("userModel", userModelDetails2.getUserModel()));

			UserModelImagesName imagesName = (UserModelImagesName) criteria.uniqueResult();
			try {
				String s[] = imagesName.getImageNames().split("#");
				List<String> lst = new ArrayList<>();
				lst = Arrays.asList(s);
				profileView.setImageObject(lst);
			} catch (Exception e) {

			}
			try {
				HttpSession httpSession = request.getSession();
				UserModel m = (UserModel) httpSession.getAttribute("ActUserId");
				criteria = session.createCriteria(VisitedProfileUserWithDate.class);
				criteria.add(Restrictions.eq("userModel", m));
				criteria.add(Restrictions.eq("visitedUserModel", userModelDetails2.getUserModel()));
				VisitedProfileUserWithDate user = (VisitedProfileUserWithDate) criteria.uniqueResult();
				if (user != null) {
					profileView.setViewedOnDate(user.getProfileViewedDate());
				}
			} catch (Exception e) {

			}

			profileViews.add(profileView);
		}
		return profileViews;
	}
	@Override
	public List<OccupationCategory> getPartnerOccuptionCatagory(
			UserModel userModel) {
		try {
			Session session = sessionFactory.getCurrentSession();
			String sql = "SELECT occupationCategory FROM PartnerOccupationAssociation WHERE user_id=:user_id";
			Query query = session.createQuery(sql);
			query.setParameter("user_id", userModel);
	        List<OccupationCategory> occupationCatagoryList = query.list();
			return occupationCatagoryList;	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}
	@Override
	public List<EducationCategory> getEducationCatagory(UserModel userModel) {
		try {
			Session session = sessionFactory.getCurrentSession();
			String sql = "SELECT educationCategory FROM PartnerEducationAssociation WHERE user_id=:user_id";
			Query query = session.createQuery(sql);
			query.setParameter("user_id", userModel);
	        List<EducationCategory> educationCatagoryList = query.list();
			return educationCatagoryList;	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public List<UserModelDetails> getUserByGender(String gender) {
		try {
			Status status = new Status();
			status.setStatus_id(1);
			Session session = sessionFactory.getCurrentSession();
			String hql = "FROM UserModelDetails WHERE userModel IN (SELECT userModel FROM Kit_Upgrade_Or_Reg_Details) AND gender =:gender AND (status is null OR status=:status)";
			Query query = session.createQuery(hql);
			query.setParameter("status", status);
			query.setParameter("status",null);
			query.setParameter("gender", gender);
			List<UserModelDetails>usermodelList = query.list();
			return usermodelList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public List<UserModelDetails> getUserByGenderAndUserType(String gender) {
		try {
			Status status = new Status();
			status.setStatus_id(1);
			Session session = sessionFactory.getCurrentSession();
			//SELECT * FROM usermodeldetails WHERE userModel_id IN (SELECT ID FROM USER WHERE Kit_id IS NULL) AND gender='Male' AND (STATUS IS NULL OR STATUS=1)

			//String hql = "FROM UserModelDetails WHERE gender =:gender AND (status is null OR status=:status)";
			String hql = "FROM UserModelDetails WHERE userModel IN (SELECT userId FROM UserModel where kit is null) AND gender =:gender AND (status is null OR status=:status)";
			
			Query query = session.createQuery(hql);
			query.setParameter("status", status);
			query.setParameter("status",null);
			query.setParameter("gender", gender);
			List<UserModelDetails>usermodelList = query.list();
			return usermodelList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public long getMaxCountOfUserDetails() {
		try {
			Status status = new Status();
			status.setStatus_id(1);	
			Session session =sessionFactory.getCurrentSession();
			Criteria criteria =session.createCriteria(UserModelDetails.class);
			Disjunction or = Restrictions.disjunction();
			or.add(Restrictions.isNull("status"));
			or.add(Restrictions.eq("status", status));
			criteria.add(or);
			criteria.setProjection(Projections.rowCount());
			Long count = (Long)criteria.uniqueResult();
			return count;
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return 0;
	}
	@Override
	public List<AdminUserModel> getListOfUserModelDetailsForAdminPanelAjax(int pageNumber) {
		
//		Status stat = new Status();
//		stat.setStatus_id(1);
//		Session session = sessionFactory.getCurrentSession();
//		Criteria cr = session.createCriteria(UserModelDetails.class);
//		cr.setFirstResult(pageNumber*100);
//		//cr.addOrder(Order.desc("userDetailsId"));
//		cr.createCriteria("userModel", "u");
//		cr.setProjection(Projections.projectionList().add(Projections.property("u.userId"), "userId")
//				.add(Projections.property("u.firstName"), "firstName")
//				.add(Projections.property("registrationDate"), "registrationDate")
//				.add(Projections.property("dateOfBirth"), "dateOfBirth")
//				.add(Projections.property("u.kit"), "kit")
//				.add(Projections.property("mobileNum"), "mobileNum")    
//				.add(Projections.property("gender"), "gender")
//				
//				)
//				.setResultTransformer(Transformers.aliasToBean(AdminUserModel.class));
//
//		//deleted user disabled using following lines
//		pageNumber++;
//		System.out.println("pageNumber:"+pageNumber);
//				Disjunction or = Restrictions.disjunction();
//				or.add(Restrictions.isNull("status"));
//				or.add(Restrictions.eq("status", stat));
//				cr.add(or);
//				cr.addOrder(Order.desc("registrationDate"));
//				cr.setMaxResults(pageNumber*100);
//		List<AdminUserModel> list = cr.list();
//		//		return list;
		
//		if(costSavingSearchCriteria.getCurrentPage() == 0 || costSavingSearchCriteria.getCurrentPage() == 1){
//			query.setFirstResult(0);
//		}else{
//			query.setFirstResult(((costSavingSearchCriteria.getCurrentPage()*10) -10));
//		}
		


		//int endInd = ((pageNumber+1)*10);
		//System.out.println("startInd:"+startInd+"=======endInd:"+endInd);
		Status stat = new Status();
		stat.setStatus_id(1);
		Session session = sessionFactory.getCurrentSession();
		Criteria cr = session.createCriteria(UserModelDetails.class);
		if(pageNumber == 0 || pageNumber == 1)
		cr.setFirstResult(1);
		else
	    cr.setFirstResult(((pageNumber*1000) -1000));
		//cr.addOrder(Order.desc("userDetailsId"));
		cr.createCriteria("userModel", "u");
		cr.setProjection(Projections.projectionList().add(Projections.property("u.userId"), "userId")
				.add(Projections.property("u.firstName"), "firstName")
				.add(Projections.property("registrationDate"), "registrationDate")
				.add(Projections.property("dateOfBirth"), "dateOfBirth")
				.add(Projections.property("u.kit"), "kit")
				.add(Projections.property("mobileNum"), "mobileNum")    
				.add(Projections.property("gender"), "gender")
				
				)
				.setResultTransformer(Transformers.aliasToBean(AdminUserModel.class));

		//deleted user disabled using following lines
				Disjunction or = Restrictions.disjunction();
				or.add(Restrictions.isNull("status"));
				or.add(Restrictions.eq("status", stat));
				cr.add(or);
				cr.addOrder(Order.desc("registrationDate"));
				cr.setMaxResults(1000);
		List<AdminUserModel> list = cr.list();
		return list;
	}
	@Override
	public List<AdminUserModel> globalSearch(String searchText) {
		Status stat = new Status();
		stat.setStatus_id(1);
		Session session = sessionFactory.getCurrentSession();
		Criteria cr = session.createCriteria(UserModelDetails.class);
		cr.setFirstResult(1);
		cr.createCriteria("userModel", "u");
	   // cr = (Criteria) Restrictions.like("stateName", "%"+searchText+"%");
		cr.add(Restrictions.like("u.firstName", searchText+"%"));
		//cr.add(Restrictions.like("u.userId", "%"+Long.parseLong(searchText)+"%"));
		cr.setProjection(Projections.projectionList().add(Projections.property("u.userId"), "userId")
				.add(Projections.property("u.firstName"), "firstName")
				.add(Projections.property("registrationDate"), "registrationDate")
				.add(Projections.property("dateOfBirth"), "dateOfBirth")
				.add(Projections.property("u.kit"), "kit")
				.add(Projections.property("mobileNum"), "mobileNum")    
				.add(Projections.property("gender"), "gender")
				
				)
				.setResultTransformer(Transformers.aliasToBean(AdminUserModel.class));

		//deleted user disabled using following lines
				Disjunction or = Restrictions.disjunction();
				or.add(Restrictions.isNull("status"));
				or.add(Restrictions.eq("status", stat));
				cr.add(or);
				cr.addOrder(Order.desc("registrationDate"));
				//cr.createAlias("userModel", "user"); 
				
				//cr.setMaxResults(100);
		List<AdminUserModel> list = cr.list();
		return list;
	}
	
	private void addDataTablesFilters(Criteria cr, String search, Map<Integer, String> columnFilters) {
		Status stat = new Status();
		stat.setStatus_id(1);
		Disjunction or = Restrictions.disjunction();
		or.add(Restrictions.isNull("status"));
		or.add(Restrictions.eq("status", stat));
		cr.add(or);

		if (search != null && !search.trim().isEmpty()) {
			Disjunction searchOr = Restrictions.disjunction();
			searchOr.add(Restrictions.like("u.firstName", "%" + search + "%"));
			searchOr.add(Restrictions.like("mobileNum", "%" + search + "%"));
			try {
				searchOr.add(Restrictions.eq("u.userId", Long.parseLong(search)));
			} catch(Exception e) {}
			cr.add(searchOr);
		}

		if (columnFilters != null) {
			for (Map.Entry<Integer, String> entry : columnFilters.entrySet()) {
				int col = entry.getKey();
				String val = entry.getValue();
				if (val == null || val.trim().isEmpty()) continue;
				val = val.trim();
				
				if (col == 0) { // Id
					try { cr.add(Restrictions.eq("u.userId", Long.parseLong(val))); } catch(Exception e) {}
				} else if (col == 1) { // Name
					cr.add(Restrictions.like("u.firstName", "%" + val + "%"));
				} else if (col == 2) { // Gender
					cr.add(Restrictions.like("gender", val + "%"));
				} else if (col == 3) { // Mobile
					cr.add(Restrictions.like("mobileNum", "%" + val + "%"));
				} else if (col == 6) { // User Type (Free/Paid)
					if (val.equalsIgnoreCase("free")) {
						cr.add(Restrictions.isNull("u.kit"));
					} else if (val.equalsIgnoreCase("paid")) {
						cr.add(Restrictions.isNotNull("u.kit"));
					}
				}
			}
		}
	}

	@Override
	public List<AdminUserModel> getRegisteredUsersForDataTable(int start, int length, String search, Map<Integer, String> columnFilters, int sortColumn, String sortDir) {
		Session session = sessionFactory.getCurrentSession();
		Criteria cr = session.createCriteria(UserModelDetails.class);
		cr.createCriteria("userModel", "u");
		
		addDataTablesFilters(cr, search, columnFilters);
		
		// Sorting
		String sortField = "registrationDate"; // default
		boolean isDesc = true;
		if (sortDir != null && sortDir.equalsIgnoreCase("asc")) {
			isDesc = false;
		}
		if (sortColumn == 0) sortField = "u.userId";
		else if (sortColumn == 1) sortField = "u.firstName";
		else if (sortColumn == 2) sortField = "gender";
		else if (sortColumn == 3) sortField = "mobileNum";
		else if (sortColumn == 4) sortField = "registrationDate";
		else if (sortColumn == 5) sortField = "dateOfBirth";
		else if (sortColumn == 6) sortField = "u.kit";

		if (isDesc) {
			cr.addOrder(Order.desc(sortField));
		} else {
			cr.addOrder(Order.asc(sortField));
		}
		
		cr.setFirstResult(start);
		cr.setMaxResults(length);
		
		cr.setProjection(Projections.projectionList()
				.add(Projections.property("u.userId"), "userId")
				.add(Projections.property("u.firstName"), "firstName")
				.add(Projections.property("registrationDate"), "registrationDate")
				.add(Projections.property("dateOfBirth"), "dateOfBirth")
				.add(Projections.property("u.kit"), "kit")
				.add(Projections.property("mobileNum"), "mobileNum")    
				.add(Projections.property("gender"), "gender")
				).setResultTransformer(Transformers.aliasToBean(AdminUserModel.class));

		List<AdminUserModel> list = cr.list();
		return list;
	}

	@Override
	public long getRegisteredUsersForDataTableCount(String search, Map<Integer, String> columnFilters) {
		Session session = sessionFactory.getCurrentSession();
		Criteria cr = session.createCriteria(UserModelDetails.class);
		cr.createCriteria("userModel", "u");
		
		addDataTablesFilters(cr, search, columnFilters);
		
		cr.setProjection(Projections.rowCount());
		Long count = (Long) cr.uniqueResult();
		return count == null ? 0 : count;
	}

	@Override
	public void updateAllUserModelDetailsStatus(int statusId) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("update UserModelDetails set status.status_id = :statusId");
		query.setParameter("statusId", statusId);
		query.executeUpdate();
	}
	@Override
	public List<UserProfileView> getListOFCustomSearchProfiles(String searchId, String searchName, String searchRegDate, String searchDOB, UserModel currentUser) {
		Session session1 = sessionFactory.getCurrentSession();
		Criteria criteria1 = session1.createCriteria(UserModelDetails.class);
		
		criteria1.createAlias("userModel", "um");
		
		if (currentUser != null) {
			UserModelDetails currentDetails = getUserdetails(currentUser);
			if (currentDetails != null && currentDetails.getGender() != null) {
				if (currentDetails.getGender().equalsIgnoreCase("Male")) {
					criteria1.add(Restrictions.eq("gender", "Female"));
				} else if (currentDetails.getGender().equalsIgnoreCase("Female")) {
					criteria1.add(Restrictions.eq("gender", "Male"));
				}
			}
		}
		if (searchId != null && !searchId.trim().isEmpty()) {
			try {
				Long uid = Long.parseLong(searchId.trim());
				criteria1.add(Restrictions.disjunction()
					.add(Restrictions.eq("um.userId", uid))
					.add(Restrictions.eq("um.userName", searchId.trim())));
			} catch (Exception e) {
				criteria1.add(Restrictions.eq("um.userName", searchId.trim()));
			}
		}
		if (searchName != null && !searchName.trim().isEmpty()) {
			criteria1.add(Restrictions.like("um.firstName", "%" + searchName.trim() + "%"));
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if (searchRegDate != null && !searchRegDate.trim().isEmpty()) {
			try {
				Date date = sdf.parse(searchRegDate);
				Calendar cal = Calendar.getInstance();
				cal.setTime(date);
				cal.set(Calendar.HOUR_OF_DAY, 0);
				cal.set(Calendar.MINUTE, 0);
				cal.set(Calendar.SECOND, 0);
				Date start = cal.getTime();
				cal.set(Calendar.HOUR_OF_DAY, 23);
				cal.set(Calendar.MINUTE, 59);
				cal.set(Calendar.SECOND, 59);
				Date end = cal.getTime();
				criteria1.add(Restrictions.between("registrationDate", start, end));
			} catch (Exception e) { e.printStackTrace(); }
		}
		
		if (searchDOB != null && !searchDOB.trim().isEmpty()) {
			try {
				Date date = sdf.parse(searchDOB);
				Calendar cal = Calendar.getInstance();
				cal.setTime(date);
				cal.set(Calendar.HOUR_OF_DAY, 0);
				cal.set(Calendar.MINUTE, 0);
				cal.set(Calendar.SECOND, 0);
				Date start = cal.getTime();
				cal.set(Calendar.HOUR_OF_DAY, 23);
				cal.set(Calendar.MINUTE, 59);
				cal.set(Calendar.SECOND, 59);
				Date end = cal.getTime();
				criteria1.add(Restrictions.between("dateOfBirth", start, end));
			} catch (Exception e) { e.printStackTrace(); }
		}
		
		Status stat = new Status();
		stat.setStatus_id(1);
		Disjunction or = Restrictions.disjunction();
		or.add(Restrictions.isNull("status"));
		or.add(Restrictions.eq("status", stat));
		criteria1.add(or);
		
		criteria1.setMaxResults(20);
		
		List<UserModelDetails> userModelDetails = criteria1.list();
		List<UserProfileView> profileViews = new ArrayList<>();
		
		for (UserModelDetails userModelDetails2 : userModelDetails) {
			UserProfileView profileView = new UserProfileView();
			profileView.setAboutHer(userModelDetails2.getAboutMe());
			profileView.setName(userModelDetails2.getUserModel().getFirstName());
			if (userModelDetails2.getDistricts() != null && userModelDetails2.getState_Country() != null) {
				profileView.setLocation(userModelDetails2.getDistricts().getDistrict_Name() + ","
						+ userModelDetails2.getState_Country().getState_Name());
			}
			try {
				Date date = userModelDetails2.getDateOfBirth();
				Calendar calendar = new GregorianCalendar();
				calendar.setTime(date);
				Date d1 = new GregorianCalendar(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
						calendar.get(Calendar.DAY_OF_MONTH), 23, 59).getTime();
				Date today = new Date();
				long diff = today.getTime() - d1.getTime();
				long i = diff / (1000 * 60 * 60 * 24);
				int dob = (int) (i / 365);
				profileView.setAge(dob);
			} catch (Exception e) {
			}
			if (userModelDetails2.getEducation() != null) {
				profileView.setEducation(userModelDetails2.getEducation().getEducation_name());
			}
			if (userModelDetails2.getOccupation() != null) {
				profileView.setOccupation(userModelDetails2.getOccupation().getOccupation_name());
			}
			if (userModelDetails2.getCaste() != null) {
				profileView.setCaste(userModelDetails2.getCaste().getCaste_name());
			}
			profileView.setVisitorUserId(userModelDetails2.getUserModel().getUserId());
			
			Criteria criteriaImg = session1.createCriteria(UserModelImagesName.class);
			criteriaImg.add(Restrictions.eq("userModel", userModelDetails2.getUserModel()));
			UserModelImagesName imagesName = (UserModelImagesName) criteriaImg.uniqueResult();
			try {
				if(imagesName != null && imagesName.getImageNames() != null) {
					String s[] = imagesName.getImageNames().split("#");
					List<String> lst = new ArrayList<>();
					lst = Arrays.asList(s);
					profileView.setImageObject(lst);
				}
			} catch (Exception e) {
			}
			profileViews.add(profileView);
		}
		return profileViews;
	}

	@Override
	public List<UserProfileView> getTopShortlistedProfiles(int limit, HttpServletRequest request) {
		Session session = sessionFactory.getCurrentSession();
		List<Long> shortlistedCandidateIds = new ArrayList<>();
		try {
			// Query shortlisted users ordered by shortlist count desc (broad pool up to 50)
			String hql = "select s.sortlistedUserModel.userId, count(s.sortlistedUserModel.userId) as scount "
					+ "from SortlistedProfileUserWithDate s "
					+ "where s.sortlistedUserModel.userId is not null "
					+ "group by s.sortlistedUserModel.userId "
					+ "order by count(s.sortlistedUserModel.userId) desc";
			Query query = session.createQuery(hql);
			query.setMaxResults(50);
			List<Object[]> results = query.list();
			for (Object[] row : results) {
				if (row[0] != null) {
					shortlistedCandidateIds.add((Long) row[0]);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Query fallback candidate users who have images
		List<Long> fallbackCandidateIds = new ArrayList<>();
		try {
			Criteria imgCriteria = session.createCriteria(UserModelImagesName.class);
			imgCriteria.add(Restrictions.isNotNull("imageNames"));
			imgCriteria.add(Restrictions.ne("imageNames", ""));
			imgCriteria.setMaxResults(50);
			List<UserModelImagesName> imgList = imgCriteria.list();
			for (UserModelImagesName img : imgList) {
				if (img.getUserModel() != null) {
					Long uid = img.getUserModel().getUserId();
					if (uid != null && !shortlistedCandidateIds.contains(uid) && !fallbackCandidateIds.contains(uid)) {
						fallbackCandidateIds.add(uid);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (shortlistedCandidateIds.size() + fallbackCandidateIds.size() < limit * 3) {
			try {
				Criteria fallbackCriteria = session.createCriteria(UserModelDetails.class);
				fallbackCriteria.addOrder(Order.desc("registrationDate"));
				fallbackCriteria.setMaxResults(50);
				List<UserModelDetails> fallbackList = fallbackCriteria.list();
				for (UserModelDetails umd : fallbackList) {
					if (umd.getUserModel() != null) {
						Long uid = umd.getUserModel().getUserId();
						if (uid != null && !shortlistedCandidateIds.contains(uid) && !fallbackCandidateIds.contains(uid)) {
							fallbackCandidateIds.add(uid);
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		List<Long> allCandidateIds = new ArrayList<>();
		allCandidateIds.addAll(shortlistedCandidateIds);
		allCandidateIds.addAll(fallbackCandidateIds);

		List<UserProfileView> shortlistedWithImages = new ArrayList<>();
		List<UserProfileView> shortlistedWithoutImages = new ArrayList<>();
		List<UserProfileView> fallbackWithImages = new ArrayList<>();
		List<UserProfileView> fallbackWithoutImages = new ArrayList<>();

		for (Long uId : allCandidateIds) {
			try {
				UserModel userModel = (UserModel) session.get(UserModel.class, uId);
				if (userModel == null) continue;

				Criteria criteriaDetails = session.createCriteria(UserModelDetails.class);
				criteriaDetails.add(Restrictions.eq("userModel", userModel));
				UserModelDetails uDetails = (UserModelDetails) criteriaDetails.uniqueResult();
				if (uDetails == null) continue;

				UserProfileView profileView = new UserProfileView();
				profileView.setUserId(userModel.getUserId());
				profileView.setVisitorUserId(userModel.getUserId());
				profileView.setName(userModel.getFirstName() != null ? userModel.getFirstName() : "");
				profileView.setAboutHer(uDetails.getAboutMe());

				if (uDetails.getDistricts() != null && uDetails.getState_Country() != null) {
					profileView.setLocation(uDetails.getDistricts().getDistrict_Name() + ", " + uDetails.getState_Country().getState_Name());
				} else if (uDetails.getState_Country() != null) {
					profileView.setLocation(uDetails.getState_Country().getState_Name());
				}

				if (uDetails.getDateOfBirth() != null) {
					try {
						Date date = uDetails.getDateOfBirth();
						Calendar calendar = new GregorianCalendar();
						calendar.setTime(date);
						Date d1 = new GregorianCalendar(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 23, 59).getTime();
						Date today = new Date();
						long diff = today.getTime() - d1.getTime();
						long i = diff / (1000 * 60 * 60 * 24);
						int dob = (int) (i / 365);
						profileView.setAge(dob);
					} catch (Exception e) {}
				}

				if (uDetails.getEducation() != null) {
					profileView.setEducation(uDetails.getEducation().getEducation_name());
				}
				if (uDetails.getOccupation() != null) {
					profileView.setOccupation(uDetails.getOccupation().getOccupation_name());
				}
				if (uDetails.getCaste() != null) {
					profileView.setCaste(uDetails.getCaste().getCaste_name());
				}

				Criteria criteriaImg = session.createCriteria(UserModelImagesName.class);
				criteriaImg.add(Restrictions.eq("userModel", userModel));
				UserModelImagesName imagesName = (UserModelImagesName) criteriaImg.uniqueResult();
				boolean hasImage = false;
				if (imagesName != null && imagesName.getImageNames() != null && !imagesName.getImageNames().trim().isEmpty()) {
					String s[] = imagesName.getImageNames().split("#");
					List<String> lst = new ArrayList<>();
					for (String imgItem : s) {
						if (imgItem != null && !imgItem.trim().isEmpty()) {
							lst.add(imgItem.trim());
						}
					}
					if (!lst.isEmpty()) {
						profileView.setImageObject(lst);
						hasImage = true;
					}
				}

				boolean isShortlisted = shortlistedCandidateIds.contains(uId);
				if (isShortlisted) {
					if (hasImage) {
						shortlistedWithImages.add(profileView);
					} else {
						shortlistedWithoutImages.add(profileView);
					}
				} else {
					if (hasImage) {
						fallbackWithImages.add(profileView);
					} else {
						fallbackWithoutImages.add(profileView);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// Daily rotation seed based on calendar day (changes every day at midnight)
		Calendar cal = Calendar.getInstance();
		long daySeed = cal.get(Calendar.YEAR) * 366L + cal.get(Calendar.DAY_OF_YEAR);
		Random dailyRandom = new Random(daySeed);

		List<UserProfileView> finalSelection = new ArrayList<>();

		// If we have more shortlisted profiles with images than the limit, rotate among them daily
		if (shortlistedWithImages.size() > limit) {
			Collections.shuffle(shortlistedWithImages, dailyRandom);
			finalSelection.addAll(shortlistedWithImages.subList(0, limit));
		} else if (shortlistedWithImages.size() == limit) {
			// If exactly equal to limit, also mix with fallbackWithImages so that profiles change every day
			List<UserProfileView> combinedPool = new ArrayList<>(shortlistedWithImages);
			combinedPool.addAll(fallbackWithImages);
			if (combinedPool.size() > limit) {
				Collections.shuffle(combinedPool, dailyRandom);
				finalSelection.addAll(combinedPool.subList(0, limit));
			} else {
				finalSelection.addAll(shortlistedWithImages);
			}
		} else {
			// If shortlisted with images is fewer than limit, include all shortlisted first and supplement from fallback
			finalSelection.addAll(shortlistedWithImages);
			if (!fallbackWithImages.isEmpty()) {
				Collections.shuffle(fallbackWithImages, dailyRandom);
				for (UserProfileView pv : fallbackWithImages) {
					if (finalSelection.size() >= limit) break;
					finalSelection.add(pv);
				}
			}
			// If still not enough, supplement from profiles without images
			if (finalSelection.size() < limit) {
				List<UserProfileView> remaining = new ArrayList<>();
				remaining.addAll(shortlistedWithoutImages);
				remaining.addAll(fallbackWithoutImages);
				Collections.shuffle(remaining, dailyRandom);
				for (UserProfileView pv : remaining) {
					if (finalSelection.size() >= limit) break;
					finalSelection.add(pv);
				}
			}
		}

		return finalSelection;
	}
}
