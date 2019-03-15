<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Registration</title>
    <link rel="stylesheet" href="styles/loginStyle.css">
</head>
<body>
<div class="login-page">
    <div class="form">
        <form class="login-form" method="post">
            <input type="text" name = "login" placeholder="login"/>
            <input type="password" name = "password" placeholder="password"/>
            <input type="email" name = "password" placeholder="email"/>
            <button>create</button>
            <p class="message">Already registered? <a href="${pageContext.servletContext.contextPath}/login">Sign In</a></p>
        </form>
    </div>
</div>

</body>
</html>
