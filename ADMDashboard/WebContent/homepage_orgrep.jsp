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


<title>Dashboard ADM</title>

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
	<script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
	<script src="js/materialize.js"></script>


	<script>
   
			
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
			});
			
			// for ajax
			function clickedType(id) {
				var reqID = "#reqList" + id;
				$.ajax({
					url: "RequirementsServlet",
					method: "post",
					data: {
						eventtypeID : id
					},
					dataType: "json",
					success: function(json) {
						$(reqID).empty();
						for(var i = 0; i < json.length; i ++) {
							console.log("<li>" + json[i].reqName + "</li>");
							$(reqID).append("<li "+"class =" +"itemList" +">" + json[i].reqName + "</li>");
						}
					}
				});
			}
			
		</script>

	<script src="https://apis.google.com/js/platform.js?onload=onLoad"
		async defer></script>

	<!-- START OF NAVBAR -->
	<form id="dashboard" action="HomeOrgRepServlet" method="post">
		<input name="course" type="hidden" />
	</form>
	<form id="calendar" action="CalendarOrgRepServlet" method="post">
		<input name="course" type="hidden" />
	</form>

	<ul id="slide-out" class="side-nav fixed">
		<li>
			<div class="userView">
				<img class="background" src="images/office.jpg"> <a
					href="#!user"><img class="circle" src="${logoURL }"></a> <a
					href="#!name"><span class="white-text name">${orgcode }</span></a>
				<a href="#!email"><span class="white-text email">${email }</span></a>
			</div>
		</li>
		<li class="active"><a href="#" class="home"><i
				class="material-icons">dashboard</i>Dashboard</a></li>
		<li><a href="#" class="home2"><i class="material-icons">today</i>Calendar
				of special deadlines</a></li>
		<li>
			<div class="divider"></div>
		</li>
		<li><a href="#modal1" class="modal-trigger"><i
				class="material-icons">settings</i>Sign Out</a></li>
	</ul>

	<a href="#" data-activates="slide-out" class="button-collapse"><i
		class="material-icons">menu</i></a>
	<!-- END OF NAVBAR -->
	<!-- START OF MAIN VIEW( Body) -->

	<div class="row">
		<div class="col s3"></div>

		<div class="col s9">
			<div class="row">
				<div class="col s7">
					<div class="col s12">
						<h5>Post-Activity Requirements</h5>

						<a class="waves-effect waves-light btn" href=""
							download="CSO1617_Activity_Report"> <i
							class="material-icons left">get_app</i>Download Post-Acts Form
						</a> <br> <br>
						<h6>Other requirements per activity</h6>
						<label>Click to see the list of other requirements per
							type of activity</label>

						<ul class="collapsible" data-collapsible="accordion">
							<c:forEach items="${eventTypeList }" var="e">
								<li>
									<div id="${e.eventtypeID }" class="collapsible-header"
										onclick="clickedType(${e.eventtypeID })">
										<i id="i${e.eventtypeID }" class="material-icons left rList"
											value="add">add</i><b> ${e.eventtype }</b>

									</div>

									<div class="collapsible-body">
										<ol id="reqList${e.eventtypeID }" class="deadlineList">
										</ol>
									</div>
								</li>
							</c:forEach>
						</ul>
					</div>

				</div>

				<div class="col s5">
					<h5>Deadlines</h5>
					<label>Click to see the list of events</label>

					<ul class="collapsible" data-collapsible="accordion">
						<li>
							<div class="collapsible-header red-text">
								<i class="material-icons ">warning</i>Overdue
							</div>
							<div class="collapsible-body">
								<ul class="deadlineList" id="overDueList">
									<c:forEach items="${overdueDeadlines}" var="o"
										varStatus="status">
										<li class="itemList">${overdueList[status.index]}|
											${o.eventname}</li>

									</c:forEach>
									<h9>${noOverdueDeadlines}</h9>
								</ul>
							</div>
						</li>
						<li>
							<div class="collapsible-header orange-text">
								<i class="material-icons">error_outline</i>Due in 1 week
							</div>
							<div class="collapsible-body">
								<ul class="deadlineList" id="deadlineList1">
									<c:forEach items="${pendingPostActList1}" var="p"
										varStatus="status">
										<li class="itemList">${due1DateList[status.index]}|${p.eventname}</li>
									</c:forEach>
									<h9>${noPendingPostActList1}</h9>
								</ul>
							</div>
						</li>
						<li>
							<div class="collapsible-header">
								<i class="material-icons">info_outline</i>Due in 2 weeks
							</div>
							<div class="collapsible-body">
								<ul class="deadlineList" id="deadlineList2">
									<c:forEach items="${pendingPostActList2}" var="q"
										varStatus="status">
										<li class="itemList">${due2DateList[status.index]}|${q.eventname}</li>
									</c:forEach>
									<h9>${noPendingPostActList2}</h9>
								</ul>
							</div>
						</li>
						<li>
							<div class="collapsible-header grey-text">
								<i class="material-icons">list</i>Others
							</div>
							<div class="collapsible-body">
								<ul class="deadlineList" id="otherdeadlineList">
									<c:forEach items="${otherDeadlines}" var="r" varStatus="status">
										<li class="itemList">${otherDueDateList[status.index]}|${r.eventname}</li>
									</c:forEach>
								</ul>
							</div>
						</li>
					</ul>
				</div>
			</div>

		</div>
	</div>
	<!-- END OF MAIN VIEW (Body) -->
	<!-- Modal Structure -->
	<!-- <div id="modal1" class="modal">
		<div class="modal-content">
			<p>Are you sure you want to sign out?</p>
		</div>
		<div class="modal-footer">
			<a href="#!" onclick="signOut(); "
				class=" modal-action modal-close waves-effect waves-green btn-flat">Log
				out </a>
		</div>
	</div>
	 -->
	<!--  end of modal structure. -->
	<script>
 // Initialize collapse button
  $(".button-collapse").sideNav();
  $(document).ready(function() {
	   $('.modal-trigger').leanModal();	
		 
        $('select').material_select();
        
     });
  
  $('.collapsible-header').click(function() {
	 
	  console.log("toggled");
	  //console.log($("i").val());
	  console.log($(this).attr('id'));
	  var headerID =  $(this).attr('id');
	  if($('#' +headerID).attr('class') == "collapsible-header active"){
		  console.log("true");
		  $('#i' +headerID).val("add");
		  $('#i' +headerID).text("add");
  	  }else if (!(headerID == null)){
  		  $(".rList").val("add");
  		  $(".rList").text("add");
  		  $('#i' +headerID).val("remove");
		  $('#i' +headerID).text("remove");  
  	  }
	});
 
  // Initialize collapsible (uncomment the line below if you use the dropdown variation)
  //$('.collapsible').collapsible();
</script>

</body>
</html>
