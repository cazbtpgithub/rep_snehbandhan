function uploadKitIdAndPassword(){
	
	
	var kitId=	$("#kitId").val();
	var kitPass=	$("#kitPassword").val();  
	
	var oMyForm = new FormData();
	var arr = {
			kitId :kitId,
			kitPass:kitPass
	};
	
	
	oMyForm.append("jsonObjectData", JSON.stringify(arr));
	
	$.ajax({
		url : "upgradePackage",
		type : "POST",
		data : oMyForm,
		processData : false,
		contentType : false,
		success : function(data) {
			if (data == "AlreadyAvailable") {
				$("#packAvailable").slideDown();
				$("#packAvailable").fadeOut(3000);
				return false;
			}
			if (data == "Success") {
				$("#kitId").val("");
				$("#kitPassword").val("");  
				
				$("#packSuccess").slideDown();
				$("#packSuccess").fadeOut(3000);
				return false;
			}
			if (data == "InvalidKit") {
				$("#packFail").slideDown();
				$("#packFail").fadeOut(3000);
				return false;
			}
			
			
		},
		error : function(xhr, ajaxOptions, thrownError) {
			alert("err" + thrownError + xhr.responseText);
		}
	});
	
}

function uploadKitIdAndPasswordByAdmin(){
	
	var kitId=	$("#kitId").val();
	var kitPass=	$("#kitPassword").val();  
	var userId=$("#userId").val();
	var oMyForm = new FormData();
	var arr = {
			userId:userId,
			kitId :kitId,
			kitPass:kitPass
	};
	
	oMyForm.append("jsonObjectData", JSON.stringify(arr));
	$.ajax({
		url : "upgradePackageByAdmin",
		type : "POST",
		data : oMyForm,
		processData : false,
		contentType : false,
		success : function(data) {
			
			if (data == "AlreadyAvailable") {
				$("#packAvailable").slideDown();
				$("#packAvailable").fadeOut(3000);
				return false;
			}
			if (data == "Success") {
				$("#kitId").val("");
				$("#kitPassword").val("");  
				
				$("#packSuccess").slideDown();
				$("#packSuccess").fadeOut(3000);
				return false;
			}
			if (data == "InvalidKit") {
				$("#packFail").slideDown();
				$("#packFail").fadeOut(3000);
				return false;
			}
			
			
		},
		error : function(xhr, ajaxOptions, thrownError) {
			alert("err" + thrownError + xhr.responseText);
		}
	});
	
}

