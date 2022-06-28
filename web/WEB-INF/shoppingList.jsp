<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping List - <c:out value="${username}" /></title>
    </head>
    <body>
        <h1>Shopping List</h1>
        <div>
            Hello, <c:out value="${username}" /> <a href='
            <c:url value="ShoppingList">
                <c:param name="action" value="logout"/>
            </c:url>'>Logout</a>
        </div>
            
        <div>
            <h2>List</h2>
            
            <form method="post">
                <label>Add item:</label>
                <input type="text" name="new_item">
                <input type="hidden" name="action" value="add">
                <input type="submit" value="Add">
            </form>
        </div>
        
        <c:if test="${requestScope.message != null}">
            <div><c:out value="${requestScope.message}" /></div>
        </c:if>
        
        <c:if test="${shoppingList.size() > 0}">
            <form method="post">
                <c:forEach var="currListItem" items="${shoppingList}" varStatus="status">
                    <div>
                        <input type="radio" name="selected_item" value="${status.index}" id="sp_item_num${status.index}">
                        <label for="sp_item_num${status.index}">${currListItem}</label>
                    </div>
                </c:forEach>
                
                <input type="hidden" name="action" value="delete">
                <input type="submit" value="Delete">
            </form>
        </c:if>
    </body>
</html>
