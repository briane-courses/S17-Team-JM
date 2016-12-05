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
		
	function updateEvent(event, delta, revertFunc){
		//alert(event.title + " was dropped on " + event.start.format() +" with id: "+ event.id);
        
        $.ajax({
            url: "PushAjaxCalendar",
            type: "POST",
            data: ({
              type: 'admin',
              id: event.id,
              title: event.title,
              start: event.start.format(),
              date: event.start.format()
            }),
            success: function(data, textStatus) {
                if (!data)
                {
                  revertFunc();
                  console.log("NO DATA");
                  return;
                }
                $('#calendar').fullCalendar('updateEvent', event);
                console.log("Success! Event: ["+event.id+"] ["+event.title+"] moved successfully.");
              },
              error: function() {
                revertFunc();
                console.log("NO DATA");
              },

  	        loading: function(bool) {
  	            if (bool) $('#loading').show();
  	            else $('#loading').hide();
  	        }
              
            });
        
	}
	
	
		$('#calendar').fullCalendar({
			header: {
				left: 'prev,next today',
				center: 'title',
				right: 'month,basicWeek,basicDay,listWeek'
			},
			defaultDate: moment(),
			navLinks: true, // can click day/week names to navigate views
			editable: true, // allow edits (probably best for admin)
			eventLimit: true, // allow "more" link when too many events
			//selectable: true,
			//selectHelper: true,
			droppable: true,
			drop: function() {
				// remove selected item
				//$(this).remove();
			},
			events: {
		        url: 'PullAjaxCalendar',
		        type: 'POST',
		        data: function() { // a function that returns an object
		        	return {
		                type: 'admin',
		                date: $('#calendar').fullCalendar('getDate').format()
		        	};
		        },
		        error: function() {
		            alert('there was an error while fetching events!');
		        },
		        loading: function(bool) {
		            if (bool) $('#loading').show();
		            else $('#loading').hide();
				}
		    },
		    eventDrop: function(event, delta, revertFunc) {

		    	updateEvent(event, delta, revertFunc);
		    	
		        },

	        loading: function(bool) {
	            if (bool) $('#loading').show();
	            else $('#loading').hide();
	        }
			
		});
		
	});