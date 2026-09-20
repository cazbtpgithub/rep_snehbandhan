<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
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
    <meta charset="utf-8">
    <title>About Us - Snehbandhan Matrimony</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    
    <!-- Fonts & Icons -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=DM+Sans:ital,opsz,wght@0,9..40,400;0,9..40,500;0,9..40,600;0,9..40,700;0,9..40,800;1,9..40,400;1,9..40,500&family=Inter:wght@400;500;600;700&family=Mukta:wght@400;600;700&family=Playfair+Display:ital,wght@1,500;1,600&display=swap" rel="stylesheet">
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
            background-color: #ffffff;
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
            border-bottom: 1px solid rgba(0,0,0,0.04);
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

        /* ================= HERO SECTION ================= */
        .about-hero-section {
            background: #FFFDFB;
            position: relative;
            padding: 36px 0 44px;
            border-bottom: 1px solid #F6EEEF;
            overflow: hidden;
        }

        .hero-mandala {
            position: absolute;
            top: 0;
            left: 0;
            height: 280px;
            opacity: 0.85;
            pointer-events: none;
            z-index: 1;
        }

        .hero-grid {
            position: relative;
            z-index: 2;
            display: grid;
            grid-template-columns: 1.12fr 1fr;
            gap: 36px;
            align-items: center;
        }

        .section-tag {
            font-size: 12.5px;
            letter-spacing: 2px;
            text-transform: uppercase;
            font-weight: 700;
            color: var(--brand-tag);
            margin-bottom: 10px;
            display: inline-block;
        }

        .hero-heading {
            font-family: 'DM Sans', serif, sans-serif;
            font-size: 38px;
            font-weight: 800;
            color: var(--brand-maroon);
            line-height: 1.22;
            margin: 0 0 16px;
        }

        .hero-desc {
            font-size: 14.5px;
            line-height: 1.65;
            color: var(--text-muted);
            margin: 0 0 26px;
            max-width: 500px;
        }

        .hero-pills-row {
            display: flex;
            flex-wrap: wrap;
            gap: 10px;
            align-items: center;
        }

        .hero-pill {
            display: inline-flex;
            align-items: center;
            gap: 8px;
            background: #ffffff;
            border: 1px solid var(--brand-border);
            padding: 6px 12px 6px 8px;
            border-radius: 40px;
            box-shadow: 0 2px 6px rgba(0,0,0,0.02);
        }

        .pill-icon-circle {
            width: 34px;
            height: 34px;
            border-radius: 50%;
            background-color: #FDF0F4;
            color: var(--brand-primary);
            display: flex;
            align-items: center;
            justify-content: center;
            font-size: 15px;
        }

        .pill-texts {
            display: flex;
            flex-direction: column;
            line-height: 1.25;
        }

        .pill-title {
            font-size: 13.5px;
            font-weight: 700;
            color: var(--text-dark);
        }

        .pill-sub {
            font-size: 11px;
            color: #888;
        }

        .hero-banner-box {
            position: relative;
            border-radius: 16px;
            overflow: hidden;
            box-shadow: 0 8px 24px rgba(0,0,0,0.06);
        }

        .hero-banner-img {
            width: 100%;
            height: auto;
            display: block;
            border-radius: 16px;
            object-fit: cover;
        }

        /* ================= FOUNDER SECTION ================= */
        .founder-section {
            padding: 56px 0 46px;
        }

        .founder-grid {
            display: grid;
            grid-template-columns: 200px 1fr 240px;
            gap: 32px;
            align-items: flex-start;
        }

        .founder-photo-card {
            border-radius: 14px;
            overflow: hidden;
            background: #fff;
            box-shadow: 0 6px 20px rgba(0,0,0,0.06);
            position: relative;
        }

        .founder-photo {
            width: 100%;
            height: 220px;
            object-fit: cover;
            object-position: top center;
            display: block;
            background-color: #FAF4F5;
        }

        .founder-name-banner {
            background-color: #8E1B42;
            color: #ffffff;
            padding: 10px 12px;
            text-align: left;
        }

        .founder-name {
            font-size: 13.5px;
            font-weight: 700;
            line-height: 1.2;
            margin: 0;
        }

        .founder-role {
            font-size: 11px;
            color: rgba(255, 255, 255, 0.85);
            margin: 2px 0 0;
        }

        .founder-headline {
            font-family: 'DM Sans', sans-serif;
            font-size: 27px;
            font-weight: 700;
            color: var(--brand-maroon);
            line-height: 1.25;
            margin: 0 0 14px;
        }

        .founder-bio {
            font-size: 14px;
            line-height: 1.65;
            color: var(--text-muted);
            margin: 0 0 20px;
        }

        .quote-callout {
            background-color: var(--brand-pink-soft);
            border-radius: 14px;
            padding: 16px 22px;
            position: relative;
            margin-top: 18px;
        }

        .quote-icon-span {
            color: var(--brand-primary);
            font-size: 28px;
            font-family: Georgia, serif;
            line-height: 1;
            display: inline-block;
            margin-right: 6px;
            vertical-align: -4px;
        }

        .quote-callout p {
            font-size: 14px;
            font-style: italic;
            color: var(--brand-maroon);
            margin: 0;
            line-height: 1.6;
        }

        .quote-author {
            font-style: normal;
            font-weight: 600;
            font-size: 13px;
            color: #666;
            margin-top: 8px;
            display: block;
        }

        /* Stat Cards Stack */
        .stats-stack {
            display: flex;
            flex-direction: column;
            gap: 14px;
        }

        .stat-card {
            background: #ffffff;
            border: 1px solid var(--brand-border);
            border-radius: 14px;
            padding: 14px 18px;
            display: flex;
            align-items: center;
            gap: 14px;
            box-shadow: 0 2px 8px rgba(0,0,0,0.02);
            transition: transform 0.2s ease, box-shadow 0.2s ease;
        }

        .stat-card:hover {
            transform: translateY(-2px);
            box-shadow: 0 6px 16px rgba(216, 27, 96, 0.08);
        }

        .stat-icon-box {
            width: 44px;
            height: 44px;
            border-radius: 12px;
            background-color: #FDF0F3;
            color: var(--brand-primary);
            display: flex;
            align-items: center;
            justify-content: center;
            font-size: 18px;
            flex-shrink: 0;
        }

        .stat-details {
            display: flex;
            flex-direction: column;
            line-height: 1.25;
        }

        .stat-number {
            font-family: 'DM Sans', sans-serif;
            font-size: 22px;
            font-weight: 800;
            color: #8E1B42;
        }

        .stat-label {
            font-size: 11.5px;
            color: #666666;
            margin-top: 2px;
        }

        /* ================= OUR STORY SECTION ================= */
        .story-section {
            padding: 30px 0 40px;
        }

        .story-grid {
            display: grid;
            grid-template-columns: 1.05fr 1.15fr;
            gap: 36px;
            align-items: center;
        }

        .story-headline {
            font-family: 'DM Sans', sans-serif;
            font-size: 27px;
            font-weight: 700;
            color: var(--brand-maroon);
            line-height: 1.25;
            margin: 0 0 16px;
        }

        .story-text {
            font-size: 13.8px;
            line-height: 1.7;
            color: var(--text-muted);
            margin: 0 0 16px;
        }

        .story-visuals-row {
            display: flex;
            align-items: center;
            justify-content: space-between;
            gap: 12px;
        }

        .culture-cards-group {
            display: flex;
            gap: 10px;
        }

        .culture-card {
            width: 110px;
            border-radius: 12px;
            overflow: hidden;
            background: #ffffff;
            box-shadow: 0 4px 12px rgba(0,0,0,0.06);
            display: flex;
            flex-direction: column;
            border: 1px solid #f0e6e8;
        }

        .culture-card-img {
            width: 110px;
            height: 102px;
            object-fit: cover;
            display: block;
        }

        .culture-tag {
            background-color: #680E25;
            color: #ffffff;
            font-size: 11.5px;
            font-weight: 600;
            text-align: center;
            padding: 5px 0;
            letter-spacing: 0.5px;
        }

        .maharashtra-sketch-box {
            flex-shrink: 0;
            text-align: center;
        }

        .sketch-img {
            max-height: 155px;
            width: auto;
            display: block;
            margin: 0 auto;
        }

        /* ================= BOTTOM CTA BANNER ================= */
        .bottom-cta-banner {
            background-color: #FDF1F4;
            border-radius: 18px;
            padding: 24px 36px;
            margin: 40px auto 60px;
            position: relative;
            overflow: hidden;
            display: flex;
            align-items: center;
            justify-content: space-between;
            gap: 20px;
            border: 1px solid #F9E2E7;
        }

        .cta-watermark-bg {
            position: absolute;
            right: 180px;
            top: 0;
            bottom: 0;
            height: 100%;
            opacity: 0.45;
            pointer-events: none;
        }

        .cta-left-content {
            display: flex;
            align-items: center;
            gap: 18px;
            position: relative;
            z-index: 2;
        }

        .cta-family-icon {
            width: 50px;
            height: 50px;
            border-radius: 50%;
            border: 2px solid var(--brand-primary);
            color: var(--brand-primary);
            display: flex;
            align-items: center;
            justify-content: center;
            font-size: 22px;
            background-color: #fff;
            flex-shrink: 0;
        }

        .cta-texts h3 {
            font-family: 'DM Sans', sans-serif;
            font-size: 20px;
            font-weight: 700;
            color: var(--brand-maroon);
            margin: 0 0 4px;
        }

        .cta-texts p {
            font-size: 13.5px;
            color: var(--text-muted);
            margin: 0;
        }

        .cta-action-btn {
            background-color: var(--brand-primary);
            color: #ffffff;
            font-size: 14.5px;
            font-weight: 600;
            padding: 12px 28px;
            border-radius: 8px;
            text-decoration: none;
            display: inline-flex;
            align-items: center;
            gap: 8px;
            transition: all 0.2s ease;
            white-space: nowrap;
            position: relative;
            z-index: 2;
            box-shadow: 0 4px 12px rgba(216, 27, 96, 0.25);
        }

        .cta-action-btn:hover {
            background-color: var(--brand-hover);
            color: #ffffff;
            transform: translateX(3px);
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
            background: rgba(255,255,255,0.1);
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

        /* ================= RESPONSIVENESS ================= */
        @media (max-width: 991px) {
            .hero-grid {
                grid-template-columns: 1fr;
            }
            .founder-grid {
                grid-template-columns: 1fr;
            }
            .story-grid {
                grid-template-columns: 1fr;
            }
            .story-visuals-row {
                flex-direction: column;
                align-items: flex-start;
            }
            .bottom-cta-banner {
                flex-direction: column;
                align-items: flex-start;
                padding: 24px;
            }
            .cta-watermark-bg {
                display: none;
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
                    <img src="resources/images/logo.png" alt="Snehbandhan Logo" onerror="this.onerror=null; this.src='resources/UserImages/userImage.png';">
                </a>
            </div>
            
            <nav class="nav-menu">
                <a href="Home" class="nav-item-link">Home</a>
                <a href="Home" class="nav-item-link">Find Matches <i class="fa fa-angle-down" style="font-size: 12px;"></i></a>
                <a href="Home" class="nav-item-link">Success Stories</a>
                <a href="PackageHome" class="nav-item-link">Membership <i class="fa fa-angle-down" style="font-size: 12px;"></i></a>
                <a href="aboutus" class="nav-item-link active">About Us</a>
                <a href="contactus" class="nav-item-link">Contact</a>
            </nav>
            
            <div class="nav-actions">
                <a href="login" class="btn-nav-login" onclick="if(document.getElementById('loginModal')){document.getElementById('loginModal').style.display='flex'; return false;}">Login</a>
                <a href="registration" class="btn-nav-register" onclick="if(document.getElementById('registrationModal')){document.getElementById('registrationModal').style.display='flex'; return false;}">Register Free</a>
                <div class="mobile-toggle" onclick="var m = document.getElementById('mobileMenu'); m.style.display = m.style.display === 'flex' ? 'none' : 'flex';">
                    <i class="fa fa-bars"></i>
                </div>
            </div>
        </div>
        
        <!-- Mobile Menu Dropdown -->
        <div id="mobileMenu" style="display: none; flex-direction: column; background: #fff; padding: 16px 24px; border-top: 1px solid #eee; gap: 12px;">
            <a href="Home" style="text-decoration: none; color: #333; font-weight: 500;">Home</a>
            <a href="Home" style="text-decoration: none; color: #333; font-weight: 500;">Find Matches</a>
            <a href="Home" style="text-decoration: none; color: #333; font-weight: 500;">Success Stories</a>
            <a href="PackageHome" style="text-decoration: none; color: #333; font-weight: 500;">Membership</a>
            <a href="aboutus" style="text-decoration: none; color: var(--brand-primary); font-weight: 600;">About Us</a>
            <a href="contactus" style="text-decoration: none; color: #333; font-weight: 500;">Contact</a>
        </div>
    </header>

    <!-- Hero Section -->
    <section class="about-hero-section">
        <img src="resources/images/mandala_pattern.png" class="hero-mandala" alt="Mandala Motif" onerror="this.style.display='none';">
        <div class="page-container">
            <div class="hero-grid">
                <!-- Left: Headline & Features -->
                <div>
                    <span class="section-tag">ABOUT US</span>
                    <h1 class="hero-heading">Bringing Hearts Together<br>for a Happier Tomorrow</h1>
                    <p class="hero-desc">Snehbandhan Matrimony is a trusted Marathi matrimony platform, connecting families and creating lifelong relationships with values, trust and tradition.</p>
                    
                    <div class="hero-pills-row">
                        <div class="hero-pill">
                            <div class="pill-icon-circle">
                                <i class="fa fa-heart-o"></i>
                            </div>
                            <div class="pill-texts">
                                <span class="pill-title">Trust</span>
                                <span class="pill-sub">Built on Values</span>
                            </div>
                        </div>

                        <div class="hero-pill">
                            <div class="pill-icon-circle">
                                <i class="fa fa-users"></i>
                            </div>
                            <div class="pill-texts">
                                <span class="pill-title">Community</span>
                                <span class="pill-sub">A Strong Network</span>
                            </div>
                        </div>

                        <div class="hero-pill">
                            <div class="pill-icon-circle">
                                <i class="fa fa-shield"></i>
                            </div>
                            <div class="pill-texts">
                                <span class="pill-title">Safety</span>
                                <span class="pill-sub">100% Verified</span>
                            </div>
                        </div>

                        <div class="hero-pill">
                            <div class="pill-icon-circle">
                                <i class="fa fa-handshake-o"></i>
                            </div>
                            <div class="pill-texts">
                                <span class="pill-title">Support</span>
                                <span class="pill-sub">Always with You</span>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Right: Hero Banner Image -->
                <div>
                    <div class="hero-banner-box">
                        <img src="resources/images/hero_couple_banner.png" class="hero-banner-img" alt="More than matches, we create families" onerror="this.onerror=null; this.src='resources/images/about_hero_couple.jpg';">
                    </div>
                </div>
            </div>
        </div>
    </section>

    <!-- Main Content Container -->
    <main class="page-container">
        <!-- ================= OUR FOUNDER ================= -->
        <section class="founder-section">
            <div class="founder-grid">
                <!-- Founder Photo Card -->
                <div>
                    <div class="founder-photo-card">
                        <img src="resources/images2/amin_image.png" class="founder-photo" alt="Mr. Amin Mullani" onerror="this.onerror=null; this.src='resources/images/founder_amin_card.png';">
                        <div class="founder-name-banner">
                            <div class="founder-name">Mr. Amin Mullani (M.D)</div>
                            <div class="founder-role">Director, Snehbandhan Matrimony</div>
                        </div>
                    </div>
                </div>

                <!-- Founder Story & Quote -->
                <div>
                    <span class="section-tag">OUR FOUNDER</span>
                    <h2 class="founder-headline">A Vision to Strengthen Families</h2>
                    <p class="founder-bio">
                        Mr. Amin Mullani, Director of Snehbandhan Matrimony, Pune, Kolhapur. We started our matrimonial social activity over 2 years ago across Maharashtra.
                    </p>
                    <p class="founder-bio">
                        Today, we have 48+ franchisees and 77+ dealers in Maharashtra, and are joined by thousands of happy families who trust us.
                    </p>

                    <div class="quote-callout">
                        <p><span class="quote-icon-span">“</span>Our goal is simple – to help individuals and families find the right life partner with trust, respect and cultural values.”</p>
                        <span class="quote-author">— Mr. Amin Mullani</span>
                    </div>
                </div>

                <!-- Stat Cards Stack -->
                <div class="stats-stack">
                    <div class="stat-card">
                        <div class="stat-icon-box">
                            <i class="fa fa-users"></i>
                        </div>
                        <div class="stat-details">
                            <span class="stat-number">48+</span>
                            <span class="stat-label">Franchisees<br>Across Maharashtra</span>
                        </div>
                    </div>

                    <div class="stat-card">
                        <div class="stat-icon-box">
                            <i class="fa fa-home"></i>
                        </div>
                        <div class="stat-details">
                            <span class="stat-number">77+</span>
                            <span class="stat-label">Dealers in Maharashtra</span>
                        </div>
                    </div>

                    <div class="stat-card">
                        <div class="stat-icon-box">
                            <i class="fa fa-heart-o"></i>
                        </div>
                        <div class="stat-details">
                            <span class="stat-number">Thousands</span>
                            <span class="stat-label">Happy Families</span>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <!-- ================= OUR STORY ================= -->
        <section class="story-section">
            <div class="story-grid">
                <!-- Left Story Column -->
                <div>
                    <span class="section-tag">OUR STORY</span>
                    <h2 class="story-headline">Rooted in Tradition, Built for Today</h2>
                    <p class="story-text">
                        Snehbandhan Matrimony is one of the largest and most trusted matrimony platforms in Maharashtra. We understand the importance of choosing the right partner, especially in the Maharashtrian culture. Our relationship managers provide a secure, personalized and trusted matchmaking experience with complete privacy, verified profiles and dedicated support.
                    </p>
                    <p class="story-text">
                        We go beyond just a platform — we bring families closer, create meaningful bonds, and make the journey of finding a life partner simple, safe and joyful.
                    </p>
                </div>

                <!-- Right Visuals Column -->
                <div class="story-visuals-row">
                    <div class="culture-cards-group">
                        <!-- Tradition Card -->
                        <div class="culture-card">
                            <img src="resources/images/card_tradition.png" class="culture-card-img" alt="Tradition" onerror="this.onerror=null; this.src='resources/images/about_tradition.jpg';">
                            <div class="culture-tag">Tradition</div>
                        </div>

                        <!-- Culture Card -->
                        <div class="culture-card">
                            <img src="resources/images/card_culture.png" class="culture-card-img" alt="Culture">
                            <div class="culture-tag">Culture</div>
                        </div>

                        <!-- Values Card -->
                        <div class="culture-card">
                            <img src="resources/images/card_values.png" class="culture-card-img" alt="Values">
                            <div class="culture-tag">Values</div>
                        </div>
                    </div>

                    <!-- Maharashtra Architectural Sketch & Calligraphy -->
                    <div class="maharashtra-sketch-box">
                        <img src="resources/images/sketch_maharashtra.png" class="sketch-img" alt="Maharashtra Jodte Manane, Jodte Sanskruti">
                    </div>
                </div>
            </div>
        </section>

        <!-- ================= BOTTOM CTA BANNER ================= -->
        <section class="bottom-cta-banner">
            <img src="resources/images/cta_watermark.png" class="cta-watermark-bg" alt="Celebration Watermark">
            <div class="cta-left-content">
                <div class="cta-family-icon">
                    <i class="fa fa-users"></i>
                </div>
                <div class="cta-texts">
                    <h3>A Stronger Maharashtra Through Stronger Families</h3>
                    <p>Join thousands who found their perfect match on Snehbandhan.</p>
                </div>
            </div>
            
            <a href="registration" class="cta-action-btn" onclick="if(document.getElementById('registrationModal')){document.getElementById('registrationModal').style.display='flex'; return false;}">
                Register Free Now &nbsp;<i class="fa fa-arrow-right"></i>
            </a>
        </section>
    </main>

    <!-- Footer -->
    <footer class="site-footer">
        <div class="page-container">
            <div class="footer-socials">
                <a href="https://www.facebook.com/snehbandhanburo" class="footer-social-link" target="_blank"><i class="fa fa-facebook"></i></a>
                <a href="https://twitter.com/snehbandhan" class="footer-social-link" target="_blank"><i class="fa fa-twitter"></i></a>
                <a href="https://goo.gl/maps/QGa85D23gVP2" class="footer-social-link" target="_blank"><i class="fa fa-google-plus"></i></a>
            </div>
            <p style="margin: 0; color: #A0AEC0;">
                Copyright &copy; Snehbandhan Matrimony. All Rights Reserved. &nbsp;|&nbsp; 
                <a href="aboutus" style="color: #CBD5E0; text-decoration: none;">About Us</a> &nbsp;|&nbsp; 
                <a href="contactus" style="color: #CBD5E0; text-decoration: none;">Contact Us</a>
            </p>
        </div>
    </footer>

    <!-- Login Modal -->
    <div id="loginModal" class="modal-overlay" style="display: none; position: fixed; top: 0; left: 0; width: 100%; height: 100%; background: rgba(0,0,0,0.5); z-index: 2000; justify-content: center; align-items: center;">
        <div class="split-modal-content" style="background: #fff; border-radius: 16px; width: 90%; max-width: 760px; display: flex; position: relative; overflow: hidden; box-shadow: 0 10px 30px rgba(0,0,0,0.2);">
            <span class="close-modal" onclick="document.getElementById('loginModal').style.display='none';" style="position: absolute; top: 12px; right: 18px; font-size: 24px; cursor: pointer; color: #666; z-index: 10;">&times;</span>
            
            <div class="split-modal-left" style="background: #FFF4F7; width: 45%; padding: 36px 28px; display: flex; flex-direction: column; justify-content: center;">
                <img src="resources/images/logo.png" alt="Snehbandhan Logo" style="height: 42px; margin-bottom: 20px; align-self: flex-start;">
                <h3 style="margin: 0; font-size: 16px; color: #888;">Welcome Back!</h3>
                <h2 style="margin: 4px 0 10px; font-size: 24px; color: var(--brand-maroon);">Login to Continue</h2>
                <p style="font-size: 13px; color: #666; margin: 0;">Find your perfect Marathi match with trusted families.</p>
            </div>
            
            <div class="split-modal-right" style="width: 55%; padding: 36px 28px;">
                <div style="font-size: 13px; color: #666; margin-bottom: 18px; text-align: right;">
                    New user? <a href="registration" style="color: var(--brand-primary); font-weight: 600;" onclick="document.getElementById('loginModal').style.display='none'; document.getElementById('registrationModal').style.display='flex'; return false;">Create Account</a>
                </div>
                
                <form action="<c:url value='j_spring_security_check'/>" method="POST">
                    <div style="margin-bottom: 14px; position: relative;">
                        <input type="text" name="j_username" placeholder="Mobile Number / Email ID" required autofocus style="width: 100%; padding: 12px 16px; border: 1px solid #ddd; border-radius: 8px; font-size: 14px; box-sizing: border-box;">
                    </div>
                    
                    <div style="margin-bottom: 14px; position: relative;">
                        <input type="password" name="j_password" placeholder="Password" required style="width: 100%; padding: 12px 16px; border: 1px solid #ddd; border-radius: 8px; font-size: 14px; box-sizing: border-box;">
                    </div>
                    
                    <div style="text-align: right; margin-bottom: 16px;">
                        <a href="<c:url value='forgotpassword'/>" style="font-size: 12px; color: #888; text-decoration: none;">Forgot Password?</a>
                    </div>
                    
                    <button type="submit" class="btn-nav-register" style="width: 100%; padding: 12px; border-radius: 8px; font-size: 15px;">Login</button>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                </form>
            </div>
        </div>
    </div>

    <!-- Registration Modal -->
    <div id="registrationModal" class="modal-overlay" style="display: none; position: fixed; top: 0; left: 0; width: 100%; height: 100%; background: rgba(0,0,0,0.5); z-index: 2000; justify-content: center; align-items: center;">
        <div class="signup-modal-content" style="background: #fff; border-radius: 16px; width: 90%; max-width: 580px; padding: 28px; max-height: 90vh; overflow-y: auto; position: relative; box-shadow: 0 10px 30px rgba(0,0,0,0.2);">
            <span class="close-modal" onclick="document.getElementById('registrationModal').style.display='none';" style="position: absolute; top: 14px; right: 18px; font-size: 24px; cursor: pointer; color: #666;">&times;</span>
            
            <div style="text-align: center; margin-bottom: 20px;">
                <img src="resources/images/logo.png" alt="Snehbandhan Logo" style="height: 44px; margin-bottom: 8px;">
                <h3 style="margin: 0; color: var(--brand-maroon); font-size: 20px; font-weight: 700;">Create Your Account</h3>
                <p style="margin: 4px 0 0; color: #666; font-size: 13px;">Find your ideal life partner with trusted Marathi profiles</p>
            </div>

            <form action="registration" method="GET">
                <div style="margin-bottom: 14px;">
                    <label style="font-size: 13px; font-weight: 600; color: #444; display: block; margin-bottom: 4px;">Create Profile For</label>
                    <select name="ProfileCreatedFor" required style="width: 100%; padding: 10px 14px; border: 1px solid #ddd; border-radius: 8px; font-size: 14px; box-sizing: border-box;">
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
                        <label style="font-size: 13px; font-weight: 600; color: #444; display: block; margin-bottom: 4px;">Full Name</label>
                        <input type="text" name="fullname" placeholder="First Last" required style="width: 100%; padding: 10px 14px; border: 1px solid #ddd; border-radius: 8px; font-size: 14px; box-sizing: border-box;">
                    </div>
                    <div style="flex: 1;">
                        <label style="font-size: 13px; font-weight: 600; color: #444; display: block; margin-bottom: 4px;">Gender</label>
                        <div style="display: flex; gap: 14px; margin-top: 10px;">
                            <label><input type="radio" name="gender" value="Male" required> Male</label>
                            <label><input type="radio" name="gender" value="Female"> Female</label>
                        </div>
                    </div>
                </div>

                <div style="display: flex; gap: 14px; margin-bottom: 14px;">
                    <div style="flex: 1;">
                        <label style="font-size: 13px; font-weight: 600; color: #444; display: block; margin-bottom: 4px;">Mobile Number</label>
                        <input type="tel" name="mobile" placeholder="10-digit mobile" pattern="[0-9]{10}" required style="width: 100%; padding: 10px 14px; border: 1px solid #ddd; border-radius: 8px; font-size: 14px; box-sizing: border-box;">
                    </div>
                    <div style="flex: 1;">
                        <label style="font-size: 13px; font-weight: 600; color: #444; display: block; margin-bottom: 4px;">Date of Birth</label>
                        <input type="text" name="dob" placeholder="DD/MM/YYYY" required onfocus="(this.type='date')" onblur="(this.type='text')" style="width: 100%; padding: 10px 14px; border: 1px solid #ddd; border-radius: 8px; font-size: 14px; box-sizing: border-box;">
                    </div>
                </div>

                <button type="submit" class="btn-nav-register" style="width: 100%; padding: 12px; border-radius: 8px; font-size: 15px; margin-top: 8px;">Proceed to Registration &rarr;</button>

                <div style="text-align: center; margin-top: 14px; font-size: 13px; color: #666;">
                    Already have an account? <a href="login" onclick="document.getElementById('registrationModal').style.display='none'; document.getElementById('loginModal').style.display='flex'; return false;" style="color: var(--brand-primary); font-weight: 600;">Login</a>
                </div>
            </form>
        </div>
    </div>

    <script>
        // Close modal when clicking on backdrop
        window.onclick = function(event) {
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
