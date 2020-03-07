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
    <jsp:include page="_js.jsp"/>
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
                <li class="list-group-item"><span id=${currentStock.symbol}>${currentStock.symbol} - ${currentStock.ownedUnits} Shares $${currentStock.getTotalValue()}</span></li>
                <script>
                    continuousPromise(() => {
                        let stockId = "<c:out value='${currentStock.symbol}'/>";

                        return axios.get("https://sandbox-sse.iexapis.com/stable/stock/"
                            + stockId + "/quote?token=Tpk_18dfe6cebb4f41ffb219b9680f9acaf2")
                            .then(function (response) {
                                var quote = response.data;
                                var stock_color;

                                if (quote.open > quote.latestPrice) {
                                    stock_color = "red_stock"
                                } else if (quote.open < quote.latestPrice) {
                                    stock_color = "green_stock"
                                } else {
                                    stock_color = "grey_stock"
                                }
                                document.getElementById(stockId).setAttribute("class", stock_color);
                            });
                    }, 150);
                </script>
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
</body>
</html>