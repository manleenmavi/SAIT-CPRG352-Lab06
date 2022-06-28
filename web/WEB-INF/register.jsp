<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping List - Register User</title>
    </head>
    <body>
        <h1>Shopping List</h1>
        
        <div>
            <form method="post" action="ShoppingList">
                <label>Username:</label>
                <input type="text" name="reg_username">
                
                <input type="hidden" name="action" value="register">
                <input type="submit" value="Register Name">
            </form>
        </div>
        
        <c:if test="${requestScope.message != null}">
            <div><c:out value="${requestScope.message}" /></div>
        </c:if>
    </body>
</html>
