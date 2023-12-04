<%--<%! DecimalFormat decimalFormat = new DecimalFormat("#,##0.00"); %>
<%! ClientService service = new ClientServiceImpl(); %>--%>

<div class="topbar">
  <p>Bem-vindo, 
    <span><%= client.getName() %></span>
  </p>

  <% if (!client.getBankAccountId().equals(null)) { %>
    <h1>R$ <span><%=decimalFormat.format(saldo)%></span></h1>
  <% } else {%>
      <h3>R$ 0,00</h3>
  <% } %>
</div>