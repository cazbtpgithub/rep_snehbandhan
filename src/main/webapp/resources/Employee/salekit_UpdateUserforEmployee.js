function updateSaleKitByEmployee()
{
   
	var kitUsername = $("#kidUserName").val();
	if (kitUsername == "") {
		$("#kitSelect").slideDown();
		$("#kitSelect").fadeOut(15000);
		$("#kidUserName").focus()
	
		return false;
	}
	
	var kitPassword = $("#kidPassword").val();
	if (kitPassword == "") {
		$("#passwordSelect").slideDown();
		$("#passwordSelect").fadeOut(15000);
		$("#kidPassword").focus()
		return false;
	}
	
	$.ajax({
		url : "updateSaleKitByEmployee",
		type : "POST",
		data : {
			kitUsername : "" + kitUsername,
			kitPassword : "" + kitPassword
		},
		success : function(data) {

			
			
			if(data=='one')
				{
				
					$("#oneSelect").slideDown();
					$("#oneSelect").fadeOut(15000);
				
				}
			if(data=='two')
			{
				$("#twoSelect").slideDown();
				$("#twoSelect").fadeOut(15000);
			}
			if(data=='success')
			{
			
				$("#successSelect").slideDown();
				$("#successSelect").fadeOut(15000);
				$("#kidUserName").val("");
			   $("#kidPassword").val("");
			}
			

		},
		error : function(xhr, ajaxOptions, thrownError) {
			console.log("err" + thrownError + xhr.responseText);

			return false;
		}
	});




	
}

function updateEmployeeUser()
{

	

	var regId = $("#regId").val();
	
	if (regId == "") {
		$("#regIdSelect").slideDown();
		$("#regIdSelect").fadeOut(15000);
		$("#regId").focus()
	
		return false;
	}
	
	
	$.ajax({
		url : "updateuserEmployeeAssociation",
		type : "POST",
		data : {
			regId : "" + regId
		},
		success : function(data) {
			
			if(data=='one')
			{
			
				$("#oneSelect").slideDown();
				$("#oneSelect").fadeOut(15000);
			
			}
		if(data=='two')
		{
			$("#twoSelect").slideDown();
			$("#twoSelect").fadeOut(15000);
		}
		if(data=='success')
		{
		
			$("#successSelect").slideDown();
			$("#successSelect").fadeOut(15000);
			$("#regId").val("");
		}
		
		
		},
		error : function(xhr, ajaxOptions, thrownError) {
			console.log("err" + thrownError + xhr.responseText);

			return false;
		}
	});




	
}