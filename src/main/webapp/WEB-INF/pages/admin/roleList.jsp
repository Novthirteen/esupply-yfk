<%@ include file="/common/taglibs.jsp"%>

<head>
<title><fmt:message key="roleList.title" /></title>
<meta name="menu" content="AdminMenu" />
</head>

<div class="span10">
	<h2>
		<fmt:message key="roleList.heading" />
	</h2>

	<div id="actions" class="form-actions">
		<form method="get" action="${ctx}/admin/roles" id="searchForm"
			class="form-search">
			<table class="table-striped">
				<tr>
					<td><input type="text" size="20" name="code" id="query"
						value="${param.qCode}"
						placeholder="<fmt:message key="search.enterTerms"/>"
						class="input-medium search-query" /></td>
					<td><input type="text" size="20" name="name" id="query"
						value="${param.qName}"
						placeholder="<fmt:message key="search.enterTerms"/>"
						class="input-medium search-query" /></td>
				</tr>
			</table>

			<a class="btn btn-primary"
				href="<c:url value='/editRole?method=Add&from=list'/>"> <i
				class="icon-plus icon-white"></i> <fmt:message key="button.add" />
			</a>

			<button id="button.search" class="btn" type="submit">
				<i class="icon-search"></i>
				<fmt:message key="button.search" />
			</button>

		</form>
	</div>
	<display:table name="roles" cellspacing="0" cellpadding="0"
		requestURI="" defaultsort="1" id="roles" pagesize="25"
		class="table table-condensed table-striped table-hover" export="true">

		<display:column property="code" escapeXml="true" sortable="true"
			titleKey="role.code" style="width: 25%" url="/editRole?from=list"
			paramId="code" paramProperty="code" />
		<display:column property="name" escapeXml="true" sortable="true"
			titleKey="role.name" style="width: 34%" />


		<display:setProperty name="paging.banner.item_name">
			<fmt:message key="roleList.role" />
		</display:setProperty>
		<display:setProperty name="paging.banner.items_name">
			<fmt:message key="roleList.roles" />
		</display:setProperty>

		<display:setProperty name="export.excel.filename"
			value="Role List.xls" />
		<display:setProperty name="export.csv.filename" value="Role List.csv" />
		<display:setProperty name="export.pdf.filename" value="Role List.pdf" />
	</display:table>
</div>
