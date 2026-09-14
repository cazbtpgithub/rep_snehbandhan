  <!-- ACTUAL HEADER END-->
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
  <script src="resources/plugins/jQuery/jquery-ui.min.js"></script>
  <script src="resources/bootstrap/js/bootstrap.min.js"></script>

<script async src="https://www.googletagmanager.com/gtag/js?id=UA-112016473-1"></script>
<script>
  window.dataLayer = window.dataLayer || [];
  function gtag(){dataLayer.push(arguments);}
  gtag('js', new Date());
  gtag('config', 'UA-112016473-1');
</script>
  <header class="main-header">
    <!-- Logo -->
    <a href="home" class="logo">
      <!-- mini logo for sidebar mini 50x50 pixels -->
      <span class="logo-mini"><b>${ActUserId.userRoll.role}</span>
      <!-- logo for regular state and mobile devices -->
      <span class="logo-lg"><b>${ActUserId.userRoll.role}</b></span>
    </a>
    <!-- Header Navbar: style can be found in header.less -->
    <nav class="navbar navbar-static-top">
      <!-- Sidebar toggle button-->
      <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
        <span class="sr-only">Toggle navigation</span>
      </a>

      <div class="navbar-custom-menu">
        <ul class="nav navbar-nav">
          <!-- User Account: style can be found in dropdown.less -->
          <li class="dropdown user user-menu">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
              <img src="resources/dist/img/user2-160x160.jpg" class="user-image" alt="User Image" onerror="this.onerror=null; this.src='resources/UserImages/userImage.png';">
              <span class="hidden-xs">${ActUserId.firstName}</span>
            </a>
            <ul class="dropdown-menu">
              <!-- User image -->
              <li class="user-header">
                <img src="resources/dist/img/user2-160x160.jpg" class="img-circle" alt="User Image" onerror="this.onerror=null; this.src='resources/UserImages/userImage.png';">

                <p>
                  ${ActUserId.firstName}
                  <!-- <small>Director</small> -->
                  <small>${ActUserId.userRoll.role}</small> 
                </p>
              </li>
              <!-- Menu Body -->
              
              <!-- Menu Footer-->
              <li class="user-footer" " style="background-color: #222d32;">
                <div class="pull-left">
                  <a href="#" class="btn btn-default" style="color: #d23779; border-radius: 8px;">Profile</a>
                </div>
                <div class="pull-right">
                 
                     <a  class="btn btn-default"  style="color: #d23779; border-radius: 8px;"
                       href="<c:url value='j_spring_security_logout'/>>
                    Sign out</a>
                </div>
              </li>
            </ul>
          </li>
          <!-- Control Sidebar Toggle Button -->
          <li>
            <a href="#" data-toggle="control-sidebar"></a>
          </li>
        </ul>
      </div>
    </nav>
  </header>
  
  <!-- ACTUAL HEADER END-->
  
  
  <!-- ACTUAL SIDE MENU BAR START-->
    <!-- Left side column. contains the logo and sidebar -->
  <aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
      <!-- Sidebar user panel -->
      <div class="user-panel">
        <div class="pull-left image">
          <img src="resources/dist/img/user2-160x160.jpg" class="img-circle" alt="User Image" onerror="this.onerror=null; this.src='resources/UserImages/userImage.png';">
        </div>
        <div class="pull-left info">
          <p>${ActUserId.firstName}</p>
          
        </div>
      </div>
      <!-- search form -->
      
      <!-- /.search form -->
      <!-- sidebar menu: : style can be found in sidebar.less -->
      
      
      <c:if test="${loginUserRole == 'Admin'}">
      <ul class="sidebar-menu">
       <li></li>
        <li class="treeview">
          <a href="#">
            <i class="fa fa-dashboard"></i> <span>Setting</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
            <li class="active"><a href="location"><i class="fa fa-circle-o"></i>Location Setting</a></li>
            <li class="active"><a href="sendBulkSmsAndEmails"><i class="fa fa-circle-o"></i>Send Bulk SMS and Emails</li>
            <li class="active"><a href="package"><i class="fa fa-circle-o"></i>Package Setting</a></li>
            <li class="active"><a href="kit"><i class="fa fa-circle-o"></i>Kit Setting</a></li>
           
            
          </ul>
        </li>
        <li class="treeview">
          <a href="#">
            <i class="fa fa-files-o"></i>
            <span>Member</span>
           <!--  <span class="pull-right-container">
              <span class="label label-primary pull-right">4</span>
            </span> -->
          </a>
          <ul class="treeview-menu">
            <li><a href="registeredUser"><i class="fa fa-circle-o"></i> Registered User</a></li>
            <li><a href="upgradedUser"><i class="fa fa-circle-o"></i> Upgraded User</a></li>
            <li><a href="onlineupgradedUser"><i class="fa fa-circle-o"></i> Online Upgraded User</a></li>
            <li><a href="deleteUser"><i class="fa fa-circle-o"></i> Deleted User Details</a></li>
          <!--   <li><a href="#"><i class="fa fa-circle-o"></i> Fixed</a></li>
            <li><a href="#"><i class="fa fa-circle-o"></i> Collapsed Sidebar</a></li> -->
          </ul>
        </li>
        
        
         <li class="treeview">
          <a href="#">
            <i class="fa fa-inr"></i>
            <span>Revenue</span>
           <!--  <span class="pull-right-container">
              <span class="label label-primary pull-right">4</span>
            </span> -->
          </a>
          <ul class="treeview-menu">
            <li><a href="revenueHome"><i class="fa fa-circle-o"></i>Revenue Details </a></li>  
            <li><a href="dealwiseRevenue"><i class="fa fa-circle-o"></i>Dealer Wise Revenue</a></li>
             <li><a href="employeeWiseRevenue"><i class="fa fa-circle-o"></i>Employee Wise Revenue</a></li> 
          
          </ul>
        </li>
        
        
        <li class="treeview">
          <a href="#">
            <i class="fa fa-user"></i>
            <span>Dealer</span>
           <!--  <span class="pull-right-container">
              <span class="label label-primary pull-right">4</span>
            </span> -->
          </a>
          <ul class="treeview-menu">
           <li><a href="dealerRegistration"><i class="fa fa-circle-o"></i>Dealer Registration </a></li> 
           <li><a href="dealerWiseMember"><i class="fa fa-circle-o"></i>Dealer Wise Member </a></li>
           <li><a href="dealerWiseSubDealer"><i class="fa fa-circle-o"></i>Dealer Wise Sub Dealer </a></li>
          
          </ul>
        </li>
        
         <li class="treeview">
          <a href="#">
            <i class="fa fa-user"></i>
            <span>Employee</span>
          </a>
          <ul class="treeview-menu">
           <li><a href="employeeRegistration"><i class="fa fa-circle-o"></i>Employee Details</a></li> 
            <li><a href="employeeWiseMemberForAdmin"><i class="fa fa-circle-o"></i>Employee Wise Member</a></li> 
		<!-- 	 <li><a href="employeeWiseRevenue"><i class="fa fa-circle-o"></i>Employee Wise Revenue</a></li>  -->
                 
                  
          </ul>
        </li>
        
        
        <li class="treeview">
          <a href="#">
            <i class="fa fa-user"></i>
            <span>Reports</span>
          </a>
          <ul class="treeview-menu">
           <li><a href="memberReport"><i class="fa fa-circle-o"></i>Member Report</a></li>      
           <li><a href="dealerReport"><i class="fa fa-circle-o"></i>Dealer Report</a></li>      
           <li><a href="employeeReport"><i class="fa fa-circle-o"></i>Employee Report</a></li>      
           
          </ul>
        </li>
        
        
        <li class="treeview">
          <a href="imagesSetting">
            <i class="fa fa-dashboard"></i> <span>Home Page Setting</span>
           <!--  <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span> -->
          </a>
         <!--  <ul class="treeview-menu">
            <li class="active"><a href="imagesSetting"><i class="fa fa-circle-o"></i>Upload New Images</a></li>
          </ul> -->
        </li>
           <li class="treeview">
          <a href="magazineSetting">
           <!--  <i class="fa fa-dashboard"></i> <span>Magazine Setting</span> -->
              <i class="fa fa-dashboard"></i> <span>Upload Magazine & Images</span>
          <!--   <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span> -->
          </a>
         <!--  <ul class="treeview-menu">
            <li class="active"><a href="magazineSetting"><i class="fa fa-circle-o"></i>Upload Magazine & Images</a></li>
          </ul> -->
        </li>
        
        
      <!-- <li class="treeview">
          <a href="#">
            <i class="fa fa-share"></i> <span>Multilevel</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
            <li><a href="#"><i class="fa fa-circle-o"></i> Level One</a></li>
            <li>
              <a href="#"><i class="fa fa-circle-o"></i> Level One
                <span class="pull-right-container">
                  <i class="fa fa-angle-left pull-right"></i>
                </span>
              </a>
              <ul class="treeview-menu">
                <li><a href="#"><i class="fa fa-circle-o"></i> Level Two</a></li>
                <li>
                  <a href="#"><i class="fa fa-circle-o"></i> Level Two
                    <span class="pull-right-container">
                      <i class="fa fa-angle-left pull-right"></i>
                    </span>
                  </a>
                  <ul class="treeview-menu">
                    <li><a href="#"><i class="fa fa-circle-o"></i> Level Three</a></li>
                    <li><a href="#"><i class="fa fa-circle-o"></i> Level Three</a></li>
                  </ul>
                </li>
              </ul>
            </li>
            <li><a href="#"><i class="fa fa-circle-o"></i> Level One</a></li>
          </ul>
        </li> -->
        
        

        
      </ul>
      
      </c:if>
      
        <c:if test="${loginUserRole == 'Employee'}">
        
        
        
        
        	<!--Write Menus according to E  -->
  
        <ul class="sidebar-menu">
       <li></li>
        <li class="treeview">
          <a href="#">
            <i class="fa fa-dashboard"></i> <span>Setting</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
          
          
          <c:forEach items="${userPermissionAssociationsList}"
				var="userPermissionAssociation">
				<c:if test="${userPermissionAssociation.permissionType.id == 1}">
                 <li class="active"><a href="location"><i class="fa fa-circle-o"></i>Location Setting</a></li>
				</c:if>
			</c:forEach>
			
			 <c:forEach items="${userPermissionAssociationsList}"
				var="userPermissionAssociation">
				<c:if test="${userPermissionAssociation.permissionType.id == 2}">
                  <li class="active"><a href="package"><i class="fa fa-circle-o"></i>Package Setting</a></li>
				</c:if>
			</c:forEach>
			
			 <c:forEach items="${userPermissionAssociationsList}"
				var="userPermissionAssociation">
				<c:if test="${userPermissionAssociation.permissionType.id == 3}">
               <li class="active"><a href="kit"><i class="fa fa-circle-o"></i>Kit Setting</a></li>
				</c:if>
			</c:forEach>
             <li><a href="employeeSaleKit"><i class="fa fa-circle-o"></i>UpdateSaleKit</a></li> 
             <li><a href="updateEmployeeUser"><i class="fa fa-circle-o"></i>UpdateUser</a></li> 
			
			
		   
          </ul>
        </li>
        <li class="treeview">
          <a href="#">
            <i class="fa fa-files-o"></i>
            <span>Member</span>
          
          </a>
          <ul class="treeview-menu">
          
            <c:forEach items="${userPermissionAssociationsList}"
				var="userPermissionAssociation">
				<c:if test="${userPermissionAssociation.permissionType.id == 4}">
              <li><a href="registeredUser"><i class="fa fa-circle-o"></i> Registered User</a></li>
				</c:if>
			</c:forEach>
			
			 <c:forEach items="${userPermissionAssociationsList}"
				var="userPermissionAssociation">
				<c:if test="${userPermissionAssociation.permissionType.id == 5}">
                 <li><a href="upgradedUser"><i class="fa fa-circle-o"></i> Upgraded User</a></li>
				</c:if>
			</c:forEach>
			
			 <c:forEach items="${userPermissionAssociationsList}"
				var="userPermissionAssociation">
				<c:if test="${userPermissionAssociation.permissionType.id == 6}">
               <li><a href="onlineupgradedUser"><i class="fa fa-circle-o"></i> Online Upgraded User</a></li>
				</c:if>
			</c:forEach>
           <c:forEach items="${userPermissionAssociationsList}"
				var="userPermissionAssociation">
				<c:if test="${userPermissionAssociation.permissionType.id == 7}">
            <li><a href="deleteUser"><i class="fa fa-circle-o"></i> Deleted User Details</a></li>
				</c:if>
			</c:forEach>
         
          </ul>
        </li>
        
        
         <li class="treeview">
          <a href="#">
            <i class="fa fa-inr"></i>
            <span>Revenue</span>         
          </a>
          <ul class="treeview-menu">
          
           <c:forEach items="${userPermissionAssociationsList}"
				var="userPermissionAssociation">
				<c:if test="${userPermissionAssociation.permissionType.id == 8}">
             <li><a href="revenueHome"><i class="fa fa-circle-o"></i>Revenue Details </a></li> 
				</c:if>
			</c:forEach>
           <c:forEach items="${userPermissionAssociationsList}"
				var="userPermissionAssociation">
				<c:if test="${userPermissionAssociation.permissionType.id == 9}">
           <li><a href="dealwiseRevenue"><i class="fa fa-circle-o"></i>Dealer Wise Revenue</a></li>
				</c:if>
			</c:forEach>
			
			 <c:forEach items="${userPermissionAssociationsList}"
				var="userPermissionAssociation">
				<c:if test="${userPermissionAssociation.permissionType.id == 10}">
			  <li><a href="employeeWiseRevenue"><i class="fa fa-circle-o"></i>Employee Wise Revenue</a></li> 
				</c:if>
			</c:forEach>
         
          </ul>
        </li>
        
        
        <li class="treeview">
          <a href="#">
            <i class="fa fa-user"></i>
            <span>Dealer</span>
          </a>
          <ul class="treeview-menu">
          
           <c:forEach items="${userPermissionAssociationsList}"
				var="userPermissionAssociation">
				<c:if test="${userPermissionAssociation.permissionType.id == 11}">
          <li><a href="dealerRegistration"><i class="fa fa-circle-o"></i>Dealer Registration </a></li> 
				</c:if>
			</c:forEach>
           <c:forEach items="${userPermissionAssociationsList}"
				var="userPermissionAssociation">
				<c:if test="${userPermissionAssociation.permissionType.id == 12}">
         <li><a href="dealerWiseMember"><i class="fa fa-circle-o"></i>Dealer Wise Member </a></li>
				</c:if>
			</c:forEach>
        
          </ul>
        </li>
        
         <li class="treeview">
          <a href="#">
            <i class="fa fa-user"></i>
            <span>Work Details</span>
          </a>
          <ul class="treeview-menu">
            <c:forEach items="${userPermissionAssociationsList}"
				var="userPermissionAssociation">
				<c:if test="${userPermissionAssociation.permissionType.id == 13}">
            <li><a href="employeeRegistration"><i class="fa fa-circle-o"></i>Employee Details</a></li> 
				</c:if>
			</c:forEach>
			
			 <c:forEach items="${userPermissionAssociationsList}"
				var="userPermissionAssociation">
				<c:if test="${userPermissionAssociation.permissionType.id == 14}">
				  <li><a href="employeeWiseMember"><i class="fa fa-circle-o"></i>Employee Wise Member</a></li> 
				</c:if>
			</c:forEach>
			
			    <li><a href="employeeWiseMember"><i class="fa fa-circle-o"></i>Registered Member</a></li> 
			<!--  <li><a href="employeeWiseRevenue"><i class="fa fa-circle-o"></i>Employee Wise Revenue</a></li>  -->
			
           
          </ul>
        </li>
        
        
        <li class="treeview">
          <a href="#">
            <i class="fa fa-user"></i>
            <span>Reports</span>
          </a>
          <ul class="treeview-menu">
          <c:forEach items="${userPermissionAssociationsList}"
				var="userPermissionAssociation">
				<c:if test="${userPermissionAssociation.permissionType.id == 15}">
           <li><a href="memberReport"><i class="fa fa-circle-o"></i>Member Report</a></li>      
				</c:if>
			</c:forEach>
			
			 <c:forEach items="${userPermissionAssociationsList}"
				var="userPermissionAssociation">
				<c:if test="${userPermissionAssociation.permissionType.id == 16}">
           <li><a href="dealerReport"><i class="fa fa-circle-o"></i>Dealer Report</a></li>       
				</c:if>
			</c:forEach>
			
			 <c:forEach items="${userPermissionAssociationsList}"
				var="userPermissionAssociation">
				<c:if test="${userPermissionAssociation.permissionType.id == 17}">
          <li><a href="employeeReport"><i class="fa fa-circle-o"></i>Employee Report</a></li>  
				</c:if>
			</c:forEach>
	       
          </ul>
        </li>
        
    
				<c:forEach items="${userPermissionAssociationsList}"
					var="userPermissionAssociation">
					<c:if test="${userPermissionAssociation.permissionType.id == 18}">
						<li class="treeview"><a href="imagesSetting"> <i
								class="fa fa-dashboard"></i> <span>Home Page Setting</span> <span
								class="pull-right-container"> 
							</span>
						</a></li>
					</c:if>
				</c:forEach>



	     <c:forEach items="${userPermissionAssociationsList}"
					var="userPermissionAssociation">
					<c:if test="${userPermissionAssociation.permissionType.id == 19}">
						<li class="treeview"><a href="magazineSetting"> <i
								class="fa fa-dashboard"></i> <span>Upload Magazine & Images</span> <span
								class="pull-right-container"> 
							</span>
						</a></li>
					</c:if>
				</c:forEach>

         <%--   <li class="treeview">
          <a href="magazineSetting">
          
            <c:forEach items="${userPermissionAssociationsList}"
				var="userPermissionAssociation">
				<c:if test="${userPermissionAssociation.permissionType.id == 19}">
            <li class="active"><a href="magazineSetting"><i class="fa fa-circle-o"></i>Upload Magazine & Images</a></li>
				</c:if>
			</c:forEach>
          
          <!--   <i class="fa fa-dashboard"></i> <span>Magazine Setting</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
             -->
            
          </a>
          <ul class="treeview-menu">
          
           <c:forEach items="${userPermissionAssociationsList}"
				var="userPermissionAssociation">
				<c:if test="${userPermissionAssociation.permissionType.id == 19}">
            <li class="active"><a href="magazineSetting"><i class="fa fa-circle-o"></i>Upload Magazine & Images</a></li>
				</c:if>
			</c:forEach>
          
          </ul>
        </li> --%>
<!--         
          <li class="treeview">
          <a href="#">
            <i class="fa fa-dashboard"></i> <span>Work Details</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
         
        </li> -->
        
      </ul>
        
			


		</c:if>
        
        
      
        
        
        
      
    </section>    <!--End amin  -->
    <!-- /.sidebar -->
  </aside>
  <!-- ACTUAL SIDE MENU BAR END-->
  <!-- FOOTER -->
   <footer class="main-footer sidebar-mini" "  style="position: fixed;"
    height: 50px;
    bottom: 0;
    width: 100%;
    margin-left:230
    max-width:767px">
    <div class="pull-right hidden-xs">
      
    </div>
    <strong>Copyright &copy; 2016-2017 <a href="http://snehbandh.com">Snehbandh</a>.</strong> All rights
    reserved.
  </footer>