//googleSignIn.js

var auth2; // The Sign-In object.
var googleUser; // The current user.
/**
 * Calls startAuth after Sign in V2 finishes setting up.
 */
var appStart = function() {
  console.log('appStart()')
  gapi.load('auth2', initSigninV2);
};
/**
 * Initializes Signin v2 and sets up listeners.
 */
var initSigninV2 = function() {
  auth2 = gapi.auth2.getAuthInstance({
      client_id: '20332845396-pvv6j3ifeu0391esi12rdcill7tmq0u7.apps.googleusercontent.com',
      scope: 'profile'
  });

  // Listen for sign-in state changes.
  auth2.isSignedIn.listen(signinChanged);
  // Listen for changes to current user.
  auth2.currentUser.listen(userChanged);

  // Sign in the user if they are currently signed in.
  if (auth2.isSignedIn.get() == true) {
      auth2.signIn();
  }
};
/**
 * Listener method for sign-out live value.
 *
 * @param {boolean} val the updated signed out state.
 */
var signinChanged = function (val) {
    console.log('Signin state changed to ', val);
};
/**
 * Listener method for when the user changes.
 *
 * @param {GoogleUser} user the updated user.
 */
var userChanged = function (user) {
  console.log('User now: ', user);
  googleUser = user;
};

//$('.login-button').on('click', function() {
//    console.log('signing in');
//    auth2 = gapi.auth2.getAuthInstance({
//      client_id: '20332845396-pvv6j3ifeu0391esi12rdcill7tmq0u7.apps.googleusercontent.com',
//      scope: 'profile'
//  });
//    auth2.signIn().then(function(googleUser) {
//        //callback to handle sign in
//    	 var profile = auth2.getBasicProfile();
//    	 console.log('nakapasok po.checking...');
//         console.log("ID: " + profile.getId()); // Don't send this directly to your server!
//         console.log('Full Name: ' + profile.getName());
//         console.log('Given Name: ' + profile.getGivenName());
//         console.log('Family Name: ' + profile.getFamilyName());
//         console.log("Image URL: " + profile.getImageUrl());
//         console.log("Email: " + profile.getEmail());
//         console.log('nakapasok po. working na.');
//         // The ID token you need to pass to your backend:
//         var id_token = googleUser.getAuthResponse().id_token;
//         console.log("ID Token: " + id_token);
// 		
// 		$("#email").val(profile.getEmail());
// 		$("#logoURL").val(profile.getImageUrl());
// 		location.href = "LoginServlet?var=" + profile.getEmail();
// 		//$("#loginform").submit();
// 		
// 		
//    });
//    console.log('nakapasok po.');
//  //  onSignIn(user);
//   
//        
//		
//    });

