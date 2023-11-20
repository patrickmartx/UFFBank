/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.services;

/**
 *
 * @author Patrick
 */
public interface BankAccountService {
    
    public void save(Double saldo, Integer agencia, String numConta);
    public Long idByNumber(Integer agencia, String number);
}
