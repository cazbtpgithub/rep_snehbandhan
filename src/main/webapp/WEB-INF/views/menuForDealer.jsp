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
    <a href="homeForDealer" class="logo">
      <!-- mini logo for sidebar mini 50x50 pixels -->
      <span class="logo-mini"><b>Dealer</span>
      <!-- logo for regular state and mobile devices -->
      <span class="logo-lg"><b>Dealer</b></span>
    </a>
    <!-- Header Navbar: style can be found in header.less -->
    <nav class="navbar navbar-static-top">
      <!-- Sidebar toggle button-->
      <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
        <span class="sr-only">Toggle navigation</span>
      </a>

      <div class="navbar-custom-menu">
        <ul class="nav navbar-nav">
          <!-- Messages: style can be found in dropdown.less-->
          
          <!-- Notifications: style can be found in dropdown.less -->
          
          <!-- Tasks: style can be found in dropdown.less -->
          
          <!-- User Account: style can be found in dropdown.less -->
          <li class="dropdown user user-menu">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
              <img src="resources/dist/img/user2-160x160.jpg" class="user-image" alt="User Image" onerror="this.onerror=null; this.src='resources/UserImages/userImage.png';">
              <span class="hidden-xs">${DealeName}</span>
            </a>
            <ul class="dropdown-menu">
              <!-- User image -->
              <li class="user-header">
                <img src="resources/dist/img/user2-160x160.jpg" class="img-circle" alt="User Image" onerror="this.onerror=null; this.src='resources/UserImages/userImage.png';">

                <p>
                ${DealeName}
                  <small>Dealer</small>
                </p>
              </li>
              <!-- Menu Body -->
              
              <!-- Menu Footer-->
              <li class="user-footer">
                <div class="pull-left">
                  <a href="#" class="btn btn-default btn-flat">Profile</a>
                </div>
                <div class="pull-right">
                 
                     <a  class="btn btn-default btn-flat"  href="<c:url value='j_spring_security_logout'/>>
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
          <p>${DealeName}</p>
          
        </div>
      </div>
      <!-- search form -->
      
      <!-- /.search form -->
      <!-- sidebar menu: : style can be found in sidebar.less -->
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
            <li class="active"><a href="dealerSaleKit"><i class="fa fa-circle-o"></i>Update Sale Kit</a></li>
            <li class="active"><a href="updateDealerUser"><i class="fa fa-circle-o"></i>Update User</a></li>
            
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
            <li><a href="DealerRegisteredUser"><i class="fa fa-circle-o"></i> Registered User</a></li>
            <!-- <li><a href="#"><i class="fa fa-circle-o"></i> Upgraded User</a></li> -->
          </ul>
        </li>
        
        
         <li class="treeview">
          <a href="#">
            <i class="fa fa-inr"></i>
            <span>Revenue</span>
           
          </a>
          <ul class="treeview-menu">
            <li><a href="DealerRevenueHome"><i class="fa fa-circle-o"></i>Revenue Details </a></li>
          
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
           <li><a href="subdealerRegistration"><i class="fa fa-circle-o"></i>Dealer Registration </a></li> 
           <li><a href="subdealerWiseMember"><i class="fa fa-circle-o"></i>Dealer Wise Member </a></li>
           <!-- <li><a href="#"><i class="fa fa-circle-o"></i>Dealer Registration </a></li>  -->
          
          </ul>
        </li>
        
            <li class="treeview">
          <a href="subDealerReport">
            <i class="fa fa-user"></i>
            <span>Reports</span>
          </a>
         <!--  <ul class="treeview-menu">
           <li><a href="subdealerRegistration"><i class="fa fa-circle-o"></i>Dealer Registration </a></li> 
           <li><a href="subdealerWiseMember"><i class="fa fa-circle-o"></i>Dealer Wise Member </a></li>
           <li><a href="#"><i class="fa fa-circle-o"></i>Dealer Registration </a></li> 
          
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
    </section>
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