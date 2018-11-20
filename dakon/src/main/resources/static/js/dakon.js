
// ---------------------------------------------------- 
// initialize positions of pits and stones in the board
// ----------------------------------------------------

var positions =   [ [ 619.5, 293 ], [ 536.5, 299 ], [ 448.5, 301 ], 
				    [ 366.5, 298 ], [ 282.5, 286 ], [ 196.5, 286 ], [ 115.5, 326 ],
					[ 198.5, 382 ], [ 284.5, 380 ], [ 370.5, 377 ], 
					[ 449.5, 380 ], [ 530.5, 379 ], [ 609.5, 374 ], [ 692.5, 351 ] ];

// UI instantiation of the board

function handleMoves(moves) {
	for (i = 0; i < moves.length; i++) { 
	    var move = moves[i];
	    console.log(move);
	    if (move.name === "move") {
	    	moveStone(move.stone, move.origin, move.destination);
	    }
	    else if (move.name === "capture") {
	    	capturePit(move.ownPit, move.ownStone, move.capturedPit, move.capturedStones, move.target);
	    }
	    else if (move.name === "finish") {
	    	finishGame(move.winningPlayer);
	    }
	    else {
	    	switchPlayer(move.currentPlayer);
	    }
	}
}

function moveStone(stone, origin, destination) {
	var delta = getAnimationDelta(origin, destination),
	    index = $( "#"+destination.id ).children().length,
	    position = getAllocatedPosition(index);
	$( "#"+stone.uuid ).animate({
	    top: delta.y, left: delta.x,
	  }, // animate
	  5000, // animationDuration
	  function () { // onAnimationCompleted
		  $( "#"+stone.uuid )
		  		.detach()
		  		.appendTo("#"+destination.id)
		  		.css({
				    top : position.top,
				    left : position.left,
				    zIndex : (2000 + index)
				  });
	  });
}

function capturePit(ownPit, ownStone, opposite, captives, lubang) {
	moveStone(ownStone, ownPit, lubang);
	for (var i = 0; i < captives.length; i++) {
		moveStone(captives[i], opposite, lubang);
	}
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

function finishGame(player) {
	alert("Congratulations "+player.name+", we have a winner!");
}

function getAllocatedPosition(stoneIndex) {
	var center = {left : 30, top : 24}, radius = 20;
	if (stoneIndex == 0) return center;
	var radian = (stoneIndex-1) * Math.PI / 6;
	return  {top : center.top + (radius * Math.sin(radian)),
		     left : center.left + (radius * Math.cos(radian))};
}

function getAnimationDelta(origin, destination) {
	var start = $( "#"+origin.id ).position(),
	    finish = $( "#"+destination.id ).position(),
	    center = {left : 30, top : 24};
	return { x : (finish.left - start.left) + center.left, 
		     y : (finish.top - start.top) + center.top };
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
									//border:'1px solid black'
								});
								$(this).children().each(function(index) {
									var pos = getAllocatedPosition(index);
									$(this).css({
										width : 20,
										height : 20,
										top : pos.top,
										left : pos.left,
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
			// register pit click & hover handlers
			$("#dakonPits")
				.click(function(event) {
					var pitId = event.target.id;
					if ($(event.target).attr("class") !== "pit") {
						pitId = $(event.target).closest(".pit").attr('id');
					}
					$.post( "/board", pitId, function( result ) {
						  	console.log(result);
						  	handleMoves(eval(result));
						})
						.fail(function(e) {
						    alert(e.responseText);
						})
				})
				.hover( // onMouseEnter
					function(event) { 
						var pitId = event.target.id;
						if ($(event.target).attr("class") !== "pit") {
							pitId = $(event.target).closest(".pit").attr('id');
						}
						var size = $( "#"+pitId ).children().length;
						$("#"+pitId).append("<span class=\"pitSize\">"+size+"</span>");
					}, // onMouseExit
					function(event) { 
						$( this ).find( "span:last" ).remove();					
					}
				);
			// initialize highlights
			switchPlayer(currentPlayer);
		});
