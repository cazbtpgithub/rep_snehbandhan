<!DOCTYPE html>
<html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Kit Home</title>
<!-- Tell the browser to be responsive to screen width -->
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
<jsp:include page="body.jsp"></jsp:include>
<script src="${pageContext.request.contextPath}/resources/packageAndKit/packageAndKit.js" charset='utf-8'></script>

<style>

.box-custom{
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

	var files = [];
	var allUserName=new Array();
</script>
<%-- <c:forEach var="kit" items="${kitList}" varStatus="theCount">
	<script>
	allUserName.push("${kit.kit_username}");
</script>
</c:forEach> --%>






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
         
       
       <div class="alert alert-danger alert-dismissable"
							id="packageNameSelect">
							<button type="button" class="close" data-dismiss="alert"
						aria-hidden="true">&times;</button>
							<strong>Error: </strong>Please Select Package Name. 
			</div>
		
	 <div class="alert alert-danger alert-dismissable"
							id="PasswordSelect">
							<button type="button" class="close" data-dismiss="alert"
						aria-hidden="true">&times;</button>
							<strong>Error: </strong>Please Enter password. 
			</div>
				
			
		      <div class="alert alert-danger alert-dismissable"
							id="userNameSelect">
							<button type="button" class="close" data-dismiss="alert"
						aria-hidden="true">&times;</button>
							<strong>Error: </strong>Please Enter User Name. 
			</div>
				
				<div class="alert alert-success alert-dismissable"
					id="successSelect">
					<button type="button" class="close" data-dismiss="alert"
				aria-hidden="true">&times;</button>
					<strong>Message: </strong>Data Saved Successfully.
				</div>    
				
				
				<div class="alert alert-success alert-dismissable"
					id="uploadSuccess">
					<button type="button" class="close" data-dismiss="alert"
				aria-hidden="true">&times;</button>
					<strong>Message: </strong>File Uploaded Successfully.
				</div> 
				
				
				  <div class="alert alert-danger alert-dismissable"
							id="uploadfail">
							<button type="button" class="close" data-dismiss="alert"
						aria-hidden="true">&times;</button>
							<strong>Error: </strong>Error In File Uplaod. 
			</div>
			
			
			
			
			
			
			
			  
			
				         
                
       <div class="panel panel-danger">
      <div class="panel-heading" style="background-color:#d23779"><font color="White">Save Kit Data</font></div>
      <div class="panel-body">
      <form class="form-horizontal" >
              <div class="box-body">
                <div class="form-group">
                 
               
                
					 <div class="col-sm-2">
					  <label for="packageName">Select Package</label>
                	<select id="packageName" name="packageName" class="form-control input-sm">
					     <option value= "0">Select Package</option>
					  	<c:forEach var="pack" items="${packageList}" varStatus="theCount">
					  	 <option value= '${pack.package_id}'> ${pack.package_Name}</option>
					  	</c:forEach>
					  	 
					  	 
				  	 </select>
                </div>
                <div class="col-sm-2">
               	 <label for="userName" >Enter User</label>
                    <input type="text" class="form-control input-sm" id="userName" name="userName" placeholder="Enter User Name">
                  </div>
				<div class="col-sm-2">
                  <label for="password" >Enter Password</label>
                    <input type="text" class="form-control input-sm" id="password" name="password" placeholder="Enter Password">
                  </div><br>
                  <div class="col-sm-2">
                  
                    <button type="button" class="btn btn-primary" " onclick="return saveKit();" >Add Kit Data</button>
                  </div>
                  
                </div>
              
               <input type="hidden" id="hiddenkit" name="hiddenkit">  
               <input type="hidden" id="hiddenpackage" name="hiddenpackage"> 
              </div>
              
              
            </form>
            
            <!--Model code   -->
            <div id="myModal" class="modal fade" role="dialog" style="">
				<div class="modal-dialog">

					<!-- Modal content-->
					<div class="modal-content" role="dialog"
						style="height: 600px; width: 900px; margin-left: -250px;overflow: auto;overflow-x:hidden;">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" onclick="closeModel();">&times;</button>
							<h4 class="modal-title">kit Details</h4>
						</div>
						<div class="modal-body">
 		
		
		<div class="alert alert-success alert-dismissable"
							id="packSuccess">
							<button type="button" class="close" data-dismiss="alert"
								aria-hidden="true">&times;</button>
							<font size="3px;"><strong>Success: </strong>Succeessfully upgraded Package.  Thank you  </font>
		</div>
		
		<script>
		$("#packSuccess").hide();
		</script>

							<div class="row">
								
								
								<div class="col-md-9">
									<div class="nav-tabs-custom">
										<ul class="nav nav-tabs">
											<li class="active"><a href="#update" data-toggle="tab">Update
													</a></li>
											<li><a href="#delete" data-toggle="tab">Delete </a></li>
											
										
											
										</ul>
										<div class="tab-content" style="font: bold 13px courier" !important;">
											<div class="tab-pane" id="update">
											<div class="box box-primary">
															<br>
													<div class="alert alert-danger alert-dismissable"
														id="updatepackselect">
														<button type="button" class="close" data-dismiss="alert"
															aria-hidden="true">&times;</button>
														<strong>Error: </strong>Please Select Package.
														
													</div>	
													
													<div class="alert alert-success alert-dismissable"
																id="updateSuccess">
														<button type="button" class="close" data-dismiss="alert"
														aria-hidden="true">&times;</button>
														<font size="3px;"><strong>Success: </strong>Kit Updated Successfully </font>
													</div>
													
													
													<form class="form-horizontal">
													
													<div class="form-group">
															<label class="control-label col-sm-5" for="messages">package</label>
															<div class="col-sm-5">
																
															<select id="uppackageName" name="uppackageName" class="form-control input-sm">
															     <option value= "0">Select Package</option>
															  	<c:forEach var="pack" items="${packageList}" varStatus="theCount">
															  	 <option value= '${pack.package_id}'> ${pack.package_Name}</option>
															  	</c:forEach>
															  	 
															  	 
														  	 </select>
																
															</div>
														</div>
													<div class="form-group">
															<label class="control-label col-sm-5" for="messages">Kit Id</label>
															<div class="col-sm-5">
																<input type="text"  id="kitId"/>
															</div>
														</div>
														<div class="form-group">
															<label class="control-label col-sm-5" for="messages">Kit Password</label>
															<div class="col-sm-5">
																<input type="text"  id="kitpasswordd"/>
															</div>
														</div>  
																											
														<div class="form-group" style="text-align: center; padding-bottom: 10px;" >
															<button type="button"  class="btn btn-primary pull-center" onclick="return updatekitinfo();" >
																 Update Kit
															
															</button>

														</div>
													 	
													</form>
																
																
																
											</div>
											</div>
											
											<script type="text/javascript">
													$("#updatepackselect").hide();
													$("#updateSuccess").hide();
											</script>
											
											<div class="tab-pane" id="delete">
											<div class="box box-primary">
												<br>
													
													
													<div class="alert alert-danger alert-dismissable"
														id="deleteError">
														<button type="button" class="close" data-dismiss="alert"
															aria-hidden="true">&times;</button>
														<strong>Error: </strong>Can't Delete Active Kit
													</div>	
													<div class="alert alert-success alert-dismissable"
																id="deleteSuccess">
														<button type="button" class="close" data-dismiss="alert"
														aria-hidden="true">&times;</button>
														<font size="3px;"><strong>Success: </strong>Kit Deleted Successfully </font>
													</div>
													
													<form class="form-horizontal">
													
													
													<div class="form-group">
															<label class="control-label col-sm-5" for="messages">Kit Status</label>
															<div class="col-sm-5">
																<input type="text" readonly="readonly" id="delkitstatus"/>
															</div>
														</div>
													<div class="form-group">
															<label class="control-label col-sm-5" for="messages">Kit Id</label>
															<div class="col-sm-5">
																<input type="text"  id="delkitId"/>
															</div>
														</div>
														<div class="form-group">
															<label class="control-label col-sm-5" for="messages">Kit Password</label>
															<div class="col-sm-5">
																<input type="text"  id="delkitpasswordd"/>
															</div>
														</div>  
																<br>											
														<div class="form-group" style="text-align: center; padding-bottom: 10px;" >
															<button type="button"  class="btn btn-primary" " onclick="return deleteKitInf();" >
																 Delete Kit
															
															</button>

														</div>
													
													</form>
													
													
											</div>
											</div>
											
											<script type="text/javascript">
													$("#deleteError").hide(); 
													$("#deleteSuccess").hide();
											</script>
											
											
											
										</div>
									</div>
								</div>
							</div>
							





						</div>
					</div>

				</div>
            
            
            <!--MOdel code end  -->
      
      
      </div>
    </div>
    
    <!--Start  -->
    <div class="panel panel-danger">
      <div class="panel-heading" style="background-color:#d23779"><font color="White">Assign / Return Kit</font></div>
      <div class="panel-body">
      
      
      <ul class="nav nav-tabs">
              <li class="active"><a href="#country_toggle" data-toggle="tab">Allocate Kit</a></li>
              <li><a href="#state_toggle" data-toggle="tab">Return Kit</a></li>
            </ul>
            <div class="tab-content">
              <div class="active tab-pane" id="country_toggle">
              
                <div class="alert alert-danger alert-dismissable"
							id="dealerNameSelect">
							<button type="button" class="close" data-dismiss="alert"
						aria-hidden="true">&times;</button>
							<strong>Error: </strong>Please Select Dealer Name. 
			</div>
			
			  <div class="alert alert-danger alert-dismissable"
							id="noOfKItSelect">
							<button type="button" class="close" data-dismiss="alert"
						aria-hidden="true">&times;</button>
							<strong>Error: </strong>Please Enter No Of Kits. 
			</div>
			
			
			 <div class="alert alert-success alert-dismissable"
							id="kisAssignSelect">
							<button type="button" class="close" data-dismiss="alert"
						aria-hidden="true">&times;</button>
							<strong>Error: </strong>Kit Assigned Successfully. 
			</div>
			
			<div class="alert alert-danger alert-dismissable"
							id="packageNameSelect1">
							<button type="button" class="close" data-dismiss="alert"
						aria-hidden="true">&times;</button>
							<strong>Error: </strong>Please Select Package Name. 
			</div>
              	
              	<form class="form-horizontal" >
              <div class="box-body">
                <div class="form-group">
                 
               
                
					 <div class="col-sm-2">
					  <label for="packageName">Select Package</label>
                	<select id="packageNamed" name="packageNamed" class="form-control input-sm">
					     <option value= "0">Select Package</option>
					  	<c:forEach var="pack" items="${packageList}" varStatus="theCount">
					  	 <option value= '${pack.package_id}'> ${pack.package_Name}</option>
					  	</c:forEach>
					  	 
					  	 
				  	 </select>
                </div>
                 <div class="col-sm-2">
					  <label for="packageName">Select Dealer</label>
                	<select id="dealerName" name="dealerName" class="form-control input-sm">
					     <option value= "0">Select Dealer</option>
					  	<c:forEach var="pack" items="${DealerList}" varStatus="theCount">  	
					  	 <c:if test="${pack.status.status_id == 1 || pack.status.status_id == null}">
					  	 <option value= '${pack.id}'>${pack.fullName}   &nbsp;&nbsp;( ${pack.dealecode} )</option>                                              
				         </c:if>	  	
					  	</c:forEach>
					  	 
					  	 
				  	 </select>
                </div>
				<div class="col-sm-2">
                  <label for="password" >Enter No Of Kit</label>
                    <input type="text" class="form-control input-sm" id="noOfKIt" name="noOfKIt" placeholder="no of kits" onkeypress="return event.charCode ">= 48 && event.charCode <= 57 || event.keyCode === 8 || event.keyCode === 9 || event.keyCode === 37 ||event.keyCode === 39 ||event.keyCode === 46'>
                  </div><br>
                  <div class="col-sm-2">
                  
                    <button type="button" class="btn btn-primary" onclick="return assignKit();" >Allocate Kit</button>
                  </div>
                  
                </div>
              
              </div>
              
              
            </form> 
              
              </div>
              
              
              <script type="text/javascript">
              
             	 	$("#dealerNameSelect").hide();
      				$("#noOfKItSelect").hide();
      				$("#kisAssignSelect").hide();  
      				$("#packageNameSelect1").hide();
              </script>
              <div class="tab-pane" id="state_toggle">
              
              
              <div class="alert alert-danger alert-dismissable"
							id="dealerNameSelect1">
							<button type="button" class="close" data-dismiss="alert"
						aria-hidden="true">&times;</button>
							<strong>Error: </strong>Please Select Dealer Name. 
			</div>
			
			  <div class="alert alert-danger alert-dismissable"
							id="noOfKItSelect1">
							<button type="button" class="close" data-dismiss="alert"
						aria-hidden="true">&times;</button>
							<strong>Error: </strong>Please Enter No Of Kits. 
			</div>
			
			
			 <div class="alert alert-success alert-dismissable"
							id="kisAssignSelect1">
							<button type="button" class="close" data-dismiss="alert"
						aria-hidden="true">&times;</button>
							<strong>Error: </strong>Kit Return Successfully. 
			</div>
			
			<div class="alert alert-danger alert-dismissable"
							id="packageNameSelect2">
							<button type="button" class="close" data-dismiss="alert"
						aria-hidden="true">&times;</button>
							<strong>Error: </strong>Please Select Package Name. 
			</div>
              
              	<form class="form-horizontal" >
              <div class="box-body">
                <div class="form-group">
                 
               
                
					 <div class="col-sm-2">
					  <label for="packageName">Select Package</label>
                	<select id="packageNamed1" name="packageNamed1" class="form-control input-sm">
					     <option value= "0">Select Package</option>
					  	<c:forEach var="pack" items="${packageList}" varStatus="theCount">
					  	 <option value= '${pack.package_id}'> ${pack.package_Name}</option>
					  	</c:forEach>
					  	 
					  	 
				  	 </select>
                </div>
                 <div class="col-sm-2">
					  <label for="packageName1">Select Dealer</label>
                	<select id="dealerName1" name="dealerName1" class="form-control input-sm">
					     <option value= "0">Select Package</option>
					  	<c:forEach var="pack" items="${DealerList}" varStatus="theCount">
					  	
					  	 <c:if test="${pack.status.status_id == 1 || pack.status.status_id == null}">
					  	 <option value= '${pack.id}'>${pack.fullName}   &nbsp;&nbsp;( ${pack.dealecode} )</option>       
				         </c:if>	
					  	</c:forEach>
					  	 
					  	 
				  	 </select>
                </div>
				<div class="col-sm-2">
                  <label for="password" >Enter No Of Kit</label>
                    <input type="text" class="form-control input-sm" id="noOfKIt1" name="noOfKIt1" placeholder="no of kits" onkeypress="return event.charCode ">= 48 && event.charCode <= 57 || event.keyCode === 8 || event.keyCode === 9 || event.keyCode === 37 ||event.keyCode === 39 ||event.keyCode === 46'>
                  </div>
                  <br>
                  <div class="col-sm-2">
                  
                    <button type="button" class="btn btn-primary" onclick="return returnKit();" >Return Kit</button>
                  </div>
                  
                </div>
              
              </div>
              
              
            </form>
              
              </div>
              
              
              <script type="text/javascript">
              
             	 	$("#dealerNameSelect1").hide();
      				$("#noOfKItSelect1").hide();
      				$("#kisAssignSelect1").hide(); 
      				$("#packageNameSelect2").hide();
              </script>
              
              </div>

				<br>

            <div>
            
            							<table id="example5"
											class="table table-bordered table-striped">
											<thead>
												<tr>
													<th>Sr No</th>
													<th>Dealer Name</th>
													<th>Package Name</th>
													<!-- <th>Gender</th> -->
													<th>Total Kit</th>
													<th>Used Kit</th>
													<th>Return Kit</th>  
													<th>Remaining Kit</th>
												</tr>
											</thead>
											<tbody>
											</tbody>
										</table>
										
						</div>	
						
						<script type="text/javascript">
						
						
						var data = eval('${userModelDetailList}');
						
						var table = $('#example5')
								.DataTable(
										{
											"aaData" : data,
											"pagingType" : "full_numbers",
											"aoColumns" : [
													{
														"data" : "id"
													},
													{
														"data" : "dealer.fullName"  
													},
													
													{
														"data" : "pack.package_Name"  
													},
													{
														"data" : "totalKit"  
													},
													{
														"data" : "usedKit"  
													},
													{
														"data" : "returnKit"  
													},  
													{
														"data" : "remKit"  
													}
													]
										});     
						
						
						</script>			
            
            <!--Model code   -->
            
            
            
            <!--MOdel code end  -->
      
      
      </div>
    </div>
    
    
    <!--ENd  -->
    
    
                
                
                
                
                
                
                
                 <!-- panel-danger  style="background-color:#d23779"  -->
      
            <div class="panel panel-danger">
      		<div class="panel-heading" style="background-color:#d23779"><font color="White">Upload Excel File Of Kit</font></div>
      		<div class="panel-body">
      		
      		    <div class="box-header" >
              <form class="form-horizontal" enctype="multipart/form-data" id="Myform1" name="Myform1">
              <div class="box-body">
                <div class="form-group">
                
                
                 
					 <div class="col-sm-2">
					 
					<label for="packageName1">Select Package</label>
                	<select id="packageName1" name="packageName1" class="form-control input-sm">
					     <option value= "0">Select Package</option>
					  	<c:forEach var="pack" items="${packageList}" varStatus="theCount">
					  	 <option value= '${pack.package_id}'> ${pack.package_Name}</option>
					  	</c:forEach>
					  	 
				  	 </select>
                </div>
               
                 <div class="col-sm-2">
                   <label for="packageName">Upload File</label>
                  <input type="file" name="loader11" id="loader11"/>
				</div>
                  <div class="col-sm-2">
                  <label for="packageName2">&nbsp;&nbsp;</label>
                    <button type="button" class="btn btn-default" onclick="return uploadDocumentFile();"style="margin-left:-8px;margin-bottom:-2px;margin-top:20px;min-height:25px;">Save</button>
                  </div>
                </div>
              </div>
            </form> 		
      	    </div>
   			 </div>
   			 </div> 
            
              
          
            <!-- /.box-header -->
            
     <div class="panel panel-danger">
      <div class="panel-body">
      	<div class="form-group">
              <div class="col-sm-2">
					  <label for="packageName2">Select Package</label>
                	<select id="packageName2" name="packageName2" class="form-control input-sm">
					     <option value= "0">Select Package</option>
					  	<c:forEach var="pack" items="${packageList}" varStatus="theCount">
					  	 <option value= '${pack.package_id}'> ${pack.package_Name}</option>
					  	</c:forEach>
					  	 
				  	 </select>
                </div>
						<br>
              <div class="col-sm-2">
                 <label for="packageName2">&nbsp;&nbsp;</label>
                <button class="btn btn-primary" onclick="return viewKitData();">View Data</button>
                  <br><br><br>
              </div>
              
              
                <br><br><br><br>
     
   
		      <div id="KitResult">
		      </div>
      
     
        <div id="light" class="" style="position: fixed; float: left; width: 40%; padding: 0px; margin: 0px; top: 30%; left: 50%; overflow: hidden; display: none;">
		
					<input type="hidden" id="yearValue" >
					
					<div class="meetingContent">
					
						
						<img src="${pageContext.request.contextPath}/resources/images/spinner.gif" class="logo" style="width: 150px">
							
						</div>
					Loading
				</div>
				<div id="fade" class="black_overlay"></div>
				
      <%-- <div id="KitResult">
      
                  <div class="box">
            <div class="box-body">
              <table id="example1" class="table table-bordered table-striped">
                <thead>
                <tr>
                 <th>No</th>
                 <th>User Name</th>
                 <th>Password</th>
                 <th>Package</th>
                  <th>Edit</th>
                
                
                </tr>
                </thead>
                <tbody>
             
                   <c:forEach var="kit" items="${kitList}" varStatus="theCount">
   					<tr>
			    	<td><a href="#">${theCount.count }</a></td> 
					<td><a href="#">${kit.kit_username}</a></td>
					<td><a href="#" >${kit.kit_password}</a></td>
					<td><a href="#" >${kit.package_id.package_Name}</a></td>
					
					
				    <tr>
				   </tr>
					
			</tr> 
   					</c:forEach>
                  
                  
               	
				    <tr>
				    <th></th>
				     <th></th>
				      <th></th>
				       <th></th>
				   </tr>
                </tbody>
              
              </table>
            </div>           <!-- /.box-body -->
          </div>
      </div> --%>
      
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
<!-- 	
 	<script>
 
  
  $('#example1').dataTable({
	  "pagingType": "full_numbers"
	}); 
  

</script>  -->

<script type="text/javascript">
		$("#packageNameSelect").hide(); 
		$("#userNameSelect").hide(); 
		$("#PasswordSelect").hide();
		$("#successSelect").hide();
		$("#uploadSuccess").hide(); 
		$("#uploadfail").hide();     
		
		
		
		
		
		/* var data =eval('${employeeList}');
		var table = $('#example1').DataTable( {
		"aaData": data,
		"aoColumns": [
		              {"data" : "kit_id"},
					    {"data" : "kit_username"}, 
					    {"data" : "kit_password"},
					    {"data" : "package_id.package_Name"},
					    {"data":null,"defaultContent":"<button onclick=fun(this)>Edit</button>"
					    	}
		]
		});
		
		
		
		function fun(aa){
			alert($(aa).closest('td').prev().prev().prev().text());
			
		} */
	
		
		
		 
		
		
		
		
		
</script>

<script>
function uploadDocumentFile()
{
	
		
		
			
			

			
	
	
				var packageName1=$("#packageName1").val();
				
				var arr = { Name:packageName1 };
				var fileNames="";
				var nameOfFile=null;
				 var oMyForm = new FormData();
				  var inp = document.getElementById('loader11');
				  var flag=0;
				  for (var i = 0; i < inp.files.length; ++i) {
					  var name = inp.files.item(i).name;
					 
					 files[i]=inp.files.item(i).name;
					 
					flag=1;
				      oMyForm.append("file"+i, loader11.files[i]);
				      
					}
				  oMyForm.append("jsonObjectData", JSON.stringify(arr));
				
				  var lastFour = files[0].substr(files[0].length - 5);
				
				  
				
					   
			if(lastFour !=".xlsx")
						{
						
							alert('Please Import Only Excel file');
							return false;
						}
				  
				
					   var other_data = $('Myform').serialize();
					   
							  if(flag==1){
				    	
				  $.ajax({
						   	       dataType : 'text',
						           url : "uploadMyFile",
						           data : oMyForm,
						           type : "POST",
						           enctype: 'multipart/form-data',
						           processData: false,
						           contentType:false,
						          success : function(result) {
						        	  
						        	 if(result=="success")
						        		 {
						        		 
						        			 $("#packageName1").val("");
						        			 $("#loader11").val("");	
						        			 $("#uploadSuccess").slideDown();
						     				$("#uploadSuccess").fadeOut(5000);
						        		 
						        		 }
						        	 else
						        		 {
						        		 $("#packageName1").val("");
					        			 $("#loader11").val("");
						        			 $("#uploadfail").slideDown();
						     				 $("#uploadfail").fadeOut(5000);
						        		 
						        		 }
						        	  
						        
								  
						 },
			          error : function(result){
			             console.log('error'+JSON.stringify(result));
			             return false;
			          } 
				          
					  }); 
					  
				  } 

				  
				
		}




</script>


<script type="text/javascript">

function updateKit(aa)
{

	var kitid=$(aa).closest('td').prev().prev().prev().prev().prev().text();
	var kituserName=$(aa).closest('td').prev().prev().prev().prev().text();
	var kitpassword=$(aa).closest('td').prev().prev().prev().text();
	var status=$(aa).closest('td').prev().text();
	var packageName=$("#uppackageName").val();
	$("#hiddenkit").val(kitid);  
	$("#uppackageName").val(packageName);  
	$("#kitId").val(kituserName);
	$("#kitpasswordd").val(kitpassword);   
	$("#delkitId").val(kituserName);
	$("#delkitpasswordd").val(kitpassword);
	$("#delkitstatus").val(status); 
	$('#myModal').modal('show');
	
	

}

</script>


</body>
</html>







