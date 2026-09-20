<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
            <%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
                <html lang="en">

                <head>
                    <!-- Google tag (gtag.js) -->
                    <script async src="https://www.googletagmanager.com/gtag/js?id=G-578JZ59RJR"></script>
                    <script>
                        window.dataLayer = window.dataLayer || [];
                        function gtag() { dataLayer.push(arguments); }
                        gtag('js', new Date());
                        gtag('config', 'G-578JZ59RJR');
                    </script>
                    <meta charset="utf-8">
                    <title>Contact Us - Snehbandhan Matrimony</title>
                    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

                    <!-- Fonts & Icons -->
                    <link rel="preconnect" href="https://fonts.googleapis.com">
                    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
                    <link
                        href="https://fonts.googleapis.com/css2?family=DM+Sans:ital,opsz,wght@0,9..40,400;0,9..40,500;0,9..40,600;0,9..40,700;0,9..40,800;1,9..40,400;1,9..40,500&family=Inter:wght@400;500;600;700&family=Mukta:wght@400;600;700&display=swap"
                        rel="stylesheet">
                    <link rel="stylesheet" href="resources/Home/css/font-awesome.min.css">
                    <link rel="stylesheet" href="resources/customcss/snehbandhan_brand.css">

                    <style>
                        :root {
                            --brand-primary: #D81B60;
                            --brand-hover: #B9154E;
                            --brand-maroon: #481123;
                            --brand-tag: #A83858;
                            --brand-cream: #FAF8F5;
                            --brand-pink-soft: #FFF2F5;
                            --brand-border: #EFE4E6;
                            --text-dark: #2E2E2E;
                            --text-muted: #5E5E5E;
                        }

                        body {
                            font-family: 'Inter', sans-serif;
                            background-color: #FAFAF8;
                            color: var(--text-dark);
                            margin: 0;
                            padding: 0;
                            overflow-x: hidden;
                        }

                        /* Top Navbar */
                        .header-nav {
                            background-color: #ffffff;
                            position: sticky;
                            top: 0;
                            z-index: 1000;
                            box-shadow: 0 1px 6px rgba(0, 0, 0, 0.05);
                            border-bottom: 1px solid rgba(0, 0, 0, 0.04);
                        }

                        .nav-inner {
                            max-width: 1240px;
                            margin: 0 auto;
                            padding: 12px 24px;
                            display: flex;
                            align-items: center;
                            justify-content: space-between;
                        }

                        .nav-logo img {
                            height: 48px;
                            display: block;
                        }

                        .nav-menu {
                            display: flex;
                            align-items: center;
                            gap: 28px;
                        }

                        .nav-item-link {
                            text-decoration: none;
                            color: #444444;
                            font-size: 14.5px;
                            font-weight: 500;
                            transition: color 0.2s ease;
                            position: relative;
                            display: inline-flex;
                            align-items: center;
                            gap: 5px;
                        }

                        .nav-item-link:hover {
                            color: var(--brand-primary);
                        }

                        .nav-item-link.active {
                            color: var(--brand-primary);
                            font-weight: 600;
                        }

                        .nav-item-link.active::after {
                            content: "";
                            position: absolute;
                            bottom: -6px;
                            left: 0;
                            right: 0;
                            height: 3px;
                            background-color: var(--brand-primary);
                            border-radius: 2px;
                        }

                        .nav-actions {
                            display: flex;
                            align-items: center;
                            gap: 12px;
                        }

                        .btn-nav-login {
                            background: transparent;
                            color: var(--brand-primary);
                            border: 1.5px solid var(--brand-primary);
                            padding: 8px 22px;
                            border-radius: 8px;
                            font-size: 14px;
                            font-weight: 600;
                            text-decoration: none;
                            cursor: pointer;
                            transition: all 0.2s ease;
                        }

                        .btn-nav-login:hover {
                            background-color: #fdf0f4;
                            color: var(--brand-primary);
                        }

                        .btn-nav-register {
                            background-color: var(--brand-primary);
                            color: #ffffff;
                            border: none;
                            padding: 9px 24px;
                            border-radius: 8px;
                            font-size: 14px;
                            font-weight: 600;
                            text-decoration: none;
                            cursor: pointer;
                            transition: background 0.2s ease;
                            box-shadow: 0 2px 8px rgba(216, 27, 96, 0.2);
                        }

                        .btn-nav-register:hover {
                            background-color: var(--brand-hover);
                            color: #ffffff;
                        }

                        /* Mobile Hamburger */
                        .mobile-toggle {
                            display: none;
                            font-size: 20px;
                            color: #333;
                            cursor: pointer;
                        }

                        /* Container Layout */
                        .page-container {
                            max-width: 1240px;
                            margin: 0 auto;
                            padding: 0 24px;
                        }

                        /* ================= CONTACT HERO SECTION ================= */
                        .contact-hero-section {
                            background: #FFFDFB;
                            position: relative;
                            padding: 38px 0 32px;
                            border-bottom: 1px solid #F6EEEF;
                            overflow: hidden;
                        }

                        .hero-mandala-left {
                            position: absolute;
                            top: 0;
                            left: 0;
                            height: 250px;
                            opacity: 0.75;
                            pointer-events: none;
                            z-index: 1;
                        }

                        .hero-mandala-right {
                            position: absolute;
                            top: 0;
                            right: 0;
                            height: 220px;
                            opacity: 0.55;
                            pointer-events: none;
                            z-index: 1;
                            transform: scaleX(-1);
                        }

                        .contact-hero-grid {
                            position: relative;
                            z-index: 2;
                            display: grid;
                            grid-template-columns: 1.15fr 1fr;
                            gap: 32px;
                            align-items: center;
                        }

                        .section-tag-pill {
                            font-size: 11.5px;
                            letter-spacing: 1.5px;
                            text-transform: uppercase;
                            font-weight: 700;
                            color: var(--brand-primary);
                            background-color: #FCEEF1;
                            padding: 5px 14px;
                            border-radius: 20px;
                            display: inline-block;
                            margin-bottom: 14px;
                        }

                        .contact-hero-heading {
                            font-family: 'DM Sans', serif, sans-serif;
                            font-size: 38px;
                            font-weight: 800;
                            color: var(--brand-maroon);
                            line-height: 1.2;
                            margin: 0 0 14px;
                        }

                        .contact-hero-desc {
                            font-size: 14px;
                            line-height: 1.65;
                            color: var(--text-muted);
                            margin: 0 0 24px;
                            max-width: 480px;
                        }

                        .social-icons-row {
                            display: flex;
                            align-items: center;
                            gap: 12px;
                        }

                        .social-circle-btn {
                            width: 36px;
                            height: 36px;
                            border-radius: 50%;
                            background-color: #FFF0F3;
                            border: 1px solid #F8DDE3;
                            color: var(--brand-primary);
                            display: inline-flex;
                            align-items: center;
                            justify-content: center;
                            text-decoration: none;
                            font-size: 15px;
                            transition: all 0.2s ease;
                            box-shadow: 0 2px 6px rgba(0, 0, 0, 0.02);
                        }

                        .social-circle-btn:hover {
                            background-color: var(--brand-primary);
                            color: #ffffff;
                            transform: translateY(-2px);
                            box-shadow: 0 4px 10px rgba(216, 27, 96, 0.2);
                        }

                        .contact-hero-img-box {
                            position: relative;
                            text-align: right;
                        }

                        .contact-hero-img {
                            max-width: 100%;
                            height: auto;
                            display: block;
                            margin-left: auto;
                            border-radius: 12px;
                        }

                        /* ================= BRANCHES SECTION ================= */
                        .branches-container {
                            padding: 40px 0 20px;
                        }

                        .branch-card {
                            background: #ffffff;
                            border: 1px solid var(--brand-border);
                            border-radius: 18px;
                            padding: 24px 30px;
                            margin-bottom: 26px;
                            box-shadow: 0 3px 14px rgba(0, 0, 0, 0.02);
                            transition: transform 0.2s ease, box-shadow 0.2s ease;
                        }

                        .branch-card:hover {
                            box-shadow: 0 6px 20px rgba(0, 0, 0, 0.04);
                        }

                        .branch-card-grid {
                            display: grid;
                            grid-template-columns: 1.15fr 1fr;
                            gap: 32px;
                            align-items: center;
                        }

                        .branch-header {
                            display: flex;
                            align-items: flex-start;
                            gap: 14px;
                            margin-bottom: 20px;
                        }

                        .branch-pin-circle {
                            width: 34px;
                            height: 34px;
                            border-radius: 50%;
                            background-color: var(--brand-primary);
                            color: #ffffff;
                            display: flex;
                            align-items: center;
                            justify-content: center;
                            font-size: 15px;
                            flex-shrink: 0;
                            box-shadow: 0 3px 8px rgba(216, 27, 96, 0.25);
                            margin-top: 2px;
                        }

                        .branch-meta {
                            display: flex;
                            flex-direction: column;
                        }

                        .branch-pill-tag {
                            font-size: 10.5px;
                            letter-spacing: 1.5px;
                            text-transform: uppercase;
                            font-weight: 700;
                            color: var(--brand-tag);
                            margin-bottom: 2px;
                        }

                        .branch-title {
                            font-family: 'DM Sans', sans-serif;
                            font-size: 22px;
                            font-weight: 700;
                            color: var(--brand-maroon);
                            margin: 0;
                        }

                        .branch-details-table {
                            display: flex;
                            flex-direction: column;
                            gap: 11px;
                            margin-bottom: 24px;
                        }

                        .detail-row {
                            display: grid;
                            grid-template-columns: 130px 14px 1fr;
                            font-size: 13.5px;
                            line-height: 1.55;
                            align-items: baseline;
                        }

                        .detail-label {
                            color: #777777;
                            font-weight: 500;
                            display: inline-flex;
                            align-items: center;
                            gap: 8px;
                        }

                        .detail-label i {
                            color: var(--brand-primary);
                            font-size: 14px;
                            width: 14px;
                            text-align: center;
                        }

                        .detail-colon {
                            color: #999999;
                            font-weight: 600;
                        }

                        .detail-value {
                            color: #2D3748;
                            font-weight: 500;
                        }

                        .branch-actions-row {
                            display: flex;
                            align-items: center;
                            gap: 14px;
                            flex-wrap: wrap;
                        }

                        .btn-branch-call {
                            background-color: var(--brand-primary);
                            color: #ffffff;
                            border-radius: 25px;
                            padding: 9px 24px;
                            font-size: 13.5px;
                            font-weight: 600;
                            text-decoration: none;
                            display: inline-flex;
                            align-items: center;
                            gap: 8px;
                            transition: all 0.2s ease;
                            box-shadow: 0 3px 8px rgba(216, 27, 96, 0.2);
                        }

                        .btn-branch-call:hover {
                            background-color: var(--brand-hover);
                            color: #ffffff;
                            transform: translateY(-1px);
                        }

                        .btn-branch-email {
                            background: transparent;
                            color: var(--brand-primary);
                            border: 1.5px solid var(--brand-primary);
                            border-radius: 25px;
                            padding: 8px 22px;
                            font-size: 13.5px;
                            font-weight: 600;
                            text-decoration: none;
                            display: inline-flex;
                            align-items: center;
                            gap: 8px;
                            transition: all 0.2s ease;
                        }

                        .btn-branch-email:hover {
                            background-color: #FFF0F3;
                            color: var(--brand-primary);
                        }

                        /* Map Container */
                        .branch-map-box {
                            border-radius: 14px;
                            overflow: hidden;
                            border: 1px solid #EAEAEA;
                            box-shadow: 0 3px 12px rgba(0, 0, 0, 0.03);
                            background: #ffffff;
                        }

                        .map-preview-link {
                            text-decoration: none;
                            display: block;
                            position: relative;
                        }

                        .map-preview-img {
                            width: 100%;
                            height: auto;
                            display: block;
                            object-fit: cover;
                        }

                        .map-bottom-bar {
                            background-color: #FFF2F5;
                            padding: 10px 18px;
                            display: flex;
                            justify-content: space-between;
                            align-items: center;
                            font-size: 12.5px;
                            font-weight: 600;
                            color: var(--brand-primary);
                            border-top: 1px solid #F8E0E5;
                        }

                        .map-bottom-bar span {
                            display: inline-flex;
                            align-items: center;
                            gap: 6px;
                        }

                        /* ================= NEED HELP BANNER ================= */
                        .need-help-banner {
                            background-color: #FDF1F4;
                            border: 1px solid #F8E2E7;
                            border-radius: 18px;
                            padding: 20px 32px;
                            margin: 20px auto 55px;
                            display: flex;
                            align-items: center;
                            justify-content: space-between;
                            gap: 20px;
                        }

                        .help-left {
                            display: flex;
                            align-items: center;
                            gap: 16px;
                        }

                        .help-icon-circle {
                            width: 44px;
                            height: 44px;
                            border-radius: 50%;
                            background-color: #ffffff;
                            border: 1.5px solid var(--brand-primary);
                            color: var(--brand-primary);
                            display: flex;
                            align-items: center;
                            justify-content: center;
                            font-size: 18px;
                            flex-shrink: 0;
                        }

                        .help-texts h4 {
                            font-family: 'DM Sans', sans-serif;
                            font-size: 18px;
                            font-weight: 700;
                            color: var(--brand-maroon);
                            margin: 0 0 3px;
                        }

                        .help-texts p {
                            font-size: 13px;
                            color: var(--text-muted);
                            margin: 0;
                        }

                        .btn-contact-support {
                            background-color: #ffffff;
                            border: 1.5px solid var(--brand-primary);
                            color: var(--brand-primary);
                            border-radius: 25px;
                            padding: 9px 24px;
                            font-size: 13.5px;
                            font-weight: 600;
                            text-decoration: none;
                            display: inline-flex;
                            align-items: center;
                            gap: 8px;
                            white-space: nowrap;
                            transition: all 0.2s ease;
                            box-shadow: 0 2px 6px rgba(216, 27, 96, 0.08);
                        }

                        .btn-contact-support:hover {
                            background-color: var(--brand-primary);
                            color: #ffffff;
                            transform: translateX(2px);
                        }

                        /* ================= FOOTER ================= */
                        .site-footer {
                            background-color: #2D3748;
                            color: #E2E8F0;
                            padding: 30px 0 24px;
                            font-size: 13.5px;
                            text-align: center;
                        }

                        .footer-socials {
                            display: flex;
                            justify-content: center;
                            gap: 14px;
                            margin-bottom: 16px;
                        }

                        .footer-social-link {
                            width: 36px;
                            height: 36px;
                            border-radius: 50%;
                            background: rgba(255, 255, 255, 0.1);
                            color: #ffffff;
                            display: inline-flex;
                            align-items: center;
                            justify-content: center;
                            text-decoration: none;
                            transition: all 0.2s ease;
                        }

                        .footer-social-link:hover {
                            background: var(--brand-primary);
                            color: #ffffff;
                        }

                        /* Responsive */
                        @media (max-width: 991px) {
                            .contact-hero-grid {
                                grid-template-columns: 1fr;
                            }

                            .contact-hero-img-box {
                                text-align: center;
                            }

                            .branch-card-grid {
                                grid-template-columns: 1fr;
                            }

                            .need-help-banner {
                                flex-direction: column;
                                align-items: flex-start;
                                padding: 20px;
                            }

                            .nav-menu {
                                display: none;
                            }

                            .mobile-toggle {
                                display: block;
                            }
                        }
                    </style>
                </head>

                <body>

                    <!-- Header Navigation -->
                    <header class="header-nav">
                        <div class="nav-inner">
                            <div class="nav-logo">
                                <a href="Home">
                                    <img src="resources/images/logo.png" alt="Snehbandhan Logo"
                                        onerror="this.onerror=null; this.src='resources/UserImages/userImage.png';">
                                </a>
                            </div>

                            <nav class="nav-menu">
                                <a href="Home" class="nav-item-link">Home</a>
                                <a href="Home" class="nav-item-link">Find Matches <i class="fa fa-angle-down"
                                        style="font-size: 12px;"></i></a>
                                <a href="Home" class="nav-item-link">Success Stories</a>
                                <a href="PackageHome" class="nav-item-link">Membership <i class="fa fa-angle-down"
                                        style="font-size: 12px;"></i></a>
                                <a href="aboutus" class="nav-item-link">About Us</a>
                                <a href="contactus" class="nav-item-link active">Contact</a>
                            </nav>

                            <div class="nav-actions">
                                <a href="login" class="btn-nav-login"
                                    onclick="if(document.getElementById('loginModal')){document.getElementById('loginModal').style.display='flex'; return false;}">Login</a>
                                <a href="registration" class="btn-nav-register"
                                    onclick="if(document.getElementById('registrationModal')){document.getElementById('registrationModal').style.display='flex'; return false;}">Register
                                    Free</a>
                                <div class="mobile-toggle"
                                    onclick="var m = document.getElementById('mobileMenu'); m.style.display = m.style.display === 'flex' ? 'none' : 'flex';">
                                    <i class="fa fa-bars"></i>
                                </div>
                            </div>
                        </div>

                        <!-- Mobile Menu Dropdown -->
                        <div id="mobileMenu"
                            style="display: none; flex-direction: column; background: #fff; padding: 16px 24px; border-top: 1px solid #eee; gap: 12px;">
                            <a href="Home" style="text-decoration: none; color: #333; font-weight: 500;">Home</a>
                            <a href="Home" style="text-decoration: none; color: #333; font-weight: 500;">Find
                                Matches</a>
                            <a href="Home" style="text-decoration: none; color: #333; font-weight: 500;">Success
                                Stories</a>
                            <a href="PackageHome"
                                style="text-decoration: none; color: #333; font-weight: 500;">Membership</a>
                            <a href="aboutus" style="text-decoration: none; color: #333; font-weight: 500;">About Us</a>
                            <a href="contactus"
                                style="text-decoration: none; color: var(--brand-primary); font-weight: 600;">Contact</a>
                        </div>
                    </header>

                    <!-- ================= CONTACT HERO SECTION ================= -->
                    <section class="contact-hero-section">
                        <img src="resources/images/mandala_pattern.png" class="hero-mandala-left" alt="Mandala Left"
                            onerror="this.style.display='none';">
                        <img src="resources/images/mandala_pattern.png" class="hero-mandala-right" alt="Mandala Right"
                            onerror="this.style.display='none';">

                        <div class="page-container">
                            <div class="contact-hero-grid">
                                <!-- Left: Headline & Social Links -->
                                <div>
                                    <span class="section-tag-pill">CONTACT US</span>
                                    <h1 class="contact-hero-heading">We're Here to Help</h1>
                                    <p class="contact-hero-desc">Have a question, need assistance, or want to know more
                                        about Snehbandhan Matrimony? Our team is always happy to help you.</p>

                                    <div class="social-icons-row">
                                        <a href="https://www.facebook.com/snehbandhanburo" target="_blank"
                                            class="social-circle-btn" title="Facebook">
                                            <i class="fa fa-facebook"></i>
                                        </a>
                                        <a href="https://twitter.com/snehbandhan" target="_blank"
                                            class="social-circle-btn" title="Twitter / X">
                                            <i class="fa fa-twitter"></i>
                                        </a>
                                        <a href="#" class="social-circle-btn" title="Instagram">
                                            <i class="fa fa-instagram"></i>
                                        </a>
                                        <a href="https://goo.gl/maps/QGa85D23gVP2" target="_blank"
                                            class="social-circle-btn" title="Google Maps">
                                            <i class="fa fa-map-marker"></i>
                                        </a>
                                        <a href="https://play.google.com/store/apps/details?id=com.trial.sneh&hl=en"
                                            target="_blank" class="social-circle-btn" title="Google Play">
                                            <i class="fa fa-play" style="font-size: 12px; margin-left: 2px;"></i>
                                        </a>
                                    </div>
                                </div>

                                <!-- Right: Hero Banner with Couple and Calligraphy -->
                                <div class="contact-hero-img-box">
                                    <img src="resources/images/contact_hero_couple.png" class="contact-hero-img"
                                        alt="Together for a better tomorrow">
                                </div>
                            </div>
                        </div>
                    </section>

                    <!-- ================= MAIN BRANCH OFFICES ================= -->
                    <main class="page-container">
                        <div class="branches-container">
                            <!-- Branch 1: Head Office – Pune -->
                            <div class="branch-card">
                                <div class="branch-card-grid">
                                    <!-- Left Details Column -->
                                    <div>
                                        <div class="branch-header">
                                            <div class="branch-pin-circle">
                                                <i class="fa fa-map-marker"></i>
                                            </div>
                                            <div class="branch-meta">
                                                <span class="branch-pill-tag">OUR BRANCH</span>
                                                <h2 class="branch-title">Head Office – Pune</h2>
                                            </div>
                                        </div>

                                        <div class="branch-details-table">
                                            <div class="detail-row">
                                                <span class="detail-label"><i class="fa fa-user-o"></i> Branch
                                                    Name</span>
                                                <span class="detail-colon">:</span>
                                                <span class="detail-value">SHUBHAMANGAL VIVAHSANSTA</span>
                                            </div>
                                            <div class="detail-row">
                                                <span class="detail-label"><i class="fa fa-map-marker"></i>
                                                    Address</span>
                                                <span class="detail-colon">:</span>
                                                <span class="detail-value">dattijrao Baegr, Shoping Centre, juna motar
                                                    stand, Talkoregao, Dist, Satara.pin-415 501</span>
                                            </div>
                                            <div class="detail-row">
                                                <span class="detail-label"><i class="fa fa-phone"></i> Contact No</span>
                                                <span class="detail-colon">:</span>
                                                <span class="detail-value">8600447760</span>
                                            </div>
                                            <div class="detail-row">
                                                <span class="detail-label"><i class="fa fa-envelope-o"></i> Email
                                                    Id</span>
                                                <span class="detail-colon">:</span>
                                                <span class="detail-value">snehbandhan01@gmail.com</span>
                                            </div>
                                        </div>

                                        <div class="branch-actions-row">
                                            <a href="tel:8600447760" class="btn-branch-call">
                                                <i class="fa fa-phone"></i> Call Now
                                            </a>
                                            <a href="mailto:snehbandhan01@gmail.com" class="btn-branch-email">
                                                <i class="fa fa-envelope-o"></i> Send Email
                                            </a>
                                        </div>
                                    </div>

                                    <!-- Right Map Preview Column -->
                                    <div>
                                        <div class="branch-map-box">
                                            <a href="https://maps.google.com/?q=dattijrao+Baegr+Shoping+Centre+juna+motar+stand+Talkoregao+Satara"
                                                target="_blank" class="map-preview-link">
                                                <img src="resources/images/map_preview_pune.png" class="map-preview-img"
                                                    alt="Head Office Pune Map Location">
                                                <div class="map-bottom-bar">
                                                    <span><i class="fa fa-map-marker"></i> View on Google Maps</span>
                                                    <span>Get Directions &gt;</span>
                                                </div>
                                            </a>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <!-- Branch 2: Branch Office – Kolhapur -->
                            <div class="branch-card">
                                <div class="branch-card-grid">
                                    <!-- Left Details Column -->
                                    <div>
                                        <div class="branch-header">
                                            <div class="branch-pin-circle">
                                                <i class="fa fa-map-marker"></i>
                                            </div>
                                            <div class="branch-meta">
                                                <span class="branch-pill-tag">OUR BRANCH</span>
                                                <h2 class="branch-title">Branch Office – Kolhapur</h2>
                                            </div>
                                        </div>

                                        <div class="branch-details-table">
                                            <div class="detail-row">
                                                <span class="detail-label"><i class="fa fa-user-o"></i> Branch
                                                    Name</span>
                                                <span class="detail-colon">:</span>
                                                <span class="detail-value">SNEH BANDHAN MATRIMONY</span>
                                            </div>
                                            <div class="detail-row">
                                                <span class="detail-label"><i class="fa fa-map-marker"></i>
                                                    Address</span>
                                                <span class="detail-colon">:</span>
                                                <span class="detail-value">Shop No. A1, Shivaji Stadium Market, Tembe
                                                    Road, Near Bus Stop, Mangalwar Peth, Kolhapur.</span>
                                            </div>
                                            <div class="detail-row">
                                                <span class="detail-label"><i class="fa fa-phone"></i> Contact No</span>
                                                <span class="detail-colon">:</span>
                                                <span class="detail-value">7517520378</span>
                                            </div>
                                            <div class="detail-row">
                                                <span class="detail-label"><i class="fa fa-envelope-o"></i> Email
                                                    Id</span>
                                                <span class="detail-colon">:</span>
                                                <span class="detail-value">snehbandhan01@gmail.com</span>
                                            </div>
                                        </div>

                                        <div class="branch-actions-row">
                                            <a href="tel:7517520378" class="btn-branch-call">
                                                <i class="fa fa-phone"></i> Call Now
                                            </a>
                                            <a href="mailto:snehbandhan01@gmail.com" class="btn-branch-email">
                                                <i class="fa fa-envelope-o"></i> Send Email
                                            </a>
                                        </div>
                                    </div>

                                    <!-- Right Map Preview Column -->
                                    <div>
                                        <div class="branch-map-box">
                                            <a href="https://maps.google.com/?q=Shop+No+A1+Shivaji+Stadium+Market+Tembe+Road+Mangalwar+Peth+Kolhapur"
                                                target="_blank" class="map-preview-link">
                                                <img src="resources/images/map_preview_kolhapur.png"
                                                    class="map-preview-img" alt="Branch Office Kolhapur Map Location">
                                                <div class="map-bottom-bar">
                                                    <span><i class="fa fa-map-marker"></i> View on Google Maps</span>
                                                    <span>Get Directions &gt;</span>
                                                </div>
                                            </a>
                                        </div>
                                    </div>
                                </div>
                            </div>


                        </div>

                        <!-- ================= NEED HELP BANNER ================= -->
                        <section class="need-help-banner">
                            <div class="help-left">
                                <div class="help-icon-circle">
                                    <i class="fa fa-headphones"></i>
                                </div>
                                <div class="help-texts">
                                    <h4>Need Help?</h4>
                                    <p>Our support team is available to assist you.</p>
                                </div>
                            </div>

                            <a href="tel:8600447760" class="btn-contact-support">
                                <i class="fa fa-headphones"></i> Contact Support &gt;
                            </a>
                        </section>
                    </main>

                    <!-- Footer -->
                    <footer class="site-footer">
                        <div class="page-container">
                            <div class="footer-socials">
                                <a href="https://www.facebook.com/snehbandhanburo" class="footer-social-link"
                                    target="_blank"><i class="fa fa-facebook"></i></a>
                                <a href="https://twitter.com/snehbandhan" class="footer-social-link" target="_blank"><i
                                        class="fa fa-twitter"></i></a>
                                <a href="https://goo.gl/maps/QGa85D23gVP2" class="footer-social-link" target="_blank"><i
                                        class="fa fa-google-plus"></i></a>
                            </div>
                            <p style="margin: 0; color: #A0AEC0;">
                                Copyright &copy; Snehbandhan Matrimony. All Rights Reserved. &nbsp;|&nbsp;
                                <a href="aboutus" style="color: #CBD5E0; text-decoration: none;">About Us</a>
                                &nbsp;|&nbsp;
                                <a href="contactus" style="color: #CBD5E0; text-decoration: none;">Contact Us</a>
                            </p>
                        </div>
                    </footer>

                    <!-- Login Modal -->
                    <div id="loginModal" class="modal-overlay"
                        style="display: none; position: fixed; top: 0; left: 0; width: 100%; height: 100%; background: rgba(0,0,0,0.5); z-index: 2000; justify-content: center; align-items: center;">
                        <div class="split-modal-content"
                            style="background: #fff; border-radius: 16px; width: 90%; max-width: 760px; display: flex; position: relative; overflow: hidden; box-shadow: 0 10px 30px rgba(0,0,0,0.2);">
                            <span class="close-modal"
                                onclick="document.getElementById('loginModal').style.display='none';"
                                style="position: absolute; top: 12px; right: 18px; font-size: 24px; cursor: pointer; color: #666; z-index: 10;">&times;</span>

                            <div class="split-modal-left"
                                style="background: #FFF4F7; width: 45%; padding: 36px 28px; display: flex; flex-direction: column; justify-content: center;">
                                <img src="resources/images/logo.png" alt="Snehbandhan Logo"
                                    style="height: 42px; margin-bottom: 20px; align-self: flex-start;">
                                <h3 style="margin: 0; font-size: 16px; color: #888;">Welcome Back!</h3>
                                <h2 style="margin: 4px 0 10px; font-size: 24px; color: var(--brand-maroon);">Login to
                                    Continue</h2>
                                <p style="font-size: 13px; color: #666; margin: 0;">Find your perfect Marathi match with
                                    trusted families.</p>
                            </div>

                            <div class="split-modal-right" style="width: 55%; padding: 36px 28px;">
                                <div style="font-size: 13px; color: #666; margin-bottom: 18px; text-align: right;">
                                    New user? <a href="registration"
                                        style="color: var(--brand-primary); font-weight: 600;"
                                        onclick="document.getElementById('loginModal').style.display='none'; document.getElementById('registrationModal').style.display='flex'; return false;">Create
                                        Account</a>
                                </div>

                                <form action="<c:url value='j_spring_security_check'/>" method="POST">
                                    <div style="margin-bottom: 14px; position: relative;">
                                        <input type="text" name="j_username" placeholder="Mobile Number / Email ID"
                                            required autofocus
                                            style="width: 100%; padding: 12px 16px; border: 1px solid #ddd; border-radius: 8px; font-size: 14px; box-sizing: border-box;">
                                    </div>

                                    <div style="margin-bottom: 14px; position: relative;">
                                        <input type="password" name="j_password" placeholder="Password" required
                                            style="width: 100%; padding: 12px 16px; border: 1px solid #ddd; border-radius: 8px; font-size: 14px; box-sizing: border-box;">
                                    </div>

                                    <div style="text-align: right; margin-bottom: 16px;">
                                        <a href="<c:url value='forgotpassword'/>"
                                            style="font-size: 12px; color: #888; text-decoration: none;">Forgot
                                            Password?</a>
                                    </div>

                                    <button type="submit" class="btn-nav-register"
                                        style="width: 100%; padding: 12px; border-radius: 8px; font-size: 15px;">Login</button>
                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                                </form>
                            </div>
                        </div>
                    </div>

                    <!-- Registration Modal -->
                    <div id="registrationModal" class="modal-overlay"
                        style="display: none; position: fixed; top: 0; left: 0; width: 100%; height: 100%; background: rgba(0,0,0,0.5); z-index: 2000; justify-content: center; align-items: center;">
                        <div class="signup-modal-content"
                            style="background: #fff; border-radius: 16px; width: 90%; max-width: 580px; padding: 28px; max-height: 90vh; overflow-y: auto; position: relative; box-shadow: 0 10px 30px rgba(0,0,0,0.2);">
                            <span class="close-modal"
                                onclick="document.getElementById('registrationModal').style.display='none';"
                                style="position: absolute; top: 14px; right: 18px; font-size: 24px; cursor: pointer; color: #666;">&times;</span>

                            <div style="text-align: center; margin-bottom: 20px;">
                                <img src="resources/images/logo.png" alt="Snehbandhan Logo"
                                    style="height: 44px; margin-bottom: 8px;">
                                <h3 style="margin: 0; color: var(--brand-maroon); font-size: 20px; font-weight: 700;">
                                    Create Your Account</h3>
                                <p style="margin: 4px 0 0; color: #666; font-size: 13px;">Find your ideal life partner
                                    with trusted Marathi profiles</p>
                            </div>

                            <form action="registration" method="GET">
                                <div style="margin-bottom: 14px;">
                                    <label
                                        style="font-size: 13px; font-weight: 600; color: #444; display: block; margin-bottom: 4px;">Create
                                        Profile For</label>
                                    <select name="ProfileCreatedFor" required
                                        style="width: 100%; padding: 10px 14px; border: 1px solid #ddd; border-radius: 8px; font-size: 14px; box-sizing: border-box;">
                                        <option value="">Select</option>
                                        <option value="Self">Self</option>
                                        <option value="Son">Son</option>
                                        <option value="Daughter">Daughter</option>
                                        <option value="Brother">Brother</option>
                                        <option value="Sister">Sister</option>
                                        <option value="Relative">Relative</option>
                                    </select>
                                </div>

                                <div style="display: flex; gap: 14px; margin-bottom: 14px;">
                                    <div style="flex: 1;">
                                        <label
                                            style="font-size: 13px; font-weight: 600; color: #444; display: block; margin-bottom: 4px;">Full
                                            Name</label>
                                        <input type="text" name="fullname" placeholder="First Last" required
                                            style="width: 100%; padding: 10px 14px; border: 1px solid #ddd; border-radius: 8px; font-size: 14px; box-sizing: border-box;">
                                    </div>
                                    <div style="flex: 1;">
                                        <label
                                            style="font-size: 13px; font-weight: 600; color: #444; display: block; margin-bottom: 4px;">Gender</label>
                                        <div style="display: flex; gap: 14px; margin-top: 10px;">
                                            <label><input type="radio" name="gender" value="Male" required> Male</label>
                                            <label><input type="radio" name="gender" value="Female"> Female</label>
                                        </div>
                                    </div>
                                </div>

                                <div style="display: flex; gap: 14px; margin-bottom: 14px;">
                                    <div style="flex: 1;">
                                        <label
                                            style="font-size: 13px; font-weight: 600; color: #444; display: block; margin-bottom: 4px;">Mobile
                                            Number</label>
                                        <input type="tel" name="mobile" placeholder="10-digit mobile"
                                            pattern="[0-9]{10}" required
                                            style="width: 100%; padding: 10px 14px; border: 1px solid #ddd; border-radius: 8px; font-size: 14px; box-sizing: border-box;">
                                    </div>
                                    <div style="flex: 1;">
                                        <label
                                            style="font-size: 13px; font-weight: 600; color: #444; display: block; margin-bottom: 4px;">Date
                                            of Birth</label>
                                        <input type="text" name="dob" placeholder="DD/MM/YYYY" required
                                            onfocus="(this.type='date')" onblur="(this.type='text')"
                                            style="width: 100%; padding: 10px 14px; border: 1px solid #ddd; border-radius: 8px; font-size: 14px; box-sizing: border-box;">
                                    </div>
                                </div>

                                <button type="submit" class="btn-nav-register"
                                    style="width: 100%; padding: 12px; border-radius: 8px; font-size: 15px; margin-top: 8px;">Proceed
                                    to Registration &rarr;</button>

                                <div style="text-align: center; margin-top: 14px; font-size: 13px; color: #666;">
                                    Already have an account? <a href="login"
                                        onclick="document.getElementById('registrationModal').style.display='none'; document.getElementById('loginModal').style.display='flex'; return false;"
                                        style="color: var(--brand-primary); font-weight: 600;">Login</a>
                                </div>
                            </form>
                        </div>
                    </div>

                    <script>
                        // Close modal when clicking on backdrop
                        window.onclick = function (event) {
                            var loginModal = document.getElementById('loginModal');
                            var regModal = document.getElementById('registrationModal');
                            if (event.target == loginModal) {
                                loginModal.style.display = "none";
                            }
                            if (event.target == regModal) {
                                regModal.style.display = "none";
                            }
                        }
                    </script>
                </body>

                </html>