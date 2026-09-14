<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="box box-primary">
<div class="row">

<div class="col-md-3">
<h4>Remove Images</h4>
<div id="imgDiv"> 
<ul style="list-style: none;">
<c:forEach var="img" items="${profileImages}">
	<li id="${img}" ><img src="resources/UserImages/${img}" class= onerror="this.onerror=null; this.src='resources/UserImages/userImage.png';">
	<li ><input type="button" class="btn-danger pinkbtn" onclick="removeImageByAdmin('${img}',this,'${userId}');"  class="btn btn-secondary btn-sm pinkbtn" value="Remove" " style="background-color: #d23779; height: 20px;"></li>&nbsp;&nbsp;&nbsp;&nbsp;
	</li>&nbsp;&nbsp;&nbsp;&nbsp;
 </c:forEach>
</ul>
</div>
</div>
<div class="col-md-3">
<h4>Add  Images</h4>
<div id="addimgDiv"> 

								<ul style="list-style: none;">
							
								<br>
								
								<li >
								<br>
									<input type="file" class="form-control" style="width: 100px; height: auto;" accept=".gif,.jpg,.jpeg,.png"
												name="loader11" id="loader11" multiple="multiple"
												onchange="model();">
												<br>
								</li>
								
									<li>	
										<input type="button" onclick="saveImagesByAdmin('${userId}');" data-toggle="modal" data-target="#userlogin"  class="btn-danger pinkbtn" value="Add Images" style="background-color: #d23779;height: 20px;">
									</li>
									
							</ul>


</div>
</div>
</div>

<div class="col-md-3">
<h4>Remove Kundali Image</h4>
<div id="kundaliImgDiv"> 
<ul style="list-style: none;">
<c:forEach var="kundaliImg" items="${kundaliImages}">
	<li id="${kundaliImg}" ><img src="resources/UserImages/${kundaliImg}" class= onerror="this.onerror=null; this.src='resources/UserImages/userImage.png';">
	<li ><input type="button" class="btn-danger pinkbtn" onclick="removeKundaliImageByAdmin('${kundaliImg}',this,'${userId}');"  class="btn btn-secondary btn-sm pinkbtn" value="Remove" " style="background-color: #d23779; height: 20px;"></li>&nbsp;&nbsp;&nbsp;&nbsp;
	</li>&nbsp;&nbsp;&nbsp;&nbsp;
 </c:forEach>
</ul>
</div>
</div>
<div class="col-md-3">
<h4>Add Kundali Image</h4>
<div id="addKundaliImgDiv"> 

								<ul style="list-style: none;">
							
								<br>
								
								<li >
								<br>
									<input type="file" class="form-control" style="width: 100px; height: auto;" accept=".gif,.jpg,.jpeg,.png"
												name="loaderKundaliAdmin" id="loaderKundaliAdmin" multiple="multiple"
												onchange="model();">
												<br>
								</li>
								
									<li>	
										<input type="button" onclick="saveKundaliImagesByAdmin('${userId}');" data-toggle="modal" data-target="#userlogin"  class="btn-danger pinkbtn" value="Add Kundali Image" style="background-color: #d23779;height: 20px;">
									</li>
									
							</ul>


</div>
</div>

</div>
</div>
