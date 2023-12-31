<%-- 
    Document   : Deposit
    Created on : 2 de dez. de 2023, 23:57:09
    Author     : Patrick
--%>
<%@page import="dev.services.ClientService"%>
<%@page import="dev.services.impl.ClientServiceImpl"%>
<%@page import="dev.services.BankAccountService"%>
<%@page import="dev.services.impl.BankAccountServiceImpl"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="dev.entity.Client"%>
<%@page import="dev.entity.BankAccount"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%! DecimalFormat decimalFormat = new DecimalFormat("#,##0.00"); %>
<%! ClientService service = new ClientServiceImpl(); %>
<%
    Client client = new Client();
    client = (Client) session.getAttribute("client");
%>
<% Double saldo = service.getAccountBalance(client.getBankAccountId()); %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>DEPOSITAR</h1>
        <% if (!client.getBankAccountId().equals(null)) { %>
            <h3>Seu saldo: R$<%=decimalFormat.format(saldo)%></h3>
        <% } else {%>
            <h3>Conta desconhecida.</h3>
        <% } %>
        
        <form action="Deposit" method="post">
            <label for="value">Valor para depósito:</label>
            <input type="number" min="0.00" step="0.01" id="value" name="value" />
            <input type="submit" value="Deposit" />
        </form>
        
        <%-- Exibe a mensagem de erro apenas se ela estiver presente --%>
            <% String sucessMessege = (String) request.getAttribute("sucessMessege"); %>
            <% if (sucessMessege != null) { %>
                <div style="color: green;"><%= sucessMessege %></div>
            <% } %>
    </body>
</html>
