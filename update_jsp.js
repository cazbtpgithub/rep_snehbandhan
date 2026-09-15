const fs = require('fs');

const path = 'f:/Antigravity/Sneh_Bandhan/src/main/webapp/WEB-INF/views/registration.jsp';
let content = fs.readFileSync(path, 'utf8');

// Add CSS link
const cssLink = '<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/Registration/registration_v2.css">';
if (!content.includes('registration_v2.css')) {
    content = content.replace('</head>', `    ${cssLink}\n</head>`);
}

// Update body class
if (!content.includes('v2-registration-body')) {
    content = content.replace('<body class="loading">', '<body class="loading v2-registration-body">');
}

// We need to replace the entire start of DivStep2 up to right after Weight select.
const startIndex = content.indexOf('<div id="DivStep2">');
const searchStr = 'id="weightSelect"';
let endIndex = content.indexOf(searchStr, startIndex);

// Find the closing </select> and its parent divs. We want to stop before the next <div class="col-md-2"></div>
endIndex = content.indexOf('</select>', endIndex) + 9;
endIndex = content.indexOf('</div>', endIndex) + 6;
endIndex = content.indexOf('</div>', endIndex) + 6;

const newHtml = `
<div id="DivStep2" class="v2-form-container">
    <h1 class="v2-title">Create Your Profile</h1>
    <p class="v2-subtitle">Begin your journey to a timeless connection. Tell us about yourself to help us find your perfect match.</p>

    <div class="v2-stepper">
        <div class="v2-step active">
            <div class="v2-step-number">1</div>
            <div class="v2-step-label">Personal</div>
        </div>
        <div class="v2-step-line"></div>
        <div class="v2-step">
            <div class="v2-step-number">2</div>
            <div class="v2-step-label">Professional</div>
        </div>
        <div class="v2-step-line"></div>
        <div class="v2-step">
            <div class="v2-step-number">3</div>
            <div class="v2-step-label">Preferences</div>
        </div>
    </div>

    <!-- Hidden Alerts for JS -->
    <div class="alert alert-danger alert-dismissable" id="nameSelect" style="display:none;"><button type="button" class="close alertClose" data-dismiss="alert">&times;</button><strong>Error: </strong>Please Enter Name.</div>
    <div class="alert alert-danger alert-dismissable" id="emailselect" style="display:none;"><button type="button" class="close alertClose" data-dismiss="alert">&times;</button><strong>Error: </strong>Please Enter Valid Email Id.</div>
    <div class="alert alert-danger alert-dismissable" id="mobilenumselect" style="display:none;"><button type="button" class="close alertClose" data-dismiss="alert">&times;</button><strong>Error: </strong>Please Enter Valid Mobile Number.</div>
    <div class="alert alert-danger alert-dismissable" id="fileSelect" style="display:none;"><button type="button" class="close alertClose" data-dismiss="alert">&times;</button><strong>Error: </strong>Please Select File.</div>
    <div class="alert alert-danger alert-dismissable" id="DivMaritalselect" style="display:none;"><button type="button" class="close alertClose" data-dismiss="alert">&times;</button><strong>Error: </strong>Please select Marital Status.</div>
    <div class="alert alert-danger alert-dismissable" id="DivPhysicalselect" style="display:none;"><button type="button" class="close alertClose" data-dismiss="alert">&times;</button><strong>Error: </strong>Please select Physical Status.</div>
    <div class="alert alert-danger alert-dismissable" id="DivHeightselect" style="display:none;"><button type="button" class="close alertClose" data-dismiss="alert">&times;</button><strong>Error: </strong>Please select Height.</div>
    <div class="alert alert-danger alert-dismissable" id="DivWeightselect" style="display:none;"><button type="button" class="close alertClose" data-dismiss="alert">&times;</button><strong>Error: </strong>Please select Weight.</div>
    <div class="alert alert-danger alert-dismissable" id="DivCountryselect" style="display:none;"><button type="button" class="close alertClose" data-dismiss="alert">&times;</button><strong>Error: </strong>Please select Country.</div>

    <form class="form-horizontal" id="companyForm" method="post">
        
        <h2 class="v2-section-title"><i class="fa fa-user-o"></i> Personal Information</h2>
        
        <div class="v2-grid">
            <div class="v2-form-group v2-grid-full">
                <label>Profile Created For</label>
                <div class="v2-pill-group">
                    <input type="radio" id="pcf_self" name="ProfileCreatedFor" value="Self" checked>
                    <label for="pcf_self">Self</label>
                    <input type="radio" id="pcf_son" name="ProfileCreatedFor" value="Son / Daughter">
                    <label for="pcf_son">Son / Daughter</label>
                    <input type="radio" id="pcf_brother" name="ProfileCreatedFor" value="Brother / Sister">
                    <label for="pcf_brother">Brother / Sister</label>
                    <input type="radio" id="pcf_friend" name="ProfileCreatedFor" value="Relative / Friend">
                    <label for="pcf_friend">Relative / Friend</label>
                    
                    <input type="radio" id="pcf_parents" name="ProfileCreatedFor" value="Parents" style="display:none;">
                    <input type="radio" id="pcf_sibling" name="ProfileCreatedFor" value="Sibling" style="display:none;">
                    <input type="radio" id="pcf_relative" name="ProfileCreatedFor" value="Relative" style="display:none;">
                    <input type="radio" id="pcf_sister" name="ProfileCreatedFor" value="Sister" style="display:none;">
                    <input type="radio" id="pcf_friend_hidden" name="ProfileCreatedFor" value="Friend" style="display:none;">
                </div>
            </div>

            <div class="v2-form-group">
                <label>Full Name</label>
                <input type="text" id="firstName" name="firstName" class="v2-form-control" value="\${fullname}" placeholder="Enter your full name">
            </div>

            <div class="v2-form-group">
                <label>Email Address</label>
                <input type="text" id="email" class="v2-form-control" value="\${email}" placeholder="Enter your email address">
                <input type="hidden" value="\${hiddenemail}" name="hiddenemail" id="hiddenemail">
            </div>

            <div class="v2-form-group">
                <label>Mobile Number</label>
                <div class="v2-input-group">
                    <select class="v2-form-control"><option>+91</option></select>
                    <input type="text" id="mobile" name="mobile" class="v2-form-control" value="\${mobile}" placeholder="10-digit mobile number" maxlength="10">
                </div>
            </div>

            <div class="v2-form-group">
                <label>Date of Birth</label>
                <input type="text" id="dob" class="v2-form-control dob" value="\${dob}" placeholder="mm/dd/yyyy" readonly="readonly">
            </div>

            <div class="v2-form-group">
                <label>Gender</label>
                <div class="v2-radio-group">
                    <div class="v2-radio-item">
                        <input type="radio" id="gender_male" name="gender" value="Male">
                        <label for="gender_male">Male</label>
                    </div>
                    <div class="v2-radio-item">
                        <input type="radio" id="gender_female" name="gender" value="Female" checked>
                        <label for="gender_female">Female</label>
                    </div>
                </div>
            </div>

            <div class="v2-form-group">
                <label>Username</label>
                <input type="text" id="userName" name="userName" class="v2-form-control" value="\${userName}" placeholder="Choose a username">
            </div>

            <div class="v2-form-group">
                <label>Password</label>
                <div class="v2-password-wrapper">
                    <input type="password" id="password" name="password" class="v2-form-control" value="\${password}" placeholder="Create a strong password">
                    <i class="fa fa-eye"></i>
                </div>
            </div>
            
            <div class="v2-form-group v2-grid-full" style="display:none;">
                <label>Select File</label>
                <input id="uploadImage" type="file" onchange="loadImageFile();" />
            </div>
            <div class="v2-form-group v2-grid-full" style="display:none;">
                <label>About Me</label>
                <textarea id="aboutMe" class="v2-form-control">I am a good person looking for a partner.</textarea>
                <input type="radio" name="complexion" value="Fair" checked>
            </div>
        </div>

        <h2 class="v2-section-title" style="margin-top: 1rem;"><i class="fa fa-male"></i> Physical Attributes</h2>
        
        <div class="v2-grid">
            <div class="v2-form-group">
                <label>Marital Status</label>
                <select id="materialStatus" name="materialStatus" class="v2-form-control">
                    <option value="0">Select marital status</option>
                    <option value="Single">Single</option>
                    <option value="Married">Married</option>
                    <option value="Divorced">Divorced</option>
                    <option value="Widowed">Widowed</option>
                </select>
            </div>

            <div class="v2-form-group">
                <label>Physical Status</label>
                <select id="physicalStatus" name="physicalStatus" class="v2-form-control">
                    <option value="0">Select physical status</option>
                    <option value="Normal">Normal</option>
                    <option value="Physically challenged">Physically challenged</option>
                    <option value="Mentally Challenged">Mentally Challenged</option>
                    <option value="Deaf">Deaf</option>
                    <option value="Dumb">Dumb</option>
                    <option value="Other Disability">Other Disability</option>
                    <option value="Blind">Blind</option>
                </select>
            </div>

            <div class="v2-form-group v2-grid-full">
                <label>Body Type</label>
                <div class="v2-pill-group">
                    <input type="radio" id="bt_slim" name="bodyType" value="Slim" checked>
                    <label for="bt_slim">Slim</label>

                    <input type="radio" id="bt_athletic" name="bodyType" value="Athletic">
                    <label for="bt_athletic">Athletic</label>

                    <input type="radio" id="bt_average" name="bodyType" value="Average">
                    <label for="bt_average">Average</label>

                    <input type="radio" id="bt_heavy" name="bodyType" value="Heavy">
                    <label for="bt_heavy">Heavy</label>
                </div>
            </div>

            <div class="v2-form-group">
                <label>Height</label>
                <select id="heightSelect" name="heightSelect" class="v2-form-control">
                    <option value="0">Select height</option>
                    <c:forEach var="category" items="\${height}">
                        <option value="\${category.getHeight_id()}">\${category.getHeight_name()}</option>
                    </c:forEach>
                </select>
            </div>

            <div class="v2-form-group">
                <label>Weight</label>
                <select id="weightSelect" name="weightSelect" class="v2-form-control">
                    <option value="0">Select weight</option>
                    <c:forEach var="category" items="\${weight}">
                        <option value="\${category.getWeight_id()}">\${category.getWeight_name()}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        
        <div class="v2-actions" style="margin-bottom: 2rem;">
            <button type="button" class="v2-btn v2-btn-outline">Save Draft</button>
            <button type="button" class="v2-btn v2-btn-primary" onclick="MoveToStep3();">Save & Continue</button>
        </div>
        
        <div style="opacity: 0.2; pointer-events: none; height: 0; overflow: hidden;">
`;

const updatedContent = content.substring(0, startIndex) + newHtml + content.substring(endIndex);

fs.writeFileSync(path, updatedContent, 'utf8');
console.log('Successfully updated registration.jsp');
