/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.services;
import dev.entity.BankAccount;
import dev.utils.Status;
import java.util.ArrayList;

/**
 *
 * @author Patrick
 */
public interface BankAccountService {
    
    public BankAccount getById(Long id);
    public ArrayList<BankAccount> getAll();
    public void insert(Integer bankNumber, String accountNumber, Long investmentWalletId);
    public void update(Double accountBalance, Integer bankNumber, String accountNumber);
    public void deleteById(Long id);
    public BankAccount getAccountByBankNumberAndAccountNumber(Integer bankNumber, String accountNumber);
}