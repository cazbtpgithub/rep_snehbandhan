function addBranchDetails(){

	var branch = $("#branch").val();
	var branchAdd=$("#branchAdd").val();
	var lat = $("#lat").val();
	var longitude = $("#longitude").val();
	
	var contactNo = $("#contactNo").val();
	var emailId = $("#emailId").val();
	
	
	if (branch.trim() == "" || branch == null) {
		$("#addBranchName").slideDown();
		$("#addBranchName").fadeOut(15000);
		$('#branch').focus();
		return false;
	}
	
	if (branchAdd.trim() == ""|| branchAdd == null) {
		$("#addAddress").slideDown();
		$("#addAddress").fadeOut(15000);
		$("#branchAdd").focus();
		return false;
	}

	if (lat.trim() == ""|| lat == null) {
		$("#addLat").slideDown();
		$("#addlat").fadeOut(15000);
		$("#lat").focus();
		return false;
	}
	
	if (longitude.trim() == ""|| longitude == null) {
		$("#addLong").slideDown();
		$("#addLong").fadeOut(15000);
		$("#longitude").focus();
		return false;
	}
 
	
	
	if ($("#emailId").val() == "") 
	{

	}
	else
		{
		   
		var email_regex = /^[\w\-\.\+]+\@[a-zA-Z0-9\.\-]+\.[a-zA-z0-9]{2,4}$/;
		if (!email_regex.test(emailId))
		{
			$("#emailRequired").slideDown();
			$("#emailRequired").fadeOut(3000);
			$("#emailId").focus();
			return false;
		}
		}
	
	// dont delete commented code that is used for deleted funtionality and they use in future
	$.ajax({

		  url : 'saveBranchDetails',
		  type : "POST",
		  dataType : 'json',
		  data : {branch:branch,branchAdd:branchAdd,lat:lat,longitude:longitude,contactNo:contactNo,emailId:emailId},
		  async : false,
		  success : function(responce)
		  {
			 
			  var	branchId = JSON.stringify(responce);
			  branchId = branchId.slice(1, -1);
			  
			  
			    var oTable = $('#branchDetailsTable').dataTable();
				var rows = $("#branchDetailsTable").dataTable().fnGetNodes();
				var number = parseInt(oTable.fnGetData().length) + 1;
				oTable
				.fnAddData([
				            number,
				            branch,
				            contactNo,
				            emailId,
				            lat,
				            longitude,
				            branchAdd,
				            "<td class='text-center'>"
				            + "<div class='hidden-sm hidden-xs btn-group'>"
				            + "<a style=' width: 100%; display: block; text-align: center; cursor:pointer;' <input type='button'  onclick='return deleteBranch("
				            +"&quot;"
				            + number +"&quot;,&quot;"
				            +branchId+"&quot;"+");'>"+" <i class='fa fa-trash-o'></i></a>"
				            + "</div></td>", ]);
				
				
			  	$("#branch").val("");
				$("#branchAdd").val("");
				$("#lat").val("");
				$("#longitude").val("");
				$("#contactNo").val("");
				$("#emailId").val("");
				$("#branchSuccess").slideDown();
				$("#branchSuccess").fadeOut(4000);
			
		  },
		  error : function(errorThrown) {
		   console.log("Error: " + errorThrown);
		  }
		 });
}

/*function editBranch(branchName, contactNo,emailId,lattitude,logitude,brachAddress, branchID)
{
	

	
	
}*/

function deleteBranch(rowNo,branchID)
{
	$.confirm({
	    title: '',
	    content: 'Are you sure you want to delete branch? ',
	    buttons: {
	        confirm: function () {
       		$.ajax({
       			  url : 'deleteBranchDetails',
       			  type : "POST",
       			  dataType : 'text',
       			  data : {branchID:branchID},
       			  async : false,
       			  success : function(responce) 
       			  {
       				    var oTable = $('#branchDetailsTable').dataTable();
       				     var rows = $("#branchDetailsTable").dataTable().fnGetNodes();
       				     for (var i = 0; i < rows.length; i++) 
       				     {
       				      var id = $(rows[i]).find("td:eq(0)").text();
       				      if (id == rowNo) 
       				      {
       				       var row = $(rows[i]).closest('tr');
       				       $('.dataTable').dataTable().fnDeleteRow(row); 
       				      }
       				     }
       						
       				 $("#branchDeleted").slideDown();
       				 $("#branchDeleted").fadeOut(3000);
       				 
       			  },
       			  error : function(errorThrown) {
       			   console.log("Error: " + errorThrown);
       			  }
       			 });	
       			
	        },
	        cancel: function () {
	            
	        }
	      
	    }
	});
	
}


/*function changePassword()
{
    	var currentUserName = $("#currentUserName").val();
    	var newUserName = $("#newUserName").val();
	    var currentPass = $("#currentPass").val();
		var newPass = $("#newPass").val();
		var confirmPass = $("#confirmPass").val();
		var currentAdminUserName = $("#currentAdminUserName").val();
        var currentAdminPassword = $("#currentAdminPass").val();

        if (currentUserName == "" || currentUserName == null) {
		$("#currentUserNameAlert").slideDown();
		$("#currentUserNameAlert").fadeOut(5000);
		$('#currentUserName').focus();
		return false;
	}
	if (newUserName == "" || newUserName == null) {
		$("#newUserNameAlert").slideDown();
		$("#newUserNameAlert").fadeOut(5000);
		$('#newUserName').focus();
		return false;
	}
    if(currentAdminUserName != currentUserName)
	{
		$("#newUserNameMatchAlert").slideDown();
		$("#newUserNameMatchAlert").fadeOut(5000);
		$('#currentUserName').focus();
		return false;
	}
     

		if (currentPass == "" || currentPass == null) {
			$("#currentPasswordAlert").slideDown();
			$("#currentPasswordAlert").fadeOut(5000);
			$('#currentPassword').focus();
			return false;
		}
		if (newPass.trim() == ""|| newPass == null) {
			$("#newPassAlert").slideDown();
			$("#newPassAlert").fadeOut(5000);
			$("#newPass").focus();
			return false;
		}
		   if(currentAdminPassword != currentPass)
       	{
      		$("#currentPassMatchAlert").slideDown();
			$("#currentPassMatchAlert").fadeOut(5000);
			$('#currentPass').focus();
			return false;
       	}
		if (confirmPass.trim() == ""|| confirmPass == null) {
			$("#confirmPassAlert").slideDown();
			$("#confirmPassAlert").fadeOut(5000);
			$("#confirmPass").focus();
			return false;
		}
		
		if ($("#newPass").val() !== $("#confirmPass").val()) {
			$("#newPassMatchAlert").slideDown();
			$("#newPassMatchAlert").fadeOut(5000);
			$("#confirmPass").focus();
			$("#newPass").focus();
			return false;
		}		
		$.ajax({
			  url : 'changeAdminPass',
			  type : "POST",
			  dataType : 'text',
			  data : {currentUserName:currentUserName,newUserName:newUserName,currentPass:currentPass,newPass:newPass},
			  async : false,
			  success : function(responce)
			  {
				  	$("#currentUserName").val("");
					$("#newUserName").val("");
		 	     	$("#currentPass").val("");
					$("#newPass").val("");
					$("#confirmPass").val("");
					$("#changePassSuccessAlert").slideDown();
					$("#changePassSuccessAlert").fadeOut(4000);
				
			  },
			  error : function(errorThrown) {
			   console.log("Error: " + errorThrown);
			  }
			 });
}*/