
// ---------------------------------------------------- 
// initialize positions of pits and stones in the board
// ----------------------------------------------------

var positions = [ [617.5 , 293],[536.5 , 299],[448.5 , 301],[366.5 , 298],[276.5 , 286],[190.5 , 286],[115.5 , 326],
                  [195.5 , 380],[281.5 , 374],[370.5 , 375],[449.5 , 380],[530.5 , 379],[611.5 , 371],[692.5 , 351] ];

$(document).ready(function() {
	// set the container to relative
	$(".container").css({position: 'relative'});
	// position the pits and each stone
	$(".pit").each(function(index) { 
		var margin = 15,
		    pitTop = positions[index][1] + margin,
		    pitLeft = positions[index][0] + margin;
		$(this).css({
			width:80, height:80, top: pitTop, left: pitLeft, 
			position:'absolute', zIndex:(1000 + index), 
			//border:'1px solid black'
		});
		$(this).children().each(
			function(index) { 
				$(this).css({
					width:20, height:20, top: 10+(index*5), left: 2+(index*11), 
					position:'absolute', zIndex:(2000 + index)
				});
			});
		});
	// hide the highlights
	$(".highlight").each(function(index) { 
			$(this).css({
				top: 60, left: 60, position:'absolute', 
				display: 'none', visibility: 'hidden', zIndex: -1
			});
		});
	// hide the instructions
	$(".instruction").each(function(index) { 
			$(this).css({
				width:820, height: 30, top: 240 + (index * 280), left: 60, 
				position:'absolute', textAlign:'center',
				display: 'none', visibility: 'hidden', zIndex: -1
			});
		});
	});


