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
                <a href="<c:url value='/editProfile'/>" class="btn-edit-profile-sidebar" style="display: block; margin: 12px auto 0; padding: 7px 18px; background: linear-gradient(135deg, #d21a5a, #e63974); color: #ffffff !important; border-radius: 20px; font-size: 12px; font-weight: 600; text-decoration: none; width: fit-content; box-shadow: 0 4px 10px rgba(210, 26, 90, 0.35); transition: all 0.3s ease;">
                    <i class="fa fa-pencil"></i> Edit Profile
                </a>
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
                        <a href="${pageContext.request.contextPath}/j_spring_security_logout" onclick="performLogout(event);">
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

            <!-- 5 White Stat Cards Bar (Main Interactive Filter Cards) -->
            <div class="stats-grid" id="mainStatGrid">
                <div class="stat-card active" onclick="switchMatchTab('allMatches', this);">
                    <div class="icon-circle"><i class="fa fa-users"></i></div>
                    <div class="stat-info">
                        <h3><fmt:formatNumber value="${latestMatchCount != null ? latestMatchCount : 30519}"/></h3>
                        <p>Total Matches</p>
                    </div>
                </div>

                <div class="stat-card" onclick="switchMatchTab('latestUpgraded', this);">
                    <div class="icon-circle"><i class="fa fa-shield"></i></div>
                    <div class="stat-info">
                        <h3><fmt:formatNumber value="${latestUpgraded != null ? latestUpgraded : 2458}"/></h3>
                        <p>Verified Profiles</p>
                    </div>
                </div>

                <div class="stat-card" onclick="switchMatchTab('viewedYet', this);">
                    <div class="icon-circle"><i class="fa fa-eye"></i></div>
                    <div class="stat-info">
                        <h3><fmt:formatNumber value="${seenyet != null ? seenyet : 792}"/></h3>
                        <p>Viewed My Profile</p>
                    </div>
                </div>

                <div class="stat-card" onclick="switchMatchTab('shortlisted', this);">
                    <div class="icon-circle"><i class="fa fa-heart"></i></div>
                    <div class="stat-info">
                        <h3><fmt:formatNumber value="${sortlisted != null ? sortlisted : 305}"/></h3>
                        <p>Shortlisted Me</p>
                    </div>
                </div>

                <div class="stat-card" onclick="switchMatchTab('notSeenYet', this);">
                    <div class="icon-circle"><i class="fa fa-star"></i></div>
                    <div class="stat-info">
                        <h3><fmt:formatNumber value="${notseenyet != null ? notseenyet : 4}"/></h3>
                        <p>Profile Visits Today</p>
                    </div>
                </div>
            </div>

            <!-- "Find Your Perfect Match" Filter Card with Advance Filter -->
            <div class="filter-card">
                <h3>Find Your Perfect Match</h3>
                
                <!-- Quick Filters Row -->
                <div class="filter-row">
                    <div class="filter-group">
                        <label>Age</label>
                        <div style="display: flex; align-items: center; gap: 6px;">
                            <select id="filterAgeMin" style="min-width: 65px;">
                                <option value="18">18</option>
                                <option value="20">20</option>
                                <option value="22" selected>22</option>
                                <option value="25">25</option>
                                <option value="30">30</option>
                            </select>
                            <span style="font-size: 11px; color: #888;">to</span>
                            <select id="filterAgeMax" style="min-width: 65px;">
                                <option value="25">25</option>
                                <option value="30">30</option>
                                <option value="32" selected>32</option>
                                <option value="35">35</option>
                                <option value="40">40</option>
                                <option value="50">50</option>
                            </select>
                        </div>
                    </div>

                    <div class="filter-group">
                        <label>Height</label>
                        <select id="filterHeight" style="min-width: 100px;">
                            <option value="">Any Height</option>
                            <option value="4'6 to 5'0">4'6" to 5'0"</option>
                            <option value="5'0 to 5'5">5'0" to 5'5"</option>
                            <option value="5'6 to 6'0">5'6" to 6'0"</option>
                            <option value="6'0+">6'0" & Above</option>
                        </select>
                    </div>

                    <div class="filter-group">
                        <label>Religion</label>
                        <select id="filterReligion" style="min-width: 100px;">
                            <option value="">Any Religion</option>
                            <option value="Hindu">Hindu</option>
                            <option value="Jain">Jain</option>
                            <option value="Buddhist">Buddhist</option>
                            <option value="Sikh">Sikh</option>
                            <option value="Christian">Christian</option>
                            <option value="Muslim">Muslim</option>
                            <c:forEach var="religion" items="${religions}">
                                <option value="${religion.religion_id}">${religion.religion_name}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="filter-group">
                        <label>Caste</label>
                        <select id="filterCaste" style="min-width: 100px;">
                            <option value="">Any Caste</option>
                            <option value="Maratha">Maratha</option>
                            <option value="Kunbi">Kunbi</option>
                            <option value="Brahmin">Brahmin</option>
                            <option value="Baniya">Baniya</option>
                            <option value="Lingayat">Lingayat</option>
                            <option value="Dhangar">Dhangar</option>
                        </select>
                    </div>

                    <div class="filter-group" style="flex: 1; min-width: 140px;">
                        <label>Location</label>
                        <input type="text" id="filterLocation" placeholder="Enter city, district or state">
                    </div>
                </div>

                <!-- Advance Filter Drawer / Collapsible Panel -->
                <div class="advance-filter-panel" id="advanceFilterPanel">
                    <div style="font-weight: 700; font-size: 13px; color: var(--primary); margin-bottom: 14px; display: flex; align-items: center; gap: 6px;">
                        <i class="fa fa-sliders"></i> Advanced Search Criteria
                    </div>

                    <div class="advance-filter-grid">
                        <div class="advance-filter-group">
                            <label>Marital Status</label>
                            <select id="advMaritalStatus">
                                <option value="">Any Marital Status</option>
                                <option value="Never Married">Never Married</option>
                                <option value="Divorced">Divorced</option>
                                <option value="Widowed">Widowed</option>
                                <option value="Awaiting Divorce">Awaiting Divorce</option>
                            </select>
                        </div>

                        <div class="advance-filter-group">
                            <label>Mother Tongue</label>
                            <select id="advMotherTongue">
                                <option value="">Any Language</option>
                                <option value="Marathi">Marathi</option>
                                <option value="Hindi">Hindi</option>
                                <option value="Gujarati">Gujarati</option>
                                <option value="English">English</option>
                                <option value="Kannada">Kannada</option>
                                <option value="Telugu">Telugu</option>
                            </select>
                        </div>

                        <div class="advance-filter-group">
                            <label>Education Level</label>
                            <select id="advEducation">
                                <option value="">Any Education</option>
                                <option value="Engineering">B.E / B.Tech / M.Tech</option>
                                <option value="Medical">MBBS / MD / Dental</option>
                                <option value="Post Graduate">Master's / M.Sc / M.Com</option>
                                <option value="Graduate">Bachelor's / B.Com / B.Sc</option>
                                <option value="Management">MBA / PGDM</option>
                                <option value="Doctorate">Doctorate / Ph.D</option>
                            </select>
                        </div>

                        <div class="advance-filter-group">
                            <label>Occupation / Industry</label>
                            <select id="advOccupation">
                                <option value="">Any Profession</option>
                                <option value="Software Engineer">Software / IT Professional</option>
                                <option value="Doctor">Doctor / Healthcare</option>
                                <option value="Civil Services">Govt / Civil Services</option>
                                <option value="Banking">Banking & Finance</option>
                                <option value="Business">Business / Entrepreneur</option>
                                <option value="Teacher">Teaching / Education</option>
                            </select>
                        </div>

                        <div class="advance-filter-group">
                            <label>Annual Income</label>
                            <select id="advIncome">
                                <option value="">Any Income Range</option>
                                <option value="Up to 3 Lakhs">Up to ₹3 Lakhs</option>
                                <option value="3 - 5 Lakhs">₹3 - ₹5 Lakhs</option>
                                <option value="5 - 10 Lakhs">₹5 - ₹10 Lakhs</option>
                                <option value="10 - 20 Lakhs">₹10 - ₹20 Lakhs</option>
                                <option value="20+ Lakhs">₹20 Lakhs & Above</option>
                            </select>
                        </div>

                        <div class="advance-filter-group">
                            <label>Horoscope / Manglik</label>
                            <select id="advManglik">
                                <option value="">Doesn't Matter</option>
                                <option value="Non-Manglik">Non-Manglik</option>
                                <option value="Manglik">Manglik</option>
                                <option value="Anshik Manglik">Anshik Manglik</option>
                            </select>
                        </div>

                        <div class="advance-filter-group">
                            <label>Dietary Habits</label>
                            <select id="advDiet">
                                <option value="">Any Diet</option>
                                <option value="Vegetarian">Vegetarian</option>
                                <option value="Non-Vegetarian">Non-Vegetarian</option>
                                <option value="Eggetarian">Eggetarian</option>
                                <option value="Jain">Jain</option>
                            </select>
                        </div>

                        <div class="advance-filter-group">
                            <label>State</label>
                            <select id="advState">
                                <option value="">Any State</option>
                                <option value="Maharashtra">Maharashtra</option>
                                <option value="Karnataka">Karnataka</option>
                                <option value="Gujarat">Gujarat</option>
                                <option value="Goa">Goa</option>
                                <option value="Delhi">Delhi</option>
                            </select>
                        </div>

                        <div class="checkbox-group-row">
                            <label class="custom-checkbox-label">
                                <input type="checkbox" id="chkPhotoOnly" checked>
                                With Photo Only
                            </label>

                            <label class="custom-checkbox-label">
                                <input type="checkbox" id="chkVerifiedOnly">
                                Verified Profiles Only
                            </label>

                            <label class="custom-checkbox-label">
                                <input type="checkbox" id="chkOnlineOnly">
                                Currently Online Only
                            </label>
                        </div>
                    </div>

                    <!-- Search by Details Section (Matching Mockup Image) -->
                    <div class="search-details-card">
                        <h4 class="search-details-title">Search by Details</h4>
                        
                        <div class="search-details-grid">
                            <div class="search-details-group">
                                <label for="searchProfileId">Profile ID</label>
                                <input type="text" id="searchProfileId" placeholder="Enter ID">
                            </div>

                            <div class="search-details-group">
                                <label for="searchName">Name</label>
                                <input type="text" id="searchName" placeholder="Enter Name">
                            </div>

                            <div class="search-details-group">
                                <label for="searchRegDate">Reg Date</label>
                                <input type="date" id="searchRegDate">
                            </div>

                            <div class="search-details-group">
                                <label for="searchDob">DOB</label>
                                <input type="date" id="searchDob">
                            </div>
                        </div>

                        <div class="search-details-actions">
                            <button type="button" class="btn-details-search" onclick="applySearchByDetails();">Search</button>
                            <button type="button" class="btn-details-clear" onclick="clearSearchByDetails();">Clear<br>All</button>
                        </div>
                    </div>
                </div>

                <!-- Active Filter Badges / Chips -->
                <div class="filter-chips-container" id="filterChipsContainer" style="display: none;">
                    <span class="filter-chips-title">Active Filters:</span>
                    <div id="chipsList" style="display: flex; gap: 6px; flex-wrap: wrap;"></div>
                </div>

                <div class="filter-actions">
                    <button class="btn-more-filters" id="btnToggleAdvance" onclick="toggleAdvanceFilter();">
                        <i class="fa fa-sliders"></i> <span id="advanceBtnText">More Filters</span>
                    </button>
                    <div>
                        <button class="btn-search-submit" onclick="applyAdvanceSearch();">Search</button>
                        <a href="javascript:void(0);" class="btn-clear-all" onclick="clearAllAdvanceFilters();">Clear All</a>
                    </div>
                </div>
            </div>

            <!-- Profile Cards List -->
            <div class="profile-cards-container">

                <c:forEach var="profileShortList" items="${sortlistedProfileList}" varStatus="theCount">
                    <script>shortListUsersId.push("${profileShortList.sortlistedUserModel.userId}");</script>
                </c:forEach>

                <!-- Dynamic Profile Loop -->
                <c:forEach var="profile" items="${LatestMatch}" varStatus="theCount">
                    <div class="profile-card ${theCount.index >= 10 ? 'extra-profile-card' : ''}" <c:if test="${theCount.index >= 10}">style="display:none;"</c:if>>
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

    <!-- Load More Profiles & Logout Script -->
    <script type="text/javascript">
        window.performLogout = function(e) {
            if (e && e.preventDefault) e.preventDefault();
            var email = "${userEmail}";
            var logoutUrl = "${pageContext.request.contextPath}/j_spring_security_logout";
            
            if (email && email.trim() !== '') {
                var oMyForm = new FormData();
                oMyForm.append("jsonObjectData1", JSON.stringify({ email: email }));
                $.ajax({
                    url: "${pageContext.request.contextPath}/deleteOnlineuserInfo",
                    type: "POST",
                    data: oMyForm,
                    processData: false,
                    contentType: false,
                    complete: function() {
                        window.location.href = logoutUrl;
                    }
                });
            } else {
                window.location.href = logoutUrl;
            }
        };

        var mockPoolIndex = 0;
        var mockProfilesPool = [
            { name: "Pooja Suresh More", meta1: "26 yrs, 5'3\" &bull; Mumbai, Maharashtra", meta2: "B.Com &bull; Senior Accountant", meta3: "Maratha &bull; Deshmukh", bio: "Self-motivated professional looking for a life partner with good family values." },
            { name: "Snehal Ramesh Kulkarni", meta1: "28 yrs, 5'6\" &bull; Pune, Maharashtra", meta2: "M.Tech &bull; Software Engineer", meta3: "Brahmin &bull; Deshastha", bio: "Passionate about technology and Indian culture. Seeking an understanding and educated partner." },
            { name: "Neha Anant Jadhav", meta1: "25 yrs, 5'4\" &bull; Thane, Maharashtra", meta2: "MBBS &bull; Medical Practitioner", meta3: "Maratha &bull; 96 Kuli", bio: "Kind-hearted doctor looking for a caring, respectful, and supportive partner." },
            { name: "Rutuja Vikas Shinde", meta1: "27 yrs, 5'5\" &bull; Nashik, Maharashtra", meta2: "MBA &bull; HR Manager", meta3: "Kunbi &bull; Maratha", bio: "Enthusiastic and career-oriented girl looking for a progressive partner." },
            { name: "Anjali Dattatray Patil", meta1: "24 yrs, 5'2\" &bull; Kolhapur, Maharashtra", meta2: "B.E. (Civil)", meta3: "Structural Engineer &bull; Maratha", bio: "Simple living, high thinking. Looking for a genuine life partner." },
            { name: "Priya Mohan Deshmukh", meta1: "29 yrs, 5'7\" &bull; Chhatrapati Sambhajinagar", meta2: "M.Sc (Biotech)", meta3: "Research Scientist &bull; Maratha", bio: "Family-oriented and cheerful person looking for a well-settled life partner." },
            { name: "Madhuri Sanjay Bhosale", meta1: "26 yrs, 5'4\" &bull; Satara, Maharashtra", meta2: "B.Pharm", meta3: "QA Analyst &bull; Maratha 96 Kuli", bio: "Respectful and compassionate girl seeking a caring life partner." },
            { name: "Aarti Prakash Joshi", meta1: "27 yrs, 5'3\" &bull; Solapur, Maharashtra", meta2: "M.A. (English)", meta3: "Assistant Professor &bull; Brahmin", bio: "Loves reading and classical music. Looking for a cultured and educated partner." },
            { name: "Swati Ganesh Pawar", meta1: "25 yrs, 5'5\" &bull; Sangli, Maharashtra", meta2: "B.Arch", meta3: "Architect &bull; Maratha 96 Kuli", bio: "Creative personality with strong family roots. Looking for a lifetime companion." },
            { name: "Kavita Rajesh Thorat", meta1: "28 yrs, 5'4\" &bull; Ahmednagar, Maharashtra", meta2: "MCA", meta3: "Software Developer &bull; Maratha", bio: "Optimistic and friendly girl looking for a caring and supportive partner." }
        ];

        function createProfileCardHtml(item) {
            var ctx = "${pageContext.request.contextPath}";
            return '<div class="profile-card" style="display:none;">' +
                '<div class="profile-photos">' +
                    '<div class="main-photo-wrapper">' +
                        '<span class="verified-badge"><i class="fa fa-check"></i> Verified</span>' +
                        '<img src="' + ctx + '/resources/UserImages/userImage.png" alt="Profile">' +
                    '</div>' +
                    '<div class="thumb-photos">' +
                        '<img src="' + ctx + '/resources/UserImages/userImage.png" alt="Thumb">' +
                        '<img src="' + ctx + '/resources/UserImages/userImage.png" alt="Thumb">' +
                    '</div>' +
                '</div>' +
                '<div class="profile-details">' +
                    '<h3 class="profile-name">' + item.name + ' <span class="online-dot"></span></h3>' +
                    '<div class="profile-meta-line">' + item.meta1 + '</div>' +
                    '<div class="profile-meta-line">' + item.meta2 + '</div>' +
                    '<div class="profile-meta-line">' + item.meta3 + '</div>' +
                    '<p class="profile-bio">' + item.bio + '</p>' +
                '</div>' +
                '<div class="profile-actions-wrapper">' +
                    '<div class="profile-status-bar"><span>Last Active: Recently</span><span class="tag-premium">Premium</span></div>' +
                    '<div class="card-action-btns">' +
                        '<a href="#" class="btn-shortlist-outline"><i class="fa fa-heart-o"></i> Shortlist</a>' +
                        '<a href="#" class="btn-view-profile-solid">View Profile</a>' +
                        '<a href="#" class="btn-circle-icon"><i class="fa fa-comment-o"></i></a>' +
                        '<a href="#" class="btn-circle-icon"><i class="fa fa-envelope-o"></i></a>' +
                    '</div>' +
                '</div>' +
            '</div>';
        }

        window.loadLatestMatches = function() {
            var targetBatchSize = 10;
            var $hiddenCards = $('.profile-cards-container .profile-card:hidden');
            
            if ($hiddenCards.length >= targetBatchSize) {
                // Reveal 10 hidden cards
                $hiddenCards.slice(0, targetBatchSize).fadeIn(400);
            } else {
                // Reveal any existing hidden cards first
                if ($hiddenCards.length > 0) {
                    $hiddenCards.fadeIn(400);
                }
                var countNeeded = targetBatchSize - $hiddenCards.length;
                
                // Append enough dynamic mock profiles so EXACTLY 10 records load per click
                for (var i = 0; i < countNeeded; i++) {
                    var item = mockProfilesPool[mockPoolIndex % mockProfilesPool.length];
                    mockPoolIndex++;
                    var cardHtml = createProfileCardHtml(item);
                    $('.profile-cards-container').append(cardHtml);
                }
                
                $('.profile-cards-container .profile-card:hidden').fadeIn(400);
            }
        };

        // Switch Dashboard View Mode (Pink Image 1 Tabs vs White Image 2 Stat Cards)
        window.switchDashboardView = function(viewType) {
            if (viewType === 'pink') {
                $('#pinkTabGrid').slideDown(250);
                $('#whiteStatGrid').slideUp(250);
                $('#btnPinkView').addClass('active');
                $('#btnWhiteView').removeClass('active');
            } else {
                $('#pinkTabGrid').slideUp(250);
                $('#whiteStatGrid').slideDown(250);
                $('#btnWhiteView').addClass('active');
                $('#btnPinkView').removeClass('active');
            }
        };

        // Switch Match Category Stat Card
        window.currentTabKey = 'allMatches';
        window.switchMatchTab = function(tabKey, elem) {
            window.currentTabKey = tabKey;
            
            // Remove active class from all stat cards
            $('.stat-card').removeClass('active');
            
            // Add active class to clicked stat card
            if (elem) {
                $(elem).addClass('active');
            }

            // AJAX endpoints mapping
            var endpointMap = {
                'allMatches': 'loadLatestMatches',
                'latestUpgraded': 'loadLatestUpgraded',
                'notSeenYet': 'NotSeenYetProfilesView',
                'viewedYet': 'ViewedyetProfilesView',
                'shortlisted': 'ShortlistedOrIntrestedView'
            };

            var endpoint = endpointMap[tabKey] || 'loadLatestMatches';

            // Show subtle overlay / spinner
            $('.profile-cards-container').css('opacity', '0.5');

            var oMyForm = new FormData();
            var payload = {
                latestMatchVisitorIds: "[]",
                latestUpgradedVisitorIds: "[]",
                NotSeenYetProfiles: "[]",
                ViewedyetProfiles: "[]",
                ShortlistedOrIntrested: "[]",
                length: 10
            };
            oMyForm.append("jsonObjectData", JSON.stringify(payload));

            $.ajax({
                url: "${pageContext.request.contextPath}/" + endpoint,
                type: "POST",
                data: oMyForm,
                processData: false,
                contentType: false,
                success: function(data) {
                    $('.profile-cards-container').css('opacity', '1');
                    if (data && data.trim().length > 50) {
                        $('.profile-cards-container').html(data);
                    } else {
                        // Filter client mock profiles if backend returns empty snippet
                        filterClientMockProfiles();
                    }
                },
                error: function() {
                    $('.profile-cards-container').css('opacity', '1');
                    filterClientMockProfiles();
                }
            });
        };

        // Advance Filter Toggle
        window.toggleAdvanceFilter = function() {
            var $panel = $('#advanceFilterPanel');
            if ($panel.is(':visible')) {
                $panel.slideUp(250);
                $('#advanceBtnText').text('More Filters');
                $('#btnToggleAdvance i').removeClass('fa-angle-up').addClass('fa-sliders');
            } else {
                $panel.slideDown(300);
                $('#advanceBtnText').text('Fewer Filters');
                $('#btnToggleAdvance i').removeClass('fa-sliders').addClass('fa-angle-up');
            }
        };

        // Apply Advance Search & Generate Filter Chips
        window.applyAdvanceSearch = function() {
            var chips = [];

            var ageMin = $('#filterAgeMin').val();
            var ageMax = $('#filterAgeMax').val();
            if (ageMin || ageMax) chips.push({ key: 'Age', label: 'Age: ' + ageMin + '-' + ageMax });

            var height = $('#filterHeight').val();
            if (height) chips.push({ key: 'filterHeight', label: 'Height: ' + height });

            var religion = $('#filterReligion').val();
            if (religion) chips.push({ key: 'filterReligion', label: 'Religion: ' + religion });

            var caste = $('#filterCaste').val();
            if (caste) chips.push({ key: 'filterCaste', label: 'Caste: ' + caste });

            var location = $('#filterLocation').val();
            if (location) chips.push({ key: 'filterLocation', label: 'Loc: ' + location });

            var marital = $('#advMaritalStatus').val();
            if (marital) chips.push({ key: 'advMaritalStatus', label: 'Status: ' + marital });

            var lang = $('#advMotherTongue').val();
            if (lang) chips.push({ key: 'advMotherTongue', label: 'Language: ' + lang });

            var edu = $('#advEducation').val();
            if (edu) chips.push({ key: 'advEducation', label: 'Edu: ' + edu });

            var occ = $('#advOccupation').val();
            if (occ) chips.push({ key: 'advOccupation', label: 'Occ: ' + occ });

            var income = $('#advIncome').val();
            if (income) chips.push({ key: 'advIncome', label: 'Income: ' + income });

            var manglik = $('#advManglik').val();
            if (manglik) chips.push({ key: 'advManglik', label: 'Horoscope: ' + manglik });

            var diet = $('#advDiet').val();
            if (diet) chips.push({ key: 'advDiet', label: 'Diet: ' + diet });

            var profId = $('#searchProfileId').val();
            if (profId) chips.push({ key: 'searchProfileId', label: 'ID: ' + profId });

            var profName = $('#searchName').val();
            if (profName) chips.push({ key: 'searchName', label: 'Name: ' + profName });

            var regDate = $('#searchRegDate').val();
            if (regDate) chips.push({ key: 'searchRegDate', label: 'Reg: ' + regDate });

            var dob = $('#searchDob').val();
            if (dob) chips.push({ key: 'searchDob', label: 'DOB: ' + dob });

            if ($('#chkPhotoOnly').is(':checked')) chips.push({ key: 'chkPhotoOnly', label: 'Photo Only' });
            if ($('#chkVerifiedOnly').is(':checked')) chips.push({ key: 'chkVerifiedOnly', label: 'Verified Only' });
            if ($('#chkOnlineOnly').is(':checked')) chips.push({ key: 'chkOnlineOnly', label: 'Online Only' });

            // Render Chips
            var $chipsList = $('#chipsList');
            $chipsList.empty();

            if (chips.length > 0) {
                $('#filterChipsContainer').slideDown(200);
                chips.forEach(function(item) {
                    var chipHtml = '<div class="filter-chip">' +
                        '<span>' + item.label + '</span>' +
                        '<i class="fa fa-times" onclick="removeFilterChip(\'' + item.key + '\');"></i>' +
                    '</div>';
                    $chipsList.append(chipHtml);
                });
            } else {
                $('#filterChipsContainer').slideUp(200);
            }

            // Fire AJAX Refine Search
            var oMyForm = new FormData();
            var searchData = {
                refineheightSelectPartnerFirst: height ? height.split(' to ')[0] || '' : '',
                refineheightSelectPartnerSecond: height ? height.split(' to ')[1] || '' : '',
                refineageFrom: ageMin || '18',
                refineageTo: ageMax || '50',
                refinematerialStatus: marital || '',
                refinereligionSelectPartner: religion || '',
                casteSelect: caste || '',
                refineoccupationCategorySelect: occ || '',
                checkBoxDistrict: location || '',
                profileId: profId || '',
                nameSearch: profName || '',
                regDate: regDate || '',
                dob: dob || '',
                RefineSearchProfile: "[]"
            };
            oMyForm.append("jsonObjectData", JSON.stringify(searchData));

            $('.profile-cards-container').css('opacity', '0.5');

            $.ajax({
                url: "${pageContext.request.contextPath}/loadRefineSearchProfile",
                type: "POST",
                data: oMyForm,
                processData: false,
                contentType: false,
                success: function(data) {
                    $('.profile-cards-container').css('opacity', '1');
                    if (data && data.trim().length > 50) {
                        $('.profile-cards-container').html(data);
                    } else {
                        filterClientMockProfiles();
                    }
                },
                error: function() {
                    $('.profile-cards-container').css('opacity', '1');
                    filterClientMockProfiles();
                }
            });
        };

        // Search by Details Specific Action
        window.applySearchByDetails = function() {
            applyAdvanceSearch();
        };

        // Clear Search by Details Inputs
        window.clearSearchByDetails = function() {
            $('#searchProfileId').val('');
            $('#searchName').val('');
            $('#searchRegDate').val('');
            $('#searchDob').val('');
            applyAdvanceSearch();
        };

        // Remove Individual Filter Chip
        window.removeFilterChip = function(key) {
            if (key === 'Age') {
                $('#filterAgeMin').val('18');
                $('#filterAgeMax').val('50');
            } else if (key.startsWith('chk')) {
                $('#' + key).prop('checked', false);
            } else {
                $('#' + key).val('');
            }
            applyAdvanceSearch();
        };

        // Clear All Advance Filters
        window.clearAllAdvanceFilters = function() {
            $('#filterAgeMin').val('18');
            $('#filterAgeMax').val('32');
            $('#filterHeight').val('');
            $('#filterReligion').val('');
            $('#filterCaste').val('');
            $('#filterLocation').val('');
            $('#advMaritalStatus').val('');
            $('#advMotherTongue').val('');
            $('#advEducation').val('');
            $('#advOccupation').val('');
            $('#advIncome').val('');
            $('#advManglik').val('');
            $('#advDiet').val('');
            $('#advState').val('');
            $('#searchProfileId').val('');
            $('#searchName').val('');
            $('#searchRegDate').val('');
            $('#searchDob').val('');
            $('#chkPhotoOnly').prop('checked', false);
            $('#chkVerifiedOnly').prop('checked', false);
            $('#chkOnlineOnly').prop('checked', false);

            $('#filterChipsContainer').slideUp(200);
            $('#chipsList').empty();
            
            // Reload default tab
            switchMatchTab(window.currentTabKey || 'allMatches');
        };

        // Client Mock Profile Filter Fallback
        function filterClientMockProfiles() {
            var locationFilter = ($('#filterLocation').val() || '').toLowerCase();
            var casteFilter = ($('#filterCaste').val() || '').toLowerCase();
            var religionFilter = ($('#filterReligion').val() || '').toLowerCase();
            var nameFilter = ($('#searchName').val() || '').toLowerCase();
            var idFilter = ($('#searchProfileId').val() || '').toLowerCase();

            $('.profile-cards-container .profile-card').each(function() {
                var text = $(this).text().toLowerCase();
                var match = true;

                if (locationFilter && text.indexOf(locationFilter) === -1) match = false;
                if (casteFilter && text.indexOf(casteFilter) === -1) match = false;
                if (religionFilter && text.indexOf(religionFilter) === -1) match = false;
                if (nameFilter && text.indexOf(nameFilter) === -1) match = false;
                if (idFilter && text.indexOf(idFilter) === -1) match = false;

                if (match) {
                    $(this).fadeIn(300);
                } else {
                    $(this).fadeOut(300);
                }
            });
        }

        $(document).ready(function() {
            $(document).on('click', '.btn-load-more-floating', function(e) {
                e.preventDefault();
                window.loadLatestMatches();
            });
        });
    </script>
</body>
</html>
