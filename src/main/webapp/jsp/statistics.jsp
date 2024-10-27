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
        <table style="width:50%">
        <caption>Application stats:</caption>
            <tr>
                <th>Indicator</th>
                <th>Value</th>
            </tr>
            <tr>
                <th>Total active users:</th>
                <th>${statResult.activeUsers}</th>
            </tr>
            <tr>
                <th>Total users:</th>
                <th>${statResult.totalUsers}</th>
            </tr>
            <tr>
                <th>Total messages sent:</th>
                <th>${statResult.totalMessagesSent}</th>
            </tr>
        </table>
    </main>
</body>
</html>