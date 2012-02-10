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
					<h2>User list</h2>
					<jsp:include page="/common/messages.jsp" />
					<p>
						<display:table name="users" id="user" class="bordered-table">
							<display:column property="username" escapeXml="true" href="/admin-user.html" paramId="id" paramProperty="id" />
							<display:column property="email" escapeXml="true" />
							<display:column titleKey="admin.users.enabled">
								<c:choose>
									<c:when test="${user.enabled}">
										<input type="checkbox" disabled checked="checked" />
									</c:when>
									<c:otherwise>
										<input type="checkbox" disabled />
									</c:otherwise>
								</c:choose>
							</display:column>
							<display:column titleKey="admin.users.locked">
								<c:choose>
									<c:when test="${user.accountLocked}">
										<input type="checkbox" disabled checked="checked" />
									</c:when>
									<c:otherwise>
										<input type="checkbox" disabled />
									</c:otherwise>
								</c:choose>
							</display:column>
							<display:column titleKey="admin.users.expired">
								<c:choose>
									<c:when test="${user.accountExpired}">
										<input type="checkbox" disabled checked="checked" />
									</c:when>
									<c:otherwise>
										<input type="checkbox" disabled />
									</c:otherwise>
								</c:choose>
							</display:column>
							<display:column titleKey="admin.users.password">
								<c:choose>
									<c:when test="${user.credentialsExpired}">
										<input type="checkbox" disabled checked="checked" />
									</c:when>
									<c:otherwise>
										<input type="checkbox" disabled />
									</c:otherwise>
								</c:choose>
							</display:column>
							<display:column titleKey="admin.users.roles">
								<ul class="unstyled" style="margin:0;">
									<c:forEach var="role" items="${user.roles}">
										<li><c:out value="${role.roleName}"></c:out></li>
									</c:forEach>
								</ul>
							</display:column>
						</display:table>
					</p>
				</div>
			</div>
		</div>
	</div>
</body>
</html>