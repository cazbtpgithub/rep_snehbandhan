<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="box">
	<div class="box-body">
		<table id="example1"
											class="table table-bordered table-striped">
											<thead>
												<tr>
													<th>Sr No.</th>
													<th>Package</th>
													<th>Total Amount</th>
													<th>Discount Amount</th>
													
												</tr>
											</thead>
											<tbody>
													
								
								<c:forEach items="${discountList}" var="discount" varStatus="theCount">
												
									<tr>
									<td>${theCount.count}</td>
								   <td>${discount.packageName}</td>
									<td>${discount.totalAmount}</td>
									<td>${discount.discountAmount}</td>
									</tr>
   									
   									 
   							  </c:forEach>
										
											
											
											</tbody>

										</table>
	</div>
	<!-- /.box-body -->
</div>

<script>
		$('#example1').dataTable({
			"pagingType" : "full_numbers",

		});
	</script>

