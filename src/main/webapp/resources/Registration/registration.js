function landingPageRegistration() {
	
	var name_regex = /^[a-zA-Z\s]+$/;
	
	var email="";
					
	var email_regex = /^[\w\-\.\+]+\@[a-zA-Z0-9\.\-]+\.[a-zA-z0-9]{2,4}$/;
	var mob_regex = /^[0-9]+$/;
	var patt1 = /\s/g;
	var uname=$("#userName").val();
	var gender= $("input[name=gender]:checked").val();
	
	if (!$("#fullname").val().match(name_regex)
			|| $("#fullname").val().length == 0) {
		$("#fullname").focus();
		$("#invalidName").slideDown();
		$("#invalidName").fadeOut(10000);
		return false;
	}
	
	
	var date1 = convertDate($('#dob').val());
	
	
	if(date1=="" ||date1==null || date1=='Invalid Date')
		{
			$("#dob").focus();
			$("#emptyage").slideDown();
			$("#emptyage").fadeOut(10000)
			return false;
		}
	
	var d = new Date();
	var n = d.getFullYear();
	

		if (gender == 'Male') {
		
			if (n - date1.getFullYear() < 18) {
			$("#dob").focus();
			$("#invalidagemale").slideDown();
			$("#invalidagemale").fadeOut(10000);
			return false;
		}
	}
		
		if (gender == 'Female') {
			if (n - date1.getFullYear() < 18) {
				$("#dob").focus();
				$("#invalidage").slideDown();
				$("#invalidage").fadeOut(10000);

				return false;
			}
		}

	if ($("#email").val().trim().length == 0 || !$("#email").val().match(email_regex)) {
			$("#email").focus();
			$("#invalidEmail").slideDown();
			$("#invalidEmail").fadeOut(10000);
			return false;
		}

	if (!$("#mobile").val().match(mob_regex) || $("#mobile").val().length!=10) {
		$("#mobile").focus();
		$("#invalidMobile").slideDown();
		$("#invalidMobile").fadeOut(10000);

		return false;
	}

	if ($("#userName").val().trim().length == 0) {
		$("#userName").focus();
		$("#invalidUserName").slideDown();
		$("#invalidUserName").fadeOut(10000);

		return false;
	}
	
	
		if (uname.match(/\s/g)){
		    
			$("#userName").focus();
			$("#spaceNot").slideDown();
			$("#spaceNot").fadeOut(10000);
			return false;
		}
		
	
	if ($("#password").val().trim().length == 0) {
		$("#password").focus();
		$("#invalidPassword").slideDown();
		$("#invalidPassword").fadeOut(10000);

		return false;
	}
	
	if ($("#password").val().trim().length < 6) {
		$("#password").focus();
		$("#invalidpassword").slideDown();
		$("#invalidpassword").fadeOut(10000);

		return false;
	}
	
	

		/*if ($("#email").val().trim() != "") {
		}*/
	
	if ($("#email").val()!= "")
		{
			email = $("#email").val();
		}
	
	
	$("#hiddenemail").val(email);
	
		var oMyForm1 = new FormData();
		var arr1 = {
			email : email
		};

		oMyForm1.append("jsonObjectData1", JSON.stringify(arr1));
		$.ajax({
			url : "validateEmail",
			type : "POST",
			data : oMyForm1,
			enctype : 'multipart/form-data',
			processData : false,
			contentType : false,
			success : function(data) {
				if (data == "AlreadyAvailable") {
					$("#email").focus();
					$("#emailAvailable").slideDown();
					$("#emailAvailable").fadeOut(10000);
					return false;
				} else {
					
					
					var mob = $("#mobile").val();
					var oMyForm3 = new FormData();
					var arr3 = {
							mob : mob
					};

					oMyForm3.append("jsonObjectData3", JSON.stringify(arr3));
					$.ajax({
						url : "validateMobile",
						type : "POST",
						data : oMyForm3,
						enctype : 'multipart/form-data',
						processData : false,
						contentType : false,
						success : function(data) {
							if (data == "AlreadyAvailable") {
								$("#mobile").focus();
								$("#mobileAvailable").slideDown();
								$("#mobileAvailable").fadeOut(10000);
								
								return false;
							} else {
								var userName = $("#userName").val();
								var oMyForm = new FormData();
								var arr = {
									userName : userName
								};

								oMyForm.append("jsonObjectData", JSON.stringify(arr));

								$.ajax({
									url : "validateUser",
									type : "POST",
									data : oMyForm,
									enctype : 'multipart/form-data',
									processData : false,
									contentType : false,
									success : function(data) {
										if (data == "AlreadyAvailable") {
											$("#userName").focus();
											$("#UserNameAvailable").slideDown();
											$("#UserNameAvailable").fadeOut(10000);
											return false;
										} else {
											$("#landingRegistration").submit();
										}

									},
									error : function(xhr, ajaxOptions, thrownError) {
										alert("err" + thrownError + xhr.responseText);
									}
								});
							}
						},
						error : function(xhr, ajaxOptions, thrownError) {
							alert("err" + thrownError + xhr.responseText);
						}
					});
				}

			},
			error : function(xhr, ajaxOptions, thrownError) {
				alert("err" + thrownError + xhr.responseText);
			}
		});

	}


	
	
			
	



function readOnlyKit() {
	$("#kitId").attr('readonly', 'readonly');
	$("#kitPassword").attr('readonly', 'readonly');
}

function readWriteKit() {
	$("#kitId").removeAttr('readonly');
	$("#kitPassword").removeAttr('readonly');

}


function convertDate( str ) {
	   if (!str) return new Date("");
	   if (str.indexOf("-") !== -1) {
	       var parts = str.split("-");
	       if (parts[0].length === 4) {
	           // YYYY-MM-DD format
	           return new Date(parts[1] + "/" + parts[2] + "/" + parts[0]);
	       } else {
	           // DD-MM-YYYY format
	           return new Date(parts[1] + "/" + parts[0] + "/" + parts[2]);
	       }
	   }
	   var parts = str.split("/");
	   if (parts.length === 3) {
	       return new Date(parts[1] + "/" + parts[0] + "/" + parts[2]);
	   }
	   return new Date(str);
	}


