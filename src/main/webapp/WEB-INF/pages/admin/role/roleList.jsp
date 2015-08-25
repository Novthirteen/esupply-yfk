<%@ include file="/common/taglibs.jsp"%>

<head>
<title><fmt:message key="roleList.title" /></title>
<meta name="menu" content="AdminMenu" />
</head>

<div class="span10">
	<h2>
		<fmt:message key="roleList.heading" />
	</h2>

	<s:form name="roleForm" method="post" validate="true">
		<div class="row-fluid">
			<div class="span4">
				<s:textfield key="role.code" />
			</div>
			<div class="span4">
				<s:textfield key="role.name" />
			</div>
		</div>
		<div class="row-fluid">
			<div class="span8">
				<s:hidden name="from" value="list" />
				<s:submit type="button" cssClass="btn btn-primary" action="editRole"
					key="button.add" theme="simple">
					<i class="icon-plus icon-white"></i>
					<fmt:message key="button.add" />
				</s:submit>
				<s:submit type="button" cssClass="btn" action="roles"
					key="button.search" theme="simple">
					<i class="icon-search"></i>
					<fmt:message key="button.search" />
				</s:submit>
			</div>
		</div>
	</s:form>

	<display:table name="roles" cellspacing="0" cellpadding="0"
		requestURI="roles" defaultsort="1" id="role" pagesize="25"
		class="table table-condensed table-striped table-hover" export="true">

		<display:column property="code" escapeXml="true" sortable="true"
			titleKey="role.code" url="/admin/editRole?from=list" paramId="code"
			paramProperty="code" />
		<display:column property="name" escapeXml="true" sortable="true"
			titleKey="role.name" url="/admin/editRole?from=list" paramId="code"
			paramProperty="code" />

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
