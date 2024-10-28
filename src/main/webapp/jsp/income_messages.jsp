<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Messages</title>
    <style>
        body {
          background-color: rgb(224, 224, 224);
          padding-left: 5%;
        }

        table, th, td{
            border: 1px solid black;
            border-collapse: collapse;
            padding: 4px;
        }

        th {
            text-align: left;
        }
    </style>
</head>
<body>
    <main>
            <table style="width:85%">
                <caption>All income messages:</caption>
                <tr>
                    <th>Time</th>
                    <th>From</th>
                    <th>Message</th>
                </tr>
                <c:choose>
                    <c:when test="${messages.size() > 0}">
                        <c:forEach items="${messages}" var="message">
                            <tr>
                                <td>${formatter.format(message.getDeliveredAt())}</td>
                                <td>${message.from.login}</td>
                                <td><c:out value="${message.text}"/></td>
                            </tr>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <tr>
                            <td colspan="3">No messages</td>
                        </tr>
                    </c:otherwise>
                </c:choose>
            </table>
            <form style="display: inline" action="${pageContext.request.contextPath}/ui/user" method="get">
                <button>Back</button>
            </form>
    </main>
</body>
</html>