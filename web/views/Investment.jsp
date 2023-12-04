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
<%! BankAccountService bankService = new BankAccountServiceImpl();
   %>
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
        <title>Investimentos | UFFBANK</title>
    </head>
    <body>
        <div class="page">
            <%@ include file="/views/components/sidebar.jsp" %>

            <main>
                <div class="head">
                    <button type="button" onclick="openSidebar()">
                        <svg width="44px" height="44px" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg"><g id="SVGRepo_bgCarrier" stroke-width="0"></g><g id="SVGRepo_tracerCarrier" stroke-linecap="round" stroke-linejoin="round"></g><g id="SVGRepo_iconCarrier"> <line x1="5" y1="7" x2="19" y2="7" stroke="#000000" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"></line> <line x1="5" y1="12" x2="19" y2="12" stroke="#000000" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"></line> <line x1="5" y1="17" x2="19" y2="17" stroke="#000000" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"></line> </g></svg>
                    </button>

                    <%@ include file="/views/components/TopBar.jsp" %>
                </div>
                <%
                    BankAccount bankAccount = bankService.getById(client.getBankAccountId());
//                    Double saldo = service.getAccountBalance(client.getBankAccountId());
                    Double InvestmentWalletBallance = service.getInvestmentWalletBallance(bankAccount.getInvestmentWalletId());
                    Double yieldPercentage = service.getYieldPercentage(bankAccount.getInvestmentWalletId());
                %>

                <form class="form" action="Investment" method="post">
                    <h2 class="title">Investimentos</h2>

                    <div class="investiment">
                        <p>investidos</p>
                        <h1 class="value">
                            R$<%=decimalFormat.format(InvestmentWalletBallance)%>
                        </h1>
                        <p><%=decimalFormat.format(yieldPercentage)%>% ao mês</p>
                    </div>

                    <div class="field">
                        <label for="value">Quanto você deseja investir?</label>
                        <input 
                            type="number" 
                            min="0.00" 
                            id="value" 
                            name="value"
                            required
                            placeholder="0,00">
                    </div>

                    <input class="primary-btn" type="submit" value="Investir">
                </form>
            </main>
        </div>

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

        <script type="text/javascript">
            function openSidebar() {
                const sidebar = document.getElementById("sidebar");
                sidebar.classList.add("openSidebar");
            }

            function closeSidebar() {
                const sidebar = document.getElementById("sidebar");
                sidebar.classList.remove("openSidebar");
            }
        </script>
    </body>
</html>
