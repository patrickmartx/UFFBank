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
    public void save(Double accountBalance, Integer bankNumber, String accountNumber) {
        try{
            BankAccount conta = new BankAccount();

            conta.setAccountBalance(accountBalance);
            conta.setBankNumber(bankNumber);
            conta.setAccountNumber(accountNumber);

            repository.insertBankAccount(conta);
        } catch (Exception e) {
            System.out.println("Não foi possível criar a conta\nmessage: " + e.getMessage());
        }
    }

    @Override
    public Long getIdByAccount(Integer bankNumber, String accountNumber) {
        try {
            return repository.getIdByAccount(bankNumber, accountNumber);
        } catch (NoConnectException ex) {
            Logger.getLogger(BankAccountServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void transferByAccount(Double value, Integer bankNumberSender, String accountNumberSender, 
                                  Integer bankNumberReceiver, String accountNumberReceiver) {
        try {
            Long idSenderAccount = repository.getIdByAccount(bankNumberSender, accountNumberSender);
            Double balanceSenderAccount = repository.getSaldoById(idSenderAccount);
            
            if (value > balanceSenderAccount) {
                throw new IllegalArgumentException("Valor de transferência maior do que o saldo!");
            }
            
            repository.transfer(value, bankNumberSender, accountNumberSender, bankNumberReceiver, accountNumberReceiver);
        } catch (Exception ex) {
            Logger.getLogger(BankAccountServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void deposit(Double value, Integer bankNumber, String accountNumber) {
        try {
            repository.deposit(value, bankNumber, accountNumber);
        }
        catch(NoConnectException ex) {
            Logger.getLogger(BankAccountServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
