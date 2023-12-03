<%-- 
    Document   : Investment
    Created on : 3 de dez. de 2023, 17:16:35
    Author     : Patrick
--%>

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
<%! ClientService service = new ClientServiceImpl(); %>
<%! BankAccountService bankService = new BankAccountServiceImpl(); %>
<%
    Client client = new Client();
    client = (Client) session.getAttribute("client");
    
    BankAccount bankAccount = bankService.getById(client.getBankAccountId());
%>

<% Double saldo = service.getAccountBalance(client.getBankAccountId()); 
   Double InvestmentWalletBallance = service.getInvestmentWalletBallance(bankAccount.getInvestmentWalletId());
   Double yieldPercentage = service.getYieldPercentage(bankAccount.getInvestmentWalletId());
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>INVESTIR</h1>
            <h3>Valor investido: R$<%=decimalFormat.format(InvestmentWalletBallance)%></h3>
            <h3>Rendimento por mês: <%=decimalFormat.format(yieldPercentage)%>%</h3>
        
        <form action="Investment" method="post">
            <label for="value">Valor para investir:</label>
            <input type="number" min="0.00" step="0.01" id="value" name="value" />
            <input type="submit" value="Investment" />
        </form>
        
        <%-- Exibe a mensagem de sucesso apenas se ela estiver presente --%>
            <% String sucessMessege = (String) request.getAttribute("sucessMessege"); %>
            <% if (sucessMessege != null) { %>
                <div style="color: green;"><%= sucessMessege %></div>
            <% } %>
            
        <%-- Exibe a mensagem de sucesso apenas se ela estiver presente --%>
            <% String errorMessege = (String) request.getAttribute("errorMessege"); %>
            <% if (errorMessege != null) { %>
                <div style="color: green;"><%= errorMessege %></div>
            <% } %>   
    </body>
</html>
