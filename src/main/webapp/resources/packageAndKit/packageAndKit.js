function returnKit()
{
	
	
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



function assignKit()
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




//   old code


function savepackage() {

	var table = $("#example1").DataTable();
	var r = 1;

	table.column(1).data().each(function(value, index) {
		r++;
	});

	var packageName = $("#packageName").val();
	var amount = $("#price").val();
	var no_of_msg = $("#no_of_msg").val();
	var no_of_email = $("#no_of_email").val();
	var no_of_profiles =$("#no_of_profiles").val() ;
	var no_of_days=$("#no_of_days").val() ;

	if (packageName == "" || packageName == null) {
		$("#packageNameSelect").slideDown();
		$("#packageNameSelect").fadeOut(5000);
		$("#packageName").focus();

		return false;

	}

	if (amount == "" || amount == null) {
		$("#amountSelect").slideDown();
		$("#amountSelect").fadeOut(5000);
		$("#price").focus();

		return false;

	}

	for (var i = 0; i < allPackageName.length; i++) {
		if (allPackageName[i].toUpperCase() === packageName.toUpperCase()) {

			$("#packageAvailable").slideDown();
			$("#packageAvailable").fadeOut(5000);

			return false;
		}
	}

	$.ajax({
		url : "savePackage",
		type : "POST",
		data : {
			no_of_profiles : "" + no_of_profiles,
			no_of_email : "" + no_of_email,
			no_of_msg : "" + no_of_msg,
			packageName : "" + packageName,
			amount : "" + amount,
			no_of_days : ""+no_of_days
		}, 
		success : function(data) {

			$("#packageName").val("");
			$("#price").val("");
			$("#successSelect").slideDown();
			$("#successSelect").fadeOut(5000);
			table.row.add([ r, packageName, amount , no_of_msg , no_of_email ,no_of_profiles ,no_of_days ]).draw();
		},
		error : function(xhr, ajaxOptions, thrownError) {
			console.log("err" + thrownError + xhr.responseText);

			return false;
		}
	});

}

function saveKit() {

	var userName = $("#userName").val();
	var Password = $("#password").val();
	var packageName = $("#packageName").val();

	/*
	 * var table = $("#example1").DataTable(); var r = 1;
	 * 
	 * table.column(1).data().each(function(value, index) { r++; });
	 */

	if (packageName == "" || packageName == '0') {
		$("#packageNameSelect").slideDown();
		$("#packageNameSelect").fadeOut(5000);
		$("#packageName").focus();

		return false;

	}
	if (userName == "" || userName == null) {
		$("#userNameSelect").slideDown();
		$("#userNameSelect").fadeOut(5000);
		$("#userName").focus();

		return false;

	}

	if (Password == "" || Password == null) {
		$("#PasswordSelect").slideDown();
		$("#PasswordSelect").fadeOut(5000);
		$("#password").focus();

		return false;

	}

	$.ajax({
		url : "saveKit",
		type : "POST",
		data : {
			userName : "" + userName,
			Password : "" + Password,
			packageName : "" + packageName,
		},
		success : function(data) {

			$("#userName").val("");
			$("#password").val("");
			$("#packageName").val("0");
			$("#successSelect").slideDown();
			$("#successSelect").fadeOut(5000);
			// table.row.add([ r, userName, Password ]).draw();
		},
		error : function(xhr, ajaxOptions, thrownError) {
			console.log("err" + thrownError + xhr.responseText);

			return false;
		}
	});

}

function viewKitData() {

	var packageId = $("#packageName2").val()
	if (packageId == '0' || packageId == "") {

		return false;
	}

	document.getElementById('light').style.display = 'block';
	document.getElementById('fade').style.display = 'block';

	$.ajax({
		url : "kitByType",
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

/*
 * $("#example1").dataTable( { "bProcessing": true, "bServerSide": true, "sort":
 * "position", "dataType": 'json', "bStateSave": false, "iDisplayLength": 10,
 * //We will use below variable to track page number on server side(For more
 * information visit: http://legacy.datatables.net/usage/options#iDisplayStart)
 * "iDisplayStart": 0, "fnDrawCallback": function () { }, "sAjaxSource":
 * "getKitDataByPackage?packageId="+packageId,
 * 
 * 
 * "aoColumns": [ { "mData": "kit_username" }, { "mData": "kit_password" }, ] } );
 * 
 * $('#example1').DataTable({ 'ajax': { "dataType": 'json', "type" : "GET",
 * "url" : 'getKitDataByPackage', data : { packageId : "" + packageId, }, },
 * 'columns': [ {"data" : "kit_id"}, {"data" : "kit_username"}, {"data" :
 * "kit_password"}, {"data" : "package_id.package_Name"}, ]
 * 
 * });
 * 
 * 
 * 
 * $('#example1').dataTable({ "bServerSide": true, "bProcessing": true,
 * "deferRender": true, "sAjaxSource":
 * "getKitDataByPackage?packageId="+packageId, "sAjaxDataProp": "aoData",
 * "fnServerData": function (sSource, aoData, fnCallback) { alert(aoData);
 * $.ajax({ "dataType": 'json', "type": "GET", "url": sSource, "data": aoData,
 * "success":fnCallback }); }, "aoColumns": [ { "mData": "kit_username" }, {
 * "mData": "kit_password" } ], "columnDefs": [{"targets": [0], "visible":
 * false, "searchable": false}], "sDom": 'W<"clear">lfrtip',
 * "oColumnFilterWidgets": { "aiExclude": [0,1] } });
 */

/*
 * $.ajax({ dataType : 'json', url : 'getKitDataByPackage', type : "POST",
 * dataType : 'text', data : "packageId=" + packageId, async : false, success :
 * function(data) {
 * 
 * 
 * var jsonData = JSON.parse(JSON.stringify(data)); var info =
 * JSON.parse(JSON.stringify(data));
 * 
 * var counterdatatable = 1; var oTable = $('#example1').dataTable();
 * $("#example1 tr").each(function() {
 * 
 * for (var pz = 0; pz < oTable.fnGetData().length; pz++) {
 * 
 * if (counterdatatable == 1) { } else { var row = $(this).closest("tr").get(0);
 * oTable.fnDeleteRow(oTable.fnGetPosition(row)); } counterdatatable =
 * counterdatatable + 1; } });
 * 
 * var xx = JSON.parse(info);
 * 
 * 
 * 
 * var srNo = 1; for (key1 in xx) { var xx1 = JSON.parse(xx[key1]); for (key2 in
 * xx1) { oTable.fnAddData([ srNo, xx1[key2].kit_username,
 * xx1[key2].kit_password , xx1[key2].package_id.package_Name , ]);
 * 
 * srNo = srNo + 1; }
 * 
 *  } }, error : function(errorThrown) { console.log("Error: " + errorThrown); }
 * });
 */

function updatekitinfo()
{
	
	var kitid = $("#hiddenkit").val();  
	var packageName = $("#uppackageName").val();  
	var kituserName = $("#kitId").val();
	var kitpassword =$("#kitpasswordd").val();


	
	if(packageName=='0' ||packageName=='0')
		{
		
		$("#updatepackselect").slideDown();
		$("#updatepackselect").fadeOut(5000);
		return false;
		}
	$.ajax({
		url : "updateKitByAdmin",
		type : "POST",
		data : {
			kituserName : "" + kituserName,
			kitpassword : "" + kitpassword,
			kitid : "" + kitid,
			packageName : "" + packageName
		},
		success : function(data) {

			
			$("#updateSuccess").slideDown();
			$("#updateSuccess").fadeOut(5000);
			
		
			

		},
		error : function(xhr, ajaxOptions, thrownError) {
			console.log("err" + thrownError + xhr.responseText);

			return false;
		}
	});
	
}
function deleteKitInf()
{
	
	
	
	var status=$("#delkitstatus").val(); 
	var kituserName=$("#delkitId").val();
	var kitpassword=$("#delkitpasswordd").val(); 
	
	if(status.toUpperCase()==='Active'.toUpperCase() )
		{
		
			$("#deleteError").slideDown();
			$("#deleteError").fadeOut(5000);
			return false;
		
		}

	$.ajax({
		url : "Deletekit",
		type : "POST",
		data : {
			kituserName : "" + kituserName,
			kitpassword : "" + kitpassword
		},
		success : function(data) {

			
			$("#deleteSuccess").slideDown();
			$("#deleteSuccess").fadeOut(5000);
			
		
			

		},
		error : function(xhr, ajaxOptions, thrownError) {
			console.log("err" + thrownError + xhr.responseText);

			return false;
		}
	});


}

function updatepackageinfo()
{
	
	

	var packageId = $("#hiddenPackId").val();
	var packName = $("#packageName1").val();
	var price = $("#price1").val();
	var messages = $("#noOfMessage").val();
	var emails = $("#noOfEmails").val();
	var profiles = $("#noOfProfiles").val();
	var days = $("#noOfDays").val(); 
	
	if(packName=="" || packName==null)
		{
		
				$("#packSelect").slideDown();
				$("#packSelect").fadeOut(5000);
				return false;
		}
		if(price=="" || price==null)
		{
			$("#priceSelect").slideDown();
			$("#priceSelect").fadeOut(5000);
			return false;
		}
		if(messages=="" || messages==null)
		{
			$("#messageSelect").slideDown();
			$("#messageSelect").fadeOut(5000);
			return false;
		}
		if(emails=="" || emails==null)
		{
			$("#emailSelect").slideDown();
			$("#emailSelect").fadeOut(5000);
			return false;
		}
		if(profiles=="" || profiles==null)
		{
			
			$("#profileSelect").slideDown();
			$("#profileSelect").fadeOut(5000);
			return false;
		}
		if(days=="" || days==null)
		{
			$("#daySelect").slideDown();
			$("#daySelect").fadeOut(5000);
			return false;
		}
	
	$.ajax({
		url : "updatePackageDetails",
		type : "POST",
		data : {
			packageId : "" + packageId,
			packName : "" + packName,
			price : "" + price,
			messages : "" + messages,
			emails : "" + emails,
			profiles : "" + profiles,
			days : "" + days
			
		},
		success : function(data) {

			
			$("#packSuccess").slideDown();
			$("#packSuccess").fadeOut(5000);
			
		},
		error : function(xhr, ajaxOptions, thrownError) {
			console.log("err" + thrownError + xhr.responseText);

			return false;
		}
	});
	
	

}