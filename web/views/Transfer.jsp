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

<%--<%! DecimalFormat decimalFormat = new DecimalFormat("#,##0.00"); %>
<%! ClientService service = new ClientServiceImpl(); %>
<%
    Client client = new Client();
    client = (Client) session.getAttribute("client");
%>
<% Double saldo = service.getAccountBalance(client.getBankAccountId()); %>--%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="estilo.css" />
        <link rel="stylesheet" type="text/css" href="views/styles/estilo.css" />
        <link rel="stylesheet" type="text/css" href="views/styles/deposito.css" />
        
        <title>Transferência | UFFBANK</title>
    </head>
    <body>
        <!-- <form action="/UFFBank/Login" method="get">
            <button type="submit">Home</button>
        </form>
        <form action="/UFFBank/Logout" method="post">
            <button type="submit">Logout</button>
        </form> -->
        <!-- <h1>TRANSFERIR</h1>
        <form action="Transfer" method="post">
            <label for="otherBankNumber">Número do banco destino</label>
            <input type="number" id="otherBankNumber" name="otherBankNumber" />

            <label for="otherAccountNumber">Número da conta destino</label>
            <input type="text" id="otherAccountNumber" name="otherAccountNumber" />
            
            <label for="pix">pix</label>
            <input type="text" id="value" name="pix valido" />
            
            <label for="value">Valor:</label>
            <input type="text" id="value" name="0,00" />

            <input type="submit" value="Depositar" />
        </form> --> 
        <div class="page">
          <%@ include file="/views/components/sidebar.html" %>
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
                <label for="otherAccountNumber">Número da agência a receber</label>
                <input 
                  id="otherAccountNumber" 
                  type="text" 
                  name="otherAccountNumber"
                  required >
              </div>

              <div class="field">
                <label for="otherBankNumber">Número da conta a receber</label>
                <input 
                  id="otherBankNumber" 
                  type="text" 
                  name="otherBankNumber"
                  required >
              </div>
      
              <input class="primary-btn" type="submit" value="Transferir" />
            </form>
          </main>
        </div>
    </body>
</html>
