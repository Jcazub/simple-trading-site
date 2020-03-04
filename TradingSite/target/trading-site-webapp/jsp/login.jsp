<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html class="login-html">
<head>

    <title>Login</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Bootstrap 3 core CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" id="bootstrap-css">
    <!--     Fonts and icons     -->
    <link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700|Roboto+Slab:400,700|Material+Icons" >
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css" >
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Oswald">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Open Sans">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

    <!-- Main CSS -->
    <link href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet">
</head>

<body>
<div class="w3-container">
    <h2 style="text-align:center;">Log In</h2>
    <c:if test="${param.login_error == 1}">
        <h3 style="text-align:center;">Wrong id or password!</h3>
    </c:if>
    <form class="form-horizontal"
          role="form"
          method="post"
          action="j_spring_security_check">
        <div class="form-group">
            <label for="j_username" class="col-md-4 control-label">Username:</label>
            <div class="col-md-4">
                <input type="text"
                       class="form-control"
                       name="j_username"
                       placeholder="Username"
                       maxlength="50"
                       required/>
            </div>
        </div>
        <div class="form-group">
            <label for="j_password" class="col-md-4 control-label">Password:</label>
            <div class="col-md-4">
                <input type="password"
                       class="form-control"
                       name="j_password"
                       placeholder="Password"
                       maxlength="50"
                       required/>
            </div>
        </div>
        <div class="form-group">
            <div class="col-md-offset-4 col-md-8">
                <input type="submit"
                       class="btn btn-default"
                       id="search-button"
                       value="Sign In"/>
            </div>
        </div>
    </form>
</div>
<!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~FOOTER-->
<div class="w3-container">
    <footer class="container-fluid text-center main-footer">
        <p>&copy; Trading Site</p>
    </footer>
</div>
<!-- Placed at the end of the document so the pages load faster -->
<!-- Bootstrap 3 scripts -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<!-- Personal Scripts -->
<script src="${pageContext.request.contextPath}/js/main.js"></script>

</body>
</html>