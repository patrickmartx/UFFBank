<%-- 
    Document   : Deposit
    Created on : 7 de nov. de 2023, 20:55:05
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
        <h1>Depositar</h1>
        <form action="/UFFBank/Deposit/Send" method="post">
            <label for="value">Valor:</label>
            
            <input type="text" id="value" name="0,00" />

            <input type="submit" value="Depositar" />
        </form>
    </body>
</html>
