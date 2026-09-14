<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>





<script
	src="${pageContext.request.contextPath}/resources/location/highcharts.js"></script>
	
	<body>
	<div class="col-lg">
					<!-- <div id="container1"
						style="min-width: 100px; height: 250px; margin: 0" auto"></div> -->
	</div>
	</body>
	<script>
	var arryear = new Array();
	var arrcount = new Array();
</script>


<c:forEach var="count" items="${countList}"
	varStatus="theCount">
	<script>
		var value = "${count.key}";
		arryear.push(value);
		var value = "${count.value}";
		arrcount.push(Number(value));
	</script>
</c:forEach>

	<script type="text/javascript">
	
	$('#container1').highcharts({
		chart : {
			type : 'column',

			margin : [ 50, 50, 100, 80 ]
		},
		title : {
			text : 'Analyse Flow'
		},
		xAxis : {
			categories : arryear,

			title : {
				text : 'Month'
			},
			labels : {
				rotation : -45,
				align : 'right',
				style : {
					fontSize : '10px',
					fontFamily : 'Verdana, sans-serif'
				}
			}
		},
		yAxis : {
			min : 0,

			title : {
				text : 'Total Registration'
			}
		},
		legend : {
			enabled : false
		},
		tooltip : {
			pointFormat : 'Total Registration: <b>{point.y:.1f}</b>',
		},
		plotOptions : {
			series : {
				color : '#092d3d',
				cursor : 'pointer',
				pointWidth : 15,
				point : {
					events : {
						click : function() {
							
							renderFillDayGraphByYearAndMonth1(this.category);
						}
					}
				}
			}
		},
		series : [ {
			name : 'All',
			data : arrcount,
			color : '#092d3d',
			dataLabels : {

				enabled : false,
				rotation : -90,
				color : '#FFFFFF',
				align : 'right',
				x : 2,
				y : 0,
				style : {

					fontSize : '10px',
					fontFamily : 'Verdana, sans-serif',
					textShadow : '0 0 0px black'
				}
			}

		} ]
	});
	
	function renderFillDayGraphByYearAndMonth1(Month)
	{
		

		
		var year =$("#hiddenYear").val();
		var intMonth;
		
		switch (Month) {
		case "January":
			intMonth = "1";
			break;
		case "February":
			intMonth = "2";
			break;
		case "March":
			intMonth = "3";
			break;
		case "April":
			intMonth = "4";
			break;
		case "May":
			intMonth = "5";
			break;
		case "June":
			intMonth = "6";
			break;
		case "July":
			intMonth = "7";
			break;
		case "August":
			intMonth = "8";
			break;
		case "September":
			intMonth = "9";
			break;
		case "October":
			intMonth = "10";
			break;
		case "November":
			intMonth = "11";
			break;
		case "December":
			intMonth = "12";
			break;
		}
	
	  $.ajax({
			url : "getDaywiseCount",
			type : "POST",
			dataType : 'json',
			data : {
				year : "" + year,
				month : "" + intMonth

			},
			
			success : function(data) {
				
				var arrDay = new Array();
				var arrDayCount = new Array();
				var jsonData = JSON.parse(JSON.stringify(data));
				var info = JSON.parse(JSON.stringify(data));
				var infoJSON = info.hashMap;
				var xx = JSON.parse(infoJSON);
				
				
				arrDayCount=[];
				arrDay=[];
				
				for (key1 in xx) {
					
					arrDay.push(key1);
					arrDayCount.push(xx[key1]);
					
					
							
				}
				
				fillGraphValues1(arrDay,arrDayCount); 
			},
			error : function(xhr, ajaxOptions, thrownError) {
				console.log("Error occured");
			}
		}); 
	 
	}
	
	
	function fillGraphValues1(arrDay,arrDayCount)
	{
		
		//$('#container3').hide();
		$('#container3').show();
		
		$('#container3').highcharts({
			chart : {
				type : 'column',

				margin : [ 50, 50, 100, 80 ]
			},
			title : {
				text : 'Analyse Flow Day Wise'
			},
			xAxis : {
				categories : arrDay,

				title : {
					text : 'Day Of Month'
				},
				labels : {
					rotation : -45,
					align : 'right',
					style : {
						fontSize : '10px',
						fontFamily : 'Verdana, sans-serif'
					}
				}
			},
			yAxis : {
				min : 0,

				title : {
					text : 'Total Registration'
				}
			},
			legend : {
				enabled : false
			},
			tooltip : {
				pointFormat : 'Total Registration: <b>{point.y:.1f}</b>',
			},
			credits: {
		        enabled: false
		    },
		  
			plotOptions : {
				series : {
					color : '#092d3d',
					cursor : 'pointer',
					pointWidth : 15,
					point : {
						events : {
							click : function() {
								
								//renderFillDayGraphByYearAndMonth(this.category);
							}
						}
					}
				}
			},
			series : [ {
				name : 'All',
				data : arrDayCount,
				color : '#092d3d',
				dataLabels : {

					enabled : false,
					rotation : -90,
					color : '#FFFFFF',
					align : 'right',
					x : 2,
					y : 0,
					style : {

						fontSize : '10px',
						fontFamily : 'Verdana, sans-serif',
						textShadow : '0 0 0px black'
					}
				}

			} ]
		});
		
		
		
		
	}
	
	</script>
	
	</body>
</html>