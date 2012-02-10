<%@ include file="/common/taglibs.jsp"%>

<html>
<head>
<title>Admin</title>
</head>

<body>
	<div class="container">
		<div class="content">
			<div class="row">
				<div class="span16">
					<h2>User detail</h2>
					<jsp:include page="/common/messages.jsp" />
					<form:form commandName="user" action="/admin-user.html" method="post">
						<c:set var="errors"><form:errors path="*" /></c:set>
						<c:if test="${not empty errors}">
							<div class="alert-message block-message info">
								<form:errors path="*" htmlEscape="false" />
							</div>
						</c:if>
						<form:hidden path="id" />
						<form:hidden path="password" />
						<fieldset>
							<div class="clearfix">
								<form:label path="username">Username</form:label>
								<div class="input">
									<c:choose>
										<c:when test="${user.id == null}">
											<form:input path="username" />
										</c:when>
										<c:otherwise>
											<form:hidden path="username" />
											<form:input path="username" disabled="true" />
										</c:otherwise>
									</c:choose>
								</div>
							</div>
							<div class="clearfix">
								<label for="email">Email</label>
								<div class="input">
									<form:input path="email" />
								</div>
							</div>
							<div class="clearfix">
								<table class="bordered-table">
									<thead>
										<tr>
											<th>Enabled</th>
											<th>Account locked</th>
											<th>Account expired</th>
											<th>Credentials expired</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td><form:checkbox path="enabled" /></td>
											<td><form:checkbox path="accountLocked" /></td>
											<td><form:checkbox path="accountExpired" /></td>
											<td><form:checkbox path="credentialsExpired" /></td>
										</tr>
									</tbody>
								</table>
							</div>
							<div class="clearfix">
								<label for="roles">Roles</label>
								<div class="input">
									<form:select multiple="true" path="roles" items="${allRoles}"
										itemLabel="roleName" itemValue="id"></form:select>
								</div>
							</div>
							<div class="clearfix">
								<div class="actions" style="padding-left: 20px;">
									<a class="btn" href="/admin-user.html?id=<c:out value="${user.id}" escapeXml="true" />">Cancel</a>
									<input type="submit" class="btn primary" value="Save">
								</div>
							</div>
						</fieldset>
					</form:form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>