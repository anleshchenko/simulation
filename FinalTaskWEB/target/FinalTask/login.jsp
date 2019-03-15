<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="styles/loginStyle.css">
</head>
<body>
<div class="login-page">
    <div class="form">
        <form class="login-form" method="post">
            <input type="text" name="login" placeholder="login"/>
            <input type="password" name = "password" placeholder="password"/>
            <button>login</button>
            <p class="message">Not registered? <a href="${pageContext.servletContext.contextPath}/register">Create an account</a></p>
        </form>
    </div>
</div>
</body>
</html>