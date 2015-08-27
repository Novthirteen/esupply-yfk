<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="mainMenu.title"/></title>
    <meta name="menu" content="MainMenu"/>
</head>
<body class="home">

<h2><fmt:message key="mainMenu.heading"/></h2>
<p><fmt:message key="mainMenu.message"/></p>

<div id="example">                    
   <div style="width:200px;">
       <h4>Menu</h4>
       <ul id="menu" style="width:150px">
       </ul>
   </div>
</div>

<ul class="glassList">
    <li>
        <a href="<c:url value='/editProfile'/>"><fmt:message key="menu.user"/></a>
    </li>
    <li>
        <a href="<c:url value='/uploadFile'/>"><fmt:message key="menu.selectFile"/></a>
    </li>
</ul>

<script>
    $(document).ready(function() {
    	debugger;
    	var source ;
    	$.ajax({    
    	    url:'/esupply/services/api/userMenus/getusermenus.json',
    	    type:'get',    
    	    cache:false,    
    	    dataType:'json',
    	    async : false,
    	    success:function(data) {    
    	    	source = data;
    	    },    
    	    error:function() {     
    	         alert("error");    
    	    }    
    	});  


        $("#menu").kendoMenu({
   			openOnClick: true,
   			orientation: "vertical",
            dataSource: source
        });

    });
    
</script>
</body>