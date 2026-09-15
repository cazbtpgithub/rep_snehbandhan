
function removeImageByAdmin(img, btn,userId)
{

	$.ajax({
	url : "removeImagesByAdmin",
	type : "GET",
	data : "image=" + img +"&userId="+userId,
	processData : false,
	contentType : false,
	success : function(data) {

		var ul = document.getElementById("imgDiv");
		var items = ul.getElementsByTagName("li");
		for (var i = 0; i < items.length; ++i) {
			// do something with items[i], which is a <li> element
			if (img == items[i].id) {
				$(items[i]).remove();
				$(btn).remove();
			}
		}

		$("img").each(function() {
			if ($(this).attr("id") == "img" + img) {
				$(this).remove();
			}
		});
		showDivs(1);
	},
	error : function(xhr, ajaxOptions, thrownError) {
		alert("err" + thrownError + xhr.responseText);
	}
});


}


function saveImagesByAdmin(userid)
{
	
	var oMyForm = new FormData();
	var x = "";
	files = [];
	var arr = { userid:userid };
	var inp = document.getElementById('loader11');
	for (var i = 0; i < inp.files.length; ++i) {

		var name = inp.files.item(i).name;
		files[i] = inp.files.item(i).name;
		oMyForm.append("file" + i, loader11.files[i]);

	}
	
	oMyForm.append("jsonObjectData", JSON.stringify(arr));
	
	
	$.ajax({
		url : "saveImagesByAdmin",
		type : "POST",
		data : oMyForm,
		enctype : 'multipart/form-data',
		processData : false,
		contentType : false,
		success : function(data) {
			$("#loader11").val("");
			//location.reload(true);
			$("#imgsetting").html(data);
			
		},
		error : function(xhr, ajaxOptions, thrownError) {
			alert("err" + thrownError + xhr.responseText);
		}
	});



}

function removeKundaliImageByAdmin(img, btn,userId)
{

	$.ajax({
	url : "removeKundaliImagesByAdmin",
	type : "GET",
	data : "image=" + img +"&userId="+userId,
	processData : false,
	contentType : false,
	success : function(data) {

		var ul = document.getElementById("kundaliImgDiv");
		var items = ul.getElementsByTagName("li");
		for (var i = 0; i < items.length; ++i) {
			if (img.replace(".", "_") == items[i].id || img == items[i].id) {
				$(items[i]).remove();
				$(btn).remove();
			}
		}

		$("img").each(function() {
			if ($(this).attr("id") == "img" + img) {
				$(this).remove();
			}
		});
		showDivs(1);
	},
	error : function(xhr, ajaxOptions, thrownError) {
		alert("err" + thrownError + xhr.responseText);
	}
});


}

function saveKundaliImagesByAdmin(userid)
{
	
	var oMyForm = new FormData();
	var x = "";
	files = [];
	var arr = { userid:userid };
	var inp = document.getElementById('loaderKundaliAdmin');
	for (var i = 0; i < inp.files.length; ++i) {

		var name = inp.files.item(i).name;
		files[i] = inp.files.item(i).name;
		oMyForm.append("file" + i, loaderKundaliAdmin.files[i]);

	}
	
	oMyForm.append("jsonObjectData", JSON.stringify(arr));
	
	
	$.ajax({
		url : "saveKundaliImagesByAdmin",
		type : "POST",
		data : oMyForm,
		enctype : 'multipart/form-data',
		processData : false,
		contentType : false,
		success : function(data) {
			$("#loaderKundaliAdmin").val("");
			$("#imgsetting").html(data);
			
		},
		error : function(xhr, ajaxOptions, thrownError) {
			alert("err" + thrownError + xhr.responseText);
		}
	});



}

function closeModel()
{
	//jQuery('#imgDiv div').html('');
	$("#imgDiv").html('');  
	
	$("#addimgDiv").html(''); 
	$("#kundaliImgDiv").html('');
	$("#addKundaliImgDiv").html('');
}

function printDealerWiseAllMemberDetails()
{
	$("#printDealerWiseMemberDetailsButton").prop('disabled', true);
	document.getElementById('light').style.display = 'block';
	document.getElementById('fade').style.display = 'block';
	$.ajax({
				url : "printDealerAllMemberDetails",
				type : "GET",
				data : {},
				success : function(data)
				{

					window.location.href = 'Download?path='+data;
					
					$("#printDealerWiseMemberDetailsButton").prop('disabled', false);
					document.getElementById('light').style.display = 'none';
					document.getElementById('fade').style.display = 'none';

				},
				error : function(xhr, ajaxOptions,
						thrownError) {
					console.log("err" + thrownError
							+ xhr.responseText);

					return false;
				}
			});
}


