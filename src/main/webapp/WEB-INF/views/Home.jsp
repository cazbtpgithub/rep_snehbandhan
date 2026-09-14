<!DOCTYPE html>
<html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Admin | Dashboard</title>
<!-- Tell the browser to be responsive to screen width -->
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
<jsp:include page="body.jsp"></jsp:include>

<script
	src="${pageContext.request.contextPath}/resources/location/highcharts.js"></script>


</head>
<body class="hold-transition skin-blue sidebar-mini">
	<div class="wrapper">

		<jsp:include page="menu.jsp"></jsp:include>

		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
 		 <section class="content-header">
				<div class="row">
        <div class="col-lg-3 col-xs-6">
         Recent Added Profiles
          <div class="small-box bg-aqua">
            <div class="inner">
            
            
              <h3>${recentlyAddedUser} </h3>

              <p>New User</p>
            </div>
            <div class="icon">
              <i class="ion ion-bag"></i>
            </div>
            <a href="registeredUser" class="small-box-footer">Registered Profile Details<i class="fa fa-arrow-circle-right"></i></a>
          </div>
        </div>
      
        <div class="col-lg-3 col-xs-6">
          Total Dealer
          <div class="small-box bg-yellow">
            <div class="inner">
              <h3>${totaldealer}</h3>

              <p>Total Dealer</p>
            </div>
            <div class="icon">
              <i class="ion ion-person-add"></i>
            </div>
            <a href="dealerRegistration" class="small-box-footer"> Total Registered Dealer Details <i class="fa fa-arrow-circle-right"></i></a>
          </div>
        </div>
        
        
          <div class="col-lg-3 col-xs-6">
          Total Revenue
          <div class="small-box bg-green">
            <div class="inner">
              <h3>${totalRevenue}/-<sup style="font-size: 20px;"></sup></h3>

              <p>Total Revenue</p>
            </div>
            <div class="icon">
              <i class="ion ion-stats-bars"></i>
            </div>
            <a href="revenueHome" class="small-box-footer">Revenue Details <i class="fa fa-arrow-circle-right"></i></a>
          </div>
        </div>
        <div class="col-lg-3 col-xs-6">
         Total Sale Kits
          <div class="small-box bg-red">
            <div class="inner">
              <h3>${totalsaleKit}</h3>

              <p>Total Sale Kits</p>
            </div>
            <div class="icon">
              <i class="ion ion-pie-graph"></i>
            </div>
            <a href="kit" class="small-box-footer">Sale Kit Details <i class="fa fa-arrow-circle-right"></i></a>
          </div>
        </div>
      
      </div>
			</section> 

			<!-- Main content -->
			<section class="content">
				
				 <div class="panel panel-danger">
          <div class="panel-heading" style="background-color:#d23779"><font color="white">View Year Wise Report</font></div>
      <div class="panel-body">
      
      
       			 <div class="form-group">
                
                
                 
					 <div class="col-sm-2">
					  <label for="year">Select Year</label>
                	<select id="year" name="year" class="form-control input-sm" >
									<option value="2010">2010</option>
									<option value="2011">2011</option>
									<option value="2012">2012</option>
									<option value="2013">2013</option>
									<option value="2014">2014</option>
									<option value="2015">2015</option>
									<option value="2016">2016</option>
									<option value="2017">2017</option>
									<option value="2018">2018</option>
									<option value="2019">2019</option>
									<option value="2020">2020</option>
									<option value="2021">2021</option>
									<option value="2022">2022</option>
									<option value="2023">2023</option>
									<option value="2024">2024</option>
									<option value="2025">2025</option>
									<option value="2026">2026</option>
									<option value="2027">2027</option>
					</select>
                </div>
                
             <!--    <script>
										$('[name=year] option').filter(function() { 
										    return ($(this).text() == '${modelDetails.year}'); 
										}).prop('selected', true); 
	  									
  				</script> -->
						
						<input type="hidden"  id="hiddenYear"  name="hiddenYear" value="${thisYear}">
                  <div class="col-sm-2">
                   <label for="packageName2">&nbsp;&nbsp;</label><br>
                    <button class="btn btn-primary" id="viewAnnualReportButton" onclick="return viewReport();">View Report</button>
                  </div>
                </div>
              
      <br><br>
   
      			<div class="col-lg">
					<div id="container1"
						style="min-width: 100px; height: 250px; margin: 0" auto"></div>
						
						
						<div id="container2"></div>
						
					<div id="container3" style="min-width: 100px; height: 250px; margin: 0" auto"></div>
						
						
					<!-- <div id="detail" style="min-width: 400px; height: 300px; margin: 0" auto"> -->
				</div>
				
				
      
      
      </div>
      
      <div id="light" class=""
									style="position: fixed; float: left; width: 40%; padding: 0px; margin: 0px; top: 30%; left: 50%; overflow: hidden; display: none;">
									<div class="meetingContent">


										<img src="${pageContext.request.contextPath}/resources/images/spinner.gif" class="logo" style="width: 150px">

									</div>
									Loading
								</div>
								<div id="fade" class="black_overlay"></div>
    </div>
				
			</section>
		</div>
		
		<div class="control-sidebar-bg"></div>
	</div>


	<!-- jQuery 2.2.3 -->
	<!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip -->
	<script>
		$.widget.bridge('uibutton', $.ui.button);
	</script>
	
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
	var year = $("#hiddenYear").val();
	$("#year").val(year);
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
							
							renderFillDayGraphByYearAndMonth(this.category);
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
	
	
	function renderFillDayGraphByYearAndMonth(Month)
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
				
				fillGraphValues(arrDay,arrDayCount); 
			},
			error : function(xhr, ajaxOptions, thrownError) {
				console.log("Error occured");
			}
		});  
		
		
	}
	
	
	
	function fillGraphValues (arrDay,arrDayCount)
	{
		
		
		
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
	
	
	<script type="text/javascript">
	
	
	
			function viewReport()
			{
				var year=$("#year").val();
				
				if (year == '0' || year == "") {
					alert('Empty');
				}
				
				
				$("#hiddenYear").val(year);
				//$('#container1').hide();
			    $("#viewAnnualReportButton").prop('disabled',true);
	            document.getElementById('light').style.display = 'block';
				document.getElementById('fade').style.display = 'block';

				$.ajax({
					url : "getReportByYear",
					type : "Post",
					data : {
						year : "" + year
					},
					success : function(data) {

						$("#container3").hide();
						$("#container2").html(data);
						   $("#viewAnnualReportButton").prop('disabled',false);
						document.getElementById('light').style.display = 'none';
						document.getElementById('fade').style.display = 'none';
						

					},
					error : function(xhr, ajaxOptions, thrownError) {
						console.log("err" + thrownError + xhr.responseText);

						return false;
					}
				}); 

				
			} 
	
	
	
	</script>
	
</body>
</html>
