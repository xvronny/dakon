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
		<script>var board;</script>
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
		                    <a href="/board">
		                       <i class="icon-signal"></i>
		                       Start New Board
		                     </a>
		                  </li>
					    <li class="divider"></li>
					  	<li>
					  		<a href="/save">
					  			<i class="icon-list"></i>
					  			Save Board
					  		</a>
					  	</li>
					  	<li class="divider"></li>
					  	<li>
					  		<a href="/load">
					  			<i class="icon-list"></i>
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
		  			<div class="pit" id=${pit.getId()}>
		  				<#list pit.stones as stone>
			  			<div class="stone" id=${stone.uuid}>
							<img src="/images/ruby.png"/>
				      	</div>	
			  			</#list>
			      	</div>	
	  				</#list>
		      	</div>
	      	</div>
	      	
	      	
  			
	      	<footer>
			  <p>Developed by <a href="http://xvronny.me/" target="_blank">xvronny</a>.</p>
			</footer>
		</div>
	</body>
</html>