<!DOCTYPE html>
<html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>

<title>Online Upgraded</title>
<!-- Tell the browser to be responsive to screen width -->
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
<jsp:include page="body.jsp"></jsp:include>


<script>
	function showOccupation() {
		$(".panel1").slideToggle("slow");
	}

	function showEducation() {
		$(".panel123").slideToggle("slow");
	}
</script>

<style>
div.panel123, p.flip {
	margin: 0px;
	background: #ddd;
	border: solid 1px #fff;
}

div.panel123 {
	background-color: #ddd;
	display: none;
}

div.panel1, p.flip1 {
	margin: 0px;
	background: #ddd;
	border: solid 1px #fff;
}

div.panel1 {
	background-color: #ddd;
	display: none;
}

.box-custom {
	background: #dddddd;
}

label {
	margin-right: -20px;
	font: normal 12px cambria !important;
}

table {
	font: 12px cambria !important;
}
</style>
</head>
<body class="hold-transition skin-blue sidebar-mini">
	<div class="wrapper">

		<jsp:include page="menu.jsp"></jsp:include>
		<div class="content-wrapper">
			<!-- Main content -->
			<section class="content">
				<div class="row">

					<div class="col-md-12">
						<!-- /.box-header -->

						<div class="panel panel-danger">
							<div class="panel-heading" style="background-color: #d23779;">
								<font color="White">Deleted User Detail</font>
							</div>
							<div class="panel-body">
								<br> <br>
								<div class="box">
									<div class="box-body">

										<div id="SearchResult"></div>


										<input type="hidden" name="hiddenUsermodelId"
											id="hiddenUsermodelId" /> <input type="hidden"
											name="hiddenUserpartnerId" id="hiddenUserpartnerId" /> <input
											type="hidden" name="hiddenuserId" id="hiddenuserId" />


										<table id="example2"
											class="table table-bordered table-striped">
											<thead>
												<tr>
													<th>Sr no</th>
													<th>User Name</th>
													<th>Delete By</th>
													<th>Date</th>

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
			</section>
		</div>

	</div>

	<script type="text/javascript">
	var data = eval('${deleteUserList}');
	var table = $('#example2').DataTable({
		"sPaginationType" : "full_numbers",
		"sDom" : 'T<"clear">lfrtip<"clear spacer">T',
		"aaData" : data,
		"aoColumns" : [  {
			"data" : "deleteId",
			"defaultContent" : ""
		}, {
			"data" : "firstName",  
			"defaultContent" : ""
		}, {
			"data" : "deletedBy",
			"defaultContent" : ""
		}, 
		{
			"data" : "deleteDate",
			"render" : function(data) {
				var date = new Date(data);
				var month = date.getMonth() + 1;
				return date.getDate()
						+ "-"
						+ (month.length > 1 ? month
								: month)
						+ "-" + date.getFullYear();
			}
		} ]
	});

	
</script>




</body>
</html>




