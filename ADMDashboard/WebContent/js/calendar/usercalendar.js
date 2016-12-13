/*
// Google api console clientID and apiKey 
 var clientId = '20332845396-276a6b44nm93v7rtdlqqt8m88bbtvsvk.apps.googleusercontent.com';
 var apiKey = 'AIzaSyB3cFVU08CzydxKzUcHiZZ9vLdSjC1v11w';
 // enter the scope of current project (this API must be turned on in the Google console)
   var scopes = 'http://localhost:8080/ADMDashboard/';
// OAuth2 functions
     function handleClientLoad() {
           gapi.client.setApiKey(apiKey);
           window.setTimeout(checkAuth, 1);
        }
//To authenticate
  function checkAuth() {
    gapi.auth.authorize({ client_id: clientId, scope: scopes, immediate: true }, handleAuthResult);
        }
var today = new Date();
var twoHoursLater = new Date();
twoHoursLater.setHours(today.getHours()+2);
  
// This is the resource we will pass while calling api function
var resource = {
            "summary": "My Event",
            "start": {
                "dateTime": today
            },
            "end": {
                "dateTime": twoHoursLater
            },
            "description":"We are organizing events",
            "location":"US",
            "attendees":[
            {
                    "email":"attendee1@gmail.com",
                    "displayName":"Jhon",
                    "organizer":true,
                    "self":false,
                    "resource":false,
                    "optional":false,
                    "responseStatus":"needsAction",
                    "comment":"This is my demo event",
                    "additionalGuests":3
                    
            },
            {    
                "email":"attendee2@gmail.com",
                    "displayName":"Marry",
                    "organizer":true,
                    "self":false,
                    "resource":false,
                    "optional":false,
                    "responseStatus":"needsAction",
                    "comment":"This is an official event",
                    "additionalGuests":3
            }
            ],
        };
function makeApiCall(){
gapi.client.load('calendar', 'v3', function () { // load the calendar api (version 3)
                var request = gapi.client.calendar.events.insert({
                    'calendarId': 'dlsu.edu.ph_r6jgffm9cce9velrt7p30adj64@group.calendar.google.com', 
// calendar ID which id of Google Calendar where you are creating events. this can be copied from your Google Calendar user view.
                    "resource": resource 	// above resource will be passed here
                });                
});
}
*/
$(document).ready(function() {
		
	
	
		$('#calendar').fullCalendar({
			default: true,
			businessHours: {
				// days of week. an array of zero-based day of week integers (0=Sunday)
				dow: [ 1, 2, 3, 4, 5, 6] // Monday - Saturday
			},
	
			header: {
				left: 'prev,next today',
				center: 'title',
				right: 'month,basicWeek,listDay'
			},
			views: {
				listDay: { buttonText: 'day' }
			},
			defaultDate: moment(),
			navLinks: true, // can click day/week names to navigate views
			//editable: true, // allow edits (probably best for admin)
			eventLimit: true, // allow "more" link when too many events
			selectable: false, // allow click and drag 
			events: {
		        url: 'PullAjaxCalendar',
		        type: 'POST',
		        data: function() { // a function that returns an object
		        	return {
		                user: 'user',
		                date: $('#calendar').fullCalendar('getDate').format()
		        };},
		        error: function() {
		            alert('there was an error while fetching events!');
		        },
		        loading: function(bool) {
	  	            if (bool) $('#loading').show();
	  	            else $('#loading').hide();
				}
		    },
	        eventMouseover: function (data, event, view) {
	        	tooltip = 
	        		"<div class='tooltiptopicevent tooltipcontainer card-panel hoverable small'"
	        		+ " style='background-color:"+data.color+"'>"
	        		+ "<span class='tooltipheader card-title'>"
	        		+ data.title
	        		+ "</span>"
	        		+ "<div class='divider'></div>"
	        		+ "<br/>"
	        		+ 'Status:<br />' + '<span class="indent">'+ data.status + '</span>'
	        		+ '<br/>'
	        		+ 'Deadline:<br />' + '<span class="indent">'+ data.start.format() + '</span>'
	        		+ '<br/>'
	        		+ 'Type:<br />' + '<span class="indent">'+ data.description + '</span>'
	        		+ "</div>";

	            $("body").append(tooltip);
	            $(this).mouseover(function (e) {
	                $(this).css('z-index', 10000);
	                $('.tooltiptopicevent').fadeIn('500');
	                $('.tooltiptopicevent').fadeTo('10', 1.9);
	            }).mousemove(function (e) {
	                $('.tooltiptopicevent').css('top', e.pageY + 10);
	                $('.tooltiptopicevent').css('left', e.pageX + 20);
	            });


	        },
	        eventMouseout: function (data, event, view) {
	            $(this).css('z-index', 8);

	            $('.tooltiptopicevent').remove();

	        }
		});
		
	});