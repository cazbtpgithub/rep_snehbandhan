
<!DOCTYPE html>
<html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Revenue Home</title>
<!-- Tell the browser to be responsive to screen width -->
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
<jsp:include page="body.jsp"></jsp:include>
<script src="${pageContext.request.contextPath}/resources/Revenue/revenue.js" charset='utf-8'></script>

<style>

.box-custom{
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

		<jsp:include page="menu.jsp"></jsp:include>

		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
    <!-- Content Header (Page header) -->
   

    <!-- Main content -->
    <section class="content">
      <div class="row">
       
        <div class="col-md-12">
         
    	
            <!-- /.box-header -->
            
            <div class="panel panel-danger">
            <div class="panel-heading" style="background-color:#d23779"><font color="White">Revenue Details</font></div>
      <div class="panel-body">

								<div class="form-group">



									<div class="col-sm-2">
										<label for="packageName">Select Package</label> <select
											id="packageName" name="packageName"
											class="form-control input-sm">
											<option value="0">Select Package</option>
											<c:forEach var="pack" items="${packageList}"
												varStatus="theCount">
												<option value="${pack.package_id}">
													${pack.package_Name}</option>
											</c:forEach>
											<option value="All">All</option>
										</select>
									</div>
									<br>
									 <div class="col-sm-2">
                  				 <label for="packageName2">&nbsp;&nbsp;</label>
                    	<button class="btn btn-primary" id="getRevenueButton" onclick="return getRevenue();">View Revenue</button>
                  </div>
								</div>
								
								<br><br>  <br><br>
								
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


								
      
      
      							<div class="box">
									<div class="box-body">
										
										
										<%-- <table id="example1"
											class="table table-bordered table-striped">
											<thead>
												<tr>
													<th>Sr No.</th>
													<th>Kit Id</th>
													<th>Date</th>
													<th>Price</th>
													
												</tr>
											</thead>
											<tbody>
													
												
								<c:set var="counter" value="${1}"/>	
								  <c:set var="grandTotal" value="${0}"/>
								<c:forEach items="${revMap}" var="events" varStatus="theCount">
												
									   <c:set var="subTotal" value="${0}"/>
									    
									   
  										<c:forEach items="${events.value}" var="item">
		  											
		  													  
		  													  <tr>
																<td>${counter}</td>
																<td>${item.Kit_id.kit_username}</td>  not working
																<td>${item.userModel.kit.kit_username}</td>
																<td>${item.date}</td>
																<td>${item.payment}</td>
															</tr>
														
													<c:set var="counter" value="${counter + 1}"/>		
		       										<c:set var="subTotal" value="${subTotal + item.payment}"/>
		       											
       																 
   									 </c:forEach>
   									 
   									 
   									 <c:set var="grandTotal" value="${grandTotal + subTotal}"/>
   									 
   									 <td>${counter}</td>
   									 <td></td>
   									 <td><font color="red"><b>Sub Total</b></font></td>
   									 <td><font color="red"><b>${subTotal}</b></font></td>
   									 
   						</c:forEach>
									<tr>
									
									<td>${counter+1}</td>
   									 <td></td>
   									 <td><font color="Green"><b>Grand Total</b></font></td>
   									 <td><font color="Green"><b>${grandTotal}</b></font></td>		
									
									
									</tr>
												
											
											
											</tbody>

										</table> --%>
									</div>
									<!-- /.box-body -->
								</div>
                
                 
                 
				
                  
                </div>
              
      <br><br>
   
      
      
     
  
				
     
      
      </div>
    </div>
            
                
                
                
                
              </div>
              
            </div>
            
               
                
             
              
              <!-- /.tab-pane -->
           
		<div class="control-sidebar-bg"></div>
	
</section>
</div>


	<script>
		$.widget.bridge('uibutton', $.ui.button);
	</script>
<script>
		$('#example1').dataTable({
			"pagingType" : "full_numbers",

		});
	</script>


</body>
</html>







