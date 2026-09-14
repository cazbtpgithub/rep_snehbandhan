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
	src="${pageContext.request.contextPath}/resources/location/highcharts.js">  </script>


</head>
<body class="hold-transition skin-blue sidebar-mini">
	<div class="wrapper">

		<jsp:include page="menuForDealer.jsp"></jsp:include>

		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<!-- <section class="content-header">
				<div class="row">
        <div class="col-lg-3 col-xs-6">
          small box
          <div class="small-box bg-aqua">
            <div class="inner">
              <h3>0</h3>

              <p>New User</p>
            </div>
            <div class="icon">
              <i class="ion ion-bag"></i>
            </div>
            <a href="#" class="small-box-footer">More info <i class="fa fa-arrow-circle-right"></i></a>
          </div>
        </div>
        ./col
      
        ./col
        <div class="col-lg-3 col-xs-6">
          small box
          <div class="small-box bg-yellow">
            <div class="inner">
              <h3>0</h3>

              <p>Dealer Registrations</p>
            </div>
            <div class="icon">
              <i class="ion ion-person-add"></i>
            </div>
            <a href="#" class="small-box-footer">More info <i class="fa fa-arrow-circle-right"></i></a>
          </div>
        </div>
        ./col
        
          <div class="col-lg-3 col-xs-6">
          small box
          <div class="small-box bg-green">
            <div class="inner">
              <h3>0<sup style="font-size: 20px;">%</sup></h3>

              <p>Success Stories</p>
            </div>
            <div class="icon">
              <i class="ion ion-stats-bars"></i>
            </div>
            <a href="#" class="small-box-footer">More info <i class="fa fa-arrow-circle-right"></i></a>
          </div>
        </div>
        <div class="col-lg-3 col-xs-6">
          small box
          <div class="small-box bg-red">
            <div class="inner">
              <h3>0</h3>

              <p>Todays Visitors</p>
            </div>
            <div class="icon">
              <i class="ion ion-pie-graph"></i>
            </div>
            <a href="#" class="small-box-footer">More info <i class="fa fa-arrow-circle-right"></i></a>
          </div>
        </div>
        ./col
      </div>
			</section> -->
<br><br>
			<!-- Main content -->
			<section class="content">
				
				 <div class="panel panel-danger">
          <div class="panel-heading" style="background-color:#d23779"><font color="white">View Year Wise Report</font></div>
      <div class="panel-body">
      
      
       			 <div class="form-group">
                
                
                 
					 <div class="col-sm-2">
					  <label for="year">Select Year</label>
                	<select id="year" name="year" class="form-control input-sm">
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
					
					<input type="hidden" id="currentYear" name="currentYear" value="${currentYear}">
                </div>
						
                  <div class="col-sm-2">
                   <label for="packageName2">&nbsp;&nbsp;</label>
                    <button class="btn btn-primary" onclick="return viewDealerReport();">View Report</button>
                  </div>
                </div>
                
                <script type="text/javascript">
                
                var currentYear = $("#currentYear").val();
                $("#year").val(currentYear);
                </script>
              
      <br><br>
   
      			<div class="col-lg">
					<div id="container1"
						style="min-width: 100px; height: 250px; margin: 0" auto"></div>
						
						<div id="container2"></div>
					<!-- <div id="detail" style="min-width: 400px; height: 300px; margin: 0" auto"> -->
				</div>
				
				
      
      
      </div>
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
	
	$('#container1').highcharts({
		 credits: {
			    enabled: false
			  },
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
	
	
	</script>
	<script type="text/javascript">
			function viewDealerReport()
			{
				var year=$("#year").val();
				
				if (year == '0' || year == "") {
					alert('Empty');
				}
				
				//$('#container1').hide();

				$.ajax({
					url : "getReportByYearAndDealer",
					type : "Post",
					data : {
						year : "" + year
					},
					success : function(data) {

						$("#container2").html(data);
						

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
