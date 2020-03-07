<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html>
<html class="login-html">
<head>

    <title>Login</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <jsp:include page="_css.jsp"/>
</head>

<body>
<jsp:include page="_nav.jsp"/>
<div class="w3-container">
    <h2 style="text-align:center;">Log In</h2>
    <c:if test="${param.login_error == 1}">
        <h3 style="text-align:center;">Wrong id or password!</h3>
    </c:if>
    <form class="form-horizontal"
          name="f"
          role="form"
          method="POST"
          action="perform_login">
        <div class="form-group">
            <label for="login-username" class="col-md-4 control-label">Email:</label>
            <div class="col-md-4">
                <input type="email"
                       class="form-control"
                       id="login-username"
                       name="username"
                       placeholder="Email"
                       maxlength="50"
                       required/>
            </div>
        </div>
        <div class="form-group">
            <label for="login-password" class="col-md-4 control-label">Password:</label>
            <div class="col-md-4">
                <input type="password"
                       class="form-control"
                       id="login-password"
                       name="password"
                       placeholder="Password"
                       maxlength="50"
                       required/>
            </div>
        </div>
        <div class="form-group">
            <div class="text-center">
                <input type="submit"
                       name="submit"
                       class="btn btn-default"
                       value="Sign In"/>
            </div>
        </div>
    </form>

    <div class="text-center">
        <span>New to the site? </span>
        <a  href="${pageContext.request.contextPath}/register">Register</a>
    </div>

</div>
<jsp:include page="_footer.jsp"/>
<jsp:include page="_js.jsp"/>

</body>
</html>