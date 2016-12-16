var map = null;
	 var latitude;
	 var longitude;
	 function inicializemap(){
	   latitude = 41.3349509; 
	   longitude = 19.8217028;

	  var egglabs = new google.maps.LatLng(latitude, longitude);
	  var egglabs1 = new google.maps.LatLng(43.0630171, 89.2296082);
	  var egglabs2 = new google.maps.LatLng(13.0630171, 80.2296082 );


	  var image = new google.maps.MarkerImage('images/marker.png', new google.maps.Size(84,56), new google.maps.Point(0,0), new google.maps.Point(42,56));
	  var mapCoordinates = new google.maps.LatLng(latitude, longitude);
	  var mapOptions = {
	   backgroundColor: '#ffffff',
	   zoom: 10,
	   disableDefaultUI: true,
	   center: mapCoordinates,
	   mapTypeId: google.maps.MapTypeId.ROADMAP,
	   scrollwheel: true,
	   draggable: true, 
	   zoomControl: false,
	   disableDoubleClickZoom: true,
	   mapTypeControl: false,
	   styles: [
					{
						"featureType": "all",
						"elementType": "labels.text.fill",
						"stylers": [
							{
								"color": "#1f242f"
							}
						]
					},
					{
						"featureType": "all",
						"elementType": "labels.icon",
						"stylers": [
							{
								"hue": "#ff9d00"
							}
						]
					},
					{
						"featureType": "landscape.man_made",
						"elementType": "geometry.fill",
						"stylers": [
							{
								"color": "#fef8ef"
							}
						]
					},
					{
						"featureType": "poi.medical",
						"elementType": "geometry.fill",
						"stylers": [
							{
								"hue": "#ff0000"
							}
						]
					},
					{
						"featureType": "poi.park",
						"elementType": "geometry.fill",
						"stylers": [
							{
								"color": "#c9d142"
							},
							{
								"lightness": "0"
							},
							{
								"visibility": "on"
							},
							{
								"weight": "1"
							},
							{
								"gamma": "1.98"
							}
						]
					},
					{
						"featureType": "road",
						"elementType": "geometry",
						"stylers": [
							{
								"weight": "1.00"
							}
						]
					},
					{
						"featureType": "road",
						"elementType": "geometry.fill",
						"stylers": [
							{
								"color": "#d7b19c"
							},
							{
								"weight": "1"
							}
						]
					},
					{
						"featureType": "road.highway",
						"elementType": "geometry",
						"stylers": [
							{
								"visibility": "on"
							}
						]
					},
					{
						"featureType": "road.highway",
						"elementType": "geometry.fill",
						"stylers": [
							{
								"weight": "4.03"
							}
						]
					},
					{
						"featureType": "road.highway",
						"elementType": "geometry.stroke",
						"stylers": [
							{
								"visibility": "off"
							},
							{
								"color": "#ffed00"
							}
						]
					},
					{
						"featureType": "road.highway.controlled_access",
						"elementType": "geometry.fill",
						"stylers": [
							{
								"visibility": "on"
							}
						]
					},
					{
						"featureType": "road.arterial",
						"elementType": "geometry",
						"stylers": [
							{
								"visibility": "on"
							}
						]
					},
					{
						"featureType": "road.local",
						"elementType": "geometry",
						"stylers": [
							{
								"visibility": "on"
							}
						]
					},
					{
						"featureType": "transit.line",
						"elementType": "geometry.fill",
						"stylers": [
							{
								"color": "#cbcbcb"
							}
						]
					},
					{
						"featureType": "water",
						"elementType": "geometry.fill",
						"stylers": [
							{
								"color": "#0b727f"
							}
						]
					}
				]
					  };

	  map = new google.maps.Map(document.getElementById('map-canvas-holder'),mapOptions);
	  marker = new google.maps.Marker({position: egglabs, raiseOnDrag: false, icon: image, map: map, draggable: false,  title: 'ATIS1'}); 
	  marker = new google.maps.Marker({position: egglabs1, raiseOnDrag: false, icon: image, map: map, draggable: false,  title: 'ATIS2'}); 
	  marker = new google.maps.Marker({position: egglabs2, raiseOnDrag: false, icon: image, map: map, draggable: false,  title: 'ATIS3'}); 
	  google.maps.event.addListener(map, 'zoom_changed', function() {
	   var center = map.getCenter();
	   google.maps.event.trigger(map, 'resize');
	   map.setCenter(center);
	  });
	 }