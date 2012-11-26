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
		.form-horizontal .form-actions {
			padding-left: 80px;
		}
	</style>
</head>

<form:form class="form-horizontal" action="admin-edit-config.html" commandName="configForm">
	<div class="row-fluid">
		<jsp:include page="../../../common/messages.jsp" />
	</div>
	<div class="row-fluid">
		<div class="span12">
			<c:set var="errors"><form:errors path="*" /></c:set>
			<c:if test="${not empty errors}">
				<div class="alert alert-error">
					<form:errors path="*" htmlEscape="false" />
				</div>
			</c:if>
			
			<form:hidden path="id" />
			
			<legend>Config data</legend>
			<div class="control-group">
				<form:label path="configName" cssClass="short-label" cssErrorClass="short-label-error">Name</form:label>
				<div class="controls">
					<form:input path="configName" placeholder="Name" cssErrorClass="error" />
				</div>
			</div>
			<div class="control-group">
				<form:label path="configValue" cssClass="short-label" cssErrorClass="short-label-error">Value</form:label>
				<div class="controls">
					<form:input path="configValue" placeholder="Value" cssErrorClass="error" />
				</div>
			</div>
		</div>

	</div>
	
	<div class="row-fluid">
		<div class="span12">
			<div class="form-actions">
				<button type="submit" class="btn btn-primary">Save</button>
				<a type="button" class="btn" href="admin-list-configs.html">Back to configs list</a>
			</div>
		</div>
	</div>
</form:form>

