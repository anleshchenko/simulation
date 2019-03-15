<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Route</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
</head>
<body>

<div class="container">
    <h2>Route</h2>
    <table class="table">
        <thead>
        <tr>
            <th>Station id</th>
            <th>Station name</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${stations}" var="station">
            <tr>
                <td>${station.id}</td>
                <td>${station.name}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
