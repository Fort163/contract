<%--<%@ page import="su.opencode.sog.dao.HandbookSubclassEnum" %>--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="menuContentWrap">
    <div class="menuContent">
        <div class="menuContentInner">
            <ul class="navigation">
                <li>
                    <a href="<spring:url value="/page/mainContractPage"/>">Работа с договорами</a>
                </li>
                <li>
                    <a href="">Справочники</a>
                    <ul>
                        <%--<c:forEach var="num" items="<%=HandbookSubclassEnum.values()%>">
                        <li>
                            <a href="<spring:url value="/page/handbook?handbookNum=${num.ordinal()}"/>">
                                    <spring:message code="${num.title}"/>
                            </a>
                        </li>
                        </c:forEach>--%>
                    </ul>
                </li>
                <div class="clear"></div>
            </ul>
        </div>
    </div>
</div>


