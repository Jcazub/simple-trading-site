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
<jsp:include page="_nav.jsp"/>

<div class = "text-center">
    <jsp:include page="_errorMessage.jsp"/>

    <h1>PORTFOLIO</h1>
    <div class="col-xs-6 text-center">
        <span>Total Value: ${totalPortfolioValue}</span>
        <ul class="list-group list-group-flush">
            <c:forEach var="currentStock" items="${userStocks}">
                <li class="list-group-item">${currentStock.symbol} - ${currentStock.ownedUnits} Shares   $${currentStock.getTotalValue()}</li>
            </c:forEach>
        </ul>
    </div>
    <div class="col-xs-6 text-center">
        <p>Account Balance: $${user.currentBalance}</p>
        <form class="form-horizontal"
              name="f"
              role="form"
              method="POST"
              action="buyStock">
            <div class="form-group">
                <label for="buy-symbol" class="col-md-4 control-label"></label>
                <div class="col-md-4">
                    <input type="text"
                           id="buy-symbol"
                           name="symbol"
                           placeholder="Ticker"
                           maxlength="50"
                           required/>
                </div>
            </div>
            <div class="form-group">
                <label for="buy-amount" class="col-md-4 control-label"></label>
                <div class="col-md-4">
                    <input type="number"
                           id="buy-amount"
                           name="amount"
                           placeholder="Qty"
                           maxlength="50"
                           required/>
                </div>
            </div>
            <div class="form-group">
                <div class="text-center">
                    <input type="submit"
                           name="submit"
                           class="btn btn-default"
                           value="Buy"/>
                </div>
            </div>
        </form>
    </div>


</div>

<jsp:include page="_footer.jsp"/>
<!-- Placed at the end of the document so the pages load faster -->
<!-- Bootstrap 3 scripts -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<!-- Personal Scripts -->
<script src="${pageContext.request.contextPath}/js/main.js"></script>

</body>
</html>