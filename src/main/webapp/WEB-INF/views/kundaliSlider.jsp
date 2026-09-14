<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

       
                
                
                
  <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>Simple HTML Photo Gallery with JavaScript</title>

<style type="text/css">

.imgthumbnails img {
	height: 80px;
	border: 4px solid #555;
	padding: 1px;
	margin: 0 10px 10px 0;
}

.imgthumbnails img:hover {
	border: 4px solid #00ccff;
	cursor:pointer;
}

.preview img {
	border: 4px solid #444;
	padding: 1px;
	width: 400px;
}
</style>

</head>
<body>
<div class="gallery" align="center">
	
	<div class="imgthumbnails">
	
	  <c:forEach var="img" items="${imageObject}" varStatus="theCount">
  	    	    <img src="${pageContext.request.contextPath}/resources/UserImages/${img}" onmouseover="if(typeof preview !==" 'undefined' && preview && this) preview.src=this.src name="img${theCount.count}" " / onerror="this.onerror=null; this.src='resources/UserImages/userImage.png';">
  	    		</c:forEach>
  	 	</div><br/>

  <c:forEach var="img" items="${imageObject}" varStatus="theCount" begin="0" end="0">
	<div class="preview" align="center">
		<img src="${pageContext.request.contextPath}/resources/UserImages/${img}" name="preview" " alt=""/ onerror="this.onerror=null; this.src='resources/UserImages/userImage.png';">
	</div>
</c:forEach>
</div>


</body>
</html>
              