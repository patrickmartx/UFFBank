/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.services.impl;

import dev.entity.BankAccount;
import dev.entity.InvestmentWallet;
import dev.exceptions.NoEntityFoundException;
import dev.model.complements.InvestmentWalletRepository;
import dev.services.InvestmentWalletService;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Patrick
 */
public class InvestmentWalletServiceImpl implements InvestmentWalletService {
    private InvestmentWalletRepository repository;
    private Double yieldPercentage = 0.01;
    
    public InvestmentWalletServiceImpl() {
        this.repository = new InvestmentWalletRepository();
    }

    @Override
    public InvestmentWallet getById(Long id) {
        try{
            InvestmentWallet searchedInvestmentWallet = repository.get(id);
            
            if(searchedInvestmentWallet.getId() == null) {
                throw new NoEntityFoundException("Não existe essa conta no banco.");
            }
            return searchedInvestmentWallet;
        } catch(Exception ex) {
            Logger.getLogger(InvestmentWalletServiceImpl.class.getName()).log(Level.SEVERE, "Mensagem: " + ex.getMessage(), ex);
            throw ex;
        }
    }

    @Override
    public ArrayList<InvestmentWallet> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insert() {
        try{
            InvestmentWallet newInvestmentWallet = new InvestmentWallet();
        
            newInvestmentWallet.setAmountInvested(0.00);
            newInvestmentWallet.setYieldPercentage(this.yieldPercentage);
        
            repository.insert(newInvestmentWallet);
        } catch (Exception ex) {
            Logger.getLogger(InvestmentWalletServiceImpl.class.getName()).log(Level.SEVERE, "Mensagem: " + ex.getMessage(), ex);
        }
    }

    @Override
    public void update(Double amountInvested, Long id) {
        try{
            InvestmentWallet newInvestmentWallet = getById(id);
        
            newInvestmentWallet.setAmountInvested(amountInvested);
            newInvestmentWallet.setYieldPercentage(this.yieldPercentage);
        
            repository.update(newInvestmentWallet);
        } catch (Exception ex) {
            Logger.getLogger(InvestmentWalletServiceImpl.class.getName()).log(Level.SEVERE, "Mensagem: " + ex.getMessage(), ex);
        }
    }

    @Override
    public void deleteById(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    public InvestmentWallet getWalletWithLastId(){
         try{
            InvestmentWallet searchedInvestmentWallet = repository.getByLastId();
             System.out.println(searchedInvestmentWallet.getId() + " by service");
            
            if(searchedInvestmentWallet.getId() == null) {
                throw new NoEntityFoundException("Não existe essa conta no banco.");
            }
            return searchedInvestmentWallet;
        } catch(Exception ex) {
            Logger.getLogger(InvestmentWalletServiceImpl.class.getName()).log(Level.SEVERE, "Mensagem: " + ex.getMessage(), ex);
            throw ex;
        }
    }
    
}
