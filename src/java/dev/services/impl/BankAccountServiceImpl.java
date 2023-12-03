/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.services.impl;

import dev.model.complements.BankAccountRepository;
import dev.services.BankAccountService;
import dev.entity.BankAccount;
import dev.exceptions.NoConnectException;
import dev.exceptions.NoEntityFoundException;
import dev.utils.Status;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Patrick
 */
public class BankAccountServiceImpl implements BankAccountService {
    BankAccountRepository repository;
    
    
    public BankAccountServiceImpl() {
        this.repository = new BankAccountRepository();
    }

    @Override
    public BankAccount getById(Long id) {
        try{
            BankAccount searchedBankAccount = repository.get(id);
            
            if(searchedBankAccount.getBankNumber() == null || searchedBankAccount.getAccountNumber() == null) {
                throw new NoEntityFoundException("Não existe essa conta no banco.");
            }
            return searchedBankAccount;
        } catch(Exception ex) {
            Logger.getLogger(BankAccountServiceImpl.class.getName()).log(Level.SEVERE, "Mensagem: " + ex.getMessage(), ex);
            throw ex;
        }
    }

    @Override
    public ArrayList<BankAccount> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insert(Integer bankNumber, String accountNumber) {
        try{
            BankAccount bankAccount = new BankAccount();
        
            bankAccount.setAccountBalance(0.00);
            bankAccount.setAccountNumber(accountNumber);
            bankAccount.setBankNumber(bankNumber);
            bankAccount.setStatus(Status.ACTIVE);
        
            repository.insert(bankAccount);
        } catch (Exception ex) {
            Logger.getLogger(BankAccountServiceImpl.class.getName()).log(Level.SEVERE, "Mensagem: " + ex.getMessage(), ex);
        }
    }

    @Override
    public void update(Double accountBalance, Integer bankNumber, String accountNumber) {
        try{
            BankAccount bankAccount = getAccountByBankNumberAndAccountNumber(bankNumber, accountNumber);
        
            bankAccount.setAccountBalance(accountBalance);
            bankAccount.setAccountNumber(bankAccount.getAccountNumber());
            bankAccount.setBankNumber(bankAccount.getBankNumber());
            bankAccount.setStatus(Status.ACTIVE);
        
            repository.update(bankAccount);
        } catch (Exception ex) {
            Logger.getLogger(BankAccountServiceImpl.class.getName()).log(Level.SEVERE, "Mensagem: " + ex.getMessage(), ex);
        }
    }

    @Override
    public void deleteById(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    public BankAccount getAccountByBankNumberAndAccountNumber(Integer bankNumber, String accountNumber) {
        try{
            BankAccount searchedBankAccount = repository.getAccountByBankNumberAndAccountNumber(bankNumber, accountNumber);
            
            if(searchedBankAccount.getBankNumber() == null || searchedBankAccount.getAccountNumber() == null) {
                throw new NoEntityFoundException("Não existe essa conta no banco.");
            }
            return searchedBankAccount;
        } catch(Exception ex) {
            Logger.getLogger(BankAccountServiceImpl.class.getName()).log(Level.SEVERE, "Mensagem: " + ex.getMessage(), ex);
            throw ex;
        }
    }
}
