package com.xanadutec.coreflex.userTask;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.xanadutec.coreflex.model.AdminUserModel;
import com.xanadutec.coreflex.model.Caste;
import com.xanadutec.coreflex.model.CompareUserProfileView;
import com.xanadutec.coreflex.model.Dealer;
import com.xanadutec.coreflex.model.DeleteUserDetail;
import com.xanadutec.coreflex.model.EducationCategory;
import com.xanadutec.coreflex.model.EmployeeRegistrationDetails;
import com.xanadutec.coreflex.model.Occupation;
import com.xanadutec.coreflex.model.OccupationCategory;
import com.xanadutec.coreflex.model.OnlineUser;
import com.xanadutec.coreflex.model.PartnerEducationAssociation;
import com.xanadutec.coreflex.model.PartnerOccupationAssociation;
import com.xanadutec.coreflex.model.Religion;
import com.xanadutec.coreflex.model.RoleModel;
import com.xanadutec.coreflex.model.SortlistedProfileUserWithDate;
import com.xanadutec.coreflex.model.UserModel;
import com.xanadutec.coreflex.model.UserModelDetails;
import com.xanadutec.coreflex.model.UserModelImagesName;
import com.xanadutec.coreflex.model.UserModelPartnerPreferances;
import com.xanadutec.coreflex.model.UserProfileView;
import com.xanadutec.coreflex.model.UserProfileViewForMessages;

public interface UserService {

	public UserModel addUser(UserModel userModel);

	public UserModelDetails addUserdetails(UserModelDetails userModelDetails);

	public UserModelPartnerPreferances addUserPartnerPreferances(
			UserModelPartnerPreferances userModelPartnerPreferances);

	public void savePartnerEducationAssociation(PartnerEducationAssociation educationAssociation);

	public void savePartnerOccupationAssociation(PartnerOccupationAssociation occupationAssociation);

	public void saveUserImageNames(UserModelImagesName imagesName);

	public List<UserModelDetails> getListOfUserModelDetails();
	
	public List<UserProfileView> getListOFCustomSearchProfiles(String searchId, String searchName, String searchRegDate, String searchDOB, UserModel currentUser);
	
	public List<AdminUserModel> getListOfUserModelDetailsForAdminPanel();

	public UserModelPartnerPreferances getUserModelPartnerPreferancesByUserModelId(UserModel userModel);

	public UserModelDetails getUserdetails(UserModel userModel);

	public List<UserModel> listUser();

	public void deleteUser(int userId);

	public RoleModel userRoll(int userRoll);

	public void updateUser(UserModel user);

	public void updateUserFullName(String userId,String name);
	public UserModel getUserById(int id);

	public List<UserProfileView> getListOFProfileForHomeAccordingToGender(String gender, HttpServletRequest request);

	public List<UserProfileView> getListOFProfileForHomeAccordingToGenderExcludeUserIds(String gender,
			List<UserModel> models, HttpServletRequest request);

	public UserModel getUserByUname(String userName);

	public UserModelImagesName getImageNameAccordingToUserModel(UserModel userModel);

	public CompareUserProfileView getCompareUserProfileViewAccordingToUserModelId(UserModel userModel,
			HttpServletRequest request);

	public HashMap<String, String> getUserNamePasswordAccordingToEmailId(String emailId);

	public Long getCountOfAllLatestMatchAccordingToGender(String gender);

	public void saveVisitedProfileUserWithDate(UserModel userModel, UserModel VisitUserModelId);

	public List<PartnerEducationAssociation> getPartnerEducationAssociationByUserModel(UserModel userModel);

	public List<PartnerOccupationAssociation> getPartnerOccuptionAssociationByUserModel(UserModel userModel);

	public void deletePartnerEducationAssociationByUserModel(UserModel userModel);

	public void deletePartnerOccuptionAssociationByUserModel(UserModel userModel);

	public List<UserProfileView> getListOFLatestUpgradedUserForHomeAccordingToGenderExcludeUserIds(String gender,
			List<UserModel> models, HttpServletRequest request);

	public List<UserProfileView> getListOFNotSeenYetProfilesExcludeUserIds(String string, List<UserModel> models,
			HttpServletRequest request);

	public List<UserProfileView> getListOFViewedYetProfilesExcludeUserIds(String string, List<UserModel> models,
			HttpServletRequest request);

	Long getCountOfAllLatestUpgradedMatchAccordingToGender(String gender);

	Long getCountOfAllSeenYetToGender(String gender, UserModel model);
	
	List<UserProfileView> getListOFShortlistedOrIntrestedProfilesExcludeUserIds(String gender, List<UserModel> models,
			HttpServletRequest request);

	Long getCountOfShortlistedToGender(String gender, UserModel model);

	List<UserProfileViewForMessages> getlistOfMessagesAndEmailByUser(HttpServletRequest request, UserModel model);

	public List<UserProfileViewForMessages> getlistOfSentMessagesAndEmail(HttpServletRequest request,
			UserModel userModel);

	public Map<String, List<UserProfileViewForMessages>> getlistOfInvitationByUser(HttpServletRequest request,
			UserModel userModel);

	public Map<String, List<UserProfileViewForMessages>> getlistOfAcceptedInvitationByUser(HttpServletRequest request,
			UserModel userModel);

	Map<String, List<UserProfileViewForMessages>> getlistOfRejectedInvitationByUser(HttpServletRequest request,
			UserModel userModel);

	public List<UserProfileView> getlistOfRefineSearchByUser(HttpServletRequest request, UserModel userModel,String gender,
			String refineheightSelectPartnerFirst, String refineheightSelectPartnerSecond, String refineageFrom,
			String refineageTo, String refinematerialStatus, String refinereligionSelectPartner, String casteSelect,
			String refineoccupationCategorySelect,String refineDistrict);
	
	
	public List<UserProfileView> getlistOfRefineSearchByUserExcludeUserModels(HttpServletRequest request, UserModel userModel,String gender,
			String refineheightSelectPartnerFirst, String refineheightSelectPartnerSecond, String refineageFrom,
			String refineageTo, String refinematerialStatus, String refinereligionSelectPartner, String casteSelect,
			String refineoccupationCategorySelect,String refineDistrict,List<UserModel>excludeUserModelsRefineSearch);

	public List<UserProfileView> getlistOfRefineSearchByUserExcludeUserModels(HttpServletRequest request, UserModel userModel,String gender,
			String refineheightSelectPartnerFirst, String refineheightSelectPartnerSecond, String refineageFrom,
			String refineageTo, String refinematerialStatus, String refinereligionSelectPartner, String casteSelect,
			String refineoccupationCategorySelect,String refineDistrict,
			String profileId, String nameSearch, String regDate, String dob, String education,
			List<UserModel>excludeUserModelsRefineSearch);
	

	public List<UserModelDetails> getListOfUserModelDetailsByGender(String gender,HttpServletRequest request);
	public void saveOnlineUserInformation(UserModel model,String sex);
	public void deleteOnlineUser(UserModel usermodel);
	public OnlineUser getonlineUser(UserModel usermodel);
	public void updateOnlineUser(UserModel usermodel);
	public List<OnlineUser> getListOfOnlineUser(String sex);
	List<UserProfileView> getListOFProfileForChattingAccordingToGender(String gender, HttpServletRequest request);
	public void updateUserModelDetails(UserModelDetails usermodeldetails);
		public Long getCountOfShortlistedMe(UserModel userModel);
	public Long getCountOfShortlistedByMe(UserModel userModel);
	public Long getCountOfInboxMessages(UserModel userModel);
	public Long getCountOfInterestReceived(UserModel userModel);
	public Long getCountOfInterestSent(UserModel userModel);
	public Long getCountOfWhoViewedMe(UserModel userModel);

	public List<AdminUserModel> getListOfUserModelDetailsForDealerByDealer(Dealer dealer);
	public void  saveDeleteUserDetail(UserModel usermodel,UserModel deleteBy);
	
	public List<DeleteUserDetail> getListOfDeletedUserForAdmin();
	public void updateUserModelDetailsWithDealer(UserModelDetails usermodeldetails);
	public UserModel getadminDetails();

	public List<AdminUserModel> getListOfUserModelDetailsByEmp(EmployeeRegistrationDetails empDetails);
	
	public List<AdminUserModel> getDeletedUserInAdminFormat();

	public long getTotalSaleKitCount();

	public long getTotalRevenue();

	public long getRecentlyAddedUserBy_Date(Date today, Date yesterday);
	public void changeUserRole(RoleModel role , int roleId);

	public List<SortlistedProfileUserWithDate> getListOFShortlistedUserProfileByUser(UserModel userModel);

	public List<OccupationCategory> getAllOccupationCatogory();

	public List<Occupation> getAllOccupations_List();

	public List<Religion> getAllReligion();

	public List<Caste> getAllCast();

	public List<String> getListOfAllRegisteredMobileNumbers();

	public List<UserProfileView> getListOFProfileForHomeAccordingToGenderAndUserName(
			String gender, HttpServletRequest request, String userName, String castId, String occupationId, String stateId, String districtId);

	public List<OccupationCategory> getPartnerOccuptionCatagory(
			UserModel userModel);

	public List<EducationCategory> getEducationCatagory(UserModel userModel);

	public List<UserModelDetails> getUserByGender(String gender);

	public List<UserModelDetails> getUserByGenderAndUserType(String gender);

	public long getMaxCountOfUserDetails();

	public List<AdminUserModel> getListOfUserModelDetailsForAdminPanelAjax(int pageNumber);

	public List<AdminUserModel> getRegisteredUsersForDataTable(int start, int length, String search, Map<Integer, String> columnFilters, int sortColumn, String sortDir);

	public long getRegisteredUsersForDataTableCount(String search, Map<Integer, String> columnFilters);

	public List<AdminUserModel> globalSearch(String searchText);
	
	public List<UserProfileView> getTopShortlistedProfiles(int limit, HttpServletRequest request);

	public List<UserProfileView> getTopViewedProfiles(int femaleLimit, int maleLimit, HttpServletRequest request);

	public void updateAllUserModelDetailsStatus(int statusId);
}
