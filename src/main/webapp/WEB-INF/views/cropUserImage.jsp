<!DOCTYPE> 
<html>
<head>


<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Image Crop</title>
<script language="Javascript">
	function showCoords(c) {
		var imageheight = document.getElementById('cropbox').naturalHeight;
		var imagewidth = document.getElementById('cropbox').naturalWidth;
		var xper = (c.x * 100 / jQuery('#cropbox').width());
		var yper = (c.y * 100 / jQuery('#cropbox').height());
		var wPer = (c.w * 100 / jQuery('#cropbox').width());
		var hper = (c.h * 100 / jQuery('#cropbox').height());

		var actX = (xper * imagewidth / 100);
		var actY = (yper * imageheight / 100);
		var actW = (wPer * imagewidth / 100);
		var actH = (hper * imageheight / 100);
		jQuery('#x').val(parseInt(actX));
		jQuery('#y').val(parseInt(actY));
		jQuery('#w').val(parseInt(actW));
		jQuery('#h').val(parseInt(actH));
	};
	jQuery(document).ready(function() {
		var w = 100;
		var h = 100;
		var W = jQuery('#cropbox').width();
		var H = jQuery('#cropbox').height();
		var x = W / 2 - w / 2;
		var y = H / 2 - h / 2;
		var x1 = x + w;
		var y1 = y + h;

		jQuery('#cropbox').Jcrop({
			onChange : showCoords,
			onSelect : showCoords,

			setSelect : [ x, y, x1, y1 ]
		    ,minSize : [ 100, 100 ] // use for crop min size
		    ,aspectRatio : 1 / 1    // crop ration 
		});

		jQuery('#CropButton').click(function() {
			var cropX = jQuery('#x').val();
			var cropY = jQuery('#y').val();
			var cropW = jQuery('#w').val();
			var cropH = jQuery('#h').val();
			
			var cropimg =$("#cropidName").val();
			var cc=cropimg.substring(3, cropimg.length);
			
			var request_Data = {
				image :cc,
				cropX : cropX,
				cropY : cropY,
				cropW : cropW,
				cropH : cropH
			}
			$.post("cropImages", request_Data, function(data) {
				$("#myModal").modal('toggle');
				
				$('#secondModal').modal('toggle');
				//location.reload(true);
				$("#tabthree").html(data);
				$('#secondModal').modal('toggle');
				
			});
		});
	});
</script>

</head>
<body>
	<center>
		<table border="1">
			<tr>
				<td> <img src="resources/UserImages/${image}" " alt="Image not added Upload Profile Image"/ onerror="this.onerror=null; this.src='resources/UserImages/userImage.png';">
				</td>
				
			</tr>
		</table>
		<br> 
		<input type="button" value="Crop" id="CropButton"  class="btn btn-secondary btn-sm pinkbtn"/>
		<input type="hidden" size="4" id="x" name="l" />
		<input type="hidden" size="4" id="y" name="t" />
		<input type="hidden" size="4" id="w" name="w" />
		<input type="hidden" size="4" id="h" name="h" />

    	</center>
</body>
</html>