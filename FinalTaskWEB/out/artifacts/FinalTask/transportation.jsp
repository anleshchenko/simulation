<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Transportation</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
</head>
<body>

<div class="container">
    <h2>Transportation</h2>
    <p>This table represents all routes in transportation system:</p>
    <table class="table">
        <thead>
        <tr>
            <th>Route number</th>
            <th>Is circle</th>
            <th>Break time</th>
        </tr>
        </thead>

        <tbody>
        <c:forEach items="${routes}" var="route">
                <tr>
                    <td>
                        <a href="/FinalTask/route?id=${route.id}">
                                ${route.id}
                        </a>
                    </td>
                    <td>${route.circle}</td>
                    <td>${route.breaktime}</td>
                </tr>
        </c:forEach>
        </tbody>
    </table>

    <a href="result"><button type="button" class="btn btn-primary btn-lg">Result</button></a>
</div>
</body>
</html>
