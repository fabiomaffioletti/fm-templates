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

label {
	text-align: left;
}

form .input {
	margin-left: 80px;
}
</style>

<script	src="https://ajax.googleapis.com/ajax/libs/jquery/1.6.4/jquery.min.js"></script>
<script	src="js/bootstrap-dropdown.js"></script>

<decorator:head />
</head>

<body
	<decorator:getProperty property="body.id" writeEntireProperty="true"/>
	<decorator:getProperty property="body.class" writeEntireProperty="true"/>>

	<div class="topbar">
		<div class="fill">
			<div class="container">
				<a class="brand" href="/home.html"><fmt:message
						key="webapp.name" /></a>
				<ul class="nav">
					<li><a href="/anonymous.html">Read me</a></li>
					<auth:authorize ifAnyGranted="ROLE_USER,ROLE_ADMIN">
						<li><a href="/user.html">User</a></li>
					</auth:authorize>
					<auth:authorize ifAllGranted="ROLE_ADMIN">
						<li class="dropdown" data-dropdown="dropdown"><a href="#" class="dropdown-toggle">Admin</a>
							<ul class="dropdown-menu">
								<li><a href="/admin.html">User list</a></li>
								<li><a href="/admin-user.html">New user</a></li>
							</ul>
						</li>
					</auth:authorize>
					<auth:authorize ifNotGranted="ROLE_ANONYMOUS">
						<li><a href="/logout.jsp">Logout</a></li>
					</auth:authorize>
				</ul>
			</div>
		</div>
	</div>

	<decorator:body />
	
	<script>
		$('#topbar').dropdown();	
	</script>

</body>
</html>