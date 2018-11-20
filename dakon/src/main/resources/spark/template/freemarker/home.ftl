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
	</head>
	<body>

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
		                    <a href="/#" id="menuStart">
		                       <i class="icon-play"></i>
		                       Start New Board
		                    </a>
		                </li>
					  	<li class="divider"></li>
					  	<li>
					  		<a href="/#" id="menuLoad">
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
				<img src="/images/fishboard_small.png"/>
		        <h1>${brand}</h1>
		        <br />
		        <p><strong>${title}</strong>, also known as <strong>Mancala</strong>, is the most ancient board game known to mankind,
		        with the oldest board found dating back from the 4th century Roman Egypt era. <strong>Dakon</strong> is the name of
		        this bean-sowing game in the region of East Java.</p>
		        <p><strong>Interested?</strong> This project is hosted @<a href="${source}">github</a>, feel free to fork, play, and
		        modify as you please.
	      	</div>

	      	<div class="alert alert-block" id="dakonRules">
			  <h4>How to Play</h4>
			  <br />
			  <p>I didn't know how to play Dakon or Mancala, because I've never played it. Thankfully nowadays we have Youtube
			  explainers for nearly every subject there is in the universe. View the video below if you don't have any idea
			  on how the game works.</p><br />
			  <iframe width="853" height="480" src="//www.youtube.com/embed/k4drX3HOnMg" frameborder="0" allowfullscreen></iframe>
			  <br />
			</div>
			
			<div class="fileForm" id="dakonForm">
				<form method="POST" action="/dakon" id="loadFileForm">
			  		<input type="file" name="filePathField" id="filePathField"/>
			  		<input type="hidden" name="fileContentField" id="fileContentField"/>
			    </form>
			</div>

	      	<hr>

	      	<footer>
			  <p>Developed by <a href="http://xvronny.me/" target="_blank">xvronny</a> using <a href="http://www.sparkjava.com/" target="_blank">
			  SparkJava</a> framework and <a href="http://freemarker.org/" target="_blank">FreeMarker</a> templates on Java 8. The front end work
			  owes much to <a href="http://devtyr.com/" target="_blank">DevTyr</a>.</p>
			</footer>
		</div>
	</body>
</html>