/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.entity;

import dev.utils.Status;
import java.util.Date;
import dev.utils.TransactionType;

/**
 *
 * @author Patrick
 */
public class TransactionHistory {

    private Long id;
    private Double value;
    private Date transactionDate;
    private TransactionType transactionType;
    private Long idOtherAccount;
    private Status status;

    public TransactionHistory() {
    }

    public TransactionHistory(Long id, Double value, Date transactionDate,
            TransactionType transactionType, Long idOtherAccount, Status status) {
        this.id = id;
        this.value = value;
        this.transactionDate = transactionDate;
        this.transactionType = transactionType;
        this.idOtherAccount = idOtherAccount;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public Long getIdOtherAccount() {
        return idOtherAccount;
    }

    public void setIdOtherAccount(Long idOtherAccount) {
        this.idOtherAccount = idOtherAccount;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
