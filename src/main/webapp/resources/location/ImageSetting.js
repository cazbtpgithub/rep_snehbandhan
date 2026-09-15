function uploadLogoImages()
{
	var oMyForm = new FormData();
	var inp = document.getElementById('logoImages');
	var flag=0;
	var count=0;

	for (var i = 0; i < inp.files.length; ++i) {
		var name = inp.files.item(i).name;
		files[i]=inp.files.item(i).name;
		flag=1;
		oMyForm.append("file"+i, logoImages.files[i]);
		count++;
	}

	if(flag==0 ||count !=5 )
	{
		$("#logoSelect").slideDown();
		$("#logoSelect").fadeOut(3000);
		return false;
	}
	$("#uploadLogoImagesButton").prop('disabled',true);
	spinnerOn();
	$.ajax({
		dataType : 'Text',
		url : "uploadLogoFiles",
		data : oMyForm,
		type : "POST",
		enctype: 'multipart/form-data',
		processData: false,
		contentType:false,
		success : function(result) {
			$("#uploadLogoImagesButton").prop('disabled',false);
			$("#logosuccess").slideDown();
			$("#logosuccess").fadeOut(3000);
			$("#logoImages").val("");
			spinnerOff();
		},
		error : function(result){
			// console.log('error'+JSON.stringify(result));
			alert('error'+JSON.stringify(result));
			return false;
		} 
	});
}

function uploadSuccessImages ()
{
	var chatBoxSuccessStories = $('.successStoriesMassage').code();
	var text=chatBoxSuccessStories.replace(/(<([^>]+)>)/ig, " ");
	text = text.replace(/&nbsp;/g, ' ');
	if(text.length==3 ||text=="" )
	{
		$("#selectFirstSuccessStoryPortionAlert").slideDown();
		$("#selectFirstSuccessStoryPortionAlert").fadeOut(3000);
		return false;
	}
	
	var chatBoxSuccessStories2 = $('.successStoriesMassageSecand').code();
	var text=chatBoxSuccessStories2.replace(/(<([^>]+)>)/ig, " ");
	text = text.replace(/&nbsp;/g, ' ');

	if(text.length==3 ||text=="" )
	{
		$("#selectSecondSuccessStoryPortionAlert").slideDown();
		$("#selectSecondSuccessStoryPortionAlert").fadeOut(3000);
		return false;
	}

	var chatBoxSuccessStorie3 = $('.successStoriesMassageThird').code();
	var text=chatBoxSuccessStorie3.replace(/(<([^>]+)>)/ig, " ");
	text = text.replace(/&nbsp;/g, ' ');
	if(text.length==3 ||text=="" )
	{
		$("#selectThirdSuccessStoryPortionAlert").slideDown();
		$("#selectThirdSuccessStoryPortionAlert").fadeOut(3000);
		return false;
	}


	var chatBoxSuccessStorie4 = $('.successStoriesMassageforth').code();
	var text=chatBoxSuccessStorie4.replace(/(<([^>]+)>)/ig, " ");
	text = text.replace(/&nbsp;/g, ' ');
	if(text.length==3 ||text=="" )
	{
		$("#selectFourSuccessStoryPortionAlert").slideDown();
		$("#selectFourSuccessStoryPortionAlert").fadeOut(3000);
		return false;
	}

	var oMyForm = new FormData();
	var inp = document.getElementById('successImages');
	var flag=0;
	var count=0;
	for (var i = 0; i < inp.files.length; ++i) {
		var name = inp.files.item(i).name;

		files[i]=inp.files.item(i).name;
		flag=1;
		oMyForm.append("file"+i, successImages.files[i]);

		count++;
	}



	if(flag==0 || count!=4)
	{
		$("#successImageselect").slideDown();
		$("#successImageselect").fadeOut(3000);
		return false;

	}


	//  
	$("#uploadSuccessImagesButton").prop('disabled',true);
	spinnerOn();
	$.ajax({
		dataType : 'Text',
		url : "uploadSuccessFiles",
		data : oMyForm,
		type : "POST",
		enctype: 'multipart/form-data',
		processData: false,
		contentType:false,
		success : function(result) {	
			if(result=="success")
			{
				saveSuccessStory();
				saveSuccessStorySecand();
				saveSuccessStoryThird();
				saveSuccessStoryForth();

				$(".successStoriesMassage").code("");
				$(".successStoriesMassageSecand").code("");
				$(".successStoriesMassageThird").code("");
				$(".successStoriesMassageforth").code("");

				$("#uploadSuccessImagesButton").prop('disabled',false);
				$("#successImgessuccess").slideDown();
				$("#successImgessuccess").fadeOut(3000);
				spinnerOff();
			}

			$("#successImages").val("");

		},
		error : function(result){
			// console.log('error'+JSON.stringify(result));
			alert('error'+JSON.stringify(result));
			return false;
		} 

	});





}




function uploadBackgroundImages  ()
{
	
	
	
	 var count=0;

	 var oMyForm = new FormData();
	  var inp = document.getElementById('backgroundImages');
	  var flag=0;
	  for (var i = 0; i < inp.files.length; ++i) {
		  var name = inp.files.item(i).name;
		 
		 files[i]=inp.files.item(i).name;
		 
		 
		flag=1;
	      oMyForm.append("file"+i, backgroundImages.files[i]);
	      count++;
	      
		}

	  
	  
	  
	  if(flag==0 || count!=4)
	   {
	      $("#backGroundImageselect").slideDown();
	      $("#backGroundImageselect").fadeOut(3000);
	     return false;
	   
	   }
	  $("#uploadBackgroundImagesButton").prop('disabled',true);
	  spinnerOn();
  $.ajax({
			dataType : 'Text',
			url : "uploadBackgroundFiles",
			data : oMyForm,
			type : "POST",
			enctype: 'multipart/form-data',
			processData: false,
			contentType:false,
			success : function(result) {
				

				 if(result=="success")
				   {
					 $("#uploadBackgroundImagesButton").prop('disabled',false);
				      $("#backImgessuccess").slideDown();
				      $("#backImgessuccess").fadeOut(3000);
				      spinnerOff();
				      
				   }
		
				 $("#backgroundImages").val("");
				 
			},
			error : function(result){
				// console.log('error'+JSON.stringify(result));
				alert('error'+JSON.stringify(result));
				return false;
			} 
		
		});


}




///  

function uploadOfferImages  ()
{
	
	
	
	 var count=0;

	 var oMyForm = new FormData();
	  var inp = document.getElementById('offerImages');
	  var flag=0;
	  for (var i = 0; i < inp.files.length; ++i) {
		  var name = inp.files.item(i).name;
		 
		 files[i]=inp.files.item(i).name;
		 
		 
		flag=1;
	      oMyForm.append("file"+i, offerImages.files[i]);
	      count++;
	      
		}

	  
	  
	  
	  if(flag==0)
	   {
	      $("#offerImageselect").slideDown();
	      $("#offerImageselect").fadeOut(3000);
	     return false;
	   
	   }
	  $("#uploadOfferImagesButton").prop('disabled',true);
	 spinnerOn();
  $.ajax({
			dataType : 'Text',
			url : "uploadOfferFiles",
			data : oMyForm,
			type : "POST",
			enctype: 'multipart/form-data',
			processData: false,
			contentType:false,
			success : function(result) {
				

				 if(result=="success")
				   {
					 $("#uploadOfferImagesButton").prop('disabled',false);
				      $("#offerImgessuccess").slideDown();
				      $("#offerImgessuccess").fadeOut(3000);
				      spinnerOff();
				      
				   }
		
				 $("#offerImages").val("");
				 
			},
			error : function(result){
				// console.log('error'+JSON.stringify(result));
				alert('error'+JSON.stringify(result));
				return false;
			} 
		
		});


}



function saveSuccessStory()
{
var chatBoxSuccessStories = $('.successStoriesMassage').code();
	

	$.ajax({

			url : 'saveSuccessStory',
			type : "POST",
			dataType : 'text',
			data : {chatBoxSuccessStories:""+chatBoxSuccessStories},
			async : false,
			success : function(data) 
			{
				//  $(".successStoriesMassage").code("");
				/*  $("#storySuccessSelect").slideDown();
			      $("#storySuccessSelect").fadeOut(3000);*/
			},
			error : function(errorThrown) {
				console.log("Error: " + errorThrown);
			}
		});
}


function saveSuccessStorySecand(){
	var chatBoxSuccessStories = $('.successStoriesMassageSecand').code();
		
		
		
		$.ajax({

				url : 'saveSuccessStory',
				type : "POST",
				dataType : 'text',
				data : {chatBoxSuccessStories:""+chatBoxSuccessStories},
				async : false,
				success : function(data) 
				{
					 // $(".successStoriesMassageSecand").code("");
					
				},
				error : function(errorThrown) {
					console.log("Error: " + errorThrown);
				}
			});

	
}
function saveSuccessStoryThird(){
	var chatBoxSuccessStories = $('.successStoriesMassageThird').code();
		$.ajax({
				url : 'saveSuccessStory',
				type : "POST",
				dataType : 'text',
				data : {chatBoxSuccessStories:""+chatBoxSuccessStories},
				async : false,
				success : function(data) 
				{
					 
				},
				error : function(errorThrown) {
					console.log("Error: " + errorThrown);
				}
			});	
}

function saveSuccessStoryForth(){
	var chatBoxSuccessStories = $('.successStoriesMassageforth').code();
		
		var text=chatBoxSuccessStories.replace(/(<([^>]+)>)/ig, " ");
		text = text.replace(/&nbsp;/g, ' ');
		
		if(text.length==3 ||text=="" )
		{
			 $("#storySelect").slideDown();
		     $("#storySelect").fadeOut(3000);
		      return false;
		}
		$.ajax({
				url : 'saveSuccessStory',
				type : "POST",
				dataType : 'text',
				data : {chatBoxSuccessStories:""+chatBoxSuccessStories},
				async : false,
				success : function(data) 
				{
					 
				},
				error : function(errorThrown) {
					console.log("Error: " + errorThrown);
				}
			});

}



function addFaqs()
{
	
		var question =$("#question").val();
	    var answer =$("#answer").val();
	
		
	    if(question=="")
	    	{
	    		$("#questionSelect").slideDown();
		      $("#questionSelect").fadeOut(3000);
		      return false;
	    	}
	    
	    if(answer=="")
    	{
    		$("#answerSelect").slideDown();
	      $("#answerSelect").fadeOut(3000);
	      return false;
    	}
	    
	    
	    $.ajax({

			url : 'saveFAQs',
			type : "POST",
			dataType : 'text',
			//data : "question=" +""+question +"&answer=" +""+answer,
			data:{question:""+question,answer:""+answer},
			async : false,
			success : function(data) {

				 if(data=="success")
				   {
				     
					 $("#faqSucccess").slideDown();
				     $("#faqSucccess").fadeOut(3000);
				      
				   }
				$("#question").val("");
				$("#answer").val("");
			      
			},
			error : function(errorThrown) {
				console.log("Error: " + errorThrown);
			}
		});
	    
}


function savePrivacyPolicy()
{
	var chatBox = $('.privacyPolicyMassage').code();
	var text=chatBox.replace(/(<([^>]+)>)/ig, " ");
	text = text.replace(/&nbsp;/g, ' ');
	if(text.length==3 ||text=="" )
	{
		 $("#privacySelect").slideDown();
	     $("#privacySelect").fadeOut(3000);
	      return false;
	}
	$.ajax({
			url : 'savePrivacyPolicy',
			type : "POST",
			dataType : 'text',
			data : {chatBox:""+chatBox},
			async : false,
			success : function(data) 
			{
				 $(".privacyPolicyMassage").code("");
				 $("#privacySuccessSelect").slideDown();
			     $("#privacySuccessSelect").fadeOut(3000);
			},
			error : function(errorThrown) {
				console.log("Error: " + errorThrown);
			}
		});

}



function saveAboutus()
{
	var chatBoxFirstPortion = $('.aboutUsPortionFirstMassage').code();
	var textFirstPortion = chatBoxFirstPortion.replace(/(<([^>]+)>)/ig, " ");
	textFirstPortion = textFirstPortion.replace(/&nbsp;/g, ' ');
	if(textFirstPortion.length==3 ||textFirstPortion=="" )
	{
		$("#firstPortionSelection").slideDown();
		$("#firstPortionSelection").fadeOut(3000);
		return false;
	}
	var chatBoxSecondPortion = $('.aboutUsPortionSecondMassage').code();
	var textSecandPortion=chatBoxSecondPortion.replace(/(<([^>]+)>)/ig, " ");
	textSecandPortion = textSecandPortion.replace(/&nbsp;/g, ' ');
	if(textSecandPortion.length==3 ||textSecandPortion=="" )
	{
		$("#secondortionSelection").slideDown();
		$("#secondortionSelection").fadeOut(3000);
		return false;
	}

	$.ajax({
		url : 'saveAboutUs',
		type : "POST",
		dataType : 'text',
		data : {chatBoxFirstPortion :""+chatBoxFirstPortion,chatBoxSecondPortion:""+chatBoxSecondPortion},
		async : false,
		success : function(data) 
		{

			if(data=="success")
			{
				$("#aboutusSuccessSelect").slideDown();
				$("#aboutusSuccessSelect").fadeOut(3000);

				$(".aboutUsPortionFirstMassage").code("");
				$(".aboutUsPortionSecondMassage").code("");
			}
		},
		error : function(errorThrown) {
			console.log("Error: " + errorThrown);
		}
	});

}

function saveAboutUsThirdandForth()
{
	var aboutUsThirdStoryMassage = $('.aboutUsPortionThirdMassage').code();
	var aboutUsThirdPortion = aboutUsThirdStoryMassage.replace(/(<([^>]+)>)/ig, " ");
	aboutUsThirdPortion = aboutUsThirdPortion.replace(/&nbsp;/g, ' ');
	if(aboutUsThirdPortion.length==3 ||aboutUsThirdPortion=="" )
	{
		$("#firstPortionSelection").slideDown();
		$("#firstPortionSelection").fadeOut(3000);
		return false;
	}
	var aboutUsFourthStoryMassage = $('.aboutUsPortionForthMassage').code();

	var aboutUsThirdPortion=aboutUsFourthStoryMassage.replace(/(<([^>]+)>)/ig, " ");
	aboutUsThirdPortion = aboutUsThirdPortion.replace(/&nbsp;/g, ' ');

	if(aboutUsThirdPortion.length==3 ||aboutUsThirdPortion=="" )
	{
		$("#secondortionSelection").slideDown();
		$("#secondortionSelection").fadeOut(3000);
		return false;
	}
	$.ajax({
		url : 'saveAboutUs',
		type : "POST",
		dataType : 'text',
		data : {chatBoxFirstPortion :""+aboutUsThirdPortion,chatBoxSecondPortion:""+aboutUsFourthStoryMassage},
		async : false,
		success : function(data) 
		{
			if(data=="success")
			{
				$("#aboutusSuccessSelect").slideDown();
				$("#aboutusSuccessSelect").fadeOut(3000);

				$(".aboutUsPortionFirstMassage").code("");
				$(".aboutUsPortionSecondMassage").code("");
			}
		},
		error : function(errorThrown) {
			console.log("Error: " + errorThrown);
		}
	});	
}
function saveTermAndCondition()
{
	var chatBox = $('.termsAndCondition').code();
	var text=chatBox.replace(/(<([^>]+)>)/ig, " ");
	text = text.replace(/&nbsp;/g, ' ');
	
	if(text.length==3 ||text=="" )
	{
		$("#chatBoxSel").slideDown();
		$("#chatBoxSel").fadeOut(5000);
		$("#chatBox").focus();
		return false;
	}
	
	$.ajax({
			url : 'saveTermAndCondition',
			type : "POST",
			dataType : 'text',
			data : {chatBox:""+chatBox},
			async : false,
			success : function(data) {
				$(".termsAndCondition").code("");
				$("#termsuccess").slideDown();
				$("#termsuccess").fadeOut(5000);
			},
			error : function(errorThrown) {
				console.log("Error: " + errorThrown);
			}
		});
}
function spinnerOn()
{
	document.getElementById('light').style.display = 'block';
	document.getElementById('fade').style.display = 'block';
}
function spinnerOff()
{
	document.getElementById('light').style.display = 'none';
	document.getElementById('fade').style.display = 'none';
}

function changePassword()
{
    	var currentUserName = $("#currentUserName").val();
    	var newUserName = $("#newUserName").val();
	    var currentPass = $("#currentPass").val();
		var newPass = $("#newPass").val();
		var confirmPass = $("#confirmPass").val();
		var currentAdminUserName = $("#currentAdminUserName").val();
        var currentAdminPassword = $("#currentAdminPass").val();

        if (currentUserName == "" || currentUserName == null) {
		$("#currentUserNameAlert").slideDown();
		$("#currentUserNameAlert").fadeOut(5000);
		$('#currentUserName').focus();
		return false;
	}
	if (newUserName == "" || newUserName == null) {
		$("#newUserNameAlert").slideDown();
		$("#newUserNameAlert").fadeOut(5000);
		$('#newUserName').focus();
		return false;
	}
    if(currentAdminUserName != currentUserName)
	{
		$("#newUserNameMatchAlert").slideDown();
		$("#newUserNameMatchAlert").fadeOut(5000);
		$('#currentUserName').focus();
		return false;
	}
     

		if (currentPass == "" || currentPass == null) {
			$("#currentPasswordAlert").slideDown();
			$("#currentPasswordAlert").fadeOut(5000);
			$('#currentPassword').focus();
			return false;
		}
		if (newPass.trim() == ""|| newPass == null) {
			$("#newPassAlert").slideDown();
			$("#newPassAlert").fadeOut(5000);
			$("#newPass").focus();
			return false;
		}
		   if(currentAdminPassword != currentPass)
       	{
      		$("#currentPassMatchAlert").slideDown();
			$("#currentPassMatchAlert").fadeOut(5000);
			$('#currentPass').focus();
			return false;
       	}
		if (confirmPass.trim() == ""|| confirmPass == null) {
			$("#confirmPassAlert").slideDown();
			$("#confirmPassAlert").fadeOut(5000);
			$("#confirmPass").focus();
			return false;
		}
		
		if ($("#newPass").val() !== $("#confirmPass").val()) {
			$("#newPassMatchAlert").slideDown();
			$("#newPassMatchAlert").fadeOut(5000);
			$("#confirmPass").focus();
			$("#newPass").focus();
			return false;
		}		
		$.ajax({
			  url : 'changeAdminPass',
			  type : "POST",
			  dataType : 'text',
			  data : {currentUserName:currentUserName,newUserName:newUserName,currentPass:currentPass,newPass:newPass},
			  async : false,
			  success : function(responce)
			  {
				  if(responce=="success")
					  {
					  $("#currentAdminUserName").val(newUserName);
					  $("#currentAdminPass").val(newPass);
					  }

				  	$("#currentUserName").val("");
					$("#newUserName").val("");
		 	     	$("#currentPass").val("");
					$("#newPass").val("");
					$("#confirmPass").val("");
					$("#changePassSuccessAlert").slideDown();
					$("#changePassSuccessAlert").fadeOut(4000);
				
			  },
			  error : function(errorThrown) {
			   console.log("Error: " + errorThrown);
			  }
			 });
}
function sendBulkSms()
{
	var gender = $("#gender").val();	
	if(gender =="0")
		{
		  $("#selectUserOptionAlert").slideDown();
		  $("#selectUserOptionAlert").fadeOut(4000);
		  return false;
		}
}
function getUserByGender(){
	var gender = $("#gender").val();
	$.ajax({
		dataType : 'json',
		url : "userMobileNumberByGender",
		type : "GET",
		data :"gender="+gender,
		success : function(data) 
		{
			var jsonData = JSON.parse(JSON.stringify(data));   //Other Guest User attendee Details
			var counter1 = jsonData.userModelList;
			var cntForiterateobj1 = JSON.parse(counter1);
			var emailAttendeeTableData = '<br><div class="table-responsive"><table id="recipientsEmailsTable">'
				+' <thead><tr><th><i class="fa fa-check-square-o" aria-hidden="true"></i></th>'+
				'<th>Sr.No</th><th>Mobile Number</th><th>Full Name</th></tr></thead><tbody>';
			emailAttendeeTableData+='<div style="float:right;"><button type="button" id="addAttenndeeButtion" onclick="return addGroupAttendee(\'checkAll\');" class="btn btn-warning btn-small" >Add/Remove User</button></div><br><br>';
			for (var c = 0; c < cntForiterateobj1.length; c++) 
			{
				
				emailAttendeeTableData+='<tr><td><input type="checkbox"  id="'+cntForiterateobj1[c].userId+'" value="'+cntForiterateobj1[c].mobileNum+'-'+cntForiterateobj1[c].userId+'"onclick="addPerticualrRecipient('+cntForiterateobj1[c].userId+',this.value)" class="groupAttendeeCheck"/></td>'+
				'<td><h5>'+eval(c+1)+'</h5></td><td><h5>'+cntForiterateobj1[c].mobileNum+'</h5></td>'+
				'<td><h5>'+(cntForiterateobj1[c].userModel.firstName)+'</h5></td></tr>';	
			}

			if(cntForiterateobj1.length == 0)
			{
				emailAttendeeTableData+='<tr><td align="center" colspan="4"><h5>No Attendee Available in this Team</h5></td></tr>';
			}

			emailAttendeeTableData+='</tbody></table></div>';
			$('#teamWiseRecipient').html(emailAttendeeTableData);
			
			$('#recipientsEmailsTable').dataTable({
				"pagingType" : "full_numbers",

			});
		},
		error : function(xhr, ajaxOptions, thrownError) {
			alert("err" + thrownError + xhr.responseText);
		}
	});
}

function addGroupAttendee(id)
{
	$.confirm({
	    content: 'Do you want to Add/Remove All User Mobile Number?',
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
	    		$('input:checkbox').prop('checked', true);
	    		}
	    	else
	    		{
	    		$('input:checkbox').prop('checked', false);
	    		} 	
	    }
	});
}