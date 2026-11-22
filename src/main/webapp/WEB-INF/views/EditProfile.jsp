<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>SnehBandhan - Edit Profile</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="resources/Template/css/bootstrap.min.css">
    <link rel="stylesheet" href="resources/Template/css/font-awesome.min.css">
    <link rel="stylesheet" href="resources/Registration/edit_profile_v3.css">
    
    <script src="resources/Template/js/jquery-min.js"></script>
    <script src="resources/Template/js/bootstrap.min.js"></script>
    <script src="resources/Registration/editProfile.js"></script>
    <script src="resources/Jcrop/js/jquery.Jcrop.min.js"></script>
    <link rel="stylesheet" href="resources/Jcrop/css/jquery.Jcrop.css" type="text/css" />

    <script>
        var educationId = new Array();
        var occupationId = new Array();
        var userModelMobileNumberArray = new Array();
        var flag = false;
    </script>

    <c:forEach var="userModel" items="${userModelsList}">
        <script>userModelMobileNumberArray.push("${userModel.mobileNo}");</script>
    </c:forEach>
    <c:forEach var="userModelDetails" items="${userModelDetailsList}">
        <script>userModelMobileNumberArray.push("${userModelDetails.mobileNum}");</script>
    </c:forEach>

    <script>
        function showStep(stepNum) {
            $('.ep-v3-step-item').removeClass('active completed');
            $('.ep-v3-step-divider').removeClass('active');
            $('.ep-v3-step-pane').hide();

            if (stepNum === 1) {
                $('#step1-nav').addClass('active');
                $('#step1-pane').show();
            } else if (stepNum === 2) {
                $('#step1-nav').addClass('completed');
                $('#step-div-1').addClass('active');
                $('#step2-nav').addClass('active');
                $('#step2-pane').show();
            } else if (stepNum === 3) {
                $('#step1-nav').addClass('completed');
                $('#step2-nav').addClass('completed');
                $('#step-div-1, #step-div-2').addClass('active');
                $('#step3-nav').addClass('active');
                $('#step3-pane').show();
            }
        }
        
        function countChar(val) {
            var len = val.value.length;
            if (len >= 400) {
                val.value = val.value.substring(0, 400);
                $('#charNum').text('0 characters left');
            } else {
                $('#charNum').text('Remaining ' + (400 - len) + ' characters');
            }
        }
    </script>
</head>
<body class="ep-v3-body">

<!-- TOP HEADER -->
<header class="ep-v3-header">
    <a href="<c:url value='userHome'></c:url>">
        <img class="ep-v3-logo" src="resources/images/logo.png" alt="SnehBandhan Logo">
    </a>
    <div class="ep-v3-nav-right">
        <div class="ep-v3-bell-btn">
            <i class="fa fa-bell"></i>
            <span class="ep-v3-bell-badge">${notificationCount != null ? notificationCount : 0}</span>
        </div>
        <div class="ep-v3-user-chip dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            <img class="ep-v3-user-avatar" src="resources/UserImages/${UserProfile}" alt="User Avatar" onerror="this.onerror=null; this.src='resources/UserImages/userImage.png';">
            <span class="ep-v3-user-name">${ActUserName}</span>
            <i class="fa fa-angle-down" style="color:#6B7280; font-size:12px;"></i>
        </div>
        <div class="dropdown-menu dropdown-menu-right">
            <a class="dropdown-item" href="<c:url value='/userHome'/>"><i class="fa fa-home"></i> Home</a>
            <a class="dropdown-item" href="KitUpgradeAndPayment"><i class="fa fa-star"></i> Upgrade Plan</a>
            <div class="dropdown-divider"></div>
            <a class="dropdown-item" href="<c:url value='/j_spring_security_logout'/>"><i class="fa fa-sign-out"></i> Logout</a>
        </div>
    </div>
</header>

<!-- MAIN TWO-COLUMN LAYOUT -->
<div class="ep-v3-wrapper">

    <!-- LEFT SIDEBAR -->
    <aside class="ep-v3-sidebar">
        <!-- USER PROFILE CARD -->
        <div class="ep-v3-profile-card">
            <img class="ep-v3-profile-img" src="resources/UserImages/${UserProfile}" alt="Profile Avatar" onerror="this.onerror=null; this.src='resources/UserImages/userImage.png';">
            <h4 class="ep-v3-profile-name">${ActUserName}</h4>
            <p class="ep-v3-profile-id">Profile ID: SB${modelDetails.userModel.userId}</p>
            
            <div class="ep-v3-progress-wrapper">
                <div class="ep-v3-progress-header">
                    <span>Profile Completeness</span>
                    <span class="ep-v3-progress-val">${profileCompleteness != null ? profileCompleteness : 65}%</span>
                </div>
                <div class="ep-v3-progress-bar">
                    <div class="ep-v3-progress-fill" style="width: ${profileCompleteness != null ? profileCompleteness : 65}%;"></div>
                </div>
            </div>
        </div>

        <!-- SIDEBAR NAV MENU -->
        <div class="ep-v3-nav-card">
            <div class="ep-v3-menu-group">
                <a href="<c:url value='userHome'></c:url>" class="ep-v3-menu-item">
                    <span><i class="fa fa-home"></i> Dashboard</span>
                </a>
            </div>

            <div class="ep-v3-menu-group">
                <div class="ep-v3-menu-title">MANAGE PROFILE</div>
                <a onclick="showStep(2);" class="ep-v3-menu-item active">
                    <span><i class="fa fa-user"></i> Edit Profile</span>
                </a>
                <a href="#" class="ep-v3-menu-item">
                    <span><i class="fa fa-eye"></i> Profile Preview</span>
                </a>
                <a onclick="showStep(1);" class="ep-v3-menu-item">
                    <span><i class="fa fa-picture-o"></i> Photos</span>
                </a>
                <a onclick="showStep(3);" class="ep-v3-menu-item">
                    <span><i class="fa fa-heart"></i> Partner Preferences</span>
                </a>
            </div>

            <div class="ep-v3-menu-group">
                <div class="ep-v3-menu-title">ACTIVITY</div>
                <a href="<c:url value='userHome'></c:url>" class="ep-v3-menu-item">
                    <span><i class="fa fa-envelope-o"></i> Messages</span>
                    <span class="ep-v3-badge">${inboxCount != null ? inboxCount : 0}</span>
                </a>
                <a href="<c:url value='userHome'></c:url>" class="ep-v3-menu-item">
                    <span><i class="fa fa-heart-o"></i> Interested In You</span>
                    <span class="ep-v3-badge">${interestReceivedCount != null ? interestReceivedCount : 0}</span>
                </a>
                <a href="<c:url value='userHome'></c:url>" class="ep-v3-menu-item">
                    <span><i class="fa fa-eye"></i> Who Viewed You</span>
                    <span class="ep-v3-badge">${seenyet != null ? seenyet : 0}</span>
                </a>
                <a href="<c:url value='userHome'></c:url>" class="ep-v3-menu-item">
                    <span><i class="fa fa-star-o"></i> Shortlisted</span>
                    <span class="ep-v3-badge">${shortlistedCount != null ? shortlistedCount : 0}</span>
                </a>
            </div>

            <div class="ep-v3-menu-group">
                <div class="ep-v3-menu-title">ACCOUNT</div>
                <a href="KitUpgradeAndPayment" class="ep-v3-menu-item">
                    <span><i class="fa fa-crown"></i> Membership Plan</span>
                </a>
                <a href="#" class="ep-v3-menu-item">
                    <span><i class="fa fa-cog"></i> Account Settings</span>
                </a>
                <a href="PrivacyPolicy" class="ep-v3-menu-item">
                    <span><i class="fa fa-shield"></i> Privacy Settings</span>
                </a>
            </div>
        </div>

        <!-- NEED HELP CARD -->
        <div class="ep-v3-help-card">
            <div class="ep-v3-help-icon">
                <i class="fa fa-headphones"></i>
            </div>
            <div class="ep-v3-help-text">
                <strong>Need Help?</strong>
                <span>We are here for you</span>
            </div>
        </div>
    </aside>

    <!-- RIGHT MAIN CONTENT -->
    <main class="ep-v3-main">

        <!-- STEPPER BAR -->
        <div class="ep-v3-stepper-card">
            <div class="ep-v3-step-item active" id="step1-nav" onclick="showStep(1);">
                <div class="ep-v3-step-num">1</div>
                <div class="ep-v3-step-info">
                    <span class="ep-v3-step-title">Profile Images</span>
                    <span class="ep-v3-step-sub">Add your photos</span>
                </div>
            </div>
            <div class="ep-v3-step-divider" id="step-div-1"></div>

            <div class="ep-v3-step-item" id="step2-nav" onclick="showStep(2);">
                <div class="ep-v3-step-num">2</div>
                <div class="ep-v3-step-info">
                    <span class="ep-v3-step-title">Personal Information</span>
                    <span class="ep-v3-step-sub">Tell us about yourself</span>
                </div>
            </div>
            <div class="ep-v3-step-divider" id="step-div-2"></div>

            <div class="ep-v3-step-item" id="step3-nav" onclick="showStep(3);">
                <div class="ep-v3-step-num">3</div>
                <div class="ep-v3-step-info">
                    <span class="ep-v3-step-title">Partner Preference</span>
                    <span class="ep-v3-step-sub">Your match preference</span>
                </div>
            </div>
        </div>

        <!-- SUCCESS ALERT MSG -->
        <div id="successSelect" class="alert alert-success" style="display:none; margin-bottom:16px;">
            Profile updated successfully!
        </div>
        <div id="partnersuccessSelect" class="alert alert-success" style="display:none; margin-bottom:16px;">
            Partner Preferences updated successfully!
        </div>

        <!-- STEP 1: PROFILE IMAGES PANE -->
        <div class="ep-v3-step-pane" id="step1-pane" style="display: none;">
            <div class="ep-v3-main-card" id="tabthree">
                <h2 class="ep-v3-card-title">Profile Images</h2>
                <p class="ep-v3-card-sub">Add clear and recent photos for better matches</p>

                <div class="ep-v3-photo-container">
                    <!-- LEFT MAIN PHOTO BOX -->
                    <div>
                        <div class="ep-v3-main-photo-box">
                            <span class="ep-v3-main-badge">Main Photo</span>
                            <div id="profileimg">
                                <c:forEach var="img" items="${profileImages}">
                                    <img class="mySlides ep-v3-main-photo-img" src="resources/UserImages/${img}" id="img${img}" alt="Profile Photo" onerror="this.onerror=null; this.src='resources/UserImages/userImage.png';">
                                </c:forEach>
                            </div>
                            <button type="button" class="ep-v3-arrow-btn ep-v3-arrow-left" onclick="plusDivs(-1);">&#10094;</button>
                            <button type="button" class="ep-v3-arrow-btn ep-v3-arrow-right" onclick="plusDivs(1);">&#10095;</button>
                        </div>
                        <input type="hidden" value="" name="cropidName" id="cropidName">
                        <button type="button" onclick="CropImage();" class="ep-v3-crop-btn">
                            <i class="fa fa-crop"></i> Crop & Adjust
                        </button>
                    </div>

                    <!-- RIGHT UPLOADED PHOTOS GRID -->
                    <div>
                        <div style="font-weight:700; font-size:13px; margin-bottom:12px; color:var(--text-dark);">
                            Uploaded Photos <span style="font-weight:400; color:var(--text-muted);">(Max 6 photos)</span>
                        </div>

                        <div class="ep-v3-photo-grid" id="divImage">
                            <c:forEach var="img" items="${profileImages}" varStatus="status">
                                <div class="ep-v3-thumb-box" id="${img}">
                                    <img class="ep-v3-thumb-img" src="resources/UserImages/${img}" alt="Thumbnail" onerror="this.onerror=null; this.src='resources/UserImages/userImage.png';">
                                    <c:if test="${status.first}">
                                        <span class="ep-v3-thumb-check"><i class="fa fa-check"></i></span>
                                    </c:if>
                                    <button type="button" class="ep-v3-thumb-remove" onclick="removeImage('${img}',this);"><i class="fa fa-times"></i></button>
                                </div>
                            </c:forEach>

                            <!-- UPLOAD DROPZONE BOX -->
                            <label class="ep-v3-upload-dropzone">
                                <i class="fa fa-cloud-upload"></i>
                                <span class="ep-v3-upload-title">Add More Photos</span>
                                <span class="ep-v3-upload-sub">JPG, PNG (Max 5MB)</span>
                                <input type="file" style="display:none;" accept=".gif,.jpg,.jpeg,.png" name="loader11" id="loader11" multiple="multiple" onchange="model();">
                            </label>
                        </div>

                        <div style="margin-top:12px;">
                            <input type="button" onclick="saveImages();" class="ep-v3-btn-primary" value="Upload & Save Photos">
                        </div>

                        <!-- TIPS BANNER -->
                        <div class="ep-v3-tips-box">
                            <i class="fa fa-lightbulb-o ep-v3-tips-icon"></i>
                            <div class="ep-v3-tips-text">
                                <strong>Tips for a great profile</strong>
                                <span>Add clear, well-lit photos. A mix of close-up and full-length photos works best.</span>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- ACTION BAR -->
                <div class="ep-v3-action-bar">
                    <button type="button" class="ep-v3-btn-outline" onclick="showStep(1);">Cancel</button>
                    <button type="button" class="ep-v3-btn-primary" onclick="showStep(2);">Save & Next <i class="fa fa-angle-right"></i></button>
                </div>
            </div>
        </div>

        <!-- STEP 2: PERSONAL INFORMATION PANE -->
        <div class="ep-v3-step-pane" id="step2-pane">
            <div class="ep-v3-main-card">
                <h2 class="ep-v3-card-title">Personal Information</h2>
                <p class="ep-v3-card-sub">Please provide accurate information to help us find the right match for you.</p>

                <form class="form-horizontal" id="companyForm" method="post">
                    <input type="hidden" name="hiddenUsermodelId" id="hiddenUsermodelId" value="${modelDetails.userDetailsId}" />
                    <input type="hidden" name="hiddenUserpartnerId" id="hiddenUserpartnerId" value="${userPartnerPreferances.userDetailsId}" />
                    <input type="hidden" name="hiddenuserId" id="hiddenuserId" value="${modelDetails.userModel.userId}" />
                    <input type="hidden" id="editUserRegistrationDateHidden" value="${modelDetails.registrationDate}">

                    <!-- SECTION 1: BASIC INFORMATION -->
                    <div class="ep-v3-section">
                        <div class="ep-v3-sec-header">
                            <i class="fa fa-user-o ep-v3-sec-icon"></i> Basic Information
                        </div>
                        <div class="ep-v3-grid">
                            <div class="ep-v3-field ep-v3-col-1">
                                <label class="ep-v3-label">Created For <span class="ep-v3-req">*</span></label>
                                <select id="ProfileCreatedFor" name="ProfileCreatedFor" class="ep-v3-select">
                                    <option value="Self" ${modelDetails.profileCreatedFor == 'Self' ? 'selected' : ''}>Self</option>
                                    <option value="Son" ${modelDetails.profileCreatedFor == 'Son' ? 'selected' : ''}>Son</option>
                                    <option value="Daughter" ${modelDetails.profileCreatedFor == 'Daughter' ? 'selected' : ''}>Daughter</option>
                                    <option value="Son / Daughter" ${modelDetails.profileCreatedFor == 'Son / Daughter' ? 'selected' : ''}>Son / Daughter</option>
                                    <option value="Brother" ${modelDetails.profileCreatedFor == 'Brother' ? 'selected' : ''}>Brother</option>
                                    <option value="Sister" ${modelDetails.profileCreatedFor == 'Sister' ? 'selected' : ''}>Sister</option>
                                    <option value="Brother / Sister" ${modelDetails.profileCreatedFor == 'Brother / Sister' || modelDetails.profileCreatedFor == 'Sibling' ? 'selected' : ''}>Brother / Sister (Sibling)</option>
                                    <option value="Relative" ${modelDetails.profileCreatedFor == 'Relative' ? 'selected' : ''}>Relative</option>
                                    <option value="Friend" ${modelDetails.profileCreatedFor == 'Friend' ? 'selected' : ''}>Friend</option>
                                    <option value="Relative / Friend" ${modelDetails.profileCreatedFor == 'Relative / Friend' ? 'selected' : ''}>Relative / Friend</option>
                                    <option value="Parents" ${modelDetails.profileCreatedFor == 'Parents' ? 'selected' : ''}>Parents</option>
                                </select>
                            </div>
                            <div class="ep-v3-field ep-v3-col-1">
                                <label class="ep-v3-label">Full Name <span class="ep-v3-req">*</span></label>
                                <input type="text" id="firstName" name="firstName" class="ep-v3-input" value="${modelDetails.userModel.firstName != null ? modelDetails.userModel.firstName : ActUserName}">
                            </div>
                            <div class="ep-v3-field ep-v3-col-1">
                                <label class="ep-v3-label">Gender <span class="ep-v3-req">*</span></label>
                                <div class="ep-v3-radio-group">
                                    <label class="ep-v3-radio">
                                        <input type="radio" id="genderMale" name="gender" value="Male" ${modelDetails.gender == 'Male' ? 'checked' : ''}> Male
                                    </label>
                                    <label class="ep-v3-radio">
                                        <input type="radio" id="genderFemale" name="gender" value="Female" ${modelDetails.gender == 'Female' ? 'checked' : ''}> Female
                                    </label>
                                </div>
                            </div>
                            <div class="ep-v3-field ep-v3-col-1">
                                <label class="ep-v3-label">Date of Birth <span class="ep-v3-req">*</span></label>
                                <input type="text" id="dob" class="ep-v3-input dob" value="${dob}" placeholder="dd/mm/yyyy">
                            </div>

                            <div class="ep-v3-field ep-v3-col-1">
                                <label class="ep-v3-label">Mobile Number <span class="ep-v3-req">*</span></label>
                                <input type="text" id="mobile" name="mobile" class="ep-v3-input" value="${modelDetails.mobileNum != null ? modelDetails.mobileNum : modelDetails.userModel.mobileNo}" maxlength="10">
                            </div>
                            <div class="ep-v3-field ep-v3-col-1">
                                <label class="ep-v3-label">Email ID <span class="ep-v3-req">*</span></label>
                                <input type="text" id="email" class="ep-v3-input" value="${userMailId}">
                            </div>
                            <div class="ep-v3-field ep-v3-col-1">
                                <label class="ep-v3-label">Marital Status <span class="ep-v3-req">*</span></label>
                                <select id="materialStatus" name="materialStatus" class="ep-v3-select">
                                    <option value="0">Select marital status</option>
                                    <option value="Single" ${modelDetails.materialStatus == 'Single' ? 'selected' : ''}>Single</option>
                                    <option value="Never Married" ${modelDetails.materialStatus == 'Never Married' ? 'selected' : ''}>Never Married</option>
                                    <option value="Married" ${modelDetails.materialStatus == 'Married' ? 'selected' : ''}>Married</option>
                                    <option value="Divorced" ${modelDetails.materialStatus == 'Divorced' ? 'selected' : ''}>Divorced</option>
                                    <option value="Widowed" ${modelDetails.materialStatus == 'Widowed' ? 'selected' : ''}>Widowed</option>
                                    <option value="Awaiting Divorce" ${modelDetails.materialStatus == 'Awaiting Divorce' ? 'selected' : ''}>Awaiting Divorce</option>
                                </select>
                            </div>
                            <div class="ep-v3-field ep-v3-col-2">
                                <label class="ep-v3-label">About Me <span class="ep-v3-req">*</span></label>
                                <textarea id="aboutMe" name="aboutMe" class="ep-v3-textarea" onkeyup="countChar(this);">${modelDetails.aboutMe}</textarea>
                                <div class="ep-v3-char-count" id="charNum">Remaining 400 characters</div>
                            </div>
                        </div>
                    </div>

                    <!-- SECTION 2: PHYSICAL DETAILS -->
                    <div class="ep-v3-section">
                        <div class="ep-v3-sec-header">
                            <i class="fa fa-heartbeat ep-v3-sec-icon"></i> Physical Details
                        </div>
                        <div class="ep-v3-grid">
                            <div class="ep-v3-field ep-v3-col-1">
                                <label class="ep-v3-label">Height <span class="ep-v3-req">*</span></label>
                                <select id="heightSelect" name="heightSelect" class="ep-v3-select">
                                    <option value="0">Select Height</option>
                                    <option value="121.92">4 feet</option>
                                    <option value="124.46">4 feet 1 inches</option>
                                    <option value="127.00">4 feet 2 inches</option>
                                    <option value="129.54">4 feet 3 inches</option>
                                    <option value="132.08">4 feet 4 inches</option>
                                    <option value="134.62">4 feet 5 inches</option>
                                    <option value="137.16">4 feet 6 inches</option>
                                    <option value="139.70">4 feet 7 inches</option>
                                    <option value="142.24">4 feet 8 inches</option>
                                    <option value="144.78">4 feet 9 inches</option>
                                    <option value="147.32">4 feet 10 inches</option>
                                    <option value="149.86">4 feet 11 inches</option>
                                    <option value="152.40">5 feet</option>
                                    <option value="154.94">5 feet 1 inches</option>
                                    <option value="157.48">5 feet 2 inches</option>
                                    <option value="160.02">5 feet 3 inches</option>
                                    <option value="162.56">5 feet 4 inches</option>
                                    <option value="165.10">5 feet 5 inches</option>
                                    <option value="167.64">5 feet 6 inches</option>
                                    <option value="170.18">5 feet 7 inches</option>
                                    <option value="172.72">5 feet 8 inches</option>
                                    <option value="175.26">5 feet 9 inches</option>
                                    <option value="177.80">5 feet 10 inches</option>
                                    <option value="180.34">5 feet 11 inches</option>
                                    <option value="182.88">6 feet</option>
                                    <option value="185.42">6 feet 1 inches</option>
                                    <option value="187.96">6 feet 2 inches</option>
                                    <option value="190.50">6 feet 3 inches</option>
                                    <option value="193.04">6 feet 4 inches</option>
                                    <option value="195.58">6 feet 5 inches</option>
                                    <option value="198.12">6 feet 6 inches</option>
                                    <option value="200.66">6 feet 7 inches</option>
                                    <option value="203.20">6 feet 8 inches</option>
                                    <option value="205.74">6 feet 9 inches</option>
                                    <option value="208.28">6 feet 10 inches</option>
                                    <option value="210.82">6 feet 11 inches</option>
                                    <option value="213.36">7 feet</option>
                                    <option value="215.90">7 feet 1 inches</option>
                                    <option value="218.44">7 feet 2 inches</option>
                                    <option value="220.98">7 feet 3 inches</option>
                                    <option value="223.52">7 feet 4 inches</option>
                                    <option value="226.06">7 feet 5 inches</option>
                                    <option value="228.60">7 feet 6 inches</option>
                                    <option value="231.14">7 feet 7 inches</option>
                                    <option value="233.68">7 feet 8 inches</option>
                                    <option value="236.22">7 feet 9 inches</option>
                                    <option value="238.76">7 feet 10 inches</option>
                                    <option value="241.30">7 feet 11 inches</option>
                                </select>
                            </div>
                            <div class="ep-v3-field ep-v3-col-1">
                                <label class="ep-v3-label">Weight (kg) <span class="ep-v3-req">*</span></label>
                                <select id="weightSelect" name="weightSelect" class="ep-v3-select">
                                    <option value="0">Select Weight</option>
                                    <option value="41">41 kg</option>
                                    <option value="42">42 kg</option>
                                    <option value="43">43 kg</option>
                                    <option value="44">44 kg</option>
                                    <option value="45">45 kg</option>
                                    <option value="46">46 kg</option>
                                    <option value="47">47 kg</option>
                                    <option value="48">48 kg</option>
                                    <option value="49">49 kg</option>
                                    <option value="50">50 kg</option>
                                    <option value="51">51 kg</option>
                                    <option value="52">52 kg</option>
                                    <option value="53">53 kg</option>
                                    <option value="54">54 kg</option>
                                    <option value="55">55 kg</option>
                                    <option value="56">56 kg</option>
                                    <option value="57">57 kg</option>
                                    <option value="58">58 kg</option>
                                    <option value="59">59 kg</option>
                                    <option value="60">60 kg</option>
                                    <option value="61">61 kg</option>
                                    <option value="62">62 kg</option>
                                    <option value="63">63 kg</option>
                                    <option value="64">64 kg</option>
                                    <option value="65">65 kg</option>
                                    <option value="66">66 kg</option>
                                    <option value="67">67 kg</option>
                                    <option value="68">68 kg</option>
                                    <option value="69">69 kg</option>
                                    <option value="70">70 kg</option>
                                    <option value="71">71 kg</option>
                                    <option value="72">72 kg</option>
                                    <option value="73">73 kg</option>
                                    <option value="74">74 kg</option>
                                    <option value="75">75 kg</option>
                                    <option value="76">76 kg</option>
                                    <option value="77">77 kg</option>
                                    <option value="78">78 kg</option>
                                    <option value="79">79 kg</option>
                                    <option value="80">80 kg</option>
                                    <option value="81">81 kg</option>
                                    <option value="82">82 kg</option>
                                    <option value="83">83 kg</option>
                                    <option value="84">84 kg</option>
                                    <option value="85">85 kg</option>
                                    <option value="86">86 kg</option>
                                    <option value="87">87 kg</option>
                                    <option value="88">88 kg</option>
                                    <option value="89">89 kg</option>
                                    <option value="90">90 kg</option>
                                    <option value="91">91 kg</option>
                                    <option value="92">92 kg</option>
                                    <option value="93">93 kg</option>
                                    <option value="94">94 kg</option>
                                    <option value="95">95 kg</option>
                                    <option value="96">96 kg</option>
                                    <option value="97">97 kg</option>
                                    <option value="98">98 kg</option>
                                    <option value="99">99 kg</option>
                                    <option value="100">100 kg</option>
                                    <option value="101">101 kg</option>
                                    <option value="102">102 kg</option>
                                    <option value="103">103 kg</option>
                                    <option value="104">104 kg</option>
                                    <option value="105">105 kg</option>
                                    <option value="106">106 kg</option>
                                    <option value="107">107 kg</option>
                                    <option value="108">108 kg</option>
                                    <option value="109">109 kg</option>
                                    <option value="110">110 kg</option>
                                    <option value="111">111 kg</option>
                                    <option value="112">112 kg</option>
                                    <option value="113">113 kg</option>
                                    <option value="114">114 kg</option>
                                    <option value="115">115 kg</option>
                                    <option value="116">116 kg</option>
                                    <option value="117">117 kg</option>
                                    <option value="118">118 kg</option>
                                    <option value="119">119 kg</option>
                                    <option value="120">120 kg</option>
                                    <option value="121">121 kg</option>
                                    <option value="122">122 kg</option>
                                    <option value="123">123 kg</option>
                                    <option value="124">124 kg</option>
                                    <option value="125">125 kg</option>
                                    <option value="126">126 kg</option>
                                    <option value="127">127 kg</option>
                                    <option value="128">128 kg</option>
                                    <option value="129">129 kg</option>
                                    <option value="130">130 kg</option>
                                    <option value="131">131 kg</option>
                                    <option value="132">132 kg</option>
                                    <option value="133">133 kg</option>
                                    <option value="134">134 kg</option>
                                    <option value="135">135 kg</option>
                                    <option value="136">136 kg</option>
                                    <option value="137">137 kg</option>
                                    <option value="138">138 kg</option>
                                    <option value="139">139 kg</option>
                                    <option value="140">140 kg</option>
                                </select>
                            </div>
                            <div class="ep-v3-field ep-v3-col-1">
                                <label class="ep-v3-label">Physical Status <span class="ep-v3-req">*</span></label>
                                <select id="physicalStatus" name="physicalStatus" class="ep-v3-select">
                                    <option value="0">Select physical status</option>
                                    <option value="Normal" ${modelDetails.physicalStatus == 'Normal' || empty modelDetails.physicalStatus ? 'selected' : ''}>Normal</option>
                                    <option value="Physically challenged" ${modelDetails.physicalStatus == 'Physically challenged' || modelDetails.physicalStatus == 'Physically Challenged' ? 'selected' : ''}>Physically challenged</option>
                                    <option value="Mentally Challenged" ${modelDetails.physicalStatus == 'Mentally Challenged' ? 'selected' : ''}>Mentally Challenged</option>
                                    <option value="Deaf" ${modelDetails.physicalStatus == 'Deaf' ? 'selected' : ''}>Deaf</option>
                                    <option value="Dumb" ${modelDetails.physicalStatus == 'Dumb' ? 'selected' : ''}>Dumb</option>
                                    <option value="Other Disability" ${modelDetails.physicalStatus == 'Other Disability' ? 'selected' : ''}>Other Disability</option>
                                    <option value="Blind" ${modelDetails.physicalStatus == 'Blind' ? 'selected' : ''}>Blind</option>
                                </select>
                            </div>
                            <div class="ep-v3-field ep-v3-col-2">
                                <label class="ep-v3-label">Body Type <span class="ep-v3-req">*</span></label>
                                <div class="ep-v3-radio-group">
                                    <label class="ep-v3-radio"><input type="radio" name="bodyType" value="Slim" ${modelDetails.bodyType == 'Slim' ? 'checked' : ''}> Slim</label>
                                    <label class="ep-v3-radio"><input type="radio" name="bodyType" value="Average" ${modelDetails.bodyType == 'Average' || empty modelDetails.bodyType ? 'checked' : ''}> Average</label>
                                    <label class="ep-v3-radio"><input type="radio" name="bodyType" value="Athletic" ${modelDetails.bodyType == 'Athletic' ? 'checked' : ''}> Athletic</label>
                                    <label class="ep-v3-radio"><input type="radio" name="bodyType" value="Heavy" ${modelDetails.bodyType == 'Heavy' ? 'checked' : ''}> Heavy</label>
                                </div>
                            </div>
                            <div class="ep-v3-field ep-v3-col-2">
                                <label class="ep-v3-label">Complexion <span class="ep-v3-req">*</span></label>
                                <div class="ep-v3-radio-group">
                                    <label class="ep-v3-radio"><input type="radio" name="complexion" value="Very Fair" ${modelDetails.complexion == 'Very Fair' ? 'checked' : ''}> Very Fair</label>
                                    <label class="ep-v3-radio"><input type="radio" name="complexion" value="Fair" ${modelDetails.complexion == 'Fair' || empty modelDetails.complexion ? 'checked' : ''}> Fair</label>
                                    <label class="ep-v3-radio"><input type="radio" name="complexion" value="Wheatish" ${modelDetails.complexion == 'Wheatish' ? 'checked' : ''}> Wheatish</label>
                                    <label class="ep-v3-radio"><input type="radio" name="complexion" value="Wheatish brown" ${modelDetails.complexion == 'Wheatish brown' ? 'checked' : ''}> Wheatish Brown</label>
                                    <label class="ep-v3-radio"><input type="radio" name="complexion" value="Dark" ${modelDetails.complexion == 'Dark' ? 'checked' : ''}> Dark</label>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- SECTION 3: LOCATION & RELIGION -->
                    <div class="ep-v3-section">
                        <div class="ep-v3-sec-header">
                            <i class="fa fa-map-marker ep-v3-sec-icon"></i> Location & Community
                        </div>
                        <div class="ep-v3-grid">
                            <div class="ep-v3-field ep-v3-col-1">
                                <label class="ep-v3-label">Country <span class="ep-v3-req">*</span></label>
                                <select id="countrySelect" name="countrySelect" class="ep-v3-select">
                                    <c:forEach var="c" items="${countries}">
                                        <option value="${c.country_id}" selected>${c.country_Name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="ep-v3-field ep-v3-col-1">
                                <label class="ep-v3-label">State <span class="ep-v3-req">*</span></label>
                                <select id="stateSelect" name="stateSelect" class="ep-v3-select" onchange="loadCityAccordingToState(this.value);">
                                    <option value="0">Select State</option>
                                    <c:forEach var="state" items="${state_Countries}">
                                        <option value="${state.state_Country_id}" ${modelDetails.state_Country != null && modelDetails.state_Country.state_Country_id == state.state_Country_id ? 'selected' : ''}>${state.state_Name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="ep-v3-field ep-v3-col-1">
                                <label class="ep-v3-label">City / District <span class="ep-v3-req">*</span></label>
                                <select id="cityStateSelect" name="cityStateSelect" class="ep-v3-select" onchange="loadsubDistrictAccordingToDistrict(this.value);">
                                    <option value="0">Select District</option>
                                    <c:if test="${modelDetails.districts != null}">
                                        <option value="${modelDetails.districts.state_district_id}" selected>${modelDetails.districts.district_Name}</option>
                                    </c:if>
                                </select>
                            </div>
                            <div class="ep-v3-field ep-v3-col-1">
                                <label class="ep-v3-label">Sub District / Tehsil</label>
                                <select id="subDistSelect" name="subDistSelect" class="ep-v3-select">
                                    <option value="0">Select Sub District</option>
                                    <c:if test="${modelDetails.subDistricts != null}">
                                        <option value="${modelDetails.subDistricts.district_subDistricts_id}" selected>${modelDetails.subDistricts.subdistricts_Name}</option>
                                    </c:if>
                                </select>
                            </div>
                            <div class="ep-v3-field ep-v3-col-1">
                                <label class="ep-v3-label">Landmark / Address</label>
                                <input type="text" id="userLandMark" name="userLandMark" class="ep-v3-input" value="${modelDetails.landmark}">
                            </div>
                            <div class="ep-v3-field ep-v3-col-1">
                                <label class="ep-v3-label">Religion <span class="ep-v3-req">*</span></label>
                                <select id="religionSelect" name="religionSelect" class="ep-v3-select" onchange="loadCasteAccordingToReligion(this.value);">
                                    <option value="0">Select Religion</option>
                                    <c:forEach var="rel" items="${religions}">
                                        <option value="${rel.religion_id}" ${modelDetails.caste != null && modelDetails.caste.religion_id != null && modelDetails.caste.religion_id.religion_id == rel.religion_id ? 'selected' : ''}>${rel.religion_name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="ep-v3-field ep-v3-col-1">
                                <label class="ep-v3-label">Caste <span class="ep-v3-req">*</span></label>
                                <select id="casteSelect" name="casteSelect" class="ep-v3-select">
                                    <option value="0">Select Caste</option>
                                    <c:if test="${modelDetails.caste != null}">
                                        <option value="${modelDetails.caste.caste_id}" selected>${modelDetails.caste.caste_name}</option>
                                    </c:if>
                                </select>
                            </div>
                        </div>
                    </div>

                    <!-- SECTION 4: EDUCATION & OCCUPATION -->
                    <div class="ep-v3-section">
                        <div class="ep-v3-sec-header">
                            <i class="fa fa-briefcase ep-v3-sec-icon"></i> Education & Career
                        </div>
                        <div class="ep-v3-grid">
                            <div class="ep-v3-field ep-v3-col-1">
                                <label class="ep-v3-label">Education Category <span class="ep-v3-req">*</span></label>
                                <select id="educationalCategorySelect" name="educationalCategorySelect" class="ep-v3-select" onchange="loadEducationAccordingToEduCategory(this.value);">
                                    <option value="0">Select Category</option>
                                    <c:forEach var="eduCat" items="${educationCategories}">
                                        <option value="${eduCat.edu_cat_id}" ${modelDetails.education != null && modelDetails.education.edu_cat_id != null && modelDetails.education.edu_cat_id.edu_cat_id == eduCat.edu_cat_id ? 'selected' : ''}>${eduCat.edu_cat_name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="ep-v3-field ep-v3-col-1">
                                <label class="ep-v3-label">Education Degree <span class="ep-v3-req">*</span></label>
                                <select id="educationalSelect" name="educationalSelect" class="ep-v3-select">
                                    <option value="0">Select Degree</option>
                                    <c:if test="${modelDetails.education != null}">
                                        <option value="${modelDetails.education.education_id}" selected>${modelDetails.education.education_name}</option>
                                    </c:if>
                                </select>
                            </div>
                            <div class="ep-v3-field ep-v3-col-1">
                                <label class="ep-v3-label">Occupation Category <span class="ep-v3-req">*</span></label>
                                <select id="occupationCategorySelect" name="occupationCategorySelect" class="ep-v3-select" onchange="loadOccupationAccordingToOccuCategory(this.value);">
                                    <option value="0">Select Category</option>
                                    <c:forEach var="occCat" items="${occupationCategories}">
                                        <option value="${occCat.occu_id}" ${modelDetails.occupation != null && modelDetails.occupation.occ_cat_id != null && modelDetails.occupation.occ_cat_id.occu_id == occCat.occu_id ? 'selected' : ''}>${occCat.occu_name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="ep-v3-field ep-v3-col-1">
                                <label class="ep-v3-label">Occupation <span class="ep-v3-req">*</span></label>
                                <select id="occupationSelect" name="occupationSelect" class="ep-v3-select">
                                    <option value="0">Select Occupation</option>
                                    <c:if test="${modelDetails.occupation != null}">
                                        <option value="${modelDetails.occupation.occupation_id}" selected>${modelDetails.occupation.occupation_name}</option>
                                    </c:if>
                                </select>
                            </div>
                            <div class="ep-v3-field ep-v3-col-1">
                                <label class="ep-v3-label">Annual Income (Lakhs)</label>
                                <input type="text" id="incomeAmount" name="incomeAmount" class="ep-v3-input" value="${modelDetails.incomeAmount != null ? modelDetails.incomeAmount : '5-10 Lakhs'}">
                            </div>
                            <div class="ep-v3-field ep-v3-col-2">
                                <label class="ep-v3-label">Employed In</label>
                                <div class="ep-v3-radio-group">
                                    <label class="ep-v3-radio"><input type="radio" name="employedIn" value="Government" ${modelDetails.employeedIn == 'Government' ? 'checked' : ''}> Government</label>
                                    <label class="ep-v3-radio"><input type="radio" name="employedIn" value="Defence" ${modelDetails.employeedIn == 'Defence' ? 'checked' : ''}> Defence</label>
                                    <label class="ep-v3-radio"><input type="radio" name="employedIn" value="Private" ${modelDetails.employeedIn == 'Private' || empty modelDetails.employeedIn ? 'checked' : ''}> Private</label>
                                    <label class="ep-v3-radio"><input type="radio" name="employedIn" value="Business" ${modelDetails.employeedIn == 'Business' ? 'checked' : ''}> Business</label>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- SECTION 5: LIFESTYLE & FAMILY -->
                    <div class="ep-v3-lifestyle-section">
                        <div class="ep-v3-sec-header">
                            <div class="ep-v3-sec-icon-wrapper">
                                <i class="fa fa-cutlery ep-v3-sec-icon"></i>
                            </div>
                            <div>
                                <div class="ep-v3-sec-title">Personal Lifestyle & Habits</div>
                                <div class="ep-v3-sec-subtitle">Specify your personal habits and daily lifestyle choices</div>
                            </div>
                        </div>
                        <div class="ep-v3-lifestyle-grid">
                            <!-- EATING HABITS -->
                            <div class="ep-v3-lifestyle-group">
                                <label class="ep-v3-lifestyle-label">
                                    <i class="fa fa-cutlery label-icon"></i> Eating Habits
                                </label>
                                <div class="ep-v3-segmented-control">
                                    <label class="ep-v3-segmented-option">
                                        <input type="radio" name="eating" value="vegetarian" ${modelDetails.eating == 'vegetarian' || modelDetails.eating == 'Vegetarian' ? 'checked' : ''}>
                                        <div class="ep-v3-chip-content">
                                            <span class="ep-v3-chip-icon veg-icon"><i class="fa fa-leaf"></i></span>
                                            <span class="ep-v3-chip-text">Vegetarian</span>
                                            <i class="fa fa-check-circle ep-v3-chip-check"></i>
                                        </div>
                                    </label>
                                    <label class="ep-v3-segmented-option">
                                        <input type="radio" name="eating" value="Non vegetarian" ${modelDetails.eating == 'Non vegetarian' || modelDetails.eating == 'Non-Vegetarian' || empty modelDetails.eating ? 'checked' : ''}>
                                        <div class="ep-v3-chip-content">
                                            <span class="ep-v3-chip-icon nonveg-icon"><i class="fa fa-cutlery"></i></span>
                                            <span class="ep-v3-chip-text">Non vegetarian</span>
                                            <i class="fa fa-check-circle ep-v3-chip-check"></i>
                                        </div>
                                    </label>
                                    <label class="ep-v3-segmented-option">
                                        <input type="radio" name="eating" value="Eggs Only" ${modelDetails.eating == 'Eggs Only' || modelDetails.eating == 'Eggetarian' ? 'checked' : ''}>
                                        <div class="ep-v3-chip-content">
                                            <span class="ep-v3-chip-icon nopref-icon"><i class="fa fa-cutlery"></i></span>
                                            <span class="ep-v3-chip-text">Eggs Only</span>
                                            <i class="fa fa-check-circle ep-v3-chip-check"></i>
                                        </div>
                                    </label>
                                </div>
                            </div>

                            <!-- DRINKING HABITS -->
                            <div class="ep-v3-lifestyle-group">
                                <label class="ep-v3-lifestyle-label">
                                    <i class="fa fa-glass label-icon"></i> Drinking Habits
                                </label>
                                <div class="ep-v3-segmented-control">
                                    <label class="ep-v3-segmented-option">
                                        <input type="radio" name="drink" value="Never" ${modelDetails.drink == 'Never' || modelDetails.drink == 'No' || empty modelDetails.drink ? 'checked' : ''}>
                                        <div class="ep-v3-chip-content">
                                            <span class="ep-v3-chip-icon never-icon"><i class="fa fa-ban"></i></span>
                                            <span class="ep-v3-chip-text">Never</span>
                                            <i class="fa fa-check-circle ep-v3-chip-check"></i>
                                        </div>
                                    </label>
                                    <label class="ep-v3-segmented-option">
                                        <input type="radio" name="drink" value="Social drinker" ${modelDetails.drink == 'Social drinker' || modelDetails.drink == 'Social Drinker' || modelDetails.drink == 'Yes' ? 'checked' : ''}>
                                        <div class="ep-v3-chip-content">
                                            <span class="ep-v3-chip-icon social-icon"><i class="fa fa-glass"></i></span>
                                            <span class="ep-v3-chip-text">Social drinker</span>
                                            <i class="fa fa-check-circle ep-v3-chip-check"></i>
                                        </div>
                                    </label>
                                    <label class="ep-v3-segmented-option">
                                        <input type="radio" name="drink" value="Regular drinker" ${modelDetails.drink == 'Regular drinker' || modelDetails.drink == 'Regular Drinker' ? 'checked' : ''}>
                                        <div class="ep-v3-chip-content">
                                            <span class="ep-v3-chip-icon social-icon"><i class="fa fa-glass"></i></span>
                                            <span class="ep-v3-chip-text">Regular drinker</span>
                                            <i class="fa fa-check-circle ep-v3-chip-check"></i>
                                        </div>
                                    </label>
                                </div>
                            </div>

                            <!-- SMOKING HABITS -->
                            <div class="ep-v3-lifestyle-group">
                                <label class="ep-v3-lifestyle-label">
                                    <i class="fa fa-ban label-icon"></i> Smoking Habits
                                </label>
                                <div class="ep-v3-segmented-control">
                                    <label class="ep-v3-segmented-option">
                                        <input type="radio" name="smoke" value="Non-smoker" ${modelDetails.smoke == 'Non-smoker' || modelDetails.smoke == 'Non Smoker' || modelDetails.smoke == 'No' || empty modelDetails.smoke ? 'checked' : ''}>
                                        <div class="ep-v3-chip-content">
                                            <span class="ep-v3-chip-icon never-icon"><i class="fa fa-shield"></i></span>
                                            <span class="ep-v3-chip-text">Non-smoker</span>
                                            <i class="fa fa-check-circle ep-v3-chip-check"></i>
                                        </div>
                                    </label>
                                    <label class="ep-v3-segmented-option">
                                        <input type="radio" name="smoke" value="Social smoker" ${modelDetails.smoke == 'Social smoker' || modelDetails.smoke == 'Social Smoker' || modelDetails.smoke == 'Yes' ? 'checked' : ''}>
                                        <div class="ep-v3-chip-content">
                                            <span class="ep-v3-chip-icon social-icon"><i class="fa fa-fire"></i></span>
                                            <span class="ep-v3-chip-text">Social smoker</span>
                                            <i class="fa fa-check-circle ep-v3-chip-check"></i>
                                        </div>
                                    </label>
                                    <label class="ep-v3-segmented-option">
                                        <input type="radio" name="smoke" value="Chain smoker" ${modelDetails.smoke == 'Chain smoker' || modelDetails.smoke == 'Chain Smoker' ? 'checked' : ''}>
                                        <div class="ep-v3-chip-content">
                                            <span class="ep-v3-chip-icon social-icon"><i class="fa fa-fire"></i></span>
                                            <span class="ep-v3-chip-text">Chain smoker</span>
                                            <i class="fa fa-check-circle ep-v3-chip-check"></i>
                                        </div>
                                    </label>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- ACTION BAR -->
                    <div class="ep-v3-action-bar">
                        <button type="button" class="ep-v3-btn-outline" onclick="showStep(1);">Back</button>
                        <input type="button" id="btnSubmit" class="ep-v3-btn-primary" value="Save & Next" onclick="updateUser(); showStep(3);">
                    </div>
                </form>
            </div>
        </div>

        <!-- STEP 3: PARTNER PREFERENCE PANE -->
        <div class="ep-v3-step-pane" id="step3-pane" style="display: none;">
            <div class="ep-v3-main-card">
                <h2 class="ep-v3-card-title">Partner Preferences</h2>
                <p class="ep-v3-card-sub">Tell us what you are looking for in your life partner</p>

                <form class="form-horizontal" id="companyFormPartner" method="post">
                    
                    <!-- SECTION 1: BASIC PREFERENCES -->
                    <div class="ep-v3-section">
                        <div class="ep-v3-sec-header">
                            <i class="fa fa-user-o ep-v3-sec-icon"></i> Basic Preferences
                        </div>
                        <div class="ep-v3-grid">
                            <div class="ep-v3-field ep-v3-col-1">
                                <label class="ep-v3-label">Marital Status</label>
                                <select id="materialStatusPartner" name="materialStatusPartner" class="ep-v3-select">
                                    <option value="0">Select Status</option>
                                    <option value="Single" ${userPartnerPreferances.materialStatus == 'Single' ? 'selected' : ''}>Single</option>
                                    <option value="Never Married" ${userPartnerPreferances.materialStatus == 'Never Married' ? 'selected' : ''}>Never Married</option>
                                    <option value="Married" ${userPartnerPreferances.materialStatus == 'Married' ? 'selected' : ''}>Married</option>
                                    <option value="Divorced" ${userPartnerPreferances.materialStatus == 'Divorced' ? 'selected' : ''}>Divorced</option>
                                    <option value="Widowed" ${userPartnerPreferances.materialStatus == 'Widowed' ? 'selected' : ''}>Widowed</option>
                                    <option value="Does Not Matter" ${userPartnerPreferances.materialStatus == 'Does Not Matter' ? 'selected' : ''}>Does Not Matter</option>
                                </select>
                            </div>
                            <div class="ep-v3-field ep-v3-col-1">
                                <label class="ep-v3-label">Physical Status</label>
                                <select id="physicalStatusPartner" name="physicalStatusPartner" class="ep-v3-select">
                                    <option value="0">Physical Status</option>
                                    <option value="Normal" ${userPartnerPreferances.physicalStatus == 'Normal' || empty userPartnerPreferances.physicalStatus ? 'selected' : ''}>Normal</option>
                                    <option value="Physically challenged" ${userPartnerPreferances.physicalStatus == 'Physically challenged' || userPartnerPreferances.physicalStatus == 'Physically Challenged' ? 'selected' : ''}>Physically challenged</option>
                                    <option value="Mentally Challenged" ${userPartnerPreferances.physicalStatus == 'Mentally Challenged' ? 'selected' : ''}>Mentally Challenged</option>
                                    <option value="Deaf" ${userPartnerPreferances.physicalStatus == 'Deaf' ? 'selected' : ''}>Deaf</option>
                                    <option value="Dumb" ${userPartnerPreferances.physicalStatus == 'Dumb' ? 'selected' : ''}>Dumb</option>
                                    <option value="Other Disability" ${userPartnerPreferances.physicalStatus == 'Other Disability' ? 'selected' : ''}>Other Disability</option>
                                    <option value="Blind" ${userPartnerPreferances.physicalStatus == 'Blind' ? 'selected' : ''}>Blind</option>
                                </select>
                            </div>
                            <div class="ep-v3-field ep-v3-col-1">
                                <label class="ep-v3-label">Height Range (From - To)</label>
                                <div style="display:flex; gap:4%;">
                                    <select id="heightSelectPartnerFirst" name="heightSelectPartnerFirst" class="ep-v3-select" style="width:48%;">
                                        <option value="0">Select Height</option>
                                        <option value="121.92">4 feet</option>
                                        <option value="124.46">4 feet 1 inches</option>
                                        <option value="127.00">4 feet 2 inches</option>
                                        <option value="129.54">4 feet 3 inches</option>
                                        <option value="132.08">4 feet 4 inches</option>
                                        <option value="134.62">4 feet 5 inches</option>
                                        <option value="137.16">4 feet 6 inches</option>
                                        <option value="139.70">4 feet 7 inches</option>
                                        <option value="142.24">4 feet 8 inches</option>
                                        <option value="144.78">4 feet 9 inches</option>
                                        <option value="147.32">4 feet 10 inches</option>
                                        <option value="149.86">4 feet 11 inches</option>
                                        <option value="152.40" selected>5 feet</option>
                                        <option value="154.94">5 feet 1 inches</option>
                                        <option value="157.48">5 feet 2 inches</option>
                                        <option value="160.02">5 feet 3 inches</option>
                                        <option value="162.56">5 feet 4 inches</option>
                                        <option value="165.10">5 feet 5 inches</option>
                                        <option value="167.64">5 feet 6 inches</option>
                                        <option value="170.18">5 feet 7 inches</option>
                                        <option value="172.72">5 feet 8 inches</option>
                                        <option value="175.26">5 feet 9 inches</option>
                                        <option value="177.80">5 feet 10 inches</option>
                                        <option value="180.34">5 feet 11 inches</option>
                                        <option value="182.88">6 feet</option>
                                        <option value="185.42">6 feet 1 inches</option>
                                        <option value="187.96">6 feet 2 inches</option>
                                        <option value="190.50">6 feet 3 inches</option>
                                        <option value="193.04">6 feet 4 inches</option>
                                        <option value="195.58">6 feet 5 inches</option>
                                        <option value="198.12">6 feet 6 inches</option>
                                        <option value="200.66">6 feet 7 inches</option>
                                        <option value="203.20">6 feet 8 inches</option>
                                        <option value="205.74">6 feet 9 inches</option>
                                        <option value="208.28">6 feet 10 inches</option>
                                        <option value="210.82">6 feet 11 inches</option>
                                        <option value="213.36">7 feet</option>
                                        <option value="215.90">7 feet 1 inches</option>
                                        <option value="218.44">7 feet 2 inches</option>
                                        <option value="220.98">7 feet 3 inches</option>
                                        <option value="223.52">7 feet 4 inches</option>
                                        <option value="226.06">7 feet 5 inches</option>
                                        <option value="228.60">7 feet 6 inches</option>
                                        <option value="231.14">7 feet 7 inches</option>
                                        <option value="233.68">7 feet 8 inches</option>
                                        <option value="236.22">7 feet 9 inches</option>
                                        <option value="238.76">7 feet 10 inches</option>
                                        <option value="241.30">7 feet 11 inches</option>
                                    </select>
                                    <select id="heightSelectPartnerSecond" name="heightSelectPartnerSecond" class="ep-v3-select" style="width:48%;">
                                        <option value="0">Select Height</option>
                                        <option value="121.92">4 feet</option>
                                        <option value="124.46">4 feet 1 inches</option>
                                        <option value="127.00">4 feet 2 inches</option>
                                        <option value="129.54">4 feet 3 inches</option>
                                        <option value="132.08">4 feet 4 inches</option>
                                        <option value="134.62">4 feet 5 inches</option>
                                        <option value="137.16">4 feet 6 inches</option>
                                        <option value="139.70">4 feet 7 inches</option>
                                        <option value="142.24">4 feet 8 inches</option>
                                        <option value="144.78">4 feet 9 inches</option>
                                        <option value="147.32">4 feet 10 inches</option>
                                        <option value="149.86">4 feet 11 inches</option>
                                        <option value="152.40">5 feet</option>
                                        <option value="154.94">5 feet 1 inches</option>
                                        <option value="157.48">5 feet 2 inches</option>
                                        <option value="160.02">5 feet 3 inches</option>
                                        <option value="162.56">5 feet 4 inches</option>
                                        <option value="165.10">5 feet 5 inches</option>
                                        <option value="167.64">5 feet 6 inches</option>
                                        <option value="170.18">5 feet 7 inches</option>
                                        <option value="172.72">5 feet 8 inches</option>
                                        <option value="175.26">5 feet 9 inches</option>
                                        <option value="177.80">5 feet 10 inches</option>
                                        <option value="180.34">5 feet 11 inches</option>
                                        <option value="182.88" selected>6 feet</option>
                                        <option value="185.42">6 feet 1 inches</option>
                                        <option value="187.96">6 feet 2 inches</option>
                                        <option value="190.50">6 feet 3 inches</option>
                                        <option value="193.04">6 feet 4 inches</option>
                                        <option value="195.58">6 feet 5 inches</option>
                                        <option value="198.12">6 feet 6 inches</option>
                                        <option value="200.66">6 feet 7 inches</option>
                                        <option value="203.20">6 feet 8 inches</option>
                                        <option value="205.74">6 feet 9 inches</option>
                                        <option value="208.28">6 feet 10 inches</option>
                                        <option value="210.82">6 feet 11 inches</option>
                                        <option value="213.36">7 feet</option>
                                        <option value="215.90">7 feet 1 inches</option>
                                        <option value="218.44">7 feet 2 inches</option>
                                        <option value="220.98">7 feet 3 inches</option>
                                        <option value="223.52">7 feet 4 inches</option>
                                        <option value="226.06">7 feet 5 inches</option>
                                        <option value="228.60">7 feet 6 inches</option>
                                        <option value="231.14">7 feet 7 inches</option>
                                        <option value="233.68">7 feet 8 inches</option>
                                        <option value="236.22">7 feet 9 inches</option>
                                        <option value="238.76">7 feet 10 inches</option>
                                        <option value="241.30">7 feet 11 inches</option>
                                    </select>
                                </div>
                            </div>
                            <div class="ep-v3-field ep-v3-col-1">
                                <label class="ep-v3-label">Body Type</label>
                                <select id="bodyTypePartner" name="bodyTypePartner" class="ep-v3-select">
                                    <option value="Slim" ${userPartnerPreferances.bodyType == 'Slim' ? 'selected' : ''}>Slim</option>
                                    <option value="Average" ${userPartnerPreferances.bodyType == 'Average' ? 'selected' : ''}>Average</option>
                                    <option value="Slim, Average" ${userPartnerPreferances.bodyType == 'Slim, Average' || empty userPartnerPreferances.bodyType ? 'selected' : ''}>Slim, Average</option>
                                    <option value="Athletic" ${userPartnerPreferances.bodyType == 'Athletic' ? 'selected' : ''}>Athletic</option>
                                    <option value="Heavy" ${userPartnerPreferances.bodyType == 'Heavy' ? 'selected' : ''}>Heavy</option>
                                    <option value="Does Not Matter" ${userPartnerPreferances.bodyType == 'Does Not Matter' ? 'selected' : ''}>Does Not Matter</option>
                                </select>
                            </div>
                        </div>
                    </div>

                    <!-- SECTION 2: LOCATION PREFERENCES -->
                    <div class="ep-v3-section">
                        <div class="ep-v3-sec-header">
                            <i class="fa fa-map-marker ep-v3-sec-icon"></i> Location Preferences
                        </div>
                        <div class="ep-v3-grid">
                            <div class="ep-v3-field ep-v3-col-1">
                                <label class="ep-v3-label">Country</label>
                                <select id="countrySelectPartner" name="countrySelectPartner" class="ep-v3-select">
                                    <c:forEach var="c" items="${countries}">
                                        <option value="${c.country_id}" selected>${c.country_Name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="ep-v3-field ep-v3-col-1">
                                <label class="ep-v3-label">State</label>
                                <select id="stateSelectPartner" name="stateSelectPartner" class="ep-v3-select" onchange="loadCityAccordingToStatePartnerPreferance(this.value);">
                                    <option value="0">Select State</option>
                                    <c:forEach var="state" items="${state_Countries}">
                                        <option value="${state.state_Country_id}" ${userPartnerPreferances.state_Country != null && userPartnerPreferances.state_Country.state_Country_id == state.state_Country_id ? 'selected' : ''}>${state.state_Name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="ep-v3-field ep-v3-col-1">
                                <label class="ep-v3-label">City / District</label>
                                <select id="cityStateSelectPartnerPreferance" name="cityStateSelectPartnerPreferance" class="ep-v3-select">
                                    <option value="0">Select District</option>
                                    <c:if test="${userPartnerPreferances.districts != null}">
                                        <option value="${userPartnerPreferances.districts.state_district_id}" selected>${userPartnerPreferances.districts.district_Name}</option>
                                    </c:if>
                                </select>
                            </div>
                            <div class="ep-v3-field ep-v3-col-1">
                                <label class="ep-v3-label">Other Location Preferences</label>
                                <div style="display:flex; align-items:center; gap:8px; height:42px;">
                                    <input type="checkbox" id="openToOtherCitiesPartner" name="openToOtherCitiesPartner" value="Yes" ${userPartnerPreferances.openToOtherCities == 'Yes' || empty userPartnerPreferances.openToOtherCities ? 'checked' : ''} style="accent-color:var(--primary); width:18px; height:18px;">
                                    <span style="font-size:13px; font-weight:600;">Open to other cities in Maharashtra</span>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- SECTION 3: PROFESSIONAL PREFERENCES -->
                    <div class="ep-v3-section">
                        <div class="ep-v3-sec-header">
                            <i class="fa fa-graduation-cap ep-v3-sec-icon"></i> Professional Preferences
                        </div>
                        <div class="ep-v3-grid">
                            <div class="ep-v3-field ep-v3-col-1">
                                <label class="ep-v3-label">Education</label>
                                <select id="educationalCategorySelectPartner" name="educationalCategorySelectPartner" class="ep-v3-select">
                                    <option value="0" ${selectedPartnerEduCatId == null || selectedPartnerEduCatId == 0 ? 'selected' : ''}>Any</option>
                                    <c:forEach var="eduCat" items="${educationCategories}">
                                        <option value="${eduCat.edu_cat_id}" ${selectedPartnerEduCatId != null && selectedPartnerEduCatId == eduCat.edu_cat_id ? 'selected' : ''}>${eduCat.edu_cat_name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="ep-v3-field ep-v3-col-1">
                                <label class="ep-v3-label">Occupation</label>
                                <select id="occupationCategorySelectPartner" name="occupationCategorySelectPartner" class="ep-v3-select">
                                    <option value="0" ${selectedPartnerOccCatId == null || selectedPartnerOccCatId == 0 ? 'selected' : ''}>Any</option>
                                    <c:forEach var="occCat" items="${occupationCategories}">
                                        <option value="${occCat.occu_id}" ${selectedPartnerOccCatId != null && selectedPartnerOccCatId == occCat.occu_id ? 'selected' : ''}>${occCat.occu_name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="ep-v3-field ep-v3-col-1">
                                <label class="ep-v3-label">Annual Income</label>
                                <select id="incomeAmountPartner" name="incomeAmountPartner" class="ep-v3-select">
                                    <option value="Any" ${userPartnerPreferances.incomeType == 'Any' || userPartnerPreferances.incomeAmount == null || userPartnerPreferances.incomeAmount == 0 ? 'selected' : ''}>Any</option>
                                    <option value="3-5 Lakhs" ${userPartnerPreferances.incomeType == '3-5 Lakhs' || userPartnerPreferances.incomeAmount == 300000 ? 'selected' : ''}>3-5 Lakhs</option>
                                    <option value="5-10 Lakhs" ${userPartnerPreferances.incomeType == '5-10 Lakhs' || userPartnerPreferances.incomeAmount == 500000 ? 'selected' : ''}>5-10 Lakhs</option>
                                    <option value="10-15 Lakhs" ${userPartnerPreferances.incomeType == '10-15 Lakhs' || userPartnerPreferances.incomeAmount == 1000000 ? 'selected' : ''}>10-15 Lakhs</option>
                                    <option value="15+ Lakhs" ${userPartnerPreferances.incomeType == '15+ Lakhs' || userPartnerPreferances.incomeAmount == 1500000 ? 'selected' : ''}>15+ Lakhs</option>
                                </select>
                            </div>
                            <div class="ep-v3-field ep-v3-col-1">
                                <label class="ep-v3-label">Self-Employed Preference</label>
                                <div style="display:flex; align-items:center; gap:8px; height:42px;">
                                    <input type="checkbox" id="openToSelfEmployed" name="openToSelfEmployed" value="Yes" ${userPartnerPreferances.openToSelfEmployed == 'Yes' || empty userPartnerPreferances.openToSelfEmployed ? 'checked' : ''} style="accent-color:var(--primary); width:18px; height:18px;">
                                    <span style="font-size:13px; font-weight:600;">Open to partner who is self-employed</span>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- SECTION 4: RELIGION & COMMUNITY -->
                    <div class="ep-v3-section">
                        <div class="ep-v3-sec-header">
                            <i class="fa fa-home ep-v3-sec-icon"></i> Religion & Community
                        </div>
                        <div class="ep-v3-grid">
                            <div class="ep-v3-field ep-v3-col-1">
                                <label class="ep-v3-label">Religion</label>
                                <select id="religionSelectPartner" name="religionSelectPartner" class="ep-v3-select" onchange="loadCasteAccordingToReligionPartner(this.value);">
                                    <option value="0">Select Religion</option>
                                    <c:forEach var="rel" items="${religions}">
                                        <option value="${rel.religion_id}" ${userPartnerPreferances.religion != null && userPartnerPreferances.religion.religion_id == rel.religion_id ? 'selected' : ''}>${rel.religion_name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="ep-v3-field ep-v3-col-1">
                                <label class="ep-v3-label">Caste</label>
                                <select id="casteSelectPartner" name="casteSelectPartner" class="ep-v3-select">
                                    <option value="0">Select Caste</option>
                                    <c:if test="${userPartnerPreferances.caste != null}">
                                        <option value="${userPartnerPreferances.caste.caste_id}" selected>${userPartnerPreferances.caste.caste_name}</option>
                                    </c:if>
                                </select>
                            </div>
                            <div class="ep-v3-field ep-v3-col-1">
                                <label class="ep-v3-label">Community (Optional)</label>
                                <select id="communityPartner" name="communityPartner" class="ep-v3-select">
                                    <option value="Any" ${userPartnerPreferances.community == 'Any' || empty userPartnerPreferances.community ? 'selected' : ''}>Any</option>
                                    <option value="Marathi" ${userPartnerPreferances.community == 'Marathi' ? 'selected' : ''}>Marathi</option>
                                    <option value="Gujarati" ${userPartnerPreferances.community == 'Gujarati' ? 'selected' : ''}>Gujarati</option>
                                    <option value="Hindi" ${userPartnerPreferances.community == 'Hindi' ? 'selected' : ''}>Hindi</option>
                                </select>
                            </div>
                        </div>
                    </div>

                    <!-- SECTION 5: LIFESTYLE PREFERENCES -->
                    <div class="ep-v3-lifestyle-section">
                        <div class="ep-v3-sec-header">
                            <div class="ep-v3-sec-icon-wrapper">
                                <i class="fa fa-heart ep-v3-sec-icon"></i>
                            </div>
                            <div>
                                <div class="ep-v3-sec-title">Lifestyle Preferences</div>
                                <div class="ep-v3-sec-subtitle">Set your lifestyle choices & expectations for ideal match recommendations</div>
                            </div>
                        </div>

                        <div class="ep-v3-lifestyle-grid">
                            <!-- EATING HABITS -->
                            <div class="ep-v3-lifestyle-group">
                                <label class="ep-v3-lifestyle-label">
                                    <i class="fa fa-cutlery label-icon"></i> Eating Habits
                                </label>
                                <div class="ep-v3-segmented-control">
                                    <label class="ep-v3-segmented-option">
                                        <input type="radio" name="eatingPartner" value="vegetarian" ${userPartnerPreferances.eating == 'vegetarian' || userPartnerPreferances.eating == 'Vegetarian' ? 'checked' : ''}>
                                        <div class="ep-v3-chip-content">
                                            <span class="ep-v3-chip-icon veg-icon"><i class="fa fa-leaf"></i></span>
                                            <span class="ep-v3-chip-text">Vegetarian</span>
                                            <i class="fa fa-check-circle ep-v3-chip-check"></i>
                                        </div>
                                    </label>
                                    <label class="ep-v3-segmented-option">
                                        <input type="radio" name="eatingPartner" value="Non vegetarian" ${userPartnerPreferances.eating == 'Non vegetarian' || userPartnerPreferances.eating == 'Non-Vegetarian' ? 'checked' : ''}>
                                        <div class="ep-v3-chip-content">
                                            <span class="ep-v3-chip-icon nonveg-icon"><i class="fa fa-cutlery"></i></span>
                                            <span class="ep-v3-chip-text">Non vegetarian</span>
                                            <i class="fa fa-check-circle ep-v3-chip-check"></i>
                                        </div>
                                    </label>
                                    <label class="ep-v3-segmented-option">
                                        <input type="radio" name="eatingPartner" value="Eggs Only" ${userPartnerPreferances.eating == 'Eggs Only' || userPartnerPreferances.eating == 'Eggetarian' ? 'checked' : ''}>
                                        <div class="ep-v3-chip-content">
                                            <span class="ep-v3-chip-icon nopref-icon"><i class="fa fa-cutlery"></i></span>
                                            <span class="ep-v3-chip-text">Eggs Only</span>
                                            <i class="fa fa-check-circle ep-v3-chip-check"></i>
                                        </div>
                                    </label>
                                    <label class="ep-v3-segmented-option">
                                        <input type="radio" name="eatingPartner" value="No Preference" ${userPartnerPreferances.eating == 'No Preference' || empty userPartnerPreferances.eating ? 'checked' : ''}>
                                        <div class="ep-v3-chip-content">
                                            <span class="ep-v3-chip-icon nopref-icon"><i class="fa fa-globe"></i></span>
                                            <span class="ep-v3-chip-text">No Preference</span>
                                            <i class="fa fa-check-circle ep-v3-chip-check"></i>
                                        </div>
                                    </label>
                                </div>
                            </div>

                            <!-- DRINKING HABITS -->
                            <div class="ep-v3-lifestyle-group">
                                <label class="ep-v3-lifestyle-label">
                                    <i class="fa fa-glass label-icon"></i> Drinking Habits
                                </label>
                                <div class="ep-v3-segmented-control">
                                    <label class="ep-v3-segmented-option">
                                        <input type="radio" name="drinkPartner" value="Never" ${userPartnerPreferances.drink == 'Never' ? 'checked' : ''}>
                                        <div class="ep-v3-chip-content">
                                            <span class="ep-v3-chip-icon never-icon"><i class="fa fa-ban"></i></span>
                                            <span class="ep-v3-chip-text">Never</span>
                                            <i class="fa fa-check-circle ep-v3-chip-check"></i>
                                        </div>
                                    </label>
                                    <label class="ep-v3-segmented-option">
                                        <input type="radio" name="drinkPartner" value="Social drinker" ${userPartnerPreferances.drink == 'Social drinker' || userPartnerPreferances.drink == 'Social Drinker' ? 'checked' : ''}>
                                        <div class="ep-v3-chip-content">
                                            <span class="ep-v3-chip-icon social-icon"><i class="fa fa-glass"></i></span>
                                            <span class="ep-v3-chip-text">Social drinker</span>
                                            <i class="fa fa-check-circle ep-v3-chip-check"></i>
                                        </div>
                                    </label>
                                    <label class="ep-v3-segmented-option">
                                        <input type="radio" name="drinkPartner" value="Regular drinker" ${userPartnerPreferances.drink == 'Regular drinker' || userPartnerPreferances.drink == 'Regular Drinker' ? 'checked' : ''}>
                                        <div class="ep-v3-chip-content">
                                            <span class="ep-v3-chip-icon social-icon"><i class="fa fa-glass"></i></span>
                                            <span class="ep-v3-chip-text">Regular drinker</span>
                                            <i class="fa fa-check-circle ep-v3-chip-check"></i>
                                        </div>
                                    </label>
                                    <label class="ep-v3-segmented-option">
                                        <input type="radio" name="drinkPartner" value="No Preference" ${userPartnerPreferances.drink == 'No Preference' || empty userPartnerPreferances.drink ? 'checked' : ''}>
                                        <div class="ep-v3-chip-content">
                                            <span class="ep-v3-chip-icon nopref-icon"><i class="fa fa-globe"></i></span>
                                            <span class="ep-v3-chip-text">No Preference</span>
                                            <i class="fa fa-check-circle ep-v3-chip-check"></i>
                                        </div>
                                    </label>
                                </div>
                            </div>

                            <!-- SMOKING HABITS -->
                            <div class="ep-v3-lifestyle-group">
                                <label class="ep-v3-lifestyle-label">
                                    <i class="fa fa-ban label-icon"></i> Smoking Habits
                                </label>
                                <div class="ep-v3-segmented-control">
                                    <label class="ep-v3-segmented-option">
                                        <input type="radio" name="smokePartner" value="Non-smoker" ${userPartnerPreferances.smoke == 'Non-smoker' || userPartnerPreferances.smoke == 'Non Smoker' ? 'checked' : ''}>
                                        <div class="ep-v3-chip-content">
                                            <span class="ep-v3-chip-icon never-icon"><i class="fa fa-shield"></i></span>
                                            <span class="ep-v3-chip-text">Non-smoker</span>
                                            <i class="fa fa-check-circle ep-v3-chip-check"></i>
                                        </div>
                                    </label>
                                    <label class="ep-v3-segmented-option">
                                        <input type="radio" name="smokePartner" value="Social smoker" ${userPartnerPreferances.smoke == 'Social smoker' || userPartnerPreferances.smoke == 'Social Smoker' ? 'checked' : ''}>
                                        <div class="ep-v3-chip-content">
                                            <span class="ep-v3-chip-icon social-icon"><i class="fa fa-fire"></i></span>
                                            <span class="ep-v3-chip-text">Social smoker</span>
                                            <i class="fa fa-check-circle ep-v3-chip-check"></i>
                                        </div>
                                    </label>
                                    <label class="ep-v3-segmented-option">
                                        <input type="radio" name="smokePartner" value="Chain smoker" ${userPartnerPreferances.smoke == 'Chain smoker' || userPartnerPreferances.smoke == 'Chain Smoker' ? 'checked' : ''}>
                                        <div class="ep-v3-chip-content">
                                            <span class="ep-v3-chip-icon social-icon"><i class="fa fa-fire"></i></span>
                                            <span class="ep-v3-chip-text">Chain smoker</span>
                                            <i class="fa fa-check-circle ep-v3-chip-check"></i>
                                        </div>
                                    </label>
                                    <label class="ep-v3-segmented-option">
                                        <input type="radio" name="smokePartner" value="No Preference" ${userPartnerPreferances.smoke == 'No Preference' || empty userPartnerPreferances.smoke ? 'checked' : ''}>
                                        <div class="ep-v3-chip-content">
                                            <span class="ep-v3-chip-icon nopref-icon"><i class="fa fa-globe"></i></span>
                                            <span class="ep-v3-chip-text">No Preference</span>
                                            <i class="fa fa-check-circle ep-v3-chip-check"></i>
                                        </div>
                                    </label>
                                </div>
                            </div>

                            <!-- OTHER PREFERENCES -->
                            <div class="ep-v3-lifestyle-group">
                                <label class="ep-v3-lifestyle-label">
                                    <i class="fa fa-star label-icon"></i> Other Preferences & Compatibility
                                </label>
                                <div class="ep-v3-toggles-container">
                                    <label class="ep-v3-toggle-card">
                                        <div class="ep-v3-toggle-info">
                                            <div class="ep-v3-toggle-icon"><i class="fa fa-map-marker"></i></div>
                                            <div>
                                                <div class="ep-v3-toggle-title">Willing to Relocate</div>
                                                <div class="ep-v3-toggle-desc">Open to moving location</div>
                                            </div>
                                        </div>
                                        <div class="ep-v3-switch">
                                            <input type="checkbox" id="relocatePartner" name="relocatePartner" value="Yes" ${userPartnerPreferances.willingToRelocate == 'Yes' ? 'checked' : ''}>
                                            <span class="ep-v3-slider"></span>
                                        </div>
                                    </label>

                                    <label class="ep-v3-toggle-card">
                                        <div class="ep-v3-toggle-info">
                                            <div class="ep-v3-toggle-icon"><i class="fa fa-plane"></i></div>
                                            <div>
                                                <div class="ep-v3-toggle-title">Willing to Settle Abroad</div>
                                                <div class="ep-v3-toggle-desc">Open to international relocation</div>
                                            </div>
                                        </div>
                                        <div class="ep-v3-switch">
                                            <input type="checkbox" id="settleAbroadPartner" name="settleAbroadPartner" value="Yes" ${userPartnerPreferances.willingToSettleAbroad == 'Yes' ? 'checked' : ''}>
                                            <span class="ep-v3-slider"></span>
                                        </div>
                                    </label>

                                    <label class="ep-v3-toggle-card">
                                        <div class="ep-v3-toggle-info">
                                            <div class="ep-v3-toggle-icon"><i class="fa fa-magic"></i></div>
                                            <div>
                                                <div class="ep-v3-toggle-title">Horoscope Matching</div>
                                                <div class="ep-v3-toggle-desc">Require Kundali match</div>
                                            </div>
                                        </div>
                                        <div class="ep-v3-switch">
                                            <input type="checkbox" id="horoscopePartner" name="horoscopePartner" value="Yes" ${userPartnerPreferances.horoscopeMatching == 'Yes' || empty userPartnerPreferances.horoscopeMatching ? 'checked' : ''}>
                                            <span class="ep-v3-slider"></span>
                                        </div>
                                    </label>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- ACTION BAR -->
                    <div class="ep-v3-action-bar">
                        <button type="button" class="ep-v3-btn-outline" onclick="showStep(2);">Back</button>
                        <input type="button" id="btnSubmitPartnerPreferance" class="ep-v3-btn-primary" value="Save & Finish >" onclick="updatePartner();">
                    </div>

                    <!-- TIP BANNER -->
                    <div class="ep-v3-tips-box" style="margin-top:20px;">
                        <i class="fa fa-lightbulb-o ep-v3-tips-icon"></i>
                        <div class="ep-v3-tips-text">
                            <strong>Tip:</strong> The more accurate your preferences, the better your matches will be.
                        </div>
                    </div>
                </form>
            </div>
        </div>

        <!-- TRUST BADGES BAR -->
        <div class="ep-v3-trust-bar">
            <div class="ep-v3-trust-item">
                <div class="ep-v3-trust-icon"><i class="fa fa-shield"></i></div>
                <div>
                    <div class="ep-v3-trust-title">100% Verified</div>
                    <div class="ep-v3-trust-sub">Trusted Profiles</div>
                </div>
            </div>
            <div class="ep-v3-trust-item">
                <div class="ep-v3-trust-icon"><i class="fa fa-lock"></i></div>
                <div>
                    <div class="ep-v3-trust-title">Privacy Protected</div>
                    <div class="ep-v3-trust-sub">Your data is safe</div>
                </div>
            </div>
            <div class="ep-v3-trust-item">
                <div class="ep-v3-trust-icon"><i class="fa fa-users"></i></div>
                <div>
                    <div class="ep-v3-trust-title">Family Trusted</div>
                    <div class="ep-v3-trust-sub">By thousands of families</div>
                </div>
            </div>
            <div class="ep-v3-trust-item">
                <div class="ep-v3-trust-icon"><i class="fa fa-headphones"></i></div>
                <div>
                    <div class="ep-v3-trust-title">24x7 Support</div>
                    <div class="ep-v3-trust-sub">We are here to help</div>
                </div>
            </div>
        </div>

        <!-- FOOTER TEXT -->
        <div class="ep-v3-footer-text">
            <span>&copy; 2025 Snehbandhan. All rights reserved.</span>
            <span>Made with <i class="fa fa-heart" style="color:var(--primary);"></i> for Marathi Families</span>
        </div>

    </main>
</div>

<!-- MODALS FOR IMAGE CROPPING & UPLOAD -->
<div id="myModal" class="modal fade" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body model-images"></div>
        </div>
    </div>
</div>

<div id="secondModal" class="modal fade" role="dialog">
    <div class="modal-dialog modal-sm">
        <div class="modal-content text-center" style="padding: 20px;">
            <i class="fa fa-spinner fa-spin fa-2x" style="color:var(--primary);"></i>
            <p style="margin-top: 10px; font-weight:600;">Uploading images...</p>
        </div>
    </div>
</div>

<script>
    $(document).ready(function() {
        showStep(2); // Default to Step 2 (Personal Information)

        // Pre-select saved numeric Height and Weight values accurately
        function selectClosestNumericOption(selectId, rawVal) {
            if (!rawVal || rawVal === 'null') return;
            var numVal = parseFloat(rawVal);
            if (isNaN(numVal) || numVal <= 0) return;
            // Convert legacy feet.inches (< 10) to cm if needed
            if (numVal < 10) {
                var ft = Math.floor(numVal);
                var inch = Math.round((numVal - ft) * 10);
                numVal = (ft * 12 + inch) * 2.54;
            }
            var bestVal = null;
            var minDiff = 9999;
            $('#' + selectId + ' option').each(function() {
                var optNum = parseFloat($(this).val());
                if (!isNaN(optNum) && optNum > 0) {
                    var diff = Math.abs(optNum - numVal);
                    if (diff < minDiff) {
                        minDiff = diff;
                        bestVal = $(this).val();
                    }
                }
            });
            if (bestVal !== null && minDiff <= 2.0) {
                $('#' + selectId).val(bestVal);
            }
        }

        selectClosestNumericOption('heightSelect', '${modelDetails.height}');
        selectClosestNumericOption('weightSelect', '${modelDetails.weight}');
        selectClosestNumericOption('heightSelectPartnerFirst', '${userPartnerPreferances.heightFrom}');
        selectClosestNumericOption('heightSelectPartnerSecond', '${userPartnerPreferances.heightTo}');
    });
</script>

</body>
</html>
