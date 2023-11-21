/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.entity;

/**
 *
 * @author Patrick
 */
public class BankAccount {
    
    private Long id;
    private Double accountBalance;
    private Integer bankNumber;
    private String accountNumber;

    public BankAccount() {
    }

    public BankAccount(Long id, Double accountBalance, Integer bankNumber, String accountNumber) {
        this.id = id;
        this.accountBalance = accountBalance;
        this.bankNumber = bankNumber;
        this.accountNumber = accountNumber;
    }

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(Double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public Integer getBankNumber() {
        return bankNumber;
    }

    public void setBankNumber(Integer bankNumber) {
        this.bankNumber = bankNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
    
    
    
}
