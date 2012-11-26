<%@ include file="/common/taglibs.jsp"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title><fmt:message key="webapp.name" /> | Login</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<!-- Le styles -->
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/font-awesome.css" rel="stylesheet">

<style type="text/css">
body {
	padding-top: 40px;
	padding-bottom: 40px;
	background-color: #f5f5f5;
}

.form-signin {
	max-width: 300px;
	padding: 19px 29px 29px;
	margin: 0 auto 20px;
	background-color: #fff;
	border: 1px solid #e5e5e5;
	-webkit-border-radius: 5px;
	-moz-border-radius: 5px;
	border-radius: 5px;
	-webkit-box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
	-moz-box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
	box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
}

.form-signin .form-signin-heading,.form-signin .checkbox {
	margin-bottom: 10px;
}

.form-signin input[type="text"],.form-signin input[type="password"] {
	font-size: 16px;
	height: auto;
	margin-bottom: 15px;
	padding: 7px 9px;
}
</style>

	<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
	<!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

<script src="js/jquery-1.8.2.min.js"></script>
<script src="js/bootstrap.min.js"></script>

</head>

<body>

	<div class="container">
		<form method="post" class="form-signin" id="loginForm" action="/j_security_check">
			<jsp:include page="/common/messages.jsp" />
			<c:if test="${param.error != null}">
				<div class="alert alert-error">
					<button type="button" class="close" data-dismiss="alert">×</button>
					<h4>Login failed</h4>
					Wrong username/password combination 
				</div>
			</c:if>
			
			<h2 class="form-signin-heading">Please login</h2>
			<input type="text" name="j_username" id="j_username" class="input-block-level" placeholder="Username">
			<input type="password" name="j_password" id="j_password" class="input-block-level" placeholder="Password">
			<label class="checkbox">
				<input type="checkbox" value="remember-me">Remember me
			</label>
			<button class="btn btn-large btn-primary" type="submit">Login</button>
		</form>

	</div>
	<!-- /container -->

<script>
	$("#loginForm input:first").focus();
</script>

</body>
</html>
