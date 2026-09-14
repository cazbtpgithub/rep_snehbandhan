<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>




<%-- 
<% 
response.setHeader("Cache-Control", "no-cache"); //Forces caches to obtain a new copy of the page from the origin server 
response.setHeader("Cache-Control", "no-store"); //Directs caches not to store the page under any circumstance 
response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale" 
response.setHeader("Pragma", "no-cache"); //HTTP 1.0 backward compatibility 
 
%> --%>


<%-- <html>
	<body>
		<h1 id="banner">Login to Security Demo</h1>  
		
		<form name="f" action="<c:url value='j_spring_security_check'/>
					method="POST">
			<table>
				<tr>
					<td>Username:</td>
					<td><input type='text' name='j_username' /></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><input type='password' name='j_password'></td>
				</tr>
				<tr>
					<td colspan="2">&nbsp;</td>
				</tr>
				<tr>
					<td colspan='2'><input name="submit" type="submit">&nbsp;<input name="reset" type="reset"></td>
				</tr>
			</table>
		</form>
	</body>
</html> --%>

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
<link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<style type="text/css">
/* input style */
.form-control, input {
	border: none;
	border-bottom: solid 1px #dddddd;
	box-shadow: none;
	border-radius: 0;
	padding: 5px;
}

.form-control:focus, input :focus {
	outline: none;
	border-color: #cc0001;
	box-shadow: none;
}

/* button style */
.btn {
	padding: 10px 15px;
	border-radius: 0;
}

.btn-primary {
	background: #c5161d;
	color: #ffffff;
	text-align: center;
	border-color: #c5161d;
}

.btn-primary:hover {
	background: #d6272e;
	border-color: #d6272e;
}

.link {
	color: #092d3d;
}

.errorMessage {
	color: #c5161d;
    font-size: 12px;
    left: 18px;
    padding: 0 10px;
    position: absolute;
    text-align: left;
    top: 55px;	
}

body {
	background: #ffffff;
	font-family: arial;
}

.headerWrap {
	background: #fff;
	height: 80px;
	border-bottom: solid 1px #dddddd;
}

.headerWrap h1 {
	margin: 10px 0;
}

.containerBg {
	background: url("data:image/jpeg;base64,/9j/4AAQSkZJRgABAgAAZABkAAD/7AARRHVja3kAAQAEAAAATwAA/+4ADkFkb2JlAGTAAAAAAf/bAIQAAwICAgICAwICAwQCAgIEBAMDAwMEBQQEBAQEBQYFBgUFBgUGBggICAgIBgoKCwsKCgwMDAwMDAwMDAwMDAwMDAEDAwMFBQUKBgYKDgsJCw4QDg4ODhAQDAwMDAwQEAwMDAwMDBAMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwM/8AAEQgASACbAwERAAIRAQMRAf/EAG0AAQEBAQAAAAAAAAAAAAAAAAABAgkBAQAAAAAAAAAAAAAAAAAAAAAQAAADBQYFAgUEAwAAAAAAAAABAvCRElKjEVFh4SJiITFB0ZOBknEyomODQnKCAyMzcxEBAAAAAAAAAAAAAAAAAAAAAP/aAAwDAQACEQMRAD8A6hwnLSzAITPkmkfcAhOWkAQ7aQBCctLMAJJy0swCHbSzZ4BCfKGlmAQ7S8R9wCHbSAIdtn4gCE5aWYBCctI+4BCctJm9QCE5aWYBCcpeI+4BCfVNLMAhOWkzegBCfVNLMAhOWlmAmi9L1gJovS9YC6Nr1t0awAODa9bdGsATTel6wF03l9YBo2vWbNeAhwXpesA0YPWApQbXrboAaNr1t0awBNF6frANN6XrAXRtetmxANO16wE0XpesBdF6bPisA0bXrZsADRtesBNF6XrAaixqAEVx1MgCKw+Cuf3Wb1ASLlx+H+TJngEWNTIAixqZAEWNVm9ACI76gCxbqmQCRbsP9mTPACVcqr8GYwCLGpkAtvD5qmQCRXny+4zegCxc+NQBLcamQCxXHUASK4+f3Gb1AWKz9VQAixqZALr3fQAa71H6oANV6noANd6noANe56AE17vU0ALrvVwxQTNcAmvdZ8UAFqt30ALr3PQYBrvU9ABavrE9ABq5lE9ABr3PQAa9z0M2ABrvU9ABr3ccUM2IBr3PQAat3oaAC1d6noASEpaWYCQlLSIBYSlpZs4BISlpZs4BYSlpEAkJS0i7gEJS/Cz+rNngEJSu/q7mAQlLSzAISlpAEJS0s2cAQlLSIBYSl5/aLuAkJS8vtZs8BYSlpAEJS0iASEpX/wBWYBCUvP7WbOAWEpT8RAEKZaRdwEsI+iXLALCuS5YC6dpei2bABOFycOC2bAAsK5LlgFhXJcsA03JcturWgFhH0I/RYBYVyXLAOBHySVmCybkAcOhJctmwALCuS5YBYVyXLANNyXLbq1oC8D6EfosBOFyXLAXhcTlgJpuS5bdGsAOFxOWAWFcn6wCIucReQwCIuii8hgERTl5DALUzl5DAIimLyGARFMXkMAiKcvIfYAiKYvIYBEUxeQwCIpiL8h9gCIpyP8hgESZi8hgBqKYvIYBamci/IfYAiLoorf8AofYBbSmLyGAkSZi8h9gC1M5H+Q+wCxFMXkMBIimLyGA0Rnu9yAC0zm9yAEtVu96DZrwCJRze9Bs14BaZTe5ABarnq96GbAAiPd7kEzXAFqjnL+aGbABbVbrf3IAS1RTe5DNiApKUc3HehmxAS1W4/wCSAC1XH5vcgAiPdw3IJmuAW1W73IZsAEtO2zVb+5AC2q3e5DNiAkR7uO5Bs14C2ner3oASI91/zoACSqWkQBCq6mkAgVLTLviAQquppALDuPxl3AIVcNNNPcAhVLTJugBCrlDTIAhO4/GQASVH+mmXcBYVS00t1ASxV1n40gEKpT8ZdwCFV1NPfAAhVLTLuzwCFV1MrACFUtMu7OAIVXU098QCFUp+NPcAsVcfiT3Af//Z") 0 0 repeat;
	height: 90%; 
}

.loginWrap {
	width: 320px;
	margin: 120px auto;
	border: solid 1px #dddddd;
	border-radius: 4px;
	text-align: center;
	position: relative;
}

.loginWrap form {
	margin-bottom: 0;
}

.loginWrap h5 {
	background: #092d3d;
	padding: 15px 0;
	border-bottom: solid 3px #cc0001;
	border-radius: 4px 4px 0 0;
	margin: 0;
	color: #ffffff;
	text-align: center;
}

.loginWrap .formWrap {
	background: #ffffff;
	padding: 40px 15px 15px 15px;
	border-radius: 0 0 5px 5px;
}

.loginWrap .form-group {
	margin-bottom: 20px;
	position: relative;
}

.loginWrap .form-group .forgotPassword {
	position: absolute;
	right: 10px;
	top: 53px;
	font-size: 11px;
	text-decoration: underline;
}

.loginWrap .fa-times {
	color: #ffffff;
	float: right;
	margin: 0 20px;
	font-size: 18px;
}

.rememberMeWrap {
	margin-top: 15px;
	text-align: left;
}

.rememberMeWrap label {
	font-weight: normal;
	cursor: pointer;
}

.loginWrap .btn-primary {
	width: 180px;
	margin: 0 auto;
}


.footerWrap {
	padding: 15px 0;
	text-align: center;
}
</style>
<script type="text/javascript">
    window.history.forward();

    function noBack() {
      window.history.forward();
    }
  </script>
</head>
<body onload="noBack();" onpageshow="if (event.persisted)" noBack();">
	<div class="headerWrap text-center">
		<h1>
			<a href="#"><img src="data:image/png;base64,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" " style="width: 290px" onerror="this.onerror=null; this.src='resources/UserImages/userImage.png';">
</a>
		</h1>
	</div>
	<div class="containerBg">
		<div class="container">
			<div class="loginWrap">
				<h5>Sign In</h5>
				<form class="form-signin" name="f"
					action="<c:url value='j_spring_security_check'/> method="POST">
				<p class="errorMessage">	${errorMsg} </p>
						<div class="formWrap">
							<div class="form-group">
								<input type="text" class="form-control" name='j_username'
									placeholder="User Name" required="required" autofocus="" />
							</div>
							<div class="form-group">
								<input type="password" class="form-control" name='j_password'
									placeholder="Password" required="required" />
								<div class="rememberMeWrap">
									<input type="checkbox" name="remember-me" /> <label for="rememberMe">Remember
										me</label>
										
										 <a href="<c:url value='forgotpassword'/>
									class="forgotPassword link">Forgot ?</a>
									
									
									
								</div>
							</div>
							<button class="btn btn-primary" type="submit">Login</button>
						</div>
						<input type="hidden" name="${_csrf.parameterName}"
	value="${_csrf.token}" />
	

					</form>
					
			</div>
			
		</div>
	</div>

<div class="navbar navbar-default navbar-fixed-bottom">
   <div class="container">
		<div class="footerWrap">
	     <p>&copy; CoreFlex Solutions Inc.</p>
	   	</div>
	</div>
</div>
</body>
</html>