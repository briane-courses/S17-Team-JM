<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0"/>
  	   <meta name="google-signin-scope" content="profile email">
    <meta name="google-signin-client_id" content="20332845396-pvv6j3ifeu0391esi12rdcill7tmq0u7.apps.googleusercontent.com">
	
	
  <title>Dashboard ADM</title>

  <!-- CSS  -->
  <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
  <link href="css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection"/>
 
  
    <script src="https://apis.google.com/js/platform.js" async defer>
	 </script>
	

</head>

<body>

  <!--  Scripts-->
  <!--Import jQuery before materialize.js-->
  <script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
  <script src="js/materialize.js"></script>
  <script>
    function signOut() {
	    gapi.load('auth2', function() {
        gapi.auth2.init();
      });
      var auth2 = gapi.auth2.getAuthInstance();
      auth2.signOut().then(function () {
        console.log('User signed out.');
		window.location.href = "login.jsp";
      });
    }

    function onLoad() {
      gapi.load('auth2', function() {
        gapi.auth2.init();
      });
    }
         $(document).ready(function() {
         $('select').material_select();
      });
     

     
  </script>

  <script src="https://apis.google.com/js/platform.js?onload=onLoad" async defer></script>
  
<!-- START OF NAVBAR --> 
 <div class="navbar-fixed">
 <nav class = "green">
    <div class="nav-wrapper">
   
      <ul id="nav-mobile" class="right hide-on-med-and-down">
        <li> <a href="#" onclick="signOut();"><i class="material-icons">settings</i>Settings/Sign Out</a></li>
		  <li><a href="#"><i class="material-icons">add_alert</i>Notification Icon</a></li>
      </ul>
    </div>
  </nav>
</div>

 <ul id="slide-out" class="side-nav fixed">
			<li><div class="userView">
			  <img class="background" src="images/office.jpg">
			  <a href="#!user"><img class="circle" src="images/cso.jpg"></a>
			  <a href="#!name"><span class="white-text name">${orgcode } </span></a>
			  <a href="#!email"><span class="white-text email">${email }</span></a>
			</div></li>
			<li><a href="homepage.html"><i class="material-icons">dashboard</i>Dashboard</a></li>
						<li><div class="divider"></div></li>
			<li><a href="calendar.html"><i class="material-icons">today</i>Calendar of special deadlines</a></li>
		  </ul>
		  
  <a href="#" data-activates="slide-out" class="button-collapse"><i class="material-icons">menu</i></a>
        <!-- END OF NAVBAR -->
<!-- START OF MAIN VIEW( Body) -->

	<div class = "row">
		<div class ="col s3">
		</div>
		
		<div class ="col s9">
		<div class ="row">
			<div class = "col s8">
	 <div class = "col s8 card-panel white hoverable">
    <div class ="col s8">
      <label>Nature of the Activity</label>
               <select>
                  <option value="" disabled selected>Select Nature of Activity</option>
                  <option value="1">Academic</option>
                  <option value="2">Special Interest</option>
                  <option value="3">Departmental Initiative</option>
                  <option value="4">Fundraising</option>
                  <option value="5">Community Engagement</option>
                  <option value="6">Organizational Development</option>
                  <option value="7">Issue Advocacy</option>
                  <option value="8">Lasallian Formation/Spiritual Growth</option>
                  <option value="9">Outreach</option>
               </select>
   </div>
    <div class ="col s8">
      <label>Type of Activity</label>
               <select>
	                  <option value="" disabled selected>Select Type of Activity</option>
	                  
	                  <c:forEach items = "${eventTypeList }" var = "e">
	                  		<option value = "${e.eventtypeID }" id = "${e.eventtypeID }">${e.eventtype }</option>
	                  </c:forEach>
	                  
               </select>
   </div>


   <div class ="col s8">
      <h5> Post-Activity Requirements</h5>
      <ol>
      	<c:forEach items = "${reqList }" var = "r">
      		<li>${r.reqName }</li>
      	</c:forEach>
      
        <!-- <li>Pre-Activity Requirements</li>
        <li>General Attendance Log-Sheet</li>
        <li>List of Expenses</li>
        <li>List of Pictures</li>
        <li>Activity Report</li>
         -->
      </ol>
   </div>
   <div class ="col s8">
    <h5> Downloadable Form(s)</h5>   
     <a href="sample.pdf" download = "">Click to Download!</a>
     <br>
   </div> 
   </div>
		  </div>
			 <div class = "col s4">
    <div class="card-panel white hoverable">
      <h5>  Upcoming Deadlines</h5>
        <ul>
          <li>10/27/2016 | Event Name</li>
        </ul>
    </div>
  </div>
	.	</div>
	
	</div>
  </div>
<!-- END OF MAIN VIEW (Body) -->  
 <script>
 // Initialize collapse button
  $(".button-collapse").sideNav();
  // Initialize collapsible (uncomment the line below if you use the dropdown variation)
  //$('.collapsible').collapsible();
</script>

</body>
</html>