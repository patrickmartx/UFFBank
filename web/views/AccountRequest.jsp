<%-- 
    Document   : AccountRequest
    Created on : 2 de dez. de 2023, 19:20:05
    Author     : Patrick
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="styles/estilo.css" />
        <link rel="stylesheet" type="text/css" href="styles/cadastro.css" />
      
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;700&family=Nunito:wght@400;700&display=swap" rel="stylesheet">
  
        <title>Cadastro | UFFBANK</title>
    </head>
    <!-- <body>
        <form action="AccountRequest" method="post">
            <label for="cpf">cpf</label><br>
            <input type="text" id="cpf" name="cpf" /><br>
            <label for="name">nome</label><br>
            <input type="text" id="name" name="name" /><br>
            <label for="phone">telefone</label><br>
            <input type="text" id="phone" name="phone" /><br>
            <label for="cep">cep</label>
            <input type="text" id="cep" name="cep" /><br>
            <label for="email">email</label>
            <input type="text" id="email" name="email" /><br>
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" /><br>
            <label for="houseNumber">número da casa</label>
            <input type="number" id="houseNumber" name="houseNumber" /><br>
            <label for="birthDate">Data de nascimento</label>
            <input type="date" id="birthDate" name="birthDate" /><br>
            

            <input type="submit" value="AccountRequest" />
            
             <%-- Exibe a mensagem de erro apenas se ela estiver presente --%>
            <% String errorMessage = (String) request.getAttribute("errorMessage"); %>
            <% if (errorMessage != null) { %>
                <div style="color: red;"><%= errorMessage %></div>
            <% } %>
        </form>
    </body> -->
    <body>
        <header>
          <div class="menu">
          <a class="logo">
            <svg width="48" height="48" viewBox="0 0 48 48" fill="none" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink">
              <rect width="48" height="48" fill="url(#pattern0)"/>
              <defs>
              <pattern id="pattern0" patternContentUnits="objectBoundingBox" width="1" height="1">
              <use xlink:href="#image0_80_486" transform="scale(0.0025)"/>
              </pattern>
              <image id="image0_80_486" width="400" height="400" xlink:href="data:image/png;base64,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"/>
              </defs>
            </svg>
          </a>
            
          <nav>
            <a class="link" href="">Cadastro</a>
            <a class="link" href="">Área do administrador</a>
            <a class="btn-login" href="">Login</a>
          </nav>
          
          </div>
        </header>
      
        <main>
          <h1 class="title">Crie sua conta</h1>
          <p class="subtitle">
            Após a análise dos seus dados, assim que aprovados, você terá acesso imediato à sua conta no <span>UFF<strong>BANK</strong> </span>!
          </p>
      
          
          <form class="register-form" action="AccountRequest" method="post">
            <div class="fieldset">
              <h2 class="legend">Identificação</h2>
      
              <div class="field">
                <label for="name">Nome completo</label>
                <input 
                  id="name" 
                  name="name"
                  required
                  autofocus
                  type="text"
                  placeholder="Ex: Jonh Doe">
              </div>
      
              <div class="field">
                <label for="nasc">Data de nascimento</label>
                <input 
                  id="nasc" 
                  name="house_number"
                  required
                  type="date"
                  max="2005-12-31"
                  placeholder="DD/MM/AAAA">
              </div>
      
              <div class="field">
                <label for="cpf">CPF</label>
                <input 
                  id="cpf" 
                  name="cpf"
                  required
                  type="tel"
                  maxlength="14"
                  minlength="11"
                  required
                  placeholder="000.000.000-00">
              </div>
            </div>
      
            <div class="fieldset">
              <h2 class="legend">Endereço</h2>
      
              <div class="field">
                <label for="end">CEP</label>
                <input 
                  id="cep" 
                  name="cep"
                  required
                  type="text"
                  type="number"
                  placeholder="00000-000">
              </div>
              <div class="address">
                <div class="field">
                  <label for="end">Endereço</label>
                  <input 
                    id="end" 
                    name="address"
                    required
                    type="text"
                    placeholder="Ex: Rua Mem de Sá ">
                </div>
      
                <div class="field">
                  <label for="number">Número</label>
                  <input 
                    id="number" 
                    name="number"
                    required
                    type="number"
                    placeholder="Ex: 345">
                </div>
            </div>
      
            </div>
      
            <div class="fieldset">
              <h2 class="legend">Informações adicionais</h2>
      
              <div class="field">
                <label for="email">Email</label>
                <input 
                  id="email" 
                  name="email"
                  required
                  type="email"
                  required
                  placeholder="Ex: name@email.com">
              </div>

              <div class="field">
                <label for="phone">Telefone</label>
                <input 
                  id="phone" 
                  name="phone"
                  required
                  type="tel"
                  maxlength="11"
                  required
                  placeholder="(00) 00000-0000">
              </div>

              <div class="field">
                <label for="password">Senha</label>
                <input 
                  id="password" 
                  type="password" 
                  name="user_password"
                  required
                  placeholder="Digite sua senha">
              </div>
            </div>

            <input class="primary-btn register-btn" type="submit" value="Pedir acesso" />
            
            <%-- Exibe a mensagem de erro apenas se ela estiver presente --%>
           <% String errorMessage = (String) request.getAttribute("errorMessage"); %>
           <% if (errorMessage != null) { %>
               <div style="color: red;"><%= errorMessage %></div>
           <% } %>
          </form>
        </main>
      </body>
</html>
