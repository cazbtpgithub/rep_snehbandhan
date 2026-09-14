<!DOCTYPE html>
<html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>

<title>Package Home</title>
<!-- Tell the browser to be responsive to screen width -->
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
<jsp:include page="body.jsp"></jsp:include>
<script
	src="${pageContext.request.contextPath}/resources/packageAndKit/packageAndKit.js"
	charset='utf-8'></script>

<style>
.box-custom {
	background: #dddddd;
}

label {
	margin-right: -20px;
	font: normal 14px cambria !important;
}
 table {
   
       font:  12px cambria New !important;
} 
</style>

<script type="text/javascript">
	var allPackageName = new Array();
</script>








</head>
<body class="hold-transition skin-blue sidebar-mini">
	<div class="wrapper">

		<jsp:include page="menu.jsp"></jsp:include>

		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->


			<!-- Main content -->
			<section class="content">
				

					<div class="col-md-12">



						<div class="alert alert-danger alert-dismissable"
							id="packageNameSelect">
							<button type="button" class="close" data-dismiss="alert"
								aria-hidden="true">&times;</button>
							<strong>Error: </strong>Please Enter Package Name.
						</div>
						<div class="alert alert-danger alert-dismissable"
							id="packageAvailable">
							<button type="button" class="close" data-dismiss="alert"
								aria-hidden="true">&times;</button>
							<strong>Error: </strong>Package Name Already Available.
						</div>


						<div class="alert alert-danger alert-dismissable"
							id="amountSelect">
							<button type="button" class="close" data-dismiss="alert"
								aria-hidden="true">&times;</button>
							<strong>Error: </strong>Please Enter Price Name.
						</div>

						<div class="alert alert-success alert-dismissable"
							id="successSelect">
							<button type="button" class="close" data-dismiss="alert"
								aria-hidden="true">&times;</button>
							<strong>Message: </strong>Data Saved Successfully.
						</div>





						<div class="panel panel-danger">
							<div class="panel-heading" style="background-color: #d23779;">
								<font color="white">Save Package Information</font>
							</div>
							<div class="panel-body">
								<form class="form-horizontal">
									<div class="box-body">
										<div class="form-group">
											<label for="packageName" class="col-sm-1 control-label">Package</label>
											<div class="row">
												<div class="col-sm-4">
													<input type="text" class="form-control input-sm"
														id="packageName" name="packageName"
														placeholder="Enter Package Name">
												</div>

												<label for="price" class="col-sm-1 control-label">Price</label>

												<div class="col-sm-4">
													<input type="text" class="form-control input-sm" id="price"
														name="price" placeholder="Enter Price"
														onkeypress="return event.charCode ">= 48 && event.charCode <= 57 || event.keyCode === 8 || event.keyCode === 9 '>
												</div>
											</div>
											<br>
											<div class="row">
												<label for="price" class="col-sm-1 control-label">no_of_msg</label>
												<div class="col-sm-1">
													<input type="number" class="form-control input-sm"
														id="no_of_msg" name="no_of_msg" onkeypress="return event.charCode ">= 48 && event.charCode <= 57 || event.keyCode === 8 || event.keyCode === 9 '
														value="0">
												</div>

												<label for="price" class="col-sm-1 control-label">no_of_email</label>
												<div class="col-sm-1">
													<input type="number" class="form-control input-sm" onkeypress="return event.charCode ">= 48 && event.charCode <= 57 || event.keyCode === 8 || event.keyCode === 9 '
														id="no_of_email" name="no_of_email"
														value="0">
												</div>
												<label for="price" class="col-sm-1 control-label">no_of_profiles</label>
												<div class="col-sm-1">
													<input type="number" class="form-control input-sm" onkeypress="return event.charCode ">= 48 && event.charCode <= 57 || event.keyCode === 8 || event.keyCode === 9 '
														id="no_of_profiles" name="no_of_profiles"
														value="0">
												</div>
												<label for="price" class="col-sm-1 control-label">no_of_days</label>
												<div class="col-sm-1">
													<input type="number" class="form-control input-sm" onkeypress="return event.charCode ">= 48 && event.charCode <= 57 || event.keyCode === 8 || event.keyCode === 9 '
														id="no_of_days" name="no_of_days"
														value="0">
												</div>
												<div class="col-sm-1">
													<button type="button" class="btn btn-primary"
														onclick="return savepackage();">Add Package</button>
												</div>

											</div>

										</div>
											<input type="hidden" id="hiddenPackId" name="hiddenPackId">
									</div>


								</form>
								
								 <!--Model code   -->
            <div id="myModal" class="modal fade" role="dialog" style="">
				<div class="modal-dialog">

					<!-- Modal content-->
					<div class="modal-content" role="dialog"
						style="height: 500px; width: 500px; margin-left: 200px;overflow: auto;overflow-x:hidden;">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" onclick="closeModel();">&times;</button>
							<h4 class="modal-title">Package Details</h4>
						</div>
						<div class="modal-body">
 		
		
							<div class="alert alert-success alert-dismissable"
												id="packSuccess">
												<button type="button" class="close" data-dismiss="alert"
													aria-hidden="true">&times;</button>
												<font size="3px;"><strong>Success: </strong>Package Upgraded Successfully </font>
							</div>
							
							
							<div class="alert alert-danger alert-dismissable"
												id="packSelect">
												<button type="button" class="close" data-dismiss="alert"
													aria-hidden="true">&times;</button>
												<font size="3px;"><strong>Error: </strong>Please Enter Package Name. </font>
							</div>
							
							<div class="alert alert-danger alert-dismissable"
												id="priceSelect">
												<button type="button" class="close" data-dismiss="alert"
													aria-hidden="true">&times;</button>
												<font size="3px;"><strong>Error: </strong>Please Enter Price. </font>
							</div>
							
							<div class="alert alert-danger alert-dismissable"
												id="messageSelect">
												<button type="button" class="close" data-dismiss="alert"
													aria-hidden="true">&times;</button>
												<font size="3px;"><strong>Error: </strong>Please Enter No Of Message. </font>
							</div>
							
							<div class="alert alert-danger alert-dismissable"
												id="emailSelect">
												<button type="button" class="close" data-dismiss="alert"
													aria-hidden="true">&times;</button>
												<font size="3px;"><strong>Error: </strong>Please Enter No Of Emails. </font>
							</div>
							
							<div class="alert alert-danger alert-dismissable"
												id="profileSelect">
												<button type="button" class="close" data-dismiss="alert"
													aria-hidden="true">&times;</button>
												<font size="3px;"><strong>Error: </strong>Please Enter No Of Profiles. </font>
							</div>
							<div class="alert alert-danger alert-dismissable"
												id="daySelect">
												<button type="button" class="close" data-dismiss="alert"
													aria-hidden="true">&times;</button>
												<font size="3px;"><strong>Error: </strong>Please Enter No Of Days. </font>
							</div>
		

							
							<div class="row">
								
								
								<div class="col-md-9">
									<div class="nav-tabs-custom">
										
										<div class="tab-content" style="font: bold 13px courier" !important;">
										
											<div class="box box-primary">
															<br>
													
											<form class="form-horizontal">
													
													<div class="form-group">
															<label class="control-label col-sm-5" for="messages">package Name</label>
															
																
															<div class="col-sm-5">
																<input type="text"  id="packageName1" name="packageName1"/>
															</div>
																
															
														</div>
													<div class="form-group">
															<label class="control-label col-sm-5" for="messages">Package Price</label>
															<div class="col-sm-5">
																<input type="text"  id="price1" name="price1"/>
															</div>   
														</div>
														<div class="form-group">
															<label class="control-label col-sm-5" for="messages">No Of Messages</label>
															<div class="col-sm-5">
																<input type="text"  id="noOfMessage"/>
															</div>
														</div> 
														<div class="form-group">
															<label class="control-label col-sm-5" for="messages">No Of Emails</label>
															<div class="col-sm-5">
																<input type="text"  id="noOfEmails"/>
															</div>
														</div>  
														<div class="form-group">
															<label class="control-label col-sm-5" for="messages">No Of Profiles</label>
															<div class="col-sm-5">
																<input type="text"  id="noOfProfiles"/>
															</div>
														</div>
														<div class="form-group">
															<label class="control-label col-sm-5" for="messages">No Of Days</label>
															<div class="col-sm-5">
																<input type="text"  id="noOfDays"/>
															</div>
														</div>
																											
														<div class="form-group" style="text-align: center; padding-bottom: 10px;" >
															<button type="button"  class="btn btn-primary" " onclick="updatepackageinfo();"  >
																 Update Package
															
															</button>

														</div>
													
													</form>
																
																
																
											</div>
											
										
										</div>
									</div>
								</div>
							</div>





						</div>
						
						<script>
					$("#packSuccess").hide();
					$("#packSelect").hide();
					$("#priceSelect").hide();
					$("#messageSelect").hide();
					$("#emailSelect").hide();
					$("#profileSelect").hide();
					$("#daySelect").hide();
					
					
					</script>
					</div>

				</div>
            
            
            <!--MOdel code end  -->
      
      
      </div>
							</div>
						</div>

						<div class="panel panel-danger">

							<div class="panel-body">
								<div class="box">
									<div class="box-body">
										<table id="example1"
											class="table table-bordered table-striped">
											<thead>
												<tr>
													<th>Sr No.</th>
													<th>Package Name</th>
													<th>Price</th>
													<th>No Of Message</th>
													<th>No Of Email</th>
													<th>No Of Profile</th>
													<th>No Of Days</th>
													<th>Edit/Update</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach var="pack" items="${packageList}"
													varStatus="theCount">
													
													<tr>
														<td>${pack.package_id }</td>
														<td>${pack.package_Name}</td>
														<td>${pack.amount}</td>
														<td>${pack.no_of_msg}</td>
														<td>${pack.no_of_email}</td>
														<td>${pack.no_of_profiles}</td>
														<td>${pack.no_of_days}</td>
														<td><button onclick=editPackage(this)>Edit/Update</button></td>

														
													</tr>
													<script>
														allPackageName
																.push("${pack.package_Name}");
													</script>
												</c:forEach>
											</tbody>

										</table>
									</div>
									<!-- /.box-body -->
								</div>


							</div>
						</div>







					</div>

				





				<!-- /.tab-pane -->

				<div class="control-sidebar-bg"></div>

			</section>
		</div>
	</div>

	<!-- jQuery 2.2.3 -->
	<!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip -->
	<script>
		$.widget.bridge('uibutton', $.ui.button);
	</script>


<script type="text/javascript">
   
   function editPackage(aa)
   {
	   
	   var packageId=$(aa).closest('td').prev().prev().prev().prev().prev().prev().prev().text();
	   var packName=$(aa).closest('td').prev().prev().prev().prev().prev().prev().text();
	   var price=$(aa).closest('td').prev().prev().prev().prev().prev().text();
	   var messages=$(aa).closest('td').prev().prev().prev().prev().text();
	   var emails=$(aa).closest('td').prev().prev().prev().text();
	   var profiles=$(aa).closest('td').prev().prev().text();
	   var days=$(aa).closest('td').prev().text();
	  
	        
	   $("#hiddenPackId").val(packageId); 
	   $("#packageName1").val(packName);   
	   $("#price1").val(price); 
	   $("#noOfMessage").val(messages); 
	   $("#noOfEmails").val(emails); 
	   $("#noOfProfiles").val(profiles); 
	   $("#noOfDays").val(days); 
	   
	   $('#myModal').modal('show');
   }



</script>
	<script>
		$('#example1').dataTable({
			"pagingType" : "full_numbers",

		});
	</script>

	<script type="text/javascript">
		$("#packageNameSelect").hide();
		$("#amountSelect").hide();
		$("#successSelect").hide();
		$("#packageAvailable").hide();
	</script>





</body>
</html>




