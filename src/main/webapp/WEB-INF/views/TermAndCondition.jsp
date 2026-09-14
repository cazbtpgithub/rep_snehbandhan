<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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
<style>
.footerIconPadding{
padding-top: 8px;
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
</style>
<link rel="stylesheet"
	href="resources/Template/css/perfect-scrollbar.css">
<link rel="stylesheet"
	href="resources/Template/css/font-awesome.min.css">
<link rel="stylesheet" href="resources/Template/css/ninja-slider.css">
<link rel="stylesheet"
	href="resources/Template/css/thumbnail-slider.css">
<link rel="stylesheet" href="resources/Template/css/main.css">
<link rel="stylesheet" href="resources/Template/css/media-queries.css">
<link rel="stylesheet" href="resources/Template/css/flexslider.css">


<script src="resources/Template/js/jquery-min.js"></script>
<script src="resources/Template/js/tether.min.js"></script>
<script src="resources/Template/js/bootstrap.min.js"></script>
<script src="resources/Template/js/perfect-scrollbar.jquery.js"></script>
<script src="resources/Template/js/ninja-slider.js"></script>
<script src="resources/Template/js/thumbnail-slider.js"></script>
<script src="resources/Template/js/jquery.flexslider.js"></script>

<script async src="https://www.googletagmanager.com/gtag/js?id=UA-112016473-1"></script>
<script>
  window.dataLayer = window.dataLayer || [];
  function gtag(){dataLayer.push(arguments);}
  gtag('js', new Date());

  gtag('config', 'UA-112016473-1');
</script>

</head>





<body>


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
		
		<nav class="navbar navbar-dark bg-inverse mainnav">
			<div class="collapse navbar-toggleable-sm" id="exCollapsingNavbar">
				<div class="nav navbar-nav float-xs-left">
				<a class="nav-item nav-link active"
					href="TermAndCondition">
					Terms & Conditions
				</a>

			</div>
				<div class="nav navbar-nav float-xs-right">
					<a class="nav-item nav-link active"
						href="https://www.snehbandhan.org/login"> <i
						class="fa fa-home"></i> Home
					</a>

				</div>
			</div>
			<button class="navbar-toggler float-xs-right hidden-md-up"
				type="button" data-toggle="collapse"
				data-target="#exCollapsingNavbar" aria-controls="exCollapsingNavbar"
				aria-expanded="false" aria-label="Toggle" navigation"></button>
		</nav>
		<style>
.div_hover:hover {
	border: 3px solid #d23779;
	padding: 15px;
}

P {
	text-align: justify;
}

#testTermsCondition p{

font-family: arial;
color: #33004d;
}



#testTermsCondition span{

color: #e60073;

font-size: 18px;
}
</style>

		
	</div>
		
	
	
	<div class="maincontainer clearfix">
		<div class="col-lg-1 col-md-3 col-sm-3 col-xs-12"></div>
		<div>
			<div class="col-lg-9 col-md-9 col-sm-9 col-xs-12" id="testTermsCondition">
               	${term.text}
					<br><br>
			</div>

		</div>
	</div>
	<!-- <--//////////////////////////////////////////////////////////////////////////-->
<section id="footer">
	<div class="pagefooter">

		<div class="sociallinks">
			<a href="https://www.facebook.com/snehbandhanburo"
				class="social-circle footerIconPadding"><i class="fa fa-facebook"></i></a> <a
				href="https://twitter.com/snehbandhan" class="social-circle footerIconPadding"><i
				class="fa fa-twitter"></i></a> <a href="https://goo.gl/maps/QGa85D23gVP2" class="social-circle footerIconPadding"><i
				class="fa fa-google-plus"></i></a>
		</div>
		
		<p style="text-align: center;">
			Copyright &copy; 2016 <a href="#">www.snehbandhan.org</a>
		</p>
	</div>
	</section>
</body>
</html>


