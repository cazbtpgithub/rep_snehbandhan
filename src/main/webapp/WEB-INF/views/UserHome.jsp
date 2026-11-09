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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/Template/css/flexslider.css">
    <link rel="stylesheet" href="resources/customcss/dashboard_new.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/confirmBox/jquery-confirm.css" media="screen">

    <script src="${pageContext.request.contextPath}/resources/Template/js/jquery-min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/Template/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/Template/js/jquery.flexslider.js"></script>
    <script src="${pageContext.request.contextPath}/resources/confirmBox/jquery-confirm.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/resources/ProfileViewer/profileView.js"></script>

    <style>
        /* Conversation Containers & Profile Styling */
        .conversation-container {
            display: flex;
            flex-direction: column;
            gap: 20px;
        }
        .conversation-header-card {
            background: #ffffff;
            border-radius: var(--radius-md, 12px);
            padding: 16px 22px;
            border: 1px solid var(--border-color, #f0e4e8);
            box-shadow: var(--shadow-sm, 0 2px 8px rgba(0,0,0,0.04));
            display: flex;
            justify-content: space-between;
            align-items: center;
        }
        .conversation-header-card h3 {
            margin: 0;
            font-size: 18px;
            font-weight: 700;
            color: #222;
            display: flex;
            align-items: center;
            gap: 10px;
        }
        .conversation-header-card .header-subtext {
            margin: 4px 0 0 0;
            font-size: 12px;
            color: #777;
        }
        .btn-back-dashboard {
            background: #fdf0f4;
            color: #d21a5a !important;
            border: 1px solid #f3c2d4;
            border-radius: 20px;
            padding: 6px 16px;
            font-size: 12px;
            font-weight: 600;
            cursor: pointer;
            text-decoration: none;
            transition: all 0.2s ease;
            display: inline-flex;
            align-items: center;
            gap: 6px;
        }
        .btn-back-dashboard:hover {
            background: #d21a5a;
            color: #ffffff !important;
        }

        /* User Profile Cards in Conversations */
        .user-profile {
            background: #ffffff;
            border-radius: var(--radius-md, 12px);
            padding: 20px;
            border: 1px solid var(--border-color, #f0e4e8);
            box-shadow: var(--shadow-sm, 0 2px 8px rgba(0,0,0,0.04));
            margin-bottom: 20px;
            transition: all 0.25s ease;
            clear: both;
            overflow: hidden;
        }
        .user-profile:hover {
            box-shadow: 0 6px 20px rgba(210, 26, 90, 0.1);
            border-color: #f3c2d4;
        }
        .user-profile .user-slider {
            max-width: 270px;
            padding: 0 10px;
        }
        .user-profile .user-slider .flexslider {
            margin-bottom: 8px;
            border: 1px solid #f0e4e8;
            border-radius: 10px;
            overflow: hidden;
            background: #fff;
        }
        .user-profile .user-slider .flexslider .slides img {
            width: 100%;
            height: 220px;
            object-fit: cover;
            border-radius: 8px;
        }
        .user-profile .member-name {
            color: #d21a5a;
            font-size: 18px;
            font-weight: 700;
            margin-top: 0;
            margin-bottom: 8px;
        }
        .user-profile .detail-profile ul {
            list-style: none;
            padding: 0;
            margin: 0 0 12px 0;
            font-size: 13px;
            color: #555;
            line-height: 1.8;
        }
        .user-profile .detail-profile ul li span {
            color: #222;
            font-weight: 500;
        }
        .user-profile .about-info h6 {
            font-weight: 700;
            font-size: 13px;
            color: #333;
            margin-top: 8px;
            margin-bottom: 6px;
        }
        .user-profile .about-info p {
            font-size: 13px;
            color: #666;
            line-height: 1.5;
            margin-bottom: 12px;
        }
        .user-profile .pinkbtn {
            background: linear-gradient(135deg, #d21a5a, #e63974);
            color: #ffffff !important;
            border: none;
            border-radius: 20px;
            padding: 6px 18px;
            font-size: 12px;
            font-weight: 600;
            cursor: pointer;
            transition: all 0.25s;
        }
        .user-profile .pinkbtn:hover {
            opacity: 0.92;
            transform: translateY(-1px);
        }
        .user-profile div[style*="background-color: #d23779"] {
            left: 0 !important;
            width: 100% !important;
            margin-top: 15px;
            border-radius: 10px !important;
            background: linear-gradient(135deg, #d21a5a, #e63974) !important;
            border: none !important;
            box-shadow: 0 4px 12px rgba(210, 26, 90, 0.2);
            padding: 12px !important;
            color: #fff;
        }

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

        .btn-logout-nav {
            display: inline-flex;
            align-items: center;
            gap: 7px;
            padding: 8px 16px;
            border-radius: 24px;
            background: #fff0f3;
            border: 1px solid #ffd1dc;
            color: #d21a5a !important;
            font-size: 13px;
            font-weight: 600;
            text-decoration: none !important;
            cursor: pointer;
            transition: all 0.25s ease;
            box-shadow: 0 2px 6px rgba(210, 26, 90, 0.08);
        }
        .btn-logout-nav:hover {
            background: #d21a5a;
            color: #ffffff !important;
            border-color: #d21a5a;
            box-shadow: 0 4px 14px rgba(210, 26, 90, 0.25);
            transform: translateY(-1px);
        }
        .btn-logout-nav i {
            font-size: 14px;
        }

        /* Profile Photos Face Visibility Optimization */
        .profile-photos {
            display: flex !important;
            flex-direction: column !important;
            gap: 8px !important;
            width: 155px !important;
            flex-shrink: 0 !important;
        }

        .main-photo-wrapper {
            position: relative !important;
            width: 155px !important;
            height: 165px !important;
            border-radius: 10px !important;
            overflow: hidden !important;
            background: #f8fafc !important;
            box-shadow: 0 2px 8px rgba(0, 0, 0, 0.05) !important;
        }

        .main-photo-wrapper img {
            width: 100% !important;
            height: 100% !important;
            object-fit: cover !important;
            object-position: top center !important; /* Always show faces and head clearly */
            transition: transform 0.3s ease !important;
        }

        .main-photo-wrapper:hover img {
            transform: scale(1.04) !important;
        }

        .verified-badge {
            position: absolute !important;
            top: 6px !important;
            left: 6px !important;
            background: rgba(16, 185, 129, 0.92) !important;
            backdrop-filter: blur(4px) !important;
            color: #ffffff !important;
            font-size: 9px !important;
            font-weight: 600 !important;
            padding: 2px 7px !important;
            border-radius: 10px !important;
            display: flex !important;
            align-items: center !important;
            gap: 4px !important;
            z-index: 2 !important;
        }

        .thumb-photos {
            display: flex !important;
            gap: 6px !important;
            width: 155px !important;
        }

        .thumb-photos:empty {
            display: none !important;
        }

        .thumb-photos img {
            width: calc(50% - 3px) !important;
            height: 50px !important;
            border-radius: 6px !important;
            object-fit: cover !important;
            object-position: top center !important;
            transition: transform 0.2s ease, opacity 0.2s ease !important;
        }

        .thumb-photos img:hover {
            opacity: 0.9 !important;
            transform: scale(1.03) !important;
        }

        .sidebar-user-card img,
        .user-profile-pill img,
        .user-dropdown-avatar,
        .online-user-info img {
            object-fit: cover !important;
            object-position: top center !important;
        }

        /* User Profile Pill & Dropdown Styles */
        .user-dropdown {
            position: relative;
        }
        .user-profile-pill {
            display: flex;
            align-items: center;
            gap: 10px;
            padding: 4px 14px 4px 4px;
            border-radius: 30px;
            background: #ffffff;
            border: 1px solid var(--border-color, #f0e4e8);
            cursor: pointer;
            transition: all 0.2s ease;
            user-select: none;
        }
        .user-profile-pill:hover,
        .user-dropdown.open .user-profile-pill,
        .user-dropdown.show .user-profile-pill {
            border-color: var(--primary, #d21a5a);
            box-shadow: 0 3px 12px rgba(210, 26, 90, 0.15);
        }
        .user-profile-pill.dropdown-toggle::after {
            display: none !important;
        }
        .user-pill-arrow {
            color: #888888;
            font-size: 13px;
            margin-left: 4px;
            transition: transform 0.25s ease, color 0.2s ease;
        }
        .user-dropdown.open .user-pill-arrow,
        .user-dropdown.show .user-pill-arrow {
            transform: rotate(180deg);
            color: var(--primary, #d21a5a);
        }
        .user-profile-dropdown-menu {
            position: absolute;
            top: calc(100% + 10px);
            right: 0;
            left: auto;
            min-width: 250px;
            background: #ffffff;
            border-radius: 14px;
            border: 1px solid rgba(0, 0, 0, 0.08);
            box-shadow: 0 14px 35px rgba(0, 0, 0, 0.12), 0 2px 6px rgba(0, 0, 0, 0.04);
            padding: 10px 0 8px 0;
            margin: 0;
            display: none;
            z-index: 1050;
            animation: userDropdownFadeIn 0.2s cubic-bezier(0.16, 1, 0.3, 1) forwards;
        }
        .user-dropdown.open .user-profile-dropdown-menu,
        .user-profile-dropdown-menu.show {
            display: block !important;
        }
        @keyframes userDropdownFadeIn {
            from {
                opacity: 0;
                transform: translateY(-8px);
            }
            to {
                opacity: 1;
                transform: translateY(0);
            }
        }
        .user-dropdown-header {
            display: flex;
            align-items: center;
            gap: 12px;
            padding: 8px 18px 12px 18px;
        }
        .user-dropdown-avatar {
            width: 44px;
            height: 44px;
            border-radius: 50%;
            object-fit: cover;
            border: 2px solid #ffe4ec;
            flex-shrink: 0;
        }
        .user-dropdown-meta {
            display: flex;
            flex-direction: column;
            overflow: hidden;
        }
        .user-dropdown-name {
            font-weight: 700;
            font-size: 14px;
            color: #1f2937;
            white-space: nowrap;
            overflow: hidden;
            text-overflow: ellipsis;
            line-height: 1.2;
        }
        .user-dropdown-role {
            font-size: 11px;
            color: var(--primary, #d21a5a);
            font-weight: 600;
            margin-top: 3px;
            display: flex;
            align-items: center;
            gap: 4px;
        }
        .user-dropdown-email {
            font-size: 11px;
            color: #6b7280;
            white-space: nowrap;
            overflow: hidden;
            text-overflow: ellipsis;
            margin-top: 2px;
        }
        .user-dropdown-divider {
            height: 1px;
            margin: 6px 0;
            background-color: #f1f3f5;
            border: none;
        }
        .user-dropdown-item,
        .user-profile-dropdown-menu .dropdown-item {
            display: flex !important;
            align-items: center;
            gap: 12px;
            padding: 10px 18px !important;
            font-size: 13px !important;
            font-weight: 500 !important;
            color: #374151 !important;
            text-decoration: none !important;
            transition: all 0.15s ease !important;
            background: transparent;
            border: none;
            width: 100%;
            box-sizing: border-box;
            cursor: pointer;
        }
        .user-dropdown-item i,
        .user-profile-dropdown-menu .dropdown-item i {
            width: 18px;
            font-size: 15px;
            text-align: center;
            color: #6b7280;
            transition: color 0.15s ease;
        }
        .user-dropdown-item:hover,
        .user-profile-dropdown-menu .dropdown-item:hover {
            background-color: #fdf2f6 !important;
            color: var(--primary, #d21a5a) !important;
            padding-left: 22px !important;
        }
        .user-dropdown-item:hover i,
        .user-profile-dropdown-menu .dropdown-item:hover i {
            color: var(--primary, #d21a5a) !important;
        }
        .user-dropdown-item.user-logout-item,
        .user-profile-dropdown-menu .dropdown-item[href*="logout"] {
            color: #dc2626 !important;
            font-weight: 600 !important;
        }
        .user-dropdown-item.user-logout-item i,
        .user-profile-dropdown-menu .dropdown-item[href*="logout"] i {
            color: #dc2626 !important;
        }
        .user-dropdown-item.user-logout-item:hover,
        .user-profile-dropdown-menu .dropdown-item[href*="logout"]:hover {
            background-color: #fef2f2 !important;
            color: #b91c1c !important;
            padding-left: 22px !important;
        }
        .user-dropdown-item.user-logout-item:hover i,
        .user-profile-dropdown-menu .dropdown-item[href*="logout"]:hover i {
            color: #b91c1c !important;
        }

        /* Shortlist Modal Styles matching target screenshot */
        #myModal .modal-dialog {
            max-width: 580px;
            margin: 60px auto;
        }
        #myModal .modal-content {
            border-radius: 6px;
            border: 1px solid rgba(0, 0, 0, 0.15);
            box-shadow: 0 5px 20px rgba(0, 0, 0, 0.25);
            background: #ffffff;
            overflow: hidden;
        }
        #myModal .modal-header {
            padding: 16px 22px 14px 22px;
            border-bottom: 1px solid #eeeeee;
            position: relative;
        }
        #myModal .modal-header .close {
            position: absolute;
            right: 18px;
            top: 14px;
            font-size: 28px;
            font-weight: 300;
            color: #888888;
            opacity: 0.7;
            border: none;
            background: transparent;
            cursor: pointer;
            line-height: 1;
            padding: 0;
            margin: 0;
        }
        #myModal .modal-header .close:hover {
            opacity: 1;
            color: #333333;
        }
        #myModal .modal-body {
            padding: 20px 22px;
        }
        .custom-shortlist-breadcrumb {
            background-color: var(--primary, #d21a5a);
            color: #ffffff;
            padding: 12px 10px;
            border-radius: 6px;
            display: flex;
            text-align: center;
            margin-bottom: 18px;
        }
        .custom-shortlist-breadcrumb a,
        .custom-shortlist-breadcrumb span {
            color: #ffffff !important;
            text-decoration: none !important;
            font-size: 14px;
            font-weight: 500;
            cursor: pointer;
            display: block;
        }
        .custom-shortlist-breadcrumb a:hover,
        .custom-shortlist-breadcrumb span:hover {
            opacity: 0.9;
        }
        #menu3ViewContact {
            font-size: 14.5px;
            color: #333333;
            line-height: 2.1;
            padding: 5px 0 10px 0;
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
            
            <a href="<c:url value='/j_spring_security_logout'/>" onclick="performLogout(event);" class="btn-logout-nav" id="headerLogoutBtn" title="Logout">
                <i class="fa fa-sign-out"></i>
                <span>Logout</span>
            </a>

            <a href="#" class="nav-icon-btn" title="Notifications">
                <i class="fa fa-bell-o"></i>
                <span class="badge">5</span>
            </a>

            <div class="dropdown user-dropdown">
                <div class="user-profile-pill dropdown-toggle" id="userProfileDropdown" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" role="button" title="Account & Logout Menu">
                    <img src="${pageContext.request.contextPath}/resources/UserImages/${UserProfile}" onerror="this.onerror=null; this.src='${pageContext.request.contextPath}/resources/UserImages/userImage.png';" alt="User">
                    <div class="user-info">
                        <span class="user-name">${ActUserName}</span>
                        <span class="user-role"><i class="fa fa-crown"></i> Premium Member</span>
                    </div>
                    <i class="fa fa-angle-down user-pill-arrow"></i>
                </div>
                <div class="dropdown-menu dropdown-menu-right user-profile-dropdown-menu" aria-labelledby="userProfileDropdown">
                    <div class="user-dropdown-header">
                        <img src="${pageContext.request.contextPath}/resources/UserImages/${UserProfile}" onerror="this.onerror=null; this.src='${pageContext.request.contextPath}/resources/UserImages/userImage.png';" alt="User" class="user-dropdown-avatar">
                        <div class="user-dropdown-meta">
                            <span class="user-dropdown-name">${ActUserName}</span>
                            <span class="user-dropdown-role"><i class="fa fa-crown"></i> Premium Member</span>
                            <c:if test="${not empty userEmail}">
                                <span class="user-dropdown-email" title="${userEmail}">${userEmail}</span>
                            </c:if>
                        </div>
                    </div>
                    <div class="dropdown-divider user-dropdown-divider"></div>
                    <a class="dropdown-item user-dropdown-item" href="<c:url value='/editProfile'/>">
                        <i class="fa fa-user-circle-o"></i> View / Edit Profile
                    </a>
                    <a class="dropdown-item user-dropdown-item" href="<c:url value='/KitUpgradeAndPayment'/>">
                        <i class="fa fa-crown" style="color: #f59e0b;"></i> Upgrade Plan
                    </a>
                    <a class="dropdown-item user-dropdown-item" href="<c:url value='/editProfile'/>">
                        <i class="fa fa-cog"></i> Account Settings
                    </a>
                    <div class="dropdown-divider user-dropdown-divider"></div>
                    <a class="dropdown-item" href="/j_spring_security_logout"><i class="fa fa-sign-out"></i> Logout</a>
                </div>
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
                        <a href="#" class="active" id="sidebarDashboard" onclick="switchConversation('mainDivForUser', this); return false;">
                            <div class="menu-left"><i class="fa fa-dashboard"></i> Dashboard</div>
                        </a>
                    </li>
                    <li>
                        <a href="#" id="sidebarInbox" onclick="switchConversation('inbox_conversation', this); return false;">
                            <div class="menu-left"><i class="fa fa-envelope-o"></i> Inbox</div>
                            <span class="badge-count">${inboxCount != null ? inboxCount : 0}</span>
                        </a>
                    </li>
                    <li>
                        <a href="#" id="sidebarInterestReceived" onclick="switchConversation('invitation_conversation', this); return false;">
                            <div class="menu-left"><i class="fa fa-heart-o"></i> Interest Received</div>
                            <span class="badge-count">${interestReceivedCount != null ? interestReceivedCount : 0}</span>
                        </a>
                    </li>
                    <li>
                        <a href="#" id="sidebarInterestSent" onclick="switchConversation('sent_conversation', this); return false;">
                            <div class="menu-left"><i class="fa fa-paper-plane-o"></i> Interest Sent</div>
                            <span class="badge-count">${interestSentCount != null ? interestSentCount : 0}</span>
                        </a>
                    </li>
                    <li>
                        <a href="#" onclick="switchConversation('mainDivForUser', this); return false;">
                            <div class="menu-left"><i class="fa fa-users"></i> Matches</div>
                        </a>
                    </li>
                    <li>
                        <a href="#" onclick="switchConversation('mainDivForUser', this); switchMatchTab('viewedYet'); return false;">
                            <div class="menu-left"><i class="fa fa-eye"></i> Who Viewed Me</div>
                            <span class="badge-count">${seenyet != null ? seenyet : 0}</span>
                        </a>
                    </li>
                    <li>
                        <a href="#" onclick="switchConversation('mainDivForUser', this); switchMatchTab('shortlisted'); return false;">
                            <div class="menu-left"><i class="fa fa-star-o"></i> Shortlisted</div>
                            <span class="badge-count">${shortlistedCount != null ? shortlistedCount : 0}</span>
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
                        <a class="dropdown-item" href="/j_spring_security_logout">
                            <div class="menu-left"><i class="fa fa-sign-out"></i> Logout</div>
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
                        <h3><fmt:formatNumber value="${seenyet != null ? seenyet : 0}"/></h3>
                        <p>Viewed My Profile</p>
                    </div>
                </div>

                <div class="stat-card" onclick="switchMatchTab('shortlisted', this);">
                    <div class="icon-circle"><i class="fa fa-heart"></i></div>
                    <div class="stat-info">
                        <h3><fmt:formatNumber value="${shortlistedMeCount != null ? shortlistedMeCount : (sortlisted != null ? sortlisted : 0)}"/></h3>
                        <p>Shortlisted Me</p>
                    </div>
                </div>

                <div class="stat-card" onclick="switchMatchTab('notSeenYet', this);">
                    <div class="icon-circle"><i class="fa fa-star"></i></div>
                    <div class="stat-info">
                        <h3><fmt:formatNumber value="${notseenyet != null ? notseenyet : 0}"/></h3>
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
                            <%-- Age min: empty value means "no filter" --%>
                            <select id="filterAgeMin" style="min-width: 65px;">
                                <option value="">Any</option>
                                <option value="18">18</option>
                                <option value="20">20</option>
                                <option value="22">22</option>
                                <option value="25">25</option>
                                <option value="28">28</option>
                                <option value="30">30</option>
                                <option value="35">35</option>
                            </select>
                            <span style="font-size: 11px; color: #888;">to</span>
                            <select id="filterAgeMax" style="min-width: 65px;">
                                <option value="">Any</option>
                                <option value="25">25</option>
                                <option value="28">28</option>
                                <option value="30">30</option>
                                <option value="32">32</option>
                                <option value="35">35</option>
                                <option value="40">40</option>
                                <option value="45">45</option>
                                <option value="50">50</option>
                            </select>
                        </div>
                    </div>

                    <div class="filter-group">
                        <%-- Height: values are numeric decimals (feet.inches) that the DAO can parse --%>
                        <label>Height</label>
                        <select id="filterHeight" style="min-width: 100px;">
                            <option value="">Any Height</option>
                            <option value="4.0|4.6">4'0" to 4'6"</option>
                            <option value="4.6|5.0">4'6" to 5'0"</option>
                            <option value="5.0|5.3">5'0" to 5'3"</option>
                            <option value="5.3|5.6">5'3" to 5'6"</option>
                            <option value="5.6|6.0">5'6" to 6'0"</option>
                            <option value="6.0|7.0">6'0" &amp; Above</option>
                        </select>
                    </div>

                    <div class="filter-group">
                        <%-- FIX: Religion dropdown uses only dynamic DB options (religion_id as value) --%>
                        <label>Religion</label>
                        <select id="filterReligion" style="min-width: 100px;">
                            <option value="">Any Religion</option>
                            <c:forEach var="religion" items="${religions}">
                                <option value="${religion.religion_id}">${religion.religion_name}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="filter-group">
                        <%-- FIX: Caste dropdown is dynamically loaded via AJAX when religion is selected --%>
                        <label>Caste</label>
                        <select id="filterCaste" style="min-width: 100px;">
                            <option value="">Any Caste</option>
                        </select>
                    </div>

                    <div class="filter-group" style="flex: 1; min-width: 140px;">
                        <%-- FIX: Location now uses free-text search (state/district name) matched server-side --%>
                        <label>Location</label>
                        <input type="text" id="filterLocation" placeholder="City, District or State" autocomplete="off">
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

                <script>
                    var shortListUsersId = window.shortListUsersId || [];
                    window.latestMatchVisitorIds = window.latestMatchVisitorIds || [];
                </script>
                <c:forEach var="profileShortList" items="${sortlistedProfileList}" varStatus="theCount">
                    <script>shortListUsersId.push("${profileShortList.sortlistedUserModel.userId}");</script>
                </c:forEach>

                <!-- Dynamic Profile Loop -->
                <c:forEach var="profile" items="${LatestMatch}" varStatus="theCount">
                    <script>
                        if (${not empty profile.visitorUserId}) {
                            window.latestMatchVisitorIds.push(${profile.visitorUserId});
                        }
                    </script>
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
                                <a href="#" class="btn-shortlist-outline" onclick="Shortlist('25005','Trupti Popat Sawant');"><i class="fa fa-heart-o"></i> Shortlist</a>
                                <a href="#" class="btn-view-profile-solid">View Profile</a>
                                <a href="#" class="btn-circle-icon"><i class="fa fa-comment-o"></i></a>
                                <a href="#" class="btn-circle-icon" onclick="Shortlist('25005','Trupti Popat Sawant');"><i class="fa fa-envelope-o"></i></a>
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
                                <a href="#" class="btn-shortlist-outline" onclick="Shortlist('23300','Ashwini Arun Pawar');"><i class="fa fa-heart-o"></i> Shortlist</a>
                                <a href="#" class="btn-view-profile-solid">View Profile</a>
                                <a href="#" class="btn-circle-icon"><i class="fa fa-comment-o"></i></a>
                                <a href="#" class="btn-circle-icon" onclick="Shortlist('23300','Ashwini Arun Pawar');"><i class="fa fa-envelope-o"></i></a>
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
                                <a href="#" class="btn-shortlist-outline" onclick="Shortlist('25044','Komal Chandar Patil');"><i class="fa fa-heart-o"></i> Shortlist</a>
                                <a href="#" class="btn-view-profile-solid">View Profile</a>
                                <a href="#" class="btn-circle-icon"><i class="fa fa-comment-o"></i></a>
                                <a href="#" class="btn-circle-icon" onclick="Shortlist('25044','Komal Chandar Patil');"><i class="fa fa-envelope-o"></i></a>
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

        <!-- Column 2b: Inbox Conversation Container -->
        <main class="dash-main conversation-container" id="inbox_conversation" style="display: none;">
            <div class="conversation-header-card">
                <div>
                    <h3><i class="fa fa-envelope-o" style="color: #d21a5a;"></i> Inbox</h3>
                    <p class="header-subtext">Messages and communications received</p>
                </div>
                <button type="button" class="btn-back-dashboard" onclick="switchConversation('mainDivForUser');">
                    <i class="fa fa-arrow-left"></i> Back to Matches
                </button>
            </div>
            <div id="latest_inbox_conversationView"></div>
        </main>

        <!-- Column 2c: Interest Received Container -->
        <main class="dash-main conversation-container" id="invitation_conversation" style="display: none;">
            <div class="conversation-header-card">
                <div>
                    <h3><i class="fa fa-heart-o" style="color: #d21a5a;"></i> Interest Received</h3>
                    <p class="header-subtext">Invitations and interest received from members</p>
                </div>
                <button type="button" class="btn-back-dashboard" onclick="switchConversation('mainDivForUser');">
                    <i class="fa fa-arrow-left"></i> Back to Matches
                </button>
            </div>
            <div id="latest_invitation_conversationView"></div>
        </main>

        <!-- Column 2d: Interest Sent Container -->
        <main class="dash-main conversation-container" id="sent_conversation" style="display: none;">
            <div class="conversation-header-card">
                <div>
                    <h3><i class="fa fa-paper-plane-o" style="color: #d21a5a;"></i> Interest Sent</h3>
                    <p class="header-subtext">Invitations and messages sent by you</p>
                </div>
                <button type="button" class="btn-back-dashboard" onclick="switchConversation('mainDivForUser');">
                    <i class="fa fa-arrow-left"></i> Back to Matches
                </button>
            </div>
            <div id="latest_sent_conversationView"></div>
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
    <!-- Shortlist Form Hidden Fields -->
    <form id="ShortlistForm">
        <input type="hidden" id="ShortlistVisitedId" name="ShortlistVisitedId">
        <input type="hidden" id="ShortlistName" name="ShortlistName">
    </form>

    <!-- Shortlist Modal -->
    <div id="myModal" class="modal fade" role="dialog" tabindex="-1">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">&times;</button>
                    <font color="green" size="3;">You have shortlisted successfully...</font>
                    <h4 class="modal-title" id="shortName" style="color: #222222; font-size: 22px; font-weight: 700; margin-top: 4px; margin-bottom: 0;"></h4>
                </div>
                <div class="modal-body">
                    <!-- Pink Navigation Bar: Send Sms, Send Email, Contact Details -->
                    <nav class="custom-shortlist-breadcrumb">
                        <div style="flex: 1;">
                            <a href="#menu1" id="imenu1" onclick="show1('menu1'); return false;">Send Sms</a>
                        </div>
                        <div style="flex: 1;">
                            <a href="#menu2" id="imenu2" onclick="show1('menu2'); return false;">Send Email</a>
                        </div>
                        <div style="flex: 1;">
                            <span id="imenu3" href="#menu3" onclick="show1('menu3'); return false;">Contact Details</span>
                        </div>
                    </nav>

                    <div class="tab-content" style="padding: 0 4px;">
                        <!-- Menu 1: Send SMS -->
                        <div id="menu1" class="tab-pane" style="display: none;">
                            <form id="sms" onsubmit="event.preventDefault(); ShortlistSendMSG();">
                                <div class="form-group" style="margin-bottom: 14px;">
                                    <label style="font-size: 13px; color: #555555; margin-bottom: 6px; display: block;">104 characters only</label>
                                    <textarea class="form-control" rows="3" id="ShortlistMSG" maxlength="104" style="width: 100%; border: 1px solid #cccccc; border-radius: 4px; padding: 10px; font-size: 14px; box-sizing: border-box;"></textarea>
                                </div>
                                <div class="form-group" style="margin-bottom: 0;">
                                    <input type="button" class="btn btn-primary" style="background: var(--primary, #d21a5a); border-color: var(--primary, #d21a5a); color: #ffffff; padding: 7px 20px; font-size: 14px; border-radius: 4px; font-weight: 500; cursor: pointer;" onclick="ShortlistSendMSG();" value="Send MSG">
                                </div>
                            </form>
                        </div>

                        <!-- Menu 2: Send Email -->
                        <div id="menu2" class="tab-pane" style="display: none;">
                            <form id="email" onsubmit="event.preventDefault(); ShortlistSendEmail();">
                                <div class="form-group" style="margin-bottom: 14px;">
                                    <textarea class="form-control" rows="5" id="ShortlistEmail" placeholder="Enter message..." style="width: 100%; border: 1px solid #cccccc; border-radius: 4px; padding: 10px; font-size: 14px; box-sizing: border-box;"></textarea>
                                </div>
                                <div class="form-group" style="margin-bottom: 0;">
                                    <input type="button" class="btn btn-primary" style="background: var(--primary, #d21a5a); border-color: var(--primary, #d21a5a); color: #ffffff; padding: 7px 20px; font-size: 14px; border-radius: 4px; font-weight: 500; cursor: pointer;" onclick="ShortlistSendEmail();" value="Send Email">
                                </div>
                            </form>
                        </div>

                        <!-- Menu 3: Contact Details -->
                        <div id="menu3" class="tab-pane" style="display: block;">
                            <div id="menu3ViewContact" class="tab-pane"></div>
                        </div>

                        <form id="ShortlistForm">
                            <input type="hidden" id="ShortlistVisitedId" name="ShortlistVisitedId">
                            <input type="hidden" id="ShortlistName" name="ShortlistName">
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Upgrade Package Modal -->
    <div id="myModalUpgrade" class="modal fade" role="dialog">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title" style="color:red;">Upgrade Package Required</h4>
                </div>
                <div class="modal-body">
                    <p>Your shortlist profile limit has reached maximum count or expired. Please upgrade your package to continue shortlisting profiles.</p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    <a href="<c:url value='/KitUpgradeAndPayment'/>" class="btn btn-primary">Upgrade Package</a>
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
        // Switch Conversation View (Inbox, Interest Received, Interest Sent, Dashboard)
        window.switchConversation = function(loadDiv, elem) {
            // Update active link in sidebar
            $('.sidebar-menu li a').removeClass('active');
            if (elem) {
                $(elem).addClass('active');
            } else {
                if (loadDiv === 'inbox_conversation') {
                    $('#sidebarInbox').addClass('active');
                } else if (loadDiv === 'invitation_conversation') {
                    $('#sidebarInterestReceived').addClass('active');
                } else if (loadDiv === 'sent_conversation') {
                    $('#sidebarInterestSent').addClass('active');
                } else if (loadDiv === 'mainDivForUser') {
                    $('#sidebarDashboard').addClass('active');
                }
            }

            // Hide all main containers
            $('#mainDivForUser').hide();
            $('#inbox_conversation').hide();
            $('#invitation_conversation').hide();
            $('#sent_conversation').hide();

            // Smooth scroll to top of main area
            $('html, body').animate({ scrollTop: 0 }, 150);

            if (loadDiv === 'mainDivForUser') {
                $('#mainDivForUser').fadeIn(200);
                return;
            }

            var spinnerHtml = '<div style="text-align:center; padding: 40px; background:#fff; border-radius:12px; border:1px solid #f0e4e8; margin-top:10px;">' +
                '<i class="fa fa-spinner fa-spin fa-2x" style="color:#d21a5a;"></i>' +
                '<p style="margin-top:12px; color:#666; font-size:14px; font-weight:500;">Loading...</p>' +
                '</div>';

            if (loadDiv === 'inbox_conversation') {
                $('#inbox_conversation').show();
                $('#latest_inbox_conversationView').html(spinnerHtml);
                $.ajax({
                    url: "${pageContext.request.contextPath}/latest_inbox_conversation",
                    type: "POST",
                    processData: false,
                    contentType: false,
                    success: function(data) {
                        $('#latest_inbox_conversationView').html(data);
                    },
                    error: function(xhr, ajaxOptions, thrownError) {
                        console.error("Error loading inbox: " + thrownError);
                        $('#latest_inbox_conversationView').html(
                            '<div style="text-align:center; padding: 30px; background:#fff; border-radius:12px; border:1px solid #f0e4e8;">' +
                            '<p style="color:#d21a5a; font-weight:600;">Failed to load Inbox messages. Please try again.</p>' +
                            '</div>'
                        );
                    }
                });
            } else if (loadDiv === 'invitation_conversation') {
                $('#invitation_conversation').show();
                $('#latest_invitation_conversationView').html(spinnerHtml);
                $.ajax({
                    url: "${pageContext.request.contextPath}/invitation_conversation",
                    type: "POST",
                    processData: false,
                    contentType: false,
                    success: function(data) {
                        $('#latest_invitation_conversationView').html(data);
                    },
                    error: function(xhr, ajaxOptions, thrownError) {
                        console.error("Error loading invitations: " + thrownError);
                        $('#latest_invitation_conversationView').html(
                            '<div style="text-align:center; padding: 30px; background:#fff; border-radius:12px; border:1px solid #f0e4e8;">' +
                            '<p style="color:#d21a5a; font-weight:600;">Failed to load received interests. Please try again.</p>' +
                            '</div>'
                        );
                    }
                });
            } else if (loadDiv === 'sent_conversation') {
                $('#sent_conversation').show();
                $('#latest_sent_conversationView').html(spinnerHtml);
                $.ajax({
                    url: "${pageContext.request.contextPath}/latest_sent_conversation",
                    type: "POST",
                    processData: false,
                    contentType: false,
                    success: function(data) {
                        $('#latest_sent_conversationView').html(data);
                    },
                    error: function(xhr, ajaxOptions, thrownError) {
                        console.error("Error loading sent conversations: " + thrownError);
                        $('#latest_sent_conversationView').html(
                            '<div style="text-align:center; padding: 30px; background:#fff; border-radius:12px; border:1px solid #f0e4e8;">' +
                            '<p style="color:#d21a5a; font-weight:600;">Failed to load sent interests. Please try again.</p>' +
                            '</div>'
                        );
                    }
                });
            } else {
                $('#' + loadDiv).show();
            }
        };
        window.performLogout = function(e) {
            if (e && e.preventDefault) e.preventDefault();
            var email = "${userEmail}";
            var logoutUrl = "${pageContext.request.contextPath}/j_spring_security_logout";
            
            var redirected = false;
            function doRedirect() {
                if (!redirected) {
                    redirected = true;
                    window.location.href = logoutUrl;
                }
            }
            
            // Safety timeout ensures redirect happens quickly even if background call hangs
            setTimeout(doRedirect, 1000);
            
            if (email && email.trim() !== '') {
                try {
                    var oMyForm = new FormData();
                    oMyForm.append("jsonObjectData1", JSON.stringify({ email: email }));
                    $.ajax({
                        url: "${pageContext.request.contextPath}/deleteOnlineuserInfo",
                        type: "POST",
                        data: oMyForm,
                        processData: false,
                        contentType: false,
                        timeout: 800,
                        complete: function() {
                            doRedirect();
                        }
                    });
                } catch(err) {
                    doRedirect();
                }
            } else {
                doRedirect();
            }
        };

        // User Profile Pill Dropdown Toggle Handler
        $(document).ready(function() {
            $(document).on('click', '.user-dropdown .user-profile-pill', function(e) {
                e.preventDefault();
                e.stopPropagation();
                var $dropdown = $(this).closest('.user-dropdown');
                var $menu = $dropdown.find('.user-profile-dropdown-menu');
                var isOpen = $dropdown.hasClass('open') || $menu.hasClass('show');
                
                // Close other dropdowns
                $('.user-dropdown').removeClass('open');
                $('.user-profile-dropdown-menu').removeClass('show');
                
                if (!isOpen) {
                    $dropdown.addClass('open');
                    $menu.addClass('show');
                }
            });

            $(document).on('click', function(e) {
                if (!$(e.target).closest('.user-dropdown').length) {
                    $('.user-dropdown').removeClass('open');
                    $('.user-profile-dropdown-menu').removeClass('show');
                }
            });
        });

        window.loadLatestMatches = function() {
            var $btn = $('.btn-load-more-floating');
            var originalBtnHtml = '<i class="fa fa-refresh"></i> Load More Profiles';
            $btn.prop('disabled', true).html('<i class="fa fa-spinner fa-spin"></i> Loading Profiles...');

            var tabKey = window.currentTabKey || 'allMatches';
            var endpointMap = {
                'allMatches': 'loadLatestMatches',
                'latestUpgraded': 'loadLatestUpgraded',
                'notSeenYet': 'NotSeenYetProfilesView',
                'viewedYet': 'ViewedyetProfilesView',
                'shortlisted': 'ShortlistedOrIntrestedView'
            };
            var endpoint = endpointMap[tabKey] || 'loadLatestMatches';

            // Gather all currently rendered profile IDs
            var currentVisitorIds = [];
            $('.profile-cards-container .profile-card').each(function() {
                var uid = $(this).attr('data-user-id');
                if (uid && !isNaN(uid)) {
                    var num = parseInt(uid, 10);
                    if (currentVisitorIds.indexOf(num) === -1) {
                        currentVisitorIds.push(num);
                    }
                }
            });
            if (window.latestMatchVisitorIds && Array.isArray(window.latestMatchVisitorIds)) {
                window.latestMatchVisitorIds.forEach(function(id) {
                    var num = parseInt(id, 10);
                    if (!isNaN(num) && currentVisitorIds.indexOf(num) === -1) {
                        currentVisitorIds.push(num);
                    }
                });
            }
            window.latestMatchVisitorIds = currentVisitorIds;

            var oMyForm = new FormData();
            var payload = {
                latestMatchVisitorIds: currentVisitorIds,
                latestUpgradedVisitorIds: currentVisitorIds,
                NotSeenYetProfiles: currentVisitorIds,
                ViewedyetProfiles: currentVisitorIds,
                ShortlistedOrIntrested: currentVisitorIds,
                length: "10"
            };
            oMyForm.append("jsonObjectData", JSON.stringify(payload));

            $.ajax({
                url: "${pageContext.request.contextPath}/" + endpoint,
                type: "POST",
                data: oMyForm,
                processData: false,
                contentType: false,
                success: function(data) {
                    $btn.prop('disabled', false).html(originalBtnHtml);
                    if (data && data.trim().length > 50) {
                        var $wrapper = $('<div>').html(data);
                        var $cards = $wrapper.find('.profile-card');
                        if ($cards.length === 0) {
                            $cards = $(data).filter('.profile-card');
                        }

                        if ($cards.length > 0) {
                            $cards.hide();
                            $('.profile-cards-container').append($cards);
                            $cards.fadeIn(400);

                            // Append new IDs to tracked list
                            $cards.each(function() {
                                var uid = $(this).attr('data-user-id');
                                if (uid && !isNaN(uid)) {
                                    var num = parseInt(uid, 10);
                                    if (window.latestMatchVisitorIds.indexOf(num) === -1) {
                                        window.latestMatchVisitorIds.push(num);
                                    }
                                }
                            });
                        } else {
                            $btn.html('<i class="fa fa-check"></i> No More Profiles');
                            setTimeout(function() {
                                $btn.html(originalBtnHtml);
                            }, 3000);
                        }
                    } else {
                        $btn.html('<i class="fa fa-check"></i> No More Profiles');
                        setTimeout(function() {
                            $btn.html(originalBtnHtml);
                        }, 3000);
                    }
                },
                error: function(xhr, ajaxOptions, thrownError) {
                    $btn.prop('disabled', false).html(originalBtnHtml);
                    console.error("Error loading profiles: " + thrownError);
                }
            });
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

            // Reset tracked IDs for the newly selected category
            window.latestMatchVisitorIds = [];

            // Show subtle overlay / spinner
            $('.profile-cards-container').css('opacity', '0.5');

            var oMyForm = new FormData();
            var payload = {
                latestMatchVisitorIds: [],
                latestUpgradedVisitorIds: [],
                NotSeenYetProfiles: [],
                ViewedyetProfiles: [],
                ShortlistedOrIntrested: [],
                length: "10"
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
                        // Track IDs from newly rendered cards
                        $('.profile-cards-container .profile-card').each(function() {
                            var uid = $(this).attr('data-user-id');
                            if (uid && !isNaN(uid)) {
                                var num = parseInt(uid, 10);
                                if (window.latestMatchVisitorIds.indexOf(num) === -1) {
                                    window.latestMatchVisitorIds.push(num);
                                }
                            }
                        });
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
            if (ageMin || ageMax) chips.push({ key: 'Age', label: 'Age: ' + (ageMin || 'Any') + '-' + (ageMax || 'Any') });

            // FIX: Height uses pipe-separated numeric format "4.6|5.0" now
            var heightVal = $('#filterHeight').val();
            var heightFirst = '';
            var heightSecond = '';
            if (heightVal) {
                var hParts = heightVal.split('|');
                heightFirst = hParts[0] || '';
                heightSecond = hParts[1] || '';
                chips.push({ key: 'filterHeight', label: 'Height: ' + $('#filterHeight option:selected').text() });
            }

            var religion = $('#filterReligion').val();
            if (religion) chips.push({ key: 'filterReligion', label: 'Religion: ' + $('#filterReligion option:selected').text() });

            var caste = $('#filterCaste').val();
            if (caste) chips.push({ key: 'filterCaste', label: 'Caste: ' + $('#filterCaste option:selected').text() });

            var location = $('#filterLocation').val();
            var advState = $('#advState').val();
            var combinedLoc = location;
            if (advState) {
                combinedLoc = combinedLoc ? (combinedLoc + ' ' + advState) : advState;
            }
            if (location) chips.push({ key: 'filterLocation', label: 'Loc: ' + location });
            if (advState) chips.push({ key: 'advState', label: 'State: ' + advState });

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
                refineheightSelectPartnerFirst: heightFirst,
                refineheightSelectPartnerSecond: heightSecond,
                refineageFrom: ageMin || '',
                refineageTo: ageMax || '',
                refinematerialStatus: marital || '',
                refinereligionSelectPartner: religion || '',
                casteSelect: caste || '',
                refineoccupationCategorySelect: occ || '',
                checkBoxDistrict: combinedLoc || '',
                profileId: profId || '',
                nameSearch: profName || '',
                regDate: regDate || '',
                dob: dob || '',
                education: edu || '',
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
                $('#filterAgeMin').val('');
                $('#filterAgeMax').val('');
            } else if (key.startsWith('chk')) {
                $('#' + key).prop('checked', false);
            } else {
                $('#' + key).val('');
                if (key === 'filterReligion') {
                    $('#filterCaste').html('<option value="">Any Caste</option>');
                }
            }
            applyAdvanceSearch();
        };

        // Clear All Advance Filters
        window.clearAllAdvanceFilters = function() {
            $('#filterAgeMin').val('');
            $('#filterAgeMax').val('');
            $('#filterHeight').val('');
            $('#filterReligion').val('');
            // FIX: Reset caste and reload empty options
            $('#filterCaste').html('<option value="">Any Caste</option>');
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
            var casteText = $('#filterCaste option:selected').text() || '';
            var casteFilter = (casteText === 'Any Caste' || casteText === 'Loading castes...') ? '' : casteText.toLowerCase();
            var relText = $('#filterReligion option:selected').text() || '';
            var religionFilter = (relText === 'Any Religion') ? '' : relText.toLowerCase();
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

        // Tab Switcher for Shortlist Modal
        window.show1 = function(txt) {
            $('#menu1').hide();
            $('#menu2').hide();
            $('#menu3').hide();
            $('#' + txt).show();
        };

        $(document).ready(function() {
            // Dynamically load castes when religion changes
            $('#filterReligion').on('change', function() {
                var religionId = $(this).val();
                var $casteSelect = $('#filterCaste');
                $casteSelect.html('<option value="">Loading castes...</option>');
                if (religionId) {
                    $.ajax({
                        url: "${pageContext.request.contextPath}/educationOccupation/loadCasteAccordingToReligion",
                        type: "GET",
                        data: { id: religionId },
                        dataType: "json",
                        success: function(data) {
                            $casteSelect.html('<option value="">Any Caste</option>');
                            if (data && data.length) {
                                $.each(data, function(index, caste) {
                                    $casteSelect.append($('<option>', {
                                        value: caste.caste_id,
                                        text: caste.caste_name
                                    }));
                                });
                            }
                        },
                        error: function() {
                            $casteSelect.html('<option value="">Any Caste</option>');
                        }
                    });
                } else {
                    $casteSelect.html('<option value="">Any Caste</option>');
                }
            });

            // Pressing Enter in location input triggers search
            $('#filterLocation').on('keypress', function(e) {
                if (e.which === 13) {
                    e.preventDefault();
                    applyAdvanceSearch();
                }
            });

            // Default to Contact Details tab whenever Shortlist modal opens as requested
            $('#myModal').on('show.bs.modal', function() {
                window.show1('menu3');
            });

            // Ensure Landmark is always displayed in Contact Details
            var contactElem = document.getElementById('menu3ViewContact');
            if (contactElem && window.MutationObserver) {
                var observer = new MutationObserver(function() {
                    var html = $('#menu3ViewContact').html();
                    if (html && html.indexOf('Address:') !== -1 && html.indexOf('Landmark') === -1) {
                        observer.disconnect();
                        $('#menu3ViewContact').html(html + 'Landmark : <br>');
                        observer.observe(contactElem, { childList: true });
                    }
                });
                observer.observe(contactElem, { childList: true });
            }

            $(document).on('click', '.btn-load-more-floating', function(e) {
                e.preventDefault();
                window.loadLatestMatches();
            });
        });
    </script>
</body>
</html>
