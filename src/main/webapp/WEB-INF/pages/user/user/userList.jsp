<%@ include file="/common/taglibs.jsp"%>


<head>
<title><fmt:message key="userList.title" /></title>
<meta name="menu" content="AdminMenu" />
</head>


<h2>
	<fmt:message key="userList.heading" />
</h2>

<s:form name="userForm" action="users" method="post"
	validate="true">

	<div class="row-fluid">
		<div class="span3">
			<s:textfield key="user.username" cssClass="text medium" />
		</div>
		<div class="span3">
			<s:textfield key="user.lastName" cssClass="text medium" />
		</div>
		<div class="span3">
			<s:textfield key="user.firstName" cssClass="text medium" />
		</div>
	</div>

	<div class="row-fluid">
		<div class="span3">
			<input type="hidden" name="from" value="list" />
			<s:submit type="button" cssClass="btn btn-primary" action="editUser"
				key="button.add" theme="simple">
				<i class="icon-plus icon-white"></i>
				<fmt:message key="button.add" />
			</s:submit>
			<s:submit type="button" cssClass="btn" action="users"
				key="button.search" theme="simple">
				<i class="icon-search"></i>
				<fmt:message key="button.search" />
			</s:submit>
		</div>
	</div>
</s:form>

<display:table name="users" cellspacing="0" cellpadding="0"
	requestURI="" defaultsort="1" id="users" pagesize="25"
	class="table table-condensed table-striped table-hover" export="true">
	<display:column property="username" escapeXml="true" sortable="true"
		titleKey="user.username" url="/user/editUser?from=list"
		paramId="username" paramProperty="username" />
	<display:column property="lastName" escapeXml="true" sortable="true"
		titleKey="user.lastName" />
	<display:column property="firstName" escapeXml="true" sortable="true"
		titleKey="user.firstName" />
	<display:column property="email" sortable="true" titleKey="user.email"
		autolink="true" media="html" />
	<display:column property="email" titleKey="user.email"
		media="csv xml excel pdf" />
	<display:column property="address.address" sortable="true"
		titleKey="user.address" autolink="true" media="html" />
	<display:column property="gender" sortable="true"
		titleKey="user.gender" autolink="true" media="html" />
	<display:column property="phoneNumber" sortable="true"
		titleKey="user.phoneNumber" autolink="true" media="html" />
	<display:column property="mobilephone" sortable="true"
		titleKey="user.mobilephone" autolink="true" media="html" />
	<display:column sortProperty="enabled" sortable="true"
		titleKey="user.enabled" style="width: 16%; padding-left: 15px"
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
