function uploadMagazineImages()
{
	 var magazine_Title = $("#magazine_Title").val();
	 var oMyForm = new FormData();
	 var arr = { Name:magazine_Title};
	  var inp = document.getElementById('magazineImage');
	  var flag=0;
	  for (var i = 0; i < inp.files.length; ++i) {
		  var name = inp.files.item(i).name;
		 
		 files[i]=inp.files.item(i).name;
		 
		flag=1;
	      oMyForm.append("file"+i, magazineImage.files[i]);
	      
		}
	  oMyForm.append("jsonObjectData", JSON.stringify(arr));
	  $.ajax({
			dataType : 'Text',
			url : "uploadImagesfromUploadMagazineAndImages",
			data : oMyForm ,
			type : "POST",
			enctype: 'multipart/form-data',
			processData: false,
			contentType:false,
			success : function(result) {


			},
			error : function(result){
			
				return false;
			} 

		});


}

function uploadMagazinePdfFile()
{
	
	 var magazine_File_Id = 0;
	 var magazine_Title = $("#magazine_Title").val();
	 if(magazine_Title == "")
		 {
		      $("#magazineTitleAlert").slideDown();
		      $("#magazineTitleAlert").fadeOut(3000);
		      $("#magazineTitleAlert").focus();
		      return false;
		 }
	 
	 var pdfFile = document.getElementById('magazinePdfFile');

	 var magazineFile = document.getElementById('magazineImage');

	 if(magazineFile.value == '' && pdfFile.value == '')
	 {
		 $("#magazineSelect").slideDown();
		 $("#magazineSelect").fadeOut(3000);
		 $("#magazinePdfFile").focus();
		 
		 return false;
	 }
	 
	 if(pdfFile.value == '')
	 {

     }
	 else
	 {
		 var val = $('input[type=file]').val().toLowerCase();
		 var regex = new RegExp("(.*?)\.(pdf)$");
		 if(!(regex.test(val))) 
		 {
			 $("#pdfFileSelect").slideDown();
			 $("#pdfFileSelect").fadeOut(3000);
			 $("#magazinePdfFile").val("");	 
			 $("#magazinePdfFile").focus();
			 return false;
		 }

	 }
	 
	 if(magazineFile.value == '')
	 {

     }
	 else
	 {
		 var ext = $('#magazineImage').val().split('.').pop().toLowerCase();
		 if($.inArray(ext, ['gif','png','jpg','jpeg']) == -1) 
		 {
		     $("#invalidImageFile").slideDown();
		     $("#invalidImageFile").fadeOut(3000);
		     $("#magazineImage").val("");
		     $("#magazineImage").focus();
		     
		     return false;
		 }
		

	 }
	

	 
	 var arr = { Name:magazine_Title};
	 
	 var oMyForm = new FormData();
	  var inp = document.getElementById('magazinePdfFile');
	   
	  
	  var flag=0;
	  for (var i = 0; i < inp.files.length; ++i) {
		  var name = inp.files.item(i).name;
		 
		 files[i]=inp.files.item(i).name;
		 
		flag=1;
	      oMyForm.append("file"+i, magazinePdfFile.files[i]);
	      
		}
	  
	 
        $("#uploadMagazinePdfFileButton").prop('disabled',true);
		document.getElementById('light').style.display = 'block';
		document.getElementById('fade').style.display = 'block';
	   
	  oMyForm.append("jsonObjectData", JSON.stringify(arr));
	  
	  $.ajax({
			dataType : 'json',
			url : "uploadPdfFilefromUploadMagazineAndImages",
			data : oMyForm,
			type : "POST",
			enctype: 'multipart/form-data',
			processData: false,
			contentType:false,
			success : function(responce) 
			{
				var	magazineFileId = JSON.stringify(responce);
				magazineFileId = magazineFileId.slice(1, -1);


				var oMyForm = new FormData();
				var inp = document.getElementById('magazineImage');
				var flag=0;
				for (var i = 0; i < inp.files.length; ++i) {
					var name = inp.files.item(i).name;

					files[i]=inp.files.item(i).name;

					flag=1;
					oMyForm.append("file"+i, magazineImage.files[i]);

				}
				
				
				
				
				var arr2 = { magazineFileId:magazineFileId};  
				oMyForm.append("jsonObjectData", JSON.stringify(arr2));
				
				
				
				$.ajax({
					dataType : 'json',
					url : "updateUploadPdfFilefromUploadMagazineAndImages",
					data : oMyForm,
					type : "POST",
					enctype: 'multipart/form-data',
					processData: false,
					contentType:false,
					success : function(responce) 
					{
						window.location.reload();
						
						$("#submitAlert").slideDown();
						$("#submitAlert").fadeOut(10000); 
						$("#magazine_Title").val("");
						$("#magazinePdfFile").val("");
						$("#magazineImage").val("");
						$("#uploadMagazinePdfFileButton").prop('disabled',fasle);
						document.getElementById('light').style.display = 'none';
						document.getElementById('fade').style.display = 'none';

					},
					error : function(result){
					
						return false;
					} 

				});


				//window.location.reload();

			},
			error : function(result){
			
				return false;
			} 

		});
	  

	
}
function downloadPdfFile(srNo, fileName)
{
   //alert("sr No= "+srNo+"=File Name =="+fileName);
}


function deleteMagazineDetails(rowId,magazineTableRowID,magazinePdfFileName,magazineImagesZipFileName)
{

	$.confirm({
	    title: '',
	    content: 'Are you sure you want to delete magazine? ',
	    buttons: {
	        confirm: function () {
	        	$.ajax({
	    		url : 'deleteMagazineDetails',
	    		type : "POST",
	    		dataType : 'text',
	    		data : {magazineTableRowID:magazineTableRowID,magazinePdfFileName:magazinePdfFileName,magazineImagesZipFileName:magazineImagesZipFileName},
	    		async : false,
	    		success : function(responce) 
	    		{
	    			var oTable = $('#magazineTable').dataTable();
	    			var rows = $("#magazineTable").dataTable().fnGetNodes();
	    			for (var i = 0; i < rows.length; i++) 
	    			{
	    				var id = $(rows[i]).find("td:eq(0)").text();
	    				if (id == rowId) 
	    				{
	    					var row = $(rows[i]).closest('tr');
	    					$('.dataTable').dataTable().fnDeleteRow(row); 
	    				}
	    			}

	    			$("#magazineDeleteAlert").slideDown();
	    			$("#magazineDeleteAlert").fadeOut(3000);

	    		},
	    		error : function(errorThrown) {
	    			console.log("Error: " + errorThrown);
	    		}
	    	});
	        	
	        },
	        cancel: function () {
	            
	        }
	      
	    }
	});
	

}