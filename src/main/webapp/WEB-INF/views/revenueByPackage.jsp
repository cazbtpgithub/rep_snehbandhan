<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="box">
	<div class="box-body">
		<table id="example1"
											class="table table-bordered table-striped">
											<thead>
												<tr>
													<th>Sr No.</th>
													<th>Kit Id</th>
													<th>Date</th>
													<th>Price</th>
													
												</tr>
											</thead>
											<tbody>
													
												
								<c:set var="counter" value="${1}"/>	
								  <c:set var="grandTotal" value="${0}"/>
								<c:forEach items="${revMap}" var="events" varStatus="theCount">
										 <c:set var="subTotal" value="${0}"/>
									   <c:forEach items="${events.value}" var="item">
		  											<tr>
																<td>${counter}</td>
																
																<td>${item.userModel.kit.kit_username}</td>
																<td>
																 <fmt:formatDate pattern="dd-MM-yyyy" value="${item.date}"/>
																</td>
																<td>${item.payment}</td>
															</tr>
														
													<c:set var="counter" value="${counter + 1}"/>		
		       										<c:set var="subTotal" value="${subTotal + item.payment}"/>
		       						     </c:forEach>
   									 
   									 
   									 <c:set var="grandTotal" value="${grandTotal + subTotal}"/>
   									 
   									 <c:if test="${subTotal gt 0}">
   									  <td>${counter}</td>
   									 <td></td>
   									 <td><font color="red"><b>Sub Total</b></font></td>
   									 <td><font color="red"><b>${subTotal}</b></font></td>
  										
										</c:if>
   									 
   									
   									 
   						</c:forEach>
									<tr>
									
									<td>${counter+1}</td>
   									 <td></td>
   									 <td><font color="Green"><b>Grand Total</b></font></td>
   									 <td><font color="Green"><b>${grandTotal}</b></font></td>		
									
									
									</tr>
												
											
											
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

