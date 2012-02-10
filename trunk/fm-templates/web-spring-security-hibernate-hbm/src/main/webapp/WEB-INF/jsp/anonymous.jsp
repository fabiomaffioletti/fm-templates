<%@ include file="/common/taglibs.jsp"%>

<html>
<head>
	<title>Read me</title>
</head>

<body>
	<div class="container">
		<div class="content">
			<div class="row">
				<div class="span16">
					<p>Everyone is able to access this page since it is not secured. Here is a list of pre-configured users to access the web application.</p>
					<table class="bordered-table">
						<thead>
							<tr>
								<th>Username</th>
								<th>Password</th>
								<th>Roles</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>user</td>
								<td>user</td>
								<td>ROLE_USER</td>
							</tr>
							<tr>
								<td>admin</td>
								<td>admin</td>
								<td>ROLE_ADMIN, ROLE_USER</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</body>

</html>