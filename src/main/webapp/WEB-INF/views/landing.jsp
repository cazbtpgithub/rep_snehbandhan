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
    <title>Snehbandhan - The Most Trusted Marathi Matrimony Platform</title>
    <meta name="viewport" content="width=device-width, initial-scale=1," shrink-to-fit=no">
    
    <!-- CSS Dependencies -->
    <link rel="stylesheet" href="resources/Home/css/bootstrap.min.css">
    <link rel="stylesheet" href="resources/Home/css/font-awesome.min.css">
    <link rel="stylesheet" href="resources/customcss/snehbandhan_brand.css">
    
    <style>
        /* ================= PREMIUM FAQ ACCORDION ================= */
        .landing-faqs-section {
            background-color: var(--bg-color, #FFF9F6);
            padding: 60px 0 70px;
        }

        .landing-faq-header {
            text-align: center;
            max-width: 680px;
            margin: 0 auto 38px;
            padding: 0 15px;
        }

        .landing-faq-badge {
            font-size: 11.5px;
            letter-spacing: 1.5px;
            text-transform: uppercase;
            font-weight: 700;
            color: var(--primary, #D81B74);
            background-color: #FDF0F4;
            padding: 5px 16px;
            border-radius: 20px;
            display: inline-block;
            margin-bottom: 12px;
        }

        .landing-faq-title {
            font-family: 'DM Sans', sans-serif;
            font-size: 32px;
            font-weight: 800;
            color: var(--heading-color, #2E2E2E);
            margin: 0 0 10px;
            line-height: 1.25;
        }

        .landing-faq-subtitle {
            font-size: 15px;
            color: var(--body-color, #666666);
            line-height: 1.6;
            margin: 0;
        }

        .landing-faq-list {
            max-width: 860px;
            margin: 0 auto;
            padding: 0 15px;
            display: flex;
            flex-direction: column;
            gap: 14px;
        }

        .landing-faq-item {
            background: #ffffff;
            border: 1.5px solid #F0E4E8;
            border-radius: 14px;
            overflow: hidden;
            transition: border-color 0.25s ease, box-shadow 0.25s ease;
            box-shadow: 0 2px 8px rgba(0, 0, 0, 0.02);
        }

        .landing-faq-item:hover {
            border-color: #E6B8C6;
            box-shadow: 0 4px 16px rgba(216, 27, 116, 0.06);
        }

        .landing-faq-item.active {
            border-color: var(--primary, #D81B74);
            box-shadow: 0 4px 18px rgba(216, 27, 116, 0.1);
        }

        .landing-faq-btn {
            width: 100%;
            padding: 18px 24px;
            display: flex;
            align-items: center;
            justify-content: space-between;
            gap: 18px;
            background: none;
            border: none;
            cursor: pointer;
            text-align: left;
            user-select: none;
            transition: background-color 0.2s ease;
        }

        .landing-faq-item.active .landing-faq-btn {
            background-color: #FFFAFC;
        }

        .landing-faq-btn-left {
            display: flex;
            align-items: center;
            gap: 16px;
            flex: 1;
        }

        .landing-faq-num {
            font-family: 'DM Sans', sans-serif;
            font-size: 13px;
            font-weight: 700;
            color: #A83858;
            background-color: #FDF0F4;
            width: 32px;
            height: 32px;
            border-radius: 50%;
            display: flex;
            align-items: center;
            justify-content: center;
            flex-shrink: 0;
            transition: all 0.25s ease;
        }

        .landing-faq-item.active .landing-faq-num {
            background-color: var(--primary, #D81B74);
            color: #ffffff;
        }

        .landing-faq-question {
            font-family: 'DM Sans', sans-serif;
            font-size: 16.5px;
            font-weight: 700;
            color: #2E2E2E;
            line-height: 1.4;
            margin: 0;
            transition: color 0.2s ease;
        }

        .landing-faq-item.active .landing-faq-question {
            color: var(--secondary, #7A1F3D);
        }

        .landing-faq-toggle-icon {
            width: 34px;
            height: 34px;
            border-radius: 50%;
            background-color: #FDF0F4;
            display: flex;
            align-items: center;
            justify-content: center;
            flex-shrink: 0;
            transition: transform 0.3s ease, background-color 0.25s ease, color 0.25s ease;
            color: var(--primary, #D81B74);
            font-size: 14px;
        }

        .landing-faq-item.active .landing-faq-toggle-icon {
            background-color: var(--primary, #D81B74);
            color: #ffffff;
            transform: rotate(45deg);
        }

        .landing-faq-collapse {
            max-height: 0;
            overflow: hidden;
            transition: max-height 0.35s cubic-bezier(0.4, 0, 0.2, 1);
        }

        .landing-faq-answer {
            padding: 0 24px 20px 72px;
            font-size: 14.5px;
            line-height: 1.7;
            color: #555555;
            border-top: 1px dashed transparent;
        }

        .landing-faq-item.active .landing-faq-answer {
            border-top-color: #F8E2E7;
            padding-top: 16px;
        }

        @media (max-width: 768px) {
            .landing-faq-title {
                font-size: 24px;
            }
            .landing-faq-btn {
                padding: 16px;
                gap: 12px;
            }
            .landing-faq-question {
                font-size: 15px;
            }
            .landing-faq-answer {
                padding: 14px 16px 18px 16px;
                font-size: 14px;
            }
        }

        html {
            scroll-behavior: smooth;
        }

        #success-stories {
            scroll-margin-top: 85px;
        }

        /* ================= SUCCESS STORIES COMPACT & PREMIUM ================= */
        .success-stories {
            padding: 55px 0 65px;
            background: #FFFFFF;
        }

        .stories-grid {
            display: grid;
            grid-template-columns: repeat(3, 1fr);
            gap: 22px;
            margin-top: 28px;
        }

        .story-card-compact {
            background: #FFFFFF;
            border: 1px solid #F0E4E8;
            border-radius: 16px;
            padding: 14px 14px 16px 14px;
            box-shadow: 0 4px 16px rgba(0, 0, 0, 0.03);
            transition: transform 0.25s ease, box-shadow 0.25s ease, border-color 0.25s ease;
            display: flex;
            flex-direction: column;
            text-align: left;
            position: relative;
        }

        .story-card-compact:hover {
            transform: translateY(-4px);
            border-color: #E6B8C6;
            box-shadow: 0 10px 28px rgba(216, 27, 116, 0.09);
        }

        .story-img-box {
            position: relative;
            width: 100%;
            height: 195px;
            border-radius: 12px;
            overflow: hidden;
            background: #F8F9FA;
            margin-bottom: 14px;
        }

        .story-img-box img {
            width: 100%;
            height: 100%;
            object-fit: cover;
            display: block;
            transition: transform 0.3s ease;
        }

        .story-img-komal {
            object-position: center 18% !important;
        }

        .story-img-vinoti {
            object-position: center 36% !important;
        }

        .story-img-tushar {
            object-position: center 15% !important;
        }

        .story-card-compact:hover .story-img-box img {
            transform: scale(1.04);
        }

        .story-badge-married {
            position: absolute;
            top: 10px;
            right: 10px;
            background: rgba(216, 27, 116, 0.9);
            backdrop-filter: blur(4px);
            color: #ffffff;
            font-size: 11px;
            font-weight: 600;
            padding: 3px 10px;
            border-radius: 20px;
            display: inline-flex;
            align-items: center;
            gap: 4px;
            box-shadow: 0 2px 6px rgba(0,0,0,0.15);
        }

        .story-content-box {
            display: flex;
            flex-direction: column;
            flex-grow: 1;
        }

        .story-couple-header {
            display: flex;
            align-items: center;
            justify-content: space-between;
            margin-bottom: 4px;
        }

        .story-couple-title {
            font-family: 'DM Sans', sans-serif;
            font-size: 16.5px;
            font-weight: 700;
            color: #2E2E2E;
            margin: 0;
            display: inline-flex;
            align-items: center;
            gap: 6px;
        }

        .story-heart-icon {
            color: var(--primary, #D81B74);
            font-size: 12px;
        }

        .story-loc-pill {
            font-size: 11.5px;
            color: #718096;
            background: #F7FAFC;
            border: 1px solid #EDF2F7;
            padding: 2px 8px;
            border-radius: 10px;
            display: inline-flex;
            align-items: center;
            gap: 4px;
        }

        .story-loc-pill i {
            color: var(--primary, #D81B74);
            font-size: 10px;
        }

        .story-preview-text {
            font-size: 13px;
            line-height: 1.55;
            color: #666666;
            margin: 8px 0 14px;
            display: -webkit-box;
            -webkit-line-clamp: 2;
            -webkit-box-orient: vertical;
            overflow: hidden;
            text-overflow: ellipsis;
            min-height: 40px;
        }

        .btn-view-story {
            margin-top: auto;
            background: #FFF2F5;
            color: var(--primary, #D81B74);
            border: 1.5px solid #F8D0DB;
            padding: 8px 16px;
            border-radius: 20px;
            font-size: 12.5px;
            font-weight: 600;
            cursor: pointer;
            display: inline-flex;
            align-items: center;
            justify-content: center;
            gap: 6px;
            transition: all 0.2s ease;
            width: 100%;
            box-sizing: border-box;
        }

        .btn-view-story:hover {
            background: var(--primary, #D81B74);
            color: #ffffff;
            border-color: var(--primary, #D81B74);
            box-shadow: 0 4px 12px rgba(216, 27, 116, 0.2);
            transform: translateY(-1px);
        }

        /* Story Details Modal */
        .story-modal-overlay {
            display: none;
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background: rgba(15, 23, 42, 0.65);
            backdrop-filter: blur(5px);
            -webkit-backdrop-filter: blur(5px);
            z-index: 2200;
            justify-content: center;
            align-items: center;
            padding: 20px;
            box-sizing: border-box;
        }

        .story-modal-dialog {
            background: #ffffff;
            border-radius: 20px;
            width: 100%;
            max-width: 600px;
            overflow: hidden;
            box-shadow: 0 25px 50px -12px rgba(0, 0, 0, 0.25);
            animation: storyPopIn 0.25s cubic-bezier(0.16, 1, 0.3, 1);
            display: flex;
            flex-direction: column;
            max-height: 88vh;
        }

        @keyframes storyPopIn {
            from {
                opacity: 0;
                transform: scale(0.95) translateY(10px);
            }
            to {
                opacity: 1;
                transform: scale(1) translateY(0);
            }
        }

        @media (max-width: 991px) {
            .stories-grid {
                grid-template-columns: 1fr;
                gap: 16px;
            }
        }
        /* ================= FEATURED PROFILES ENHANCEMENT ================= */
        .featured-profiles .profile-card {
            background: #fff;
            border-radius: 16px;
            border: 1px solid #eee;
            overflow: hidden;
            box-shadow: 0 4px 20px rgba(0,0,0,0.06);
            transition: transform 0.3s cubic-bezier(0.165, 0.84, 0.44, 1), box-shadow 0.3s cubic-bezier(0.165, 0.84, 0.44, 1);
            display: flex;
            flex-direction: column;
        }

        .featured-profiles .profile-card:hover {
            transform: translateY(-6px);
            box-shadow: 0 14px 30px rgba(0,0,0,0.12) !important;
        }

        .featured-profiles .profile-img-wrap {
            position: relative;
            height: 290px;
            overflow: hidden;
            background: #f0f2f5;
            cursor: pointer;
        }

        .featured-profiles .profile-img-wrap img {
            width: 100%;
            height: 100%;
            object-fit: cover;
            object-position: center 20%;
            display: block;
            transition: transform 0.45s ease;
        }

        .featured-profiles .profile-card:hover .profile-img-wrap img {
            transform: scale(1.05);
        }
        .section {
            padding: 0 !important;
        }
    </style>
</head>
<body>

    <!-- Navbar -->
    <nav class="navbar">
        <div class="logo">
            <a href="Home"><img src="resources/images/logo.png" alt="Snehbandhan Logo" style="height: 50px"></a>
        </div>
        <div class="nav-links">
            <a href="Home" style="color: var(--primary); font-weight: 600;"><i class="fa fa-home"></i> Home</a>
            <a href="#">Find Matches <i class="fa fa-angle-down"></i></a>
            <a href="#success-stories" onclick="var el = document.getElementById('success-stories'); if(el){ el.scrollIntoView({behavior: 'smooth'}); return false; }">Success Stories</a>
            <a href="PackageHome">Membership <i class="fa fa-angle-down"></i></a>
            <a href="aboutus">About Us</a>
            <a href="contactus">Contact</a>
        </div>
        <div class="nav-actions">
            <a href="#" class="btn-outline" onclick="document.getElementById('loginModal').style.display='flex'; return false;">Login</a>
            <a href="#" class="btn-primary" onclick="document.getElementById('registrationModal').style.display='flex'; return false;">Register Free</a>
        </div>
        <div class="mobile-controls" style="display: none;">
            <a href="#" class="btn-outline" style="height: 36px; padding: 0 16px; border-radius: 8px; font-size: 14px;" onclick="document.getElementById('loginModal').style.display='flex'; return false;">Login</a>
            <div class="mobile-menu-toggle" style="display: block;">
                <i class="fa fa-bars"></i>
            </div>
        </div>
    </nav>

    <!-- Hero Section -->
    <section class="hero">
        <div class="hero-content">
            <span class="badge">Most Trusted Marathi Matrimony Platform</span>
            <h1>Find Your Perfect<br><span>Marathi Match</span></h1>
            <p>Thousands of Marathi families have trusted Snehbandhan<br>to begin their beautiful journey.</p>
            
            <div class="hero-features">
                <div class="hero-feature">
                    <i class="fa fa-check"></i> 100% Verified Profiles
                </div>
                <div class="hero-feature">
                    <i class="fa fa-shield"></i> Privacy Protected
                </div>
                <div class="hero-feature">
                    <i class="fa fa-heart"></i> Family Trusted
                </div>
            </div>
            
            <div class="hero-actions">
                <a href="#" class="btn-primary" style="height: var(--btn-height);" onclick="document.getElementById('registrationModal').style.display='flex'; return false;">Register Free</a>
                <a href="#" class="btn-outline" style="height: var(--btn-height);">Browse Profiles</a>
            </div>
        </div>
        <div class="hero-image">
            <img src="resources/images/hero_couple.jpg" alt="Marathi Matrimony Couple" class="main-img">
            
            <!-- Trust Indicators -->
            <div class="floating-card floating-card-1">
                <div class="icon-box"><i class="fa fa-check-square"></i></div>
                <div class="content">100% Verified</div>
            </div>
            <div class="floating-card floating-card-2">
                <div class="icon-box"><i class="fa fa-heart"></i></div>
                <div class="content">50,000+<br><span style="font-size: 12px; color: var(--body-color); font-weight: 400;">Success Stories</span></div>
            </div>
            <div class="floating-card floating-card-3">
                <div class="icon-box"><i class="fa fa-lock"></i></div>
                <div class="content">Complete Privacy</div>
            </div>
        </div>
    </section>

    <!-- Stats Section -->
    <div class="stats-container">
        <div class="stat-item">
            <i class="fa fa-users stat-icon"></i>
            <div class="stat-content">
                <h3>2 Lakh+</h3>
                <p>Registered Members</p>
            </div>
        </div>
        <div style="width: 1px; background: #eee;"></div>
        <div class="stat-item">
            <i class="fa fa-heart stat-icon"></i>
            <div class="stat-content">
                <h3>50,000+</h3>
                <p>Happy Marriages</p>
            </div>
        </div>
        <div style="width: 1px; background: #eee;"></div>
        <div class="stat-item">
            <i class="fa fa-shield stat-icon"></i>
            <div class="stat-content">
                <h3>100%</h3>
                <p>Verified Profiles</p>
            </div>
        </div>
        <div style="width: 1px; background: #eee;"></div>
        <div class="stat-item">
            <i class="fa fa-headphones stat-icon"></i>
            <div class="stat-content">
                <h3>24x7</h3>
                <p>Customer Support</p>
            </div>
        </div>
    </div>

    <!-- How It Works -->
    <section class="how-it-works">
        <h2>How It Works</h2>
        <div class="steps-container">
            <div class="step">
                <div class="step-icon"><i class="fa fa-user-o"></i></div>
                <p>1. Create Profile</p>
            </div>
            <div class="step-arrow"><i class="fa fa-long-arrow-right"></i></div>
            <div class="step">
                <div class="step-icon"><i class="fa fa-shield"></i></div>
                <p>2. Get Verified</p>
            </div>
            <div class="step-arrow"><i class="fa fa-long-arrow-right"></i></div>
            <div class="step">
                <div class="step-icon"><i class="fa fa-search"></i></div>
                <p>3. Find Matches</p>
            </div>
            <div class="step-arrow"><i class="fa fa-long-arrow-right"></i></div>
            <div class="step">
                <div class="step-icon"><i class="fa fa-comments-o"></i></div>
                <p>4. Start Conversation</p>
            </div>
            <div class="step-arrow"><i class="fa fa-long-arrow-right"></i></div>
            <div class="step">
                <div class="step-icon"><i class="fa fa-heart-o"></i></div>
                <p>5. Get Married</p>
            </div>
        </div>
    </section>

    <!-- Featured Profiles Section -->
    <c:if test="${not empty featuredProfiles}">
    <section class="featured-profiles" style="padding: 60px 0; background: #fff;">
        <div class="container">
            <div class="section-header" style="text-align: center; margin-bottom: 40px;">
                <span class="badge" style="background: rgba(194, 0, 66, 0.1); color: var(--primary); padding: 6px 16px; border-radius: 20px; font-weight: 600; font-size: 13px;">Handpicked Matches</span>
                <h2 style="font-size: 32px; color: #111; margin-top: 10px; font-weight: 700;">Featured Profiles</h2>
                <p style="color: #666; font-size: 15px; margin-top: 8px;">Explore our most shortlisted verified profiles</p>
            </div>
            
            <div class="profiles-grid" style="display: grid; grid-template-columns: repeat(auto-fit, minmax(250px, 1fr)); gap: 24px;">
                <c:forEach var="profile" items="${featuredProfiles}">
                    <div class="profile-card">
                        <div class="profile-img-wrap" onclick="imageSlider('${profile.userId}');">
                            <c:choose>
                                <c:when test="${not empty profile.imageObject and not empty profile.imageObject[0]}">
                                    <img src="resources/UserImages/${profile.imageObject[0]}" alt="${profile.name}" onerror="this.onerror=null; this.src='resources/UserImages/userImage.png'; this.style.objectFit='cover'; this.style.objectPosition='center 20%';">
                                </c:when>
                                <c:otherwise>
                                    <img src="resources/UserImages/userImage.png" alt="${profile.name}" onerror="this.onerror=null; this.src='resources/UserImages/userImage.png';">
                                </c:otherwise>
                            </c:choose>
                            <div style="position: absolute; bottom: 0; left: 0; right: 0; height: 60px; background: linear-gradient(to top, rgba(0,0,0,0.4), transparent); pointer-events: none;"></div>
                            <span class="badge-featured" style="position: absolute; top: 12px; right: 12px; background: rgba(194, 0, 66, 0.9); color: #fff; padding: 4px 12px; border-radius: 12px; font-size: 11px; font-weight: 600; box-shadow: 0 2px 6px rgba(0,0,0,0.2);"><i class="fa fa-star" style="color: #ffc107; margin-right: 4px;"></i> Top Shortlisted</span>
                            <c:if test="${not empty profile.imageObject and profile.imageObject.size() gt 1}">
                                <span style="position: absolute; bottom: 10px; right: 12px; background: rgba(0,0,0,0.6); color: #fff; padding: 3px 8px; border-radius: 10px; font-size: 11px; font-weight: 600; display: flex; align-items: center; gap: 4px; backdrop-filter: blur(2px);">
                                    <i class="fa fa-camera"></i> ${profile.imageObject.size()}
                                </span>
                            </c:if>
                        </div>
                        
                        <div class="profile-details" style="padding: 20px; flex-grow: 1; display: flex; flex-direction: column;">
                            <h3 style="font-size: 18px; font-weight: 700; color: #222; margin: 0 0 8px 0;">${profile.name}</h3>
                            
                            <div class="profile-meta" style="font-size: 13px; color: #666; margin-bottom: 12px; display: flex; flex-wrap: wrap; gap: 12px;">
                                <c:if test="${profile.age gt 0}">
                                    <span><i class="fa fa-calendar-o" style="color: var(--primary); margin-right: 4px;"></i> ${profile.age} Yrs</span>
                                </c:if>
                                <c:if test="${not empty profile.location}">
                                    <span><i class="fa fa-map-marker" style="color: var(--primary); margin-right: 4px;"></i> ${profile.location}</span>
                                </c:if>
                            </div>
                            
                            <div class="profile-tags" style="font-size: 13px; color: #555; line-height: 1.6; margin-bottom: 20px; flex-grow: 1;">
                                <c:if test="${not empty profile.education}">
                                    <div style="margin-bottom: 4px;"><i class="fa fa-graduation-cap" style="width: 18px; color: #888;"></i> ${profile.education}</div>
                                </c:if>
                                <c:if test="${not empty profile.occupation}">
                                    <div style="margin-bottom: 4px;"><i class="fa fa-briefcase" style="width: 18px; color: #888;"></i> ${profile.occupation}</div>
                                </c:if>
                                <c:if test="${not empty profile.caste}">
                                    <div><i class="fa fa-users" style="width: 18px; color: #888;"></i> ${profile.caste}</div>
                                </c:if>
                            </div>
                            
                            <a href="ViewProfileWithPartner?userId=${profile.userId}" class="btn-primary" style="text-align: center; width: 100%; border-radius: 10px; padding: 10px; font-size: 14px; font-weight: 600; text-decoration: none; display: block; box-sizing: border-box;">View Profile</a>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </section>
    </c:if>

    <!-- Success Stories -->
    <section class="success-stories" id="success-stories">
        <div class="container">
            <div class="section-header" style="text-align: center; margin-bottom: 8px;">
                <span class="landing-faq-badge" style="margin-bottom: 8px;">REAL LOVE STORIES</span>
                <h2 style="font-size: 32px; font-weight: 800; color: #2E2E2E; margin: 0 0 8px;">Success Stories</h2>
                <p style="color: #666; font-size: 15px; margin: 0;">Inspiring journeys of couples who found their forever partner on Snehbandhan</p>
            </div>

            <div class="stories-grid">
                <!-- Story Card 1 -->
                <div class="story-card-compact">
                    <div class="story-img-box">
                        <img src="resources/images/komal_vishwaraj.jpg" class="story-img-komal" alt="Komal & Vishwaraj" onerror="this.onerror=null; this.src='resources/UserImages/userImage.png';">
                        <span class="story-badge-married"><i class="fa fa-heart"></i> Happily Married</span>
                    </div>
                    <div class="story-content-box">
                        <div class="story-couple-header">
                            <h3 class="story-couple-title">Komal <i class="fa fa-heart story-heart-icon"></i> Vishwaraj</h3>
                            <span class="story-loc-pill"><i class="fa fa-map-marker"></i> Kolhapur</span>
                        </div>
                        <p class="story-preview-text">Some journeys begin with a search, but end with a beautiful forever. The story of Komal & Vishwaraj Shinde...</p>
                        <button type="button" class="btn-view-story" onclick="openStoryModal(0)">
                            View More <i class="fa fa-angle-right" style="font-weight: 700; font-size: 14px;"></i>
                        </button>
                    </div>
                </div>

                <!-- Story Card 2 -->
                <div class="story-card-compact">
                    <div class="story-img-box">
                        <img src="resources/images/vinoti_sangram.jpg" class="story-img-vinoti" alt="Vinoti & Sangram" onerror="this.onerror=null; this.src='resources/UserImages/userImage.png';">
                        <span class="story-badge-married"><i class="fa fa-heart"></i> Happily Married</span>
                    </div>
                    <div class="story-content-box">
                        <div class="story-couple-header">
                            <h3 class="story-couple-title">Vinoti <i class="fa fa-heart story-heart-icon"></i> Sangram</h3>
                            <span class="story-loc-pill"><i class="fa fa-map-marker"></i> Nashik</span>
                        </div>
                        <p class="story-preview-text">From a simple profile match to a match made in heaven. Vinoti & Sangram found their happily ever after...</p>
                        <button type="button" class="btn-view-story" onclick="openStoryModal(1)">
                            View More <i class="fa fa-angle-right" style="font-weight: 700; font-size: 14px;"></i>
                        </button>
                    </div>
                </div>

                <!-- Story Card 3 -->
                <div class="story-card-compact">
                    <div class="story-img-box">
                        <img src="resources/images/tushar_mansi.jpg" class="story-img-tushar" alt="Tushar & Mansi" onerror="this.onerror=null; this.src='resources/UserImages/userImage.png';">
                        <span class="story-badge-married"><i class="fa fa-heart"></i> Happily Married</span>
                    </div>
                    <div class="story-content-box">
                        <div class="story-couple-header">
                            <h3 class="story-couple-title">Tushar <i class="fa fa-heart story-heart-icon"></i> Mansi</h3>
                            <span class="story-loc-pill"><i class="fa fa-map-marker"></i> Pune</span>
                        </div>
                        <p class="story-preview-text">Two families connected through a business-oriented profile and discovered a wonderful relationship of a lifetime...</p>
                        <button type="button" class="btn-view-story" onclick="openStoryModal(2)">
                            View More <i class="fa fa-angle-right" style="font-weight: 700; font-size: 14px;"></i>
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </section>

    <!-- Membership Plans (Hidden for now) -->
    <section class="membership" style="display: none;">
        <div class="container">
            <div class="section-header" style="justify-content: flex-start;">
                <h2>Membership Plans</h2>
            </div>
            <div class="plans-grid">
                <!-- Free Plan -->
                <div class="plan-card">
                    <div class="plan-icon"><i class="fa fa-user-o"></i></div>
                    <div class="plan-title">Free</div>
                    <div style="height: 60px;"></div> <!-- Spacer for price alignment -->
                    <ul class="plan-features">
                        <li><i class="fa fa-check"></i> Basic Features</li>
                        <li><i class="fa fa-check"></i> Create Profile</li>
                        <li><i class="fa fa-check"></i> View Profiles</li>
                        <li><i class="fa fa-check"></i> Express Interest</li>
                    </ul>
                    <a href="#" class="btn-outline" onclick="document.getElementById('registrationModal').style.display='flex'; return false;">Register Now</a>
                </div>
                
                <!-- Silver Plan -->
                <div class="plan-card popular">
                    <div class="popular-tag">Popular</div>
                    <div class="plan-icon" style="color: #A0A0A0;"><i class="fa fa-certificate"></i></div>
                    <div class="plan-title">Silver</div>
                    <div class="plan-price">₹999 <span style="font-size: 16px; font-weight: normal; color: var(--body-color);">/ 1 Month</span></div>
                    <ul class="plan-features">
                        <li><i class="fa fa-check"></i> Unlimited Chat</li>
                        <li><i class="fa fa-check"></i> View Contact Details</li>
                        <li><i class="fa fa-check"></i> Priority Matches</li>
                        <li><i class="fa fa-check"></i> Advanced Filters</li>
                    </ul>
                    <a href="#" class="btn-primary">Choose Silver</a>
                </div>
                
                <!-- Gold Plan -->
                <div class="plan-card">
                    <div class="plan-icon" style="color: var(--gold);"><i class="fa fa-star"></i></div>
                    <div class="plan-title">Gold</div>
                    <div class="plan-price">₹2499 <span style="font-size: 16px; font-weight: normal; color: var(--body-color);">/ 1 Month</span></div>
                    <ul class="plan-features">
                        <li><i class="fa fa-check"></i> All Silver Features</li>
                        <li><i class="fa fa-check"></i> Dedicated Relationship Manager</li>
                        <li><i class="fa fa-check"></i> Personalised Matchmaking</li>
                        <li><i class="fa fa-check"></i> Advanced Search</li>
                        <li><i class="fa fa-check"></i> Profile Boost</li>
                    </ul>
                    <a href="#" class="btn-primary">Choose Gold</a>
                </div>
            </div>
        </div>
    </section>

    <!-- FAQs Section -->
    <section class="landing-faqs-section" id="faqs">
        <div class="landing-faq-header">
            <span class="landing-faq-badge">FAQ</span>
            <h2 class="landing-faq-title">Frequently Asked Questions</h2>
            <p class="landing-faq-subtitle">Everything you need to know about joining Snehbandhan Matrimony, finding matches, and profile safety.</p>
        </div>

        <div class="landing-faq-list">
            <!-- FAQ 1 -->
            <div class="landing-faq-item">
                <button type="button" class="landing-faq-btn" onclick="toggleLandingFaq(this)">
                    <div class="landing-faq-btn-left">
                        <span class="landing-faq-num">01</span>
                        <h3 class="landing-faq-question">Is Snehbandhan Matrimony really free to join?</h3>
                    </div>
                    <div class="landing-faq-toggle-icon">
                        <i class="fa fa-plus"></i>
                    </div>
                </button>
                <div class="landing-faq-collapse">
                    <div class="landing-faq-answer">
                        Yes. Creating your profile and joining Snehbandhan Matrimony is free. You can create your profile, explore suitable matches, and discover potential partners. Some advanced features may be available through premium plans.
                    </div>
                </div>
            </div>

            <!-- FAQ 2 -->
            <div class="landing-faq-item">
                <button type="button" class="landing-faq-btn" onclick="toggleLandingFaq(this)">
                    <div class="landing-faq-btn-left">
                        <span class="landing-faq-num">02</span>
                        <h3 class="landing-faq-question">How do you ensure the safety of profiles?</h3>
                    </div>
                    <div class="landing-faq-toggle-icon">
                        <i class="fa fa-plus"></i>
                    </div>
                </button>
                <div class="landing-faq-collapse">
                    <div class="landing-faq-answer">
                        We take profile safety seriously. Profiles are reviewed and monitored to help maintain a trusted community. We also provide reporting and blocking options so members can report suspicious or inappropriate activity.
                    </div>
                </div>
            </div>

            <!-- FAQ 3 -->
            <div class="landing-faq-item">
                <button type="button" class="landing-faq-btn" onclick="toggleLandingFaq(this)">
                    <div class="landing-faq-btn-left">
                        <span class="landing-faq-num">03</span>
                        <h3 class="landing-faq-question">How is Snehbandhan different from other matrimony sites?</h3>
                    </div>
                    <div class="landing-faq-toggle-icon">
                        <i class="fa fa-plus"></i>
                    </div>
                </button>
                <div class="landing-faq-collapse">
                    <div class="landing-faq-answer">
                        Snehbandhan focuses on creating meaningful connections with a simple, trusted, and user-friendly matrimony experience. Our goal is to help individuals and families find compatible matches based on their preferences, values, and relationship goals.
                    </div>
                </div>
            </div>

            <!-- FAQ 4 -->
            <div class="landing-faq-item">
                <button type="button" class="landing-faq-btn" onclick="toggleLandingFaq(this)">
                    <div class="landing-faq-btn-left">
                        <span class="landing-faq-num">04</span>
                        <h3 class="landing-faq-question">Can I contact members directly?</h3>
                    </div>
                    <div class="landing-faq-toggle-icon">
                        <i class="fa fa-plus"></i>
                    </div>
                </button>
                <div class="landing-faq-collapse">
                    <div class="landing-faq-answer">
                        Yes. Depending on your membership and the other member's privacy settings, you can connect with suitable profiles through the available communication features. You can express interest and start a conversation when communication is permitted.
                    </div>
                </div>
            </div>

            <!-- FAQ 5 -->
            <div class="landing-faq-item">
                <button type="button" class="landing-faq-btn" onclick="toggleLandingFaq(this)">
                    <div class="landing-faq-btn-left">
                        <span class="landing-faq-num">05</span>
                        <h3 class="landing-faq-question">How does the verification process work?</h3>
                    </div>
                    <div class="landing-faq-toggle-icon">
                        <i class="fa fa-plus"></i>
                    </div>
                </button>
                <div class="landing-faq-collapse">
                    <div class="landing-faq-answer">
                        Our verification process helps improve the authenticity and trustworthiness of profiles. Members may be asked to provide relevant information or verification documents. Verified profiles can provide additional confidence when exploring potential matches.
                    </div>
                </div>
            </div>

            <!-- FAQ 6 -->
            <div class="landing-faq-item">
                <button type="button" class="landing-faq-btn" onclick="toggleLandingFaq(this)">
                    <div class="landing-faq-btn-left">
                        <span class="landing-faq-num">06</span>
                        <h3 class="landing-faq-question">How can I create my matrimony profile?</h3>
                    </div>
                    <div class="landing-faq-toggle-icon">
                        <i class="fa fa-plus"></i>
                    </div>
                </button>
                <div class="landing-faq-collapse">
                    <div class="landing-faq-answer">
                        Creating your profile is simple. Sign up with your basic details, add your preferences, upload your photos, and complete your profile information. A complete and genuine profile helps you find more relevant matches.
                    </div>
                </div>
            </div>

            <!-- FAQ 7 -->
            <div class="landing-faq-item">
                <button type="button" class="landing-faq-btn" onclick="toggleLandingFaq(this)">
                    <div class="landing-faq-btn-left">
                        <span class="landing-faq-num">07</span>
                        <h3 class="landing-faq-question">What should I do if I find a suspicious or fake profile?</h3>
                    </div>
                    <div class="landing-faq-toggle-icon">
                        <i class="fa fa-plus"></i>
                    </div>
                </button>
                <div class="landing-faq-collapse">
                    <div class="landing-faq-answer">
                        If you notice anything suspicious, you can report the profile to our support team. We review reported profiles and take appropriate action to help keep Snehbandhan a safe and trusted matrimony platform.
                    </div>
                </div>
            </div>
        </div>
    </section>

    <!-- CTA Banner -->
    <div class="container">
        <div class="cta-banner">
            <div class="cta-content">
                <i class="fa fa-heart cta-icon"></i>
                <div>
                    <h2>Ready to find your perfect match?</h2>
                    <p>Join thousands of happy couples who found their life partner on Snehbandhan.</p>
                </div>
            </div>
            <a href="#" class="btn-primary" onclick="document.getElementById('registrationModal').style.display='flex'; return false;">Register Free Now</a>
        </div>
    </div>

    <!-- Footer -->
    <footer class="footer">
        <div class="footer-grid">
            <div class="footer-about">
                <img src="resources/images/logo.png" alt="Snehbandhan Logo" style="height: 60px">
                <p>Snehbandhan is a trusted Marathi<br>matrimony platform dedicated to<br>helping families find the perfect<br>life partner.</p>
                <div class="social-links">
                    <a href="#"><i class="fa fa-facebook"></i></a>
                    <a href="#"><i class="fa fa-instagram"></i></a>
                    <a href="#"><i class="fa fa-youtube-play"></i></a>
                    <a href="#"><i class="fa fa-twitter"></i></a>
                </div>
            </div>
            
            <div class="footer-col">
                <h4>Quick Links</h4>
                <ul class="footer-links">
                    <li><a href="Home">Home</a></li>
                    <li><a href="#">Find Matches</a></li>
                    <li><a href="#success-stories" onclick="var el = document.getElementById('success-stories'); if(el){ el.scrollIntoView({behavior: 'smooth'}); return false; }">Success Stories</a></li>
                    <li><a href="PackageHome">Membership Plans</a></li>
                    <li><a href="aboutus">About Us</a></li>
                    <li><a href="contactus">Contact Us</a></li>
                </ul>
            </div>
            
            <div class="footer-col">
                <h4>Help & Support</h4>
                <ul class="footer-links">
                    <li><a href="#">How it Works</a></li>
                    <li><a href="#">FAQs</a></li>
                    <li><a href="#">Privacy Policy</a></li>
                    <li><a href="#">Terms & Conditions</a></li>
                    <li><a href="#">Safety Tips</a></li>
                    <li><a href="#">Sitemap</a></li>
                </ul>
            </div>
            
            <div class="footer-col">
                <h4>For Members</h4>
                <ul class="footer-links">
                    <li><a href="Login">Login</a></li>
                    <li><a href="#" onclick="document.getElementById('registrationModal').style.display='flex'; return false;">Register Free</a></li>
                    <li><a href="#">My Account</a></li>
                    <li><a href="#">Premium Plans</a></li>
                    <li><a href="#">Verification</a></li>
                </ul>
            </div>
            
            <div class="footer-col">
                <div class="footer-contact">
                    <div class="contact-item">
                        <i class="fa fa-headphones contact-icon"></i>
                        <div>
                            <div style="font-weight: 600; color: #333; margin-bottom: 4px;">24x7 Support</div>
                            <div style="font-size: 12px;">We are always here<br>to help you.</div>
                        </div>
                    </div>
                    <div style="margin-top: 16px;">
                        <div style="margin-bottom: 8px;"><i class="fa fa-phone" style="color: var(--orange); margin-right: 8px;"></i> +91751 752 0378</div>
                        <div><i class="fa fa-envelope-o" style="color: var(--orange); margin-right: 8px;"></i> support@snehbandhan.com</div>
                    </div>
                </div>
            </div>
        </div>
        
        <div class="footer-bottom">
            <div>&copy; 2026 Snehbandhan Matrimony. All Rights Reserved.</div>
            <div>Made with <i class="fa fa-heart" style="color: var(--primary);"></i> for Marathi Families</div>
        </div>
    </footer>

        <!-- Login Modal -->
    <div id="loginModal" class="modal-overlay">
        <div class="split-modal-content">
            <span class="close-modal" onclick="document.getElementById('loginModal').style.display='none';">&times;</span>
            
            <div class="split-modal-left">
                <div class="modal-brand">
                    <img src="resources/images/logo.png" alt="Snehbandhan Logo">
                </div>
                
                <h3>Welcome Back!</h3>
                <h2>Login to Continue</h2>
                <p>Find your perfect Marathi match with trusted families.</p>
                
                <img src="resources/images/44.png" alt="Couple" class="login-mobile-img" onerror="this.onerror=null; this.src='resources/UserImages/userImage.png';">
            </div>
            
            <div class="split-modal-right">
                <div class="top-link">
                    New to Snehbandhan? <a href="#" onclick="document.getElementById('loginModal').style.display='none'; document.getElementById('registrationModal').style.display='flex'; return false;">Create Account</a>
                </div>
                
                <form action="<c:url value='j_spring_security_check'/>" method="POST" class="login-mobile-form">
                    <div class="input-with-icon">
                        <i class="fa fa-user-o"></i>
                        <input type="text" name="j_username" placeholder="Mobile Number / Email ID" required autofocus>
                    </div>
                    
                    <div class="input-with-icon">
                        <i class="fa fa-lock"></i>
                        <input type="password" name="j_password" placeholder="Password" required>
                        <i class="fa fa-eye right-icon"></i>
                    </div>
                    
                    <div class="forgot-pwd">
                        <a href="<c:url value='forgotpassword'/>">Forgot Password?</a>
                    </div>
                    
                    <button type="submit" class="btn-primary">Login</button>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                </form>
                
                <div class="divider">or continue with</div>
                
                <div class="social-btn-container">
                    <button type="button" class="social-btn">
                        <img src="https://cdn-icons-png.flaticon.com/512/2991/2991148.png" alt="Google" onerror="this.onerror=null; this.src='resources/UserImages/userImage.png';"> Continue with Google
                    </button>
                    
                    <button type="button" class="social-btn">
                        <img src="https://cdn-icons-png.flaticon.com/512/124/124010.png" alt="Facebook" onerror="this.onerror=null; this.src='resources/UserImages/userImage.png';"> Continue with Facebook
                    </button>
                    
                    <button type="button" class="social-btn" style="position: relative;">
                        <span style="color: #1a237e; font-weight: 800; font-size: 14px;">KiT</span> Continue with KiT
                        <span class="kit-badge" style="position: absolute; right: 12px;">New</span>
                    </button>
                </div>
                
                <div class="safety-text">
                    <i class="fa fa-shield"></i>
                    <div style="text-align: left; line-height: 1.2;">
                        <div style="font-weight: 600; color: #333;">Your information is safe with us.</div>
                        <div style="font-size: 10px;">We never share your details with anyone.</div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Registration Modal -->
    <div id="registrationModal" class="modal-overlay">
        <div class="signup-modal-content">
            <div class="signup-header">
                <i class="fa fa-arrow-left" onclick="document.getElementById('registrationModal').style.display='none';"></i>
                <div class="signup-logo">
                    <img src="resources/images/logo.png" alt="Snehbandhan">
                </div>
            </div>
            
            <div class="signup-title-section">
                <div class="signup-icon">
                    <i class="fa fa-user-circle-o"></i>
                </div>
                <div class="signup-title-text">
                    <h3>Create Your Account</h3>
                    <p>Fill in the details below to get started.</p>
                </div>
            </div>
            
            <div class="signup-divider">
                <i class="fa fa-heart-o"></i>
            </div>
            
            <form action="registration" method="GET" class="signup-form">
                <div class="form-group full-width">
                    <label>Create Profile For</label>
                    <div class="input-with-icon">
                        <i class="fa fa-user-o"></i>
                        <select name="ProfileCreatedFor" required style="width: 100%; padding: 12px 16px 12px 40px; border: 1px solid #ddd; border-radius: 8px; font-size: 14px; box-sizing: border-box; appearance: none; -webkit-appearance: none; background: url(data:image/svg+xml;utf8,<svg xmlns=\; http://www.w3.org/2000/svg\;" width=\"12\" height=\"12\" fill=\"%23333\" viewBox=\"0 0 16 16\"><path d=\"M8 11L3 6h10z\"/></svg>') no-repeat right 16px center; background-color: #fff;">
                            <option value="">Select</option>
                            <option value="Self">Self</option>
                            <option value="Son">Son</option>
                            <option value="Daughter">Daughter</option>
                            <option value="Brother">Brother</option>
                            <option value="Sister">Sister</option>
                            <option value="Relative">Relative</option>
                        </select>
                    </div>
                </div>
                
                <div class="form-row">
                    <div class="form-group">
                        <label>Name</label>
                        <div class="input-with-icon">
                            <i class="fa fa-user-o"></i>
                            <input type="text" name="fullname" placeholder="First Last" required>
                        </div>
                    </div>
                    <div class="form-group">
                        <label>Gender</label>
                        <div class="radio-group">
                            <label class="radio-label"><input type="radio" name="gender" value="Male" required> <span>Male</span></label>
                            <label class="radio-label"><input type="radio" name="gender" value="Female"> <span>Female</span></label>
                        </div>
                    </div>
                </div>

                <div class="form-row">
                    <div class="form-group">
                        <label>Date of Birth</label>
                        <div class="input-with-icon">
                            <i class="fa fa-calendar-o"></i>
                            <input type="text" name="dob" placeholder="DD/MM/YYYY" required onfocus="(this.type='date')" onblur="(this.type='text')">
                        </div>
                    </div>
                    <div class="form-group">
                        <label>Mobile Number</label>
                        <div class="input-with-icon">
                            <i class="fa fa-phone"></i>
                            <input type="tel" name="mobile" placeholder="Enter Mobile Number" pattern="[0-9]{10}" required>
                        </div>
                    </div>
                </div>
                
                <div class="form-group full-width">
                    <label>Email ID <span style="font-size: 12px; color: #888; font-weight: normal;">(Optional)</span></label>
                    <div class="input-with-icon">
                        <i class="fa fa-envelope-o"></i>
                        <input type="email" name="email" placeholder="Enter Email Address (Optional)">
                    </div>
                </div>
                
                <div class="form-row">
                    <div class="form-group">
                        <label>Username</label>
                        <div class="input-with-icon">
                            <i class="fa fa-user-o"></i>
                            <input type="text" name="userName" placeholder="Enter Username" required>
                        </div>
                    </div>
                    <div class="form-group">
                        <label>Password</label>
                        <div class="input-with-icon">
                            <i class="fa fa-lock"></i>
                            <input type="password" name="password" placeholder="Enter Password" required>
                            <i class="fa fa-eye toggle-password" style="position: absolute; right: 16px; cursor: pointer; z-index: 10;"></i>
                        </div>
                    </div>
                </div>
                
                <div class="terms-group">
                    <input type="checkbox" required id="terms">
                    <label for="terms">I agree to the <a href="#">Terms & Conditions</a> and <a href="#">Privacy Policy</a>.</label>
                </div>
                
                <button type="submit" class="btn-primary" style="width: 100%; margin-top: 10px; background-color: #c20042;">Create Account</button>
                
                <div class="divider">or continue with</div>
                
                <button type="button" class="social-btn">
                    <img src="https://cdn-icons-png.flaticon.com/512/2991/2991148.png" alt="Google" onerror="this.onerror=null; this.src='resources/UserImages/userImage.png';"> Continue with Google
                </button>
                <button type="button" class="social-btn">
                    <img src="https://cdn-icons-png.flaticon.com/512/124/124010.png" alt="Facebook" onerror="this.onerror=null; this.src='resources/UserImages/userImage.png';"> Continue with Facebook
                </button>
                <button type="button" class="social-btn" style="position: relative;">
                    <span style="color: #1a237e; font-weight: 800; font-size: 16px; margin-right: 8px;">KiT</span> Continue with KIT
                    <span class="kit-badge" style="position: absolute; right: 16px;">New</span>
                </button>
                
                <div style="text-align: center; margin-top: 16px; font-size: 14px;">
                    Already have an account? <a href="#" onclick="document.getElementById('registrationModal').style.display='none'; document.getElementById('loginModal').style.display='flex'; return false;" style="color: #c20042; font-weight: 600;">Login</a>
                </div>
            </form>
        </div>
    </div>

    <!-- Premium Success Story Modal -->
    <div id="storyModal" class="story-modal-overlay">
        <div class="story-modal-dialog">
            <!-- Modal Header with couple info -->
            <div style="background: linear-gradient(135deg, #FFF0F4 0%, #FFF9FA 100%); padding: 20px 24px; border-bottom: 1px solid #FCE4EC; display: flex; align-items: center; justify-content: space-between; position: relative;">
                <div style="display: flex; align-items: center; gap: 14px;">
                    <img id="modalStoryImg" src="" alt="Couple" style="width: 58px; height: 58px; border-radius: 50%; object-fit: cover; border: 3px solid #fff; box-shadow: 0 4px 10px rgba(216,27,116,0.2); flex-shrink: 0;" onerror="this.onerror=null; this.src='resources/UserImages/userImage.png';">
                    <div>
                        <span style="font-size: 11px; letter-spacing: 1px; text-transform: uppercase; font-weight: 700; color: var(--primary); background: #FFE4EC; padding: 2px 10px; border-radius: 12px; display: inline-block; margin-bottom: 4px;">SUCCESS STORY</span>
                        <h3 id="modalStoryNames" style="margin: 0; font-size: 19px; font-weight: 700; color: #2E2E2E; display: flex; align-items: center; gap: 6px;"></h3>
                        <span id="modalStoryLocation" style="font-size: 12px; color: #718096; display: inline-flex; align-items: center; gap: 4px; margin-top: 2px;"></span>
                    </div>
                </div>
                <button type="button" onclick="closeStoryModal()" style="background: #fff; border: 1px solid #F0D0D8; width: 34px; height: 34px; border-radius: 50%; font-size: 20px; line-height: 1; cursor: pointer; color: #666; display: flex; align-items: center; justify-content: center; transition: all 0.2s ease;">&times;</button>
            </div>

            <!-- Quote banner -->
            <div style="background: #FFF9FA; padding: 12px 24px; border-bottom: 1px dashed #FCE4EC; font-size: 13.5px; font-weight: 600; color: var(--secondary); display: flex; align-items: center; gap: 8px;">
                <i class="fa fa-quote-left" style="color: var(--primary); font-size: 13px;"></i>
                <span id="modalStorySubtitle"></span>
            </div>

            <!-- Story Body -->
            <div id="modalStoryBody" style="padding: 22px 24px; overflow-y: auto; flex-grow: 1;">
            </div>

            <!-- Modal Footer -->
            <div style="padding: 14px 24px; background: #FAF9F9; border-top: 1px solid #EFEFEF; display: flex; justify-content: space-between; align-items: center;">
                <span style="font-size: 13px; color: #718096;"><i class="fa fa-heart" style="color: var(--primary);"></i> Find your life partner today</span>
                <a href="#" class="btn-primary" style="height: 36px; padding: 0 20px; border-radius: 18px; font-size: 13px; font-weight: 600;" onclick="closeStoryModal(); document.getElementById('registrationModal').style.display='flex'; return false;">Register Free</a>
            </div>
        </div>
    </div>

    <script>
        // Close modal when clicking outside of it
        window.onclick = function(event) {
            var loginModal = document.getElementById('loginModal');
            var regModal = document.getElementById('registrationModal');
            var storyModal = document.getElementById('storyModal');
            if (event.target == loginModal) {
                loginModal.style.display = "none";
            }
            if (event.target == regModal) {
                regModal.style.display = "none";
            }
            if (event.target == storyModal) {
                storyModal.style.display = "none";
            }
        }

        // Success Stories Data
        var storiesData = [
            {
                names: "Komal & Vishwaraj",
                location: "Kolhapur",
                image: "resources/images/komal_vishwaraj.jpg",
                avatarPosition: "45% 20%",
                subtitle: "Some journeys begin with a search, but end with a beautiful forever.",
                paragraphs: [
                    "Some journeys begin with a search, but end with a beautiful forever. The story of Komal Mhamulkar and Vishwaraj Shinde is one such heartwarming success story from Kolhapur.",
                    "Komal is a B.Com graduate from a humble middle-class family. Vishwaraj holds a B.E. in Civil Engineering and is working with the Sports Authority of India (SAI) as an Assistant Coach in a Central Government job.",
                    "Vishwaraj and his family were looking for a beautiful, well-cultured life partner. During their search, they visited Snehbandhan Matrimony Kolhapur and came across Komal's profile.",
                    "After getting to know each other and with the support and blessings of their families, they realized that they were the perfect match. Their journey through Snehbandhan Matrimony eventually led to a beautiful marriage and the beginning of a new chapter in their lives.",
                    "Today, Komal and Vishwaraj are happily married, proving that the right platform can bring two wonderful people together and turn a simple profile visit into a lifelong bond of love."
                ]
            },
            {
                names: "Vinoti & Sangram",
                location: "Nashik",
                image: "resources/images/vinoti_sangram.jpg",
                avatarPosition: "55% 42%",
                subtitle: "From a simple profile match to a match made in heaven.",
                paragraphs: [
                    "From a simple profile match to a match made in heaven, Vinoti & Sangram found their happily ever after through trusted family matchmaking on Snehbandhan.",
                    "Both families were seeking compatible life partners who appreciated cultural values as well as modern goals. When they discovered each other's verified profiles, they initiated communication through Snehbandhan's platform.",
                    "Their families met in Nashik, and the connection was immediate and natural. Today, Vinoti and Sangram celebrate their harmonious marriage and are a shining example of happiness found through our trusted community."
                ]
            },
            {
                names: "Tushar & Mansi",
                location: "Pune",
                image: "resources/images/tushar_mansi.jpg",
                avatarPosition: "50% 25%",
                subtitle: "A wonderful relationship of a lifetime built on trust and shared vision.",
                paragraphs: [
                    "At Snehbandhan Matrimony, we are proud to share another beautiful success story where two families connected through a business-oriented profile and discovered a wonderful relationship.",
                    "Tushar and Mansi's families wanted compatible partners with shared cultural values and professional understanding. When they found each other's profiles on Snehbandhan, the compatibility was evident right from their first conversation.",
                    "Today, Tushar Yamagekar & Mansi Patole's journey stands as a wonderful example that the right profile, the right connection, and the right platform can lead to a lifetime of happiness."
                ]
            }
        ];

        function openStoryModal(index) {
            var story = storiesData[index];
            if (!story) return;
            var modalImg = document.getElementById('modalStoryImg');
            modalImg.src = story.image;
            modalImg.style.objectPosition = story.avatarPosition || 'center center';
            document.getElementById('modalStoryNames').innerHTML = story.names + ' <i class="fa fa-heart" style="color:var(--primary); font-size:14px;"></i>';
            document.getElementById('modalStoryLocation').innerHTML = '<i class="fa fa-map-marker" style="color:var(--primary);"></i> ' + story.location;
            document.getElementById('modalStorySubtitle').textContent = story.subtitle;
            
            var bodyHtml = '';
            story.paragraphs.forEach(function(p) {
                bodyHtml += '<p style="margin-bottom: 12px; font-size: 14.5px; line-height: 1.7; color: #4A5568;">' + p + '</p>';
            });
            document.getElementById('modalStoryBody').innerHTML = bodyHtml;
            document.getElementById('storyModal').style.display = 'flex';
        }

        function closeStoryModal() {
            document.getElementById('storyModal').style.display = 'none';
        }

        // Toggle Landing FAQ item open/close
        function toggleLandingFaq(btn) {
            var item = btn.closest('.landing-faq-item');
            var collapse = item.querySelector('.landing-faq-collapse');
            var isOpen = item.classList.contains('active');

            // Close other items for a clean accordion UX
            var allItems = document.querySelectorAll('.landing-faq-item');
            allItems.forEach(function (other) {
                if (other !== item && other.classList.contains('active')) {
                    other.classList.remove('active');
                    var otherCollapse = other.querySelector('.landing-faq-collapse');
                    if (otherCollapse) {
                        otherCollapse.style.maxHeight = null;
                    }
                }
            });

            if (isOpen) {
                item.classList.remove('active');
                collapse.style.maxHeight = null;
            } else {
                item.classList.add('active');
                collapse.style.maxHeight = collapse.scrollHeight + "px";
            }
        }
    </script>

<!-- Image Slider Modal Popup -->
<div id="imgSlider" class="modal fade" data-backdrop="static" data-keyboard="false" role="dialog" style="z-index: 3000;">
    <div class="modal-dialog modal-lg custom-slider-modal">
        <div class="modal-content" style="border-radius: 20px; overflow: hidden;">
            <div class="modal-header" style="border: none; padding: 15px 20px 0;">
                <button type="button" class="close" data-dismiss="modal" style="font-size: 24px;">&times;</button>
            </div>
            <div class="modal-body" style="padding: 20px;">
                <div id="customSlider"></div>
            </div>
            <div class="modal-footer" style="border: none; padding: 10px 20px 20px;">
                <button type="button" class="btn btn-secondary" data-dismiss="modal" style="border-radius: 20px;">Close</button>
            </div>
        </div>
    </div>
</div>

<script>
function imageSlider(userId) {
    $.ajax({
        url: "imageSlider",
        type: "GET",
        data: "userId=" + userId,
        processData: false,
        contentType: false,
        success: function(data) {
            if (data.trim() === "UPGRADE_REQUIRED") {
                if ($('#loginModal').length) $('#loginModal').css('display', 'flex');
            } else {
                $('#imgSlider').modal('show');
                $('#customSlider').html(data);
            }
        },
        error: function(xhr, ajaxOptions, thrownError) {
            console.log("Error loading image gallery");
        }
    });
}
</script>

</body>
</html>