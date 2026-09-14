<!DOCTYPE html>
<html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Magazine Home</title>
<!-- Tell the browser to be responsive to screen width -->

<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
	
<%-- <jsp:include page="body.jsp"></jsp:include>

<link rel="stylesheet" href="resources/datepicker/css/datepicker.css">
<script 
	src="resources/datepicker/js/datepicker.js"></script> --%>
	
	<jsp:include page="body.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="resources/confirmBox/confirm.css" media="screen">
<script src="resources/confirmBox/confirm.js" type="text/javascript"></script>
<link rel="stylesheet" href="resources/datepicker/css/datepicker.css">
<script src="resources/datepicker/js/datepicker.js"></script>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script
	src="${pageContext.request.contextPath}/resources/Magazine/magazineUpload.js"
	charset='utf-8'></script>

<style>
.box-custom {
	background: #dddddd;
}

label {
	margin-right: -20px;
	font: normal 14px cambria !important;
}

table {
	font: 12px Courier New !important;
}

.downloadIcon{

	color: #d23779; 
	font-size: 18px;
	cursor: pointer;
}
th {
	background: #d23779;
	color: white;
	font-weight: bold;
	font-size: 12px;
}
.noLink tr td a{

cursor:default;
}
</style>
<script>
	var allCountryName = new Array();
	var files = [];
</script>



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
						<div class="nav-tabs-custom">



							<div class="tab-content">
								<div class="active tab-pane" id="country_toggle">

									<div class="panel panel-danger">
										<div class="panel-heading" style="background-color: #d23779;">
											<font color="white">Magazine Pdf File And Image Upload</font>
										</div>

										<div class="panel-body">

											<div class="alert alert-danger alert-dismissable"
												id="magazineSelect">
												<button type="button" class="close" data-dismiss="alert"
													aria-hidden="true">&times;</button>
												<strong>Error: </strong>Please Select at least One File to upload Magazine
											</div>

											<div class="alert alert-danger alert-dismissable"
												id="pdfFileSelect">
												<button type="button" class="close" data-dismiss="alert"
													aria-hidden="true">&times;</button>
												<strong>Error: </strong>Please Select PDF File
											</div>
											<div class="alert alert-danger alert-dismissable"
												id="invalidImageFile">
												<button type="button" class="close" data-dismiss="alert"
													aria-hidden="true">&times;</button>
												<strong>Error: </strong>Image type is not supported , please select valid Image
											</div>
											
											<div class="alert alert-danger alert-dismissable"
												id="magazineTitleAlert">
												<button type="button" class="close" data-dismiss="alert"
													aria-hidden="true">&times;</button>
												<strong>Error: </strong>Please Enter Magazine Title.
											</div>

											<div class="alert alert-success" id="submitAlert">
												<button type="button" class="close" data-dismiss="alert"
													aria-hidden="true">&times;</button>
												<strong>Message: </strong>Magazine Uploaded Successfully.
											</div>

											<div class="alert alert-success" id="magazineDeleteAlert">
												<button type="button" class="close" data-dismiss="alert"
													aria-hidden="true">&times;</button>
												<strong>Message: </strong>Magazine Deleted Successfully.
											</div>

											<div class="form-group">


												<div class="col-sm-2">
													<label
														style="color: gray; width: auto; height: 20px; font-size: 15px; display: block;">
														Magazine Title <sup><font color="red"
															size="2px">*</font></sup>
													</label>
												</div>

												<div class="col-sm-4">
													<input type="text" style="width: 250px; height: 40px;"
														id="magazine_Title" name="magazine_Title">
												</div>


												

												<div class="col-sm-4"></div>
											</div>
											<br> <br>
											<div class="panel-body">
												<div class="form-group">


													<div class="col-sm-2">
														<label
															style="color: gray; width: 250px; height: 20px; font-size: 15px; display: block;">
															Add PDF File 
														</label>
													</div>

												
													<div class="col-sm-4">

														<input type="file" class="form-control"
															style="width: auto; height: auto;"
															 accept="application/pdf"
															id="magazinePdfFile">

													</div>

<div>
													
													<label style="color: red; padding-top: 5px;">(Note : - Select Only One PDF file at a time )</label>
													
													</div>
												

												</div>

												<div id="fade" class="black_overlay"></div>


											</div>




											<div class="panel-body">


												<div class="form-group">


													<div class="col-sm-2">
														<label
															style="color: gray; width: 250px; height: 20px;"
																	font-size: 15px; display: block;">
															Add Images 
														</label>
													</div>

													<div class="col-sm-4">

														<input type="file" class="form-control"
															style="width: auto; height: auto;"
															accept="image/*" data-type='image' name="magazineImage"
															id="magazineImage" multiple="multiple">

													</div>
													

												</div>
													
													<div>
													
													<label style="color: green;">(Note : - Press ctrl & Select Multiple Images )</label>
													
													</div>

			


												<div id="fade" class="black_overlay"></div>

												<div align="center" style="padding-top: 80px;">
													<button class="btn btn-primary" " id="uploadMagazinePdfFileButton"
														onclick="return uploadMagazinePdfFile();">Upload Magazine</button>

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

											<hr style="border-top: 1px solid" #d23779">
											<div class="panel-body">


												<table id="magazineTable"
													class="table table-bordered table-striped">
													<thead>
														<tr>
															<th>Sr No<i class="fa fa-sort-amount-asc"></i></th>
															<th>Magazine Title<i class="fa fa-sort-amount-asc"></i></th>
															<th>Upload Date<i class="fa fa-sort-amount-asc"></i></th>
															<th>PDF Details<i class="fa fa-sort-amount-asc"></i></th>
															<th>Download PDF<i class="fa fa-sort-amount-asc"></i></th>
															<th>Images Details<i class="fa fa-sort-amount-asc"></i></th>
															<th>Download Images<i class="fa fa-sort-amount-asc"></i></th>
															 <th>Delete</th> 
														</tr>
													</thead>
													<tbody class="noLink">
														<c:forEach var="magazineObject" items="${magazinePdfList}"
															varStatus="theCount">
															<tr class="decorationNone">
																<td><a href="#">${theCount.count }</a></td>
																<td><a href="#">${magazineObject.magazineTitle}</a></td>
																<td>
																<a>
																 <fmt:formatDate pattern="dd-MM-yyyy" value="${magazineObject.file_Uploaded_date}"/>
																</a></td>
										
																<td><a href="#">${magazineObject.magazinPdf_fileName}</a></td>
																<td>
																    <c:set var="string" scope="session"
																		value="${magazineObject.magazinPdf_fileName}">
																	</c:set> <c:choose>
																		<c:when test="${string eq '-' }">
																		</c:when>
																		<c:otherwise>
																			<a
																				href="<c:url value="magazineFileDownload"><c:param name="magazinFileName" value="${magazineObject.magazinPdf_fileName}"/> </c:url>>
																				<strong><i class="fa fa-download downloadIcon"
																					aria-hidden="true"> </i> </strong>
																			</a>
																		</c:otherwise>
																	</c:choose>
                                                                </td>
                                                                
                                                                <td><a href="#">${magazineObject.magazineImageNames}</a></td>
																<td>
																
																<c:set var="string" scope="session"
																		value="${magazineObject.magazineImageNames}">
																	</c:set> <c:choose>
																		<c:when test="${string eq '-' }">
																		</c:when>
																		<c:otherwise>
																			<a
																				href="<c:url value="magazineImageZipFileDownload"><c:param name="magazineZipFileName" value="${magazineObject.magazineImageNames}"/> </c:url>>
																				<strong><i class="fa fa-download downloadIcon"
																					aria-hidden="true"> </i> </strong>
																			</a>
																		</c:otherwise>
																	</c:choose>
																</td>	
																
																<!--  this is comemt code do not delete -->
																
																<td class="text-center"><a href="#" "
																				onclick="" deleteMagazineDetails('${theCount.count }','${magazineObject.magazinePdfId}','${magazineObject.magazinPdf_fileName}','${magazineObject.magazineImageNames}');">
																					<i class="fa fa-trash-o"></i>
															    </a></td>
														
															</tr>
														</c:forEach>
													</tbody>

												</table>
											</div>


										</div>


									</div>

								</div>
								<!-- /.tab-content -->
							</div>
							<!-- /.nav-tabs-custom -->
						</div>
						<!-- /.col -->
					</div>
					<!-- /.row -->
			</section>
			<!-- /.content -->
		</div>

		<div class="control-sidebar-bg"></div>
	</div>

	<script type="text/javascript">
		$("#magazineSelect").hide();
		$("#pdfFileNameSelect").hide();
		$("#submitAlert").hide();
		$("#magazineTitleAlert").hide();
		$("#pdfFileSelect").hide();
		$("#invalidImageFile").hide();
		$("#magazineDeleteAlert").hide();
		
		  $('#magazineTable').dataTable({
			  "pagingType": "full_numbers"
			}); 
	</script>


</body>
</html>




