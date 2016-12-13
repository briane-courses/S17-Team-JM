
var tempEvent = null;

$(document).ready(function() {
		
	function createEventEditBox(){
		var editBox = "";
	}
	
	function updateEventEdit(event){
		 $.ajax({
	            url: "PushAjaxCalendar",
	            type: "POST",
	            data: ({
	              user: 'admin',
	              type: 'editEvent',
	              id: event.id,
	              title: event.title,
	              description: event.description,
	              org: event.org,
	              status: event.status,
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
	                $('#calendar').fullCalendar('refetchEvents');
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
	
	function updateEventMove(event, delta, revertFunc){
		//alert(event.title + " was dropped on " + event.start.format() +" with id: "+ event.id);
        
        $.ajax({
            url: "PushAjaxCalendar",
            type: "POST",
            data: ({
              user: 'admin',
              type: 'moveEvent',
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
                //$('#calendar').fullCalendar('updateEvent', event);
                $('#calendar').fullCalendar('refetchEvents');
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
		 	default: true
		 	,
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
				listDay: { buttonText: 'Day' }
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
	        eventMouseover: function (data, event, view) {
	        	tooltip = 
	        		  "<div class='tooltiptopicevent tooltipcontainer card-panel hoverable small'"
	        		+ " style='background-color:"+data.color+"'>"
	        		+ "<span class='tooltipheader card-title'>"
	        		+ data.title
	        		+ "</span>"
	        		+ "<div class='divider'></div>"
	        		+ 'Organization: ' + data.org
	        		+ '<br/>'
	        		+ 'Deadline: ' + data.start.format()
	        		+ '<br/>'
	        		+ 'Type: ' + data.description
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

	        },
			events: {
		        url: 'PullAjaxCalendar',
		        type: 'POST',
		        data: function() { // a function that returns an object
		        	return {
		                user: 'admin',
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
		    eventClick: function(event, jsEvent, view){
                console.log("TEST: ["+event.id+"] ["+event.title+"] ");
	            $(this).css('z-index', 8);
	            $('.tooltiptopicevent').remove();
		    },
	        eventDrop: function(event, delta, revertFunc) {
	        	var day = moment(event.start.format()).day();
	        	var isWeekend = (day == 6) || (day == 0); 
	        	if(!isWeekend)
	        		updateEventMove(event, delta, revertFunc);
	        	else
	                revertFunc();
		        },

	        loading: function(bool) {
	            if (bool) $('#loading').show();
	            else $('#loading').hide();
	        }
		});
		
	});