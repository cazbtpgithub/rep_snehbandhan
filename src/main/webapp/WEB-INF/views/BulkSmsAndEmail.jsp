<!DOCTYPE html>
<html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Location Home</title>
<!-- Tell the browser to be responsive to screen width -->
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
<jsp:include page="body.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="resources/confirmBox/confirm.css" media="screen">
<script src="resources/confirmBox/confirm.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resources/location/Location.js" charset='utf-8'></script>
<style>
.box-custom{
	background: #dddddd;
}
label {
    margin-right: -20px;
    font: normal 14px cambria !important;
}
table {
       font:  12px Courier New !important;
}
</style>
<script>
			var allCountryName=new Array();
</script>
<c:forEach var="country" items="${countryList}" varStatus="theCount">
	<script>
			allCountryName.push("${country.country_Name}");
	</script>
</c:forEach>
</head>
<body class="hold-transition skin-blue sidebar-mini">
	<div class="wrapper">
		<jsp:include page="menu.jsp"></jsp:include>
		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <!-- Main content -->
    <section class="content">
      <div class="row">
        <div class="col-md-12">
          <div class="nav-tabs-custom">
       					<input type="hidden" id="hiddenCountry">
      					<input type="hidden" id="hiddenState">
       					<input type="hidden" id="hiddenState">
            <ul class="nav nav-tabs">
              <li class="active"><a href="#country_toggle" data-toggle="tab">Send Bulk SMS</a></li>
            </ul>
            <div class="tab-content">
    <div class="active tab-pane" id="country_toggle">
         <div class="panel panel-danger">
      <div class="panel-heading" style="background-color:#d23779"><font color="white">Send Bulk SMS</font></div>
      <div class="panel-body">
      
		      <div class="alert alert-danger alert-dismissable"
							id="enterBulkMessageAlert">
							<button type="button" class="close" data-dismiss="alert"
						aria-hidden="true">&times;</button>
							<strong>Error: </strong>Please Enter Bulk Message
			</div>
			
			 <div class="alert alert-danger alert-dismissable"
							id="selectGenderAlert">
							<button type="button" class="close" data-dismiss="alert"
						aria-hidden="true">&times;</button>
							<strong>Error: </strong>Please select gender
			</div>
			
			 <div class="alert alert-danger alert-dismissable"
							id="userTypeAlert">
							<button type="button" class="close" data-dismiss="alert"
						aria-hidden="true">&times;</button>
							<strong>Error: </strong>Please select user type
			</div>
			
				<div class="alert alert-success alert-dismissable"
					id="messageSendsuccess">
					<button type="button" class="close" data-dismiss="alert"
				aria-hidden="true">&times;</button>
					<strong>Message: </strong>Message Send Successfully.
				</div>
											<form class="form-horizontal" id="sendBulkSmsForm">
												<div class="box-body">
													<div class="form-group">
														<div class="col-sm-3">
															<label for="firstPortion">Select User</label> <select
																id="gender" name="gender" class="form-control input-sm">
																<option value="0">Select User</option>
																<option value="male">Male</option>
																<option value="female">Female</option>
															</select>
														</div>
														<div class="col-sm-3">
															<label for="firstPortion">Select User Type</label> <select
																id="userType" name="userType" class="form-control input-sm">
																<option value="0">Select User</option>
																<option value="paid">Paid</option>
																<option value="unpaid">Unpaid</option>
															</select>
														</div>
														<div class="col-sm-3"><br>
														 <button type="button" class="btn btn-primary" onclick="return getUserByGender();">Search</button>
														</div>
														
														
													<div class="col-sm-4" style="margin-top: 5px;">
														<textarea rows="3" cols="45"
															placeholder="Enter sms content" id="bulksmsMessage" name="bulksmsMessage">
                                                         </textarea>
												   </div>
										       <div class="row" align="center" >
													<div class="col-sm-2" align="center" style="margin-top: 5px;">
														<button type="button" class="btn btn-primary"
															onclick="sendBulkSms()">Send</button>
													</div><br>
														
														<!-- <div class="" col-sm-3" align="left">
                                                         <label for="countryName" class="col-sm-8 control-label">
                                                          <input type="checkbox"  id="checkUncheack" onclick="cheakAndUncheckAllCheckBox()">
                                                         Check All 
														  </label>
														</div> -->
												</div>
														
														
														<div class="col-sm-12" style="margin-top: 20px;">
															<nav class="breadcrumb custom-breadcrumb"
																style="background-color: #d23779; color: #ffffff;;">
																  <input type=;"checkbox"  id="checkUncheack" onclick="cheakAndUncheckAllCheckBox()">Check/Uncheck
																<label style="padding-left: 250px;">  User Details</label>
																
															</nav>
															<div class="col-sm-12" id="permissionCheckBox"
																style="max-height: 300px; overflow-y: scroll;"></div>
															<!--   <button type="button" class="btn btn-primary" onclick="return saveState();">Search</button> -->
														</div>
												
													
													</div>
													
												</div>
											
												
											</form>
										</div>
    </div>
        
        
              
              
          
  <%--        
   <div class="panel panel-danger">
      <div class="panel-body">
       <div class="box">
            <div class="box-body">
            
            <div class="col-md-5" id="permissionCheckBox" style="max-height:300px; overflow-y:scroll;">
																			
			</div>
            
            
            
            
            
            
            <div id="userDetailsDiv"></div>
              <table id="example1" class="table table-bordered table-striped">
                <thead>
                <tr>
                  <th>Sr No.</th>
                  <th>Country Name</th>
                </tr>
                </thead>
               	 <tbody>
                   <c:forEach var="country" items="${countryList}" varStatus="theCount">
   					<tr>
			    	<td>${theCount.count }</td> 
					<td>${country.country_Name}</td>
					</tr> 
   					</c:forEach>
                </tbody>
              </table>
            </div>
            <!-- /.box-body -->
          </div>
      
      </div>
    </div> --%>
              </div>
            
              <!-- /.tab-pane -->
            </div>
            <!-- /.tab-content -->
          </div>
          <!-- /.nav-tabs-custom -->
        </div>
        <!-- /.col -->
      </div>
      <!-- /.row -->

    </section>
    <!-- /.content -->
  </div>
		
		<div class="control-sidebar-bg"></div>
	</div>


	<!-- jQuery 2.2.3 -->
	<!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip -->
	<script>
		$.widget.bridge('uibutton', $.ui.button);
	</script>
	
	<script>
 
  
  $('#teamWiseRecipient').dataTable({
	  "pagingType": "full_numbers"
	}); 
  
  $('#example2').dataTable({
	  "pagingType": "full_numbers"
	}); 
  $('#example3').dataTable({
	  "pagingType": "full_numbers"
	}); 

$('#example4').dataTable({
	  
	}); 
</script>



<script type="text/javascript">
$("#enterBulkMessageAlert").hide(); 
$("#userTypeAlert").hide(); 
$("#selectGenderAlert").hide(); 
$("#messageSendsuccess").hide();
$("#stateNamesuccess").hide(); 
$("#stateNameAvailable").hide();

$("#countryNamedistSelect").hide();
$("#stateNameDistselect").hide();  
$("#distNameAvailable").hide();
$("#distNameSelect").hide();  
$("#suceessdistNameSelect").hide(); 


$("#countryNamesubdistSelect").hide();
$("#stateNamesubDistselect").hide(); 
$("#distNamesubDistSelect").hide();  
$("#subDistNameSelect").hide();   
$("#subDistAvailable").hide();  
$("#subDistNameSelectssuccess").hide(); 
$("#bulksmsMessage").val("");

//countryNamesubdistSelect  stateNamesubDistselect distNamesubDistSelect  subDistNameSelect subDistNameSelectssuccess
    
</script>


</body>
</html>




