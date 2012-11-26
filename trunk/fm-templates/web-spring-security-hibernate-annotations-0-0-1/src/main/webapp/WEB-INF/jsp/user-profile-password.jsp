<%@ include file="/common/taglibs.jsp"%>

<head>
	<title>Profile | Password</title>
	
	<style>
		.form-horizontal .short-label {
			float: left;
		    padding-top: 5px;
		    text-align: right;
		}
		.form-horizontal .controls {
		    margin-left: 150px;
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
		.form-horizontal .form-actions {
			padding-left: 150px;
		}
	</style>
</head>

<form:form class="form-horizontal" action="user-profile-password.html" id="changePasswordForm" commandName="userProfilePassword">
	<div class="row-fluid">
		<jsp:include page="../../common/messages.jsp" />
	</div>
	<div class="row-fluid">
		<div class="span12">
			<c:set var="errors"><form:errors path="*" /></c:set>
			<c:if test="${not empty errors}">
				<div class="alert alert-error">
					<form:errors path="*" htmlEscape="false" />
				</div>
			</c:if>
			
			<legend>Change password</legend>
			<div class="control-group">
				<form:label path="password" cssClass="short-label" cssErrorClass="short-label-error">Old password</form:label>
				<div class="controls">
					<form:password path="password" placeholder="Old password" cssErrorClass="error" />
				</div>
			</div>
			
			<form:hidden path="id" />
			
			<div class="control-group">
				<form:label path="newPassword" cssClass="short-label" cssErrorClass="short-label-error">New password</form:label>
				<div class="controls">
					<form:password path="newPassword" placeholder="New password" cssErrorClass="error" />
				</div>
			</div>
			<div class="control-group">
				<form:label path="confirmNewPassword" cssClass="short-label" cssErrorClass="short-label-error">Confirm new password</form:label>
				<div class="controls">
					<form:password path="confirmNewPassword" placeholder="Confirm new password" cssErrorClass="error" />
				</div>
			</div>
		</div>

	</div>
	
	<div class="row-fluid">
		<div class="span12">
			<div class="form-actions">
				<button type="submit" class="btn btn-primary">Confirm</button>
				<a type="button" class="btn" href="admin-list-configs.html">Cancel</a>
			</div>
		</div>
	</div>
</form:form>

<script>
	$("#changePasswordForm input:first").focus();
</script>

