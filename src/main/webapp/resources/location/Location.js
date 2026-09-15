function saveCountry()
{
	var cname=$("#countryName").val();
	if (cname == "" || cname==null) {
		$("#cnameSelect").slideDown();
		$("#cnameSelect").fadeOut(5000);
		$("#countryName").focus();
		return false;
	}
	for(var i=0;i<allCountryName.length;i++)
	{
			if(allCountryName[i].toUpperCase()===cname.toUpperCase())
				{
				return false;
				}
	}
			var table = $("#example1").DataTable();  
			var r=1;
			table.column(1).data().each(function(value, index) {
			r++;
			});	
	$.ajax({
		url : "saveCountry",
		type : "POST",
		data : {
			cname : "" + cname
		},
		success : function(data) {
			
			$("#countryName").val("");
			$("#countryNamesuccess").slideDown();
			$("#countryNamesuccess").fadeOut(5000);
			
			table.row.add([ r, cname]).draw();
		},
		error : function(xhr, ajaxOptions, thrownError) {
			console.log("err" + thrownError + xhr.responseText);

			return false;
		}
	});
}
function saveState()
{
	var cname=$("#countrynameState").val();
	var stateName=$("#stateName").val();	
	if (cname.trim() == "" || cname=='0') {
		$("#cnameSelect1").slideDown();
		$("#cnameSelect1").fadeOut(5000);
		$("#countrynameState").focus();
		return false;
	}
	if (stateName == "" || stateName==null) {
		$("#stateNameselect").slideDown();
		$("#stateNameselect").fadeOut(5000);
		$("#stateName").focus();
		return false;
	}
	var table = $('#example2').DataTable();
	var r = 1;
	var flag=0;
	     table.column(2).data().each(function(value, index) {
		if (stateName.trim().toUpperCase()=== value.trim().toUpperCase()) {
			flag=1;
		}
		r++;
	});
     if(flag==0)
    	 {
    	 $.ajax({
    			url : "saveState",
    			type : "POST",
    			data : {
    				cname : "" + cname,
    				stateName : "" + stateName
    			},
    			success : function(data) {
    				
    				$("#stateName").val("");
    				$("#countrynameState").val("0");
    				$("#stateNamesuccess").slideDown();
    				$("#stateNamesuccess").fadeOut(5000);
    			},
    			error : function(xhr, ajaxOptions, thrownError) {
    				console.log("err" + thrownError + xhr.responseText);

    				return false;
    			}
    		});
    	 
    	 }
     else
    	 {
    	 	$("#stateNameAvailable").slideDown();
 			$("#stateNameAvailable").fadeOut(5000);
 			$("#stateName").focus();
 			return false;
    	 }
	}
function saveDistrict()
{
	var cname=$("#countryNamedist").val();
	var stateName=$("#stateNameDist").val();
	var distName=$("#districtname").val();
	if (cname == "" || cname=='0') {
		$("#countryNamedistSelect").slideDown();
		$("#countryNamedistSelect").fadeOut(5000);
		$("#countryNamedist").focus();
		return false;
	}
	if (stateName == "" || stateName=='0') {
		$("#stateNameDistselect").slideDown();
		$("#stateNameDistselect").fadeOut(5000);
		$("#stateNameDist").focus();
		return false;
	}   
	if (distName.trim() == "" || distName==null) {
		$("#distNameSelect").slideDown();
		$("#distNameSelect").fadeOut(5000);
		$("#districtname").focus();
		return false;
	}	
	var table = $('#example3').DataTable();
	var r = 1;
	var flag=0;
	     table.column(3).data().each(function(value, index) {
		if (distName.trim().toUpperCase()=== value.trim().toUpperCase()) {
			flag=1;
		}
		r++;
	});
     if(flag==0)
    	 {
		    	 $.ajax({
		    			url : "saveDistrict",
		    			type : "POST",
		    			data : {
		    				cname : "" + cname,
		    				stateName : "" + stateName, 
		    				distName : "" + distName,
		    			},
		    			success : function(data) {
		    				$("#districtname").val("");
		    				$("#countryNamedist").val("0");
		    				$("#stateNameDist").val("0");
		    				$("#suceessdistNameSelect").slideDown();
		    				$("#suceessdistNameSelect").fadeOut(5000);
		    			},
		    			error : function(xhr, ajaxOptions, thrownError) {
		    				console.log("err" + thrownError + xhr.responseText);
		    				return false;
		    			}
		    		});
    	 }
     else
    	 {
    	 	$("#distNameAvailable").slideDown();
 			$("#distNameAvailable").fadeOut(5000);
 			return false;
    	 }
}
function saveSubDistrict() {
	var cname = $("#countryNamesubDist").val();
	var stateName = $("#stateNameSubDist").val();
	var distName = $("#DistNameSubDist").val();
	var subDistName = $("#subdistName").val();
	if (cname == "" || cname == '0') {
		$("#countryNamesubdistSelect").slideDown();
		$("#countryNamesubdistSelect").fadeOut(5000);
		$("#countryNamedist").focus();
		return false;
	}
	if (stateName == "" || stateName == '0') {
		$("#stateNamesubDistselect").slideDown();
		$("#stateNamesubDistselect").fadeOut(5000);
		$("#countryNamesubDist").focus();
		return false;
	}
	if (distName == "" || distName == '0') {
		$("#distNamesubDistSelect").slideDown();
		$("#distNamesubDistSelect").fadeOut(5000);
		$("#DistNameSubDist").focus();
		return false;
	}
	if (subDistName == "" || subDistName == null) {
		$("#subDistNameSelect").slideDown();
		$("#subDistNameSelect").fadeOut(5000);
		$("#subdistName").focus();
		return false;
	}
	var table = $('#example4').DataTable();
	var r = 1;
	var flag = 0;
	table.column(4).data().each(function(value, index) {
		if (subDistName.trim().toUpperCase() === value.trim().toUpperCase()) {
			flag = 1;
		}
		r++;
	});
	if (flag == 0) {
		$.ajax({
			url : "saveSubDistrict",
			type : "POST",
			data : {
				cname : "" + cname,
				stateName : "" + stateName,
				distName : "" + distName,
				subDistName : "" + subDistName
			},
			success : function(data) {
				$("#subdistName").val("");
				$("#countryNamesubDist").val("0");
				$("#stateNameSubDist").val("0");
				$("#DistNameSubDist").val("0");
				$("#subDistNameSelectssuccess").slideDown();
				$("#subDistNameSelectssuccess").fadeOut(5000);
			},
			error : function(xhr, ajaxOptions, thrownError) {
				console.log("err" + thrownError + xhr.responseText);
				return false;
			}
		});

	} else {
		$("#subDistAvailable").slideDown();
			$("#subDistAvailable").fadeOut(5000);
			return false;
	}
}
function loadStateData()
{	
			var countryId=$("#countrynameState").val()
			if(countryId==0)
				{
					return false;
				}
			$.ajax({
				dataType : 'json',
				url : 'getstateByCountry',
				type : "POST",
				dataType : 'text',
				data : "countryId=" + countryId,
				async : false,
				success : function(data) {
					var jsonData = JSON.parse(JSON.stringify(data));
					var info = JSON.parse(JSON.stringify(data));
					var counterdatatable = 1;
					var oTable = $('#example2').dataTable();
					$("#example2 tr").each(function() {
						for (var pz = 0; pz < oTable.fnGetData().length; pz++) {
							if (counterdatatable == 1) {
							} else {
								var row = $(this).closest("tr").get(0);
								oTable.fnDeleteRow(oTable.fnGetPosition(row));
							}
							counterdatatable = counterdatatable + 1;
						}
					});
					var xx = JSON.parse(info);
					var srNo = 1;
					for (key1 in xx) {
						var xx1 = JSON.parse(xx[key1]);
						for (key2 in xx1) {
							   oTable.fnAddData([
									srNo,
									xx1[key2].country.country_Name,
									xx1[key2].state_Name ,]);
							   srNo = srNo + 1;   
						}
					}		
			},
				error : function(errorThrown) {
					console.log("Error: " + errorThrown);
				}
			});
}
function loadDistrict()
{	
	var statId=$("#stateNameDist").val()
	if(statId==0)
		{
			return false;
		}
	//hiddenState
	$.ajax({
		dataType : 'json',
		url : 'getDistrictByState',
		type : "POST",
		dataType : 'text',
		data : "statId=" + statId,
		async : false,
		success : function(data) {
			var jsonData = JSON.parse(JSON.stringify(data));
			var info = JSON.parse(JSON.stringify(data));
			var counterdatatable = 1;
			var oTable = $('#example3').dataTable();
			$("#example3 tr").each(function() {
				for (var pz = 0; pz < oTable.fnGetData().length; pz++) {
					if (counterdatatable == 1) {
					} else {
						var row = $(this).closest("tr").get(0);
						oTable.fnDeleteRow(oTable.fnGetPosition(row));
					}
					counterdatatable = counterdatatable + 1;
				}
			});
			var xx = JSON.parse(info);
			var srNo = 1;
			for (key1 in xx) {
				var xx1 = JSON.parse(xx[key1]);
				for (key2 in xx1) {
					   oTable.fnAddData([
							srNo,
							xx1[key2].state_Country.country.country_Name,
							xx1[key2].state_Country.state_Name,
							xx1[key2].district_Name ,]);
					   srNo = srNo + 1;
				}
			}		
	},
		error : function(errorThrown) {
			console.log("Error: " + errorThrown);
		}
	});
}
function   loadSubdistrict()
{
	var distId=$("#DistNameSubDist").val()
	if(distId==0)
		{
			return false;
		}
	$.ajax({
		dataType : 'json',
		url : 'getSubdistrictByDistrict',
		type : "POST",
		dataType : 'text',
		data : "distId=" + distId,
		async : false,
		success : function(data) {
			var jsonData = JSON.parse(JSON.stringify(data));
			var info = JSON.parse(JSON.stringify(data));
			var counterdatatable = 1;
			var oTable = $('#example4').dataTable();
			$("#example4 tr").each(function() {
				for (var pz = 0; pz < oTable.fnGetData().length; pz++) {
					if (counterdatatable == 1) {
					} else {
						var row = $(this).closest("tr").get(0);
						oTable.fnDeleteRow(oTable.fnGetPosition(row));
					}
					counterdatatable = counterdatatable + 1;
				}
			});
			var xx = JSON.parse(info);
			var srNo = 1;
			for (key1 in xx) {
				var xx1 = JSON.parse(xx[key1]);
				for (key2 in xx1) {
					   oTable.fnAddData([
										srNo,
										xx1[key2].state_Districts.state_Country.country.country_Name,
										xx1[key2].state_Districts.state_Country.state_Name,
										xx1[key2].state_Districts.district_Name ,
										xx1[key2].subdistricts_Name, 
								]);
					   srNo = srNo + 1;
				}
			}		
	},
		error : function(errorThrown) {
			console.log("Error: " + errorThrown);
		}
	});	
}
function setStateForCountry()
{
	  var countryId=$("#countryNamedist").val();
		if(countryId==0)
		{
			return false;
		}
		var select = document.getElementById("stateNameDist");
		select.options.length = 1
		$.ajax({
			dataType : 'json',
			url : 'getstateByCountry',
			type : "POST",
			dataType : 'text',
			data : "countryId=" + countryId,
			async : false,
			success : function(data) {
				var jsonData = JSON.parse(JSON.stringify(data));
				var info = JSON.parse(JSON.stringify(data));
				var xx = JSON.parse(info);
				var srNo = 1;
				for (key1 in xx) {
					var xx1 = JSON.parse(xx[key1]);
					for (key2 in xx1) {
						 $("select#stateNameDist").append( $("<option>")
								    .val(xx1[key2].state_Country_id)
								    .html(xx1[key2].state_Name  ) 
							
								);
					}
				}		
			},
			error : function(errorThrown) {
				console.log("Error: " + errorThrown);
			}
		});
}
function setStateForSubdist()
{	
	  var countryId=$("#countryNamesubDist").val();
		if(countryId==0)
		{
			return false;
		}
		var select = document.getElementById("stateNameSubDist");
		select.options.length = 1
		$.ajax({
			dataType : 'json',
			url : 'getstateByCountry',
			type : "POST",
			dataType : 'text',
			data : "countryId=" + countryId,
			async : false,
			success : function(data) {
				var jsonData = JSON.parse(JSON.stringify(data));
				var info = JSON.parse(JSON.stringify(data));
				var xx = JSON.parse(info);
				var srNo = 1;
				for (key1 in xx) {
					var xx1 = JSON.parse(xx[key1]);
					for (key2 in xx1) {
						 $("select#stateNameSubDist").append( $("<option>")
								    .val(xx1[key2].state_Country_id)
								    .html(xx1[key2].state_Name  ) 
								);
					}
				}		
			},
			error : function(errorThrown) {
				console.log("Error: " + errorThrown);
			}
		});
}
function setDistrictForState()
{	
	var statId=$("#stateNameSubDist").val()
	if(statId==0)
		{
			return false;
		}
	//  Remove elements of option tag
	var select = document.getElementById("DistNameSubDist");
	select.options.length = 1
	$.ajax({
		dataType : 'json',
		url : 'getDistrictByState',
		type : "POST",
		dataType : 'text',
		data : "statId=" + statId,
		async : false,
		success : function(data) {
			var jsonData = JSON.parse(JSON.stringify(data));
			var info = JSON.parse(JSON.stringify(data));
			var xx = JSON.parse(info);
			for (key1 in xx) {
				var xx1 = JSON.parse(xx[key1]);
				for (key2 in xx1) {
					$("select#DistNameSubDist").append( $("<option>")
						    .val(xx1[key2].state_district_id)
						    .html(xx1[key2].district_Name ) 
						);
				}
			}		
	},
		error : function(errorThrown) {
			console.log("Error: " + errorThrown);
		}
	});
}

function getUserByGender(){
	var gender = $("#gender").val();
	var userType = $("#userType").val();
	if(gender == "0")
		{
		     $("#selectGenderAlert").slideDown();
		     $("#selectGenderAlert").fadeOut(4000);
			 return false;
		}
	if(userType == "0")
		{
	     $("#userTypeAlert").slideDown();
	     $("#userTypeAlert").fadeOut(4000);
		 return false;
		}
	$.ajax({
		dataType : 'json',
		url : "userMobileNumberByGender",
		type : "GET",
		data :{gender:gender,userType:userType},
		success : function(data) 
		{
			var jsonData = JSON.parse(JSON.stringify(data)); 
			var counter1 = jsonData.userModelList;
			var cntForiterateobj1 = JSON.parse(counter1);
			for (var c = 0; c < cntForiterateobj1.length; c++) 
			{
				var node = document.createElement('div');        
				node.innerHTML = '<input type="checkbox" id="' + cntForiterateobj1[c].mobileNum + '" name="' + cntForiterateobj1[c].mobileNum + '"class="bulkSMS"><label for="' + cntForiterateobj1[c].userModel.userId + ' "> &nbsp; ('+Number(c+1)+')&nbsp;MNO: '+cntForiterateobj1[c].mobileNum+" &nbsp; Name :"+(cntForiterateobj1[c].userModel.firstName)+'</label>';       
				document.getElementById('permissionCheckBox').appendChild(node); 
			}
		},
		error : function(xhr, ajaxOptions, thrownError) {
			alert("err" + thrownError + xhr.responseText);
		}
	});
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
function sendBulkSms(){
    var bulksmsMessage = $("#bulksmsMessage").val();
    if(bulksmsMessage == "")
    	{
    	  $("#enterBulkMessageAlert").slideDown();
    	  $("#enterBulkMessageAlert").fadeOut(4000);
    	  return false;
    	}
	var formData = $("#sendBulkSmsForm").serialize();
	//formData += "&bulksmsMessage=" + encodeURIComponent(bulksmsMessage);
	$.ajax({
		url : "sendBulkSms",
		type : "GET",
		data : formData,
		success : function(data) {
			$("#bulksmsMessage").val("");
			$("#gender").val("0");
			$("#userType").val("0");
			$('input:checkbox').prop('checked', false);
			$("#messageSendsuccess").slideDown();
			$("#messageSendsuccess").fadeOut(4000);
		},
		error : function(xhr, ajaxOptions, thrownError) {
			alert("err" + thrownError + xhr.responseText);
		}
	});
	
	
	
	/*$.ajax({
		url : 'sendBulkSms',
		type : "POST",
		data : {
			sendBulkSmsIds : "" + sendBulkSmsIds
		},
		success : function(data) 
		{
			
		},
		error : function(xhr, ajaxOptions, thrownError) {
			console.log("Error In saveChatForfeedbackcom" + thrownError
					+ xhr.responseText);
		}
	});*/
    
	
	
	
}