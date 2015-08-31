<%@ include file="/common/taglibs.jsp"%>


<style>
.k-header {
	border: none;
	background-color: white;
}

.k-group {
	border: none;
	background-color: white;
}

.k-link-hover {
	border-left-color: #286EA0 !important;
	color: #E3144A !important;
}

.k-link {
	color: #777;
	border-left: 4px solid #cccccc;
	padding: 5px;
	height: 20px;
	font-weight: bold;
	background-color: rgb(234, 234, 234);
	margin: 0px 0px 4px 0px;
	width: 120px;
}
</style>

<div class="pull-left">
	<ul id="menu"></ul>
</div>

<script>
	$(document).ready(function() {
		$.ajax({
			url : "<c:url value="/services/api/users/getUserMenus.json"/>",
			type : "get",
			cache : false,
			dataType : "json",
			async : false,
			success : function(data) {
				$("#menu").kendoMenu({
					orientation : "vertical",
					dataSource : data
				});

				$("span.k-link").hover(function() {
					$(this).addClass("k-link-hover");
				}, function() {
					$(this).removeClass("k-link-hover");
				});

				$("a.k-link").hover(function() {
					$(this).addClass("k-link-hover");
				}, function() {
					$(this).removeClass("k-link-hover");
				});
			},
			error : function() {

			}
		});
	});
</script>