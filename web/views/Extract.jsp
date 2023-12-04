<%-- 
    Document   : Extract.jsp
    Created on : 4 de dez. de 2023, 00:28:53
    Author     : Patrick
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="dev.entity.TransactionHistory"%>
<%@page import="dev.services.AdminService"%>
<%@page import="dev.services.impl.AdminServiceImpl"%>
<%@page import="dev.services.ClientService"%>
<%@page import="dev.services.impl.ClientServiceImpl"%>
<%@page import="dev.services.BankAccountService"%>
<%@page import="dev.services.impl.BankAccountServiceImpl"%>
<%@page import="dev.services.InvestmentWalletService"%>
<%@page import="dev.services.impl.InvestmentWalletServiceImpl"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="dev.entity.Admin"%>
<%@page import="dev.entity.Client"%>
<%@page import="dev.entity.BankAccount"%>
<%@page import="dev.entity.InvestmentWallet"%>
<%@page import="java.text.SimpleDateFormat" %> 
<%@page import="dev.utils.TransactionType" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%! SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); %>
<%! DecimalFormat decimalFormat = new DecimalFormat("#,##0.00"); %>
<%! ClientService clientService = new ClientServiceImpl(); %>
<%! BankAccountService bankService = new BankAccountServiceImpl(); %>
<%! AdminService service = new AdminServiceImpl(); %>
<%
    Admin admin = new Admin();
    admin = (Admin) session.getAttribute("admin");
%>
<%
    Long clientId = Long.valueOf(request.getParameter("userId"));
    Client client = new Client();
    client = clientService.getById(clientId);
    
    BankAccount bankAccount = bankService.getById(client.getBankAccountId());
    
    ArrayList<TransactionHistory> extract = service.generateTransactionHistory(bankAccount.getId());
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="views/styles/estilo.css" />
        <link rel="stylesheet" type="text/css" href="views/styles/deposito.css" />
        <title>JSP Page</title>
    </head>
    <body>
        <div class="page">
        <%@ include file="/views/components/sidebarAdmin.jsp" %>
        <main>
          <%@ include file="/views/components/TopBarAdmin.jsp" %>
          <div class="title">Extrato do cliente <b><%=client.getName()%></b></div>
          
          <% if (!extract.isEmpty()) { %>
                <div class="table">
                    <div class="tb-head">
                      <p>Data da transferência</p>  
                      <p>Tipo de transferência</p>
                      <p>Valor</p>
                      <p>Id da conta remetente</p>
                    </div>
                 <ul class="client-list ">

            <%    for (int i = extract.size() - 1; i >= 0; i--) { %>
                    <li class="data-client">
                        <p><%= dateFormat.format(extract.get(i).getTransactionDate())  %></p>
                        <p><%= extract.get(i).getTransactionType().getValue() %></p>
                        <% if (extract.get(i).getTransactionType().getValue() == TransactionType.WITHDRAWAL.getValue() || 
                               (extract.get(i).getTransactionType().getValue() == TransactionType.TRANSFER.getValue() && extract.get(i).getSenderAccountId() == bankAccount.getId() )) { %>
                            <p><%= decimalFormat.format(extract.get(i).getValue() * -1) %></p>
                        <% } else {%>
                            <p><%= decimalFormat.format(extract.get(i).getValue()) %></p>
                        <% } %>
                        <p><%= extract.get(i).getReceiverAccountId() %></p>
                    </li>
                    
            </ul>
                    
        <% }
        } else { %>
            <h3 class="emp-clients">Não há histórico de Transações!</h3>
        <%
        } %>
        
    </body>
</html>
