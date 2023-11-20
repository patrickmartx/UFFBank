/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.services.impl;

import dev.model.complements.BankAccountRepository;
import dev.services.BankAccountService;
import dev.entity.BankAccount;
import dev.exceptions.NoConnectException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Patrick
 */
public class BankAccountServiceImpl implements BankAccountService {
    
    BankAccountRepository repository;
    
    public BankAccountServiceImpl(BankAccountRepository bankAccountRepository) {
        this.repository = bankAccountRepository;
    }

    @Override
    public void save(Double saldo, Integer agencia, String numConta) {
        try{
            BankAccount conta = new BankAccount();

            conta.setSaldo(saldo);
            conta.setAgencia(agencia);
            conta.setNumConta(numConta);

            repository.insertBankAccount(conta);
        } catch (Exception e) {
            System.out.println("Não foi possível criar a conta\nmessage: " + e.getMessage());
        }
    }

    @Override
    public Long idByNumber(Integer agencia, String number) {
        try {
            return repository.idByNumber(agencia, number);
        } catch (NoConnectException ex) {
            Logger.getLogger(BankAccountServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
