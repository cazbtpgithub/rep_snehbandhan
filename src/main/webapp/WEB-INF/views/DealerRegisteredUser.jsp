<!DOCTYPE html>
<html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>

<title>Registered User</title>
<!-- Tell the browser to be responsive to screen width -->
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
<jsp:include page="body.jsp"></jsp:include>

<link href="<c:url value='/resources/mail/bootstrap3-wysihtml5.min.css'/> rel="stylesheet">
<link rel="stylesheet" type="text/css" href="resources/confirmBox/confirm.css" media="screen">
 <script src="resources/upgradePackage/upgradePackage.js"></script>	
 <script src="resources/Registration/editProfile.js"></script>  
  <script src="resources/Registration/registeredUser.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/mail/bootstrap3-wysihtml5.all.min.js"></script>
	<script src="resources/confirmBox/confirm.js" type="text/javascript"></script>



<script type="text/javascript">

var educationId = new Array();
var occupationId = new Array();
var flag=false;

</script>

<script>
		function showOccupation() {
			$(".panel1").slideToggle("slow");
		}
		
		function showEducation() {
			$(".panel123").slideToggle("slow");
		}
		</script>

<style>
div.panel123, p.flip {
	margin: 0px;
	background: #ddd;
	border: solid 1px #fff;
}

div.panel123 {
	background-color: #ddd;
	display: none;
}

div.panel1, p.flip1 {
	margin: 0px;
	
	background: #ddd;
	border: solid 1px #fff;
}

div.panel1 {
	background-color: #ddd;
	display: none;
}

.box-custom {
	background: #dddddd;
}

label {
	margin-right: -20px;
	font: normal 12px cambria !important;
}

 table{
	font:  12px cambria !important;
} 
</style>
</head>
<body class="hold-transition skin-blue sidebar-mini">
	<div class="wrapper">

		<jsp:include page="menuForDealer.jsp"></jsp:include>
		<div class="content-wrapper">
			<!-- Main content -->
			<section class="content">
				<div class="row">

					<div class="col-md-12">
						<!-- /.box-header -->

						<div class="panel panel-danger">
							<div class="panel-heading" style="background-color:#d23779"><font color="White">Registered User Detail</font></div>
							<div class="panel-body">
								<br> <br>
								<div class="box">
									<div class="box-body">

										<div id="SearchResult"></div>
										
										
										<input type="hidden" name="hiddenUsermodelId" id="hiddenUsermodelId"  />
										<input type="hidden" name="hiddenUserpartnerId" id="hiddenUserpartnerId"  />
										<input type="hidden" name="hiddenuserId" id="hiddenuserId" />
	

										<table id="example1"
											class="table table-bordered table-striped">
											<thead>
												<tr>
													<th>Id</th>
													<th>Name</th>
													<!-- <th>Gender</th> -->
													<th>Registration Date</th>
													<th>DOB</th>
													<th>View</th>
													<!-- <th>Delete</th> -->

												</tr>
											</thead>
											<tbody>

											</tbody>

										</table>
										 
		
									</div>
									<!-- /.box-body -->
								</div>
								
		<br>
                  <div class="col-sm-3">
                  
                    <button type="button" class="btn btn-primary" " id="printDealerWiseMemberDetailsButton" style="margin-left: 160%;"
                     onclick="printDealerWiseAllMemberDetails();" >Print Register Member Details</button>
                  </div>

							
							<div id="light" class="" style="position: fixed; float: left; width: 40%; padding: 0px; margin: 0px; top: 30%; left: 50%; overflow: hidden; display: none;">
		
					<input type="hidden" id="yearValue" >
					
					<div class="meetingContent">
					
						
						<img src="${pageContext.request.contextPath}/resources/images/spinner.gif" class="logo" style="width: 150px">
							
						</div>
					Loading
				</div>
				<div id="fade" class="black_overlay"></div>
							
							
								
							</div>






						</div>

					</div>


				</div>
			</section>

			<div id="myModal" class="modal fade" role="dialog" style="">
				<div class="modal-dialog">

					<!-- Modal content-->
					<div class="modal-content" role="dialog"
						style="height: 600px; width: 1150px; margin-left: -250px;overflow: auto;overflow-x:hidden;">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" onclick="closeModel();">&times;</button>
							<h4 class="modal-title">Registered User Detail <a id="viewFullProfileModalBtn" href="#" target="_blank" class="btn btn-default btn-xs pull-right" style="margin-right:20px;"><i class="fa fa-external-link"></i> View Full Profile</a></h4>
						</div>
						<div class="modal-body">
 		<div class="alert alert-danger alert-dismissable"
							id="packFail">
							<button type="button" class="close" data-dismiss="alert"
								aria-hidden="true">&times;</button>
							<font size="3px;"><strong>Error: </strong>Kit Id or Password Is Incorrect </font>
		</div>
		<div class="alert alert-danger alert-dismissable"
							id="packAvailable">
							<button type="button" class="close" data-dismiss="alert"
								aria-hidden="true">&times;</button>
							<font size="3px;"><strong>Error: </strong>Kit is Already in use Please contact Customer Care.</font>
		</div>
		<div class="alert alert-success alert-dismissable"
							id="packSuccess">
							<button type="button" class="close" data-dismiss="alert"
								aria-hidden="true">&times;</button>
							<font size="3px;"><strong>Success: </strong>Succeessfully upgraded Package.  Thank you  </font>
		</div>
		
		<script>
		$("#packSuccess").hide();
		$("#packFail").hide();
		$("#packAvailable").hide();
		</script>

							<div class="row">
								<div class="col-md-3">

									<!-- Profile Image -->
									<div class="box" style="font: bold 13px courier" !important;">
										<div class="box-body box-profile">
											<img src="resources/dist/img/user4-128x128.jpg" class="profile-user-img img-responsive img-circle" alt="User profile picture" id="userProfilePicture" onerror="this.onerror=null; this.src='resources/UserImages/userImage.png';">

											<h3 class="profile-username text-center">
												<span id="userName"></span>
											</h3>

											<p class="text-muted text-center">
												<span id="userDesignation"></span>
											</p>

											<ul class="list-group list-group-unbordered">
												<li class="list-group-item"><b>State</b> <span
													class="pull-right" id="userState"></span></li>
												<li class="list-group-item"><b>City</b> <span
													class="pull-right" id="userCity"></span></li>
													
													<li class="list-group-item"><b>Uname</b> <span
													class="pull-right" id="userUname"></span></li>
													
													
													<li class="list-group-item"><b>Password</b> <span
													class="pull-right" id="userPassword"></span></li>
											</ul>


										</div>
										<!-- /.box-body -->
									</div>
									<!-- /.box -->


								</div>
								<!-- /.col -->
								<div class="col-md-9">
									<div class="nav-tabs-custom">
										<ul class="nav nav-tabs">
											<li class="active"><a href="#activity" data-toggle="tab">Personal
													Info</a></li>
											<li><a href="#timeline" data-toggle="tab">Partner
													Preference</a></li>
											<li><a href="#settings" data-toggle="tab">Send Mail</a></li>
											<li><a href="#message" data-toggle="tab">Send Message</a></li>
											<li><a href="#kitActive" data-toggle="tab">Activate Kit</a></li>
											<li><a href="#kitinfo" data-toggle="tab">Kit Info</a></li>
											<li><a href="#imgsetting" data-toggle="tab">Profile Image</a></li>
											
										</ul>
										<div class="tab-content" style="font: bold 13px courier" !important;">
											<div class="active tab-pane" id="activity">
											
											
											<div class="alert alert-success alert-dismissable"
													id="successSelect">
													<button type="button" class="close" data-dismiss="alert"
												aria-hidden="true">&times;</button>
													<strong>Message: </strong>Personal Information Updated Successfully.
											</div> 
											
												<!-- Post -->
												<div class="post">


													<ul class="list-group list-group-unbordered">
													<li class="list-group-item"><b>Created For</b>
													
														<select id="ProfileCreatedFor" name="ProfileCreatedFor" class="pull-right">
															<option value="Self">Self</option>
															<option value="Parents">Parents</option>
															<option value="Sibling">Sibling</option>
															<option value="Relative">Relative</option>
															<option value="Sister">Sister</option>
															<option value="Friend">Friend</option>
											</select>
													</li>
													<li class="list-group-item"><b>Name</b>
													
														<input type="text" id="firstName" name="firstName" class="pull-right"
															class="form-control"
															placeholder="First Name     Last Name"
															onkeypress="return ((event.charCode "> 64 && event.charCode < 91)||(event.charCode >  96 && event.charCode < 123)) || event.charCode === 32  || charCode === 8 || event.keyCode === 9 ' />
																
													</li>
													<li class="list-group-item"><b>Email</b>
														<input type="text" class="pull-right"  id="email"/> 
													</li>
														<li class="list-group-item"><b>Gender</b>
														
														<div class="pull-right"><input name="gender" id="gender" value="Male" type="radio">
														<label style="color: gray; font-size: 15px;">Male</label>&nbsp;&nbsp;&nbsp;
														<input name="gender" id="gender" value="Female" type="radio"
														checked> <label
														style="color: gray; font-size: 15px;">Female</label></div>
														 </li>
														 <li class="list-group-item"><b>About Me</b>
														 
														 <textarea id="aboutMe" class="form-control" rows="2" style=""class="pull-right"
															onkeyup="countChar(this)" placeholder="Minimum 100 characters" ></textarea>
														 
														 </li>
														 
														 
														<li class="list-group-item"><b>Mobile Number</b> 
														<input type="text" class="pull-right"  id="mobile"/>
														</li>
														<li class="list-group-item"><b>DOB</b> 
														<input type="text" class="pull-right"  id="dob"/>
														</li>
														<li class="list-group-item"><b>Marital Status</b>
														<select id="materialStatus" name="materialStatus" class="pull-right"
																style="color: gray; font-size: 14px;"
																class="form-control   label label-default" ">
																<option value="0">Select Status</option>
																<option value='Single'>Single</option>
																<option value='Married'>Married</option>
																<option value='Divorced'>Divorced</option>
																<option value='Widowed'>Widowed</option>
														</select>
														
															
														</li>
														<li class="list-group-item"><b>Physical Status</b> 
															<!-- <input type="text" class="pull-right"  id="userPhysical"/> -->
															
															<select style="color: gray; font-size: 14px;"
																id="physicalStatus" name="physicalStatus"
																class="pull-right" ">
						
																<option value="0">Physical Status</option>
																<option value="Normal">Normal</option>
																<option value="Physically challenged">Physically
																	challenged</option>
																<option value="Mentally Challenged">Mentally
																	Challenged</option>
																<option value="Deaf">Deaf</option>
																<option value="Dumb">Dumb</option>
																<option value="Other Disability">Other Disability</option>
																<option value="Blind">Blind</option>
															</select>
															
															
															
														</li>
														<li class="list-group-item"><b>Body Type</b> 
														<div  class="pull-right" ">
														<input name="bodyType" value="Slim" type="radio"> <label
																		style="color: gray; font-size: 15px;">Slim</label>&nbsp;&nbsp;&nbsp;
																	<input name="bodyType" value="Athletic" type="radio" checked>
																	<label style="color: gray; font-size: 15px;">Athletic</label>&nbsp;&nbsp;&nbsp;
																	<input name="bodyType" value="Average" type="radio"> <label
																		style="color: gray; font-size: 15px;">Average</label>&nbsp;&nbsp;&nbsp;
																	<input value="Heavy" type="radio" name="bodyType"> <label
																		style="color: gray; font-size: 15px;">Heavy</label>&nbsp;&nbsp;&nbsp;
														</div>
														</li>
														<li  class="list-group-item"><b>complexion</b> 
														<div  class="pull-right">
																<input name="complexion" value="Very Fair" type="radio">
																<label style="color: gray; font-size: 15px;">Very Fair</label>&nbsp;&nbsp;
																<input name="complexion" value="Fair" type="radio"> <label
																	style="color: gray; font-size: 15px;">Fair</label>&nbsp;&nbsp; <input
																	name="complexion" value="Wheatish" checked type="radio">
																<label style="color: gray; font-size: 15px;">Wheatish</label>&nbsp;&nbsp;
																<input name="complexion" value="Wheatish brown" type="radio">
																<label style="color: gray; font-size: 15px;">Wheatish
																	brown</label>&nbsp;&nbsp; <input value="Dark" type="radio"> <label
																	style="color: gray; font-size: 15px;">Dark</label>&nbsp;
																	</div>
														</li>
														<li class="list-group-item"><b>Height</b>
														<!-- <input type="text" class="pull-right"  id="userHeight"/> -->
														
														<select id="heightSelect" name="heightSelect" class="pull-right"
																	style="color: gray; font-size: 14px;"
																	class="form-control   label label-default" ">
																	<option value='0'>Select Height</option>
																	<option value='121.92'>4 feet</option>
																	<option value='124.46'>4 feet 1 inches</option>
																	<option value='127.00'>4 feet 2 inches</option>
																	<option value='129.54'>4 feet 3 inches</option>
																	<option value='132.08'>4 feet 4 inches</option>
																	<option value='134.62'>4 feet 5 inches</option>
																	<option value='137.16'>4 feet 6 inches</option>
																	<option value='139.70'>4 feet 7 inches</option>
																	<option value='142.24'>4 feet 8 inches</option>
																	<option value='144.78'>4 feet 9 inches</option>
																	<option value='147.32'>4 feet 10 inches</option>
																	<option value='149.86'>4 feet 11 inches</option>
																	<option value='152.40'>5 feet</option>
																	<option value='154.94'>5 feet 1 inches</option>
																	<option value='157.48'>5 feet 2 inches</option>
																	<option value='160.02'>5 feet 3 inches</option>
																	<option value='162.56'>5 feet 4 inches</option>
																	<option value='165.10'>5 feet 5 inches</option>
																	<option value='167.64'>5 feet 6 inches</option>
																	<option value='170.18'>5 feet 7 inches</option>
																	<option value='172.72'>5 feet 8 inches</option>
																	<option value='175.26'>5 feet 9 inches</option>
																	<option value='177.80'>5 feet 10 inches</option>
																	<option value='180.34'>5 feet 11 inches</option>
																	<option value='182.88'>6 feet</option>
																	<option value='185.42'>6 feet 1 inches</option>
																	<option value='187.96'>6 feet 2 inches</option>
																	<option value='190.50'>6 feet 3 inches</option>
																	<option value='193.04'>6 feet 4 inches</option>
																	<option value='195.58'>6 feet 5 inches</option>
																	<option value='198.12'>6 feet 6 inches</option>
																	<option value='200.66'>6 feet 7 inches</option>
																	<option value='203.20'>6 feet 8 inches</option>
																	<option value='205.74'>6 feet 9 inches</option>
																	<option value='208.28'>6 feet 10 inches</option>
																	<option value='210.82'>6 feet 11 inches</option>
																	<option value='213.36'>7 feet</option>
																	<option value='215.90'>7 feet 1 inches</option>
																	<option value='218.44'>7 feet 2 inches</option>
																	<option value='220.98'>7 feet 3 inches</option>
																	<option value='223.52'>7 feet 4 inches</option>
																	<option value='226.06'>7 feet 5 inches</option>
																	<option value='228.60'>7 feet 6 inches</option>
																	<option value='231.14'>7 feet 7 inches</option>
																	<option value='233.68'>7 feet 8 inches</option>
																	<option value='236.22'>7 feet 9 inches</option>
																	<option value='238.76'>7 feet 10 inches</option>
																	<option value='241.30'>7 feet 11 inches</option>
																</select>
														 </li>
														<li class="list-group-item"><b>Weight</b>
															<!-- <input type="text" class="pull-right"  id="userWeight"/> -->
															
															
															<select style="color: gray; font-size: 14px;" id="weightSelect"name="weightSelect"
										                class="pull-right" ">
									
																<option value='0'>Select Weight</option>
																<option value='41'>41 kg</option>
																<option value='42'>42 kg</option>
																<option value='43'>43 kg</option>
																<option value='44'>44 kg</option>
																<option value='45'>45 kg</option>
																<option value='46'>46 kg</option>
																<option value='47'>47 kg</option>
																<option value='48'>48 kg</option>
																<option value='49'>49 kg</option>
																<option value='50'>50 kg</option>
																<option value='51'>51 kg</option>
																<option value='52'>52 kg</option>
																<option value='53'>53 kg</option>
																<option value='54'>54 kg</option>
																<option value='55'>55 kg</option>
																<option value='56'>56 kg</option>
																<option value='57'>57 kg</option>
																<option value='58'>58 kg</option>
																<option value='59'>59 kg</option>
																<option value='60'>60 kg</option>
																<option value='61'>61 kg</option>
																<option value='62'>62 kg</option>
																<option value='63'>63 kg</option>
																<option value='64'>64 kg</option>
																<option value='65'>65 kg</option>
																<option value='66'>66 kg</option>
																<option value='67'>67 kg</option>
																<option value='68'>68 kg</option>
																<option value='69'>69 kg</option>
																<option value='70'>70 kg</option>
																<option value='71'>71 kg</option>
																<option value='72'>72 kg</option>
																<option value='73'>73 kg</option>
																<option value='74'>74 kg</option>
																<option value='75'>75 kg</option>
																<option value='76'>76 kg</option>
																<option value='77'>77 kg</option>
																<option value='78'>78 kg</option>
																<option value='79'>79 kg</option>
																<option value='80'>80 kg</option>
																<option value='81'>81 kg</option>
																<option value='82'>82 kg</option>
																<option value='83'>83 kg</option>
																<option value='84'>84 kg</option>
																<option value='85'>85 kg</option>
																<option value='86'>86 kg</option>
																<option value='87'>87 kg</option>
																<option value='88'>88 kg</option>
																<option value='89'>89 kg</option>
																<option value='90'>90 kg</option>
																<option value='91'>91 kg</option>
																<option value='92'>92 kg</option>
																<option value='93'>93 kg</option>
																<option value='94'>94 kg</option>
																<option value='95'>95 kg</option>
																<option value='96'>96 kg</option>
																<option value='97'>97 kg</option>
																<option value='98'>98 kg</option>
																<option value='99'>99 kg</option>
																<option value='100'>100 kg</option>
																<option value='101'>101 kg</option>
																<option value='102'>102 kg</option>
																<option value='103'>103 kg</option>
																<option value='104'>104 kg</option>
																<option value='105'>105 kg</option>
																<option value='106'>106 kg</option>
																<option value='107'>107 kg</option>
																<option value='108'>108 kg</option>
																<option value='109'>109 kg</option>
																<option value='110'>110 kg</option>
																<option value='111'>111 kg</option>
																<option value='112'>112 kg</option>
																<option value='113'>113 kg</option>
																<option value='114'>114 kg</option>
																<option value='115'>115 kg</option>
																<option value='116'>116 kg</option>
																<option value='117'>117 kg</option>
																<option value='118'>118 kg</option>
																<option value='119'>119 kg</option>
																<option value='120'>120 kg</option>
																<option value='121'>121 kg</option>
																<option value='122'>122 kg</option>
																<option value='123'>123 kg</option>
																<option value='124'>124 kg</option>
																<option value='125'>125 kg</option>
																<option value='126'>126 kg</option>
																<option value='127'>127 kg</option>
																<option value='128'>128 kg</option>
																<option value='129'>129 kg</option>
																<option value='130'>139 kg</option>
																<option value='132'>132 kg</option>
																<option value='131'>131 kg</option>
																<option value='133'>133 kg</option>
																<option value='134'>134 kg</option>
																<option value='135'>135 kg</option>
																<option value='136'>136 kg</option>
																<option value='137'>137 kg</option>
																<option value='138'>138 kg</option>
																<option value='139'>139 kg</option>
																<option value='140'>140 kg</option>
															</select>
														</li>
														
														<li class="list-group-item"> <b>Country</b>
															
														<select style="color: gray; font-size: 14px;" class="pull-right"
																id="countrySelect" name="countrySelect"
																class="form-control   label label-default" ">
																<!-- <option value="0">Select Country</option> -->
																<c:forEach var="country" items="${countries}">
																	<option value="${country.country_id}">
																		${country.country_Name}</option>
																</c:forEach>
															</select>
														</li>
														
														<li class="list-group-item"> <b>State</b>
															
														<select style="color: gray; font-size: 14px;" id="stateSelect" class="pull-right"
																name="stateSelect" class="form-control   label label-default" "
																onchange="loadCityAccordingToState(this.value);">
																<option value="0">Select State</option>
																<c:forEach var="stateCountry" items="${state_Countries}">
																	<option value="${stateCountry.state_Country_id}">
																		${stateCountry.state_Name}</option>
																</c:forEach>
															</select>
															</li>
															<li class="list-group-item" >
															<b>City/District </b>
																<select style="color: gray; font-size: 14px;"class="pull-right"
																	id="cityStateSelect" name="cityStateSelect"
																	class="form-control   label label-default" "
																	onchange="loadsubDistrictAccordingToDistrict(this.value);">
																	<option value="0">Select City/District</option>
																</select>
																
																
															
															</li>
															<li class="list-group-item" ><b>Sub-District</b>
																	<select style="color: gray; font-size: 14px;"class="pull-right"
																	id="subDistSelect" name="subDistSelect"
																	class="form-control   label label-default" ">
																	<option value="0">Sub-District</option>
							
																</select>
															
															</li>
															
															<li class="list-group-item" ><b>Religion</b>
															
																<select style="color: gray; font-size: 14px;" class="pull-right"
																		id="religionSelect" name="religionSelect"
																		class="form-control   label label-default" "
																		onchange="loadCasteAccordingToReligion(this.value);">
																		<option value="0">Select Religion</option>
																		<c:forEach var="religion" items="${religions}">
																			<option value="${religion.religion_id}">
																				${religion.religion_name}</option>
																		</c:forEach>
																</select>
															</li>
															<li class="list-group-item"> <b>Caste</b>
																<select style="color: gray; font-size: 14px;" id="casteSelect"class="pull-right"
																		name="casteSelect" class="form-control   label label-default" ">
																		<option value="0">Caste</option>
								
																	</select>
															
															</li>
															<li>
															
																
															
															
															</li>
															<li class="list-group-item">
															<b>Education Category</b>
															
															<select style="color: gray; font-size: 14px;" class="pull-right"
																	id="educationalCategorySelect"
																	name="educationalCategorySelect"
																	class="form-control   label label-default" "
																	onchange="loadEducationAccordingToEduCategory(this.value);">
																	<option value="0">Educational Category</option>
																	<c:forEach var="education" items="${educationCategories}">
																	<option value="${education.edu_cat_id}">
																	${education.edu_cat_name}</option>
																	</c:forEach>
																</select>
															
															</li>
															
															<li class="list-group-item">
																	<b>Education</b>
																<select style="color: gray; font-size: 14px;" class="pull-right"
																		id="educationalSelect" name="educationalSelect"
																		class="form-control   label label-default" " disabled>
																		<option value="0">Select Education</option>
								
																	</select>
															</li>
						
						
														<li class="list-group-item"><b>Occupation Category </b>
														<select style="color: gray; font-size: 14px;" class="pull-right"
																id="occupationCategorySelect" name="occupationCategorySelect"
																class="form-control   label label-default" "
																onchange="loadOccupationAccordingToOccuCategory(this.value);">
																<option value="0">Occupation Category</option>
																<c:forEach var="occupation" items="${occupationCategories}">
																	<option value="${occupation.occu_id}">
																		${occupation.occu_name}</option>
																</c:forEach>
															</select>
														</li>
														
														<li class="list-group-item">
														<b>Occupation</b>
															<select style="color: gray; font-size: 14px;" class="pull-right"
																	id="occupationSelect" name="occupationSelect"
																	class="form-control   label label-default" ">
																	<option value="0">Select Occupation</option>
							
																</select>
														
														</li>
														
								<li class="list-group-item"><b>Emp In</b>
														 <div class="pull-right">
														 	<input id="employedIn" name="employedIn"
																value="Government" type="radio"> <label
																style="color: gray; font-size: 15px;">Government</label>&nbsp;&nbsp;&nbsp;
															<input id="employedIn" name="employedIn"
																value="Defence" type="radio" checked> <label
																style="color: gray; font-size: 15px;">Defence</label>&nbsp;&nbsp;&nbsp;
															<input name="employedIn" id="employedIn"
																value="Private" type="radio"> <label
																style="color: gray; font-size: 15px;">Private </label>&nbsp;&nbsp;&nbsp;
															<input id="employedIn" value="Business" type="radio"
																name="employedIn"> <label
																style="color: gray; font-size: 15px;">Business</label>&nbsp;&nbsp;&nbsp;
					
															<input id="employedIn" name="employedIn"
																value="Self Employed" type="radio" name="employedIn">
															<label style="color: gray; font-size: 15px;">Self
																Employed</label>&nbsp;&nbsp;&nbsp; <input id="employedIn"
																value="Not Working" type="radio" name="employedIn">
															<label style="color: gray; font-size: 15px;">Not
																Working</label>&nbsp;&nbsp;&nbsp;
														</div>		
														 
														 </li>
														 <li class="list-group-item"><b>Annual Income</b>
														 <div class="pull-right">
															<div class="col-md-6">
																	<input name="income" value="Monthly" type="radio"
																		checked> <label
																		style="color: gray; font-size: 15px;">Monthly</label>&nbsp;&nbsp;&nbsp;
																	<input name="income" value="Annual" type="radio">
																	<label style="color: gray; font-size: 15px;">Annual</label>&nbsp;&nbsp;&nbsp;
																</div>
																<div class="col-md-2 form-group">
																	<input type="number" name="incomeAmount"
																		style="color: gray; width: 200px; height: 20px; font-size: 15px;"
																		id="incomeAmount" >
																</div>	
															</div>														 
															 </li>
											<li class="list-group-item">
											<b>Family Preference</b>
											<div class="pull-right">
														<input name="familyClassInfo" id="familyClassInfo" 
													value="Middle class" type="radio" /> <label
													style="color: gray; font-size: 15px;">Middle class</label>&nbsp;&nbsp; <input
													name="familyClassInfo" id="familyClassInfo"
													value="Upper middle class" type="radio" /> <label
													style="color: gray; font-size: 15px;">Upper middle
													class</label> &nbsp;&nbsp;<input name="familyClassInfo" id="familyClassInfo"
													value="Rich" type="radio" /><label
													style="color: gray; font-size: 15px;">Rich</label>&nbsp;&nbsp; <input
													name="familyClassInfo" id="familyClassInfo" value="Affluent"
													type="radio" /> <label style="color: gray; font-size: 15px;">Affluent</label>&nbsp;&nbsp;
													</div>
											
											</li>			
															
														<li class="list-group-item"><b>Family Type </b>
														<!-- <input type="text" class="pull-right"  id="userFamilyType"/> -->
														<div class="pull-right">
														<input name="familyTypeInfo" value="Joint" type="radio" /><label
													style="color: gray; font-size: 15px;">Joint</label>&nbsp;&nbsp; <input
													name="familyTypeInfo" value="Nuclear" type="radio" /><label
													style="color: gray; font-size: 15px;">Nuclear</label>&nbsp;&nbsp;
													</div>
													</li>
													<li class="list-group-item"><b>Family Values </b>
													<div class="pull-right">
														<input name="familyValueInfo" value="Orthodox" type="radio" /><label style="color: gray; font-size: 15px;">Orthodox</label>&nbsp;&nbsp;
													<input name="familyValueInfo" value="Traditional" type="radio" /><label
														style="color: gray; font-size: 15px;">Traditional</label> &nbsp;&nbsp;<input
														name="familyValueInfo" value="Moderate" type="radio" /><label
														style="color: gray; font-size: 15px;">Moderate</label>&nbsp;&nbsp; <input
														name="familyValueInfo" value="Liberal" type="radio" /><label
														style="color: gray; font-size: 15px;">Liberal</label>&nbsp;&nbsp;
													</div>
													</li>
													
													<li class="list-group-item"><b>Eating Habits  </b>
													<div class="pull-right">
															<input name="eating" value="vegetarian" type="radio" 
															checked> <label style="color: gray; font-size: 15px;">vegetarian</label>&nbsp;&nbsp;&nbsp;
															<input name="eating" value="Non vegetarian" type="radio">
															<label style="color: gray; font-size: 15px;">Non
																vegetarian</label>&nbsp;&nbsp;&nbsp; <input
															name="eating" value="Eggs Only" type="radio"> <label
															style="color: gray; font-size: 15px;">Eggs Only</label>&nbsp;&nbsp;&nbsp;
															</div>
															</li>
															
															<li class="list-group-item"><b>Drinking Habits</b>
															
															<div class="pull-right">
																						<input name="drink" value="Never" type="radio" checked>
																<label style="color: gray; font-size: 15px;">Never</label>&nbsp;&nbsp;&nbsp;
																<input name="drink" value="Regular drinker" type="radio">
																<label style="color: gray; font-size: 15px;">Regular
																	drinker</label>&nbsp;&nbsp;&nbsp; <input name="drink"
																	value="Social drinker" type="radio"> <label
																	style="color: gray; font-size: 15px;">Social drinker</label>&nbsp;&nbsp;&nbsp;
															</div>
															</li>
													
														
														<li class="list-group-item"><b>Smoking Habit</b>
														<!-- <input type="text" class="pull-right"  id="userReligion"/> -->
														<div class="pull-right">
													<input name="smoke" value="Non-smoker" type="radio" checked>
													<label style="color: gray; font-size: 15px;">
														Non-smoker </label>&nbsp;&nbsp;&nbsp; <input name="smoke"
														value="Social smoker" type="radio"> <label
														style="color: gray; font-size: 15px;"> Social smoker </label>&nbsp;&nbsp;&nbsp;
													<input name="smoke" value="Chain smoker" type="radio">
													<label style="color: gray; font-size: 15px;">Chain
														smoker</label>&nbsp;&nbsp;&nbsp;
														</div>
														</li>
														
														<li>
															<a href="#">
																<button type="button" style="margin-left: 45%; margin-top: 20px;"
																	onclick="updateUser();" class="btn btn-primary" ">
																	<span>Update</span>
																</button>
															</a>
															
														</li>
													</ul>

												</div>
											</div>
											
											<script type="text/javascript">
													$("#successSelect").hide();
											</script>
											<!-- /.tab-pane -->
											<div class="tab-pane" id="timeline">

												<div class="post">
															
													<div class="alert alert-success alert-dismissable"
														id="partnersuccessSelect">
															<button type="button" class="close" data-dismiss="alert"
															aria-hidden="true">&times;</button>
															<strong>Message: </strong>Partner Preference Updated Successfully.
													</div> 
				
												
													<ul class="list-group list-group-unbordered">
														<li class="list-group-item"><b>Marital Status</b>
														<!-- 	<input type="text" class="pull-right"  id="partnerMstatus"/> -->
														
															<select id="materialStatusPartner" class="pull-right"
																	name="materialStatusPartner"
																	style="color: gray; font-size: 14px;"
																	class="form-control   label label-default" ">
																	<option value="0">Select Status</option>
																	<option value='Single'>Single</option>
																	<option value='Married'>Married</option>
																	<option value='Divorced'>Divorced</option>
																	<option value='Widowed'>Widowed</option>
																</select>
														 </li>
														<li class="list-group-item"><b>Physical Status</b>
														<!-- <input type="text" class="pull-right"  id="partnerPhysical"/> -->
														<select style="color: gray; font-size: 14px;"
																id="physicalStatusPartner" name="physicalStatusPartner" class="pull-right"
																class="form-control   label label-default" ">
					
																<option value="0">Physical Status</option>
																<option value="Normal">Normal</option>
																<option value="Physically challenged">Physically
																	challenged</option>
																<option value="Mentally Challenged">Mentally
																	Challenged</option>
																<option value="Deaf">Deaf</option>
																<option value="Dumb">Dumb</option>
																<option value="Other Disability">Other Disability</option>
																<option value="Blind">Blind</option>
															</select>
														
														</li>
														<li class="list-group-item"><b>Body Type</b>
														<!-- <input type="text" class="pull-right"  id="partnerBody"/> -->
														<div class="pull-right">
														<input name="bodyTypePartner" value="Slim" type="radio">
																	<label style="color: gray; font-size: 15px;">Slim</label>&nbsp;&nbsp;&nbsp;
																	<input name="bodyTypePartner" value="Athletic" type="radio"
																		checked> <label style="color: gray; font-size: 15px;">Athletic</label>&nbsp;&nbsp;&nbsp;
																	<input name="bodyTypePartner" value="Average" type="radio">
																	<label style="color: gray; font-size: 15px;">Average</label>&nbsp;&nbsp;&nbsp;
																	<input value="Heavy" type="radio" name="bodyTypePartner">
																	<label style="color: gray; font-size: 15px;">Heavy</label>&nbsp;&nbsp;&nbsp;
														</div>
														</li>
														
														<li  class="list-group-item"><b>complexion</b>
														
														<div class="pull-right">
															<input name="complexionPartner" value="Very Fair" type="radio">
															<label style="color: gray; font-size: 15px;">Very Fair</label>&nbsp;&nbsp;
															<input name="complexionPartner" value="Fair" type="radio">
															<label style="color: gray; font-size: 15px;">Fair</label>&nbsp;&nbsp;
															<input name="complexionPartner" value="Wheatish" checked
																type="radio"> <label
																style="color: gray; font-size: 15px;">Wheatish</label>&nbsp;&nbsp;
															<input value="Wheatish brown" name="complexionPartner"
																type="radio"> <label
																style="color: gray; font-size: 15px;">Wheatish brown</label>&nbsp;&nbsp;
															<input name="complexionPartner" value="Dark" type="radio">
															<label style="color: gray; font-size: 15px;">Dark</label>&nbsp;&nbsp;
														</div>
														
														</li>
														
														
														
														
														<li class="list-group-item"><b>Height</b>
														<!-- <input type="text" class="pull-right"  id="partnerHeight"/> -->
										<div class="pull-right">

										<div class="col-md-6">
											<select id="heightSelectPartnerFirst"
												name="heightSelectPartnerFirst"
												style="color: gray; font-size: 14px;"
											   label label-default ">
												<option value="0">Select Height</option>
												<option value="121.92">4 feet</option>
												<option value="124.46">4 feet 1 inches</option>
												<option value="127.00">4 feet 2 inches</option>
												<option value="129.54">4 feet 3 inches</option>
												<option value="132.08">4 feet 4 inches</option>
												<option value="134.62">4 feet 5 inches</option>
												<option value="137.16">4 feet 6 inches</option>
												<option value="139.70">4 feet 7 inches</option>
												<option value="142.24">4 feet 8 inches</option>
												<option value="144.78">4 feet 9 inches</option>
												<option value="147.32">4 feet 10 inches</option>
												<option value="149.86">4 feet 11 inches</option>
												<option value="152.40">5 feet</option>
												<option value="154.94">5 feet 1 inches</option>
												<option value="157.48">5 feet 2 inches</option>
												<option value="160.02">5 feet 3 inches</option>
												<option value="162.56">5 feet 4 inches</option>
												<option value="165.10">5 feet 5 inches</option>
												<option value="167.64">5 feet 6 inches</option>
												<option value="170.18">5 feet 7 inches</option>
												<option value="172.72">5 feet 8 inches</option>
												<option value="175.26">5 feet 9 inches</option>
												<option value="177.80">5 feet 10 inches</option>
												<option value="180.34">5 feet 11 inches</option>
												<option value="182.88">6 feet</option>
												<option value="185.42">6 feet 1 inches</option>
												<option value="187.96">6 feet 2 inches</option>
												<option value="190.50">6 feet 3 inches</option>
												<option value="193.04">6 feet 4 inches</option>
												<option value="195.58">6 feet 5 inches</option>
												<option value="198.12">6 feet 6 inches</option>
												<option value="200.66">6 feet 7 inches</option>
												<option value="203.20">6 feet 8 inches</option>
												<option value="205.74">6 feet 9 inches</option>
												<option value="208.28">6 feet 10 inches</option>
												<option value="210.82">6 feet 11 inches</option>
												<option value="213.36">7 feet</option>
												<option value="215.90">7 feet 1 inches</option>
												<option value="218.44">7 feet 2 inches</option>
												<option value="220.98">7 feet 3 inches</option>
												<option value="223.52">7 feet 4 inches</option>
												<option value="226.06">7 feet 5 inches</option>
												<option value="228.60">7 feet 6 inches</option>
												<option value="231.14">7 feet 7 inches</option>
												<option value="233.68">7 feet 8 inches</option>
												<option value="236.22">7 feet 9 inches</option>
												<option value="238.76">7 feet 10 inches</option>
												<option value="241.30">7 feet 11 inches</option>
											</select>
										</div>
										<div class="col-md-6">
											<select id="heightSelectPartnerSecond"
												name="heightSelectPartnerSecond"
												style="color: gray; font-size: 14px;"
												  label label-default ">
												<option value="0">Select Height</option>
												<option value="121.92">4 feet</option>
												<option value="124.46">4 feet 1 inches</option>
												<option value="127.00">4 feet 2 inches</option>
												<option value="129.54">4 feet 3 inches</option>
												<option value="132.08">4 feet 4 inches</option>
												<option value="134.62">4 feet 5 inches</option>
												<option value="137.16">4 feet 6 inches</option>
												<option value="139.70">4 feet 7 inches</option>
												<option value="142.24">4 feet 8 inches</option>
												<option value="144.78">4 feet 9 inches</option>
												<option value="147.32">4 feet 10 inches</option>
												<option value="149.86">4 feet 11 inches</option>
												<option value="152.40">5 feet</option>
												<option value="154.94">5 feet 1 inches</option>
												<option value="157.48">5 feet 2 inches</option>
												<option value="160.02">5 feet 3 inches</option>
												<option value="162.56">5 feet 4 inches</option>
												<option value="165.10">5 feet 5 inches</option>
												<option value="167.64">5 feet 6 inches</option>
												<option value="170.18">5 feet 7 inches</option>
												<option value="172.72">5 feet 8 inches</option>
												<option value="175.26">5 feet 9 inches</option>
												<option value="177.80">5 feet 10 inches</option>
												<option value="180.34">5 feet 11 inches</option>
												<option value="182.88">6 feet</option>
												<option value="185.42">6 feet 1 inches</option>
												<option value="187.96">6 feet 2 inches</option>
												<option value="190.50">6 feet 3 inches</option>
												<option value="193.04">6 feet 4 inches</option>
												<option value="195.58">6 feet 5 inches</option>
												<option value="198.12">6 feet 6 inches</option>
												<option value="200.66">6 feet 7 inches</option>
												<option value="203.20">6 feet 8 inches</option>
												<option value="205.74">6 feet 9 inches</option>
												<option value="208.28">6 feet 10 inches</option>
												<option value="210.82">6 feet 11 inches</option>
												<option value="213.36">7 feet</option>
												<option value="215.90">7 feet 1 inches</option>
												<option value="218.44">7 feet 2 inches</option>
												<option value="220.98">7 feet 3 inches</option>
												<option value="223.52">7 feet 4 inches</option>
												<option value="226.06">7 feet 5 inches</option>
												<option value="228.60">7 feet 6 inches</option>
												<option value="231.14">7 feet 7 inches</option>
												<option value="233.68">7 feet 8 inches</option>
												<option value="236.22">7 feet 9 inches</option>
												<option value="238.76">7 feet 10 inches</option>
												<option value="241.30">7 feet 11 inches</option>
											</select>
										</div>

									</div>
														
														 </li>
														 <li class="list-group-item"><b>Country</b>
														 
														 <select style="color: gray; font-size: 14px;" class="pull-right" 
															id="countrySelectPartner" name="countrySelectPartner"
															class="form-control   label label-default" ">
															<!-- <option value="0">Select Country</option> -->
															<c:forEach var="country" items="${countries}">
																<option value="${country.country_id}">
																	${country.country_Name}</option>
															</c:forEach>
														</select>
														 
														 </li>
														 <li class="list-group-item"><b>State</b>
														 
														 	<select style="color: gray; font-size: 14px;" 
																id="stateSelectPartner" name="stateSelectPartner"class="pull-right"
																class="form-control   label label-default" "
																onchange="loadCityAccordingToStatePartnerPreferance(this.value);">
																<option value="0">Select State</option>
																<c:forEach var="stateCountry" items="${state_Countries}">
																	<option value="${stateCountry.state_Country_id}">
																		${stateCountry.state_Name}</option>
																</c:forEach>
															</select>
														 
														 </li>
														 <li class="list-group-item"><b>City/District</b>
														 <select style="color: gray; font-size: 14px;"  class="pull-right"
															id="cityStateSelectPartnerPreferance"
															name="cityStateSelectPartnerPreferance"
															class="form-control   label label-default" ">
															<option value="0">Select City/District</option>
														</select>
														
														<li class="list-group-item"><b>Religion</b>
															<select style="color: gray; font-size: 14px;" class="pull-right"
																id="religionSelectPartner" name="religionSelectPartner"
																class="form-control   label label-default" "
																onchange="loadCasteAccordingToReligionPartner(this.value);">
																<option value="0">Select Religion</option>
																<c:forEach var="religion" items="${religions}">
																	<option value="${religion.religion_id}">
																		${religion.religion_name}</option>
																</c:forEach>
														</select>
														
														</li>
														  
														 <li class="list-group-item"><b>Caste</b>
														 
														 <select style="color: gray; font-size: 14px;"class="pull-right"
																id="casteSelectPartner" name="casteSelectPartner"
																class="form-control   label label-default" ">
																<option value="0">Caste</option>
					
														 </select>
														 
														 </li>
														  <li class="list-group-item"><b>Edu Category</b>
														 
																
																<p class="flip pull-right" " onclick="showEducation();">Education Types</p>
						
																<div class="panel123 col-md" style="color: gray; height: auto;">
																	<c:forEach var="education" items="${educationCategories}">
																	
																	<script>
																	educationId.push("${education.edu_cat_id}");
																	
																	</script>
																	
																		<div >
																			<input  type="checkbox"
																				id="${education.edu_cat_id}"
																				value="${education.edu_cat_id}"
																				name="checkBoxEducationPartner">${education.edu_cat_name}
																	</div>
																	</c:forEach>
																</div>
																
														</li>
														
													<li class="list-group-item"><b>Edu Category</b>
												
			
													<p class="flip1 pull-right" onclick="showOccupation();">Occupation
														Types</p>
													<div class="panel1 col-md" style="color: gray; height: auto;">
														<c:forEach var="occupation" items="${occupationCategories}">
														
														<script>
														occupationId.push("${occupation.occu_id}");
														
														</script>
															<div>
																<input class="" type="checkbox"
																	id="occ_${occupation.occu_id}" value="${occupation.occu_id}"
																	name="checkBoxOccupationPartner">${occupation.occu_name}
															</div>
														</c:forEach>
													</div>
													
													
													</li>
														
														
														
														 <li class="list-group-item"><b>Emp In</b>
														 <div class="pull-right">
														 	<input id="employedInPartner" name="employedInPartner"
																value="Government" type="radio"> <label
																style="color: gray; font-size: 15px;">Government</label>&nbsp;&nbsp;&nbsp;
															<input id="employedInPartner" name="employedInPartner"
																value="Defence" type="radio" checked> <label
																style="color: gray; font-size: 15px;">Defence</label>&nbsp;&nbsp;&nbsp;
															<input name="employedInPartner" id="employedInPartner"
																value="Private" type="radio"> <label
																style="color: gray; font-size: 15px;">Private </label>&nbsp;&nbsp;&nbsp;
															<input id="employedInPartner" value="Business" type="radio"
																name="employedInPartner"> <label
																style="color: gray; font-size: 15px;">Business</label>&nbsp;&nbsp;&nbsp;
					
															<input id="employedInPartner" name="employedInPartner"
																value="Self Employed" type="radio" name="employedInPartner">
															<label style="color: gray; font-size: 15px;">Self
																Employed</label>&nbsp;&nbsp;&nbsp; <input id="employedInPartner"
																value="Not Working" type="radio" name="employedInPartner">
															<label style="color: gray; font-size: 15px;">Not
																Working</label>&nbsp;&nbsp;&nbsp;
														</div>		
														 
														 </li>
														 <li class="list-group-item"><b>Annual Income</b>
														 <div class="pull-right">
															<div class="col-md-6">
																	<input name="incomePartner" value="Monthly" type="radio"
																		checked> <label
																		style="color: gray; font-size: 15px;">Monthly</label>&nbsp;&nbsp;&nbsp;
																	<input name="incomePartner" value="Annual" type="radio">
																	<label style="color: gray; font-size: 15px;">Annual</label>&nbsp;&nbsp;&nbsp;
																</div>
																<div class="col-md-2 form-group">
																	<input type="number" name="incomeAmountPartner"
																		style="color: gray; width: 200px; height: 20px; font-size: 15px;"
																		id="incomeAmountPartner">
																</div>	
															</div>														 
															 </li>
															 <li class="list-group-item"> 
															 <b>Family Preference</b>
															 <div class="pull-right">
																<input name="familyClassInfoPartner"
																id="familyClassInfoPartner" value="Middle class" type="radio"
																checked /><label style="color: gray; font-size: 15px;">Middle
																class</label>&nbsp;&nbsp; <input name="familyClassInfoPartner"
																id="familyClassInfoPartner" value="Upper middle class"
																type="radio" /> <label style="color: gray; font-size: 15px;">Upper
																middle class</label> &nbsp;&nbsp;<input name="familyClassInfoPartner"
																id="familyClassInfoPartner" value="Rich" type="radio" /> <label
																style="color: gray; font-size: 15px;">Rich</label>&nbsp;&nbsp; <input
																name="familyClassInfoPartner" id="familyClassInfoPartner"
																value="Affluent" type="radio" /> <label
																style="color: gray; font-size: 15px;">Affluent</label>
																
																</div>
															 
															 </li>
															<li class="list-group-item"> <b>Family Type</b> 
															<div class="pull-right">
																<input name="familyTypeInfoPartner" value="Joint" type="radio"
																checked /><label style="color: gray; font-size: 15px;">Joint</label>&nbsp;&nbsp;&nbsp;
															<input name="familyTypeInfoPartner" value="Nuclear"
																type="radio" /><label style="color: gray; font-size: 15px;">Nuclear</label>&nbsp;&nbsp;
																</div>		 
															 </li>
															 <li class="list-group-item"> <b>Family values</b> 
															 <div class="pull-right">
															 	<input name="familyValueInfoPartner" value="Orthodox"
																	type="radio" checked /><label
																	style="color: gray; font-size: 15px;">Orthodox</label>&nbsp;&nbsp; <input
																	name="familyValueInfoPartner" value="Traditional"
																	type="radio" /><label style="color: gray; font-size: 15px;">Traditional</label>&nbsp;&nbsp;
						
																<input name="familyValueInfoPartner" value="Moderate"
																	type="radio" /><label style="color: gray; font-size: 15px;">Moderate</label>&nbsp;&nbsp;
																<input name="familyValueInfoPartner" value="Liberal"
																	type="radio" /><label style="color: gray; font-size: 15px;">Liberal</label>&nbsp;&nbsp;
															 </div>
															 </li>
															 <li class="list-group-item"> <b>Eating Habits</b>
															 <div class="pull-right">
															 		<input name="eatingPartner" value="vegetarian" type="radio"
																		checked> <label style="color: gray; font-size: 15px;">vegetarian</label>&nbsp;&nbsp;&nbsp;
																	<input name="eatingPartner" value="Non vegetarian"
																		type="radio"> <label
																		style="color: gray; font-size: 15px;">Non vegetarian</label>&nbsp;&nbsp;&nbsp;
																	<input name="eatingPartner" value="Eggs Only" type="radio">
																	<label style="color: gray; font-size: 15px;">Eggs Only</label>&nbsp;&nbsp;&nbsp;
															</div>					 
															 </li>
															 <li class="list-group-item"> <b>Drinking Habits</b>
															 <div class="pull-right">
															 		<input name="drinkPartner" value="Never" type="radio" checked>
																	<label style="color: gray; font-size: 15px;">Never</label>&nbsp;&nbsp;&nbsp;
																	<input name="drinkPartner" value="Regular drinker"
																		type="radio"> <label
																		style="color: gray; font-size: 15px;">Regular drinker</label>&nbsp;&nbsp;&nbsp;
																	<input name="drinkPartner" value="Social drinker" type="radio">
																	<label style="color: gray; font-size: 15px;">Social
																		drinker</label>&nbsp;&nbsp;&nbsp;
															</div>
															 </li>
															 <li class="list-group-item"> <b>Smoking Habits</b>
															  <div class="pull-right">
															 		<input name="smokePartner" value="Non-smoker" type="radio"
																	checked> <label style="color: gray; font-size: 15px;">
																	Non-smoker </label>&nbsp;&nbsp;&nbsp; <input name="smokePartner"
																	value="Social smoker" type="radio"> <label
																	style="color: gray; font-size: 15px;"> Social smoker
																</label>&nbsp;&nbsp;&nbsp; <input name="smokePartner"
																	value="Chain smoker" type="radio"> <label
																	style="color: gray; font-size: 15px;">Chain smoker</label>&nbsp;&nbsp;&nbsp;
															 </div>
															 </li>
														 <li><br>
														 	<button type="button" style="margin-left: 45%; size: 50px; background-color: #d23779; height: 30px;"
																onclick="return updatePartner();" class="btn-danger pinkbtn">
															<span>Update</span>

								</button>
														 	
														 </li>
														
													</ul>



												</div>

											<script type="text/javascript">
												
											$("#partnersuccessSelect").hide();
											</script>
													



											</div>
											<!-- /.tab-pane -->

											<div class="tab-pane" id="settings">



												<div class="box box-primary">

													<div class="alert alert-success alert-dismissable"
														id="mailSendselect">
														<button type="button" class="close" data-dismiss="alert"
															aria-hidden="true">&times;</button>
														<strong>Message: </strong>Email Send Successfully.
													</div>

													<div class="alert alert-danger alert-dismissable"
														id="subjectSelect">
														<button type="button" class="close" data-dismiss="alert"
															aria-hidden="true">&times;</button>
														<strong>Error: </strong>Please Enter Subject
														
													</div>

													<div class="box-body">
														<div class="form-group">
															<input class="form-control" id="userEmailId" readonly="readonly">
														</div>
														<div class="form-group">
															<input class="form-control" placeholder="Subject:" id="subject">
														</div>
														<div class="form-group">
															<textarea  class="form-control"
																style="height: 300px;" id="msgContents" placeholder="Email Body:">
                      
                    </textarea>
														</div>
														<div class="form-group">
															<!-- <div class="btn btn-default btn-file">
																<i class="fa fa-paperclip"></i> Attachment <input
																	type="file" name="attachment" id="attachment">
														</div> -->


															<button type="button" class="btn btn-primary pull-right" onclick=" return sendEmail();" " style="background-color: #d23779;">
																<i class="fa fa-envelope-o"></i> Send
															</button>

														</div>

													</div>
												</div>
												<!-- /. box -->

												<script type="text/javascript">
													$("#mailSendselect").hide();  
													$("#subjectSelect").hide();
													$("#msgContents").val("").selectRange(0,0);
												
													
												</script>

											</div>






											<div class="tab-pane" id="message">
											<div class="box box-primary">
											
											<div class="box-body">
														<div class="form-group">
															<input class="form-control" id="userMobile" readonly="readonly">
														</div>
														
														<div class="form-group">
														<input type="radio" name="optradio" value="1" checked="checked" />&nbsp;Transactional&nbsp;
														<input type="radio" name="optradio" value="2" />&nbsp;Pramotional&nbsp;
														
															
														</div>
														<div class="form-group">
															<textarea  class="form-control"
																style="height: 250px;" id="smsContents" placeholder="Message Body:">
                      
                    									</textarea>
														</div>
														
														<div class="form-group">
															<button type="button" class="btn btn-primary pull-right" onclick=" return sendSms();" " style="background-color: #d23779;">
																<i class="fa fa-envelope-o"></i> Send
															
															</button>

														</div>

													</div>
											
											
											
											</div>
											
											<script type="text/javascript">
												$("#smsContents").val("").selectRange(0,0);
											</script>
											


											</div>
											
											
											<div class="tab-pane" id="kitinfo">
											<div class="box box-primary">
												<br>
													
  													
														
													<div class="alert alert-danger alert-dismissable"
														id="updateError">
														<button type="button" class="close" data-dismiss="alert"
															aria-hidden="true">&times;</button>
														<strong>Error: </strong>Can't Update Free User
														
													</div>
													
													
													<div class="alert alert-success alert-dismissable"
														id="updateSuccess">
														<button type="button" class="close" data-dismiss="alert"
															aria-hidden="true">&times;</button>
														<strong>Error: </strong>Kit Info Updated Successfully.
														
													</div>
													
													
													<form class="form-horizontal">
													<div class="form-group">
															<label class="control-label col-sm-5" for="messages">Kit Id</label>
															<div class="col-sm-5">
																<input type="text"  readonly="readonly" id="kitIdd"/>
															</div>
														</div>
														<div class="form-group">
															<label class="control-label col-sm-5" for="messages">Kit Password</label>
															<div class="col-sm-5">
																<input type="text"  readonly="readonly" id="kitpasswordd"/>
															</div>
														</div>
													<div class="form-group">
															<label class="control-label col-sm-5" for="messages">No Of Messages:</label>
															<div class="col-sm-5">
																 <input type="number" id="noOfMessage" value="0" onkeypress="return event.charCode ">= 48 && event.charCode <= 57 || event.keyCode === 8 || event.keyCode === 9 '>
															</div>
														</div>
														 
														<div class="form-group">
															<label class="control-label col-sm-5" for="noofdays">No Of Days:</label>
															<div class="col-sm-5">
																 <input type="number" value="0" id="noOfDay" onkeypress="return event.charCode ">= 48 && event.charCode <= 57 || event.keyCode === 8 || event.keyCode === 9 '>
															</div>
														</div>
														
														<div class="form-group">
															<label class="control-label col-sm-5" for="noofprofile">No Of Profiles:</label>
															<div class="col-sm-5">
																 <input type="number" value="0" id="noOfProfile" onkeypress="return event.charCode ">= 48 && event.charCode <= 57 || event.keyCode === 8 || event.keyCode === 9 '/>
															</div>
														</div>   
														<div class="form-group">
															<label class="control-label col-sm-5" for="noofprofile">No Of Emails:</label>
															<div class="col-sm-5">
																 <input type="number" value="0" id="noOfEmail" onkeypress="return event.charCode ">= 48 && event.charCode <= 57 || event.keyCode === 8 || event.keyCode === 9 '>
															</div>
														</div>
														
														<div class="col-sm-offset-5 col-sm-10" >
															<button type="button"  class="btn btn-primary pull-center"   onclick="return updateBonusInfo();" style="background-color: #d23779;height: 30px;" >
																 Update
															
															</button>

														</div>
														<br>
														
														<div class="col-sm-offset-5 col-sm-10" >
														</div>
													
													</form>
													     
													
													  
												
												</div>
											</div>
											
											<script type="text/javascript">
													$("#updateError").hide();
													$("#updateSuccess").hide();
													
											</script>
											
											
										<!--Img setting Tag  -->
										
										<div class="tab-pane" id="imgsetting">
											<div class="box box-primary">
												<br>
													
													<div class="row">
													<div class="col-md-3">
  													<h4>Remove Images</h4>
													<div id="imgDiv"> 
													
													</div>
													</div>
													<div class="col-md-3">
													 <h4>Add  Images</h4>
													 <div id="addimgDiv"> 
													
													</div>
													</div>
													  
												
												</div>
											</div>
											</div>
										<!--Img setting Tag End  -->
											
											
											
											
											
											
											<div class="tab-pane" id="kitActive">
											<div class="box box-primary">
											
													

													<br> 
													<input type="text" class="form-control" id="userId" name="userId"
																	placeholder="Enter  User " " style="display: none;">
													<form class="form-horizontal">
														<div class="form-group">
															<label class="control-label col-sm-5" for="email">Kit User Name:</label>
															<div class="col-sm-7">
																<input type="text" class="form-control" id="kitId" name="kitId"
																	placeholder="Enter Kit User Name" ">
															</div>
														</div>
														<div class="form-group">
															<label class="control-label col-sm-5" for="pwd">Kit Password:</label>
															<div class="col-sm-7">
																<input type="password" class="form-control" id="kitPassword"  name="kitPassword"
																	placeholder="Enter Kit password">
															</div>
														</div>
														<div class="form-group">
														<div class="form-group">
															<div class="col-sm-offset-5 col-sm-10">
																<button type="button" class="btn-danger pinkbtn" onclick="uploadKitIdAndPasswordByAdmin();" style="background-color: #d23779;height: 30px;">Submit</button>
															</div>
														</div>
													</form>


												</div>
											
											<script type="text/javascript">
												$("#smsContents").val("").selectRange(0,0);
											</script>
											
											</div>
											
											
											
											
											
											
											
											
											


											</div>
											
											
											
										</div>
									</div>
								</div>
							</div>
							





						</div>
					</div>

				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript">
	
		var data = eval('${userModelDetailList}');
		
		var table = $('#example1')
				.DataTable(
						{
							"aaData" : data,
							"pagingType" : "full_numbers",
							"aoColumns" : [
									{
										"data" : "userId"
									},
									{
										"data" : "firstName"  
									},
									{
										"data" : "registrationDate",
										"render" : function(data) {
											var date = new Date(data);
											var month = date.getMonth() + 1;
											return (month.length > 1 ? month
													: month)
													+ "-"
													+ date.getDate()
													+ "-" + date.getFullYear();
										}

									},
									{
										"data" : "dateOfBirth",
										"render" : function(data) {
											var date = new Date(data);
											var month = date.getMonth() + 1;
											return (month.length > 1 ? month
													: month)
													+ "-"
													+ date.getDate()
													+ "-" + date.getFullYear();
										}
									},
									
									{
										"data" : null,
										"defaultContent" : "<button onclick=fun(this)>View</button>"
									}
									
									/* ,
									{
										"data" : null,
										"defaultContent" : "<button onclick=funDelete(this)>Delete</button>"
									} */]
						});

		function fun(aa) {
			//alert($(aa).closest('td').prev().prev().prev().prev().prev().prev().prev().text());

			var userId=$(aa).closest('td').prev().prev().prev().prev().text();
			// Here i am directlly passing the userModel id fot getting the userMOdel details
			$("#userId").val(userId);
			$("#viewFullProfileModalBtn").attr("href", "ViewProfileWithPartner?userId=" + userId);
			
			$("#hiddenuserId").val(userId);
			
			  $.ajax({
				url : '${pageContext.request.contextPath}/getUserModelPartnerPreferancesByUserModelId',
				type : "POST",
				dataType : "json",
				data : {
					userId : "" + userId
				},
				success : function(data) {
				 //incomeAmount
				 
				 
				 	var jsonData1 = JSON.parse(JSON.stringify(data));
					var userNameForAdmin = jsonData1.userName;
					var jsonData2 = JSON.parse(JSON.stringify(data));
					var passwordForAdmin = jsonData2.password;
					
					$("#userUname").text(userNameForAdmin);
					$("#userPassword").text(passwordForAdmin);
					
					
				 var userid=$("#hiddenuserId").val();
					var jsonData = JSON.parse(JSON.stringify(data));
					var counter = jsonData.preference;
					var cntForiterateobj = JSON.parse(counter);
					
					$("#hiddenUserpartnerId").val(cntForiterateobj.userDetailsId);
					
					$("#partnerState").val(cntForiterateobj.state_Country.state_Name);
					$("#partnerDistrict").val(cntForiterateobj.districts.district_Name); 
					
					
					$('[name=materialStatusPartner] option').filter(function() { 
					    return ($(this).val() == cntForiterateobj.materialStatus); 
					}).prop('selected', true); 
					
					$('[name=physicalStatusPartner] option').filter(function() { 
					    return ($(this).val() == cntForiterateobj.physicalStatus); 
					}).prop('selected', true); 
					
					$('input[name=bodyTypePartner]').prop('checked', false);
					if (cntForiterateobj.bodyType) { $('input[name=bodyTypePartner]').filter(function() { return $(this).val().trim().toLowerCase() === cntForiterateobj.bodyType.trim().toLowerCase(); }).prop('checked', true); }
					
					$('input[name=complexionPartner]').prop('checked', false);
					if (cntForiterateobj.complexion) { $('input[name=complexionPartner]').filter(function() { return $(this).val().trim().toLowerCase() === cntForiterateobj.complexion.trim().toLowerCase(); }).prop('checked', true); }
					
					
					$('[name=heightSelectPartnerFirst] option').filter(function() { 
					    return ($(this).val() == cntForiterateobj.heightFrom); 
					}).prop('selected', true); 
					
					
					$('[name=heightSelectPartnerSecond] option').filter(function() { 
					    return ($(this).val() == cntForiterateobj.heightTo); 
					}).prop('selected', true); 
					
					$('[name=stateSelectPartner] option').filter(function() { 
					    return ($(this).val() == cntForiterateobj.state_Country.state_Country_id); 
					}).prop('selected', true);
					
					try {	
						
						// Assigning city
						$.ajax({
							dataType : 'json',
							url : "educationOccupation/loadCityAccordingToState",
							type : "GET",
							data : "id=" + cntForiterateobj.state_Country.state_Country_id,"
							success : function(data) {

								$("#cityStateSelectPartnerPreferance option").remove();
								$("#cityStateSelectPartnerPreferance").append(
										'<option value=0>Select District</option>');
								for (var i = 0; i < data.length; i++) {
									$("#cityStateSelectPartnerPreferance").append(
											'<option value=" + data[i].state_district_id +" '>'
													+ data[i].district_Name + '</option>');
								}
								
								
								 $('[name=cityStateSelectPartnerPreferance] option').filter(function() { 
									    return ($(this).val() == cntForiterateobj.districts.state_district_id); 
									}).prop('selected', true); 

							},
							error : function(xhr, ajaxOptions, thrownError) {
								alert("err" + thrownError + xhr.responseText);
							}
						});
						
						
					
						
						$.ajax({
							dataType : 'json',
							url : "getPartnerEductionAssociationByAdmin",
							type : "GET",
							data : "userId=" +userId,
							success : function(data) {
								
								//first uncheck all 
								  for(j=0;j<educationId.length;j++){
									  $('#'+educationId[j]).prop('checked', false);
								  } 
											 
							       // then check only required 
							 
							
								for(j=0;j<data.length;j++){
									
									$('#'+data[j].educationCategory.edu_cat_id).prop('checked', true);	
												
								} 
								
							       flag=true;
								
							},
							error : function(xhr, ajaxOptions, thrownError) {
								alert("err" + thrownError + xhr.responseText);
							}
						});
						
						
						
						
						 $.ajax({
							dataType : 'json',
							url : "getPartnerOccupationAssociationByAdmin",
							type : "GET",
							data : "userId=" +userId,
							success : function(data) {
								
							
								for(j=0;j<occupationId.length;j++){
									  $('#occ_'+occupationId[j]).prop('checked', false);
								  } 
											 
							    for(j=0;j<data.length;j++){
										
									$('#occ_'+data[j].occupationCategory.occu_id).prop('checked', true);	
												
								}  
								
								
							},
							error : function(xhr, ajaxOptions, thrownError) {
								alert("err" + thrownError + xhr.responseText);
							}
						});  
						 
						
						/* Remove Image section */
						 var counter2=jsonData.profileImages;
							var cntForiterateobj3 = JSON.parse(counter2);
						
							var imageNamestring=""+cntForiterateobj3+"";
						
							var myarray = imageNamestring.split(',');

							for(var i = 0; i < myarray.length; i++)
							{
									
							
								var imgList="<ul" style="list-style: none;;">;"+
								"<li id="+myarray[i]+">"+
								"<img src=resources/UserImages/"+myarray[i]+""+ " style="width: 40%;" height:auto%;' onerror="this.onerror=null; this.src='resources/UserImages/userImage.png';">"+
								 "<li ><input type=button onclick=removeImageByAdmin('"+myarray[i]+"',this,"+userid+");" +
								 " btn-secondary btn-sm pinkbtn value=Remove style="background-color: #d23779;;"></li>&nbsp;&nbsp;&nbsp;&nbsp;"+
								"</li>&nbsp;&nbsp;&nbsp;&nbsp;"+
								"</ul>";
								
								 $("#imgDiv").append(imgList);
							}
						
							/* End of remove image section */
						// addimgDiv
						
						/* Add new Image section */
						
						
						var addImg ="<ul style="list-style: none;;"> <br>;"+
							"<li ><br>"+
							"<input type=file class=form-control style="width: 100px; height: auto; accept=.gif,.jpg,.jpeg,.png;"+
							"name=loader11 id=loader11 multiple=multiple onchange=model();><br>"+
							"</li><li>"+	
							"<input type=button class=btn-danger pinkbtn onclick=saveImagesByAdmin("+userid+"); data-toggle=modal data-target=#userlogin style="background-color: #d23779;  class=btn btn-secondary pinkbtn value=save Image;">;"+
							"</li></ul>";
									
							 $("#addimgDiv").append(addImg);
					
						/*End of add image section  */
							
							
							
						
						 
						
						
						
						$('[name=religionSelectPartner] option').filter(function() { 
						    return ($(this).val() == cntForiterateobj.religion.religion_id); 
						}).prop('selected', true); 
						
						
						$.ajax({
							dataType : 'json',
							url : "educationOccupation/loadCasteAccordingToReligion",
							type : "GET",
							data : "id=" + cntForiterateobj.religion.religion_id,"
							success : function(data) {

								$("#casteSelectPartner option").remove();
								$("#casteSelectPartner").append('<option value=0>Select Caste</option>');
								for (var i = 0; i < data.length; i++) {
									$("#casteSelectPartner").append(
											'<option value=" + data[i].caste_id +" '>'
													+ data[i].caste_name + '</option>');
								}
								
								$('[name=casteSelectPartner] option').filter(function() { 
								    return ($(this).val() == cntForiterateobj.caste.caste_id); 
								}).prop('selected', true); 

							},
							error : function(xhr, ajaxOptions, thrownError) {
								alert("err" + thrownError + xhr.responseText);
							}
						});
						
						$('input[name=employedInPartner][value="'+cntForiterateobj.employeedIn+'"]')
						.attr('checked', true);
						
						$('input[name=incomePartner][value="'+cntForiterateobj.incomeType+'"]')
						.attr('checked', true);
						
						$("#incomeAmountPartner").val(cntForiterateobj.incomeAmount);
						
						$('input[name=familyClassInfoPartner][value="'+cntForiterateobj.familyClass+'"]')
						.attr('checked', true);
						
						$('input[name=familyTypeInfoPartner][value="'+cntForiterateobj.familyType+'"]')
						.attr('checked', true);
						

						$('input[name=familyValueInfoPartner][value="'+cntForiterateobj.familyValues+'"]')
									.attr('checked', true);
						

						$('input[name=eatingPartner][value="'+cntForiterateobj.eating+'"]')
									.attr('checked', true);
						$('input[name=drinkPartner][value="'+cntForiterateobj.drink+'"]')
						.attr('checked', true);
						
						$('input[name=smokePartner][value="'+cntForiterateobj.smoke+'"]')
						.attr('checked', true);
				
						
						
						
						
						
						
					} catch (e) {
						
					}

					
					
					
					
					
			// user details list
					var counter1=jsonData.userDetail;
					var cntForiterateobj1 = JSON.parse(counter1);
					$("#userName").text(cntForiterateobj1.userModel.firstName);
					$("#firstName").val(cntForiterateobj1.userModel.firstName);
					$("#userState").text(cntForiterateobj1.state_Country.state_Name);
					$("#userCity").text(cntForiterateobj1.districts.district_Name); 
					$("#userDesignation").text(cntForiterateobj1.occupation.occupation_name);
					$("#email").val(cntForiterateobj1.email);  
					
					
					// 18-04-2017 code update user Details By Admin
					
					$('[name=ProfileCreatedFor] option').filter(function() { 
					    return ($(this).text() == cntForiterateobj1.profileCreatedFor); 
					}).prop('selected', true); 
					
					$('input[name=gender][value="'+cntForiterateobj1.gender+'"]')
						.attr('checked', true);
					
					var regex = /<br\s*[\/]?>/gi;
					$("#aboutMe").val(cntForiterateobj1.aboutMe.replace(regex, "\n"));
					$('[name=materialStatus] option').filter(function() { 
					    return ($(this).val() == cntForiterateobj1.materialStatus); 
					}).prop('selected', true); 
					
					$('[name=physicalStatus] option').filter(function() { 
					    return ($(this).text() == cntForiterateobj1.physicalStatus); 
					}).prop('selected', true); 
						
					$('input[name=bodyType][value="'+cntForiterateobj1.bodyType+'"]')
					.attr('checked', true);
					
					$('input[name=complexion][value="'+cntForiterateobj1.complexion+'"]')
					.attr('checked', true);
					
					
					$('[name=heightSelect] option').filter(function() { 
					    return ($(this).val() == cntForiterateobj1.height); 
					}).prop('selected', true);
					
					$('[name=weightSelect] option').filter(function() { 
					    return ($(this).val() == cntForiterateobj1.weight); 
					}).prop('selected', true); 
					
					
					$('[name=stateSelect] option').filter(function() { 
					    return ($(this).val() == cntForiterateobj1.state_Country.state_Country_id); 
					}).prop('selected', true); 

						// load and set districty
					$.ajax({
						dataType : 'json',
						url : "educationOccupation/loadCityAccordingToState",
						type : "GET",
						data : "id=" +cntForiterateobj1.state_Country.state_Country_id,"
						success : function(data) {

							$("#cityStateSelect option").remove();
							$("#cityStateSelect").append(
									'<option value=0>Select District</option>');
							for (var i = 0; i < data.length; i++) {
								$("#cityStateSelect").append(
										'<option value=" + data[i].state_district_id +" '>'
												+ data[i].district_Name + '</option>');
							}
							
							 $('[name=cityStateSelect] option').filter(function() { 
								    return ($(this).val() == cntForiterateobj1.districts.state_district_id); 
								}).prop('selected', true); 

						},
						error : function(xhr, ajaxOptions, thrownError) {
							alert("err" + thrownError + xhr.responseText);
						}
					});
					
					// load and set  sub districty
					$.ajax({
						dataType : 'json',
						url : "educationOccupation/loadsubDistrictAccordingToDistrict",
						type : "GET",
						data : "id=" + cntForiterateobj1.districts.state_district_id,"
						success : function(data) {

							$("#subDistSelect option").remove();
							$("#subDistSelect").append(
									'<option value=0>Select Sub District</option>');
							for (var i = 0; i < data.length; i++) {
								$("#subDistSelect")
										.append(
												'<option value=""
														+ data[i].district_subDistricts_id
														+ '>' + data[i].subdistricts_Name
														+ '</option>');
							}
							

							$('[name=subDistSelect] option').filter(function() { 
							    return ($(this).val() == cntForiterateobj1.subDistricts.district_subDistricts_id); 
							}).prop('selected', true); 

						},
						error : function(xhr, ajaxOptions, thrownError) {
							alert("err" + thrownError + xhr.responseText);
						}
					});
					
					$('[name=religionSelect] option').filter(function() { 
					    return ($(this).val() == cntForiterateobj1.caste.religion_id.religion_id); 
					}).prop('selected', true); 
					
					// load and assign caste
					
					$.ajax({
						dataType : 'json',
						url : "educationOccupation/loadCasteAccordingToReligion",
						type : "GET",
						data : "id=" + cntForiterateobj1.caste.religion_id.religion_id,"
						success : function(data) {

							$("#casteSelect option").remove();
							$("#casteSelect").append('<option value=0>Select Caste</option>');
							for (var i = 0; i < data.length; i++) {
								$("#casteSelect").append(
										'<option value=" + data[i].caste_id +" '>'
												+ data[i].caste_name + '</option>');
							}
							
							$('[name=casteSelect] option').filter(function() { 
							    return ($(this).val() == cntForiterateobj1.caste.caste_id); 
							}).prop('selected', true); 

						},
						error : function(xhr, ajaxOptions, thrownError) {
							alert("err" + thrownError + xhr.responseText);
						}
					});
					
					
					 $('[name= educationalCategorySelect] option').filter(function() { 
						    return ($(this).val() == cntForiterateobj1.education.edu_cat_id.edu_cat_id); 
						}).prop('selected', true); 
					
					 
					 
					 $.ajax({
							dataType : 'json',
							url : "educationOccupation/loadEducationAccordingToEduCategory",
							type : "GET",
							data : "id=" +cntForiterateobj1.education.edu_cat_id.edu_cat_id,"
							success : function(data) {
								$("#educationalSelect").prop('disabled', false);
								$("#educationalSelect option").remove();
								$("#educationalSelect").append(
										'<option value=0>Select Education</option>');
								for (var i = 0; i < data.length; i++) {
									$("#educationalSelect").append(
											'<option value=" + data[i].education_id +" '>'
													+ data[i].education_name + '</option>');
								}
								
								 $('[name=educationalSelect] option').filter(function() { 
									    return ($(this).val() == cntForiterateobj1.education.education_id); 
									}).prop('selected', true); 
								 
							},
							error : function(xhr, ajaxOptions, thrownError) {
								alert("err" + thrownError + xhr.responseText);
							}
						});
					
						$('[name= occupationCategorySelect] option').filter(function() { 
						    return ($(this).val() == cntForiterateobj1.occupation.occ_cat_id.occu_id); 
						}).prop('selected', true); 
						
						
						
						$.ajax({
							dataType : 'json',
							url : "educationOccupation/loadOccupationAccordingToOccuCategory",
							type : "GET",
							data : "id=" + cntForiterateobj1.occupation.occ_cat_id.occu_id,"
							success : function(data) {
								$("#occupationSelect").prop('disabled', false);

								$("#occupationSelect option").remove();
								$("#occupationSelect").append(
										'<option value=0>Select Occupation</option>');
								for (var i = 0; i < data.length; i++) {
									$("#occupationSelect").append(
											'<option value=" + data[i].occupation_id +" '>'
													+ data[i].occupation_name + '</option>');
								}

								$('[name= occupationSelect] option').filter(function() { 
								    return ($(this).val() == cntForiterateobj1.occupation.occupation_id); 
								}).prop('selected', true); 
							},
							error : function(xhr, ajaxOptions, thrownError) {
								alert("err" + thrownError + xhr.responseText);
							}
						});
					 
						
						try {
								$('input[name=employedIn][value="'+cntForiterateobj1.employeedIn+'"]')
							.attr('checked', true);
							
						} catch (e) {
						}
						try {
							$('input[name=income][value="'+cntForiterateobj1.incomeType+'"]')
							.attr('checked', true);
							
						} catch (e) {
						}
						
						try {
							$("#incomeAmount").val(cntForiterateobj1.incomeAmount);
						} catch (e) {
						}
						try {
							

							$('input[name=familyClassInfo]').prop('checked', false);
							if (cntForiterateobj1.familyClass) { $('input[name=familyClassInfo]').filter(function() { return $(this).val().trim().toLowerCase() === cntForiterateobj1.familyClass.trim().toLowerCase(); }).prop('checked', true); }
						} catch (e) {
						}
						try {
							$('input[name=familyTypeInfo]').prop('checked', false);
							if (cntForiterateobj1.familyType) { $('input[name=familyTypeInfo]').filter(function() { return $(this).val().trim().toLowerCase() === cntForiterateobj1.familyType.trim().toLowerCase(); }).prop('checked', true); }
							
						} catch (e) {
						}
						try {
							
							$('input[name=familyValueInfo]').prop('checked', false);
							if (cntForiterateobj1.familyValues) { $('input[name=familyValueInfo]').filter(function() { return $(this).val().trim().toLowerCase() === cntForiterateobj1.familyValues.trim().toLowerCase(); }).prop('checked', true); }
						} catch (e) {
							// TODO: handle exception
						}
						
						try {
							$('input[name=eating]').prop('checked', false);
							if (cntForiterateobj1.eating) { $('input[name=eating]').filter(function() { return $(this).val().trim().toLowerCase() === cntForiterateobj1.eating.trim().toLowerCase(); }).prop('checked', true); }
							
							
						} catch (e) {
							// TODO: handle exception
						}
						
						try {
								$('input[name=drink]').prop('checked', false);
							if (cntForiterateobj1.drink) { $('input[name=drink]').filter(function() { return $(this).val().trim().toLowerCase() === cntForiterateobj1.drink.trim().toLowerCase(); }).prop('checked', true); }
							
						} catch (e) {
						}
						
						try {
								$('input[name=smoke]').prop('checked', false);
							if (cntForiterateobj1.smoke) { $('input[name=smoke]').filter(function() { return $(this).val().trim().toLowerCase() === cntForiterateobj1.smoke.trim().toLowerCase(); }).prop('checked', true); }
						} catch (e) {
						}
						
						
						
						
						
					//$("#userMstatus").val(cntForiterateobj1.materialStatus);
					// End of 18-04 code
				   
					
					$("#hiddenUsermodelId").val(cntForiterateobj1.userDetailsId);
					$("#mobile").val(cntForiterateobj1.mobileNum);
					
					if (cntForiterateobj1.dateOfBirth) {
						var time = new Date(cntForiterateobj1.dateOfBirth);
						if (!isNaN(time.getTime()) && time.getFullYear() > 1900 && time.getFullYear() < 2100) {
							var year=time.getFullYear();
							var month=time.getMonth()+1;
							var today=time.getDate();
							if(today < 10) today='0'+today;
							if(month < 10) month='0'+month;
							var fullYear=today+"/"+month+"/"+year;
							$("#dob").val(fullYear);
						} else {
							$("#dob").val("");
						}
					} else {
						$("#dob").val("");
					} 
					 
					
					
					
					/* var cm=cntForiterateobj1.height;
					var realFeet=cm/30.48;
					var feet = Math.floor(realFeet);
					var inches = Math.round((realFeet - feet) * 12);
					
					$("#userHeight").val(feet+" Ft  "+inches+"  inc"); */
					
					$("#userFamilyType").val(cntForiterateobj1.familyType);
					$("#userReligion").val(cntForiterateobj.religion.religion_name);
					$("#userCast").val(cntForiterateobj1.caste.caste_name);
					$("#userEmailId").val(cntForiterateobj1.email);  
					$("#userMobile").val(cntForiterateobj1.mobileNum);
					$("#userMobile").val(cntForiterateobj1.mobileNum);
				
					var img=jsonData.userImage.replace(/"/g, "");
					$('#userProfilePicture').attr('src', 'resources/UserImages/'+img);
					
					// Settting kit details   
					try {
						
						$("#kitIdd").val(cntForiterateobj1.userModel.kit.kit_username);
						$("#kitpasswordd").val(cntForiterateobj1.userModel.kit.kit_password);
					} catch (e) {
						
						$("#kitIdd").val('Free User');
						$("#kitpasswordd").val('Free User');
						
					}
					
					
					$('#myModal').modal('show')
					
					},
				error : function(errorThrown) {
					alert("Error: " + errorThrown);
				}
			}); 
			
			//$('#myModal').modal('show')

		}
		
		
		function funDelete(cc)
		{
			var userId=$(cc).closest('td').prev().prev().prev().prev().prev().text();
			
			$.confirm({
			    title: '',
			    content: 'Do You Want To Delete ?',
			    buttons: {
			        confirm: function () {
			        	
			        	$.ajax({
							dataType : 'Text',
							url : "deleteProfileByAdmin",
							type : "POST",
							data : "userId=" +userId,
							success : function(data) {
								
								var p = cc.parentNode.parentNode.rowIndex;
								document.getElementById("example1").deleteRow(p);
								
							},
							error : function(xhr, ajaxOptions, thrownError) {
								alert("err" + thrownError + xhr.responseText);
							}
						});
			           
			        	
			        	
			        },
			        cancel: function () {
			            
			        }
			      
			    }
			});
		}
		
	</script>
	
	
	<script type="text/javascript">
	
	
	function sendEmail()
	{
		
			  var email=$("#userEmailId").val();
			  var subject=$("#subject").val();
			 var message=$("#msgContents").val();
			 
			 if(subject.trim()=="" || subject.trim()==null)
				 {
				 
				 	$("#subjectSelect").slideDown();
					$("#subjectSelect").fadeOut(5000);
					return false;
				 }
		
				$.ajax({
					url : "sendEmailToRegisteredUser",
					type : "POST",
					data : {
						email : "" + email,
						subject : "" + subject,
						message : "" + message
					},
					success : function(data) {
						
						
					$("#subject").val("");
					$("#msgContents").val("");
					$("#mailSendselect").slideDown();
					$("#mailSendselect").fadeOut(5000);
					
					
					},
					error : function(xhr, ajaxOptions, thrownError) {
						console.log("err" + thrownError + xhr.responseText);
		
						return false;
					}
				});
		
		
		
	}
	
	
	
	function sendSms()
	{
		
		
		var smsCriteria=$('input[name=optradio]:checked').val(); 
		var smsText=$("#smsContents").val();
		var mob=$("#userMobile").val();
		
		
		
		$.ajax({
			url : "sendSms",
			type : "POST",
			data : {
				mob : "" + mob,
				smsText : "" + smsText,
				type : "" + smsCriteria
			},    
			success : function(data) {
				
					if(data.trim()=='success')
						{
							$("#smsContents").val("");
							
						}
			},
			error : function(xhr, ajaxOptions, thrownError) {
				console.log("err" + thrownError + xhr.responseText);

				return false;
			}
		});
		
		
		  
	}
	
	
	function updateBonusInfo()
	{
		
		var userId=$("#userId").val();   
		var kitId=$("#kitIdd").val();   
		var kitPass=$("#kitpasswordd").val();
		var noOfMessage=$("#noOfMessage").val();
		var noOfDay=$("#noOfDay").val();
		var noOfProfile=$("#noOfProfile").val();
		var noOfEmail=$("#noOfEmail").val();
		
		
		if(kitId=="Free User" || kitPass=="Free User")
			{
					
					$("#updateError").slideDown();
					$("#updateError").fadeOut(5000);
					return false;
			}
		  
		
		
		
		var arr = {
				
				userId :userId,
				kitId :kitId,
				kitPass :kitPass,
				noOfMessage :noOfMessage,
				noOfDay :noOfDay,
				noOfProfile :noOfProfile,
				noOfEmail :noOfEmail
				
		};
		
		var oMyForm = new FormData();
		oMyForm.append("jsonObjectData", JSON.stringify(arr));
		$.ajax({
			url : "upgradeKitBonusByAdmin",
			type : "POST",
			data : oMyForm,
			processData : false,
			contentType : false,
			success : function(data) {
				
				
				
				$("#noOfMessage").val("0");
				$("#noOfDay").val("0");
				$("#noOfProfile").val("0");
				$("#noOfEmail").val("0");
				
				$("#updateSuccess").slideDown();
				$("#updateSuccess").fadeOut(5000);
				
				
				
			},
			error : function(xhr, ajaxOptions, thrownError) {
				alert("err" + thrownError + xhr.responseText);
			}
		});
	
		
		
	}

	
	</script>

</body>
</html>




