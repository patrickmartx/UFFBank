<%--<%! DecimalFormat decimalFormat = new DecimalFormat("#,##0.00"); %>
<%! ClientService service = new ClientServiceImpl(); %>--%>
<%
    Client client = new Client();
    client = (Client) session.getAttribute("client");
%>
<% Double saldo = service.getAccountBalance(client.getBankAccountId()); %>

<div class="topbar">
  <p>Bem-vindo, 
    <span>Igor</span>
  </p>

  <% if (!client.getBankAccountId().equals(null)) { %>
    <h1>R$ <span><%=decimalFormat.format(saldo)%></span></h1>
  <% } else {%>
      <h3>R$ 0,00</h3>
  <% } %>
</div>