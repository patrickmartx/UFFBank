<%-- 
    Document   : Home
    Created on : 7 de nov. de 2023, 20:06:11
    Author     : Patrick
--%>

<%@page import="dev.model.complements.BankAccountRepository"%>
<%@page import="java.text.DecimalFormat"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="dev.services.ClientService" %>
<%@ page import="dev.services.impl.ClientServiceImpl" %>
<%@ page import="dev.entity.Client" %>
<%@ page import="dev.entity.BankAccount" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="dev.utils.Status" %>

<%! SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); %>
<%! DecimalFormat decimalFormat = new DecimalFormat("#,###.##"); %>
<%! ClientServiceImpl service = new ClientServiceImpl(); %>
<%
    Client client = new Client();
    client = (Client) session.getAttribute("client");
%>
<%! Double saldo = service.getSaldo(); %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
    </head>
    <body>
        <h1>Bem vindo, <%=client.getName()%>!</h1>
        <a href="/UFFBank/Deposit"><button>Depositar</button></a>
        <a href="/UFFBank/Investment"><button>Investir</button></a>
        <a href="/UFFBank/Transfer"><button>Transferir</button></a>
        <% if (!client.getBankAccountId().equals(null)) { %>
            <h3>Seu saldo: R$<%=decimalFormat.format(saldo)%></h3>
        <% } else {%>
            <h3>Conta desconhecida.</h3>
        <% } %>
    </body>
</html>
