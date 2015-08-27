<%@ include file="/common/taglibs.jsp"%>

<head>
<title><fmt:message key="userProfile.title" /></title>
<meta name="menu" content="UserMenu" />
</head>

<c:set var="delObject" scope="request">
	<fmt:message key="userList.user" />
</c:set>
<script type="text/javascript">
	var msgDelConfirm = "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>


	<h2>
		<fmt:message key="userProfile.heading" />
	</h2>
	<c:choose>
		<c:when test="${param.from == 'list'}">
			<p>
				<fmt:message key="userProfile.admin.message" />
			</p>
		</c:when>
		<c:otherwise>
			<p>
				<fmt:message key="userProfile.message" />
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
		<s:if test="user.version == null">
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
		<s:textfield key="user.website" />
		<s:textfield key="user.address.address" />
	
		<s:radio key="user.gender" list="#{'M':'Male','F':'Female'}" listKey="key" listValue="value" value="'M'"/>

		<s:textfield key="user.phoneNumber" />
		<s:textfield key="user.mobilephone" />
		
		<c:choose>
			<c:when test="${param.from == 'list'}">
				<fieldset class="control-group">
					<label class="control-label"><fmt:message
							key="userProfile.accountSettings" /></label>
					<div class="controls">
						<label class="checkbox inline"> <s:checkbox
								key="user.enabled" id="user.enabled" theme="simple"
								fieldValue="true" /> <fmt:message key="user.enabled" />
						</label> <label class="checkbox inline"> <s:checkbox
								key="user.accountExpired" id="user.accountExpired"
								theme="simple" fieldValue="true" /> <fmt:message
								key="user.accountExpired" />
						</label> <label class="checkbox inline"> <s:checkbox
								key="user.accountLocked" id="user.accountLocked" theme="simple"
								fieldValue="true" /> <fmt:message key="user.accountLocked" />
						</label> <br /> <label class="checkbox inline"> <s:checkbox
								key="user.credentialsExpired" id="user.credentialsExpired"
								theme="simple" fieldValue="true" /> <fmt:message
								key="user.credentialsExpired" />
						</label>
					</div>
				</fieldset>
			</c:when>
			<c:otherwise>
				<fieldset class="control-group">
					<s:hidden name="user.enabled" value="%{user.enabled}" />
					<s:hidden name="user.accountExpired" value="%{user.accountExpired}" />
					<s:hidden name="user.accountLocked" value="%{user.accountLocked}" />
					<s:hidden name="user.credentialsExpired"
						value="%{user.credentialsExpired}" />
				</fieldset>
			</c:otherwise>
		</c:choose>
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
