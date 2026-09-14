<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>SnehBandhan - User Dashboard</title>
    
    <!-- Google Fonts -->
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;500;600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="resources/Template/css/font-awesome.min.css">
    <link rel="stylesheet" href="resources/Template/css/bootstrap.min.css">
    <link rel="stylesheet" href="resources/customcss/dashboard_new.css">

    <script src="${pageContext.request.contextPath}/resources/Template/js/jquery-min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/Template/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/ProfileViewer/profileView.js"></script>

    <style>
        /* Supplementary inline styles for exact alignment */
        .dash-search-container {
            position: relative;
            flex: 1;
            max-width: 420px;
        }
        .dash-search-container input {
            width: 100%;
            padding: 10px 40px 10px 18px;
            border-radius: 25px;
            border: 1px solid #f0e4e8;
            background: #faf6f7;
            font-size: 13px;
            outline: none;
        }
        .dash-search-container i {
            position: absolute;
            right: 15px;
            top: 50%;
            transform: translateY(-50%);
            color: #888;
        }
        .online-dot-lg {
            width: 8px;
            height: 8px;
            border-radius: 50%;
            background: #10b981;
            display: inline-block;
            margin-left: 6px;
        }
    </style>
</head>
<body>

    <!-- 1. Header / Navbar -->
    <header class="dash-navbar">
        <div class="logo">
            <a href="<c:url value='/userHome'/>">
                <img src="${pageContext.request.contextPath}/resources/images/logo.png" alt="SnehBandhan Logo" onerror="this.onerror=null; this.src='${pageContext.request.contextPath}/resources/Template/images/logo.png';">
            </a>
        </div>

        <div class="dash-search-container">
            <input type="text" placeholder="Search by ID / Name">
            <i class="fa fa-search"></i>
        </div>

        <div class="dash-nav-actions">
            <a href="<c:url value='/KitUpgradeAndPayment'/>" class="btn-upgrade">
                <i class="fa fa-crown"></i> Upgrade Plan
            </a>
            
            <a href="#" class="nav-icon-btn" title="Chat">
                <i class="fa fa-comment-o"></i>
                <span class="badge">0</span>
            </a>

            <a href="#" class="nav-icon-btn" title="Notifications">
                <i class="fa fa-bell-o"></i>
                <span class="badge">5</span>
            </a>

            <div class="user-profile-pill">
                <img src="${pageContext.request.contextPath}/resources/UserImages/${UserProfile}" onerror="this.onerror=null; this.src='${pageContext.request.contextPath}/resources/UserImages/userImage.png';" alt="User">
                <div class="user-info">
                    <span class="user-name">${ActUserName}</span>
                    <span class="user-role"><i class="fa fa-crown"></i> Premium Member</span>
                </div>
                <i class="fa fa-angle-down" style="color: #888; margin-left: 4px;"></i>
            </div>
        </div>
    </header>

    <!-- 2. Main Page 3-Column Layout -->
    <div class="dashboard-layout">

        <!-- Column 1: Left Sidebar -->
        <aside class="dash-sidebar">
            <!-- User Mini Profile Card -->
            <div class="sidebar-user-card">
                <img src="${pageContext.request.contextPath}/resources/UserImages/${UserProfile}" onerror="this.onerror=null; this.src='${pageContext.request.contextPath}/resources/UserImages/userImage.png';" alt="Avatar">
                <h3>${ActUserName}</h3>
                <span class="badge-premium"><i class="fa fa-crown"></i> Premium Member</span>
            </div>

            <!-- Navigation Links -->
            <nav class="sidebar-menu">
                <ul>
                    <li>
                        <a href="#" class="active" onclick="switchConversation('mainDivForUser');">
                            <div class="menu-left"><i class="fa fa-dashboard"></i> Dashboard</div>
                        </a>
                    </li>
                    <li>
                        <a href="#" onclick="switchConversation('inbox_conversation');">
                            <div class="menu-left"><i class="fa fa-envelope-o"></i> Inbox</div>
                            <span class="badge-count">12</span>
                        </a>
                    </li>
                    <li>
                        <a href="#" onclick="switchConversation('mainDivForUser');">
                            <div class="menu-left"><i class="fa fa-heart-o"></i> Interest Received</div>
                            <span class="badge-count">8</span>
                        </a>
                    </li>
                    <li>
                        <a href="#" onclick="switchConversation('sent_conversation');">
                            <div class="menu-left"><i class="fa fa-paper-plane-o"></i> Interest Sent</div>
                            <span class="badge-count">6</span>
                        </a>
                    </li>
                    <li>
                        <a href="#" onclick="switchConversation('mainDivForUser');">
                            <div class="menu-left"><i class="fa fa-users"></i> Matches</div>
                        </a>
                    </li>
                    <li>
                        <a href="#" onclick="switchConversation('mainDivForUser');">
                            <div class="menu-left"><i class="fa fa-eye"></i> Who Viewed Me</div>
                            <span class="badge-count">15</span>
                        </a>
                    </li>
                    <li>
                        <a href="#" onclick="switchConversation('mainDivForUser');">
                            <div class="menu-left"><i class="fa fa-star-o"></i> Shortlisted</div>
                            <span class="badge-count">4</span>
                        </a>
                    </li>
                    <li>
                        <a href="#">
                            <div class="menu-left"><i class="fa fa-ban"></i> Blocked Profiles</div>
                        </a>
                    </li>
                    <li>
                        <a href="<c:url value='/KitUpgradeAndPayment'/>">
                            <div class="menu-left"><i class="fa fa-credit-card"></i> My Plan</div>
                        </a>
                    </li>
                    <li>
                        <a href="<c:url value='/editProfile'/>">
                            <div class="menu-left"><i class="fa fa-cog"></i> Settings</div>
                        </a>
                    </li>
                    <li>
                        <a href="#">
                            <div class="menu-left"><i class="fa fa-question-circle-o"></i> Help & Support</div>
                        </a>
                    </li>
                    <li>
                        <a href="<c:url value='/j_spring_security_logout'/>">
                            <div class="menu-left"><i class="fa fa-power-off"></i> Logout</div>
                        </a>
                    </li>
                </ul>
            </nav>

            <!-- Refer & Earn Box -->
            <div class="refer-card">
                <h4>Refer & Earn</h4>
                <p>Invite your friends and earn Premium membership!</p>
                <a href="#" class="btn-refer">Refer Now</a>
                <svg class="gift-img" viewBox="0 0 64 64" fill="none" xmlns="http://www.w3.org/2000/svg">
                    <rect x="8" y="24" width="48" height="32" rx="4" fill="#d21a5a" fill-opacity="0.15"/>
                    <rect x="6" y="16" width="52" height="12" rx="2" fill="#d21a5a"/>
                    <rect x="28" y="16" width="8" height="40" fill="#ffffff" fill-opacity="0.6"/>
                    <path d="M28 16C28 16 20 6 12 10C6 13 14 20 28 16Z" fill="#d21a5a"/>
                    <path d="M36 16C36 16 44 6 52 10C58 13 50 20 36 16Z" fill="#d21a5a"/>
                </svg>
            </div>
        </aside>

        <!-- Column 2: Center Main Content -->
        <main class="dash-main" id="mainDivForUser">

            <!-- 5 Stat Cards Bar -->
            <div class="stats-grid">
                <div class="stat-card">
                    <div class="icon-circle"><i class="fa fa-users"></i></div>
                    <div class="stat-info">
                        <h3><fmt:formatNumber value="${latestMatchCount != null ? latestMatchCount : 30519}"/></h3>
                        <p>Total Matches</p>
                    </div>
                </div>

                <div class="stat-card">
                    <div class="icon-circle"><i class="fa fa-shield"></i></div>
                    <div class="stat-info">
                        <h3><fmt:formatNumber value="${latestUpgraded != null ? latestUpgraded : 2458}"/></h3>
                        <p>Verified Profiles</p>
                    </div>
                </div>

                <div class="stat-card">
                    <div class="icon-circle"><i class="fa fa-eye"></i></div>
                    <div class="stat-info">
                        <h3><fmt:formatNumber value="${seenyet != null ? seenyet : 792}"/></h3>
                        <p>Viewed My Profile</p>
                    </div>
                </div>

                <div class="stat-card">
                    <div class="icon-circle"><i class="fa fa-heart"></i></div>
                    <div class="stat-info">
                        <h3><fmt:formatNumber value="${sortlisted != null ? sortlisted : 305}"/></h3>
                        <p>Shortlisted Me</p>
                    </div>
                </div>

                <div class="stat-card">
                    <div class="icon-circle"><i class="fa fa-star"></i></div>
                    <div class="stat-info">
                        <h3><fmt:formatNumber value="${notseenyet != null ? notseenyet : 4}"/></h3>
                        <p>Profile Visits Today</p>
                    </div>
                </div>
            </div>

            <!-- "Find Your Perfect Match" Filter Card -->
            <div class="filter-card">
                <h3>Find Your Perfect Match</h3>
                <div class="filter-row">
                    <div class="filter-group">
                        <label>Age</label>
                        <div style="display: flex; align-items: center; gap: 6px;">
                            <select style="min-width: 65px;">
                                <option>22</option>
                                <option>18</option>
                                <option>20</option>
                                <option>25</option>
                            </select>
                            <span style="font-size: 11px; color: #888;">to</span>
                            <select style="min-width: 65px;">
                                <option>32</option>
                                <option>28</option>
                                <option>30</option>
                                <option>35</option>
                            </select>
                        </div>
                    </div>

                    <div class="filter-group">
                        <label>Height</label>
                        <select style="min-width: 100px;">
                            <option>Any</option>
                            <option>5'0" to 5'5"</option>
                            <option>5'6" to 6'0"</option>
                        </select>
                    </div>

                    <div class="filter-group">
                        <label>Religion</label>
                        <select style="min-width: 100px;">
                            <option>Any</option>
                            <c:forEach var="religion" items="${religions}">
                                <option value="${religion.religion_id}">${religion.religion_name}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="filter-group">
                        <label>Caste</label>
                        <select style="min-width: 100px;">
                            <option>Any</option>
                            <option>Maratha</option>
                            <option>Baniya</option>
                            <option>Brahmin</option>
                        </select>
                    </div>

                    <div class="filter-group" style="flex: 1; min-width: 140px;">
                        <label>Location</label>
                        <input type="text" placeholder="Enter city or district">
                    </div>
                </div>

                <div class="filter-actions">
                    <button class="btn-more-filters">
                        <i class="fa fa-sliders"></i> More Filters
                    </button>
                    <div>
                        <button class="btn-search-submit">Search</button>
                        <a href="#" class="btn-clear-all">Clear All</a>
                    </div>
                </div>
            </div>

            <!-- Profile Cards List -->
            <div class="profile-cards-container">

                <c:forEach var="profileShortList" items="${sortlistedProfileList}" varStatus="theCount">
                    <script>shortListUsersId.push("${profileShortList.sortlistedUserModel.userId}");</script>
                </c:forEach>

                <!-- Dynamic Profile Loop -->
                <c:forEach var="profile" items="${LatestMatch}" begin="0" end="4" varStatus="theCount">
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

                <!-- Fallback / Static Demo Profile Cards matching target mockup exactly if database is empty -->
                <c:if test="${empty LatestMatch}">
                    <!-- Card 1: Trupti Popat Sawant -->
                    <div class="profile-card">
                        <div class="profile-photos">
                            <div class="main-photo-wrapper">
                                <span class="verified-badge"><i class="fa fa-check"></i> Verified</span>
                                <img src="${pageContext.request.contextPath}/resources/UserImages/userImage.png" alt="Profile">
                            </div>
                            <div class="thumb-photos">
                                <img src="${pageContext.request.contextPath}/resources/UserImages/userImage.png" alt="Thumb">
                                <img src="${pageContext.request.contextPath}/resources/UserImages/userImage.png" alt="Thumb">
                            </div>
                        </div>
                        <div class="profile-details">
                            <h3 class="profile-name">Trupti Popat Sawant <span class="online-dot"></span></h3>
                            <div class="profile-meta-line">27 yrs, 5'4" &bull; Palus, Sangli</div>
                            <div class="profile-meta-line">BE (Computer) &bull; PRJ Industries Ltd, Pune</div>
                            <div class="profile-meta-line">Maratha &bull; Kunbi</div>
                            <p class="profile-bio">A simple and homely girl looking for a caring and understanding life partner.</p>
                        </div>
                        <div class="profile-actions-wrapper">
                            <div class="profile-status-bar">
                                <span>Last Active: 2 Hours ago</span>
                                <span class="tag-premium">Premium</span>
                            </div>
                            <div class="card-action-btns">
                                <a href="#" class="btn-shortlist-outline"><i class="fa fa-heart-o"></i> Shortlist</a>
                                <a href="#" class="btn-view-profile-solid">View Profile</a>
                                <a href="#" class="btn-circle-icon"><i class="fa fa-comment-o"></i></a>
                                <a href="#" class="btn-circle-icon"><i class="fa fa-envelope-o"></i></a>
                            </div>
                        </div>
                    </div>

                    <!-- Card 2: Ashwini Arun Pawar -->
                    <div class="profile-card">
                        <div class="profile-photos">
                            <div class="main-photo-wrapper">
                                <span class="verified-badge"><i class="fa fa-check"></i> Verified</span>
                                <img src="${pageContext.request.contextPath}/resources/UserImages/userImage.png" alt="Profile">
                            </div>
                            <div class="thumb-photos">
                                <img src="${pageContext.request.contextPath}/resources/UserImages/userImage.png" alt="Thumb">
                                <img src="${pageContext.request.contextPath}/resources/UserImages/userImage.png" alt="Thumb">
                            </div>
                        </div>
                        <div class="profile-details">
                            <h3 class="profile-name">Ashwini Arun Pawar <span class="online-dot"></span></h3>
                            <div class="profile-meta-line">27 yrs, 5'5" &bull; Satara, Maharashtra</div>
                            <div class="profile-meta-line">M.Sc (Microbiology) &bull; Health Care Professional</div>
                            <div class="profile-meta-line">Maratha &bull; 96 Kuli</div>
                            <p class="profile-bio">Looking for a well-educated and supportive partner for a happy life together.</p>
                        </div>
                        <div class="profile-actions-wrapper">
                            <div class="profile-status-bar">
                                <span>Last Active: 1 Day ago</span>
                            </div>
                            <div class="card-action-btns">
                                <a href="#" class="btn-shortlist-outline"><i class="fa fa-heart-o"></i> Shortlist</a>
                                <a href="#" class="btn-view-profile-solid">View Profile</a>
                                <a href="#" class="btn-circle-icon"><i class="fa fa-comment-o"></i></a>
                                <a href="#" class="btn-circle-icon"><i class="fa fa-envelope-o"></i></a>
                            </div>
                        </div>
                    </div>

                    <!-- Card 3: Komal Chandar Patil -->
                    <div class="profile-card">
                        <div class="profile-photos">
                            <div class="main-photo-wrapper">
                                <span class="verified-badge"><i class="fa fa-check"></i> Verified</span>
                                <img src="${pageContext.request.contextPath}/resources/UserImages/userImage.png" alt="Profile">
                            </div>
                            <div class="thumb-photos">
                                <img src="${pageContext.request.contextPath}/resources/UserImages/userImage.png" alt="Thumb">
                                <img src="${pageContext.request.contextPath}/resources/UserImages/userImage.png" alt="Thumb">
                            </div>
                        </div>
                        <div class="profile-details">
                            <h3 class="profile-name">Komal Chandar Patil <span class="online-dot"></span></h3>
                            <div class="profile-meta-line">30 yrs, 5'3" &bull; Ratnagiri, Maharashtra</div>
                            <div class="profile-meta-line">MCA &bull; UI/UX Designer</div>
                            <div class="profile-meta-line">Maratha &bull; 96 Kuli</div>
                            <p class="profile-bio">Believe in simple living and high thinking. Looking for a genuine life partner.</p>
                        </div>
                        <div class="profile-actions-wrapper">
                            <div class="profile-status-bar">
                                <span>Last Active: 3 Hours ago</span>
                            </div>
                            <div class="card-action-btns">
                                <a href="#" class="btn-shortlist-outline"><i class="fa fa-heart-o"></i> Shortlist</a>
                                <a href="#" class="btn-view-profile-solid">View Profile</a>
                                <a href="#" class="btn-circle-icon"><i class="fa fa-comment-o"></i></a>
                                <a href="#" class="btn-circle-icon"><i class="fa fa-envelope-o"></i></a>
                            </div>
                        </div>
                    </div>
                </c:if>
            </div>

            <!-- Floating Load More Button -->
            <button class="btn-load-more-floating" onclick="loadLatestMatches();">
                <i class="fa fa-refresh"></i> Load More Profiles
            </button>

        </main>

        <!-- Column 3: Right Sidebar -->
        <aside class="dash-rightbar">

            <!-- "Who's Online" Widget -->
            <div class="widget-card">
                <div class="widget-header">
                    <h4>Who's Online</h4>
                    <a href="#" class="view-all">View All</a>
                </div>
                <div class="online-users-list">
                    <c:forEach var="onlineUser" items="${onlineProfiles}" begin="0" end="4">
                        <div class="online-user-item">
                            <div class="online-user-info">
                                <c:set var="onlineImg" value="userImage.png" />
                                <c:if test="${not empty onlineUser.imageObject}">
                                    <c:set var="onlineImg" value="${onlineUser.imageObject[0]}" />
                                </c:if>
                                <img src="${pageContext.request.contextPath}/resources/UserImages/${onlineImg}" onerror="this.onerror=null; this.src='${pageContext.request.contextPath}/resources/UserImages/userImage.png';" alt="User">
                                <div>
                                    <p class="online-user-name">${onlineUser.name}</p>
                                    <p class="online-user-location">${onlineUser.location}</p>
                                </div>
                            </div>
                            <span class="green-status-dot"></span>
                        </div>
                    </c:forEach>

                    <c:if test="${empty onlineProfiles}">
                        <div class="online-user-item">
                            <div class="online-user-info">
                                <img src="${pageContext.request.contextPath}/resources/UserImages/userImage.png" alt="User">
                                <div>
                                    <p class="online-user-name">Rutuja Deshmukh</p>
                                    <p class="online-user-location">Pune</p>
                                </div>
                            </div>
                            <span class="green-status-dot"></span>
                        </div>
                        <div class="online-user-item">
                            <div class="online-user-info">
                                <img src="${pageContext.request.contextPath}/resources/UserImages/userImage.png" alt="User">
                                <div>
                                    <p class="online-user-name">Sneha Jadhav</p>
                                    <p class="online-user-location">Nashik</p>
                                </div>
                            </div>
                            <span class="green-status-dot"></span>
                        </div>
                        <div class="online-user-item">
                            <div class="online-user-info">
                                <img src="${pageContext.request.contextPath}/resources/UserImages/userImage.png" alt="User">
                                <div>
                                    <p class="online-user-name">Pooja Kulkarni</p>
                                    <p class="online-user-location">Kolhapur</p>
                                </div>
                            </div>
                            <span class="green-status-dot"></span>
                        </div>
                        <div class="online-user-item">
                            <div class="online-user-info">
                                <img src="${pageContext.request.contextPath}/resources/UserImages/userImage.png" alt="User">
                                <div>
                                    <p class="online-user-name">Mayuri Shinde</p>
                                    <p class="online-user-location">Satara</p>
                                </div>
                            </div>
                            <span class="green-status-dot"></span>
                        </div>
                        <div class="online-user-item">
                            <div class="online-user-info">
                                <img src="${pageContext.request.contextPath}/resources/UserImages/userImage.png" alt="User">
                                <div>
                                    <p class="online-user-name">Anjali Patil</p>
                                    <p class="online-user-location">Sangli</p>
                                </div>
                            </div>
                            <span class="green-status-dot"></span>
                        </div>
                    </c:if>
                </div>
            </div>

            <!-- "Recent Activity" Widget -->
            <div class="widget-card">
                <div class="widget-header">
                    <h4>Recent Activity</h4>
                    <a href="#" class="view-all">View All</a>
                </div>
                <div class="activity-list">
                    <div class="activity-item">
                        <div class="activity-icon-circle"><i class="fa fa-user-plus"></i></div>
                        <div class="activity-content">
                            <p><strong>You received interest</strong> from Rahul K.</p>
                            <span>10 min ago</span>
                        </div>
                    </div>
                    <div class="activity-item">
                        <div class="activity-icon-circle"><i class="fa fa-eye"></i></div>
                        <div class="activity-content">
                            <p><strong>Priya S. viewed your profile</strong></p>
                            <span>1 hour ago</span>
                        </div>
                    </div>
                    <div class="activity-item">
                        <div class="activity-icon-circle"><i class="fa fa-star"></i></div>
                        <div class="activity-content">
                            <p><strong>Your profile was shortlisted</strong> by 3 members</p>
                            <span>2 hours ago</span>
                        </div>
                    </div>
                    <div class="activity-item">
                        <div class="activity-icon-circle"><i class="fa fa-check-circle"></i></div>
                        <div class="activity-content">
                            <p><strong>Your profile is 100% complete</strong></p>
                            <span>Yesterday</span>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Marathi Banner Promo Card -->
            <div class="promo-marathi-card">
                <p class="promo-marathi-text">
                    विश्वासाचे नाते,<br>
                    आयुष्यभर साथ
                </p>
                <svg style="position: absolute; right: -10px; bottom: -10px; opacity: 0.25; width: 120px; height: 120px;" viewBox="0 0 100 100" fill="none">
                    <circle cx="50" cy="50" r="45" stroke="#d21a5a" stroke-width="2"/>
                    <circle cx="50" cy="50" r="35" stroke="#d21a5a" stroke-width="1" stroke-dasharray="4 4"/>
                    <path d="M50 10 L50 90 M10 50 L90 50" stroke="#d21a5a" stroke-width="1"/>
                </svg>
            </div>

        </aside>
    </div>

    <!-- 3. Bottom Footer -->
    <footer class="dash-footer">
        <div>&copy; 2025 Snehbandhan. All rights reserved.</div>
        <div style="display: flex; gap: 16px;">
            <a href="#">Privacy Policy</a> &bull; 
            <a href="#">Terms & Conditions</a> &bull; 
            <a href="#">Help Center</a>
        </div>
    </footer>

    <!-- Hidden Fields & Modals Preserved for Core Application Scripts -->
    <input type="hidden" id="loguserid" name="loguserid" value="${loguser}">
    <input type="hidden" id="firstName" name="firstName" value="${firstName}"> 
    <input type="hidden" id="hiddenExpDate" name="hiddenExpDate" value="${expDate}">  
    <input type="hidden" id="hiddelUserId" name="hiddelUserId" value="${delUserId}">

    <!-- Shortlist Modal -->
    <div id="myModal" class="modal fade" role="dialog">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <font color="green" size="3;">You have shortlisted successfully...</font>
                    <h4 class="modal-title" id="shortName"></h4>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label>Send Message to Shortlisted Profile</label>
                        <textarea class="form-control" rows="3" id="ShortlistMSG" maxlength="104"></textarea>
                    </div>
                    <button type="button" class="btn btn-primary" onclick="ShortlistSendMSG();">Send Message</button>
                </div>
            </div>
        </div>
    </div>

    <!-- Image Slider Modal -->
    <div id="imgSlider" class="modal fade" role="dialog">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-body">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <div id="customSlider"></div>
                </div>
            </div>
        </div>
    </div>

</body>
</html>
