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


<script src="https://apis.google.com/js/platform.js" async defer></script>
<script type="text/javascript" src="js/jquery-3.0.0.min.js"></script>


</head>

<body>

	<!--  Scripts-->
	<!--Import jQuery before materialize.js-->
	<script src="js/materialize.js"></script>
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
     
    	function viewEvents(obj) {
    		var id = obj.id;
    		console.log(id);
    		
    		document.getElementById("orgHeader").innerHTML = id + "'s Events";
    		
    		$.ajax({
				url: "ViewEventsServlet",
				method: "post",
				data: {
					orgcode : id
				},
				dataType: "json",
				success: function(json) {
					
					var overdueEventsJson = json[0];
					var overdueDatesJson = json[1];
					var noOverdueStringJson = json[2];
					
					$("#overdueList").empty();
					if(overdueEventsJson.length == 0) {
						$("#overdueList").append("<li>" + noOverdueStringJson + "</li>");
					} else {
						for(var i = 0; i < overdueEventsJson.length; i ++) {
							$("#overdueList").append("<li>" + overdueDatesJson[i] + " | " + overdueEventsJson[i].eventname + "</li>");
						}
					}
					
					var due1weekEventsJson = json[3];
					var due1weekDatesJson = json[4];
					var nodue1weekStringJson = json[5];
					
					$("#due1weekList").empty();
					if(due1weekEventsJson.length == 0) {
						$("#due1weekList").append("<li>" + nodue1weekStringJson + "</li>");
					} else {
						for(var i = 0; i < due1weekEventsJson.length; i ++) {
							$("#due1weekList").append("<li>" + due1weekDatesJson[i] + " | " + due1weekEventsJson[i].eventname + "</li>");
						}
					}
					
					var due2weekEventsJson = json[6];
					var due2weekDatesJson = json[7];
					var nodue2weekStringJson = json[8];
					
					$("#due2weekList").empty();
					if(due2weekEventsJson.length == 0) {
						$("#due2weekList").append("<li>" + nodue2weekStringJson + "</li>");
					} else {
						for(var i = 0; i < due2weekEventsJson.length; i ++) {
							$("#due2weekList").append("<li>" + due2weekDatesJson[i] + " | " + due2weekEventsJson[i].eventname + "</li>");
						}
					}
					
					var dueNextEventsJson = json[9];
					var dueNextDatesJson = json[10];
					var nodueNextStringJson = json[11];
					
					$("#dueNextList").empty();
					if(dueNextEventsJson.length == 0) {
						$("#dueNextList").append("<li>" + nodueNextStringJson + "</li>");
					} else {
						for(var i = 0; i < dueNextEventsJson.length; i ++) {
							$("#dueNextList").append("<li>" + dueNextDatesJson[i] + " | " + dueNextEventsJson[i].eventname + "</li>");
						}
					}
				}
			});
    		
    	}

     
  </script>

	<script src="https://apis.google.com/js/platform.js?onload=onLoad"
		async defer></script>

	<!-- START OF NAVBAR -->
	<div class="navbar-fixed">
		<nav class="green">
		<div class="nav-wrapper">

			<ul id="nav-mobile" class="right hide-on-med-and-down">
				<li><a href="#" onclick="signOut();"><i
						class="material-icons">settings</i>Settings/Sign Out</a></li>
			</ul>
		</div>
		</nav>
	</div>

	<ul id="slide-out" class="side-nav fixed">
		<li>
			<div class="userView">
				<img class="background" src="images/office.jpg"> <a
					href="#!user"><img class="circle" src="${logoURL }"></a> <a
					href="#!name"><span class="white-text name">${orgcode }</span></a>
				<a href="#!email"><span class="white-text email">${email }</span></a>
			</div>
		</li>
		<li><a href="homepage_admin.jsp"><i class="material-icons">dashboard</i>Dashboard</a></li>
		<li><div class="divider"></div></li>
		<li><a href="calendar.jsp"><i class="material-icons">today</i>Calendar
				of special deadlines</a></li>
		<li><a href="orglist_admin.jsp"><i class="material-icons">view_list</i>List
				of Organizations</a></li>
	</ul>

	<a href="#" data-activates="slide-out" class="button-collapse"><i
		class="material-icons">menu</i></a>
	<!-- END OF NAVBAR -->
	<!-- START OF MAIN VIEW( Body) -->

	<div class="row">
		<div class="col s3"></div>

		<div class="col s9">
			<div class="col s8">
				<h4>Organizations</h4>
			</div>

			<div class="col s4 card-panel">
				<form>
					<div class="input-field">
						<input id="search" type="search"> <label
							for="search"><i class="material-icons">search</i></label> <i
							class="material-icons">close</i>
					</div>
				</form>
			</div>


			<ul class="collection">
				<c:forEach items="${orgList }" var="o">
					<li class="collection-item avatar">
						<div class="col s3">${o.orgcode }</div>
						<div class="col s6">${o.orgname }</div>
						<div class="col s3">
							<button id="${o.orgcode}" data-target="modal1"
								class="btn modal-trigger" onclick="viewEvents(this)">View
								Events</button>
							<!-- Modal Structure -->
							<div id="modal1" class="modal">
								<div class="modal-content">
									<h4 id="orgHeader">CSO's Events</h4>
									<div class="s12">
										<ul class="collapsible" data-collapsible="accordion">
											<li>
												<div class="collapsible-header red-text">
													<i class="material-icons">warning</i>Overdue
												</div>
												<div class="collapsible-body">
													<ul id="overdueList" class="deadlineList">

													</ul>
												</div>
											</li>
											<li>
												<div class="collapsible-header orange-text">
													<i class="material-icons">error_outline</i>Due in 1 week
												</div>
												<div class="collapsible-body">
													<ul id="due1weekList" class="deadlineList">

													</ul>
												</div>
											</li>
											<li>
												<div class="collapsible-header">
													<i class="material-icons">info_outline</i>Due in 2 weeks
												</div>
												<div class="collapsible-body">
													<ul id="due2weekList" class="deadlineList">

													</ul>
												</div>
											</li>
											<li>
												<div class="collapsible-header grey-text">
													<i class="material-icons">list</i>Others
												</div>
												<div class="collapsible-body">
													<ul id="dueNextList" class="deadlineList">

													</ul>
												</div>
											</li>
										</ul>
									</div>
								</div>
								<div class="modal-footer">
									<a href="#!"
										class=" modal-action modal-close waves-effect waves-green btn-flat">Close</a>
								</div>
							</div>
						</div>
					</li>
				</c:forEach>
			</ul>

		</div>
		.
	</div>

	</div>
	</div>
	<!-- END OF MAIN VIEW (Body) -->
	<script>
 // Initialize collapse button
  $(".button-collapse").sideNav();
  // Initialize collapsible (uncomment the line below if you use the dropdown variation)
  //$('.collapsible').collapsible();
</script>
	<script>
  $(document).ready(function(){
    // the "href" attribute of .modal-trigger must specify the modal ID that wants to be triggered
    $('.modal-trigger').leanModal();
  });
</script>

</body>
</html>