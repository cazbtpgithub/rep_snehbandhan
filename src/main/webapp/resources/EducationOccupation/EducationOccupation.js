function SaveRegistrationDetails() {
	
	var ProfileCreatedFor =$('#ProfileCreatedFor option:selected')
	.val();
	var materialStatusPartner = $('#materialStatusPartner option:selected').val();
	var physicalStatusPartner = $('#physicalStatusPartner option:selected').val();
	var bodyTypePartner = $("input[name=bodyTypePartner]:checked").val();
	var complexionPartner = $("input[name=complexionPartner]:checked").val();

	var heightSelectPartnerFirst = $('#heightSelectPartnerFirst option:selected').val();
	var heightSelectPartnerSecond = $('#heightSelectPartnerSecond option:selected').val();
	var countrySelectPartner = $('#countrySelectPartner option:selected').val();
	var stateSelectPartner = $('#stateSelectPartner option:selected').val();
	var cityStateSelectPartnerPreferance = $('#cityStateSelectPartnerPreferance option:selected').val();

	var religionSelectPartner = $('#religionSelectPartner option:selected').val();
	var casteSelectPartner = $('#casteSelectPartner option:selected').val();

	var checkBoxEducationPartner = [];
	$("input:checkbox[name=checkBoxEducationPartner]:checked").each(function() {
		checkBoxEducationPartner.push($(this).val());
	});
	var checkBoxOccupationPartner = [];
	$("input:checkbox[name=checkBoxOccupationPartner]:checked").each(
			function() {
				checkBoxOccupationPartner.push($(this).val());
			});

	var incomePartner = $("input[name=incomePartner]:checked").val();

	var incomeAmountPartner = $("#incomeAmountPartner").val();

	var familyClassInfoPartner = $("input[name=familyClassInfoPartner]:checked").val();
	var familyTypeInfoPartner = $("input[name=familyTypeInfoPartner]:checked").val();
	var familyValueInfoPartner = $("input[name=familyValueInfoPartner]:checked").val();
	var eatingPartner = $("input[name=eatingPartner]:checked").val();
	var drinkPartner = $("input[name=drinkPartner]:checked").val();
	var smokePartner = $("input[name=smokePartner]:checked").val();
    var kundaliImg="No";
	
	  if (materialStatusPartner == 0) {
	  $("#DivMaritalPartnerselect").slideDown();
	  $("#DivMaritalPartnerselect").fadeOut(15000);
	  $('#DivMaritalPartnerselect').focus(); 
	  return false; } if
	  (physicalStatusPartner == 0) {
	  $("#DivPhysicalPartnerselect").slideDown();
	  $("#DivPhysicalPartnerselect").fadeOut(15000);
	  $('#DivPhysicalPartnerselect').focus(); return false; 
	  }
	  if
	  (heightSelectPartnerFirst == 0) {
	  $("#DivHeightPartnerselect").slideDown();
	  $("#DivHeightPartnerselect").fadeOut(15000);
	  $('#DivHeightPartnerselect').focus(); return false; } if
	  (heightSelectPartnerSecond == 0) {
	  $("#DivHeightPartnerselect").slideDown();
	  $("#DivHeightPartnerselect").fadeOut(15000);
	  $('#DivHeightPartnerselect').focus();
	  return false; } 
	  if(countrySelectPartner == 0) { $("#DivCountryPartnerselect").slideDown();
	  $("#DivCountryPartnerselect").fadeOut(15000);
	  $('#DivCountryPartnerselect').focus(); return false;
	  
	  } if
	  (stateSelectPartner == 0) { $("#DivStatePartnerselect").slideDown();
	  $("#DivStatePartnerselect").fadeOut(15000);
	  $('#DivStatePartnerselect').focus(); return false;
	  
	  } if
	  (religionSelectPartner == 0) {
	  $("#DivReligionPartnerselect").slideDown();
	  $("#DivReligionPartnerselect").fadeOut(15000);
	  $('#DivReligionPartnerselect').focus();
	  $('html, body').animate({ scrollTop: $('#DivReligionPartnerselect').offset().top }, 'slow');
	  return false; 
	  
	  } 
	  if(casteSelectPartner == 0) { $("#DivCastePartnerselect").slideDown();
	  $("#DivCastePartnerselect").fadeOut(15000);
	  $('#DivCastePartnerselect').focus(); 
	  $('html, body').animate({ scrollTop: $('#DivCastePartnerselect').offset().top }, 'slow');
	  return false; 
	  }
	  
	  if (cityStateSelectPartnerPreferance == 0) {
	  $("#DivDistrictPartnerselect").slideDown();
	  $("#DivDistrictPartnerselect").fadeOut(15000);
	  $('#DivDistrictPartnerselect').focus();
	  $('html, body').animate({ scrollTop: $('#DivDistrictPartnerselect').offset().top }, 'slow');
	  return false; 
	  
	  }
	  
	  if (checkBoxEducationPartner.length == 0) {
	  $("#DivEducationPartnerselect").slideDown();
	  $("#DivEducationPartnerselect").fadeOut(15000);
	  $('#DivEducationPartnerselect').focus();
	  $('html, body').animate({ scrollTop: $('#DivEducationPartnerselect').offset().top }, 'slow');
	  return false; } if
	  (checkBoxOccupationPartner.length == 0) {
	  $("#DivOccupationPartnerselect").slideDown();
	  $("#DivOccupationPartnerselect").fadeOut(15000);
	  $('#DivOccupationPartnerselect').focus(); 
	  $('html, body').animate({ scrollTop: $('#DivOccupationPartnerselect').offset().top }, 'slow');
	  return false; }
	 
	    var firstName = $("#firstName").val();
		var gender= $("input[name=gender]:checked").val();
		var mobile = $("#mobile").val();
		var dob = $("#dob").val();
		var userName = $("#userName").val();
		var email = $("#email").val();
		if (email == null || email.trim() == "") {
			email = "abc" + new Date().getTime() + "@gmail.com";
		}
		
		
		var employedInPartner=$("input[name=employedInPartner]:checked").val();
		var employedIn = $("input[name=employedIn]:checked").val();
		var password = $("#password").val();
		
	
		var oMyForm = new FormData();
		var j=0;
		var x = "";
		files = [];
		var inp = document.getElementById('uploadImage');
		if (inp && inp.files) {
			for (var i = 0; i < inp.files.length; ++i) {
				var name = inp.files.item(i).name;
				files[i] = inp.files.item(i).name;
				oMyForm.append("file" + i, uploadImage.files[i]);
				j=i;
			}
		}
		
		files = [];
		var inp1 = document.getElementById('loaderKundali');
		if (inp1 && inp1.files) {
			for (var i = 0; i < inp1.files.length; ++i) {
				kundaliImg="Yes";
				var name = inp1.files.item(i).name;
				files[i] = inp1.files.item(i).name;
				oMyForm.append("file1" + i, loaderKundali.files[i]);
			}
		}
	$("#secondModal").modal();
    
	var aboutMe = $("#aboutMe").val().replace(/\n/g, "<br>");
	var materialStatus = $('#materialStatus option:selected').val();
	
	var physicalStatus = $('#physicalStatus option:selected').val();
	var heightSelect = $('#heightSelect option:selected').val();
	var weightSelect = $('#weightSelect option:selected').val();
	var countrySelect = $('#countrySelect option:selected').val();
	var stateSelect = $('#stateSelect option:selected').val();
	var bodyType = $("input[name=bodyType]:checked").val();
	var complexion = $("input[name=complexion]:checked").val();
	var cityStateSelect = $('#cityStateSelect option:selected').val();
	var subDistSelect = $('#subDistSelect option:selected').val();
	var religionSelect = $('#religionSelect option:selected').val();
	var casteSelect = $('#casteSelect option:selected').val();
	var educationalCategorySelect = $('#educationalCategorySelect option:selected').val();

	var educationalSelect = $('#educationalSelect option:selected').val();
	var occupationCategorySelect = $('#occupationCategorySelect option:selected').val();
	var occupationSelect = $('#occupationSelect option:selected').val();
	var income = $("input[name=income]:checked").val();
	var incomeAmount = $("#incomeAmount").val();
	var familyClassInfo = $("input[name=familyClassInfo]:checked").val();
	var familyTypeInfo = $("input[name=familyTypeInfo]:checked").val();
	var familyValueInfo = $("input[name=familyValueInfo]:checked").val();
	var eating = $("input[name=eating]:checked").val();
	var drink = $("input[name=drink]:checked").val();
	var smoke = $("input[name=smoke]:checked").val();
	
	
	var kitUser = $("input[name=kitUser]:checked").val();
	var kitId = $("#kitId").val();
	var kitPassword = $("#kitPassword").val();
	var ladmark  = $("#ladmark").val();
	
	
	$.ajax({
		dataType : 'text',
		url : "registrationLogin",
		type : "POST",
		data : {aboutMe : aboutMe,
		materialStatus : materialStatus,
		physicalStatus : physicalStatus,
		heightSelect : heightSelect,
		weightSelect : weightSelect,
		countrySelect : countrySelect,
		stateSelect : stateSelect,
		bodyType : bodyType,
		complexion : complexion,
		cityStateSelect : cityStateSelect,
		subDistSelect : subDistSelect,
		religionSelect : religionSelect,
		casteSelect : casteSelect,
		educationalCategorySelect : educationalCategorySelect,
		educationalSelect : educationalSelect,
		occupationCategorySelect : occupationCategorySelect,
		occupationSelect : occupationSelect,
		income : income,
		incomeAmount : incomeAmount,
		familyClassInfo : familyClassInfo,
		familyTypeInfo : familyTypeInfo,
		familyValueInfo : familyValueInfo,
		eating : eating,
		drink : drink,
		smoke : smoke,
		materialStatusPartner : materialStatusPartner,
		physicalStatusPartner : physicalStatusPartner,
		bodyTypePartner : bodyTypePartner,
		complexionPartner : complexionPartner,
		heightSelectPartnerFirst : heightSelectPartnerFirst,
		heightSelectPartnerSecond : heightSelectPartnerSecond,
		countrySelectPartner : countrySelectPartner,
		stateSelectPartner : stateSelectPartner,
		cityStateSelectPartnerPreferance : cityStateSelectPartnerPreferance,
		religionSelectPartner : religionSelectPartner,
		casteSelectPartner : casteSelectPartner,
		checkBoxEducationPartner : ""+checkBoxEducationPartner.toString(),
		checkBoxOccupationPartner : ""+checkBoxOccupationPartner.toString(),
		incomePartner : incomePartner,
		incomeAmountPartner : incomeAmountPartner,
		familyClassInfoPartner : familyClassInfoPartner,
		familyTypeInfoPartner : familyTypeInfoPartner,
		familyValueInfoPartner : familyValueInfoPartner,
		eatingPartner : eatingPartner,
		drinkPartner : drinkPartner,
		smokePartner : smokePartner,
		firstName:firstName,
		gender:gender,
		mobile:mobile,
		dob:dob,
		userName:userName,
		password:password,
		email:email,
		employedIn:employedIn,
		employedInPartner:employedInPartner,
		ProfileCreatedFor:ProfileCreatedFor,
		kitUser:kitUser,
		kitId : kitId,
		kitPassword : kitPassword,
		ladmark : ladmark,
		kundaliImg :kundaliImg},
		timeout:8000000,
		success : function(data) 
		{
			if (data === "Duplicate") {
				$("#secondModal").modal('hide');
				$('body').removeClass('modal-open');
				$('.modal-backdrop').remove();
				alert("Error: Username is already registered! Please choose a different username.");
				return;
			}
			var arr = {kitUser:kitUser,
					   userId:""+data,
					   kundaliImg:kundaliImg};
			oMyForm.append("jsonObjectData", JSON.stringify(arr));
			
			$.ajax({
				url : "regImgUpload",
				type : "POST",
				data : oMyForm,
				enctype : 'multipart/form-data',
				processData : false,
				contentType : false,
				success : function(data1) {
					
					$("#secondModal").modal('hide');
					$('body').removeClass('modal-open');
					$('.modal-backdrop').remove();
					 $.confirm({
					        content: 'User Registration id is ='+data1+', Do You Want To Print The Form',
					        theme: 'white',
					        animationSpeed: 2000 ,
					        confirmButtonClass: 'btn-info',
					        cancelButtonClass: 'btn-danger',
					        onOpen: function(){
					            this.$content.find('button.examplebutton').click(function(){
					            });
					        },
					        onClose: function(){
					           
					        },
					        onAction: function(action){
					        	var test=1;
					        	if('confirm'==action)
					        		{
					        		
					        		$.ajax({
					        			url : "printUserProfile",
					        			type : "GET",
					        			data : "userDetailsID=" + data1,
					        			contentType : false,
					        			success : function(data1) 
					        			{
					        				window.location.href = 'Download?path='+data1;
					        				window.setTimeout(function(){
					        				        window.location = "login";
					        				    }, 6000);

					        			},
					        			error : function(xhr, ajaxOptions, thrownError) {
					        				alert("err" + thrownError + xhr.responseText);
					        			}
					        		});


					        		}
					        	else
					        		{
					        		
					        			window.location = "login";
					        		}
					        	
					        	
					        	
					        }
					    });
					
					
				},
				error : function(xhr, ajaxOptions, thrownError) {
					$("#secondModal").modal('hide');
					$('body').removeClass('modal-open');
					$('.modal-backdrop').remove();
					alert("err" + thrownError + xhr.responseText);
				}
			});
			

		},
		error : function(xhr, ajaxOptions, thrownError) {
			$("#secondModal").modal('hide');
			$('body').removeClass('modal-open');
			$('.modal-backdrop').remove();
			alert("err" + thrownError + xhr.responseText);
		}
	});
	
	
	return false;
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	oMyForm.append("jsonObjectData", JSON.stringify(arr));
	$.ajax({
		url : "registrationLogin",
		type : "POST",
		data : oMyForm,
		enctype : 'multipart/form-data',
		processData : false,
		contentType : false,
		success : function(data) {
			
			$("#secondModal").modal('toggle');
			/*$("#regmodel").modal();
			$("#regmodel").fadeIn(400);
			//alert(data);
*/			
			
			 $.confirm({
			        content: 'User Registration id is ='+data+', Do You Want To Print The Form',
			        theme: 'white',
			        animationSpeed: 2000 ,
			        confirmButtonClass: 'btn-info',
			        cancelButtonClass: 'btn-danger',
			            
			        onOpen: function(){
			         
			            
			            this.$content.find('button.examplebutton').click(function(){
			               
			            });
			        },
			        onClose: function(){
			           
			        },
			        onAction: function(action){
			        	
			        	
			        	var test=1;
			          
			        	
			        	if('confirm'==action)
			        		{
			        		
			        		$.ajax({
			        			url : "printUserProfile",
			        			type : "GET",
			        			data : "userDetailsID=" + data,
			        			contentType : false,
			        			success : function(data1) 
			        			{
			        				
			        				
			        				window.location.href = 'Download?path='+data1;
			        				window.setTimeout(function(){

			        					 
			        				        // Move to a new location or you can do something else
			        				       // window.location.href = "https://www.google.co.in";
			        				        
			        				        window.location = "https://www.snehbandhan.org/login";

			        				    }, 6000);
			        				
			        				
			        				
			        				
			        				

			        			},
			        			error : function(xhr, ajaxOptions, thrownError) {
			        				alert("err" + thrownError + xhr.responseText);
			        			}
			        		});


			        		}
			        	else
			        		{
			        		
			        			window.location = "https://www.snehbandhan.org/login";
			        		}
			        	
			        	
			        	
			        }
			    });
			
			//window.location = "https://www.snehbandhan.org/login";
		},
		error : function(xhr, ajaxOptions, thrownError) {
			alert("err" + thrownError + xhr.responseText);
		}
	});

}

function MoveToLogin()
{
	window.location = "https://www.snehbandhan.org/login";
}

function MoveToStep2()
{
	$("#DivStep3").hide();
	$("#DivStep2").show();

}

function MoveToStep3() {
	
	
	//var inp = document.getElementById('loader11');
	var inp = document.getElementById('uploadImage');
	/* photo upload is optional */
	
	
	var firstName = $("#firstName").val();
	var gender= $("input[name=gender]:checked").val();
	var mobile = $("#mobile").val();
	var dob = $(".dob").val();
	var userName = $("#userName").val();
	var password = $("#password").val();
	var email = $("#email").val();
	//var hiddenemail = $("#hiddenemail").val();
	
	var employedIn=$("input[name=employedIn]:checked").val();
	
	var aboutMe = $("#aboutMe").val();
	var materialStatus = $('#materialStatus option:selected').val();
	var physicalStatus = $('#physicalStatus option:selected').val();
	var bodyType = $("input[name=bodyType]:checked").val();
	var complexion = $("input[name=complexion]:checked").val();
	var emailRegex = /^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$/;

	/*var len = aboutMe.trim().length;
	if (len < 100) {
		$("#aboutMe").focus($("#aboutMe").css({
			'background-color' : '#ffb3b3'
		}));
		return false;
	}*/
	
	
	

	var heightSelect = $('#heightSelect option:selected').val();
	var weightSelect = $('#weightSelect option:selected').val();
	var countrySelect = $('#countrySelect option:selected').val();
	var stateSelect = $('#stateSelect option:selected').val();
	var cityStateSelect = $('#cityStateSelect option:selected').val();
	var subDistSelect = $('#subDistSelect option:selected').val();
	var religionSelect = $('#religionSelect option:selected').val();
	var casteSelect = $('#casteSelect option:selected').val();
	var educationalCategorySelect = $(
			'#educationalCategorySelect option:selected').val();

	var educationalSelect = $('#educationalSelect option:selected').val();
	var occupationCategorySelect = $(
			'#occupationCategorySelect option:selected').val();
	var occupationSelect = $('#occupationSelect option:selected').val();
	var income = $("input[name=income]:checked").val();
	var incomeAmount = $("#incomeAmount").val();
	var familyClassInfo = $("input[name=familyClassInfo]:checked").val();
	var familyTypeInfo = $("input[name=familyTypeInfo]:checked").val();
	var familyValueInfo = $("input[name=familyValueInfo]:checked").val();
	var eating = $("input[name=eating]:checked").val();
	var drink = $("input[name=drink]:checked").val();
	var smoke = $("input[name=smoke]:checked").val();
	
	
	
	if(firstName.trim()=="" ||firstName==null )
		{
		 	$("#nameSelect").slideDown();
		 	$("#nameSelect").fadeOut(15000); 
		 	$('#firstName').focus();
		 	return false; 
		}
	
	if (email != null && email.trim() != "" && !emailRegex.test(email.trim())) {
		$("#emailselect").slideDown();
		$("#emailselect").fadeOut(15000);
		$("#email").focus();			 
		return false;
	}
	if (email == null || email.trim() == "") {
		email = "abc" + new Date().getTime() + "@gmail.com";
	}
	
	var numericRegex = /^[0-9]{10}$/;
	if(!numericRegex.test(mobile))
		{
		  $("#mobilenumselect").slideDown();
		  $("#mobilenumselect").fadeOut(15000);
		  $("#mobile").focus();
		    return false;
		}

	
	 if (materialStatus == 0) 
	 {
	  
	  $("#DivMaritalselect").slideDown();
	  $("#DivMaritalselect").fadeOut(15000);
	  $('#DivMaritalselect').focus();
	  
	  return false; 
	 } if (physicalStatus == 0) {
	  $("#DivPhysicalselect").slideDown();
	  $("#DivPhysicalselect").fadeOut(15000);
	  $('#DivPhysicalselect').focus();
	  return false; 
	  
	 } if (heightSelect == 0) {
	  $("#DivHeightselect").slideDown();
	  $("#DivHeightselect").fadeOut(15000);
	  $('#DivHeightselect').focus(); return false; 
	  } 
	 
	 if (weightSelect == 0) {
	  $("#DivWeightselect").slideDown(); $("#DivWeightselect").fadeOut(15000);
	  $('#DivWeightselect').focus(); return false; 
	  }
	 
	 if (countrySelect == 0) {
	  $("#DivCountryselect").slideDown(); $("#DivCountryselect").fadeOut(15000);
	  $('#DivCountryselect').focus(); return false; 
	  } 
	 if (stateSelect == 0) {
	  $("#DivStateselect").slideDown();
	  $("#DivStateselect").fadeOut(15000);
	  $('#DivStateselect').focus(); 
	  $('html, body').animate({ scrollTop: $('#DivStateselect').offset().top }, 'slow');
	  return false;
	  } if (cityStateSelect == 0) {
	  $("#DivDistrictselect").slideDown();
	  $("#DivDistrictselect").fadeOut(15000); $('#DivDistrictselect').focus();
	  $('html, body').animate({ scrollTop: $('#DivDistrictselect').offset().top }, 'slow');
	  return false; 
	  } if (subDistSelect == 0) {
	  $("#DivSub-Districtselect").slideDown();
	  $("#DivSub-Districtselect").fadeOut(15000);
	  $('#DivSub-Districtselect').focus();
	  
	  $('html, body').animate({ scrollTop: $('#DivSub-Districtselect').offset().top }, 'slow');
	  
	  return false; } if (religionSelect == 0) {
	  $("#DivReligionselect").slideDown();
	  $("#DivReligionselect").fadeOut(15000); $('#DivReligionselect').focus();
	  
	  $('html, body').animate({ scrollTop: $('#DivReligionselect').offset().top }, 'slow');
	  return false; 
	  
	  }
	  
	  if (casteSelect == 0) { $("#DivCasteselect").slideDown();
	  $("#DivCasteselect").fadeOut(15000); $('#DivCasteselect').focus(); 
	  $('html, body').animate({ scrollTop: $('#DivCasteselect').offset().top }, 'slow');
	  
	  return false; } if (educationalCategorySelect == 0) {
	  $("#DivEducationCategoryselect").slideDown();
	  $("#DivEducationCategoryselect").fadeOut(15000);
	  $('#DivEducationCategoryselect').focus(); 
	  $('html, body').animate({ scrollTop: $('#DivEducationCategoryselect').offset().top }, 'slow');
	  return false;
	  
	  } 
	  if(educationalSelect == 0) { $("#DivEducationselect").slideDown();
	  $("#DivEducationselect").fadeOut(15000); $('#DivEducationselect').focus();
	  $('html, body').animate({ scrollTop: $('#DivEducationselect').offset().top }, 'slow');
	  
	  return false; } if (occupationCategorySelect == 0) {
	  $("#DivOccupationCategoryselect").slideDown();
	  $("#DivOccupationCategoryselect").fadeOut(15000);
	  $('#DivOccupationCategoryselect').focus();
	  $('html, body').animate({ scrollTop: $('#DivOccupationCategoryselect').offset().top }, 'slow');
	  return false; 
	  
	  } if
	  (occupationSelect == 0) { $("#DivOccupationselect").slideDown();
	  $("#DivOccupationselect").fadeOut(15000);
	  $('#DivOccupationselect').focus(); 
	  $('html, body').animate({ scrollTop: $('#DivOccupationselect').offset().top }, 'slow');
	  return false; }
	
	$("#DivStep3").show();
	$("#DivStep2").hide();

	$("#successStep2").slideDown();
	$("#successStep2").fadeOut(3000);
	$('#successStep2').focus();

}

function readOnlyKit() {
	$("#kitId").attr('readonly', 'readonly');
	$("#kitPassword").attr('readonly', 'readonly');
}

function readWriteKit() {
	$("#kitId").removeAttr('readonly');
	$("#kitPassword").removeAttr('readonly');

}
function loadCityAccordingToState(id) {

	$.ajax({
		dataType : 'json',
		url : "educationOccupation/loadCityAccordingToState",
		type : "GET",
		data : "id=" + id,
		success : function(data) {

			$("#cityStateSelect option").remove();
			$("#cityStateSelect").append(
					'<option value=0>Select District</option>');
			for (var i = 0; i < data.length; i++) {
				$("#cityStateSelect").append(
						'<option value=' + data[i].state_district_id + '>'
								+ data[i].district_Name + '</option>');
			}

		},
		error : function(xhr, ajaxOptions, thrownError) {
			alert("err" + thrownError + xhr.responseText);
		}
	});

}
function loadsubDistrictAccordingToDistrict(id) {
	$.ajax({
		dataType : 'json',
		url : "educationOccupation/loadsubDistrictAccordingToDistrict",
		type : "GET",
		data : "id=" + id,
		success : function(data) {

			$("#subDistSelect option").remove();
			$("#subDistSelect").append(
					'<option value=0>Select Sub District</option>');
			for (var i = 0; i < data.length; i++) {
				$("#subDistSelect")
						.append(
								'<option value='
										+ data[i].district_subDistricts_id
										+ '>' + data[i].subdistricts_Name
										+ '</option>');
			}

		},
		error : function(xhr, ajaxOptions, thrownError) {
			alert("err" + thrownError + xhr.responseText);
		}
	});
}
function loadCasteAccordingToReligion(id) {
	$.ajax({
		dataType : 'json',
		url : "educationOccupation/loadCasteAccordingToReligion",
		type : "GET",
		data : "id=" + id,
		success : function(data) {

			$("#casteSelect option").remove();
			$("#casteSelect").append('<option value=0>Select Caste</option>');
			for (var i = 0; i < data.length; i++) {
				$("#casteSelect").append(
						'<option value=' + data[i].caste_id + '>'
								+ data[i].caste_name + '</option>');
			}

		},
		error : function(xhr, ajaxOptions, thrownError) {
			alert("err" + thrownError + xhr.responseText);
		}
	});
}
function loadOccupationAccordingToOccuCategory(id) {
	$.ajax({
		dataType : 'json',
		url : "educationOccupation/loadOccupationAccordingToOccuCategory",
		type : "GET",
		data : "id=" + id,
		success : function(data) {
			$("#occupationSelect").prop('disabled', false);

			$("#occupationSelect option").remove();
			$("#occupationSelect").append(
					'<option value=0>Select Occupation</option>');
			for (var i = 0; i < data.length; i++) {
				$("#occupationSelect").append(
						'<option value=' + data[i].occupation_id + '>'
								+ data[i].occupation_name + '</option>');
			}

			// $("#occupationSelect").attr("disabled", "true");
		},
		error : function(xhr, ajaxOptions, thrownError) {
			alert("err" + thrownError + xhr.responseText);
		}
	});
}
function loadEducationAccordingToEduCategory(id) {
	$.ajax({
		dataType : 'json',
		url : "educationOccupation/loadEducationAccordingToEduCategory",
		type : "GET",
		data : "id=" + id,
		success : function(data) {
			$("#educationalSelect").prop('disabled', false);
			$("#educationalSelect option").remove();
			$("#educationalSelect").append(
					'<option value=0>Select Education</option>');
			for (var i = 0; i < data.length; i++) {
				$("#educationalSelect").append(
						'<option value=' + data[i].education_id + '>'
								+ data[i].education_name + '</option>');
			}

		},
		error : function(xhr, ajaxOptions, thrownError) {
			alert("err" + thrownError + xhr.responseText);
		}
	});
}
function loadCityAccordingToStatePartnerPreferance(id) {
	$.ajax({
		dataType : 'json',
		url : "educationOccupation/loadCityAccordingToState",
		type : "GET",
		data : "id=" + id,
		success : function(data) {
			$("#cityStateSelectPartnerPreferance option").remove();
			$("#cityStateSelectPartnerPreferance").append(
					'<option value=0>Select District</option>');
			for (var i = 0; i < data.length; i++) {
				$("#cityStateSelectPartnerPreferance").append(
						'<option value=' + data[i].state_district_id + '>'
								+ data[i].district_Name + '</option>');
			}

		},
		error : function(xhr, ajaxOptions, thrownError) {
			alert("err" + thrownError + xhr.responseText);
		}
	});
}

function loadCasteAccordingToReligionPartner(id) {

	$.ajax({
		dataType : 'json',
		url : "educationOccupation/loadCasteAccordingToReligion",
		type : "GET",
		data : "id=" + id,
		success : function(data) {

			$("#casteSelectPartner option").remove();
			$("#casteSelectPartner").append(
					'<option value=0>Select Caste</option>');
			for (var i = 0; i < data.length; i++) {
				$("#casteSelectPartner").append(
						'<option value=' + data[i].caste_id + '>'
								+ data[i].caste_name + '</option>');
			}

		},
		error : function(xhr, ajaxOptions, thrownError) {
			alert("err" + thrownError + xhr.responseText);
		}
	});

}