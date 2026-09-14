<!DOCTYPE html>
<html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Super Admin Dashboard</title>
<!-- Tell the browser to be responsive to screen width -->
<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
<jsp:include page="body.jsp"></jsp:include>
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
			<!-- Main content -->
			<section class="content">
				<div class="row">
					<div class="col-md-12">
						<div class="panel panel-danger">
							<div class="panel-heading" style="background-color:#d23779"><font color="White">Daily Plan Activation Report</font></div>
							<div class="panel-body">
								<div class="box">
									<div class="box-body">
										<table id="example1" class="table table-bordered table-striped">
											<thead>
												<tr>
													<th>Sr No.</th>
													<th>Date</th>
													<th>User ID</th>
													<th>User Name</th>
													<th>Kit ID / Plan</th>
													<th>Price</th>
												</tr>
											</thead>
											<tbody>
												<c:set var="counter" value="1"/>
												<c:forEach var="item" items="${historyList}">
													
														<tr>
															<td>${counter}</td>
															<td>${item.date}</td>
															<td>${item.userModel.userId}</td>
															<td>${item.userModel.firstName}</td>
															<td>${item.kit_id.kit_username} (${item.kit_id.package_id.package_Name})</td>
															<td>${item.payment}</td>
														</tr>
														<c:set var="counter" value="${counter + 1}"/>		
													
												</c:forEach>
											</tbody>
										</table>
									</div>
									<!-- /.box-body -->
								</div>
							</div>
						</div>
					</div>
				</div>
			</section>
		</div>
	</div>

	<script>
		$.widget.bridge('uibutton', $.ui.button);
	</script>
	<script>
		$(document).ready(function() {
			$('#example1').dataTable({
				"pagingType" : "full_numbers",
			});
		});
	</script>
</body>
</html>
