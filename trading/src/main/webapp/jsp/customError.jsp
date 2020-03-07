<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Trading Site</title>
    <jsp:include page="_css.jsp"/>
</head>
<body>
<div class="container">
    <h1>${error_tech}</h1>
    <hr/>
    <div class="navbar">
        <ul class="nav nav-tabs">
            <li role="presentation" class="active">
                <a href="${pageContext.request.contextPath}/">Home</a>
            </li>
        </ul>
    </div>
    <div>
        <h1>An error has occurred...</h1>
        <h3>${error_human}</h3>
    </div>
    <jsp:include page="_footer.jsp"/>
    <jsp:include page="_js.jsp"/>
</body>
</html>