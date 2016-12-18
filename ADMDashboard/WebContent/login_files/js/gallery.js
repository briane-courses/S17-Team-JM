function gallery(){};

		var $itemsHolder = $('ul.port2');
		var $probeHolder = $('ul.port2.probe');
		var $engageHolder = $('ul.port2.engage');
		var $cap12Holder = $('ul.port2.cap12');
		var $asoHolder = $('ul.port2.aso');
		var $aspireHolder = $('ul.port2.aspire');
		var $itemsClone = $itemsHolder.clone(); 
		var $probeClone = $probeHolder.clone();
		var $engageClone = $engageHolder.clone();
		var $cap12Clone = $cap12Holder.clone();
		var $asoClone = $asoHolder.clone();
		var $aspireClone = $aspireHolder.clone();
		
		
		
		
		var $filterClass = "";
		$('ul.filter li').click(function(e) {
		e.preventDefault();
		$filterClass = $(this).attr('data-value');
			var $filters1 = $probeClone.find('li');
			var $filters2 = $engageClone.find('li');
			var $filters3 = $cap12Clone.find('li');
			var $filters4 = $asoClone.find('li');
			var $filters5 = $aspireClone.find('li');
			
			if($filterClass == 'probe'){
			var $filters1 = $probeClone.find('li');			}
			else if($filterClass == 'engage'){
			var $filters2 = $engageClone.find('li'); 	
			}else if($filterClass == 'cap12'){
			var $filters3 = $cap12Clone.find('li'); 
			}
			else if($filterClass == 'aso'){
			var $filters4 = $asoClone.find('li'); 	
			}else if($filterClass == 'aspire'){
			var $filters5 = $aspireClone.find('li'); 	
			}
			else {
				var $filters1 = $probeClone.find('li[data-type='+ $filterClass +']'); 
				var $filters2 = $engageClone.find('li[data-type='+ $filterClass +']'); 
				var $filters3 = $cap12Clone.find('li[data-type='+ $filterClass +']'); 
				var $filters4 = $asoClone.find('li[data-type='+ $filterClass +']'); 
				var $filters5 = $aspireClone.find('li[data-type='+ $filterClass +']'); 
				if($filters1.length == 0) {
					console.log("empty");$filters1 = $probeClone.find('li');
				}
				if($filters2.length == 0) {
					console.log("empty");$filters2 = $engageClone.find('li');
				}
				if($filters3.length == 0) {
					console.log("empty");$filters3 = $cap12Clone.find('li');
				}
				if($filters4.length == 0) {
					console.log("empty");$filters4 = $asoClone.find('li');
				}
				if($filters5.length == 0) {
					console.log("empty");$filters5 = $aspireClone.find('li');
				}
				
			}
			$probeHolder.quicksand(
			  $filters1,
			  { duration: 1000 },
			  gallery
			  );
			  
			    $engageHolder.quicksand(
			  $filters2,
			  { duration: 1000 },
			  gallery
			  );
			    $cap12Holder.quicksand(
			  $filters3,
			  { duration: 1000 },
			  gallery
			  );
				$asoHolder.quicksand(
			  $filters4,
			  { duration: 1000 },
			  gallery
			  );
				$aspireHolder.quicksand(
			  $filters5,
			  { duration: 1000 },
			  gallery
			  );			  
			 		  
			  
			  
			  
		});

		$(document).ready(gallery);