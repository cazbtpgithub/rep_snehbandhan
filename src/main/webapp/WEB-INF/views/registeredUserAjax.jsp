                                       <table id="example2"
											class="table table-bordered table-striped">
											<thead>
												<tr>
													<th>Id</th>
													<th>Name</th>
													<th>Gender</th>
													<th>Mobile</th>
													<th>Registration Date</th>
													<th>DOB</th>
													<th>User Type</th>
													<!-- <th>Dealer Name</th> -->
													<th>View</th>
													<th>Delete</th>

												</tr>
											</thead>
											<tbody>

											</tbody>

										</table>
										
										<div id="productListingPaginationID" style="font-size: 30px;"></div>
										
										<script>
										var data = eval('${userModelDetailList}');
										
										var table = $('#example2')
												.DataTable(
														{
															"aaData" : data,
															"pagingType" : "full_numbers",
															 "order": [[ 0, 'desc' ]],
															"aoColumns" : [
																	{
																		"data" : "userId"
																	},
																	{
																		"data" : "firstName"    
																	},
																	{
																		"data" : "gender"    
																	},
																	{
																		"data" : "mobileNum"    
																	},
																	{
																		"data" : "registrationDate",
																		"render" : function(data) {
																			var date = new Date(data);
																			var month = date.getMonth() + 1;
																			return (date.getDate())+"-"+month+ "-" + date.getFullYear();
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
																		"data" : "kit",
																		"render" : function(data) {
																			if(data === undefined || data == null || data.length <= 0)
																				{
																				
																				   
																				   return  "<label style='color:red;'>free</label>";
																				}
																			else
																				{
																				return  "<label style='color:green;'>Paid</label>";
																				}
																			
																		}
																	},
																	
																	{
																		"data" : null,
																		"defaultContent" : "<button onclick=fun1(this)>View</button>"
																	},
																	{
																		"data" : null,
																		"defaultContent" : "<button onclick=funDelete1(this)>Delete</button>"
																	}]
														});
										
										function fun1(aa) {
											//alert($(aa).closest('td').prev().prev().prev().prev().prev().prev().prev().text());

											var userId=$(aa).closest('td').prev().prev().prev().prev().prev().prev().prev().text();
											
											// Here i am directlly passing the userModel id fot getting the userMOdel details
											$("#userId").val(userId);
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
													
													$('input[name=bodyTypePartner][value="'+cntForiterateobj.bodyType+'"]')
													.attr('checked', true);
													
													$('input[name=complexionPartner][value="'+cntForiterateobj.complexion+'"]') 
													.attr('checked', true);
													
													
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
																"<img src="resources/UserImages/userImage.png" src=resources/UserImages/"+myarray[i]+" onerror=\"this.onerror=null; this. onerror="this.onerror=null; this.src='resources/UserImages/userImage.png';">"+
																 "<li ><input type=button onclick=removeImageByAdmin('"+myarray[i]+"',this,"+userid+");" +
																 "btn btn-primary value=Remove></li>&nbsp;&nbsp;&nbsp;&nbsp;"+
																"</li>&nbsp;&nbsp;&nbsp;&nbsp;"+
																"</ul>";
																
																 $("#imgDiv").append(imgList);
															}
														
															/* End of remove image section */
														// addimgDiv
														
														/* Add new Image section */
														
														
														var addImg ="<ul style="list-style: none;;"> <br>;"+
															"<li ><br>"+
															"<input type=file class=form-control style="width: auto; height: auto; accept=.gif,.jpg,.jpeg,.png;"+
															"name=loader11 id=loader11 multiple=multiple onchange=model();><br>"+
															"</li><li>"+	
															"<input type=button  onclick=saveImagesByAdmin("+userid+"); data-toggle=modal data-target=#userlogin "+
															"class=btn btn-primary value=save Image>"+
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
															data : "id=" + cntForiterateobj.religion.religion_id,
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
												
										            var userEmailId = cntForiterateobj1.email;

															if (userEmailId == '-') 
															{
																$("#email").val("-");
															} else
															{
																var spliteEmailid = userEmailId.substring(0, 3);
																if (spliteEmailid == 'abc') 
																{
																	$("#email").val("-");
																} else 
																{
																	$("#email").val(cntForiterateobj1.email);
																}

															}

															//$("#email").val(cntForiterateobj1.email);  

															// 18-04-2017 code update user Details By Admin

															$('[name=ProfileCreatedFor] option')
																	.filter(
																			function() {
																				return ($(this).text() == cntForiterateobj1.profileCreatedFor);
																			}).prop('selected', true);

															$(
																	'input[name=gender][value="'
																			+ cntForiterateobj1.gender + '"]')
																	.attr('checked', true);

															var regex = /<br\s*[\/]?>/gi;
															$("#aboutMe").val(
																	cntForiterateobj1.aboutMe.replace(regex,
																			"\n"));
															$('[name=materialStatus] option')
																	.filter(
																			function() {
																				return ($(this).val() == cntForiterateobj1.materialStatus);
																			}).prop('selected', true);

															$('[name=physicalStatus] option')
																	.filter(
																			function() {
																				return ($(this).text() == cntForiterateobj1.physicalStatus);
																			}).prop('selected', true);

															$(
																	'input[name=bodyType][value="'
																			+ cntForiterateobj1.bodyType + '"]')
																	.attr('checked', true);

															$(
																	'input[name=complexion][value="'
																			+ cntForiterateobj1.complexion
																			+ '"]').attr('checked', true);

															$('[name=heightSelect] option')
																	.filter(
																			function() {
																				return ($(this).val() == cntForiterateobj1.height);
																			}).prop('selected', true);

															$('[name=weightSelect] option')
																	.filter(
																			function() {
																				return ($(this).val() == cntForiterateobj1.weight);
																			}).prop('selected', true);

															$('[name=stateSelect] option')
																	.filter(
																			function() {
																				return ($(this).val() == cntForiterateobj1.state_Country.state_Country_id);
																			}).prop('selected', true);

															// load and set districty
															$
																	.ajax({
																		dataType : 'json',
																		url : "educationOccupation/loadCityAccordingToState",
																		type : "GET",
																		data : "id=""
																				+ cntForiterateobj1.state_Country.state_Country_id,
																		success : function(data) {

																			$("#cityStateSelect option")
																					.remove();
																			$("#cityStateSelect")
																					.append(
																							'<option value=0>Select District</option>');
																			for (var i = 0; i < data.length; i++) {
																				$("#cityStateSelect")
																						.append(
																								'<option value=" + data[i].state_district_id +" '>'
																										+ data[i].district_Name
																										+ '</option>');
																			}

																			$('[name=cityStateSelect] option')
																					.filter(
																							function() {
																								return ($(this)
																										.val() == cntForiterateobj1.districts.state_district_id);
																							}).prop('selected',
																							true);

																		},
																		error : function(xhr, ajaxOptions,
																				thrownError) {
																			alert("err" + thrownError
																					+ xhr.responseText);
																		}
																	});

															// load and set  sub districty
															$
																	.ajax({
																		dataType : 'json',
																		url : "educationOccupation/loadsubDistrictAccordingToDistrict",
																		type : "GET",
																		data : "id=""
																				+ cntForiterateobj1.districts.state_district_id,
																		success : function(data) {

																			$("#subDistSelect option").remove();
																			$("#subDistSelect")
																					.append(
																							'<option value=0>Select Sub District</option>');
																			for (var i = 0; i < data.length; i++) {
																				$("#subDistSelect")
																						.append(
																								'<option value=""
																						+ data[i].district_subDistricts_id
																						+ '>'
																										+ data[i].subdistricts_Name
																										+ '</option>');
																			}

																			$('[name=subDistSelect] option')
																					.filter(
																							function() {
																								return ($(this)
																										.val() == cntForiterateobj1.subDistricts.district_subDistricts_id);
																							}).prop('selected',
																							true);

																		},
																		error : function(xhr, ajaxOptions,
																				thrownError) {
																			alert("err" + thrownError
																					+ xhr.responseText);
																		}
																	});

															$('[name=religionSelect] option')
																	.filter(
																			function() {
																				return ($(this).val() == cntForiterateobj1.caste.religion_id.religion_id);
																			}).prop('selected', true);

															// load and assign caste

															$
																	.ajax({
																		dataType : 'json',
																		url : "educationOccupation/loadCasteAccordingToReligion",
																		type : "GET",
																		data : "id=""
																				+ cntForiterateobj1.caste.religion_id.religion_id,
																		success : function(data) {

																			$("#casteSelect option").remove();
																			$("#casteSelect")
																					.append(
																							'<option value=0>Select Caste</option>');
																			for (var i = 0; i < data.length; i++) {
																				$("#casteSelect")
																						.append(
																								'<option value=" + data[i].caste_id +" '>'
																										+ data[i].caste_name
																										+ '</option>');
																			}

																			$('[name=casteSelect] option')
																					.filter(
																							function() {
																								return ($(this)
																										.val() == cntForiterateobj1.caste.caste_id);
																							}).prop('selected',
																							true);

																		},
																		error : function(xhr, ajaxOptions,
																				thrownError) {
																			alert("err" + thrownError
																					+ xhr.responseText);
																		}
																	});

															$('[name= educationalCategorySelect] option')
																	.filter(
																			function() {
																				return ($(this).val() == cntForiterateobj1.education.edu_cat_id.edu_cat_id);
																			}).prop('selected', true);

															$
																	.ajax({
																		dataType : 'json',
																		url : "educationOccupation/loadEducationAccordingToEduCategory",
																		type : "GET",
																		data : "id=""
																				+ cntForiterateobj1.education.edu_cat_id.edu_cat_id,
																		success : function(data) {
																			$("#educationalSelect").prop(
																					'disabled', false);
																			$("#educationalSelect option")
																					.remove();
																			$("#educationalSelect")
																					.append(
																							'<option value=0>Select Education</option>');
																			for (var i = 0; i < data.length; i++) {
																				$("#educationalSelect")
																						.append(
																								'<option value=" + data[i].education_id +" '>'
																										+ data[i].education_name
																										+ '</option>');
																			}

																			$('[name=educationalSelect] option')
																					.filter(
																							function() {
																								return ($(this)
																										.val() == cntForiterateobj1.education.education_id);
																							}).prop('selected',
																							true);

																		},
																		error : function(xhr, ajaxOptions,
																				thrownError) {
																			alert("err" + thrownError
																					+ xhr.responseText);
																		}
																	});

															$('[name= occupationCategorySelect] option')
																	.filter(
																			function() {
																				return ($(this).val() == cntForiterateobj1.occupation.occ_cat_id.occu_id);
																			}).prop('selected', true);

															$
																	.ajax({
																		dataType : 'json',
																		url : "educationOccupation/loadOccupationAccordingToOccuCategory",
																		type : "GET",
																		data : "id=""
																				+ cntForiterateobj1.occupation.occ_cat_id.occu_id,
																		success : function(data) {
																			$("#occupationSelect").prop(
																					'disabled', false);

																			$("#occupationSelect option")
																					.remove();
																			$("#occupationSelect")
																					.append(
																							'<option value=0>Select Occupation</option>');
																			for (var i = 0; i < data.length; i++) {
																				$("#occupationSelect")
																						.append(
																								'<option value=" + data[i].occupation_id +" '>'
																										+ data[i].occupation_name
																										+ '</option>');
																			}

																			$('[name= occupationSelect] option')
																					.filter(
																							function() {
																								return ($(this)
																										.val() == cntForiterateobj1.occupation.occupation_id);
																							}).prop('selected',
																							true);
																		},
																		error : function(xhr, ajaxOptions,
																				thrownError) {
																			alert("err" + thrownError
																					+ xhr.responseText);
																		}
																	});

															try {
																$(
																		'input[name=employedIn][value="'
																				+ cntForiterateobj1.employeedIn
																				+ '"]').attr('checked', true);

															} catch (e) {
															}
															try {
																$(
																		'input[name=income][value="'
																				+ cntForiterateobj1.incomeType
																				+ '"]').attr('checked', true);

															} catch (e) {
															}

															try {
																$("#incomeAmount").val(
																		cntForiterateobj1.incomeAmount);
															} catch (e) {
															}
															try {

																$(
																		'input[name=familyClassInfo][value="'
																				+ cntForiterateobj1.familyClass
																				+ '"]').attr('checked', true);
															} catch (e) {
															}
															try {
																$(
																		'input[name=familyTypeInfo][value="'
																				+ cntForiterateobj1.familyType
																				+ '"]').attr('checked', true);

															} catch (e) {
															}
															try {

																$(
																		'input[name=familyValueInfo][value="'
																				+ cntForiterateobj1.familyValues
																				+ '"]').attr('checked', true);
															} catch (e) {
																// TODO: handle exception
															}

															try {
																$(
																		'input[name=eating][value="'
																				+ cntForiterateobj1.eating
																				+ '"]').attr('checked', true);

															} catch (e) {
																// TODO: handle exception
															}

															try {
																$(
																		'input[name=drink][value="'
																				+ cntForiterateobj1.drink
																				+ '"]').attr('checked', true);

															} catch (e) {
															}

															try {
																$(
																		'input[name=smoke][value="'
																				+ cntForiterateobj1.smoke
																				+ '"]').attr('checked', true);
															} catch (e) {
															}

															//$("#userMstatus").val(cntForiterateobj1.materialStatus);
															// End of 18-04 code

															$("#hiddenUsermodelId").val(cntForiterateobj1.userDetailsId);
															$("#mobile").val(cntForiterateobj1.mobileNum);
															$("#editUserMobileNoHidden").val(cntForiterateobj1.mobileNum);
															var time = new Date(cntForiterateobj1.dateOfBirth);
															
															
															
															var year = time.getFullYear();
															var month = time.getMonth() + 1;
															var today = time.getDate();
															if (today < 10) {

																today = '0' + today;
															}
															if (month < 10) {
																month = '0' + month;
															}
															var fullYear = today + "/" + month + "/" + year;
															$("#dob").val(fullYear);
															
															var userRegistrationDate = new Date(cntForiterateobj1.registrationDate);
															var userRegistrationDay =userRegistrationDate.getDate();
															var userRegistrationMonth = userRegistrationDate.getMonth()+ 1;
															var userRegistrationYear = userRegistrationDate.getFullYear();
															
															var registrationDay = null;
															var registrationMonth = null;
															if(userRegistrationMonth < 10){
																registrationMonth = "0"+userRegistrationMonth;
															}else{
																registrationMonth = userRegistrationMonth;
															}
															if(userRegistrationDay < 10){
																registrationDay = "0"+userRegistrationDay;
															}else{
																registrationDay = userRegistrationDay;
															}
															var editUserRegistrationDate = userRegistrationYear+"-"+registrationMonth+"-"+registrationDay+" 00:00:00";							
															$("#editUserRegistrationDateHidden").val(editUserRegistrationDate);
															
															var userLandMark = cntForiterateobj1.landmark;	
															$("#userLandMark").val(userLandMark);
															/* var cm=cntForiterateobj1.height;
															var realFeet=cm/30.48;
															var feet = Math.floor(realFeet);
															var inches = Math.round((realFeet - feet) * 12);
															
															$("#userHeight").val(feet+" Ft  "+inches+"  inc"); */

															$("#userFamilyType").val(
																	cntForiterateobj1.familyType);
															$("#userReligion").val(
																	cntForiterateobj.religion.religion_name);
															/* 					$("#userCast").val(cntForiterateobj1.caste.caste_name);
															 //					alert("Email id=="+cntForiterateobj1.email);
															
															 var userEmailId =cntForiterateobj1.email;
															 //				alert("User Email Id**********========="+userEmailId);
															
															 var spliteEmailid = userEmailId.substring(0,3);
															 alert("Spite email String ="+spliteEmailid);
															 if(spliteEmailid =='abc')
															 {
															 alert("Auto Part");
															 $("#userEmailId").val("-");  
															 }
															 else
															 {
															 alert("Elase Part");
															 } */
															$("#userEmailId").val(cntForiterateobj1.email);

															$("#userMobile").val(cntForiterateobj1.mobileNum);
															$("#userMobile").val(cntForiterateobj1.mobileNum);

															var img = jsonData.userImage.replace(/"/g, "");
															
															
															$('#userProfilePicture').attr('src','resources/UserImages/' + img).on('error', function() { $(this).attr('src', 'resources/UserImages/userImage.png'); });
															
															var kundaliImageArray=jsonData.kundaliImage;
															var userKundaliImage = JSON.parse(kundaliImageArray);
															var kundaliNameString=""+userKundaliImage+"";
															var userKundaliImage = kundaliNameString.split(',');

															
															$('#userKundaliPicture').attr('src','resources/UserImages/' + userKundaliImage);
															
														/* 	var kundaliImg = jsonData.userImage.replace(/"/g, ""); */


															// Settting kit details   
															try {

																$("#kitIdd")
																		.val(
																				cntForiterateobj1.userModel.kit.kit_username);
																$("#kitpasswordd")
																		.val(
																				cntForiterateobj1.userModel.kit.kit_password);
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
										
										
										function funDelete1(cc) {
											var userId = $(cc).closest('td').prev().prev().prev().prev().prev()
													.prev().prev().prev().text();

											$.confirm({
												title : '',
												content : 'Do You Want To Delete ?',
												buttons : {
													confirm : function() {

														$.ajax({
															dataType : 'Text',
															url : "deleteProfileByAdmin",
															type : "POST",
															data : "userId=" + userId,
															success : function(data) {

																var p = cc.parentNode.parentNode.rowIndex;
																document.getElementById("example2")
																		.deleteRow(p);

															},
															error : function(xhr, ajaxOptions, thrownError) {
																alert("err" + thrownError + xhr.responseText);
															}
														});

													},
													cancel : function() {

													}

												}
											});
										}
										
										
										
										</script>