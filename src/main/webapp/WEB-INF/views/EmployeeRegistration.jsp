<!DOCTYPE html>
<html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Employee Details</title>
<!-- Tell the browser to be responsive to screen width -->
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
<jsp:include page="body.jsp"></jsp:include>
<link rel="stylesheet" type="text/css"
	href="resources/confirmBox/confirm.css" media="screen">

<script src="resources/confirmBox/confirm.js" type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/resources/Employee/employee_Details.js"
	charset='utf-8'></script>
<link rel="stylesheet" href="resources/datepicker/css/datepicker.css">
<script src="resources/datepicker/js/datepicker.js"></script>

<style>
.box-custom {
	background: #dddddd;
}

label {
	margin-right: -20px;
	font: normal 14px cambria !important;
}

table {
	font: 12px Courier New !important;
}

.enableTextColor {
	color: green;
}

.actionLink{

cursor: pointer; 
text-decoration: underline;
}
.actionLink:hover{

	color: #d23779;
	text-decoration: underline;
}
</style>
<script>
	var allCountryName = new Array();
	var files = [];
</script>



</head>
<body class="hold-transition skin-blue sidebar-mini">
	<div class="wrapper">

		<jsp:include page="menu.jsp"></jsp:include>

		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->


			<!-- Main content -->
			<section class="content">
				<div id="home">
					<div class="row">

						<div class="col-md-12">
							<div class="nav-tabs-custom">


								<ul class="nav nav-tabs">
									<li class="active"><a href="#employeeRegistration_toggle"
										data-toggle="tab">Employee Registration</a></li>
									<li><a href="#assignEmployeePermission_toggle"
										data-toggle="tab">Assign Employee Permissions</a></li>
									<li><a href="#permissionType_toggle" data-toggle="tab">Add
											Permission Type</a></li>

								</ul>
								<div class="tab-content">

									<div class="active tab-pane" id="employeeRegistration_toggle">

										<div class="panel panel-danger">
											<div class="panel-heading" style="background-color: #d23779;">
												<font color="white">Employee Registration Form</font>
											</div>


											<div class="panel-body">


												<br>


												<div class="alert alert-danger alert-dismissable"
													id="nameSelect">
													<button type="button" class="close" data-dismiss="alert"
														aria-hidden="true">&times;</button>
													<strong>Error: </strong>Please Enter Full Name.

												</div>
												<div class="alert alert-danger alert-dismissable"
													id="invalidName">
													<button type="button" class="close" data-dismiss="alert"
														aria-hidden="true">&times;</button>
													<strong>Error: </strong>Please Enter Valid Full Name.

												</div>
												

												<div class="alert alert-danger alert-dismissable"
													id="emailselect">
													<button type="button" class="close" data-dismiss="alert"
														aria-hidden="true">&times;</button>
													<strong>Error: </strong>Please Enter valid Email.

												</div>
												
												<div class="alert alert-danger alert-dismissable"
													id="dateOfBirthRequiredAlert">
													<button type="button" class="close" data-dismiss="alert"
														aria-hidden="true">&times;</button>
													<strong>Error: </strong>Please Enter Invalid DateOfBirth.

												</div>
												<div class="alert alert-danger alert-dismissable"
													id="registrationRequiredAlert">
													<button type="button" class="close" data-dismiss="alert"
														aria-hidden="true">&times;</button>
													<strong>Error: </strong>Please Select Registration Date.

												</div>

												<div class="alert alert-danger alert-dismissable"
													id="enableEmployeeAlert">
													<button type="button" class="close" data-dismiss="alert"
														aria-hidden="true">&times;</button>
													<strong>Error: </strong>This Employee is already Enabled.
												</div>

												<div class="alert alert-danger alert-dismissable"
													id="disableEmployeeAlert">
													<button type="button" class="close" data-dismiss="alert"
														aria-hidden="true">&times;</button>
													<strong>Error: </strong>This Employee is already Disabled.
												</div>

												<div class="alert alert-success" id="disabledEmployeeAlert">
													<button type="button" class="close" data-dismiss="alert"
														aria-hidden="true">&times;</button>
													<strong>Success: </strong>Employee Disabled Successfully.
												</div>

												<div class="alert alert-success" id="employeeDeleteAlert">
													<button type="button" class="close" data-dismiss="alert"
														aria-hidden="true">&times;</button>
													<strong>Success: </strong>Employee DeletedSuccessfully.
												</div>

												<div class="alert alert-success" id="enabledEmployeeAlert">
													<button type="button" class="close" data-dismiss="alert"
														aria-hidden="true">&times;</button>
													<strong>Success: </strong>Employee Enabled Successfully.
												</div>

												<div class="alert alert-danger alert-dismissable"
													id="mobileNoRequired">
													<button type="button" class="close" data-dismiss="alert"
														aria-hidden="true">&times;</button>
													<strong>Error: </strong>Please Enter Mobile No.

												</div>

												<div class="alert alert-danger alert-dismissable"
													id="mobileNoAlreadyPresent">
													<button type="button" class="close" data-dismiss="alert"
														aria-hidden="true">&times;</button>
													<strong>Error: </strong>Mobile No Already Registered.

												</div>


												<div class="alert alert-danger alert-dismissable"
													id="mobilenumselect">
													<button type="button" class="close" data-dismiss="alert"
														aria-hidden="true">&times;</button>
													<strong>Error: </strong>Please Enter Valid Mobile Number.

												</div>

												<div class="alert alert-danger alert-dismissable"
													id="passwordselect">
													<button type="button" class="close" data-dismiss="alert"
														aria-hidden="true">&times;</button>
													<strong>Error: </strong>Please Enter Password.

												</div>
												<div class="alert alert-danger alert-dismissable"
													id="unameselect">
													<button type="button" class="close" data-dismiss="alert"
														aria-hidden="true">&times;</button>
													<strong>Error: </strong>Please Enter Username.

												</div>

												<div class="alert alert-danger alert-dismissable"
													id="emailAvailable">
													<button type="button" class="close" data-dismiss="alert"
														aria-hidden="true">&times;</button>
													<strong>Error: </strong>Email Id Already Registered.

												</div>


												<div class="alert alert-danger alert-dismissable"
													id="mobileAvailable">
													<button type="button" class="close" data-dismiss="alert"
														aria-hidden="true">&times;</button>
													<strong>Error: </strong>Mobile Number Already Registered.

												</div>

												<div class="alert alert-danger alert-dismissable"
													id="UserNameAvailable">
													<button type="button" class="close" data-dismiss="alert"
														aria-hidden="true">&times;</button>
													<strong>Error: </strong>Username Already Registered.

												</div>
												<div class="alert alert-success alert-dismissable"
													id="successSelect">
													<button type="button" class="close" data-dismiss="alert"
														aria-hidden="true">&times;</button>
													<strong>Success: </strong>Employee Details Added
													Successfully.

												</div>
												<div class="alert alert-success alert-dismissable"
													id="employeeUpdateSuccess">
													<button type="button" class="close" data-dismiss="alert"
														aria-hidden="true">&times;</button>
													<strong>Success: </strong>Employee Details Updated
													Successfully.

												</div>
												<div class="alert alert-danger alert-dismissable"
													id="updateEmployeeErrorAlert">
													<button type="button" class="close" data-dismiss="alert"
														aria-hidden="true">&times;</button>
													<strong>Error: </strong>You can not update disabled
													Employee Details, if you want to update Employee please
													enable Employee Details.
												</div>


												<form id="customerRegistrationForm">

													<div class="row">
														<div class="" col-sm-3">
															<label class="control-label" " for="messages">Full
																Name:<sup><font color="red" size="2px">*</font></sup>
															</label>
														</div>
														<div class="col-sm-3">
															<input type="text" id="fullName" name="fullName" />
														</div>
														<div class="" col-sm-3">
															<label class="control-label" " for="messages">Date
																of Birth :</label>
														</div>
														<div class="col-sm-3">
															<input type="text" id="dateOfBirth" name="dateOfBirth"  onmouseover="datePiker();"/>
														</div>
													</div>
													<br>
													<div class="row">
														<div class="" col-sm-3">
															<label class="control-label" " for="messages">UserName<sup><font
																	color="red" size="2px">*</font></sup></label>
														</div>
														<div class="col-sm-3">
															<input type="text" id="userName" name="userName">
														</div>
														<div class="" col-sm-3">
															<label class="control-label" " for="noofdays">Password<sup><font
																	color="red" size="2px">*</font></sup></label>
														</div>
														<div class="col-sm-3">
															<input type="password" id="employeePassword"
																name="employeePassword">
														</div>
													</div>



													<br>

													<div class="row">
														<div class="" col-sm-3">
															<label class="control-label" " for="noofdays">Email:</label>
														</div>
														<div class="col-sm-3">
															<input type="text" id="email" name="email">
														</div>


														<div class="" col-sm-3">
															<label class="control-label" " for="noofdays">Designation:
															</label>
														</div>
														<div class="col-sm-3">
															<input type="text" id="designation" name="designation">
														</div>
													</div>
													<br>

													<div class="row">


														<div class="" col-sm-3">
															<label class="control-label" " for="noofdays">Mobile
																No: <sup><font color="red" size="2px">*</font></sup>
															</label>
														</div>
														<div class="col-sm-3">
															<input type="text" id="mobileNo" name="mobileNo"
																maxlength="10"
																onkeypress="return event.charCode ">= 48 && event.charCode <= 57 || event.keyCode === 8 || event.keyCode === 9 '>
														</div>

														<div class="" col-sm-3">
															<label class="control-label" " for="noofdays">
																Alternate Mobile No: </label>
														</div>
														<div class="col-sm-3">
															<input type="text" id="alernateMobileNo"
																name="alernateMobileNo" maxlength="10"
																onkeypress="return event.charCode ">= 48 && event.charCode <= 57 || event.keyCode === 8 || event.keyCode === 9 '>
														</div>
													</div>

													<br>
													<div class="row">
														<div class="" col-sm-3">
															<label class="control-label" " for="noofdays">City:</label>
														</div>
														<div class="col-sm-3">
															<input type="text" id="city" name="city">
														</div>

                                                         <div class="" col-sm-3">
															<label class="control-label" " for="noofdays">RegistrationDate
																:</label>
														</div>
														<div class="col-sm-3">
															<input type="text" id="registrationDate" name="registrationDate" readonly="true">
														</div>
														<!-- <div class="" col-sm-3">
															<label class="control-label" " for="noofdays">Address:
															</label>
														</div>
														<div class="col-sm-3">
															<input type="text" id="address" name="address">
														</div> -->
													</div>
													<br>
													
													<div class="row">
													  <div class="" col-sm-3">
															<label class="control-label" " for="noofdays">Address:
															</label>
													 </div>
													  <div class="" col-sm-3">
													   <textarea rows="2"  id="address" name="address">
                                                      </textarea>
                                                      </div>
                                                      
                                                      <div class="" col-sm-3">
															<label class="control-label" " for="noofdays">Previous
																Salary:</label>
														</div>
													
														<div class="col-sm-3">
															<input type="text" id="previousSalary"
																name="previousSalary" maxlength="9"
																onkeypress="return event.charCode ">= 48 && event.charCode <= 57 || event.keyCode === 8 || event.keyCode === 9 '>
														</div>
														<br><br>
														<div class="" col-sm-3">
															<label class="control-label" " for="noofdays">Current
																Salary: </label>
														</div>
														<div class="col-sm-3">
															<input type="text" id="currentSalary"
																name="currentSalary" maxlength="9"
																onkeypress="return event.charCode ">= 48 && event.charCode <= 57 || event.keyCode === 8 || event.keyCode === 9 '>
														</div>
                                                      
													</div>
													<br>
													
													<%-- <div class="row">
														<div class="" col-sm-3">
															<label class="control-label" " for="noofdays">Previous
																Salary:</label>
														</div>
														<div class="col-sm-3">
															<input type="text" id="previousSalary"
																name="previousSalary" maxlength="9"
																onkeypress="return event.charCode ">= 48 && event.charCode <= 57 || event.keyCode === 8 || event.keyCode === 9 '>
														</div>


														<div class="" col-sm-3">
															<label class="control-label" " for="noofdays">Current
																Salary: </label>
														</div>
														<div class="col-sm-3">
															<input type="text" id="currentSalary"
																name="currentSalary" maxlength="9"
																onkeypress="return event.charCode ">= 48 && event.charCode <= 57 || event.keyCode === 8 || event.keyCode === 9 '>
														</div>
													</div>
													<br> --%>
													<div class="row">
														<div class="" col-sm-3">
															<label class="control-label" " for="noofdays">Total
																Experience:</label>
														</div>
														<div class="col-sm-3">
															<input type="text" id="totalExperience"
																name="totalExperience" onkeypress="return event.charCode ">= 48 && event.charCode <= 57 || event.keyCode === 8 || event.keyCode === 9 '>
														</div>


														<div class="" col-sm-3">
															<label class="control-label" " for="noofdays">Experience
																Details: </label>
														</div>
														<div class="col-sm-3">
															<input type="text" id="experienceDetails"
																name="experienceDetails">
														</div>
													</div>

													<br>
													<div class="row">
														<div class="" col-sm-3">
															<label class="control-label" " for="noofdays">Bank
																Name:</label>
														</div>
														<div class="col-sm-3">
															<input type="text" id="bankName" name="bankName">
														</div>


														<div class="" col-sm-3">
															<label class="control-label" " for="noofdays">IFC
																Code: </label>
														</div>
														<div class="col-sm-3">
															<input type="text" id="ifcCode" name="ifcCode" onkeypress="return event.charCode ">= 48 && event.charCode <= 57 || event.keyCode === 8 || event.keyCode === 9 '>
														</div>
													</div>
													<br>
													<div class="row">
														<div class="" col-sm-3">
															<label class="control-label" " for="noofdays">Branch
																Name:</label>
														</div>
														<div class="col-sm-3">
															<input type="text" id="branchName" name="branchName">
														</div>


														<div class="" col-sm-3">
															<label class="control-label" " for="noofdays">Account
																No: </label>
														</div>
														<div class="col-sm-3">
															<input type="text" id="accountNo" name="accountNo" onkeypress="return event.charCode ">= 48 && event.charCode <= 57 || event.keyCode === 8 || event.keyCode === 9 '>
															<!-- 	---------------	hidden id for Employee Details Update Record	------------------	 -->
															<input type="hidden" id="employeeHiddenId"
																name="employeeHiddenId" value=""> <input
																type="hidden" id="employeeEnableDisableStatus"
																name="employeeEnableDisableStatus" value=""> <input
																type="hidden" name="editselectedUserName"
																Id="editselectedUserName" value=""> <input
																type="hidden" name="editselectedMobileNo"
																id="editselectedMobileNo" value="">
														</div>
													</div>
													<br>
													<!-- <div class="row">
														<div class="" col-sm-3">
															<label class="control-label" " for="noofdays">RegistrationDate
																:</label>
														</div>
														<div class="col-sm-3">
															<input type="text" id="registrationDate" name="registrationDate">
														</div>
													</div>
													<br> -->
													 <br>
													<div class="row">
														<div class="" col-sm-4"></div>


														<div class="" col-sm-3">
															<button id="employeeSaveButton" type="button"
																class="btn btn-primary" "
																onclick="return saveEmployeeRegistrationDetails();">
																Save Employee</button>


														</div>
														<div class="" col-sm-2">
															<button id="empUpdateButton" type="button"
																class="btn btn-primary"
																onclick="return updateEmployeeRegistrationDetails();">
																Update Employee</button>

														</div>
													</div>


												</form>
											</div>


										</div>

									



										<div class="panel-body">
											<br> <br>
											<div class="box">
												<div class="box-body">

													<div id="SearchResult"></div>


													<input type="hidden" name="datatable_row_id"
														id="datatable_row_id" value="" />


													<table id="employeeDetailsTable"
														class="table table-bordered table-striped">
														<thead>
															<tr>
																<th>Sr no</th>
																<th>Full Name</th>
																<th>Username</th>
																<th>Password</th>
																<th>Mobile No</th>
																<th>Status</th>
																<th>Action</th>

															</tr>
														</thead>
														<tbody>
															<c:forEach var="employeeDetails"
																items="${employeeRegistrationDetailsList}"
																varStatus="theCount">
																<tr>
																	<td><a href="#">${theCount.count }</a></td>
																	<td><a href="#">${employeeDetails.fullName}</a></td>
																	<td><a href="#">${employeeDetails.userName}</a></td>
																	<td><a href="#">${employeeDetails.password}</a></td>
																	<td><a href="#">${employeeDetails.mobileNo}</a></td>

																	<td><c:set var="num" scope="session"
																			value="${employeeDetails.status.status_id}">
																		</c:set> <c:choose>

																			<c:when test="${num eq '2' or num  eq 2  }">

																				<a style="color: red; text-decoration: none;"><c:out
																						value="Deleted" /></a>
																			</c:when>

																			<c:when test="${num eq '1' or num  eq 1  }">
																				<a style="color: green; text-decoration: none;"><c:out
																						value="Enable" /></a>
																			</c:when>

																			<c:when test="${num eq '3' or num  eq 3  }">
																				<a style="color: red; text-decoration: none;"><c:out
																						value="Disable" /></a>
																			</c:when>


																		</c:choose></td>


																	<td class="text-center">
																	<c:set var="num" scope="session" value="${employeeDetails.status.status_id}">
																	</c:set> <c:if test="${num !=2}">
																			<div class="dropdown">
																				<a class="dropdown-toggle actionLink" id="menu1" type="button"
																					data-toggle="dropdown" >Action</a>


																				<ul class="dropdown-menu" role="menu" aria-labelledby="menu1" style="overflow: hidden;">
																				<li role="presentation">
																				<a role="menuitem"	tabindex="-1" href="#" " onclick="" editEmployeeDetails('${theCount.count}','${employeeDetails.fullName}','${employeeDetails.userName}','${employeeDetails.password}','${employeeDetails.mobileNo}','${employeeDetails.emp_id}',
																				'${employeeDetails.accountNo}','${employeeDetails.branchName}','${employeeDetails.ifcCode}','${employeeDetails.bankName}','${employeeDetails.designation}','${employeeDetails.experienceDetails}',
																				'${employeeDetails.totalExperience}','${employeeDetails.currentSalary}','${employeeDetails.previousSalary}','${employeeDetails.alternateMobileNo}','${employeeDetails.email}','${employeeDetails.address}',
																				'${employeeDetails.city}','${employeeDetails.dateOfBirth}','${employeeDetails.registrationDate}','${employeeDetails.status.status_id}','${employeeDetails.userModel.userId}','${employeeDetails.userModel.firstName}',);">Edit</a></li>

																				<li role="presentation"><a role="menuitem" tabindex="-1" href="#" onclick="" enableEmployeeDetails('${theCount.count}','${employeeDetails.fullName}','${employeeDetails.userName}','${employeeDetails.password}','${employeeDetails.mobileNo}','${employeeDetails.emp_id}',
																				'${employeeDetails.accountNo}','${employeeDetails.branchName}','${employeeDetails.ifcCode}','${employeeDetails.bankName}','${employeeDetails.designation}','${employeeDetails.experienceDetails}',
																				'${employeeDetails.totalExperience}','${employeeDetails.currentSalary}','${employeeDetails.previousSalary}','${employeeDetails.alternateMobileNo}','${employeeDetails.email}','${employeeDetails.address}',
																				'${employeeDetails.city}','${employeeDetails.dateOfBirth}','${employeeDetails.registrationDate}','${employeeDetails.status.status_id}','${employeeDetails.userModel.userId}','${employeeDetails.userModel.firstName}',);">Enable</a></li>

																				<li role="presentation"><a role="menuitem" tabindex="-1" href="#" onclick="" disableEmployeeDetails('${theCount.count}','${employeeDetails.fullName}','${employeeDetails.userName}','${employeeDetails.password}','${employeeDetails.mobileNo}','${employeeDetails.emp_id}',
																				'${employeeDetails.accountNo}','${employeeDetails.branchName}','${employeeDetails.ifcCode}','${employeeDetails.bankName}','${employeeDetails.designation}','${employeeDetails.experienceDetails}',
																				'${employeeDetails.totalExperience}','${employeeDetails.currentSalary}','${employeeDetails.previousSalary}','${employeeDetails.alternateMobileNo}','${employeeDetails.email}','${employeeDetails.address}',
																				'${employeeDetails.city}','${employeeDetails.dateOfBirth}','${employeeDetails.registrationDate}','${employeeDetails.status.status_id}','${employeeDetails.userModel.userId}','${employeeDetails.userModel.firstName}',);">Disable</a></li>

																				<li role="presentation"><a role="menuitem" tabindex="-1" href="#" onclick="" deleteEmployeeDetails('${theCount.count}','${employeeDetails.fullName}','${employeeDetails.userName}','${employeeDetails.password}','${employeeDetails.mobileNo}','${employeeDetails.emp_id}',
																				'${employeeDetails.accountNo}','${employeeDetails.branchName}','${employeeDetails.ifcCode}','${employeeDetails.bankName}','${employeeDetails.designation}','${employeeDetails.experienceDetails}',
																				'${employeeDetails.totalExperience}','${employeeDetails.currentSalary}','${employeeDetails.previousSalary},','${employeeDetails.alternateMobileNo}','${employeeDetails.email}','${employeeDetails.address}',
																				'${employeeDetails.city}','${employeeDetails.dateOfBirth}','${employeeDetails.registrationDate}','${employeeDetails.status.status_id}','${employeeDetails.userModel.userId}','${employeeDetails.userModel.firstName}',);">Delete</a></li>



																				</ul>


																			</div>

																		</c:if></td>
																</tr>
															</c:forEach>
														</tbody>

													</table>
												</div>
												<!-- /.box-body -->
											</div>
										</div>









									</div>




									<!-- /.tab-pane -->
									<div class="tab-pane" id="assignEmployeePermission_toggle">
										<div class="panel panel-danger">
											<div class="panel-heading" style="background-color: #d23779;">
												<font color="white">Assign Employee Permission</font>
											</div>
											<div class="alert alert-success alert-dismissable"
												id="permissionAssingsuccess">
												<button type="button" class="close" data-dismiss="alert"
													aria-hidden="true">&times;</button>
												<strong>Message: </strong>Permission Assigned Successfully.
											</div>
											
											<div class="alert alert-danger alert-dismissable"
												id="selectEmployee">
												<button type="button" class="close" data-dismiss="alert"
													aria-hidden="true">&times;</button>
												<strong>Message: </strong>Please Select Employee .
											</div>

											<div id="assignFeedBackType" class="tabsContentWrapper"
												style="margin: 20px;">
												<div class="feedbackTypeWrap">
													<div id="documetntFirst" align="center">
													
													<div class="row">
														<div class="" col-sm-2" align="left" >
															<label> Select Employee </label> 
														</div>
														<div class="col-sm-4" align="left">
														
														<select id="employee_lst" class="" input-sm"
																	style="width: 250px;"
																	onchange="loadEmployeeAssignPermissionDetailsByEmp_Id(this.value);">
																	<option value="0">Select Employee</option>
																	<c:forEach var="emp" items="${employeeRegistrationDetailsList}" varStatus="theCount">
                                                                       
                                                                       <c:if test="${emp.status.status_id == 1}">
                                                                       <option value="${emp.userModel.userId}">
																			${emp.userModel.firstName}
																		</option>		
				                                                      </c:if>
																	</c:forEach>
																</select>
														
														
														</div>

                                                         <div class="" col-sm-4">
                                                         <label for="countryName" class="col-sm-8 control-label">
                                                          <input type="checkbox"  id="checkUncheack" onclick="cheakAndUncheckAllCheckBox()">
                                                         Check All Permission
																</label>
														</div>

														 <!-- <div class="" col-sm-2" align="left" >
																 <input type="checkbox"  id="checkUncheack" onclick="cheakAndUncheckAllCheckBox()">
														</div> -->
													
													</div>
													
													
													
													
													
													
													
													
													
													
														<%-- <div class=row>
															<div class="col-sm-3">
																<label> Select Employee </label> <select
																	id="employee_lst" class="" input-sm"
																	style="width: 250px;"
																	onchange="loadEmployeeAssignPermissionDetailsByEmp_Id(this.value);">
																	<option value="0">Select Employee</option>
																	<c:forEach var="emp" items="${employeeRegistrationDetailsList}" varStatus="theCount">
                                                                       
                                                                       <c:if test="${emp.status.status_id == 1}">
                                                                       <option value="${emp.userModel.userId}">
																			${emp.userModel.firstName}
																		</option>		
				                                                      </c:if>
																	</c:forEach>
																</select>
															</div>
														</div> --%>
													</div>

													<br>
													<div class=row>
														<div class="tab-pane active designList" id="association">
														
														<div class="box-body">
														<!-- <div class="form-group">
															<label for="countryName" class="col-sm-2 control-label">Check All Permission
																</label>

															<div class="col-sm-4">
																 <input type="checkbox"  id="checkUncheack" onclick="cheakAndUncheckAllCheckBox()">
															</div>
															
														</div> -->


													</div>
														
															<form id="EmployeeUserPermissionTypeAssociationForm">
																<ul class="list-unstyled clearfix">
																	<div id="CompanyFeedBackTypeAssociationTable">

																		<c:forEach var="permissionTypeobj"
																			items="${permissionTypesList}" varStatus="theCount">
					                                              	         <div class="col-md-3" id="permissionCheckBox">
																			
																				<label><input type="checkbox"
																					id="${permissionTypeobj.id}"
																					name="${permissionTypeobj.id}">
																					${permissionTypeobj.permissionType}
																					
																					</label>
																					
																					<div id="newChekBox">
																					
																					</div>
																			</div>

																		</c:forEach>

																	</div>

																</ul>
																<div align="center" style="padding-bottom: 15px;">
																	<input type="Button" value="Assign Permission Type" 
																		onclick="saveUserPermissionTypeAssociation();"
																		class="btn btn-primary" " />
																</div>
															</form>



														</div>
														
														
													</div>
												</div>
											</div>
										</div>
									</div>
									<!-- /.tab-pane -->

									<div class="tab-pane" id="permissionType_toggle">

										<div class="panel panel-danger">
											<div class="panel-heading" style="background-color: #d23779;">
												<font color="white">Add Permission Type</font>
											</div>
											<div class="panel-body">

												<div class="alert alert-danger alert-dismissable"
													id="permissionTypeSelect">
													<button type="button" class="close" data-dismiss="alert"
														aria-hidden="true">&times;</button>
													<strong>Error: </strong>Please Enter Permission Type.
												</div>




												<div class="alert alert-success alert-dismissable"
													id="permissionTypesuccess">
													<button type="button" class="close" data-dismiss="alert"
														aria-hidden="true">&times;</button>
													<strong>Message: </strong>Permission Type saved
													Successfully.
												</div>


												<form class="form-horizontal" id="permissionTypeForm">
													<div class="box-body">
														<div class="form-group">
															<label for="countryName" class="col-sm-2 control-label">Permission
																Type</label>

															<div class="col-sm-4">
																<input type="text" class="form-control input-sm"
																	id="permission_Type" name="permission_Type">
															</div>
															<div class="col-sm-4">
																<button type="button" class="btn btn-primary"
																	onclick="return savePermissionType();">Add
																	Permission</button>
															</div>
														</div>


													</div>


												</form>

											</div>
										</div>





										<div class="panel panel-danger">

											<div class="panel-body">
												<div class="box">
													<div class="box-body">
														<table id="permissionTypeTable"
															class="table table-bordered table-striped">
															<thead>
																<tr>
																	<th>Sr No.</th>
																	<th>Permission Type</th>
																</tr>
															</thead>
															<tbody>

																<c:forEach var="permission_type"
																	items="${permissionTypesList}" varStatus="theCount">
																	<tr>
																		<td>${theCount.count }</td>
																		<td>${permission_type.permissionType}</td>

																	</tr>
																</c:forEach>



															</tbody>

														</table>
													</div>
													<!-- /.box-body -->
												</div>

											</div>
										</div>


									</div>
              

									<!-- /.tab-pane -->
								</div>
								<!-- /.tab-content -->
									
							</div>
							<!-- /.nav-tabs-custom -->
							
						</div>
						<!-- /.col -->
				
					</div>
					<!-- /.row -->
					
				</div>


			</section>
			<!-- /.content -->
		</div>

		
	</div>


	<!-- jQuery 2.2.3 -->
	<!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip -->
	<script>
		var data = eval('${deleteUserList}');
		var table = $('#example2')
				.DataTable(
						{
							"sPaginationType" : "full_numbers",
							"sDom" : 'T<"clear">lfrtip<"clear spacer">T',
							"aaData" : data,
							"aoColumns" : [
									{
										"data" : "id",
										"defaultContent" : ""
									},
									{
										"data" : "userModel.firstName",
										"defaultContent" : ""
									},
									{
										"data" : "deletedBy.firstName",
										"defaultContent" : ""
									},
									{
										"data" : "deleteDate",
										"render" : function(data) {
											var date = new Date(data);
											var month = date.getMonth() + 1;
											return date.getDate()
													+ "-"
													+ (month.length > 1 ? month
															: month) + "-"
													+ date.getFullYear();
										}
									} ]
						});
		
		 
		  
		  $('#permissionTypeTable').dataTable({
			  "pagingType": "full_numbers"
			}); 
		  
		  $('#employeeDetailsTable').dataTable({
			  "pagingType": "full_numbers"
			}); 
		  
		  
		 
		
	</script>

	<script type="text/javascript">
									$("#updateButton").hide();
									$("#nameSelect").hide();
									$("#emailselect").hide();
									$("#mobilenumselect").hide();
									$("#successSelect").hide();
									$("#unameselect").hide();
									$("#passwordselect").hide();
									$("#emailAvailable").hide();
									$("#mobileAvailable").hide();
									$("#UserNameAvailable").hide();
									$("#invalidName").hide();
									$("#mobileNoRequired").hide();
									$("#enableEmployeeAlert").hide();
									$("#disableEmployeeAlert").hide();
									$("#enabledEmployeeAlert").hide();
									$("#disabledEmployeeAlert").hide();
									
									$("#permissionTypesuccess").hide();
									$("#permissionTypeSelect").hide();
									$("#selectEmployee").hide();
									$("#assignPermissionSuccess").hide();
									$("#mobileNoAlreadyPresent").hide();
									$("#updateEmployeeErrorAlert").hide();
									$("#employeeDeleteAlert").hide();
									$("#permissionAssingsuccess").hide();
									$("#employeeUpdateSuccess").hide();
									$("#dateOfBirthRequiredAlert").hide();
									$("#registrationRequiredAlert").hide();
									
									$("#empUpdateButton").prop('disabled', true);

								
									 var date = new Date();
									 document.getElementById("dateOfBirth").value = (date.getMonth() + 1) + '/' + date.getDate() + '/' + date.getFullYear();
											
		</script>


	<script type="text/javascript">
	
	
	document.getElementById("registrationDate").value = (date.getMonth() + 1) + '/' + date.getDate() + '/' + date.getFullYear();
	$("#registrationDate").datepicker({
		dateFormat : 'dd/mm/yy',
		changeMonth : true,
		changeYear : true,
		yearRange : '1980:2099'
	}).datepicker('setDate', new Date());

	
	function enableEmployeeDetails(srno,fullName,userName,password,mobileNo,empid,accountNo,branchName,ifscode,bankName,degignation,experienceDetails,
			totalExperience,cureentSalary,prevouseSalary,alernateMobileNo,email,address,city,dateOfBirth,registration_Date,status_id,employeeUserId,employeeUserFullName)
	{
		
		clearAllTextFields();
		
		$('html, body').animate({ scrollTop: $('#home').offset().top }, 'slow');
		
		 var rows = $("#employeeDetailsTable").dataTable().fnGetNodes();
		 var statusDisable = 'Enable';
		
	   for (var i = 0; i < rows.length; i++) 
	   {
	    var id = $(rows[i]).find("td:eq(0)").text();
	    var selectedEmplyeeStatus = $(rows[i]).find("td:eq(5)").text();
	    
	    var isEqual = (id.toString() === srno.toString());
	    var isDisable =  (statusDisable.toString() === selectedEmplyeeStatus.toString().trim());

	    if(isEqual == true)
	     {
	   	       if(isDisable == true)
	     	 {
	   		     $("#enableEmployeeAlert").slideDown();
	   	   		 $("#enableEmployeeAlert").fadeOut(4000); 
	   	   		
	   	    	 return false; 
	   		 
	   	      } 
	   	  // return false;
	   	
		  }  
	   } 
		
	
		$.confirm({
		    title: '',
		    content: 'Are you sure you want to Enable this Employee? ',
		    buttons: {
		        confirm: function () {
		        	var status_id = eval(1)
		        	
		        	$.ajax({
		    			dataType : 'text',
		    			url : "saveOrUpdateEmployeeUserDetails",
		    			data : {empid:empid,status_id:status_id},
		    			type : "POST",
		    			success : function(responce) 
		    			{
		    			
		    				if(responce == "enableEmployeeDetails")
	    					{
			    				$("#enabledEmployeeAlert").slideDown();
			    				$("#enabledEmployeeAlert").fadeOut(3000);
								$('<option/>').attr("value",employeeUserId).text(employeeUserFullName).appendTo('#employee_lst');
			    				
	    					}
							
	    				
		    				var rows = $("#employeeDetailsTable").dataTable().fnGetNodes();
		    				var row = eval(srno);
		    				var status = "Enable";
		    				
		    				$(rows[row - 1]).find("td:eq(5)").text(status).addClass('enableTextColor');;	
		    				
		    				$(rows[row - 1])
		    				.find("td:eq(6)")
		    				.html("<style> .aligCenter{width: 100%; display: block; text-align: center;}</style>"
		    						+"<td class='aligCenter'>"
		    						+"<div class='dropdown'>" 
		    						+"<a class='dropdown-toggle' id='menu1' type='button' data-toggle='dropdown' style=" width: 100%; display: block; text-align: center;" cursor:pointer;'>"
		    			            +"Action</a>"
		    			            +"<ul class='dropdown-menu' role='menu' aria-labelledby='menu1'>"
		    			            +"<li role='presentation'><a role='menuitem' tabindex='-1' href="#" ' onclick="return" editEmployeeDetails("
		    						+"&quot;"
		    						+ row +"&quot;"
		    						+ ",&quot;"+fullName
		    						+ "&quot;,&quot;"+userName
		    						+ "&quot;,&quot;"+password
		    						+ "&quot;,&quot;"+mobileNo
		    						+ "&quot;,&quot;"+empid
		    						+ "&quot;,&quot;"+accountNo
		    						+ "&quot;,&quot;"+branchName
		    						+ "&quot;,&quot;"+ifscode
		    						+ "&quot;,&quot;"+bankName
		    						+ "&quot;,&quot;"+degignation
		    						+ "&quot;,&quot;"+experienceDetails
		    						+ "&quot;,&quot;"+totalExperience
		    						+ "&quot;,&quot;"+cureentSalary
		    						+ "&quot;,&quot;"+prevouseSalary
		    						+ "&quot;,&quot;"+alernateMobileNo
		    						+ "&quot;,&quot;"+email
		    						+ "&quot;,&quot;"+address
		    						+ "&quot;,&quot;"+city
		    						+ "&quot;,&quot;"+dateOfBirth
		    						+ "&quot;,&quot;"+registration_Date
		    						+ "&quot;,&quot;"+status_id  
		    						+ "&quot;,&quot;"+employeeUserId
		    						+ "&quot;,&quot;"+employeeUserFullName
		    						+ "&quot;"
		    						+");'>Edit</a></li>"
		    						+"<li role='presentation'><a role='menuitem' tabindex='-1' href="#" ' onclick="return" disableEmployeeDetails("
		    						+"&quot;"
		    						+ row +"&quot;"
		    						+ ",&quot;"+fullName
		    						+ "&quot;,&quot;"+userName
		    						+ "&quot;,&quot;"+empPassword
		    						+ "&quot;,&quot;"+mobileNo
		    						+ "&quot;,&quot;"+employee_id
		    						+ "&quot;,&quot;"+accountNo
		    						+ "&quot;,&quot;"+branchName
		    						+ "&quot;,&quot;"+ifsccode
		    						+ "&quot;,&quot;"+bankName
		    						+ "&quot;,&quot;"+designation
		    						+ "&quot;,&quot;"+experienceDetails
		    						+ "&quot;,&quot;"+totalExperience
		    						+ "&quot;,&quot;"+currentSallary
		    						+ "&quot;,&quot;"+previousSallary
		    						+ "&quot;,&quot;"+alternateMobileNo
		    						+ "&quot;,&quot;"+email
		    						+ "&quot;,&quot;"+address
		    						+ "&quot;,&quot;"+city
		    						+ "&quot;,&quot;"+dateofBirth
		    						+ "&quot;,&quot;"+registration_Date
		    						+ "&quot;,&quot;"+disableStatus
		    						+ "&quot;,&quot;"+employeeUserId
		    						+ "&quot;,&quot;"+employeeUserFullName
		    						+ "&quot;"
		    						+");'>Disable</a></li>"
		    						+"<li role='presentation'><a role='menuitem' tabindex='-1' href="#" ' onclick="return" enableEmployeeDetails("
		    						+"&quot;"
		    						+ row +"&quot;"
		    						+ ",&quot;"+fullName
		    						+ "&quot;,&quot;"+userName
		    						+ "&quot;,&quot;"+password
		    						+ "&quot;,&quot;"+mobileNo
		    						+ "&quot;,&quot;"+empid
		    						+ "&quot;,&quot;"+accountNo
		    						+ "&quot;,&quot;"+branchName
		    						+ "&quot;,&quot;"+ifscode
		    						+ "&quot;,&quot;"+bankName
		    						+ "&quot;,&quot;"+degignation
		    						+ "&quot;,&quot;"+experienceDetails
		    						+ "&quot;,&quot;"+totalExperience
		    						+ "&quot;,&quot;"+cureentSalary
		    						+ "&quot;,&quot;"+prevouseSalary
		    						+ "&quot;,&quot;"+alernateMobileNo
		    						+ "&quot;,&quot;"+email
		    						+ "&quot;,&quot;"+address
		    						+ "&quot;,&quot;"+city
		    						+ "&quot;,&quot;"+dateOfBirth
		    						+ "&quot;,&quot;"+registration_Date
		    						+ "&quot;,&quot;"+enableStatus
		    						+ "&quot;,&quot;"+employeeUserId
		    						+ "&quot;,&quot;"+employeeUserFullName
		    						+ "&quot;"
		    						+");'>Enable</a></li>"
		    						+" </ul></div>"
		    						+ "</td>");		
		    			   
		    				
		    				
		    				
		    			},
		    			error : function(result){
		    				alert('error'+JSON.stringify(result));
		    				return false;
		    			} 

		    		}); 
		           
		        	
		        	
		        },
		        cancel: function () {
		            
		        }
		      
		    }
		});
		

	}
	
	
	
	function disableEmployeeDetails(srno,fullName,userName,password,mobileNo,empid,accountNo,branchName,ifscode,bankName,degignation,experienceDetails,
			totalExperience,cureentSalary,prevouseSalary,alernateMobileNo,email,address,city,dateOfBirth,registration_Date,status_id,employeeUserId,employeeUserFullName)
	{
		clearAllTextFields();
		$('html, body').animate({ scrollTop: $('#home').offset().top }, 'slow');
		
		 var rows = $("#employeeDetailsTable").dataTable().fnGetNodes();
		 var statusDisable = 'Disable';
		
	   for (var i = 0; i < rows.length; i++) 
	   {
	    var id = $(rows[i]).find("td:eq(0)").text();
	    var selectedTeamStatus = $(rows[i]).find("td:eq(5)").text();
	    
	    var isEqual = (id.toString() === srno.toString());
	    var isDisable =  (statusDisable.toString() === selectedTeamStatus.toString().trim());

	    if(isEqual == true)
	     {
	   	       if(isDisable == true)
	     	 {
	   		     $("#disableEmployeeAlert").slideDown();
	   	   		 $("#disableEmployeeAlert").fadeOut(4000);
	   	   		
	   	    	 return false; 
	   		 
	   	      } 
	   	  // return false;
	   	
		  }  
	   } 
		
		
		
			var status_id = eval(3)
		
			$.confirm({
			    title: '',
			    content: 'Are you sure you want to Disable this Employee? ',
			    buttons: {
			        confirm: function () {
			        	
			        	
			        	$.ajax({
			    			dataType : 'text',
			    			url : "saveOrUpdateEmployeeUserDetails",
			    			data : {empid:empid,status_id:status_id},
			    			type : "POST",
			    			success : function(responce) 
			    			{
			    				
			    				if(responce == "DisableEmployeeDetails")
		    					{
				    				
			    				$("#disabledEmployeeAlert").slideDown();
			    				$("#disabledEmployeeAlert").fadeOut(3000);
			    				
			    				$("#employee_lst option[value="+employeeUserId+"]").remove();
			    				
		    					}
			    				
			    				var rows = $("#employeeDetailsTable").dataTable().fnGetNodes();
			    				var row = eval(srno);
			    				var status = "Disable";
			    				
			    				$(rows[row - 1]).find("td:eq(5)").text(status);
			    				
			    				$(rows[row - 1])
			    				.find("td:eq(6)")
			    				.html("<style> .aligCenter{width: 100%; display: block; text-align: center;}</style>"
			    						+"<td class='aligCenter'>"
			    						+"<div class='dropdown'>" 
			    						+"<a class='dropdown-toggle' id='menu1' type='button' data-toggle='dropdown' style=" width: 100%; display: block; text-align: center;" cursor:pointer;'>"
			    			            +"Action</a>"
			    			            +"<ul class='dropdown-menu' role='menu' aria-labelledby='menu1'>"
			    			            +"<li role='presentation'><a role='menuitem' tabindex='-1' href="#" ' onclick="return" editEmployeeDetails("
			    						+"&quot;"
			    						+ row +"&quot;"
			    						+ ",&quot;"+fullName
			    						+ "&quot;,&quot;"+userName
			    						+ "&quot;,&quot;"+password
			    						+ "&quot;,&quot;"+mobileNo
			    						+ "&quot;,&quot;"+empid
			    						+ "&quot;,&quot;"+accountNo
			    						+ "&quot;,&quot;"+branchName
			    						+ "&quot;,&quot;"+ifscode
			    						+ "&quot;,&quot;"+bankName
			    						+ "&quot;,&quot;"+degignation
			    						+ "&quot;,&quot;"+experienceDetails
			    						+ "&quot;,&quot;"+totalExperience
			    						+ "&quot;,&quot;"+cureentSalary
			    						+ "&quot;,&quot;"+prevouseSalary
			    						+ "&quot;,&quot;"+alernateMobileNo
			    						+ "&quot;,&quot;"+email
			    						+ "&quot;,&quot;"+address
			    						+ "&quot;,&quot;"+city
			    						+ "&quot;,&quot;"+dateOfBirth
			    						+ "&quot;,&quot;"+registration_Date
			    						+ "&quot;,&quot;"+status_id  
			    						+ "&quot;,&quot;"+employeeUserId 
			    						+ "&quot;,&quot;"+employeeUserFullName
			    						+ "&quot;"
			    						+");'>Edit</a></li>"
			    						+"<li role='presentation'><a role='menuitem' tabindex='-1' href="#" ' onclick="return" disableEmployeeDetails("
			    						+"&quot;"
			    						+ row +"&quot;"
			    						+ ",&quot;"+fullName
			    						+ "&quot;,&quot;"+userName
			    						+ "&quot;,&quot;"+empPassword
			    						+ "&quot;,&quot;"+mobileNo
			    						+ "&quot;,&quot;"+employee_id
			    						+ "&quot;,&quot;"+accountNo
			    						+ "&quot;,&quot;"+branchName
			    						+ "&quot;,&quot;"+ifsccode
			    						+ "&quot;,&quot;"+bankName
			    						+ "&quot;,&quot;"+designation
			    						+ "&quot;,&quot;"+experienceDetails
			    						+ "&quot;,&quot;"+totalExperience
			    						+ "&quot;,&quot;"+currentSallary
			    						+ "&quot;,&quot;"+previousSallary
			    						+ "&quot;,&quot;"+alternateMobileNo
			    						+ "&quot;,&quot;"+email
			    						+ "&quot;,&quot;"+address
			    						+ "&quot;,&quot;"+city
			    						+ "&quot;,&quot;"+dateofBirth
			    						+ "&quot;,&quot;"+registration_Date
			    						+ "&quot;,&quot;"+status_id
			    						+ "&quot;,&quot;"+employeeUserId  
			    						+ "&quot;,&quot;"+employeeUserFullName
			    						+ "&quot;"
			    						+");'>Disable</a></li>"
			    						+"<li role='presentation'><a role='menuitem' tabindex='-1' href="#" ' onclick="return" enableEmployeeDetails("
			    						+"&quot;"
			    						+ row +"&quot;"
			    						+ ",&quot;"+fullName
			    						+ "&quot;,&quot;"+userName
			    						+ "&quot;,&quot;"+password
			    						+ "&quot;,&quot;"+mobileNo
			    						+ "&quot;,&quot;"+empid
			    						+ "&quot;,&quot;"+accountNo
			    						+ "&quot;,&quot;"+branchName
			    						+ "&quot;,&quot;"+ifscode
			    						+ "&quot;,&quot;"+bankName
			    						+ "&quot;,&quot;"+degignation
			    						+ "&quot;,&quot;"+experienceDetails
			    						+ "&quot;,&quot;"+totalExperience
			    						+ "&quot;,&quot;"+cureentSalary
			    						+ "&quot;,&quot;"+prevouseSalary
			    						+ "&quot;,&quot;"+alernateMobileNo
			    						+ "&quot;,&quot;"+email
			    						+ "&quot;,&quot;"+address
			    						+ "&quot;,&quot;"+city
			    						+ "&quot;,&quot;"+dateOfBirth
			    						+ "&quot;,&quot;"+registration_Date
			    						+ "&quot;,&quot;"+status_id
			    						+ "&quot;,&quot;"+employeeUserId  
			    						+ "&quot;,&quot;"+employeeUserFullName
			    						+ "&quot;"
			    						+");'>Enable</a></li>"
			    						+" </ul></div>"
			    						+ "</td>");		
			    			 
			    				
			    				
								
			    			},
			    			error : function(result){
			    				alert('error'+JSON.stringify(result));
			    				return false;
			    			} 

			    		}); 
			           
			        	
			        	
			        },
			        cancel: function () {
			            
			        }
			      
			    }
			});
		
	}
	
	function deleteEmployeeDetails(srno,fullName,userName,password,mobileNo,empid,accountNo,branchName,ifscode,bankName,degignation,experienceDetails,
			totalExperience,cureentSalary,prevouseSalary,alernateMobileNo,email,address,city,dateOfBirth,registration_Date,status_id, userModelId)
	{

		$('html, body').animate({ scrollTop: $('#home').offset().top }, 'slow');
		
		   clearAllTextFields();
			var status_id = eval(2);
			$.confirm({
			    title: '',
			    content: 'Are you sure you want to Delete this Employee? ',
			    buttons: {
			        confirm: function () {
			        	
			        	$.ajax({
			    			dataType : 'text',
			    			url : "saveOrUpdateEmployeeUserDetails",
			    			data : {empid:empid,status_id:status_id},
			    			type : "POST",
			    			success : function(responce) 
			    			{
			    				
			    				     var oTable = $('#employeeDetailsTable').dataTable();
			    				     var rows = $("#employeeDetailsTable").dataTable().fnGetNodes();
			    				     for (var i = 0; i < rows.length; i++) 
			    				     {
			    				      var id = $(rows[i]).find("td:eq(0)").text();
			    				      if (id == srno) 
			    				      {
			    				       var row = $(rows[i]).closest('tr');
			    				       $('.dataTable').dataTable().fnDeleteRow(row);
			    				       $("#employee_lst option[value="+userModelId+"]").remove();
			    				       $("#employeeDeleteAlert").slideDown();
			    				       $("#employeeDeleteAlert").fadeOut(3000);
			    				       $("#employee_lst option[value="+userModelId+"]").remove();
			    				       
			    				       
			    				      }
			    				     }
			   	
			    			},
			    			error : function(result){
			    				alert('error'+JSON.stringify(result));
			    				return false;
			    			} 

			    		}); 
			           
			        	
			        	
			        },
			        cancel: function () {
			            
			        }
			      
			    }
			});
		
		
		
	}
	
	function clearAllTextFields()
	{
		$("#fullName").val("");
		$("#dateOfBirth").val("");
		$("#userName").val("");
		$("#employeePassword").val("");
		$("#email").val("");
		$("#designation").val("");
		$("#mobileNo").val("");
		$("#alernateMobileNo").val("");
		$("#city").val("");
		$("#address").val("");
		$("#previousSalary").val("");
		$("#currentSalary").val("");
		$("#totalExperience").val("");
		$("#experienceDetails").val("");
		$("#bankName").val("");
		$("#ifcCode").val("");
		$("#branchName").val("");
		$("#accountNo").val("");
		$("#registrationDate").val("");
		
		
		$("#employeeHiddenId").val("");
		$("#editselectedMobileNo").val("");
		$("#editselectedUserName").val("");
		$("#employeeEnableDisableStatus").val("");

		$("#employeeSaveButton").prop('disabled',false);
		$("#empUpdateButton").prop('disabled', true);
		var date = new Date();
		document.getElementById("dateOfBirth").value = (date.getMonth() + 1) + '/' + date.getDate() + '/' + date.getFullYear();
		
		document.getElementById("registrationDate").value = (date.getMonth() + 1) + '/' + date.getDate() + '/' + date.getFullYear();
	}
	</script>


  <div id="light" class="" style="position: fixed; float: left; width: 40%; padding: 0px; margin: 0px; top: 30%; left: 50%; overflow: hidden; display: none;">
		
					<input type="hidden" id="yearValue" >
					
					<div class="meetingContent">
					
						
						<img src="${pageContext.request.contextPath}/resources/images/spinner.gif" class="logo" style="width: 150px">
							
						</div>
					Loading
				</div>
				<div id="fade" class="black_overlay"></div>
              


</body>
</html>




