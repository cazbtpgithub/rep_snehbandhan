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
            <a href="#">Success Stories</a>
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
    <section class="section how-it-works">
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
    <section class="section featured-profiles" style="padding: 60px 0; background: #fff;">
        <div class="container">
            <div class="section-header" style="text-align: center; margin-bottom: 40px;">
                <span class="badge" style="background: rgba(194, 0, 66, 0.1); color: var(--primary); padding: 6px 16px; border-radius: 20px; font-weight: 600; font-size: 13px;">Handpicked Matches</span>
                <h2 style="font-size: 32px; color: #111; margin-top: 10px; font-weight: 700;">Featured Profiles</h2>
                <p style="color: #666; font-size: 15px; margin-top: 8px;">Explore our most shortlisted verified profiles</p>
            </div>
            
            <div class="profiles-grid" style="display: grid; grid-template-columns: repeat(auto-fit, minmax(250px, 1fr)); gap: 24px;">
                <c:forEach var="profile" items="${featuredProfiles}">
                    <div class="profile-card" style="background: #fff; border-radius: 16px; border: 1px solid #eee; overflow: hidden; box-shadow: 0 4px 20px rgba(0,0,0,0.06); transition: transform 0.3s ease, box-shadow 0.3s ease; display: flex; flex-direction: column;">
                        <div class="profile-img-wrap" style="position: relative; height: 260px; overflow: hidden; background: #f8f9fa; cursor: pointer;" onclick="imageSlider('${profile.userId}');" style="position: relative; height: 260px; overflow: hidden; background: #f8f9fa;">
                            <c:choose>
                                <c:when test="${not empty profile.imageObject and not empty profile.imageObject[0]}">
                                    <img src="resources/UserImages/${profile.imageObject[0]}" alt="${profile.name}" style="width: 100%; height: 100%; object-fit: cover; object-position: top center;" onerror="this.onerror=null; this.src='resources/UserImages/userImage.png';">
                                </c:when>
                                <c:otherwise>
                                    <img src="resources/UserImages/userImage.png" onerror="this.onerror=null; this.src='resources/UserImages/userImage.png';">
                                </c:otherwise>
                            </c:choose>
                            <span class="badge-featured" style="position: absolute; top: 12px; right: 12px; background: rgba(194, 0, 66, 0.9); color: #fff; padding: 4px 12px; border-radius: 12px; font-size: 11px; font-weight: 600;"><i class="fa fa-star" style="color: #ffc107; margin-right: 4px;"></i> Top Shortlisted</span>
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
    <section class="section success-stories">
        <div class="container">
            <div class="section-header">
                <h2>Success Stories</h2>
                <a href="#" class="view-all">View All Stories</a>
            </div>
            <div class="stories-grid">
                <div class="story-card" style="display: flex; flex-direction: column; height: 100%;">
                    <img src="resources/images/komal_vishwaraj.jpg" alt="Komal & Vishwaraj" class="story-img" style="object-fit: cover" onerror="this.onerror=null; this.src='resources/UserImages/userImage.png';">
                    <div class="story-quote" style="font-size: 13px; text-align: left; flex-grow: 1; overflow-y: auto; max-height: 250px; padding: 10px; line-height: 1.4;">
                        <p style="margin-bottom: 8px;">Some journeys begin with a search, but end with a beautiful forever. The story of Komal Mhamulkar and Vishwaraj Shinde is one such heartwarming success story from kolhapur.</p>
                        <p style="margin-bottom: 8px;">Komal is a B.Com graduate from a humble middle-class family. Vishwaraj holds a B.E. in Civil Engineering and is working with the Sports Authority of India (SAI) as an Assistant Coach in a Central Government job.</p>
                        <p style="margin-bottom: 8px;">Vishwaraj and his family were looking for a beautiful, well-cultured life partner. During their search, they visited Snehbandhan Matrimony kolhapur and came across Komal's profile.</p>
                        <p style="margin-bottom: 8px;">After getting to know each other and with the support and blessings of their families, they realized that they were the perfect match. Their journey through Snehbandhan Matrimony eventually led to a beautiful marriage and the beginning of a new chapter in their lives.</p>
                        <p style="margin-bottom: 0;">Today, Komal and Vishwaraj are happily married, proving that the right platform can bring two wonderful people together and turn a simple profile visit into a lifelong bond of love.</p>
                    </div>
                    <p class="story-author" style="margin-top: 15px;">- Komal <i class="fa fa-heart" style="color:var(--primary); font-size:12px;"></i> Vishwaraj<br><span style="color:#999;">Kolhapur</span></p>
                </div>
                <div class="story-card" style="display: flex; flex-direction: column; height: 100%;">
                    <img src="resources/images/vinoti_sangram.jpg" alt="Vinoti & Sangram" class="story-img" style="object-fit: cover" onerror="this.onerror=null; this.src='resources/UserImages/userImage.png';">
                    <div class="story-quote" style="font-size: 14px; text-align: center; flex-grow: 1; padding: 15px 10px; line-height: 1.5; font-style: italic;">
                        <p style="margin-bottom: 0;">"From a simple profile match to a match made in heaven.<br>Vinoti & Sangram found their happily ever after!"</p>
                    </div>
                    <p class="story-author" style="margin-top: auto;">- Vinoti <i class="fa fa-heart" style="color:var(--primary); font-size:12px;"></i> Sangram<br><span style="color:#999;">Nashik</span></p>
                </div>
                <div class="story-card" style="display: flex; flex-direction: column; height: 100%;">
                    <img src="resources/images/tushar_mansi.jpg" alt="Tushar & Mansi" class="story-img" style="object-fit: cover" onerror="this.onerror=null; this.src='resources/UserImages/userImage.png';">
                    <div class="story-quote" style="font-size: 13px; text-align: left; flex-grow: 1; padding: 15px 10px; line-height: 1.4;">
                        <p style="margin-bottom: 8px;">At Snehbandhan Matrimony, we are proud to share another beautiful success story where two families connected through a business-oriented profile and discovered a wonderful relationship.</p>
                        <p style="margin-bottom: 0;">Today, Tushar Yamagekar & Mansi patole's journey stands as a wonderful example that the right profile, the right connection, and the right platform can lead to a lifetime of happiness.</p>
                    </div>
                    <p class="story-author" style="margin-top: auto;">- Tushar <i class="fa fa-heart" style="color:var(--primary); font-size:12px;"></i> Mansi</p>
                </div>
            </div>
        </div>
    </section>

    <!-- Membership Plans -->
    <section class="section membership">
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
    <!-- FAQs -->
    <section class="section faqs" style="background-color: var(--bg-color);">
        <div class="container" style="max-width: 800px; padding-bottom: 40px;">
            <h2 style="text-align: center; margin-bottom: 40px;">Frequently Asked Questions</h2>
            
            <div class="faq-item" style="background: #fff;">
                <div class="faq-question">
                    <i class="fa fa-plus-circle fa-lg faq-icon"></i> Is Snehbandhan Matrimony really free to join?
                </div>
                <i class="fa fa-angle-down" style="color: #999;"></i>
            </div>
            
            <div class="faq-item" style="background: #fff;">
                <div class="faq-question">
                    <i class="fa fa-plus-circle fa-lg faq-icon"></i> How do you ensure the safety of profiles?
                </div>
                <i class="fa fa-angle-down" style="color: #999;"></i>
            </div>
            
            <div class="faq-item" style="background: #fff;">
                <div class="faq-question">
                    <i class="fa fa-plus-circle fa-lg faq-icon"></i> How is Snehbandhan different from other matrimony sites?
                </div>
                <i class="fa fa-angle-down" style="color: #999;"></i>
            </div>
            
            <div class="faq-item" style="background: #fff;">
                <div class="faq-question">
                    <i class="fa fa-plus-circle fa-lg faq-icon"></i> Can I contact members directly?
                </div>
                <i class="fa fa-angle-down" style="color: #999;"></i>
            </div>
            
            <div class="faq-item" style="background: #fff;">
                <div class="faq-question">
                    <i class="fa fa-plus-circle fa-lg faq-icon"></i> How does the verification process work?
                </div>
                <i class="fa fa-angle-down" style="color: #999;"></i>
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
                    <li><a href="#">Success Stories</a></li>
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

    <script>
        // Close modal when clicking outside of it
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