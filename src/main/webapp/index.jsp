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
    <h1>Hello, my dear friend!</h1>
    <h2>Please sign in to start</h2>
    <form style="display: inline" action="${pageContext.request.contextPath}/ui/signIn" method="get">
        <button>Sign in</button>
    </form>
    <h2><c:out value="If you don't have an account please sign up!"/></h2>
    <form style="display: inline" action="${pageContext.request.contextPath}/ui/signUp" method="get">
        <button>Sign up</button>
    </form>
</main>
</body>
</html>