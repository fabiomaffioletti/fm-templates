<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<%@ include file="/common/meta.jsp" %>
	<title><fmt:message key="webapp.name" /> | <decorator:title /></title>
	
	<!-- Le HTML5 shim, for IE6-8 support of HTML elements -->
	<!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

<!-- Le styles -->
<link href="css/bootstrap.css" rel="stylesheet">

<style type="text/css">
/* Override some defaults */
html,body {
	background-color: #eee;
	background-image: url('img/black_paper.png');
}

body {
	padding-top: 20px;
	/* 40px to make the container go all the way to the bottom of the topbar */
	padding-bottom: 20px;
}

.container>footer p {
	text-align: center; /* center align it with the container */
}

/* The white background content wrapper */
.content {
	background-color: #fff;
	padding: 20px;
	margin: 0 -20px;
	/* negative indent the amount of the padding to maintain the grid system */
}

section {
	padding-top: 20px;
}

input[type=text] {
	border-top: 0px;
	border-left: 0px;
	border-right: 0px;
	box-shadow: 0 0px 0px rgba(0, 0, 0, 0.075) inset;
}

input[type=text]:focus {
	border-top: 0px;
	border-left: 0px;
	border-right: 0px;
	box-shadow: 0 0px 0px rgba(0, 0, 0, 0.075) inset;
}
</style>

<script src="js/jquery-1.8.0.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/ICanHaz.min.js"></script>
<script src="js/jlinq.js"></script>
	
	<decorator:head />
</head>

<body <decorator:getProperty property="body.id" writeEntireProperty="true"/> <decorator:getProperty property="body.class" writeEntireProperty="true"/>>

	<div class="container">
		<div class="content">
			<div class="row" style="margin-bottom: 20px;">
				<div class="span12">
					<img src="img/4.jpg">
				</div>
			</div>
			<decorator:body/>
		</div>
	</div>

</body>
</html>