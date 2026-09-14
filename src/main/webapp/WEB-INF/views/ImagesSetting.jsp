<!DOCTYPE html>
<html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Location Home</title>
<!-- Tell the browser to be responsive to screen width -->
<jsp:include page="body.jsp"></jsp:include>
<link rel="stylesheet" type="text/css"
	href="resources/confirmBox/confirm.css" media="screen">
<script src="resources/confirmBox/confirm.js" type="text/javascript"></script>
<link rel="stylesheet" href="resources/datepicker/css/datepicker.css">
<script src="resources/datepicker/js/datepicker.js"></script>
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
<style>
.noteLink {
	font-size: 16px;
	color: green;
}

.noteLink:hover {
	text-decoration: underline;
	color: #3c8dbc;
}

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
</style>
<script>
	var allCountryName = new Array();
	var files = [];
</script>
<!-- Asset start -->
<!--Asset  end  -->
<script
	src="${pageContext.request.contextPath}/resources/location/ImageSetting.js"
	charset='utf-8'></script>
<script
	src="${pageContext.request.contextPath}/resources/Branch/branch.js"
	charset='utf-8'></script>

</head>
<body class="hold-transition skin-blue sidebar-mini">
	<div class="wrapper">
		<jsp:include page="menu.jsp"></jsp:include>
		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<!-- Main content -->
			<section class="content">
				<div class="row">
					<div class="col-md-12">
						<div class="nav-tabs-custom">
							<input type="hidden" id="hiddenCountry"> <input
								type="hidden" id="hiddenState"> <input type="hidden"
								id="hiddenState">
							<ul class="nav nav-tabs">
								<li class="active"><a href="#country_toggle"
									data-toggle="tab">Logo setting</a></li>
								<li><a href="#state_toggle" data-toggle="tab">Success
										Images & Stories</a></li>
								<li><a href="#district_toggle" data-toggle="tab">Background
										Images</a></li>
								<!--     <li><a href="#subdistrict_toggle" data-toggle="tab">Success Stories</a></li> -->
								<li><a href="#contactUs_toggle" data-toggle="tab">Contact
										Us</a></li>
								<li><a href="#offer_toggle" data-toggle="tab">Offer
										Image</a></li>
								<li><a href="#faqs" data-toggle="tab">FAQs</a></li>
								<li><a href="#privacy_toggle" data-toggle="tab">Privacy
										Policy</a></li>
								<li><a href="#aboutus_toggle" data-toggle="tab">About
										Us</a></li>
								<li><a href="#term_toggle" data-toggle="tab">Term &
										Condition</a></li>
								<li><a href="#changePass_toggle" data-toggle="tab">Change
										Password</a></li>
							</ul>
							<div class="tab-content">
								<div class="active tab-pane" id="country_toggle">
									<div class="panel panel-danger">
										<div class="panel-heading" style="background-color: #d23779;">
											<font color="white">Update New Logo Images</font>
										</div>
										<div class="panel-body" ">
											<div class="alert alert-danger alert-dismissable"
												id="logoSelect">
												<button type="button" class="close" data-dismiss="alert"
													aria-hidden="true">&times;</button>
												<strong>Error: </strong>Please Select Five Logos.
											</div>
											<div class="alert alert-success alert-dismissable"
												id="logosuccess">
												<button type="button" class="close" data-dismiss="alert"
													aria-hidden="true">&times;</button>
												<strong>Message: </strong>Logo Uploaded Successfully.
											</div>
											<div class="alert alert-danger alert-dismissable"
												id="logoSizeSelect">
												<button type="button" class="close" data-dismiss="alert"
													aria-hidden="true">&times;</button>
												<strong>Error: </strong>You have Already Uploaded 10 Logo's.
											</div>
											<div class="col-lg-1 col-md-3 col-sm-3 col-xs-12"></div>
											<label style="color: green;">(Note : Dimensions for
												logo images - Height 75px and Width - 280px)</label>
											<div class="col-lg-3 col-md-9 col-sm-9 col-xs-6">
												<form>
													<input type="file" name="b" id="logoImages"
														multiple="multiple" accept=".gif,.jpg,.jpeg,.png" /> <br>
													<input type="button" class="btn btn-primary" "
														id="uploadLogoImagesButton" value="Save Logo"
														onclick="  uploadLogoImages()" " />
												</form>
											</div>
											<div class="col-lg-6 col-md-9 col-sm-9 col-xs-12"></div>
										</div>
									</div>
								</div>
								<!-- /.tab-pane -->
								<div class="tab-pane" id="state_toggle">
									<div class="panel panel-danger">
										<div class="panel-heading" style="background-color: #d23779;">
											<font color="white">Update New Success Images and
												Stories</font>
										</div>
										<div class="panel-body">
											<div class="alert alert-danger alert-dismissable"
												id="successImageselect">
												<button type="button" class="close" data-dismiss="alert"
													aria-hidden="true">&times;</button>
												<strong>Error: </strong>Please Select Four Image.
											</div>

											<div class="alert alert-danger alert-dismissable"
												id="selectFirstSuccessStoryPortionAlert">
												<button type="button" class="close" data-dismiss="alert"
													aria-hidden="true">&times;</button>
												<strong>Error: </strong>Please Select First Success Story
												Portion.
											</div>

											<div class="alert alert-danger alert-dismissable"
												id="selectSecondSuccessStoryPortionAlert">
												<button type="button" class="close" data-dismiss="alert"
													aria-hidden="true">&times;</button>
												<strong>Error: </strong>Please Select Second Success Story
												Portion.
											</div>

											<div class="alert alert-danger alert-dismissable"
												id="selectThirdSuccessStoryPortionAlert">
												<button type="button" class="close" data-dismiss="alert"
													aria-hidden="true">&times;</button>
												<strong>Error: </strong>Please Select Third Success Story
												Portion.
											</div>
											<div class="alert alert-danger alert-dismissable"
												id="selectFourSuccessStoryPortionAlert">
												<button type="button" class="close" data-dismiss="alert"
													aria-hidden="true">&times;</button>
												<strong>Error: </strong>Please Select Forth Success Story
												Portion.
											</div>
											<div class="alert alert-success alert-dismissable"
												id="successImgessuccess">
												<button type="button" class="close" data-dismiss="alert"
													aria-hidden="true">&times;</button>
												<strong>Message: </strong>Image Uploaded Successfully.
											</div>

											<div class="form-group">
												<div class="col-sm-5">
													<label for="firstPortion">Enter First Success Story</label>
													<div class="successStoriesMassage"
														id="successStoriesMassage"></div>
												</div>
												<div class="col-sm-5">
													<label for="secondPortion">Enter Second Success
														Story</label>
													<div class="successStoriesMassageSecand"
														id="successStoriesMassageSecand"></div>
												</div>

											</div>

											<!-- <div class="col-lg-1 col-md-3 col-sm-3 col-xs-12"></div> -->
											<div class="form-group">
												<div class="col-sm-5">
													<label for="firstPortion">Enter Third Success Story</label>
													<div class="successStoriesMassageThird"
														id="successStoriesMassageThird"></div>
												</div>
												<div class="col-sm-5">
													<label for="secondPortion">Enter fourth Success
														Story</label>
													<div class="successStoriesMassageforth"
														id="successStoriesMassageforth"></div>
												</div>
											</div>
											<br> <label style="color: green; padding-top: 15px;">(Note
												: Dimensions for Success images - Height 482px and Width -
												584px)</label>
											<div class="col-lg-3 col-md-9 col-sm-9 col-xs-6">
												<form>
													<input type="file" name="b" id="successImages"
														multiple="multiple" accept=".gif,.jpg,.jpeg,.png"
														style="padding-top: 15px;" /> <br> <input
														type="button" class="btn btn-primary" "
														id="uploadSuccessImagesButton" value="Save Success Images"
														onclick="  uploadSuccessImages()" " />
												</form>
											</div>
										</div>
									</div>
								</div>
								<!-- /.tab-pane -->
								<div class="tab-pane" id="district_toggle">
									<div class="panel panel-danger">
										<div class="panel-heading" style="background-color: #d23779;">
											<font color="white">Update New Background Images</font>
										</div>
										<div class="panel-body">
											<div class="alert alert-danger alert-dismissable"
												id="backGroundImageselect">
												<button type="button" class="close" data-dismiss="alert"
													aria-hidden="true">&times;</button>
												<strong>Error: </strong>Please Select Four Image.
											</div>
											<div class="alert alert-success alert-dismissable"
												id="backImgessuccess">
												<button type="button" class="close" data-dismiss="alert"
													aria-hidden="true">&times;</button>
												<strong>Message: </strong>Image Uploaded Successfully.
											</div>
											<div class="col-lg-1 col-md-3 col-sm-3 col-xs-12"></div>
											<label style="color: green;">(Note : Dimensions for
												Background images - Height 672px and Width - 1222px)</label>
											<div class="col-lg-3 col-md-9 col-sm-9 col-xs-6">
												<form>
													<input type="file" name="b" id="backgroundImages"
														multiple="muliple" accept=".gif,.jpg,.jpeg,.png" /> <br>
													<input type="button" class="btn btn-primary" "
														id="uploadBackgroundImagesButton"
														value="Save Background Images"
														onclick="  uploadBackgroundImages()" " />
												</form>
											</div>
											<div class="col-lg-6 col-md-9 col-sm-9 col-xs-12"></div>
										</div>
										<script type="text/javascript">
											$("#backGroundImageselect").hide();
											$("#backImgessuccess").hide();
										</script>
									</div>
								</div>
								<div class="tab-pane" id="subdistrict_toggle">
									<div class="panel panel-danger">
										<div class="panel-heading" style="background-color: #d23779;">
											<font color="White">Save New Success Stories</font>
										</div>
										<div class="alert alert-danger alert-dismissable"
											id="storySelect">
											<button type="button" class="close" data-dismiss="alert"
												aria-hidden="true">&times;</button>
											<strong>Error: </strong>Please Enter Success Story Message.
										</div>
										<div class="alert alert-success alert-dismissable"
											id="storySuccessSelect">
											<button type="button" class="close" data-dismiss="alert"
												aria-hidden="true">&times;</button>
											<strong>Message: </strong>New Success Story Added
											Successfully.
										</div>
										<div class="panel-body">
											<form class="form-horizontal">
												<div class="box-body">
													<div class="form-group">
														<!--  <label for="subdistName" class="col-sm-3 control-label">Sub District Name</label> -->
														<div class="col-sm-12">
															<label for="countryNamesubDist">Enter New Story</label>
															<div class="successStoriesMassage"
																id="successStoriesMassage"></div>
														</div>
														<div class="col-sm-12">
															<button type="button" class="btn btn-primary btn-block"
																onclick="return saveSuccessStory();">Save</button>
														</div>
													</div>
												</div>
											</form>
										</div>
									</div>
									<script type="text/javascript">
										$('.successStoriesMassage')
												.summernote(
														{
															disableDragAndDrop : false,
															height : 250,

															codemirror : {
																theme : 'monokai'
															},
															toolbar : [
																	[
																			'style',
																			[
																					'bold',
																					'italic',
																					'underline',
																					'clear' ] ],
																	[
																			'para',
																			[
																					'ul',
																					'ol',
																					'paragraph' ] ],
																	[
																			'fontsize',
																			[ 'fontsize' ] ],
																	[
																			'color',
																			[ 'color' ] ]

															],
															//focus : true,
															onImageUpload : function(
																	files,
																	editor,
																	welEditable) {
																sendFile(
																		files[0],
																		editor,
																		welEditable);
															}
														});

										$('.successStoriesMassageSecand')
												.summernote(
														{
															disableDragAndDrop : false,
															height : 250,

															codemirror : {
																theme : 'monokai'
															},
															toolbar : [
																	[
																			'style',
																			[
																					'bold',
																					'italic',
																					'underline',
																					'clear' ] ],
																	[
																			'para',
																			[
																					'ul',
																					'ol',
																					'paragraph' ] ],
																	[
																			'fontsize',
																			[ 'fontsize' ] ],
																	[
																			'color',
																			[ 'color' ] ]

															],
															//focus : true,
															onImageUpload : function(
																	files,
																	editor,
																	welEditable) {
																sendFile(
																		files[0],
																		editor,
																		welEditable);
															}
														});

										$('.successStoriesMassageThird')
												.summernote(
														{
															disableDragAndDrop : false,
															height : 250,

															codemirror : {
																theme : 'monokai'
															},
															toolbar : [
																	[
																			'style',
																			[
																					'bold',
																					'italic',
																					'underline',
																					'clear' ] ],
																	[
																			'para',
																			[
																					'ul',
																					'ol',
																					'paragraph' ] ],
																	[
																			'fontsize',
																			[ 'fontsize' ] ],
																	[
																			'color',
																			[ 'color' ] ]

															],
															//focus : true,
															onImageUpload : function(
																	files,
																	editor,
																	welEditable) {
																sendFile(
																		files[0],
																		editor,
																		welEditable);
															}
														});

										$('.successStoriesMassageforth')
												.summernote(
														{
															disableDragAndDrop : false,
															height : 250,

															codemirror : {
																theme : 'monokai'
															},
															toolbar : [
																	[
																			'style',
																			[
																					'bold',
																					'italic',
																					'underline',
																					'clear' ] ],
																	[
																			'para',
																			[
																					'ul',
																					'ol',
																					'paragraph' ] ],
																	[
																			'fontsize',
																			[ 'fontsize' ] ],
																	[
																			'color',
																			[ 'color' ] ]

															],
															//focus : true,
															onImageUpload : function(
																	files,
																	editor,
																	welEditable) {
																sendFile(
																		files[0],
																		editor,
																		welEditable);
															}
														});

										$("#chatBoxSel").hide();
										$("#story").val("").selectRange(0, 0);
										$("#storySelect").hide();
									</script>
								</div>

								<div class="tab-pane" id="contactUs_toggle">


									<div class="alert alert-danger alert-dismissable"
										id="addBranchName">
										<button type="button" class="close" data-dismiss="alert"
											aria-hidden="true">&times;</button>
										<strong>Error: </strong>Please Enter Branch Name
									</div>

									<div class="alert alert-danger alert-dismissable" id="addLat">
										<button type="button" class="close" data-dismiss="alert"
											aria-hidden="true">&times;</button>
										<strong>Error: </strong>Please Enter Latitude
									</div>
									<div class="alert alert-danger alert-dismissable" id="addLong">
										<button type="button" class="close" data-dismiss="alert"
											aria-hidden="true">&times;</button>
										<strong>Error: </strong>Please Enter Longitude
									</div>
									<div class="alert alert-danger alert-dismissable"
										id="addAddress">
										<button type="button" class="close" data-dismiss="alert"
											aria-hidden="true">&times;</button>
										<strong>Error: </strong>Please Enter Address
									</div>

									<div class="alert alert-danger alert-dismissable"
										id="emailRequired">
										<button type="button" class="close" data-dismiss="alert"
											aria-hidden="true">&times;</button>
										<strong>Error: </strong>Please Enter Valid Email Id
									</div>
									<div class="alert alert-success alert-dismissable"
										id="branchDeleted">
										<button type="button" class="close" data-dismiss="alert"
											aria-hidden="true">&times;</button>
										<strong>Error: </strong>Branch Details Deleted Successfully
									</div>



									<div class="alert alert-success" id="branchSuccess">
										<button type="button" class="close" data-dismiss="alert"
											aria-hidden="true">&times;</button>
										<strong>Success: </strong>Branch Added Successfully
									</div>
									<div class="panel panel-danger">
										<div class="panel-heading" style="background-color: #d23779;">
											<font color="white">Update Google Map</font>
										</div>
										<div class="panel-body">





											<div class="form-group">
												<!--  <label for="subdistName" class="col-sm-3 control-label">Sub District Name</label> -->
												<div class="row">
													<div class="col-sm-3">
														<label for="countryNamesubDist">Branch Name<sup><font
																color="red" size="2px">*</font></sup></label> <input type="text"
															class="form-control input-sm" id="branch" name="branch"
															placeholder="Enter Branch Name">
													</div>
													<div class="col-sm-3">
														<label for="stateNameSubDist">Branch Address <font
															color="red" size="2px">*</font></sup></label> <input type="text"
															class="form-control input-sm" id="branchAdd"
															name="branchAdd" placeholder="Enter Branch Address">
													</div>
												</div>
												<br>
												<div class="row">
													<div class="col-sm-3">
														<label for="countryNamesubDist">Contact No </label> <input
															type="text" class="form-control input-sm" id="contactNo"
															name="contactNo" placeholder="Enter Contact No"
															maxlength="10"
															onkeypress="return event.charCode ">= 48 && event.charCode <= 57 || event.keyCode === 8 || event.keyCode === 9 '>
													</div>
													<div class="col-sm-3">
														<label for="stateNameSubDist">Email id</label> <input
															type="text" class="form-control input-sm" id="emailId"
															name="emailId" placeholder="Enter email id">
													</div>
												</div>
												<br>
												<div class="row">
													<div class="col-sm-3">
														<label for="DistNameSubDist">Latitude <font
															color="red" size="2px">*</font></sup></label> <input type="text"
															class="form-control input-sm" id="lat" name="lat"
															placeholder="Enter Latitude">
													</div>

													<div class="col-sm-3">
														<label for="subdistName">Longitude <font
															color="red" size="2px">*</font></sup></label> <input type="text"
															class="form-control input-sm" id="longitude"
															name="longitude" placeholder="Enter Longitude">
													</div>
													<br>
													<div class="col-sm-4">
														<a href="https://www.latlong.net/" target="blank"
															class="noteLink"> (Note : Click Here to Select
															Latitude & Longitude)</a>
													</div>

												</div>
												<br>
												<div class="row" style="text-align: center;">
													<div class="col-sm-3">
														<button type="button" style="margin-left: 180%;"
															class="btn btn-primary"
															onclick="return addBranchDetails();">Add Branch
															Details</button>
													</div>
												</div>


												<div class="panel-body">
													<div class="box">
														<div class="box-body">
															<table id="branchDetailsTable"
																class="table table-bordered table-striped">
																<thead>
																	<tr>
																		<th>Sr No.</th>
																		<th>Branch Name</th>
																		<th>Contact No</th>
																		<th>Email id</th>
																		<td>Latitude</td>
																		<th>Longitude</th>
																		<th>Branch Address</th>
																		<th>Delete</th>
																	</tr>
																</thead>
																<tbody>

																	<c:forEach var="branch" items="${branchModelList}"
																		varStatus="theCount">
																		<tr>
																			<td>${theCount.count }</td>
																			<td>${branch.branchName}</td>
																			<td>${branch.contactNo}</td>
																			<td>${branch.emailId}</td>
																			<td>${branch.latitude}</td>
																			<td>${branch.longitude}</td>
																			<td>${branch.branchAddress}</td>

																			<td class="text-center"><a href="#" "
																				onclick="" deleteBranch('${theCount.count }','${branch.branchId}');">
																					<i class="fa fa-trash-o"></i>
																			</a></td>

																		</tr>
																	</c:forEach>



																</tbody>

															</table>
														</div>
														<!-- /.box-body -->
													</div>

												</div>

											</div>
											<div class="col-lg-6 col-md-9 col-sm-9 col-xs-12"></div>

										</div>
									</div>

								</div>

								<div class="tab-pane" id="offer_toggle">



									<div class="panel panel-danger">
										<div class="panel-heading" style="background-color: #d23779;">
											<font color="white">Update New Offer Image</font>
										</div>
										<div class="panel-body">


											<div class="alert alert-danger alert-dismissable"
												id="offerImageselect">
												<button type="button" class="close" data-dismiss="alert"
													aria-hidden="true">&times;</button>
												<strong>Error: </strong>Please Select Image.
											</div>

											<div class="alert alert-success alert-dismissable"
												id="offerImgessuccess">
												<button type="button" class="close" data-dismiss="alert"
													aria-hidden="true">&times;</button>
												<strong>Message: </strong>Image Uploaded Successfully.
											</div>


											<div class="col-lg-1 col-md-3 col-sm-3 col-xs-12"></div>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label
												style="color: green;">(Note : To replace offer image
												click on Browse button)</label>
											<div class="col-lg-3 col-md-9 col-sm-9 col-xs-6">
												<form>
													<input type="file" name="b" id="offerImages"
														accept=".gif,.jpg,.jpeg,.png" class="form-control"
														style="width: auto; height: auto;" /> <br> <input
														type="button" class="btn btn-primary" "
														id="uploadOfferImagesButton" value="Save Offer Images"
														onclick="  uploadOfferImages()" " />

												</form>
											</div>
											<div class="col-lg-6 col-md-3 col-sm-3 col-xs-12">
												&nbsp;&nbsp;&nbsp;<label style="color: green;">(Note
													: Dimensions for offer images - Height 75px and Width -
													280px)</label>
											</div>

											<div class="col-lg-6 col-md-9 col-sm-9 col-xs-12"></div>

										</div>
										<script type="text/javascript">
											$("#offerImageselect").hide();
											$("#offerImgessuccess").hide();
										</script>
									</div>






								</div>


								<div class="tab-pane" id="faqs">
									<div class="panel panel-danger">
										<div class="panel-heading" style="background-color: #d23779;">
											<font color="White">Add New FAQs</font>
										</div>
										<div class="alert alert-danger alert-dismissable"
											id="questionSelect">
											<button type="button" class="close" data-dismiss="alert"
												aria-hidden="true">&times;</button>
											<strong>Error: </strong>Please Enter Question.
										</div>


										<div class="alert alert-danger alert-dismissable"
											id="answerSelect">
											<button type="button" class="close" data-dismiss="alert"
												aria-hidden="true">&times;</button>
											<strong>Error: </strong>Please Enter Answer.
										</div>

										<div class="alert alert-success alert-dismissable"
											id="faqSucccess">
											<button type="button" class="close" data-dismiss="alert"
												aria-hidden="true">&times;</button>
											<strong>Message: </strong>FAQs Added Successfully.
										</div>



										<div class="panel-body">


											<form class="form-horizontal">
												<div class="box-body">

													<div class="form-group">
														<!--  <label for="subdistName" class="col-sm-3 control-label">Sub District Name</label> -->
														<div class="row">
															<div class="col-sm-5">
																<label for="Question">Question</label>
																<!--  <input type="text" class="form-control input-sm" id="question" name="question" placeholder="Enter Branch Question"> -->
																<textarea class="form-control input-sm" id="question"
																	name="question" placeholder="Enter FAQs Question"></textarea>
															</div>
															<div class="col-sm-5">
																<label for="Answer">Answer</label>
																<!--  <input type="text" class="form-control input-sm" id="answer" name="answer" placeholder="Enter Branch Answer"> -->
																<textarea class="form-control input-sm" id="answer"
																	name="answer" placeholder="Enter FAQs Answer"></textarea>
															</div>
														</div>
														<br> <br>
														<div class="row" style="text-align: center;">
															<div class="col-sm-9">
																<button type="button" class="btn btn-primary"
																	onclick="return addFaqs();">Add FAQs</button>
															</div>
														</div>
													</div>
												</div>

											</form>

										</div>


									</div>
									<script type="text/javascript">
										$("#faqSucccess").hide();
										$("#questionSelect").hide();
										$("#answerSelect").hide();
									</script>
								</div>





								<div class="tab-pane" id="privacy_toggle">
									<div class="panel panel-danger">
										<div class="panel-heading" style="background-color: #d23779;">
											<font color="White">Privacy Policy</font>
										</div>




										<div class="alert alert-danger alert-dismissable"
											id="privacySelect">
											<button type="button" class="close" data-dismiss="alert"
												aria-hidden="true">&times;</button>
											<strong>Error: </strong>Please Enter Privacy Policy Message.
										</div>

										<div class="alert alert-success alert-dismissable"
											id="privacySuccessSelect">
											<button type="button" class="close" data-dismiss="alert"
												aria-hidden="true">&times;</button>
											<strong>Message: </strong>Privacy Policy Updated
											successfully.
										</div>


										<div class="panel-body">


											<form class="form-horizontal">
												<div class="box-body">

													<div class="form-group">
														<!--  <label for="subdistName" class="col-sm-3 control-label">Sub District Name</label> -->
														<div class="col-sm-12">
															<label for="countryNamesubDist">Enter New Privacy
																Policy</label>

															<!--  <textarea rows="5" cols="50" id="policy" name="policy" >
							
					</textarea>  -->

															<div class="privacyPolicyMassage"
																id="privacyPolicyMassage"></div>


														</div>

														<div class="col-sm-12">
															<button type="button" class="btn btn-primary btn-block"
																onclick="return savePrivacyPolicy();">Save</button>
														</div>
													</div>


												</div>


											</form>

										</div>
										<script type="text/javascript">
											$('.privacyPolicyMassage')
													.summernote(
															{
																disableDragAndDrop : false,
																height : 250,

																codemirror : {
																	theme : 'monokai'
																},
																toolbar : [
																		[
																				'style',
																				[
																						'bold',
																						'italic',
																						'underline',
																						'clear' ] ],
																		[
																				'para',
																				[
																						'ul',
																						'ol',
																						'paragraph' ] ],
																		[
																				'fontsize',
																				[ 'fontsize' ] ],
																		[
																				'color',
																				[ 'color' ] ]

																],
																//focus : true,
																onImageUpload : function(
																		files,
																		editor,
																		welEditable) {
																	sendFile(
																			files[0],
																			editor,
																			welEditable);
																}
															});

											$("#chatBoxSel").hide();
											$("#termsuccess").hide();

											$("#policy").val("").selectRange(0,
													0);
										</script>

									</div>

								</div>





								<div class="tab-pane" id="aboutus_toggle">
									<div class="panel panel-danger">
										<div class="panel-heading" style="background-color: #d23779;">
											<font color="White">About Us</font>
										</div>






										<div class="panel-body">

											<div class="alert alert-danger alert-dismissable"
												id="firstPortionSelection">
												<button type="button" class="close" data-dismiss="alert"
													aria-hidden="true">&times;</button>
												<strong>Error: </strong>Please Enter First Portion.
											</div>

											<div class="alert alert-danger alert-dismissable"
												id="secondortionSelection">
												<button type="button" class="close" data-dismiss="alert"
													aria-hidden="true">&times;</button>
												<strong>Error: </strong>Please Enter Second Portion.
											</div>

											<div class="alert alert-success alert-dismissable"
												id="aboutusSuccessSelect">
												<button type="button" class="close" data-dismiss="alert"
													aria-hidden="true">&times;</button>
												<strong>Message: </strong>About Us Updated successfully.
											</div>



											<form class="form-horizontal">
												<div class="box-body">

													<div class="form-group">
														<!--  <label for="subdistName" class="col-sm-3 control-label">Sub District Name</label> -->
														<div class="col-sm-6">
															<label for="firstPortion">Enter First Portion</label>

															<!--  <textarea rows="5" cols="50" id="firstPortion" name="firstPortion" >
							
					</textarea>  -->
															<div class="aboutUsPortionFirstMassage"
																id="aboutUsPortionFirstMassage"></div>

														</div>

														<div class="col-sm-6">
															<label for="secondPortion">Enter Second Portion</label>

															<div class="aboutUsPortionSecondMassage"
																id="aboutUsPortionSecondMassage"></div>
															<!--  <textarea rows="5" cols="50" id="secondPortion" name="secondPortion" >
					</textarea>  -->
															<script type="text/javascript">
																$(
																		"#secondPortion")
																		.val("")
																		.selectRange(
																				0,
																				0);
															</script>
														</div>
														<div align="center">
															<button type="button" class="btn btn-primary"
																onclick="return saveAboutus();"
																style="margin-left: -30px; margin-bottom: -2px; margin-top: 20px; min-height: 25px;">Save</button>
														</div>
													</div>


												</div>


											</form>

										</div>

										<script type="text/javascript">
											$('.aboutUsPortionFirstMassage')
													.summernote(
															{
																disableDragAndDrop : false,
																height : 250,

																codemirror : {
																	theme : 'monokai'
																},
																toolbar : [
																		[
																				'style',
																				[
																						'bold',
																						'italic',
																						'underline',
																						'clear' ] ],
																		[
																				'para',
																				[
																						'ul',
																						'ol',
																						'paragraph' ] ],
																		[
																				'fontsize',
																				[ 'fontsize' ] ],
																		[
																				'color',
																				[ 'color' ] ]

																],
																//focus : true,
																onImageUpload : function(
																		files,
																		editor,
																		welEditable) {
																	sendFile(
																			files[0],
																			editor,
																			welEditable);
																}
															});

											$('.aboutUsPortionSecondMassage')
													.summernote(
															{
																disableDragAndDrop : false,
																height : 250,

																codemirror : {
																	theme : 'monokai'
																},
																toolbar : [
																		[
																				'style',
																				[
																						'bold',
																						'italic',
																						'underline',
																						'clear' ] ],
																		[
																				'para',
																				[
																						'ul',
																						'ol',
																						'paragraph' ] ],
																		[
																				'fontsize',
																				[ 'fontsize' ] ],
																		[
																				'color',
																				[ 'color' ] ]

																],
																//focus : true,
																onImageUpload : function(
																		files,
																		editor,
																		welEditable) {
																	sendFile(
																			files[0],
																			editor,
																			welEditable);
																}
															});

											$("#chatBoxSel").hide();
											$("#termsuccess").hide();

											$("#firstPortionSelection").hide();
											$("#secondortionSelection").hide();
											$("#aboutusSuccessSelect").hide();

											$("#firstPortion").val("")
													.selectRange(0, 0);
											$("#secondPortion").val("")
													.selectRange(0, 0);
										</script>


									</div>



								</div>






								<div class="tab-pane" id="term_toggle">
									<div class="panel panel-danger">
										<div class="panel-heading" style="background-color: #d23779;">
											<font color="White">Terms And Condition</font>
										</div>

										<div class="panel-body">
											<form class="form-horizontal">
												<div class="box-body">




													<div class="alert alert-danger alert-dismissable"
														id="chatBoxSel">
														<button type="button" class="close" data-dismiss="alert"
															aria-hidden="true">&times;</button>
														<strong>Error: </strong>Please Enter Term And Condition.
													</div>

													<div class="alert alert-success alert-dismissable"
														id="termsuccess">
														<button type="button" class="close" data-dismiss="alert"
															aria-hidden="true">&times;</button>
														<strong>Message: </strong>Term And Condition Updated
														Successfully.
													</div>

													<div class="termsAndCondition" id="termsAndCondition"></div>


													<button type="button"
														class="  btn btn-primary btn-block  tooltip1 " "
														onclick="saveTermAndCondition();" id="submitThumb">Submit</button>

												</div>
											</form>

										</div>

										<script type="text/javascript">
											/* 	  $('.feedcomMessage').summernote({
											 disableDragAndDrop : false,
											 height : 250,

											 codemirror : {
											 theme : 'monokai'
											 },
											 toolbar : [
											 ['style', ['bold', 'italic', 'underline', 'clear']],
											 ['para', ['ul', 'ol', 'paragraph']],
											 ['fontsize', ['fontsize']],
											 ['color', ['color']]
											
											

											 ],
											 //focus : true,
											 onImageUpload : function(files, editor, welEditable) {
											 sendFile(files[0], editor, welEditable);
											 }
											 }); */

											$('.termsAndCondition')
													.summernote(
															{
																disableDragAndDrop : false,
																height : 250,

																codemirror : {
																	theme : 'monokai'
																},
																toolbar : [
																		[
																				'style',
																				[
																						'bold',
																						'italic',
																						'underline',
																						'clear' ] ],
																		[
																				'para',
																				[
																						'ul',
																						'ol',
																						'paragraph' ] ],
																		[
																				'fontsize',
																				[ 'fontsize' ] ],
																		[
																				'color',
																				[ 'color' ] ]

																],
																//focus : true,
																onImageUpload : function(
																		files,
																		editor,
																		welEditable) {
																	sendFile(
																			files[0],
																			editor,
																			welEditable);
																}
															});

											/* $('#feedcomMessage').css({
												'height' : '200px',
												'text-align' : 'left'
											}).ace_wysiwyg(

													{
														toolbar_place : function(toolbar) {
															return $(this).closest('.widget-box')
																	.find('.widget-header').prepend(toolbar).find(
																			'.wysiwyg-toolbar').addClass('inline');
														},
														toolbar:
															[
																'font',
																null,
																{name:'bold', className:'btn-info'},
																{name:'italic', className:'btn-info'},
																{name:'strikethrough', className:'btn-info'},
																{name:'underline', className:'btn-info'},
																null,
																{name:'insertunorderedlist', className:'btn-success'},
																{name:'insertorderedlist', className:'btn-success'},
																{name:'outdent', className:'btn-purple'},
																{name:'indent', className:'btn-purple'},
																null,
																{name:'justifyleft', className:'btn-primary'},
																{name:'justifycenter', className:'btn-primary'},
																{name:'justifyright', className:'btn-primary'},
																{name:'justifyfull', className:'btn-inverse'},
																null,
																'foreColor',
																null,
																{name:'undo', className:'btn-grey'},
																{name:'redo', className:'btn-grey'}
															],
														speech_button : false
													});
											 */

											$("#chatBoxSel").hide();
											$("#termsuccess").hide();
										</script>
									</div>
								</div>



								<div class="tab-pane" id="changePass_toggle">
									<div class="alert alert-danger alert-dismissable"
										id="currentPasswordAlert">
										<button type="button" class="close" data-dismiss="alert"
											aria-hidden="true">&times;</button>
										<strong>Error: </strong>Please Enter Current Password
									</div>

									<div class="alert alert-danger alert-dismissable"
										id="currentPassMatchAlert">
										<button type="button" class="close" data-dismiss="alert"
											aria-hidden="true">&times;</button>
										<strong>Error: </strong>Current Password Is Not Match
									</div>
									<div class="alert alert-danger alert-dismissable"
										id="newPassAlert">
										<button type="button" class="close" data-dismiss="alert"
											aria-hidden="true">&times;</button>
										<strong>Error: </strong>Please Enter New Password
									</div>
									<div class="alert alert-danger alert-dismissable"
										id="confirmPassAlert">
										<button type="button" class="close" data-dismiss="alert"
											aria-hidden="true">&times;</button>
										<strong>Error: </strong>Please Enter Confirm Password
									</div>

									<div class="alert alert-danger alert-dismissable"
										id="newPassMatchAlert">
										<button type="button" class="close" data-dismiss="alert"
											aria-hidden="true">&times;</button>
										<strong>Error: </strong>Password and Confirm Password Dose not
										Match
									</div>
									<div class="alert alert-danger alert-dismissable"
										id="currentUserNameAlert">
										<button type="button" class="close" data-dismiss="alert"
											aria-hidden="true">&times;</button>
										<strong>Error: </strong>Enter Current UserName
									</div>
									<div class="alert alert-danger alert-dismissable"
										id="newUserNameAlert">
										<button type="button" class="close" data-dismiss="alert"
											aria-hidden="true">&times;</button>
										<strong>Error: </strong>Enter New UserName
									</div>
									<div class="alert alert-danger alert-dismissable"
										id="newUserNameMatchAlert">
										<button type="button" class="close" data-dismiss="alert"
											aria-hidden="true">&times;</button>
										<strong>Error: </strong>Current UserName and New UserName Does
										not Match
									</div>
									<div class="alert alert-success alert-dismissable"
										id="changePassSuccessAlert">
										<button type="button" class="close" data-dismiss="alert"
											aria-hidden="true">&times;</button>
										<strong>Error: </strong>UserName Password Change Successfully
									</div>

									<div class="panel panel-danger">
										<div class="panel-heading" style="background-color: #d23779;">
											<font color="white">Change Password</font>
										</div>
										<div class="panel-body">
											<div class="form-group">
												<div class="row">
													<div class="col-sm-3">
														<label>Enter Current UserName<sup><font
																color="red" size="2px">*</font></sup></label>

													</div>
													<div class="col-sm-3">
														<input type="text" class="form-control input-sm"
															id="currentUserName" name="currentUserName"
															placeholder="Current UserName">
													</div>
												</div>
												<br>
												<div class="row">
													<div class="col-sm-3">
														<label>Enter New UserName<sup><font
																color="red" size="2px">*</font></sup></label>

													</div>
													<div class="col-sm-3">
														<input type="text" class="form-control input-sm"
															id="newUserName" name="newUserName"
															placeholder="New UserName">
													</div>
												</div>
												<br>
												<div class="row">
													<div class="col-sm-3">
														<label>Enter Current Password<sup><font
																color="red" size="2px">*</font></sup></label>

													</div>
													<div class="col-sm-3">
														<input type="password" class="form-control input-sm"
															id="currentPass" name="currentPass"
															placeholder="Current Password">
													</div>
												</div>
												<br>
												<div class="row">
													<div class="col-sm-3">
														<label>Enter New Password <font color="red"
															size="2px">*</font></sup></label>

													</div>
													<div class="col-sm-3">
														<input type="password" class="form-control input-sm"
															id="newPass" name="newPass" placeholder="New Password">
													</div>
												</div>
												<br>
												<div class="row">
													<div class="col-sm-3">
														<label>Enter Confirm Password <font color="red"
															size="2px">*</font></sup></label>
													</div>

													<div class="col-sm-3">
														<input type="password" class="form-control input-sm"
															id="confirmPass" name="confirmPass"
															placeholder="Confirm Password">
													</div>
												</div>
												<br>
												<div class="row">
													<div class="col-sm-3"></div>

													<div class="col-sm-3">
														<button type="button" class="btn btn-primary"
															onclick="changePassword()">Change Password</button>
														<input type="hidden" id="currentAdminUserName"
															name="currentAdminUserName"
															value="${currentAdminUserName}"> <input
															type="hidden" id="currentAdminPass"
															name="currentAdminPass" value="${currentAdminPassword}">
													</div>

												</div>

												<div class="col-lg-6 col-md-9 col-sm-9 col-xs-12"></div>
											</div>

										</div>
									</div>


									<!-- /.tab-pane -->
								</div>







							</div>


							<!-- /.tab-pane -->
						</div>







						<!-- /.tab-content -->
					</div>
					<!-- /.nav-tabs-custom -->
				</div>
				<!-- /.col -->



				<div id="light" class=""
					style="position: fixed; float: left; width: 40%; padding: 0px; margin: 0px; top: 30%; left: 50%; overflow: hidden; display: none;">

					<input type="hidden" id="yearValue">

					<div class="meetingContent">


						<img src="${pageContext.request.contextPath}/resources/images/spinner.gif" class="logo" style="width: 150px">

					</div>
					Loading
				</div>
				<div id="fade" class="black_overlay"></div>
		</div>
		<!-- /.row -->

		</section>
		<!-- /.content -->
	</div>

	<div class="control-sidebar-bg"></div>
	</div>


	<!-- jQuery 2.2.3 -->
	<!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip -->
	<script>
		$.widget.bridge('uibutton', $.ui.button);
	</script>





	<script type="text/javascript">
		$("#logoSelect").hide();
		$("#logosuccess").hide();
		$("#logoSizeSelect").hide();

		$("#successImageselect").hide();
		$("#successImgessuccess").hide();
		$("#selectFirstSuccessStoryPortionAlert").hide();
		$("#selectSecondSuccessStoryPortionAlert").hide();
		$("#selectThirdSuccessStoryPortionAlert").hide();
		$("#selectFourSuccessStoryPortionAlert").hide();
		$("#storySelect").hide();
		$("#storySuccessSelect").hide();

		$("#countryNamesubdistSelect").hide();
		$("#stateNamesubDistselect").hide();
		$("#distNamesubDistSelect").hide();
		$("#subDistNameSelect").hide();
		$("#subDistAvailable").hide();
		$("#subDistNameSelectssuccess").hide();

		$("#addBranchName").hide();
		$("#addLat").hide();
		$("#addLong").hide();
		$("#addAddress").hide();
		$("#branchSuccess").hide();
		$("#emailRequired").hide();
		$("#branchDeleted").hide();

		$("#privacySelect").hide();
		$("#privacySuccessSelect").hide();

		$("#currentPasswordAlert").hide();
		$("#currentPassMatchAlert").hide();
		$("#newPassAlert").hide();
		$("#confirmPassAlert").hide();
		$("#newPassMatchAlert").hide();
		$("#changePassSuccessAlert").hide();
		$("#newUserNameMatchAlert").hide();
		$("#currentUserNameAlert").hide();
		$("#newUserNameAlert").hide();
		$("#selectUserOptionAlert").hide();
		$("#enterTextMessageAlert").hide();
	</script>
</body>
</html>







