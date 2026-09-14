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
	src="${pageContext.request.contextPath}/resources/Employee/salekit_UpdateUserforEmployee.js"
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
									<strong>Error: </strong>This Kit Already Sold By Another Employee.

								</div>
								
								<div class="alert alert-success alert-dismissable"
									id="successSelect">
									<button type="button" class="close" data-dismiss="alert"
										aria-hidden="true">&times;</button>
									<strong>Error: </strong>Employee Info Updated successfully.

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



									<div class="col-sm-3">
										<label for="packageName">Kit Username</label> <input
											type="text" id="kidUserName" name="kidUserName"
											class="form-control input-sm">
									</div>

									<div class="col-sm-3">
										<label for="packageName">Kit Password</label> <input
											type="text" id="kidPassword" name="kidPassword"
											class="form-control input-sm">
									</div>

							
								</div>
										<div class="col-sm-3">
										<label for="packageName2">&nbsp;&nbsp;</label>
										<button class="btn btn-primary"
											onclick="return updateSaleKitByEmployee();">Update
											</button>
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
					</div>
					
					





				</div>
		</div>





		<!-- /.tab-pane -->

		<div class="control-sidebar-bg"></div>

		</section>
	</div>

</body>
</html>







