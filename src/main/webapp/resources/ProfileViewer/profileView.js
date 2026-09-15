
	
	function  ShortlistSendMSG(){
		var ShortlistVisitedId =$('#ShortlistVisitedId').val();
		var ShortlistMSG =$('#ShortlistMSG').val();
		var oMyForm = new FormData();
		var arr = {
				ShortlistVisitedId :ShortlistVisitedId,
				ShortlistMSG:ShortlistMSG
		};
		oMyForm.append("jsonObjectData", JSON.stringify(arr));

		$.ajax({
			dataType : 'json',
			url : "ShortlistSendMSG",
			type : "POST",
			data :oMyForm,
			processData : false,
			contentType : false,
			success : function(data) {
				if(data.profileCount){
					$("#myModal").modal();	
					$('#ShortlistMSG').val("");
					}else{
					$("#myModalUpgrade").modal();	
				}
			},
			error : function(xhr, ajaxOptions, thrownError) {
				console.log("Error occured");
			}
		});
	}
	
	function imageSlider(userId){
		
		
		$.ajax({
			url : "imageSlider",
			type : "GET",
			data : "userId=" + userId,
			processData : false,
			contentType : false,
			success : function(data) {
				if (data.trim() === "UPGRADE_REQUIRED") {
					$('#myModalUpgrade').modal('show');
				} else {
					$('#imgSlider').modal('toggle');
					$('#customSlider').html(data);
				}
			},
			error : function(xhr, ajaxOptions, thrownError) {
				console.log("Error occured");
			}
		});
	}
	
	
function kundaliSlider(userId){
		
		
		$.ajax({
			url : "kundaliSlider",
			type : "GET",
			data : "userId=" + userId,
			processData : false,
			contentType : false,
			success : function(data) {
				$('#imgSlider').modal('toggle');
				$('#customSlider').html(data);
				
			},
			error : function(xhr, ajaxOptions, thrownError) {
				console.log("Error occured");
			}
		});
	}
	
	
	
	
	function changeSortlistStatus(sortlistedId,status){
		
		var oMyForm = new FormData();
		var arr = {
				sortlistedId :sortlistedId,
				status:status
		};
		oMyForm.append("jsonObjectData", JSON.stringify(arr));
		
		$.ajax({
			dataType : 'json',
			url : "changeSortlistStatus",
			type : "POST",
			data :oMyForm,
			processData : false,
			contentType : false,
			success : function(data) {
				switchConversation("invitation_conversation");
			},
			error : function(xhr, ajaxOptions, thrownError) {
				console.log("Error occured");
			}
		});
	}
	function  ShortlistSendEmail(){
	
		var ShortlistVisitedId =$('#ShortlistVisitedId').val();
		var ShortlistEmail =$('#ShortlistEmail').val();
		var oMyForm = new FormData();
		var arr = {
				ShortlistVisitedId :ShortlistVisitedId,
				ShortlistEmail:ShortlistEmail
		};
		oMyForm.append("jsonObjectData", JSON.stringify(arr));
		
		$.ajax({
			dataType : 'json',
			url : "ShortlistSendEmail",
			type : "POST",
			data :oMyForm,
			processData : false,
			contentType : false,
			success : function(data) {
				if(data.profileCount){
					$("#myModal").modal();	
					$('#ShortlistEmail').val("");
					}else{
					$("#myModalUpgrade").modal();	
				}
				
				
			},
			error : function(xhr, ajaxOptions, thrownError) {
				console.log("Error occured");
			}
		});
	}
	
	function Shortlist(VisitorId , profileName){/*
	    
		
		   var retVal = confirm("Do you want to continue ?");
           if( retVal == true ){
       	    $("#ShortlistVisitedId").val(VisitorId);
       	    $("#ShortlistName").val(profileName);
       	    
       	    $("#shortName").text(profileName);
       	// $("#ShortlistName").val(profileName);
       	    	var oMyForm = new FormData();
       		var arr = {
       				VisitorId : VisitorId
       		};
       		oMyForm.append("jsonObjectData", JSON.stringify(arr));
       	    
       	    $.ajax({
       			dataType : 'json',
       			url : "ShortlistUser",
       			type : "POST",
       			data : oMyForm,
       			processData : false,
       			contentType : false,
       			success : function(data) {
       				console.log(data.profileCount);
       				if(data.profileCount){
       					$("#myModal").modal();	
       					var text="<br>DOB:  "+data.dob+"<br>Mobile:  "+data.mobile+"<br>Email-id:  "+data.email+"<br>Address:  "+data.Address+"<br>";
       					$("#menu3ViewContact").html(text);
       					
       					}else{
       					
       					$("#myModalUpgrade").modal();	
       				}
       					
       					
       				
       			},
       			error : function(xhr, ajaxOptions, thrownError) {
       				console.log("err" + thrownError + xhr.responseText);
       			}
       		});	
           }
           else{
            
           }
         
	*/
		
	
		
	    $.confirm({
	        content: '<font size="3px;" color="#373a3c">Do you want to continue ? </font>',
	        theme: 'white',
	        animationSpeed: 2000 ,
	        confirmButtonClass: 'btn-info',
	        cancelButtonClass: 'btn-danger',
	            
	        onOpen: function(){
	         
	            
	            this.$content.find('button.examplebutton').click(function(){
	               
	            });
	        },
	        onClose: function(){
	           
	        },
	        onAction: function(action){
	          
	        	
	        	if('confirm'==action)
	        		{	
                    $("#"+VisitorId).hide();
	           	    $("#ShortlistVisitedId").val(VisitorId);
	           	    $("#ShortlistName").val(profileName);
	           	    
	           	    $("#shortName").text(profileName);
	           	// $("#ShortlistName").val(profileName);
	           	    	var oMyForm = new FormData();
	           		var arr = {
	           				VisitorId : VisitorId
	           		};
	           		oMyForm.append("jsonObjectData", JSON.stringify(arr));
	           	    
	           	    $.ajax({
	           			dataType : 'json',
	           			url : "ShortlistUser",
	           			type : "POST",
	           			data : oMyForm,
	           			processData : false,
	           			contentType : false,
	           			success : function(data) {
	           				console.log(data.profileCount);
	           				if(data.profileCount){
	           					$("#myModal").modal();	
	           					var landMarkText = (data.landmark && data.landmark.trim() !== "") ? "<br>Landmark : " + data.landmark : "";
	           					var text="<br>DOB:  "+data.dob+"<br>Mobile:  "+data.mobile+"<br>Email-id:  "+data.email+"<br>Address:  "+data.Address+landMarkText+"<br>";
	           					$("#menu3ViewContact").html(text);
	           					}else{
	           					$("#myModalUpgrade").modal();	
	           				}
	           			},
	           			error : function(xhr, ajaxOptions, thrownError) {
	           				console.log("err" + thrownError + xhr.responseText);
	           			}
	           		});
	        		
	        		}
	        	
	        }
	    });
	
	
	
	
	}

function loadProfileView(userId){
	$.ajax({
		url : "ViewProfileWithPartner",
		type : "GET",
		data :"userId="+userId,
		processData : false,
		contentType : false,
		success : function(data) {
			$("#mainDiv").html(data);
		},
		error : function(xhr, ajaxOptions, thrownError) {
			alert("err" + thrownError + xhr.responseText);
		}
	});
	
}

function loadCasteAccordingToReligion(id) {
	$.ajax({
		dataType : 'json',
		url : "educationOccupation/loadCasteAccordingToReligion",
		type : "GET",
		data : "id=" + id,
		success : function(data) {

			$("#casteSelect option").remove();
			$("#casteSelect").append('<option value=0>Select Caste</option>');
			for (var i = 0; i < data.length; i++) {
				$("#casteSelect").append(
						'<option value=' + data[i].caste_id + '>'
								+ data[i].caste_name + '</option>');
			}

		},
		error : function(xhr, ajaxOptions, thrownError) {
			alert("err" + thrownError + xhr.responseText);
		}
	});
}



function loadLatestMatches(){
	/*
	 * thumbnailSliderOptionsArr mcThumbnailSliderArr nsliderArr nsOptionsArr
	 */
	$('#LoaderModal').modal('toggle');
	maintainCounter=thumbnailSliderOptionsArr.length;
	
	var nsOptionsArrLength=0;
	var nsliderArrLength=0;
	var mcThumbnailSliderArrLength=0;
	var thumbnailSliderOptionsArrLength=0;
	
	var length=thumbnailSliderOptionsArr.length;
	var oMyForm = new FormData();
	var arr = {
			latestMatchVisitorIds :latestMatchVisitorIds,
			length:maintainCounter
	};
	oMyForm.append("jsonObjectData", JSON.stringify(arr));
	$.ajax({
		url : "loadLatestMatches",
		type : "POST",
		data : oMyForm,
		processData : false,
		contentType : false,
		success : function(data) {
			$("#latestOneDiv").append(data);
			$('#LoaderModal').modal('toggle');
		},
		error : function(xhr, ajaxOptions, thrownError) {
			alert("err" + thrownError + xhr.responseText);
		}
	});	
	   var latestLength=thumbnailSliderOptionsArr.length;
}

function loadLatestUpgraded(){
	$('#LoaderModal').modal('toggle');
	var length=thumbnailSliderOptionsArr.length;
	var oMyForm = new FormData();
	var arr = {
			latestUpgradedVisitorIds :latestUpgradedVisitorIds,
			length:maintainCounter
	};
	oMyForm.append("jsonObjectData", JSON.stringify(arr));
	$.ajax({
		url : "loadLatestUpgraded",
		type : "POST",
		data : oMyForm,
		processData : false,
		contentType : false,
		success : function(data) {
			$("#latestTwoDiv").append(data);
			$('#LoaderModal').modal('toggle');
		},
		error : function(xhr, ajaxOptions, thrownError) {
			alert("err" + thrownError + xhr.responseText);
		}
	});	
	   var latestLength=thumbnailSliderOptionsArr.length;
}


// Ramesh Changes


function initializeChatBox(userId,name){
	

	var hiddenExpDate=$("#hiddenExpDate").val(); 
	
	/*try {
			var startdate = new Date(hiddenExpDate);
			var enddate = new Date();
			
			if(hiddenExpDate==null ||hiddenExpDate=="" ||startdate < enddate)
			{
			  
				alert("Sorry you can't chat please upgrade package");
			  return false;
			}
			
		
	} catch (e) {
		
	}
	*/
	
	
	var flag =0;
	for(var i=0;i<activeUser.length;i++){
		
		if(activeUser[i]==userId){
			flag=1;
		}
	}
	if(flag==0){
	var staticval =$("#staticChatboxValue").val();
	$("#staticChatboxValue").val(parseInt(staticval)+50);
	
	var chatBox = "<div id=chat"+userId+" style='position: fixed; right: 16.66%; bottom: 0; height: 250px; width: 250px; background: #fff; border: 1px solid #ddd; margin-right:"+staticval +"px;'>"+
	"<div class='pophead clearfix'>"+
		"<h5 class='float-lg-center' id='tempName${profile.userId}'>"+name+" </h5>"+
		"<i class='fa fa-close float-lg-right closechat'onclick='closechat("+userId+");'></i> "+
		" <i class='float-lg-right' style=' padding: 0px 10px;'onclick='minimizechat("+userId+");'></i>"+
		"</div><div class='msgarea' id=msgarea"+userId+"  >"+
		"<div class='usermsg clearfix'><p>     " +
		" </div>	</div>" +
		"	<div class='typemsg'>" +
		" <input class='form-control form-control-sm' type='text' id=message"+userId+" name='message'" +
				"placeholder='Type and Enter to send' onkeypress=' sendMessage(event,"+userId+");'></div></div>";
	$("#chatBoxDiv").append(chatBox);
	activeUser.push(userId);
	
	return true;
	
	}else{
		flag=0;
		return false;
	}
	
}
function minimizechat(userId)
{
	
	$("#chat"+userId).val();

	$($(this).attr('href')).slideToggle();

}
function sendMessage(event,userId)
{
    var userName=$("#firstName").val();
	var msg=$("#message"+userId).val();
	var userFrom=$("#loguserid").val();
	var userTo=userId;
	userName=userName+"-"+userFrom+"-"+userTo;
	
	
	if(event.which==13 || event.keyCode==13)
		{
		
		
		try {
			
			var hiddenExpDate=$("#hiddenExpDate").val(); 
			var startdate = new Date(hiddenExpDate);
			var enddate = new Date();
			
			
			
			if(hiddenExpDate==null ||hiddenExpDate=="" ||startdate < enddate)
			{
				$("#message"+userId).val("Please Upgrade Package First");
				$("#myModalUpgrade").modal();
				return false;
			}
			
		
	} catch (e) {
		
	}
		
		
				var flower = new FriendlyChat();
				flower.saveMessage(event,msg,userName,userFrom,userTo);
				$("#message"+userId).val("");
				
				
			
		
				
		}
}






// Firebase code
// ##########################################################################
// ##########################################################################
// ##########################################################################
// ##########################################################################




// Initializes FriendlyChat.
function FriendlyChat(id) {
	
	
	
  this.checkSetup();
  this.initFirebase();
  this.loadMessages(id);
  
}

// Sets up shortcuts to Firebase features and initiate firebase auth.
FriendlyChat.prototype.initFirebase = function() {
	
	 this.auth = firebase.auth();
	  this.database = firebase.database();
	  this.storage = firebase.storage();
	  // Initiates Firebase auth and listen to auth state changes.
	  this.auth.onAuthStateChanged(this.onAuthStateChanged.bind(this));
  // TODO(DEVELOPER): Initialize Firebase.
};

// Loads chat messages history and listens for upcoming ones.
FriendlyChat.prototype.loadMessages = function(id) {
	

	
	
	var userFrom=$("#loguserid").val();

	
	// alert('Messages Loaded called');
	 // Reference to the /messages/ database path.
	  this.messagesRef = this.database.ref('messages');
	  // Make sure we remove all previous listeners.
	  this.messagesRef.off();

	  // Loads the last 12 messages and listen for new ones.
	  var setMessage = function(data) {
	    var val = data.val();
	    var array = val.name.split("-");  // i am spliting the concated
											// userFrom and UserTo
	    
	    
	    if((array[1]== userFrom && array[2]== id)||(array[1]==id && array[2]==userFrom) )
	    	{
	    	
	    	  this.displayMessage(data.key, array[0], val.text, val.photoUrl, val.imageUrl,id);// load
																								// data
																								// according
																								// to
																								// user
																								// only
	    	}
	   
	    
	  
	  }.bind(this);
	  this.messagesRef.limitToLast(1000).on('child_added', setMessage);
	  this.messagesRef.limitToLast(1000).on('child_changed', setMessage);
	
  // TODO(DEVELOPER): Load and listens for new messages.
};

// Saves a new message on the Firebase DB.
FriendlyChat.prototype.saveMessage = function(e,msg,userName,userFrom,userTo) {
  e.preventDefault();
  // Check that the user entered a message and is signed in.
  
  
  if (this.checkSignedInWithMessage()) {
   var currentUser = this.auth.currentUser;
 
    // Add a new message entry to the Firebase Database.
    this.messagesRef.push({
      name: userName,
      text: msg,
      photoUrl: null
    }).then(function() {
      // Clear message text field and SEND button state.
      // FriendlyChat.resetMaterialTextfield(this.messageInput);
      // this.toggleButton();
    	// We load currently existing chant messages.
    	
    	// 24 march code
    	
    	/*
		 * this.messagesRef.remove(function(error){ //do stuff after removal
		 * Here i am removing recently added database });
		 */
    	
    	
    	
    	var oMyForm = new FormData();
    	var arr = {
    			msg:msg,
    			userFrom :userFrom,
    			userTo:userTo
    	};
    	
    	oMyForm.append("jsonObjectData", JSON.stringify(arr));
    	$.ajax({
    		url : "saveChat",
    		type : "POST",
    		data : oMyForm,
    		processData : false,
    		contentType : false,
    		success : function(data) {
    			
    			
    		},
    		error : function(xhr, ajaxOptions, thrownError) {
    			alert("err" + thrownError + xhr.responseText);
    		}
    	});
    	
    	
    	
    	// End Of 24 code
    	
    	this.loadMessages(userTo);
    	
    	// here removing the database values and updating as null after
		// displaying 25 sat code
    	 var ref=this.messagesRef;
    	 this.messagesRef.orderByChild('name').equalTo(userName).once("value", function(snapshot){
    		
    		 var updates = {};
    	     snapshot.forEach(function(child){
    	          updates[child.key] = null;
    	          
    	          
    	     });
    	     ref.update(updates);
    	});
    	 
    	 
    	 
    }.bind(this)).catch(function(error) {
      console.error('Error writing new message to Firebase Database', error);
    });
  }
};

// Sets the URL of the given img element with the URL of the image stored in
// Firebase Storage.
FriendlyChat.prototype.setImageUrl = function(imageUri, imgElement) {
  if (imgElement) {
    imgElement.src = imageUri;
  }
};

// Saves a new message containing an image URI in Firebase.
// This first saves the image in Firebase storage.
FriendlyChat.prototype.saveImageMessage = function(event) {
var file = event.target.files[0];

// Clear the selection in the file picker input.
this.imageForm.reset();

// Check if the file is an image.
if (!file.type.match('image.*')) {
 var data = {
   message: 'You can only share images',
   timeout: 2000
 };
 this.signInSnackbar.MaterialSnackbar.showSnackbar(data);
 return;
}
// Check if the user is signed-in
if (this.checkSignedInWithMessage()) {

 // We add a message with a loading icon that will get updated with the
	// shared image.
 var currentUser = this.auth.currentUser;
 this.messagesRef.push({
   name: currentUser.displayName,
   imageUrl: FriendlyChat.LOADING_IMAGE_URL,
   photoUrl: currentUser.photoURL || '/images/profile_placeholder.png'
 }).then(function(data) {

   // Upload the image to Firebase Storage.
   var uploadTask = this.storage.ref(currentUser.uid + '/' + Date.now() + '/' + file.name)
       .put(file, {'contentType': file.type});
   // Listen for upload completion.
   uploadTask.on('state_changed', null, function(error) {
     console.error('There was an error uploading a file to Firebase Storage:', error);
   }, function() {

     // Get the file's Storage URI and update the chat message placeholder.
     var filePath = uploadTask.snapshot.metadata.fullPath;
     data.update({imageUrl: this.storage.ref(filePath).toString()});
   }.bind(this));
 }.bind(this));
}
};


// Signs-in Friendly Chat.
FriendlyChat.prototype.signIn = function() {
	
	  var provider = new firebase.auth.GoogleAuthProvider();
	  this.auth.signInWithPopup(provider);
  // TODO(DEVELOPER): Sign in Firebase with credential from the Google user.
};

// Signs-out of Friendly Chat.
FriendlyChat.prototype.signOut = function() {
	this.auth.signOut();
  // TODO(DEVELOPER): Sign out of Firebase.
};




// Not Nedded

// Triggers when the auth state change for instance when the user signs-in or
// signs-out.
FriendlyChat.prototype.onAuthStateChanged = function(user) {
  if (user) { // User is signed in!
    // Get profile pic and user's name from the Firebase user object.
    var profilePicUrl = null;   // TODO(DEVELOPER): Get profile pic.
    var userName = null;        // TODO(DEVELOPER): Get user's name.

    // Set the user's profile pic and name.
    // this.userPic.style.backgroundImage = 'url(' + profilePicUrl + ')';
    this.userName.textContent = userName;

    // Show user's profile and sign-out button.
    this.userName.removeAttribute('hidden');
    this.userPic.removeAttribute('hidden');
    this.signOutButton.removeAttribute('hidden');

    // Hide sign-in button.
    this.signInButton.setAttribute('hidden', 'true');

    // We load currently existing chant messages.
    // this.loadMessages();

    // We save the Firebase Messaging Device token and enable notifications.
    this.saveMessagingDeviceToken();
  } else { // User is signed out!
    // Hide user's profile and sign-out button.
    this.userName.setAttribute('hidden', 'true');
    this.userPic.setAttribute('hidden', 'true');
    this.signOutButton.setAttribute('hidden', 'true');

    // Show sign-in button.
    this.signInButton.removeAttribute('hidden');
  }
};

// Returns true if user is signed-in. Otherwise false and displays a message.
FriendlyChat.prototype.checkSignedInWithMessage = function() {
  /* TODO(DEVELOPER): Check if user is signed-in Firebase. */

	 if (this.auth.currentUser) {
		    return true;
		  }
  // Display a message to the user using a Toast.
  var data = {
    message: 'You must sign-in first',
    timeout: 2000
  };
  this.signInSnackbar.MaterialSnackbar.showSnackbar(data);
  return false;
};

// Saves the messaging device token to the datastore.
FriendlyChat.prototype.saveMessagingDeviceToken = function() {
	
	  firebase.messaging().getToken().then(function(currentToken) {
		    if (currentToken) {
		      console.log('Got FCM device token:', currentToken);
		      // Saving the Device Token to the datastore.
		      firebase.database().ref('/fcmTokens').child(currentToken)
		          .set(firebase.auth().currentUser.uid);
		    } else {
		      // Need to request permissions to show notifications.
		      this.requestNotificationsPermissions();
		    }
		  }.bind(this)).catch(function(error){
		    console.error('Unable to get messaging token.', error);
		  });
	
  // TODO(DEVELOPER): Save the device token in the realtime datastore
};

// Requests permissions to show notifications.
FriendlyChat.prototype.requestNotificationsPermissions = function() {
	
	  console.log('Requesting notifications permission...');
	  firebase.messaging().requestPermission().then(function() {
	    // Notification permission granted.
	    this.saveMessagingDeviceToken();
	  }.bind(this)).catch(function(error) {
	    console.error('Unable to get permission to notify.', error);
	  });
	
  // TODO(DEVELOPER): Request permissions to send notifications.
};



// Template for messages.
FriendlyChat.MESSAGE_TEMPLATE =
    '<div class="message-container">' +
      '<div class="spacing"><div class="pic"></div></div>' +
      '<div class="message"></div>' +
      '<div class="name"></div>' +
    '</div>';

// A loading image URL.
FriendlyChat.LOADING_IMAGE_URL = 'https://www.google.com/images/spin-32.gif';

// Displays a Message in the UI.
FriendlyChat.prototype.displayMessage = function(key, name, text, picUrl, imageUri,id) {
	
	// alert('append called');
	 var msgText="<div>"+
		"<div class='usermsg clearfix'><p> "+         
			""+text+""+
			"<span style='color:blue;'>"+name+"</span>"+
		"</p></div>	</div>";
	 
		$("#msgarea"+id).append(msgText);
		
		// $("#chatBoxDiv").animate({ scrollTop: $(document).height()+1000000 },
		// "fast");
		$("#msgarea"+id).animate({ scrollTop: $(document).height()+10000000000 }, "fast");    // this
																								// keep
																								// scroller
																								// position
																								// at
																								// bottom
};



// Checks that the Firebase SDK has been correctly setup and configured.
FriendlyChat.prototype.checkSetup = function() {
  if (!window.firebase || !(firebase.app instanceof Function) || !window.config) {
    window.alert('You have not configured and imported the Firebase SDK. ' +
        'Make sure you go through the codelab setup instructions.');
  } else if (config.storageBucket === '') {
    window.alert('Your Firebase Storage bucket has not been enabled. Sorry about that. This is ' +
        'actually a Firebase bug that occurs rarely. ' +
        'Please go and re-generate the Firebase initialisation snippet (step 4 of the codelab) ' +
        'and make sure the storageBucket attribute is not empty. ' +
        'You may also need to visit the Storage tab and paste the name of your bucket which is ' +
        'displayed there.');
  }
};





function closechat(userid){
	// $('#31').hide();
	
	var statVal =$("#staticChatboxValue").val();
	$("#staticChatboxValue").val(parseInt(statVal)-50)
	var id= userid;
	$('#chat'+id).remove();
	for(var i=0;i<activeUser.length;i++){
		
		if(activeUser[i]==userid){
			activeUser.splice(i, 1);
		}
	}
	
	
}



var Gathering = (function() {
	
	

    var randomName = function () {
        return Math.random().toString(36).replace(/[^a-z]+/g, '').substr(0, 5);
    };

    function Gathering(databaseReference, roomName) {

    	
        this.db = databaseReference;
        this.roomName = roomName || 'globe';

        this.room = this.db.ref("gatherings/" + encodeURIComponent(this.roomName));
        this.myName = '';
        this.user = null;

        
        this.join = function(uid, displayName) {
            if(this.user) {
                console.error('Already joined.');
                return false;
            }

            this.myName = displayName || 'Anonymous - '+ randomName();
            this.user = uid ? this.room.child(uid) : this.room.push();

            // Add user to presence list when online.
            var self = this;
            var presenceRef = this.db.ref(".info/connected");
            presenceRef.on("value", function(snap) {
                if (snap.val()) {
                    self.user.onDisconnect().remove();
                    self.user.set(self.myName);
                }
            });

            return this.myName;
        };

        this.leave = function() {
            this.user.remove();
            this.myName = '';
        };

        this.over = function () {
            this.room.remove();
        };

        this.onUpdated = function (callback) {
        	
        	
            if('function' == typeof callback) {
                this.room.on("value", function(snap) {
                    callback(snap.numChildren(), snap.val());
                });
            } else {
                console.error('You have to pass a callback function to onUpdated(). That function will be called (with user count and hash of users as param) every time the user list changed.');
            }
        };
    }

    return Gathering;
})();




function loadCityAccordingToStateForRefineSearch(id) {

	$.ajax({
		dataType : 'json',
		url : "educationOccupation/loadCityAccordingToState",
		type : "GET",
		data : "id=" + id,
		success : function(data) {

			$("#district").html("");
			
			for (var i = 0; i < data.length; i++) {
				var checkboxHtml = '<label class="premium-checkbox-label" for="dist_' + data[i].state_district_id + '">' +
				    '<input type="checkbox" name="checkBoxRefineDistrict" id="dist_' + data[i].state_district_id + '" value="' + data[i].state_district_id + '">' +
				    '<span class="custom-checkbox-text">' + data[i].district_Name + '</span>' +
				    '</label>';
				$("#district").append(checkboxHtml);
			}

			
		},
		error : function(xhr, ajaxOptions, thrownError) {
			alert("err" + thrownError + xhr.responseText);
		}
	});

	
}






