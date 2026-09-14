<!DOCTYPE html>
<html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Member Report</title>
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

<link rel="stylesheet" href="resources/datepicker/css/datepicker.css">
<script src="resources/datepicker/js/datepicker.js"></script>
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
								<font color="White">Member Report</font>
							</div>
							<div class="panel-body">

								<div class="alert alert-danger alert-dismissable"
									id="jenderSelect">
									<button type="button" class="close" data-dismiss="alert"
										aria-hidden="true">&times;</button>
									<strong>Error: </strong>Please Select Gender.

								</div>
								<div class="alert alert-danger alert-dismissable"
									id="paidStatusSelect">
									<button type="button" class="close" data-dismiss="alert"
										aria-hidden="true">&times;</button>
									<strong>Error: </strong>Please Select Paid Status.

								</div>
								
								<div class="alert alert-success alert-dismissable"
									id="memberReportSuccess">
									<button type="button" class="close" data-dismiss="alert"
										aria-hidden="true">&times;</button>
									<strong>Success: </strong>Member Report Generated Successfully

								</div>

								<div class="alert alert-danger alert-dismissable"
									id="fDateselect">
									<button type="button" class="close" data-dismiss="alert"
										aria-hidden="true">&times;</button>
									<strong>Error: </strong>Please Select From Date.
								</div>

								<div class="alert alert-danger alert-dismissable"
									id="tdateselect">
									<button type="button" class="close" data-dismiss="alert"
										aria-hidden="true">&times;</button>
									<strong>Error: </strong>Please Select To Date
								</div>

								<h5 style="color: green;">Note : You can get Date wise and
									gender wise all Member details here , please select date and
									gender to generate report</h5>
								<br>
								<div class="col-sm-3">
									<label for="packageName">Select From Date</label> <input
										type="text" id="fromDate" name="fDate"
										class="form-control form-control-sm" placeholder="From Date"
										readonly="readonly">

								</div>
								<div class="col-sm-3">
									<label for="userName">Select To Date</label> <input
										type="text" id="todate" name="tdate"
										class="form-control form-control-sm" placeholder="To Date"
										readonly="readonly">
								</div>


								<div class="col-sm-3">
									<label for="packageName">Select Criteria</label> <select
										id="gender" name="gender" class="form-control input-sm">
										<option value="0">Select Criteria</option>
										<option value="male">Male</option>
										<option value="female">Female</option>
										<option value="allUser">All</option>
									</select>
								</div>
								<div class="col-sm-3">
									<label for="packageName">Select Criteria</label> <select
										id="payStatus" name="payStatus" class="form-control input-sm">
										<option value="0">Select Criteria</option>
										<option value="paidUser">Paid</option>
										<option value="unpaidUser">Unpaid</option>

									</select>
								</div>

								<div align="center" style="padding-top: 80px;">
									<button class="btn btn-primary" id="printMemberReportDetailsButton"
										onclick="return printMemberDetails();">Print Member
										Details</button>

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
								$("#memberReportSuccess").hide();
								$("#jenderSelect").hide();
								$("#paidStatusSelect").hide();

								function printMemberDetails() {
									var gender = $("#gender").val();
									var payStatus = $("#payStatus").val();
									var fromDate = $("#fromDate").val();
									var todate = $("#todate").val();

									if (fromDate == "") {
										$("#fDateselect").slideDown();
										$("#fDateselect").fadeOut(3000);
										$("#fromDate").focus();
										return false;
									}
									if (todate == "") {

										$("#tdateselect").slideDown();
										$("#tdateselect").fadeOut(3000);
										$("#todate").focus();
										return false;
									}
									if (gender == "0") {
										$("#jenderSelect").slideDown();
										$("#jenderSelect").fadeOut(3000);
										$("#gender").focus();
										return false;

									}
									if (payStatus == "0") {

										$("#paidStatusSelect").slideDown();
										$("#paidStatusSelect").fadeOut(3000);
										$("#payStatus").focus();
										return false;
									}
									
                                    $("#printMemberReportDetailsButton").prop('disabled',true);
									document.getElementById('light').style.display = 'block';
									document.getElementById('fade').style.display = 'block';

									$.ajax({

												url : "getMemberDetailsByUserAndGender",
												type : "POST",
												data : {
													gender : "" + gender,
													payStatus : "" + payStatus,
													fromDate : "" + fromDate,
													todate : "" + todate
												},
												success : function(data) {
													window.location.href = 'Download?path="+ data;"
													
													$("#gender").val("0");
													$("#payStatus").val("0");
													$("#fromDate").val("");
													$("#todate").val("");
													$("#printMemberReportDetailsButton").prop('disabled',false);
													document.getElementById('light').style.display = 'none';
													document.getElementById('fade').style.display = 'none';
													
													//$("#memberReportSuccess").show();
													//$("#memberReportSuccess").fadeOut(3000);
												},
												error : function(xhr,
														ajaxOptions,
														thrownError) {
													console
															.log("Error occured");
												}
											});

								}
							</script>
							<script type="text/javascript">
									$("#fDateselect").hide(); 
									$("#tdateselect").hide(); 
								
							</script>

							<script type="text/javascript">
							
							
							$("#fromDate").datepicker({
								dateFormat : 'dd/mm/yy',
								changeMonth : true,
								changeYear : true,
								yearRange : '1980:2099'
							}).datepicker('setDate', new Date());
							
					
							</script>



							<script type="text/javascript">
							$("#todate").datepicker({
								dateFormat : 'dd/mm/yy',
								changeMonth : true,
								changeYear : true,
								yearRange : '1980:2099'
							}).datepicker('setDate', new Date());
							
							
							
							</script>

							<div id="userResult"></div>


						</div>


					</div>
				</div>

			</section>




		</div>






		<!-- /.tab-pane -->




		<!-- <script>
			$.widget.bridge('uibutton', $.ui.button);
		</script> -->
		<script>
			$('#example1').dataTable({
				"pagingType" : "full_numbers",

			});
		</script>
</body>
</html>







