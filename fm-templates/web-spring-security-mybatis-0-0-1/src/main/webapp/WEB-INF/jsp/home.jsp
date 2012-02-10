<%@ include file="/common/taglibs.jsp"%>

<html>
<head>
	<title>Home</title>
</head>

<body>
	<div class="container">
		<div class="content">
			<div class="row">
				<div class="span16">
					<auth:authentication property="principal" var="p"/>
					<h2>Logged in as <c:out value="${p.username}" /></h2>
				</div>
			</div>
		</div>
	</div>
</body>

</html>