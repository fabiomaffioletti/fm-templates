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
<!-- 		<div class="span2"> -->
<!-- 			<form class="form" action="admin-search-user-action.html" method="post" id="formFilterUserActions"> -->
<!-- 				<label for="performedBy"><strong>Performed by</strong> -->
<%-- 					<input type="text" id="performedBy" name="performedBy" class="span12" value="${sessionScope.performedBy}" /> --%>
<!-- 				</label> -->
<!-- 				<label for="performedOn"><strong>Performed on</strong> -->
<%-- 					<input type="text" id="performedOn" name="performedOn" class="span12" value="${sessionScope.performedOn}" /> --%>
<!-- 				</label> -->
<!-- 				<div class="form-actions"> -->
<!-- 					<button type="submit" class="btn btn-primary">Filter</button> -->
<%-- 					<c:if test="${not empty sessionScope.performedBy or not empty sessionScope.performedOn}"> --%>
<!-- 						<button class="btn btn-danger" id="clearFiltersButton">Clear filters</button> -->
<%-- 					</c:if> --%>
<!-- 				</div> -->
<!-- 			</form> -->
<!-- 		</div> -->
		<div class="span12">
			<display:table name="userActions" id="userAction" uid="userAction" requestURI="admin-list-user-actions.html" class="table table-condensed table-striped table-bordered"
				pagesize="${sessionScope.defaultUserActionsPageSize}" export="false"
				sort="external" defaultsort="3" defaultorder="descending" partialList="true" size="resultSize">
				<display:column property="performedBy" title="ID" escapeXml="true" />
				<display:column property="performedByUsername" escapeXml="true" />
				<display:column property="performedOn" title="ID" escapeXml="true" />
				<display:column property="performedOnUsername" escapeXml="true" />
				<display:column property="performedAt" title="Performed at" format="{0,date,yyyy-MM-dd HH:mm:ss}" escapeXml="false" />
				<display:column property="actionDescription" title="Action" escapeXml="true" />
			</display:table>
		</div>
	</div>
	
	<script>
		$("#clearFiltersButton").click(function(e) {
			$("#performedBy").val("");
			$("#performedOn").val("");
			$("#formFilterUserActions").submit();
		});
	</script>