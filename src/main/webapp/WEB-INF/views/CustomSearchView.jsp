<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<style>
.flash-button{
	background:#d23779;
	padding:5px 10px;
	color:#fff;
	border:none;
	border-radius:5px;
	
	animation-name: flash;
	animation-duration: 1s;
	animation-timing-function: linear;
	animation-iteration-count: infinite;

	//Firefox 1+
	-webkit-animation-name: flash;
	-webkit-animation-duration: 1s;
	-webkit-animation-timing-function: linear;
	-webkit-animation-iteration-count: infinite;

	//Safari 3-4
	-moz-animation-name: flash;
	-moz-animation-duration: 1s;
	-moz-animation-timing-function: linear;
	-moz-animation-iteration-count: infinite;
}

@keyframes flash {  
    0% { opacity: 1.0; }
    50% { opacity: 0.5; }
    100% { opacity: 1.0; }
}

//Firefox 1+
@-webkit-keyframes flash {  
    0% { opacity: 1.0; }
    50% { opacity: 0.5; }
    100% { opacity: 1.0; }
}

//Safari 3-4
@-moz-keyframes flash {  
    0% { opacity: 1.0; }
    50% { opacity: 0.5; }
    100% { opacity: 1.0; }
}
</style>
<c:forEach var="profile" items="${LatestMatch}" begin="0" end="9" varStatus="theCount">
<div class="profile-card">
    <div class="profile-photos" style="cursor: pointer;" onclick="imageSlider('${profile.visitorUserId}');">
        <div class="main-photo-wrapper" style="position:relative;">
            <span class="verified-badge"><i class="fa fa-check-circle"></i> Verified</span>
            <c:set var="mainImg" value="userImage.png" />
            <c:if test="${not empty profile.imageObject}">
                <c:set var="mainImg" value="${profile.imageObject[0]}" />
            </c:if>
            <img src="${pageContext.request.contextPath}/resources/UserImages/${mainImg}" class="main-photo" onerror="this.onerror=null; this.src='resources/UserImages/userImage.png';">
        </div>
        <div class="thumb-photos">
            <c:forEach var="img" items="${profile.imageObject}" begin="1" end="2">
                <img src="${pageContext.request.contextPath}/resources/UserImages/${img}" onerror="this.onerror=null; this.src='resources/UserImages/userImage.png';">
            </c:forEach>
        </div>
    </div>
    <div class="profile-details">
        <h3>${profile.name} <span class="online-dot"></span></h3>
        <div class="info-grid">
            <span><i class="fa fa-calendar"></i> ${profile.age} yrs</span>
            <span><i class="fa fa-map-marker"></i> ${profile.location}</span>
            <span><i class="fa fa-graduation-cap"></i> ${profile.education}</span>
            <span><i class="fa fa-briefcase"></i> ${profile.occupation}</span>
            <span><i class="fa fa-users"></i> ${profile.caste}</span>
        </div>
        <p class="bio-text">${profile.aboutHer}</p>
    </div>
    <div class="profile-actions">
        <div class="meta-info">
            <c:if test="${not empty profile.viewedOnDate}">
                Last Active: <fmt:formatDate type="both" value="${profile.viewedOnDate}" /><br/>
            </c:if>
            <span class="premium-tag">Premium</span>
        </div>
        <div class="action-buttons">
            <a href="#" class="btn-shortlist" onclick="Shortlist('${profile.visitorUserId}','${profile.name}');">
                <i class="fa fa-heart-o"></i> Shortlist
            </a>
            <a target="_blank" href="<c:url value='ViewProfileWithPartner'><c:param name='userId' value="${profile.visitorUserId}"/></c:url>" class="btn-view">
                View Profile
            </a>
            <a href="#" class="btn-icon"><i class="fa fa-comment-o"></i></a>
            <a href="#" class="btn-icon" onclick="Shortlist('${profile.visitorUserId}','${profile.name}');"><i class="fa fa-envelope-o"></i></a>
        </div>
        <script>
            for(var i=0;i<shortListUsersId.length;i++) {
                $("#"+shortListUsersId[i]).hide();
            }
            if (typeof CustomSearchProfiles !== 'undefined') {
                CustomSearchProfiles.push(${profile.visitorUserId});
            }
        </script>
    </div>
</div>
</c:forEach>
