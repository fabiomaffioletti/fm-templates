<%@ include file="taglibs.jsp"%>

<c:if test="${not empty successMessage}">
	<div class="alert-message block-message success">
		<p>
			<c:out value="${successMessage}" escapeXml="true" />
		</p>
	</div>
	<c:remove var="successMessage"/>
</c:if>

<c:if test="${not empty infoMessage}">
	<div class="alert-message block-message info">
		<p>
			<c:out value="${infoMessage}" escapeXml="true "/>
		</p>
	</div>
	<c:remove var="infoMessage"/>
</c:if>

<c:if test="${not empty errorMessage}">
	<div class="alert-message block-message error">
		<p>
			<c:out value="${errorMessage}" escapeXml="true "/>
		</p>
	</div>
	<c:remove var="errorMessage"/>
</c:if>