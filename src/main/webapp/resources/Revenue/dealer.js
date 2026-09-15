function UpdateDealer()
{
	var discount=$("#discount").val();
	var fname = $("#fname").val();
	var email = $("#email").val();
	var mob = $("#mob").val();
	var branchName = $("#branchName").val();

	var uname = $("#uname").val();
	var password = $("#password").val(); 
	var dealerId = $("#hiddenDealer").val(); 
	var officeNo = $("#officeNo").val();
	var address = $("#address").val();
	var pinCode = $("#pinCode").val();
	
	
	if (fname.trim() == "" || fname == null) {
		$("#nameSelect").slideDown();
		$("#nameSelect").fadeOut(15000);
		$('#firstName').focus();
		return false;
	}
	
	if (mob.length != 10) {
		$("#mobilenumselect").slideDown();
		$("#mobilenumselect").fadeOut(15000);
		$("#mob").focus();
		return false;
	}

	if (uname.trim() == "") {
		$("#unameselect").slideDown();
		$("#unameselect").fadeOut(15000);
		$("#uname").focus();
		return false;
	}

	if (password.trim() == "") {
		$("#passwordselect").slideDown();
		$("#passwordselect").fadeOut(15000);
		$("#password").focus();
		return false;
	}
	
	
	
	var rows = $("#example1").dataTable().fnGetNodes();
	var emai_id =$("#email").val();
	for (var i = 0; i < rows.length; i++) 
	{
		var datatableEmailid = $(rows[i]).find("td:eq(2)").text();

		if (datatableEmailid == emai_id.trim()) 
		{
			var emai_id =$("#email").val();
			var editselectedEmailid = $("#hiddenEmailId").val();
			if(editselectedEmailid == emai_id.trim())
				{				
				  // alert("Same pp email ");				
				}	
			else
				{
				$("#emailAvailable").slideDown();
				$("#emailAvailable").fadeOut(3000);
				$("#email").val("");
				$("#email").focus();
				return false;
				
				}
			
		}
		
	}
	
	
	var mobile_No =$("#mob").val();
	for (var i = 0; i < rows.length; i++) 
	{
		var datatableEmailid = $(rows[i]).find("td:eq(3)").text();

		if (datatableEmailid == emai_id.trim()) 
		{
			var mobile_No =$("#mob").val();
			var editselectedMobileNo = $("#hiddenMobileNo").val();
			if(editselectedMobileNo == mobile_No.trim())
				{				
				  // alert("Same pp Mobile ");				
			
				}	
			else
				{
				$("#mobileAvailable").slideDown();
				$("#mobileAvailable").fadeOut(3000);
				$("#mob").val("");
				$("#mob").focus();
				return false;
				
				}
			
		}
		
	}
	
	
	var userName =$("#uname").val();
	for (var i = 0; i < rows.length; i++) 
	{
		var datatableEmailid = $(rows[i]).find("td:eq(4)").text();

		if (datatableEmailid == userName.trim()) 
		{
			var userName =$("#uname").val();
			var editselectedDealerUserName = $("#hiddenDealerName").val();
			if(editselectedDealerUserName == userName.trim())
				{				
				   //alert("Same pp Dealer User Name");				
			
				}	
			else
				{
				$("#UserNameAvailable").slideDown();
				$("#UserNameAvailable").fadeOut(3000);
				$("#uname").val("");
				$("#uname").focus();
				return false;
				
				}
			
		}
		
	}
	
	
var hiddenRegistrationDate = $("#hiddenRegistrationDate").val().trim();
	
$.ajax({
		url : "updateDealer",
		type : "POST",
		data : {
			fname : "" + fname,
			email : "" + email,
			mob : "" + mob,
			branchName : "" + branchName,
			uname : "" + uname,
			password : "" + password,
			dealerId : "" + dealerId,
			officeNo : "" + officeNo,
			address : "" + address,
			pinCode : "" + pinCode,
			discount : ""+discount,
			hiddenRegistrationDate :hiddenRegistrationDate
		},
		success : function(data) {

			if (data == "success") {

				$("#fname").val("");
				$("#email").val("");
				$("#mob").val("");
				$("#branchName").val("");
				$("#address").val("");
				$("#uname").val("");
				$("#password").val("")
				$("#hiddenEmailId").val("");
				$("#hiddenDealerName").val("");
				$("#hiddenMobileNo").val("");
				
				$("#successSelect").slideDown();
				$("#successSelect").fadeOut(15000);
				
				setTimeout(function(){
					location.reload(true);
				}, 5000);

			} else {
				//alert(data);
				
				$("#userNameAlreadyExitsAlert").slideDown();
				$("#userNameAlreadyExitsAlert").fadeOut(5000);
			}

		},
		error : function(xhr, ajaxOptions, thrownError) {
			console.log("err" + thrownError + xhr.responseText);

			return false;
		}
	});
	
	
	

}



function saveDealer() 
{

	var dname = $("#dname").val();
	var discount=$("#discount").val();
	var fname = $("#fname").val();
	var email = $("#email").val();
	var mob = $("#mob").val();
	var branchName = $("#branchName").val();
	var officeNo = $("#officeNo").val();
	var address = $("#address").val();
	var pinCode = $("#pinCode").val()
	/*var registrationDate = $("#registrationDate").val();*/
	
	
	var uname = $("#uname").val();
	var password = $("#password").val();
	if (fname.trim() == "" || fname == null) {
		$("#nameSelect").slideDown();
		$("#nameSelect").fadeOut(3000);
		$('#fname').focus();
		return false;
	}
	
	if (mob.length != 10) {
		$("#mobilenumselect").slideDown();
		$("#mobilenumselect").fadeOut(3000);
		$("#mob").focus();
		return false;
	}

	if (uname.trim() == "") {
		$("#unameselect").slideDown();
		$("#unameselect").fadeOut(3000);
		$("#uname").focus();
		return false;
	}

	if (password.trim() == "") {
		$("#passwordselect").slideDown();
		$("#passwordselect").fadeOut(3000);
		$("#password").focus();
		return false;
	}
	
	var mob = $("#mob").val();
	var oMyForm3 = new FormData();
	var arr3 = {
			mob : mob
	};

	oMyForm3.append("jsonObjectData3", JSON.stringify(arr3));
	$.ajax({
		url : "validateMobileByDealer",
		type : "POST",
		data : oMyForm3,
		enctype : 'multipart/form-data',
		processData : false,
		contentType : false,
		success : function(data) {
			if (data == "AlreadyAvailable") {
				$("#mob").focus();
				$("#mobileAvailable").slideDown();
				$("#mobileAvailable").fadeOut(3000);
				return false;
			} else {
				var userName = $("#uname").val();
				var oMyForm = new FormData();
				var arr = {
					userName : userName
				};

				oMyForm.append("jsonObjectData", JSON.stringify(arr));

				$.ajax({
					url : "validateUserByDealer",
					type : "POST",
					data : oMyForm,
					enctype : 'multipart/form-data',
					processData : false,
					contentType : false,
					success : function(data) 
					{
						if (data == "AlreadyAvailable") {
							$("#UserNameAvailable").slideDown();
							$("#UserNameAvailable").fadeOut(3000);
							return false;
						} else {
							
							$.ajax({
								url : "DealerRegistation",
								type : "POST",
								data : {
									fname :fname,
									email :email,
									mob :mob,
									branchName : branchName,
									uname : uname,
									password : password,
									dname  :dname,
									discount :discount,
									officeNo :officeNo,
									address : address,
									pinCode : pinCode
								/*	registrationDate : registrationDate*/

								},
								success : function(data) {
									

									if (data == "success") {

										$("#fname").val("");
										$("#email").val("");
										$("#mob").val("");
										$("#branchName").val("");
										$("#address").val("");
										$("#uname").val("");
										$("#password").val("")
										$("#successSelect").slideDown();
										$("#successSelect").fadeOut(5000);
										
											setTimeout(function(){
				                             location.reload(true);
			                             	}, 5000);

									} else {
										alert("Dealer Not created successfully");
									}

								},
								error : function(xhr, ajaxOptions, thrownError) {
									console.log("err" + thrownError + xhr.responseText);

									return false;
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

function saveSubDealer()
{
	

	
	var discount=$("#discount").val();
	var fname = $("#fname").val();
	var email = $("#email").val();
	var mob = $("#mob").val();
	var branchName = $("#branchName").val();
	var pinCode = $("#pinCode").val();
	var officeNo = $("#officeNo").val();
	var address = $("#address").val();
	var registrationDate = $("#registrationDate").val();
	
	var uname = $("#uname").val();
	var password = $("#password").val();
	if (fname.trim() == "" || fname == null) {
		$("#nameSelect").slideDown();
		$("#nameSelect").fadeOut(15000);
		$('#firstName').focus();
		return false;
	}
	
	
	if (mob.length != 10) {
		$("#mobilenumselect").slideDown();
		$("#mobilenumselect").fadeOut(15000);
		$("#mobile").focus();
		return false;
	}

	if (uname.trim() == "") {
		$("#unameselect").slideDown();
		$("#unameselect").fadeOut(15000);
		$("#uname").focus();
		return false;
	}

	if (password.trim() == "") {
		$("#passwordselect").slideDown();
		$("#passwordselect").fadeOut(15000);
		$("#password").focus();
		return false;
	}
	
	
	

	var mob = $("#mob").val();
	var oMyForm3 = new FormData();
	var arr3 = {
			mob : mob
	};

	oMyForm3.append("jsonObjectData3", JSON.stringify(arr3));
	$.ajax({
		url : "validateMobileByDealer",
		type : "POST",
		data : oMyForm3,
		enctype : 'multipart/form-data',
		processData : false,
		contentType : false,
		success : function(data) {
			if (data == "AlreadyAvailable") {
				$("#mob").focus();
				$("#mobileAvailable").slideDown();
				$("#mobileAvailable").fadeOut(3000);
				return false;
			} else {
				var userName = $("#uname").val();
				var oMyForm = new FormData();
				var arr = {
					userName : userName
				};

				oMyForm.append("jsonObjectData", JSON.stringify(arr));

				$.ajax({
					url : "validateUserByDealer",
					type : "POST",
					data : oMyForm,
					enctype : 'multipart/form-data',
					processData : false,
					contentType : false,
					success : function(data) {
						if (data == "AlreadyAvailable") {
							$("#UserNameAvailable").slideDown();
							$("#UserNameAvailable").fadeOut(3000);
							return false;
						} else {
							
							
							$.ajax({
								url : "SubDealerRegistation",
								type : "POST",
								data : {
									
									fname :fname,
									email :email,
									mob :mob,
									branchName : branchName,
									uname : uname,
									password : password,
									discount :discount,
									officeNo :officeNo,
									address : address,
									pinCode : pinCode,
									registrationDate : registrationDate

								},
								success : function(data) {

									if (data == "success") {

										$("#fname").val("");
										$("#email").val("");
										$("#mob").val("");
										$("#branchName").val("");
										$("#address").val("");
										$("#uname").val("");
										$("#password").val("")
										
										$("#successSelect").slideDown();
										$("#successSelect").fadeOut(5000);
										setTimeout(function(){
											location.reload(true);
										}, 5000);

									} else {
										alert("Dealer Not created successfully");
									}

								},
								error : function(xhr, ajaxOptions, thrownError) {
									console.log("err" + thrownError + xhr.responseText);

									return false;
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

