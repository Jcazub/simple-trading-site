<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>

    <title>Signup</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Bootstrap 3 core CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" id="bootstrap-css">
    <!--     Fonts and icons     -->
    <link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700|Roboto+Slab:400,700|Material+Icons" />
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css" />
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Oswald">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Open Sans">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

    <!-- Main CSS -->
    <link href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet">
</head>

<body>

<div class="container">
    <h2 class="text-center">Account Creation</h2>

    <form class="form-horizontal"
          role="form"
          method="post"
          action="addUser">

        <div class="form-group">
            <label for="firstname" class="col-md-4 control-label">First Name:</label>
            <div class="col-md-4">
                <input type="text"
                       class="form-control"
                       name="firstname"
                       placeholder="First Name:"
                       maxlength="50"
                       required/>
            </div>
        </div>

        <div class="form-group">
            <label for="lastname" class="col-md-4 control-label">Last Name:</label>
            <div class="col-md-4">
                <input type="text"
                       class="form-control"
                       name="lastname"
                       placeholder="Last Name:"
                       maxlength="50"
                       required/>
            </div>
        </div>

        <div class="form-group">
            <label for="email" class="col-md-4 control-label">Email:</label>
            <div class="col-md-4">
                <input type="email"
                       class="form-control"
                       name="email"
                       placeholder="Email:"
                       maxlength="50"
                       required/>
            </div>
        </div>

        <div class="form-group">
            <label for="password" class="col-md-4 control-label">Password:</label>
            <div class="col-md-4">
                <input type="password"
                       class="form-control"
                       name="password"
                       placeholder="Password"
                       maxlength="50"
                       required/>
            </div>
        </div>

        <div class="form-group">
            <div class="col-md-offset-4 col-md-8">
                <input type="submit"
                       class="btn btn-default"
                       id=""
                       value="Sign Up"/>
            </div>
        </div>
    </form>

</div>

<!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~FOOTER-->
<div class="w3-container">
    <footer class="container-fluid text-center main-footer">
        <p>	&copy; Trading Site</p>
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