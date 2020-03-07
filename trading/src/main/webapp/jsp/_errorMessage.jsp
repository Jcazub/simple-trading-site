<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: jcazu
  Date: 3/7/2020
  Time: 1:16 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:if test="${not empty errorMessage}">
    <div class="alert alert-danger">
        <strong>${errorMessage}</strong>
    </div>
</c:if>
