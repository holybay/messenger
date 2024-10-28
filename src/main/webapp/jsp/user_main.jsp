<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Index</title>
    <style>
        body {
            background-color: rgb(224, 224, 224);
            padding-left: 5%;
        }
    </style>
</head>
<body>
<main>
    <h1>Hello, ${user.login}</h1>
    <form style="display: inline" action="${pageContext.request.contextPath}/ui/signOut" method="post">
        <button>Log out</button>
    </form>

    <h2>Check your messages: </h2>
    <form style="display: inline" action="${pageContext.request.contextPath}/ui/user/chats" method="get">
        <button>Check messages</button>
    </form>

    <h2>Send a new message: </h2>
    <form style="display: inline" action="${pageContext.request.contextPath}/ui/user/message" method="get">
        <button>Send message</button>
    </form>

    <c:if test="${user.role.name() == 'ADMIN'}">
        <h2>Check application statistics: </h2>
        <form style="display: inline" action="${pageContext.request.contextPath}/api/admin/statistics" method="get">
            <button>Get stats</button>
        </form>
    </c:if>
</main>
</body>
</html>