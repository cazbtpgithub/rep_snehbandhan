<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<!-- Google tag (gtag.js) -->
<script async src="https://www.googletagmanager.com/gtag/js?id=G-578JZ59RJR"></script>
<script>
  window.dataLayer = window.dataLayer || [];
  function gtag(){dataLayer.push(arguments);}
  gtag('js', new Date());

  gtag('config', 'G-578JZ59RJR');
</script>
<title>SnehBandhan</title>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1," shrink-to-fit=no">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<meta name="description" content="" />
<meta name="keywords" content="" />
<link rel="stylesheet" href="resources/Template/css/bootstrap.min.css">


<link rel="stylesheet"
	href="resources/Template/css/perfect-scrollbar.css">
<link rel="stylesheet"
	href="resources/Template/css/font-awesome.min.css">
<link rel="stylesheet" href="resources/Template/css/main.css">
<link rel="stylesheet" href="resources/Template/css/media-queries.css">



<script src="resources/Template/js/jquery-min.js"></script>
<script src="resources/Template/js/tether.min.js"></script>
<script src="resources/Template/js/bootstrap.min.js"></script>


<script type="text/javascript"
	src="https://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"></script>

<script async src="https://www.googletagmanager.com/gtag/js?id=UA-112016473-1"></script>
<script>
  window.dataLayer = window.dataLayer || [];
  function gtag(){dataLayer.push(arguments);}
  gtag('js', new Date());

  gtag('config', 'UA-112016473-1');
</script>


<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width," initial-scale=1">
<link href="http://www.jqueryscript.net/css/jquerysctipttop.css"
	rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400"
	rel="stylesheet">
<link rel="stylesheet"
	href="http://netdna.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="../dist/css/wimmViewer.css" />
<link rel="stylesheet" type="text/css" href="styles.css" />


<script type="text/javascript">
	var magazineTitle = new Array();
	var count = 0;
</script>

<c:forEach var="dist" items="${magazinePdfList}">
	<script>
		magazineTitle.push('${dist.magazineTitle}');
	</script>
</c:forEach>



<style type="text/css">
/* Center JQuery Confirm (Shortlist Popup) */
.jconfirm .jconfirm-scrollpane {
    display: flex !important;
    align-items: center !important;
    justify-content: center !important;
}
.jconfirm .jconfirm-box-container, .jconfirm .jconfirm-box {
    width: 70% !important;
    max-width: none !important;
    margin: auto !important;
    border-radius: 12px !important;
    box-shadow: 0 10px 30px rgba(0,0,0,0.2) !important;
}

/* Image Slider Modal Redesign */
.custom-slider-modal {
    margin: 1.75rem auto;
    display: flex;
    align-items: center;
    min-height: calc(100% - 3.5rem);
}
.custom-slider-modal .modal-content {
    border-radius: 16px;
    border: none;
    box-shadow: 0 10px 40px rgba(0,0,0,0.2);
    overflow: hidden;
}
.custom-slider-modal .modal-body {
    padding: 20px;
    background: #fff;
}
.custom-slider-modal #customSlider img {
    max-width: 100%;
    height: auto;
    border-radius: 8px;
    display: block;
    margin: 0 auto;
}
.custom-slider-modal .close {
    position: absolute;
    top: 15px;
    right: 20px;
    z-index: 10;
    font-size: 1.5rem;
    color: #333;
    opacity: 0.7;
    background: #fff;
    border-radius: 50%;
    width: 30px;
    height: 30px;
    line-height: 30px;
    text-align: center;
    box-shadow: 0 2px 5px rgba(0,0,0,0.1);
}
.custom-slider-modal .close:hover {
    opacity: 1;
    color: #e53935;
}
.custom-slider-modal .modal-footer {
    border-top: none;
    padding: 10px 20px 20px;
}
@media (max-width: 767px) {
    .custom-slider-modal {
        margin: 0.5rem;
        min-height: calc(100% - 1rem);
    }
}

#footer {
     background: #364252;
    text-align: center;
    padding: 20px 0;
    color: #fff;
    -webkit-box-sizing: inherit;
    box-sizing: inherit;
    bottom: 0px;
    left: 0px;
    right: 0px;
}
.footerIconPadding{
padding-top: 8px;
}
.imgthumbnails img {
	height: 110px;
	border: 4px solid #555;
}

.imgthumbnails img:hover {
	border: 4px solid #00ccff;
	cursor: pointer;
}

.navigationButton {
	padding-top: 30px;
}

.navigationButton img {
	height: 30px;
	width: 30px;
	border: 0px solid #fff;
	margin: 0px;
}

.preview img {
	border: 4px solid #444;
	padding: 1px;
	width: 400px;
}

.upcomingEvents {
	background-image:
		url(${pageContext.request.contextPath}/resources/images/Events.jpg);
	font: bold 13px courier !important;
	border: 1px solid #0275d8;
	height: 50%;
	opacity: 0.7;
	filter: alpha(opacity = 50);
}

.topMatchHeader{
text-align: left; padding-top: 20px;
}

.headerSpan{
color :#0275d8; font-size: 16px;
padding-left: 25px;
}

​
</style>




<style>

#map {
	height: 40%;
	width: 40%;
}

.downloadIcon {
	color: #d23779;
	font-size: 18px;
	cursor: pointer;
}
/* Optional: Makes the sample page fill the window. */
#message {
	text-align: center;
	color: #e6005c;
}

#message_text {
	color: #0a0f0f;
	text-align: center;
	font-size: 12px;
	font-weight: bold;
}

.div_hover:hover {
	border: 3px solid #d23779;
	padding: 15px;
}

P {
	text-align: justify;
}

/* 
Generic Styling, for Desktops/Laptops 
*/
table {
	width: 60%;
	font: 12px Courier New !important;
}

th {
	background: #d23779;
	color: white;
	font-weight: bold;
	font-size: 12px;
}

.noLink tr td a {
	color: #3c8dbc;
	cursor: default;
	font-size: 12px;
}

body {
	background-color: #fff;
}

a {
	color: #d23779;
}

a:hover {
	color: #3c8dbc;
	text-decoration: underline;
}

.thumbnails img {
	height: 100px;
	border: 4px solid #d23779;
	padding: 1px;
	margin: 0 10px 10px 0;
}

.thumbnails img:hover {
	border: 4px solid red;
	cursor: pointer;
}

.preview img {
	border: 4px solid #444;
	padding: 1px;
	width: 800px;
}

</style>

</head>


<body class="hold-transition skin-blue sidebar-mini">

	<div class="">
		
		<nav class="navbar navbar-full" style="height: 80px;">
			
			<div class="fullpageslider">
				<div>
					<div class="col-sm-5 nopad h-100">
						<div id="carousel-sucess" class="carousel slide h-100"
							data-ride="carousel">
							<div class="carousel-inner h-100" role="listbox">
								
								<div class="carousel-item active h-100"
									style="height: 100px; width: 320px;">
									<img src="resources/RameshImages/@1.png" height="75px;" width="280px" alt="First slide" onerror="this.onerror=null; this.src='resources/UserImages/userImage.png';">
								</div>
								<div class="carousel-item h-100"
									style="height: 100px; width: 320px;">
									<img src="resources/RameshImages/@2.png" height="75px;" width="280px" alt="Second slide" onerror="this.onerror=null; this.src='resources/UserImages/userImage.png';">
								</div>

								<div class="carousel-item h-100"
									style="height: 100px; width: 320px;">
									<img src="resources/RameshImages/@3.png" height="75px;" width="280px" alt="third slide" onerror="this.onerror=null; this.src='resources/UserImages/userImage.png';">
								</div>
								<div class="carousel-item h-100"
									style="height: 100px; width: 320px;">
									<img src="resources/RameshImages/@4.png" height="75px;" width="280px" alt="fourth slide" onerror="this.onerror=null; this.src='resources/UserImages/userImage.png';">
								</div>

								<div class="carousel-item h-100"
									style="height: 100px; width: 320px;">
									<img src="resources/RameshImages/@5.png" height="75px;" width="280px" alt="fifth slide" onerror="this.onerror=null; this.src='resources/UserImages/userImage.png';">
								</div> 
								
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="float-xs-right">
				
				<img src="resources/RameshImages/@offer.png" height="70px;" width="280px" alt="First slide" onerror="this.onerror=null; this.src='resources/UserImages/userImage.png';">
			</div>
		</nav>
	</div>
	
	
	
	
	<nav class="navbar navbar-dark bg-inverse mainnav">
		<div class="collapse navbar-toggleable-sm" id="exCollapsingNavbar">
		<div class="nav navbar-nav float-xs-left">
				<a class="nav-item nav-link active"
					href="magazine">
					Magazine Details
				</a>

			</div>
			<div class="nav navbar-nav float-xs-right">
				<a class="nav-item nav-link active"
					href="https://www.snehbandhan.org/login"> <i class="fa fa-home"></i>
					Home
				</a>

			</div>
		</div>
		<button class="navbar-toggler float-xs-right hidden-md-up"
			type="button" data-toggle="collapse"
			data-target="#exCollapsingNavbar" aria-controls="exCollapsingNavbar"
			aria-expanded="false" aria-label="Toggle" navigation"></button>
	</nav>


	<nav class="breadcrumb custom-breadcrumb">
		<a class="breadcrumb-item" href="#"></a>

	</nav>

	<div class="maincontainer clearfix">
		<div class="col-lg-2 col-md-3 col-sm-3 col-xs-12"></div>


		<div class="wrapper">


			<!-- Content Wrapper. Contains page content -->
			<div class="content-wrapper">
				<!-- Content Header (Page header) -->


				<!-- Main content -->
				<section class="content">
					<img src="${pageContext.request.contextPath}/resources/images/sneh23.png" height="20%" width="100%" onerror="this.onerror=null; this.src='resources/UserImages/userImage.png';">
					<div class="row"
						style="background-image: url(${pageContext.request.contextPath}/resources/images/bg.png);">


						<div class="col-md-12">
							<div class="nav-tabs-custom">
							
								<div style="padding: 30px;">
									<br> <br>
									<div class="row">
										<div class="col-md-8">
											<h3>Latest Magazine</h3>
											<c:set var="count1" value="0" scope="page" />
											<c:forEach items="${imgMap}" var="map" varStatus="theCount">
												<c:set var="counter" scope="session"
													value="${theCount.count}">
												</c:set>
												<c:choose>
													<c:when test="${counter eq '1' or num  eq 1  }">
														<c:forEach var="img" items="${map.value}"
															varStatus="theCount1" begin="0" end="0">
															<img src="${pageContext.request.contextPath}/resources/MagazineImages/${map.key}/${img}" onclick="displayPreview('${map.key}') "/ onerror="this.onerror=null; this.src='resources/UserImages/userImage.png';">
														</c:forEach>
													</c:when>
													<c:otherwise>

													</c:otherwise>
												</c:choose>
											</c:forEach>
										</div>

										<div class="col-md-4">
											<h3>Upcoming Events</h3>
											<!-- Profile Image -->
											<div class="box upcomingEvents">

												<div class="box-body box-profile"></div>
												<!-- /.box-body -->
											</div>
											<!-- /.box -->


										</div>
									</div>
									<!-- /.col -->



									<h4 class="topMatchHeader">Top Profile of This Month<span class="headerSpan"><i class="fa fa-arrow-down" aria-hidden="true"></i>
									(Scroll down to load more)</span></h4>
									<br>

									<div class="imgthumbnails row" " style="overflow-y: scroll; scrollbar-face-color: #b46868; border:1px solid #d23779; padding-top:10px; height: 150px; width: 100%;">

										<c:set var="count1" value="0" scope="page" />

										<c:forEach items="${imgMap}" var="map" varStatus="theCount">

											<c:set var="counter" scope="session"
												value="${theCount.count}">
											</c:set>


											<c:forEach var="img" items="${map.value}"
												varStatus="theCount1" begin="0" end="0">
												<div class="col-md-2" align="center">

													<img src="${pageContext.request.contextPath}/resources/MagazineImages/${map.key}/${img}" onclick="displayPreview('${map.key}') " / onerror="this.onerror=null; this.src='resources/UserImages/userImage.png';">
													<br>
													<div class="row" align="center">
														<span id="pdfName${count1}" style="text-align: center;">
															PDF Name</span>
													</div>
													<br>
													<hr>
												</div>
												<script>
													$("#pdfName"+ count).text(magazineTitle[count]);
													count++;
																
															</script>
											</c:forEach>
											<c:set var="count1" value="${count1 + 1}" scope="page" />
										</c:forEach>
									</div>
									<br>
									<br>


									  <div class="imgthumbnails row"  style="overflow-y: scroll; height: 150px; width: 103%;">
													<c:forEach var="magazineObject" items="${magazinePdfList}"
														varStatus="theCount">
                                             <c:set var="msg" value="${magazineObject.magazinPdf_fileName}" />
														<c:if test="${magazineObject.magazinPdf_fileName ne '-'}">
															<div class="col-md-2" align="center">
																 <a
																	href="<c:url value="magazineFileDownload"> 
	                                                                 <c:param name="magazinFileName" value="${magazineObject.magazinPdf_fileName}"/> 
	                                                                  </c:url>>
	                                                                  
	                                                                  		<img src="${pageContext.request.contextPath}/resources/images/PDF.png" style="max-width: 160px; max-height: 120px; border: 2px; border-style: solid" onerror="this.onerror=null; this.src='resources/UserImages/userImage.png';">                                                        
	                                                                  </a>

																<div class="row" align="center">
																	
																		
																		${fn:substring(msg, 1,fn:length(msg)-1)}
																</div>
																
															</div>

														</c:if>

													</c:forEach>

												</div> 

									<div id="imgSlider" class="modal fade" data-backdrop="static"
										data-keyboard="false">
										<div class="modal-dialog modal-lg custom-slider-modal">

											<div class="modal-content">
												<div class="modal-body">
													<div class="container">
														<div class="tab-content">
																
															<button type="button" class="close" data-dismiss="modal"
																aria-hidden="true">&times;</button>
															<div id="customSlider">
														
															</div>
												</div>
												</div>
												</div>
											</div>
										</div>
									</div>
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

	<!-- /.content -->


	<div class="control-sidebar-bg"></div>

<!-- <section id="footer">
	<div class="pagefooter">

		<div class="sociallinks">
			<a href="https://www.facebook.com/snehbandhanburo"
				class="social-circle"><i class="fa fa-facebook"></i></a> <a
				href="https://twitter.com/snehbandhan" class="social-circle"><i
				class="fa fa-twitter"></i></a> <a href="https://goo.gl/maps/QGa85D23gVP2" class="social-circle"><i
				class="fa fa-google-plus"></i></a>
		</div>
		<p style="text-align: center;">
			Copyright &copy; 2016 <a href="#">www.snehbandhan.org</a>
		</p>
	</div>
	</section> -->

<section id="footer">
	<div class="pagefooter">
		<div class="sociallinks">
			<a href="https://www.facebook.com/snehbandhanburo" 
				class="social-circle footerIconPadding" target="_blank"><i class="fa fa-facebook" ></i></a>
			<a href="https://twitter.com/snehbandhan" class="social-circle footerIconPadding"
				target="_blank"><i class="fa fa-twitter"></i></a> 
				<a 
				href="https://goo.gl/maps/QGa85D23gVP2" class="social-circle footerIconPadding"
				target="_blank"><i class="fa fa-google-plus"></i></a>
				 <a  href="https://play.google.com/store/apps/details?id=com.trial.sneh&hl=en" 
				target="_blank"><img src="${pageContext.request.contextPath}/resources/images/playstore.png" /></a>

		</div>
		
		<p style="text-align: center;">
			Copyright &copy; 2016 <a href="#">www.snehbandhan.org</a>
		</p>
	</div> 
</section>
	<script>

$("#preview").hide();


function displayPreview(abc)
{
	
	 $.ajax({
		url : "magzineSlider",
		type : "GET",
		data : "folderName=" + abc,
		processData : false,
		contentType : false,
		success : function(data) {
			$('#imgSlider').modal('toggle');
			$('#customSlider').html(data);
			
		},
		error : function(xhr, ajaxOptions, thrownError) {
			console.log("Error occured");
		}
	}); 
	
	}
	
	function loadPreviousImages()
	{
		
		alert("Load Previous Images");
	}

</script>



	<script type="text/javascript">
	
	 $('#magazineDetailsTable').DataTable();
	
	</script>
</body>
</html>









