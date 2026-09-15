function getRevenueByEmployee()
{
	


	
	var packageId = $("#packageName").val();
	if (packageId == '0' || packageId == "") {

		$("#packageNameSelect").slideDown();
		$("#packageNameSelect").fadeOut(15000);
		$("#packageName").focus();   
		
		 
		return false;
	}
	
	var employeeId=$("#employeeName").val();
	
	
	if (employeeId == '0' || employeeId == "") {

		$("#dealerSelect").slideDown();
		$("#dealerSelect").fadeOut(15000);
		$("#employeeName").focus();  
		return false;
	}
	$("#getRevenueByEmployeeButton").prop('disabled',true);
	document.getElementById('light').style.display = 'block';
	document.getElementById('fade').style.display = 'block';

	$.ajax({
		url : "getRevenueByEmployee",
		type : "GET",
		data : {
			packageId : "" + packageId,
			employeeId : "" + employeeId
		},
		success : function(data) {

			$("#revResult").html(data);
			$("#employeeName").val("0");
		    $("#packageName").val("0");
			
			$("#getRevenueByEmployeeButton").prop('disabled',false);
			
			document.getElementById('light').style.display = 'none';
			document.getElementById('fade').style.display = 'none';

		},
		error : function(xhr, ajaxOptions, thrownError) {
			console.log("err" + thrownError + xhr.responseText);

			return false;
		}
	});
	


	
	
	/*var employeeId = $("#employeeName").val()
	if (employeeId == '0' || employeeId == "") {

		return false;
	}
	
	
	
	document.getElementById('light').style.display = 'block';
	document.getElementById('fade').style.display = 'block';

	$.ajax({
		url : "getRevenueByEmployee",
		type : "GET",
		data : {
			employeeId : "" + employeeId
		},
		success : function(data) {

			$("#KitResult").html(data);
			document.getElementById('light').style.display = 'none';
			document.getElementById('fade').style.display = 'none';

		},
		error : function(xhr, ajaxOptions, thrownError) {
			console.log("err" + thrownError + xhr.responseText);

			return false;
		}
	});*/
	


}