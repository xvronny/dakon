

var positions = [[190.5 , 286],[276.5 , 286],[366.5 , 298],[448.5 , 301],[536.5 , 299],[617.5 , 293],[692.5 , 351],
                 [611.5 , 371],[530.5 , 379],[449.5 , 380],[370.5 , 375],[281.5 , 374],[195.5 , 380],[115.5 , 326]];

$(document).ready(function() {
	$(".container").css({position: 'relative'});
	$(".pit").each(function(index, element) { 
		var margin = 15,
		    pitTop = positions[index][1] + margin,
		    pitLeft = positions[index][0] + margin;
		$(this).css({
			width:80, height:80, top: pitTop, left: pitLeft, 
			position:'absolute', zIndex:(1000 + index), 
			border:'1px solid black'
		});
	});
	/* $("#dakonBoard").click( function(e) { 
		var posX = $(this).offset().left,
	        posY = $(this).offset().top;
		console.log((e.pageX - posX) + ' , ' + (e.pageY - posY));
	}); */
});

