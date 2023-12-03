/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.entity;

/**
 *
 * @author Patrick
 */
public class InvestmentWallet {
    
    private Long id;
    private Double amountInvested;
    private Double yieldPercentage;

    public InvestmentWallet() {
    }

    public InvestmentWallet(Long id, Double amountInvested, Double yieldPercentage) {
        this.id = id;
        this.amountInvested = amountInvested;
        this.yieldPercentage = yieldPercentage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getAmountInvested() {
        return amountInvested;
    }

    public void setAmountInvested(Double amountInvested) {
        this.amountInvested = amountInvested;
    }

    public Double getYieldPercentage() {
        return yieldPercentage;
    }

    public void setYieldPercentage(Double yieldPercentage) {
        this.yieldPercentage = yieldPercentage;
    }
    
    
}
