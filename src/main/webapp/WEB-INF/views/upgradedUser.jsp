<div class="box">
	<div class="box-body">
		<table id="example2" class="table table-bordered table-striped">
			<thead>
				<tr>
					
					<th>Name</th>
					<th>Kit</th>
					<th>Package</th>
					<th>Date</th>
					


				</tr>
			</thead>
			<tbody>

				<tr>
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

	var data = eval('${upgradedUserList}');
	var table = $('#example2').DataTable({
		"sPaginationType" : "full_numbers",
		"sDom" : 'T<"clear">lfrtip<"clear spacer">T',
		"aaData" : data,
		"aoColumns" : [  {
			"data" : "name",
			"defaultContent" : ""
		}, {
			"data" : "kit",
			"defaultContent" : ""
		}, {
			"data" : "packageName",
			"defaultContent" : ""
		}, 
		{
			"data" : "registrationDate",
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
