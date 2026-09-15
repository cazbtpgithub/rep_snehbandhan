const fs = require('fs');

const path = 'f:/Antigravity/Sneh_Bandhan/src/main/webapp/WEB-INF/views/registration.jsp';
let content = fs.readFileSync(path, 'utf8');

const startIndex = content.indexOf('<form class="form-horizontal" id="companyForm" method="post">');
let endIndex = content.indexOf('</form>', startIndex) + 7;

const newHtml = `
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
                <input type="text" id="firstName" name="firstName" class="v2-form-control" value="\\\${fullname}" placeholder="Enter your full name">
            </div>

            <div class="v2-form-group">
                <label>Email Address</label>
                <input type="text" id="email" class="v2-form-control" value="\\\${email}" placeholder="Enter your email address">
                <input type="hidden" value="\\\${hiddenemail}" name="hiddenemail" id="hiddenemail">
            </div>

            <div class="v2-form-group">
                <label>Mobile Number</label>
                <div class="v2-input-group">
                    <select class="v2-form-control"><option>+91</option></select>
                    <input type="text" id="mobile" name="mobile" class="v2-form-control" value="\\\${mobile}" placeholder="10-digit mobile number" maxlength="10">
                </div>
            </div>

            <div class="v2-form-group">
                <label>Date of Birth</label>
                <input type="text" id="dob" class="v2-form-control dob" value="\\\${dob}" placeholder="mm/dd/yyyy" readonly="readonly">
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
                <input type="text" id="userName" name="userName" class="v2-form-control" value="\\\${userName}" placeholder="Choose a username">
            </div>

            <div class="v2-form-group">
                <label>Password</label>
                <div class="v2-password-wrapper">
                    <input type="password" id="password" name="password" class="v2-form-control" value="\\\${password}" placeholder="Create a strong password">
                    <i class="fa fa-eye"></i>
                </div>
            </div>
            
            <div class="v2-form-group v2-grid-full">
                <label>About Me</label>
                <textarea id="aboutMe" class="v2-form-control" rows="3" placeholder="Tell us about yourself">I am a good person looking for a partner.</textarea>
            </div>
        </div>

        <h2 class="v2-section-title" style="margin-top: 2rem;"><i class="fa fa-male"></i> Physical Attributes</h2>
        
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

            <div class="v2-form-group v2-grid-full">
                <label>Complexion</label>
                <div class="v2-pill-group">
                    <input type="radio" id="cpx_very_fair" name="complexion" value="Very Fair">
                    <label for="cpx_very_fair">Very Fair</label>

                    <input type="radio" id="cpx_fair" name="complexion" value="Fair">
                    <label for="cpx_fair">Fair</label>

                    <input type="radio" id="cpx_wheatish" name="complexion" value="Wheatish" checked>
                    <label for="cpx_wheatish">Wheatish</label>
                    
                    <input type="radio" id="cpx_wheatish_brown" name="complexion" value="Wheatish brown">
                    <label for="cpx_wheatish_brown">Wheatish brown</label>

                    <input type="radio" id="cpx_dark" name="complexion" value="Dark">
                    <label for="cpx_dark">Dark</label>
                </div>
            </div>

            <div class="v2-form-group">
                <label>Height</label>
                <select id="heightSelect" name="heightSelect" class="v2-form-control">
                    <option value="0">Select height</option>
                    <c:forEach var="category" items="\\\${height}">
                        <option value="\\\${category.getHeight_id()}">\\\${category.getHeight_name()}</option>
                    </c:forEach>
                </select>
            </div>

            <div class="v2-form-group">
                <label>Weight</label>
                <select id="weightSelect" name="weightSelect" class="v2-form-control">
                    <option value="0">Select weight</option>
                    <c:forEach var="category" items="\\\${weight}">
                        <option value="\\\${category.getWeight_id()}">\\\${category.getWeight_name()}</option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <h2 class="v2-section-title" style="margin-top: 2rem;"><i class="fa fa-map-marker"></i> Location</h2>
        <div class="v2-grid">
            <div class="v2-form-group">
                <label>Country</label>
                <div class="alert alert-danger alert-dismissable" id="DivCountryselect" style="display:none;">
                    <button type="button" class="close alertClose" data-dismiss="alert">&times;</button>
                    <strong>Error: </strong>Please select Country.
                </div>
                <select id="countrySelect" name="countrySelect" class="v2-form-control">
                    <c:forEach var="country" items="\\\${countries}">
                        <option value='\\\${country.country_id}'>\\\${country.country_Name}</option>
                    </c:forEach>
                </select>
            </div>
            
            <div class="v2-form-group">
                <label>State</label>
                <div class="alert alert-danger alert-dismissable" id="DivStateselect" style="display:none;">
                    <button type="button" class="close alertClose" data-dismiss="alert">&times;</button>
                    <strong>Error: </strong>Please select State.
                </div>
                <select id="stateSelect" name="stateSelect" class="v2-form-control" onchange="loadCityAccordingToState(this.value);">
                    <option value="0">Select State</option>
                    <c:forEach var="stateCountry" items="\\\${state_Countries}">
                        <option value='\\\${stateCountry.state_Country_id}'>\\\${stateCountry.state_Name}</option>
                    </c:forEach>
                </select>
            </div>
            
            <div class="v2-form-group">
                <label>City / District</label>
                <div class="alert alert-danger alert-dismissable" id="DivDistrictselect" style="display:none;">
                    <button type="button" class="close alertClose" data-dismiss="alert">&times;</button>
                    <strong>Error: </strong>Please select District.
                </div>
                <select id="cityStateSelect" name="cityStateSelect" class="v2-form-control" onchange="loadsubDistrictAccordingToDistrict(this.value);">
                    <option value="0">Select City/District</option>
                </select>
            </div>
            
            <div class="v2-form-group">
                <label>Sub-District</label>
                <div class="alert alert-danger alert-dismissable" id="DivSub-Districtselect" style="display:none;">
                    <button type="button" class="close alertClose" data-dismiss="alert">&times;</button>
                    <strong>Error: </strong>Please select Sub-District.
                </div>
                <select id="subDistSelect" name="subDistSelect" class="v2-form-control">
                    <option value="0">Sub-District</option>
                </select>
            </div>
            
            <div class="v2-form-group v2-grid-full">
                <label>Landmark</label>
                <input type="text" id="ladmark" name="ladmark" class="v2-form-control" placeholder="Enter landmark">
            </div>
        </div>

        <h2 class="v2-section-title" style="margin-top: 2rem;"><i class="fa fa-book"></i> Religion Information</h2>
        <div class="v2-grid">
            <div class="v2-form-group">
                <label>Religion</label>
                <div class="alert alert-danger alert-dismissable" id="DivReligionselect" style="display:none;">
                    <button type="button" class="close alertClose" data-dismiss="alert">&times;</button>
                    <strong>Error: </strong>Please select Religion.
                </div>
                <select id="religionSelect" name="religionSelect" class="v2-form-control" onchange="loadCasteAccordingToReligion(this.value);">
                    <option value="0">Select Religion</option>
                    <c:forEach var="religion" items="\\\${religions}">
                        <option value='\\\${religion.religion_id}'>\\\${religion.religion_name}</option>
                    </c:forEach>
                </select>
            </div>
            
            <div class="v2-form-group">
                <label>Caste</label>
                <div class="alert alert-danger alert-dismissable" id="DivCasteselect" style="display:none;">
                    <button type="button" class="close alertClose" data-dismiss="alert">&times;</button>
                    <strong>Error: </strong>Please select Caste.
                </div>
                <select id="casteSelect" name="casteSelect" class="v2-form-control">
                    <option value="0">Caste</option>
                </select>
            </div>
        </div>
        
        <h2 class="v2-section-title" style="margin-top: 2rem;"><i class="fa fa-graduation-cap"></i> Professional Information</h2>
        <div class="v2-grid">
            <div class="v2-form-group">
                <label>Education Category</label>
                <div class="alert alert-danger alert-dismissable" id="DivEducationCategoryselect" style="display:none;">
                    <button type="button" class="close alertClose" data-dismiss="alert">&times;</button>
                    <strong>Error: </strong>Please select Education Category.
                </div>
                <select id="educationalCategorySelect" name="educationalCategorySelect" class="v2-form-control" onchange="loadEducationAccordingToEduCategory(this.value);">
                    <option value="0">Educational Category</option>
                    <c:forEach var="education" items="\\\${educationCategories}">
                        <option value='\\\${education.edu_cat_id}'>\\\${education.edu_cat_name}</option>
                    </c:forEach>
                </select>
            </div>
            
            <div class="v2-form-group">
                <label>Education</label>
                <div class="alert alert-danger alert-dismissable" id="DivEducationselect" style="display:none;">
                    <button type="button" class="close alertClose" data-dismiss="alert">&times;</button>
                    <strong>Error: </strong>Please select Education.
                </div>
                <select id="educationalSelect" name="educationalSelect" class="v2-form-control" disabled>
                    <option value="0">Select Education</option>
                </select>
            </div>
            
            <div class="v2-form-group">
                <label>Occupation Category</label>
                <div class="alert alert-danger alert-dismissable" id="DivOccupationCategoryselect" style="display:none;">
                    <button type="button" class="close alertClose" data-dismiss="alert">&times;</button>
                    <strong>Error: </strong>Please select Occupation Category.
                </div>
                <select id="occupationCategorySelect" name="occupationCategorySelect" class="v2-form-control" onchange="loadOccupationAccordingToOccuCategory(this.value);">
                    <option value="0">Occupation Category</option>
                    <c:forEach var="occupation" items="\\\${occupationCategories}">
                        <option value='\\\${occupation.occu_id}'>\\\${occupation.occu_name}</option>
                    </c:forEach>
                </select>
            </div>
            
            <div class="v2-form-group">
                <label>Occupation</label>
                <div class="alert alert-danger alert-dismissable" id="DivOccupationselect" style="display:none;">
                    <button type="button" class="close alertClose" data-dismiss="alert">&times;</button>
                    <strong>Error: </strong>Please select Occupation.
                </div>
                <select id="occupationSelect" name="occupationSelect" class="v2-form-control">
                    <option value="0">Select Occupation</option>
                </select>
            </div>
            
            <div class="v2-form-group v2-grid-full">
                <label>Employed In</label>
                <div class="v2-pill-group">
                    <input id="emp_gov" name="employedIn" value="Government" type="radio">
                    <label for="emp_gov">Government</label>

                    <input id="emp_def" name="employedIn" value="Defence" type="radio" checked>
                    <label for="emp_def">Defence</label>

                    <input id="emp_pvt" name="employedIn" value="Private" type="radio">
                    <label for="emp_pvt">Private</label>
                    
                    <input id="emp_bus" name="employedIn" value="Business" type="radio">
                    <label for="emp_bus">Business</label>
                    
                    <input id="emp_self" name="employedIn" value="Self Employed" type="radio">
                    <label for="emp_self">Self Employed</label>
                    
                    <input id="emp_not" name="employedIn" value="Not Working" type="radio">
                    <label for="emp_not">Not Working</label>
                </div>
            </div>
            
            <div class="v2-form-group">
                <label>Income Frequency</label>
                <div class="v2-radio-group">
                    <div class="v2-radio-item">
                        <input name="income" id="inc_monthly" value="Monthly" type="radio" checked>
                        <label for="inc_monthly">Monthly</label>
                    </div>
                    <div class="v2-radio-item">
                        <input name="income" id="inc_annual" value="Annual" type="radio">
                        <label for="inc_annual">Annual</label>
                    </div>
                </div>
            </div>
            
            <div class="v2-form-group">
                <label>Income Amount</label>
                <input type="number" name="incomeAmount" id="incomeAmount" class="v2-form-control" value="0" onkeypress="return event.charCode >= 48 && event.charCode <= 57 || event.keyCode === 8 || event.keyCode === 9">
            </div>
        </div>

        <h2 class="v2-section-title" style="margin-top: 2rem;"><i class="fa fa-users"></i> Family Details</h2>
        <div class="v2-grid">
            <div class="v2-form-group v2-grid-full">
                <label>Family Class</label>
                <div class="v2-pill-group">
                    <input name="familyClassInfo" id="fc_middle" value="Middle class" type="radio" checked>
                    <label for="fc_middle">Middle class</label>
                    
                    <input name="familyClassInfo" id="fc_upper" value="Upper middle class" type="radio">
                    <label for="fc_upper">Upper middle class</label>
                    
                    <input name="familyClassInfo" id="fc_rich" value="Rich" type="radio">
                    <label for="fc_rich">Rich</label>
                    
                    <input name="familyClassInfo" id="fc_affluent" value="Affluent" type="radio">
                    <label for="fc_affluent">Affluent</label>
                </div>
            </div>
            
            <div class="v2-form-group">
                <label>Family Type</label>
                <div class="v2-pill-group">
                    <input name="familyTypeInfo" id="ft_joint" value="Joint" type="radio" checked>
                    <label for="ft_joint">Joint</label>
                    
                    <input name="familyTypeInfo" id="ft_nuclear" value="Nuclear" type="radio">
                    <label for="ft_nuclear">Nuclear</label>
                </div>
            </div>
            
            <div class="v2-form-group">
                <label>Family Values</label>
                <div class="v2-pill-group">
                    <input name="familyValueInfo" id="fv_trad" value="Traditional" type="radio" checked>
                    <label for="fv_trad">Traditional</label>
                    
                    <input name="familyValueInfo" id="fv_mod" value="Moderate" type="radio">
                    <label for="fv_mod">Moderate</label>
                    
                    <input name="familyValueInfo" id="fv_lib" value="Liberal" type="radio">
                    <label for="fv_lib">Liberal</label>
                    
                    <input name="familyValueInfo" id="fv_orth" value="Orthodox" type="radio">
                    <label for="fv_orth">Orthodox</label>
                </div>
            </div>
        </div>
        
        <h2 class="v2-section-title" style="margin-top: 2rem;"><i class="fa fa-coffee"></i> Lifestyle Details</h2>
        <div class="v2-grid">
            <div class="v2-form-group v2-grid-full">
                <label>Diet</label>
                <div class="v2-pill-group">
                    <input name="eating" id="diet_veg" value="vegetarian" type="radio" checked>
                    <label for="diet_veg">Vegetarian</label>
                    
                    <input name="eating" id="diet_nonveg" value="Non vegetarian" type="radio">
                    <label for="diet_nonveg">Non-Vegetarian</label>
                    
                    <input name="eating" id="diet_egg" value="Eggs Only" type="radio">
                    <label for="diet_egg">Eggs Only</label>
                </div>
            </div>
            
            <div class="v2-form-group">
                <label>Drinking Habit</label>
                <div class="v2-pill-group">
                    <input name="drink" id="dr_never" value="Never" type="radio" checked>
                    <label for="dr_never">Never</label>
                    
                    <input name="drink" id="dr_reg" value="Regular drinker" type="radio">
                    <label for="dr_reg">Regular</label>
                    
                    <input name="drink" id="dr_soc" value="Social drinker" type="radio">
                    <label for="dr_soc">Social</label>
                </div>
            </div>
            
            <div class="v2-form-group">
                <label>Smoking Habit</label>
                <div class="v2-pill-group">
                    <input name="smoke" id="sm_never" value="Non-smoker" type="radio" checked>
                    <label for="sm_never">Never</label>
                    
                    <input name="smoke" id="sm_soc" value="Social smoker" type="radio">
                    <label for="sm_soc">Social</label>
                    
                    <input name="smoke" id="sm_chain" value="Chain smoker" type="radio">
                    <label for="sm_chain">Chain Smoker</label>
                </div>
            </div>
        </div>
        
        <h2 class="v2-section-title" style="margin-top: 2rem;"><i class="fa fa-file-image-o"></i> Documents</h2>
        <div class="v2-grid">
            <div class="v2-form-group v2-grid-full">
                <label>Profile Picture</label>
                <input id="uploadImage" type="file" onchange="loadImageFile();" class="v2-form-control">
            </div>
            
            <div class="v2-form-group v2-grid-full">
                <label>Kundali Document</label>
                <div class="alert alert-danger alert-dismissable" id="kundali_file_alert" style="display:none;">
                    <button type="button" class="close" data-dismiss="alert">&times;</button>
                    <strong>Error: </strong>Please Select Kundali File.
                </div>
                <input type="file" class="v2-form-control" accept=".gif,.jpg,.jpeg,.png" name="loaderKundali" id="loaderKundali" onchange="modelforkundali();">
                <img id="blah1" width="100" height="100" hidden />
            </div>
        </div>
        
        <div class="v2-actions" style="margin-top: 3rem; margin-bottom: 2rem; justify-content: flex-end;">
            <button type="button" class="v2-btn v2-btn-outline" style="margin-right: 1rem;" onclick="MoveToLogin();">Back</button>
            <button type="button" class="v2-btn v2-btn-primary" onclick="MoveToStep3();">Save & Continue</button>
        </div>
    </form>
`;

const updatedContent = content.substring(0, startIndex) + newHtml + content.substring(endIndex);

fs.writeFileSync(path, updatedContent, 'utf8');
console.log('Successfully updated registration.jsp with full form!');
