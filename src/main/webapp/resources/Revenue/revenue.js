

function getRevenue()
{

	
	var packageId = $("#packageName").val();
	if (packageId == '0' || packageId == "") {

		return false;
	}
	
	
	$("#getRevenueButton").prop('disabled',true);
	document.getElementById('light').style.display = 'block';
	document.getElementById('fade').style.display = 'block';

	$.ajax({
		url : "getRevenueByPackage",
		type : "GET",
		data : {
			packageId : "" + packageId
		},
		success : function(data) {

			$("#KitResult").html(data);
			$("#packageName").val("0");
			$("#getRevenueButton").prop('disabled',false);
			document.getElementById('light').style.display = 'none';
			document.getElementById('fade').style.display = 'none';

		},
		error : function(xhr, ajaxOptions, thrownError) {
			console.log("err" + thrownError + xhr.responseText);

			return false;
		}
	});
	

}




function getRevenueByDealerAndPackage()
{

	
	var packageId = $("#packageName").val();
	if (packageId == '0' || packageId == "") {

		$("#packageNameSelect").slideDown();
		$("#packageNameSelect").fadeOut(15000);
		$("#packageName").focus();   
		
		 
		return false;
	}
	
	var dealerId=$("#dealerName").val();
	
	
	if (dealerId == '0' || dealerId == "") {

		$("#dealerSelect").slideDown();
		$("#dealerSelect").fadeOut(15000);
		$("#dealerName").focus();  
		return false;
	}
	$("#getRevenueByDealerAndPackageButton").prop('disabled',true);
	document.getElementById('light').style.display = 'block';
	document.getElementById('fade').style.display = 'block';

	$.ajax({
		url : "getRevenueByPackageIdAndDealerId",
		type : "GET",
		data : {
			packageId : "" + packageId,
			dealerId : "" + dealerId
		},
		success : function(data) {

			 $("#revResult").html(data);
			 $("#packageName").val("0");
			 $("#dealerName").val("0");
			 $("#getRevenueByDealerAndPackageButton").prop('disabled',false);
			document.getElementById('light').style.display = 'none';
			document.getElementById('fade').style.display = 'none';

		},
		error : function(xhr, ajaxOptions, thrownError) {
			console.log("err" + thrownError + xhr.responseText);

			return false;
		}
	});
	

}



function getDealerDiscount()
{

	
	var packageId = $("#packageName").val();
	if (packageId == '0' || packageId == "") {

		$("#packageNameSelect").slideDown();
		$("#packageNameSelect").fadeOut(15000);
		$("#packageName").focus();   
		
		 
		return false;
	}
	
	var dealerId=$("#dealerName").val();
	
	
	if (dealerId == '0' || dealerId == "") {

		$("#dealerSelect").slideDown();
		$("#dealerSelect").fadeOut(15000);
		$("#dealerName").focus();  
		return false;
	}
	
	$("#getDealerDiscountButton").prop('disabled',true);
	document.getElementById('light').style.display = 'block';
	document.getElementById('fade').style.display = 'block';

	$.ajax({
		url : "getDiscountByPackageIdAndDealerId",
		type : "GET",
		data : {
			packageId : "" + packageId,
			dealerId : "" + dealerId
		},
		success : function(data) {

			$("#revResult").html(data);
			$("#packageName").val("0");
			$("#dealerName").val("0");
			$("#getDealerDiscountButton").prop('disabled',false);
			document.getElementById('light').style.display = 'none';
			document.getElementById('fade').style.display = 'none';

		},
		error : function(xhr, ajaxOptions, thrownError) {
			console.log("err" + thrownError + xhr.responseText);

			return false;
		}
	});
	

}




function getRevenueByDealer()
{

	
	var packageId = $("#packageName").val()
	if (packageId == '0' || packageId == "") {

		return false;
	}
	
	
	
	document.getElementById('light').style.display = 'block';
	document.getElementById('fade').style.display = 'block';

	$.ajax({
		url : "getRevenueByPackageAndDealer",
		type : "GET",
		data : {
			packageId : "" + packageId
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
	});
	

}



function updateSaleKitDealer()
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
		url : "updateSaleKitDealer",
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




function updateUserDealerRelationship()
{
	

	var regId = $("#regId").val();
	
	if (regId == "") {
		$("#regIdSelect").slideDown();
		$("#regIdSelect").fadeOut(15000);
		$("#regId").focus()
	
		return false;
	}
	
	
	$.ajax({
		url : "updateuserDealerAssociation",
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

function assignKitToSubDealer()
{
	
	var packageName = $("#packageNamed").val();
	var dealerName = $("#dealerName").val();
	var noOfKIt = $("#noOfKIt").val();




	var packName=$("#packageNamed option:selected").text();
	var dealName=$("#dealerName option:selected").text();
	
	var table = $('#example5').DataTable();
	
	if (packageName == "" || packageName == '0') {
		$("#packageNameSelect1").slideDown();
		$("#packageNameSelect1").fadeOut(5000);
		$("#packageNamed").focus();
		return false;

	}
	if (dealerName == "" || dealerName == '0') {
		$("#dealerNameSelect").slideDown();
		$("#dealerNameSelect").fadeOut(5000);
		$("#dealerName").focus();
		return false;
		
	}

	if (noOfKIt == "" || noOfKIt == null) {
		$("#noOfKItSelect").slideDown();
		$("#noOfKItSelect").fadeOut(5000);
		$("#noOfKIt").focus();
		return false;
}

	
	
	$.ajax({
		url : "assignKitToDealer",
		type : "POST",
		data : {
			packageName : "" + packageName,
			dealerName : "" + dealerName,
			noOfKIt : "" + noOfKIt
		}, 
		success : function(data) {

			if(data=='success')
				{
					$("#packageNamed").val("0");
					$("#dealerName").val("0");
					$("#noOfKIt").val("");
					$("#kisAssignSelect").slideDown();
					$("#kisAssignSelect").fadeOut(5000);
					
					location.reload(); 
					
				
				}
			else
				{
				
				alert('Not Assigned Successfully');
				}
			
		},
		error : function(xhr, ajaxOptions, thrownError) {
			console.log("err" + thrownError + xhr.responseText);

			return false;
		}
	});
	

}

function returnKitToSubDealer()
{
	
	alert("Return Sub dealer");
	var packageName = $("#packageNamed1").val();
	var dealerName = $("#dealerName1").val();
	var noOfKIt = $("#noOfKIt1").val();

	if (packageName == "" || packageName == '0') {
		$("#packageNameSelect2").slideDown();
		$("#packageNameSelect2").fadeOut(5000);
		$("#packageNamed").focus();
		return false;

	}
	if (dealerName == "" || dealerName == '0') {
		$("#dealerNameSelect1").slideDown();
		$("#dealerNameSelect1").fadeOut(5000);
		$("#dealerName1").focus();
		return false;
		
	}

	if (noOfKIt == "" || noOfKIt == null) {
		$("#noOfKItSelect1").slideDown();
		$("#noOfKItSelect1").fadeOut(5000);
		$("#noOfKIt").focus();
		return false;
}

	
$.ajax({
		url : "returnKit",
		type : "POST",
		data : {
			packageName : "" + packageName,
			dealerName : "" + dealerName,
			noOfKIt : "" + noOfKIt
		}, 
		success : function(data) {

			if(data=='success')
				{
					$("#packageNamed").val("0");
					$("#dealerName1").val("0");
					$("#noOfKIt1").val("");
					$("#kisAssignSelect1").slideDown();
					$("#kisAssignSelect1").fadeOut(5000);
					
					location.reload(); 
				}
			else
				{
				
				alert('Kit Not Return Successfully');
				}
			
		},
		error : function(xhr, ajaxOptions, thrownError) {
			console.log("err" + thrownError + xhr.responseText);

			return false;
		}
	});




}