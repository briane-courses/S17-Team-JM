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
			header: {
				left: 'prev,next today',
				center: 'title',
				right: 'month,agendaWeek,agendaDay,listWeek'
			},
			defaultDate: moment(),
			navLinks: true, // can click day/week names to navigate views
			//editable: true, // allow edits (probably best for admin)
			eventLimit: true, // allow "more" link when too many events
			selectable: false, // allow click and drag 
			events: {
		        url: 'AjaxUserCalendar',
		        type: 'POST',
		        data: {
		            orgcode: 'something'
		        },
		        error: function() {
		            alert('there was an error while fetching events!');
		        },
		        loading: function(bool) {
					$('#loading').toggle(bool);
				}
		    }
			
			/*
			events: [
				{
					title: 'All Day Event',
					start: '2016-09-01'
				},
				{
					title: 'Long Event',
					start: '2016-09-07',
					end: '2016-09-10'
				},
				{
					id: 999,
					title: 'Repeating Event',
					start: '2016-09-09T16:00:00'
				},
				{
					id: 999,
					title: 'Repeating Event',
					start: '2016-09-16T16:00:00'
				},
				{
					title: 'Conference',
					start: '2016-09-11',
					end: '2016-09-13'
				},
				{
					title: 'Meeting',
					start: '2016-09-12T10:30:00',
					end: '2016-09-12T12:30:00'
				},
				{
					title: 'Lunch',
					start: '2016-09-12T12:00:00'
				},
				{
					title: 'Meeting',
					start: '2016-09-12T14:30:00'
				},
				{
					title: 'Happy Hour',
					start: '2016-09-12T17:30:00'
				},
				{
					title: 'Dinner',
					start: '2016-09-12T20:00:00'
				},
				{
					title: 'Birthday Party',
					start: '2016-09-13T07:00:00'
				},
				{
					title: 'Click for Google',
					url: 'http://google.com/',
					start: '2016-09-28'
				}
			]
			*/
		});
		
	});