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
<%! BankAccountService bankService = new BankAccountServiceImpl();%>
<% BankAccount bankAccout = bankService.getById(client.getBankAccountId()); %>

<%Integer bankNumber = bankAccout.getBankNumber(); %>
<%String accountNumber = bankAccout.getAccountNumber(); %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="estilo.css" />
        <link rel="stylesheet" type="text/css" href="views/styles/estilo.css" />
        <link rel="stylesheet" type="text/css" href="views/styles/deposito.css" />
        <title>Depósito | UFFBANK</title>
    </head>
    <body>
      <div class="page">
        <%@ include file="/views/components/sidebar.jsp" %>
        <main>
          <%@ include file="/views/components/TopBar.jsp" %>
    
          <h1 class="home-title">Nós somos o banco oficial do Uffiano</h1>
          <p class="home-subtitle">Deposite, tranfira, saque e invista</p>
          <p class="home-p">Comece agora</p>
       
          <div class="number-wrapper">
             <div>
               <p>Número da agência</p>
               <p><%= bankNumber %></p>
             </div>
       
             <div>
               <p>Número da conta</p>
               <p><%= accountNumber %></p>
             </div>
         </div>
        </main>
      </div>
          
    </body>
</html>
