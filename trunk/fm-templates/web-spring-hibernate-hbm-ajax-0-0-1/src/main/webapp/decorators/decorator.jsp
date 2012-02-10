<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/common/taglibs.jsp"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/meta.jsp"%>
<title><fmt:message key="webapp.name" /> | <decorator:title /></title>
<link rel="icon" href="/images/favicon.ico" type="image/x-icon" />

<link href="css/bootstrap.min.css" rel="stylesheet" />

<!-- Le HTML5 shim, for IE6-8 support of HTML elements -->
<!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

<style type="text/css">
html,body {
	background-color: #eee;
}

body {
	padding-top: 40px;
}

.content {
	background-color: #fff;
	padding: 20px;
	margin: 0 -20px;
}
</style>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.6.4/jquery.min.js"></script>

<decorator:head />
</head>

<body
	<decorator:getProperty property="body.id" writeEntireProperty="true"/>
	<decorator:getProperty property="body.class" writeEntireProperty="true"/>>

	<div class="navbar navbar-fixed-top">
		<div class="navbar-inner">
			<div class="container">
				<a class="btn btn-navbar" data-toggle="collapse"
					data-target=".nav-collapse"> <span class="icon-bar"></span> <span
					class="icon-bar"></span> <span class="icon-bar"></span>
				</a> <a class="brand" href="#"><fmt:message key="webapp.name" /></a>
				<div class="nav-collapse">
					<ul class="nav">
						<li><a href="#">Countries</a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>


	<decorator:body />

</body>
</html>