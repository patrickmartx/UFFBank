<%-- 
    Document   : login.jsp
    Created on : 7 de nov. de 2023, 19:56:25
    Author     : Patrick
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="views/styles/estilo.css" />
        <link rel="stylesheet" type="text/css" href="views/styles/login.css" />
        <title>Login | UFFBANK</title>
    </head>

    <body>
      <div class="login-page">
        <aside class="hero">
          <div class="img-wrapper">
            <div class="text">
              <h1>UFF<span>BANK<span></h1>
              <p>O banco que  inova para você.</p>
            </div>
          </div>
        </aside>
    
        <main>
    
          <form 
            action="Login"
            method="post" 
            class="form-login">
    
            <img 
              src="contents/imgs/logo.png" 
              alt="logo da UFFBANK"
              class="logo" 
            />
    
              <div class="field">
                <label for="cpf">CPF</label>
                <input 
                  name="cpf" 
                  id="cpf" 
                  type="tel"
                  maxlength="14"
                  minlength="11"
                  required
                  placeholder="000.000.000-00">
              </div>
        
              <div class="field">
                <label for="password">Senha</label>
                <input 
                  id="password" 
                  type="password" 
                  name="password"
                  required
                  placeholder="Digite sua senha">
              </div>
    
            <button class="primary-btn" type="submit">
                Login 
                <svg width="32" height="32" viewBox="0 0 32 32" fill="none" xmlns="http://www.w3.org/2000/svg">
                  <path d="M28.8 15.2L18.45 4.7C18 4.25 17.3 4.25 16.85 4.7C16.4 5.15 16.4 5.85 16.85 6.3L25.25 14.85H3.99999C3.39999 14.85 2.89999 15.35 2.89999 15.95C2.89999 16.55 3.39999 17.1 3.99999 17.1H25.35L16.85 25.75C16.4 26.2 16.4 26.9 16.85 27.35C17.05 27.55 17.35 27.65 17.65 27.65C17.95 27.65 18.25 27.55 18.45 27.3L28.8 16.8C29.25 16.35 29.25 15.65 28.8 15.2Z" fill="#FAFAFA"/>
                  </svg>
              </button>

             <%-- Exibe a mensagem de erro apenas se ela estiver presente --%>
             <% String errorMessage = (String) request.getAttribute("errorMessage"); %>
             <% if (errorMessage != null) { %>
                <div class="error-msg"><%= errorMessage %></div>
             <% } %>
          </form>
        </main>
      </div>
    </body>
</html>
