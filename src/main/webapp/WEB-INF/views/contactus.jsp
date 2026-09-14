
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
</style>
<link rel="stylesheet"
	href="resources/Template/css/perfect-scrollbar.css">
<link rel="stylesheet"
	href="resources/Template/css/font-awesome.min.css">
<link rel="stylesheet" href="resources/Template/css/main.css">
<link rel="stylesheet" href="resources/Template/css/media-queries.css">
<script src="resources/Template/js/jquery-min.js"></script>
<script src="resources/Template/js/tether.min.js"></script>
<script src="resources/Template/js/bootstrap.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?sensor=false"></script>
<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyApbdaJeou_tqF8XuC4UwdNHOT10zRM8c0&callback=myMap"></script>
<script async src="https://www.googletagmanager.com/gtag/js?id=UA-112016473-1"></script>
<script>
  window.dataLayer = window.dataLayer || [];
  function gtag(){dataLayer.push(arguments);}
  gtag('js', new Date());
  gtag('config', 'UA-112016473-1');
</script>
 <style>
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
      /* Always set the map height explicitly to define the size of the div
       * element that contains the map. */
      #map {
        height: 40%;
        width: 40%;
      }
      /* Optional: Makes the sample page fill the window. */
     
#message{

 text-align: center;
 color: #e6005c;
}

#message_text{

color:#0a0f0f;
 text-align: center;
 font-size: 12px;
 font-weight: bold;
}


/* .center-on-page {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%,-50%);
} */
h1 {
  text-align: center;
}
/* Reset Select */
select {
  -webkit-appearance: none;
  -moz-appearance: none;
  -ms-appearance: none;
  appearance: none;
  outline: 0;
  box-shadow: none;
  border: 0 !important;
  background: green;
  background-image: none;
  height: 40px;
  
}
/* Custom Select */
.select {
  
  display: block;
  width: 20em;
  height: 3em;
  line-height: 3;
  background: #99ccff;
  overflow: hidden;
  border-radius: .25em;
}
select {
  
  margin: 0;
  padding: 0 0 0 .5em;
  color: #000;
  cursor: pointer;

}
select::-ms-expand {
  display: none;
}
/* Arrow */
.select::after {
  content: '\25BC';
  position: absolute;
  top: 0;
  right: 0;
  bottom: 0;
  padding: 0 1em;
  background: #00bfff;
  pointer-events: none;
}

.select::after {
  -webkit-transition: .25s all ease;
  -o-transition: .25s all ease;
  transition: .25s all ease;
}
option {
	background-color: #e6f2ff;
	border :2px solid #00bfff;
}
    </style>

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
	</div>
	
	
	<nav class="navbar navbar-dark bg-inverse mainnav">
		<div class="collapse navbar-toggleable-sm" id="exCollapsingNavbar">
		<div class="nav navbar-nav float-xs-left">
				<a class="nav-item nav-link active"
					href="contactus">
					Contact Us
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
	
		<style>
		.div_hover:hover { 
		
    border: 3px solid #d23779;
    padding: 15px;
  
		 }
		 
		 P{
		 text-align: justify;
		 	}
		 	
		 	

		</style>
		<div class="alert alert-success" id="mapSuccess">
													<button type="button" class="close" data-dismiss="alert"
														aria-hidden="true">&times;</button>
													<strong>Success: </strong>Google Map Loaded Successfully
												</div>
		<nav class="breadcrumb custom-breadcrumb">
			<a class="breadcrumb-item" href="#"></a> <!-- <a
				class="breadcrumb-item" href="#">Search</a> <span
				class="breadcrumb-item active">Results</span> -->
		</nav>
	<div class="maincontainer clearfix">
	<div class="col-lg-2 col-md-3 col-sm-3 col-xs-12">
	</div> 
	<div >
	
	<div class="col-lg-7 col-md-9 col-sm-9 col-xs-12" >
  
  
  
<div style="padding-bottom: 30px;">
<p id="message_text">Thank You for visiting snehbandhan Matrimony. </p>
<p id="message_text">Now finding partner is the easy with <span style ="color:#e6005c;">SNEHBANDHAN Matrimony</span> </p>
<p style="text-align: center;">
<img src="${pageContext.request.contextPath}/resources/images2/facebook.png" onclick="gotToFacebook();" style="cursor:pointer" onerror="this.onerror=null; this.src='resources/UserImages/userImage.png';">  &nbsp;&nbsp;&nbsp;&nbsp;
<img src="${pageContext.request.contextPath}/resources/images2/twitter.png" onclick="goToTwitter();" style="cursor:pointer" onerror="this.onerror=null; this.src='resources/UserImages/userImage.png';"> &nbsp;&nbsp;&nbsp;&nbsp;
<img src="${pageContext.request.contextPath}/resources/images2/google.png" onclick="goToGoogle()" style="cursor:pointer" onerror="this.onerror=null; this.src='resources/UserImages/userImage.png';">&nbsp;&nbsp;&nbsp;&nbsp;
<img src="${pageContext.request.contextPath}/resources/images2/playstore.png" onclick="goToPlayStore();" style="cursor:pointer"></p>
<p id="message">______________________________________</p>
        <br><br>     
     <div class="form-group">
						<div class="row">
							<div>
								<c:forEach var="branchDetails" items="${branchList}"
									varStatus="theCount">
									<c:set var="branch" scope="session"
										value="${branchDetails.branchName}">
									</c:set>
								</c:forEach>
							</div>
							<c:forEach var="branchDetails" items="${branchList}"
								varStatus="theCount">
								<c:set var="branch" scope="session"
									value="${branchDetails.branchId}">
								</c:set>
								<div class="row">
									<c:choose>
										<c:when test="${not empty branch}">
											<br>
											<div class="" col-sm-5">
												<img src="${pageContext.request.contextPath}/resources/images2/address.png" onerror="this.onerror=null; this.src='resources/UserImages/userImage.png';">&nbsp;&nbsp;
												<span style="font-size: 18px; font-weight: bold;">Branch
													Details</span> <br> <label for="firstPortion">Branch
													Name : </label>
												<c:out value="${branchDetails.branchName}" />
												<br> <label for="firstPortion">Address : </label>
												<c:out value="${branchDetails.branchAddress}" />

												<br> <label for="firstPortion">Contact No : </label>
												<c:out value="${branchDetails.contactNo}" />
												<br> <label for="firstPortion">Email id : </label>
												<c:out value="${branchDetails.emailId}" />

											</div>
											<div class="" col-sm-5" id="${branchDetails.branchId}"
												style="height: 20%; width: 55%;"></div>
										</c:when>
									</c:choose>

								</div>
								<br>
							</c:forEach>

						</div>

						<input type="button" id="submit" style="display: none;"  onclick="loadAllBranchesDetails();">
        </div>      
   
 </div>
 
 <script type="text/javascript">
   
    $("#submit").click(); 
	$("#mapSuccess").hide(); 
	
	function loadAllBranchesDetails()
	{
		  $.ajax({

			  url : 'loadAllBranches',
			  type : "POST",
			  dataType : 'json',
			  data : {} ,
			  async : false,
			  success : function(data) 
			  {
				  
				  var jsonData = JSON.parse(JSON.stringify(data));

					var counter1 = jsonData.branchModelList;
					var cntForiterateobj1 = JSON.parse(counter1);
					
					for (var c = 0; c < cntForiterateobj1.length; c++) 
					{
						var id = cntForiterateobj1[c].branchId; 
	                    var lat = cntForiterateobj1[c].latitude; 
	                    var logitude = cntForiterateobj1[c].longitude;
	                    var address =  cntForiterateobj1[c].branchAddress;
	                    
	      
	                    myMap(id,lat,logitude,address);
					}
					
					
					
					function myMap(id,lat,logitude,address) 
					{
					      
						  var myCenter = new google.maps.LatLng(lat,logitude);   
						  var mapCanvas = document.getElementById(id);
						  var mapOptions = {center: myCenter, zoom: 5};
						  var map = new google.maps.Map(mapCanvas, mapOptions);
						  var marker = new google.maps.Marker({position:myCenter, 
							  map: map,
						      title: address
						});
						  marker.addListener('click', function() {
						      infowindow.open(map, marker);
						    });

						  marker.setMap(map);

						  google.maps.event.addListener(marker,'click',function() {
						    map.setZoom(9);
						    map.setCenter(marker.getPosition());
						  });
						  
						  
						  
						  
						  
				} 

				   
				
			  },
			  error : function(errorThrown) {
			   console.log("Error: " + errorThrown);
			  }
			 });
	}
	
 
 </script>
<script type="text/javascript">

function displayGoogleMap(branchId) 
{
	  if (branchId=="") return; // please select - possibly you want something else here
	  
	  var branch_List = $("#branch_List").val();
	  
	  if(branch_List == "defaultBranch")
		  {
		    $("#map").show();
		    $("#selectedMap").hide();
		    return false;
		  }
	  
	  $.ajax({

		  url : 'loadGoogleMap',
		  type : "POST",
		  dataType : 'json',
		  data : "branch_List=" +branch_List ,"
		  async : false,
		  success : function(data) {
			  
			  var jsonData = JSON.parse(JSON.stringify(data));

				var counter1 = jsonData.branchModelList;
				var cntForiterateobj1 = JSON.parse(counter1);
				
				for (var c = 0; c < cntForiterateobj1.length; c++) 
				{
                    var lat = cntForiterateobj1[c].latitude; 
                    var logitude = cntForiterateobj1[c].longitude;
                    var address =  cntForiterateobj1[c].branchAddress;
					loadGoogleMap(lat,logitude,address);
					
					$("#map").hide();
				}

			  
			
		  },
		  error : function(errorThrown) {
		   console.log("Error: " + errorThrown);
		  }
		 });
	} 

</script>

		<script>

        function gotToFacebook(){

            window.open('www.facebook.com/snehbandhanburo');
        }

        function goToTwitter(){

        window.open('https://twitter.com/snehbandhan');
        }
        
        function goToGoogle(){

            window.open('https://goo.gl/maps/QGa85D23gVP2');
        }   
       
         function goToPlayStore()
       {

            window.open('https://play.google.com/store/apps/details?id=com.trial.sneh&hl=en');
        } 
</script>	
</div>
	</div>
</div>

<!-- <section id="footer">
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
	</section> -->

<section id="footer">	
<div class="pagefooter">
  <div class="sociallinks">
   <a href="https://www.facebook.com/snehbandhanburo" class="social-circle footerIconPadding" target="_blank"><i class="fa fa-facebook"></i></a>
   <a href="https://twitter.com/snehbandhan" class="social-circle footerIconPadding" target="_blank"><i class="fa fa-twitter"></i></a> 
   <a href="https://goo.gl/maps/QGa85D23gVP2" class="social-circle footerIconPadding" target="_blank"><i class="fa fa-google-plus"></i></a>
  <a href="https://play.google.com/store/apps/details?id=com.trial.sneh&hl=en" target="_blank">
 <img src="${pageContext.request.contextPath}/resources/images/playstore.png" height="50px;"width="80px"/></a>
  <p style="text-align: center;">
   Copyright &copy; 2016 <a href="#">www.snehbandhan.org</a>
  </p>
  </div>
  </div>
 </section>
</body>
</html>
