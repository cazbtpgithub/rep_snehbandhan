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
<link rel="stylesheet" type="text/css"
	href="resources/Registration/registration.css">

	
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
		
		
</head>

<script type="text/javascript">
	function showEducation() {
		$(".panel").slideToggle("slow");
	}


	
	function model() {

		var x = "";
		files = [];
		var fileNames = "";
		var inp = document.getElementById('loader11');
		var flag = 0;
		for (var i = 0; i < inp.files.length; ++i) {
			var name = inp.files.item(i).name;
			files[i] = inp.files.item(i).name;
			x = x
					+ "	<img src="" " class='logo' + window.URL.createObjectURL(inp.files.item(i)) + "' style="height: 200px; width: 200px; border-bottom-width: 2px; border-bottom-color: red; border-width: medium; border-style: dotted; border-color:skyblue">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
			flag = 1;

		}

		$("#myModal").modal();

		$(".model-images").html(x);

	}
	function showOccupation() {
		$(".panel1").slideToggle("slow");
	}

	function countChar(val) {
		$("#aboutMe").css({
			'background-color' : '#f4f4f4'
		});
		var len = val.value.trim().length;
		if (len >= 101) {
			$('#charNum').text(0 + " Charecter  Remaining");
		} else {
			$('#charNum').text(100 - len + " Charecter  Remaining");
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
</style>

<body>
	<div class="">
		<nav class="navbar navbar-full">
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
		</nav>
	</div>

	<div class="maincontainer clearfix">
		<nav class="navbar " "
			style="background-color: #c82868;">
			<div class="collapse navbar-toggleable-sm" id="exCollapsingNavbar">
				<div class="nav navbar-nav float-xs-right">
					<a class="nav-item nav-link active" href="#"><i
						class="fa fa-home"></i> Home </a> <a class="nav-item nav-link"
						href="#"><i class="fa fa-envelope"></i> Inbox</a> <a
						class="nav-item nav-link" href="#"><i class="fa fa-heart"></i>
						Matches</a> <a class="nav-item nav-link" href="#"><i
						class="fa fa-user"></i> My Profile</a> <a class="nav-item nav-link"
						href="#"><i class="fa fa-search"></i> Search</a>
				</div>
			</div>
		</nav>
		<div class="col-lg-12 col-md-3 col-sm-3 col-xs-12">


			<!-- Remove this code if not needed -->
			<!-- Modal -->
			<div class="modal" " id="myModal" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="momPopupWrap">
							<div class="popupHeader">

								<button type="button" class="close popupClose"
									data-dismiss="modal" aria-label="Close">
									<i class="fa fa-times"></i>
								</button>
								<div class="model-images" "></div>
							</div>

						</div>
					</div>
				</div>
			</div>

			<div id="secondModal" class="modal fade" data-backdrop="static"
				data-keyboard="false">
				<div class="modal-dialog">
					<!--   <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;
                    </button> -->
					<div class="modal-body">

						<div class="col-md-6">
							<img src="resources/images/spinner.gif" class="img-fluid" height="150px" width="150px"> <br> <Font
								color="White" size="3px;">creating your profile ...<br> do not refresh page </Font>
						</div>
					</div>
				</div>
			</div>
			<!--model end   -->


			<div id="containerBg">
				<div id="DivStep2">

					<div class="container"
						style="margin-top: 20px; background: white; height:60%; border-bottom: solid 3px #c82868; border-top: solid 3px" #c82868;">
						<br> 
						
			<div class="alert alert-danger alert-dismissable"
			id="unameselect"  style="font-size: 15px;">
			 <button type="button" class="close" data-dismiss="alert"
		aria-hidden="true">&times;</button>
			<strong>Error: </strong>Please Enter Email Id Or Mobile Number.
		</div>  
		
		<div class="alert alert-danger alert-dismissable"
			id="mobilenumselect"  style="font-size: 15px;">
			 <button type="button" class="close" data-dismiss="alert"
		aria-hidden="true">&times;</button>
			<strong>Error: </strong>Please Enter Valid Mobile Number.
		</div>  
		
		<div class="alert alert-danger alert-dismissable"
			id="emailselect"  style="font-size: 15px;">
			 <button type="button" class="close" data-dismiss="alert"
		aria-hidden="true">&times;</button>
			<strong>Error: </strong>Please Enter Valid Email Id.
		</div>
		 
		<div class="alert alert-danger alert-dismissable"
			id="invalidselect" style="font-size: 15px;">
			 <button type="button" class="close" data-dismiss="alert"
		aria-hidden="true">&times;</button>
			<strong>Error: </strong> Given Details Are Not Available In Snehbandhan .
		</div>
		
		
		
		<div class="alert alert-danger alert-dismissable"
			id="typeselect" style="font-size: 15px;">
			 <button type="button" class="close" data-dismiss="alert"
		aria-hidden="true">&times;</button>
			<strong>Error: </strong> Please select Type.
		</div>
		
		<div class="alert alert-success"
			id="successForm" style="font-size: 15px;">
			<button type="button" class="close" data-dismiss="alert"
		aria-hidden="true">&times;</button>
			<strong>Message: </strong>User Details Successfully Send On Given Details.
		</div>
		<script type="text/javascript">

			$("#unameselect").hide();
	        $("#successForm").hide();
	        $("#invalidselect").hide();
	        $("#typeselect").hide();  
	        $("#mobilenumselect").hide(); 
	        $("#emailselect").hide();
	     </script> 
			
		<div class="" style="width: 500px;"
	margin: 120px auto;
	border: solid 1px #dddddd;
	border-radius: 4px;
	text-align: center;
	position: relative;
	background-color: ">
				<h5 >Forgot your password? </h5>
				<form class="form-signin" name="f1" id="f1">
				
					
				<br>
						<div >
						<div class="form-group" " id="emailBox">
								<input type="text"name="email" id="email" class=""
									placeholder="Enter Your Email Or Mobile Number"  autofocus="" style="width: 60%; font-size: 15px;" />
						</div>
						
						<div autofocus="" style="width: 88%; font-size: 15px;">	
						<span> Type &nbsp;&nbsp;&nbsp;</span>
						<input type="radio" name="byType" id="byType" value="byEmail" checked>By Email	 &nbsp;&nbsp;&nbsp;
						<input type="radio" name="byType" id="byType" value="byText">By Message
						
						</div>
							<input type="Button" value="Send" id="submit" onclick="return showPassWord();" class="btn btn-primary btn-small" " style="padding: 1px" 5px;"/>
							<a style="position: absolute;right: 15px;top: 115px;font-size: 15px;text-decoration: underline;"
 							 href="<c:url value="login">
		                      </c:url>>Login</a></td>
							
							<!-- data-toggle='modal' data-target="#myModal"/ -->
						</div>
						
						
					</form>
			</div>
		</div>
		
	     
	</div>

					</div>
				</div>

			
			</div>
	
	
	<div class="pagefooter">
		<div class="sociallinks">
			<a href="#" class="social-circle"><i class="fa fa-facebook fa-3x"></i></a>
			<a href="#" class="social-circle"><i class="fa fa-twitter fa-3x"></i></a>
			<a href="#" class="social-circle"><i
				class="fa fa-google-plus fa-3x"></i></a>
		</div>
		<div class="footerlinks mt20" style="font-size: 15px;">
			<nav class="nav nav-inline">
				<a class="nav-link" href="#">About Us</a> <a class="nav-link"
					href="#">FAQs</a> <a class="nav-link" href="#">Terms &
					Conditions</a> <a class="nav-link" href="#">Privacy Policies</a> <a
					class="nav-link" href="#">Contact Us</a>
			</nav>
		</div>
		<p class="mt20" style="font-size: 15px;">
			Copyright &copy; 2016 <a href="#">www.snehbandhan.org</a>
		</p>
	</div>


		   <script src="${pageContext.request.contextPath}/resources/plugins/jQuery/jquery-ui.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/Template/js/tether.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/Template/js/bootstrap.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/Template/js/perfect-scrollbar.jquery.js"></script>


<script type="text/javascript">

function showPassWord()
{
	

	
	
	var name= $("#email").val();
	var type = $("input[name=byType]:checked").val();
	var emailRegex = /^[A-Za-z0-9._]*\@[A-Za-z]*\.[A-Za-z]{2,5}$/;
	
	
	if (name=="") {
		
		$("#unameselect").slideDown();
		$("#unameselect").fadeOut(3000);
		$("#unameselect").focus();
		return false;
		
		return false;
	}
	

	if(type=="" ||type==null)
		{
				$("#typeselect").slideDown();
				$("#typeselect").fadeOut(3000);
				$("#typeselect").focus();
					return false;
		}
	
	
	if(type.toUpperCase() === 'byText'.toUpperCase() && name.length !=10)
			{
			  $("#mobilenumselect").slideDown();
			  $("#mobilenumselect").fadeOut(15000);
			    return false;
			}
	
	if((type.toUpperCase() === 'byEmail'.toUpperCase()) && (!emailRegex.test(name)))
	{
	  	$("#emailselect").slideDown();
		 $("#emailselect").fadeOut(15000);
	    return false;
	}
	
	
	
	var formData = $("#f1").serialize();
	
	$.ajax({
		url : "sendForgotPassword",
		type : "GET",
		data :formData,
		success : function(data) {
			
			if(data=='Success')
				{
			
				    $("#email").val("");
					$("#successForm").slideDown();
					$("#successForm").fadeOut(6000);
				}
			else
				{
			
				
							 $("#invalidselect").slideDown();
							 $("#invalidselect").fadeOut(6000);
				
				 		
				
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


