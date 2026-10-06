<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <meta name="description" content="" />
    <meta name="keywords" content="" />
    <link rel="stylesheet" href="resources/Template/css/bootstrap.min.css">
    <link rel="stylesheet" href="resources/Template/css/perfect-scrollbar.css">
    <link rel="stylesheet" href="resources/Template/css/font-awesome.min.css">
    <link rel="stylesheet" href="resources/Template/css/main.css">
    <link rel="stylesheet" href="resources/Template/css/media-queries.css">
    <link rel="stylesheet" href="resources/upgradePackage/upgradePackage.css">

    <script src="resources/upgradePackage/upgradePackage.js"></script>	
    <script src="resources/Template/js/jquery-min.js"></script>
    <script src="resources/Template/js/tether.min.js"></script>
    <script src="resources/Template/js/bootstrap.min.js"></script>
    <script src="resources/Template/js/perfect-scrollbar.jquery.js"></script>
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
      if (!firebase.apps.length) {
          firebase.initializeApp(config);
      }
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
        </div>
        </div>
        <div class="btn-group float-xs-right nocaret">
        <button type="button" class="btn btn-secondary dropdown-toggle btn-sm btn-none" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            <i class="fa fa-bell"></i>
            <span class="notify">0</span>
        </button>
        <div class="dropdown-menu dropdown-menu-right">
            <a class="dropdown-item" href="#">No Messages</a>
        </div>
        </div>  
        <div class="btn-group float-xs-right">
        <button type="button" class="btn btn-secondary dropdown-toggle btn-sm btn-none" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            <img src="resources/UserImages/${UserProfile}" onerror="this.onerror=null; this.src='resources/UserImages/userImage.png';"> <span class="ml5">${ActUserName} </span>  
        </button>
        <div class="dropdown-menu dropdown-menu-right">
            <a class="dropdown-item" href="#" id="demo"><i class="fa fa-sign-out"></i> Logout</a>
        </div>
        </div>  
        </div>
    </nav>
    <nav class="navbar navbar-dark bg-inverse mainnav">
        <div class="collapse navbar-toggleable-sm" id="exCollapsingNavbar">
            <div class="nav navbar-nav float-xs-right">
                <a class="nav-item nav-link active" href="<c:url value='/userHome'/>"> 
                    <i class="fa fa-home"></i> Home</a>
                <a class="nav-item nav-link" href="#"><i class="fa fa-refresh"></i> Upgrade Plan</a>
            </div>
        </div>
        <button class="navbar-toggler float-xs-right hidden-md-up" type="button" data-toggle="collapse" data-target="#exCollapsingNavbar" aria-controls="exCollapsingNavbar" aria-expanded="false" aria-label="Toggle navigation"></button>
    </nav>
</div>

<div class="maincontainer clearfix">
	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
		<div class="rightpanel clearfix">
		
  <div id="pricing-table" class="clear">
        <div class="alert alert-danger alert-dismissable" id="packFail">
            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
            <font size="3px;"><strong>Error: </strong>Kit Id or Password Is Incorrect </font>
		</div>
		<div class="alert alert-danger alert-dismissable" id="packAvailable">
            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
            <font size="3px;"><strong>Error: </strong>Kit is Already in use Please contact Customer Care.</font>
		</div>
		<div class="alert alert-success alert-dismissable" id="packSuccess">
            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
            <font size="3px;"><strong>Success: </strong>Succeessfully upgraded Package.  Thank you  </font>
		</div>
		
		<div class="alert alert-danger alert-dismissable" id="transactionError">
            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
            <font size="3px;"><strong>Fail: </strong>Incorrect Email Id and Mobile Number Edit Your Profile</font>
		</div>
		
		<script>
		$("#packSuccess").hide();
		$("#packFail").hide();
		$("#packAvailable").hide();
		$("#transactionError").hide();
		</script>

		<div class="col-lg-12 text-center" style="margin-bottom: 30px;">
		    <h3 style="font-weight: 700; color: #333; margin-top: 20px;">Kit Upgrade Details</h3>
		</div>
		
        <div id="pricing-table">
            <div class="plan">
                <h3>Kit <span>Upgrade</span></h3>
                <ul style="margin-bottom: 20px;">
                    <li><input type="text" id="kitId" name="kitId" placeholder="Kit Id" class="form-control" style="border-radius: 8px;"></li>
                    <li><input type="password" name="kitPassword" id="kitPassword" placeholder="Kit Password" class="form-control" style="border-radius: 8px;"></li>
                </ul>  
                <button class="signup" onclick="uploadKitIdAndPassword();">Upgrade</button>  
            </div>
            
            <form action="PaymentGatewayController" method="post">
                <input type="text" name="pack_id" id="pack_id" style="display: none;">
                <input type="submit" id="submitPayment" style="display: none;">
            </form>
            
            <c:forEach var="pack" items="${packages}"  varStatus="theCount">
                <div class="plan" <c:if test="${(theCount.count % 2)==0}">id="most-popular"</c:if>>
                    <h3>${pack.package_Name}<span>&#8377; ${pack.amount}</span></h3>
                    <ul style="margin-bottom: 20px;">
                        <li><b>${pack.no_of_profiles}</b> Profile Views</li>
                        <li><b>${pack.no_of_msg}</b> Mobile Number Views</li>
                        <li><b>${pack.no_of_email}</b> Email-Id Views</li>
                        <li><b>${pack.no_of_days}</b> Days Validity</li>
                    </ul> 
                    <button type="button" onclick="PaymentGatewayCall('${pack.package_id}');" class="signup">Upgrade Now</button>         
                </div>
            </c:forEach> 
        </div>
      
</div>
 <img src="resources/images/kit.png" style="width: 100%; height:400px" onerror="this.onerror=null; this.src='resources/UserImages/userImage.png';"> 

<div id="TransactionStatus" class="modal fade" role="dialog">
  <div class="modal-dialog">
    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title" ><font color="">
	Transactional Summary
</font></h4>
      </div>
      <div class="modal-body">
        	<nav class="breadcrumb custom-breadcrumb"  style='background-color:#373a3c;'>
					<font color="white">
					Amount : ${Amount} <br>
					Package: ${productinfo}<br>
					Transaction Id:  ${txnid} <br>
					Status:  ${status} <br> 
					Additional Charges: ${additionalCharges} 
					</font>
		</nav>
      </div>
    </div>
  </div>
</div>

<c:if test="${not empty status}">
    <script>
		$("#TransactionStatus").modal();	
		$.ajax({
			url : "removeTransactionSummary",
			type : "POST",
			processData : false,
			contentType : false,
			success : function(data) {
			},
			error : function(xhr, ajaxOptions, thrownError) {
				console.log("Error occured");
			}
		});
</script>
</c:if>

		</div>
	</div>
</div>

	<div class="pagefooter">
		<div class="sociallinks">
			<a href="https://www.facebook.com/snehbandhanburo"
				class="social-circle" target="_blank"><i class="fa fa-facebook"></i></a>
			<a href="https://twitter.com/snehbandhan" class="social-circle"
				target="_blank"><i class="fa fa-twitter"></i></a> <a
				href="https://goo.gl/maps/QGa85D23gVP2" class="social-circle"
				target="_blank"><i class="fa fa-google-plus"></i></a> <a
				href="https://play.google.com/store/apps/details?id=com.trial.sneh&hl=en"
				target="_blank"><img src="${pageContext.request.contextPath}/resources/images/playstore.png" /></a>

		</div>
		<div class="footerlinks mt20">
			<nav class="nav nav-inline"></nav>
		</div>
		<p style="text-align: center;">
			Copyright &copy; 2016 <a href="#">www.snehbandhan.org</a>
		</p>
	</div>

<script>
	$(window).resize(function() {
		var rpheight = $('.rightpanel').height();
		$('.chatbox').height(rpheight - 70);
	});
	$(window).resize();

	$('#onlinechatbox').on('shown.bs.collapse', function() {
		$(window).resize();
	});
	$('.openchatbox').click(function() {
		$('#userChat').slideToggle();
	});
	$('.closechat').click(function() {
		$('#userChat').slideToggle();
	});
	
	$("#demo").on('click', function(e) {
		if (e && e.preventDefault) e.preventDefault();
		try {
			window.friendlyChat = new Demo('${userEmail}','${userPass}');
		} catch(err) {
			console.log(err);
		}
		
		var email="${userEmail}";
		if (email && email.trim() !== '') {
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
				complete : function() {
					window.location = "j_spring_security_logout";
				}
			});
		} else {
			window.location = "j_spring_security_logout";
		}
	});
</script>

<script type="text/javascript">
function Demo(email,password)
{
	try {
		firebase.auth().currentUser.delete(); 
	} catch ( e ) {
		return false;
	}
}

function PaymentGatewayCall(package_id){
	var oMyForm = new FormData();
	var arr = {
			package_id :package_id
	};
	oMyForm.append("jsonObjectData", JSON.stringify(arr));

	$.ajax({
		dataType : 'json',
		url : "paymentGatewaySession",
		type : "POST",
		data :oMyForm,
		processData : false,
		contentType : false,
		success : function(data) {
			if(data==false){
				$("#transactionError").hide();
				$("#transactionError").slideDown();
				$("#transactionError").fadeOut(5000);
				return false;
			}else{
				$('#pack_id').val(package_id);
				$('#submitPayment').click();
			}
		},
		error : function(xhr, ajaxOptions, thrownError) {
			console.log("Error occured");
		}
	});
}
</script>

</body>
</html>