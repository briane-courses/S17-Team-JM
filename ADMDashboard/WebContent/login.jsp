
<!DOCTYPE html5>
<html lang="en">

<head>

	<meta name="google-signin-scope" content="profile email">
	<meta name="google-signin-client_id" content="20332845396-pvv6j3ifeu0391esi12rdcill7tmq0u7.apps.googleusercontent.com">
		
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>ADM Dashboard</title>

    <!-- Bootstrap Core CSS -->
    <link href="login_files/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	
	
	<link rel="stylesheet" href="./login_files/css/style.css">
	
    <!-- Custom Fonts -->
    <link href="login_files/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css">
    <link href='https://fonts.googleapis.com/css?family=Kaushan+Script' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Droid+Serif:400,700,400italic,700italic' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700' rel='stylesheet' type='text/css'>

    <!-- Theme CSS -->
    <link href="./login_files/css/agency.min.css" rel="stylesheet">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
	
	
</head>

<body id="page-top">

    <!-- Navigation -->
    <nav id="mainNav" class="navbar navbar-fixed-top">
        <div class="container">
            <button class="btn btn-primary btn-toggle hidden-md-up float-xs-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">Menu <i class="fa fa-bars"></i></button>
            <!-- Clearfix with a utility class added to allow for better navbar responsiveness. -->
            <div class="clearfix hidden-md-up"></div>
            <div class="collapse navbar-toggleable-sm" id="navbarResponsive">
                <ul class="nav navbar-nav float-md-right">
					 <li class="nav-item">
                        <a class="nav-link page-scroll" href="#page-top">Home</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link page-scroll" href="#adm">ADM</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link page-scroll" href="#probe">PROBE</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link page-scroll" href="#engage">ENGAGE</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link page-scroll" href="#cap12">CAP12</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link page-scroll" href="#aso">ASO</a>
                    </li>
					<li class="nav-item">
                        <a class="nav-link page-scroll" href="#aspire">ASPIRE</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>

    <!-- Header -->
    <header>
        <div class="container">
            <div class="intro-text">
                <div class="intro-lead-in">CSO</div>
                <div class="intro-heading">ADM Dashboard</div>
              	<button class="g-signin2 login-button" data-onsuccess="onSignIn"></button>
            </div>
            <form id="loginform" action="LoginServlet" method="POST">
				<input type="hidden" name="email" id="email" />
				<input type="hidden" name="logoURL" id="logoURL" />
			</form>
        </div>
    </header>

    <!-- Services Section -->
    <section id="adm">
			<div class="container">
				<div class="row">
					
						<div class="col-lg-12 text-xs-center">	
							<h2>What is ADM?</h2>
							<img class="img-responsive displayed" src="./login_files/images/short.png" class="img-responsive" alt="Company about">
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
		</section>
 	<section id="probe">
		<div class="container">
		<div class="row">
			<div class="col-lg-12 text-xs-center">
			<h3 class="section-heading">Professional Organization of Business and Economics</h3>
			</div>

			<ul class="filter nav nav-pills">
			  <li data-value="probe"><a href="#">All</a></li>
			  <li data-value="adcreate"><a href="#">ADCREATE</a></li>
			  <li data-value="bms"><a href="#">BMS</a></li>
			  <li data-value="econorg"><a href="#">ECONORG</a></li>
			  <li data-value="jema"><a href="#">JEMA</a></li>
			  <li data-value="jpia"><a href="#">JPIA</a></li>
			  <li data-value="leylasalle"><a href="#">LEY LA SALLE</a></li>
			  <li data-value="mafia"><a href="#">MAFIA</a></li>
			  <li data-value="yes"><a href="#">YES</a></li>
			 </ul>
 
			<ul class="port2 probe">
			  <li data-type="adcreate" data-id="id-1" class="port3">
				<a href="#" id="adcreate1"><img src="./login_files/images/ADCREATE.png "class="img-responsive" class="img-responsive" alt=""></a></li>
			  <li data-type="bms" data-id="id-2" class="port3">
				<a href="#" id="bms1"><img src="./login_files/images/BMS.png" class="img-responsive"class="img-responsive" alt=""></a>
			  </li>
			  <li data-type="econorg" data-id="id-3" class="port3">
				<a href="#" id="econorg1"><img src="./login_files/images/ECONORG.png" class="img-responsive" class="img-responsive" alt=""></a>
			  </li>
				<li data-type="jema" data-id="id-4" class="port3">
				<a href="#" id="jema1"><img src="./login_files/images/JEMA.png" class="img-responsive" class="img-responsive" alt=""></a>
			  </li>
			  <li data-type="jpia" data-id="id-5" class="port3">
				<a href="#" id="jpia1"><img src="./login_files/images/JPIA.png" class="img-responsive" class="img-responsive" alt=""></a>
			  </li>
			  <li data-type="leylasalle" data-id="id-6" class="port3">
				<a href="#" id="leylasalle1"><img src="./login_files/images/LLS.png" class="img-responsive" class="img-responsive" alt=""></a>
			  </li>
			  <li data-type="mafia" data-id="id-7" class="port3">
				<a href="#" id="mafia1"><img src="./login_files/images/MAFIA.jpg" class="img-responsive" class="img-responsive" alt=""></a></li>
			  <li data-type="yes" data-id="id-8" class="port3">
				<a href="#" id="yes1"><img src="./login_files/images/YES.png" class="img-responsive" class="img-responsive" alt=""></a>
			  </li>
			</ul>
		  </div> 
		</div>
		</section>
		<section id="engage">
		<div class="container">
		<div class="row">
			<div class="col-lg-12 text-xs-center">
			<h3 class="section-heading">Engineering Alliance Geared Towards Excellence</h3>
			</div>
			<ul class="filter nav nav-pills">
			  <li data-value="engage"><a href="#">All</a></li>
			  <li data-value="access"><a href="#">ACCESS</a></li>
			  <li data-value="ces"><a href="#">CES</a></li>
			  <li data-value="chen"><a href="#">CHEN</a></li>
			  <li data-value="eces"><a href="#">ECES</a></li>
			  <li data-value="imes"><a href="#">IMES</a></li>
			  <li data-value="mes"><a href="#">MES</a></li>
			  <li data-value="sme"><a href="#">SME</a></li>
			 </ul>
 
			<ul class="port2 engage">
			  <li data-type="access" data-id="id-9" class="port3">
				<a href="#" id="access1"><img src="./login_files/images/ACCESS.png" class="img-responsive" class="img-responsive" alt=""></a></li>
			  <li data-type="ces" data-id="id-10" class="port3">
				<a href="#" id="ces1"><img src="./login_files/images/CES.png" class="img-responsive" class="img-responsive" alt=""></a>
			  </li>
			  <li data-type="chen" data-id="id-11" class="port3">
				<a href="#" id="chen1"><img src="./login_files/images/CHEN.png" class="img-responsive" class="img-responsive" alt=""></a>
			  </li>
				<li data-type="eces" data-id="id-12" class="port3">
				<a href="#" id="eces1"><img src="./login_files/images/ECES.png" class="img-responsive" class="img-responsive" alt=""></a>
			  </li>
			  <li data-type="imes" data-id="id-13" class="port3">
				<a href="#" id="imes1"><img src="./login_files/images/IMES.png" class="img-responsive" class="img-responsive" alt=""></a>
			  </li>
			  <li data-type="mes" data-id="id-14" class="port3">
				<a href="#" id="mes1"><img src="./login_files/images/MES.png" class="img-responsive" class="img-responsive" alt=""></a>
			  </li>
			  <li data-type="sme" data-id="id-15" class="port3">
				<a href="#" id="sme1"><img src="./login_files/images/SME.png" class="img-responsive" class="img-responsive" alt=""></a></li>
			</ul>
		  </div> 
		</div>
		</section>
	<section id="cap12">
		<div class="container">
		<div class="row">
			<div class="col-lg-12 text-xs-center">
			<h3 class= "section-heading">Alliance of College of Liberal Arts Professional Organizations</h3>
			</div>

			<ul class="filter nav nav-pills">
			  <li data-value="cap12"><a href="#">All</a></li>
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
 
			<ul class="port2 cap12">
			  <li data-type="amstud" data-id="id-16" class="port3">
				<a href="#" id="amstud1"><img src="./login_files/images/AMSTUD.png" class="img-responsive" class="img-responsive" alt=""></a></li>
			  <li data-type="bss" data-id="id-17" class="port3">
				<a href="#" id="bss1"><img src="./login_files/images/BSS.jpg" class="img-responsive" class="img-responsive" alt=""></a>
			  </li>
			  <li data-type="cultura" data-id="id-18" class="port3">
				<a href="#" id="cultura1"><img src="./login_files/images/CULTURA.png" class="img-responsive" class="img-responsive" alt=""></a>
			  </li>
				<li data-type="danum" data-id="id-19" class="port3">
				<a href="#" id="danum1"><img src="./login_files/images/DANUM.png" class="img-responsive" class="img-responsive" alt=""></a>
			  </li>
			  <li data-type="pilosopo" data-id="id-20" class="port3">
				<a href="#" id="pilosopo1"><img src="./login_files/images/PILOSOPO.png" class="img-responsive" class="img-responsive" alt=""></a>
			  </li>
			  <li data-type="esa" data-id="id-21" class="port3">
				<a href="#" id="esa1"><img src="./login_files/images/ESA.png" class="img-responsive" class="img-responsive" alt=""></a>
			  </li>
			  <li data-type="kkk" data-id="id-22" class="port3">
				<a href="#" id="kkk1"><img src="./login_files/images/KKK.png" class="img-responsive" class="img-responsive" alt=""></a></li>
			  <li data-type="nkk" data-id="id-23" class="port3">
				<a href="#" id="nkk1"><img src="./login_files/images/NKK.png" class="img-responsive" class="img-responsive" alt=""></a>
			  </li>
			  <li data-type="poliscy" data-id="id-24" class="port3">
				<a href="#" id="policy1"><img src="./login_files/images/POLISCY.png" class="img-responsive" class="img-responsive" alt=""></a>
			  </li>
				<li data-type="sdh" data-id="id-25" class="port3">
				<a href="#" id="sdh1"><img src="./login_files/images/SDH.png" class="img-responsive" class="img-responsive" alt=""></a>
			  </li>
			  <li data-type="sms" data-id="id-26" class="port3">
				<a href="#" id="sms1"><img src="./login_files/images/port2.jpg" class="img-responsive" class="img-responsive" alt=""></a>
			  </li>
			  <li data-type="teamcomm" data-id="id-27" class="port3">
				<a href="#" id="teamcomm1"><img src="./login_files/images/TEAMCOMM.png" class="img-responsive" class="img-responsive" alt=""></a>
			  </li>
			</ul>
		  </div> 
		</div>
		</section>
		
		<section id="aso">
		<div class="container">
		<div class="row">
			<div class="col-lg-12 text-xs-center">
			<h3 class ="section-heading">Alliance of Science Organizations</h3>
			</div>

			<ul class="filter nav nav-pills">
			  <li data-value="aso"><a href="#">All</a></li>
			  <li data-value="chemsoc"><a href="#">CHEMSOC</a></li>
			  <li data-value="mathcircle"><a href="#">MATH CIRCLE</a></li>
			  <li data-value="physoc"><a href="#">PHYSOC</a></li>
			  <li data-value="sv"><a href="#">SV</a></li>
			 </ul>
 
			<ul class="port2 aso">
			  <li data-type="chemsoc" data-id="id-1" class="port3">
				<a href="#" id="chemsoc1"><img src="./login_files/images/port2.jpg" class="img-responsive" class="img-responsive" alt=""></a></li>
			  <li data-type="mathcircle" data-id="id-2" class="port3">
				<a href="#" id="mathcircle1"><img src="./login_files/images/MC.png" class="img-responsive" class="img-responsive" alt=""></a>
			  </li>
			  <li data-type="physoc" data-id="id-3" class="port3">
				<a href="#" id="physoc1"><img src="./login_files/images/PHYSOC.png" class="img-responsive" class="img-responsive" alt=""></a>
			  </li>
				<li data-type="sv" data-id="id-4" class="port3">
				<a href="#" id="sv1"><img src="./login_files/images/SV.png" class="img-responsive" class="img-responsive" alt=""></a>
			  </li>
			</ul>
		  </div> 
		</div>
		</section>
		
		<section id="aspire">
		<div class="container">
		<div class="row">
			<div class="col-lg-12 text-xs-center">
			<h3 class = "section-heading">Alliance of Socio-Civic and Special Interest</h3>
			</div>

			<ul class="filter nav nav-pills">
			  <li data-value="aspire"><a href="#">All</a></li>
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
 
			<ul class="port2 aspire">
			  <li data-type="aiesec" data-id="id-1" class="port3">
				<a href="#portfolioModal3" class="portfolio-link" data-toggle="modal" id="aiesec1"><img src="./login_files/images/AIESEC.png" class="img-responsive" class="img-responsive" alt=""></a></li>
			  <li data-type="englicom" data-id="id-2" class="port3">
				<a href="#" id="englicom1"><img src="./login_files/images/ENGLICOM.png" class="img-responsive" class="img-responsive" alt=""></a>
			  </li>
			  <li data-type="moomedia" data-id="id-3" class="port3">
				<a href="#" id="moomedia1"><img src="./login_files/images/MOOMEDIA.png" class="img-responsive" class="img-responsive" alt=""></a>
			  </li>
				<li data-type="oc" data-id="id-4" class="port3">
				<a href="#" id="oc1"><img src="./login_files/images/port2.jpg" class="img-responsive" class="img-responsive" alt=""></a>
			  </li>
			  <li data-type="rotaract" data-id="id-5" class="port3">
				<a href="#" id="rotaract1"><img src="./login_files/images/ROTARACT.png" class="img-responsive" class="img-responsive" alt=""></a>
			  </li>
			  <li data-type="lscs" data-id="id-6" class="port3">
				<a href="#" id="lscs1"><img src="./login_files/images/LSCS.png" class="img-responsive" class="img-responsive" alt=""></a>
			  </li>
			  <li data-type="unisto" data-id="id-7" class="port3">
				<a href="#" id="unisto1"><img src="./login_files/images/UNISTO.png" class="img-responsive" class="img-responsive" alt=""></a></li>
			  <li data-type="united" data-id="id-8" class="port3">
				<a href="#" id="united1"><img src="./login_files/images/UNITED.jpg" class="img-responsive" class="img-responsive" alt=""></a>
			  </li>
			  <li data-type="wg" data-id="id-9" class="port3">
				<a href="#" id="wg1"><img src="./login_files/images/WG.png" class="img-responsive" class="img-responsive" alt=""></a>
			  </li>
			</ul>
		  </div> 
		</div>
		</section>
		
    <!-- Clients Aside -->
    <aside class="clients">
        <div class="container">
            <div class="row">
                <div class="col-md-3 col-sm-6">
                    <a href="#">
                        <img src="./login_files/img/logos/creative-market.jpg" class="img-fluid img-centered" class="img-responsive" class="img-responsive" alt="">
                    </a>
                </div>
            </div>
        </div>
    </aside>
	
    <!-- Contact Section -->
    <section id="contact">
        <div class="container">
            <div class="row">
                <div class="col-lg-12 text-xs-center">
                    <h2 class="section-heading">Contact Us</h2>
                    <h3 class="section-subheading text-muted">If you have any problems</h3>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-12">
                    <form name="sentMessage" id="contactForm" novalidate>
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <input type="text" class="form-control" placeholder="Your Name *" id="name" required data-validation-required-message="Please enter your name.">
                                    <p class="help-block text-danger"></p>
                                </div>
                                <div class="form-group">
                                    <input type="email" class="form-control" placeholder="Your Email *" id="email" required data-validation-required-message="Please enter your email address.">
                                    <p class="help-block text-danger"></p>
                                </div>
                                <div class="form-group">
                                    <input type="tel" class="form-control" placeholder="Your Phone *" id="phone" required data-validation-required-message="Please enter your phone number.">
                                    <p class="help-block text-danger"></p>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <textarea class="form-control" placeholder="Your Message *" id="message" required data-validation-required-message="Please enter a message."></textarea>
                                    <p class="help-block text-danger"></p>
                                </div>
                            </div>
                            <div class="clearfix"></div>
                            <div class="col-lg-12 text-xs-center">
                                <div id="success"></div>
                                <button type="submit" class="btn btn-xl">Send Message</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </section>
    
	
    <footer>
        <div class="container">
            <div class="row">
                <div class="col-md-4">
                    <span class="copyright">Copyright &copy;	s17_TEAM_JM /2013-2016 Blackrock Digital LLC.</span>
                </div>
                <div class="col-md-4">
                    <ul class="list-inline social-buttons">
                        <li class="list-inline-item"><a href="#"><i class="fa fa-twitter"></i></a>
                        </li>
                        <li class="list-inline-item"><a href="#"><i class="fa fa-facebook"></i></a>
                        </li>
                        <li class="list-inline-item"><a href="#"><i class="fa fa-linkedin"></i></a>
                        </li>
                    </ul>
                </div>
                <div class="col-md-4">
                    <ul class="list-inline quicklinks">
                        <li class="list-inline-item"><a href="#">Privacy Policy</a>
                        </li>
                        <li class="list-inline-item"><a href="#">Terms of Use</a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </footer>
	<script src="https://apis.google.com/js/plus.js?onload=appStart"></script>
    <!-- jQuery -->
	<script src = "js/jquery-3.0.0.min.js"></script>

    <!-- Tether -->
    <script src="login_files/vendor/tether/tether.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="login_files/vendor/bootstrap/js/bootstrap.min.js"></script>

    <!-- Plugin JavaScript -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.3/jquery.easing.min.js"></script>

    <!-- Contact Form JavaScript -->
    <script src="./login_files/js/jqBootstrapValidation.js"></script>
    <script src="./login_files/js/contact_me.js"></script>

    <!-- Theme JavaScript -->
    <script src="./login_files/js/agency.min.js"></script>
	
	<!--Jquery Smooth Scrolling-->
	<script src="./login_files/js/slider.js"></script>
	<script src="./login_files/js/gallery.js"></script>
	<script src="./login_files/js/jquery.quicksand.js"></script> 

	<script src="./login_files/js/googleSignIn.js"></script> 
	
	
	<script>
	
	$(document).ready(function() {
		var auth2 = gapi.auth2.getAuthInstance();
	});
	
	var onSignIn = function(googleUser) {
		var profile = googleUser.getBasicProfile();
		 console.log('nakapasok po.checking...');
	    console.log("ID: " + profile.getId()); // Don't send this directly to your server!
	    console.log('Full Name: ' + profile.getName());
	    console.log('Given Name: ' + profile.getGivenName());
	    console.log('Family Name: ' + profile.getFamilyName());
	    console.log("Image URL: " + profile.getImageUrl());
	    console.log("Email: " + profile.getEmail());
	    console.log('nakapasok po. working na.');
	    // The ID token you need to pass to your backend:
	    var id_token = googleUser.getAuthResponse().id_token;
	    console.log("ID Token: " + id_token);
		
		$("#email").val(profile.getEmail());
		$("#logoURL").val(profile.getImageUrl());
		
		$("#loginform").submit();
	}
	
	</script>
</body>

</html>
