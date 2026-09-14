
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<head>

<jsp:include page="${pageContext.request.contextPath}/body.jsp"></jsp:include>

<script
	src="${pageContext.request.contextPath}/resources/ServerSetup/ServerSetup.js"	charset='utf-8'></script>


</head>


	<body>
	<jsp:include page="${pageContext.request.contextPath}/menu.jsp"></jsp:include>
	<div id="home">
	<div class="container">
		<div class="adminContentWrap clearfix">
			<div class="alert alert-success alert-dismissable" id="notificationStatus">
		<button type="button" class="close" data-dismiss="alert"
			aria-hidden="true">&times;</button>
		<strong>Success: </strong>FCM Notification Setting Saved successfully.
	</div>
	<div class="alert alert-success alert-dismissable" id="ftpStatus">
		<button type="button" class="close" data-dismiss="alert"
			aria-hidden="true">&times;</button>
		<strong>Warning: </strong>  FTP Server Setting Saved successfully.
	</div>
	<div style="margin-top: 20px;" class="alert alert-success "
		id="emailStatus">
		<button type="button" class="close" data-dismiss="alert"
			aria-hidden="true">&times;</button>
		<strong>Success: </strong> Email Server Setting Saved successfully.
	</div>

	<div class="alert alert-success alert-dismissable" id="updateNotificationStatus">
		<button type="button" class="close" data-dismiss="alert"
			aria-hidden="true">&times;</button>
		<strong>Success: </strong>FCM Notification Setting Updated successfully.
	</div>
	<div class="alert alert-success alert-dismissable" id="updateFtpStatus">
		<button type="button" class="close" data-dismiss="alert"
			aria-hidden="true">&times;</button>
		<strong>Warning: </strong>  FTP Server Setting Updated successfully.
	</div>
	<div style="margin-top: 20px;" class="alert alert-success "
		id="updateEmailStatus">
		<button type="button" class="close" data-dismiss="alert"
			aria-hidden="true">&times;</button>
		<strong>Success: </strong> Email Server Setting Updated successfully.
	</div> 
	
	<div style="margin-top: 20px;" class="alert alert-danger "
		id="allFieldsMandatory">
		<button type="button" class="close" data-dismiss="alert"
			aria-hidden="true">&times;</button>
		<strong>Warning: </strong>Please Fill The Complete Details All Fields Are Mandatory.
	</div>
	
	<script type="text/javascript">
		$("#notificationStatus").hide();
		$("#ftpStatus").hide();
		$("#emailStatus").hide();
		$("#updateNotificationStatus").hide();
		$("#updateFtpStatus").hide();
		$("#updateEmailStatus").hide();
		$("#allFieldsMandatory").hide(); 
	</script>
	
	
		 	<ul class="feedbackTabs list-unstyled clearfix">
				<li class="active"
					onclick="switchView('FcmServerSetup', this);"><a
					href="#">FCM Notification Setting</a></li>

				<li onclick="switchView('FtpServerSetup', this);"><a
					href="#">FTP Server Setting</a></li>
					
				<li onclick="switchView('serverSetup', this);"><a
					href="#">Email Server Setting</a></li>
			</ul>
				
			<div class="clearfix"></div>
					<div id="FcmServerSetup" class="tabsContentWrapper">
 							<div id="createFeedBackTypeFirst">
 									
						<h3>FCM SERVER DETAILS</h3>
						<div class="formWrapper adminContentWrap ">
						
						    <label for="companyname" class="col-sm-2 control-label">Authorization</label>
						      <input type="text" name="authorization" id="authorization" size="100"  >
						    <br>
						    <label for="contact" class="col-sm-2 control-label">Title</label>
						      <input type="text"  id="title" name="title" size="100" >
						      
						    <br>
						    <label for="contact" class="col-sm-2 control-label">Server URL</label>
						      <input type="text"  id="serverUrl" name="serverUrl"  size="100">
						    <br> <br>
						    
						   <div class="text-center">
	    <c:if test="${empty fcmNotification}">
					<button type="button" id ="fcmsave" onclick="return saveFcmNotification();" name="save" class="btn btn-small btn-primary ">Save</button>
		</c:if>
		 <c:if test="${not empty fcmNotification}">
					<button type="button" id ="fcmsave" onclick="return saveFcmNotification();" name="save" class="btn btn-small btn-primary " >Save</button>
					<script>
					$("#fcmsave").prop('disabled', true);
					</script>
					
		</c:if>
		  
		  		<button type="button" id="fcmupdate" onclick="return updateFcmNotification();" class="btn btn-small btn-secondary ">Update</button>
		  			<script>
					$("#fcmupdate").prop('disabled', true);
					</script>
		  
	  		</div>
						   
					
						</div>
															<table class="table table-bordered table-hover table-striped" id="FcmDataTable">
											<thead>
											<tr>
												<th>Sr No <i class="fa fa-sort-amount-asc"></i></th>
												<th>Authorization<i class="fa fa-sort-amount-asc"></i></th>
												<th>Title<i class="fa fa-sort-amount-asc"></i></th>
												<th>Server URL<i class="fa fa-sort-amount-asc"></i></th>
												<th>Update <i class="fa fa-sort-amount-asc"></i></th>
											</tr>
										</thead>
										<tbody>
												<c:if test="${empty fcmNotification}">
												<tr>
													<td></td>
													<td></td>
													<td></td>
													<td></td>
													<td></td>
												</tr>
												</c:if>
												
												<c:if test="${not empty fcmNotification}">
												<tr>
												
												      
													<td>1</td>
													<td>${fcmNotification.authorization}</td>
													<td>${fcmNotification.title}</td>
													<td>${fcmNotification.url}</td>
													<td class="text-center">
                   									
                   									<a href="# " id="nodeGoto" onclick=" editNotification(this);">
				    
				    								<i class="fa fa-pencil-square-o"></i>
				    								
				    								</a>  </td>
				    								
												</tr>
												</c:if>
											
											</tbody>
										</table>
							</div>
						</div>
					
	
					<div id="FtpServerSetup"  class="tabsContentWrapper">
 							<div id="createFeedBackTypeFirst">
 									
						<h3>FTP SERVER DETAILS</h3>
						<div class="formWrapper adminContentWrap ">
						
						    <label for="companyname" class="col-sm-2 control-label">User Name</label>
						      <input type="text" name="userNameFtp" id="userNameFtp"  size="100" >
						    <br>
						    <label for="contact" class="col-sm-2 control-label">Server Name</label>
						      <input type="text"  id="serverNameFtp" name="serverNameFtp"  size="100" >
						      
						    <br>
						    <label for="contact" class="col-sm-2 control-label">Port</label>
						      <input type="text"  id="portFtp" name="portFtp" size="100" >

							<br>
						    <label for="contact" class="col-sm-2 control-label">Directory Name</label>
						      <input type="text"  id="directoryFtp" name="directoryFtp" size="100" >
						      
						    <br>
						    <label for="contact" class="col-sm-2 control-label">Password</label>
						      <input type="text"  id="passFtp" name="passFtp" size="100" >
						    <br> <br>
						    
						   <div class="text-center">
				<c:if test="${empty ftpInfo}">
					<button type="button" id ="ftpsave" onclick="return saveFtpServerDetails();" name="save" class="btn btn-small btn-primary ">Save</button>
				</c:if>
				<c:if test="${not empty ftpInfo}">
				   	<button type="button" id ="ftpsave" onclick="return saveFtpServerDetails();" name="save" class="btn btn-small btn-primary ">Save</button>
				   	<script>
					$("#ftpsave").prop('disabled', true);
					</script>
				</c:if>
		  		<button type="button" id="ftpupdate" onclick="return updateFtpServerDetails();" class="btn btn-small btn-secondary ">Update</button>
		  		<script>
					$("#ftpupdate").prop('disabled', true);
					</script>
	  		</div>
						   
						</div>
										<table class="table table-bordered table-hover table-striped" id="FtpDataTable">
											<thead>
											<tr>
												<th>Sr No <i class="fa fa-sort-amount-asc"></i></th>
												<th>User Name<i class="fa fa-sort-amount-asc"></i></th>
												<th>Server Name<i class="fa fa-sort-amount-asc"></i></th>
												<th>Port<i class="fa fa-sort-amount-asc"></i></th>
												<th>Directory Name<i class="fa fa-sort-amount-asc"></i></th>
												<th>Password<i class="fa fa-sort-amount-asc"></i></th>
												<th>Update <i class="fa fa-sort-amount-asc"></i></th>
												
											</tr>
										</thead>
										<tbody>
												<c:if test="${empty ftpInfo}">
												<tr>
													<td></td>
													<td></td>
													<td></td>
													<td></td>
													<td></td>
													<td></td>
													<td></td>
													
												</tr>
												</c:if>
												
												<c:if test="${not empty ftpInfo}">
												<tr>
												    <td>1</td>
													<td>${ftpInfo.userName}</td>
													<td>${ftpInfo.serverName}</td>
													<td>${ftpInfo.port}</td>
													<td>${ftpInfo.folderName}</td>
													<td>${ftpInfo.password}</td>
													<td class="text-center">
                   									
                   									<a href="# " onclick="editFtpInfo(this);">
				    
				    								<i class="fa fa-pencil-square-o"></i>
				    								
				    								</a>  </td>
				    								
												</tr>
												</c:if>
											
											</tbody>
										</table>
							</div>
				 		</div>
					
					    
					<div id="serverSetup" class="tabsContentWrapper">
 							<div id="createFeedBackTypeFirst">
 									
						<h3>EMAIL SERVER DETAILS</h3>
						<div class="formWrapper adminContentWrap ">
						
						    <label for="companyname" class="col-sm-2 control-label">Host</label>
						      <input type="text" name="hostEmail" id="hostEmail"  size="100">
						    <br>
						    <label for="contact" class="col-sm-2 control-label">Server Name</label>
						      <input type="text"  id="serverNameEmail" name="serverNameEmail" size="100" >
						      
						    <br>
						    <label for="contact" class="col-sm-2 control-label">Port</label>
						      <input type="text"  id="portEmail" name="portEmail" size="100" >
						    <br>
						    <label for="contact" class="col-sm-2 control-label">EmailName</label>
						      <input type="text"  id="emailName" name="emailName" size="100"  >
						      
						    <br>
						    <label for="contact" class="col-sm-2 control-label">Password</label>
						      <input type="text"  id="passwordEmail" name="passwordEmail" size="100" >
						    <br> <br>
						    
						   <div class="text-center">
				<c:if test="${empty serverInfo}">
					<button type="button" id ="emailsave" onclick="return saveEmailServerInfo();" name="save" class="btn btn-small btn-primary ">Save</button>
				</c:if>
				<c:if test="${not empty serverInfo}">
					<button type="button" id ="emailsave" onclick="return saveEmailServerInfo();" name="save" class="btn btn-small btn-primary ">Save</button>
						<script>
					$("#emailsave").prop('disabled', true);
					</script>
				</c:if>
		  		<button type="button" id="emailupdate" onclick="return updateEmailServerInfo();" class="btn btn-small btn-secondary ">Update</button>
		  		<script>
					$("#emailupdate").prop('disabled', true);
					</script>
	  		</div>
						   
						</div>
						
										<table class="table table-bordered table-hover table-striped" id="emailServerDataTable">
											<thead>
											<tr>
												<th>Sr No <i class="fa fa-sort-amount-asc"></i></th>
												<th>Host<i class="fa fa-sort-amount-asc"></i></th>
												<th>Server Name<i class="fa fa-sort-amount-asc"></i></th>
												<th>Port<i class="fa fa-sort-amount-asc"></i></th>
												<th>EmailName<i class="fa fa-sort-amount-asc"></i></th>
												<th>Password<i class="fa fa-sort-amount-asc"></i></th>
												<th>Update <i class="fa fa-sort-amount-asc"></i></th>
												
											</tr>
										</thead>
										<tbody>
												<c:if test="${empty serverInfo}">
												<tr>
													<td></td>
													<td></td>
													<td></td>
													<td></td>
													<td></td>
													<td></td>
													<td></td>
													
												</tr>
												</c:if>
												                    
												<c:if test="${not empty serverInfo}">
												<tr>
												    <td>1</td>
													<td>${serverInfo.mail_host}</td>
													<td>${serverInfo.mail_serverName}</td>
													<td>${serverInfo.mail_port}</td>
													<td>${serverInfo.msgName}</td>
													<td>${serverInfo.mail_password}</td>
													<td class="text-center">
                   									
                   									<a href="# " onclick="editserverInfo(this);">
				    
				    								<i class="fa fa-pencil-square-o"></i>
				    								
				    								</a>  </td>
				    								
												</tr>
												</c:if>
											
											</tbody>
										</table>
							</div>
				 		</div>
					</div>
		</div>
	</div>
	


	
<script type="text/javascript">
	

	$('#FcmDataTable').DataTable({
		"bPaginate": false,
	    "bLengthChange": false,
	    "bFilter": false,
	    "bInfo": false,
	    "bAutoWidth": false

	});

	$('#emailServerDataTable').DataTable({
		"bPaginate": false,
	    "bLengthChange": false,
	    "bFilter": false,
	    "bInfo": false,
	    "bAutoWidth": false

	});
	
	$('#FtpDataTable').DataTable({
		"bPaginate": false,
	    "bLengthChange": false,
	    "bFilter": false,
	    "bInfo": false,
	    "bAutoWidth": false

	});
	

</script>



<script>
	function hideAllViews() {

		$("#FcmServerSetup").hide();

		$("#FtpServerSetup").hide();
		$("#serverSetup").hide();
		

	}

	function switchView(currentSubTabView, currentLi) {
		hideAllViews();

		$("#" + currentSubTabView).show();

		$(".feedbackTabs li").removeClass("active");

		$(currentLi).addClass("active");
	}

	function loadDataAfterLoadingPage() {

		switchView('FcmServerSetup');

	
	}

	loadDataAfterLoadingPage();
</script>

<script>
$('#myTabs a').click(function (e) {
	  e.preventDefault()
	  $(this).tab('show')
	})
</script>
</body>
</html>



