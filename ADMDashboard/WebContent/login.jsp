<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!-- saved from url=(0030)http://skilldpro.com/#services -->
<html lang="en"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		
		<meta name="google-signin-scope" content="profile email">
		<meta name="google-signin-client_id" content="20332845396-pvv6j3ifeu0391esi12rdcill7tmq0u7.apps.googleusercontent.com">
		
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta name="description" content="">
		<meta name="author" content="">
		<link rel="shortcut icon" type="image/png" href="images/cso.png">
		<title>CSO ADM</title>
		<link href="./login_files/bootstrap.min.css" rel="stylesheet">
		<link rel="stylesheet" href="./login_files/font-awesome.min.css">
		<link rel="stylesheet" href="./login_files/style.css">
	<script type="text/javascript" charset="UTF-8" src="./login_files/common.js"></script>
	<script type="text/javascript" charset="UTF-8" src="./login_files/util.js"></script>
	<script type="text/javascript" charset="UTF-8" src="./login_files/stats.js"></script>
	<script type="text/javascript" charset="UTF-8" src="./login_files/AuthenticationService.Authenticate"></script>
	
	
	<script src="https://apis.google.com/js/platform.js" async defer></script>
	</head>

	<body data-spy="scroll">
		<nav class="navbar navbar-inverse navbar-fixed-top">
			<div class="container">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
						<span class="sr-only">Toggle navigation</span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</button>
					<!--<a class="navbar-brand" href="index.html">
						<img src="images/logo.png" alt="company logo" />
					</a>-->
				</div>
				<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
					<ul class="nav navbar-nav navbar-right custom-menu">
						<li class="active"><a href="#home">Home</a></li>
						<li><a href="#about">About</a></li>
						<li ><a href="#probe">PROBE</a></li>
						<li><a href="#engage">ENGAGE</a></li>
						<li><a href="#cap12">CAP12</a></li>
						<li><a href="#aso">ASO</a></li>
						<li><a href="#aspire">ASPIRE</a></li>
					</ul>
				</div>
			</div>
		</nav>

		<!-- Header Carousel -->
		<header id="home" class="carousel slide">
			<ul class="cb-slideshow">
				<li>
					<span></span>
					<div class="container">
						<div class="row">
							<div class="col-lg-12">
								<div class="center">
									<h3>CSO</h3>
								
								</div>
							</div>
						</div>
					<h4>ADM Dashboard<br> <br></h4>
					</div>
				</li>
			</ul>
			
			<div class="intro-scroller">
				<a class="inner-link" href="#about">
					<div class="mouse-icon" style="opacity: 1;">
						<div class="wheel"></div>
					</div>
				</a> 
			</div>          
		</header>
		
		<!-- Page Content -->
		<section id="about">
			<div class="container">
				<div class="row">
					<div class="col-md-offset-1 col-md-10">
						<div class="text-center">	
						<form id="loginform" action="LoginServlet" method="POST">
			<input type="hidden" name="email" id="email" />
			<input type="hidden" name="logoURL" id="logoURL" />
		</form>
						<button class="  center g-signin2 " data-onsuccess="onSignIn">Sign in</button>
							<h2>What is ADM?</h2>
							<img class="img-responsive displayed" src="./login_files/short.png" alt="Company about">
							<div class="row">
								<div class="col-md-12">
									<p>
										<i>The Activity Documentations and Management Team is the executive team of the Council of Student Organizations that takes charge of the documentation of the post-activity requirements of all accredited organizations. 
										</i>
									</p>
							<h2>CSO?</h2>
							<p>
							The Council of Student Organizations (CSO) is the union of accredited professional (PROF), special interest (SPIN) and socio-civic organizations of De La Salle University.
							</p>
									<p>
									The CSO Executive Board also serves as the coordinating body of the Council and oversees the implementation of university-wide activities participated in by the different organizations.
									</p>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</section>

		<section id="probe">
		<div class="container">
		<div class="row">
			<div class="text-center">
			<h3>Professional Organization of Business and Economics</h3>
			<img class="img-responsive displayed" src="./login_files/short.png" alt="about">
			</div>

			<ul class="filter nav nav-pills">
			  <li data-value="probe" id="1"><a href="#">All</a></li>
			  <li data-value="adcreate"><a href="#">ADCREATE</a></li>
			  <li data-value="bms"><a href="#">BMS</a></li>
			  <li data-value="econorg"><a href="#">ECONORG</a></li>
			  <li data-value="jema"><a href="#">JEMA</a></li>
			  <li data-value="jpia"><a href="#">JPIA</a></li>
			  <li data-value="leylasalle"><a href="#">LEY LA SALLE</a></li>
			  <li data-value="mafia"><a href="#">MAFIA</a></li>
			  <li data-value="yes"><a href="#">YES</a></li>
			 </ul>
 
			<ul class="port2">
			  <li data-type="adcreate" data-id="id-1" class="port3">
				<a href="#" id="adcreate1"><img src="./images/ADCREATE.png" alt=""></a></li>
			  <li data-type="bms" data-id="id-2" class="port3">
				<a href="#" id="bms1"><img src="./images/BMS.png" alt=""></a>
			  </li>
			  <li data-type="econorg" data-id="id-3" class="port3">
				<a href="#" id="econorg1"><img src="./images/ECONORG.png" alt=""></a>
			  </li>
				<li data-type="jema" data-id="id-4" class="port3">
				<a href="#" id="jema1"><img src="./images/JEMA.png" alt=""></a>
			  </li>
			  <li data-type="jpia" data-id="id-5" class="port3">
				<a href="#" id="jpia1"><img src="./images/JPIA.png" alt=""></a>
			  </li>
			  <li data-type="leylasalle" data-id="id-6" class="port3">
				<a href="#" id="leylasalle1"><img src="./images/LLS.png" alt=""></a>
			  </li>
			  <li data-type="mafia" data-id="id-7" class="port3">
				<a href="#" id="mafia1"><img src="./images/MAFIA.jpg" alt=""></a></li>
			  <li data-type="yes" data-id="id-8" class="port3">
				<a href="#" id="yes1"><img src="./images/YES.png" alt=""></a>
			  </li>
			</ul>
		  </div> 
		</div>
		</section>
		<section id="engage">
		<div class="container">
		<div class="row">
			<div class="text-center">
			<h3>Engineering Alliance Geared Towards Excellence</h3>
			<img class="img-responsive displayed" src="./login_files/short.png" alt="about">
			</div>

			<ul class="filter nav nav-pills">
			  <li data-value="all"><a href="#">All</a></li>
			  <li data-value="access"><a href="#">ACCESS</a></li>
			  <li data-value="ces"><a href="#">CES</a></li>
			  <li data-value="chen"><a href="#">CHEN</a></li>
			  <li data-value="eces"><a href="#">ECES</a></li>
			  <li data-value="imes"><a href="#">IMES</a></li>
			  <li data-value="mes"><a href="#">MES</a></li>
			  <li data-value="sme"><a href="#">SME</a></li>
			 </ul>
 
			<ul class="port2">
			  <li data-type="access" data-id="id-9" class="port3">
				<a href="#" id="access1"><img src="./images/ACCESS.png" alt=""></a></li>
			  <li data-type="ces" data-id="id-10" class="port3">
				<a href="#" id="ces1"><img src="./images/CES.png" alt=""></a>
			  </li>
			  <li data-type="chen" data-id="id-11" class="port3">
				<a href="#" id="chen1"><img src="./images/CHEN.png" alt=""></a>
			  </li>
				<li data-type="eces" data-id="id-12" class="port3">
				<a href="#" id="eces1"><img src="./images/ECES.png" alt=""></a>
			  </li>
			  <li data-type="imes" data-id="id-13" class="port3">
				<a href="#" id="imes1"><img src="./images/IMES.png" alt=""></a>
			  </li>
			  <li data-type="mes" data-id="id-14" class="port3">
				<a href="#" id="mes1"><img src="./images/MES.png" alt=""></a>
			  </li>
			  <li data-type="sme" data-id="id-15" class="port3">
				<a href="#" id="sme1"><img src="./images/SME.png" alt=""></a></li>
			</ul>
		  </div> 
		</div>
		</section>
		
		<section id="cap12">
		<div class="container">
		<div class="row">
			<div class="text-center">
			<h3>Alliance of College of Liberal Arts Professional Organizations</h3>
			<img class="img-responsive displayed" src="./login_files/short.png" alt="about">
			</div>

			<ul class="filter nav  nav-pills">
			  <li data-value="all"><a href="#">All</a></li>
			  <li data-value="amstud"><a href="#">AMSTUD</a></li>
			  <li data-value="bss"><a href="#">BSS</a></li>
			  <li data-value="cultura"><a href="#">CULTURA</a></li>
			  <li data-value="danum"><a href="#">DANUM</a></li>
			  <li data-value="pilosopo"><a href="#">DLSU PILOSOPO</a></li>
			  <li data-value="esa"><a href="#">ESA</a></li>
			  <li data-value="kkk"><a href="#">KKK</a></li>
			  <li data-value="nkk"><a href="#">NKK</a></li>
			  <li data-value="poliscy"><a href="#">POLISCY</a></li>
			  <li data-value="sdh"><a href="#">SDH</a></li>
			  <li data-value="sms"><a href="#">SMS</a></li>
			  <li data-value="teamcomm"><a href="#">TEAMCOMM</a></li>
			 </ul>
 
			<ul class="port2">
			  <li data-type="amstud" data-id="id-16" class="port3">
				<a href="#" id="amstud1"><img src="./images/AMSTUD.png" alt=""></a></li>
			  <li data-type="bss" data-id="id-17" class="port3">
				<a href="#" id="bss1"><img src="./images/BSS.jpg" alt=""></a>
			  </li>
			  <li data-type="cultura" data-id="id-18" class="port3">
				<a href="#" id="cultura1"><img src="./images/CULTURA.png" alt=""></a>
			  </li>
				<li data-type="danum" data-id="id-19" class="port3">
				<a href="#" id="danum1"><img src="./images/DANUM.png" alt=""></a>
			  </li>
			  <li data-type="pilosopo" data-id="id-20" class="port3">
				<a href="#" id="pilosopo1"><img src="./images/PILOSOPO.png" alt=""></a>
			  </li>
			  <li data-type="esa" data-id="id-21" class="port3">
				<a href="#" id="esa1"><img src="./images/ESA.png" alt=""></a>
			  </li>
			  <li data-type="kkk" data-id="id-22" class="port3">
				<a href="#" id="kkk1"><img src="./images/KKK.png" alt=""></a></li>
			  <li data-type="nkk" data-id="id-23" class="port3">
				<a href="#" id="nkk1"><img src="./images/NKK.png" alt=""></a>
			  </li>
			  <li data-type="poliscy" data-id="id-24" class="port3">
				<a href="#" id="policy1"><img src="./images/POLISCY.png" alt=""></a>
			  </li>
				<li data-type="sdh" data-id="id-25" class="port3">
				<a href="#" id="sdh1"><img src="./images/SDH.png" alt=""></a>
			  </li>
			  <li data-type="sms" data-id="id-26" class="port3">
				<a href="#" id="sms1"><img src="./images/port2.jpg" alt=""></a>
			  </li>
			  <li data-type="teamcomm" data-id="id-27" class="port3">
				<a href="#" id="teamcomm1"><img src="./images/TEAMCOMM.png" alt=""></a>
			  </li>
			</ul>
		  </div> 
		</div>
		</section>
		
		<section id="aso">
		<div class="container">
		<div class="row">
			<div class="text-center">
			<h3>Alliance of Science Organizations</h3>
			<img class="img-responsive displayed" src="./images/short.png" alt="about">
			</div>

			<ul class="filter nav nav-pills">
			  <li data-value="all"><a href="#">All</a></li>
			  <li data-value="chemsoc"><a href="#">CHEMSOC</a></li>
			  <li data-value="mathcircle"><a href="#">MATH CIRCLE</a></li>
			  <li data-value="physoc"><a href="#">PHYSOC</a></li>
			  <li data-value="sv"><a href="#">SV</a></li>
			 </ul>
 
			<ul class="port2">
			  <li data-type="chemsoc" data-id="id-28" class="port3">
				<a href="#" id="chemsoc1"><img src="./images/port2.jpg" alt=""></a></li>
			  <li data-type="mathcircle" data-id="id-29" class="port3">
				<a href="#" id="mathcircle1"><img src="./images/MC.png" alt=""></a>
			  </li>
			  <li data-type="physoc" data-id="id-30" class="port3">
				<a href="#" id="physoc1"><img src="./images/PHYSOC.png" alt=""></a>
			  </li>
				<li data-type="sv" data-id="id-31" class="port3">
				<a href="#" id="sv1"><img src="./images/SV.png" alt=""></a>
			  </li>
			</ul>
		  </div> 
		</div>
		</section>
		
		<section id="aspire">
		<div class="container">
		<div class="row">
			<div class="text-center">
			<h3>Alliance of Socio-Civic and Special Interest</h3>
			<img class="img-responsive displayed" src="./login_files/short.png" alt="about">
			</div>

			<ul class="filter nav nav-pills">
			  <li data-value="all"><a href="#">All</a></li>
			  <li data-value="aiesec"><a href="#">AIESEC</a></li>
			  <li data-value="englicom"><a href="#">ENGLICOM</a></li>
			  <li data-value="moomedia"><a href="#">MOOMEDIA</a></li>
			  <li data-value="oc"><a href="#">OUTDOOR CLUB</a></li>
			  <li data-value="rotaract"><a href="#">ROTARACT</a></li>
			  <li data-value="lscs"><a href="#">LSCS</a></li>
			  <li data-value="unisto"><a href="#">UNISTO</a></li>
			  <li data-value="united"><a href="#">UNITED</a></li>
			  <li data-value="wg"><a href="#">WG</a></li>
			 </ul>
 
			<ul class="port2">
			  <li data-type="aiesec" data-id="id-32" class="port3">
				<a href="#" id="aiesec1"><img src="./images/AIESEC.png" alt=""></a></li>
			  <li data-type="englicom" data-id="id-33" class="port3">
				<a href="#" id="englicom1"><img src="./images/ENGLICOM.png" alt=""></a>
			  </li>
			  <li data-type="moomedia" data-id="id-34" class="port3">
				<a href="#" id="moomedia1"><img src="./images/MOOMEDIA.png" alt=""></a>
			  </li>
				<li data-type="oc" data-id="id-35" class="port3">
				<a href="#" id="oc1"><img src="./images/port2.jpg" alt=""></a>
			  </li>
			  <li data-type="rotaract" data-id="id-36" class="port3">
				<a href="#" id="rotaract1"><img src="./images/ROTARACT.png" alt=""></a>
			  </li>
			  <li data-type="lscs" data-id="id-37" class="port3">
				<a href="#" id="lscs1"><img src="./images/LSCS.png" alt=""></a>
			  </li>
			  <li data-type="unisto" data-id="id-38" class="port3">
				<a href="#" id="unisto1"><img src="./images/UNISTO.png" alt=""></a></li>
			  <li data-type="united" data-id="id-39" class="port3">
				<a href="#" id="united1"><img src="./images/UNITED.jpg" alt=""></a>
			  </li>
			  <li data-type="wg" data-id="id-40" class="port3">
				<a href="#" id="wg1"><img src="./images/WG.png" alt=""></a>
			  </li>
			</ul>
		  </div> 
		</div>
		</section>

		<!-- MAP 
		<div id="location">
			<div class="row prodmap">
				<div id="map-canvas-holder" class="map_holder" style="height: 400px;"></div>
			</div>
		</div>
		-->
		<section id="contact">
			<div class="container"> 
				<div class="row">
					<div class="col-md-12">
						<div class="col-lg-12">
							<div class="text-center color-elements">
							<h2>Contact</h2>
							</div>
						</div>
						<div class="col-lg-6 col-md-8">
							<form class="inline" id="contactForm" method="post">
								<div class="row">
									<div class="col-sm-6 height-contact-element">
										<div class="form-group">
											<input type="text" name="first_name" class="form-control custom-labels" id="name" placeholder="FULL NAME" required="" data-validation-required-message="Please write your name!">
											<p class="help-block text-danger"></p>
										</div>
									</div>
									<div class="col-sm-6 height-contact-element">
										<div class="form-group">
											<input type="email" name="email" class="form-control custom-labels" id="email" placeholder="EMAIL" required="" data-validation-required-message="Please write your email!">
											<p class="help-block text-danger"></p>
										</div>
									</div>
									<div class="col-sm-12 height-contact-element">
										<div class="form-group">
											<input type="text" name="message" class="form-control custom-labels" id="message" placeholder="Any concerns?" required="" data-validation-required-message="Please write a message!">
										</div>
									</div>
									<div class="col-sm-3 col-xs-6 height-contact-element">
										<div class="form-group">
											<input type="submit" class="btn btn-md btn-custom btn-noborder-radius" value="Send It">
										</div>
									</div>
									<div class="col-sm-3 col-xs-6 height-contact-element">
										<div class="form-group">
											<button type="button" class="btn btn-md btn-noborder-radius btn-custom" name="reset">RESET
											</button>
										</div>
									</div>
									<div class="col-sm-3 col-xs-6 height-contact-element">
										<div class="form-group">
											<div id="response_holder"></div>
										</div>
									</div>
									<div class="col-sm-12 contact-msg">
									<div id="success"></div>
									</div>
								</div>
							</form>
						</div>
						<div class="col-lg-5 col-md-3 col-lg-offset-1 col-md-offset-1">
							<div class="row">
								<div class="col-md-12 height-contact-element">
									<div class="form-group">
										<i class="fa fa-2x fa-map-marker"></i>
										<span class="text">G208, Gokongwei Bldg. DLSU 1004</span>
									</div>
								</div>
								<div class="col-md-12 height-contact-element">    
									<div class="form-group">
										<i class="fa fa-2x fa-envelope"></i>
										<span class="text"><a href="malito:jmhilario@dlsu.edu.ph">jmhilario@dlsu.edu.ph</a></span>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</section>

		<!--<section id="follow-us">
			<div class="container"> 
				<div class="text-center height-contact-element">
					<h2>Keep in touch</h2>
				</div>
				<img class="img-responsive displayed" src="images/short.png" alt="short" />
				<div class="text-center height-contact-element">
					<ul class="list-unstyled list-inline list-social-icons">
						<li class="active"><a href="https://www.facebook.com/skilldpro"><i class="fa fa-facebook social-icons"></i></a></li>
						<li><a href="https://www.linkedin.com/company/skilldpro"><i class="fa fa-twitter social-icons"></i></a></li>
						<li><a href="#"><i class="fa fa-google-plus social-icons"></i></a></li>
						<li><a href="https://www.linkedin.com/company/skilldpro"><i class="fa fa-linkedin social-icons"></i></a></li>
					</ul>
				</div>
			</div>
		</section>-->

		<footer id="footer">
			<div class="container">
				<div class="row myfooter">
					<div class="col-sm-6"><div class="pull-left">
					© s17_TeamJM_2016 | <a href="#">Privacy Policy</a> | <a href="#">Terms of Use</a>
					</div></div>
				</div>
			</div>
		</footer>

		

		<!-- jQuery -->
		<script src="./login_files/jquery.js"></script>
		<!-- Bootstrap Core JavaScript -->
		<script src="./login_files/bootstrap.min.js"></script>

		<!-- Google Map -->
		<script src="./login_files/js"></script>

		<!-- Portfolio -->
		<script src="./login_files/jquery.quicksand.js"></script>    
	

		<!--Jquery Smooth Scrolling-->
		<script src="./login_files/slider.js"></script>

		<script src="./login_files/gallery.js"></script>

		<script src="./login_files/email.js"></script>

		<script src="./login_files/map-holder.js"></script>

		<script>

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
		$("#logoURL").val(profile.getImageUrl());
		$("#loginform").submit();
		
      };
	  
	  
	  
    </script>





<div id="toTop" class="btn btn-primary color1" style="display: block;"><span class="glyphicon glyphicon-chevron-up"></span></div></body></html>