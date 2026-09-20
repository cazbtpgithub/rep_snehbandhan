<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>SnehBandhan - Upgrade Membership</title>

    <!-- Google Fonts -->
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;500;600;700;800&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="resources/Template/css/font-awesome.min.css">
    <link rel="stylesheet" href="resources/Template/css/bootstrap.min.css">
    <link rel="stylesheet" href="resources/customcss/dashboard_new.css">

    <script src="resources/Template/js/jquery-min.js"></script>
    <script src="resources/Template/js/tether.min.js"></script>
    <script src="resources/Template/js/bootstrap.min.js"></script>
    <script src="resources/upgradePackage/upgradePackage.js"></script>
    <script src="resources/fcm/fcmLogout.js"></script>

    <!-- Firebase SDK & Init -->
    <script src="https://www.gstatic.com/firebasejs/3.7.0/firebase.js"></script>
    <script>
      var config = {
          apiKey: "AIzaSyATshbLVZDikxhaqstwaqTPCMHNr01An5E",
          authDomain: "sneh-fc203.firebaseapp.com",
          databaseURL: "https://sneh-fc203.firebaseio.com",
          projectId: "sneh-fc203",
          storageBucket: "sneh-fc203.appspot.com",
          messagingSenderId: "429812797232"
      };
      if (!firebase.apps.length) {
          firebase.initializeApp(config);
      }
    </script>

    <style>
        :root {
            --primary: #d21a5a;
            --primary-hover: #b31349;
            --primary-light: #fdf2f6;
            --accent: #f59e0b;
            --text-dark: #1f2937;
            --text-muted: #6b7280;
            --border-color: #f0e4e8;
            --bg-page: #f8fafc;
        }

        body {
            background-color: var(--bg-page);
            font-family: 'Inter', -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, sans-serif;
            color: var(--text-dark);
            margin: 0;
            padding-top: 72px;
            min-height: 100vh;
            display: flex;
            flex-direction: column;
        }

        /* 1. Modern Header / Navbar */
        .dash-navbar {
            display: flex;
            align-items: center;
            justify-content: space-between;
            padding: 0 32px;
            background: #ffffff;
            border-bottom: 1px solid var(--border-color);
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.03);
            position: fixed;
            top: 0; left: 0; right: 0;
            width: 100%;
            box-sizing: border-box;
            z-index: 1000;
            height: 72px;
        }

        .dash-navbar .logo img {
            height: 44px;
            width: auto;
            max-width: 220px;
            object-fit: contain;
        }

        .dash-nav-actions {
            display: flex;
            align-items: center;
            gap: 16px;
        }

        .btn-back-home {
            background: #ffffff;
            color: #374151 !important;
            border: 1px solid #e5e7eb;
            padding: 9px 18px;
            border-radius: 25px;
            font-weight: 600;
            font-size: 13px;
            display: inline-flex;
            align-items: center;
            gap: 8px;
            text-decoration: none !important;
            transition: all 0.2s ease;
            box-shadow: 0 1px 3px rgba(0,0,0,0.05);
        }

        .btn-back-home:hover {
            background: #f9fafb;
            color: var(--primary) !important;
            border-color: var(--primary);
            transform: translateY(-1px);
        }

        /* User Profile Pill & Dropdown */
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
            border: 1px solid var(--border-color);
            cursor: pointer;
            transition: all 0.2s ease;
            user-select: none;
        }

        .user-profile-pill:hover,
        .user-dropdown.open .user-profile-pill,
        .user-dropdown.show .user-profile-pill {
            border-color: var(--primary);
            box-shadow: 0 3px 12px rgba(210, 26, 90, 0.15);
        }

        .user-profile-pill.dropdown-toggle::after {
            display: none !important;
        }

        .user-profile-pill img {
            width: 36px;
            height: 36px;
            border-radius: 50%;
            object-fit: cover;
        }

        .user-profile-pill .user-info {
            display: flex;
            flex-direction: column;
        }

        .user-profile-pill .user-name {
            font-weight: 600;
            font-size: 13px;
            color: var(--text-dark);
            line-height: 1.2;
        }

        .user-profile-pill .user-role {
            font-size: 10px;
            color: var(--primary);
            font-weight: 600;
        }

        .user-profile-pill .user-pill-arrow {
            color: #888888;
            font-size: 13px;
            margin-left: 4px;
            transition: transform 0.25s ease, color 0.2s ease;
        }

        .user-dropdown.open .user-pill-arrow,
        .user-dropdown.show .user-pill-arrow {
            transform: rotate(180deg);
            color: var(--primary);
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
            from { opacity: 0; transform: translateY(-8px); }
            to { opacity: 1; transform: translateY(0); }
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
            color: var(--primary);
            font-weight: 600;
            margin-top: 3px;
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

        .user-dropdown-item {
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

        .user-dropdown-item i {
            width: 18px;
            font-size: 15px;
            text-align: center;
            color: #6b7280;
            transition: color 0.15s ease;
        }

        .user-dropdown-item:hover {
            background-color: #fdf2f6 !important;
            color: var(--primary) !important;
            padding-left: 22px !important;
        }

        .user-dropdown-item:hover i {
            color: var(--primary) !important;
        }

        .user-dropdown-item.user-logout-item {
            color: #dc2626 !important;
            font-weight: 600 !important;
        }

        .user-dropdown-item.user-logout-item i {
            color: #dc2626 !important;
        }

        .user-dropdown-item.user-logout-item:hover {
            background-color: #fef2f2 !important;
            color: #b91c1c !important;
            padding-left: 22px !important;
        }

        /* 2. Hero Section */
        .upgrade-hero {
            background: linear-gradient(135deg, #fff1f5 0%, #ffffff 50%, #fdf2f8 100%);
            border-bottom: 1px solid #fce7ef;
            padding: 48px 24px 36px;
            text-align: center;
            position: relative;
        }

        .upgrade-hero-badge {
            display: inline-flex;
            align-items: center;
            gap: 8px;
            padding: 6px 16px;
            background: #ffe4ec;
            color: var(--primary);
            border-radius: 30px;
            font-size: 12px;
            font-weight: 700;
            text-transform: uppercase;
            letter-spacing: 0.5px;
            margin-bottom: 16px;
        }

        .upgrade-hero-title {
            font-size: 32px;
            font-weight: 800;
            color: #111827;
            margin: 0 0 12px;
            line-height: 1.25;
        }

        .upgrade-hero-title span {
            background: linear-gradient(135deg, #d21a5a, #e11d48);
            -webkit-background-clip: text;
            -webkit-text-fill-color: transparent;
        }

        .upgrade-hero-desc {
            font-size: 15px;
            color: #4b5563;
            max-width: 680px;
            margin: 0 auto 24px;
            line-height: 1.6;
        }

        .trust-pills {
            display: flex;
            align-items: center;
            justify-content: center;
            flex-wrap: wrap;
            gap: 16px;
        }

        .trust-pill {
            display: inline-flex;
            align-items: center;
            gap: 7px;
            font-size: 13px;
            font-weight: 600;
            color: #374151;
            background: #ffffff;
            padding: 6px 14px;
            border-radius: 20px;
            border: 1px solid #e5e7eb;
            box-shadow: 0 1px 3px rgba(0,0,0,0.04);
        }

        .trust-pill i {
            color: #10b981;
        }

        /* 3. Main Container */
        .upgrade-container {
            max-width: 1200px;
            margin: 0 auto;
            padding: 36px 20px 60px;
            width: 100%;
            box-sizing: border-box;
            flex: 1;
        }

        /* Floating Alert Banners */
        .custom-alert {
            display: none;
            border-radius: 12px;
            padding: 14px 20px;
            margin-bottom: 24px;
            font-size: 14px;
            font-weight: 500;
            box-shadow: 0 4px 15px rgba(0,0,0,0.06);
            align-items: center;
            gap: 12px;
        }

        .custom-alert-danger {
            background: #fef2f2;
            border: 1px solid #fecaca;
            color: #991b1b;
        }

        .custom-alert-success {
            background: #f0fdf4;
            border: 1px solid #bbf7d0;
            color: #166534;
        }

        /* 4. Pricing Cards Grid */
        .plans-grid {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(280px, 1fr));
            gap: 24px;
            margin-bottom: 40px;
            align-items: stretch;
        }

        .plan-card {
            background: #ffffff;
            border-radius: 20px;
            border: 1px solid #e5e7eb;
            box-shadow: 0 4px 20px rgba(0, 0, 0, 0.04);
            padding: 32px 24px 28px;
            display: flex;
            flex-direction: column;
            position: relative;
            transition: all 0.3s cubic-bezier(0.16, 1, 0.3, 1);
        }

        .plan-card:hover {
            transform: translateY(-8px);
            box-shadow: 0 16px 36px rgba(210, 26, 90, 0.12);
            border-color: #fbcfe8;
        }

        .plan-card.featured {
            border: 2px solid var(--primary);
            box-shadow: 0 12px 32px rgba(210, 26, 90, 0.15);
            background: linear-gradient(180deg, #fffbfa 0%, #ffffff 100%);
        }

        .featured-ribbon {
            position: absolute;
            top: -14px;
            left: 50%;
            transform: translateX(-50%);
            background: linear-gradient(135deg, #d21a5a, #f43f5e);
            color: #ffffff;
            font-size: 11px;
            font-weight: 700;
            text-transform: uppercase;
            letter-spacing: 0.8px;
            padding: 4px 18px;
            border-radius: 20px;
            box-shadow: 0 4px 12px rgba(210, 26, 90, 0.35);
            white-space: nowrap;
        }

        .plan-header {
            text-align: center;
            border-bottom: 1px solid #f3f4f6;
            padding-bottom: 20px;
            margin-bottom: 20px;
        }

        .plan-name {
            font-size: 20px;
            font-weight: 700;
            color: #111827;
            margin: 0 0 10px;
            text-transform: uppercase;
            letter-spacing: 0.5px;
        }

        .plan-price-wrapper {
            display: flex;
            align-items: baseline;
            justify-content: center;
            gap: 4px;
            margin-bottom: 6px;
        }

        .plan-currency {
            font-size: 24px;
            font-weight: 700;
            color: var(--primary);
        }

        .plan-amount {
            font-size: 38px;
            font-weight: 800;
            color: #111827;
            line-height: 1;
        }

        .plan-validity {
            display: inline-block;
            font-size: 12px;
            font-weight: 600;
            color: #6b7280;
            background: #f3f4f6;
            padding: 3px 10px;
            border-radius: 12px;
        }

        .plan-features {
            list-style: none;
            padding: 0;
            margin: 0 0 28px;
            flex: 1;
        }

        .plan-features li {
            display: flex;
            align-items: center;
            gap: 12px;
            font-size: 14px;
            color: #4b5563;
            padding: 10px 0;
            border-bottom: 1px solid #f9fafb;
        }

        .plan-features li:last-child {
            border-bottom: none;
        }

        .plan-features li i {
            color: #10b981;
            font-size: 16px;
            flex-shrink: 0;
        }

        .plan-features li b {
            color: #111827;
            font-weight: 600;
        }

        .btn-upgrade-plan {
            width: 100%;
            padding: 13px 20px;
            border-radius: 30px;
            font-weight: 700;
            font-size: 14px;
            text-transform: uppercase;
            letter-spacing: 0.5px;
            border: none;
            cursor: pointer;
            background: linear-gradient(135deg, var(--primary), #e11d48);
            color: #ffffff;
            box-shadow: 0 6px 18px rgba(210, 26, 90, 0.3);
            transition: all 0.2s ease;
        }

        .btn-upgrade-plan:hover {
            transform: translateY(-2px);
            box-shadow: 0 8px 24px rgba(210, 26, 90, 0.45);
            background: linear-gradient(135deg, var(--primary-hover), #be123c);
        }

        /* 5. Offline Kit Activation Card (Right/Special Column) */
        .kit-activation-card {
            background: linear-gradient(145deg, #1e293b, #0f172a);
            color: #ffffff;
            border-radius: 20px;
            padding: 32px 24px 28px;
            display: flex;
            flex-direction: column;
            box-shadow: 0 10px 30px rgba(15, 23, 42, 0.2);
            position: relative;
        }

        .kit-activation-card:hover {
            transform: translateY(-8px);
            box-shadow: 0 16px 40px rgba(15, 23, 42, 0.3);
        }

        .kit-activation-card .plan-name {
            color: #ffffff;
        }

        .kit-activation-card .plan-desc {
            color: #94a3b8;
            font-size: 13px;
            text-align: center;
            margin-bottom: 20px;
            line-height: 1.5;
        }

        .kit-form-group {
            margin-bottom: 14px;
        }

        .kit-form-input {
            width: 100%;
            padding: 12px 16px;
            border-radius: 12px;
            border: 1px solid #334155;
            background: #1e293b;
            color: #ffffff;
            font-size: 14px;
            box-sizing: border-box;
            outline: none;
            transition: border-color 0.2s ease;
        }

        .kit-form-input:focus {
            border-color: #38bdf8;
            box-shadow: 0 0 0 3px rgba(56, 189, 248, 0.2);
        }

        .kit-form-input::placeholder {
            color: #64748b;
        }

        .btn-activate-kit {
            width: 100%;
            padding: 13px 20px;
            border-radius: 30px;
            font-weight: 700;
            font-size: 14px;
            text-transform: uppercase;
            letter-spacing: 0.5px;
            border: none;
            cursor: pointer;
            background: linear-gradient(135deg, #38bdf8, #2563eb);
            color: #ffffff;
            box-shadow: 0 6px 18px rgba(37, 99, 235, 0.35);
            transition: all 0.2s ease;
            margin-top: auto;
        }

        .btn-activate-kit:hover {
            transform: translateY(-2px);
            box-shadow: 0 8px 24px rgba(37, 99, 235, 0.5);
        }

        /* 6. Premium Kit Showcase Banner (REPLACES BIG UGLY IMAGE) */
        .premium-kit-showcase {
            background: linear-gradient(135deg, #ffffff 0%, #fff7f9 100%);
            border: 1px solid #fed7e2;
            border-radius: 20px;
            padding: 32px 40px;
            margin: 20px auto 40px;
            display: flex;
            align-items: center;
            justify-content: space-between;
            gap: 36px;
            box-shadow: 0 10px 30px rgba(210, 26, 90, 0.06);
            box-sizing: border-box;
        }

        .kit-showcase-text {
            flex: 1;
        }

        .kit-badge {
            display: inline-flex;
            align-items: center;
            gap: 6px;
            background: #ffe4ec;
            color: var(--primary);
            font-size: 11px;
            font-weight: 700;
            text-transform: uppercase;
            letter-spacing: 0.5px;
            padding: 4px 12px;
            border-radius: 20px;
            margin-bottom: 12px;
        }

        .kit-showcase-text h3 {
            font-size: 22px;
            font-weight: 800;
            color: #111827;
            margin: 0 0 10px;
        }

        .kit-showcase-text p {
            font-size: 14px;
            color: #4b5563;
            line-height: 1.6;
            margin: 0 0 18px;
        }

        .kit-perks-list {
            display: flex;
            flex-wrap: wrap;
            gap: 12px 20px;
            padding: 0;
            margin: 0;
            list-style: none;
        }

        .kit-perks-list li {
            display: inline-flex;
            align-items: center;
            gap: 7px;
            font-size: 13px;
            font-weight: 600;
            color: #374151;
        }

        .kit-perks-list li i {
            color: #10b981;
            font-size: 15px;
        }

        .kit-showcase-visual {
            flex-shrink: 0;
            display: flex;
            align-items: center;
            justify-content: center;
            max-width: 320px;
        }

        /* CONTROLLED, ELEGANT IMAGE SIZING - NEVER STRETCHES! */
        .kit-showcase-img {
            max-height: 200px;
            max-width: 280px;
            width: auto;
            height: auto;
            object-fit: contain;
            border-radius: 12px;
            filter: drop-shadow(0 10px 20px rgba(0, 0, 0, 0.1));
            transition: transform 0.3s ease;
        }

        .kit-showcase-img:hover {
            transform: translateY(-4px) scale(1.02);
        }

        /* 7. Footer */
        .upgrade-footer {
            background: #ffffff;
            border-top: 1px solid #e5e7eb;
            padding: 24px;
            text-align: center;
            margin-top: auto;
            color: #6b7280;
            font-size: 13px;
        }

        .upgrade-footer a {
            color: var(--primary);
            text-decoration: none;
            font-weight: 600;
        }

        .upgrade-footer a:hover {
            text-decoration: underline;
        }

        @media (max-width: 850px) {
            .premium-kit-showcase {
                flex-direction: column-reverse;
                text-align: center;
                padding: 24px;
            }
            .kit-perks-list {
                justify-content: center;
            }
            .kit-showcase-img {
                max-height: 160px;
            }
            .upgrade-hero-title {
                font-size: 26px;
            }
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

        <div class="dash-nav-actions">
            <a href="<c:url value='/userHome'/>" class="btn-back-home">
                <i class="fa fa-arrow-left"></i> Back to Dashboard
            </a>

            <!-- User Profile Pill & Dropdown -->
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
                    <a class="dropdown-item user-dropdown-item" href="<c:url value='/userHome'/>">
                        <i class="fa fa-home"></i> Dashboard
                    </a>
                    <a class="dropdown-item user-dropdown-item" href="<c:url value='/editProfile'/>">
                        <i class="fa fa-user-circle-o"></i> View / Edit Profile
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

    <!-- 2. Hero Section -->
    <section class="upgrade-hero">
        <span class="upgrade-hero-badge"><i class="fa fa-crown"></i> SnehBandhan Premium Plans</span>
        <h1 class="upgrade-hero-title">Upgrade Your Plan & <span>Connect Directly</span></h1>
        <p class="upgrade-hero-desc">Unlock verified contact numbers, direct messaging, unlimited profile views, and personalized matchmaking support to find your life partner faster.</p>
        <div class="trust-pills">
            <span class="trust-pill"><i class="fa fa-check-circle"></i> 100% Verified Profiles</span>
            <span class="trust-pill"><i class="fa fa-lock"></i> 256-Bit SSL Secure Payment</span>
            <span class="trust-pill"><i class="fa fa-bolt"></i> Instant Plan Activation</span>
            <span class="trust-pill"><i class="fa fa-shield"></i> Complete Privacy Protection</span>
        </div>
    </section>

    <!-- 3. Main Content Container -->
    <main class="upgrade-container">

        <!-- Alerts -->
        <div class="custom-alert custom-alert-danger" id="packFail">
            <i class="fa fa-exclamation-circle" style="font-size: 18px;"></i>
            <span><strong>Invalid Details:</strong> Kit Id or Password is incorrect. Please try again.</span>
        </div>
        <div class="custom-alert custom-alert-danger" id="packAvailable">
            <i class="fa fa-info-circle" style="font-size: 18px;"></i>
            <span><strong>Kit Already Used:</strong> This Kit is already in use. Please contact Customer Support.</span>
        </div>
        <div class="custom-alert custom-alert-success" id="packSuccess">
            <i class="fa fa-check-circle" style="font-size: 18px;"></i>
            <span><strong>Success!</strong> Your package has been upgraded successfully. Thank you!</span>
        </div>
        <div class="custom-alert custom-alert-danger" id="transactionError">
            <i class="fa fa-times-circle" style="font-size: 18px;"></i>
            <span><strong>Transaction Failed:</strong> Incorrect Email or Mobile number. Please update your profile.</span>
        </div>

        <!-- Hidden Payment Gateway Form -->
        <form action="PaymentGatewayController" method="post" id="paymentForm">
            <input type="text" name="pack_id" id="pack_id" style="display: none;">
            <input type="submit" id="submitPayment" style="display: none;">
        </form>

        <!-- Pricing Cards Grid -->
        <div class="plans-grid">

            <!-- 1. Offline Kit Activation Card -->
            <div class="kit-activation-card">
                <div class="plan-header" style="border-color: #334155;">
                    <div style="font-size: 32px; color: #38bdf8; margin-bottom: 8px;"><i class="fa fa-key"></i></div>
                    <h3 class="plan-name">Kit Activation</h3>
                    <p class="plan-desc">Have a physical franchise kit or offline voucher? Enter your credentials below.</p>
                </div>
                
                <div class="kit-form-group">
                    <input type="text" id="kitId" name="kitId" placeholder="Enter Kit ID" class="kit-form-input">
                </div>
                <div class="kit-form-group">
                    <input type="password" name="kitPassword" id="kitPassword" placeholder="Enter Kit Password" class="kit-form-input">
                </div>

                <div style="flex: 1;"></div>
                <button type="button" class="btn-activate-kit" onclick="uploadKitIdAndPassword();">
                    <i class="fa fa-bolt"></i> Activate Kit
                </button>
            </div>

            <!-- 2. Dynamic Online Packages List -->
            <c:forEach var="pack" items="${packages}" varStatus="theCount">
                <div class="plan-card <c:if test='${theCount.count == 2 || (theCount.count % 2) == 0}'>featured</c:if>">
                    <c:if test="${theCount.count == 2 || (theCount.count % 2) == 0}">
                        <div class="featured-ribbon"><i class="fa fa-star"></i> Most Popular</div>
                    </c:if>
                    
                    <div class="plan-header">
                        <h3 class="plan-name">${pack.package_Name}</h3>
                        <div class="plan-price-wrapper">
                            <span class="plan-currency">&#8377;</span>
                            <span class="plan-amount">${pack.amount}</span>
                        </div>
                        <span class="plan-validity"><i class="fa fa-calendar-check-o"></i> ${pack.no_of_days} Days Validity</span>
                    </div>

                    <ul class="plan-features">
                        <li>
                            <i class="fa fa-check-circle"></i>
                            <span><b>${pack.no_of_profiles}</b> Profile Views</span>
                        </li>
                        <li>
                            <i class="fa fa-check-circle"></i>
                            <span><b>${pack.no_of_msg}</b> Contact Numbers</span>
                        </li>
                        <li>
                            <i class="fa fa-check-circle"></i>
                            <span><b>${pack.no_of_email}</b> Verified Email IDs</span>
                        </li>
                        <li>
                            <i class="fa fa-check-circle"></i>
                            <span><b>${pack.no_of_days}</b> Days Premium Access</span>
                        </li>
                        <li>
                            <i class="fa fa-check-circle"></i>
                            <span>Direct Chat & Messaging</span>
                        </li>
                        <li>
                            <i class="fa fa-check-circle"></i>
                            <span>Priority Profile Visibility</span>
                        </li>
                    </ul>

                    <button type="button" onclick="PaymentGatewayCall('${pack.package_id}');" class="btn-upgrade-plan">
                        <i class="fa fa-crown"></i> Upgrade Now
                    </button>
                </div>
            </c:forEach>
        </div>

        <!-- 4. Premium Kit Showcase Banner (Properly Constrained, Never Stretched!) -->
        <section class="premium-kit-showcase">
            <div class="kit-showcase-text">
                <span class="kit-badge"><i class="fa fa-gift"></i> Membership Welcome Kit</span>
                <h3>Receive Our Exclusive Marriage Assistance Kit</h3>
                <p>Upgraded members receive verified matchmaking privileges, direct phone & email access, verified credibility badges, and personalized customer care support throughout their journey.</p>
                <ul class="kit-perks-list">
                    <li><i class="fa fa-check-circle"></i> Direct Contact Exchange</li>
                    <li><i class="fa fa-check-circle"></i> Verified Profile Badge</li>
                    <li><i class="fa fa-check-circle"></i> Top Search Placement</li>
                    <li><i class="fa fa-check-circle"></i> Relationship Assistance</li>
                </ul>
            </div>
            <div class="kit-showcase-visual">
                <img src="${pageContext.request.contextPath}/resources/images/kit.png" alt="SnehBandhan Kit" class="kit-showcase-img" onerror="this.parentElement.style.display='none';">
            </div>
        </section>

    </main>

    <!-- Transaction Summary Modal -->
    <div id="TransactionStatus" class="modal fade" role="dialog" tabindex="-1">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content" style="border-radius: 16px; border: none; overflow: hidden; box-shadow: 0 20px 40px rgba(0,0,0,0.15);">
                <div class="modal-header" style="background: linear-gradient(135deg, var(--primary), #e11d48); color: #ffffff; padding: 18px 24px;">
                    <h5 class="modal-title" style="font-weight: 700; margin: 0;"><i class="fa fa-receipt"></i> Transaction Summary</h5>
                    <button type="button" class="close" data-dismiss="modal" style="color: #ffffff; opacity: 0.9; text-shadow: none;">&times;</button>
                </div>
                <div class="modal-body" style="padding: 24px;">
                    <div style="background: #f8fafc; border: 1px solid #e2e8f0; border-radius: 12px; padding: 16px; line-height: 1.8; font-size: 14px;">
                        <div><strong>Amount Paid:</strong> &#8377; ${Amount}</div>
                        <div><strong>Package:</strong> ${productinfo}</div>
                        <div><strong>Transaction ID:</strong> <span style="font-family: monospace; color: #4b5563;">${txnid}</span></div>
                        <div><strong>Status:</strong> <span class="badge badge-success" style="padding: 4px 10px; border-radius: 12px;">${status}</span></div>
                        <c:if test="${not empty additionalCharges}">
                            <div><strong>Additional Charges:</strong> ${additionalCharges}</div>
                        </c:if>
                    </div>
                </div>
                <div class="modal-footer" style="border-top: 1px solid #e5e7eb; padding: 14px 24px;">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal" style="border-radius: 20px; padding: 7px 20px; font-weight: 600;">Close</button>
                    <a href="<c:url value='/userHome'/>" class="btn" style="background: var(--primary); color: #fff; border-radius: 20px; padding: 7px 20px; font-weight: 600;">Go to Dashboard</a>
                </div>
            </div>
        </div>
    </div>

    <!-- 5. Footer -->
    <footer class="upgrade-footer">
        <p style="margin: 0;">
            &copy; 2026 SnehBandhan Matrimony. All rights reserved. <a href="<c:url value='/userHome'/>">Return to Home</a>
        </p>
    </footer>

    <!-- Scripts -->
    <script type="text/javascript">
        // Global Logout Function
        window.performLogout = function(e) {
            if (e && e.preventDefault) e.preventDefault();
            
            // Delete Firebase user session if any
            try {
                if (typeof Demo === 'function') {
                    new Demo('${userEmail}', '${userPass}');
                }
            } catch (err) {
                console.log(err);
            }

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

        function Demo(email, password) {
            try {
                if (firebase && firebase.auth() && firebase.auth().currentUser) {
                    firebase.auth().currentUser.delete();
                }
            } catch (e) {
                return false;
            }
        }

        // Payment Gateway Call
        function PaymentGatewayCall(package_id) {
            var oMyForm = new FormData();
            var arr = { package_id: package_id };
            oMyForm.append("jsonObjectData", JSON.stringify(arr));

            $.ajax({
                dataType: 'json',
                url: "paymentGatewaySession",
                type: "POST",
                data: oMyForm,
                processData: false,
                contentType: false,
                success: function(data) {
                    if (data == false) {
                        $("#transactionError").hide().slideDown().fadeOut(5000);
                        return false;
                    } else {
                        $('#pack_id').val(package_id);
                        $('#submitPayment').click();
                    }
                },
                error: function(xhr, ajaxOptions, thrownError) {
                    console.log("Error occured in payment gateway session");
                }
            });
        }

        // Dropdown & UI Initialization
        $(document).ready(function() {
            // Profile Dropdown Toggle
            $(document).on('click', '.user-dropdown .user-profile-pill', function(e) {
                e.preventDefault();
                e.stopPropagation();
                var $dropdown = $(this).closest('.user-dropdown');
                var $menu = $dropdown.find('.user-profile-dropdown-menu');
                var isOpen = $dropdown.hasClass('open') || $menu.hasClass('show');
                
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
    </script>

    <c:if test="${not empty status}">
        <script type="text/javascript">
            $(document).ready(function() {
                $("#TransactionStatus").modal('show');
                $.ajax({
                    url: "removeTransactionSummary",
                    type: "POST",
                    processData: false,
                    contentType: false,
                    success: function(data) {},
                    error: function(xhr, ajaxOptions, thrownError) {
                        console.log("Error occurred removing transaction summary");
                    }
                });
            });
        </script>
    </c:if>

</body>
</html>