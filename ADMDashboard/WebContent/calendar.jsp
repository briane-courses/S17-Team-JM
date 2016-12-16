<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
  <link href='css/calendar/calendar.css' rel='stylesheet' />
  <link href="css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection"/>
  
	<link href='css/calendar/fullcalendar.css' rel='stylesheet' />
	<link href='css/calendar/fullcalendar.print.css' rel='stylesheet' media='print' />
	<script src='js/calendar/moment.min.js'></script>
  	<script src="js/jquery-3.0.0.min.js"></script>
  	<script src="js/calendar/jquery-migrate-3.0.0.js"></script>
  	<script src="js/calendar/jquery-ui.min.js"></script>
	<script src='js/calendar/fullcalendar.min.js'></script>
	<% if(session.getAttribute("usertype").equals("ADMIN")) { %>
	<script src='js/calendar/admincalendar.js'></script>
    
	<% } else {%>
	<script src='js/calendar/usercalendar.js'></script>
    
	<% } %>
	
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
		<form id="logoutform" action="LogoutServlet" method="POST">
		</form>
 <script>
	function signOut() {
		gapi.load('auth2', function() {
			gapi.auth2.init();
		});
		var auth2 = gapi.auth2.getAuthInstance();
		auth2.signOut().then(function() {
			console.log('User signed out.');

			$("#logoutform").submit();
		});
	}

		function onLoad() {
			gapi.load('auth2', function() {
				gapi.auth2.init();
			});
		}
		$(document).ready(function() {
			$(".home").click(function() {
				$("#dashboard").submit();
			});
			$(".home2").click(function() {
				$("#calendarForm").submit();
			});
			$(".home3").click(function() {
				$("#orglist").submit();
			});
		$('.modal-trigger').leanModal();
         $('select').material_select();
 		$(".button-collapse").sideNav();
      });


     
  </script>

  <script src="https://apis.google.com/js/platform.js?onload=onLoad" async defer></script>
  
<header><!-- START OF NAVBAR --> 
 <form id="dashboard" action="HomeAdminServlet" method="post">
		<input name="course" type="hidden" />
	</form>
	<form id="calendarForm" action="CalendarOrgRepServlet" method="post">
		<input name="course" type="hidden" />
	</form>
	<form id="orglist" action="OrglistAdminServlet" method="post">
		<input name="course" type="hidden" />
	</form>
	<div class="container">
		<a class="button-collapse top-nav full hide-on-large-only" href="#"
			data-activates="slide-out"> <i class="material-icons medium">menu</i>
		</a>
	</div>

	<ul id="slide-out" class="side-nav fixed"
		style="width: 300px; transfor: translateX(-100%);">
		<li>
			<div class="userView">
				<img class="background" src="images/office.jpg"> <a
					href="#!user"><img class="circle" src="${logoURL}"></a> <a
					href="#!name"><span class="white-text name">${orgcode}</span></a> <a
					href="#!email"><span class="white-text email">${email}</span></a>
			</div>
		</li>
		<li class = "active">
				<a href="#" class="home"><i class="material-icons left">dashboard</i>Dashboard</a>
			</li>
		<li><div class="divider"></div></li>
		<li>
				<a href="#" class="home2"><i class="material-icons left">today</i>Calendar of
					special deadlines</a>
			</li>
		<li>
				<a href="#" class="home3"><i class="material-icons left">view_list</i>List of
					Organizations</a>
			</li>
		<li><a href="#modal1" class="modal-trigger"><i
				class="material-icons left">settings</i>Sign Out</a></li>
	</ul>
<!-- END OF NAVBAR --></header>
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
  <!-- Modal Structure -->
  <div id="modal1" class="modal">
		<div class="modal-content">
			<p>Are you sure you want to sign out?</p>
		</div>
		<div class="modal-footer">
			<a href="#!" onclick="signOut(); "
				class=" modal-action modal-close waves-effect waves-green btn-flat">Log
				out </a>
		</div>
	</div>
	<% if(session.getAttribute("usertype").equals("ADMIN")) { %>
	<div id='event_modal' class='modal modal-fixed-footer'>
    <div class='modal-content'>
    <div class='row'>
    <div class='col'>
      <h4 id='event_modal_header'>Event Title</h4>
      <div class='divider'></div>
      <br/>
      <div id='event_modal_content'>Details</div>
	  </div>
	  </div>
	<div class='row'>
		<div class="input-field">
			<input value="" id="event_modal_id" type="hidden">
			<input value="" id="event_modal_date" type="date" class="datepicker">
			<label class="active" for="event_modal_date">Deadline</label>
		</div>
	</div>
	
	<div class='row'>
		<div class="input-field">
		<select value="" id="event_modal_status" class="browser-default">
      <option value="DONE" selected>Done</option>
      <option value="PENDING">Pending</option>
      <option value="NOT_PASSED">Not Passed</option>
    </select>
		</div>
	</div>
	<div class='row'>
		<a class="waves-effect waves-light btn green" onclick="updateEventEdit();$('#event_modal').closeModal();">Save Changes</a>
		<a class="waves-effect waves-light btn grey" onclick="$('#event_modal').closeModal();">Back</a>
	</div>
	</div>
	</div>
	<% } %>
  <!--  end of modal structure. --> 
</body>
</html>