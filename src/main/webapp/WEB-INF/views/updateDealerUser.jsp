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
								<font color="White">Update Dealer Information Of Registered User</font>
							</div>
							<div class="panel-body">
							
							
							
							<div class="alert alert-danger alert-dismissable"
									id="oneSelect">
									<button type="button" class="close" data-dismiss="alert"
										aria-hidden="true">&times;</button>
									<strong>Error: </strong>Invalid Registration Id.

								</div>  
								
								
								<div class="alert alert-danger alert-dismissable"
									id="twoSelect">
									<button type="button" class="close" data-dismiss="alert"
										aria-hidden="true">&times;</button>
									<strong>Error: </strong>This Registration Has Been Done By Another Dealer.

								</div>
								
								<div class="alert alert-success alert-dismissable"
									id="successSelect">
									<button type="button" class="close" data-dismiss="alert"
										aria-hidden="true">&times;</button>
									<strong>Success: </strong>Dealer Info Updated successfully.

								</div>  
								
								
								<div class="alert alert-danger alert-dismissable"
									id="regIdSelect">
									<button type="button" class="close" data-dismiss="alert"
										aria-hidden="true">&times;</button>
									<strong>Error: </strong>Please Enter Registration Id.

								</div>
								
								<div class="alert alert-danger alert-dismissable"
									id="passwordSelect">
									<button type="button" class="close" data-dismiss="alert"
										aria-hidden="true">&times;</button>
									<strong>Error: </strong>Please Enter Kit Password.

								</div>

								<div class="form-group">



									<div class="col-sm-2">
										<label for="packageName">Registration Id</label> <input
											type="text" id="regId" name="regId"
											class="form-control input-sm" onkeypress="return event.charCode ">= 48 && event.charCode <= 57 || event.keyCode === 8 || event.keyCode === 9 '>
									</div>

									

									<div class="col-sm-2">
										<label for="packageName2">&nbsp;&nbsp;</label>
										<br>
										<button class="btn btn-primary"
											onclick="return updateUserDealerRelationship();">Update
											</button>
									</div>
								</div>
								</div>
								
								
								<script type="text/javascript">
								
								$("#successSelect").hide(); 
								$("#oneSelect").hide(); 
								$("#twoSelect").hide();
								$("#passwordSelect").hide();
								$("#regIdSelect").hide();
								
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







