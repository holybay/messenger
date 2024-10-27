<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Create User</title>
    <style>
        body {
            background-color: rgb(224, 224, 224);
            padding-left: 5%;
        }
    </style>
</head>
<body>
<main>
    <form name="user_create_form" method="post" action="${pageContext.request.contextPath}/api/user">
        <fieldset>
            <legend>Please fill in all fields:</legend>
            <label for="full_name">Full Name:</label>
            <input id="full_name" name="fullName" type="text" placeholder="Please enter full name" required/>
            <br>
            <label for="login">Login:</label>
            <input id="login" name="login" type="text" placeholder="Please enter your login" required/>
            <br>
            <label for="password">Password:</label>
            <input id="password" name="password" type="password" placeholder="Please enter your password" required/>
            <br>
            <label for="dateOfBirth">Date Of Birth:</label>
            <input id="dateOfBirth" name="dateOfBirth" type="Date" required/>
        </fieldset>
        <input type="submit" value="Create">
    </form>
</main>
</body>
</html>