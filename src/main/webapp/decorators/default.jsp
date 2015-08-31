
<!DOCTYPE html>
<%@ include file="/common/taglibs.jsp"%>
<html lang="en">
<head>
<meta http-equiv="Cache-Control" content="no-store" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="icon" href="<c:url value="/images/favicon.ico"/>" />
<title><decorator:title /> | <fmt:message key="webapp.name" /></title>

<link rel="stylesheet" type="text/css" media="all"
	href="<c:url value='/styles/lib/bootstrap.min.css'/>" />
<link rel="stylesheet" type="text/css" media="all"
	href="<c:url value='/styles/lib/bootstrap-responsive.min.css'/>" />
<link rel="stylesheet" type="text/css" media="all"
	href="<c:url value='/styles/style.css'/>" />
<link rel="stylesheet" type="text/css"
	href="<c:url value='/styles/lib/bootstrap-duallistbox.min.css'/>" />
<link rel="stylesheet" type="text/css" media="all"
	href="<c:url value='/styles/kendoui/kendo.common.min.css'/>" />
<link rel="stylesheet" type="text/css" media="all"
	href="<c:url value='/styles/kendoui/kendo.rtl.min.css'/>" />
<link rel="stylesheet" type="text/css" media="all"
	href="<c:url value='/styles/kendoui/kendo.bootstrap.min.css'/>" />
<link rel="stylesheet" type="text/css" media="all"
	href="<c:url value='/styles/kendoui/kendo.dataviz.min.css'/>" />
<link rel="stylesheet" type="text/css" media="all"
	href="<c:url value='/styles/kendoui/kendo.dataviz.bootstrap.min.css'/>" />
<decorator:head />


<script type="text/javascript"
	src="<c:url value='/scripts/lib/jquery-1.8.2.min.js'/>"></script>
<script type="text/javascript"
	src="<c:url value='/scripts/lib/bootstrap.min.js'/>"></script>
<script type="text/javascript"
	src="<c:url value='/scripts/lib/plugins/jquery.cookie.js'/>"></script>
<script type="text/javascript" src="<c:url value='/scripts/script.js'/>"></script>
<script type="text/javascript"
	src="<c:url value='/scripts/lib/jquery.bootstrap-duallistbox.min.js'/>"></script>
<script type="text/javascript"
	src="<c:url value='/scripts/kendoui/kendo.all.js'/>"></script>
</head>
<body
	<decorator:getProperty property="body.id" writeEntireProperty="true"/>
	<decorator:getProperty property="body.class" writeEntireProperty="true"/>>
	<c:set var="currentMenu" scope="request">
		<decorator:getProperty property="meta.menu" />
	</c:set>

	<div class="navbar navbar-fixed-top">
		<div class="navbar-inner">
			<div class="container-fluid">
				<%-- For smartphones and smaller screens --%>
				<button type="button" class="btn btn-navbar" data-toggle="collapse"
					data-target=".nav-collapse">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="brand" href="<c:url value='/'/>"><fmt:message
						key="webapp.name" /></a>
			</div>
		</div>
	</div>
	
		<div class="navbar-collapse collapse">
			 <ul class="nav navbar-nav">
			   <li class="dropdown">
				 <a href="#" class="dropdown-toggle" data-toggle="dropdown">Themes <b class="caret"></b></a>
				 <ul class="dropdown-menu">
				   <li><a href="#" data-theme="default" class="theme-link">Default</a></li>
				   <li><a href="#" data-theme="cerulean" class="theme-link">Cerulean</a></li>
				   <li><a href="#" data-theme="cosmo" class="theme-link">Cosmo</a></li>
				   <li><a href="#" data-theme="cyborg" class="theme-link">Cyborg</a></li>
				   <li><a href="#" data-theme="flatly" class="theme-link">Flatly</a></li>
				   <li><a href="#" data-theme="journal" class="theme-link">Journal</a></li>
				   <li><a href="#" data-theme="readable" class="theme-link">Readable</a></li>
				   <li><a href="#" data-theme="simplex" class="theme-link">Simplex</a></li>
					<li><a href="#" data-theme="slate" class="theme-link">Slate</a></li>
					 <li><a href="#" data-theme="spacelab" class="theme-link">Spacelab</a></li>
					 <li><a href="#" data-theme="united" class="theme-link">United</a></li>
				 </ul>
			   </li>
			 </ul>
			</div><!--/.nav-collapse -->

	<div class="container-fluid">
		<%@ include file="/common/messages.jsp"%>
		<div class="row-fluid">
			<%@ include file="/common/menu.jsp"%>
			<div class="span10">
				<decorator:body />
			</div>
		</div>
	</div>

	<div id="footer">
		<span class="left"><fmt:message key="webapp.version" /> <c:if
				test="${pageContext.request.remoteUser != null}">
            | <fmt:message key="user.status" /> ${pageContext.request.remoteUser}
            </c:if> </span> <span class="right"> &copy; <fmt:message
				key="copyright.year" /> <a href="<fmt:message key="company.url"/>"><fmt:message
					key="company.name" /></a>
		</span>
	</div>
	<%=(request.getAttribute("scripts") != null) ? request.getAttribute("scripts") : ""%>


<script>
	var themes = {
	    "default": "//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css",
	    "cerulean" : "//bootswatch.com/cerulean/bootstrap.min.css",
	    "cosmo" : "//bootswatch.com/cosmo/bootstrap.min.css",
	    "cyborg" : "//bootswatch.com/cyborg/bootstrap.min.css",
	    "flatly" : "//bootswatch.com/flatly/bootstrap.min.css",
	    "journal" : "//bootswatch.com/journal/bootstrap.min.css",
	    "readable" : "//bootswatch.com/readable/bootstrap.min.css",
	    "simplex" : "//bootswatch.com/simplex/bootstrap.min.css",
	    "slate" : "//bootswatch.com/slate/bootstrap.min.css",
	    "spacelab" : "//bootswatch.com/spacelab/bootstrap.min.css",
	    "united" : "//bootswatch.com/united/bootstrap.min.css"
	}
	$(function(){
	   var themesheet = $('<link href="'+themes['default']+'" rel="stylesheet" />');
	    themesheet.appendTo('head');
	    $('.theme-link').click(function(){
	       var themeurl = themes[$(this).attr('data-theme')]; 
	        themesheet.attr('href',themeurl);
	    });
	});
</script>
</body>
</html>

