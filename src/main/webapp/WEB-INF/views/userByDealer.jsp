<div class="box">
	<div class="box-body">
		<table id="example1"
											class="table table-bordered table-striped">
											<thead>
												<tr>
													<th>Id</th>
													<th>Name</th>
													<th>Mobile</th>
													<th>Registration Date</th>
													<th>DOB</th>
													<!-- <th>Dealer Name</th> -->
													

												</tr>
											</thead>
											<tbody>

											</tbody>

										</table>
	</div>
	<!-- /.box-body -->
</div>
<script type="text/javascript">
var data = eval('${userModelDetailList}');

var table = $('#example1')
		.DataTable(
				{
					"aaData" : data,
					"pagingType" : "full_numbers",
					"aoColumns" : [
							{
								"data" : "userId"
							},
							{
								"data" : "firstName"    
							},
							{
								"data" : "mobileNum"    
							},
							{
								"data" : "registrationDate",
								"render" : function(data) {
									var date = new Date(data);
									var month = date.getMonth() + 1;
									return (month.length > 1 ? month
											: month)
											+ "-"
											+ date.getDate()
											+ "-" + date.getFullYear();
							}

							},
							{
								"data" : "dateOfBirth",
								"render" : function(data) {
									var date = new Date(data);
									var month = date.getMonth() + 1;
									return (month.length > 1 ? month
											: month)
											+ "-"
											+ date.getDate()
											+ "-" + date.getFullYear();
								}
							}
							
							
							]
				});
	
</script>