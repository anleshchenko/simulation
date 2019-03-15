<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Result</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
</head>
<body>

<div class="container">
    <h2>Result</h2>
    <table class="table">
        <thead>
        <tr>
            <th>Simulation start</th>
            <th>Simulation end</th>
            <th>Simulation time</th>
            <th>Bus effiency</th>
            <th>Total buses</th>
            <th>Routes</th>
            <th>Passengers total</th>
        </tr>
        </thead>

        <tbody>
            <tr>
                <td>${result.start}</td>
                <td>${result.end}</td>
                <td>${result.between}</td>
                <td>${result.efficiency}</td>
                <td>${result.buses}</td>
                <td>${result.routes}</td>
                <td>${result.passengers}</td>
            </tr>
        </tbody>
    </table>

    <a href="/FinalTask/transportation">
        <button type="button" class="btn btn-primary btn-lg">Simulation input</button>
    </a>
</div>
</body>
</html>