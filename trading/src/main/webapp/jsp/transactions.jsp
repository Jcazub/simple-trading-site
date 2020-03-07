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
    <jsp:include page="_css.jsp"/>
</head>

<body>
<jsp:include page="_nav.jsp"/>

<div class="text-center">
    <jsp:include page="_errorMessage.jsp"/>

    <h1>TRANSACTIONS</h1>
    <ul class="list-group list-group-flush">
        <c:forEach var="currentTransaction" items="${transactions}">
            <li class="list-group-item">${currentTransaction.transactionType} (${currentTransaction.symbol}) - ${currentTransaction.amountTraded} Shares @ ${currentTransaction.stockPriceAtPurchase.doubleValue()}</li>
        </c:forEach>
    </ul>
</div>


<jsp:include page="_footer.jsp"/>
<jsp:include page="_js.jsp"/>

</body>
</html>