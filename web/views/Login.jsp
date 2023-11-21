<%-- 
    Document   : login.jsp
    Created on : 7 de nov. de 2023, 19:56:25
    Author     : Patrick
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="/UFFBank/Login/Auth" method="post">
            <label for="cpf">cpf</label>
            <input type="text" id="cpf" name="cpf" />

            <label for="password">Password:</label>
            <input type="password" id="password" name="password" />

            <input type="submit" value="Login" />
        </form>
    </body>
</html>
