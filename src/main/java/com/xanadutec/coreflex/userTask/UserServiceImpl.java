package com.xanadutec.coreflex.userTask;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	public UserModel addUser(UserModel userModel) {
		return userDao.addUser(userModel);

	}

	@Override
	public List<UserModel> listUser() {

		return userDao.listUser();
	}

	@Override
	public void deleteUser(int userId) {
		userDao.deleteUser(userId);
	}

	@Override
	public RoleModel userRoll(int userRoll) {
		return userDao.userRoll(userRoll);
	}

	@Override
	public UserModel getUserById(int id) {
		return userDao.getUserById(id);
	}

	@Override
	public void updateUser(UserModel user) {
		userDao.updateUser(user);
	}

	@Override
	public UserModelDetails addUserdetails(UserModelDetails userModelDetails) {
		return userDao.addUserdetails(userModelDetails);
	}

	@Override
	public UserModelPartnerPreferances addUserPartnerPreferances(
			UserModelPartnerPreferances userModelPartnerPreferances) {
		return userDao.addUserPartnerPreferances(userModelPartnerPreferances);
	}

	@Override
	public void savePartnerEducationAssociation(PartnerEducationAssociation educationAssociation) {
		userDao.savePartnerEducationAssociation(educationAssociation);
	}

	@Override
	public void savePartnerOccupationAssociation(PartnerOccupationAssociation occupationAssociation) {
		userDao.savePartnerOccupationAssociation(occupationAssociation);
	}

	@Override
	public void saveUserImageNames(UserModelImagesName imagesName) {
		userDao.saveUserImageNames(imagesName);

	}

	@Override
	public UserModel getUserByUname(String userName) {
		return userDao.getUserByUname(userName);
	}

	@Override
	public UserModelImagesName getImageNameAccordingToUserModel(UserModel userModel) {
		return userDao.getImageNameAccordingToUserModel(userModel);
	}

	@Override
	public List<UserProfileView> getListOFProfileForHomeAccordingToGender(String gender, HttpServletRequest request) {
		return userDao.getListOFProfileForHomeAccordingToGender(gender, request);
	}

	@Override
	public UserModelDetails getUserdetails(UserModel userModel) {
		return userDao.getUserdetails(userModel);
	}

	@Override
	public CompareUserProfileView getCompareUserProfileViewAccordingToUserModelId(UserModel userModel,
			HttpServletRequest request) {
		return userDao.getCompareUserProfileViewAccordingToUserModelId(userModel, request);
	}

	@Override
	public List<UserModelDetails> getListOfUserModelDetails() {
		return userDao.getListOfUserModelDetails();
	}

	@Override
	public UserModelPartnerPreferances getUserModelPartnerPreferancesByUserModelId(UserModel userModel) {
		return userDao.getUserModelPartnerPreferancesByUserModelId(userModel);
	}

	@Override
	public HashMap<String, String> getUserNamePasswordAccordingToEmailId(String emailId) {
		return userDao.getUserNamePasswordAccordingToEmailId(emailId);
	}

	@Override
	public Long getCountOfAllLatestMatchAccordingToGender(String gender) {
		return userDao.getCountOfAllLatestMatchAccordingToGender(gender);
	}

	@Override
	public List<UserProfileView> getListOFProfileForHomeAccordingToGenderExcludeUserIds(String gender,
			List<UserModel> models, HttpServletRequest request) {
		return userDao.getListOFProfileForHomeAccordingToGenderExcludeUserIds(gender, models, request);
	}

	@Override
	public void saveVisitedProfileUserWithDate(UserModel userModel, UserModel VisitUserModelId) {
		userDao.saveVisitedProfileUserWithDate(userModel, VisitUserModelId);
	}

	@Override
	public List<PartnerEducationAssociation> getPartnerEducationAssociationByUserModel(UserModel userModel) {
		return userDao.getPartnerEducationAssociationByUserModel(userModel);
	}

	@Override
	public List<PartnerOccupationAssociation> getPartnerOccuptionAssociationByUserModel(UserModel userModel) {
		return userDao.getPartnerOccuptionAssociationByUserModel(userModel);
	}

	@Override
	public void deletePartnerEducationAssociationByUserModel(UserModel userModel) {
		userDao.deletePartnerEducationAssociationByUserModel(userModel);

	}

	@Override
	public void deletePartnerOccuptionAssociationByUserModel(UserModel userModel) {
		userDao.deletePartnerOccuptionAssociationByUserModel(userModel);
	}

	@Override
	public List<UserProfileView> getListOFLatestUpgradedUserForHomeAccordingToGenderExcludeUserIds(String gender,
			List<UserModel> models, HttpServletRequest request) {
		return userDao.getListOFLatestUpgradedUserForHomeAccordingToGenderExcludeUserIds(gender, models, request);
	}

	@Override
	public List<UserProfileView> getListOFNotSeenYetProfilesExcludeUserIds(String string, List<UserModel> models,
			HttpServletRequest request) {
		return userDao.getListOFNotSeenYetProfilesExcludeUserIds(string, models, request);
	}

	@Override
	public List<UserProfileView> getListOFViewedYetProfilesExcludeUserIds(String string, List<UserModel> models,
			HttpServletRequest request) {
		return userDao.getListOFViewedYetProfilesExcludeUserIds(string, models, request);
	}

	@Override
	public Long getCountOfAllLatestUpgradedMatchAccordingToGender(String gender) {
		return userDao.getCountOfAllLatestUpgradedMatchAccordingToGender(gender);
	}

	@Override
	public Long getCountOfAllSeenYetToGender(String gender, UserModel model) {
		return userDao.getCountOfAllSeenYetToGender(gender, model);
	}

	@Override
	public List<UserProfileView> getListOFShortlistedOrIntrestedProfilesExcludeUserIds(String gender,
			List<UserModel> models, HttpServletRequest request) {
		return userDao.getListOFShortlistedOrIntrestedProfilesExcludeUserIds(gender, models, request);
	}

	@Override
	public Long getCountOfShortlistedToGender(String gender, UserModel model) {
		return userDao.getCountOfShortlistedToGender(gender, model);
	}

	public List<UserProfileViewForMessages> getlistOfMessagesAndEmailByUser(HttpServletRequest request,
			UserModel model) {
		return userDao.getlistOfMessagesAndEmailByUser(request, model);

	}

	@Override
	public List<UserProfileViewForMessages> getlistOfSentMessagesAndEmail(HttpServletRequest request,
			UserModel userModel) {
		return userDao.getlistOfSentMessagesAndEmail(request, userModel);
	}

	@Override
	public Map<String, List<UserProfileViewForMessages>> getlistOfInvitationByUser(HttpServletRequest request,
			UserModel userModel) {
		return userDao.getlistOfInvitationByUser(request, userModel);
	}

	@Override
	public Map<String, List<UserProfileViewForMessages>> getlistOfAcceptedInvitationByUser(HttpServletRequest request,
			UserModel userModel) {
		return userDao.getlistOfAcceptedInvitationByUser(request, userModel);
	}

	@Override
	public Map<String, List<UserProfileViewForMessages>> getlistOfRejectedInvitationByUser(HttpServletRequest request,
			UserModel userModel) {
		return userDao.getlistOfRejectedInvitationByUser(request, userModel);
	}

	@Override
	public List<UserProfileView> getlistOfRefineSearchByUser(HttpServletRequest request, UserModel userModel,
			String gender, String refineheightSelectPartnerFirst, String refineheightSelectPartnerSecond,
			String refineageFrom, String refineageTo, String refinematerialStatus, String refinereligionSelectPartner,
			String casteSelect, String refineoccupationCategorySelect, String refineDistrict) {
		return userDao.getlistOfRefineSearchByUser(request, userModel, gender, refineheightSelectPartnerFirst,
				refineheightSelectPartnerSecond, refineageFrom, refineageTo, refinematerialStatus,
				refinereligionSelectPartner, casteSelect, refineoccupationCategorySelect, refineDistrict);
	}

	@Override
	public void updateUserFullName(String userId, String name) {
		userDao.updateUserFullName(userId, name);

	}

	@Override
	public List<UserModelDetails> getListOfUserModelDetailsByGender(String gender, HttpServletRequest request) {
		return userDao.getListOfUserModelDetailsByGender(gender, request);
	}

	@Override
	public void saveOnlineUserInformation(UserModel model, String sex) {
		userDao.saveOnlineUserInformation(model, sex);
	}

	@Override
	public void deleteOnlineUser(UserModel usermodel) {
		userDao.deleteOnlineUser(usermodel);
	}

	@Override
	public OnlineUser getonlineUser(UserModel usermodel) {

		return userDao.getonlineUser(usermodel);
	}

	@Override
	public void updateOnlineUser(UserModel usermodel) {
		userDao.updateOnlineUser(usermodel);
	}

	@Override
	public List<OnlineUser> getListOfOnlineUser(String sex) {
		return userDao.getListOfOnlineUser(sex);
	}

	@Override
	public List<UserProfileView> getListOFProfileForChattingAccordingToGender(String gender,
			HttpServletRequest request) {
		return userDao.getListOFProfileForChattingAccordingToGender(gender, request);
	}

	@Override
	public void updateUserModelDetails(UserModelDetails usermodeldetails) {
		userDao.updateUserModelDetails(usermodeldetails);

	}

	@Override
	public List<AdminUserModel> getListOfUserModelDetailsForAdminPanel() {
		return userDao.getListOfUserModelDetailsForAdminPanel();
	}

	@Override
	public List<UserProfileView> getlistOfRefineSearchByUserExcludeUserModels(HttpServletRequest request,
			UserModel userModel, String gender, String refineheightSelectPartnerFirst,
			String refineheightSelectPartnerSecond, String refineageFrom, String refineageTo,
			String refinematerialStatus, String refinereligionSelectPartner, String casteSelect,
			String refineoccupationCategorySelect, String refineDistrict,
			List<UserModel> excludeUserModelsRefineSearch) {
		return userDao.getlistOfRefineSearchByUserExcludeUserModels(request, userModel, gender,
				refineheightSelectPartnerFirst, refineheightSelectPartnerSecond, refineageFrom, refineageTo,
				refinematerialStatus, refinereligionSelectPartner, casteSelect, refineoccupationCategorySelect,
				refineDistrict, excludeUserModelsRefineSearch);
	}

	@Override
	public List<AdminUserModel> getListOfUserModelDetailsForDealerByDealer(Dealer dealer) {
		return userDao.getListOfUserModelDetailsForDealerByDealer(dealer);
	}

	@Override
	public void saveDeleteUserDetail(UserModel usermodel, UserModel deleteBy) {
		userDao.saveDeleteUserDetail(usermodel, deleteBy);
	}

	@Override
	public List<DeleteUserDetail> getListOfDeletedUserForAdmin() {
		return userDao.getListOfDeletedUserForAdmin();
	}

	@Override
	public void updateUserModelDetailsWithDealer(UserModelDetails usermodeldetails) {
		userDao.updateUserModelDetailsWithDealer(usermodeldetails);
		
	}

	@Override
	public UserModel getadminDetails() {
		return userDao.getadminDetails();
	}

	@Override
	public List<AdminUserModel> getListOfUserModelDetailsByEmp(
			EmployeeRegistrationDetails empModel) {
	
		return userDao.getListOfUserModelDetailsByEmp(empModel);
	}

	@Override
	public List<AdminUserModel> getDeletedUserInAdminFormat() {
		return userDao.getDeletedUserInAdminFormat();
	}

	@Override
	public long getTotalSaleKitCount() 
	{
		return userDao.getTotalSaleKitCount();
	}

	@Override
	public long getTotalRevenue() 
	{
		return userDao.getTotalRevenue();
	}

	@Override
	public long getRecentlyAddedUserBy_Date(Date today, Date yesterday) 
	{
		return userDao.getRecentlyAddedUserBy_Date(today,yesterday);
	}

	@Override
	public void changeUserRole(RoleModel role, int roleId) {
	 userDao.changeUserRole(role, roleId);
	}

	@Override
	public List<SortlistedProfileUserWithDate> getListOFShortlistedUserProfileByUser(UserModel userModel) {
		return userDao.getListOFShortlistedUserProfileByUser(userModel);
	}

	@Override
	public List<OccupationCategory> getAllOccupationCatogory() {
		return userDao.getAllOccupationCatogory();
	}

	@Override
	public List<Occupation> getAllOccupations_List() {
		return userDao.getAllOccupations_List();
	}

	@Override
	public List<Religion> getAllReligion() {
		return userDao.getAllReligion();
	}

	@Override
	public List<Caste> getAllCast() {
		return userDao.getAllCast();
	}

	@Override
	public List<String> getListOfAllRegisteredMobileNumbers() {
		return userDao.getListOfAllRegisteredMobileNumbers();
	}

	@Override
	public List<UserProfileView> getListOFProfileForHomeAccordingToGenderAndUserName(
			String gender, HttpServletRequest request, String userName,String castId, String occupationId, String stateId, String districtId) {
		return userDao.getListOFProfileForHomeAccordingToGenderAndUserName(gender,request,userName,castId,occupationId,stateId,districtId);
	}

	@Override
	public List<OccupationCategory> getPartnerOccuptionCatagory(
			UserModel userModel) {
		return userDao.getPartnerOccuptionCatagory(userModel);
	}

	@Override
	public List<EducationCategory> getEducationCatagory(UserModel userModel) {
		return userDao.getEducationCatagory(userModel);
	}

	@Override
	public List<UserModelDetails> getUserByGender(String gender) {
		return userDao.getUserByGender(gender);
	}

	@Override
	public List<UserModelDetails> getUserByGenderAndUserType(String gender) {
		return userDao.getUserByGenderAndUserType(gender);
	}

	@Override
	public long getMaxCountOfUserDetails() {
		return userDao.getMaxCountOfUserDetails();
	}

	@Override
	public List<AdminUserModel> getListOfUserModelDetailsForAdminPanelAjax(int pageNumber) {
		return userDao.getListOfUserModelDetailsForAdminPanelAjax(pageNumber);
	}

	@Override
	public List<AdminUserModel> globalSearch(String searchText) {
		return userDao.globalSearch(searchText);
	}

	@Override
	public List<AdminUserModel> getRegisteredUsersForDataTable(int start, int length, String search, Map<Integer, String> columnFilters, int sortColumn, String sortDir) {
		return userDao.getRegisteredUsersForDataTable(start, length, search, columnFilters, sortColumn, sortDir);
	}

	@Override
	public long getRegisteredUsersForDataTableCount(String search, Map<Integer, String> columnFilters) {
		return userDao.getRegisteredUsersForDataTableCount(search, columnFilters);
	}

	@Override
	public void updateAllUserModelDetailsStatus(int statusId) {
		userDao.updateAllUserModelDetailsStatus(statusId);
	}

	@Override
	public List<UserProfileView> getListOFCustomSearchProfiles(String searchId, String searchName, String searchRegDate, String searchDOB, UserModel currentUser) {
		return userDao.getListOFCustomSearchProfiles(searchId, searchName, searchRegDate, searchDOB, currentUser);
	}

	@Override
	public List<UserProfileView> getTopShortlistedProfiles(int limit, HttpServletRequest request) {
		return userDao.getTopShortlistedProfiles(limit, request);
	}
}
