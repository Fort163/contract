<%--<%@ page import="su.opencode.sog.dao.HandbookSubclassEnum" %>--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="menuContentWrap">
    <div class="menuContent">
        <div class="menuContentInner">
            <ul class="navigation">
                <li>
                    <a href="<spring:url value="/mainContractPage"/>">Работа с договорами</a>
                </li>
                <li>
                    <a href="">Справочники</a>
                    <ul>
                        <li>
                            <a href="<spring:url value="/handbook?token=1"/>">Тип недвижимости</a>
                        </li>
                        <li>
                            <a href="<spring:url value="/handbook?token=2"/>">Год постройки</a>
                        </li>
                        <li>
                            <a href="<spring:url value="/handbook?token=3"/>">Площадь</a>
                        </li>
                    </ul>
                </li>
                <div class="clear"></div>
            </ul>
        </div>
    </div>
</div>


