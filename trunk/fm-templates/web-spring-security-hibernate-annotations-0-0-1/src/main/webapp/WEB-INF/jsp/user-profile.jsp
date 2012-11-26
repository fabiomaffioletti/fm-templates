<%@ include file="/common/taglibs.jsp"%>

<head>
	<title>Profile</title>
</head>

<div class="row-fluid">
		<div class="span4">
			<table class="table table-condensed table-striped table-bordered">
				<tr>
					<th>User ID</th>
					<td><c:out value="${userProfile.id}" escapeXml="true" /></td>
				</tr>
				<tr>
					<th>Username</th>
					<td><c:out value="${userProfile.username}" escapeXml="true" /></td>
				</tr>
				<tr>
					<th>Email</th>
					<td><c:out value="${userProfile.email}" escapeXml="true" /></td>
				</tr>
			</table>
			<table class="table table-condensed table-striped table-bordered">
				<tr>
					<th>Last successful login</th>
					<td><fmt:formatDate value="${userProfile.userLoginStatistic.lastLoginOk}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				</tr>
				<tr>
					<th>Successful logins</th>
					<td><c:out value="${userProfile.userLoginStatistic.loginOk}" escapeXml="true" /></td>
				</tr>
				<tr>
					<th>Last successful login IP</th>
					<td><c:out value="${userProfile.userLoginStatistic.lastLoginOkIPAddress}" escapeXml="true" /></td>
				</tr>
				<tr>
					<th>Last failed login</th>
					<td><fmt:formatDate value="${userProfile.userLoginStatistic.lastLoginKo}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				</tr>
				<tr>
					<th>Failed logins</th>
					<td><c:out value="${userProfile.userLoginStatistic.loginKo}" escapeXml="true" /></td>
				</tr>
				<tr>
					<th>Last failed login IP</th>
					<td><c:out value="${userProfile.userLoginStatistic.lastLoginKoIPAddress}" escapeXml="true" /></td>
				</tr>
			</table>
		</div>
		<div class="span4">
			<table class="table table-condensed table-striped table-bordered">
				<tr>
					<th colspan="2">Main address</th>
				</tr>
				<tr>
					<th>Name</th>
					<td><c:out value="${userProfile.address.firstName}" escapeXml="true" /> <c:out value="${userProfile.address.lastName}" escapeXml="true" /></td>
				</tr>
				<tr>
					<th>Street</th>
					<td><c:out value="${userProfile.address.street}" escapeXml="true" /></td>
				</tr>
				<tr>
					<th>Number</th>
					<td><c:out value="${userProfile.address.number}" escapeXml="true" /></td>
				</tr>
				<tr>
					<th>City</th>
					<td><c:out value="${userProfile.address.city}" escapeXml="true" /></td>
				</tr>
				<tr>
					<th>State</th>
					<td><c:out value="${userProfile.address.state}" escapeXml="true" /></td>
				</tr>
				<tr>
					<th>ZIP code</th>
					<td><c:out value="${userProfile.address.zipCode}" escapeXml="true" /></td>
				</tr>
				<tr>
					<th>Country</th>
					<td><c:out value="${userProfile.address.country}" escapeXml="true" /></td>
				</tr>
			</table>
		</div>
	</div>
</div>