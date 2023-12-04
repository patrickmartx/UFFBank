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
<%@page import="dev.entity.Client"%>
<%@page import="dev.entity.BankAccount"%>
<%@page import="dev.entity.InvestmentWallet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%! DecimalFormat decimalFormat = new DecimalFormat("#,##0.00"); %>
<%! ClientService clientService = new ClientServiceImpl(); %>
<%! BankAccountService bankService = new BankAccountServiceImpl(); %>
<%! AdminService service = new AdminServiceImpl(); %>
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
        <title>JSP Page</title>
    </head>
    <body>
        <% 
            for (int i = 0; i < extract.size(); i++) { %>
            <h3>Tipo de transição: <%=extract.get(i).getTransactionType().getValue()%></h3>
            <h3>Valor transferido: <%=extract.get(i).getValue() %></h3>
        <%     } %>
        
    </body>
</html>
