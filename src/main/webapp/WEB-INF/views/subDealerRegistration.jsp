<!DOCTYPE html>
<html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Sub Dealer Registration</title>
<!-- Tell the browser to be responsive to screen width -->
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
<jsp:include page="body.jsp"></jsp:include>

<link rel="stylesheet" href="resources/datepicker/css/datepicker.css">
<script src="resources/datepicker/js/datepicker.js"></script>
<link rel="stylesheet" type="text/css" href="resources/confirmBox/confirm.css" media="screen">

<script src="resources/confirmBox/confirm.js" type="text/javascript"></script>

<script
	src="${pageContext.request.contextPath}/resources/Revenue/dealer.js"
	charset='utf-8'></script>
	<script type="text/javascript">
var disableUserIdArray = new Array();
</script>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<style>

/* .box-custom{
	background: #dddddd;
}
 */
label {
	/*  margin-left: 20px; */
	font: normal 12px cambria !important;
	text-align: left !important;
	padding-left: 70px;
}
table {
        display: block;
        overflow-x: auto;
        white-space: nowrap;
    }

</style>
</head>
<body class="hold-transition skin-blue sidebar-mini">
	<div class="wrapper">

		<jsp:include page="menuForDealer.jsp"></jsp:include>

		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->


			<!-- Main content -->
			<section class="content">
				<div class="row">

					<div class="col-md-12">


						<!-- /.box-header -->
                      <div id="homeDiv">
						<div class="panel panel-danger">
							<div class="panel-heading" style="background-color: #d23779;">
								<font color="White">Sub Dealer Registration</font>
							</div>
							<div class="panel-body">


								<br>


								<div class="alert alert-danger alert-dismissable"
									id="nameSelect">
									<button type="button" class="close" data-dismiss="alert"
										aria-hidden="true">&times;</button>
									<strong>Error: </strong>Please Enter Full Name.

								</div>

								<div class="alert alert-danger alert-dismissable"
									id="emailselect">
									<button type="button" class="close" data-dismiss="alert"
										aria-hidden="true">&times;</button>
									<strong>Error: </strong>Please Enter valid Email.

								</div>
								
								<div class="alert alert-danger alert-dismissable"
									id="disableDealerAlert">
									<button type="button" class="close" data-dismiss="alert"
										aria-hidden="true">&times;</button>
									<strong>Error: </strong>Disable dealer can't be update please enable first.

								</div>

								<div class="alert alert-danger alert-dismissable"
									id="mobilenumselect">
									<button type="button" class="close" data-dismiss="alert"
										aria-hidden="true">&times;</button>
									<strong>Error: </strong>Please Enter Valid Mobile Number.

								</div>

								<div class="alert alert-danger alert-dismissable"
									id="passwordselect">
									<button type="button" class="close" data-dismiss="alert"
										aria-hidden="true">&times;</button>
									<strong>Error: </strong>Please Enter Password.

								</div>
								<div class="alert alert-danger alert-dismissable"
									id="unameselect">
									<button type="button" class="close" data-dismiss="alert"
										aria-hidden="true">&times;</button>
									<strong>Error: </strong>Please Enter Username.

								</div>  
								
								<div class="alert alert-danger alert-dismissable"
									id="emailAvailable">
									<button type="button" class="close" data-dismiss="alert"
										aria-hidden="true">&times;</button>
									<strong>Error: </strong>Email Id Already Registered.

								</div>  
								
								
								<div class="alert alert-danger alert-dismissable"
									id="mobileAvailable">
									<button type="button" class="close" data-dismiss="alert"
										aria-hidden="true">&times;</button>
									<strong>Error: </strong>Mobile Number Already Registered.

								</div>
								
								<div class="alert alert-danger alert-dismissable"
									id="UserNameAvailable">
									<button type="button" class="close" data-dismiss="alert"
										aria-hidden="true">&times;</button>
									<strong>Error: </strong>User Name Already Registered.

								</div>
								<div class="alert alert-success alert-dismissable"
									id="successSelect">
									<button type="button" class="close" data-dismiss="alert"
										aria-hidden="true">&times;</button>
									<strong>Success: </strong>Dealer Saved Successfully.

								</div> 


								<form>
								
								<input type="hidden" name="hiddenDealer" id="hiddenDealer">
								<input type="hidden" name="hiddenEmailId" id="hiddenEmailId" value="">
								<input type="hidden" name="hiddenDealerName" id="hiddenDealerName" value="">
								<input type="hidden" name="hiddenMobileNo" id="hiddenMobileNo" value="">
								<input type="hidden" name="hiddenRegistrationDate" id="hiddenRegistrationDate" value="">
								<div class="row">
										<div class="" col-sm-3">
											
										</div>
										<div class="col-sm-3">
										</div>
									
									</div>
								<br>
									<div class="row">
										<div class="" col-sm-3">
											<label class="control-label" " for="messages">Full
												Name:<sup><font color="red"  size="2px">*</font></sup></label>
										</div>
										<div class="col-sm-3">
											<input type="text" id="fname" />
										</div>
											<!-- <div class="" col-sm-3">
											<label class="control-label" " for="messages">Registration Date:</label>
										</div>
										<div class="col-sm-3">
											<input type="text" id="registrationDate" name="registrationDate" readonly="readonly"/>
										</div> -->
									
									</div>
											<br>
									<div class="row">
									
									<div class="" col-sm-3">
										<label class="control-label" " for="noofdays">Username: <sup><font color="red"  size="2px">*</font></sup></label>
									</div>
									<div class="col-sm-3">
											<input type="text" id="uname">
										</div>
										<div class="" col-sm-3">
											<label class="control-label" " for="noofdays">Password: <sup><font color="red"  size="2px">*</font></sup></label>
										</div>
										<div class="col-sm-3">
											<input type="password" id="password">
										</div>

                                     
									</div>
									<br>
									<div class="row">
									<div class="" col-sm-3">
										<label class="control-label" " for="messages">Mob Num: <sup><font color="red"  size="2px">*</font></sup></label>
									</div>
									<div class="col-sm-3">
										<input type="text" id="mob" maxlength="10"
											onkeypress="return event.charCode ">= 48 && event.charCode <= 57 || event.keyCode === 8 || event.keyCode === 9 '>
									</div>
										 <div class="" col-sm-3">
											<label class="control-label" " for="messages">Email :</label>
										</div>
										<div class="col-sm-3">
											<input type="text" id="email" />
										</div> 
									
										</div>
								
									<br>
									<div class="row">
										<div class="" col-sm-3">
											<label class="control-label" " for="noofdays">Office No</label>
										</div>
										<div class="col-sm-3">
											<input type="text" id="officeNo" name="officeNo">
										</div>


										<div class="" col-sm-3">
											<label class="control-label" " for="noofdays">Address
												</label>
										</div>
										<div class="col-sm-3">
											<input type="text" id="address" name="address">
										</div>
									</div>
									<br>
								
								
								<div class="row">
										<div class="" col-sm-3">
											<label class="control-label" " for="noofdays">PinCode</label>
										</div>
										<div class="col-sm-3">
											<input type="text" id="pinCode" name="pinCode" maxlength="6" onkeypress="return event.charCode ">= 48 && event.charCode <= 57 || event.keyCode === 8 || event.keyCode === 9 '>
										</div>
                                        
                                         
										<div class="" col-sm-3">
											<label class="control-label" " for="noofdays">Branch
												Name:</label>
										</div>
										<div class="col-sm-3">
											<input type="text" id="branchName">
										</div> 

									

									</div>
									<br>
								
								<div class="row">
										

										 <div class="" col-sm-3">
											<label class="control-label" " for="noofdays">Discount(%)
												:</label>
										</div>
										<div class="col-sm-3">
											<input type="number" id="discount" value="0">
										</div> 

									</div>
									<br>
								
								
								<br>
								<div class="row">
									<div class="" col-sm-4">
										<!-- <label class="control-label" " for="noofprofile">Address:</label> -->
										
									</div>
									<!-- <div class="col-sm-3">
											<textarea rows="1" cols="18"></textarea>
										</div> -->
										
										<div class="" col-sm-3">
										<button id="saveButton" type="button" class="btn btn-primary pull-center"
											onclick="return saveSubDealer();">
											Save</button>

										</div>
									
									<div class="" col-sm-2">
										<button id="updateButton" type="button" class="btn btn-primary pull-center"
											onclick="return UpdateDealer();">
											Update</button>

									</div>
								</div>
									

								</form>
							</div>

							<script type="text/javascript">
								
								$("#updateButton").hide(); 
								$("#nameSelect").hide(); 
								$("#emailselect").hide();
								$("#mobilenumselect").hide();
								$("#successSelect").hide();
								$("#unameselect").hide();
								$("#passwordselect").hide(); 
								$("#emailAvailable").hide();  
								$("#mobileAvailable").hide(); 
								$("#UserNameAvailable").hide();
								$("#disableDealerAlert").hide();
								
								/*  var date = new Date();
								 document.getElementById("registrationDate").value = (date.getMonth() + 1) + '/' + date.getDate() + '/' + date.getFullYear(); */
								/* 
								 $("#registrationDate").datepicker({
										dateFormat : 'dd/mm/yy',
										changeMonth : true,
										changeYear : true,
										yearRange : '1980:2099'
									}).datepicker('setDate', new Date()); */
							</script>


						</div>

                 </div>
			<table id="example1"class="table table-bordered table-striped">
											<thead>
												<tr>
													<th>Dealer Id</th>
													<th>Name</th>
													<th>Email</th>
													<th>Mob</th>
													<th>Uname</th>
													<th>password</th>
													<th>Branch</th>
													<th>Discount</th>
													
													<th>Office No</th>
													<th>Address</th>
													<th>PinCode</th>
													<th>RegistrationDate</th>
													
													<th>Edit/Update</th>
													<th>Delete</th>
													<th>Unable/Disable</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach var="pack" items="${DealerList}"
													varStatus="theCount">
													
													
													<tr>
														<td>${pack.id }</td>
														<td>${pack.fullName}</td>
														<td>${pack.email}</td>
														<td>${pack.mobileNum}</td>
														<td>${pack.userModel.userName}</td>
											         	<td>${pack.userModel.password}</td>
															
											        
														<td>${pack.branchName}</td>
														<td>${pack.discount}</td>
														<td>${pack.officeNo}</td>
														<td>${pack.address}</td>
														<td>${pack.pincode}</td>
														  <td>
														 <fmt:formatDate pattern="dd-MM-yyyy" value="${pack.regDate}"/>
														</td>

										            <td><button onclick=editDealer(this)>Edit/Update</button></td>
														<td><button onclick=deleteDealer(this)>Delete</button></td>
														<c:if
											         test="${pack.status.status_id eq 3}">
														<td><button onclick=unableDealer(this)>Unable</button>
														 <script>  
														     disableUserIdArray.push("${pack.id}");
	                                                     </script>
														</td>
													</c:if>

													<c:if
											         test="${pack.status.status_id ne 3}">
														<td><button onclick=disableDealer(this)>Disable</button></td>
													</c:if>

														
													</tr>
												</c:forEach>
											</tbody>

										</table>

                  	</div>

					<br>
					<br>
					
					 <div class="row" style="margin-bottom: 40px;">
											
		           </div>

		


<script>
function clearAllFields()
{

	    $("#fname").val("");
		 $("#email").val("");
		 $("#mob").val("");
		 $("#uname").val("");
		 $("#password").val(""); 
		 $("#branchName").val("");
		 $("#discount").val("0"); 
		 $("#officeNo").val("");
		 $("#address").val("");
		 $("#pinCode").val("");
		 
		/*  var date = new Date();
		 document.getElementById("registrationDate").value = (date.getMonth() + 1) + '/' + date.getDate() + '/' + date.getFullYear(); */
		
}
			function disableDealer(aa)
			{
				clearAllFields();
				var id=	$(aa).closest('td').prev().prev().prev().prev().prev().prev().prev().prev().prev().prev().prev().prev().prev().prev().text();
				
				 $.confirm({
					    title: '',
					    content: 'Do You Want To Disable ?',
					    buttons: {
					        confirm: function () {
					        	
					        	
					        	
					        	   $.ajax({
									dataType : 'Text',
									url : "disableDealerByAdmin",
									type : "POST",
									data : "dealerId=" +id,
									success : function(data) {
										
									
										$(aa).closest('td').html('<button onclick=unableDealer(this)>Unable</button>');
										
									},
									error : function(xhr, ajaxOptions, thrownError) {
										alert("err" + thrownError + xhr.responseText);
									}
								}); 
					           
					        	
					        	
					        },
					        cancel: function () {
					            
					        }
					      
					    }
					});
				
				
				
			}


			function unableDealer(aa)
			{
				clearAllFields();
				var id=	$(aa).closest('td').prev().prev().prev().prev().prev().prev().prev().prev().prev().prev().prev().prev().prev().prev().text(); 
				$.confirm({
				    title: '',
				    content: 'Do You Want To Unable ?',
				    buttons: {
				        confirm: function () {
				        	
				        	
				        	$.ajax({
								dataType : 'Text',
								url : "unableDealerByAdmin",
								type : "POST",
								data : "dealerId=" +id,
								success : function(data) {
									
									
									$(aa).closest('td').html('<button onclick=disableDealer(this)>Disable</button>');
									
								},
								error : function(xhr, ajaxOptions, thrownError) {
									alert("err" + thrownError + xhr.responseText);
								}
							}); 
				           
				        	
				        	
				        },
				        cancel: function () {
				            
				        }
				      
				    }
				});
			}


			function deleteDealer(aa)
			{
				clearAllFields();
				var id=	$(aa).closest('td').prev().prev().prev().prev().prev().prev().prev().prev().prev().prev().prev().prev().prev().text(); 
				 $.confirm({
				    title: '',
				    content: 'Do You Want To Delete ?',
				    buttons: {
				        confirm: function () {
				        	
				        	
				        	
				        	 $.ajax({
								dataType : 'Text',
								url : "deleteDealerByAdmin",
								type : "POST",
								data : "dealerId=" +id,
								success : function(data) {
									
									var p = aa.parentNode.parentNode.rowIndex;
									document.getElementById("example1").deleteRow(p);
									
								},
								error : function(xhr, ajaxOptions, thrownError) {
									alert("err" + thrownError + xhr.responseText);
								}
							}); 
				           
				        	
				        	
				        },
				        cancel: function () {
				            
				        }
				      
				    }
				});
				 
			}
		
			function editDealer(aa)
			{
				$('html, body').animate({ scrollTop: $('#homeDiv').offset().top }, 'slow');
				
				$("#updateButton").show(); 
				$("#saveButton").hide(); 
				
				var id =$(aa).closest('td').prev().prev().prev().prev().prev().prev().prev().prev().prev().prev().prev().prev().text(); 
		  		var fullName =$(aa).closest('td').prev().prev().prev().prev().prev().prev().prev().prev().prev().prev().prev().text();
		  		var email =$(aa).closest('td').prev().prev().prev().prev().prev().prev().prev().prev().prev().prev().text();
		  		var mob =$(aa).closest('td').prev().prev().prev().prev().prev().prev().prev().prev().prev().text();
		  		var uname =$(aa).closest('td').prev().prev().prev().prev().prev().prev().prev().prev().text();
		 		var password=$(aa).closest('td').prev().prev().prev().prev().prev().prev().prev().text();	  		 
		  		var branch=$(aa).closest('td').prev().prev().prev().prev().prev().prev().text();
		  		
		  		var discount=$(aa).closest('td').prev().prev().prev().prev().prev().text();
		  		var officeNo=$(aa).closest('td').prev().prev().prev().prev().text();
		  		var address=$(aa).closest('td').prev().prev().prev().text();
		  		var pinCode=$(aa).closest('td').prev().prev().text();
		  		var registrationDate =$(aa).closest('td').prev().text();
		  		
		  		 for(var i=0;i<disableUserIdArray.length;i++)
			 		{
			 				if(disableUserIdArray[i].toUpperCase()===id.toUpperCase())
			 					{
			 					 $("#disableDealerAlert").slideDown();
			 					 $("#disableDealerAlert").fadeOut(6000);
			 					  return false;
			 					}
			 		}
		  		 
		  		 $("#hiddenRegistrationDate").val(registrationDate);
		  		  $("#hiddenDealer").val(id);
		  		  $("#hiddenEmailId").val(email);
		  		  $("#hiddenMobileNo").val(mob);
		  		  $("#hiddenDealerName").val(uname);
		  		 $("#fname").val(fullName);
		  		 $("#email").val(email);
		  		 $("#mob").val(mob);
		  		 $("#uname").val(uname);
		  		 $("#password").val(password); 
		  		 $("#branchName").val(branch);
		  		 $("#discount").val(discount); 
		  		 $("#officeNo").val(officeNo);
		  		 $("#address").val(address);
		  		 $("#pinCode").val(pinCode);
				
		  	/* 	var   dateArr = registrationDate.split("-");
		  		var str1 =  dateArr[2];
		  		var year_str2 =dateArr[0];
		  		var month_str3 = dateArr[1];
		  		var day = str1.substring(0, 2);
		  		$("#registrationDate").val(month_str3+"/"+day+"/"+year_str2); */
		   
				
		
				
	}
		
		
		
		</script>





				</div>
		</div>





	</div>






	<!-- /.tab-pane -->




	<script>
		$.widget.bridge('uibutton', $.ui.button);
	</script>
	<script>
		$('#example1').dataTable({
			"pagingType" : "full_numbers",
			   "order": [[ 0, "desc" ]]	
		});
	</script>


</body>
</html>







