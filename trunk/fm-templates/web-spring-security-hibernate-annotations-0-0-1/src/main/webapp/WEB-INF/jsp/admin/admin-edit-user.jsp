<%@ include file="/common/taglibs.jsp"%>

<head>
	<title>Administration</title>
	
	<style>
		.form-horizontal .short-label {
			float: left;
		    padding-top: 5px;
		    text-align: right;
		}
		.form-horizontal .controls {
		    margin-left: 80px;
		}
		.short-label-error {
			float: left;
		    padding-top: 5px;
		    text-align: right;
			color: #B94A48;
		}
		.controls .error {
			border-color: #B94A48;
			-webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075);
			-moz-box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075);
			box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075);
			color: #B94A48;
		}
	</style>
</head>

<form:form class="form-horizontal" action="admin-edit-user.html" commandName="user">
	<div class="row-fluid">
		<jsp:include page="../../../common/messages.jsp" />
	</div>
	<div class="row-fluid">
		<div class="span2">
			<div class="well">
				<a href="admin-list-user-actions.html?performedBy=${user.id}">User actions</a>
			</div>
		</div>
		<div class="span10">
			<c:set var="errors"><form:errors path="*" /></c:set>
			<c:if test="${not empty errors}">
				<div class="row-fluid">
					<div class="span12">
						<div class="alert alert-error">
							<form:errors path="*" htmlEscape="false" />
						</div>
					</div>
				</div>
			</c:if>
			
			<div class="row-fluid">
				<div class="span4">
					<form:hidden path="id" />
					<form:hidden path="password"/>
					<form:hidden path="userLoginStatistic.loginOk"/>
					<form:hidden path="userLoginStatistic.lastLoginOk"/>
					<form:hidden path="userLoginStatistic.loginKo"/>
					<form:hidden path="userLoginStatistic.lastLoginKo"/>
					<form:hidden path="userLoginStatistic.lastLoginOkIPAddress"/>
					<form:hidden path="userLoginStatistic.lastLoginKoIPAddress"/>
					<form:hidden path="createdAt"/>
					<form:hidden path="createdBy"/>
					
					<legend>Account data</legend>
					<div class="control-group">
						<form:label path="username" cssClass="short-label" cssErrorClass="short-label-error">Username</form:label>
						<div class="controls">
							<form:input path="username" placeholder="Username" cssErrorClass="error" />
						</div>
					</div>
					<div class="control-group">
						<form:label path="email" cssClass="short-label" cssErrorClass="short-label-error">Email</form:label>
						<div class="controls">
							<form:input path="email" placeholder="Email address" cssErrorClass="error"/>
						</div>
					</div>
					<table class="table">
						<tr><th>Enabled</th><th>Locked</th><th>Expired</th><th>Credentials expired</th></tr>
						<tr>
							<td><form:checkbox path="enabled" /></td>
							<td><form:checkbox path="accountLocked" /></td>
							<td><form:checkbox path="accountExpired" /></td>
							<td><form:checkbox path="credentialsExpired" /></td>
						</tr>
					</table>
					<div class="control-group">
						<form:label path="roles" cssClass="short-label" cssErrorClass="short-label-error">Roles</form:label>
						<div class="controls">
							<form:select path="roles" items="${roles}" itemLabel="roleName" itemValue="id" cssErrorClass="error"/>
						</div>
					</div>
				</div>
				
				<div class="span4">
					<legend>User data</legend>
					<div class="control-group">
						<label class="short-label" for="firstName">First name</label>
						<div class="controls">
							<form:input path="firstName" placeholder="First name"/>
						</div>
					</div>
					<div class="control-group">
						<label class="short-label" for="lastName">Last name</label>
						<div class="controls">
							<form:input path="lastName" placeholder="Last name"/>
						</div>
					</div>
					<div class="control-group">
						<label class="short-label" for="address.street">Street</label>
						<div class="controls">
							<form:input path="address.street" placeholder="Street"/>
						</div>
					</div>
					<div class="control-group">
						<label class="short-label" for="address.number">Number</label>
						<div class="controls">
							<form:input path="address.number" placeholder="Number"/>
						</div>
					</div>
					<div class="control-group">
						<label class="short-label" for="address.zipCode">ZIP code</label>
						<div class="controls">
							<form:input path="address.zipCode" placeholder="ZIP code"/>
						</div>
					</div>
					<div class="control-group">
						<label class="short-label" for="address.city">City</label>
						<div class="controls">
							<form:input path="address.city" placeholder="City"/>
						</div>
					</div>
					<div class="control-group">
						<label class="short-label" for="address.state">State</label>
						<div class="controls">
							<form:input path="address.state" placeholder="State"/>
						</div>
					</div>
					<div class="control-group">
						<label class="short-label" for="address.country">Country</label>
						<div class="controls">
							<form:input path="address.country" placeholder="Country"/>
						</div>
					</div>
				</div>
				
				<div class="span4">
					<legend>Usage data</legend>
					<table class="table table-bordered">
						<tr>
							<th>Last successful login</th>
							<td><fmt:formatDate value="${user.userLoginStatistic.lastLoginOk}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						</tr>
						<tr>
							<th>Number of successful logins</th>
							<td><c:out value="${user.userLoginStatistic.loginOk}" escapeXml="true" /></td>
						</tr>
						<tr>
							<th>Last successful login IP address</th>
							<td><c:out value="${user.userLoginStatistic.lastLoginOkIPAddress}" escapeXml="true" /></td>
						</tr>
						<tr>
							<th>Last login failure</th>
							<td><fmt:formatDate value="${user.userLoginStatistic.lastLoginKo}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						</tr>
						<tr>
							<th>Number of failed logins</th>
							<td><c:out value="${user.userLoginStatistic.loginKo}" escapeXml="true" /></td>
						</tr>
						<tr>
							<th>Last failed login IP address</th>
							<td><c:out value="${user.userLoginStatistic.lastLoginKoIPAddress}" escapeXml="true" /></td>
						</tr>
					</table>
					
					<table class="table table-bordered">
						<tr>
							<th>Modified by</th>
							<td><c:out value="${user.modifiedBy}" escapeXml="true" /></td>
						</tr>
						<tr>
							<th>Modified at</th>
							<td><fmt:formatDate value="${user.modifiedAt}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						</tr>
						<tr>
							<th>Created by</th>
							<td><c:out value="${user.createdBy}" escapeXml="true" /></td>
						</tr>
						<tr>
							<th>Created at</th>
							<td><fmt:formatDate value="${user.createdAt}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						</tr>
					</table>
				</div>
			</div>
		</div>
		
		

	</div>
	
	<div class="row-fluid">
		<div class="span12">
			<div class="form-actions">
				<button type="submit" class="btn btn-primary">Save</button>
				<c:if test="${not empty user.id}">
	  				<a type="button" class="btn btn-primary" href="admin-reset-user.html?id=${user.id}">Reset password</a>
	  				<a type="button" class="btn btn-danger" href="admin-delete-user.html?id=${user.id}">Delete</a>
  				</c:if>
  				<a type="button" class="btn" href="admin-list-users.html">Back to users list</a>
			</div>
		</div>
	</div>
</form:form>

