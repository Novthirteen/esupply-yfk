<%@ include file="/common/taglibs.jsp"%>

<head>
<title><fmt:message key="user.title" /></title>
<meta name="menu" content="UserMenu" />
</head>

<c:set var="delObject" scope="request">
	<fmt:message key="userList.user" />
</c:set>
<script type="text/javascript">
	var msgDelConfirm = "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<h2>
	<fmt:message key="user.heading" />
</h2>
<c:choose>
	<c:when test="${param.from == 'list'}">
		<p>
			<fmt:message key="user.newMessage" />
		</p>
	</c:when>
	<c:otherwise>
		<p>
			<fmt:message key="user.updateMessage" />
		</p>
	</c:otherwise>
</c:choose>


<s:form name="userForm" action="saveUser" method="post" validate="true"
	cssClass="well form-horizontal" autocomplete="off">
	<s:hidden key="user.version" />
	<input type="hidden" name="from" value="${param.from}" />

	<c:if test="${cookieLogin == 'true'}">
		<s:hidden key="user.password" />
		<s:hidden key="user.confirmPassword" />
	</c:if>
	<s:if test="user.version == 0">
		<input type="hidden" name="encryptPass" value="true" />
	</s:if>

	<c:choose>
		<c:when test="${param.from == 'list' and not empty user.username}">
			<s:hidden key="user.username" />
			<s:label key="user.username" />
		</c:when>
		<c:otherwise>
			<s:textfield key="user.username" required="true" />
		</c:otherwise>
	</c:choose>

	<s:textfield key="user.lastName" required="true" />
	<s:textfield key="user.firstName" required="true" />
	<c:if test="${cookieLogin != 'true'}">
		<s:password key="user.password" showPassword="true" required="true"
			onchange="passwordChanged(this)" />

		<s:password key="user.confirmPassword" required="true"
			showPassword="true" onchange="passwordChanged(this)" />
	</c:if>

	<s:textfield key="user.email" />
	<s:radio key="user.gender" list="genderList" listValue="label"
		listKey="value" value="'M'" required="true" />
	<s:textfield key="user.phoneNumber" />
	<s:textfield key="user.mobilephone" />

	<div id="actions" class="form-actions">
		<s:submit type="button" cssClass="btn btn-primary" method="save"
			key="button.save" theme="simple">
			<i class="icon-ok icon-white"></i>
			<fmt:message key="button.save" />
		</s:submit>
		<c:if test="${param.from == 'list' and not empty user.username}">
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


<c:set var="scripts" scope="request">
	<script type="text/javascript">
		function passwordChanged(passwordField) {
			if (passwordField.name == "user.password") {
				var origPassword = "<s:property value="user.password"/>";
			} else if (passwordField.name == "user.confirmPassword") {
				var origPassword = "<s:property value="user.confirmPassword"/>";
			}

			if (passwordField.value != origPassword) {
				createFormElement("input", "hidden", "encryptPass",
						"encryptPass", "true", passwordField.form);
			}
		}
	</script>
</c:set>
<script type="text/javascript">
	$(document).ready(
			function() {
				$("input[type='text']:visible:enabled:first",
						document.forms['userForm']).focus();
			});
</script>
