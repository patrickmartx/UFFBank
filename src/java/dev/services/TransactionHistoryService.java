/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.services;

import dev.entity.TransactionHistory;
import dev.utils.Status;
import dev.utils.TransactionType;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Patrick
 */
public interface TransactionHistoryService {
    
    public TransactionHistory getById(Long id);
    public ArrayList<TransactionHistory> getAll();
    public void insert(Date transactionDate, TransactionType transactionType, Long idOtherAccount, Status status);
    public void update(Date transactionDate, TransactionType transactionType, Long idOtherAccount, Status status);
    public void deleteById(Long id);
}