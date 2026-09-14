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
              <li class="active"><a href="#country_toggle" data-toggle="tab">Country</a></li>
              <li><a href="#state_toggle" data-toggle="tab">State</a></li>
              <li><a href="#district_toggle" data-toggle="tab">District</a></li>
               <li><a href="#subdistrict_toggle" data-toggle="tab">Sub District</a></li>
            </ul>
            <div class="tab-content">
              <div class="active tab-pane" id="country_toggle">
                
                
                
                
         <div class="panel panel-danger">
      <div class="panel-heading" style="background-color:#d23779"><font color="white">Save Country Information</font></div>
      <div class="panel-body">
      
		      <div class="alert alert-danger alert-dismissable"
							id="cnameSelect">
							<button type="button" class="close" data-dismiss="alert"
						aria-hidden="true">&times;</button>
							<strong>Error: </strong>Please Enter Country Name. 
			</div>
			
			
		    
				
				<div class="alert alert-success alert-dismissable"
					id="countryNamesuccess">
					<button type="button" class="close" data-dismiss="alert"
				aria-hidden="true">&times;</button>
					<strong>Message: </strong>Country Name saved Successfully.
				</div> 
			 
      
      <form class="form-horizontal">
              <div class="box-body">
                <div class="form-group">
                  <label for="countryName" class="col-sm-2 control-label">Country</label>

                  <div class="col-sm-4">
                    <input type="text" class="form-control input-sm" id="countryName" name="countryName" placeholder="Enter Country Name">
                  </div>
                  <div class="col-sm-4">
                   <button type="button" class="btn btn-primary" onclick="return saveCountry();">Add Country</button>
                  </div>
                </div>
              
               
              </div>
              
              
            </form>
      
      </div>
    </div>
        
        
              
              
          
         
         <div class="panel panel-danger">
   
      <div class="panel-body">
       <div class="box">
            <div class="box-body">
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
    </div>
            
           
                
                
                
                
              </div>
              <!-- /.tab-pane -->
         <div class="tab-pane" id="state_toggle">
              
              <div class="alert alert-success alert-dismissable"
					id="stateNamesuccess">
					<button type="button" class="close" data-dismiss="alert"
				aria-hidden="true">&times;</button>
					<strong>Message: </strong>State Details Saved Successfully.
				</div>
				
				<div class="alert alert-danger alert-dismissable"
							id="cnameSelect1">
							<button type="button" class="close" data-dismiss="alert"
						aria-hidden="true">&times;</button>
							<strong>Error: </strong>Please Select Country Name. 
			</div>
			
			  <div class="alert alert-danger alert-dismissable"
							id="stateNameselect">
							<button type="button" class="close" data-dismiss="alert"
						aria-hidden="true">&times;</button>
							<strong>Error: </strong>Please Enter State Name. 
			</div>
			<div class="alert alert-danger alert-dismissable"
							id="stateNameAvailable">
							<button type="button" class="close" data-dismiss="alert"
						aria-hidden="true">&times;</button>
							<strong>Error: </strong>State Name Already Available.
			</div>
			
              
             	 <div class="panel panel-danger">
      <div class="panel-heading" style="background-color:#d23779"> <font color="white">Save State Information</font></div>
      <div class="panel-body">
      
        
      <form class="form-horizontal">
              <div class="box-body">
                <div class="form-group">
                
                
                
               <div class="col-sm-3">
                <label for="countrynameState">Country Name</label>
                	<select id="countrynameState" name="countrynameState" class="form-control input-sm" onchange="return" loadStateData();">
					     <option value= "0">Select Country</option>
					  	
					  	<c:forEach var="country" items="${countryList}" varStatus="theCount">
					  	 <option value= '${country.country_id}'> ${country.country_Name}</option>
					  	</c:forEach>
					  	
					  	 
				  	 </select>
                </div>
              
                <!--   <label for="stateName" class="col-sm-2 control-label">State Name</label> -->

                  <div class="col-sm-3">
                   <label for="stateName">State Name</label>
                  <input type="text" class="form-control input-sm" id="stateName" name="stateName" placeholder="Enter State Name">
                  </div>
                  <br>
                  
                         <div class="col-sm-3">
                   <button type="button" class="btn btn-primary" onclick="return saveState();">Add State</button>
                  </div>
              </div>
                </div>
              
        
              
              
            </form>
            </div>
    </div>  
               
               
              
              
             <div class="panel panel-danger">
      
      <div class="panel-body">
      
         <div class="box">
           
            <!-- /.box-header -->
            <div class="box-body">
              <table id="example2" class="table table-bordered table-striped">
                <thead>
                <tr>
                  <th>Sr No</th>
                  <th>Country Name</th>
                  <th>State Name</th>
                </tr>
                </thead>
                <tbody>
                
             </tbody>
                
              </table>
            </div>
            <!-- /.box-body -->
          </div>
      </div>
    </div>
             
             
             
              
            
        </div>
              <!-- /.tab-pane -->

              <div class="tab-pane" id="district_toggle">
              
              
              
           
            <div class="alert alert-danger alert-dismissable"
							id="countryNamedistSelect">
							<button type="button" class="close" data-dismiss="alert"
						aria-hidden="true">&times;</button>
							<strong>Error: </strong>Please Select Country Name. 
			</div>
			
			 <div class="alert alert-danger alert-dismissable"
							id="distNameSelect">
							<button type="button" class="close" data-dismiss="alert"
						aria-hidden="true">&times;</button>
							<strong>Error: </strong>Please Enter District Name. 
			</div>
			
			
			
		      <div class="alert alert-danger alert-dismissable"
							id="stateNameDistselect">
							<button type="button" class="close" data-dismiss="alert"
						aria-hidden="true">&times;</button>
							<strong>Error: </strong>Please Select State Name. 
			</div>
			
			    <div class="alert alert-danger alert-dismissable"
							id="distNameAvailable">
							<button type="button" class="close" data-dismiss="alert"
						aria-hidden="true">&times;</button>
							<strong>Error: </strong>District Name Already Available. 
			</div>
				
				<div class="alert alert-success alert-dismissable"
					id="suceessdistNameSelect">
					<button type="button" class="close" data-dismiss="alert"
				aria-hidden="true">&times;</button>
					<strong>Message: </strong>District Name saved Successfully.
				</div> 
				
				
              
              
              <div class="panel panel-danger">
      <div class="panel-heading"style="background-color:#d23779"><font color="white">Save District Information</font></div>
      <div class="panel-body">
      
      <form class="form-horizontal">
              <div class="box-body">
                <div class="form-group">
                  <!-- <label for="districtname" class="col-sm-2 control-label">District Name</label> -->

					 
               <div class="col-sm-3">
                  <label for="countryNamedist">Country Name</label>
                	<select id="countryNamedist" name="countryNamedist" class="form-control input-sm" onchange="  return" setStateForCountry();">
					  <option value= "0">Select Country</option>
					  
					  	<c:forEach var="country" items="${countryList}" varStatus="theCount">
					  	 <option value= '${country.country_id}'> ${country.country_Name}</option>
					  	</c:forEach>
					  	
					  	 
					  	 
				  	 </select>
                </div>
                 
               <div class="col-sm-3">
                 <label for="stateNameDist">State Name</label>
                	<select id="stateNameDist" name="stateNameDist" class="form-control input-sm" onchange=" return" loadDistrict();">
					<option value= "0">Select State</option>
					
					<!-- <option value="1">Maharashtra</option> -->
					  	 
				  	 </select> 
                </div>
                  <div class="col-sm-3">
                  <label for="districtname">District Name</label>
                    <input type="text" class="form-control input-sm" id="districtname" name="districtname" placeholder="Enter District Name">
                  </div><br>
                  <div class="col-sm-3">
                    <button type="button" class="btn btn-primary" onclick="return saveDistrict();">Add District</button>
                  </div>
                </div>
              
               
              </div>
              
              
            </form>
      
      </div>
    </div>
             
              
              <div class="panel panel-danger">
     
      <div class="panel-body">
        <div class="box">
            <div class="box-header">
            
            <div class="box-body">
              <table id="example3" class="table table-bordered table-striped">
                <thead>
                <tr>
                  <th>Sr No</th>
                  <th>Country Name</th>
                  <th>State Name</th>
                  <th>District Name</th>
                 
                </tr>
                </thead>
                <tbody>
               
               </tbody>
              </table>
            </div>
            <!-- /.box-body -->
          </div>
              
             
                
              </div>
      
      </div>
    </div>
            
              
             
              </div>
              
              
              <div class="tab-pane" id="subdistrict_toggle">
              
            
             <div class="alert alert-danger alert-dismissable"
							id="countryNamesubdistSelect">
							<button type="button" class="close" data-dismiss="alert"
						aria-hidden="true">&times;</button>
							<strong>Error: </strong>Please Select Country Name. 
			</div>
			
			 <div class="alert alert-danger alert-dismissable"
							id="distNamesubDistSelect">
							<button type="button" class="close" data-dismiss="alert"
						aria-hidden="true">&times;</button>
							<strong>Error: </strong>Please Select District Name. 
			</div>
			
			
			
		      <div class="alert alert-danger alert-dismissable"
							id="stateNamesubDistselect">
							<button type="button" class="close" data-dismiss="alert"
						aria-hidden="true">&times;</button>
							<strong>Error: </strong>Please Select State Name. 
			</div>
			
			  <div class="alert alert-danger alert-dismissable"
							id="subDistNameSelect">
							<button type="button" class="close" data-dismiss="alert"
						aria-hidden="true">&times;</button>
							<strong>Error: </strong>Please Enter Sub District Name. 
			</div>
			
			  <div class="alert alert-danger alert-dismissable"
							id="subDistAvailable">
							<button type="button" class="close" data-dismiss="alert"
						aria-hidden="true">&times;</button>
							<strong>Error: </strong>Sub District Name Already Available. 
			</div>
				
				<div class="alert alert-success alert-dismissable"
					id="subDistNameSelectssuccess">
					<button type="button" class="close" data-dismiss="alert"
				aria-hidden="true">&times;</button>
					<strong>Message: </strong> Sub District Name saved Successfully.
				</div> 
            
            
         
    <div class="panel panel-danger">
      <div class="panel-heading"style="background-color:#d23779"><font color="White">Save Sub District Information</font></div>
      <div class="panel-body">
      
      <form class="form-horizontal">
              <div class="box-body">
                <div class="form-group">
                 <!--  <label for="subdistName" class="col-sm-2 control-label">Sub District Name</label> -->
                  <div class="col-sm-2">
                   <label for="countryNamesubDist">Country Name</label>
                	<select id="countryNamesubDist" name="countryNamesubDist" class="form-control input-sm" onchange=" return" setStateForSubdist();">
					     <option value= "0">Select Country</option>
					     
					     <c:forEach var="country" items="${countryList}" varStatus="theCount">
					  	 <option value= '${country.country_id}'> ${country.country_Name}</option>
					  	</c:forEach>
					  	
					  	
					  	 
				  	 </select>
                </div>
                 <div class="col-sm-2">
                 <label for="stateNameSubDist">State Name</label>
                	<select id="stateNameSubDist" name="stateNameSubDist" class="form-control input-sm" onchange=" return" setDistrictForState();">
					     <option value= "0">Select State</option>
					  	 
					  	 
				 </select>
                </div>
                 <div class="col-sm-2">
                 <label for="DistNameSubDist">District Name</label>
                	<select id="DistNameSubDist" name="DistNameSubDist" class="form-control input-sm" onchange=" return" loadSubdistrict();">
					     <option value= "0">Select District</option>
					  	 
					  	 
				  </select>
                </div>

                  <div class="col-sm-2">
                  <label for="subdistName">SubDistrict</label>
                    <input type="text" class="form-control input-sm" id="subdistName" name="subdistName" placeholder="Enter Sub District Name">
                  </div>
                  <br>
                  <div class="col-sm-2">
                    <button type="button" class="btn btn-primary" onclick="return saveSubDistrict();">Add Sub District</button>
                  </div>
                </div>
              
               
              </div>
              
              
            </form>

		</div>
    </div>
       
              
              <div class="panel panel-danger">

      <div class="panel-body">
      
       <div class="box">
            <div class="box-header">
            
            
            <!-- /.box-header -->
            <div class="box-body">
              <table id="example4" class="table table-bordered table-striped">
               <thead>
                <tr>
                  <th>Sr No</th>
                  <th>Country Name</th>
                  <th>State Name</th>
                  <th>District Name</th>
                  <th>Sub District Name</th>
                 
                </tr>
                </thead>
                <tbody>
               
               </tbody>
              </table>
            </div>
            </div>
            <!-- /.box-body -->
          </div>
      
      </div>
    </div>
           
            
              
                
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
 
  
  $('#example1').dataTable({
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
$("#cnameSelect").hide(); 
$("#cnameSelect1").hide(); 
$("#countryNamesuccess").hide(); 
$("#stateNameselect").hide();
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

//countryNamesubdistSelect  stateNamesubDistselect distNamesubDistSelect  subDistNameSelect subDistNameSelectssuccess
    
</script>


</body>
</html>




