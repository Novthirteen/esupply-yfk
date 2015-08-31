<%@ include file="/common/taglibs.jsp"%>


<head>
<title><fmt:message key="userList.title" /></title>
<meta name="menu" content=UserMenu />
</head>

<h2>
	<fmt:message key="userList.heading" />
</h2>

<s:form name="userForm" action="users" method="post"
	cssClass="form-horizontal">
	<div class="row-fluid">
		<div class="span4">
			<s:textfield key="user.username" />
		</div>
		<div class="span4">
			<s:textfield key="user.lastName" />
		</div>
		<div class="span4">
			<s:textfield key="user.firstName" />
		</div>
	</div>
	<div class="row-fluid">
		<div class="span4">
			<input type="hidden" name="from" value="list" />
			<s:submit type="button" cssClass="btn" action="users"
				key="button.search" theme="simple">
				<i class="icon-search"></i>
				<fmt:message key="button.search" />
			</s:submit>
			<s:submit type="button" cssClass="btn btn-primary" action="editUser"
				key="button.add" theme="simple">
				<i class="icon-plus icon-white"></i>
				<fmt:message key="button.add" />
			</s:submit>
		</div>
	</div>
</s:form>

<display:table name="users" cellspacing="0" cellpadding="0"
	requestURI="users" defaultsort="1" id="users" pagesize="25"
	class="table table-condensed table-striped table-hover" export="true">

	<display:column property="username" escapeXml="true" sortable="true"
		titleKey="user.username" url="/user/editUser?from=list"
		paramId="username" paramProperty="username" />
	<display:column property="lastName" escapeXml="true" sortable="true"
		titleKey="user.lastName" url="/user/editUser?from=list" />
	<display:column property="firstName" escapeXml="true" sortable="true"
		titleKey="user.firstName" url="/user/editUser?from=list" />
	<display:column property="email" sortable="true" titleKey="user.email"
		autolink="true" media="html" url="/user/editUser?from=list" />
	<display:column property="phoneNumber" sortable="true"
		titleKey="user.phoneNumber" url="/user/editUser?from=list" />
	<display:column property="mobilephone" sortable="true"
		titleKey="user.mobilephone" url="/user/editUser?from=list" />
	<display:column sortProperty="enabled" sortable="true"
		titleKey="user.enabled"
		media="html">
		<input type="checkbox" disabled="disabled"
			<c:if test="${users.enabled}">checked="checked"</c:if> />
	</display:column>
	<display:column property="enabled" titleKey="user.enabled"
		media="csv xml excel pdf" />

	<display:setProperty name="paging.banner.item_name">
		<fmt:message key="userList.user" />
	</display:setProperty>
	<display:setProperty name="paging.banner.items_name">
		<fmt:message key="userList.users" />
	</display:setProperty>

	<display:setProperty name="export.excel.filename" value="User List.xls" />
	<display:setProperty name="export.csv.filename" value="User List.csv" />
	<display:setProperty name="export.pdf.filename" value="User List.pdf" />
</display:table>
