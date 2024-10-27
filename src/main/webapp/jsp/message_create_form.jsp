<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>New Message</title>
    <style>

        body {
          background-color: rgb(224, 224, 224);
          padding-left: 5%;
        }

        label, textarea {
          font-size: 1rem;
          letter-spacing: 1px;
        }

        label {
          display: block;
          margin-top: 6px;
          margin-bottom: 10px;
        }

        textarea {
          padding: 7px;
          max-width: 100%;
          line-height: 1.5;
          border-radius: 5px;
          border: 1px solid #ccc;
          box-shadow: 1px 1px 1px #999;
        }
    </style>
</head>
<body>
<main>
    <form name="message_create_form" method="post" action="${pageContext.request.contextPath}/api/message">
        <fieldset>
            <legend>Please enter your message:</legend>
                <label for="message_to">To:</label>
                <input id="message_to" name="to" type="text" placeholder="Please enter a user login" required/>
                <br>
                <label for="message_text">Your message:</label>
                <textarea id="message_text" name="text" rows="6" cols="60"></textarea>
                <br>
        </fieldset>
        <input type="submit" value="Send">
    </form>
</main>
</body>
</html>