<%@ include file="/common/taglibs.jsp"%>

<head>
<title><fmt:message key="login.title" /></title>
<meta name="menu" content="Login" />
</head>

<body id="login">

	<form method="post" id="loginForm"
		action="<c:url value='/j_security_check'/>"
		onsubmit="saveUsername(this);return validateForm(this)"
		class="form-signin" autocomplete="off">
		<h2 class="form-signin-heading">
			<fmt:message key="login.heading" />
		</h2>
		<c:if test="${param.error != null}">
			<div class="alert alert-error fade in">
				<fmt:message key="errors.password.mismatch" />
			</div>
		</c:if>

		<fieldset class="control-group">
			<label class="control-label" for="request_locale"> <fmt:message
					key="login.username" />:
			</label>
			<div class="controls">
				<input type="text" name="j_username" id="j_username"
					class="input-block-level"
					placeholder="<fmt:message key="label.username"/>" required
					tabindex="1" />
			</div>
		</fieldset>

		<fieldset class="control-group">
			<label class="control-label" for="request_locale"> <fmt:message
					key="login.password" />:
			</label>
			<div class="controls">
				<input type="password" class="input-block-level" name="j_password"
					id="j_password" tabindex="2"
					placeholder="<fmt:message key="label.password"/>" required />
			</div>
		</fieldset>

		<fieldset class="control-group">
			<label class="control-label" for="request_locale"> <fmt:message
					key="login.language" />:
			</label>
			<div class="controls">
				<select name="request_locale" tabindex="3" id="request_locale"
					class="input-block-level" onchange="changeLanguage();">
					<option value=""><fmt:message
							key="login.language.userDefined" /></option>
					<option value="zh"><fmt:message key="login.language.zh" /></option>
					<option value="en"><fmt:message key="login.language.en" /></option>
				</select>
			</div>
		</fieldset>

		<c:if test="${appConfig['rememberMeEnabled']}">
			<label class="checkbox" for="rememberMe"> <input
				type="checkbox" class="checkbox" name="_spring_security_remember_me"
				id="rememberMe" tabindex="4" /> <fmt:message key="login.rememberMe" /></label>
		</c:if>

		<button type="submit" class="btn btn-large btn-primary" name="login"
			tabindex="5">
			<fmt:message key='button.login' />
		</button>
	</form>

	<c:set var="scripts" scope="request">
		<%@ include file="/scripts/login.js"%>
	</c:set>

	<script type="text/javascript">
		$('#request_locale').find('option[value="${param.locale}"]').attr('selected', true);
	
		function changeLanguage() {
			if ($('select[name=request_locale]')[0].selectedIndex != 0) {
				location.href = "<c:url value="/login"/>?locale="
						+ $('select[name=request_locale]').val();
			} else {
				
			}
		}
	</script>
</body>


