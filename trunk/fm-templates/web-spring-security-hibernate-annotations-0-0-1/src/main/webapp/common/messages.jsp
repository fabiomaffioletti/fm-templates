<%@ include file="taglibs.jsp"%>

<c:if test="${not empty successMessage}">
	<div class="alert alert-success">
		<button type="button" class="close" data-dismiss="alert">×</button>
		<h4>Operation successful</h4>
		<c:out value="${successMessage}" escapeXml="false" />
	</div>
	<c:remove var="successMessage"/>
</c:if>

<c:if test="${not empty infoMessage}">
	<div class="alert alert-info">
		<button type="button" class="close" data-dismiss="alert">×</button>
		<h4>Information</h4>
		<c:out value="${infoMessage}" escapeXml="false"/>
	</div>
	<c:remove var="infoMessage"/>
</c:if>

<c:if test="${not empty errorMessage}">
	<div class="alert alert-error">
		<button type="button" class="close" data-dismiss="alert">×</button>
		<h4>Error occurred</h4>
		<c:out value="${errorMessage}" escapeXml="false"/>
	</div>
	<c:remove var="errorMessage"/>
</c:if>

<c:if test="${not empty warningMessage}">
	<div class="alert">
		<button type="button" class="close" data-dismiss="alert">×</button>
		<h4>Attention!</h4>
		<c:out value="${warningMessage}" escapeXml="false"/>
	</div>
	<c:remove var="warningMessage"/>
</c:if>