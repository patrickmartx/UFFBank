<%-- 
    Document   : Deposit
    Created on : 7 de nov. de 2023, 20:55:05
    Author     : Patrick
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="estilo.css" />
        
        <title>Saque | UFFBANK</title>
    </head>
    <body
      <div class="Desktop2" style="display: flex; background: white">
      <div class="Frame4" style="width: 224px;  padding-left: 24px; padding-right: 24px; margin-right: 24px; padding-top: 48px; padding-bottom: 48px; background: #F9F9F9; border-right: 1px #EBEBEB solid; flex-direction: column; justify-content: flex-start; align-items: flex-end; gap: 150px; display: inline-flex">
      <div class="Frame15" style="align-self: stretch; flex-direction: column; justify-content: flex-start; align-items: flex-start; gap: 24px; display: flex">
        
        <div class="LOGO" style="padding-bottom: 10px; padding-right: 38px; justify-content: flex-start; align-items: flex-end; gap: 11px; display: inline-flex">
          <img class="BancoUffLogo1" style="width: 26px; height: 26px" src="contents/imgs/logo.png" />
          <div class="UffBank"><span style="color: black; font-size: 20px; font-family: Inter; font-weight: 300; word-wrap: break-word">UFF </span><span style="color: black; font-size: 20px; font-family: Inter; font-weight: 700; word-wrap: break-word">BANK</span></div>
        </div>
        <div class="Frame13" style="align-self: stretch; flex-direction: column; justify-content: flex-start; align-items: flex-start; gap: 8px; display: flex">
          <div class="Frame12" style="align-self: stretch; flex-direction: column; justify-content: center; align-items: flex-start; gap: 16px; display: flex">
            <a href="/UFFBank/Deposit" class="Sections" style="align-self: stretch; padding: 12px; border-radius: 16px; justify-content: flex-start; align-items: center; gap: 12px; display: inline-flex">
              <div class="Pagamentos" style="color: #676767; font-size: 16px; font-weight: 400; word-wrap: break-word">Depósito</div>
            </a>

            <a href="/UFFBank/Saque" class="Sections" style="align-self: stretch; padding: 12px; background: #D9E7E4; border-radius: 16px; justify-content: flex-start; align-items: center; gap: 8px; display: inline-flex">
              <div class="Pagamentos" style="color: #26413C; font-size: 16px; font-weight: 600; word-wrap: break-word">Saque</div>
            </a>

            <a href="/UFFBank/Investment" class="Sections" style="align-self: stretch; padding: 12px; border-radius: 16px; justify-content: flex-start; align-items: center; gap: 12px; display: inline-flex">
              <div class="Pagamentos" style="color: #676767; font-size: 16px; font-weight: 400; word-wrap: break-word">Investimentos</div>
            </a>
            <a href="/UFFBank/Transfer" class="Sections" style="align-self: stretch; padding: 12px; border-radius: 16px; justify-content: flex-start; align-items: center; gap: 12px; display: inline-flex">
              <div class="Pagamentos" style="color: #676767; font-size: 16px; font-weight: 400; word-wrap: break-word">Transferências</div>
            </a>
          </div>
        </div>
    </div>

    <a href="#login" class="Frame36" style="align-self: stretch; height: 48px; flex-direction: column; justify-content: flex-start; align-items: flex-start; gap: 16px; display: flex; ">
      <div class="Sections" style="width: 192px; padding: 12px; border-radius: 16px; justify-content: flex-start; align-items: center; gap: 12px; display: inline-flex">
        <div class="Pagamentos" style="color: #676767; font-size: 16px; font-weight: 400; word-wrap: break-word">Sair</div>
      </div>
    </a>
  </div>

  <div>
    <div class="Frame37" style="width: 100%; border-bottom: 1px #E3E2E2 solid; justify-content: space-between; align-items: flex-start; display: inline-flex; margin-top: 20px;">
      <div class="BemVindoIgor" ><span style="color: black; font-size: 32px; font-weight: 300; line-height: 40px; word-wrap: break-word">Bem vindo,</span><span style="color: black; font-size: 32px; font-weight: 600; line-height: 40px; word-wrap: break-word"> Igor</span></div>
      <div class="R54226" style="width: 167px; height: 48px"><span style="color: black; font-size: 32px; font-weight: 400; line-height: 40px; word-wrap: break-word">R$</span><span style="color: black; font-size: 32px; font-weight: 700; line-height: 40px; word-wrap: break-word"> 542,26</span></div>
    </div>
    
    <div class="Frame39" style=" display: flex; width: 100%;flex-direction: column; justify-content: center; align-items: center; gap: 40px; display: inline-flex">
      <div class="TransferNcias" style="margin-top: 56px; color: black; font-size: 32px; font-weight: 500; line-height: 40px; word-wrap: break-word">Saque</div>
      <form class="Frame23" style="flex-direction: column; justify-content: center; align-items: center; gap: 24px; display: flex">
        <div class="Frame38" style="flex-direction: column; justify-content: center; align-items: center; gap: 32px; display: flex">
          <div class="Input" style="width: 454px; height: 88px; flex-direction: column; justify-content: flex-start; align-items: flex-start; gap: 5px; display: flex">
            <div class="Frame20" style="align-self: stretch; flex: 1 1 0; flex-direction: column; justify-content: flex-start; align-items: flex-start; gap: 10px; display: flex">
              <label for="amount" class="Label" style=" justify-content: flex-start; align-items: flex-start; gap: 10px; display: inline-flex">
                <div class="Label" style="color: #111928; font-size: 18px; font-family: Inter; font-weight: 500; line-height: 24px; word-wrap: break-word">Quanto você deseja sacar?</div>
              </label>

              <input id="amount" name="valor_enviado" class="Placeholder" placeholder="0,00" style="cursor: pointer; outline: none; align-self: stretch; flex: 1 1 0; padding-top: 12px; padding-bottom: 12px; padding-left: 20px; padding-right: 16px; background: white; border-radius: 6px; border: 1px #B4B9BF solid; justify-content: flex-start; align-items: center; gap: 10px; display: inline-flex">
            </div>
          </div>
          </div>

          <input type="submit" value="Sacar" class="Frame21" style="width: 454px; height: 54px; background: #35BBA2; border-radius: 6px; justify-content: center; align-items: center; display: inline-flex; cursor: pointer; outline: none;
          text-align: center; color: #FAFAFA; font-size: 18px; font-family: Inter; font-weight: 500; line-height: 30px; word-wrap: break-word;
          ">
        </form>
      
      </div>
    </div>

</div>
</body>
</html>