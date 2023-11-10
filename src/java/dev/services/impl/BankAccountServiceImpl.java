/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.services.impl;

import dev.model.complements.BankAccountRepository;
import dev.services.BankAccountService;
import dev.entity.BankAccount;

/**
 *
 * @author Patrick
 */
public class BankAccountServiceImpl implements BankAccountService {
    
    BankAccountRepository bankAccountRepository;
    
    public BankAccountServiceImpl(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    @Override
    public void save(Double saldo, Integer agencia, String numConta) {
        try{
            BankAccount conta = new BankAccount();

            conta.setSaldo(saldo);
            conta.setAgencia(agencia);
            conta.setNumConta(numConta);

            bankAccountRepository.insertBankAccount(conta);
        } catch (Exception e) {
            System.out.println("Não foi possível criar a conta\nmessage: " + e.getMessage());
        }
    }
    
}
