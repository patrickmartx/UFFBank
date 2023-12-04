/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.services;

import dev.entity.InvestmentWallet;
import java.util.ArrayList;

/**
 *
 * @author Patrick
 */
public interface InvestmentWalletService {
    public InvestmentWallet getById(Long id);
    public ArrayList<InvestmentWallet> getAll();
    public void insert();
    public void update(Double amountInvested, Long id);
    public void deleteById(Long id);
    public InvestmentWallet getWalletWithLastId();
}
