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

<style type="text/css">
body {
	padding-top: 60px;
	padding-bottom: 40px;
}

.sidebar-nav {
	padding: 9px 0;
}
</style>

<!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
	<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
<![endif]-->

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>

<decorator:head />
</head>

<body <decorator:getProperty property="body.id" writeEntireProperty="true"/> <decorator:getProperty property="body.class" writeEntireProperty="true"/>>
	<div class="navbar navbar-inverse navbar-fixed-top">
		<div class="navbar-inner">
			<div class="container-fluid">
				<a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse"> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span>
				</a> <a class="brand" href="#"><fmt:message key="webapp.name" /></a>
				<div class="nav-collapse collapse">
					<auth:authorize ifNotGranted="ROLE_ANONYMOUS">
						<ul class="nav pull-right">
							<li><a href="/logout.jsp">Logout</a></li>
						</ul>
						<ul class="nav">
							<li class="dropdown"><a href="#" class="dropdown-toggle"
								data-toggle="dropdown"><strong>Switch language</strong> <b class="caret"></b></a>
								<ul class="dropdown-menu" id="languages">
								</ul>
							</li>
							<c:if test="${not empty language}">
								<li><a href="list-video.html">Video</a></li>
							</c:if>
						</ul>
					</auth:authorize>
				</div>
			</div>
		</div>
	</div>

	<div class="container-fluid">
		<div class="row-fluid">
			<decorator:body />
		</div>
	</div>

<script>
	$(document).ready(function() {
		$.getJSON("/rest/languages", function(data) {
			$.each(data, function(key, val) {
				var li = $("<li/>").appendTo($('#languages'));
				var a = $("<a/>", {href: '/switch-language.html?isoShort='+val.isoShort, text: val.name}).appendTo(li);
			});
		});
	});
</script>
	
</body>
</html>