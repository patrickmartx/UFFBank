<%-- 
    Document   : Transfer
    Created on : 3 de dez. de 2023, 01:47:35
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
        <form action="/UFFBank/Login" method="get">
            <button type="submit">Home</button>
        </form>
        <form action="/UFFBank/Logout" method="post">
            <button type="submit">Logout</button>
        </form>
        <h1>TRANSFERIR</h1>
        <% if (!client.getBankAccountId().equals(null)) { %>
            <h3>Seu saldo: R$<%=decimalFormat.format(saldo)%></h3>
        <% } else {%>
            <h3>Conta desconhecida.</h3>
        <% } %>
        <form action="Transfer" method="post">
            <label for="otherBankNumber">Número do banco destino</label>
            <input type="number" id="otherBankNumber" name="otherBankNumber" />

            <label for="otherAccountNumber">Número da conta destino</label>
            <input type="text" id="otherAccountNumber" name="otherAccountNumber" />
            
            <label for="value">Valor para transferência:</label>
            <input type="number" min="0.00" step="0.01" id="value" name="value" />

            <input type="submit" value="Transfer" />
        </form>
            <%-- Exibe a mensagem de erro apenas se ela estiver presente --%>
            <% String errorMessage = (String) request.getAttribute("errorMessage"); %>
            <% if (errorMessage != null) { %>
                <div style="color: red;"><%= errorMessage %></div>
            <% } %>
            
            <%-- Exibe a mensagem de erro apenas se ela estiver presente --%>
            <% String sucessMessege = (String) request.getAttribute("sucessMessege"); %>
            <% if (sucessMessege != null) { %>
                <div style="color: green;"><%= sucessMessege %></div>
            <% } %>
    </body>
</html>
