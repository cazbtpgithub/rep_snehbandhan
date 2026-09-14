<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
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
<script>
	function blinker() {
		$('.blink_me').fadeOut(500);
		$('.blink_me').fadeIn(500);
	}

	setInterval(blinker, 1000);
</script>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/bootstrap/Bootstrap_3.3_Css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/Template/css/perfect-scrollbar.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/Template/css/font-awesome.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/Template/css/main.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/Template/css/media-queries.css">
	<link rel="stylesheet" href="resources/Home/css/bootstrap.min.css">
<link rel="stylesheet" href="resources/Home/css/font-awesome.min.css">
<link rel="stylesheet" href="resources/Home/css/ninja-sliderfull.css">
<link rel="stylesheet" href="resources/Home/css/flexslider.css">
<link rel="stylesheet" href="resources/Home/css/main.css">
<link rel="stylesheet" href="resources/Home/css/media-queries.css">
	
<link rel="stylesheet" type="text/css" href="resources/Registration/registration_v3.css">
<link rel="stylesheet" type="text/css" href="resources/Registration/registration.css">
	
	
<link rel="stylesheet" type="text/css" href="resources/customer/jquery-confirm.css" media="screen">

	
<script
	src="${pageContext.request.contextPath}/resources/EducationOccupation/EducationOccupation.js"
	charset='utf-8'></script>
	
		<script
		src="${pageContext.request.contextPath}/resources/Template/js/jquery-min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/Template/js/tether.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/Template/js/bootstrap.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/Template/js/perfect-scrollbar.jquery.js"></script>
		
		<script src="resources/customer/jquery-confirm.js" type="text/javascript"></script>
		
		
		
		<style type="text/css">
			
			.titletxt {
					
				
					border-bottom:4px solid #d23779;
					border-right:4px solid #d23779;
					border-radius: 10px;
					padding: 5px 5px;
					text-align: center;
					color: black;
					font-family:"cambria";
					font-size: 28px;
					background: #fadddf;
					border-radius:45px 15px;"
					
					
				}
		
					.titletxt-partner {
					
				
					border-bottom:4px solid #367fa9;
					border-right:4px solid #367fa9;
					border-radius: 10px;
					padding: 5px 5px;
					text-align: center;
					color: black;
					font-family:"cambria";
					font-size: 28px;
					background: #C2D8E5;
					border-radius:45px 15px;"
					
					
				}
			.header-partner{
			color :#367fa9;
				}
				
		</style>
		
		<style type="text/css">
		
		.btn-circle {
				  width: 30%;
				  height: 30px;
				  padding: 6px 0;
				  font-size: 18px;
				  line-height: 1;
				  border-radius: 10px;
				  background-color: green;
				}
		</style>
		
		
</head>

<script type="text/javascript">
	
	/* function model() {

		var x = "";
		files = [];
		var fileNames = "";
		var inp = document.getElementById('loader11');
		var flag = 0;
		for (var i = 0; i < inp.files.length; ++i) {
			var name = inp.files.item(i).name;
			files[i] = inp.files.item(i).name;
			x = x
					+ "	<img src="" " class='logo' + window.URL.createObjectURL(inp.files.item(i)) + "' style="height: 200px; width: 180px; border-bottom-width: 2px; border-bottom-color: red; border-width: medium; border-style: dotted; border-color:skyblue">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
					
			flag = 1;

		}

		x = x +"<button type=button class=btn-circle close popupClose data-dismiss=modal style='width:50px;'> Done </button>";
		$("#myModal").modal();
		

		$(".model-images").html(x);

	} */
	
	var fileReader = new FileReader();
	var filterType = /^(?:image\/bmp|image\/cis\-cod|image\/gif|image\/ief|image\/jpeg|image\/jpeg|image\/jpeg|image\/pipeg|image\/png|image\/svg\+xml|image\/tiff|image\/x\-cmu\-raster|image\/x\-cmx|image\/x\-icon|image\/x\-portable\-anymap|image\/x\-portable\-bitmap|image\/x\-portable\-graymap|image\/x\-portable\-pixmap|image\/x\-rgb|image\/x\-xbitmap|image\/x\-xpixmap|image\/x\-xwindowdump)$/i;

	fileReader.onload = function (event) {
	  var image = new Image();
	  
	  image.onload=function(){
	      var origImg = document.getElementById("original-Img");
	      if (origImg) { origImg.src = image.src; }
	      var canvas=document.createElement("canvas");
	      var context=canvas.getContext("2d");
	      canvas.width=image.width/4;
	      canvas.height=image.height/4;
	      context.drawImage(image,
	          0,
	          0,
	          image.width,
	          image.height,
	          0,
	          0,
	          canvas.width,
	          canvas.height
	      );
	      var uploadPrev = document.getElementById("upload-Preview");
	      if (uploadPrev) { uploadPrev.src = canvas.toDataURL(); }
	  }
	  image.src=event.target.result;
	};

	var loadImageFile = function () {
	  var uploadImage = document.getElementById("uploadImage");
	  
	  //check and retuns the length of uploded file.
	  if (uploadImage.files.length === 0) { 
	    return; 
	  }
	  
	  //Is Used for validate a valid file.
	  var uploadFile = document.getElementById("uploadImage").files[0];
	  if (!filterType.test(uploadFile.type)) {
	    alert("Please select a valid image."); 
	    return;
	  }
	  
	  fileReader.readAsDataURL(uploadFile);
	}
	
	function modelforkundali() {

/* 		var y = "";
		kundali_files = [];
		var kundali_fileNames = "";
		var kundali_inp = document.getElementById('loaderKundali');
		alert("Kundali Inp ==="+kundali_inp);
		var flag = 0;
		for (var i = 0; i < kundali_inp.kundali_files.length; ++i) {
			var kundali_name = kundali_inp.kundali_files.item(i).kundali_name;
			kundali_files[i] = kundali_inp.kundali_files.item(i).kundali_name;
			y = y
					+ "	<img src="" " class='logo' + window.URL.createObjectURL(kundali_inp.item(i)) + "' style="height: 200px; width: 180px; border-bottom-width: 2px; border-bottom-color: red; border-width: medium; border-style: dotted; border-color:skyblue">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
					
			flag = 1;

		}

		y = y +"<button type=button class=btn-circle close popupClose data-dismiss=modal style='width:50px;'> Done </button>";
		$("#myKundliModal").modal();
		

		$(".model-images").html(y); */

	}
	function showOccupation() {
		$(".panel1").slideToggle("slow");
	}

	function countChar(val) {
		$("#aboutMe").css({
			'background-color' : '#f4f4f4'
		});
		var len = val.value.trim().length;
		if (len >= 401) {
			$('#charNum').text(0 + " Charecter  Remaining");
		} else {
			$('#charNum').text(400 - len + " Charecter  Remaining");
		}
	};
</script>

<style type="text/css">
div.panel, p.flip {
	margin: 0px;
	text-align: center;
	background: #ddd;
	border: solid 1px #fff;
}

div.panel {
	background-color: #ddd;
	display: none;
}

div.panel1, p.flip1 {
	margin: 0px;
	text-align: center;
	background: #ddd;
	border: solid 1px #fff;
}

div.panel1 {
	background-color: #ddd;
	display: none;
}
.alertClose{
	font-size: 40px;
}
</style>

<body>
	<div class="">
		<!-- <nav class="navbar navbar-full">
			<a class="navbar-brand" " href="#"><img src="resources/images/logo.png" class="img-fluid" " height="75" width="auto" style="margin-left: 200px;!important"></a>
			<div class="adminmenu clearfix">
				<div class="btn-group float-xs-right nocaret">

					<div class="nav navbar-nav float-xs-right"></div>
					<div class="nav navbar-nav float-xs-right"></div>
					<div class="nav navbar-nav float-xs-right"></div>
					<div class="nav navbar-nav float-xs-right"></div>
					<div class="nav navbar-nav float-xs-right"></div>
					<div class="nav navbar-nav float-xs-right"></div>
					<div class="nav navbar-nav float-xs-right"></div>
				</div>
			</div>
		</nav>

		<nav class=" " " style="margin: 70px; color: white;">
			<a class="breadcrumb-item" href="#"></a> <a class="breadcrumb-item"
				href="#"></a> <span class="breadcrumb-item active"></span>
		</nav> -->
		<nav class="navbar navbar-full" style="height: 80px;">
			<!-- <a class="navbar-brand" href="#"><img src="resources/images/logo.png" class="img-fluid" height="75" width="350px;"" height="75"></a> -->

			<div class="fullpageslider">
				<div>
					<div class="col-sm-5 nopad h-100">
						<div id="carousel-sucess" class="carousel slide h-100"
							data-ride="carousel">
							<div class="carousel-inner h-100" role="listbox">
								<div class="carousel-item active h-100"
									style="height: 100px; width: 320px;">
									<img src="resources/images/logo1.png" height="75px;" width="280px" alt="First slide">
								</div>
								<div class="carousel-item h-100"
									style="height: 100px; width: 320px;">
									<img src="resources/images/logo2.png" height="75px;" width="280px" alt="Second slide">
								</div>

								<div class="carousel-item h-100"
									style="height: 100px; width: 320px;">
									<img src="resources/images/logo3.png" height="75px;" width="280px" alt="third slide">
								</div>
								<div class="carousel-item h-100"
									style="height: 100px; width: 320px;">
									<img src="resources/images/logo4.png" height="75px;" width="280px" alt="fourth slide">
								</div>

								<div class="carousel-item h-100"
									style="height: 100px; width: 320px;">
									<img src="resources/images/logo5.png" height="75px;" width="280px" alt="fifth slide">
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="float-xs-right">
				<!-- <div class="blink_me offertxt">
					<p>Get upto 30% Off on Premium Membership</p>
				</div> -->
				
				<img src="resources/RameshImages/@offer.png" height="75px;" width="280px" alt="First slide" onerror="this.onerror=null; this.src='resources/UserImages/userImage.png';">
			</div>
		</nav>
	</div>

	<div class="maincontainer clearfix">
		<nav class="navbar " "
			style="background-color: #364252;">
			<div class="collapse navbar-toggleable-sm" id="exCollapsingNavbar">
				<div class="nav navbar-nav float-xs-right">
				
				<a class="nav-item nav-link active"  style="color:white;"
					href="https://www.snehbandhan.org/login"> <i class="fa fa-home" ></i>
					Home
				</a>
				
					<!-- <a class="nav-item nav-link active" href="#"><i
						class="fa fa-home"></i> Home </a> <a class="nav-item nav-link"
						href="#"><i class="fa fa-envelope"></i> Inbox</a> <a
						class="nav-item nav-link" href="#"><i class="fa fa-heart"></i>
						Matches</a> <a class="nav-item nav-link" href="#"><i
						class="fa fa-user"></i> My Profile</a> <a class="nav-item nav-link"
						href="#"><i class="fa fa-search"></i> Search</a> -->
				</div>
			</div>
	</nav>
		<div class="col-lg-12 col-md-3 col-sm-3 col-xs-12">


			<!-- Remove this code if not needed -->
			<!-- Modal -->
			<div class="modal" id="myModal" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel" style="width: 490px;margin-left: 420px;">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="momPopupWrap">
							<div class="popupHeader">

								<button type="button" class="close popupClose"
									data-dismiss="modal" aria-label="Close">
									
									<i class="fa fa-times"></i>
								</button>
								<div class="model-images" " >
								</div>
							</div>

						</div>
					</div>
				</div>
			</div>
			
			<div class="modal" id="myKundliModal" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel" style="width: 490px;margin-left: 420px;">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="momPopupWrap">
							<div class="popupHeader">

								<button type="button" class="close popupClose"
									data-dismiss="modal" aria-label="Close">
									
									<i class="fa fa-times"></i>
								</button>
								<div class="model-images" " >
								</div>
							</div>

						</div>
					</div>
				</div>
			</div>
	
	<div id="secondModal" class="modal fade" data-backdrop="static"
				data-keyboard="false" style="margin-left: 12cm;margin-top: 8cm;">
				<div class="modal-dialog">
					<!--   <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;
                    </button> -->
					<div class="modal-body">
						<!--src="resources/images/loadinggif.gif"  -->
						<div class="col-md-6">
							<img src="resources/images/spinner.gif" class="img-fluid" height="150px" width="150px"> <br> <Font
								color="White" size="3px;">creating your profile ...<br> do not refresh page </Font>
						</div>
					</div>
				</div>
			</div>
			
			<div id="regmodel" class="modal fade" data-backdrop="static"
				data-keyboard="false">
				<div class="modal-dialog">
					<!--   <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;
                    </button> -->
					<div class="modal-body">

						<div class="col-md-6" >
					   <br> <Font
								color="#fff" size="3px;">You Are Registered Successfully</Font>
						</div>
					</div>
				</div>
			</div>
			
			
			
			<!--model end   -->


			<div id="containerBg">
				<div id="DivStep2">

					<div class="container"
						style="margin-top: 20px; background: white; border-top: solid 3px" #c82868;">
						<br> <img src="resources/images/step2_new.png" / onerror="this.onerror=null; this.src='resources/UserImages/userImage.png';">
						
						
						<div class="alert alert-danger alert-dismissable"
							id="nameSelect">
							<button type="button" class="close alertClose" data-dismiss="alert"
								aria-hidden="true" >&times;</button>
							<font size="3px;"><strong>Error: </strong>Please Enter Name. </font>
						</div>
						
						<div class="alert alert-danger alert-dismissable"
							id="emailselect">
							<button type="button" class="close alertClose" data-dismiss="alert"
								aria-hidden="true">&times;</button>
							<font size="3px;"><strong>Error: </strong>Please Enter Valid Email Id. </font>
						</div> 
						
						<div class="alert alert-danger alert-dismissable"
							id="mobilenumselect">
							<button type="button" class="close alertClose" data-dismiss="alert"
								aria-hidden="true">&times;</button>
							<font size="3px;"><strong>Error: </strong>Please Enter Valid Mobile Number. </font>
						</div>  
						
						<!--  -->
						<div class="alert alert-danger alert-dismissable"
							id="DivCountryselect">
							<button type="button" class="close alertClose" data-dismiss="alert"
								aria-hidden="true">&times;</button>
							<font size="3px;"> <strong>Error: </strong>Please select
								Country.
							</font>
						</div>
			
						



						
    <form class="form-horizontal" id="companyForm" method="post">
        
        <h2 class="v2-section-title"><i class="fa fa-user-o"></i> Personal Information</h2>
        
        <div class="v2-grid">
            <div class="v2-form-group v2-grid-full">
                <label>Profile Created For</label>
                <div class="v2-pill-group">
                    <input type="radio" id="pcf_self" name="ProfileCreatedFor" value="Self" checked>
                    <label for="pcf_self">Self</label>
                    <input type="radio" id="pcf_son" name="ProfileCreatedFor" value="Son / Daughter">
                    <label for="pcf_son">Son / Daughter</label>
                    <input type="radio" id="pcf_brother" name="ProfileCreatedFor" value="Brother / Sister">
                    <label for="pcf_brother">Brother / Sister</label>
                    <input type="radio" id="pcf_friend" name="ProfileCreatedFor" value="Relative / Friend">
                    <label for="pcf_friend">Relative / Friend</label>
                    
                    <input type="radio" id="pcf_parents" name="ProfileCreatedFor" value="Parents" style="display:none;">
                    <input type="radio" id="pcf_sibling" name="ProfileCreatedFor" value="Sibling" style="display:none;">
                    <input type="radio" id="pcf_relative" name="ProfileCreatedFor" value="Relative" style="display:none;">
                    <input type="radio" id="pcf_sister" name="ProfileCreatedFor" value="Sister" style="display:none;">
                    <input type="radio" id="pcf_friend_hidden" name="ProfileCreatedFor" value="Friend" style="display:none;">
                </div>
            </div>

            <div class="v2-form-group">
                <label>Full Name</label>
                <input type="text" id="firstName" name="firstName" class="v2-form-control" value="\${fullname}" placeholder="Enter your full name">
            </div>

            <div class="v2-form-group">
                <label>Email Address</label>
                <input type="text" id="email" class="v2-form-control" value="\${email}" placeholder="Enter your email address">
                <input type="hidden" value="\${hiddenemail}" name="hiddenemail" id="hiddenemail">
            </div>

            <div class="v2-form-group">
                <label>Mobile Number</label>
                <div class="v2-input-group">
                    <select class="v2-form-control"><option>+91</option></select>
                    <input type="text" id="mobile" name="mobile" class="v2-form-control" value="\${mobile}" placeholder="10-digit mobile number" maxlength="10">
                </div>
            </div>

            <div class="v2-form-group">
                <label>Date of Birth</label>
                <input type="text" id="dob" class="v2-form-control dob" value="\${dob}" placeholder="mm/dd/yyyy" readonly="readonly">
            </div>

            <div class="v2-form-group">
                <label>Gender</label>
                <div class="v2-radio-group">
                    <div class="v2-radio-item">
                        <input type="radio" id="gender_male" name="gender" value="Male">
                        <label for="gender_male">Male</label>
                    </div>
                    <div class="v2-radio-item">
                        <input type="radio" id="gender_female" name="gender" value="Female" checked>
                        <label for="gender_female">Female</label>
                    </div>
                </div>
            </div>

            <div class="v2-form-group">
                <label>Username</label>
                <input type="text" id="userName" name="userName" class="v2-form-control" value="\${userName}" placeholder="Choose a username">
            </div>

            <div class="v2-form-group">
                <label>Password</label>
                <div class="v2-password-wrapper">
                    <input type="password" id="password" name="password" class="v2-form-control" value="\${password}" placeholder="Create a strong password">
                    <i class="fa fa-eye"></i>
                </div>
            </div>
            
            <div class="v2-form-group v2-grid-full">
                <label>About Me</label>
                <textarea id="aboutMe" class="v2-form-control" rows="3" placeholder="Tell us about yourself">I am a good person looking for a partner.</textarea>
            </div>
        </div>

        <h2 class="v2-section-title" style="margin-top: 2rem;"><i class="fa fa-male"></i> Physical Attributes</h2>
        
        <div class="v2-grid">
            <div class="v2-form-group">
                <label>Marital Status</label>
                <select id="materialStatus" name="materialStatus" class="v2-form-control">
                    <option value="0">Select marital status</option>
                    <option value="Single">Single</option>
                    <option value="Married">Married</option>
                    <option value="Divorced">Divorced</option>
                    <option value="Widowed">Widowed</option>
                </select>
            </div>

            <div class="v2-form-group">
                <label>Physical Status</label>
                <select id="physicalStatus" name="physicalStatus" class="v2-form-control">
                    <option value="0">Select physical status</option>
                    <option value="Normal">Normal</option>
                    <option value="Physically challenged">Physically challenged</option>
                    <option value="Mentally Challenged">Mentally Challenged</option>
                    <option value="Deaf">Deaf</option>
                    <option value="Dumb">Dumb</option>
                    <option value="Other Disability">Other Disability</option>
                    <option value="Blind">Blind</option>
                </select>
            </div>

            <div class="v2-form-group v2-grid-full">
                <label>Body Type</label>
                <div class="v2-pill-group">
                    <input type="radio" id="bt_slim" name="bodyType" value="Slim" checked>
                    <label for="bt_slim">Slim</label>

                    <input type="radio" id="bt_athletic" name="bodyType" value="Athletic">
                    <label for="bt_athletic">Athletic</label>

                    <input type="radio" id="bt_average" name="bodyType" value="Average">
                    <label for="bt_average">Average</label>

                    <input type="radio" id="bt_heavy" name="bodyType" value="Heavy">
                    <label for="bt_heavy">Heavy</label>
                </div>
            </div>

            <div class="v2-form-group v2-grid-full">
                <label>Complexion</label>
                <div class="v2-pill-group">
                    <input type="radio" id="cpx_very_fair" name="complexion" value="Very Fair">
                    <label for="cpx_very_fair">Very Fair</label>

                    <input type="radio" id="cpx_fair" name="complexion" value="Fair">
                    <label for="cpx_fair">Fair</label>

                    <input type="radio" id="cpx_wheatish" name="complexion" value="Wheatish" checked>
                    <label for="cpx_wheatish">Wheatish</label>
                    
                    <input type="radio" id="cpx_wheatish_brown" name="complexion" value="Wheatish brown">
                    <label for="cpx_wheatish_brown">Wheatish brown</label>

                    <input type="radio" id="cpx_dark" name="complexion" value="Dark">
                    <label for="cpx_dark">Dark</label>
                </div>
            </div>

            <div class="v2-form-group">
                <label>Height</label>
                <select id="heightSelect" name="heightSelect" class="v2-form-control">
                    <option value="0">Select height</option>
                    <c:forEach var="category" items="\${height}">
                        <option value="\${category.getHeight_id()}">\${category.getHeight_name()}</option>
                    </c:forEach>
                </select>
            </div>

            <div class="v2-form-group">
                <label>Weight</label>
                <select id="weightSelect" name="weightSelect" class="v2-form-control">
                    <option value="0">Select weight</option>
                    <c:forEach var="category" items="\${weight}">
                        <option value="\${category.getWeight_id()}">\${category.getWeight_name()}</option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <h2 class="v2-section-title" style="margin-top: 2rem;"><i class="fa fa-map-marker"></i> Location</h2>
        <div class="v2-grid">
            <div class="v2-form-group">
                <label>Country</label>
                <div class="alert alert-danger alert-dismissable" id="DivCountryselect" style="display:none;">
                    <button type="button" class="close alertClose" data-dismiss="alert">&times;</button>
                    <strong>Error: </strong>Please select Country.
                </div>
                <select id="countrySelect" name="countrySelect" class="v2-form-control">
                    <c:forEach var="country" items="\${countries}">
                        <option value='\${country.country_id}'>\${country.country_Name}</option>
                    </c:forEach>
                </select>
            </div>
            
            <div class="v2-form-group">
                <label>State</label>
                <div class="alert alert-danger alert-dismissable" id="DivStateselect" style="display:none;">
                    <button type="button" class="close alertClose" data-dismiss="alert">&times;</button>
                    <strong>Error: </strong>Please select State.
                </div>
                <select id="stateSelect" name="stateSelect" class="v2-form-control" onchange="loadCityAccordingToState(this.value);">
                    <option value="0">Select State</option>
                    <c:forEach var="stateCountry" items="\${state_Countries}">
                        <option value='\${stateCountry.state_Country_id}'>\${stateCountry.state_Name}</option>
                    </c:forEach>
                </select>
            </div>
            
            <div class="v2-form-group">
                <label>City / District</label>
                <div class="alert alert-danger alert-dismissable" id="DivDistrictselect" style="display:none;">
                    <button type="button" class="close alertClose" data-dismiss="alert">&times;</button>
                    <strong>Error: </strong>Please select District.
                </div>
                <select id="cityStateSelect" name="cityStateSelect" class="v2-form-control" onchange="loadsubDistrictAccordingToDistrict(this.value);">
                    <option value="0">Select City/District</option>
                </select>
            </div>
            
            <div class="v2-form-group">
                <label>Sub-District</label>
                <div class="alert alert-danger alert-dismissable" id="DivSub-Districtselect" style="display:none;">
                    <button type="button" class="close alertClose" data-dismiss="alert">&times;</button>
                    <strong>Error: </strong>Please select Sub-District.
                </div>
                <select id="subDistSelect" name="subDistSelect" class="v2-form-control">
                    <option value="0">Sub-District</option>
                </select>
            </div>
            
            <div class="v2-form-group v2-grid-full">
                <label>Landmark</label>
                <input type="text" id="ladmark" name="ladmark" class="v2-form-control" placeholder="Enter landmark">
            </div>
        </div>

        <h2 class="v2-section-title" style="margin-top: 2rem;"><i class="fa fa-book"></i> Religion Information</h2>
        <div class="v2-grid">
            <div class="v2-form-group">
                <label>Religion</label>
                <div class="alert alert-danger alert-dismissable" id="DivReligionselect" style="display:none;">
                    <button type="button" class="close alertClose" data-dismiss="alert">&times;</button>
                    <strong>Error: </strong>Please select Religion.
                </div>
                <select id="religionSelect" name="religionSelect" class="v2-form-control" onchange="loadCasteAccordingToReligion(this.value);">
                    <option value="0">Select Religion</option>
                    <c:forEach var="religion" items="\${religions}">
                        <option value='\${religion.religion_id}'>\${religion.religion_name}</option>
                    </c:forEach>
                </select>
            </div>
            
            <div class="v2-form-group">
                <label>Caste</label>
                <div class="alert alert-danger alert-dismissable" id="DivCasteselect" style="display:none;">
                    <button type="button" class="close alertClose" data-dismiss="alert">&times;</button>
                    <strong>Error: </strong>Please select Caste.
                </div>
                <select id="casteSelect" name="casteSelect" class="v2-form-control">
                    <option value="0">Caste</option>
                </select>
            </div>
        </div>
        
        <h2 class="v2-section-title" style="margin-top: 2rem;"><i class="fa fa-graduation-cap"></i> Professional Information</h2>
        <div class="v2-grid">
            <div class="v2-form-group">
                <label>Education Category</label>
                <div class="alert alert-danger alert-dismissable" id="DivEducationCategoryselect" style="display:none;">
                    <button type="button" class="close alertClose" data-dismiss="alert">&times;</button>
                    <strong>Error: </strong>Please select Education Category.
                </div>
                <select id="educationalCategorySelect" name="educationalCategorySelect" class="v2-form-control" onchange="loadEducationAccordingToEduCategory(this.value);">
                    <option value="0">Educational Category</option>
                    <c:forEach var="education" items="\${educationCategories}">
                        <option value='\${education.edu_cat_id}'>\${education.edu_cat_name}</option>
                    </c:forEach>
                </select>
            </div>
            
            <div class="v2-form-group">
                <label>Education</label>
                <div class="alert alert-danger alert-dismissable" id="DivEducationselect" style="display:none;">
                    <button type="button" class="close alertClose" data-dismiss="alert">&times;</button>
                    <strong>Error: </strong>Please select Education.
                </div>
                <select id="educationalSelect" name="educationalSelect" class="v2-form-control" disabled>
                    <option value="0">Select Education</option>
                </select>
            </div>
            
            <div class="v2-form-group">
                <label>Occupation Category</label>
                <div class="alert alert-danger alert-dismissable" id="DivOccupationCategoryselect" style="display:none;">
                    <button type="button" class="close alertClose" data-dismiss="alert">&times;</button>
                    <strong>Error: </strong>Please select Occupation Category.
                </div>
                <select id="occupationCategorySelect" name="occupationCategorySelect" class="v2-form-control" onchange="loadOccupationAccordingToOccuCategory(this.value);">
                    <option value="0">Occupation Category</option>
                    <c:forEach var="occupation" items="\${occupationCategories}">
                        <option value='\${occupation.occu_id}'>\${occupation.occu_name}</option>
                    </c:forEach>
                </select>
            </div>
            
            <div class="v2-form-group">
                <label>Occupation</label>
                <div class="alert alert-danger alert-dismissable" id="DivOccupationselect" style="display:none;">
                    <button type="button" class="close alertClose" data-dismiss="alert">&times;</button>
                    <strong>Error: </strong>Please select Occupation.
                </div>
                <select id="occupationSelect" name="occupationSelect" class="v2-form-control">
                    <option value="0">Select Occupation</option>
                </select>
            </div>
            
            <div class="v2-form-group v2-grid-full">
                <label>Employed In</label>
                <div class="v2-pill-group">
                    <input id="emp_gov" name="employedIn" value="Government" type="radio">
                    <label for="emp_gov">Government</label>

                    <input id="emp_def" name="employedIn" value="Defence" type="radio" checked>
                    <label for="emp_def">Defence</label>

                    <input id="emp_pvt" name="employedIn" value="Private" type="radio">
                    <label for="emp_pvt">Private</label>
                    
                    <input id="emp_bus" name="employedIn" value="Business" type="radio">
                    <label for="emp_bus">Business</label>
                    
                    <input id="emp_self" name="employedIn" value="Self Employed" type="radio">
                    <label for="emp_self">Self Employed</label>
                    
                    <input id="emp_not" name="employedIn" value="Not Working" type="radio">
                    <label for="emp_not">Not Working</label>
                </div>
            </div>
            
            <div class="v2-form-group">
                <label>Income Frequency</label>
                <div class="v2-radio-group">
                    <div class="v2-radio-item">
                        <input name="income" id="inc_monthly" value="Monthly" type="radio" checked>
                        <label for="inc_monthly">Monthly</label>
                    </div>
                    <div class="v2-radio-item">
                        <input name="income" id="inc_annual" value="Annual" type="radio">
                        <label for="inc_annual">Annual</label>
                    </div>
                </div>
            </div>
            
            <div class="v2-form-group">
                <label>Income Amount</label>
                <input type="number" name="incomeAmount" id="incomeAmount" class="v2-form-control" value="0" onkeypress="return event.charCode ">= 48 && event.charCode <= 57 || event.keyCode === 8 || event.keyCode === 9">
            </div>
        </div>

        <h2 class="v2-section-title" style="margin-top: 2rem;"><i class="fa fa-users"></i> Family Details</h2>
        <div class="v2-grid">
            <div class="v2-form-group v2-grid-full">
                <label>Family Class</label>
                <div class="v2-pill-group">
                    <input name="familyClassInfo" id="fc_middle" value="Middle class" type="radio" checked>
                    <label for="fc_middle">Middle class</label>
                    
                    <input name="familyClassInfo" id="fc_upper" value="Upper middle class" type="radio">
                    <label for="fc_upper">Upper middle class</label>
                    
                    <input name="familyClassInfo" id="fc_rich" value="Rich" type="radio">
                    <label for="fc_rich">Rich</label>
                    
                    <input name="familyClassInfo" id="fc_affluent" value="Affluent" type="radio">
                    <label for="fc_affluent">Affluent</label>
                </div>
            </div>
            
            <div class="v2-form-group">
                <label>Family Type</label>
                <div class="v2-pill-group">
                    <input name="familyTypeInfo" id="ft_joint" value="Joint" type="radio" checked>
                    <label for="ft_joint">Joint</label>
                    
                    <input name="familyTypeInfo" id="ft_nuclear" value="Nuclear" type="radio">
                    <label for="ft_nuclear">Nuclear</label>
                </div>
            </div>
            
            <div class="v2-form-group">
                <label>Family Values</label>
                <div class="v2-pill-group">
                    <input name="familyValueInfo" id="fv_trad" value="Traditional" type="radio" checked>
                    <label for="fv_trad">Traditional</label>
                    
                    <input name="familyValueInfo" id="fv_mod" value="Moderate" type="radio">
                    <label for="fv_mod">Moderate</label>
                    
                    <input name="familyValueInfo" id="fv_lib" value="Liberal" type="radio">
                    <label for="fv_lib">Liberal</label>
                    
                    <input name="familyValueInfo" id="fv_orth" value="Orthodox" type="radio">
                    <label for="fv_orth">Orthodox</label>
                </div>
            </div>
        </div>
        
        <h2 class="v2-section-title" style="margin-top: 2rem;"><i class="fa fa-coffee"></i> Lifestyle Details</h2>
        <div class="v2-grid">
            <div class="v2-form-group v2-grid-full">
                <label>Diet</label>
                <div class="v2-pill-group">
                    <input name="eating" id="diet_veg" value="vegetarian" type="radio" checked>
                    <label for="diet_veg">Vegetarian</label>
                    
                    <input name="eating" id="diet_nonveg" value="Non vegetarian" type="radio">
                    <label for="diet_nonveg">Non-Vegetarian</label>
                    
                    <input name="eating" id="diet_egg" value="Eggs Only" type="radio">
                    <label for="diet_egg">Eggs Only</label>
                </div>
            </div>
            
            <div class="v2-form-group">
                <label>Drinking Habit</label>
                <div class="v2-pill-group">
                    <input name="drink" id="dr_never" value="Never" type="radio" checked>
                    <label for="dr_never">Never</label>
                    
                    <input name="drink" id="dr_reg" value="Regular drinker" type="radio">
                    <label for="dr_reg">Regular</label>
                    
                    <input name="drink" id="dr_soc" value="Social drinker" type="radio">
                    <label for="dr_soc">Social</label>
                </div>
            </div>
            
            <div class="v2-form-group">
                <label>Smoking Habit</label>
                <div class="v2-pill-group">
                    <input name="smoke" id="sm_never" value="Non-smoker" type="radio" checked>
                    <label for="sm_never">Never</label>
                    
                    <input name="smoke" id="sm_soc" value="Social smoker" type="radio">
                    <label for="sm_soc">Social</label>
                    
                    <input name="smoke" id="sm_chain" value="Chain smoker" type="radio">
                    <label for="sm_chain">Chain Smoker</label>
                </div>
            </div>
        </div>
        
        <h2 class="v2-section-title" style="margin-top: 2rem;"><i class="fa fa-file-image-o"></i> Documents</h2>
        <div class="v2-grid">
            <div class="v2-form-group v2-grid-full">
                <label>Profile Picture</label>
                <input id="uploadImage" type="file" onchange="loadImageFile();" class="v2-form-control">
            </div>
            
            <div class="v2-form-group v2-grid-full">
                <label>Kundali Document</label>
                <div class="alert alert-danger alert-dismissable" id="kundali_file_alert" style="display:none;">
                    <button type="button" class="close" data-dismiss="alert">&times;</button>
                    <strong>Error: </strong>Please Select Kundali File.
                </div>
                <input type="file" class="v2-form-control" accept=".gif,.jpg,.jpeg,.png" name="loaderKundali" id="loaderKundali" onchange="modelforkundali();">
                <img id="blah1" width="100" height="100" hidden />
            </div>
        </div>
        
        <div class="v2-actions" style="margin-top: 3rem; margin-bottom: 2rem; justify-content: flex-end;">
            <button type="button" class="v2-btn v2-btn-outline" style="margin-right: 1rem;" onclick="MoveToLogin();">Back</button>
            <button type="button" class="v2-btn v2-btn-primary" onclick="MoveToStep3();">Save & Continue</button>
        </div>
    </form>



							<script>
						
						$("#fileSelect").hide();
							$("#nameSelect").hide();
							$("#emailselect").hide();
							$("#mobilenumselect").hide();
							$("#DivMaritalselect").hide();
							$("#DivPhysicalselect").hide();
							$("#DivHeightselect").hide();
							$("#DivWeightselect").hide();
							$("#DivCountryselect").hide();
							$("#DivStateselect").hide();
							$("#DivDistrictselect").hide();
							$("#DivSub-Districtselect").hide();
							$("#DivReligionselect").hide();
							$("#DivCasteselect").hide();
							$("#DivEducationCategoryselect").hide();
							$("#DivEducationselect").hide();
							$("#DivOccupationCategoryselect").hide();
							$("#DivOccupationselect").hide();
							$("#kundali_file_alert").hide();
						</script>
							

					</div>
				</div>

				<!--  -->
				<div id="DivStep3">

					<div class="container"
						style="margin-top: 20px; background: white; border-top: solid 3px" #c82868;">
						<br> <img src="resources/images/step3_new.png" / onerror="this.onerror=null; this.src='resources/UserImages/userImage.png';">
						<div class="alert alert-success alert-dismissable"
							id="successStep2">
							<button type="button" class="close" data-dismiss="alert"
								aria-hidden="true">&times;</button>
							<font size="3px;">  <strong>Error: </strong>Successfully
								Completed Step Two.
							</font>
						</div>
						
						
						
						<div class="alert alert-success alert-dismissable"
							id="regSuccess">
							<button type="button" class="close" data-dismiss="alert"
								aria-hidden="true">&times;</button>
							<font size="3px;">  <strong>Message: </strong>
							</font>
						</div>
						
						

						<!--  -->
						
						
						<form class="form-horizontal" id="companyForm" method="post">
							<div class="row-md-12">
								<div class="col-md-2"></div>
								<div class="col-md-9"></div>

								<div class="companyInfoSection">
									<div class="col-md-2"></div>
									<div class="col-md-10">
										<!-- <h3>PARTNER PREFERANCE</h3> -->
									</div>
									
									<div align="center" style="margin-left: 150px;">
												<h3  class="titletxt-partner" style="width: 400px;">PARTNER PREFERANCE</h3>
									</div>

									<div class="col-md-2"></div>
									<div class="col-md-9">
										<br>
										<div class="form-group col-md-4">
											<label class="" pull-left"
												style="color: gray; width: 150px; height: 20px; font-size: 15px; display: block;">Marital
												Status <sup><font color="red" size="2px">*</font></sup>
											</label>
										</div>
										<div class="col-md-8">
										
										
										<div class="alert alert-danger alert-dismissable"
													id="DivMaritalPartnerselect">
													<button type="button" class="close" data-dismiss="alert"
														aria-hidden="true">&times;</button>
													<font size="3px;"> <strong>Error: </strong>Please select
														Marital Status.
													</font>
										</div>
											<select id="materialStatusPartner"
												name="materialStatusPartner"
												style="color: gray; font-size: 14px;"
												class="form-control   label label-default" " onChange="this.style.color='green';">
												<option value="0">Select Status</option>
												<option value='Single' selected>Single</option>
												<option value='Married'>Married</option>
												<option value='Divorced'>Divorced</option>
												<option value='Widowed'>Widowed</option>
											</select>
										</div>
									</div>

									<div class="col-md-2"></div>
									<div class="col-md-9">
										<div class="form-group col-md-4">
											<label class="" pull-left"
												style="color: gray; width: 150px; height: 20px; font-size: 15px; display: block;">Physical
												Status <sup><font color="red" size="2px">*</font></sup>
											</label>
										</div>
										<div class="col-md-8">
										<div class="alert alert-danger alert-dismissable"
											id="DivPhysicalPartnerselect">
											<button type="button" class="close" data-dismiss="alert"
												aria-hidden="true">&times;</button>
											<font size="3px;"> <strong>Error: </strong>Please select
												Physical Status.
											</font>
										</div>
											<select style="color: gray; font-size: 14px;"
												id="physicalStatusPartner" name="physicalStatus"
												class="form-control   label label-default" " onChange="this.style.color='green';">

												<option value="0">Physical Status</option>
												<option value="Normal" selected>Normal</option>
												<option value="Physically challenged">Physically
													challenged</option>
												<option value="Mentally Challenged">Mentally
													Challenged</option>
												<option value="Deaf">Deaf</option>
												<option value="Dumb">Dumb</option>
												<option value="Other Disability">Other Disability</option>
												<option value="Blind">Blind</option>
											</select>
										</div>
									</div>

									<div class="col-md-2"></div>
									<div class="col-md-9">
										<div class="form-group col-md-4">
											<label class="" pull-left"
												style="color: gray; width: 150px; height: 20px; font-size: 15px; display: block;">Body
												Type <sup><font color="red" size="2px">*</font></sup>
											</label>
										</div>
										<div class="col-md-8">
											<input name="bodyTypePartner" value="Slim" type="radio">
											<label style="color: gray; font-size: 15px;">Slim</label>&nbsp;&nbsp;&nbsp;
											<input name="bodyTypePartner" value="Athletic" type="radio"
												checked> <label
												style="color: gray; font-size: 15px;">Athletic</label>&nbsp;&nbsp;&nbsp;
											<input name="bodyTypePartner" value="Average" type="radio">
											<label style="color: gray; font-size: 15px;">Average</label>&nbsp;&nbsp;&nbsp;
											<input value="Heavy" type="radio" name="bodyTypePartner">
											<label style="color: gray; font-size: 15px;">Heavy</label>&nbsp;&nbsp;&nbsp;


										</div>
									</div>

									<div class="col-md-2"></div>
									<div class="col-md-9">
										<div class="form-group col-md-4">
											<label class="pull-left"
												style="color: gray; width: 150px; height: 20px; font-size: 15px; display: block;">complexion
												<sup><font color="red" size="2px">*</font></sup>
											</label>
										</div>
										<div class="col-md-8">
											<input name="complexionPartner" value="Very Fair"
												type="radio"> <label
												style="color: gray; font-size: 15px;">Very Fair</label>&nbsp;
											<input name="complexionPartner" value="Fair" type="radio">
											<label style="color: gray; font-size: 15px;">Fair</label>&nbsp;
											<input name="complexionPartner" value="Wheatish" checked
												type="radio"> <label
												style="color: gray; font-size: 15px;">Wheatish</label>&nbsp;
											<input value="Wheatish brown" name="complexionPartner"
												type="radio"> <label
												style="color: gray; font-size: 15px;">Wheatish brown</label>&nbsp;
											<input name="complexionPartner" value="Dark" type="radio">
											<label style="color: gray; font-size: 15px;">Dark</label>&nbsp;
										</div>
									</div>


									<div class="col-md-2"></div>
									<div class="col-md-9">
										<div class="form-group col-md-4">
											<label class="" pull-left"
												style="color: gray; width: 150px; height: 20px; font-size: 15px; display: block;">Height
												<sup><font color="red" size="2px">*</font></sup>
											</label>
										</div>
										<div class="col-md-8">
										<div class="alert alert-danger alert-dismissable"
												id="DivHeightPartnerselect">
												<button type="button" class="close" data-dismiss="alert"
													aria-hidden="true">&times;</button>
											<font size="3px;"> <strong>Error: </strong>Please select
													Height.
												</font>
											</div>

											<div class="col-md-4">
											
												<select id="heightSelectPartnerFirst"
													name="heightSelectPartnerFirst"
													style="color: gray; font-size: 14px;"
													class="form-control   label label-default" " onChange="this.style.color='green';">
													<option value="0">Select Height</option>
													<option value="121.92">4 feet</option>
													<option value="124.46">4 feet 1 inches</option>
													<option value="127.00">4 feet 2 inches</option>
													<option value="129.54">4 feet 3 inches</option>
													<option value="132.08">4 feet 4 inches</option>
													<option value="134.62">4 feet 5 inches</option>
													<option value="137.16">4 feet 6 inches</option>
													<option value="139.70">4 feet 7 inches</option>
													<option value="142.24">4 feet 8 inches</option>
													<option value="144.78">4 feet 9 inches</option>
													<option value="147.32">4 feet 10 inches</option>
													<option value="149.86">4 feet 11 inches</option>
													<option value="152.40" selected>5 feet</option>
													<option value="154.94">5 feet 1 inches</option>
													<option value="157.48">5 feet 2 inches</option>
													<option value="160.02">5 feet 3 inches</option>
													<option value="162.56">5 feet 4 inches</option>
													<option value="165.10">5 feet 5 inches</option>
													<option value="167.64">5 feet 6 inches</option>
													<option value="170.18">5 feet 7 inches</option>
													<option value="172.72">5 feet 8 inches</option>
													<option value="175.26">5 feet 9 inches</option>
													<option value="177.80">5 feet 10 inches</option>
													<option value="180.34">5 feet 11 inches</option>
													<option value="182.88">6 feet</option>
													<option value="185.42">6 feet 1 inches</option>
													<option value="187.96">6 feet 2 inches</option>
													<option value="190.50">6 feet 3 inches</option>
													<option value="193.04">6 feet 4 inches</option>
													<option value="195.58">6 feet 5 inches</option>
													<option value="198.12">6 feet 6 inches</option>
													<option value="200.66">6 feet 7 inches</option>
													<option value="203.20">6 feet 8 inches</option>
													<option value="205.74">6 feet 9 inches</option>
													<option value="208.28">6 feet 10 inches</option>
													<option value="210.82">6 feet 11 inches</option>
													<option value="213.36">7 feet</option>
													<option value="215.90">7 feet 1 inches</option>
													<option value="218.44">7 feet 2 inches</option>
													<option value="220.98">7 feet 3 inches</option>
													<option value="223.52">7 feet 4 inches</option>
													<option value="226.06">7 feet 5 inches</option>
													<option value="228.60">7 feet 6 inches</option>
													<option value="231.14">7 feet 7 inches</option>
													<option value="233.68">7 feet 8 inches</option>
													<option value="236.22">7 feet 9 inches</option>
													<option value="238.76">7 feet 10 inches</option>
													<option value="241.30">7 feet 11 inches</option>
												</select>
											</div>
											<div class="col-md-4">
												<select id="heightSelectPartnerSecond"
													name="heightSelectPartnerSecond"
													style="color: gray; font-size: 14px;"
													class="form-control   label label-default" " onChange="this.style.color='green';">
													<option value="0">Select Height</option>
													<option value="121.92">4 feet</option>
													<option value="124.46">4 feet 1 inches</option>
													<option value="127.00">4 feet 2 inches</option>
													<option value="129.54">4 feet 3 inches</option>
													<option value="132.08">4 feet 4 inches</option>
													<option value="134.62">4 feet 5 inches</option>
													<option value="137.16">4 feet 6 inches</option>
													<option value="139.70">4 feet 7 inches</option>
													<option value="142.24">4 feet 8 inches</option>
													<option value="144.78">4 feet 9 inches</option>
													<option value="147.32">4 feet 10 inches</option>
													<option value="149.86">4 feet 11 inches</option>
													<option value="152.40">5 feet</option>
													<option value="154.94">5 feet 1 inches</option>
													<option value="157.48">5 feet 2 inches</option>
													<option value="160.02">5 feet 3 inches</option>
													<option value="162.56">5 feet 4 inches</option>
													<option value="165.10">5 feet 5 inches</option>
													<option value="167.64">5 feet 6 inches</option>
													<option value="170.18">5 feet 7 inches</option>
													<option value="172.72">5 feet 8 inches</option>
													<option value="175.26">5 feet 9 inches</option>
													<option value="177.80">5 feet 10 inches</option>
													<option value="180.34">5 feet 11 inches</option>
													<option value="182.88" selected>6 feet</option>
													<option value="185.42">6 feet 1 inches</option>
													<option value="187.96">6 feet 2 inches</option>
													<option value="190.50">6 feet 3 inches</option>
													<option value="193.04">6 feet 4 inches</option>
													<option value="195.58">6 feet 5 inches</option>
													<option value="198.12">6 feet 6 inches</option>
													<option value="200.66">6 feet 7 inches</option>
													<option value="203.20">6 feet 8 inches</option>
													<option value="205.74">6 feet 9 inches</option>
													<option value="208.28">6 feet 10 inches</option>
													<option value="210.82">6 feet 11 inches</option>
													<option value="213.36">7 feet</option>
													<option value="215.90">7 feet 1 inches</option>
													<option value="218.44">7 feet 2 inches</option>
													<option value="220.98">7 feet 3 inches</option>
													<option value="223.52">7 feet 4 inches</option>
													<option value="226.06">7 feet 5 inches</option>
													<option value="228.60">7 feet 6 inches</option>
													<option value="231.14">7 feet 7 inches</option>
													<option value="233.68">7 feet 8 inches</option>
													<option value="236.22">7 feet 9 inches</option>
													<option value="238.76">7 feet 10 inches</option>
													<option value="241.30">7 feet 11 inches</option>
												</select>
											</div>

										</div>
									</div>


									<div class="col-md-2"></div>
									<div class="col-md-9">
										<h3 class="header-partner">LOCATION</h3>
									</div>

									<div class="col-md-2"></div>
									<div class="col-md-9">
										<div class="form-group col-md-4">
											<label class="" pull-left"
												style="color: gray; width: 150px; height: 20px; font-size: 15px; display: block;">Country
												<sup><font color="red" size="2px">*</font></sup>
											</label>
										</div>
										<div class="col-md-8">
										
										<div class="alert alert-danger alert-dismissable"
											id="DivCountryPartnerselect">
											<button type="button" class="close" data-dismiss="alert"
												aria-hidden="true">&times;</button>
											<font size="3px;"> <strong>Error: </strong>Please select
												Country.
											</font>
										</div>
											<select style="color: gray; font-size: 14px;"
												id="countrySelectPartner" name="countrySelectPartner"
												class="form-control   label label-default" " onChange="this.style.color='green';">
												<!-- <option value="0">Select Country</option> -->
												<c:forEach var="country" items="${countries}">
													<option value="${country.country_id}" >
														${country.country_Name}</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<div class="col-md-2"></div>
									<div class="col-md-9">
										<div class="form-group col-md-4">
											<label class="" pull-left"
												style="color: gray; width: 150px; height: 20px; font-size: 15px; display: block;">State
												<sup><font color="red" size="2px">*</font></sup>
											</label>
										</div>
										<div class="col-md-8">
										<div class="alert alert-danger alert-dismissable"
											id="DivStatePartnerselect">
											<button type="button" class="close" data-dismiss="alert"
												aria-hidden="true">&times;</button>
											<font size="3px;"> <strong>Error: </strong>Please select
												State.
											</font>
										</div>
											<select style="color: gray; font-size: 14px;"
												id="stateSelectPartner" name="stateSelectPartner"
												class="form-control   label label-default" "
												onchange="loadCityAccordingToStatePartnerPreferance(this.value);" this.style.color='green';">
												<option value="0">Select State</option>
												<c:forEach var="stateCountry" items="${state_Countries}">
													<option value="${stateCountry.state_Country_id}" ${stateCountry.state_Name == "Other" ? 'selected' : ''} >
														${stateCountry.state_Name}</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<div class="col-md-2"></div>
									<div class="col-md-9">
										<div class="form-group col-md-4">
											<label class="" pull-left"
												style="color: gray; width: 150px; height: 20px; font-size: 15px; display: block;">City/District
												<sup><font color="red" size="2px">*</font></sup>
											</label>
										</div>
										<div class="col-md-8">
														<div class="alert alert-danger alert-dismissable"
											id="DivDistrictPartnerselect">
											<button type="button" class="close" data-dismiss="alert"
												aria-hidden="true">&times;</button>
											<font size="3px;"> <strong>Error: </strong>Please select
												District.
											</font>
										</div>
											<select style="color: gray; font-size: 14px;"
												id="cityStateSelectPartnerPreferance"
												name="cityStateSelectPartnerPreferance"
												class="form-control   label label-default" " onChange="this.style.color='green';">
												<option value="0">Select City/District</option>
												<option value="708" selected>Other</option>
											</select>
										</div>
									</div>
									<div class="col-md-2"></div>
									<div class="col-md-9">
										<h3 class="header-partner">RELIGION INFORMATION</h3>
									</div>

									<div class="col-md-2"></div>
									<div class="col-md-9">
										<div class="form-group col-md-4">
											<label class="" pull-left"
												style="color: gray; width: 150px; height: 20px; font-size: 15px; display: block;">Religion
												<sup><font color="red" size="2px">*</font></sup>
											</label>
										</div>
										<div class="col-md-8">
										
										<div class="alert alert-danger alert-dismissable"
												id="DivReligionPartnerselect">
												<button type="button" class="close" data-dismiss="alert"
													aria-hidden="true">&times;</button>
												<font size="3px;"> <strong>Error: </strong>Please select
													Religion.
												</font>
									  </div>
											<select style="color: gray; font-size: 14px;"
												id="religionSelectPartner" name="religionSelectPartner"
												class="form-control   label label-default" "
												onchange="loadCasteAccordingToReligionPartner(this.value);this.style.color='green';">
												<option value="0">Select Religion</option>
												<c:forEach var="religion" items="${religions}">
													<option value="${religion.religion_id}" ${religion.religion_name == "Doesn't Matter" ? 'selected' : ''}>
														${religion.religion_name}</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<div class="col-md-2"></div>
									<div class="col-md-9">
										<div class="form-group col-md-4">
											<label class="" pull-left"
												style="color: gray; width: 150px; height: 20px; font-size: 15px; display: block;">Caste
												<sup><font color="red" size="2px">*</font></sup>
											</label>
										</div>
										<div class="col-md-8">
													<div class="alert alert-danger alert-dismissable"
										id="DivCastePartnerselect">
										<button type="button" class="close" data-dismiss="alert"
											aria-hidden="true">&times;</button>
										<font size="3px;"> <strong>Error: </strong>Please select
											Caste.
										</font>
									</div>
											<select style="color: gray; font-size: 14px;"
												id="casteSelectPartner" name="casteSelectPartner"
												class="form-control   label label-default" " onChange="this.style.color='green';">
												<option value="0">Caste</option>
												<option value="550" selected>Doesn't Matter</option>

											</select>
										</div>
									</div>

									<div class="col-md-2"></div>
									<div class="col-md-9">
										<h3 class="header-partner">PROFESSIONAL INFORMATION</h3>
									</div>
									<div class="col-md-2"></div>
									<div class="col-md-9">
									
											<div class="alert alert-danger alert-dismissable"
											id="DivEducationPartnerselect">
											<button type="button" class="close" data-dismiss="alert"
												aria-hidden="true">&times;</button>
											<font size="3px;"> <strong>Error: </strong>Please select
												Education Category.
											</font>
									</div>
										<div class="form-group col-md-4">
											<label class="" pull-left"
												style="color: gray; width: 150px; height: 20px; font-size: 15px; display: block;">Education
												Category <sup><font color="red" size="2px">*</font></sup>
											</label>
										</div>

										<div class="col-md-8" style="OVERFLOW: auto; font-size: 15px;">

											<p class="flip" onclick="showEducation();">Education
												Types</p>

											<div class="panel" style="color: gray;">
												<c:forEach var="education" items="${educationCategories}">
													<div class="col-md-6">
														<input class="form-control" type="checkbox"
															id="${education.edu_cat_id}"
															value="${education.edu_cat_id}"
															name="checkBoxEducationPartner"  ${education.edu_cat_name == "Others" ? 'checked' : ''}>${education.edu_cat_name}
													</div>
												</c:forEach>
											</div>
										</div>


									</div>

									<div class="col-md-2"></div>
									<div class="col-md-9">
										<div class="form-group col-md-4">
											<label class="" pull-left"
												style="color: gray; width: 170px; height: 20px; font-size: 15px; display: block;">Occupation
												Category <sup><font color="red" size="2px">*</font></sup>
											</label>
										</div>

										<div class="col-md-8" style="OVERFLOW: auto; font-size: 15px;">
										
										
											<div class="alert alert-danger alert-dismissable"
												id="DivOccupationPartnerselect">
												<button type="button" class="close" data-dismiss="alert"
													aria-hidden="true">&times;</button>
												<font size="3px;"> <strong>Error: </strong>Please select
													Occupation Category.
												</font>
											</div>

											<p class="flip1" onclick="showOccupation();">Occupation
												Types</p>
											<div class="panel1" style="color: gray;">
												<c:forEach var="occupation" items="${occupationCategories}">
													<div class="col-md-6">
														<input class="form-control" type="checkbox"
															id="${occupation.occu_id}" value="${occupation.occu_id}"
															name="checkBoxOccupationPartner" ${occupation.occu_name == "OTHERS" ? 'checked' : ''}>${occupation.occu_name}
															
													</div>
												</c:forEach>
											</div>
										</div>
									</div>
									<div class="col-md-2"></div>
									<div class="col-md-9">
										<div class="form-group col-md-4">
											<label class="" pull-left"
												style="color: gray; width: 150px; height: 20px; font-size: 15px; display: block;">Employed
												In Type <sup><font color="red" size="2px">*</font></sup>
											</label>
										</div>
										<div class="col-md-8">
											<input id="employedInPartner" name="employedInPartner"
												value="Government" type="radio"> <label
												style="color: gray; font-size: 15px;">Government</label>&nbsp;&nbsp;&nbsp;
											<input id="employedInPartner" name="employedInPartner"
												value="Defence" type="radio" checked> <label
												style="color: gray; font-size: 15px;">Defence</label>&nbsp;&nbsp;&nbsp;
											<input name="employedInPartner" id="employedInPartner"
												value="Private" type="radio"> <label
												style="color: gray; font-size: 15px;">Private </label>&nbsp;&nbsp;&nbsp;
											<input id="employedInPartner" value="Business" type="radio"
												name="employedInPartner"> <label
												style="color: gray; font-size: 15px;">Business</label>&nbsp;&nbsp;&nbsp;

											<input id="employedInPartner" name="employedInPartner"
												value="Self Employed" type="radio" name="employedInPartner">
											<label style="color: gray; font-size: 15px;">Self
												Employed</label>&nbsp;&nbsp;&nbsp; <input id="employedInPartner"
												value="Not Working" type="radio" name="employedInPartner">
											<label style="color: gray; font-size: 15px;">Not
												Working</label>&nbsp;&nbsp;&nbsp;

										</div>
									</div>
									<div class="col-md-2"></div>
									<div class="col-md-9">
										<div class="form-group col-md-4">
											<label class="" pull-left"
												style="color: gray; width: 150px; height: 20px; font-size: 15px; display: block;">Annual
												Income <sup><font color="red" size="2px">*</font></sup>
											</label>
										</div>
										<div class="col-md-8">
											<div class="col-md-6">
												<input name="incomePartner" value="Monthly" type="radio"
													checked> <label
													style="color: gray; font-size: 15px;">Monthly</label>&nbsp;&nbsp;&nbsp;
												<input name="incomePartner" value="Annual" type="radio">
												<label style="color: gray; font-size: 15px;">Annual</label>&nbsp;&nbsp;&nbsp;
											</div>
											<div class="col-md-2 form-group">
												<input type="number" name="incomeAmountPartner"
													style="color: gray; width: 200px; height: 20px; font-size: 15px;"
													id="incomeAmountPartner" value="0">
											</div>
										</div>
									</div>

									<div class="col-md-2"></div>
									<div class="col-md-9">
										<h3 class="header-partner">FAMILY INFORMATION</h3>
									</div>

									<div class="col-md-2"></div>
									<div class="col-md-9">
										<div class="form-group col-md-4">
											<label class="" pull-left"
												style="color: gray; width: 150px; height: 20px; font-size: 15px; display: block;">Family
												Preference </label>
										</div>
										<div class="col-md-8">

											<input name="familyClassInfoPartner"
												id="familyClassInfoPartner" value="Middle class"
												type="radio" checked /><label
												style="color: gray; font-size: 15px;">Middle class</label> <input
												name="familyClassInfoPartner" id="familyClassInfoPartner"
												value="Upper middle class" type="radio" /> <label
												style="color: gray; font-size: 15px;">Upper middle
												class</label> <input name="familyClassInfoPartner"
												id="familyClassInfoPartner" value="Rich" type="radio" /> <label
												style="color: gray; font-size: 15px;">Rich</label> <input
												name="familyClassInfoPartner" id="familyClassInfoPartner"
												value="Affluent" type="radio" /> <label
												style="color: gray; font-size: 15px;">Affluent</label>

										</div>
									</div>

									<div class="col-md-2"></div>
									<div class="col-md-9">
										<div class="form-group col-md-4">
											<label class="" pull-left"
												style="color: gray; width: 150px; height: 20px; font-size: 15px; display: block;">Family
												Type </label>
										</div>
										<div class="col-md-8">

											<input name="familyTypeInfoPartner" value="Joint"
												type="radio" checked /><label
												style="color: gray; font-size: 15px;">Joint</label> <input
												name="familyTypeInfoPartner" value="Nuclear" type="radio" /><label
												style="color: gray; font-size: 15px;">Nuclear</label>
										</div>
									</div>

									<div class="col-md-2"></div>
									<div class="col-md-9">
										<div class="form-group col-md-4">
											<label class="" pull-left"
												style="color: gray; width: 150px; height: 20px; font-size: 15px; display: block;">Family
												values </label>
										</div>
										<div class="col-md-8">

											<input name="familyValueInfoPartner" value="Orthodox"
												type="radio" checked /><label
												style="color: gray; font-size: 15px;">Orthodox</label> <input
												name="familyValueInfoPartner" value="Traditional"
												type="radio" /><label style="color: gray; font-size: 15px;">Traditional</label>

											<input name="familyValueInfoPartner" value="Moderate"
												type="radio" /><label style="color: gray; font-size: 15px;">Moderate</label>
											<input name="familyValueInfoPartner" value="Liberal"
												type="radio" /><label style="color: gray; font-size: 15px;">Liberal</label>
										</div>
									</div>


									<div class="col-md-2"></div>
									<div class="col-md-9">
										<h3 class="header-partner">LIFE STYLE</h3>
									</div>
									<div class="col-md-2"></div>
									<div class="col-md-9">
										<div class="form-group col-md-4">
											<label class="" pull-left"
												style="color: gray; width: 150px; height: 20px; font-size: 15px; display: block;">Eating
												Habits <sup><font color="red" size="2px">*</font></sup>
											</label>
										</div>
										<div class="col-md-8">
											<input name="eatingPartner" value="vegetarian" type="radio"
												checked> <label
												style="color: gray; font-size: 15px;">vegetarian</label>&nbsp;&nbsp;&nbsp;
											<input name="eatingPartner" value="Non vegetarian"
												type="radio"> <label
												style="color: gray; font-size: 15px;">Non vegetarian</label>&nbsp;&nbsp;&nbsp;
											<input name="eatingPartner" value="Eggs Only" type="radio">
											<label style="color: gray; font-size: 15px;">Eggs
												Only</label>

										</div>
									</div>
									<div class="col-md-2"></div>
									<div class="col-md-9">
										<div class="form-group col-md-4">
											<label class="" pull-left"
												style="color: gray; width: 150px; height: 20px; font-size: 15px; display: block;">Drinking
												Habits <sup><font color="red" size="2px">*</font></sup>
											</label>
										</div>
										<div class="col-md-8">
											<input name="drinkPartner" value="Never" type="radio" checked>
											<label style="color: gray; font-size: 15px;">Never</label>&nbsp;&nbsp;&nbsp;
											<input name="drinkPartner" value="Regular drinker"
												type="radio"> <label
												style="color: gray; font-size: 15px;">Regular
												drinker</label>&nbsp;&nbsp;&nbsp; <input name="drinkPartner"
												value="Social drinker" type="radio"> <label
												style="color: gray; font-size: 15px;">Social drinker</label>&nbsp;&nbsp;&nbsp;
										</div>
									</div>
									<div class="col-md-2"></div>
									<div class="col-md-9">
										<div class="form-group col-md-4">
											<label class="" pull-left"
												style="color: gray; width: 150px; height: 20px; font-size: 15px; display: block;">Smoking
												Habits <sup><font color="red" size="2px">*</font></sup>
											</label>
										</div>
										<div class="col-md-8">
											<input name="smokePartner" value="Non-smoker" type="radio"
												checked> <label
												style="color: gray; font-size: 15px;"> Non-smoker </label>&nbsp;&nbsp;&nbsp;
											<input name="smokePartner" value="Social smoker" type="radio">
											<label style="color: gray; font-size: 15px;"> Social
												smoker </label>&nbsp;&nbsp;&nbsp; <input name="smokePartner"
												value="Chain smoker" type="radio"> <label
												style="color: gray; font-size: 15px;">Chain smoker</label>&nbsp;&nbsp;&nbsp;
										</div>
									</div>
									
								    
									<div class="col-md-2"></div>
									<div class="col-md-9">
										<div class="form-group col-md-4">
											  <label for="gender-input" class="col-xs-4 col-form-label" style="color: gray; width: 150px; height: 20px; font-size: 15px; display: block;">User Type</label>
											  </div>
										<div class="col-md-8">
											
											  <input type="radio" name="kitUser"  id="kit" value="RegisteredKit" onchange="readWriteKit();"> <label style="color: gray; font-size: 15px;"> 
												Registered User </label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										
										<input type="radio" name="kitUser"  id="kit" value="FreeKit"  onchange="readOnlyKit();" checked><label style="color: gray; font-size: 15px;"> 
												 Free User </label>
										
											    <input class="form-control form-control-sm" type="tel" value="${kitId}" placeholder="Enter Kit Id" id="kitId" name="kitId" readonly="readonly">
											    <br>
											    <input class="form-control form-control-sm" type="password" value="${kitPassword}" name="kitPassword" id="kitPassword" readonly="readonly" placeholder="Enter Kit Password">
										</div>
									</div>
										
<c:if test="${not empty kitUser}">
										<script>
											$('input[name="kitUser"][value="${kitUser}"]').attr('checked', true);
  										</script>
</c:if>
									
									
								</div>
								
								
															<div class="row">
									 <div class="col-md-3"></div>
									<div class="col-md-6 text-right">
								    		<a href="#containerBg">
										<button type="button" style="margin-left: 4%; size: 30px;"
										onclick="SaveRegistrationDetails();" class="button " ">
										<span>Submit</span>

									</button> 
									</a>
									
								   </div>
								   
								   <div class="col-md-3 text-Left">
								    		<a href="#">
										<button type="button" style="margin-left: 4%; size: 20px;"
											onclick="MoveToStep2();" class="button1">
											<span>Back</span>
										</button>
									</a>
									
								   </div>
								 	 </div>
								
								
								
								<!-- <a href="#containerBg">
									<button type="button" style="margin-left: 80%; size: 30px;"
										onclick="SaveRegistrationDetails();" class="button " ">
										<span>Submit</span>

									</button> 

								</a> -->

							</div>
						</form>
						

						
							<script>
							$("#successStep2").hide();
							$("#regSuccess").hide();
							$("#DivMaritalPartnerselect").hide();
							$("#DivPhysicalPartnerselect").hide();
							$("#DivHeightPartnerselect").hide();
							$("#DivCountryPartnerselect").hide();
							$("#DivStatePartnerselect").hide();
							$("#DivDistrictPartnerselect").hide();
							$("#DivReligionPartnerselect").hide();
							$("#DivCastePartnerselect").hide();
							$("#DivEducationPartnerselect").hide();
							$("#DivOccupationPartnerselect").hide();
						</script>
					</div>
				</div>
			</div>
		</div>


	</div>
	<script>
		$("#DivStep3").hide();
	</script>
	<div class="pagefooter">
		<div class="sociallinks">
			<a href="https://www.facebook.com/snehbandhanburo" class="social-circle"><i class="fa fa-facebook fa-2x"></i></a>
			<a href="https://twitter.com/snehbandhan" class="social-circle"><i class="fa fa-twitter fa-2x"></i></a>
			<a href="#" class="social-circle"><i
				class="fa fa-google-plus fa-2x"></i></a>
		</div>
		<div class="footerlinks mt20" style="font-size: 15px;">
			<nav class="nav nav-inline">
				<a class="nav-link" href="aboutus">About Us</a> <a class="nav-link"
					href="FAQs">FAQs</a> <a class="nav-link" href="TermAndCondition">Terms &
					Conditions</a> <a class="nav-link" href="privacyPolicy">Privacy Policies</a> <a
					class="nav-link" href="contactus">Contact Us</a>
			</nav>
		</div>
		<p class="mt20" style="font-size: 15px;">
			Copyright &copy; 2016 <a href="https://www.snehbandhan.org/login">www.snehbandhan.org</a>
		</p>
	</div>


		   <script src="${pageContext.request.contextPath}/resources/plugins/jQuery/jquery-ui.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/Template/js/tether.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/Template/js/bootstrap.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/Template/js/perfect-scrollbar.jquery.js"></script>

<style>
#ui-datepicker-div { display: none;  }
.ui-datepicker-month { font-size:15px; width: 70px; height: 30px;  }
.ui-datepicker-year { font-size:15px; width: 70px; height: 30px;  }

</style>


	<script>
	
		$(window).resize(function() {
			var rpheight = $('.rightpanel').height();
			$('.chatbox').height(rpheight - 70);
		});
		$(window).resize();
		//        $('.filter-box').perfectScrollbar();
		//        $('.update-box').perfectScrollbar();
		$('#onlinechatbox').on('shown.bs.collapse', function() {
			$(window).resize();
		});
		$('.openchatbox').click(function() {
			$('#userChat').slideToggle();
		});
		$('.closechat').click(function() {
			$('#userChat').slideToggle();
		});
		
		$("#dob").datepicker({
			dateFormat : 'dd/mm/yy',
			changeMonth : true,
			changeYear : true,
			yearRange : '1980:2099'
		}).datepicker();
		
	</script>

</body>
</html>