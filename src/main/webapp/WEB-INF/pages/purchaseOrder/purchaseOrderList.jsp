<%@ include file="/common/taglibs.jsp"%>

<head>
<title><fmt:message key="purchaseOrderList.title" /></title>
<meta name="menu" content="AdminMenu" />
</head>

<h2>
	<fmt:message key="purchaseOrderList.heading" />
</h2>

<s:form name="purchaseOrderForm" action="purchaseOrders" method="post"
	validate="true">
	<div class="row-fluid">
		<div class="span3">
			<s:textfield key="purchaseOrder.xpyhmstro_yhdnbr" />
		</div>
		<div class="span3">
			<s:textfield key="purchaseOrder.xpyhmstro_priority" />
		</div>
	</div>
	<div class="row-fluid">
		<div class="span3">
			<s:textfield key="purchaseOrder.xpyhmstro_suppcode" />
		</div>
		<div class="span3">
			<s:textfield key="purchaseOrder.xpyhmstro_creator" />
		</div>
	</div>
	<div class="row-fluid">
		<div class="span3">
			<s:textfield key="purchaseOrder.xpyhmstro_stat" />
		</div>
		<div class="span3">
			<s:textfield key="purchaseOrder.xpyhmstro_shipto" />
		</div>
	</div>
	<div class="row-fluid">
		<div class="span3">
			<s:textfield key="purchaseOrder.xpyhmstro_startdt" />
		</div>
		<div class="span3">
			<s:textfield key="purchaseOrder.xpyhmstro_receptdt" />
		</div>
	</div>
	<div class="row-fluid">
		<div class="span3">
			<s:checkbox key="purchaseOrder.isDetail" />
		</div>
	</div>
	<div class="row-fluid">
		<div class="span3">
			<input type="hidden" name="from" value="list" />
			<s:submit type="button" cssClass="btn btn-primary"
				action="confirmPurchaseOrder" key="button.confirm" theme="simple">
				<i class="icon-plus icon-white"></i>
				<fmt:message key="button.confirm" />
			</s:submit>
			<s:submit type="button" cssClass="btn" action="purchaseOrders"
				key="button.search" theme="simple">
				<i class="icon-search"></i>
				<fmt:message key="button.search" />
			</s:submit>
		</div>
	</div>
</s:form>

<display:table name="purchaseOrders" cellspacing="0" cellpadding="0"
	requestURI="/purchaseOrders" defaultsort="1" id="purchaseOrders"
	pagesize="25" class="table table-condensed table-striped table-hover"
	export="true">

	<display:column property="xpyhmstro_seq" escapeXml="true" sortable="true"
		titleKey="purchaseOrder.xpyhmstro_seq" />
	<display:column property="xpyhmstro_yhdnbr" escapeXml="true" sortable="true"
		titleKey="purchaseOrder.xpyhmstro_yhdnbr" url="/editPurchaseOrder?from=list" paramId="xpyhmstro_yhdnbr"
		paramProperty="xpyhmstro_yhdnbr" />
	<display:column property="xpyhmstro_suppcode" escapeXml="true" sortable="true"
		titleKey="purchaseOrder.xpyhmstro_suppcode" />
	<display:column property="xpyhmstro_shipto" escapeXml="true" sortable="true"
		titleKey="purchaseOrder.xpyhmstro_seq" />
	<display:column property="xpyhmstro_startdt" escapeXml="true" sortable="true"
		titleKey="purchaseOrder.xpyhmstro_startdt" />
	<display:column property="xpyhmstro_receptdt" escapeXml="true" sortable="true"
		titleKey="purchaseOrder.xpyhmstro_receptdt" />
	<display:column property="xpyhmstro_stat" escapeXml="true" sortable="true"
		titleKey="purchaseOrder.xpyhmstro_stat" />
	<display:column property="xpyhmstro_priority" escapeXml="true" sortable="true"
		titleKey="purchaseOrder.xpyhmstro_priority" />
	<display:column property="xpyhmstro_creator" escapeXml="true" sortable="true"
		titleKey="purchaseOrder.xpyhmstro_creator" />
	
	<display:column ></display:column>
	
	<display:setProperty name="paging.banner.item_name">
		<fmt:message key="purchaseOrderList.purchaseOrder" />
	</display:setProperty>
	<display:setProperty name="paging.banner.items_name">
		<fmt:message key="purchaseOrderList.purchaseOrders" />
	</display:setProperty>

	<display:setProperty name="export.excel.filename"
		value="PurchaseOrder List.xls" />
	<display:setProperty name="export.csv.filename"
		value="PurchaseOrder List.csv" />
	<display:setProperty name="export.pdf.filename"
		value="PurchaseOrder List.pdf" />
</display:table>

