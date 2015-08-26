<%@ include file="/common/taglibs.jsp"%>

<head>
<title><fmt:message key="roleProfile.title" /></title>
<meta name="menu" content="RoleMenu" />
</head>

<c:set var="delObject" scope="request">
	<fmt:message key="roleList.role" />
</c:set>
<script type="text/javascript">
	var msgDelConfirm = "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="span2">
	<h2>
		<fmt:message key="roleProfile.heading" />
	</h2>
	<c:choose>
		<c:when test="${param.from == 'list'}">
			<p>
				<fmt:message key="roleProfile.admin.message" />
			</p>
		</c:when>
		<c:otherwise>
			<p>
				<fmt:message key="roleProfile.message" />
			</p>
		</c:otherwise>
	</c:choose>
</div>
<div class="span7">
	<s:form name="roleForm" action="saveRole" method="post" validate="true"
		cssClass="well form-horizontal" autocomplete="off">
		<input type="hidden" name="from" value="${param.from}" />

		<c:choose>
			<c:when test="${param.from == 'list' and not empty role.code}">
				<s:hidden key="role.code" />
				<s:label key="role.code" />
			</c:when>
			<c:otherwise>
				<s:textfield key="role.code" required="true" />
			</c:otherwise>
		</c:choose>


		<s:textfield key="role.name" required="true" />

		<div id="actions" class="form-actions">
			<s:submit type="button" cssClass="btn btn-primary" method="save"
				key="button.save" theme="simple">
				<i class="icon-ok icon-white"></i>
				<fmt:message key="button.save" />
			</s:submit>
			<c:if test="${param.from == 'list' and not empty role.code}">
				<s:submit type="button" cssClass="btn btn-danger" method="delete"
					key="button.delete" onclick="return confirmMessage(msgDelConfirm)"
					theme="simple">
					<i class="icon-trash"></i>
					<fmt:message key="button.delete" />
				</s:submit>
			</c:if>
			<s:submit type="button" cssClass="btn" method="cancel"
				key="button.cancel" theme="simple">
				<i class="icon-remove"></i>
				<fmt:message key="button.cancel" />
			</s:submit>
		</div>
	</s:form>
</div>
<div class="span7">
	<s:form name="userForm" action="saveUser" method="post" validate="true"
		cssClass="well form-horizontal" autocomplete="off">
		<fieldset class="control-group">
			<label for="allUserRoles" class="control-label"><fmt:message
					key="userRoles.allUserRoles" /></label>
			<div class="controls">
				<select id="allUserRoles" name="allUserRoles" multiple="true">
					<c:forEach items="${availableRoles}" var="role">
						<option value="${role.value}">${role.label}</option>
					</c:forEach>
				</select>
			</div>
			<label for="userRoles" class="control-label"><fmt:message
					key="userRoles.assignRoles" /></label>
			<div class="controls">
				<select id="userRoles" name="userRoles" multiple="true">
					<c:forEach items="${availableRoles}" var="role">
						<option value="${role.value}"
							${fn:contains(user.roles, role.label) ? 'selected' : ''}>${role.label}</option>
					</c:forEach>
				</select>
			</div>
		</fieldset>


	</s:form>
</div>

<script type="text/javascript">
	$(document).ready(
			function() {
				$("input[type='text']:visible:enabled:first",
						document.forms['roleForm']).focus();
			});
</script>
