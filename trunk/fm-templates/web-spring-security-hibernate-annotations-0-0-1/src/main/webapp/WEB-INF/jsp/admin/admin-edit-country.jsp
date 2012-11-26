<%@ include file="/common/taglibs.jsp"%>

<head>
	<title>Edit country</title>
	
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

<form:form class="form-horizontal" action="admin-edit-country.html" commandName="country">
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
			<form:hidden path="createdAt" />
			<form:hidden path="createdBy" />
			<form:hidden path="modifiedAt" />
			<form:hidden path="modifiedBy" />
			
			<legend>Country data</legend>
			<div class="control-group">
				<form:label path="name" cssClass="short-label" cssErrorClass="short-label-error">Name</form:label>
				<div class="controls">
					<form:input path="name" placeholder="Name" cssErrorClass="error" />
				</div>
			</div>
			<div class="control-group">
				<form:label path="foundation" cssClass="short-label" cssErrorClass="short-label-error">Foundation</form:label>
				<div class="controls">
					<form:input path="foundation" placeholder="Value" cssErrorClass="error" />
				</div>
			</div>
			<div class="control-group">
				<form:label path="description" cssClass="short-label" cssErrorClass="short-label-error">Description</form:label>
				<div class="controls">
					<form:input path="description" placeholder="Value" cssErrorClass="error" />
				</div>
			</div>
			<div class="control-group">
				<form:label path="cities" cssClass="short-label" cssErrorClass="short-label-error">Cities</form:label>
				<div class="controls">
					<form:select path="cities" items="${allCities}" itemLabel="name" itemValue="id" cssErrorClass="error"/>
				</div>
			</div>
<!-- 			<div class="control-group"> -->
<%-- 				<form:label path="roles" cssClass="short-label" cssErrorClass="short-label-error">Roles</form:label> --%>
<!-- 				<div class="controls"> -->
<%-- 					<form:select path="roles" items="${allRoles}" itemLabel="roleName" itemValue="id" cssErrorClass="error"/> --%>
<!-- 				</div> -->
<!-- 			</div> -->
		</div>

	</div>
	
	<div class="row-fluid">
		<div class="span12">
			<div class="form-actions">
				<button type="submit" class="btn btn-primary">Save</button>
				<a type="button" class="btn" href="admin-list-countries.html">Back to configs list</a>
			</div>
		</div>
	</div>
</form:form>

