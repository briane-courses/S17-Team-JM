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
  
  <title>Invalid Login Details</title>
  <script src="https://apis.google.com/js/platform.js" async defer></script>
</head>
<body>
  <!--  Scripts-->
  <!--Import jQuery before materialize.js-->
  <script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
  <script src="js/materialize.js"></script>
  <script>
  function onLoad() {
	  gapi.load('auth2', function () {
		   var auth2 = gapi.auth2.init({
		       client_id: '20332845396-pvv6j3ifeu0391esi12rdcill7tmq0u7.apps.googleusercontent.com',

		   });
		   auth2.then(function(){
		        // this get called right after token manager is started
		        auth2.signOut().then(function () {
		            console.log('User signed out.');
		    		window.location.href = "login.jsp";
		          });
		   }); 
		});
         
     }
  </script>
  <script src="https://apis.google.com/js/platform.js?onload=onLoad" async defer></script>
  INVALID LOGIN DETAILS!
</body>
</html>