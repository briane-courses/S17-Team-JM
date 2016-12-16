<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0"/>
        <meta name="google-signin-scope" content="profile email">
        <meta name="google-signin-client_id" content="20332845396-pvv6j3ifeu0391esi12rdcill7tmq0u7.apps.googleusercontent.com">
	
        <title>Admin Dashboard ADM</title>

        <!-- CSS-->
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link href="css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection"/>

        <script src="https://apis.google.com/js/platform.js" async defer></script>
    </head>
    <body>
        <!--Scripts-->
        <!--Import jQuery before materialize.js-->
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
        <!--End scripts-->
        
        <header>
            <!-- page title -->
            <nav class="top-nav">
                <div class="container">
                    <div class="nav-wrapper">
                        <a class="page-title">Page Title</a>
                    </div>
                </div>
            </nav>
            
            <!-- menu button for slide-out navbar -->
            <div class="container">
                <a class="button-collapse top-nav full hide-on-large-only" href="#" data-activates="slide-out">
                    <i class="material-icons medium">menu</i>
                </a>
            </div>
            
            <!-- side-nav -->
            <ul id="slide-out" class="side-nav fixed" style="width: 300px; transform: translateX(-100%);">
                <!-- user data -->
                <li class="center">
                    <div class="userView">
                        <div class="logo-container">
                            <a href="#!user"><img class="circle brand-logo center" src="${logoURL }"></a>
                        </div>
                        <!--
                            <a href="#!name">
                                <span class="name">ORG NAME</span>
                            </a>
                            <a href="#email">
                                <span class="email">orgname@gmail.com</span>
                            </a> 
                        -->
                    </div>
                </li>
                
                <li>
                    <a href="#!name"><i class="material-icons">person</i><span class="name">${orgcode }</span></a>
                </li>
                
                <li><div class="divider"></div></li>
                
                <li>
                    <a href="homepage_admin.jsp"><i class="material-icons">dashboard</i>Dashboard</a>
                </li>                
                
                <li><div class="divider"></div></li>
                
                <li>
                    <a href="calendar.jsp"><i class="material-icons">today</i>Calendar</a>
                </li>
                
                <li><div class="divider"></div></li>
                
                <li>
                    <a href="#modal1" class="modal-trigger"><i class="material-icons">settings</i>Sign Out</a>
                </li>
            </ul>
        </header>
        
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
        
        <!-- scripts -->
        <script>
 // Initialize collapse button
  $(".button-collapse").sideNav();
  // Initialize collapsible (uncomment the line below if you use the dropdown variation)
  //$('.collapsible').collapsible();
</script>
<script >
  $(document).ready(function(){
    // the "href" attribute of .modal-trigger must specify the modal ID that wants to be triggered
    $('.modal-trigger').leanModal();
  });
</script>
    </body>
</html>