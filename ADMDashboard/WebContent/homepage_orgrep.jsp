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
 
  
    <script src="https://apis.google.com/js/platform.js" async defer></script>
    <script type="text/javascript" src = "js/jquery-3.0.0.min.js"></script>
	

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
				auth2.signOut().then(function() {
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
			
			// for ajax
			function changed_activity_type() {
				var id = $("#activity_type").find(":selected").val();
				$.ajax({
					url: "RequirementsServlet",
					method: "post",
					data: {
						eventtypeID : id
					},
					dataType: "json",
					success: function(json) {
						console.log(json);
						$("#reqList_jsp").empty();
						for(var i = 0; i < json.length; i ++) {
							console.log(json[i].reqName);
							$("#reqList_jsp").append("<li>" + json[i].reqName + "</li>");
						}
					}
				});
			}
			
		</script>

  <script src="https://apis.google.com/js/platform.js?onload=onLoad" async defer></script>
  
<!-- START OF NAVBAR --> 
 <div class="navbar-fixed">
 <nav class = "green">
    <div class="nav-wrapper">
   
      <ul id="nav-mobile" class="right hide-on-med-and-down">
        <li> <a href="#" onclick="signOut();"><i class="material-icons">settings</i>Settings/Sign Out</a></li>
		
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
			<li><a href="homepage_orgrep.jsp"><i class="material-icons">dashboard</i>Dashboard</a></li>
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
	 <div class = "col s12 card-panel white hoverable">
    <div class ="col s8">
    <br>
      
   </div>
    <div class ="col s8">
    <h5>Type of Activity</h5>
               <select id = "activity_type" onchange = "changed_activity_type()">
	                  <option value="" disabled selected>Select Type of Activity</option>
	                  
	                  <c:forEach items = "${eventTypeList }" var = "e">
	                  		<option value = "${e.eventtypeID }" id = "${e.eventtypeID }">${e.eventtype }</option>
	                  </c:forEach>
	                  
               </select>
   </div>


   <div class ="col s8">
      <h5> Post-Activity Requirements</h5>
      <ol id = "reqList_jsp">
      	<label>Please select a type of activity first.</label>
      </ol>
      
   </div>
   
   <a class="waves-effect waves-light btn deadlineList" href = "sample.pdf" download = ""><i class="material-icons left" >get_app</i>Download Form</a>
   	
  
   </div>
	</div>
	
	<div class="col s4">
		<div class="card-panel white hoverable">
			<h4>Deadlines</h4>
			<label>Click the deadlines to see the list of events</label>
			<ul class="collapsible" data-collapsible="accordion">
	          <li>
	            <div class="collapsible-header overdue" ><i class="material-icons">warning</i>Overdue</div>
	            <div class="collapsible-body">
	            
	              <ul id="overDueList" class = "deadlineList">
					<c:forEach items="${overdueDeadlines}" var="o" varStatus="status">
						<li>${overdueList[status.index]} | ${o.eventname}</li>
					</c:forEach>
        			<h9>${noOverdueDeadlines}</h9>
				</ul>
	          </li>
	          <li>
	            <div class="collapsible-header week1" ><i class="material-icons">error_outline</i>Due in 1 week</div>
	            <div class="collapsible-body">
	              <ul id="deadlineList1" class = "deadlineList">
					<c:forEach items="${pendingPostActList1}" var="p" varStatus="status">
						<li>${due1DateList[status.index]} | ${p.eventname}</li>
					</c:forEach>
					<h9>${noPendingPostActList1}</h9>
				</ul>
	            </div>
	          </li>
	          <li>
	            <div class="collapsible-header week2" ><i class="material-icons">info_outline</i>Due in 2 weeks</div>
	            <div class="collapsible-body">
	              <ul id="deadlineList2" class = "deadlineList">
					<c:forEach items="${pendingPostActList2}" var="q" varStatus="status">
						<li>${due2DateList[status.index]} | ${q.eventname}</li>
					</c:forEach>
					 <h9>${noPendingPostActList2}</h9>
				</ul>
	            </div>
	          </li>
	          <li>
	            <div class="collapsible-header other-week" ><i class="material-icons">thumb_up</i>Others</div>
	            <div class="collapsible-body">
	              <ul id="otherdeadlineList" class = "deadlineList">
					<c:forEach items="${otherDeadlines}" var="r" varStatus="status">
						<li>${otherDueDateList[status.index]} | ${r.eventname}</li>
					</c:forEach>
					<h9>${noOtherDeadlines}</h9>
				</ul>
	            </div>
	          </li>
	        </ul>
	</div>
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

</body>
</html>
