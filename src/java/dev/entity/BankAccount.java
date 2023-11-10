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
    private Double saldo;
    private Integer agencia;
    private String numConta;

    public BankAccount() {
    }

    public BankAccount(Long id, Double saldo, Integer agencia, String numConta) {
        this.id = id;
        this.saldo = saldo;
        this.agencia = agencia;
        this.numConta = numConta;
    }

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public Integer getAgencia() {
        return agencia;
    }

    public void setAgencia(Integer agencia) {
        this.agencia = agencia;
    }

    public String getNumConta() {
        return numConta;
    }

    public void setNumConta(String numConta) {
        this.numConta = numConta;
    }
    
    
    
}
