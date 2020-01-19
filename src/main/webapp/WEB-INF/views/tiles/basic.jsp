<meta http-equiv="Pragma" content="no-cache">
<html xmlns="http://www.w3.org/1999/html">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<spring:url value="/resources/js/ext42/locale/ext-lang-ru.js" var="extLoc" />
<spring:url value="/resources/css/main.css" var="mainCss"/>
<spring:url value="/resources/css/ext42/ext-theme-neptune-all.css" var="neptuneCss"/>
<spring:url value="/resources/css/ext42/BoxSelect.css" var="boxSelectCss"/>
<spring:url value="/resources/js/ext42/ext-all.js" var="extJs" />
<spring:url value="/resources/js/checkConditions.js" var="checkJs" />
<spring:url value="/resources/js/utils.js" var="utilsJs" />
<spring:url value="/resources/js/components/BoxSelect.js" var="boxSelect" />


<link href="${mainCss}" rel="stylesheet" />
<script src="${extJs}"></script>
<link href="${neptuneCss}" rel="stylesheet" />
<link href="${boxSelectCss}" rel="stylesheet" />
<script src="${checkJs}"></script>
<script src="${extLoc}"></script>
<script src="${utilsJs}"></script>
<script src="${boxSelect}"></script>

<head>
  <title>Договор</title>
</head>
<body>
    <div class="header">
        <tiles:insertAttribute name="header" />
        <tiles:insertAttribute name="menu" />
    </div>
    <div id="contractMainDiv" class="contractMain" ><tiles:insertAttribute name="body" /></div>
    <tiles:insertAttribute name="footer" />
</body>
