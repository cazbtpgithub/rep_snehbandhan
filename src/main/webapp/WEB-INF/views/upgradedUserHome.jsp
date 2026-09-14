<!DOCTYPE html>
<html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Upgraded User Home</title>
<!-- Tell the browser to be responsive to screen width -->
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
<jsp:include page="body.jsp"></jsp:include>
<script src="${pageContext.request.contextPath}/resources/Revenue/upgradeduser.js" charset='utf-8'></script>

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
         
       
       <div class="alert alert-danger alert-dismissable"
							id="fdateselect">
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
      <div class="panel-heading" style="background-color:#d23779"><font color="White">Upgraded User Details</font></div>
      <div class="panel-body">
      <form class="form-horizontal" >
              <div class="box-body">
                <div class="form-group">
                 
               
                
					 <div class="col-sm-2">
					  <label for="packageName">Select From Date</label>
					  
					  <input type="text" id="fdate" name="fdate" class="form-control form-control-sm"
													placeholder="From Date"  readonly="readonly">
                	
                </div>
                <div class="col-sm-2">
               	 <label for="userName" >Select To Date</label>
                    <input type="text" id="tdate" name="tdate" class="form-control form-control-sm"
													placeholder="To Date"  readonly="readonly">
                  </div>
				<br>
                  <div class="col-sm-2">
                  
                    <button type="button" class="btn btn-primary" id="loadUpdateduserButton" onclick="return loadUpdateduser();" >Show Upgraded User Details</button>
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
     
        <div id="light" class="" style="position: fixed; float: left; width: 40%; padding: 0px; margin: 0px; top: 30%; left: 50%; overflow: hidden; display: none;">
		
					<input type="hidden" id="yearValue" >
					
					<div class="meetingContent">
					
						
						<img src="${pageContext.request.contextPath}/resources/images/spinner.gif" class="logo" style="width: 150px">
							
						</div>
					Loading
				</div>
				<div id="fade" class="black_overlay"></div>
	
      
      </div>
    </div>
            
                
                
                
                
              </div>
              
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
		$("#fdateselect").hide(); 
		$("#tdateselect").hide(); 
	
</script>
<script type="text/javascript">


$("#fdate").datepicker({
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







