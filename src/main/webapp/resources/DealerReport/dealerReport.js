function printDealerDetails(){
	
	
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
	$("#printDealerDetailsButton").prop('disabled',true);
	spinnerOn();
				var oMyForm = new FormData();
				var arr = {
							fromDate :$("#fDate").val(),
					      toDate:$("#tdate").val()
				};
				oMyForm.append("jsonObjectData", JSON.stringify(arr));
				
				
				$.ajax({
					 dataType : 'text',
					url : "printDealerReport",
					type : "POST",
					data :oMyForm,
					processData: false,
					contentType: false,
					success : function(data) 
					{
						window.location.href = 'Download?path='+data;
						$("#fDate").val("");
						$("#tdate").val("");

						$("#printDealerDetailsButton").prop('disabled',false);
						spinnerOff();
					},
					error : function(xhr, ajaxOptions, thrownError) {
						console.log("Error occured");
					}
				});
	
	
}

function printDealerRevenueDetails(){
	
	var dealerId = $("#dealerName").val();
	var packageId = $("#packageName").val();
	$("#printDealserRevenueDeatilsButton").prop('disabled',true);
 spinnerOn();
	$.ajax({
		
		url : "printDealerRevenueReport",
		type : "POST",
		data :{packageId:"" +packageId , dealerId: ""+ dealerId},
		success : function(data) {
		
			window.location.href = 'Download?path='+data;
			 $("#dealerName").val("0");
			$("#packageName").val("0");
			$("#printDealserRevenueDeatilsButton").prop('disabled',false);
			spinnerOff();
		},
		error : function(xhr, ajaxOptions, thrownError) {
			console.log("Error occured");
		}
	});
	
	
}


function printDealerKitDetails()
 {
	
				var dealerId = $("#dealerName1").val();
				$("#printDealerKitDetailsButton").prop('disabled',true);
				spinnerOn();
				$.ajax({
					url : "printDealerKitReport",
					type : "POST",
					data :{
						dealerId : ""+ dealerId},
					success : function(data) {
					
						window.location.href = 'Download?path='+data;
						 $("#dealerName1").val("0");
						$("#printDealerKitDetailsButton").prop('disabled',false);
						spinnerOff();
					},
					error : function(xhr, ajaxOptions, thrownError) {
						
					}
				});
	
	
}

function printSubDealerReportDetails()
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
				$("#printSubDealerReportDetailsButton").prop('disabled',true);
				
				spinnerOn();
				
				$.ajax({
					 dataType : 'text',
					url : "printSubDealerReport",
					type : "POST",
					data :oMyForm,
					processData: false,
					contentType: false,
					success : function(data) {
						window.location.href = 'Download?path='+data;
						 $("#fDate").val("");
						 $("#tdate").val("");
						$("#printSubDealerReportDetailsButton").prop('disabled',false);
						spinnerOff();
					},
					error : function(xhr, ajaxOptions, thrownError) {
						console.log("Error occured");
					}
				});
	
	

	
	
	
}

function printSubDealerRevenueDetails()
{
	var dealerId = $("#dealerName").val();
	var packageId = $("#packageName").val();
	
 $("#printSubDealerRevenueDetailsButton").prop('disabled',true);
	spinnerOn();
	$.ajax({
		
		url : "printSubDealerRevenueReport",
		type : "POST",
		data :{packageId:"" +packageId , dealerId: ""+ dealerId},
		success : function(data) {
		
			window.location.href = 'Download?path='+data;
			$("#dealerName").val("0");
		    $("#packageName").val("0");
			 $("#printSubDealerRevenueDetailsButton").prop('disabled',false);
			spinnerOff();
		},
		error : function(xhr, ajaxOptions, thrownError) {
			console.log("Error occured");
		}
	});
	
}

function printDealerWiseMemberDetails(){
	

	
	var dealerId = $("#dealerId").val();
	$("#printDealerWiseMemberDetailsButton").prop('disabled', true);
	if(dealerId == "0")
		{
		  $("#selectDealer").slideDown();
		  $("#selectDealer").fadeOut(3000);
		  
		  return false;
		}
	spinnerOn();
	$.ajax({
				url : "printMemberByDealer",
				type : "GET",
				data : {
					dealerId :dealerId
				},
				success : function(data)
				{

					window.location.href = 'Download?path='+data;
					$("#dealerId").val("0");
					$("#printDealerWiseMemberDetailsButton").prop('disabled', false);
					spinnerOff();

				},
				error : function(xhr, ajaxOptions,
						thrownError) {
					console.log("err" + thrownError
							+ xhr.responseText);

					return false;
				}
			});

}



function printSubDealerWiseMemberDetails()
{
	
    var dealerId = $("#subDealer_id").val();
	$("#printSubDealerWiseMemberDetailsButton").prop('disabled',true);
	spinnerOn();
	$.ajax({
				url : "printMemberBySubDealer",
				type : "GET",
				data : {
					dealerId :dealerId
				},
				success : function(data) {

					window.location.href = 'Download?path='+data;
					$("#subDealer_id").val("0");
					$("#printSubDealerWiseMemberDetailsButton").prop('disabled',false);
					spinnerOff();

				},
				error : function(xhr, ajaxOptions,
						thrownError) {
					console.log("err" + thrownError
							+ xhr.responseText);

					return false;
				}
			});
}

function printSubDealerKitDetails()
{

	
	var dealerId = $("#dealerName1").val();
	$("#printSubDealerKitDetailsButton").prop('disabled',true);
	spinnerOn();
	$.ajax({
		url : "printSubDealerKitReport",
		type : "POST",
		data :{
			dealerId : ""+ dealerId},
		success : function(data) {
		
			window.location.href = 'Download?path='+data;
			$("#dealerName1").val("0");
			$("#printSubDealerKitDetailsButton").prop('disabled',false);
			spinnerOff();
		},
		error : function(xhr, ajaxOptions, thrownError) {
			
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
