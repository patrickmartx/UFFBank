<%-- 
    Document   : Home
    Created on : 7 de nov. de 2023, 20:06:11
    Author     : Patrick
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="dev.model.complements.DAOUser" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
    </head>
    <body>
        <h1>Bem vindo, Usuario!</h1>
        <a href="/UFFBank/Deposit"><button>Depositar</button></a>
        <a href="/UFFBank/Investment"><button>Investir</button></a>
        <a href="/UFFBank/Transfer"><button>Transferir</button></a>
    </body>
</html>
