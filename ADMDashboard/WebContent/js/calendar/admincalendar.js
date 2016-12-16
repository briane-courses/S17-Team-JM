

function updateEventEdit(){
	var id = $('#event_modal_id').val();
	var start = $('#event_modal_date').val();
	var status = $('#event_modal_status').val();
	
	var day = moment(start).day();
	//var isWeekend = (day == 6) || (day == 0); 

	var isWeekend = (day == 0); 
	if(isWeekend){
		 Materialize.toast('Deadline cannot be set on this day.', 2000);
	} else
	 $.ajax({
           url: "PushAjaxCalendar",
           type: "POST",
           data: ({
             user: 'admin',
             type: 'editEvent',
             id: id,
             start: start,
             status: status,
             date: $('#calendar').fullCalendar('getDate').format()
           }),
           success: function(data, textStatus) {
               if (!data)
               {
                 console.log("NO DATA");
                 return;
               }
               //$('#calendar').fullCalendar('updateEvent', event);
               $('#calendar').fullCalendar('refetchEvents');
               console.log("Success! Event: ["+id+"] moved successfully to ["+start+"]");
             },
             error: function() {
               console.log("NO DATA");
             },

 	        loading: function(bool) {
 	            if (bool) $('#loading').show();
 	            else $('#loading').hide();
 	        }
             
           });
}

$(document).ready(function() {
		
	$('.datepicker').pickadate({
	    selectMonths: true, // Creates a dropdown to control month
	    selectYears: 7, // Creates a dropdown of 15 years to control year
	    format: 'yyyy-mm-dd',
	    container: 'body',
	    clear: ''
	  });
	
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
              date: $('#calendar').fullCalendar('getDate').format()
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
                $('#event_modal').closeModal();
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
				listDay: { buttonText: 'day' }
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
	        		+ "<br/>"
	        		+ 'Status:<br />' + '<span class="indent">'+ data.status + '</span>'
	        		+ '<br/>'
	        		+ 'Organization:<br />' + '<span class="indent">'+ data.org + '</span>'
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
	            $('#event_modal_header').text(event.title);
	            $('#event_modal_content').html(
	            		'Organization:<br />' + '<span class="indent">' + event.org + '</span>' +
	            		'<br />'+
	            		'Type:<br />' + '<span class="indent">' + event.description + '</span>'+
	            		'<br />'+
	            		'Status:<br />' + '<span class="indent">' + event.status + '</span>');
	            $('#event_modal_date').val(event.start.format());
	            $('#event_modal_id').val(event.id);
	            $('#event_modal_status').val(event.status);
	            Materialize.updateTextFields();
	            $('#event_modal').openModal('open');
	            $('select').material_select();
		    },
	        eventDrop: function(event, delta, revertFunc) {
	        	var day = moment(event.start.format()).day();
	        	//var isWeekend = (day == 6) || (day == 0); 
	        	var isWeekend = (day == 0); 
	        	if(!isWeekend)
	        		updateEventMove(event, delta, revertFunc);
	        	else{
	                revertFunc();
	       		 Materialize.toast('Deadline cannot be set on this day.', 2000);
	        	}
		        },

	        loading: function(bool) {
	            if (bool) $('#loading').show();
	            else $('#loading').hide();
	        }
		});
		
	});