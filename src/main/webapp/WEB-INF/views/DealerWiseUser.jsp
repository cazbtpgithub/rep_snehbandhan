<!DOCTYPE html>
<html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Dealer Wise User</title>
<!-- Tell the browser to be responsive to screen width -->
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
<jsp:include page="body.jsp"></jsp:include>


<style>

/* .box-custom{
	background: #dddddd;
}
 */
label {
	/*  margin-left: 20px; */
	font: normal 12px cambria !important;
	text-align: left !important;
	padding-left: 70px;
}

/*  table {
   
       font: normal 12px cambria !important;
}  */
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
								<font color="White">Dealer wise Member</font>
							</div>
							<div class="panel-body">

								<div class="alert alert-danger alert-dismissable"
									id="nameSelect">
									<button type="button" class="close" data-dismiss="alert"
										aria-hidden="true">&times;</button>
									<strong>Error: </strong>Please Select Dealer Name.

								</div>


								<div class="form-group row">



									<div class="col-sm-3" ">
										<h3 for="packageName" style="color:#367fa9;" ">Select Dealer</h3> <select
											id="dealerName" name="dealerName"
											class="form-control input-sm">
											<option value="0">Select Dealer</option>
											<c:forEach var="pack" items="${DealerList}"
												varStatus="theCount">
												 <c:if test="${pack.status.status_id == 1 || pack.status.status_id == null}">
                                                                      
												<option value="${pack.id}">${pack.fullName}   &nbsp;&nbsp;( ${pack.dealecode} )</option>
				                                 </c:if>
												
											</c:forEach>

										</select>
									</div>
			
									
								</div>
								
					<div class="col-sm-3 row">
										<label for="packageName2">&nbsp;&nbsp;</label>
										<button class="btn btn-primary" id="getResultButton"
											onclick="return getResult();">View Details</button>
									</div>
								
									<div id="light" class=""
										style="position: fixed; float: left; width: 40%; padding: 0px; margin: 0px; top: 30%; left: 50%; overflow: hidden; display: none;">
										<input type="hidden" id="yearValue">
										<div class="meetingContent">
											<img src="${pageContext.request.contextPath}/resources/images/spinner.gif" class="logo" style="width: 150px">


										</div>
										Loading
									</div>
									<div id="fade" class="black_overlay"></div>


								</div>

								<script type="text/javascript">
									$("#nameSelect").hide();
								</script>
								
								<div id="userResult">


							</div>


						</div>





						<script>
							function getResult() {

								var dealerId = $("#dealerName").val()
								if (dealerId == '0' || dealerId == "") {

									$("#nameSelect").slideDown();
									$("#nameSelect").fadeOut(15000);
									$("#dealerName").focus();
									return false;
								}
                                $("#getResultButton").prop('disabled',true);
								document.getElementById('light').style.display = 'block';
								document.getElementById('fade').style.display = 'block';

								$.ajax({
											url : "getMemberByDealer",
											type : "GET",
											data : {
												dealerId : "" + dealerId
											},
											success : function(data) {

												$("#userResult").html(data);
												$("#dealerName").val("0")
												 $("#getResultButton").prop('disabled',false);
												document.getElementById('light').style.display = 'none';
												document.getElementById('fade').style.display = 'none';

											},
											error : function(xhr, ajaxOptions,
													thrownError) {
												console.log("err" + thrownError
														+ xhr.responseText);

												return false;
											}
										});

							}
						</script>

					</div>
				</div>

			</section>

		</div>

		<!-- /.tab-pane -->
		<script>
			$.widget.bridge('uibutton', $.ui.button);
		</script>
		<script>
			$('#example1').dataTable({
				"pagingType" : "full_numbers",

			});
		</script>
</body>
</html>







