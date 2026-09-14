<div class="box">
	<div class="box-body">
		<table id="example1" class="table table-bordered table-striped">
			<thead>
				<tr>
					<th>No</th>
					<th>User Name</th>
					<th>Password</th>
					<th>Package</th>
					<th>Status</th>
					<th>Edit</th>
					


				</tr>
			</thead>
			<tbody>

			<%-- 	<c:forEach var="kit" items="${kitList}" varStatus="theCount">
					<tr>
						<td><a href="#">${theCount.count }</a></td>
						<td><a href="#">${kit.kit_username}</a></td>
						<td><a href="#">${kit.kit_password}</a></td>
						<td><a href="#">${kit.package_id.package_Name}</a></td>
					<tr>
					</tr>


				</c:forEach> --%>



				<tr>
					<th></th>
					<th></th>
					<th></th>
					<th></th>
					<th></th>
				</tr>
			</tbody>

		</table>
	</div>
	<!-- /.box-body -->
</div>
<script type="text/javascript">
	var data = eval('${employeeList}');
	var table = $('#example1').DataTable({
		"sPaginationType" : "full_numbers",
		"sDom" : 'T<"clear">lfrtip<"clear spacer">T',
		"aaData" : data,
		"aoColumns" : [ {
			"data" : "kit_id"
		}, {
			"data" : "kit_username"
		}, {
			"data" : "kit_password"
		}, {
			"data" : "package_id.package_Name"
		}, 
		{
			"data" : "status_id.status_name"
		},
		{
			"data" : null,
			"defaultContent" : "<button onclick=updateKit(this)>Edit/Update</button>"
		}
		]
	});

	
</script>
