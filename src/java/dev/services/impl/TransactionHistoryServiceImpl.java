/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.services.impl;

import dev.entity.TransactionHistory;
import dev.model.complements.TransactionHistoryRepository;
import dev.services.TransactionHistoryService;
import dev.utils.Status;
import dev.utils.TransactionType;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Patrick
 */
public class TransactionHistoryServiceImpl implements TransactionHistoryService{
    private TransactionHistoryRepository repository;
    
    public TransactionHistoryServiceImpl() {
        this.repository = new TransactionHistoryRepository();
    }

    @Override
    public TransactionHistory getById(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<TransactionHistory> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insert(Double value, Date transactionDate, Long idSenderAccount, Long idReceiverAccount) {
        try {
            TransactionHistory transactionHistory = new TransactionHistory();
            
            transactionHistory.setValue(value);
            transactionHistory.setTransactionDate(transactionDate);
            transactionHistory.setTransactionType(TransactionType.TRANSFER);
            transactionHistory.setSenderAccountId(idSenderAccount);
            transactionHistory.setReceiverAccountId(idReceiverAccount);
            transactionHistory.setStatus(Status.ACTIVE);
            
            System.out.println(transactionHistory.getValue() + " " + transactionHistory.getTransactionDate().getTime() + " " +
                    transactionHistory.getSenderAccountId() + " " + transactionHistory.getReceiverAccountId() + " " + transactionHistory.getTransactionType().getValue() + " " +
                    transactionHistory.getStatus().getValue());
            
            repository.insert(transactionHistory);
        } catch (Exception ex) {
            throw new RuntimeException("Falha ao criar histórico de transação!" + ex.getClass() + " - " + ex.getMessage());
        }
    }

    @Override
    public void update(Double value, Date transactionDate, TransactionType transactionType, Long idSenderAccount, Long idReceiverAccount, Status status) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void deleteById(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    public void deposit(Double value, Date transactionDate, Long idSenderAccount) {
        try {
            TransactionHistory transactionHistory = new TransactionHistory();
            
            transactionHistory.setValue(value);
            transactionHistory.setTransactionDate(transactionDate);
            transactionHistory.setTransactionType(TransactionType.DEPOSIT);
            transactionHistory.setSenderAccountId(idSenderAccount);
            transactionHistory.setStatus(Status.ACTIVE);
            
            System.out.println(transactionHistory.getValue() + transactionHistory.getTransactionDate().getTime() +
                    transactionHistory.getSenderAccountId() + transactionHistory.getTransactionType().getValue() +
                    transactionHistory.getStatus().getValue());
            
            repository.deposit(transactionHistory);
        } catch (Exception ex) {
            throw new RuntimeException("Falha ao criar histórico de transação!" + ex.getClass() + " - " + ex.getMessage());
        }
    }
}
