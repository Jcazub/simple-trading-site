<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<nav class="navbar navbar-inverse">
    <div class="container">
        <div class="collapse navbar-collapse" id="myNavbar">
            <ul class="nav navbar-nav">
                <li><a href="<c:url value = "/"/>">Home</a></li>
                <c:if test="${pageContext.request.userPrincipal.name != null}">
                    <li><a href="<c:url value = "/portfolio"/>">Portfolio</a></li>
                    <li><a href="<c:url value = "/transactions"/>">Transactions</a></li>
                </c:if>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <c:choose>
                    <c:when test="${pageContext.request.userPrincipal.name != null}">

                        <li class="dropdown">
                            <a class="dropdown-toggle" data-toggle="dropdown" href="#">Welcome, <c:out value="${pageContext.request.userPrincipal.name}"/> <span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li><a href="<c:url value = "/portfolio"/>">Portfolio</a></li>
                                <li><a href="<c:url value = "/transactions"/>">Transactions</a></li>
                            </ul>
                        </li>
                        <li role="presentation"><a href="<c:url value = "/perform_logout"/>"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
                    </c:when>
                    <c:otherwise>
                        <c:set var = "login_uri" value = "login"/>
                        <c:choose>
                            <c:when test="${pageContext.request.requestURI.contains(login_uri)}">
                                <li><a href="<c:url value = "/register"/>"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>\
                            </c:when>
                            <c:otherwise>
                                <li><a href="<c:url value = "/login"/>"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
                            </c:otherwise>
                        </c:choose>
                    </c:otherwise>
                </c:choose>
            </ul>
        </div>
    </div>
</nav>
