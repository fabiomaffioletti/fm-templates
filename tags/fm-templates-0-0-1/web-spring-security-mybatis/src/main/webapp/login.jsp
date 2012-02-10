<%@ include file="/common/taglibs.jsp"%>
<html>

<head>
<title>Login</title>
</head>

<body>

	<div class="container">
		<div class="content">
			<div class="row">
				<div class="span-one-third offset-one-third">
					
					<c:if test="${param.error != null}">
						<div class="alert-message block-message error" style="margin-left: -20px">
							<p>
								<strong><fmt:message key="login.form.error" /></strong>
							</p>
						</div>
					</c:if>

					<form method="post" action="/j_security_check" class="form-stacked"
						style="padding-left: 0px;">
						<fieldset>
							<div class="clearfix">
								<label for="j_username">Username</label>
								<input type="text" class="span5" name="j_username" id="j_username" value="<c:out value="${SPRING_SECURITY_LAST_USERNAME}"/>" />
								<c:remove var="SPRING_SECURITY_LAST_USERNAME" />
							</div>
							<div class="clearfix">
								<label for="j_password">Password</label> <input class="span5"
									id="j_password" name="j_password" size="30" type="password">
							</div>

						</fieldset>
						<div class="span5 actions" style="text-align: right;">
							<input type="submit" class="btn primary" value="Login">
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>

	<script>
		$(document).ready(function() {
		    $("input[type='text']:first", document.forms[0]).focus();
		});
	</script>

</body>
</html>
