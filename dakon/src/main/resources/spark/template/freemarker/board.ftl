<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->

	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<title>${brand} / ${title}</title>
		<meta name="viewport" content="width=device-width">

		<link rel="stylesheet" href="/css/dakon.css">
		<link href="/css/bootstrap.min.css" rel="stylesheet">

		<style>
	      body {
	        padding-top: 60px; 
	      }
	    </style>

		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
		<script src="/js/bootstrap.min.js"></script>
		<script src="/js/dakon.js"></script>
		<script>var onBoard = true;</script>
	</head>
	<body>

	<!--[if lt IE 7]>
        <p class="chromeframe">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">
        upgrade your browser</a> or <a href="http://www.google.com/chromeframe/?redirect=true">activate Google Chrome Frame
        </a> to improve your experience.</p>
    <![endif]-->

		<div class="navbar navbar-inverse navbar-fixed-top">
		  <div class="navbar-inner">
		    <div class="container">
		      <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
		        <span class="icon-bar"></span>
		        <span class="icon-bar"></span>
		        <span class="icon-bar"></span>
		      </a>
		      <a class="brand" href="/">${brand} / ${title}</a>
		      <div class="nav-collapse collapse">
		        <ul class="nav">
		          <li><a href="/">Home</a></li>
		          <li class="dropdown active">
		            <a class="dropdown-toggle" data-toggle="dropdown" href="#">
		              Play
		              <b class="caret"></b>
		            </a>
		            <ul class="dropdown-menu">
		              
						<li>
		                    <a href="/dakon" id="menuStart">
		                       <i class="icon-play"></i>
		                       Start New Board
		                     </a>
		                  </li>
					    <li class="divider"></li>
					  	<li>
					  		<a href="/#" id="menuSave">
					  			<i class="icon-download-alt"></i>
					  			Save Board
					  		</a>
					  	</li>
					  	<li class="divider"></li>
					  	<li>
					  		<a href="/dakon" id="menuLoad">
					  			<i class="icon-share"></i>
					  			Load Board
					  		</a>
					  	</li>
		            </ul>
		          </li>
		          
		          <li><a href="${source}" target="_blank">About</a></li>
		        </ul>
		      </div><!--/.nav-collapse -->
		    </div>
		  </div>
		</div>

		<div class="container">
			<div class="hero-unit">
				<div class="board" id="dakonBoard">
					<img src="/images/mancalaboard.jpg"/>
		      	</div>
		      	<div class="pits" id="dakonPits">
		      		<#list board.getPits() as pit>
		  			<div class="pit" id="${pit.getId()}">
		  				<#list pit.stones as stone>
			  			<div class="stone" id="${stone.uuid}">
							<img src="/images/ruby.png"/>
				      	</div>	
			  			</#list>
			      	</div>	
	  				</#list>
		      	</div>
		      	<div class="highlights" id="dakonHighlights">
			      	<div class="highlight" id="highlightTop">
			      		<img src="/images/highlight_top.png"/>
			      	</div>
			      	<div class="highlight" id="highlightBottom">
			      		<img src="/images/highlight_bottom.png"/>
		      		</div>
		      	</div>
		      	<div class="instructions" id="dakonInstructions">
			      	<div class="instruction" id="instructionTop">
			      		Your turn to play, <strong>${board.players[0].name}</strong>. Select any pit on the top row.
			      	</div>
			      	<div class="instruction" id="instructionBottom">
			      		Your turn to play, <strong>${board.players[1].name}</strong>. Select any pit from the bottom row.
		      		</div>
		      	</div>
		      	<div class="fileForm" id="dakonForm">
				<form method="POST" action="/dakon" id="loadFileForm">
			  		<input type="file" name="filePathField" id="filePathField"/>
			  		<input type="hidden" name="fileContentField" id="fileContentField"/>
			    </form>
			</div>
	      	</div>
  			
	      	<footer>
			  <p>Developed by <a href="http://xvronny.me/" target="_blank">xvronny</a>.</p>
			</footer>
		</div>
	</body>
</html>