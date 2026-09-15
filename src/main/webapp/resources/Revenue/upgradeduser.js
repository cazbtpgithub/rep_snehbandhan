function loadUpdateduser() {

	var fdate = $("#fdate").val();
	var tdate = $("#tdate").val();

	if(fdate.trim()=="" ||fdate== null)
		{
			
		$("#fdateselect").slideDown();
		$("#fdateselect").fadeOut(3000);
			return false;
		}
	if(tdate.trim()=="" ||tdate== null)
	{
		
		$("#tdateselect").slideDown();
		$("#tdateselect").fadeOut(3000);
		return false;
	}
	$("#loadUpdateduserButton").prop('disabled',true);
	document.getElementById('light').style.display = 'block';
	document.getElementById('fade').style.display = 'block';

	$.ajax({
		url : "getUpgradedUserByDate",
		type : "GET",
		data : {
			fdate : "" + fdate,
			tdate : "" + tdate

		},
		success : function(data) {

			$("#KitResult").html(data);
			$("#loadUpdateduserButton").prop('disabled',false);
			document.getElementById('light').style.display = 'none';
			document.getElementById('fade').style.display = 'none';

		},
		error : function(xhr, ajaxOptions, thrownError) {
			console.log("err" + thrownError + xhr.responseText);

			return false;
		}
	});

}