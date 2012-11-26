<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<%@ include file="/common/meta.jsp"%>
<title><fmt:message key="webapp.name" /> | <decorator:title /></title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/font-awesome.css" rel="stylesheet">

<style type="text/css">
body {
	padding-top: 60px;
	padding-bottom: 40px;
}

</style>

<!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
	<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
<![endif]-->

<script src="js/jquery-1.8.2.min.js"></script>
<script src="js/bootstrap.min.js"></script>

<decorator:head />
</head>

<body <decorator:getProperty property="body.id" writeEntireProperty="true"/> <decorator:getProperty property="body.class" writeEntireProperty="true"/>>
	
	
	<div class="navbar navbar-inverse navbar-fixed-top">
		<div class="navbar-inner">
			<div class="container-fluid">
				<a class="brand" href="#">Title</a>
				<ul class="nav">
				  <li><a href="admin-list-users.html">Users</a></li>
				  <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown">Configuration <b class="caret"></b></a>
					  <ul class="dropdown-menu">
					  	<li><a href="admin-list-configs.html">Config list</a></li>
					  	<li><a href="admin-edit-config.html">New config</a></li>
					  </ul>
				  </li>
				  <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown">Countries <b class="caret"></b></a>
					  <ul class="dropdown-menu">
					  	<li><a href="admin-edit-country.html?id=1">Country 1</a></li>
					  	<li><a href="admin-edit-country.html">New country</a></li>
					  </ul>
				  </li>
				  <li><a href="admin-list-user-actions.html">User actions</a></li>
				  <li><a href="logout.jsp">Logout</a></li>
				</ul>
			</div>
		</div>
	</div>
	
	<div class="container-fluid">
		<decorator:body />
	</div>
	
</body>
</html>