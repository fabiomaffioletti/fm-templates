<%@ include file="taglibs.jsp"%>

<c:if test="${not empty successMessage}">
	<div class="success">
		<c:out value="${successMessage}" escapeXml="true" />
	</div>
	<c:remove var="successMessage"/>
</c:if>

<c:if test="${not empty infoMessage}">
	<div class="info">
		<c:out value="${infoMessage}" escapeXml="true "/>
	</div>
	<c:remove var="infoMessage"/>
</c:if>

<c:if test="${not empty errorMessage}">
	<div class="error">
		<c:out value="${errorMessage}" escapeXml="true "/>
	</div>
	<c:remove var="errorMessage"/>
</c:if>