function removeImage(img, btn) {

	$.ajax({
		url : "removeImages",
		type : "GET",
		data : "image=" + img,
		processData : false,
		contentType : false,
		success : function(data) {

			var ul = document.getElementById("divImage");
			var items = ul.getElementsByTagName("li");
			for (var i = 0; i < items.length; ++i) {
				// do something with items[i], which is a <li> element
				if (img == items[i].id) {
					$(items[i]).remove();
					$(btn).remove();
				}
			}

			$("img").each(function() {
				if ($(this).attr("id") == "img" + img) {
					$(this).remove();
				}
			});
			showDivs(1);
		},
		error : function(xhr, ajaxOptions, thrownError) {
			alert("err" + thrownError + xhr.responseText);
		}
	});

}

function CropImage(){
	
	var cropimg =$("#cropidName").val();
	var cc=cropimg.substring(3, cropimg.length);
	
	$.ajax({
		url : "cropView",
		type : "GET",
		data : "image=" + cc,
		enctype : 'multipart/form-data',
		processData : false,
		contentType : false,
		success : function(data) {
			$(".model-images").html(data);
			$("#myModal").modal();
		},
		error : function(xhr, ajaxOptions, thrownError) {
			alert("err" + thrownError + xhr.responseText);
		}
	});
}
function saveImages() {
	var oMyForm = new FormData();

	var x = "";
	files = [];
	var inp = document.getElementById('loader11');
	for (var i = 0; i < inp.files.length; ++i) {

		var name = inp.files.item(i).name;
		files[i] = inp.files.item(i).name;
		oMyForm.append("file" + i, loader11.files[i]);

	}
	$("#secondModal").modal();
	$.ajax({
		url : "saveImages",
		type : "POST",
		data : oMyForm,
		enctype : 'multipart/form-data',
		processData : false,
		contentType : false,
		success : function(data) {
			$("#loader11").val("");
			$('#secondModal').modal('hide');
			$('body').removeClass('modal-open');
			$('.modal-backdrop').remove();
			//location.reload(true);
			$("#tabthree").html(data);
		},
		error : function(xhr, ajaxOptions, thrownError) {
			$('#secondModal').modal('hide');
			$('body').removeClass('modal-open');
			$('.modal-backdrop').remove();
			alert("err" + thrownError + xhr.responseText);
		}
	});

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

function updateUser() 
{
	var editDate = $("#editUserRegistrationDateHidden").val();
	var userModelDetailId = $("#hiddenUsermodelId").val();
	var userModelId = $("#hiddenuserId").val();

	var ProfileCreatedFor = $('#ProfileCreatedFor option:selected').val();
	var firstName = $("#firstName").val();
	var gender = $("input[name=gender]:checked").val();
	var mobile = $("#mobile").val();
  /*  var userMobileNumber = $("#editUserMobileNoHidden").val();
	  for(var i=0;i<userModelMobileNumberArray.length;i++)
	  {
		  if(userModelMobileNumberArray[i].trim()===mobile.trim())
		  {
			  if(userMobileNumber.trim() === mobile.trim()) {
			  }
			  else
			  {
				  $("#mobileNumberAlreadyExitsAlert").slideDown();
				  $("#mobileNumberAlreadyExitsAlert").fadeOut(5000);
				  $("#mobile").focus();
				  return false;
			  }
		  }
	  }*/
		
	var dob = $("#dob").val();
	var email = $("#email").val();
	var employedIn = $("input[name=employedIn]:checked").val();
	//var userLand = $("#userLandMark").val();
    var regDate = $("#editUserRegistrationDateHidden").val();    
	var arr = {
		aboutMe : $("#aboutMe").val().replace(/\n/g, "<br>"),
		userLandMark : $("#userLandMark").val().replace(/\n/g, "<br>"),
		materialStatus : $('#materialStatus option:selected').val(),
		physicalStatus : $('#physicalStatus option:selected').val(),
		heightSelect : $('#heightSelect option:selected').val(),
		weightSelect : $('#weightSelect option:selected').val(),
		countrySelect : $('#countrySelect option:selected').val(),
		stateSelect : $('#stateSelect option:selected').val(),
		bodyType : $("input[name=bodyType]:checked").val(),
		complexion : $("input[name=complexion]:checked").val(),
		cityStateSelect : $('#cityStateSelect option:selected').val(),
		subDistSelect : $('#subDistSelect option:selected').val(),
		religionSelect : $('#religionSelect option:selected').val(),
		casteSelect : $('#casteSelect option:selected').val(),
		educationalCategorySelect : $(
				'#educationalCategorySelect option:selected').val(),

		educationalSelect : $('#educationalSelect option:selected').val(),
		occupationCategorySelect : $(
				'#occupationCategorySelect option:selected').val(),
		occupationSelect : $('#occupationSelect option:selected').val(),
		income : $("input[name=income]:checked").val(),
		incomeAmount : $("#incomeAmount").val(),

		familyClassInfo : $("input[name=familyClassInfo]:checked").val(),
		familyTypeInfo : $("input[name=familyTypeInfo]:checked").val(),
		familyValueInfo : $("input[name=familyValueInfo]:checked").val(),
		eating : $("input[name=eating]:checked").val(),
		drink : $("input[name=drink]:checked").val(),
		smoke : $("input[name=smoke]:checked").val(),
		editUserRegistrationDate :$("#editUserRegistrationDateHidden").val(),
		userModelDetailId : userModelDetailId,
		userModelId : userModelId,
		ProfileCreatedFor : ProfileCreatedFor,
		firstName : firstName,
		gender : gender,
		mobile : mobile,
		dob : dob,
		email : email,
		employedIn : employedIn

	};

	var oMyForm = new FormData();

	oMyForm.append("jsonObjectData", JSON.stringify(arr));
	$.ajax({

		url : "updateUserModeldetails",
		type : "POST",
		data : oMyForm,
		enctype : 'multipart/form-data',
		processData : false,
		contentType : false,
		success : function(data) {
			$('html, body').animate({ scrollTop: $('#successSelect').offset().top }, 'slow');		
			$("#editUserRegistrationDateHidden").val("");
			$("#editUserMobileNoHidden").val("");
            $("#successSelect").slideDown();
			$("#successSelect").fadeOut(7000);

			
		},
		error : function(xhr, ajaxOptions, thrownError) 
		{
			alert("err" + thrownError + xhr.responseText);
		}
	});

}



function print_UserProfile()
{
    var userDetailsID = $("#hiddenuserId").val();
	$.ajax({
		url : "printPersonalDetails",
		type : "POST",
		data : "userDetailsID=" + userDetailsID,
		dataType : "text",
		async : false,
		success : function(data) 
		{
			window.location.href = 'Download?path='+data;
			
		},
		error : function(xhr, ajaxOptions, thrownError) {
			alert("err" + thrownError + xhr.responseText);
		}
	});


}

function generatePartnerReport()
{
	
	
var userModelDetailId = $("#hiddenuserId").val();
var userModelId = $("#hiddenuserId").val();
	$.ajax({
		url : "printPartnerPreferances",
		type : "POST",
		data : "userDetailsID=" + userModelDetailId,
		dataType : "text",
		async : false,
		success : function(data) 
		{
			window.location.href = 'Download?path='+data;
			
		},
		error : function(xhr, ajaxOptions, thrownError) {
			alert("err" + thrownError + xhr.responseText);
		}
	});


}
function updatePartner() {

	var userModelId = $("#hiddenuserId").val();
	var hiddenUserpartnerId = $("#hiddenUserpartnerId").val();
	var employedInPartner = $("input[name=employedInPartner]:checked").val();
	var materialStatusPartner = $('#materialStatusPartner option:selected')
			.val();
	var physicalStatusPartner = $('#physicalStatusPartner option:selected')
			.val();
	var bodyTypePartner = $("input[name=bodyTypePartner]:checked").val();
	var complexionPartner = $("input[name=complexionPartner]:checked").val();
	var heightSelectPartnerFirst = $(
			'#heightSelectPartnerFirst option:selected').val();
	var heightSelectPartnerSecond = $(
			'#heightSelectPartnerSecond option:selected').val();
	var countrySelectPartner = $('#countrySelectPartner option:selected').val();
	var stateSelectPartner = $('#stateSelectPartner option:selected').val();
	var cityStateSelectPartnerPreferance = $(
			'#cityStateSelectPartnerPreferance option:selected').val();
	var religionSelectPartner = $('#religionSelectPartner option:selected')
			.val();
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
	var familyClassInfoPartner = $("input[name=familyClassInfoPartner]:checked")
			.val();
	var familyTypeInfoPartner = $("input[name=familyTypeInfoPartner]:checked")
			.val();
	var familyValueInfoPartner = $("input[name=familyValueInfoPartner]:checked")
			.val();
	var eatingPartner = $("input[name=eatingPartner]:checked").val();
	var drinkPartner = $("input[name=drinkPartner]:checked").val();
	var smokePartner = $("input[name=smokePartner]:checked").val();

	var arr = {

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
		checkBoxEducationPartner : checkBoxEducationPartner,
		checkBoxOccupationPartner : checkBoxOccupationPartner,
		incomePartner : incomePartner,
		incomeAmountPartner : incomeAmountPartner,
		familyClassInfoPartner : familyClassInfoPartner,
		familyTypeInfoPartner : familyTypeInfoPartner,
		familyValueInfoPartner : familyValueInfoPartner,
		eatingPartner : eatingPartner,
		drinkPartner : drinkPartner,
		smokePartner : smokePartner,
		userModelId : userModelId,
		hiddenUserpartnerId : hiddenUserpartnerId,
		employedInPartner : employedInPartner

	};

	var oMyForm = new FormData();

	oMyForm.append("jsonObjectData", JSON.stringify(arr));
	$.ajax({

		url : "updateUserPartnerPreference",
		type : "POST",
		data : oMyForm,
		enctype : 'multipart/form-data',
		processData : false,
		contentType : false,
		success : function(data) {

			$("#partnersuccessSelect").slideDown();
			$("#partnersuccessSelect").fadeOut(7000);

			$('html, body').animate({ scrollTop: $('#partnersuccessSelect').offset().top }, 'slow');
		},
		error : function(xhr, ajaxOptions, thrownError) {
			alert("err" + thrownError + xhr.responseText);
		}
	});

}
function printPartnerPreferences()
{

	var userModelDetailId = $("#hiddenuserId").val();
	
	var userModelId = $("#hiddenuserId").val();
	spinnerOn();

		$.ajax({
			url : "printPartnerPreferances",
			type : "POST",
			data : "userDetailsID=" + userModelDetailId,
			dataType : 'text',
			success : function(data) 
			{
				window.location.href = 'Download?path='+data;
				spinnerOff();
			},
			error : function(xhr, ajaxOptions, thrownError) {
				alert("err" + thrownError + xhr.responseText);
			}
		});


}

function spinnerOn()
{
	document.getElementById('light').style.display = 'block';
	document.getElementById('fade').style.display = 'block';
}
function spinnerOff()
{
	document.getElementById('light').style.display = 'none';
	document.getElementById('fade').style.display = 'none';
}



