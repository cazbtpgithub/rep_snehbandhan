<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:forEach var="profile" items="${LatestMatch}" begin="0" end="9" varStatus="theCount">
<div class="profile-card">
    <div class="profile-photos" onclick="imageSlider('${profile.visitorUserId}');" style="cursor: pointer;">
        <div class="main-photo-wrapper">
            <span class="verified-badge"><i class="fa fa-check"></i> Verified</span>
            <c:set var="mainImg" value="userImage.png" />
            <c:if test="${not empty profile.imageObject}">
                <c:set var="mainImg" value="${profile.imageObject[0]}" />
            </c:if>
            <img src="${pageContext.request.contextPath}/resources/UserImages/${mainImg}" onerror="this.onerror=null; this.src='${pageContext.request.contextPath}/resources/UserImages/userImage.png';" alt="Profile">
        </div>
        <div class="thumb-photos">
            <c:forEach var="img" items="${profile.imageObject}" begin="1" end="2">
                <img src="${pageContext.request.contextPath}/resources/UserImages/${img}" onerror="this.onerror=null; this.src='${pageContext.request.contextPath}/resources/UserImages/userImage.png';" alt="Thumb">
            </c:forEach>
        </div>
    </div>

    <div class="profile-details">
        <h3 class="profile-name">
            ${profile.name} <span class="online-dot"></span>
        </h3>
        <div class="profile-meta-line">
            <span>${profile.age} yrs, 5'4"</span> &bull; <span>${profile.location}</span>
        </div>
        <div class="profile-meta-line">
            <span>${profile.education}</span> &bull; <span>${profile.occupation}</span>
        </div>
        <div class="profile-meta-line">
            <span>${profile.caste}</span>
        </div>
        <p class="profile-bio">
            <c:choose>
                <c:when test="${not empty profile.aboutHer}">
                    ${profile.aboutHer}
                </c:when>
                <c:otherwise>
                    A simple and homely girl looking for a caring and understanding life partner.
                </c:otherwise>
            </c:choose>
        </p>
    </div>

    <div class="profile-actions-wrapper">
        <div class="profile-status-bar">
            <c:if test="${not empty profile.viewedOnDate}">
                Last Active: <fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${profile.viewedOnDate}" />
            </c:if>
            <c:if test="${empty profile.viewedOnDate}">
                Last Active: 2 Hours ago
            </c:if>
            <span class="tag-premium">Premium</span>
        </div>

        <div class="card-action-btns">
            <a href="#" class="btn-shortlist-outline" onclick="Shortlist('${profile.visitorUserId}','${profile.name}');">
                <i class="fa fa-heart-o"></i> Shortlist
            </a>
            <a target="_blank" href="<c:url value='/ViewProfileWithPartner'><c:param name='userId' value='${profile.visitorUserId}'/></c:url>" class="btn-view-profile-solid">
                View Profile
            </a>
            <a href="#" class="btn-circle-icon"><i class="fa fa-comment-o"></i></a>
            <a href="#" class="btn-circle-icon" onclick="Shortlist('${profile.visitorUserId}','${profile.name}');"><i class="fa fa-envelope-o"></i></a>
        </div>
    </div>
</div>
</c:forEach>
