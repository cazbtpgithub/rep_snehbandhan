<!DOCTYPE html>
<html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Dealer Report</title>
<!-- Tell the browser to be responsive to screen width -->
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
<jsp:include page="body.jsp"></jsp:include>
<script src="${pageContext.request.contextPath}/resources/Revenue/upgradeduser.js" charset='utf-8'></script>
<script src="${pageContext.request.contextPath}/resources/DealerReport/dealerReport.js" charset='utf-8'></script>

<style>

.box-custom{
	background: #dddddd;
}



label {
    margin-right: -20px;
    font: normal 12px courier !important;
}

 table {
   
       font: bold 12px Courier New !important;
} 
</style>

<%-- <c:forEach var="kit" items="${kitList}" varStatus="theCount">
	<script>
	allUserName.push("${kit.kit_username}");
</script>
</c:forEach> --%>



<link rel="stylesheet" href="resources/datepicker/css/datepicker.css">
<script 
	src="resources/datepicker/js/datepicker.js"></script>


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
              <li class="active"><a href="#dealer_toggle" data-toggle="tab">Dealer Details</a></li>
              <li><a href="#dealerRevenue_toggle" data-toggle="tab">Revenue Details</a></li>
                 <li><a href="#dealerKit_toggle" data-toggle="tab">Dealer kit  Details</a></li>
                                <li><a href="#dealerMember_toggle" data-toggle="tab">Dealer Registered Member Details</a></li>

            </ul>
            <div class="tab-content">
            
              <div class="active tab-pane" id="dealer_toggle">
       
       <div class="alert alert-danger alert-dismissable"
							id="fDateselect">
							<button type="button" class="close" data-dismiss="alert"
						aria-hidden="true">&times;</button>
							<strong>Error: </strong>Please Select From Date. 
			</div>
		
	 <div class="alert alert-danger alert-dismissable"
							id="tdateselect">
							<button type="button" class="close" data-dismiss="alert"
						aria-hidden="true">&times;</button>
							<strong>Error: </strong>Please Select To Date 
			</div>
				
		
       <div class="panel panel-danger">
      <div class="panel-heading" style="background-color:#d23779"><font color="White">Dealer Details</font></div>
      <div class="panel-body">
      <form class="form-horizontal" >
              <div class="box-body">
                <div class="form-group">
                 
                <h5 style="color: green;">Note : You can get Date wise all dealer and sub dealer details here , please select date to generate report</h5>
                        <br>  
                
					 <div class="col-sm-3">
					  <label for="packageName">Select From Date</label>
					  
					  <input type="text" id="fDate" name="fDate" class="form-control form-control-sm"
													placeholder="From Date"  readonly="readonly">
                	
                </div>
                <div class="col-sm-3">
               	 <label for="userName" >Select To Date</label>
                    <input type="text" id="tdate" name="tdate" class="form-control form-control-sm"
													placeholder="To Date"  readonly="readonly">
                  </div>
				<br>
                  <div class="col-sm-3">
                  
                    <button type="button" class="btn btn-primary" id="printDealerDetailsButton" onclick="return printDealerDetails();" >Print Dealer Details</button>
                  </div>
                  
                </div>
              
               
              </div>
              
              
            </form>
             <br>
      
       <div id="KitResult">
      </div>
      </div>
    </div>
                
             
     
       
              </div>
              
              
                <!-- Second tab start  -->
         <div class="tab-pane" id="dealerRevenue_toggle">
              
  
                
       <div class="panel panel-danger">
      <div class="panel-heading" style="background-color:#d23779">
      <font color="White">Dealer Revenue Details</font></div>
      <div class="panel-body">
      <form class="form-horizontal" >
              <div class="box-body">
                <div class="form-group">
                 
                <h5 style="color: green;">Note : You can get Dealer wise and Package wise Revenue Details Here , 
                Please select dealer and package and press button to generate report</h5>
                        <br>  
                
					 <div class="col-sm-3">
					  <label for="packageName">Select Dealer</label> <select
											id="dealerName" name="dealerName"
											class="form-control input-sm">
											<option value="0">Select Dealer</option>
											<c:forEach var="pack" items="${DealerList}"
												varStatus="theCount">
												 <c:if test="${pack.status.status_id == 1 || pack.status.status_id == null}">                   
												<option value="${pack.id}"> ${pack.fullName}   &nbsp;&nbsp;( ${pack.dealecode} )</option>
				                                 </c:if>
												
											</c:forEach>

										</select>
                	
                </div>
                <div class="col-sm-3">
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
                  <div class="col-sm-3">
                  
                    <button type="button" class="btn btn-primary" id="printDealserRevenueDeatilsButton" onclick="printDealerRevenueDetails();" >Print Revenue Details</button>
                  </div>
                  
                </div>
              
               
              </div>
              
              
            </form>
             <br>
      
       <div id="KitResult">
      </div>
      </div>
    </div>
                
                 <!-- panel-danger  style="background-color:#d23779"  -->
     
      
        </div>
             
            		   <!-- Third tab start  -->
              <div class="tab-pane" id="dealerKit_toggle">
          
              
       <div class="panel panel-danger">
      <div class="panel-heading" style="background-color:#d23779"><font color="White">Dealer Kit Details</font></div>
      <div class="panel-body">
      <form class="form-horizontal" >
              <div class="box-body">
                <div class="form-group">
                 <h5 style="color: green;">Note : You can get Dealer wise  Kit Details Here , Please select dealer and press button to generate report</h5>
                        <br>    
					 <div class="col-sm-3">
					  <label for="packageName">Select Dealer</label> <select
											id="dealerName1" name="dealerName1"
											class="form-control input-sm">
											<option value="0">Select Dealer</option>
											<c:forEach var="pack" items="${DealerList}"
												varStatus="theCount">
												 <c:if test="${pack.status.status_id == 1 || pack.status.status_id == null}">
												   <option value="${pack.id}"> ${pack.fullName}   &nbsp;&nbsp;( ${pack.dealecode} )</option>
                                                                      
				                                 </c:if>
												
												
											</c:forEach>

										</select>
                	 </div>
                
        
				<br>
                  <div class="col-sm-3">
                  
                    <button type="button" class="btn btn-primary" id="printDealerKitDetailsButton" onclick="return printDealerKitDetails();" >Print Dealer Kit Details</button>
                  </div>
                  
                </div>
              
               
              </div>
              
              
            </form>
             <br>
      
       <div id="KitResult">
      </div>
      </div>
    </div>
                
                
		 
    </div>
       
		  <!-- Second tab start  -->
         <div class="tab-pane" id="dealerMember_toggle">
              
  
                
       <div class="panel panel-danger">
      <div class="panel-heading" style="background-color:#d23779">
      <font color="White">Dealer Wise Registered Member Details</font></div>
      <div class="panel-body">
      
      <div class="alert alert-danger alert-dismissable"
													id="selectDealer">
													<button type="button" class="close" data-dismiss="alert"
														aria-hidden="true">&times;</button>
													<strong>Error: </strong>Please Select Dealer.

	 </div>
      <form class="form-horizontal" >
              <div class="box-body">
                <div class="form-group">
                 
                <h5 style="color: green;">Note : You can get Dealer wise registered member Details Here , 
                Please select dealer and press button to generate report</h5>
                        <br>  
                
					 <div class="col-sm-3">
					  <label for="dealer_id">Select Dealer</label> <select
											id="dealerId" name="dealerId"
											class="form-control input-sm">
											<option value="0">Select Dealer</option>
											<c:forEach var="pack" items="${DealerList}"
												varStatus="theCount">
												 <c:if test="${pack.status.status_id == 1 || pack.status.status_id == null}">
												<option value="${pack.id}"> ${pack.fullName}   &nbsp;&nbsp;( ${pack.dealecode} )</option>
                                                                      
				                                 </c:if>
												
											</c:forEach>

										</select>
                	
                </div>
         
				<br>
                  <div class="col-sm-3">
                  
                    <button type="button" class="btn btn-primary" id="printDealerWiseMemberDetailsButton" onclick="printDealerWiseMemberDetails();" >Print Registered Member Details</button>
                  </div>
                  
                </div>
              
               
              </div>
              
              
            </form>
             <br>
      
       <div id="KitResult">
      </div>
      </div>
    </div>
                
                
            
        </div>
             
         </div>  
              <!-- /.tab-pane -->

          </div>
          
          <!-- /.nav-tabs-custom -->
          
          
        </div>
       

    </div>
              </div>
              
     
        <div id="light" class="" style="position: fixed; float: left; width: 40%; padding: 0px; margin: 0px; top: 30%; left: 50%; overflow: hidden; display: none;">
		
					<input type="hidden" id="yearValue" >
					
					<div class="meetingContent">
					
						
						<img src="${pageContext.request.contextPath}/resources/images/spinner.gif" class="logo" style="width: 150px">
							
						</div>
					Loading
				</div>
				<div id="fade" class="black_overlay"></div>
              
            </div>
            
               
                
             
              
              <!-- /.tab-pane -->
           
		<div class="control-sidebar-bg"></div>
	
</section>


	<!-- jQuery 2.2.3 -->
	<!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip -->
	<script>
		$.widget.bridge('uibutton', $.ui.button);
	</script>


<script type="text/javascript">
		$("#fDateselect").hide(); 
		$("#tdateselect").hide(); 
		$("#selectDealer").hide();
		
	
		
	
</script>
<script type="text/javascript">


$("#fDate").datepicker({
	dateFormat : 'dd/mm/yy',
	changeMonth : true,
	changeYear : true,
	yearRange : '1980:2099'
}).datepicker('setDate', new Date());



</script>
<script type="text/javascript">
$("#tdate").datepicker({
	dateFormat : 'dd/mm/yy',
	changeMonth : true,
	changeYear : true,
	yearRange : '1980:2099'
}).datepicker('setDate', new Date());



</script>
</body>
</html>







