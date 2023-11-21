/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.services;
import dev.entity.BankAccount;

/**
 *
 * @author Patrick
 */
public interface BankAccountService {
    
    public void save(Double accountBalance, Integer bankNumber, String accountNumber);
    public BankAccount getAccountById(Long id);
    public Long getIdByAccount(Integer bankNumber, String accountNumber);
    public Double getSaldoById(Long id);
    public void transfer(Double accountBalance, Integer bankNumberSender, String accountNumberSender, 
                                  Integer bankNumberReceiver, String accountNumberReceiver);
    public void deposit(Double accountBalance, Integer bankNumber, String accountNumber);
}
