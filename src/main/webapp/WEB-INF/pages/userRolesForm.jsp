<%@ include file="/common/taglibs.jsp"%>

<head>
<title><fmt:message key="userProfile.title" /></title>
<meta name="menu" content="UserMenu" />
</head>


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
