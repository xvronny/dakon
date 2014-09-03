
// ---------------------------------------------------- 
// initialize positions of pits and stones in the board
// ----------------------------------------------------

var positions = [ [ 617.5, 293 ], [ 536.5, 299 ], [ 448.5, 301 ],
		[ 366.5, 298 ], [ 276.5, 286 ], [ 190.5, 286 ], [ 115.5, 326 ],
		[ 195.5, 380 ], [ 281.5, 374 ], [ 370.5, 375 ], [ 449.5, 380 ],
		[ 530.5, 379 ], [ 611.5, 371 ], [ 692.5, 351 ] ];

var currentPlayerName = "";

// UI instantiation of the board

function handleMoves(moves) {
	for (i = 0; i < moves.length; i++) { 
	    var move = moves[i];
	    if (move.name === "move") {
	    	moveStone(move.stone, move.origin, move.destination);
	    }
	    else if (move.name === "capture") {
	    	capturePit(move.finalPit, move.capturedPit, move.lubang, move.captives);
	    }
	    else {
	    	switchPlayer(move.currentPlayer);
	    }
	}
}

function moveStone(stone, origin, destination) {
	var start = $( "#"+origin.id ).position(), finish = $( "#"+destination.id ).position();
	var horizontal = finish.left - start.left, vertical = finish.top - start.top;
	/*$( "#"+stone.uuid ).animate({
	    top:vertical, left:horizontal,
	  }, 5000 );*/
	$( "#"+stone.uuid ).css({
	    top:finish.top, left:finish.left,
	  });
}

function capturePit(finalPit, opposite, lubang, stones) {
	
}

function switchPlayer(nextPlayer) {
	
	var currentHalf = (nextPlayer.name === topPlayer.name) ? "Top" : "Bottom";
	var otherHalf = (currentHalf === "Top") ? "Bottom" : "Top";
	
	var cssOn = {display : 'block', visibility : 'visible', zIndex : 120};
	var cssOff = {display : 'none', visibility : 'hidden', zIndex : -1};
	
	$("#highlight"+currentHalf).css(cssOn);
	$("#highlight"+otherHalf).css(cssOff);
	$("#instruction"+currentHalf).css(cssOn);
	$("#instruction"+otherHalf).css(cssOff);

	currentPlayer = nextPlayer;
}

$(document).ready(
		function() {
			// set the container to relative
			$(".container").css({
				position : 'relative'
			});
			// position the pits and each stone
			$(".pit")
					.each(
							function(index) {
								var margin = 15, pitTop = positions[index][1]
										+ margin, pitLeft = positions[index][0]
										+ margin;
								$(this).css({
									width : 80,
									height : 80,
									top : pitTop,
									left : pitLeft,
									position : 'absolute',
									zIndex : (1000 + index),
								// border:'1px solid black'
								});
								$(this).children().each(function(index) {
									$(this).css({
										width : 20,
										height : 20,
										top : 10 + (index * 5),
										left : 2 + (index * 11),
										position : 'absolute',
										zIndex : (2000 + index)
									});
								});
							});
			// hide the highlights
			$(".highlight").each(function(index) {
				$(this).css({
					top : 60,
					left : 60,
					position : 'absolute',
					display : 'none',
					visibility : 'hidden',
					zIndex : -1
				});
			});
			// hide the instructions
			$(".instruction").each(function(index) {
				$(this).css({
					width : 820,
					height : 30,
					top : 240 + (index * 280),
					left : 60,
					position : 'absolute',
					textAlign : 'center',
					display : 'none',
					visibility : 'hidden',
					zIndex : -1
				});
			});
			// hide the instructions
			$(".fileForm").each(function(index) {
				$(this).css({
					display : 'none',
					visibility : 'hidden',
					zIndex : -1
				});
			});
			// register menu handlers
			$("#menuStart").click(function() {
				$(location).attr('href', '/dakon');
			});
			$("#menuSave").click(function() {
				$(location).attr('href', '/board');
				return false;
			});
			$("#menuLoad").click(function() {
				$("#filePathField").trigger('click');
				return false;
			});
			// trigger load board
			$("#filePathField").change(function(event) {
				// Check for the various File API support.
				if (window.File && window.FileReader && window.FileList && window.Blob) {
					// Great success! All the File APIs are supported.
					var reader = new FileReader();
					reader.onload = function() {
						console.log(reader.result);
						$("#fileContentField").val(reader.result);
						console.log($("#fileContentField").val);
						$("#loadFileForm").submit();
					}
					console.log(event.target.files[0]);
					reader.readAsText(event.target.files[0]);
				} else {
					alert('The File APIs are not fully supported in this browser.');
				}
				return false;
			});
			// register pit click handlers
			$("#dakonPits").click(function(event) {
				var pitId = event.target.id;
				if ($(event.target).attr("class") !== "pit") {
					pitId = $(event.target).closest(".pit").attr('id');
				}
				$.post( "/board", pitId, function( result ) {
					  	console.log(result);
					  	handleMoves(eval(result));
					}).fail(function(e) {
					    alert("Error : "+e.responseText);
					});
			});
			switchPlayer(currentPlayer);
		});
