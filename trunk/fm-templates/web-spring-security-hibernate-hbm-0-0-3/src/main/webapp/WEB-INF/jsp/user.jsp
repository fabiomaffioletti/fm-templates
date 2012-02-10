<%@ include file="/common/taglibs.jsp"%>

<html>
<head>
	<title>User</title>
</head>

<body>
	<div class="container">
		<div class="content">
			<div class="row">
				<div class="span16">
					<h2>User page</h2>
					<jsp:include page="/common/messages.jsp" />
					<form:form commandName="user" action="/user.html" method="post">
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
									<form:hidden path="username" />
									<form:input path="username" disabled="true" />
								</div>
							</div>
							<div class="clearfix">
								<label for="email">Email</label>
								<div class="input">
									<form:input path="email" />
								</div>
							</div>
							<div class="clearfix">
								<form:label path="newPassword">Password</form:label>
								<div class="input">
									<form:password path="newPassword" showPassword="true" />
								</div>
							</div>
							<div class="clearfix">
								<form:label path="confirmNewPassword">Confirm password</form:label>
								<div class="input">
									<form:password path="confirmNewPassword" showPassword="true" />
								</div>
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
									<a class="btn" href="/user.html">Cancel</a>
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