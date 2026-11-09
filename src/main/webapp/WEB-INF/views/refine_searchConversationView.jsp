<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:if test="${empty LatestMatch}">
    <div style="text-align: center; padding: 48px 24px; background: #ffffff; border-radius: 12px; border: 1px solid #f0e4e8; margin-bottom: 20px;">
        <i class="fa fa-filter fa-3x" style="color: #d21a5a; opacity: 0.6; margin-bottom: 12px;"></i>
        <h4 style="color: #333333; font-weight: 700; margin-bottom: 8px;">No Profiles Match Your Filters</h4>
        <p style="color: #777777; font-size: 14px; margin-bottom: 16px;">Try adjusting or clearing your filters (Age, Height, Religion, Caste, Location) to see more profiles.</p>
        <button type="button" class="btn-search-submit" style="display: inline-block; padding: 8px 22px; font-size: 13px;" onclick="clearAllAdvanceFilters();">Clear Filters</button>
    </div>
</c:if>

<c:forEach var="profile" items="${LatestMatch}" varStatus="theCount">
    <div class="profile-card" data-user-id="${profile.visitorUserId}">
        <div class="profile-photos" onclick="imageSlider('${profile.visitorUserId}');" style="cursor: pointer;">
            <div class="main-photo-wrapper">
                <span class="verified-badge"><i class="fa fa-check"></i> Verified</span>
                <c:set var="mainImg" value="userImage.png" />
                <c:if test="${not empty profile.imageObject && not empty profile.imageObject[0]}">
                    <c:set var="mainImg" value="${profile.imageObject[0]}" />
                </c:if>
                <img src="${pageContext.request.contextPath}/resources/UserImages/${mainImg}" onerror="this.onerror=null; this.src='${pageContext.request.contextPath}/resources/UserImages/userImage.png';" alt="Profile">
            </div>
            <div class="thumb-photos">
                <c:forEach var="img" items="${profile.imageObject}" begin="1" end="2">
                    <c:if test="${not empty img}">
                        <img src="${pageContext.request.contextPath}/resources/UserImages/${img}" onerror="this.onerror=null; this.src='${pageContext.request.contextPath}/resources/UserImages/userImage.png';" alt="Thumb">
                    </c:if>
                </c:forEach>
            </div>
        </div>

        <div class="profile-details">
            <h3 class="profile-name">
                ${profile.name} <span class="online-dot"></span>
            </h3>
            <div class="profile-meta-line">
                <span>${profile.age} yrs</span> &bull; <span>${profile.location}</span>
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
                        Looking for a compatible and understanding life partner.
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
                    Last Active: Recently
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
