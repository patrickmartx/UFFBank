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
        <title>Login| UFFBANK</title>
    </head>
    <body>

        <div style="display: flex; height: 100%; position: relative; background: #FAFAFA">
            <div style="width: 700px;">
              <div style="width: 700px; height: 100vh; background: #26413C"></div>
        
              <div style="left: 80px; top: 174px; position: absolute; flex-direction: column; justify-content: flex-start; align-items: center; gap: 8px; display: inline-flex">
                <div><span style="color: white; font-size: 40px; font-family: Inter; font-weight: 400; line-height: 125%; word-wrap: break-word">UFF</span><span style="color: white; font-size: 40px; font-family: Inter; font-weight: 700; line-height: 125%; word-wrap: break-word"> BANK</span></div>
                <div style="color: white; font-size: 32px; font-family: Inter; font-weight: 500; line-height: 125%; word-wrap: break-word">O banco que  inova para você.</div>
              </div>
            </div>
            <div style="padding-top: 10%; display: flex; justify-content: center; width: 100%;">
            <div style="flex-direction: column; justify-content: flex-start; align-items: center; gap: 56px; display: inline-flex">
              <img style="width: 80px; height: 80px" src="banco_uff_logo 1.png" />

              <form action="/UFFBank/Login/Auth" method="post" style="flex-direction: column; justify-content: center; align-items: center; gap: 24px; display: flex">
                <div style="width: 454px; height: 88px; flex-direction: column; justify-content: flex-start; align-items: flex-start; gap: 5px; display: flex">
                  <div style="align-self: stretch; flex: 1 1 0; flex-direction: column; justify-content: flex-start; align-items: flex-start; gap: 10px; display: flex">
                    <div style="width: 250px; justify-content: flex-start; align-items: flex-start; gap: 10px; display: inline-flex">
                      <label
                      for="cpf" style="color: #111928; font-size: 16px; font-family: Inter; font-weight: 500; line-height: 24px; word-wrap: break-word">CPF</label>
                    </div>
                    <input name="cpf" id="cpf" style="align-self: stretch; flex: 1 1 0; padding-top: 12px; padding-bottom: 12px; padding-left: 20px; padding-right: 16px; background: white; border-radius: 6px; border: 1px #B4B9BF solid; justify-content: flex-start; align-items: center; gap: 10px; display: inline-flex; outline: none"
                    placeholder="000.000.000-00">
                  </div>
                </div>
                <div style="width: 454px; height: 88px; flex-direction: column; justify-content: flex-start; align-items: flex-start; gap: 5px; display: flex">
                  <div style="align-self: stretch; flex: 1 1 0; flex-direction: column; justify-content: flex-start; align-items: flex-start; gap: 10px; display: flex">
                    <div style="width: 250px; justify-content: flex-start; align-items: flex-start; gap: 10px; display: inline-flex">
                      <label style="color: #111928; font-size: 16px; font-family: Inter; font-weight: 500; line-height: 24px; word-wrap: break-word" for="password">Senha</label>
                    </div>
                    <input type="password" name="password" id="password" style="align-self: stretch; flex: 1 1 0; padding-top: 12px; padding-bottom: 12px; padding-left: 20px; padding-right: 16px; background: white; border-radius: 6px; border: 1px #B4B9BF solid; justify-content: flex-start; align-items: center; gap: 10px; display: inline-flex; outline: none;" placeholder="Digite sua senha" 
                    >
                  </div>
                </div>

                <input type="submit" value="Login" style="text-align: center; color: #FAFAFA; font-size: 18px; font-family: Inter; font-weight: 500; line-height: 30px; word-wrap: break-word; width: 454px; height: 54px; background: #35BBA2; border-radius: 6px; justify-content: center; align-items: center; display: inline-flex; cursor: pointer; outline: none">
                </input>
            </form>
            </div>
          </div>
          </div>

        <!-- <form action="/UFFBank/Login/Auth" method="post">
            <label for="cpf">cpf</label>
            <input type="text" id="cpf" name="cpf" />

            <label for="password">Password:</label>
            <input type="password" id="password" name="password" />

            <input type="submit" value="Login" />
        </form> -->
    </body>
</html>
