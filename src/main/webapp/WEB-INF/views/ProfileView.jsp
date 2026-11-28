<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html lang="en">
<head>
<!-- Google tag (gtag.js) -->
<script async src="https://www.googletagmanager.com/gtag/js?id=G-578JZ59RJR"></script>
<script>
  window.dataLayer = window.dataLayer || [];
  function gtag(){dataLayer.push(arguments);}
  gtag('js', new Date());

  gtag('config', 'G-578JZ59RJR');
</script>
<title>Snehbandhan - View Profile</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta http-equiv="x-ua-compatible" content="ie=edge">

<!-- Stylesheets -->
<link rel="stylesheet" href="resources/Template/css/bootstrap.min.css">
<link rel="stylesheet" href="resources/Template/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="resources/confirmBox/jquery-confirm.css" media="screen">

<!-- Google Fonts -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700;800&display=swap" rel="stylesheet">

<script src="resources/ProfileViewer/profileView.js"></script>

<script>
	var useridArray = new Array();
	var activeUser = new Array();
</script>

<!-- Firebase initialization -->
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
  firebase.initializeApp(config);
</script>

<style>
:root {
    --primary: #e11d48;
    --primary-hover: #be123c;
    --primary-light: #fff0f3;
    --primary-border: #fbcfe8;
    --bg-body: #f8fafc;
    --card-bg: #ffffff;
    --text-main: #0f172a;
    --text-muted: #64748b;
    --text-light: #94a3b8;
    --border-color: #f1f5f9;
    --green-success: #16a34a;
    --green-bg: #f0fdf4;
    --green-border: #bbf7d0;
}

body {
    background-color: var(--bg-body);
    font-family: 'Inter', system-ui, -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, sans-serif;
    color: var(--text-main);
    margin: 0;
    padding: 0;
}

/* Header Navbar */
.modern-navbar {
    background: #ffffff;
    border-bottom: 1px solid var(--border-color);
    padding: 12px 32px;
    display: flex;
    align-items: center;
    justify-content: space-between;
    box-shadow: 0 1px 4px rgba(0,0,0,0.03);
    position: sticky;
    top: 0;
    z-index: 1000;
}
.brand-logo img {
    height: 48px;
    width: auto;
}
.modern-nav-center {
    display: flex;
    align-items: center;
    gap: 24px;
}
.modern-nav-item {
    color: var(--text-muted);
    font-weight: 500;
    font-size: 14px;
    text-decoration: none !important;
    display: flex;
    align-items: center;
    gap: 8px;
    padding: 8px 14px;
    border-radius: 10px;
    transition: all 0.2s ease;
}
.modern-nav-item:hover {
    color: var(--primary);
    background: var(--primary-light);
}
.modern-nav-badge {
    background: var(--primary);
    color: #fff;
    font-size: 11px;
    font-weight: 700;
    padding: 2px 7px;
    border-radius: 10px;
}
.modern-nav-right {
    display: flex;
    align-items: center;
    gap: 16px;
}
.btn-upgrade-plan {
    border: 1px solid var(--primary);
    color: var(--primary) !important;
    background: #fff;
    padding: 8px 18px;
    border-radius: 20px;
    font-weight: 600;
    font-size: 13px;
    display: flex;
    align-items: center;
    gap: 8px;
    text-decoration: none !important;
    transition: all 0.2s ease;
}
.btn-upgrade-plan:hover {
    background: var(--primary-light);
}
.nav-icon-badge-btn {
    position: relative;
    color: var(--text-muted);
    font-size: 18px;
    cursor: pointer;
    padding: 6px;
}
.nav-icon-badge-btn .badge-dot {
    position: absolute;
    top: 2px;
    right: 2px;
    background: var(--primary);
    color: #fff;
    font-size: 10px;
    font-weight: 700;
    width: 16px;
    height: 16px;
    border-radius: 50%;
    display: flex;
    align-items: center;
    justify-content: center;
}
.nav-user-avatar {
    display: flex;
    align-items: center;
    gap: 10px;
    cursor: pointer;
}
.nav-user-avatar img {
    width: 38px;
    height: 38px;
    border-radius: 50%;
    object-fit: cover;
}

/* Sub-header */
.sub-header-bar {
    max-width: 1400px;
    margin: 0 auto;
    padding: 20px 24px 12px;
}
.back-link {
    color: var(--primary);
    font-size: 14px;
    font-weight: 600;
    text-decoration: none !important;
    display: inline-flex;
    align-items: center;
    gap: 6px;
}
.back-link:hover {
    color: var(--primary-hover);
}

/* Main Layout Grid */
.profile-container {
    max-width: 1400px;
    margin: 0 auto;
    padding: 0 24px 40px;
}

.modern-card {
    background: var(--card-bg);
    border-radius: 16px;
    border: 1px solid var(--border-color);
    box-shadow: 0 2px 10px rgba(0,0,0,0.03);
    padding: 20px;
    margin-bottom: 20px;
}
.card-header-title {
    font-size: 16px;
    font-weight: 700;
    color: var(--text-main);
    margin-bottom: 16px;
    display: flex;
    align-items: center;
    justify-content: space-between;
}

/* Left Column */
.main-profile-img-container {
    position: relative;
    border-radius: 14px;
    overflow: hidden;
    margin-bottom: 12px;
    background: linear-gradient(180deg, #fff5f8 0%, #f8fafc 100%);
    box-shadow: 0 4px 14px rgba(15, 23, 42, 0.06);
}
.main-profile-img-container img {
    width: 100%;
    height: 380px;
    object-fit: cover;
    object-position: top center;
    display: block;
    cursor: pointer;
    transition: transform 0.35s ease;
}
.main-profile-img-container:hover img {
    transform: scale(1.03);
}
.badge-verified-top {
    position: absolute;
    bottom: 14px;
    left: 14px;
    top: auto;
    background: rgba(22, 163, 74, 0.94);
    backdrop-filter: blur(4px);
    color: #fff;
    font-size: 12px;
    font-weight: 600;
    padding: 5px 12px;
    border-radius: 20px;
    display: flex;
    align-items: center;
    gap: 6px;
    box-shadow: 0 3px 10px rgba(0, 0, 0, 0.2);
    z-index: 2;
}
.badge-photos-count {
    position: absolute;
    bottom: 14px;
    right: 14px;
    left: auto;
    background: rgba(15, 23, 42, 0.75);
    backdrop-filter: blur(4px);
    color: #fff;
    font-size: 12px;
    font-weight: 500;
    padding: 4px 12px;
    border-radius: 20px;
    display: flex;
    align-items: center;
    gap: 6px;
    z-index: 2;
}
.gallery-thumbs-row {
    display: flex;
    align-items: center;
    gap: 8px;
    overflow-x: auto;
    padding: 4px 0;
}
.thumb-item img {
    width: 58px;
    height: 58px;
    border-radius: 10px;
    object-fit: cover;
    object-position: top center;
    cursor: pointer;
    border: 2px solid transparent;
    transition: all 0.2s;
}
.thumb-item img:hover {
    border-color: var(--primary);
    transform: translateY(-1px);
}

.protected-contact-box {
    background: #fff5f7;
    border: 1px solid #fecdd3;
    border-radius: 14px;
    padding: 16px;
    display: flex;
    align-items: flex-start;
    gap: 12px;
    margin-bottom: 20px;
}
.protected-contact-box i {
    color: var(--primary);
    font-size: 20px;
    margin-top: 2px;
}
.protected-contact-box div {
    font-size: 13px;
    color: #9f1239;
    line-height: 1.4;
}

.ads-placeholder-box {
    background: #f1f5f9;
    border-radius: 14px;
    height: 220px;
    display: flex;
    align-items: center;
    justify-content: center;
    color: #cbd5e1;
    font-size: 28px;
    font-weight: 800;
}

/* Middle Column Header */
.profile-title-header {
    display: flex;
    align-items: flex-start;
    justify-content: space-between;
    gap: 16px;
    flex-wrap: wrap;
}
.user-name-title {
    font-size: 26px;
    font-weight: 800;
    color: var(--text-main);
    display: flex;
    align-items: center;
    gap: 10px;
    line-height: 1.25;
}
.status-dot-online {
    width: 10px;
    height: 10px;
    background: var(--green-success);
    border-radius: 50%;
    display: inline-block;
    flex-shrink: 0;
}
.user-specs-subtitle {
    font-size: 14px;
    color: var(--text-muted);
    margin: 6px 0 14px;
    line-height: 1.55;
}
.user-last-active {
    display: inline-flex;
    align-items: center;
    gap: 6px;
    font-size: 12.5px;
    font-weight: 500;
    color: var(--text-muted);
    background: #f8fafc;
    border: 1px solid var(--border-color);
    padding: 6px 12px;
    border-radius: 20px;
    white-space: nowrap;
}
.verification-badges-row {
    display: flex;
    flex-wrap: wrap;
    gap: 8px;
    margin-bottom: 20px;
}
.v-badge-item {
    background: var(--green-bg);
    color: var(--green-success);
    border: 1px solid var(--green-border);
    font-size: 12px;
    font-weight: 600;
    padding: 5px 12px;
    border-radius: 20px;
    display: inline-flex;
    align-items: center;
    gap: 6px;
}

/* Compatibility Box */
.compatibility-banner-card {
    background: linear-gradient(135deg, #fff0f5 0%, #ffffff 100%);
    border: 1px solid var(--primary-border);
    border-radius: 16px;
    padding: 20px;
    margin-bottom: 20px;
    display: flex;
    align-items: center;
    justify-content: space-between;
    flex-wrap: wrap;
    gap: 20px;
}
.comp-left-gauge {
    display: flex;
    align-items: center;
    gap: 16px;
}
.gauge-ring-box {
    width: 72px;
    height: 72px;
    border-radius: 50%;
    border: 6px solid #fecdd3;
    border-top-color: var(--primary);
    display: flex;
    align-items: center;
    justify-content: center;
    font-size: 18px;
    font-weight: 800;
    color: var(--primary);
    flex-shrink: 0;
    background: #ffffff;
}
.comp-text-title {
    font-size: 16px;
    font-weight: 700;
    color: var(--text-main);
}
.comp-text-sub {
    font-size: 13px;
    color: var(--text-muted);
    margin: 4px 0 6px;
}
.comp-link-details {
    font-size: 12.5px;
    font-weight: 600;
    color: var(--primary);
    text-decoration: none !important;
}
.comp-link-details:hover {
    color: var(--primary-hover);
    text-decoration: underline !important;
}
.top-matches-grid {
    display: grid;
    grid-template-columns: repeat(3, 1fr);
    gap: 8px 16px;
    font-size: 13px;
    color: var(--text-muted);
}
.top-match-item {
    display: flex;
    align-items: center;
    gap: 6px;
}
.top-match-item i {
    color: var(--primary);
}

/* Action Buttons */
.action-buttons-row {
    display: flex;
    gap: 12px;
    margin-bottom: 24px;
}
.btn-express-main {
    flex: 1;
    background: var(--primary);
    color: #fff !important;
    border: none;
    padding: 12px 20px;
    border-radius: 10px;
    font-weight: 600;
    font-size: 14px;
    cursor: pointer;
    display: flex;
    align-items: center;
    justify-content: center;
    gap: 8px;
    transition: all 0.2s ease;
}
.btn-express-main:hover {
    background: var(--primary-hover);
}
.btn-action-outline {
    flex: 1;
    background: #fff;
    color: var(--primary) !important;
    border: 1px solid var(--primary);
    padding: 12px 20px;
    border-radius: 10px;
    font-weight: 600;
    font-size: 14px;
    cursor: pointer;
    display: flex;
    align-items: center;
    justify-content: center;
    gap: 8px;
    transition: all 0.2s ease;
}
.btn-action-outline:hover {
    background: var(--primary-light);
}

/* Detail Cards Grid */
.details-cards-grid {
    display: grid;
    grid-template-columns: repeat(2, 1fr);
    gap: 16px;
}
@media (max-width: 768px) {
    .details-cards-grid {
        grid-template-columns: 1fr;
    }
}
.detail-card-item {
    background: var(--card-bg);
    border: 1px solid var(--border-color);
    border-radius: 14px;
    padding: 18px;
}
.detail-card-title {
    font-size: 14px;
    font-weight: 700;
    color: var(--text-main);
    margin-bottom: 14px;
    display: flex;
    align-items: center;
    gap: 8px;
}
.detail-card-title i {
    color: var(--primary);
}
.detail-kv-list {
    display: flex;
    flex-direction: column;
    gap: 10px;
    font-size: 13px;
}
.detail-kv-row {
    display: flex;
    justify-content: space-between;
}
.detail-k-label {
    color: var(--text-muted);
}
.detail-v-val {
    font-weight: 600;
    color: var(--text-main);
}

/* Right Widgets */
.member-list-item,
.similar-profile-card-item {
    display: flex;
    align-items: center;
    justify-content: space-between;
    gap: 10px;
    padding: 10px 0;
    border-bottom: 1px solid var(--border-color);
}
.member-list-item:last-child,
.similar-profile-card-item:last-child {
    border-bottom: none;
}
.member-info-left {
    display: flex;
    align-items: center;
    gap: 12px;
    min-width: 0;
    flex: 1;
}
.member-avatar {
    width: 44px;
    height: 44px;
    border-radius: 50%;
    object-fit: cover;
    object-position: top center;
    flex-shrink: 0;
    border: 2px solid #ffe4ec;
}
.member-name-text {
    font-size: 13px;
    font-weight: 600;
    color: var(--text-main);
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
}
.member-name-text a:hover {
    color: var(--primary) !important;
}
.member-sub-text {
    font-size: 11px;
    color: var(--text-muted);
    line-height: 1.35;
}
.similar-match-pill {
    background: #fce7f3;
    color: var(--primary);
    font-size: 11px;
    font-weight: 700;
    padding: 4px 9px;
    border-radius: 12px;
    white-space: nowrap;
    flex-shrink: 0;
}

.btn-view-more-full {
    width: 100%;
    background: #fff;
    border: 1px solid var(--primary);
    color: var(--primary);
    padding: 10px;
    border-radius: 10px;
    font-weight: 600;
    font-size: 13px;
    cursor: pointer;
    margin-top: 14px;
    transition: all 0.2s;
}
.btn-view-more-full:hover {
    background: var(--primary-light);
}

/* Modern Footer */
.modern-footer {
    background: #ffffff;
    border-top: 1px solid var(--border-color);
    padding: 30px 32px 24px;
    margin-top: 50px;
}
.footer-inner {
    max-width: 1400px;
    margin: 0 auto;
    display: flex;
    align-items: center;
    justify-content: space-between;
    flex-wrap: wrap;
    gap: 20px;
}
.footer-links {
    display: flex;
    gap: 24px;
    font-size: 13px;
    font-weight: 500;
}
.footer-links a {
    color: var(--text-muted);
    text-decoration: none !important;
}
.footer-links a:hover {
    color: var(--primary);
}
.footer-social-icons {
    display: flex;
    gap: 10px;
}
.social-icon-btn {
    width: 34px;
    height: 34px;
    border-radius: 50%;
    background: var(--primary);
    color: #fff !important;
    display: flex;
    align-items: center;
    justify-content: center;
    font-size: 14px;
    text-decoration: none !important;
}

/* Popup and Slider Modal Fixes */
.jconfirm .jconfirm-scrollpane {
    display: flex !important;
    align-items: center !important;
    justify-content: center !important;
}
.jconfirm .jconfirm-box-container, .jconfirm .jconfirm-box {
    width: 70% !important;
    max-width: 520px !important;
    margin: auto !important;
    border-radius: 14px !important;
}

/* ==========================================================================
   RESPONSIVE TABLET & PREMIUM MOBILE VIEW DESIGN (UI ONLY)
   ========================================================================== */
@media (max-width: 1100px) {
    .modern-navbar {
        padding: 10px 18px;
        flex-wrap: wrap;
        gap: 10px;
    }
    .modern-nav-center {
        gap: 8px;
    }
    .modern-nav-item {
        padding: 7px 10px;
        font-size: 13px;
    }
}

@media (max-width: 768px) {
    html, body {
        overflow-x: hidden !important;
        width: 100% !important;
    }

    /* 1. Sticky Mobile Header + Scrollable App Navigation Pills */
    .modern-navbar {
        padding: 10px 14px !important;
        flex-wrap: wrap !important;
        gap: 10px !important;
    }
    .modern-navbar .brand-logo {
        order: 1 !important;
    }
    .modern-navbar .brand-logo img {
        height: 36px !important;
    }
    .modern-nav-right {
        order: 2 !important;
        gap: 10px !important;
        margin-left: auto !important;
    }
    .btn-upgrade-plan {
        padding: 7px 12px !important;
        font-size: 11.5px !important;
        border-radius: 18px !important;
        gap: 5px !important;
        white-space: nowrap !important;
    }
    .nav-user-avatar img {
        width: 34px !important;
        height: 34px !important;
        object-position: top center !important;
    }
    .nav-user-avatar span {
        display: none !important;
    }
    .modern-nav-center {
        order: 3 !important;
        display: flex !important;
        width: 100% !important;
        flex: 0 0 100% !important;
        overflow-x: auto !important;
        -webkit-overflow-scrolling: touch !important;
        scrollbar-width: none !important;
        gap: 8px !important;
        padding: 6px 2px 2px 2px !important;
        border-top: 1px solid #f1f5f9 !important;
    }
    .modern-nav-center::-webkit-scrollbar {
        display: none !important;
    }
    .modern-nav-item {
        flex: 0 0 auto !important;
        padding: 7px 13px !important;
        font-size: 12.5px !important;
        border-radius: 20px !important;
        background: #faf6f8 !important;
        border: 1px solid #f0e4e8 !important;
        white-space: nowrap !important;
    }
    .modern-nav-item.active {
        background: var(--primary) !important;
        color: #ffffff !important;
        border-color: var(--primary) !important;
        box-shadow: 0 3px 10px rgba(225, 29, 72, 0.25) !important;
    }
    .modern-nav-item.active .modern-nav-badge {
        background: #ffffff !important;
        color: var(--primary) !important;
    }

    /* 2. Sub-header & Main Container */
    .sub-header-bar {
        padding: 12px 14px 8px !important;
    }
    .back-link {
        font-size: 13px !important;
        background: #ffffff !important;
        border: 1px solid #f0e4e8 !important;
        padding: 7px 14px !important;
        border-radius: 20px !important;
        box-shadow: 0 2px 6px rgba(0, 0, 0, 0.03) !important;
    }
    .profile-container {
        padding: 4px 12px 28px !important;
    }
    .profile-container > .row {
        margin-left: 0 !important;
        margin-right: 0 !important;
    }
    .profile-container > .row > [class*="col-"] {
        padding-left: 0 !important;
        padding-right: 0 !important;
    }

    /* 3. Modern Card & Profile Photo Framing */
    .modern-card {
        padding: 16px 14px !important;
        border-radius: 18px !important;
        margin-bottom: 14px !important;
        box-shadow: 0 6px 20px rgba(15, 23, 42, 0.05) !important;
    }
    .main-profile-img-container {
        border-radius: 14px !important;
    }
    .main-profile-img-container img {
        height: 330px !important;
        object-fit: cover !important;
        object-position: top center !important;
    }
    .badge-verified-top {
        bottom: 12px !important;
        left: 12px !important;
        top: auto !important;
        font-size: 11px !important;
        padding: 4px 11px !important;
    }
    .badge-photos-count {
        bottom: 12px !important;
        right: 12px !important;
        left: auto !important;
        font-size: 11px !important;
        padding: 4px 11px !important;
    }
    .thumb-item img {
        width: 60px !important;
        height: 60px !important;
        border-radius: 10px !important;
        object-position: top center !important;
    }
    .protected-contact-box {
        padding: 14px !important;
        margin-bottom: 14px !important;
        border-radius: 14px !important;
    }
    .ads-placeholder-box {
        display: none !important;
    }

    /* 4. Profile Header, Verification Badges & Compatibility Banner */
    .profile-title-header {
        flex-direction: column !important;
        align-items: flex-start !important;
        gap: 6px !important;
        margin-bottom: 12px !important;
    }
    .user-name-title {
        font-size: 21px !important;
        flex-wrap: wrap !important;
    }
    .user-specs-subtitle {
        font-size: 13px !important;
        margin: 4px 0 4px !important;
    }
    .verification-badges-row {
        gap: 6px !important;
        margin-bottom: 16px !important;
    }
    .v-badge-item {
        font-size: 11px !important;
        padding: 4px 10px !important;
    }
    .compatibility-banner-card {
        padding: 16px 14px !important;
        flex-direction: column !important;
        align-items: flex-start !important;
        gap: 14px !important;
        border-radius: 16px !important;
    }
    .comp-left-gauge {
        width: 100% !important;
        gap: 14px !important;
    }
    .gauge-ring-box {
        width: 64px !important;
        height: 64px !important;
        font-size: 16px !important;
    }
    .top-matches-grid {
        grid-template-columns: repeat(2, 1fr) !important;
        gap: 8px 14px !important;
        width: 100% !important;
    }

    /* 5. Action Buttons Row (Express Interest full-width, Shortlist + Send Message paired) */
    .action-buttons-row {
        display: grid !important;
        grid-template-columns: repeat(2, 1fr) !important;
        gap: 10px !important;
        margin-bottom: 20px !important;
    }
    .btn-express-main {
        grid-column: 1 / -1 !important;
        padding: 12px 16px !important;
        font-size: 14px !important;
        border-radius: 12px !important;
        box-shadow: 0 4px 14px rgba(225, 29, 72, 0.25) !important;
    }
    .btn-action-outline {
        padding: 10px 12px !important;
        font-size: 13px !important;
        border-radius: 12px !important;
    }

    /* 6. Details Cards & Kundali Box */
    .detail-card-item {
        padding: 15px 14px !important;
        border-radius: 14px !important;
    }
    .detail-kv-row {
        gap: 12px !important;
        align-items: flex-start !important;
    }
    .detail-v-val {
        text-align: right !important;
        word-break: break-word !important;
    }

    /* 7. Modals & Confirm Dialog on Mobile */
    .jconfirm .jconfirm-box-container,
    .jconfirm .jconfirm-box {
        width: 92% !important;
        max-width: 92% !important;
    }
    #myModal .modal-dialog,
    #imgSlider .modal-dialog {
        max-width: calc(100% - 20px) !important;
        margin: 20px auto !important;
    }
    .custom-breadcrumb {
        display: flex !important;
        flex-direction: column !important;
        gap: 6px !important;
        text-align: center !important;
        border-radius: 10px !important;
    }
    .custom-breadcrumb > div {
        width: 100% !important;
        max-width: 100% !important;
        flex: 0 0 100% !important;
    }

    /* 8. Footer */
    .modern-footer {
        padding: 22px 16px !important;
        margin-top: 24px !important;
    }
    .footer-inner {
        flex-direction: column !important;
        text-align: center !important;
        gap: 14px !important;
    }
    .footer-links {
        flex-wrap: wrap !important;
        justify-content: center !important;
        gap: 10px 18px !important;
    }
}
</style>
</head>
<body>

<!-- Header Navigation Bar -->
<header class="modern-navbar">
    <div class="brand-logo">
        <a href="userHome"><img src="resources/images/logo.png" alt="Snehbandhan Logo"></a>
    </div>
    
    <div class="modern-nav-center hidden-sm-down">
        <a href="userHome" class="modern-nav-item active"><i class="fa fa-home"></i> Dashboard</a>
        <a href="userHome" class="modern-nav-item"><i class="fa fa-heart-o"></i> Matches</a>
        <a href="userHome" class="modern-nav-item"><i class="fa fa-comment-o"></i> Messages <span class="modern-nav-badge">12</span></a>
        <a href="userHome" class="modern-nav-item"><i class="fa fa-heart"></i> Shortlisted <span class="modern-nav-badge">8</span></a>
        <a href="userHome" class="modern-nav-item"><i class="fa fa-eye"></i> Who Viewed Me <span class="modern-nav-badge">16</span></a>
    </div>
    
    <div class="modern-nav-right">
        <a href="KitUpgradeAndPayment" class="btn-upgrade-plan"><i class="fa fa-crown"></i> Upgrade Plan</a>
        <div class="nav-icon-badge-btn">
            <i class="fa fa-bell-o"></i>
            <span class="badge-dot">5</span>
        </div>
        <div class="dropdown">
            <div class="nav-user-avatar dropdown-toggle" data-toggle="dropdown">
                <img src="resources/UserImages/${UserProfile}" alt="User Avatar" onerror="this.onerror=null; this.src='resources/UserImages/userImage.png';">
                <span style="font-weight: 600; font-size: 14px;">${ActUserName}</span>
            </div>
            <div class="dropdown-menu dropdown-menu-right">
                <a class="dropdown-item" href="<c:url value='/j_spring_security_logout'/>"><i class="fa fa-sign-out"></i> Logout</a>
            </div>
        </div>
    </div>
</header>

<!-- Sub Header / Back Link -->
<div class="sub-header-bar">
    <a href="userHome" class="back-link"><i class="fa fa-arrow-left"></i> Back to Search Results</a>
</div>

<!-- Main Container Grid -->
<div class="profile-container">
    <div class="row">
        
        <!-- Left Column: Profile Overview & Gallery -->
        <div class="col-lg-3 col-md-4 col-sm-12">
            <div class="modern-card">
                <div class="card-header-title">
                    <span>Profile Overview</span>
                </div>
                
                <c:set var="firstImg" value="userImage.png" />
                <c:if test="${not empty compareUserProfileView.imageObject}">
                    <c:forEach var="img" items="${compareUserProfileView.imageObject}">
                        <c:if test="${not empty img and firstImg eq 'userImage.png'}">
                            <c:set var="firstImg" value="${img}" />
                        </c:if>
                    </c:forEach>
                </c:if>
                
                <div class="main-profile-img-container">
                    <span class="badge-verified-top"><i class="fa fa-check-circle"></i> Verified Profile</span>
                    <img src="resources/UserImages/${firstImg}" id="primaryProfileImg" alt="Profile Image" style="object-fit: cover; object-position: top center" onerror="this.onerror=null; this.src='resources/UserImages/userImage.png';">
                    <span class="badge-photos-count"><i class="fa fa-camera"></i> ${fn:length(compareUserProfileView.imageObject)} Photos</span>
                </div>
                
                <div class="gallery-thumbs-row">
                    <c:forEach var="img" items="${compareUserProfileView.imageObject}">
                        <c:if test="${not empty img}">
                            <div class="thumb-item">
                                <img src="resources/UserImages/${img}" alt="Thumbnail" style="object-fit: cover; object-position: top center" onerror="this.onerror=null; this.src='resources/UserImages/userImage.png';">
                            </div>
                        </c:if>
                    </c:forEach>
                </div>
            </div>
            
            <div class="protected-contact-box">
                <i class="fa fa-lock"></i>
                <div>
                    <strong>Her contact details are protected.</strong><br>
                    Express interest to start a conversation.
                </div>
            </div>
            
            <div class="ads-placeholder-box">
                Ads
            </div>
        </div>
        
        <!-- Middle Column: Main Details & Specs -->
        <div class="col-lg-6 col-md-8 col-sm-12">
            <div class="modern-card">
                <div class="profile-title-header">
                    <div>
                        <div class="user-name-title">
                            ${compareUserProfileView.name} <span class="status-dot-online" title="Online Now"></span>
                        </div>
                        <div class="user-specs-subtitle">
                            ${compareUserProfileView.age} Years • ${compareUserProfileView.height} • ${compareUserProfileView.district}, ${compareUserProfileView.state}<br>
                            <span>${compareUserProfileView.education} • ${compareUserProfileView.occupation}</span>
                        </div>
                    </div>
                    <div style="text-align: right;">
                        <div class="user-last-active"><i class="fa fa-clock-o"></i> Last active 2 hours ago</div>
                    </div>
                </div>
                
                <div class="verification-badges-row">
                    <span class="v-badge-item"><i class="fa fa-check"></i> Mobile Verified</span>
                    <span class="v-badge-item"><i class="fa fa-check"></i> Email Verified</span>
                    <span class="v-badge-item"><i class="fa fa-check"></i> Photo Verified</span>
                    <span class="v-badge-item"><i class="fa fa-check"></i> ID Verified</span>
                </div>
                
                <!-- Compatibility Highlight Box -->
                <div class="compatibility-banner-card">
                    <div class="comp-left-gauge">
                        <div class="gauge-ring-box">
                            ${compareUserProfileView.matchPercentages}%
                        </div>
                        <div>
                            <div class="comp-text-title">Great Compatibility!</div>
                            <div class="comp-text-sub">${compareUserProfileView.matchTotal} of 8 preferences match</div>
                            <a href="#preferenceMatchSec" class="comp-link-details">View Compatibility Details &rarr;</a>
                        </div>
                    </div>
                    <div>
                        <div style="font-weight: 700; font-size: 13px; margin-bottom: 8px;">Top Matches</div>
                        <div class="top-matches-grid">
                            <div class="top-match-item"><i class="fa fa-circle-o"></i> Marital Status</div>
                            <div class="top-match-item"><i class="fa fa-arrows-v"></i> Height</div>
                            <div class="top-match-item"><i class="fa fa-sun-o"></i> Religion</div>
                            <div class="top-match-item"><i class="fa fa-user"></i> Lifestyle</div>
                            <div class="top-match-item"><i class="fa fa-cutlery"></i> Diet</div>
                            <div class="top-match-item" style="color: var(--primary); font-weight: 600;">And 1 more</div>
                        </div>
                    </div>
                </div>
                
                <!-- Action Buttons Bar -->
                <div class="action-buttons-row">
                    <button class="btn-express-main" onclick="Shortlist('${compareUserProfileView.visitorUserId}','${compareUserProfileView.name}');">
                        <i class="fa fa-envelope"></i> Express Interest
                    </button>
                    <button class="btn-action-outline" onclick="Shortlist('${compareUserProfileView.visitorUserId}','${compareUserProfileView.name}');">
                        <i class="fa fa-heart-o"></i> Shortlist
                    </button>
                    <button class="btn-action-outline" onclick="initializeChatBox('${compareUserProfileView.visitorUserId}','${compareUserProfileView.name}'); initializeChat('${compareUserProfileView.visitorUserId}');">
                        <i class="fa fa-comment-o"></i> Send Message
                    </button>
                </div>
                
                <!-- About Section -->
                <div style="margin-bottom: 20px;">
                    <div class="card-header-title">
                        <span><i class="fa fa-user" style="color: var(--primary); margin-right: 8px;"></i> About ${fn:split(compareUserProfileView.name, ' ')[0]}</span>
                    </div>
                    <p style="font-size: 14px; color: var(--text-muted); line-height: 1.6; margin-bottom: 6px;">
                        ${compareUserProfileView.aboutHer}
                    </p>
                    <a href="#" style="color: var(--primary); font-size: 13px; font-weight: 600; text-decoration: none;">Read More &or;</a>
                </div>
                
                <!-- Grid of Detail Cards -->
                <div class="details-cards-grid">
                    <!-- Personal Details -->
                    <div class="detail-card-item">
                        <div class="detail-card-title"><i class="fa fa-address-card-o"></i> Personal Details</div>
                        <div class="detail-kv-list">
                            <div class="detail-kv-row"><span class="detail-k-label">Marital Status</span><span class="detail-v-val">${not empty compareUserProfileView.materialStatus ? compareUserProfileView.materialStatus : 'Not Specified'}</span></div>
                            <div class="detail-kv-row"><span class="detail-k-label">Date of Birth</span><span class="detail-v-val">${not empty compareUserProfileView.dateOfBirth ? compareUserProfileView.dateOfBirth : 'Not Specified'}</span></div>
                            <div class="detail-kv-row"><span class="detail-k-label">Height</span><span class="detail-v-val">${not empty compareUserProfileView.height ? compareUserProfileView.height : 'Not Specified'}</span></div>
                            <div class="detail-kv-row"><span class="detail-k-label">Physical Status</span><span class="detail-v-val">${not empty compareUserProfileView.physicalStatus ? compareUserProfileView.physicalStatus : 'Normal'}</span></div>
                            <div class="detail-kv-row"><span class="detail-k-label">Body Type</span><span class="detail-v-val">${not empty compareUserProfileView.bodyType ? compareUserProfileView.bodyType : 'Average'}</span></div>
                            <div class="detail-kv-row"><span class="detail-k-label">Complexion</span><span class="detail-v-val">${not empty compareUserProfileView.complexion ? compareUserProfileView.complexion : 'Fair'}</span></div>
                        </div>
                    </div>
                    
                    <!-- Education & Career -->
                    <div class="detail-card-item">
                        <div class="detail-card-title"><i class="fa fa-graduation-cap"></i> Education & Career</div>
                        <div class="detail-kv-list">
                            <div class="detail-kv-row"><span class="detail-k-label">Education</span><span class="detail-v-val">${compareUserProfileView.education}</span></div>
                            <div class="detail-kv-row"><span class="detail-k-label">Occupation</span><span class="detail-v-val">${compareUserProfileView.occupation}</span></div>
                            <div class="detail-kv-row"><span class="detail-k-label">Annual Income</span><span class="detail-v-val">${compareUserProfileView.income != '0' ? compareUserProfileView.income : 'Not Specified'}</span></div>
                            <div class="detail-kv-row"><span class="detail-k-label">Working With</span><span class="detail-v-val">-</span></div>
                            <div class="detail-kv-row"><span class="detail-k-label">Work Location</span><span class="detail-v-val">${compareUserProfileView.district}</span></div>
                        </div>
                    </div>
                    
                    <!-- Family Details -->
                    <div class="detail-card-item">
                        <div class="detail-card-title"><i class="fa fa-users"></i> Family Details</div>
                        <div class="detail-kv-list">
                            <div class="detail-kv-row"><span class="detail-k-label">Family Type</span><span class="detail-v-val">${not empty compareUserProfileView.familyType ? compareUserProfileView.familyType : 'Not Specified'}</span></div>
                            <div class="detail-kv-row"><span class="detail-k-label">Family Status</span><span class="detail-v-val">${not empty compareUserProfileView.familyClass ? compareUserProfileView.familyClass : 'Not Specified'}</span></div>
                            <div class="detail-kv-row"><span class="detail-k-label">Family Values</span><span class="detail-v-val">${not empty compareUserProfileView.familyValues ? compareUserProfileView.familyValues : 'Not Specified'}</span></div>
                        </div>
                    </div>
                    
                    <!-- Religious Details -->
                    <div class="detail-card-item">
                        <div class="detail-card-title"><i class="fa fa-certificate"></i> Religious Details</div>
                        <div class="detail-kv-list">
                            <div class="detail-kv-row"><span class="detail-k-label">Religion</span><span class="detail-v-val">${compareUserProfileView.relogion}</span></div>
                            <div class="detail-kv-row"><span class="detail-k-label">Caste</span><span class="detail-v-val">${compareUserProfileView.caste}</span></div>
                            <div class="detail-kv-row"><span class="detail-k-label">Sub Caste</span><span class="detail-v-val">${compareUserProfileView.caste}</span></div>
                            <div class="detail-kv-row"><span class="detail-k-label">Gotra</span><span class="detail-v-val">-</span></div>
                        </div>
                    </div>
                    
                    <!-- Location Details -->
                    <div class="detail-card-item">
                        <div class="detail-card-title"><i class="fa fa-map-marker"></i> Location Details</div>
                        <div class="detail-kv-list">
                            <div class="detail-kv-row"><span class="detail-k-label">City</span><span class="detail-v-val">${compareUserProfileView.district}</span></div>
                            <div class="detail-kv-row"><span class="detail-k-label">State</span><span class="detail-v-val">${compareUserProfileView.state}</span></div>
                            <div class="detail-kv-row"><span class="detail-k-label">Country</span><span class="detail-v-val">${compareUserProfileView.country}</span></div>
                            <div class="detail-kv-row"><span class="detail-k-label">Living With</span><span class="detail-v-val">Parents</span></div>
                        </div>
                    </div>
                    
                    <!-- Lifestyle Information -->
                    <div class="detail-card-item">
                        <div class="detail-card-title"><i class="fa fa-cutlery"></i> Lifestyle Information</div>
                        <div class="detail-kv-list">
                            <div class="detail-kv-row"><span class="detail-k-label">Diet</span><span class="detail-v-val">Vegetarian</span></div>
                            <div class="detail-kv-row"><span class="detail-k-label">Smoke</span><span class="detail-v-val">Non-smoker</span></div>
                            <div class="detail-kv-row"><span class="detail-k-label">Drink</span><span class="detail-v-val">Never</span></div>
                            <div class="detail-kv-row"><span class="detail-k-label">Manglik</span><span class="detail-v-val">No</span></div>
                            <div class="detail-kv-row"><span class="detail-k-label">Blood Group</span><span class="detail-v-val">B+</span></div>
                        </div>
                    </div>
                </div>
                
                <!-- Kundali & Compatibility Card -->
                <div class="detail-card-item" id="preferenceMatchSec" style="margin-top: 16px;">
                    <div class="detail-card-title"><i class="fa fa-sun-o"></i> Kundali & Compatibility</div>
                    <div style="display: flex; align-items: center; justify-content: space-between; flex-wrap: wrap; gap: 16px;">
                        <div>
                            <div style="color: var(--green-success); font-weight: 700; font-size: 13px; display: flex; align-items: center; gap: 6px;">
                                <i class="fa fa-check-circle"></i> Kundali Available
                            </div>
                            <div style="font-size: 12px; color: var(--text-muted); margin: 4px 0 12px;">Kundali matched by expert astrologers.</div>
                            <div style="display: flex; gap: 10px; flex-wrap: wrap;">
                                <button class="btn-action-outline" style="padding: 6px 14px; font-size: 12px; flex: 0 0 auto;" onclick="kundaliSlider('${compareUserProfileView.visitorUserId}');"><i class="fa fa-eye"></i> View Kundali</button>
                                <button class="btn-action-outline" style="padding: 6px 14px; font-size: 12px; flex: 0 0 auto;"><i class="fa fa-download"></i> Download</button>
                            </div>
                        </div>
                        <div style="text-align: center;">
                            <div style="font-size: 12px; color: var(--text-muted); font-weight: 500;">Kundali Compatibility</div>
                            <div style="font-size: 24px; font-weight: 800; color: var(--primary); margin: 4px 0;">8/10</div>
                            <div style="font-size: 12px; font-weight: 700; color: var(--text-main);">Good Match</div>
                            <div style="width: 100px; height: 4px; background: #fbcfe8; border-radius: 2px; margin: 6px auto 0; overflow: hidden;">
                                <div style="width: 80%; height: 100%; background: var(--primary);"></div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </div>
        
        <!-- Right Column: Live Members & Similar Profiles -->
        <div class="col-lg-3 col-md-12 col-sm-12">
            <!-- Live Members Card -->
            <div class="modern-card">
                <div class="card-header-title">
                    <span>Live Members</span>
                    <a href="userHome" style="color: var(--primary); font-size: 13px; text-decoration: none; font-weight: 600;">View All</a>
                </div>
                
                <div>
                    <c:forEach var="profile" begin="0" end="6" items="${onlineProfiles}">
                        <c:set var="mImg" value="userImage.png" />
                        <c:forEach begin="0" end="0" var="img" items="${profile.imageObject}">
                            <c:set var="mImg" value="${img}" />
                        </c:forEach>
                        <div class="member-list-item">
                            <div class="member-info-left">
                                <img src="resources/UserImages/${mImg}" class="member-avatar" alt="Avatar" onerror="this.onerror=null; this.src='resources/UserImages/userImage.png';">
                                <div>
                                    <div class="member-name-text">${profile.name}</div>
                                    <div class="member-sub-text">${profile.age} Years, ${profile.location}</div>
                                </div>
                            </div>
                            <span class="status-dot-online" title="Online"></span>
                        </div>
                    </c:forEach>
                </div>
            </div>
            
            <!-- Similar Profiles Card -->
            <div class="modern-card">
                <div class="card-header-title">
                    <span>Similar Profiles</span>
                    <a href="userHome" style="color: var(--primary); font-size: 13px; text-decoration: none; font-weight: 600;">View All</a>
                </div>
                
                <div>
                    <c:forEach var="profile" begin="0" end="4" items="${LatestMatch}">
                        <c:set var="sImg" value="userImage.png" />
                        <c:forEach begin="0" end="0" var="img" items="${profile.imageObject}">
                            <c:set var="sImg" value="${img}" />
                        </c:forEach>
                        <div class="similar-profile-card-item">
                            <div class="member-info-left">
                                <img src="resources/UserImages/${sImg}" class="member-avatar" alt="Avatar" onerror="this.onerror=null; this.src='resources/UserImages/userImage.png';">
                                <div>
                                    <div class="member-name-text"><a href="<c:url value='ViewProfileWithPartner'><c:param name='userId' value='${profile.visitorUserId}'/></c:url>" style="color: inherit; text-decoration: none;">${profile.name}</a></div>
                                    <div class="member-sub-text">${profile.age} Yrs, ${profile.caste}<br>${profile.location}</div>
                                </div>
                            </div>
                            <span class="similar-match-pill">73% Match</span>
                        </div>
                    </c:forEach>
                </div>
                
                <button class="btn-view-more-full" onclick="window.location.href='userHome';">View More Profiles</button>
            </div>
        </div>
        
    </div>
</div>

<!-- Modal & Hidden Form Containers -->
<div id="imgSlider" class="modal fade" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog modal-lg custom-slider-modal" style="margin: 30px auto;">
        <div class="modal-content">
            <div class="modal-body">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true" style="float: right; font-size: 24px; border: none; background: none;">&times;</button>
                <div id="customSlider"></div>
            </div>
        </div>
    </div>
</div>

<!-- Shortlist Popup Modal -->
<div id="myModal" class="modal fade" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title" style="color: var(--green-success);">You have shortlisted successfully...</h4>
            </div>
            <div class="modal-body">
                <nav class="breadcrumb custom-breadcrumb" style="background-color: var(--primary); color: #ffffff; padding: 10px;">
                    <div class="col-sm-4">
                        <a class="breadcrumb-item" style="color:#fff;" href="#menu1" id="imenu1" onclick="show1('menu1');">Send Sms</a>
                    </div>
                    <div class="col-sm-4">
                        <a class="breadcrumb-item" style="color:#fff;" href="#menu2" id="imenu2" onclick="show1('menu2');">Send Email</a>
                    </div>
                    <div class="col-sm-4">
                        <span class="breadcrumb-item active" style="color:#fff;" id="imenu3" href="#menu3" onclick="show1('menu3');">Contact Details</span>
                    </div>
                </nav>
                <div class="container" style="padding-top: 15px;">
                    <div class="tab-content">
                        <div id="menu1" class="tab-pane active">
                            <form id="sms">
                                <div class="form-group">
                                    <label>104 characters only</label>
                                    <textarea class="form-control" rows="3" id="ShortlistMSG" maxlength="104"></textarea>
                                </div>
                                <div class="form-group">
                                    <input type="button" class="btn btn-primary" style="background: var(--primary); border: none;" onclick="ShortlistSendMSG();" value="Send MSG">
                                </div>
                            </form>
                        </div>
                        <div id="menu2" class="tab-pane">
                            <form id="email">
                                <div class="form-group">
                                    <textarea class="form-control" rows="5" id="ShortlistEmail"></textarea>
                                </div>
                                <div class="form-group">
                                    <input type="button" class="btn btn-primary" style="background: var(--primary); border: none;" onclick="ShortlistSendEmail();" value="Send Email">
                                </div>
                            </form>
                        </div>
                        <div id="menu3" class="tab-pane">
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
</div>

<input type="hidden" id="loguserid" name="loguserid" value="${loguser}">
<input type="hidden" id="userid" name="userid">
<input type="hidden" id="userName" name="userName">
<input type="hidden" id="email" name="email" value="${email}">
<input type="hidden" id="firstName" name="firstName" value="${firstName}">
<input type="hidden" id="hiddenExpDate" name="hiddenExpDate" value="${expDate}">

<!-- Footer -->
<footer class="modern-footer">
    <div class="footer-inner">
        <div class="brand-logo">
            <img src="resources/images/logo.png" alt="Snehbandhan Logo" style="height: 40px">
        </div>
        <div class="footer-links">
            <a href="PrivacyPolicy">Privacy Policy</a>
            <a href="TermAndCondition">Terms & Conditions</a>
            <a href="contactus">Help & Support</a>
            <a href="contactus">Contact Us</a>
        </div>
        <div style="font-size: 13px; color: var(--text-muted);">
            &copy; 2024 Snehbandhan Matrimony. All Rights Reserved.
        </div>
        <div class="footer-social-icons">
            <a href="https://www.facebook.com/snehbandhanburo" target="_blank" class="social-icon-btn"><i class="fa fa-facebook"></i></a>
            <a href="https://twitter.com/snehbandhan" target="_blank" class="social-icon-btn"><i class="fa fa-instagram"></i></a>
            <a href="https://goo.gl/maps/QGa85D23gVP2" target="_blank" class="social-icon-btn"><i class="fa fa-youtube-play"></i></a>
        </div>
    </div>
</footer>

<!-- JS Dependencies -->
<script src="resources/Template/js/jquery-min.js"></script>
<script src="resources/Template/js/tether.min.js"></script>
<script src="resources/Template/js/bootstrap.min.js"></script>
<script src="resources/confirmBox/jquery-confirm.js" type="text/javascript"></script>

<script>
function show(){
	$("#myModal").modal();	
}

function show1(txt){
	$("#menu1").hide();
	$("#menu2").hide();
	$("#menu3").hide();
	$("#"+txt).show();
}
</script>

</body>
</html>