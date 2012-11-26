<%@ include file="/common/taglibs.jsp"%>

<head>
	<style>
		th.order1 a {
			width: 100%;
			float: left;
 			background: url(img/arrow_up.png) no-repeat right center ;
		}
		
		th.order2 a {
			width: 100%;
			float: left;
 			background: url(img/arrow_down.png) no-repeat right center ;
		}
	</style>
</head>

	<div class="row-fluid">
	
		<div class="span12">
			<jsp:include page="../../../common/messages.jsp" />
			<form class="form-inline" id="formAdminSearchUser" action="admin-search-user.html" method="post">
				<input type="text" placeholder="Search username" id="filter" name="filter" value="${sessionScope.filter}">
				<div class="btn-group">
					<button type="submit" class="btn"><i class="icon-search"></i></button>
					<a class="btn" id="btnRemoveFilter"><i class="icon-remove"></i></a>
				</div>
				<div class="btn-group">
					<a class="btn" id="selectAll"><i class="icon-check"></i></a>
					<a class="btn" id="deselectAll"><i class="icon-check-empty"></i></a>
				</div>
				<a class="btn btn-primary" href="admin-edit-user.html">New user</a>
				
				<a class="btn btn-primary" id="btnResetPassword" style="display:none;">Reset password</a>
				<a class="btn btn-danger" id="btnDelete" style="display:none;">Delete</a>
				
				<div class="input-prepend pull-right">
					<button class="btn disabled pull-left">Results per page</button>
					<select name="resultsPerPage" id="resultsPerPage" class="input-mini pull-left">
						<c:forEach var="step" begin="25" end="100" step="25">
							<option value="${step}" ${step == defaultUsersPageSize ? 'selected' : ''}>${step}</option>
						</c:forEach>
					</select>
				</div>
			</form>
		</div>
	
	</div>
	
	<div class="row-fluid">
		<div class="span12">
			<form id="formAdminUserList" method="post">
			<display:table name="users" id="user" uid="user" requestURI="admin-list-users.html" class="table table-condensed table-striped table-bordered"
				pagesize="${sessionScope.defaultUsersPageSize}" export="false"
				sort="external" defaultsort="2" defaultorder="descending" partialList="true" size="resultSize">
				
				<display:column style="width: 20px; text-align: center;" sortable="false">
					<input type="checkbox" name="userId" value="${user.id}">
				</display:column>
				<display:column property="username" title="Username" href="admin-edit-user.html" paramId="id" paramProperty="id" sortable="true" sortName="username" escapeXml="true" />
				<display:column title="En">
					<c:choose>
						<c:when test="${user.enabled}"><i class="icon-ok"></i></c:when>
						<c:otherwise><i class="icon-ban-circle"></i></c:otherwise>
					</c:choose>
				</display:column>
				<display:column title="Lock">
					<c:choose>
						<c:when test="${user.accountLocked}"><i class="icon-ok"></i></c:when>
						<c:otherwise><i class="icon-ban-circle"></i></c:otherwise>
					</c:choose>
				</display:column>
				<display:column title="Exp">
					<c:choose>
						<c:when test="${user.accountExpired}"><i class="icon-ok"></i></c:when>
						<c:otherwise><i class="icon-ban-circle"></i></c:otherwise>
					</c:choose>
				</display:column>
				<display:column title="Pwd">
					<c:choose>
						<c:when test="${user.credentialsExpired}"><i class="icon-ok"></i></c:when>
						<c:otherwise><i class="icon-ban-circle"></i></c:otherwise>
					</c:choose>
				</display:column>
				<display:column property="userLoginStatistic.loginOk" title="Logins OK" escapeXml="true" />
				<display:column property="userLoginStatistic.lastLoginOk" title="Last login OK" format="{0,date,yyyy-MM-dd HH:mm:ss}" escapeXml="false" />
				<display:column property="userLoginStatistic.loginKo" title="Logins KO" escapeXml="true" />
				<display:column property="userLoginStatistic.lastLoginKo" title="Last login KO" format="{0,date,yyyy-MM-dd HH:mm:ss}" escapeXml="false" />
				<display:column property="modifiedAt" title="Updated datetime" format="{0,date,yyyy-MM-dd HH:mm:ss}" escapeXml="false" />
				<display:column property="modifiedBy" title="Updated by" escapeXml="true" />
				<display:column property="createdAt" title="Created datetime" format="{0,date,yyyy-MM-dd HH:mm:ss}" escapeXml="false" />
				<display:column property="createdBy" title="Created by" escapeXml="true" />
			</display:table>
			</form>
		</div>
	</div>

<script>
	
	$("#formAdminSearchUser input:first").focus();
	
	var btnDelete = $("#btnDelete");
	var btnResetPassword = $("#btnResetPassword");
	
	$("#btnRemoveFilter").click(function() {
		$("#filter").val("");
		$("#formAdminSearchUser").submit();
	});
	
	$("#selectAll").click(function() {
		$("input[name=userId]").each(function() {
			$(this).attr("checked", true);
		});
		$(btnDelete).show();
		$(btnResetPassword).show();
	});
	
	$("#deselectAll").click(function() {
		$("input[name=userId]").each(function() {
			$(this).attr("checked", false);
		});
		$(btnDelete).hide();
		$(btnResetPassword).hide();
	});
	
	$("input[name=userId]").each(function() {
		$(this).click(function() {
			if(isAnySelected()) {
				$(btnDelete).show();
				$(btnResetPassword).show();
			} else {
				$(btnDelete).hide();
				$(btnResetPassword).hide();
			}
		});
	});
	
	function getSelected() {
		var selected = []; 
		$("input[name=userId]").each(function() {
			if($(this).is(":checked")) {
				selected.push($(this).val());
			}
		});
		console.debug(selected);
	}
	
	function isAnySelected() {
		var found = false;
		$("input[name=userId]").each(function() {
			if($(this).is(":checked")) {
				found = true;
			}
		});
		return found;
	}
	
	$("#resultsPerPage").change(function() {
		$("#formAdminSearchUser").submit();
	});
	
	$(btnResetPassword).click(function() {
		$("#formAdminUserList").attr("action", "admin-reset-users.html");
		$("#formAdminUserList").submit();
	});
	
	$(btnDelete).click(function() {
		$("#formAdminUserList").attr("action", "admin-delete-users.html");
		$("#formAdminUserList").submit();
	});
	
</script>

