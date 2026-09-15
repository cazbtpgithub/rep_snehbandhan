<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
    <meta name="viewport" content="width=device-width, initial-scale=1," shrink-to-fit=no">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <meta name="description" content="" />
    <meta name="keywords" content="" />
     <link rel="stylesheet" href="resources/Template/css/bootstrap.min.css">
<style>
</style>
     <link rel="stylesheet" href="resources/Template/css/perfect-scrollbar.css">
    <link rel="stylesheet" href="resources/Template/css/font-awesome.min.css">
    <link rel="stylesheet" href="resources/Template/css/ninja-slider.css">
    <link rel="stylesheet" href="resources/Template/css/thumbnail-slider.css">
    <link rel="stylesheet" href="resources/Template/css/main.css">
    <link rel="stylesheet" href="resources/Template/css/media-queries.css">
      <link rel="stylesheet" href="resources/Template/css/flexslider.css">

       <script src="resources/ProfileViewer/profileView.js"></script>
      <script src="resources/Template/js/jquery-min.js"></script>
		<script src="resources/Template/js/tether.min.js"></script>
		<script src="resources/Template/js/bootstrap.min.js"></script>
		<script src="resources/Template/js/perfect-scrollbar.jquery.js"></script>
		<script src="resources/Template/js/ninja-slider.js"></script>
		<script src="resources/Template/js/thumbnail-slider.js"></script>
		<script src="resources/fcm/fcmLogout.js"></script>
		
		
		<!--Firebase code started  -->
	
	<script src="https://www.gstatic.com/firebasejs/3.7.0/firebase.js"></script>
<script>
  // Initialize Firebase
  var config = {
		  apiKey: "AIzaSyATshbLVZDikxhaqstwaqTPCMHNr01An5E",
	        authDomain: "sneh-fc203.firebaseapp.com",
	        databaseURL: "https://sneh-fc203.firebaseio.com",
	        projectId: "sneh-fc203",
	        storageBucket: "sneh-fc203.appspot.com",
	        messagingSenderId: "429812797232"
  };
  firebase.initializeApp(config);
</script>
  <!-- Disable tap highlight on IE -->
  <meta name="msapplication-tap-highlight" content="no">

  <!-- Web Application Manifest -->
  <link rel="manifest" href="manifest.json">

  <!-- Add to homescreen for Chrome on Android -->
  <meta name="mobile-web-app-capable" content="yes">
  <meta name="application-name" content="Friendly Chat">
  <meta name="theme-color" content="#303F9F">
	
	<!--Firebase code Ended  -->
    
</head>
<body>
<div class="">
    <nav class="navbar navbar-full">
        <a class="navbar-brand" href="#"><img src="resources/images/logo.png" class="img-fluid" height="75" width="350px"></a>
        <div class="adminmenu clearfix">
        <div class="btn-group float-xs-right nocaret">
        <button type="button" class="btn btn-secondary dropdown-toggle btn-sm btn-none" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            <i class="fa fa-gear"></i>
        </button>
        <div class="dropdown-menu dropdown-menu-right">
      <a class="dropdown-item" href="#">No Updates</a>
      <!--     <a class="dropdown-item" href="#">Another action</a>
        <a class="dropdown-item" href="#">Something else here</a>
        <div class="dropdown-divider"></div>
        <a class="dropdown-item" href="#">Separated link</a> -->
        </div>
        </div>
        <div class="btn-group float-xs-right nocaret">
        <button type="button" class="btn btn-secondary dropdown-toggle btn-sm btn-none" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            <i class="fa fa-bell"></i>
            <span class="notify">0</span>
        </button>
        <div class="dropdown-menu dropdown-menu-right">
       <a class="dropdown-item" href="#">No Messages</a>
       <!--   <a class="dropdown-item" href="#">Another action</a>
        <a class="dropdown-item" href="#">Something else here</a>
        <div class="dropdown-divider"></div>
        <a class="dropdown-item" href="#">Separated link</a> -->
        </div>
        </div>  
        <div class="btn-group float-xs-right">
        <button type="button" class="btn btn-secondary dropdown-toggle btn-sm btn-none" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
        <img src="resources/UserImages/${UserProfile}" onerror="this.onerror=null; this.src='resources/UserImages/userImage.png';"> <span class="ml5">${ActUserName} </span>  
        </button>
        <div class="dropdown-menu dropdown-menu-right">
            <a class="dropdown-item" href="<c:url value='/editProfile'/>"><i class="fa fa-pencil"></i> Edit Profile</a>
            <div class="dropdown-divider"></div>
            <a class="dropdown-item" href="#" id="demo"><i class="fa fa-sign-out"></i> Logout</a>
        </div>
        </div>  
        </div>
    </nav>
    <nav class="navbar navbar-dark bg-inverse mainnav">
        <div class="collapse navbar-toggleable-sm" id="exCollapsingNavbar">
         <div class="nav navbar-nav float-xs-right">
            <a class="nav-item nav-link active" href="<c:url value='/userHome'/>"><i class="fa fa-home"></i> Home </a>
            <a class="nav-item nav-link" href="<c:url value='/userHome'/>"><i class="fa fa-envelope"></i> Inbox</a>
            <a class="nav-item nav-link" href="<c:url value='/userHome'/>"><i class="fa fa-heart"></i> Matches</a>
            <a class="nav-item nav-link" href="<c:url value='/editProfile'/>"><i class="fa fa-user"></i> My Profile</a>
            <a class="nav-item nav-link" href="<c:url value='/userHome'/>"><i class="fa fa-search"></i> Search</a>
        </div>
        </div>
        <button class="navbar-toggler float-xs-right hidden-md-up" type="button" data-toggle="collapse" data-target="#exCollapsingNavbar" aria-controls="exCollapsingNavbar" aria-expanded="false" aria-label="Toggle" navigation"></button>
    </nav>
    </div>
    </body>
    
    <script type="text/javascript">
    
	$("#demo").on('click', function() {
		  
		
		window.friendlyChat = new Demo('${userEmail}','${userPass}');
		
		//
			var email="${userEmail}";
	var oMyForm1 = new FormData();
		var arr1 = {
			email : email
		};

		oMyForm1.append("jsonObjectData1", JSON.stringify(arr1));
		$.ajax({
			url : "deleteOnlineuserInfo",
			type : "POST",
			data : oMyForm1,
			enctype : 'multipart/form-data',
			processData : false,
			contentType : false,
			success : function(data) {
				
				window.location = "j_spring_security_logout";  // Logout after online info delete
			},
			error : function(xhr, ajaxOptions, thrownError) {
				//alert("err" + thrownError + xhr.responseText);
			}
		});
		
		//
		
		});
</script>


<script type="text/javascript">

function Demo(email,password)
{
	try {
		firebase.auth().currentUser.delete(); 
		}catch ( e ) {
			return false;
		}
	
}


</script>
    
    
    </html>