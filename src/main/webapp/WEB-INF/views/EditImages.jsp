<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="tab-pane active" id="tabthree" role="tabpanel">
					<div class="user-profile clearfix">
						<div class="user-slider col-lg-6 col-md-6 col-sm-5 col-xs-8">

							<div class="user-slider col-lg-6 col-md-6 col-sm-6 col-xs-12">

								<div class="w3-content w3-display-container" id="profileimg">
								<c:forEach var="img" items="${profileImages}">
									<img src="resources/UserImages/${img}" class="mySlides" " id="img${img}" style="width: 100%" onerror="this.onerror=null; this.src='resources/UserImages/userImage.png';"> 
												</c:forEach>
									
									<a class="w3-btn-floating w3-display-left"
										onclick="plusDivs(-1)">&#10094;</a> <a
										class="w3-btn-floating w3-display-right" onclick="plusDivs(1)">&#10095;</a>
								</div>

		
								<input type="hidden" value="" name="cropidName" id="cropidName">

								<script>
									var slideIndex = 1;
									showDivs(slideIndex);

									function plusDivs(n) {
										showDivs(slideIndex += n);
									}

									function showDivs(n) {
										var i;
										var x = document
												.getElementsByClassName("mySlides");
										if (n > x.length) {
											slideIndex = 1
										}
										if (n < 1) {
											slideIndex = x.length
										}
										for (i = 0; i < x.length; i++) {
											x[i].style.display = "none";
										}
										x[slideIndex - 1].style.display = "block";

										$("#cropidName").val(x[slideIndex - 1].id);
									}
								</script>
							<br>
								<input type="button" onclick="CropImage();"  class="btn btn-secondary btn-sm pinkbtn" value="Crop" " >
								<!--end-->
							</div>
							<div class="col-lg-6 col-md-6 col-sm-5 col-xs-8" 
								style="width: 40%; overflow-y: auto; height: 400px;" " >
								
									<div class="" panel-default">
							
   									 <div class="panel-body" style="background-color: #d23779;"><Center></>Remove Profile Images</Center></div>
 							 </div>
 							 
 							 
								<ul  id="divImage">
								<c:forEach var="img" items="${profileImages}">
									<li id="${img}"><img src="resources/UserImages/${img}" class= onerror="this.onerror=null; this.src='resources/UserImages/userImage.png';">
										<li ><input type="button" onclick="removeImage('${img}',this);"  class="btn btn-secondary btn-sm pinkbtn" value="Remove" " ></li>&nbsp;&nbsp;&nbsp;&nbsp;
									</li>&nbsp;&nbsp;&nbsp;&nbsp;
								</c:forEach>
												
												
								</ul>
							</div>

						</div>
						<div class="col-lg-3 col-md-3 col-sm-3 col-xs-3 " " style="border-bottom-width: 1px; border-bottom-color: red; border-width: medium; border-style: dotted; border-color:#d23779;">
							<br>
							<div class="" panel-default">
							
   									 <div class="panel-body" style="background-color: #d23779;"><Center></>Add Profile Images</Center></div>
 							 </div>
							 
							<ul>
							
								<br>
								
								<li >
								<br>
									<input type="file" class="form-control" style="width: 100px; height: auto;" accept=".gif,.jpg,.jpeg,.png"
												name="loader11" id="loader11" multiple="multiple"
												onchange="model();">
												<br>
								</li>
								
									<li>	
										<input type="button" onclick="saveImages();" class="btn btn-secondary" value="Add Images">
									</li>
									
							</ul>
							
							<br>
						</div>
					</div>
				</div>