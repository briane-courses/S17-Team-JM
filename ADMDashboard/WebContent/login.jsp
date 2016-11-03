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


 <title>LOG IN</title>

  <!-- CSS  -->
  <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
  <link href="css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection"/>
  <link href="css/style.css" type="text/css" rel="stylesheet" media="screen,projection"/>

  <script src="https://apis.google.com/js/platform.js" async defer></script>

  </head>
<body>
  <main>
    <center>
 
      <div class="section"></div>
	<div class="row">
	<div class ="col s2 offset-s5">
	<img class="responsive-img center" src="cso.jpg"></div></div>
	</div>
            <div class='row'>
			<div class="g-signin2" data-onsuccess="onSignIn">Sign in</div><div class="section"></div> 
		</div>
		<form id="loginform" action="LoginServlet" method="POST">
			<input type="hidden" name="email" id="email" />
		</form>
		<h5 class="indigo-text">What is ADM?</h5>
	<h6 class = "center flow-text"> The Activity Documentations and Management Team is the executive team of the Council of Student Organizations that takes charge of the documentation of the post-activity requirements of all accredited organizations. </h6>
	
	</center>

    <div class="section"></div>
    <div class="section"></div>
	
	<div class ="row">
	<div class = "col s1">
	</div>
	<div class = "col s2 center">AdCreate Society </div>
	<div class = "col s2 center">Business Management Society</div>
	<div class = "col s2 center">Economics Organization</div>
	<div class = "col s2 center">Junior Entrepreneurs Marketing Associations</div>
	<div class = "col s2 center">Junior Philippine Institute of Accountants </div>
	<div class = "col s1">
	</div>
	</div>
		<div class ="row">
	<div class = "col s1">
	</div>
	<div class = "col s2 center">Ley La Salle</div>
	<div class = "col s2 center">Management of Financial Institution Association</div>
	<div class = "col s2 center">Young Entrepreneurs Society </div>
	<div class = "col s2 center">La Salle Computer Society</div>
	<div class = "col s2 center">Union of Students Inspired Towards Education  </div>
	<div class = "col s1">
	</div>
	</div>
		<div class ="row">
	<div class = "col s1">
	</div>
	<div class = "col s2 center">Civil Engineering Society </div>
	<div class = "col s2 center">Chemical Engineering Society </div>
	<div class = "col s2 center">Electronic and Communications Engineering Society </div>
	<div class = "col s2 center">Industrial Management Engineering Society</div>
	<div class = "col s2 center">Mechanical Engineering Society  </div>
	<div class = "col s1">
	</div>
	</div>
		<div class ="row">
	<div class = "col s1">
	</div>
	<div class = "col s2 center">Society of Manufacturing Engineering </div>
	<div class = "col s2 center">Association of Computer Engineering Students </div>
	<div class = "col s2 center">Behavioral Science Society</div>
	<div class = "col s2 center">European Studies Association</div>
	<div class = "col s2 center">Literature Circle  </div>
	<div class = "col s1">
	</div>
	</div>
		<div class ="row">
	<div class = "col s1">
	</div>
	<div class = "col s2 center">Nihon Kenkyu Kai </div>
	<div class = "col s2 center">Political Science Society </div>
	<div class = "col s2 center">Samahan ng mga Mag-aaral sa Sikolohiya </div>
	<div class = "col s2 center">Students of Philosophy in Action </div>
	<div class = "col s2 center">Team Communications </div>
	<div class = "col s1">
	</div>
	</div>

	</main>

  <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.2.1/jquery.min.js"></script>
  <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.5/js/materialize.min.js"></script>
<script>

	$(document).ready(function() {
		var auth2 = gapi.auth2.getAuthInstance();
	    auth2.signOut().then(function () {
	        console.log('User signed out.');
	    });
	});

      function onSignIn(googleUser) {
	  
	  /*window.location.href = "homepage.html";*/
        // Useful data for your client-side scripts:
        var profile = googleUser.getBasicProfile();
        console.log("ID: " + profile.getId()); // Don't send this directly to your server!
        console.log('Full Name: ' + profile.getName());
        console.log('Given Name: ' + profile.getGivenName());
        console.log('Family Name: ' + profile.getFamilyName());
        console.log("Image URL: " + profile.getImageUrl());
        console.log("Email: " + profile.getEmail());

        // The ID token you need to pass to your backend:
        var id_token = googleUser.getAuthResponse().id_token;
        console.log("ID Token: " + id_token);
		
		
		
		$("#email").val(profile.getEmail());
		$("#loginform").submit();
		
      };
	  
	  
	  
    </script>

</body>
</html>