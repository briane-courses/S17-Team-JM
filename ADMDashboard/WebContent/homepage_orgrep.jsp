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
				$('select').material_select();
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
							$(reqID).append("<li>" + json[i].reqName + "</li>");
						}
					}
				});
			}
			
		</script>

  <script src="https://apis.google.com/js/platform.js?onload=onLoad" async defer></script>
  
<!-- START OF NAVBAR --> 
<div class = "navbar-fixed">
 	<nav class = "top-nav green right">
    <div class="nav-wrapper">
   		<a href="#" class="brand-logo center">Logo</a>
      	<ul id="nav-mobile" class="right hide-on-med-and-down">
        <!-- Modal Trigger -->
        	<li></li>
		
      </ul>
    </div>
  </nav>
 </div>
 


 <ul id="slide-out" class="side-nav fixed">
			<li>
				<div class="userView">
				  <img class="background" src="images/office.jpg">
				  <a href="#!user"><img class="circle" src="${logoURL }"></a>
				  <a href="#!name"><span class="white-text name">${orgcode }</span></a>
				  <a href="#!email"><span class="white-text email">${email }</span></a>
				</div>
			</li>
			<li>
				<a href="homepage_orgrep.jsp"><i class="material-icons">dashboard</i>Dashboard</a>
			</li>
			<li>
				<a href="calendar.jsp"><i class="material-icons">today</i>Calendar of special deadlines</a></li>
      		<li>
				<div class="divider"></div>
			</li>
			<li>
      			 <a href="#modal1" class="modal-trigger"><i class= "material-icons">settings</i>Sign Out</a>
      		</li>
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
    	<h5>
    		Post-Activity Requirements
    	</h5>
    	<a class="waves-effect waves-light btn" href="" download = "CSO1617_Activity_Report"> <i class="material-icons left">get_app</i>Download Post-Acts Form</a>
   		<br>
   		<br>
   		<h6>Other requirements per activity</h6>
   		<label>Click to see the list of other requirements per type of activity</label>
   		
      	<ul class="collapsible" data-collapsible="accordion">
      		<c:forEach items = "${eventTypeList }" var = "e">
	          	<li>
	            	<div id = "${e.eventtypeID }" class="collapsible-header" onclick = "clickedType(${e.eventtypeID })">${e.eventtype }</div>
	            	<div class="collapsible-body">
	            	<ol id = "reqList${e.eventtypeID }" class = "deadlineList" style = "margin: 20px; ">
	            		
				    </ol>
          		</li>
          	</c:forEach>
          	<!-- 
            	<div class="collapsible-header">Acquaintance Party (On-Campus)</div>
            	<div class="collapsible-body">
              		<ul class = "deadlineList">
			        	<li>Requirement #1</li>
			        	<li>Requirement #2</li>
			        	<li>Requirement #3</li>
			        	<li>Requirement #4</li>
			        	<li>Requirement #5</li>
              		</ul>
          	</li>
          	<li>
            	<div class="collapsible-header">Concerts</div>
            	<div class="collapsible-body">
		        	<ul class = "deadlineList">
		            	<li>Requirement #1</li>
			            <li>Requirement #2</li>
			            <li>Requirement #3</li>
			            <li>Requirement #4</li>
			            <li>Requirement #5</li>
		            </ul>
            	</div>
          	</li>
          	<li>
		    	<div class="collapsible-header" >Contests</div>
		        <div class="collapsible-body">
		        	<ul class = "deadlineList">
		            	<li>Requirement #1</li>
			            <li>Requirement #2</li>
			            <li>Requirement #3</li>
			            <li>Requirement #4</li>
			            <li>Requirement #5</li>
		            </ul>
            	</div>
          	</li>
          	 -->
   		</ul>
   </div>
   </div>
	</div>
	
	<div class="col s4">
		<div class="card-panel white hoverable">
			<h5>Deadlines</h5>
			<label>Click to see the list of events</label>

			<ul class="collapsible" data-collapsible="accordion">
	          <li>
	            <div class="collapsible-header red-text"><i class="material-icons">warning</i>Overdue</div>
	            <div class="collapsible-body">
	              <ul class = "deadlineList" id="overDueList">
					<c:forEach items="${overdueDeadlines}" var="o" varStatus="status">
						<li>${overdueList[status.index]} | ${o.eventname}</li>
					</c:forEach>
        			<h9>${noOverdueDeadlines}</h9>
				</ul>
	          </li>
	          <li>
	            <div class="collapsible-header orange-text"><i class="material-icons">error_outline</i>Due in 1 week</div>
	            <div class="collapsible-body">
	              <ul class = "deadlineList" id="deadlineList1">
					<c:forEach items="${pendingPostActList1}" var="p" varStatus="status">
						<li>${due1DateList[status.index]} | ${p.eventname}</li>
					</c:forEach>
					<h9>${noPendingPostActList1}</h9>
				</ul>
	            </div>
	          </li>
	          <li>
	            <div class="collapsible-header" ><i class="material-icons">info_outline</i>Due in 2 weeks</div>
	            <div class="collapsible-body">
	              <ul class = "deadlineList" id="deadlineList2">
					<c:forEach items="${pendingPostActList2}" var="q" varStatus="status">
						<li>${due2DateList[status.index]} | ${q.eventname}</li>
					</c:forEach>
					 <h9>${noPendingPostActList2}</h9>
				</ul>
	            </div>
	          </li>
	          <li>
	            <div class="collapsible-header grey-text"><i class="material-icons">list</i>Others</div>
	            <div class="collapsible-body">
	              <ul class = "deadlineList" id="otherdeadlineList">
					<c:forEach items="${otherDeadlines}" var="r" varStatus="status">
						<li>${otherDueDateList[status.index]} | ${r.eventname}</li>
					</c:forEach>
				</ul>
	            </div>
	          </li>
	        </ul>
	</div>
	</div>
	</div>
	
	</div>
  </div>
<!-- END OF MAIN VIEW (Body) --> 
  <!-- Modal Structure -->
  <div id="modal1" class="modal">
    <div class="modal-content">
      <p>Are you sure you want to sign out? </p>
    </div>
    <div class="modal-footer">
      <a href="#!" onclick="signOut(); "class=" modal-action modal-close waves-effect waves-green btn-flat">Log out </a>
    </div>
  </div> 
  <!--  end of modal structure. --> 
 <script>
 // Initialize collapse button
  $(".button-collapse").sideNav();
  $(document).ready(function() {
	   $('.modal-trigger').leanModal();	
		 
        $('select').material_select();
     });
  // Initialize collapsible (uncomment the line below if you use the dropdown variation)
  //$('.collapsible').collapsible();
</script>

</body>
</html>