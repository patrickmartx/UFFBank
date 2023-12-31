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
    Double saldo = service.getAccountBalance(client.getBankAccountId());
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="estilo.css" />
        <link rel="stylesheet" type="text/css" href="views/styles/estilo.css" />
        <link rel="stylesheet" type="text/css" href="views/styles/deposito.css" />
        <title>Depósito | UFFBANK</title>
    </head>
    <!-- <body>
        <h1>DEPOSITAR</h1>
        
        
        <form action="Deposit" method="post">
            <label for="value">Valor para depósito:</label>
            <input type="number" min="0.00" step="0.01" id="value" name="value" />
            <input type="submit" value="Deposit" />
        </form>
        
        
    </body> -->

    <body>
      <div class="page">
        <%@ include file="/views/components/sidebar.jsp" %>
        <main>
          <%@ include file="/views/components/TopBar.jsp" %>
    
          <form class="form" action="Deposit" method="post">
            <h2 class="title">Depósito</h2>
    
            <div class="field">
              <label for="value">Quanto você deseja depositar?</label>
              <input 
                id="value" 
                name="value"
                required
                type="number"
                placeholder="0,00">
            </div>
    
            <button class="primary-btn" type="submit">
              Depositar 
            </button>
          </form>
          <%-- Exibe a mensagem de erro apenas se ela estiver presente --%>
          <% String errorMessage = (String) request.getAttribute("errorMessage"); %>
          <% if (errorMessage != null) { %>
              <div class="error-msg"><%= errorMessage %></div>
          <% } %>

          <%-- Exibe a mensagem de sucesso apenas se ela estiver presente --%>
          <% String sucessMessege = (String) request.getAttribute("sucessMessege"); %>
          <% if (sucessMessege != null) { %>
              <div class="success-msg"><%= sucessMessege %></div>
          <% } %>
        </main>
      </div>
          
    </body>
</html>
