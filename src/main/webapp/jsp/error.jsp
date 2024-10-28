<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8">
    <title>Error</title>
    <style>
        body {
          background-color: rgb(224, 224, 224);
          padding-left: 5%;
        }
    </style>
  </head>
  <body>
    <main>
      <div>Something went wrong ... </div>
      <c:choose>
        <c:when test="${user != null}">
            <form style="display: inline" action="${pageContext.request.contextPath}/ui/user" method="get">
                <button>Back</button>
            </form>
        </c:when>
        <c:otherwise>
            <form style="display: inline" action="${pageContext.request.contextPath}/ui" method="get">
                <button>Back</button>
            </form>
        </c:otherwise>
      </c:choose>
    </main>
  </body>
</html>