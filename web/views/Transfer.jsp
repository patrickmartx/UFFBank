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
        <h1>Transferir</h1>
        <form action="/UFFBank/Transfer/Send" method="post">
            <label for="account">Conta no banco</label>
            <input type="text" id="value" name="0000.0000.0000.0000" />
            
            <label for="pix">pix</label>
            <input type="text" id="value" name="pix valido" />
            
            <label for="value">Valor:</label>
            <input type="text" id="value" name="0,00" />

            <input type="submit" value="Depositar" />
        </form>
    </body>
</html>
