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
<%@ page import="dev.services.impl.ClientServiceImpl" %>
<%@ page import="dev.entity.Admin" %>
<%@ page import="dev.entity.Client" %>
<%@ page import="java.util.Date" %>
<%@ page import="dev.utils.Status" %>

<%! AdminService service = new ClientServiceImpl(); %>
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
    <div class="page">
        <div class="sidebar">
          <div>
            <div class="logo">
              <img 
                src="./logo.png" 
                alt="logo da UFFBANK"
              />
    
              <h1>
                UFF<span>BANK</span>
              </h1>
            </div>
    
            <nav class="menu">
              <a class="link" href="">
                <svg width="24" height="22" viewBox="0 0 24 22" fill="none" xmlns="http://www.w3.org/2000/svg">
                  <path d="M22.5 17.8326H21.75V17.1651C21.75 16.7673 21.592 16.3857 21.3107 16.1044C21.0294 15.8231 20.6478 15.6651 20.25 15.6651H19.5V9.58258H20.25C20.6478 9.58258 21.0294 9.42454 21.3107 9.14324C21.592 8.86193 21.75 8.4804 21.75 8.08258V7.41508H23.25C23.4194 7.41408 23.5835 7.35578 23.7155 7.24966C23.8475 7.14354 23.9397 6.99585 23.9771 6.83064C24.0145 6.66543 23.9949 6.49242 23.9214 6.3398C23.8479 6.18717 23.725 6.0639 23.5725 5.99008L12.3225 0.575076C12.2218 0.527095 12.1116 0.502197 12 0.502197C11.8884 0.502197 11.7782 0.527095 11.6775 0.575076L0.4275 5.99008C0.275046 6.0639 0.152075 6.18717 0.0786064 6.3398C0.0051381 6.49242 -0.0144997 6.66543 0.0228903 6.83064C0.0602802 6.99585 0.152496 7.14354 0.284523 7.24966C0.41655 7.35578 0.580613 7.41408 0.75 7.41508H2.25V8.08258C2.25 8.4804 2.40804 8.86193 2.68934 9.14324C2.97064 9.42454 3.35218 9.58258 3.75 9.58258H4.5V15.6651H3.75C3.35218 15.6651 2.97064 15.8231 2.68934 16.1044C2.40804 16.3857 2.25 16.7673 2.25 17.1651V17.8326H1.5C1.10218 17.8326 0.720644 17.9906 0.43934 18.2719C0.158035 18.5532 0 18.9348 0 19.3326L0 20.7501C0 20.949 0.0790176 21.1398 0.21967 21.2804C0.360322 21.4211 0.551088 21.5001 0.75 21.5001H23.25C23.4489 21.5001 23.6397 21.4211 23.7803 21.2804C23.921 21.1398 24 20.949 24 20.7501V19.3326C24 18.9348 23.842 18.5532 23.5607 18.2719C23.2794 17.9906 22.8978 17.8326 22.5 17.8326ZM12 2.08258L19.965 5.91508H4.035L12 2.08258ZM3.75 8.08258V7.41508H20.25V8.08258H3.75ZM18 9.58258V15.6651H16.125V9.58258H18ZM14.625 9.58258V15.6651H12.75V9.58258H14.625ZM11.25 9.58258V15.6651H9.375V9.58258H11.25ZM7.875 9.58258V15.6651H6V9.58258H7.875ZM3.75 17.1651H20.25V17.8326H3.75V17.1651ZM22.5 20.0001H1.5V19.3326H22.5V20.0001Z" fill="#676767"/>
                  </svg>
                    
                Depósito
              </a>
              <a class="link" href="">
                <svg width="24" height="24" viewBox="0 0 32 32" fill="none" xmlns="http://www.w3.org/2000/svg">
                  <g clip-path="url(#clip0_163_4684)">
                  <path d="M13.3333 18.6667L28 4" stroke="#676767" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"/>
                  <path d="M28 4L19.3333 28C19.2748 28.1277 19.1809 28.2358 19.0627 28.3117C18.9446 28.3875 18.8071 28.4278 18.6667 28.4278C18.5262 28.4278 18.3888 28.3875 18.2706 28.3117C18.1524 28.2358 18.0585 28.1277 18 28L13.3333 18.6667L4 14C3.87235 13.9415 3.76417 13.8476 3.68833 13.7294C3.61249 13.6112 3.57217 13.4738 3.57217 13.3333C3.57217 13.1929 3.61249 13.0554 3.68833 12.9373C3.76417 12.8191 3.87235 12.7252 4 12.6667L28 4Z" stroke="#676767" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"/>
                  </g>
                  <defs>
                  <clipPath id="clip0_163_4684">
                  <rect width="32" height="32" fill="#676767"/>
                  </clipPath>
                  </defs>
                  </svg>
                  
                Transferências
              </a>
    
              <a class="link" href="">
                <svg width="28" height="28" viewBox="0 0 32 32" fill="none" xmlns="http://www.w3.org/2000/svg">
                  <g clip-path="url(#clip0_48_366)">
                  <path d="M25.3333 12H12C10.5272 12 9.33334 13.1939 9.33334 14.6667V22.6667C9.33334 24.1394 10.5272 25.3333 12 25.3333H25.3333C26.8061 25.3333 28 24.1394 28 22.6667V14.6667C28 13.1939 26.8061 12 25.3333 12Z" stroke="#676767"  stroke-width="2" stroke-linecap="round" stroke-linejoin="round"/>
                  <path d="M18.6667 21.3333C20.1394 21.3333 21.3333 20.1394 21.3333 18.6667C21.3333 17.1939 20.1394 16 18.6667 16C17.1939 16 16 17.1939 16 18.6667C16 20.1394 17.1939 21.3333 18.6667 21.3333Z" stroke="#676767"  stroke-width="2" stroke-linecap="round" stroke-linejoin="round"/>
                  <path d="M22.6667 12.0001V9.33341C22.6667 8.62617 22.3857 7.94789 21.8856 7.4478C21.3855 6.9477 20.7072 6.66675 20 6.66675H6.66667C5.95942 6.66675 5.28115 6.9477 4.78105 7.4478C4.28095 7.94789 4 8.62617 4 9.33341V17.3334C4 18.0407 4.28095 18.7189 4.78105 19.219C5.28115 19.7191 5.95942 20.0001 6.66667 20.0001H9.33333" stroke="#676767"  stroke-width="2" stroke-linecap="round" stroke-linejoin="round"/>
                  </g>
                  <defs>
                  <clipPath id="clip0_48_366">
                  <rect width="32" height="32" fill="white"/>
                  </clipPath>
                  </defs>
                  </svg>
                  
                Saque</a>
              <a class="link" href="">
                <svg width="32px" height="32px" viewBox="0 0 32 32" fill="none" xmlns="http://www.w3.org/2000/svg" stroke="#676767"><g id="SVGRepo_bgCarrier" stroke-width="0"></g><g id="SVGRepo_tracerCarrier" stroke-linecap="round" stroke-linejoin="round"></g><g id="SVGRepo_iconCarrier"> <path d="M18.04 13.55C17.62 13.96 17.38 14.55 17.44 15.18C17.53 16.26 18.52 17.05 19.6 17.05H21.5V18.24C21.5 20.31 19.81 22 17.74 22H7.63C7.94 21.74 8.21 21.42 8.42 21.06C8.79 20.46 9 19.75 9 19C9 16.79 7.21 15 5 15C4.06 15 3.19 15.33 2.5 15.88V11.51C2.5 9.44001 4.19 7.75 6.26 7.75H17.74C19.81 7.75 21.5 9.44001 21.5 11.51V12.95H19.48C18.92 12.95 18.41 13.17 18.04 13.55Z" stroke="#676767"  stroke-width="1.5" stroke-linecap="round" stroke-linejoin="round"></path> <path d="M2.5 12.4101V7.8401C2.5 6.6501 3.23 5.59006 4.34 5.17006L12.28 2.17006C13.52 1.70006 14.85 2.62009 14.85 3.95009V7.75008" stroke="#676767"  stroke-width="1.5" stroke-linecap="round" stroke-linejoin="round"></path> <path d="M22.5588 13.9702V16.0302C22.5588 16.5802 22.1188 17.0302 21.5588 17.0502H19.5988C18.5188 17.0502 17.5288 16.2602 17.4388 15.1802C17.3788 14.5502 17.6188 13.9602 18.0388 13.5502C18.4088 13.1702 18.9188 12.9502 19.4788 12.9502H21.5588C22.1188 12.9702 22.5588 13.4202 22.5588 13.9702Z" stroke="#676767"  stroke-width="1.5" stroke-linecap="round" stroke-linejoin="round"></path> <path d="M7 12H14" stroke="#676767"  stroke-width="1.5" stroke-linecap="round" stroke-linejoin="round"></path> <path d="M9 19C9 19.75 8.79 20.46 8.42 21.06C8.21 21.42 7.94 21.74 7.63 22C6.93 22.63 6.01 23 5 23C3.54 23 2.27 22.22 1.58 21.06C1.21 20.46 1 19.75 1 19C1 17.74 1.58 16.61 2.5 15.88C3.19 15.33 4.06 15 5 15C7.21 15 9 16.79 9 19Z" stroke="#676767"  stroke-width="1.5" stroke-miterlimit="10" stroke-linecap="round" stroke-linejoin="round"></path> <path d="M6.49171 18.9795H3.51172" stroke="#676767"  stroke-width="1.5" stroke-miterlimit="10" stroke-linecap="round" stroke-linejoin="round"></path> <path d="M5 17.5195V20.5095" stroke="#676767"  stroke-width="1.5" stroke-miterlimit="10" stroke-linecap="round" stroke-linejoin="round"></path> </g></svg>
                Investimentos</a>
            </nav>
          </div>
      
          <a class="link" href="">
            <svg width="26" height="26" viewBox="0 0 32 32" fill="none" xmlns="http://www.w3.org/2000/svg">
              <g clip-path="url(#clip0_55_67)">
              <path d="M18.6667 10.6666V7.99992C18.6667 7.29267 18.3857 6.6144 17.8856 6.1143C17.3855 5.6142 16.7072 5.33325 16 5.33325H6.66667C5.95942 5.33325 5.28115 5.6142 4.78105 6.1143C4.28095 6.6144 4 7.29267 4 7.99992V23.9999C4 24.7072 4.28095 25.3854 4.78105 25.8855C5.28115 26.3856 5.95942 26.6666 6.66667 26.6666H16C16.7072 26.6666 17.3855 26.3856 17.8856 25.8855C18.3857 25.3854 18.6667 24.7072 18.6667 23.9999V21.3333" stroke="#2C3E50" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"/>
              <path d="M9.33333 16H28M28 16L24 12M28 16L24 20" stroke="#2C3E50" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"/>
              </g>
              <defs>
              <clipPath id="clip0_55_67">
              <rect width="32" height="32" fill="white"/>
              </clipPath>
              </defs>
              </svg>
              
            Sair
          </a>
        </div>
        <main>
          <div class="topbar">
            <p>Bem-vindo, 
              <span><%=admin.getName()%></span>
            </p>
          </div>
    
        
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
              
                        <a class="btn-login" href="/UFFBank/RegisterClient?userId=<%= clients.get(i).getId() %>">
                          <svg width="16" height="16" viewBox="0 0 16 16" fill="none" xmlns="http://www.w3.org/2000/svg">
                            <path d="M6.25838 7.99999C6.25838 7.03408 7.03414 6.25832 8.00005 6.25832C8.96595 6.25832 9.74171 7.03408 9.74171 7.99999C9.74171 8.96589 8.96595 9.74165 8.00005 9.74165C7.03414 9.74165 6.25838 8.96589 6.25838 7.99999ZM6.71672 7.99999C6.71672 8.70908 7.29095 9.28332 8.00005 9.28332C8.70914 9.28332 9.28338 8.70908 9.28338 7.99999C9.28338 7.29089 8.70914 6.71665 8.00005 6.71665C7.29095 6.71665 6.71672 7.29089 6.71672 7.99999Z" fill="#6B7280" stroke="#6B7280" stroke-width="0.666667"/>
                            <path d="M14.6504 8.43787L14.6631 8.41925L14.6731 8.39908C14.7951 8.15524 14.7951 7.84478 14.6731 7.60094L14.6631 7.58078L14.6504 7.56215C13.7218 6.20118 12.7766 5.21566 11.6953 4.57287C10.6095 3.92735 9.41068 3.64168 8 3.64168C6.58932 3.64168 5.39046 3.92735 4.30466 4.57287C3.22345 5.21566 2.27816 6.20118 1.34965 7.56215L1.34963 7.56214L1.34765 7.56511C1.17301 7.82708 1.17301 8.17295 1.34765 8.43491L1.34763 8.43492L1.34965 8.43787C2.27816 9.79885 3.22345 10.7844 4.30466 11.4272C5.39046 12.0727 6.58932 12.3583 8 12.3583C9.41068 12.3583 10.6095 12.0727 11.6953 11.4272C12.7766 10.7844 13.7218 9.79885 14.6504 8.43787ZM8 3.18335C9.88299 3.18335 11.2854 3.70062 12.3883 4.46465C13.4973 5.23284 14.3195 6.2615 15.0228 7.31009C15.2955 7.71937 15.3009 8.24952 15.0213 8.69211C14.318 9.72804 13.4961 10.7557 12.3878 11.5263C11.2844 12.2937 9.88211 12.8167 8 12.8167C6.11789 12.8167 4.71558 12.2937 3.61219 11.5263C2.50285 10.7549 1.68039 9.72601 0.976641 8.68905C0.702075 8.27625 0.702231 7.72292 0.977108 7.31028C1.68036 6.26162 2.50268 5.23289 3.61169 4.46465C4.71463 3.70062 6.11701 3.18335 8 3.18335Z" fill="#6B7280" stroke="#6B7280" stroke-width="0.666667"/>
                          </svg>                            
                        </a>
                    </li>
                    
            </ul>
                    
        <% }
        } else { %>
          <h3 class="emp-clients">Não há clientes cadastrados.</h3>
        <%
        } %>

          
    
        </div>
        </main>
      </div>
    <!-- <body>
        <h1>Bem vindo, <%=admin.getName()%>!</h1>
        <h2>Contas para ativar:</h2>
        
        
        <br>
        <br>
        
        <% if (!inactiveClients.isEmpty()) {
                for (int i = 0; i < inactiveClients.size(); i++) { %>
                    <h3>Nome do cliente: <%= inactiveClients.get(i).getName() %></h3>
                    <h3>CPF do cliente: <%= inactiveClients.get(i).getCpf()%></h3>
                    <a href="/UFFBank/RegisterClient?userId=<%= inactiveClients.get(i).getId() %>">
                        <button>Cadastrar cliente</button>
                    <br>
        <% }
        } else { %>
            <h3>Não há clientes para atualizar!</h3>
        <%
        } %>
        
         <%-- Exibe a mensagem de erro apenas se ela estiver presente --%>
            <% String sucessMessage = (String) request.getAttribute("sucessMessage"); %>
            <% if (sucessMessage != null) { %>
                <div style="color: green;"><%= sucessMessage %></div>
            <% } %>
    </body> -->
</html>
