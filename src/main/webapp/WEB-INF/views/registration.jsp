<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <title>SnehBandhan - Create Your Account</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    
    <!-- STYLESHEETS -->
    <link rel="stylesheet" href="resources/Template/css/bootstrap.min.css">
    <link rel="stylesheet" href="resources/Template/css/font-awesome.min.css">
    <link rel="stylesheet" href="resources/Registration/edit_profile_v3.css">
    <link rel="stylesheet" href="resources/customer/jquery-confirm.css" media="screen">

    <!-- SCRIPTS -->
    <script src="${pageContext.request.contextPath}/resources/Template/js/jquery-min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/Template/js/tether.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/Template/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/EducationOccupation/EducationOccupation.js" charset="utf-8"></script>
    <script src="resources/customer/jquery-confirm.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/resources/plugins/jQuery/jquery-ui.js"></script>

    <style>
        #ui-datepicker-div { display: none; }
        .ui-datepicker-month, .ui-datepicker-year { font-size:14px; width: 80px; height: 32px; border-radius: 6px; }
        .alertClose { outline: none; }
        .alert { border-radius: 10px; margin-bottom: 12px; }
    </style>
</head>
<body class="ep-v3-body">

<!-- TOP HEADER -->
<header class="ep-v3-header">
    <a href="<c:url value='userHome'></c:url>">
        <img class="ep-v3-logo" src="resources/images/logo.png" alt="SnehBandhan Logo">
    </a>
    <div class="ep-v3-nav-right">
        <a href="login" class="ep-v3-btn-outline" style="padding: 6px 18px; font-size: 13px;"><i class="fa fa-sign-in"></i> Login</a>
        <a href="aboutus" class="ep-v3-user-name" style="text-decoration:none; color: var(--text-gray);">About Us</a>
        <a href="contactus" class="ep-v3-user-name" style="text-decoration:none; color: var(--text-gray);">Contact Us</a>
    </div>
</header>

<!-- LOADING & SUCCESS MODALS -->
<div id="secondModal" class="modal fade" data-backdrop="static" data-keyboard="false" style="margin-top: 15%;">
    <div class="modal-dialog modal-sm">
        <div class="modal-content" style="background: rgba(0,0,0,0.85); border-radius: 16px; text-align: center; padding: 24px; color: white;">
            <img src="resources/images/spinner.gif" style="height:80px; width:80px; margin: 0 auto 12px;">
            <div style="font-size:15px; font-weight:700;">Creating your profile...</div>
            <div style="font-size:12px; color:#D1D5DB; margin-top:4px;">Please do not refresh page</div>
        </div>
    </div>
</div>

<div id="regmodel" class="modal fade" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content" style="border-radius: 16px; padding: 24px; text-align: center;">
            <i class="fa fa-check-circle" style="font-size: 48px; color: #10B981; margin-bottom: 12px;"></i>
            <h3 style="font-size: 20px; font-weight: 700; color: #111827;">Registration Successful!</h3>
            <p style="color: #6B7280; font-size: 13px;">Your profile has been created successfully.</p>
        </div>
    </div>
</div>

<!-- MAIN TWO-COLUMN LAYOUT -->
<div class="ep-v3-wrapper">

    <!-- LEFT SIDEBAR -->
    <aside class="ep-v3-sidebar">
        <!-- USER PROFILE CARD -->
        <div class="ep-v3-profile-card">
            <img class="ep-v3-profile-img" src="resources/UserImages/userImage.png" alt="User Avatar">
            <h3 class="ep-v3-profile-name">Create Your Account</h3>
            <p class="ep-v3-profile-id">Join 100% Verified Profiles</p>
            <div class="ep-v3-progress-wrapper">
                <div class="ep-v3-progress-header">
                    <span>Profile Setup</span>
                    <span class="ep-v3-progress-val" id="progressText">50% Completed</span>
                </div>
                <div class="ep-v3-progress-bar">
                    <div class="ep-v3-progress-fill" id="progressFill" style="width: 50%;"></div>
                </div>
            </div>
        </div>

        <!-- SIDEBAR NAVIGATION MENU -->
        <div class="ep-v3-nav-card">
            <div class="ep-v3-menu-group">
                <div class="ep-v3-menu-title">Registration Steps</div>
                <div class="ep-v3-menu-item active" id="menu-step1" onclick="MoveToStep2();">
                    <i class="fa fa-user ep-v3-menu-icon"></i> 1. Personal Details
                </div>
                <div class="ep-v3-menu-item" id="menu-step2" onclick="MoveToStep3();">
                    <i class="fa fa-heart ep-v3-menu-icon"></i> 2. Partner Preferences
                </div>
            </div>
        </div>

        <!-- NEED HELP CARD -->
        <div class="ep-v3-help-card">
            <div class="ep-v3-help-icon"><i class="fa fa-headphones"></i></div>
            <div class="ep-v3-help-text">
                <strong>Need Assistance?</strong>
                <span>Call support at +91-XXXXXXXXXX</span>
            </div>
        </div>
    </aside>

    <!-- RIGHT MAIN CONTENT -->
    <main>
        <!-- STEPPER BAR -->
        <div class="ep-v3-stepper-card">
            <div class="ep-v3-step-item active" id="step1-nav" onclick="MoveToStep2();">
                <div class="ep-v3-step-num">1</div>
                <div class="ep-v3-step-info">
                    <span class="ep-v3-step-title">Personal Information</span>
                    <span class="ep-v3-step-sub">Basic info & lifestyle</span>
                </div>
            </div>

            <div class="ep-v3-step-divider" id="step-div-1"></div>

            <div class="ep-v3-step-item" id="step2-nav" onclick="MoveToStep3();">
                <div class="ep-v3-step-num">2</div>
                <div class="ep-v3-step-info">
                    <span class="ep-v3-step-title">Partner Preferences</span>
                    <span class="ep-v3-step-sub">Habits & expectations</span>
                </div>
            </div>
        </div>

        <!-- FORM ALERTS CONTAINER -->
        <div id="alertsContainer">
            <div class="alert alert-danger alert-dismissable" id="nameSelect" style="display:none;">
                <button type="button" class="close alertClose" data-dismiss="alert">&times;</button>
                <strong>Error: </strong>Please Enter Name.
            </div>
            <div class="alert alert-danger alert-dismissable" id="emailselect" style="display:none;">
                <button type="button" class="close alertClose" data-dismiss="alert">&times;</button>
                <strong>Error: </strong>Please Enter Valid Email Id.
            </div> 
            <div class="alert alert-danger alert-dismissable" id="mobilenumselect" style="display:none;">
                <button type="button" class="close alertClose" data-dismiss="alert">&times;</button>
                <strong>Error: </strong>Please Enter Valid Mobile Number.
            </div>  
            <div class="alert alert-danger alert-dismissable" id="DivMaritalselect" style="display:none;">
                <button type="button" class="close alertClose" data-dismiss="alert">&times;</button>
                <strong>Error: </strong>Please select Marital Status.
            </div>
            <div class="alert alert-danger alert-dismissable" id="DivPhysicalselect" style="display:none;">
                <button type="button" class="close alertClose" data-dismiss="alert">&times;</button>
                <strong>Error: </strong>Please select Physical Status.
            </div>
            <div class="alert alert-danger alert-dismissable" id="DivHeightselect" style="display:none;">
                <button type="button" class="close alertClose" data-dismiss="alert">&times;</button>
                <strong>Error: </strong>Please select Height.
            </div>
            <div class="alert alert-danger alert-dismissable" id="DivWeightselect" style="display:none;">
                <button type="button" class="close alertClose" data-dismiss="alert">&times;</button>
                <strong>Error: </strong>Please select Weight.
            </div>
            <div class="alert alert-danger alert-dismissable" id="DivCountryselect" style="display:none;">
                <button type="button" class="close alertClose" data-dismiss="alert">&times;</button>
                <strong>Error: </strong>Please select Country.
            </div>
            <div class="alert alert-danger alert-dismissable" id="DivStateselect" style="display:none;">
                <button type="button" class="close alertClose" data-dismiss="alert">&times;</button>
                <strong>Error: </strong>Please select State.
            </div>
            <div class="alert alert-danger alert-dismissable" id="DivDistrictselect" style="display:none;">
                <button type="button" class="close alertClose" data-dismiss="alert">&times;</button>
                <strong>Error: </strong>Please select District.
            </div>
            <div class="alert alert-danger alert-dismissable" id="DivSub-Districtselect" style="display:none;">
                <button type="button" class="close alertClose" data-dismiss="alert">&times;</button>
                <strong>Error: </strong>Please select Sub-District.
            </div>
            <div class="alert alert-danger alert-dismissable" id="DivReligionselect" style="display:none;">
                <button type="button" class="close alertClose" data-dismiss="alert">&times;</button>
                <strong>Error: </strong>Please select Religion.
            </div>
            <div class="alert alert-danger alert-dismissable" id="DivCasteselect" style="display:none;">
                <button type="button" class="close alertClose" data-dismiss="alert">&times;</button>
                <strong>Error: </strong>Please select Caste.
            </div>
            <div class="alert alert-danger alert-dismissable" id="DivEducationCategoryselect" style="display:none;">
                <button type="button" class="close alertClose" data-dismiss="alert">&times;</button>
                <strong>Error: </strong>Please select Education Category.
            </div>
            <div class="alert alert-danger alert-dismissable" id="DivEducationselect" style="display:none;">
                <button type="button" class="close alertClose" data-dismiss="alert">&times;</button>
                <strong>Error: </strong>Please select Education.
            </div>
            <div class="alert alert-danger alert-dismissable" id="DivOccupationCategoryselect" style="display:none;">
                <button type="button" class="close alertClose" data-dismiss="alert">&times;</button>
                <strong>Error: </strong>Please select Occupation Category.
            </div>
            <div class="alert alert-danger alert-dismissable" id="DivOccupationselect" style="display:none;">
                <button type="button" class="close alertClose" data-dismiss="alert">&times;</button>
                <strong>Error: </strong>Please select Occupation.
            </div>

            <!-- STEP 2 PARTNER ALERTS -->
            <div class="alert alert-danger alert-dismissable" id="DivMaritalPartnerselect" style="display:none;">
                <button type="button" class="close alertClose" data-dismiss="alert">&times;</button>
                <strong>Error: </strong>Please select Partner Marital Status.
            </div>
            <div class="alert alert-danger alert-dismissable" id="DivPhysicalPartnerselect" style="display:none;">
                <button type="button" class="close alertClose" data-dismiss="alert">&times;</button>
                <strong>Error: </strong>Please select Partner Physical Status.
            </div>
            <div class="alert alert-danger alert-dismissable" id="DivHeightPartnerselect" style="display:none;">
                <button type="button" class="close alertClose" data-dismiss="alert">&times;</button>
                <strong>Error: </strong>Please select Partner Height Range.
            </div>
            <div class="alert alert-danger alert-dismissable" id="DivCountryPartnerselect" style="display:none;">
                <button type="button" class="close alertClose" data-dismiss="alert">&times;</button>
                <strong>Error: </strong>Please select Partner Country.
            </div>
            <div class="alert alert-danger alert-dismissable" id="DivStatePartnerselect" style="display:none;">
                <button type="button" class="close alertClose" data-dismiss="alert">&times;</button>
                <strong>Error: </strong>Please select Partner State.
            </div>
            <div class="alert alert-danger alert-dismissable" id="DivDistrictPartnerselect" style="display:none;">
                <button type="button" class="close alertClose" data-dismiss="alert">&times;</button>
                <strong>Error: </strong>Please select Partner District.
            </div>
            <div class="alert alert-danger alert-dismissable" id="DivReligionPartnerselect" style="display:none;">
                <button type="button" class="close alertClose" data-dismiss="alert">&times;</button>
                <strong>Error: </strong>Please select Partner Religion.
            </div>
            <div class="alert alert-danger alert-dismissable" id="DivCastePartnerselect" style="display:none;">
                <button type="button" class="close alertClose" data-dismiss="alert">&times;</button>
                <strong>Error: </strong>Please select Partner Caste.
            </div>
            <div class="alert alert-danger alert-dismissable" id="DivEducationPartnerselect" style="display:none;">
                <button type="button" class="close alertClose" data-dismiss="alert">&times;</button>
                <strong>Error: </strong>Please select Partner Education Category.
            </div>
            <div class="alert alert-danger alert-dismissable" id="DivOccupationPartnerselect" style="display:none;">
                <button type="button" class="close alertClose" data-dismiss="alert">&times;</button>
                <strong>Error: </strong>Please select Partner Occupation Category.
            </div>
            <div class="alert alert-danger alert-dismissable" id="kundali_file_alert" style="display:none;">
                <button type="button" class="close alertClose" data-dismiss="alert">&times;</button>
                <strong>Error: </strong>Please Select Kundali File.
            </div>
            <div class="alert alert-success alert-dismissable" id="successStep2" style="display:none;">
                <button type="button" class="close alertClose" data-dismiss="alert">&times;</button>
                <strong>Success: </strong>Step One Completed.
            </div>
            <div class="alert alert-success alert-dismissable" id="regSuccess" style="display:none;">
                <button type="button" class="close alertClose" data-dismiss="alert">&times;</button>
                <strong>Message: </strong>Registration Successful.
            </div>
        </div>

        <!-- STEP 1: PERSONAL INFORMATION PANE -->
        <div id="DivStep2">
            <div class="ep-v3-main-card">
                <h2 class="ep-v3-card-title">Personal Details & Lifestyle</h2>
                <p class="ep-v3-card-sub">Please fill in your accurate information to help us recommend suitable profiles.</p>

                <form class="form-horizontal" id="companyForm" method="post">
                    <!-- SECTION 1: BASIC INFORMATION -->
                    <div class="ep-v3-section">
                        <div class="ep-v3-sec-header">
                            <i class="fa fa-user-o ep-v3-sec-icon"></i> Basic Information
                        </div>
                        <div class="ep-v3-grid">
                            <div class="ep-v3-field ep-v3-col-2">
                                <label class="ep-v3-label">Profile Created For <span class="ep-v3-req">*</span></label>
                                <div class="ep-v3-segmented-control" style="grid-template-columns: repeat(4, 1fr);">
                                    <label class="ep-v3-segmented-option">
                                        <input type="radio" id="pcf_self" name="ProfileCreatedFor" value="Self" checked>
                                        <div class="ep-v3-chip-content"><span class="ep-v3-chip-text">Self</span></div>
                                    </label>
                                    <label class="ep-v3-segmented-option">
                                        <input type="radio" id="pcf_son" name="ProfileCreatedFor" value="Son / Daughter">
                                        <div class="ep-v3-chip-content"><span class="ep-v3-chip-text">Son/Daughter</span></div>
                                    </label>
                                    <label class="ep-v3-segmented-option">
                                        <input type="radio" id="pcf_brother" name="ProfileCreatedFor" value="Brother / Sister">
                                        <div class="ep-v3-chip-content"><span class="ep-v3-chip-text">Sibling</span></div>
                                    </label>
                                    <label class="ep-v3-segmented-option">
                                        <input type="radio" id="pcf_friend" name="ProfileCreatedFor" value="Relative / Friend">
                                        <div class="ep-v3-chip-content"><span class="ep-v3-chip-text">Relative/Friend</span></div>
                                    </label>
                                </div>
                            </div>

                            <div class="ep-v3-field ep-v3-col-1">
                                <label class="ep-v3-label">Full Name <span class="ep-v3-req">*</span></label>
                                <input type="text" id="firstName" name="firstName" class="ep-v3-input" value="${fullname}" placeholder="Enter full name">
                            </div>

                            <div class="ep-v3-field ep-v3-col-1">
                                <label class="ep-v3-label">Gender <span class="ep-v3-req">*</span></label>
                                <div class="ep-v3-radio-group">
                                    <label class="ep-v3-radio"><input type="radio" id="gender_male" name="gender" value="Male"> Male</label>
                                    <label class="ep-v3-radio"><input type="radio" id="gender_female" name="gender" value="Female" checked> Female</label>
                                </div>
                            </div>

                            <div class="ep-v3-field ep-v3-col-1">
                                <label class="ep-v3-label">Email Address <span class="ep-v3-req">*</span></label>
                                <input type="text" id="email" class="ep-v3-input" value="${email}" placeholder="Enter email">
                                <input type="hidden" value="${hiddenemail}" name="hiddenemail" id="hiddenemail">
                            </div>

                            <div class="ep-v3-field ep-v3-col-1">
                                <label class="ep-v3-label">Mobile Number <span class="ep-v3-req">*</span></label>
                                <input type="text" id="mobile" name="mobile" class="ep-v3-input" value="${mobile}" placeholder="10-digit mobile number" maxlength="10">
                            </div>

                            <div class="ep-v3-field ep-v3-col-1">
                                <label class="ep-v3-label">Date of Birth <span class="ep-v3-req">*</span></label>
                                <input type="text" id="dob" class="ep-v3-input dob" value="${dob}" placeholder="dd/mm/yyyy" readonly="readonly">
                            </div>

                            <div class="ep-v3-field ep-v3-col-1">
                                <label class="ep-v3-label">Username <span class="ep-v3-req">*</span></label>
                                <input type="text" id="userName" name="userName" class="ep-v3-input" value="${userName}" placeholder="Choose a username">
                            </div>

                            <div class="ep-v3-field ep-v3-col-1">
                                <label class="ep-v3-label">Password <span class="ep-v3-req">*</span></label>
                                <input type="password" id="password" name="password" class="ep-v3-input" value="${password}" placeholder="Create password">
                            </div>

                            <div class="ep-v3-field ep-v3-col-4">
                                <label class="ep-v3-label">About Me</label>
                                <textarea id="aboutMe" class="ep-v3-textarea" rows="3" placeholder="Tell us about yourself...">I am a good person looking for a partner.</textarea>
                            </div>
                        </div>
                    </div>

                    <!-- SECTION 2: PHYSICAL ATTRIBUTES -->
                    <div class="ep-v3-section">
                        <div class="ep-v3-sec-header">
                            <i class="fa fa-male ep-v3-sec-icon"></i> Physical Attributes
                        </div>
                        <div class="ep-v3-grid">
                            <div class="ep-v3-field ep-v3-col-1">
                                <label class="ep-v3-label">Marital Status <span class="ep-v3-req">*</span></label>
                                <select id="materialStatus" name="materialStatus" class="ep-v3-select">
                                    <option value="0">Select marital status</option>
                                    <option value="Single" selected>Single</option>
                                    <option value="Married">Married</option>
                                    <option value="Divorced">Divorced</option>
                                    <option value="Widowed">Widowed</option>
                                </select>
                            </div>

                            <div class="ep-v3-field ep-v3-col-1">
                                <label class="ep-v3-label">Physical Status <span class="ep-v3-req">*</span></label>
                                <select id="physicalStatus" name="physicalStatus" class="ep-v3-select">
                                    <option value="0">Select physical status</option>
                                    <option value="Normal" selected>Normal</option>
                                    <option value="Physically challenged">Physically challenged</option>
                                    <option value="Mentally Challenged">Mentally Challenged</option>
                                    <option value="Deaf">Deaf</option>
                                    <option value="Dumb">Dumb</option>
                                    <option value="Other Disability">Other Disability</option>
                                    <option value="Blind">Blind</option>
                                </select>
                            </div>

                            <div class="ep-v3-field ep-v3-col-1">
                                <label class="ep-v3-label">Height <span class="ep-v3-req">*</span></label>
                                <select id="heightSelect" name="heightSelect" class="ep-v3-select">
                                    <option value="0">Select height</option>
                                    <c:choose>
                                        <c:when test="${not empty height}">
                                            <c:forEach var="category" items="${height}">
                                                <option value="${category.getHeight_id()}">${category.getHeight_name()}</option>
                                            </c:forEach>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="121.92">4 feet</option>
                                            <option value="152.40">5 feet</option>
                                            <option value="167.64" selected>5 feet 6 inches</option>
                                            <option value="182.88">6 feet</option>
                                            <option value="213.36">7 feet</option>
                                        </c:otherwise>
                                    </c:choose>
                                </select>
                            </div>

                            <div class="ep-v3-field ep-v3-col-1">
                                <label class="ep-v3-label">Weight <span class="ep-v3-req">*</span></label>
                                <select id="weightSelect" name="weightSelect" class="ep-v3-select">
                                    <option value="0">Select weight</option>
                                    <c:choose>
                                        <c:when test="${not empty weight}">
                                            <c:forEach var="category" items="${weight}">
                                                <option value="${category.getWeight_id()}">${category.getWeight_name()}</option>
                                            </c:forEach>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="50">50 kg</option>
                                            <option value="60">60 kg</option>
                                            <option value="65" selected>65 kg</option>
                                            <option value="70">70 kg</option>
                                            <option value="80">80 kg</option>
                                        </c:otherwise>
                                    </c:choose>
                                </select>
                            </div>

                            <div class="ep-v3-field ep-v3-col-2">
                                <label class="ep-v3-label">Body Type</label>
                                <div class="ep-v3-radio-group">
                                    <label class="ep-v3-radio"><input type="radio" id="bt_slim" name="bodyType" value="Slim" checked> Slim</label>
                                    <label class="ep-v3-radio"><input type="radio" id="bt_athletic" name="bodyType" value="Athletic"> Athletic</label>
                                    <label class="ep-v3-radio"><input type="radio" id="bt_average" name="bodyType" value="Average"> Average</label>
                                    <label class="ep-v3-radio"><input type="radio" id="bt_heavy" name="bodyType" value="Heavy"> Heavy</label>
                                </div>
                            </div>

                            <div class="ep-v3-field ep-v3-col-2">
                                <label class="ep-v3-label">Complexion</label>
                                <div class="ep-v3-radio-group">
                                    <label class="ep-v3-radio"><input type="radio" id="cpx_very_fair" name="complexion" value="Very Fair"> Very Fair</label>
                                    <label class="ep-v3-radio"><input type="radio" id="cpx_fair" name="complexion" value="Fair"> Fair</label>
                                    <label class="ep-v3-radio"><input type="radio" id="cpx_wheatish" name="complexion" value="Wheatish" checked> Wheatish</label>
                                    <label class="ep-v3-radio"><input type="radio" id="cpx_wheatish_brown" name="complexion" value="Wheatish brown"> Wheatish Brown</label>
                                    <label class="ep-v3-radio"><input type="radio" id="cpx_dark" name="complexion" value="Dark"> Dark</label>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- SECTION 3: LOCATION & RELIGION -->
                    <div class="ep-v3-section">
                        <div class="ep-v3-sec-header">
                            <i class="fa fa-map-marker ep-v3-sec-icon"></i> Location & Religion
                        </div>
                        <div class="ep-v3-grid">
                            <div class="ep-v3-field ep-v3-col-1">
                                <label class="ep-v3-label">Country <span class="ep-v3-req">*</span></label>
                                <select id="countrySelect" name="countrySelect" class="ep-v3-select">
                                    <c:forEach var="country" items="${countries}">
                                        <option value="${country.country_id}">${country.country_Name}</option>
                                    </c:forEach>
                                </select>
                            </div>

                            <div class="ep-v3-field ep-v3-col-1">
                                <label class="ep-v3-label">State <span class="ep-v3-req">*</span></label>
                                <select id="stateSelect" name="stateSelect" class="ep-v3-select" onchange="loadCityAccordingToState(this.value);">
                                    <option value="0">Select State</option>
                                    <c:forEach var="stateCountry" items="${state_Countries}">
                                        <option value="${stateCountry.state_Country_id}">${stateCountry.state_Name}</option>
                                    </c:forEach>
                                </select>
                            </div>

                            <div class="ep-v3-field ep-v3-col-1">
                                <label class="ep-v3-label">City / District <span class="ep-v3-req">*</span></label>
                                <select id="cityStateSelect" name="cityStateSelect" class="ep-v3-select" onchange="loadsubDistrictAccordingToDistrict(this.value);">
                                    <option value="0">Select City/District</option>
                                </select>
                            </div>

                            <div class="ep-v3-field ep-v3-col-1">
                                <label class="ep-v3-label">Sub-District</label>
                                <select id="subDistSelect" name="subDistSelect" class="ep-v3-select">
                                    <option value="0">Select Sub-District</option>
                                </select>
                            </div>

                            <div class="ep-v3-field ep-v3-col-2">
                                <label class="ep-v3-label">Landmark</label>
                                <input type="text" id="ladmark" name="ladmark" class="ep-v3-input" placeholder="Enter landmark">
                            </div>

                            <div class="ep-v3-field ep-v3-col-1">
                                <label class="ep-v3-label">Religion <span class="ep-v3-req">*</span></label>
                                <select id="religionSelect" name="religionSelect" class="ep-v3-select" onchange="loadCasteAccordingToReligion(this.value);">
                                    <option value="0">Select Religion</option>
                                    <c:forEach var="religion" items="${religions}">
                                        <option value="${religion.religion_id}">${religion.religion_name}</option>
                                    </c:forEach>
                                </select>
                            </div>

                            <div class="ep-v3-field ep-v3-col-1">
                                <label class="ep-v3-label">Caste <span class="ep-v3-req">*</span></label>
                                <select id="casteSelect" name="casteSelect" class="ep-v3-select">
                                    <option value="0">Select Caste</option>
                                </select>
                            </div>
                        </div>
                    </div>

                    <!-- SECTION 4: PROFESSIONAL & FAMILY -->
                    <div class="ep-v3-section">
                        <div class="ep-v3-sec-header">
                            <i class="fa fa-briefcase ep-v3-sec-icon"></i> Education & Career
                        </div>
                        <div class="ep-v3-grid">
                            <div class="ep-v3-field ep-v3-col-1">
                                <label class="ep-v3-label">Education Category <span class="ep-v3-req">*</span></label>
                                <select id="educationalCategorySelect" name="educationalCategorySelect" class="ep-v3-select" onchange="loadEducationAccordingToEduCategory(this.value);">
                                    <option value="0">Educational Category</option>
                                    <c:forEach var="education" items="${educationCategories}">
                                        <option value="${education.edu_cat_id}">${education.edu_cat_name}</option>
                                    </c:forEach>
                                </select>
                            </div>

                            <div class="ep-v3-field ep-v3-col-1">
                                <label class="ep-v3-label">Education Degree <span class="ep-v3-req">*</span></label>
                                <select id="educationalSelect" name="educationalSelect" class="ep-v3-select" disabled>
                                    <option value="0">Select Education</option>
                                </select>
                            </div>

                            <div class="ep-v3-field ep-v3-col-1">
                                <label class="ep-v3-label">Occupation Category <span class="ep-v3-req">*</span></label>
                                <select id="occupationCategorySelect" name="occupationCategorySelect" class="ep-v3-select" onchange="loadOccupationAccordingToOccuCategory(this.value);">
                                    <option value="0">Occupation Category</option>
                                    <c:forEach var="occupation" items="${occupationCategories}">
                                        <option value="${occupation.occu_id}">${occupation.occu_name}</option>
                                    </c:forEach>
                                </select>
                            </div>

                            <div class="ep-v3-field ep-v3-col-1">
                                <label class="ep-v3-label">Occupation <span class="ep-v3-req">*</span></label>
                                <select id="occupationSelect" name="occupationSelect" class="ep-v3-select">
                                    <option value="0">Select Occupation</option>
                                </select>
                            </div>

                            <div class="ep-v3-field ep-v3-col-2">
                                <label class="ep-v3-label">Employed In</label>
                                <div class="ep-v3-radio-group">
                                    <label class="ep-v3-radio"><input id="emp_gov" name="employedIn" value="Government" type="radio"> Government</label>
                                    <label class="ep-v3-radio"><input id="emp_def" name="employedIn" value="Defence" type="radio" checked> Defence</label>
                                    <label class="ep-v3-radio"><input id="emp_pvt" name="employedIn" value="Private" type="radio"> Private</label>
                                    <label class="ep-v3-radio"><input id="emp_bus" name="employedIn" value="Business" type="radio"> Business</label>
                                </div>
                            </div>

                            <div class="ep-v3-field ep-v3-col-1">
                                <label class="ep-v3-label">Income Frequency</label>
                                <div class="ep-v3-radio-group">
                                    <label class="ep-v3-radio"><input name="income" id="inc_monthly" value="Monthly" type="radio" checked> Monthly</label>
                                    <label class="ep-v3-radio"><input name="income" id="inc_annual" value="Annual" type="radio"> Annual</label>
                                </div>
                            </div>

                            <div class="ep-v3-field ep-v3-col-1">
                                <label class="ep-v3-label">Income Amount (₹)</label>
                                <input type="number" name="incomeAmount" id="incomeAmount" class="ep-v3-input" value="0">
                            </div>
                        </div>
                    </div>

                    <!-- SECTION 5: PERSONAL LIFESTYLE & HABITS -->
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
                                        <input name="eating" id="diet_veg" value="vegetarian" type="radio" checked>
                                        <div class="ep-v3-chip-content">
                                            <span class="ep-v3-chip-icon veg-icon"><i class="fa fa-leaf"></i></span>
                                            <span class="ep-v3-chip-text">Vegetarian</span>
                                            <i class="fa fa-check-circle ep-v3-chip-check"></i>
                                        </div>
                                    </label>
                                    <label class="ep-v3-segmented-option">
                                        <input name="eating" id="diet_nonveg" value="Non vegetarian" type="radio">
                                        <div class="ep-v3-chip-content">
                                            <span class="ep-v3-chip-icon nonveg-icon"><i class="fa fa-cutlery"></i></span>
                                            <span class="ep-v3-chip-text">Non-Veg</span>
                                            <i class="fa fa-check-circle ep-v3-chip-check"></i>
                                        </div>
                                    </label>
                                    <label class="ep-v3-segmented-option">
                                        <input name="eating" id="diet_egg" value="Eggs Only" type="radio">
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
                                        <input name="drink" id="dr_never" value="Never" type="radio" checked>
                                        <div class="ep-v3-chip-content">
                                            <span class="ep-v3-chip-icon never-icon"><i class="fa fa-ban"></i></span>
                                            <span class="ep-v3-chip-text">Never</span>
                                            <i class="fa fa-check-circle ep-v3-chip-check"></i>
                                        </div>
                                    </label>
                                    <label class="ep-v3-segmented-option">
                                        <input name="drink" id="dr_soc" value="Social drinker" type="radio">
                                        <div class="ep-v3-chip-content">
                                            <span class="ep-v3-chip-icon social-icon"><i class="fa fa-glass"></i></span>
                                            <span class="ep-v3-chip-text">Socially</span>
                                            <i class="fa fa-check-circle ep-v3-chip-check"></i>
                                        </div>
                                    </label>
                                    <label class="ep-v3-segmented-option">
                                        <input name="drink" id="dr_reg" value="Regular drinker" type="radio">
                                        <div class="ep-v3-chip-content">
                                            <span class="ep-v3-chip-icon social-icon"><i class="fa fa-glass"></i></span>
                                            <span class="ep-v3-chip-text">Regularly</span>
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
                                        <input name="smoke" id="sm_never" value="Non-smoker" type="radio" checked>
                                        <div class="ep-v3-chip-content">
                                            <span class="ep-v3-chip-icon never-icon"><i class="fa fa-shield"></i></span>
                                            <span class="ep-v3-chip-text">Never</span>
                                            <i class="fa fa-check-circle ep-v3-chip-check"></i>
                                        </div>
                                    </label>
                                    <label class="ep-v3-segmented-option">
                                        <input name="smoke" id="sm_soc" value="Social smoker" type="radio">
                                        <div class="ep-v3-chip-content">
                                            <span class="ep-v3-chip-icon social-icon"><i class="fa fa-fire"></i></span>
                                            <span class="ep-v3-chip-text">Socially</span>
                                            <i class="fa fa-check-circle ep-v3-chip-check"></i>
                                        </div>
                                    </label>
                                    <label class="ep-v3-segmented-option">
                                        <input name="smoke" id="sm_chain" value="Chain smoker" type="radio">
                                        <div class="ep-v3-chip-content">
                                            <span class="ep-v3-chip-icon social-icon"><i class="fa fa-fire"></i></span>
                                            <span class="ep-v3-chip-text">Chain Smoker</span>
                                            <i class="fa fa-check-circle ep-v3-chip-check"></i>
                                        </div>
                                    </label>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- SECTION 6: DOCUMENTS & UPLOADS -->
                    <div class="ep-v3-section">
                        <div class="ep-v3-sec-header">
                            <i class="fa fa-file-image-o ep-v3-sec-icon"></i> Documents & Photo Upload
                        </div>
                        <div class="ep-v3-grid">
                            <div class="ep-v3-field ep-v3-col-2">
                                <label class="ep-v3-label">Profile Picture</label>
                                <input id="uploadImage" type="file" onchange="loadImageFile();" class="ep-v3-input" style="padding-top: 8px;">
                            </div>
                            <div class="ep-v3-field ep-v3-col-2">
                                <label class="ep-v3-label">Kundali Document</label>
                                <input type="file" class="ep-v3-input" accept=".gif,.jpg,.jpeg,.png" name="loaderKundali" id="loaderKundali" onchange="modelforkundali();" style="padding-top: 8px;">
                                <img id="blah1" width="80" height="80" hidden style="border-radius: 8px; margin-top: 8px;" />
                            </div>
                        </div>
                    </div>

                    <!-- ACTION BAR -->
                    <div class="ep-v3-action-bar">
                        <a href="login" class="ep-v3-btn-outline" style="text-decoration:none;">Cancel</a>
                        <button type="button" class="ep-v3-btn-primary" onclick="MoveToStep3(); updateStepUI(2);">
                            Save & Continue to Partner Preferences <i class="fa fa-angle-right"></i>
                        </button>
                    </div>
                </form>
            </div>
        </div>

        <!-- STEP 2: PARTNER PREFERENCES PANE -->
        <div id="DivStep3" style="display: none;">
            <div class="ep-v3-main-card">
                <h2 class="ep-v3-card-title">Partner Preferences</h2>
                <p class="ep-v3-card-sub">Tell us what you are looking for in your ideal life partner.</p>

                <form class="form-horizontal" id="companyForm" method="post">
                    <!-- SECTION 1: PARTNER PHYSICAL ATTRIBUTES -->
                    <div class="ep-v3-section">
                        <div class="ep-v3-sec-header">
                            <i class="fa fa-user ep-v3-sec-icon"></i> Partner Physical Attributes
                        </div>
                        <div class="ep-v3-grid">
                            <div class="ep-v3-field ep-v3-col-1">
                                <label class="ep-v3-label">Marital Status <span class="ep-v3-req">*</span></label>
                                <select id="materialStatusPartner" name="materialStatusPartner" class="ep-v3-select">
                                    <option value="0">Select Status</option>
                                    <option value="Single" selected>Single</option>
                                    <option value="Married">Married</option>
                                    <option value="Divorced">Divorced</option>
                                    <option value="Widowed">Widowed</option>
                                </select>
                            </div>

                            <div class="ep-v3-field ep-v3-col-1">
                                <label class="ep-v3-label">Physical Status <span class="ep-v3-req">*</span></label>
                                <select id="physicalStatusPartner" name="physicalStatus" class="ep-v3-select">
                                    <option value="0">Physical Status</option>
                                    <option value="Normal" selected>Normal</option>
                                    <option value="Physically challenged">Physically challenged</option>
                                    <option value="Mentally Challenged">Mentally Challenged</option>
                                    <option value="Deaf">Deaf</option>
                                    <option value="Dumb">Dumb</option>
                                    <option value="Other Disability">Other Disability</option>
                                    <option value="Blind">Blind</option>
                                </select>
                            </div>

                            <div class="ep-v3-field ep-v3-col-1">
                                <label class="ep-v3-label">Height Range (From)</label>
                                <select id="heightSelectPartnerFirst" name="heightSelectPartnerFirst" class="ep-v3-select">
                                    <option value="0">Select Height</option>
                                    <option value="121.92">4 feet</option>
                                    <option value="152.40" selected>5 feet</option>
                                    <option value="167.64">5 feet 6 inches</option>
                                    <option value="182.88">6 feet</option>
                                </select>
                            </div>

                            <div class="ep-v3-field ep-v3-col-1">
                                <label class="ep-v3-label">Height Range (To)</label>
                                <select id="heightSelectPartnerSecond" name="heightSelectPartnerSecond" class="ep-v3-select">
                                    <option value="0">Select Height</option>
                                    <option value="152.40">5 feet</option>
                                    <option value="167.64">5 feet 6 inches</option>
                                    <option value="182.88" selected>6 feet</option>
                                    <option value="213.36">7 feet</option>
                                </select>
                            </div>

                            <div class="ep-v3-field ep-v3-col-2">
                                <label class="ep-v3-label">Body Type</label>
                                <div class="ep-v3-radio-group">
                                    <label class="ep-v3-radio"><input name="bodyTypePartner" value="Slim" type="radio"> Slim</label>
                                    <label class="ep-v3-radio"><input name="bodyTypePartner" value="Athletic" type="radio" checked> Athletic</label>
                                    <label class="ep-v3-radio"><input name="bodyTypePartner" value="Average" type="radio"> Average</label>
                                    <label class="ep-v3-radio"><input name="bodyTypePartner" value="Heavy" type="radio"> Heavy</label>
                                </div>
                            </div>

                            <div class="ep-v3-field ep-v3-col-2">
                                <label class="ep-v3-label">Complexion</label>
                                <div class="ep-v3-radio-group">
                                    <label class="ep-v3-radio"><input name="complexionPartner" value="Very Fair" type="radio"> Very Fair</label>
                                    <label class="ep-v3-radio"><input name="complexionPartner" value="Fair" type="radio"> Fair</label>
                                    <label class="ep-v3-radio"><input name="complexionPartner" value="Wheatish" checked type="radio"> Wheatish</label>
                                    <label class="ep-v3-radio"><input name="complexionPartner" value="Wheatish brown" type="radio"> Wheatish Brown</label>
                                    <label class="ep-v3-radio"><input name="complexionPartner" value="Dark" type="radio"> Dark</label>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- SECTION 2: PARTNER LOCATION & RELIGION -->
                    <div class="ep-v3-section">
                        <div class="ep-v3-sec-header">
                            <i class="fa fa-globe ep-v3-sec-icon"></i> Partner Location & Religion
                        </div>
                        <div class="ep-v3-grid">
                            <div class="ep-v3-field ep-v3-col-1">
                                <label class="ep-v3-label">Country <span class="ep-v3-req">*</span></label>
                                <select id="countrySelectPartner" name="countrySelectPartner" class="ep-v3-select">
                                    <c:forEach var="country" items="${countries}">
                                        <option value="${country.country_id}">${country.country_Name}</option>
                                    </c:forEach>
                                </select>
                            </div>

                            <div class="ep-v3-field ep-v3-col-1">
                                <label class="ep-v3-label">State <span class="ep-v3-req">*</span></label>
                                <select id="stateSelectPartner" name="stateSelectPartner" class="ep-v3-select" onchange="loadCityAccordingToStatePartnerPreferance(this.value);">
                                    <option value="0">Select State</option>
                                    <c:forEach var="stateCountry" items="${state_Countries}">
                                        <option value="${stateCountry.state_Country_id}" ${stateCountry.state_Name == "Other" ? 'selected' : ''}>${stateCountry.state_Name}</option>
                                    </c:forEach>
                                </select>
                            </div>

                            <div class="ep-v3-field ep-v3-col-1">
                                <label class="ep-v3-label">City/District <span class="ep-v3-req">*</span></label>
                                <select id="cityStateSelectPartnerPreferance" name="cityStateSelectPartnerPreferance" class="ep-v3-select">
                                    <option value="0">Select City/District</option>
                                    <option value="708" selected>Other</option>
                                </select>
                            </div>

                            <div class="ep-v3-field ep-v3-col-1">
                                <label class="ep-v3-label">Religion <span class="ep-v3-req">*</span></label>
                                <select id="religionSelectPartner" name="religionSelectPartner" class="ep-v3-select" onchange="loadCasteAccordingToReligionPartner(this.value);">
                                    <option value="0">Select Religion</option>
                                    <c:forEach var="religion" items="${religions}">
                                        <option value="${religion.religion_id}" ${religion.religion_name == "Doesn't Matter" ? 'selected' : ''}>${religion.religion_name}</option>
                                    </c:forEach>
                                </select>
                            </div>

                            <div class="ep-v3-field ep-v3-col-1">
                                <label class="ep-v3-label">Caste <span class="ep-v3-req">*</span></label>
                                <select id="casteSelectPartner" name="casteSelectPartner" class="ep-v3-select">
                                    <option value="0">Caste</option>
                                    <option value="550" selected>Doesn't Matter</option>
                                </select>
                            </div>
                        </div>
                    </div>

                    <!-- SECTION 3: PARTNER CAREER & FAMILY -->
                    <div class="ep-v3-section">
                        <div class="ep-v3-sec-header">
                            <i class="fa fa-graduation-cap ep-v3-sec-icon"></i> Education, Career & Family
                        </div>
                        <div class="ep-v3-grid">
                            <div class="ep-v3-field ep-v3-col-2">
                                <label class="ep-v3-label">Education Categories</label>
                                <div style="display:grid; grid-template-columns: repeat(2, 1fr); gap: 6px; background:#F9FAFB; padding:12px; border-radius:10px; border:1px solid #E5E7EB; max-height:120px; overflow-y:auto;">
                                    <c:forEach var="education" items="${educationCategories}">
                                        <label style="font-size:12px; font-weight:500; display:flex; align-items:center; gap:6px; cursor:pointer;">
                                            <input type="checkbox" id="${education.edu_cat_id}" value="${education.edu_cat_id}" name="checkBoxEducationPartner" ${education.edu_cat_name == "Others" ? 'checked' : ''} style="accent-color:var(--primary);">
                                            ${education.edu_cat_name}
                                        </label>
                                    </c:forEach>
                                </div>
                            </div>

                            <div class="ep-v3-field ep-v3-col-2">
                                <label class="ep-v3-label">Occupation Categories</label>
                                <div style="display:grid; grid-template-columns: repeat(2, 1fr); gap: 6px; background:#F9FAFB; padding:12px; border-radius:10px; border:1px solid #E5E7EB; max-height:120px; overflow-y:auto;">
                                    <c:forEach var="occupation" items="${occupationCategories}">
                                        <label style="font-size:12px; font-weight:500; display:flex; align-items:center; gap:6px; cursor:pointer;">
                                            <input type="checkbox" id="${occupation.occu_id}" value="${occupation.occu_id}" name="checkBoxOccupationPartner" ${occupation.occu_name == "OTHERS" ? 'checked' : ''} style="accent-color:var(--primary);">
                                            ${occupation.occu_name}
                                        </label>
                                    </c:forEach>
                                </div>
                            </div>

                            <div class="ep-v3-field ep-v3-col-2">
                                <label class="ep-v3-label">Employed In Type</label>
                                <div class="ep-v3-radio-group">
                                    <label class="ep-v3-radio"><input id="employedInPartner" name="employedInPartner" value="Government" type="radio"> Government</label>
                                    <label class="ep-v3-radio"><input id="employedInPartner" name="employedInPartner" value="Defence" type="radio" checked> Defence</label>
                                    <label class="ep-v3-radio"><input name="employedInPartner" id="employedInPartner" value="Private" type="radio"> Private</label>
                                    <label class="ep-v3-radio"><input id="employedInPartner" value="Business" type="radio" name="employedInPartner"> Business</label>
                                </div>
                            </div>

                            <div class="ep-v3-field ep-v3-col-1">
                                <label class="ep-v3-label">Income Frequency</label>
                                <div class="ep-v3-radio-group">
                                    <label class="ep-v3-radio"><input name="incomePartner" value="Monthly" type="radio" checked> Monthly</label>
                                    <label class="ep-v3-radio"><input name="incomePartner" value="Annual" type="radio"> Annual</label>
                                </div>
                            </div>

                            <div class="ep-v3-field ep-v3-col-1">
                                <label class="ep-v3-label">Min Income Amount (₹)</label>
                                <input type="number" name="incomeAmountPartner" id="incomeAmountPartner" class="ep-v3-input" value="0">
                            </div>
                        </div>
                    </div>

                    <!-- SECTION 4: PREMIUM LIFESTYLE PREFERENCES REDESIGN -->
                    <div class="ep-v3-lifestyle-section">
                        <div class="ep-v3-sec-header">
                            <div class="ep-v3-sec-icon-wrapper">
                                <i class="fa fa-heart ep-v3-sec-icon"></i>
                            </div>
                            <div>
                                <div class="ep-v3-sec-title">Lifestyle Preferences & Expectations</div>
                                <div class="ep-v3-sec-subtitle">Set your partner lifestyle choices for high-compatibility matching</div>
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
                                        <input name="eatingPartner" value="vegetarian" type="radio" checked>
                                        <div class="ep-v3-chip-content">
                                            <span class="ep-v3-chip-icon veg-icon"><i class="fa fa-leaf"></i></span>
                                            <span class="ep-v3-chip-text">Vegetarian</span>
                                            <i class="fa fa-check-circle ep-v3-chip-check"></i>
                                        </div>
                                    </label>
                                    <label class="ep-v3-segmented-option">
                                        <input name="eatingPartner" value="Non vegetarian" type="radio">
                                        <div class="ep-v3-chip-content">
                                            <span class="ep-v3-chip-icon nonveg-icon"><i class="fa fa-cutlery"></i></span>
                                            <span class="ep-v3-chip-text">Non-Veg</span>
                                            <i class="fa fa-check-circle ep-v3-chip-check"></i>
                                        </div>
                                    </label>
                                    <label class="ep-v3-segmented-option">
                                        <input name="eatingPartner" value="Eggs Only" type="radio">
                                        <div class="ep-v3-chip-content">
                                            <span class="ep-v3-chip-icon nopref-icon"><i class="fa fa-globe"></i></span>
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
                                        <input name="drinkPartner" value="Never" type="radio" checked>
                                        <div class="ep-v3-chip-content">
                                            <span class="ep-v3-chip-icon never-icon"><i class="fa fa-ban"></i></span>
                                            <span class="ep-v3-chip-text">Never</span>
                                            <i class="fa fa-check-circle ep-v3-chip-check"></i>
                                        </div>
                                    </label>
                                    <label class="ep-v3-segmented-option">
                                        <input name="drinkPartner" value="Social drinker" type="radio">
                                        <div class="ep-v3-chip-content">
                                            <span class="ep-v3-chip-icon social-icon"><i class="fa fa-glass"></i></span>
                                            <span class="ep-v3-chip-text">Social Drinker</span>
                                            <i class="fa fa-check-circle ep-v3-chip-check"></i>
                                        </div>
                                    </label>
                                    <label class="ep-v3-segmented-option">
                                        <input name="drinkPartner" value="Regular drinker" type="radio">
                                        <div class="ep-v3-chip-content">
                                            <span class="ep-v3-chip-icon nopref-icon"><i class="fa fa-glass"></i></span>
                                            <span class="ep-v3-chip-text">Regular Drinker</span>
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
                                        <input name="smokePartner" value="Non-smoker" type="radio" checked>
                                        <div class="ep-v3-chip-content">
                                            <span class="ep-v3-chip-icon never-icon"><i class="fa fa-shield"></i></span>
                                            <span class="ep-v3-chip-text">Non Smoker</span>
                                            <i class="fa fa-check-circle ep-v3-chip-check"></i>
                                        </div>
                                    </label>
                                    <label class="ep-v3-segmented-option">
                                        <input name="smokePartner" value="Social smoker" type="radio">
                                        <div class="ep-v3-chip-content">
                                            <span class="ep-v3-chip-icon social-icon"><i class="fa fa-fire"></i></span>
                                            <span class="ep-v3-chip-text">Social Smoker</span>
                                            <i class="fa fa-check-circle ep-v3-chip-check"></i>
                                        </div>
                                    </label>
                                    <label class="ep-v3-segmented-option">
                                        <input name="smokePartner" value="Chain smoker" type="radio">
                                        <div class="ep-v3-chip-content">
                                            <span class="ep-v3-chip-icon nopref-icon"><i class="fa fa-fire"></i></span>
                                            <span class="ep-v3-chip-text">Chain Smoker</span>
                                            <i class="fa fa-check-circle ep-v3-chip-check"></i>
                                        </div>
                                    </label>
                                </div>
                            </div>

                            <!-- OTHER PREFERENCES TOGGLE CARDS -->
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
                                            <input type="checkbox" name="relocatePartner">
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
                                            <input type="checkbox" name="settleAbroadPartner">
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
                                            <input type="checkbox" name="horoscopePartner" checked>
                                            <span class="ep-v3-slider"></span>
                                        </div>
                                    </label>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- SECTION 5: ACCOUNT & KIT TYPE -->
                    <div class="ep-v3-section">
                        <div class="ep-v3-sec-header">
                            <i class="fa fa-id-card-o ep-v3-sec-icon"></i> Account Membership Type
                        </div>
                        <div class="ep-v3-grid">
                            <div class="ep-v3-field ep-v3-col-2">
                                <label class="ep-v3-label">Membership Choice</label>
                                <div class="ep-v3-radio-group">
                                    <label class="ep-v3-radio"><input type="radio" name="kitUser" id="kit" value="FreeKit" onchange="readOnlyKit();" checked> Free User</label>
                                    <label class="ep-v3-radio"><input type="radio" name="kitUser" id="kit" value="RegisteredKit" onchange="readWriteKit();"> Registered User Kit</label>
                                </div>
                            </div>
                            <div class="ep-v3-field ep-v3-col-1">
                                <label class="ep-v3-label">Kit ID</label>
                                <input class="ep-v3-input" type="tel" value="${kitId}" placeholder="Enter Kit Id" id="kitId" name="kitId" readonly="readonly">
                            </div>
                            <div class="ep-v3-field ep-v3-col-1">
                                <label class="ep-v3-label">Kit Password</label>
                                <input class="ep-v3-input" type="password" value="${kitPassword}" name="kitPassword" id="kitPassword" readonly="readonly" placeholder="Enter Kit Password">
                            </div>
                        </div>
                    </div>

                    <!-- ACTION BAR -->
                    <div class="ep-v3-action-bar">
                        <button type="button" class="ep-v3-btn-outline" onclick="MoveToStep2(); updateStepUI(1);">
                            <i class="fa fa-angle-left"></i> Back to Personal Details
                        </button>
                        <button type="button" class="ep-v3-btn-primary" onclick="SaveRegistrationDetails();">
                            Complete Registration <i class="fa fa-check"></i>
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </main>
</div>

<!-- TRUST BADGES BAR -->
<div style="max-width: 1240px; margin: 0 auto 40px; padding: 0 20px;">
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
                <div class="ep-v3-trust-title">Verified Matches</div>
                <div class="ep-v3-trust-sub">Genuine matchmaking</div>
            </div>
        </div>
        <div class="ep-v3-trust-item">
            <div class="ep-v3-trust-icon"><i class="fa fa-headphones"></i></div>
            <div>
                <div class="ep-v3-trust-title">24/7 Support</div>
                <div class="ep-v3-trust-sub">We are here to help</div>
            </div>
        </div>
    </div>

    <div class="ep-v3-footer-text">
        <span>Copyright &copy; 2026 <a href="login" style="color: var(--primary);">www.snehbandhan.org</a></span>
        <div>
            <a href="aboutus" style="color:var(--text-muted); margin-right:12px;">About Us</a>
            <a href="FAQs" style="color:var(--text-muted); margin-right:12px;">FAQs</a>
            <a href="TermAndCondition" style="color:var(--text-muted); margin-right:12px;">Terms</a>
            <a href="privacyPolicy" style="color:var(--text-muted);">Privacy</a>
        </div>
    </div>
</div>

<script>
    $("#fileSelect").hide();
    $("#nameSelect").hide();
    $("#emailselect").hide();
    $("#mobilenumselect").hide();
    $("#DivMaritalselect").hide();
    $("#DivPhysicalselect").hide();
    $("#DivHeightselect").hide();
    $("#DivWeightselect").hide();
    $("#DivCountryselect").hide();
    $("#DivStateselect").hide();
    $("#DivDistrictselect").hide();
    $("#DivSub-Districtselect").hide();
    $("#DivReligionselect").hide();
    $("#DivCasteselect").hide();
    $("#DivEducationCategoryselect").hide();
    $("#DivEducationselect").hide();
    $("#DivOccupationCategoryselect").hide();
    $("#DivOccupationselect").hide();
    $("#kundali_file_alert").hide();

    $("#successStep2").hide();
    $("#regSuccess").hide();
    $("#DivMaritalPartnerselect").hide();
    $("#DivPhysicalPartnerselect").hide();
    $("#DivHeightPartnerselect").hide();
    $("#DivCountryPartnerselect").hide();
    $("#DivStatePartnerselect").hide();
    $("#DivDistrictPartnerselect").hide();
    $("#DivReligionPartnerselect").hide();
    $("#DivCastePartnerselect").hide();
    $("#DivEducationPartnerselect").hide();
    $("#DivOccupationPartnerselect").hide();
    
    $("#DivStep3").hide();

    function updateStepUI(stepNum) {
        if (stepNum === 2) {
            $('#step1-nav').addClass('completed').removeClass('active');
            $('#step-div-1').addClass('active');
            $('#step2-nav').addClass('active').removeClass('completed');
            $('#menu-step1').removeClass('active');
            $('#menu-step2').addClass('active');
            $('#progressText').text('100% Completed');
            $('#progressFill').css('width', '100%');
        } else {
            $('#step1-nav').addClass('active').removeClass('completed');
            $('#step-div-1').removeClass('active');
            $('#step2-nav').removeClass('active completed');
            $('#menu-step1').addClass('active');
            $('#menu-step2').removeClass('active');
            $('#progressText').text('50% Completed');
            $('#progressFill').css('width', '50%');
        }
    }

    $("#dob").datepicker({
        dateFormat : 'dd/mm/yy',
        changeMonth : true,
        changeYear : true,
        yearRange : '1980:2099'
    });
</script>

<c:if test="${not empty kitUser}">
<script>
    $('input[name="kitUser"][value="${kitUser}"]').attr('checked', true);
</script>
</c:if>

</body>
</html>
