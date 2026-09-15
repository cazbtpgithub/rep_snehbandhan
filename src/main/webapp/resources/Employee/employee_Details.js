
function saveEmployeeRegistrationDetails() 
{
	$('html, body').animate({ scrollTop: $('#home').offset().top }, 'slow');
	
	
	var name_regex = /^[a-zA-Z\s]+$/;

	var mob_regex = /^[0-9]+$/;
	var patt1 = /\s/g;
	var uname = $("#userName").val();

	if (!$("#fullName").val().match(name_regex)
			|| $("#fullName").val().length == 0) 
	{
		$("#fullName").focus();
		$("#invalidName").slideDown();
		$("#invalidName").fadeOut(3000);
		return false;
	}

	if ($('#userName').val() == "") {
		
		$("#unameselect").slideDown();
		$("#unameselect").fadeOut(3000);
		$("#userName").focus();
		return false;

	}
	if ($('#employeePassword').val() == "") 
	{	
		$("#passwordselect").slideDown();
		$("#passwordselect").fadeOut(3000);
		$("#employeePassword").focus();
		return false;
	}
	if ($('#mobileNo').val() == "") 
	{
		$("#mobileNoRequired").slideDown();
		$("#mobileNoRequired").fadeOut();
		$("#mobileNo").focus();
		return false;

	}
    if($("#dateOfBirth").val() == "")
    	{
    	 
    	}
    else
    	{
    	
    	var dob = document.forms["customerRegistrationForm"]["dateOfBirth"].value;
    	var data = dob.split("/");
    	if (isNaN(Date.parse(data[2] + "-" + data[0] + "-" + data[1])))
    	{
    		$("#dateOfBirthRequiredAlert").slideDown();
    		$("#dateOfBirthRequiredAlert").fadeOut(3000);
    		$("#dateOfBirth").focus();
    		return false;
    	}
    	
    	}



	// -------------validation of email mobile number and username-------------------->>

	
		var mob = $("#mobileNo").val();
		var oMyForm3 = new FormData();
		var arr3 = {
			mob : mob
		};

		oMyForm3.append("jsonObjectData3", JSON
				.stringify(arr3));
		$.ajax({
					url : "validateMobile",
					type : "POST",
					data : oMyForm3,
					enctype : 'multipart/form-data',
					processData : false,
					contentType : false,
					success : function(data) {
						if (data == "AlreadyAvailable") {
							$("#mobileNo").focus();
							$("#mobileAvailable")
									.slideDown();
							$("#mobileAvailable").fadeOut(
									3000);

							return false;
						} else {
							var userName = $("#userName")
									.val();
							var oMyForm = new FormData();
							var arr = {
								userName : userName
							};

							oMyForm.append("jsonObjectData",JSON.stringify(arr));

							$.ajax({
										url : "validateUser",
										type : "POST",
										data : oMyForm,
										enctype : 'multipart/form-data',
										processData : false,
										contentType : false,
										success : function(
												data) {
											if (data == "AlreadyAvailable") {
												$(
														"#userName")
														.focus();
												$(
														"#UserNameAvailable")
														.slideDown();
												$(
														"#UserNameAvailable")
														.fadeOut(
																3000);
												return false;
											} else {
												saveEmployeeRegistration();
											}

										},
										error : function(
												xhr,
												ajaxOptions,
												thrownError) {
											alert("err"
													+ thrownError
													+ xhr.responseText);
										}
									});
						}
					},
					error : function(xhr, ajaxOptions,
							thrownError) {
						alert("err" + thrownError
								+ xhr.responseText);
					}
				});
	
	
		function saveEmployeeRegistration() 
		{
			$('html, body').animate({ scrollTop: $('#home').offset().top }, 'slow');
			var employeeFormData = $("#customerRegistrationForm").serialize();

			var fullName = $("#fullName").val();
			var userName = $("#userName").val();
			var empPassword = $("#employeePassword").val();
			var mobileNo = $("#mobileNo").val();

			var email = $("#email").val();
			var designation = $("#designation").val();
			var alternateMobileNo = $("#alernateMobileNo").val();
			var city = $("#city").val();
			var address = $("#address").val();
			var previousSallary = $("#previousSalary").val();
			var currentSallary = $("#currentSalary").val();
			var totalExperience = $("#totalExperience").val();
			var experienceDetails = $("#experienceDetails").val();
			var bankName = $("#bankName").val();
			var ifsccode = $("#ifcCode").val();
			var branchName = $("#branchName").val();
			var accountNo = $("#accountNo").val();
			var dateofBirth = $("#dateOfBirth").val();
			var registrationDate = $("#registrationDate").val();
			if(registrationDate == "")
				{
	              
				 $("#registrationRequiredAlert").slideDown();
				 $("#registrationRequiredAlert").fadeOut(3000);
				 return false;
				
				}
			
		    var  dateofbirth = null;
			if(dateofBirth != "")
			{
				
				var date = $("#dateOfBirth").val();
				
				var   dateArr = date.split("/");
				var year_str =  dateArr[2];
				var month_str2 =dateArr[0];
				var day_str3 = dateArr[1];
				var year = year_str.substring(0, 4);
				
				dateofbirth = year+"-"+month_str2+"-"+ day_str3;
			}
			
            var Regdate = $("#registrationDate").val();
			var   RegdateArr = Regdate.split("/");
			var year_str1 =  RegdateArr[2];
			var month_str3 =RegdateArr[0];
			var day_str4 = RegdateArr[1];
			 var year1 = year_str1.substring(0, 4);

			var registration_date = year1+"-"+month_str3+"-"+ day_str4;
			
	
			var status_id = 1;

			var status = "Enable";
			$.ajax({
						dataType : 'json',
						url : "saveOrUpdateEmployeeUserDetails",
						data : employeeFormData,
						type : "POST",
						success : function(responce) {

							var employeeDetailsTableRowId = JSON
									.stringify(responce);
							employeeDetailsTableRowId = employeeDetailsTableRowId
									.slice(1, -1);
							var   spliteid = employeeDetailsTableRowId.split("#");
							var empkiyee_id = spliteid[0];
							var userModelId = spliteid[1];
							
							
							
							var oTable = $('#employeeDetailsTable').dataTable();
							var number = parseInt(oTable.fnGetData().length) + 1;
							oTable
									.fnAddData([
											number,
											fullName,
											userName,
											empPassword,
											mobileNo,
											"<span style='color:green;font-size:14px;'>"
													+ status + "</span>",
											"<td ><div class='dropdown' >"
													+ "<a class='dropdown-toggle' id='menu1' type='button' data-toggle='dropdown' style=' width: 100%; display: block; text-align: center; cursor:pointer;'>"
													+ "Action</a>"
													+ "<ul class='dropdown-menu' role='menu' aria-labelledby='menu1'>"
													+ "<li role='presentation'><a role='menuitem' tabindex='-1' href='# ' onclick='return editEmployeeDetails("
													+"&quot;"
													+ number +"&quot;"
													+ ",&quot;"+fullName
													+ "&quot;,&quot;"+userName
													+ "&quot;,&quot;"+empPassword
													+ "&quot;,&quot;"+mobileNo
													+ "&quot;,&quot;"+empkiyee_id
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
													+ "&quot;,&quot;"+dateofbirth
													+ "&quot;,&quot;"+registration_date
													+ "&quot;,&quot;"+status_id 
													+ "&quot;,&quot;"+userModelId
													+ "&quot;,&quot;"+userName
													+ "&quot;"
													+ ");'>Edit</a></li>"	
													+ "<li role='presentation'><a role='menuitem' tabindex='-1' href='# ' onclick='return enableEmployeeDetails("
													+"&quot;"
													+ number +"&quot;"
													+ ",&quot;"+fullName
													+ "&quot;,&quot;"+userName
													+ "&quot;,&quot;"+empPassword
													+ "&quot;,&quot;"+mobileNo
													+ "&quot;,&quot;"+empkiyee_id
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
													+ "&quot;,&quot;"+dateofbirth
													+ "&quot;,&quot;"+registration_date
													+ "&quot;,&quot;"+status_id
													+ "&quot;,&quot;"+userModelId
													+ "&quot;,&quot;"+userName
													+ "&quot;," 
													+ ");'>Enable</a></li>"
													+ "<li role='presentation'><a role='menuitem' tabindex='-1' href='# ' onclick='return disableEmployeeDetails("
													+"&quot;"
													+ number +"&quot;"
													+ ",&quot;"+fullName
													+ "&quot;,&quot;"+userName
													+ "&quot;,&quot;"+empPassword
													+ "&quot;,&quot;"+mobileNo
													+ "&quot;,&quot;"+empkiyee_id
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
													+ "&quot;,&quot;"+dateofbirth
													+ "&quot;,&quot;"+registration_date
													+ "&quot;,&quot;"+status_id
													+ "&quot;,&quot;"+userModelId
													+ "&quot;,&quot;"+userName
													+ "&quot;"
													+ ");'>Disable</a></li>"
													+ "<li role='presentation'><a role='menuitem' tabindex='-1' href='# ' onclick='return deleteEmployeeDetails("
													+"&quot;"
													+ number +"&quot;"
													+ ",&quot;"+fullName
													+ "&quot;,&quot;"+userName
													+ "&quot;,&quot;"+empPassword
													+ "&quot;,&quot;"+mobileNo
													+ "&quot;,&quot;"+empkiyee_id
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
													+ "&quot;,&quot;"+dateofbirth
													+ "&quot;,&quot;"+registration_date
													+ "&quot;,&quot;"+status_id
													+ "&quot;,&quot;"+userModelId
													+ "&quot;,&quot;"+userName
													+ "&quot;"
													+ ");'>Delete</a></li>"
													+ " </ul></div>" + "</td>", ]);

							$("#successSelect").slideDown();
							$("#successSelect").fadeOut(3000);

						     clearAllFields();
						
							/* var date = new Date();
							 document.getElementById("dateOfBirth").value = (date.getMonth() + 1) + '/' + date.getDate() + '/' + date.getFullYear();
							*/  
							 var theSelect = document.getElementById('employee_lst');
							 var lastValue = theSelect.options[theSelect.options.length - 1].value;

							 var lastVal = Number(lastValue);

							 var cnt = 1;
							 var counter = Number(cnt);
							 var maXValue = lastVal+counter;

							 var	dayVal = maXValue;
							 var optionName = fullName;
							 $('<option/>').attr("value",userModelId).text(optionName).appendTo('#employee_lst');

						},
						error : function(result) {
							alert('error' + JSON.stringify(result));
							return false;
						}

					});
		}

	

}

// -----------------------------------------End Employee Details Save Function-------------------------------------//
function editEmployeeDetails(srno, fullName, userName, password, mobileNo,
		empid, accountNo, branchName, ifscode, bankName, degignation,
		experienceDetails, totalExperience, cureentSalary, prevouseSalary,
		alernateMobileNo, email, address, city, dateOfBirth,registrationDate, status_id, userId,EmpUserName) {

	// =------------------- On click action Button Event this funtction for Update Employee Details--------------//
	
	
	$('html, body').animate({scrollTop : $('#home').offset().top}, 'slow');
	
	$("#empUpdateButton").prop('disabled', false);
	$("#employeeSaveButton").prop('disabled', true);
	$("#fullName").val(fullName);
	$("#userName").val(userName);
	$("#editselectedUserName").val(userName);
	$("#employeePassword").val(password);
	$("#email").val(email);
	$("#designation").val(degignation);
	$("#mobileNo").val(mobileNo);
	$("#editselectedMobileNo").val(mobileNo);
	$("#alernateMobileNo").val(alernateMobileNo);
	$("#city").val(city);
	$("#address").val(address);
	$("#previousSalary").val(prevouseSalary);
	$("#currentSalary").val(cureentSalary);
	$("#totalExperience").val(totalExperience);
	$("#experienceDetails").val(experienceDetails);
	$("#bankName").val(bankName);
	$("#ifcCode").val(ifscode);
	$("#branchName").val(branchName);
	$("#accountNo").val(accountNo);
	//$("#registrationDate").val(registrationDate)
	$("#employeeHiddenId").val(empid);
	$("#datatable_row_id").val(srno);
	$("#employeeEnableDisableStatus").val(status_id);
	if(dateOfBirth == 'null' )
		{
		$("#dateOfBirth").val("");
		}
	if(dateOfBirth == "")
		{
		$("#dateOfBirth").val("");
		}
	else
		{
		
		var   dateArr = dateOfBirth.split("-");
		var str1 =  dateArr[2];
		var year_str2 =dateArr[0];
		var month_str3 = dateArr[1];
		var day = str1.substring(0, 2);
		$("#dateOfBirth").val(month_str3+"/"+day+"/"+year_str2);
		
		}
	
	
    //--------------------date formatting------------------>>
	var   dateArr2 = registrationDate.split("-");
	var str2 =  dateArr2[2];
	var year_str3 =dateArr2[0];
	var month_str4 = dateArr2[1];
	var day1 = str2.substring(0, 2);
	$("#registrationDate").val(month_str4+"/"+day1+"/"+year_str3);

	var rows = $("#employeeDetailsTable").dataTable().fnGetNodes();
	var statusDisable = 'Disable';

   for (var i = 0; i < rows.length; i++) 
   {
    var id = $(rows[i]).find("td:eq(0)").text();
    var selectedEmployeeStatus = $(rows[i]).find("td:eq(5)").text();
    
    var isEqual = (id.toString() === srno.toString());
    var isDisable =  (statusDisable.toString() === selectedEmployeeStatus.toString().trim());

    if(isEqual == true)
     {
   	       if(isDisable == true)
     	 {
   	    	   
   	        clearAllFields();
   	  
			$("#employeeSaveButton").prop('disabled',false);
			$("#empUpdateButton").prop('disabled', true);

   		     $("#updateEmployeeErrorAlert").slideDown();
   	   		 $("#updateEmployeeErrorAlert").fadeOut(9000);
   	   		
   	    	 return false; 
   		 
   	      } 
   	  // return false;
   	
	  }  
   } 
	

	if (status_id == 2) {
		$("#updateEmployeeErrorAlert").slideDown();
		$("#updateEmployeeErrorAlert").fadeOut(10000);
		return false;
	}

}

function updateEmployeeRegistrationDetails() {
	$('html, body').animate({
		scrollTop : $('#home').offset().top
	}, 'slow');

	var name_regex = /^[a-zA-Z\s]+$/;

	var email_regex = /^[\w\-\.\+]+\@[a-zA-Z0-9\.\-]+\.[a-zA-z0-9]{2,4}$/;
	var mob_regex = /^[0-9]+$/;
	var patt1 = /\s/g;
	var uname = $("#userName").val();

	if (!$("#fullName").val().match(name_regex)
			|| $("#fullName").val().length == 0) {
		$("#fullName").focus();
		$("#invalidName").slideDown();
		$("#invalidName").fadeOut(3000);
		return false;
	}

	if ($('#userName').val() == "") {
		$("#unameselect").slideDown();
		$("#unameselect").fadeOut(3000);
		$("#userName").focus();
		return false;

	}
	if ($('#employeePassword').val() == "") {
		$("#passwordselect").slideDown();
		$("#passwordselect").fadeOut(3000);
		$("#employeePassword").focus();
		return false;
	}
	if ($('#mobileNo').val() == "") {
		$("#mobileNoRequired").slideDown();
		$("#mobileNoRequired").fadeOut();
		$("#mobileNo").focus();
		return false;

	}
	
	
	var  dateofbirth = "";

	if($("#dateOfBirth").val() == "")
	{
		dateofbirth = "";
	}
	else
	{
		var date = $("#dateOfBirth").val();
		var   dateArr = date.split("/");
		var year_str =  dateArr[2];
		var month_str2 =dateArr[0];
		var day_str3 = dateArr[1];
		var year = year_str.substring(0, 4);

		dateofbirth = year+"-"+month_str2+"-"+ day_str3;

	}


	var Regdate = $("#registrationDate").val();
	var   RegdateArr = Regdate.split("/");
	var year_str1 =  RegdateArr[2];
	var month_str3 =RegdateArr[0];
	var day_str4 = RegdateArr[1];
	var year1 = year_str1.substring(0, 4);

	var registration_date = year1+"-"+month_str3+"-"+ day_str4;


	var employee_id = $("#employeeHiddenId").val();
	var emp_enableDisableStatus = $("#employeeEnableDisableStatus").val();

	var fullName = $("#fullName").val();
	var userName = $("#userName").val();
	var empPassword = $("#employeePassword").val();
	var mobileNo = $("#mobileNo").val();

	var email = $("#email").val();
	var designation = $("#designation").val();
	var alternateMobileNo = $("#alernateMobileNo").val();
	var city = $("#city").val();
	var address = $("#address").val();
	var previousSallary = $("#previousSalary").val();
	var currentSallary = $("#currentSalary").val();
	var totalExperience = $("#totalExperience").val();
	var experienceDetails = $("#experienceDetails").val();
	var bankName = $("#bankName").val();
	var ifsccode = $("#ifcCode").val();
	var branchName = $("#branchName").val();
	var accountNo = $("#accountNo").val();
	var dateofBirth = $("#dateOfBirth").val(); 
	var registrationDate = $("#registrationDate").val();
	
	
	 if($("#dateOfBirth").val() == "")
 	{
 	 
 	}
 else
 	{
 	
 	var dob = document.forms["customerRegistrationForm"]["dateOfBirth"].value;
 	var data = dob.split("/");
 	if (isNaN(Date.parse(data[2] + "-" + data[0] + "-" + data[1])))
 	{
 		$("#dateOfBirthRequiredAlert").slideDown();
 		$("#dateOfBirthRequiredAlert").fadeOut(3000);
 		$("#dateOfBirth").focus();
 		return false;
 	}
 	
 	}
	
	
	// ---- start --Mobile -- validation-----------
	var rows = $("#employeeDetailsTable").dataTable().fnGetNodes();
	var mobile_no = $("#mobileNo").val();
	for (var i = 0; i < rows.length; i++) {
		var datatableMobileNo = $(rows[i]).find("td:eq(4)").text();

		if (datatableMobileNo == mobile_no.trim()) {
			var mobile_no = $("#mobileNo").val();
			var editselectedMobileNo = $("#editselectedMobileNo").val();
			if (editselectedMobileNo == mobile_no.trim()) {
				// alert("Same Mobile Name");
			} else {
				$('html, body').animate({
					scrollTop : $('#home').offset().top
				}, 'slow');
				$("#mobileNo").focus();
				$("mobileNo").val("");
				$("#mobileNoAlreadyPresent").slideDown();
				$("#mobileNoAlreadyPresent").fadeOut(3000);
				return false;

			}

		}

	}
	// ------------------End Mobile Validation----------------
	var rows = $("#employeeDetailsTable").dataTable().fnGetNodes();
	var userName = $("#userName").val();
	for (var i = 0; i < rows.length; i++) {
		var datatableUserName = $(rows[i]).find("td:eq(2)").text();

		if (datatableUserName == userName.trim()) {
			var userName = $("#userName").val();
			var editselectedUserName = $("#editselectedUserName").val();
			if (editselectedUserName == userName.trim()) {
				// alert("Same User Name");
			} else {
				$('html, body').animate({
					scrollTop : $('#home').offset().top
				}, 'slow');
				$("#userName").focus();
				$("userName").val("");
				$("#UserNameAvailable").slideDown();
				$("#UserNameAvailable").fadeOut(3000);
				return false;

			}

		}

	}
	// -----------------end ---------User Validation---------

	var employeeFormData = $("#customerRegistrationForm").serialize();

	$
			.ajax({
				dataType : 'json',
				url : "saveOrUpdateEmployeeUserDetails",
				data : employeeFormData,
				type : "POST",
				success : function(responce) {

					var rows = $("#employeeDetailsTable").dataTable()
							.fnGetNodes();
					var row = $("#datatable_row_id").val();
					var status = "Enable";
					$(rows[row - 1]).find("td:eq(1)").text(fullName);
					$(rows[row - 1]).find("td:eq(2)").text(userName);
					$(rows[row - 1]).find("td:eq(3)").text(empPassword);
					$(rows[row - 1]).find("td:eq(4)").text(mobileNo);
					$(rows[row - 1])
							.find("td:eq(6)")
							.html(
									"<style> .aligCenter{width: 100%; display: block; text-align: center;}</style>"
											+ "<td class='aligCenter'>"
											+ "<div class='dropdown'>"
											+ "<a class='dropdown-toggle' id='menu1' type='button' data-toggle='dropdown' style=' width: 100%; display: block; text-align: center; cursor:pointer;'>"
											+ "Action</a>"
											+ "<ul class='dropdown-menu' role='menu' aria-labelledby='menu1'>"
											+ "<li role='presentation'><a role='menuitem' tabindex='-1' href='# ' onclick='return editEmployeeDetails("
											+ "&quot;"
											+ row+ "&quot;"
											+ ",&quot;"+ fullName
											+ "&quot;,&quot;"+ userName
											+ "&quot;,&quot;"+ empPassword
											+ "&quot;,&quot;"+ mobileNo
									        + "&quot;,&quot;"+ employee_id
											+ "&quot;,&quot;"+ accountNo
											+ "&quot;,&quot;"+ branchName
											+ "&quot;,&quot;"+ ifsccode
											+ "&quot;,&quot;"+ bankName
											+ "&quot;,&quot;"+ designation
											+ "&quot;,&quot;"+ experienceDetails
											+ "&quot;,&quot;"+ totalExperience
											+ "&quot;,&quot;"+ currentSallary
											+ "&quot;,&quot;"+ previousSallary
											+ "&quot;,&quot;"+ alternateMobileNo
											+ "&quot;,&quot;"+ email
											+ "&quot;,&quot;"+ address
											+ "&quot;,&quot;"+ city
											+ "&quot;,&quot;"+ dateofbirth    
											+ "&quot;,&quot;"+ registration_date    
											+ "&quot;,&quot;"+ emp_enableDisableStatus
											+ "&quot;"
											+ ");'>Edit</a></li>"
											+ "<li role='presentation'><a role='menuitem' tabindex='-1' href='# ' onclick='return enableEmployeeDetails("
											+ "&quot;"
											+ row+ "&quot;"
											+ ",&quot;"+ fullName
											+ "&quot;,&quot;"+ userName
											+ "&quot;,&quot;"+ empPassword
											+ "&quot;,&quot;"+ mobileNo
											+ "&quot;,&quot;"+ employee_id
											+ "&quot;,&quot;"+ accountNo
											+ "&quot;,&quot;"+ branchName
											+ "&quot;,&quot;"+ ifsccode
											+ "&quot;,&quot;"+ bankName
											+ "&quot;,&quot;"+ designation
											+ "&quot;,&quot;"+ experienceDetails
											+ "&quot;,&quot;"+ totalExperience
											+ "&quot;,&quot;"+ currentSallary
											+ "&quot;,&quot;"+ previousSallary
											+ "&quot;,&quot;"+ alternateMobileNo
											+ "&quot;,&quot;"+ email
											+ "&quot;,&quot;"+ address
											+ "&quot;,&quot;"+ city
											+ "&quot;,&quot;"+ dateofbirth
											+ "&quot;,&quot;"+ registration_date    
											+ "&quot;,&quot;"+ emp_enableDisableStatus
											+ "&quot;"
											+ ");'>Enable</a></li>"
											+ "<li role='presentation'><a role='menuitem' tabindex='-1' href='# ' onclick='return disableEmployeeDetails("
											+ "&quot;"
											+ row+ "&quot;"
											+ ",&quot;"+ fullName
											+ "&quot;,&quot;"+ userName
											+ "&quot;,&quot;"+ empPassword
											+ "&quot;,&quot;"+ mobileNo
											+ "&quot;,&quot;"+ employee_id
											+ "&quot;,&quot;"+ accountNo
											+ "&quot;,&quot;"+ branchName
											+ "&quot;,&quot;"+ ifsccode
											+ "&quot;,&quot;"+ bankName
											+ "&quot;,&quot;"+ designation
											+ "&quot;,&quot;"+ experienceDetails
											+ "&quot;,&quot;"+ totalExperience
											+ "&quot;,&quot;"+ currentSallary
											+ "&quot;,&quot;"+ previousSallary
											+ "&quot;,&quot;"+ alternateMobileNo
											+ "&quot;,&quot;"+ email
											+ "&quot;,&quot;"+ address
											+ "&quot;,&quot;"+ city
											+ "&quot;,&quot;"+ dateofbirth
											+ "&quot;,&quot;"+ registration_date    
											+ "&quot;,&quot;"+ emp_enableDisableStatus
											+ "&quot;"
											+ ");'>Disable</a></li>"
											+ "<li role='presentation'><a role='menuitem' tabindex='-1' href='# ' onclick='return deleteEmployeeDetails("
											+ "&quot;"
											+ row+ "&quot;"
											+ ",&quot;"+ fullName
											+ "&quot;,&quot;"+ userName
											+ "&quot;,&quot;"+ empPassword
											+ "&quot;,&quot;"+ mobileNo
											+ "&quot;,&quot;"+ employee_id
											+ "&quot;,&quot;"+ accountNo
											+ "&quot;,&quot;"+ branchName
											+ "&quot;,&quot;"+ ifsccode
											+ "&quot;,&quot;"+ bankName
											+ "&quot;,&quot;"+ designation
											+ "&quot;,&quot;"+ experienceDetails
											+ "&quot;,&quot;"+ totalExperience
											+ "&quot;,&quot;"+ currentSallary
											+ "&quot;,&quot;"+ previousSallary
											+ "&quot;,&quot;"+ alternateMobileNo
											+ "&quot;,&quot;"+ email
											+ "&quot;,&quot;"+ address
											+ "&quot;,&quot;"+ city
											+ "&quot;,&quot;"+ dateofbirth
											+ "&quot;,&quot;"+ registration_date    
											+ "&quot;,&quot;"+ emp_enableDisableStatus
											+ "&quot;"
											+ ");'>Delete</a></li>"
											+ " </ul></div>" + "</td>");
                    clearAllFields();
                   /* var date = new Date();
					document.getElementById("dateOfBirth").value = (date.getMonth() + 1) + '/' + date.getDate() + '/' + date.getFullYear();
					
*/
					$("#employeeHiddenId").val("");
					$("#editselectedMobileNo").val("");
					$("#editselectedUserName").val("");
					$("#employeeEnableDisableStatus").val("");
					$("#employeeSaveButton").prop('disabled', false);
					$("#empUpdateButton").prop('disabled', true);
					
					$("#employeeUpdateSuccess").slideDown();
					$("#employeeUpdateSuccess").fadeOut(3000);

				},
				error : function(result) {
					alert('error' + JSON.stringify(result));
					return false;
				}

			});

}

function savePermissionType()
{

	
	if ($("#permission_Type").val() == "")
	{
       $("#permissionTypeSelect").slideDown();
       $("#permissionTypeSelect").fadeOut();
       $("#permission_Type").focus();
       return false;
	}
	
	var permission_Type = $("#permission_Type").val();

	var permissionTypeFormData = $("#permissionTypeForm").serialize();

	$.ajax({
		url : 'saveOrUpdate_PermissionType',
		type : "POST",
		dataType : 'json',
		data : permissionTypeFormData,
		async : false,
		success : function(responce) 
		{  
			var	permissionTypeTableRowId = JSON.stringify(responce);
			permissionTypeTableRowId = permissionTypeTableRowId.slice(1, -1);
			
				var oTable = $('#permissionTypeTable').dataTable();	
				var number = parseInt(oTable.fnGetData().length) + 1;
				oTable
				.fnAddData([
				            number,
				            permission_Type,
				            "<td ><div class='dropdown' >" 
				            +"<a class='dropdown-toggle' id='menu1' type='button' data-toggle='dropdown' style=' width: 100%; display: block; text-align: center; cursor:pointer;'>"
				            +"<i class='fa fa-ellipsis-v'></i></a>"
				            +"<ul class='dropdown-menu' role='menu' aria-labelledby='menu1'>"
				            +"<li role='presentation'><a role='menuitem' tabindex='-1' href='# ' onclick='return editTeamDetails("
							+"&quot;"
							+ number +"&quot;"
							+ ",&quot;"+permission_Type
							+ "&quot;"
							+");'>Update</a></li>"
							+" </ul></div>"
				            +"</td>",]);
				
				 
			$("#permissionTypesuccess").slideDown();
			$("#permissionTypesuccess").fadeOut(3000);
			
			var title   =document.getElementById('permission_Type').value;
	        var node = document.createElement('div');        
	        node.innerHTML = '<input type="checkbox" id="' + permissionTypeTableRowId + '" name="' + permissionTypeTableRowId + '"><label for="' + permissionTypeTableRowId + '">'+ title +'</label>';       
	        document.getElementById('permissionCheckBox').appendChild(node); 
	        $("#permission_Type").val("");
			
		},
		error : function(errorThrown) {
			console.log("Error: " + errorThrown);
		}
	});
}

function loadEmployeeAssignPermissionDetailsByEmp_Id(emp_id) 
{
	if (emp_id == '0') 
	{
		
		$('input[type="checkbox"]:checked').prop('checked',false);
		return false;
	}

	$.ajax({
		dataType : 'json',
		url : "employeeAssignPermissionAssociation",
		type : "GET",
		data : "employee_id=" + emp_id,
		success : function(data) {
			
			$("#association").show();

			var jsonData = JSON.parse(JSON.stringify(data));

			var counter1 = jsonData.permissionList;
			var cntForiterateobj1 = JSON.parse(counter1);
			for (var c = 0; c < cntForiterateobj1.length; c++) {

				$('#' + cntForiterateobj1[c].id).prop('checked', false);
			}

			var counter = jsonData.userPermission;
			var cntForiterateobj = JSON.parse(counter);
			for (var c = 0; c < cntForiterateobj.length; c++) {

				$('#' + cntForiterateobj[c].permissionType.id).prop('checked',
						true);
			}

		},
		error : function(xhr, ajaxOptions, thrownError) {
			alert("err" + thrownError + xhr.responseText);
		}
	});

}

function saveUserPermissionTypeAssociation() 
{

	employeeId = $("#employee_lst").val();

	if (employeeId == "0") 
	{
       
     	$("#selectEmployee").slideDown();
		$("#selectEmployee").fadeOut(3000);
		$("#employee_lst").focus();
         
		return;
	}

	document.getElementById('light').style.display = 'block';
	document.getElementById('fade').style.display = 'block';
	
	var formData = $("#EmployeeUserPermissionTypeAssociationForm").serialize();
	formData += "&employee_Id=" + encodeURIComponent(employeeId);

	$.ajax({
		url : "saveEmployeeUserPermissionTypeAssociation",
		type : "GET",
		data : formData,
		success : function(data) 
		{

			$('input[type="checkbox"]:checked').prop('checked',false);
			//$("#association").hide();
			$("#employee_lst").val("0");

			$("#permissionAssingsuccess").slideDown();
			$("#permissionAssingsuccess").fadeOut(3000);
			
			document.getElementById('light').style.display = 'none';
			document.getElementById('fade').style.display = 'none';

		},
		error : function(xhr, ajaxOptions, thrownError) {
			alert("err" + thrownError + xhr.responseText);
		}
	});


}
function datePiker()
{	
	$("#dateOfBirth").datepicker({
		dateFormat : 'dd/mm/yy',
		changeMonth : true,
		changeYear : true,
		yearRange : '1980:2099'
	}).datepicker('setDate', new Date());
}



function printEmployeeDetails()
{

	var fDate = $("#fDate").val();
	var tDate = $("#tdate").val();

	if(fDate.trim()=="" ||fDate== null)
	{

		$("#fDateselect").slideDown();
		$("#fDateselect").fadeOut(3000);
		return false;
	}
	if(tDate.trim()=="" ||tDate== null)
	{

		$("#tdateselect").slideDown();
		$("#tdateselect").fadeOut(3000);
		return false;

	}
	var oMyForm = new FormData();
	var arr = {
			fromDate :$("#fDate").val(),
			toDate:$("#tdate").val()
	};
	oMyForm.append("jsonObjectData", JSON.stringify(arr));

	$("#printEmployeeDetailsButton").prop('disabled', true);
     spinnerOn();
	$.ajax({
		url : "printEmployeeReport",
		type : "POST",
		data :oMyForm,
		processData: false,
		contentType: false,
		success : function(data) 
		{
			window.location.href = 'Download?path='+data;
			$("#printEmployeeDetailsButton").prop('disabled',false);
			$("#fDate").val("");
			$("#tdate").val("");

			spinnerOff();
		},
		error : function(xhr, ajaxOptions, thrownError) {
			console.log("Error occured");
		}
	});


}

function printEmployeeRevenueDetails()
{

	var empId = $("#employee_lst").val();
	var packageId = $("#packageName").val();

	if(empId == "0")
	{
		$("#selectEmployee").slideDown();
		$("#selectEmployee").fadeOut(3000);
		$("#employee_lst").focus();
		return false;
	}
	if(packageId == "0")
	{
		$("#selectPackage").slideDown();
		$("#selectPackage").fadeOut(3000);
		$("#packageName").focus();
		return false;
	}
   $("#printEmployeeRevenueDetailsButton").prop('disabled',true);
   spinnerOn();
	$.ajax({

		url : "employeeWiseRevenueDeatils",
		type : "POST",
		data :{packageId:"" +packageId , empId: ""+ empId},
		success : function(data) {

			window.location.href = 'Download?path='+data;
			$("#printEmployeeRevenueDetailsButton").prop('disabled',false);
			$("#employee_lst").val("0");
			$("#packageName").val("0");
			
			spinnerOff();
		},
		error : function(xhr, ajaxOptions, thrownError) {
			console.log("Error occured");
		}
	});


}

function printEmpoyeeWiseMemberDetails(){


	var empId = $("#employee_lst1").val();
	if(empId == "0")
	{
		$("#select_Employee").slideDown();
		$("#select_Employee").fadeOut(3000);
		$("#employee_lst1").focus();
		return false;

	}
	$("#printEmployeeWiseMemnerDetailsButton").prop('disabled',true);
	spinnerOn();
	$.ajax({
		url : "printEmpWiseMemberReport",
		type : "POST",
		data :{empId : ""+ empId},
		success : function(data) {

			window.location.href = 'Download?path='+data;
		    $("#printEmployeeWiseMemnerDetailsButton").prop('disabled',false);
			$("#employee_lst1").val("0");
			spinnerOff();
		},
		error : function(xhr, ajaxOptions, thrownError) {
			console.log("Error occured");
		}
	});


}

function clearAllFields()
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
	$("#registrationDate").val("");
	$("#bankName").val("");
	$("#ifcCode").val("");
	$("#branchName").val("");
	$("#accountNo").val("");
	
	var date = new Date();
	document.getElementById("dateOfBirth").value = (date.getMonth() + 1) + '/' + date.getDate() + '/' + date.getFullYear();
	document.getElementById("registrationDate").value = (date.getMonth() + 1) + '/' + date.getDate() + '/' + date.getFullYear();
	

}
function cheakAndUncheckAllCheckBox()
{
	if($('#checkUncheack').is(':checked')) 
	{
		$('input:checkbox').prop('checked',true);
	}
	else
	{
		$('input:checkbox').prop('checked', false);
	}

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

