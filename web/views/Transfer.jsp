<%-- 
    Document   : Transfer
    Created on : 3 de dez. de 2023, 01:47:35
    Author     : Patrick
--%>

<%@page import="dev.services.ClientService"%>
<%@page import="dev.services.impl.ClientServiceImpl"%>
<%--<%@page import="dev.services.BankAccountService"%>--%>
<%--<%@page import="dev.services.impl.BankAccountServiceImpl"%>--%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="dev.entity.Client"%>
<%--<%@page import="dev.entity.BankAccount"%>--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%! DecimalFormat decimalFormat = new DecimalFormat("#,##0.00"); %>
<%! ClientService service = new ClientServiceImpl(); %>

<%
    Client client = new Client();
    client = (Client) session.getAttribute("client");
    Double saldo = service.getAccountBalance(client.getBankAccountId());
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="estilo.css" />
        <link rel="stylesheet" type="text/css" href="views/styles/estilo.css" />
        <link rel="stylesheet" type="text/css" href="views/styles/deposito.css" />
        
        <title>Transferência | UFFBANK</title>
    </head>
    <body>
        <div class="page">
          <%@ include file="/views/components/sidebar.jsp" %>
          <main>
              <%@ include file="/views/components/TopBar.jsp" %>
      
            <form class="form"  action="Transfer" method="post">
              <h2 class="title">Transferências</h2>
      
              <div class="field">
                <label for="value">Quanto você  deseja enviar?</label>
                <input 
                  id="value" 
                  name="value"
                  required
                  type="number"
                  placeholder="0,00">
              </div>
      
              <div class="field">
                <label for="otherBankNumber">Número da agência a receber</label>
                <input 
                  id="otherBankNumber" 
                  type="text" 
                  name="otherBankNumber"
                  required >
              </div>

              <div class="field">
                <label for="otherAccountNumber">Número da conta a receber</label>
                <input 
                  id="otherAccountNumber" 
                  type="text" 
                  name="otherAccountNumber"
                  required >
              </div>
      
              <button class="primary-btn" type="submit">
              Transferir 
            </button>
            </form>
            <%-- Exibe a mensagem de sucesso apenas se ela estiver presente --%>
            <% String errorMessege = (String) request.getAttribute("errorMessege"); %>
            <% if (errorMessege != null) { %>
                <div style="color: red;"><%= errorMessege %></div>
            <% } %>
            
            <%-- Exibe a mensagem de sucesso apenas se ela estiver presente --%>
            <% String sucessMessege = (String) request.getAttribute("sucessMessege"); %>
            <% if (sucessMessege != null) { %>
                <div style="color: green;"><%= sucessMessege %></div>
            <% } %>
          </main>
        </div>
    </body>
</html>
