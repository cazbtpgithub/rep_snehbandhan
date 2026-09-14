<!DOCTYPE html>
<html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Sale Details</title>
<!-- Tell the browser to be responsive to screen width -->
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
<jsp:include page="body.jsp"></jsp:include>
<script
	src="${pageContext.request.contextPath}/resources/Revenue/revenue.js"
	charset='utf-8'></script>

<style>
.box-custom {
	background: #dddddd;
}

label {
	margin-right: -20px;
	font: normal 12px cambria !important;
}

table {
	font: normal 12px cambria !important;
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

						<div class="panel panel-danger">
							<div class="panel-heading" style="background-color: #d23779;">
								<font color="White">Update Sale Kit Details</font>
							</div>
							<div class="panel-body">
							
							
							
							<div class="alert alert-danger alert-dismissable"
									id="oneSelect">
									<button type="button" class="close" data-dismiss="alert"
										aria-hidden="true">&times;</button>
									<strong>Error: </strong>Invalid KitUsername Or Password.

								</div>  
								
								
								<div class="alert alert-danger alert-dismissable"
									id="twoSelect">
									<button type="button" class="close" data-dismiss="alert"
										aria-hidden="true">&times;</button>
									<strong>Error: </strong>This Kit Already Sold By Another Dealer.

								</div>
								
								<div class="alert alert-success alert-dismissable"
									id="successSelect">
									<button type="button" class="close" data-dismiss="alert"
										aria-hidden="true">&times;</button>
									<strong>Error: </strong>Dealer Info Updated successfully.

								</div>  
								
								
								<div class="alert alert-danger alert-dismissable"
									id="kitSelect">
									<button type="button" class="close" data-dismiss="alert"
										aria-hidden="true">&times;</button>
									<strong>Error: </strong>Please Enter Kit Username.

								</div>
								
								<div class="alert alert-danger alert-dismissable"
									id="passwordSelect">
									<button type="button" class="close" data-dismiss="alert"
										aria-hidden="true">&times;</button>
									<strong>Error: </strong>Please Enter Kit Password.

								</div>

								<div class="form-group">



									<div class="col-sm-2">
										<label for="packageName">Kit Username</label> <input
											type="text" id="kidUserName" name="kidUserName"
											class="form-control input-sm">
									</div>

									<div class="col-sm-2">
										<label for="packageName">Kit Password</label> <input
											type="text" id="kidPassword" name="kidPassword"
											class="form-control input-sm">
									</div>

									<div class="col-sm-2">
										<label for="packageName2">&nbsp;&nbsp;</label>
										<br>
										<button class="btn btn-primary"
											onclick="return updateSaleKitDealer();">Update
											</button>
									</div>
								</div>
								</div>
								
								
								<script type="text/javascript">
								
								$("#successSelect").hide(); 
								$("#oneSelect").hide(); 
								$("#twoSelect").hide();
								$("#passwordSelect").hide();
								$("#kitSelect").hide();
								
							</script>

							<br> <br>








						</div>
						
						
						
						   
<!--Start -->
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
					  <label for="packageName">Select SubDealer</label>
                	<select id="dealerName" name="dealerName" class="form-control input-sm">
					     <option value= "0">Select SubDealer</option>
					  	<c:forEach var="pack" items="${DealerList}" varStatus="theCount">
					  	 <c:if test="${pack.status.status_id == 1 || pack.status.status_id == null}">
					  	  <option value="${pack.id}"> ${pack.fullName}   &nbsp;&nbsp;( ${pack.dealecode} )</option>
                                                                      
				         </c:if>
												
					  	
					  	</c:forEach>
					  	 
					  	 
				  	 </select>
                </div>
				<div class="col-sm-2">
                  <label for="password" >Enter No Of Kit</label>
                    <input type="text" class="form-control input-sm" id="noOfKIt" name="noOfKIt" placeholder="no of kits" onkeypress="return event.charCode ">= 48 && event.charCode <= 57 || event.keyCode === 8 || event.keyCode === 9 || event.keyCode === 37 ||event.keyCode === 39 ||event.keyCode === 46'>
                  </div><br>
                  <div class="col-sm-2">
                  
                    <button type="button" class="btn btn-primary" onclick="return assignKitToSubDealer();" >Allocate Kit</button>
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
					  	 <option value="${pack.id}"> ${pack.fullName}   &nbsp;&nbsp;( ${pack.dealecode} )</option>
                                                                      
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
                  
                    <button type="button" class="btn btn-primary" onclick="return returnKitToSubDealer();" >Return Kit</button>
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
						
						
					</div>
					
					





				</div>
		</div>





		<!-- /.tab-pane -->

		<div class="control-sidebar-bg"></div>

		</section>
	</div>

</body>
</html>







