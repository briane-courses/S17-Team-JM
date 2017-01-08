<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1.0" />
<meta name="google-signin-scope" content="profile email">
<meta name="google-signin-client_id"
	content="20332845396-pvv6j3ifeu0391esi12rdcill7tmq0u7.apps.googleusercontent.com">


<title>Admin Dashboard ADM</title>

<!-- CSS  -->
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<link href="css/materialize.css" type="text/css" rel="stylesheet"
	media="screen,projection" />


<script src="https://apis.google.com/js/platform.js" async defer>
	
</script>

</head>

<body>

	<!--  Scripts-->
	<!--Import jQuery before materialize.js-->
	<script src="js/jquery-3.0.0.min.js"></script>
	<script src="js/jquery.autocomplete.js"></script>
	<script src="js/materialize.js"></script>
	<form id="logoutform" action="LogoutServlet" method="POST"></form>
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
			$('select').material_select();
			$(".home").click(function() {
				$("#dashboard").submit();
			});
			$(".home2").click(function() {
				$("#calendar").submit();
			});
			$(".home3").click(function() {
				$("#orglist").submit();
			});
		});
		
		var id;
		
		function viewEventDetails(obj) {
    		id = obj.id;
    		console.log(id);
    		var idSplit = id.split(" , ");
    		document.getElementById("eventName").innerHTML = idSplit[1] ;
    		
    		 $.ajax({
				url: "EventDetailsServlet",
				method: "post",
				data: {
					eventID : idSplit[0]
				},
				 dataType: "json",
				success: function(json) {
					console.log("JSON viewEventDetails SUCCESS " + idSplit[0]);
					console.log(json);
					document.getElementById("orgName").innerHTML = json[1];
					var deadline = json[2].split("-");
					document.getElementById("deadline").value = json[2] ;
				}
			});
		}
		
		function saveDate(obj) 
		{
			console.log("CLICKED SAVE");
			var idSave = id.split(" , "); 
			console.log(idSave[0]);
			var newDate = document.getElementById("deadline").value;
			console.log("NEWDATE" + newDate)
			var action = "update";
			$.ajax({
				url: "UpdateDeadlineServlet",
				method: "post",
				data: {
					eventID : idSave[0],
					newDate : newDate,
					action : action
				},
				 dataType: "json",
				success: function(json) {
					console.log("JSON saveDate SUCCESS " + idSave[0]);
					console.log("NEW DATE : " + document.getElementById(idSave + "-eventDeadline").value)
				}
			});
			console.log("RELOAD THE FREAKING LIST");
			location.reload();
		};
		
		function markasdone()
		{
			console.log(id);
			var idSplit = id.split(" , ");
			
			$.ajax({
				url: "MarkAsDoneServlet",
				method: "post",
				data: {
					eventID : idSplit[0]
				},
				success: function(result) {
					console.log(result);
				}
			});
			console.log("RELOAD THE FREAKING LIST");
			location.reload();
		}
		
		
	</script>

	<script src="https://apis.google.com/js/platform.js?onload=onLoad"
		async defer></script>
	<header> <!-- START OF NAVBAR -->  
	
	<form id="dashboard" action="HomeAdminServlet" method="post">
		<input name="course" type="hidden" />
	</form>
	<form id="calendar" action="CalendarOrgRepServlet" method="post">
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

	<!-- END OF NAVBAR --> </header>
	<div class="row">
		<div class="col s3"></div>

		<div class="col s9">
	
		<!-- START OF MAIN VIEW( Body) -->
		
		
		<div class="col s4">
			<form id="searchform" action="SearchEventServlet" method="POST">
				<div class="input-field">
					<input type="search" name="search" id="search" required> <label
						for="search"><i class="material-icons">search</i></label> <i
						class="material-icons">close</i>
				</div>
			</form>
		</div>
		<!-- TABLE -->
		<div class="row">
			<table class="bordered">
				<thead>
					<tr>
						<th data-field="orgCode">Organization</th>
						<th data-field="eventName">Event</th>
						<th data-field="eventDeadline">Deadline</th>
						<th data-field="editDeadline">Edit</th>
						<th data-field="eventStatus">Status</th>
					</tr>
				</thead>

				<tbody>
					<!-- loop START -->
					<c:forEach items="${eventList}" var="o" varStatus="status">
						<tr id="eventList">
							<td>${o.orgcode}</td>
							<td>${o.eventname}</td>
							<td id="${o.eventID}-eventDeadline">${eventStrDates[status.index]}</td>
							<td>
								<button id="${o.eventID} , ${o.eventname}"
									onclick="viewEventDetails(this)" data-target="modal2"
									class="btn modal-trigger btn-floating waves-effect waves-light tooltipped"
									data-position="bottom" data-delay="50"
									data-tooltip="Edit Event Deadline">
									<i class="material-icons right">mode_edit</i>
								</button>
								<div id="modal2" class="modal">
									<div class="modal-content">
										<h4 id="eventName">Event Name</h4>
										<br>
										<h7 id="orgName">Org Name</h7>
										<br>
										<h7>Deadline: </h7>
										<div class="row">
											<div class="">
												<input id="deadline" type="date" class="datepicker">
											</div>
										</div>
									</div>
									<div class="modal-footer">
										<a data-target="modal3" id="${o.eventID}" class="modal-trigger modal-close waves-effect waves-green btn-flat green-text text-darken-3" onclick="saveDate(this)">SAVE</a> 
										<a href="#!" class=" modal-action modal-close waves-effect waves-green btn-flat red-text">CANCEL</a>
										<a data-target="modal4" class="modal-trigger modal-close waves-effect waves-green btn-flat blue-text left">MARK AS DONE</a>
              						</div>
								</div>
							<!-- SAVE NEW DEADLINE CONFIRMATION MODAL -->
				            <div id="modal3" class="modal">
				              <div class="modal-content">
				                		<h3>Are you sure you want to mark event as done?</h3>
				              </div>
				              <div class="modal-footer">
				                		<a class=" modal-action modal-close waves-effect waves-green btn-flat green-text text-darken-3">YES</a>
							            <a class=" modal-action modal-close waves-effect waves-green btn-flat red-text">NO</a>
				              </div>
				            </div>
				            <!-- END SAVE NEW DEADLINE CONFIRMATION MODAL -->
				            <!-- MARK AS DONE CONFIRMATION MODAL -->
				            <div id="modal4" class="modal">
				              <div class="modal-content">
				                		<h3>Are you sure you want to mark event as done?</h3>
				              </div>
				              <div class="modal-footer">
				                		<a class=" modal-action modal-close waves-effect waves-green btn-flat green-text text-darken-3" onclick = "markasdone()">YES</a>
							            <a class=" modal-action modal-close waves-effect waves-green btn-flat red-text">NO</a>
				              </div>
				            </div>
				            <!-- END MARK AS DONE CONFIRMATION MODAL -->
				              </td>
							<c:choose>
								<c:when test="${deadlineType[status.index] == '1' }">
									<td><i class="small material-icons"
										style="color: red; font-size: 2.6rem;">warning</i></td>
									</td>
								</c:when>
								<c:when test="${deadlineType[status.index] == '2' }">
									<td><i class="small material-icons"
										style="color: orange; font-size: 2.6rem;">error_outline</i></td>
									</td>
								</c:when>
								<c:otherwise>
									<td><i class="small material-icons"
										style="font-size: 2.6rem;">info_outline</i></td>
									</td>
								</c:otherwise>
							</c:choose>
						</tr>

					</c:forEach>
					<!-- loop END -->

				</tbody>
			</table>
			<label id="noEventFound">${noEventFound }</label>
		</div>
		<!-- END OF TABLE -->

		<!-- END OF MAIN VIEW (Body) -->
	</div>
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
	</div>
	
	<script>
		// Initialize collapse button
		$(".button-collapse").sideNav();
		// Initialize collapsible (uncomment the line below if you use the dropdown variation)
		//$('.collapsible').collapsible();
	</script>
	<script>
		$(document).ready(function() {
			// the "href" attribute of .modal-trigger must specify the modal ID that wants to be triggered
			$('.modal-trigger').leanModal();
		});
	</script>

</body>
</html>