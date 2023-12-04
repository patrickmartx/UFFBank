<%-- 
    Document   : Home
    Created on : 7 de nov. de 2023, 20:06:11
    Author     : Patrick
--%>

<%@page import="dev.model.complements.BankAccountRepository"%>
<%@page import="java.text.DecimalFormat"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="dev.services.AdminService" %>
<%@ page import="dev.services.ClientService" %>
<%@ page import="dev.services.impl.AdminServiceImpl" %>
<%@ page import="dev.services.impl.ClientServiceImpl" %>
<%@ page import="dev.entity.Admin" %>
<%@ page import="dev.entity.Client" %>
<%@ page import="java.util.Date" %>
<%@ page import="dev.utils.Status" %>

<%! AdminService service = new AdminServiceImpl(); %>
<%
    Admin admin = new Admin();
    admin = (Admin) session.getAttribute("admin");
%>

<%! ArrayList<Client> clients = service.getActiveClients(); %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="views/styles/estilo.css" />
        <link rel="stylesheet" type="text/css" href="views/styles/deposito.css" />
        <title>Área do administrador | UFFBANK</title>
    </head>
    <body>
    <div class="page">
        <%@ include file="/views/components/sidebarAdmin.jsp" %>
        <main>
          <%@ include file="/views/components/TopBarAdmin.jsp" %>
        <div class="title">Clientes Ativos</div>
        
            <% if (!clients.isEmpty()) { %>
                <div class="table">
                    <div class="tb-head">
                      <p>Nome</p>
                      <p>CPF</p>
                    </div>
                 <ul class="client-list">

            <% for (int i = 0; i < clients.size(); i++) { %>
                    <li class="data-client">
                        <p><%= clients.get(i).getName() %></p>
                        <p><%= clients.get(i).getCpf()%></p>
              
                        <a class="btn-login" href="/UFFBank/Extract?userId=<%= clients.get(i).getId()%>">
                          <svg width="16" height="16" viewBox="0 0 16 16" fill="none" xmlns="http://www.w3.org/2000/svg">
                            <path d="M6.25838 7.99999C6.25838 7.03408 7.03414 6.25832 8.00005 6.25832C8.96595 6.25832 9.74171 7.03408 9.74171 7.99999C9.74171 8.96589 8.96595 9.74165 8.00005 9.74165C7.03414 9.74165 6.25838 8.96589 6.25838 7.99999ZM6.71672 7.99999C6.71672 8.70908 7.29095 9.28332 8.00005 9.28332C8.70914 9.28332 9.28338 8.70908 9.28338 7.99999C9.28338 7.29089 8.70914 6.71665 8.00005 6.71665C7.29095 6.71665 6.71672 7.29089 6.71672 7.99999Z" fill="#6B7280" stroke="#6B7280" stroke-width="0.666667"/>
                            <path d="M14.6504 8.43787L14.6631 8.41925L14.6731 8.39908C14.7951 8.15524 14.7951 7.84478 14.6731 7.60094L14.6631 7.58078L14.6504 7.56215C13.7218 6.20118 12.7766 5.21566 11.6953 4.57287C10.6095 3.92735 9.41068 3.64168 8 3.64168C6.58932 3.64168 5.39046 3.92735 4.30466 4.57287C3.22345 5.21566 2.27816 6.20118 1.34965 7.56215L1.34963 7.56214L1.34765 7.56511C1.17301 7.82708 1.17301 8.17295 1.34765 8.43491L1.34763 8.43492L1.34965 8.43787C2.27816 9.79885 3.22345 10.7844 4.30466 11.4272C5.39046 12.0727 6.58932 12.3583 8 12.3583C9.41068 12.3583 10.6095 12.0727 11.6953 11.4272C12.7766 10.7844 13.7218 9.79885 14.6504 8.43787ZM8 3.18335C9.88299 3.18335 11.2854 3.70062 12.3883 4.46465C13.4973 5.23284 14.3195 6.2615 15.0228 7.31009C15.2955 7.71937 15.3009 8.24952 15.0213 8.69211C14.318 9.72804 13.4961 10.7557 12.3878 11.5263C11.2844 12.2937 9.88211 12.8167 8 12.8167C6.11789 12.8167 4.71558 12.2937 3.61219 11.5263C2.50285 10.7549 1.68039 9.72601 0.976641 8.68905C0.702075 8.27625 0.702231 7.72292 0.977108 7.31028C1.68036 6.26162 2.50268 5.23289 3.61169 4.46465C4.71463 3.70062 6.11701 3.18335 8 3.18335Z" fill="#6B7280" stroke="#6B7280" stroke-width="0.666667"/>
                          </svg>                            
                        </a>
                    </li>
        <% }
        } else { %>
          <h3 class="emp-clients">Não há clientes cadastrados.</h3>
        <%
        } %>
        </ul>

        </div>
        </main>
      </div>
    </body>
</html>
