<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:if test="${fn:length(LatestMatch) == 0}">
 <div class="user-profile clearfix" " id="noRecordDiv">
                    <div class="user-slider col-lg-2 col-md-2 col-sm-2 col-xs-12">
			<p>No Records Found</p>      
        </div>
       </div>
</c:if>
              <c:forEach var="profile" items="${LatestMatch}"  varStatus="theCount">
                 <div class="user-profile clearfix  div_hover" >
                    <div class="user-slider col-lg-4 col-md-6 col-sm-5 col-xs-12">
     <section class="refineSearchOneslider${profile.visitorUserId}">
        <div id="refineSearchOneslider${profile.visitorUserId}" class="flexslider">
          <ul class="slides">
              <c:forEach var="img" items="${profile.imageObject}">
            <li>
  	    	    <img src="${pageContext.request.contextPath}/resources/UserImages/${img}" "/ onerror="this.onerror=null; this.src='resources/UserImages/userImage.png';">
  	    		</li>
  	    		</c:forEach>
  	    		
          </ul>
        </div>
        <div id="refineSearchOnecarousel${profile.visitorUserId}" class="flexslider">
          <ul class="slides">
              <c:forEach var="img" items="${profile.imageObject}">
            <li>
  	    	    <img src="${pageContext.request.contextPath}/resources/UserImages/${img}" width:50px"/ onerror="this.onerror=null; this.src='resources/UserImages/userImage.png';">
  	    		</li>
  	    		  </c:forEach> 
          </ul>
        </div>
      </section>  
    
        <script>     
           $('#refineSearchOnecarousel${profile.visitorUserId}').flexslider({
        animation: "slide",
        controlNav: false,
        animationLoop: false,
        slideshow: false,
        itemWidth: 100,
        itemMargin: 5,
        asNavFor: '#refineSearchOneslider${profile.visitorUserId}'
      });
      $('#refineSearchOneslider${profile.visitorUserId}').flexslider({
        animation: "slide",
        controlNav: false,
        animationLoop: false,
        slideshow: false,
        sync: "#refineSearchOnecarousel${profile.visitorUserId}",
        start: function(slider){
          $('body').removeClass('loading');
        }
      });
        </script>    
        
    <!--end-->
                    </div>
                    <div class="detail-profile col-lg-3 col-md-6 col-sm-7 col-xs-12">
                        <h5 class="member-name">${profile.name}</h5>
                        <ul class="mb10">
                            <li>Located in: <span class="member-location">${profile.location}</span></li>
                            <li>Age: <span class="member-age">${profile.age} Years</span></li>
                            <li>Education: <span class="member-edu">${profile.education}</span></li>
                            <li>Occupation: <span class="member-occu">${profile.occupation}</span></li>
                            <li>Caste: <span class="member-age">${profile.caste}</span></li>
                        </ul>
                        <div class="connect-icon">
                            <a href="#" class="circle"><i class="fa fa-comment"></i></a>
                         	<a href="#" class="circle" onclick="Shortlist('${profile.visitorUserId}','${profile.name}');"><i class="fa fa-mobile" ></i></a> 
							<a href="#" class="circle" onclick="Shortlist('${profile.visitorUserId}','${profile.name}');"><i class="fa fa-envelope" ></i></a>
                        </div>
                    </div>
                    <div class="about-info col-lg-5 col-md-12 col-sm-12 col-xs-12">
                        <p class="text-xs-right"><span class="viewed-ic"></span> 
                        <c:if test="${not empty profile.viewedOnDate}">
  						<b> <font color="#d23779">	Viewed on <fmt:formatDate type="both" value="${profile.viewedOnDate}" /> </font></b>
						</c:if>
                        <c:if test="${empty profile.viewedOnDate}">
						<b> <font color="#d23779"> Profile Not Viewed </font></b>
						</c:if>
                        
                       
                      <!--   <a href="#" class="similarpro">View Similar Profiles</a>     -->
                        </p>
                            <h6>About Me</h6>
                            
                        <p>${profile.aboutHer}
                        </p>
                        <div class="float-xs-left mr5">
                        <button type="button" class="btn btn-secondary btn-sm pinkbtn"  onclick="Shortlist('${profile.visitorUserId}','${profile.name}');">Shortlist</button>
                        </div>
                        <div class="float-xs-left ml5">
                        
                        <a target="_blank"  href="<c:url value="ViewProfileWithPartner">
					                       <c:param name="userId" value="${profile.visitorUserId}"/>
					                </c:url>> <button type="button"  class="btn btn-secondary btn-sm pinkbtn">Full Profile</button> 
					    </a>
                        </div>
                        
                       <script>
                       RefineSearchProfile.push(${profile.visitorUserId});
                        </script>
                        
                    </div>
              
                </div>
                
                </c:forEach>
                
