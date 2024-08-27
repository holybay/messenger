<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Messager</title>
</head>
<body>
<main>
    <c:if test="${message != null}">
        <c:out value="${message}"/>
    </c:if>
    <form name="user_login_form" method="post" action="http://localhost:8080/messager/api/login">
        <fieldset>
            <legend>Please log in:</legend>
            <label for="user_login">Login:</label>
            <input id="user_login" name="login" type="text" placeholder="Please enter your login" required/>
            <br>
            <label for="user_password">Password:</label>
            <input id="user_password" name="password" type="password" placeholder="Please enter your password" required/>
        </fieldset>
        <input type="submit" value="Log in">
    </form>
</main>
</body>
</html>