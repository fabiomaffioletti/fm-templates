<%@ include file="/common/taglibs.jsp"%>

<head>
	<title>Administration</title>
</head>

<div class="row-fluid">
	<div class="span12">
		<jsp:include page="../../../common/messages.jsp" />
		
		<display:table name="configs" id="config" uid="config" requestURI="" partialList="false" class="table table-condensed table-striped table-bordered"
			export="false" size="1000">
			
			<display:column property="configName" title="Name" href="admin-edit-config.html" paramId="id" paramProperty="id" escapeXml="true" />
			<display:column property="configValue" title="Value" escapeXml="true" />
			
		</display:table>
	</div>
</div>