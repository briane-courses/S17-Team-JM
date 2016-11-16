<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0"/>
     <meta name="google-signin-scope" content="profile email">
    <meta name="google-signin-client_id" content="20332845396-pvv6j3ifeu0391esi12rdcill7tmq0u7.apps.googleusercontent.com">
  <title>Special Calendar</title>

  <!-- CSS  -->
  <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
  <link href="css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection"/>
  <link href="css/style.css" type="text/css" rel="stylesheet" media="screen,projection"/>
  
	<link href='css/fullcalendar.css' rel='stylesheet' />
	<link href='css/fullcalendar.print.css' rel='stylesheet' media='print' />
	<script src='js/moment.min.js'></script>
  <script src="js/jquery-3.0.0.min.js"></script>
	<script src='js/fullcalendar.min.js'></script>
	<script src='js/calendar.js'></script>
	
  <!--  Scripts-->
  <!--Import jQuery before materialize.js-->
  <script src="https://apis.google.com/js/client.js?onload=handleClientLoad"></script>
  <script src="js/materialize.js"></script>
  
  <style>

	#calendar {
		max-width: 900px;
		margin: 0 auto;
	}
	
	#loading {
		display: none;
		position: absolute;
		top: 10px;
		right: 10px;
	}

</style>
  
</head>

<body>
  <script>
    function signOut() {
	    gapi.load('auth2', function() {
        gapi.auth2.init();
      });
      var auth2 = gapi.auth2.getAuthInstance();
      auth2.signOut().then(function () {
        console.log('User signed out.');
		window.location.href = "index.html";
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
        <li> <a href="#" onclick="signOut();"><i class="material-icons">settings</i></a></li>
		  <li><a href="#"><i class="material-icons">add_alert</i></a></li>
      </ul>
    </div>
  </nav>
</div>

 <ul id="slide-out" class="side-nav fixed">
			<li><div class="userView">
			  <img class="background" src="images/office.jpg">
			  <a href="#!user"><img class="circle" src="images/cso.jpg"></a>
			  <a href="#!name"><span class="white-text name">ORG NAME(org image above) </span></a>
			  <a href="#!email"><span class="white-text email">orgname@gmail.com</span></a>
			</div></li>
			<li><a href="homepage.html"><i class="material-icons">dashboard</i>Dashboard</a></li>
						<li><div class="divider"></div></li>
			<li><a href="calendar.html"><i class="material-icons">today</i>Calendar of special deadlines</a></li>
		  </ul>
		  
  <a href="#" data-activates="slide-out" class="button-collapse"><i class="material-icons">menu</i></a>
<!-- END OF NAVBAR -->
<!-- START OF MAIN VIEW( Body) -->
<div class ="row" >
	<div class ="col s3" >
	</div>
	<div class ="col s9">
<div class = "container">
<div id='loading'>loading...</div>
<div id='calendar'></div>

</div>
	</div>
</div>
<!-- END OF MAIN VIEW (Body) -->  

 
<script src="js/calendar.js"></script>
</body>
</html>