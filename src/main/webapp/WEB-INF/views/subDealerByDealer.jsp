<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="box">
	<div class="box-body">
		<table id="subDealerTable"
											class="table table-bordered table-striped">
											<thead>
												<tr>
													<th>Id</th>
													<th>Name</th>
													<th>Mobile</th>
													<th>Registration Date</th>
													<th>Address</th>
													<th>UserName</th>
													<th>Password</th>
													<th>Email</th>
													<th>Status</th>
												
												</tr>
											</thead>
											<tbody>

											</tbody>

										</table>
	</div>
	<!-- /.box-body -->
</div>
<script type="text/javascript">
var data = eval('${dealerWiseSubDealerList}');
$("#subDealerTable").dataTable().fnDestroy();
var table = $('#subDealerTable')
		.DataTable(
				{
					"aaData" : data,
					"pagingType" : "full_numbers",
					"aoColumns" : [
							{
								"data" : "id"
							},
							{
								"data" : "fullName"    
							},
							{
								"data" : "mobileNum"    
							},
							{
								"data" : "regDate",
								"render" : function(data) {
									var date = new Date(data);
									var month = date.getMonth() + 1;
									return ( date.getDate())
											+ "-"
											+ month
											+ "-" + date.getFullYear();
							}

							},
							{
								"data" : "address"
							},
							{
								"data" : "userModel.userName"
							},
							{
								"data" : "userModel.password"
							},
							{
								"data" : "email"
							},
							{
								"data" : "status.status_id",
								
							     "render" : function(data)
								{
							    	 
							    	
									 if(data === 2 || data === 3)
										{
										   return  "<p style='color:red'>Inactive</p>";
										}
									 else
							    		 {
							    		 return  "<p style='color:green'>Active</p>";
							    		 }
									
								}   
							},
							
							
							]
				});
	
</script>